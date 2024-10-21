package br.com.abreu.taskmanager.adapters;

import br.com.abreu.taskmanager.core.entities.Tarefa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TarefaRepositoryService {
    Optional<Tarefa> buscarPorId(UUID id);

    List<Tarefa> buscarPorProjeto(UUID idProjeto);

    Tarefa save(Tarefa tarefa);

    void deletar(UUID id);

    List<Tarefa> filtarPorIdEStatus(UUID id, String status);
}