package com.test.pruebaGestioLogistica.repository;

import com.test.pruebaGestioLogistica.entities.Almacen;
import com.test.pruebaGestioLogistica.entities.Entrega;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

public interface IEntregaRepository extends CrudRepository<Entrega, Long> {

    @Query(value = "SELECT e.*, "
            + " c.nombres as nombre_cliente, "
            + " p.nombre as nombre_producto, "
            + " a.nombre as nombre_almacen "
            + " FROM entregas e "
            + " INNER JOIN clientes c ON e.fk_id_cliente = c.id "
            + " INNER JOIN productos p ON e.fk_id_producto = p.id "
            + " INNER JOIN almacenes a ON e.fk_id_almacen_cliente = a.id "
            , nativeQuery=true)
    public List<Entrega> obtenerEntregasConNombres();

    @Query(value = "SELECT e.*, "
            + " c.nombres as nombre_cliente, "
            + " p.nombre as nombre_producto, "
            + " a.nombre as nombre_almacen "
            + " FROM entregas e "
            + " INNER JOIN clientes c ON e.fk_id_cliente = c.id "
            + " INNER JOIN productos p ON e.fk_id_producto = p.id "
            + " INNER JOIN almacenes a ON e.fk_id_almacen_cliente = a.id "
            + " WHERE c.id = :id "
            , nativeQuery=true)
    public List<Entrega> listByClient(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO entregas ( " +
            "   cantidad_producto, descuento, fecha_entraga, fecha_registro, identificacion_transporte, numero_guia, precio, " +
            "   fk_id_almacen_cliente, fk_id_cliente, fk_id_producto, fk_tipo_logistica " +
            " ) " +
            " VALUES ( " +
            "   :cantidad_producto, :descuento, :fecha_entraga, :fecha_registro, :identificacion_transporte, :numero_guia, :precio, " +
            "   :fk_id_almacen_cliente, :fk_id_cliente, :fk_id_producto, :fk_tipo_logistica " +
            " ) "
            , nativeQuery=true)
    public Integer insertar(
            @Param("cantidad_producto") Integer cantidad_producto,
            @Param("descuento") Double descuento ,
            @Param("fecha_entraga") Date fecha_entraga ,
            @Param("fecha_registro") Date fecha_registro,
            @Param("identificacion_transporte") String identificacion_transporte,
            @Param("numero_guia") String numero_guia,
            @Param("precio") Double precio,
            @Param("fk_id_almacen_cliente") Long fk_id_almacen_cliente,
            @Param("fk_id_cliente") Long fk_id_cliente,
            @Param("fk_id_producto") Long fk_id_producto,
            @Param("fk_tipo_logistica") Integer fk_tipo_logistica
    );

    @Query(value = "SELECT e.*, "
            + " c.nombres as nombre_cliente, "
            + " p.nombre as nombre_producto, "
            + " a.nombre as nombre_almacen "
            + " FROM entregas e "
            + " INNER JOIN clientes c ON e.fk_id_cliente = c.id "
            + " INNER JOIN productos p ON e.fk_id_producto = p.id "
            + " INNER JOIN almacenes a ON e.fk_id_almacen_cliente = a.id "
            + " WHERE e.id = :id "
            , nativeQuery=true)
    public Entrega findById2(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query(value = "UPDATE entregas SET " +
            "   cantidad_producto = :cantidad_producto, descuento = :descuento, fecha_entraga = :fecha_entraga, fecha_registro = :fecha_registro, " +
            "   identificacion_transporte = :identificacion_transporte, numero_guia = :numero_guia, precio = :precio, " +
            "   fk_id_almacen_cliente = :fk_id_almacen_cliente, fk_id_cliente = :fk_id_cliente, fk_id_producto = :fk_id_producto, fk_tipo_logistica = :fk_tipo_logistica " +
            "   WHERE id = :id  "
            , nativeQuery=true)
    public Integer update(
            @Param("id") Long id,
            @Param("cantidad_producto") Integer cantidad_producto,
            @Param("descuento") Double descuento ,
            @Param("fecha_entraga") Date fecha_entraga ,
            @Param("fecha_registro") Date fecha_registro,
            @Param("identificacion_transporte") String identificacion_transporte,
            @Param("numero_guia") String numero_guia,
            @Param("precio") Double precio,
            @Param("fk_id_almacen_cliente") Long fk_id_almacen_cliente,
            @Param("fk_id_cliente") Long fk_id_cliente,
            @Param("fk_id_producto") Long fk_id_producto,
            @Param("fk_tipo_logistica") Integer fk_tipo_logistica
    );
}
