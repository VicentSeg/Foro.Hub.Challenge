package Foro.Hub.Challenge.api.domain.Topico;

import Foro.Hub.Challenge.api.Repository.CursoRepository;
import Foro.Hub.Challenge.api.Repository.TopicoRepository;
import Foro.Hub.Challenge.api.domain.Curso.Curso;

import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TopicoService {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private CursoRepository cursoRepository;

    @Transactional
    public Topico crearTopico(DatosRegistroTopico datosRegistroTopico) {
        // Buscar el curso por nombre y categoría
        Curso curso = cursoRepository.findByNombreCursoAndCategoria(
                        datosRegistroTopico.curso().nombreCurso(),
                        datosRegistroTopico.curso().categoria())
                .orElseGet(() -> {
                    // Si no existe, crear el curso
                    Curso nuevoCurso = new Curso(datosRegistroTopico.curso());
                    return cursoRepository.save(nuevoCurso); // Guardamos el nuevo curso
                });

        // Crear el Topico con los datos proporcionados
        Topico topico = Topico.builder()
                .titulo(datosRegistroTopico.titulo())
                .mensaje(datosRegistroTopico.mensaje())
                .curso(curso)  // Asignamos el curso al tópico
                .fechaCreacion(LocalDateTime.now())
                .status(Status.ACTIVO)
                .build();

        // Guardar el tópico en la base de datos
        return topicoRepository.save(topico);
    }

    @Transactional
    public Page<DatosListadoTopico> listarTopicos(Long cursoId, Status status, Pageable paginacion) {
        // Si cursoId y status están presentes, utilizamos ambos como filtros
        Page<Topico> topicosPage;
        if (cursoId != null && status != null) {
            topicosPage = topicoRepository.findByCursoIdAndStatusNot(cursoId, Status.ELIMINADO, paginacion);
        } else if (status != null) {
            topicosPage = topicoRepository.findByStatusNot(Status.ELIMINADO, paginacion);
        } else {
            topicosPage = topicoRepository.findByStatusNot(Status.ELIMINADO, paginacion); // Excluir eliminados
        }
        // Mapear el resultado de Page<Topico> a Page<DatosListadoTopico>
        return topicosPage.map(this::mapearATopicoListado);
    }

    @Transactional
    public Topico obtenerTopicoPorId(Long id) {
        Topico topico =topicoRepository.findById(id).orElse(null);
        if (topico != null) {
            Hibernate.initialize(topico.getCurso());
        }
        return topico;
    }

    @Transactional
    public DatosListadoTopico mapearATopicoListado(Topico topico) {
        Curso curso = topico.getCurso();
        return new DatosListadoTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                curso.getNombreCurso());  // Asignamos el nombre del curso
    }

    @Transactional
    public Topico actualizarTopico(Long id, DatosRegistroTopico datosRegistroTopico) {
        // Buscar el tópico existente por ID
        Topico topicoExistente = topicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico no encontrado"));

        // Inicializar la relación curso para evitar problemas con proxies no inicializados
        Hibernate.initialize(topicoExistente.getCurso());

        // Buscar o crear el curso
        Curso curso = cursoRepository.findByNombreCursoAndCategoria(
                        datosRegistroTopico.curso().nombreCurso(),
                        datosRegistroTopico.curso().categoria())
                .orElseGet(() -> {
                    // Si no existe, crear el curso
                    Curso nuevoCurso = new Curso(datosRegistroTopico.curso());
                    return cursoRepository.save(nuevoCurso); // Guardamos el nuevo curso
                });

        // Actualizar los campos del tópico
        topicoExistente.setTitulo(datosRegistroTopico.titulo());
        topicoExistente.setMensaje(datosRegistroTopico.mensaje());
        topicoExistente.setFechaCreacion(LocalDateTime.now()); // Actualizamos la fecha de creación
        topicoExistente.setCurso(curso); // Asignamos el curso al tópico

        // Si el status está presente, lo actualizamos
        if (datosRegistroTopico.status() != null) {
            topicoExistente.setStatus(datosRegistroTopico.status());
        }

        // Guardar los cambios en la base de datos
        return topicoRepository.save(topicoExistente);
    }

    @Transactional
    public void eliminarTopico(Long id) {
        // Buscar el tópico existente por ID
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico no encontrado"));

        // Cambiar el estado a ELIMINADO
        topico.setStatus(Status.ELIMINADO);

        // Guardar los cambios
        topicoRepository.save(topico);
    }


}