package com.test.pruebaGestioLogistica.services.impl;

import com.test.pruebaGestioLogistica.entities.Almacen;
import com.test.pruebaGestioLogistica.repository.IAlmacenRepository;
import com.test.pruebaGestioLogistica.repository.IAlmacenRepository;
import com.test.pruebaGestioLogistica.services.IServiceAlmacen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("serviceAlmacen")
public class AlmacenServiceImpl implements IServiceAlmacen {

    @Autowired
    private IAlmacenRepository iAlmacenRepository;

    @Override
    public List<Almacen> listAll() {
        return (List<Almacen>) iAlmacenRepository.findAll();
    }

    @Override
    public List<Almacen> obtenerConNombreCliente() {
        return (List<Almacen>) iAlmacenRepository.obtenerConNombreCliente();
    }

    @Override
    public List<Almacen> listByClient(Long id) {
        return (List<Almacen>) iAlmacenRepository.listByClient(id);
    }

    @Override
    public Integer insertar(Almacen almacen) {
        return iAlmacenRepository.insertar(
                almacen.getNombre(),
                almacen.getDescripcion(),
                almacen.getTipoLogistica().ordinal(),
                almacen.getFk_id_cliente(),
                new Date()
        );
    }

    @Override
    public Almacen findById2(Long id) {
        return iAlmacenRepository.findById2(id);
    }

    @Override
    public Integer update(Almacen almacen) {
        return  iAlmacenRepository.update(
                almacen.getId(),
                almacen.getNombre(),
                almacen.getDescripcion(),
                almacen.getTipoLogistica().ordinal(),
                new Date()
        );
    }

    @Override
    public Almacen save(Almacen almacen) {
        return iAlmacenRepository.save(almacen);
    }

    @Override
    public Almacen findById(Long id) {
        return iAlmacenRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        iAlmacenRepository.deleteById(id);
    }
    
}
