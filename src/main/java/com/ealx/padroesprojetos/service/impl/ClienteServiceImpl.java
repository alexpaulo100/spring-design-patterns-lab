package com.ealx.padroesprojetos.service.impl;

import com.ealx.padroesprojetos.exception.ResourceNotFoundException;
import com.ealx.padroesprojetos.model.Cliente;
import com.ealx.padroesprojetos.model.Endereco;
import com.ealx.padroesprojetos.repository.ClienteRepository;
import com.ealx.padroesprojetos.repository.EnderecoRepository;
import com.ealx.padroesprojetos.service.ClienteService;
import com.ealx.padroesprojetos.service.ViaCepService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Iterable<Cliente> buscarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente buscarPorId(Long id) {
        // Usando nossa exceção customizada aqui também!
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com ID: " + id));
    }

    @Override
    @Transactional
    public void inserir(Cliente cliente) {
        salvarClienteComCep(cliente);
    }

    @Override
    @Transactional
    public void atualizar(Long id, Cliente cliente) {
        // Busca o cliente existente
        Cliente clienteBd = buscarPorId(id);
        // Atualiza o nome
        clienteBd.setNome(cliente.getNome());
        // Prepara o endereço e salva
        salvarClienteComCep(clienteBd);
    }

    @Override
    public void deletar(Long id) {
        // Opcional: validar se o cliente existe antes de deletar
        if (!clienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Impossível deletar: Cliente não encontrado.");
        }
        clienteRepository.deleteById(id);
    }

    // MÉTODO PRIVADO PARA REUTILIZAÇÃO DE LÓGICA
    private void salvarClienteComCep(Cliente cliente) {
        if (cliente.getEndereco() == null || cliente.getEndereco().getCep() == null) {
            throw new IllegalArgumentException("CEP é obrigatório");
        }

        String cep = cliente.getEndereco().getCep();
        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            // Aqui usamos a nossa exceção customizada!
            if (novoEndereco == null || novoEndereco.getCep() == null) {
                throw new ResourceNotFoundException("CEP inválido ou não encontrado: " + cep);
            }
            return enderecoRepository.save(novoEndereco);
        });

        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }

}
