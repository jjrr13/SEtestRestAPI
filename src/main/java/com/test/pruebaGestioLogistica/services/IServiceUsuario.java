package com.test.pruebaGestioLogistica.services;

import com.test.pruebaGestioLogistica.entities.Usuario;
import com.test.pruebaGestioLogistica.entities.*;


import java.util.List;

public interface IServiceUsuario {

    public List<Usuario> listAll();

    public Usuario save(Usuario usuario);

    public Usuario findById(Long id);

    public void delete(Long id);

}
