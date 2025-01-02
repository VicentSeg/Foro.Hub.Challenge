package Foro.Hub.Challenge.api.Topico;

import Foro.Hub.Challenge.api.Curso.Curso;
import Foro.Hub.Challenge.api.Respuesta.Respuesta;
import Foro.Hub.Challenge.api.Usuario.Usuario;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Topico {
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fecha;
    private Status status;
    private Usuario usuario;
    private Curso curso;
    private List<Respuesta> respuestas;

    public Topico (DatosRegistroTopico datosRegistroTopico) {
        this.titulo = datosRegistroTopico.titulo();
        this.mensaje = datosRegistroTopico.mensaje();
        this.fecha = LocalDateTime.now();
        this.status = datosRegistroTopico.status();
        this.usuario = new Usuario(datosRegistroTopico.usuario());
        this.curso = new Curso(datosRegistroTopico.curso());
    }


}
