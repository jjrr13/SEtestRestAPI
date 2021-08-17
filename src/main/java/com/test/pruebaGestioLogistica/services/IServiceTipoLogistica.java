package com.test.pruebaGestioLogistica.services;

import com.test.pruebaGestioLogistica.entities.TipoLogisticaOld;

import java.util.List;

public interface IServiceTipoLogistica {
    public List<TipoLogisticaOld> listAll();

    public TipoLogisticaOld save(TipoLogisticaOld tipoLogisticaOld);

    public TipoLogisticaOld findById(Long id);

    public void delete(Long id);
}
