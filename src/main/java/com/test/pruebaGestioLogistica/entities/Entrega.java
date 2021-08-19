package com.test.pruebaGestioLogistica.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "entregas")
public class Entrega {

    @Id
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private Integer cantidad_producto;

    @NotEmpty
    private Double descuento;

    @NotNull
    @Column(name = "fecha_entraga")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date fecha_entraga;

    @NotNull
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date fecha_registro;

    @NotEmpty
    private String identificacion_transporte;

    @NotEmpty
    private String numero_guia;


    @NotEmpty
    private Double precio;

    @NotEmpty
    private Long fk_id_cliente;

    //@Transient
    @JoinColumn(name = "nombreCliente", insertable = false, updatable = false)
    private String nombreCliente;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "fk_tipo_logistica")
    private TipoLogistica tipoLogistica;

    @NotEmpty
    private Long fk_id_producto;

    //@Transient
    @JoinColumn(name = "nombreProducto", insertable = false, updatable = false)
    private String nombreProducto;

    @NotEmpty
    private Long fk_id_almacen_cliente;

    //@Transient
    @JoinColumn(name = "nombreAlmacen", insertable = false, updatable = false)
    private String nombreAlmacen;

    /*Getters and Setters*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCantidad_producto() {
        return cantidad_producto;
    }

    public void setCantidad_producto(Integer cantidad_producto) {
        this.cantidad_producto = cantidad_producto;
    }

    public Double getDescuento() {
        return descuento;
    }

    public void setDescuento(Double decuento) {
        this.descuento = decuento;
    }

    public Date getFecha_entraga() {
        return fecha_entraga;
    }

    public void setFecha_entraga(Date fecha_entraga) {
        this.fecha_entraga = fecha_entraga;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public String getIdentificacion_transporte() {
        return identificacion_transporte;
    }

    public void setIdentificacion_transporte(String identificacion_transporte) {
        this.identificacion_transporte = identificacion_transporte;
    }

    public String getNumero_guia() {
        return numero_guia;
    }

    public void setNumero_guia(String numero_guia) {
        this.numero_guia = numero_guia;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public Long getFk_id_cliente() {
        return fk_id_cliente;
    }

    public void setFk_id_cliente(Long fk_id_cliente) {
        this.fk_id_cliente = fk_id_cliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public TipoLogistica getTipoLogistica() {
        return tipoLogistica;
    }

    public void setTipoLogistica(TipoLogistica tipoLogistica) {
        this.tipoLogistica = tipoLogistica;
    }

    public Long getFk_id_producto() {
        return fk_id_producto;
    }

    public void setFk_id_producto(Long fk_id_producto) {
        this.fk_id_producto = fk_id_producto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public Long getFk_id_almacen_cliente() {
        return fk_id_almacen_cliente;
    }

    public void setFk_id_almacen_cliente(Long fk_id_almacen_cliente) {
        this.fk_id_almacen_cliente = fk_id_almacen_cliente;
    }

    public String getNombreAlmacen() {
        return nombreAlmacen;
    }

    public void setNombreAlmacen(String nombreAlmacen) {
        this.nombreAlmacen = nombreAlmacen;
    }
}
