package com.si3ri.ForoHub.infra.validations;

import com.si3ri.ForoHub.domain.dto.topicos.RegistrarTopicosDTO;

public interface ValidarTopicos {
    void validar(RegistrarTopicosDTO datos);
}
