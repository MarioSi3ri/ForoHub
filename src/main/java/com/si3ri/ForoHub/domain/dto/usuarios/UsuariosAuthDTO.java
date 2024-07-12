package com.si3ri.ForoHub.domain.dto.usuarios;

import jakarta.validation.constraints.NotBlank;

public record UsuariosAuthDTO(
        @NotBlank
        String email,
        @NotBlank
        String password
) {
}
