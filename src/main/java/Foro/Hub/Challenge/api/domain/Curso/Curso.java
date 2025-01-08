package Foro.Hub.Challenge.api.domain.Curso;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "curso")
@Entity(name = "Curso")
@Data // Lombok annotation for getters, setters, equals, hashCode, and toString
@Builder // Lombok annotation for fluent builder pattern
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombreCurso;

    @Column(nullable = false)
    private String categoria;

    public Curso(DatosRegistroCurso curso){
        this.categoria = curso.categoria();
        this.nombreCurso = curso.nombreCurso();
    }
}
