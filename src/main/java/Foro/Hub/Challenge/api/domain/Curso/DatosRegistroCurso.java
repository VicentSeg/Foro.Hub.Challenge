package Foro.Hub.Challenge.api.domain.Curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DatosRegistroCurso(
        @NotBlank(message = "El nombre del curso no puede estar vacío")
        @Size(min = 3, max = 255, message = "El nombre del curso debe tener entre 3 y 255 caracteres")
        String nombreCurso,
        @NotBlank(message = "La categoría no puede estar vacía")
        @Size(min = 3, max = 255, message = "La categoría debe tener entre 3 y 255 caracteres")
        String categoria
) {
}