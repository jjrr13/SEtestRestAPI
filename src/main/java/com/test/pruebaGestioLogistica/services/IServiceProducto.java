package com.test.pruebaGestioLogistica.services;

import com.test.pruebaGestioLogistica.entities.Producto;

import java.util.List;

public interface IServiceProducto {

    public List<Producto> listAll();

    public List<Producto> obtenerConNombreCliente();

    public List<Producto> listByClient(Long id);

    public Integer insertar(Producto producto);

    public Integer update(Producto producto);

    public Producto save(Producto producto);

    public Producto findById(Long id);

    public Producto findById2(Long id);

    public void delete(Long id);
}
