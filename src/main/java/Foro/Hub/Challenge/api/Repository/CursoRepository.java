package Foro.Hub.Challenge.api.Repository;

import Foro.Hub.Challenge.api.domain.Curso.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
    public Optional<Curso> findByNombreCursoAndCategoria(String nombreCurso, String categoria);


}