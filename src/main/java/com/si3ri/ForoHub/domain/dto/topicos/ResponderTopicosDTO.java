package com.si3ri.ForoHub.domain.dto.topicos;

import com.si3ri.ForoHub.domain.entity.topicos.Topicos;

public record ResponderTopicosDTO(
        String titulo,
        String mensaje,
        String autor,
        String curso
) {
    public ResponderTopicosDTO(Topicos datos) {
        this(datos.getTitulo(),datos.getMensaje(), datos.getAutor(), datos.getCurso());
    }
}
