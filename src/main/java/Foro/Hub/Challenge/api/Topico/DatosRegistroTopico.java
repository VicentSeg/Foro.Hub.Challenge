package Foro.Hub.Challenge.api.Topico;

import Foro.Hub.Challenge.api.Curso.Curso;
import Foro.Hub.Challenge.api.Curso.DatosRegistroCurso;
import Foro.Hub.Challenge.api.Usuario.DatosRegistroUsuario;
import Foro.Hub.Challenge.api.Usuario.Usuario;

import java.time.LocalDateTime;

public record DatosRegistroTopico (
        String titulo,
        String mensaje,
        Status status,
        DatosRegistroUsuario usuario,
        DatosRegistroCurso curso) {


}
