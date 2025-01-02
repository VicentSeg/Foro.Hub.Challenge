package Foro.Hub.Challenge.api.Respuesta;

import Foro.Hub.Challenge.api.Topico.Topico;
import Foro.Hub.Challenge.api.Usuario.Usuario;

import java.time.LocalDateTime;

public class Respuesta {
    private Long id;
    private String mensaje;
    private Topico topico;
    private LocalDateTime fechaCreacion;
    private Usuario usuario;
    private boolean solucion;
}
