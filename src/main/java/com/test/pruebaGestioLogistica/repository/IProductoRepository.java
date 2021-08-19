package com.test.pruebaGestioLogistica.repository;

import com.test.pruebaGestioLogistica.entities.Producto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface IProductoRepository extends CrudRepository<Producto, Long> {

    @Query(value = "SELECT p.*, "
            + " c.nombres as nombre_cliente"
            + " FROM productos p "
            + " INNER JOIN clientes c ON p.fk_id_cliente = c.id "
        , nativeQuery=true)
    public List<Producto> obtenerConNombreCliente();

    @Query(value = "SELECT p.*, "
            + " c.nombres as nombre_cliente"
            + " FROM productos p "
            + " INNER JOIN clientes c ON p.fk_id_cliente = c.id "
            + " WHERE c.id = :id "
            , nativeQuery=true)
    public List<Producto> listByClient(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO productos ( nombre, descripcion, fk_tipo_logistica, fk_id_cliente, create_at ) " +
            " VALUES ( :nombre, :descripcion, :fk_tipo_logistica, :fk_id_cliente, :create_at ) "
            , nativeQuery=true)
    public Integer insertar(
            @Param("nombre") String nombre,
            @Param("descripcion") String descripcion ,
            @Param("fk_tipo_logistica") Integer fk_tipo_logistica ,
            @Param("fk_id_cliente") Long fk_id_cliente,
            @Param("create_at") Date create_at

    );

    @Query(value = "SELECT p.*, "
            + " c.nombres as nombre_cliente"
            + " FROM productos p "
            + " INNER JOIN clientes c ON p.fk_id_cliente = c.id "
            + " WHERE p.id = :id "
            , nativeQuery=true)
    public Producto findById2(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE productos SET nombre = :nombre, descripcion = :descripcion, fk_tipo_logistica = :fk_tipo_logistica  " +
            " WHERE id = :id  "
            , nativeQuery=true)
    public Integer update(
            @Param("id") Long id,
            @Param("nombre") String nombre,
            @Param("descripcion") String descripcion ,
            @Param("fk_tipo_logistica") Integer fk_tipo_logistica
    );
}
