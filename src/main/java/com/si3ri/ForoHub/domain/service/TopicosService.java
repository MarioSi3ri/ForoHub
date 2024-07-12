package com.si3ri.ForoHub.domain.service;

import com.si3ri.ForoHub.infra.repository.TopicosRepository;
import com.si3ri.ForoHub.infra.validations.ValidarTopicos;
import com.si3ri.ForoHub.domain.dto.topicos.RegistrarTopicosDTO;
import com.si3ri.ForoHub.domain.entity.topicos.Topicos;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TopicosService {

    @Autowired
    TopicosRepository topicosRepository;

    @Autowired
    List<ValidarTopicos> validadores;

    public Topicos RegistrarTopico(@Valid RegistrarTopicosDTO topicos){
    validadores.forEach(v->v.validar(topicos));
    return new Topicos(topicos);
    }
}
