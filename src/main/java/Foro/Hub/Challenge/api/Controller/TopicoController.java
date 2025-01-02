package Foro.Hub.Challenge.api.Controller;

import Foro.Hub.Challenge.api.Curso.DatosRegistroCurso;
import Foro.Hub.Challenge.api.Topico.DatosRegistroTopico;
import Foro.Hub.Challenge.api.Topico.Status;
import Foro.Hub.Challenge.api.Topico.Topico;
import Foro.Hub.Challenge.api.Usuario.DatosRegistroUsuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topico")
public class TopicoController {

    @PostMapping
    public DatosRegistroTopico  datosRegistroTopico(@RequestBody DatosRegistroTopico  datosRegistroTopico){
        Topico topico = new Topico(datosRegistroTopico);
        System.out.println(topico);
        return null;
    }

}
