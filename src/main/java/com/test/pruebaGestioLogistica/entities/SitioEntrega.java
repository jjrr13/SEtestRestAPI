package com.test.pruebaGestioLogistica.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "sitio_entrega")
public class SitioEntrega implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nombre;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_id_tipo_logistica", referencedColumnName = "id")
    private TipoLogisticaOld tipoLogisticaOld;

    @NotNull
    private String descripcion;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_at")
    private Date createAt;

    @PrePersist
    public void prePersist() {
        createAt = new Date();
    }

    /*Getters y Setters*/

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

    public TipoLogisticaOld getTipoLogistica() {
        return tipoLogisticaOld;
    }

    public void setTipoLogistica(TipoLogisticaOld tipoLogisticaOld) {
        this.tipoLogisticaOld = tipoLogisticaOld;
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
