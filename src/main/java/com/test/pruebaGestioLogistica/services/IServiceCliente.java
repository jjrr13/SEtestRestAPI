package com.test.pruebaGestioLogistica.services;

import com.test.pruebaGestioLogistica.entities.Cliente;

import java.util.List;

public interface IServiceCliente {
    public List<Cliente> listAll();

    public Cliente save(Cliente cliente);

    public Cliente findById(Long id);

    public void delete(Long id);
}
