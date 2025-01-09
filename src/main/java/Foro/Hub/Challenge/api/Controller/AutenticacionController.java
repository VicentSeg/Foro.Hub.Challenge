package Foro.Hub.Challenge.api.Controller;

import Foro.Hub.Challenge.api.domain.Usuario.DatosAutenticacion;
import Foro.Hub.Challenge.api.domain.Usuario.Usuario;
import Foro.Hub.Challenge.api.infra.security.DatosTokenJWT;
import Foro.Hub.Challenge.api.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity  realizarLogin (@RequestBody @Valid DatosAutenticacion datosAutenticacion) {
        Authentication authenticationToken = new UsernamePasswordAuthenticationToken(datosAutenticacion.login(),
                datosAutenticacion.contrasena());
        var authentication = authenticationManager.authenticate(authenticationToken);
        System.out.println(authentication);

        var tokenJWT = tokenService.generarToken((Usuario) authentication.getPrincipal());
        System.out.println(tokenJWT);

        return ResponseEntity.ok(new DatosTokenJWT(tokenJWT));
    }
}

