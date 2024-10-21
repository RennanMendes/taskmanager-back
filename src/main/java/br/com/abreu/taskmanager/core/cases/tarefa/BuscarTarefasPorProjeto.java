package br.com.abreu.taskmanager.core.cases.tarefa;

import br.com.abreu.taskmanager.core.entities.Tarefa;

import java.util.List;
import java.util.UUID;

public interface BuscarTarefasPorProjeto {
    List<Tarefa> buscarPorProjeto(UUID idProjeto);
}
