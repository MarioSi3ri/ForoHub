package com.si3ri.ForoHub.infra.validations;

import com.si3ri.ForoHub.infra.error.DataValidation;
import com.si3ri.ForoHub.domain.dto.topicos.RegistrarTopicosDTO;
import com.si3ri.ForoHub.infra.repository.TopicosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicosDuplicados implements ValidarTopicos {

    @Autowired
    TopicosRepository topicosRepository;

    @Override
    public void validar(RegistrarTopicosDTO datos) {
        var topicoDuplicado = topicosRepository.findByTituloAndMensaje(datos.titulo(),datos.mensaje());

        if (topicoDuplicado.isPresent()){
            throw new DataValidation("El tópico ya se encuentra duplicado (existe un tópico con el nombre o mensaje.)");
        }
    }
}
