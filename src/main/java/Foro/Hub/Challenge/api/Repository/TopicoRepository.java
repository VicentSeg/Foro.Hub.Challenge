package Foro.Hub.Challenge.api.Repository;

import Foro.Hub.Challenge.api.domain.Topico.Status;
import Foro.Hub.Challenge.api.domain.Topico.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Page<Topico> findByStatus(Status status, Pageable pageable);
    Page<Topico> findByCursoIdAndStatus(Long cursoId, Status status, Pageable pageable);
    Page<Topico> findByCursoIdAndStatusNot(Long cursoId, Status status, Pageable pageable);
    Page<Topico> findByStatusNot(Status status, Pageable pageable);

}