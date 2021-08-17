package com.test.pruebaGestioLogistica.repository;

import com.test.pruebaGestioLogistica.entities.Rol;
import org.springframework.data.repository.CrudRepository;

public interface IRolRepository extends CrudRepository<Rol, Long> {
    //public Rol findByUsername(String username);
}
