package com.test.pruebaGestioLogistica.repository;

import com.test.pruebaGestioLogistica.entities.Usuario;
import com.test.pruebaGestioLogistica.entities.*;
import org.springframework.data.repository.CrudRepository;

public interface IUsuarioRepository extends CrudRepository<Usuario, Long> {
    /*
        estos metodos ya vienen por debajo de la interfaz de CrudRepository
        public UsuarioGenerico findById(Long id);
        public List<UsuarioGenerico> listAll();
        public void save(UsuarioGenerico usuarioGenerico);
        public void delete(Long id);
    */

    public Usuario findByUsername(String username);

}
