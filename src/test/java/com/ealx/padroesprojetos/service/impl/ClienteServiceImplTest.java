package com.ealx.padroesprojetos.service.impl;

import com.ealx.padroesprojetos.model.Cliente;
import com.ealx.padroesprojetos.model.Endereco;
import com.ealx.padroesprojetos.repository.ClienteRepository;
import com.ealx.padroesprojetos.repository.EnderecoRepository;
import com.ealx.padroesprojetos.service.ViaCepService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ClienteServiceImplTest {

    @Mock
    private ClienteRepository clienteRepository;

    @Mock
    private EnderecoRepository enderecoRepository;

    @Mock
    private ViaCepService viaCepService;

    @InjectMocks
    private ClienteServiceImpl clienteService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testInserirClienteComEnderecoNovo() {
        String cep = "12345678";
        Endereco endereco = new Endereco();
        endereco.setCep(cep);
        Cliente cliente = new Cliente();
        cliente.setNome("Alex");
        cliente.setEndereco(endereco);

        // Simula que o endereço não existe no repo
        when(enderecoRepository.findById(cep)).thenReturn(java.util.Optional.empty());
        // Simula que a consulta viaCepService retorna o endereço
        when(viaCepService.consultarCep(cep)).thenReturn(endereco);
        when(enderecoRepository.save(endereco)).thenReturn(endereco);
        when(clienteRepository.save(cliente)).thenReturn(cliente);

        clienteService.inserir(cliente);

        verify(enderecoRepository).save(endereco);
        verify(clienteRepository).save(cliente);
    }

    @Test
    void testInserirClienteComCepInexistente() {
        String cep = "00000000";
        Cliente cliente = new Cliente();
        cliente.setNome("Teste Erro");
        Endereco endereco = new Endereco();
        endereco.setCep(cep);
        cliente.setEndereco(endereco);

        /// 2. Mocks
        when(enderecoRepository.findById(cep)).thenReturn(java.util.Optional.empty());

        // Simula o erro
        when(viaCepService.consultarCep(cep)).thenThrow(new RuntimeException("CEP inválido: " + cep));

        // 3. Execução e Validação
        assertThrows(RuntimeException.class, () -> {
            clienteService.inserir(cliente);
        });

        // Verifica que o cliente NUNCA foi salvo, pois o erro interrompeu o fluxo
        verify(clienteRepository, never()).save(any(Cliente.class));
    }

    @Test
    void testDeletarCliente() {
        Long id = 1L;
        when(clienteRepository.existsById(id)).thenReturn(true);
        clienteService.deletar(id);
        verify(clienteRepository, times(1)).deleteById(id);
    }

}
