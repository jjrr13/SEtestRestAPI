package com.test.pruebaGestioLogistica.services.impl;


import com.test.pruebaGestioLogistica.entities.Cliente;
import com.test.pruebaGestioLogistica.exceptions.BadRequestException;
import com.test.pruebaGestioLogistica.exceptions.DontFindElementException;
import com.test.pruebaGestioLogistica.repository.IUsuarioRepository;
import com.test.pruebaGestioLogistica.entities.Usuario;
import com.test.pruebaGestioLogistica.services.IServiceUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("serviceUsers")
public class UsuarioServiceImple implements IServiceUsuario {

    private IUsuarioRepository iUsuarioRepository;

    @Autowired
    public UsuarioServiceImple(IUsuarioRepository iUsuarioRepository) {
        this.iUsuarioRepository = iUsuarioRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listAll() {

        return (List<Usuario>) iUsuarioRepository.findAll();
    }


    @Override
    public Usuario save(Usuario Usuario) {
        return iUsuarioRepository.save(Usuario);
    }

    @Override
    @Transactional(readOnly = true) // vuelve el objeto una transaccion
    public Usuario findById(Long id) {
        return iUsuarioRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        iUsuarioRepository.deleteById(id);
    }
}
