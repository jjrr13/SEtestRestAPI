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


}
