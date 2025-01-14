package Foro.Hub.Challenge.api.domain.Topico;

import java.time.LocalDateTime;

public record DatosListadoTopico(
    Long id,
    String titulo,
    String mensaje,
    LocalDateTime fechaCreacion,
    Status status,
    String nombreCurso
    )
{
    public DatosListadoTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getCurso().getNombreCurso()
        );
    }
}

