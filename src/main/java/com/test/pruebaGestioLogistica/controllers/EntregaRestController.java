package com.test.pruebaGestioLogistica.controllers;

import com.test.pruebaGestioLogistica.entities.Entrega;
import com.test.pruebaGestioLogistica.exceptions.BadRequestException;
import com.test.pruebaGestioLogistica.exceptions.DontFindElementException;
import com.test.pruebaGestioLogistica.services.IServiceEntrega;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/entregas")
public class EntregaRestController {

    @Autowired
    private IServiceEntrega iServiceEntrega;

    @GetMapping(value = "/listar")
    private ResponseEntity<List<Entrega>> listAll() {

        List<Entrega> entrgas = iServiceEntrega.listAll();

        if (entrgas == null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(entrgas);
        }

    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity findById(@PathVariable("id") Long id) {
        Entrega entrega = iServiceEntrega.findById(id);
        if (id < 0) {
            throw new BadRequestException("id: " + id);
        }

        if (entrega == null) {
            throw new DontFindElementException("id: " + id);
        } else {
            return ResponseEntity.ok(entrega);
        }
    }

    @PostMapping
    public ResponseEntity<Entrega> create(@RequestBody Entrega entrega) {
        if (entrega == null) {
            throw new BadRequestException("entrega: " + entrega);
        }

        if (iServiceEntrega.validateIdentificacion(entrega.getTipoLogistica().toString(), entrega.getIdentificacion_transporte())){
            entrega.setDecuento(
                    iServiceEntrega.generateDiscount(entrega.getTipoLogistica().toString(), entrega.getCantidad_producto())
            );
        }
        else{
            if ("MARITIMA".equalsIgnoreCase(entrega.getTipoLogistica().toString())){
                throw new BadRequestException("El tipo de logistica MARITIMA: Debe contener 4 letras al inicio y 3 números al final");
            }
            else if("TERRESTRE".equalsIgnoreCase(entrega.getTipoLogistica().toString()) ){
                throw new BadRequestException("El tipo de logistica TERRESTRE: Debe contener 3 letras al inicio y 3 números al final");
            }
        }

        Entrega newEntrega = iServiceEntrega.save(entrega);

        if (newEntrega == null) {
            throw new BadRequestException("entrega: " + entrega);
        } else {
            return ResponseEntity.ok(newEntrega);
        }
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Entrega> deleteById(@PathVariable("id") Long id) {
        Entrega entrega = iServiceEntrega.findById(id);

        if (id < 0) {
            throw new BadRequestException("id: " + id);
        }

        if (entrega == null) {
            throw new DontFindElementException("id: " + id);
        } else {
            iServiceEntrega.delete(id);
            return ResponseEntity.ok(entrega);
        }
    }

    @PutMapping
    public ResponseEntity<Entrega> update(@RequestBody Entrega entrega) {
        if (entrega == null) {
            throw new BadRequestException("entrega: " + entrega);
        }

        Entrega newEntrega= iServiceEntrega.findById(entrega.getId());

        if (newEntrega == null) {
            throw new DontFindElementException("id: " + entrega.getId());
        }
        else {
            if (iServiceEntrega.validateIdentificacion(entrega.getTipoLogistica().toString(), entrega.getIdentificacion_transporte())){
                entrega.setDecuento(
                        iServiceEntrega.generateDiscount(entrega.getTipoLogistica().toString(), entrega.getCantidad_producto())
                );
            }
            else{
                if ("MARITIMA".equalsIgnoreCase(entrega.getTipoLogistica().toString())){
                    throw new BadRequestException("El tipo de logistica MARITIMA: Debe contener 4 letras al inicio y 3 números al final");
                }
                else if("TERRESTRE".equalsIgnoreCase(entrega.getTipoLogistica().toString()) ){
                    throw new BadRequestException("El tipo de logistica TERRESTRE: Debe contener 3 letras al inicio y 3 números al final");
                }
            }
            iServiceEntrega.save(entrega);
            return ResponseEntity.ok(entrega);
        }
    }
}
