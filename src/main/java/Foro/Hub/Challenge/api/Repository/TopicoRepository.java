package Foro.Hub.Challenge.api.Repository;

import Foro.Hub.Challenge.api.domain.Topico.Status;
import Foro.Hub.Challenge.api.domain.Topico.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Page<Topico> findByStatus(Status status, Pageable pageable);
    // Modificar el método para incluir el filtro por cursoId
    Page<Topico> findByCursoIdAndStatus(Long cursoId, Status status, Pageable pageable);
}