package com.test.pruebaGestioLogistica.services.impl;

import com.test.pruebaGestioLogistica.entities.Entrega;
import com.test.pruebaGestioLogistica.repository.IEntregaRepository;
import com.test.pruebaGestioLogistica.repository.IProductoRepository;
import com.test.pruebaGestioLogistica.services.IServiceEntrega;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("serviceEntrega")
public class EntregaServiceImpl implements IServiceEntrega {

    @Autowired
    private IEntregaRepository iEntregaRepository;

    @Override
    public List<Entrega> listAll() {
        return (List<Entrega>) iEntregaRepository.findAll();
    }

    @Override
    public Entrega save(Entrega entrega) {
        return iEntregaRepository.save(entrega);
    }
    

    @Override
    public Entrega findById(Long id) {
        return iEntregaRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        iEntregaRepository.deleteById(id);
    }
}
