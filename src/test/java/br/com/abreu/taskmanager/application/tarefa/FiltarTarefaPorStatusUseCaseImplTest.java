package br.com.abreu.taskmanager.application.tarefa;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import br.com.abreu.taskmanager.adapters.TarefaRepositoryService;
import br.com.abreu.taskmanager.core.entities.Prioridade;
import br.com.abreu.taskmanager.core.entities.Projeto;
import br.com.abreu.taskmanager.core.entities.Status;
import br.com.abreu.taskmanager.core.entities.Tarefa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class FiltarTarefaPorStatusUseCaseImplTest {

    @InjectMocks
    private FiltarTarefaPorStatusUseCaseImpl filtrarTarefaPorStatusUseCase;

    @Mock
    private TarefaRepositoryService repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveRetornar_listaDeTarefas_quandoFiltradoPorIdProjetoEStatusComSucesso() {
        UUID idProjeto = UUID.randomUUID();
        Status status = Status.EXECUCAO;
        List<Tarefa> tarefas = Arrays.asList(criarTarefaModelo());

        when(repository.filtarPorIdEStatus(idProjeto, status)).thenReturn(tarefas);

        List<Tarefa> resposta = filtrarTarefaPorStatusUseCase.filtrarPorIdEStatus(idProjeto, status);

        verify(repository).filtarPorIdEStatus(idProjeto, status);
        verifyNoMoreInteractions(repository);

        assertEquals(tarefas, resposta);
    }

    @Test
    public void deveRetornar_listaVazia_quandoNaoForEncontradoPorIdProjetoEStatus() {
        UUID idProjeto = UUID.randomUUID();
        Status status = Status.EXECUCAO;

        when(repository.filtarPorIdEStatus(idProjeto, status)).thenReturn(Collections.emptyList());

        List<Tarefa> resposta = filtrarTarefaPorStatusUseCase.filtrarPorIdEStatus(idProjeto, status);

        verify(repository).filtarPorIdEStatus(idProjeto, status);
        verifyNoMoreInteractions(repository);

        assertTrue(resposta.isEmpty());
    }

    private Tarefa criarTarefaModelo() {
        return new Tarefa(null, "Tarefa filtrar", "Descrição da tarefa",
                LocalDate.of(2024, 10, 25), LocalDate.of(2024, 10, 25),
                LocalDate.of(2024, 10, 25), Status.EXECUCAO,
                Prioridade.BAIXA, "Nome responsável", criarProjetoModelo());
    }

    private Projeto criarProjetoModelo() {
        return new Projeto(null, "Nome Projeto", "Projeto descrição",
                LocalDate.of(2024, 10, 25), LocalDate.of(2025, 10, 25),
                Status.EXECUCAO, Prioridade.BAIXA);
    }
}