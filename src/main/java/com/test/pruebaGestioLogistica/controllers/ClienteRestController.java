package com.test.pruebaGestioLogistica.controllers;

import com.test.pruebaGestioLogistica.exceptions.BadRequestException;
import com.test.pruebaGestioLogistica.exceptions.DontFindElementException;
import com.test.pruebaGestioLogistica.entities.Cliente;
import com.test.pruebaGestioLogistica.services.IServiceCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/clientes")
public class ClienteRestController {

    @Autowired
    private IServiceCliente iServiceCliente;

    @GetMapping(value = "/listar")
    private ResponseEntity<List<Cliente>> listAll(){

        List<Cliente> clientees = iServiceCliente.listAll();

        if (clientees == null ){
            return ResponseEntity.noContent().build();
        }
        else{
            return ResponseEntity.ok( clientees );
        }

    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public  ResponseEntity findById( @PathVariable("id") Long id){
        Cliente cliente = iServiceCliente.findById(id);
        if (id < 0 ){
            throw new BadRequestException("id: " + id);
        }

        if (cliente == null){
            throw new DontFindElementException("id: " + id);
        }
        else{
            return ResponseEntity.ok(cliente);
        }
    }

    @PostMapping
    public ResponseEntity<Cliente> create(@RequestBody Cliente cliente){
        Cliente newCliente = iServiceCliente.save(cliente);

        if (newCliente == null){
            throw new BadRequestException("cliente: " + cliente);
        }
        else{
            return ResponseEntity.ok(newCliente);
        }
    }

    @DeleteMapping(value = "{id}")
    public  ResponseEntity<Cliente> deleteById(@PathVariable("id") Long id){
        Cliente cliente = iServiceCliente.findById(id);

        if (id < 0 ){
            throw new BadRequestException("id: " + id);
        }

        if (cliente == null){
            throw new DontFindElementException("id: " + id);
        }
        else{
            iServiceCliente.delete(id);
            return ResponseEntity.ok(cliente);
        }
    }

    @PutMapping
    public ResponseEntity<Cliente> update(@RequestBody Cliente cliente){
        Cliente newCliente = iServiceCliente.findById(cliente.getId());

        if (cliente == null ){
            throw new BadRequestException("clientes: " + cliente);
        }

        if (newCliente == null){
            throw new DontFindElementException("id: " + cliente.getId());
        }
        else{
            newCliente.setNombre( cliente.getNombre() );
            newCliente.setApellido( cliente.getApellido() );
            newCliente.setIdentificacion(cliente.getIdentificacion());
            newCliente.setEmail(cliente.getEmail());
            iServiceCliente.save(newCliente);
            return ResponseEntity.ok(newCliente);
        }
    }

}
