package br.com.abreu.taskmanager.application.projeto;

import br.com.abreu.taskmanager.adapters.ProjetoRepositoryService;
import br.com.abreu.taskmanager.api.dto.HeaderInfo;
import br.com.abreu.taskmanager.application.exception.ProjetoNaoEncontradoException;
import br.com.abreu.taskmanager.common.aop.CacheAnnotation;
import br.com.abreu.taskmanager.core.entities.Projeto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TestarCache {

    @Autowired
    private ProjetoRepositoryService repository;

    @CacheAnnotation
    public Projeto buscar(UUID id, HeaderInfo headerInfo) {
        return repository.buscarPorId(id).orElseThrow(ProjetoNaoEncontradoException::new);
    }

}
