package Foro.Hub.Challenge.api.domain.Topico;

import Foro.Hub.Challenge.api.domain.Curso.DatosRegistroCurso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosEliminarTopico(

        @NotBlank(message = "El título no puede estar vacío") String titulo,
        @NotBlank(message = "El mensaje no puede estar vacío") String mensaje,
        @NotNull(message = "El curso no puede estar vacío") DatosRegistroCurso curso,
        Status status)
{
}
