package com.test.pruebaGestioLogistica.services;

import com.test.pruebaGestioLogistica.entities.Almacen;
import java.util.List;

public interface IServiceAlmacen {

    public List<Almacen> listAll();

    public List<Almacen> obtenerConNombreCliente();

    public List<Almacen> listByClient(Long id);

    public Integer insertar(Almacen almacen);

    public Integer update(Almacen almacen);

    public Almacen save(Almacen almacen);

    public Almacen findById(Long id);

    public Almacen findById2(Long id);

    public void delete(Long id);
}
