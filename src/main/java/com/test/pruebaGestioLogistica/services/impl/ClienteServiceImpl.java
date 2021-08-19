package com.test.pruebaGestioLogistica.services.impl;

import com.test.pruebaGestioLogistica.repository.IClienteRepository;
import com.test.pruebaGestioLogistica.entities.Cliente;
import com.test.pruebaGestioLogistica.services.IServiceCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("serviceCliente")
public class ClienteServiceImpl implements IServiceCliente {

    @Autowired
    private IClienteRepository iClienteRepository;


    @Override
    public List<Cliente> listAll() {
        return (List<Cliente>) iClienteRepository.findAll();
    }

    @Override
    public List<Cliente> listSelect() {
        return (List<Cliente>) iClienteRepository.findAll();
    }

    @Override
    public Cliente save(Cliente cliente) {
        return iClienteRepository.save(cliente);
    }

    @Override
    public Cliente findById(Long id) {
        return iClienteRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        iClienteRepository.deleteById(id);
    }
}
