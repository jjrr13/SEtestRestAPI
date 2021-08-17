package com.test.pruebaGestioLogistica.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30, unique = true)
    private String username;

    @Column(length = 60 )
    private  String password;

    private  boolean enabled;

    @ManyToMany(fetch = FetchType.EAGER )
    @JoinTable(name = "usuario_roles",
        joinColumns = {@JoinColumn(name = "id_usuario", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "id_rol", referencedColumnName = "id")}
    )
    private List<Rol> roles;


    /*Gestter and setters*/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public void addRoles(Rol rol) {
        roles.add(rol);
    }

    public Usuario() {
        //roles = new ArrayList<Rol>();
    }

    private static final long serialVersionUID = 1L;
}
