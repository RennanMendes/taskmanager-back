package br.com.abreu.taskmanager.adapters;

import br.com.abreu.taskmanager.core.entities.Projeto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProjetoRepositoryService {

    Optional<Projeto> buscarPorId(UUID id);

    List<Projeto> buscarPorNome(String nome);

    List<Projeto> buscarTodos();

    Projeto salvar(Projeto projeto);

    void deletar(Projeto projeto);
}
