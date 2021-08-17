package com.test.pruebaGestioLogistica.controllers;

import com.test.pruebaGestioLogistica.exceptions.BadRequestException;
import com.test.pruebaGestioLogistica.exceptions.DontFindElementException;
import com.test.pruebaGestioLogistica.entities.Usuario;
import com.test.pruebaGestioLogistica.services.IServiceUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/usuarios")
public class UsuariosRestController {

    @Autowired
    public IServiceUsuario iServiceUsuario;


    @GetMapping(value = "/listar")
    public ResponseEntity<List<Usuario>> findAll(){
        List<Usuario> usuarios = iServiceUsuario.listAll();
        if (usuarios.size() > 0){
            return ResponseEntity.ok( usuarios );
        }
        else{
            return ResponseEntity.noContent().build();
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET) // /api/users/{id} -> /api/users/1
    public  ResponseEntity findById( @PathVariable("id") Long id){
        Usuario usuario = iServiceUsuario.findById(id);
        if (id < 0 ){
            throw new BadRequestException("id: " + id);
        }

        if (usuario == null){
            throw new DontFindElementException("id: " + id);
        }
        else{
            return ResponseEntity.ok(usuario);
        }
   }

   @PostMapping
   public ResponseEntity<Usuario> create(@RequestBody Usuario usuario){
       Usuario newUsuario = iServiceUsuario.save(usuario);

       if (newUsuario == null){
           throw new BadRequestException("usuario: " + usuario);
       }
       else{
           return ResponseEntity.ok(newUsuario);
       }
   }

   @DeleteMapping(value = "{id}")
   public  ResponseEntity<Usuario> deleteById(@PathVariable("id") Long id){
       Usuario usuario = iServiceUsuario.findById(id);

       if (id < 0 ){
           throw new BadRequestException("id: " + id);
       }

       if (usuario == null){
           throw new DontFindElementException("id: " + id);
       }
       else{
           iServiceUsuario.delete(id);
           return ResponseEntity.ok(usuario);
       }
   }

   @PutMapping
   public ResponseEntity<Usuario> update(@RequestBody Usuario usuario){
       Usuario newUsuario = iServiceUsuario.findById(usuario.getId());

       if (usuario == null ){
           throw new BadRequestException("usuarioss: " + usuario);
       }

       if (newUsuario == null){
           throw new DontFindElementException("id: " + usuario.getId());
       }
       else{
           newUsuario.setUsername( usuario.getUsername() );
           newUsuario.setPassword( usuario.getPassword() );
           newUsuario.setEnabled( usuario.isEnabled() );
           iServiceUsuario.save(newUsuario);
           return ResponseEntity.ok(newUsuario);
       }
   }

}
