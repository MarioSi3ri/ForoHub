package com.si3ri.ForoHub.domain.entity.topicos;

import com.si3ri.ForoHub.domain.dto.topicos.ActualizarTopicosDTO;
import com.si3ri.ForoHub.domain.dto.topicos.RegistrarTopicosDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity(name = "Topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topicos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensaje;
    private LocalDateTime fecha;
    @Enumerated(EnumType.STRING)
    private TopicosStatus status;
    private String autor;
    private String curso;

    public Topicos(RegistrarTopicosDTO datos) {
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.fecha = LocalDateTime.now();
        this.status = TopicosStatus.SIN_RESPUESTA;
        this.autor = datos.autor();
        this.curso = datos.curso();
    }

    public void actualizarDatos(ActualizarTopicosDTO datos) {
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.autor = datos.autor();
        this.curso = datos.curso();
    }
}
