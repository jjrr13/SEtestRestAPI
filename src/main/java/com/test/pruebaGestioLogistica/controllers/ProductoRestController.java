package com.test.pruebaGestioLogistica.controllers;

import com.test.pruebaGestioLogistica.exceptions.BadRequestException;
import com.test.pruebaGestioLogistica.exceptions.DontFindElementException;
import com.test.pruebaGestioLogistica.entities.Producto;
import com.test.pruebaGestioLogistica.services.IServiceProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/productos")
public class ProductoRestController {

    @Autowired
    private IServiceProducto iServiceProducto;

    @GetMapping(value = "/listar")
    private ResponseEntity<List<Producto>> listAll() {

        List<Producto> porductos = iServiceProducto.obtenerConNombreCliente();

        if (porductos == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(porductos);
        }

    }

    @RequestMapping(value = "listar/cliente/{id}", method = RequestMethod.GET)
    private ResponseEntity<List<Producto>> listByClient(@PathVariable("id") Long id) {

        if (id < 0) {
            throw new BadRequestException("id: " + id);
        }

        List<Producto> porductos = iServiceProducto.listByClient(id);

        if (porductos == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(porductos);
        }

    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("id") Long id) {
        Producto producto = iServiceProducto.findById2(id);
        if (id < 0) {
            throw new BadRequestException("id: " + id);
        }

        if (producto == null) {
            throw new DontFindElementException("id: " + id);
        } else {
            return ResponseEntity.ok(producto);
        }
    }

    @PostMapping
    public ResponseEntity<Producto> create(@RequestBody Producto producto) {
        Integer idProducto = iServiceProducto.insertar(producto);

        if (idProducto == null) {
            throw new BadRequestException("producto: " + producto);
        } else {
            return ResponseEntity.ok(iServiceProducto.findById2(idProducto.longValue()) ) ;
        }
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Producto> deleteById(@PathVariable("id") Long id) {
        Producto producto = iServiceProducto.findById2(id);

        if (id < 0) {
            throw new BadRequestException("id: " + id);
        }

        if (producto == null) {
            throw new DontFindElementException("id: " + id);
        } else {
            iServiceProducto.delete(id);
            return ResponseEntity.ok(producto);
        }
    }

    @PutMapping
    public ResponseEntity<Producto> update(@RequestBody Producto producto) {
        Producto newProducto = iServiceProducto.findById2(producto.getId());

        if (producto == null) {
            throw new BadRequestException("porductos: " + producto);
        }

        if (newProducto == null) {
            throw new DontFindElementException("id: " + producto.getId());
        }
        else {
            iServiceProducto.update(producto);
            return ResponseEntity.ok(producto);
        }
    }
}
