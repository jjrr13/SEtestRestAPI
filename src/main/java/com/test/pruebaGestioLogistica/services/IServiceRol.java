package com.test.pruebaGestioLogistica.services;

import com.test.pruebaGestioLogistica.entities.Rol;

import java.util.List;

public interface IServiceRol {
    public List<Rol> listAll();

    public Rol save(Rol rol);

    public Rol findById(Long id);

    public void delete(Long id);
}
