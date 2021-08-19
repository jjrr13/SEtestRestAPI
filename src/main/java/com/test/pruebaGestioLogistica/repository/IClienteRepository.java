package com.test.pruebaGestioLogistica.repository;

import com.test.pruebaGestioLogistica.entities.Cliente;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IClienteRepository extends CrudRepository<Cliente, Long> {

    @Query(value = "SELECT c.id, c.nombres "
            + " FROM clientes c "
            , nativeQuery=true)
    public List<Cliente> listSelect();


}
