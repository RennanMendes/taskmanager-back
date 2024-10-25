package br.com.abreu.taskmanager.application.projeto;

import br.com.abreu.taskmanager.adapters.ProjetoRepositoryService;
import br.com.abreu.taskmanager.core.entities.Prioridade;
import br.com.abreu.taskmanager.core.entities.Projeto;
import br.com.abreu.taskmanager.core.entities.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

class BuscarProjetoPorNomeUseCaseImplTest {

    @InjectMocks
    private BuscarProjetoPorNomeUseCaseImpl buscarProjetoPorNomeUseCase;

    @Mock
    private ProjetoRepositoryService repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornar_projeto_quandoBuscarProjetoPorIdValido() {
        String nome = "Nome projeto";
        Projeto projeto = criarProjetoModelo();
        List<Projeto> projetos = new ArrayList<>();
        projetos.add(projeto);

        when(repository.buscarPorNome(nome)).thenReturn(projetos);

        List<Projeto> resposta = buscarProjetoPorNomeUseCase.buscar(nome);

        verify(repository).buscarPorNome(nome);
        verifyNoMoreInteractions(repository);

        assertEquals(projetos, resposta);
    }

    private Projeto criarProjetoModelo() {
        return new Projeto(null, "Nome Projeto", "Projeto descição",
                LocalDate.of(2024, 10, 25), LocalDate.of(2025, 10, 25),
                Status.EXECUCAO, Prioridade.BAIXA);
    }
}