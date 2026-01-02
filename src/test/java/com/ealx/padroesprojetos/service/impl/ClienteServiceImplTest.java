package com.ealx.padroesprojetos.service.impl;

import com.ealx.padroesprojetos.model.Cliente;
import com.ealx.padroesprojetos.model.Endereco;
import com.ealx.padroesprojetos.repository.ClienteRepository;
import com.ealx.padroesprojetos.repository.EnderecoRepository;
import com.ealx.padroesprojetos.service.ViaCepService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

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
}
