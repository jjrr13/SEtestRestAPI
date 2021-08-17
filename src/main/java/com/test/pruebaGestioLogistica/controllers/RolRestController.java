package com.test.pruebaGestioLogistica.controllers;

import com.test.pruebaGestioLogistica.exceptions.BadRequestException;
import com.test.pruebaGestioLogistica.exceptions.DontFindElementException;
import com.test.pruebaGestioLogistica.entities.Rol;
import com.test.pruebaGestioLogistica.services.IServiceRol;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/roles")
public class RolRestController {

    @Autowired
    private IServiceRol iServiceRol;

    @GetMapping(value = "/listar")
    private ResponseEntity<List<Rol>> listAll(){

        List<Rol> roles = iServiceRol.listAll();

        if (roles == null ){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.ok( roles );
        }

    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public  ResponseEntity findById( @PathVariable("id") Long id){
        Rol rol = iServiceRol.findById(id);
        if (id < 0 ){
            throw new BadRequestException("id: " + id);
        }

        if (rol == null){
            throw new DontFindElementException("id: " + id);
        }
        else{
            return ResponseEntity.ok(rol);
        }
    }

    @PostMapping
    public ResponseEntity<Rol> create(@RequestBody Rol rol){
        Rol newRol = iServiceRol.save(rol);

        if (newRol == null){
            throw new BadRequestException("rol: " + rol);
        }
        else{
            return ResponseEntity.ok(newRol);
        }
    }

    @DeleteMapping(value = "{id}")
    public  ResponseEntity<Rol> deleteById(@PathVariable("id") Long id){
        Rol rol = iServiceRol.findById(id);

        if (id < 0 ){
            throw new BadRequestException("id: " + id);
        }

        if (rol == null){
            throw new DontFindElementException("id: " + id);
        }
        else{
            iServiceRol.delete(id);
            return ResponseEntity.ok(rol);
        }
    }

    @PutMapping
    public ResponseEntity<Rol> update(@RequestBody Rol rol){
        Rol newRol = iServiceRol.findById(rol.getId());

        if (rol == null ){
            throw new BadRequestException("roles: " + rol);
        }

        if (newRol == null){
            throw new DontFindElementException("id: " + rol.getId());
        }
        else{
            newRol.setNombre( rol.getNombre() );
            newRol.setDescripcion( rol.getDescripcion() );
            iServiceRol.save(newRol);
            return ResponseEntity.ok(newRol);
        }
    }
}
