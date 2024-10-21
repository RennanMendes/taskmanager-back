package br.com.abreu.taskmanager.application.projeto;

import br.com.abreu.taskmanager.adapters.ProjetoRepositoryService;
import br.com.abreu.taskmanager.core.cases.projeto.ExcluirProjeto;
import br.com.abreu.taskmanager.core.entities.Projeto;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
public class ExcluirProjetoImpl implements ExcluirProjeto {

    private ProjetoRepositoryService repository;
    private BuscarProjetoPorIdImpl buscarProjetoPorId;

    @Override
    public void excluir(UUID id) {
        Projeto projeto = buscarProjetoPorId.buscar(id);
        repository.deletar(projeto);
    }
}
// TODO -> Criar arquivo de configuração e testar projeto