package com.test.pruebaGestioLogistica.repository;

import com.test.pruebaGestioLogistica.entities.TipoLogisticaOld;
import org.springframework.data.repository.CrudRepository;

public interface ITipoLogisticaRepository extends CrudRepository<TipoLogisticaOld, Long> {
}
