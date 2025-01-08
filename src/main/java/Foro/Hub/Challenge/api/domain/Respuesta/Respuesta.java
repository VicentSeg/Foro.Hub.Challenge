package Foro.Hub.Challenge.api.domain.Respuesta;

import Foro.Hub.Challenge.api.domain.Topico.Topico;
import Foro.Hub.Challenge.api.domain.Usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "respuesta")
@Entity(name = "Respuesta")
@Data // Lombok annotation for getters, setters, equals, hashCode, and toString
@Builder // Lombok annotation for fluent builder pattern
@NoArgsConstructor
@AllArgsConstructor
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) //El mensaje no puede ser nulo
    private String mensaje;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id", nullable = false) //La respuesta debe pertenecer a un topico
    private Topico topico;

    @Column(nullable = false, updatable = false) //La fecha de creación no se puede actualizar
    private LocalDateTime fechaCreacion;

    @Column(nullable = false)
    private boolean solucion = false;
}

