package com.si3ri.ForoHub.domain.dto.topicos;

import com.si3ri.ForoHub.domain.entity.topicos.TopicosStatus;
import com.si3ri.ForoHub.domain.entity.topicos.Topicos;
import java.time.LocalDateTime;

public record ListarTopicosDTO(
        String titulo,
        String mensaje,
        LocalDateTime fecha,
        TopicosStatus status,
        String autor,
        String curso
) {
    public ListarTopicosDTO(Topicos t) {
        this(t.getTitulo(),t.getMensaje(),t.getFecha(),t.getStatus(),t.getAutor(),t.getCurso());
    }
}
