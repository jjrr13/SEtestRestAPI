package com.test.pruebaGestioLogistica.services.impl;

import com.test.pruebaGestioLogistica.entities.TipoLogisticaOld;
import com.test.pruebaGestioLogistica.repository.ITipoLogisticaRepository;
import com.test.pruebaGestioLogistica.services.IServiceTipoLogistica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("serviceTipoLogistica")
public class TipoLogisticaServiceImple implements IServiceTipoLogistica {

    @Autowired
    private ITipoLogisticaRepository iTipoLogisticaRepository;

    @Override
    public List<TipoLogisticaOld> listAll() {
        return (List<TipoLogisticaOld>) iTipoLogisticaRepository.findAll();
    }

    @Override
    public TipoLogisticaOld save(TipoLogisticaOld tipoLogisticaOld) {
        return iTipoLogisticaRepository.save(tipoLogisticaOld);
    }

    @Override
    public TipoLogisticaOld findById(Long id) {
        return iTipoLogisticaRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        iTipoLogisticaRepository.deleteById(id);
    }
}
