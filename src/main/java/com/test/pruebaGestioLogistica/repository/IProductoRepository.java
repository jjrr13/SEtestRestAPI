package com.test.pruebaGestioLogistica.repository;

import com.test.pruebaGestioLogistica.entities.Producto;
import org.springframework.data.repository.CrudRepository;

public interface IProductoRepository extends CrudRepository<Producto, Long> {
}
