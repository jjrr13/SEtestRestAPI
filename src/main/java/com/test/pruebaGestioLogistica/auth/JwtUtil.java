package com.test.pruebaGestioLogistica.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

import static java.util.Collections.emptyList;

public class JwtUtil {

    private final static String HASH_VALIDATE = "4ef63528c30882466e3fc35666087b8a"; //MD5 == "soloJJ"
    private final static Integer TIME_LIFE_TOKEN = 60000; //Milisegundos == 1 minuto

    // Método para crear el JWT y enviarlo al cliente en el header de la respuesta
    static void addAuthentication(HttpServletResponse res, String username) {

        String token = Jwts.builder()
                .setSubject(username)

                // Vamos a asignar un tiempo de expiracion de 1 minuto
                // solo con fines demostrativos en el video que hay al final
                .setExpiration(new Date(System.currentTimeMillis() + TIME_LIFE_TOKEN))

                // Hash con el que firmaremos la clave
                .signWith(SignatureAlgorithm.HS512, HASH_VALIDATE)
                .compact();

        //agregamos al encabezado el token
        res.addHeader("Authorization", "Bearer " + token);
    }

    // Método para validar el token enviado por el cliente
    static Authentication getAuthentication(HttpServletRequest request) {

        // Obtenemos el token que viene en el encabezado de la peticion
        String token = request.getHeader("Authorization");

        // si hay un token presente, entonces lo validamos
        if (token != null) {
            String user = Jwts.parser()
                    .setSigningKey( HASH_VALIDATE )
                    .parseClaimsJws(token.replace("Bearer", ""))
                    .getBody()
                    .getSubject();

            // peticiones que no sean /login
            // no requeren una autenticacion por username/password
            // por este motivo devolver un UsernamePasswordAuthenticationToken sin password
            return user != null ?
                    new UsernamePasswordAuthenticationToken(user, null, emptyList()) :
                    null;
        }
        return null;
    }

}
