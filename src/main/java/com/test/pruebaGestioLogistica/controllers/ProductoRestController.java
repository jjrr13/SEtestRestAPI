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

        List<Producto> porductos = iServiceProducto.listAll();

        if (porductos == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(porductos);
        }

    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("id") Long id) {
        Producto producto = iServiceProducto.findById(id);
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
        Producto newProducto = iServiceProducto.save(producto);

        if (newProducto == null) {
            throw new BadRequestException("producto: " + producto);
        } else {
            return ResponseEntity.ok(newProducto);
        }
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Producto> deleteById(@PathVariable("id") Long id) {
        Producto producto = iServiceProducto.findById(id);

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
        Producto newProducto = iServiceProducto.findById(producto.getId());

        if (producto == null) {
            throw new BadRequestException("porductos: " + producto);
        }

        if (newProducto == null) {
            throw new DontFindElementException("id: " + producto.getId());
        }
        else {
            iServiceProducto.save(producto);
            return ResponseEntity.ok(producto);
        }
    }
}
