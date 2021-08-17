package com.test.pruebaGestioLogistica.services;

import com.test.pruebaGestioLogistica.entities.Producto;

import java.util.List;

public interface IServiceProducto {

    public List<Producto> listAll();

    public Producto save(Producto producto);

    public Producto findById(Long id);

    public void delete(Long id);
}
