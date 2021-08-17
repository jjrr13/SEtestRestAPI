package com.test.pruebaGestioLogistica.entities;

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

    //fk_id_producto
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_id_producto", referencedColumnName = "id")
    private Producto producto;

    @NotEmpty
    private Integer cantidad_producto;

    @NotNull
    @Column(name = "fecha_registro")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date fecha_registro;

    @NotNull
    @Column(name = "fecha_entraga")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date fecha_entraga;

    //fk_sitio_entrega_cliente
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_sitio_entrega_cliente", referencedColumnName = "id")
    private SitioEntrega sitioEntrega;


    @NotEmpty
    private Double precio;

    @NotEmpty
    private String identificacion_transporte;

    @NotEmpty
    private String numero_guia;

    @NotEmpty
    private Double decuento;

    //fk_id_cliente
    //@ManyToOne(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //@JoinColumn(name = "fk_id_cliente", referencedColumnName = "id")
    private Integer fk_id_cliente;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "fk_tipo_logistica")
    private TipoLogistica tipoLogistica;

    /*Getters and Setters*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidad_producto() {
        return cantidad_producto;
    }

    public void setCantidad_producto(Integer cantidad_producto) {
        this.cantidad_producto = cantidad_producto;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public Date getFecha_entraga() {
        return fecha_entraga;
    }

    public void setFecha_entraga(Date fecha_entraga) {
        this.fecha_entraga = fecha_entraga;
    }

    public SitioEntrega getSitioEntrega() {
        return sitioEntrega;
    }

    public void setSitioEntrega(SitioEntrega sitioEntrega) {
        this.sitioEntrega = sitioEntrega;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
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

    public Double getDecuento() {
        return decuento;
    }

    public void setDecuento(Double decuento) {
        this.decuento = decuento;
    }

    public Integer getFk_id_cliente() {
        return fk_id_cliente;
    }

    public void setFk_id_cliente(Integer fk_id_cliente) {
        this.fk_id_cliente = fk_id_cliente;
    }

    public TipoLogistica getTipoLogistica() {
        return tipoLogistica;
    }

    public void setTipoLogistica(TipoLogistica tipoLogistica) {
        this.tipoLogistica = tipoLogistica;
    }
}
