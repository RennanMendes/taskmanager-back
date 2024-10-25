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
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BuscarTarefaPorIdUseCaseImplTest {

    @InjectMocks
    private BuscarTarefaPorIdUseCaseImpl buscarTarefaPorIdUseCase;

    @Mock
    private TarefaRepositoryService repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveRetornar_tarefa_quandoCriarTarefaComSucesso() {
        UUID id = UUID.randomUUID();
        Tarefa tarefa = criarTarefaModelo();

        when(repository.buscarPorId(id)).thenReturn(Optional.of(tarefa));

        Tarefa resposta = buscarTarefaPorIdUseCase.buscarPorId(id);

        verify(repository).buscarPorId(id);
        verifyNoMoreInteractions(repository);

        assertEquals(tarefa, resposta);
    }

    @Test
    public void deveRetornar_TarefaNaoEncontradaException_quandoBuscarTarefaComIdInvalido() {
        UUID id = UUID.randomUUID();

        when(repository.buscarPorId(id)).thenThrow(new TarefaNaoEncontradaException());

        Exception exception = assertThrows(TarefaNaoEncontradaException.class, () -> buscarTarefaPorIdUseCase.buscarPorId(id));

        verify(repository).buscarPorId(id);
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