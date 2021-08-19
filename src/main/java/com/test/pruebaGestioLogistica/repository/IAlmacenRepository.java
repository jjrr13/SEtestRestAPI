package com.test.pruebaGestioLogistica.repository;

import com.test.pruebaGestioLogistica.entities.Almacen;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface IAlmacenRepository extends CrudRepository<Almacen, Long> {


    @Query(value = "SELECT a.*, "
            + " c.nombres as nombre_cliente"
            + " FROM almacenes a "
            + " INNER JOIN clientes c ON a.fk_id_cliente = c.id "
            , nativeQuery=true)
    public List<Almacen> obtenerConNombreCliente();

    @Query(value = "SELECT a.*, "
            + " c.nombres as nombre_cliente"
            + " FROM almacenes a "
            + " INNER JOIN clientes c ON a.fk_id_cliente = c.id "
            + " WHERE c.id = :id "
            , nativeQuery=true)
    public List<Almacen> listByClient(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO almacenes ( nombre, descripcion, fk_id_tipo_logistica, fk_id_cliente ) " +
            " VALUES ( :nombre, :descripcion, :fk_id_tipo_logistica, :fk_id_cliente ) "
            , nativeQuery=true)
    public Integer insertar(
            @Param("nombre") String nombre,
            @Param("descripcion") String descripcion ,
            @Param("fk_id_tipo_logistica") Integer fk_id_tipo_logistica ,
            @Param("fk_id_cliente") Long fk_id_cliente

    );

    @Query(value = "SELECT a.*, "
            + " c.nombres as nombre_cliente"
            + " FROM almacenes a "
            + " INNER JOIN clientes c ON a.fk_id_cliente = c.id "
            + " WHERE a.id = :id "
            , nativeQuery=true)
    public Almacen findById2(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE almacenes SET nombre = :nombre, descripcion = :descripcion, fk_id_tipo_logistica = :fk_tipo_logistica  " +
            " WHERE id = :id  "
            , nativeQuery=true)
    public Integer update(
            @Param("id") Long id,
            @Param("nombre") String nombre,
            @Param("descripcion") String descripcion ,
            @Param("fk_tipo_logistica") Integer fk_tipo_logistica
    );

}
