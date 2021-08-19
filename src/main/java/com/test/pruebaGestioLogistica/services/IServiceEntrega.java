package com.test.pruebaGestioLogistica.services;

import com.test.pruebaGestioLogistica.entities.Entrega;

import java.util.List;

public interface IServiceEntrega {

    public List<Entrega> listAll();

    public Entrega save(Entrega entrega);

    public List<Entrega> obtenerEntregasConNombres();

    public List<Entrega> listByClient(Long id);

    public Long insertar(Entrega entrega);

    public Integer update(Entrega entrega);

    public Entrega findById2(Long id);

    public Entrega findById(Long id);

    public void delete(Long id);

    public boolean validateIdentificacion(String tipoEntrega, String identificacion);

    public Double generateDiscount(String tipoEntrega, Integer cantidad_productos);

}
