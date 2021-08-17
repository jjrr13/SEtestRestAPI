package com.test.pruebaGestioLogistica.controllers;

import com.test.pruebaGestioLogistica.entities.TipoLogisticaOld;
import com.test.pruebaGestioLogistica.exceptions.BadRequestException;
import com.test.pruebaGestioLogistica.exceptions.DontFindElementException;
import com.test.pruebaGestioLogistica.services.IServiceTipoLogistica;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/tipoLogistica")
public class TipoLogisticaRestController {
    
    
    @Autowired
    private IServiceTipoLogistica iServiceTipoLogistica;

    @GetMapping(value = "/listar")
    private ResponseEntity<List<TipoLogisticaOld>> listAll(){

        List<TipoLogisticaOld> tiposLogistica = iServiceTipoLogistica.listAll();

        if (tiposLogistica == null ){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.ok( tiposLogistica );
        }

    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public  ResponseEntity findById( @PathVariable("id") Long id){
        TipoLogisticaOld tipoLogisticaOld = iServiceTipoLogistica.findById(id);
        if (id < 0 ){
            throw new BadRequestException("id: " + id);
        }

        if (tipoLogisticaOld == null){
            throw new DontFindElementException("id: " + id);
        }
        else{
            return ResponseEntity.ok(tipoLogisticaOld);
        }
    }

    @PostMapping
    public ResponseEntity<TipoLogisticaOld> create(@RequestBody TipoLogisticaOld tipoLogisticaOld){
        TipoLogisticaOld newTipoLogisticaOld = iServiceTipoLogistica.save(tipoLogisticaOld);

        if (newTipoLogisticaOld == null){
            throw new BadRequestException("tipoLogisticaOld: " + tipoLogisticaOld);
        }
        else{
            return ResponseEntity.ok(newTipoLogisticaOld);
        }
    }

    @DeleteMapping(value = "{id}")
    public  ResponseEntity<TipoLogisticaOld> deleteById(@PathVariable("id") Long id){
        TipoLogisticaOld tipoLogisticaOld = iServiceTipoLogistica.findById(id);

        if (id < 0 ){
            throw new BadRequestException("id: " + id);
        }

        if (tipoLogisticaOld == null){
            throw new DontFindElementException("id: " + id);
        }
        else{
            iServiceTipoLogistica.delete(id);
            return ResponseEntity.ok(tipoLogisticaOld);
        }
    }

    @PutMapping
    public ResponseEntity<TipoLogisticaOld> update(@RequestBody TipoLogisticaOld tipoLogisticaOld){
        TipoLogisticaOld newTipoLogisticaOld = iServiceTipoLogistica.findById(tipoLogisticaOld.getId());

        if (tipoLogisticaOld == null ){
            throw new BadRequestException("tipoLogisticas: " + tipoLogisticaOld);
        }

        if (newTipoLogisticaOld == null){
            throw new DontFindElementException("id: " + tipoLogisticaOld.getId());
        }
        else{
            newTipoLogisticaOld.setNombre( tipoLogisticaOld.getNombre() );
            iServiceTipoLogistica.save(newTipoLogisticaOld);
            return ResponseEntity.ok(newTipoLogisticaOld);
        }
    }
    
}
