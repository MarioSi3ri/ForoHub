package com.si3ri.ForoHub.controller;

import com.si3ri.ForoHub.domain.dto.topicos.*;
import com.si3ri.ForoHub.domain.entity.topicos.Topicos;
import com.si3ri.ForoHub.domain.service.TopicosService;
import com.si3ri.ForoHub.infra.repository.TopicosRepository;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicosRepository topicosRepository;

    @Autowired
    private TopicosService topicosService;

    @PostMapping
    @Transactional
    @Operation(summary = "Registrar tópico en la DB")
    public ResponseEntity<ResponderTopicosDTO> registrarTopicos(@RequestBody @Valid RegistrarTopicosDTO datos, UriComponentsBuilder uriComponentsBuilder) {
        Topicos topico = topicosService.RegistrarTopico(datos);
        ResponderTopicosDTO responderTopicosDTO = new ResponderTopicosDTO(topico);
        URI uri = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(responderTopicosDTO);
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Actualizar tópico en la DB")
    public ResponseEntity<ResponderTopicosDTO> actualizarTopico(@PathVariable Long id, @RequestBody ActualizarTopicosDTO datos) {
        Optional<Topicos> optionalTopico = topicosRepository.findById(id);

        if (optionalTopico.isPresent()) {
            Topicos topicos = topicosRepository.getReferenceById(id);
            topicos.actualizarDatos(datos);
            return ResponseEntity.ok(new ResponderTopicosDTO(topicos));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Eliminar tópico en la DB")
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        Optional<Topicos> optionalTopico = topicosRepository.findById(id);

        if (optionalTopico.isPresent()) {
            topicosRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    @Transactional
    @Operation(summary = "Obtener detalles de un solo tópico en la DB")
    public ResponseEntity<ResponderTopicosDTO> detalleTopico(@PathVariable Long id) {
        Topicos topicos = topicosRepository.getReferenceById(id);
        return ResponseEntity.ok(new ResponderTopicosDTO(topicos));
    }

    @GetMapping
    @Operation(summary = "Obtener el listado de todos los tópicos en la DB")
    public ResponseEntity<Page<ListarTopicosDTO>> listadoTopicos(@PageableDefault(size = 10, sort = "titulo") Pageable paginacion) {
        return ResponseEntity.ok(topicosRepository.findAllByOrderByTituloAsc(paginacion).map(ListarTopicosDTO::new));
    }
}
