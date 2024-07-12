package com.si3ri.ForoHub.domain.dto.topicos;

import jakarta.validation.constraints.NotBlank;

public record ActualizarTopicosDTO(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotBlank
        String autor,
        @NotBlank
        String curso
) {
}
