package com.ealx.padroesprojetos.service.impl;

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
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    @Override
    @Transactional
    public void inserir(Cliente cliente) {

        if (cliente.getEndereco() == null || cliente.getEndereco().getCep() == null) {
            throw new IllegalArgumentException("CEP é obrigatório");
        }

        String cep = cliente.getEndereco().getCep();

        Endereco endereco = enderecoRepository.findById(cep)
                .orElseGet(() -> {
                    Endereco novoEndereco = viaCepService.consultarCep(cep);
                    if (novoEndereco == null) {
                        throw new RuntimeException("CEP inválido: " + cep);
                    }
                    return enderecoRepository.save(novoEndereco);
                });

        cliente.setEndereco(endereco);
        clienteRepository.save(cliente);
    }

    @Override
    @Transactional
    public void atualizar(Long id, Cliente cliente) {

        Cliente clienteBd = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        if (cliente.getEndereco() == null || cliente.getEndereco().getCep() == null) {
            throw new IllegalArgumentException("CEP é obrigatório");
        }

        clienteBd.setNome(cliente.getNome());

        String cep = cliente.getEndereco().getCep();

        Endereco endereco = enderecoRepository.findById(cep)
                .orElseGet(() -> {
                    Endereco novoEndereco = viaCepService.consultarCep(cep);
                    if (novoEndereco == null) {
                        throw new RuntimeException("CEP inválido: " + cep);
                    }
                    return enderecoRepository.save(novoEndereco);
                });

        clienteBd.setEndereco(endereco);
        // NÃO precisa chamar save aqui por causa do @Transactional
    }

    @Override
    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }
}
