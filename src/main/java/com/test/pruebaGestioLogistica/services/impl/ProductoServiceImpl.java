package com.test.pruebaGestioLogistica.services.impl;

import com.test.pruebaGestioLogistica.repository.IProductoRepository;
import com.test.pruebaGestioLogistica.entities.Producto;
import com.test.pruebaGestioLogistica.services.IServiceProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("serviceProducto")
public class ProductoServiceImpl implements IServiceProducto {

    @Autowired
    private IProductoRepository iProductoRepository;

    @Override
    public List<Producto> listAll() {
        return (List<Producto>) iProductoRepository.findAll();
    }

    @Override
    public Producto save(Producto producto) {
        return iProductoRepository.save(producto);
    }

    @Override
    public Producto findById(Long id) {
        return iProductoRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        iProductoRepository.deleteById(id);
    }
}
