package br.com.abreu.taskmanager.application.projeto;

import br.com.abreu.taskmanager.adapters.ProjetoRepositoryService;
import br.com.abreu.taskmanager.core.cases.projeto.BuscarProjetoPorNome;
import br.com.abreu.taskmanager.core.entities.Projeto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class BuscarProjetoPorNomeImpl implements BuscarProjetoPorNome {

    private ProjetoRepositoryService repository;

    @Override
    public List<Projeto> buscar(String nome) {
        return repository.buscarPorNome(nome);
    }
}