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
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExcluirTarefaUseCaseImplTest {

    @InjectMocks
    private ExcluirTarefaUseCaseImpl excluirTarefaUseCase;

    @Mock
    private TarefaRepositoryService repository;

    @Mock
    private BuscarTarefaPorIdUseCaseImpl buscarTarefaPorIdUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveExcluirTarefaComSucesso() {
        UUID id = UUID.randomUUID();
        Tarefa tarefa = criarTarefaModelo();

        when(buscarTarefaPorIdUseCase.buscarPorId(id)).thenReturn(tarefa);

        excluirTarefaUseCase.excluir(id);

        verify(buscarTarefaPorIdUseCase).buscarPorId(id);
        verify(repository).deletar(tarefa);

        verifyNoMoreInteractions(repository, buscarTarefaPorIdUseCase);
    }

    @Test
    public void deveRetornar_TarefaNaoEncontradaException_quandoTarefaNaoExistir() {
        UUID id = UUID.randomUUID();

        when(buscarTarefaPorIdUseCase.buscarPorId(id)).thenThrow(new TarefaNaoEncontradaException());

        Exception exception = assertThrows(TarefaNaoEncontradaException.class, () -> excluirTarefaUseCase.excluir(id));

        verify(buscarTarefaPorIdUseCase).buscarPorId(id);
        verify(repository, never()).deletar(any(Tarefa.class));  // O método deletar não deve ser chamado

        verifyNoMoreInteractions(repository, buscarTarefaPorIdUseCase);

        assertEquals("Tarefa não encontrada!", exception.getMessage());
    }

    private Tarefa criarTarefaModelo() {
        return new Tarefa(null, "Tarefa para deletar", "Descrição da tarefa",
                LocalDate.of(2024, 10, 25), LocalDate.of(2024, 10, 25),
                LocalDate.of(2024, 10, 25), Status.PLANEJAMENTO,
                Prioridade.BAIXA, "Nome responsável", criarProjetoModelo());
    }

    private Projeto criarProjetoModelo() {
        return new Projeto(null, "Nome Projeto", "Projeto descrição",
                LocalDate.of(2024, 10, 25), LocalDate.of(2025, 10, 25),
                Status.EXECUCAO, Prioridade.BAIXA);
    }

}