package com.test.pruebaGestioLogistica.services.impl;

import com.test.pruebaGestioLogistica.repository.IProductoRepository;
import com.test.pruebaGestioLogistica.entities.Producto;
import com.test.pruebaGestioLogistica.services.IServiceProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public List<Producto> obtenerConNombreCliente() {
        return (List<Producto>) iProductoRepository.obtenerConNombreCliente();
    }

    @Override
    public List<Producto> listByClient(Long id) {
        return (List<Producto>) iProductoRepository.listByClient(id);
    }

    @Override
    public Integer insertar(Producto producto) {
        return iProductoRepository.insertar(
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getTipoLogistica().ordinal(),
                producto.getFk_id_cliente(),
                new Date()
        );
    }

    @Override
    public Producto findById2(Long id) {
        return iProductoRepository.findById2(id);
    }

    @Override
    public Integer update(Producto producto) {
        return iProductoRepository.update(
                producto.getId(),
                producto.getNombre(),
                producto.getDescripcion(),
                producto.getTipoLogistica().ordinal()
        );
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
