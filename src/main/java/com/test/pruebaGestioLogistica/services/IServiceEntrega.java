package com.test.pruebaGestioLogistica.services;

import com.test.pruebaGestioLogistica.entities.Entrega;

import java.util.List;

public interface IServiceEntrega {

    public List<Entrega> listAll();

    public Entrega save(Entrega entrega);

    public Entrega findById(Long id);

    public void delete(Long id);

    public boolean validateIdentificacion(String tipoEntrega, String identificacion);

    public Double generateDiscount(String tipoEntrega, Integer cantidad_productos);

}
