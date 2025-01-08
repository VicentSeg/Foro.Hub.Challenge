package Foro.Hub.Challenge.api.Controller;

import Foro.Hub.Challenge.api.Repository.TopicoRepository;
import Foro.Hub.Challenge.api.domain.Topico.*;
import jakarta.validation.Valid;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;
    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Topico crearTopico(@RequestBody @Valid DatosRegistroTopico datosRegistroTopico) {
        return topicoService.crearTopico(datosRegistroTopico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listadoTopico(
            @RequestParam(required = false) Long cursoId,  // Añadimos el filtro por cursoId
            @RequestParam(required = false) Status status, // Y por status
            @PageableDefault(size = 10) Pageable paginacion) {

        // Llamar al servicio pasando los parámetros adecuados
        Page<DatosListadoTopico> resultado = topicoService.listarTopicos(cursoId, status, paginacion);

        return ResponseEntity.ok(resultado);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DatosListadoTopico> obtenerTopicoPorId(@PathVariable Long id) {
        Topico topico = topicoService.obtenerTopicoPorId(id);
        if (topico == null) {
            return ResponseEntity.notFound().build();
        }
        Hibernate.initialize(topico.getCurso());
        return ResponseEntity.ok(topicoService.mapearATopicoListado(topico));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Topico> actualizarTopico(@PathVariable Long id,
                                                   @RequestBody @Valid DatosRegistroTopico datosRegistroTopico) {
        // Llamar al servicio para actualizar el tópico, el status será actualizado si está en el body
        Topico topicoActualizado = topicoService.actualizarTopico(id, datosRegistroTopico);
        return ResponseEntity.ok(topicoActualizado);
    }

}

