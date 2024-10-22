package br.com.abreu.taskmanager.core.cases.projeto;

import br.com.abreu.taskmanager.core.entities.Projeto;

import java.util.List;

public interface BuscarTodosProjetosUseCase {
    List<Projeto> buscar();
}