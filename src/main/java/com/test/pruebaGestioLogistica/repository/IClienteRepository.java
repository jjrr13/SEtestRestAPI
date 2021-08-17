package com.test.pruebaGestioLogistica.repository;

import com.test.pruebaGestioLogistica.entities.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface IClienteRepository extends CrudRepository<Cliente, Long> {
}
