package br.com.abreu.taskmanager.application.tarefa;

import br.com.abreu.taskmanager.adapters.TarefaRepositoryService;
import br.com.abreu.taskmanager.application.exception.TarefaNaoEncontradaException;
import br.com.abreu.taskmanager.core.entities.Prioridade;
import br.com.abreu.taskmanager.core.entities.Projeto;
import br.com.abreu.taskmanager.core.entities.Status;
import br.com.abreu.taskmanager.core.entities.Tarefa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

class BuscarTarefasPorProjetoUseCaseImplTest {

    @InjectMocks
    private BuscarTarefasPorProjetoUseCaseImpl buscarTarefasPorProjetoUseCase;

    @Mock
    private TarefaRepositoryService repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveRetornar_ListaDeTarefas_quandoBuscarTarefaPorProjetoIdValido() {
        UUID idProjeto = UUID.randomUUID();
        List<Tarefa> tarefas = List.of(criarTarefaModelo());

        when(repository.existeTarefaPorProjetoId(idProjeto)).thenReturn(true);
        when(repository.buscarPorProjeto(idProjeto)).thenReturn(tarefas);

        List<Tarefa> resposta = buscarTarefasPorProjetoUseCase.buscarPorProjeto(idProjeto);

        verify(repository).existeTarefaPorProjetoId(idProjeto);
        verify(repository).buscarPorProjeto(idProjeto);

        verifyNoMoreInteractions(repository);

        assertEquals(tarefas, resposta);
    }

    @Test
    public void deveRetornar_TarefaNaoEncontradaException_quandoBuscarTarefaPorProjetoIdInvalido() {
        UUID idProjeto = UUID.randomUUID();

        when(repository.existeTarefaPorProjetoId(idProjeto)).thenReturn(false);

        Exception exception = assertThrows(TarefaNaoEncontradaException.class,
                () -> buscarTarefasPorProjetoUseCase.buscarPorProjeto(idProjeto));

        verify(repository).existeTarefaPorProjetoId(idProjeto);
        verifyNoMoreInteractions(repository);

        assertEquals("Tarefa não encontrada!", exception.getMessage());
    }

    private Tarefa criarTarefaModelo() {
        return new Tarefa(null, "Nova tarefa", "Descrição da nova tarefa",
                LocalDate.of(2024, 10, 25), LocalDate.of(2024, 10, 25),
                LocalDate.of(2024, 10, 25), Status.PLANEJAMENTO,
                Prioridade.BAIXA, "Nome responsavel", criarProjetoModelo());
    }

    private Projeto criarProjetoModelo() {
        return new Projeto(null, "Nome Projeto", "Projeto descição",
                LocalDate.of(2024, 10, 25), LocalDate.of(2025, 10, 25),
                Status.EXECUCAO, Prioridade.BAIXA);
    }

}