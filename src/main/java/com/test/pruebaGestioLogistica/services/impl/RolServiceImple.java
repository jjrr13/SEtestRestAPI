package com.test.pruebaGestioLogistica.services.impl;

import com.test.pruebaGestioLogistica.repository.IRolRepository;
import com.test.pruebaGestioLogistica.entities.Rol;
import com.test.pruebaGestioLogistica.services.IServiceRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("serviceRol")
public class RolServiceImple implements IServiceRol {

    @Autowired
    private IRolRepository iRolRepository;


    @Override
    @Transactional(readOnly = true)
    public List<Rol> listAll() {
        return (List<Rol>) iRolRepository.findAll();
    }

    @Override
    public Rol save(Rol rol) {
        return iRolRepository.save(rol);
    }

    @Override
    @Transactional(readOnly = true)
    public Rol findById(Long id) {
        return iRolRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        iRolRepository.deleteById(id);
    }
}
