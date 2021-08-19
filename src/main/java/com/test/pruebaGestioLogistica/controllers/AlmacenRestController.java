package com.test.pruebaGestioLogistica.controllers;

import com.test.pruebaGestioLogistica.entities.Almacen;
import com.test.pruebaGestioLogistica.exceptions.BadRequestException;
import com.test.pruebaGestioLogistica.exceptions.DontFindElementException;
import com.test.pruebaGestioLogistica.services.IServiceAlmacen;
import com.test.pruebaGestioLogistica.services.IServiceAlmacen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/almacenes")
public class AlmacenRestController {


    @Autowired
    private IServiceAlmacen iServiceAlmacen;

    @GetMapping(value = "/listar")
    private ResponseEntity<List<Almacen>> listAll() {

        List<Almacen> almacenes = iServiceAlmacen.obtenerConNombreCliente();

        if (almacenes == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(almacenes);
        }
    }

    @RequestMapping(value = "listar/cliente/{id}", method = RequestMethod.GET)
    private ResponseEntity<List<Almacen>> listByClient(@PathVariable("id") Long id) {
        if (id < 0) {
            throw new BadRequestException("id: " + id);
        }

        List<Almacen> almacenes = iServiceAlmacen.listByClient(id);

        if (almacenes == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(almacenes);
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("id") Long id) {
        if (id < 0) {
            throw new BadRequestException("id: " + id);
        }

        Almacen almacen = iServiceAlmacen.findById2(id);

        if (almacen == null) {
            throw new DontFindElementException("id: " + id);
        } else {
            return ResponseEntity.ok(almacen);
        }
    }

    @PostMapping
    public ResponseEntity<Almacen> create(@RequestBody Almacen almacen) {
        Integer idAlmacen = iServiceAlmacen.insertar(almacen);

        if (idAlmacen == null) {
            throw new BadRequestException("almacen: " + almacen);
        } else {
            return ResponseEntity.ok(iServiceAlmacen.findById2(idAlmacen.longValue()) ) ;
        }
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Almacen> deleteById(@PathVariable("id") Long id) {
        Almacen almacen = iServiceAlmacen.findById2(id);

        if (id < 0) {
            throw new BadRequestException("id: " + id);
        }

        if (almacen == null) {
            throw new DontFindElementException("id: " + id);
        } else {
            iServiceAlmacen.delete(id);
            return ResponseEntity.ok(almacen);
        }
    }

    @PutMapping
    public ResponseEntity<Almacen> update(@RequestBody Almacen almacen) {
        Almacen newAlmacen = iServiceAlmacen.findById2(almacen.getId());

        if (almacen == null) {
            throw new BadRequestException("almacenes: " + almacen);
        }

        if (newAlmacen == null) {
            throw new DontFindElementException("id: " + almacen.getId());
        }
        else {
            iServiceAlmacen.update(almacen);
            return ResponseEntity.ok(almacen);
        }
    }
}
