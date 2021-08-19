package com.test.pruebaGestioLogistica.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "productos")
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nombre;

    @NotNull
    private String descripcion;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "fk_tipo_logistica")
    private TipoLogistica tipoLogistica;


    /*@JsonBackReference
    @ManyToOne
    @JoinColumn(name = "fk_id_cliente", nullable = false)
    private Cliente cliente;*/

    private Long fk_id_cliente;

    //@Transient
    @JoinColumn(name = "nombreCliente", insertable = false, updatable = false)
    private String nombreCliente;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_at")
    private Date createAt;

    @PrePersist
    public void prePersist() {
        createAt = new Date();
    }

    /*Getters y Setters*/

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public Long getFk_id_cliente() {
        return fk_id_cliente;
    }

    public void setFk_id_cliente(Long fk_id_cliente) {
        this.fk_id_cliente = fk_id_cliente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }



    public TipoLogistica getTipoLogistica() {
        return tipoLogistica;
    }

    public void setTipoLogistica(TipoLogistica tipoLogistica) {
        this.tipoLogistica = tipoLogistica;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    private static final long serialVersionUID = 1L;

}
