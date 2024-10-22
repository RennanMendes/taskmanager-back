package br.com.abreu.taskmanager.core.cases.projeto;

import br.com.abreu.taskmanager.core.entities.Projeto;

import java.util.UUID;

public interface BuscarProjetoPorIdUseCase {
    Projeto buscar(UUID id);
}