package com.test.pruebaGestioLogistica.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String nombres;

    @NotEmpty
    private String apellidos;

    @NotEmpty
    private String identificacion;

    @NotEmpty
    @Email
    private String email;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_user", referencedColumnName = "id")
    private Usuario user;

    @Transient
   /* @JsonManagedReference
    @OneToMany(mappedBy = "fk_id_cliente", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)*/
    private List<Entrega> Entregas = new ArrayList<>();

    @Transient
    /*@LazyCollection(LazyCollectionOption.FALSE)
    @JsonManagedReference
    @OneToMany(mappedBy = "fk_id_cliente", cascade = CascadeType.ALL, orphanRemoval = true)*/
    private List<Producto> productos = new ArrayList<>();


    public Cliente() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombres;
    }

    public void setNombre(String nombre) {
        this.nombres = nombre;
    }

    public String getApellido() {
        return apellidos;
    }

    public void setApellido(String apellido) {
        this.apellidos = apellido;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /*public List<Entrega> getEntregas() {
        return Entregas;
    }

    public void setEntregas(List<Entrega> Entregas) {
        this.Entregas = Entregas;
    }*/

    public void addEntrega(Entrega Entrega) {
        Entregas.add(Entrega);
    }


}
