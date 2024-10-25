package br.com.abreu.taskmanager.application.tarefa;

import br.com.abreu.taskmanager.adapters.TarefaRepositoryService;
import br.com.abreu.taskmanager.application.exception.ProjetoNaoEncontradoException;
import br.com.abreu.taskmanager.core.cases.projeto.BuscarProjetoPorIdUseCase;
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

class CriarTarefaUseCaseImplTest {

    @InjectMocks
    private CriarTarefaUseCaseImpl criarTarefaUseCase;

    @Mock
    private TarefaRepositoryService repository;

    @Mock
    private BuscarProjetoPorIdUseCase buscarProjetoPorIdUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveRetornar_tarefa_quandoCriarTarefaComSucesso() {
        UUID idProjeto = UUID.randomUUID();
        Tarefa tarefa = criarTarefaModelo();
        Projeto projeto = criarProjetoModelo();

        when(buscarProjetoPorIdUseCase.buscar(idProjeto)).thenReturn(projeto);
        when(repository.salvar(tarefa)).thenReturn(tarefa);

        Tarefa resposta = criarTarefaUseCase.criar(idProjeto, tarefa);

        verify(buscarProjetoPorIdUseCase).buscar(idProjeto);
        verify(repository).salvar(tarefa);

        verifyNoMoreInteractions(buscarProjetoPorIdUseCase, repository);

        assertNotNull(resposta);
        assertEquals(projeto, resposta.getProjeto());
    }

    @Test
    public void deveRetornar_ProjetoNaoEncontradoException_quandoCriarTarefaComIdProjetoInvalido() {
        UUID idProjeto = UUID.randomUUID();
        Tarefa tarefa = new Tarefa();
        when(buscarProjetoPorIdUseCase.buscar(idProjeto)).thenThrow(new ProjetoNaoEncontradoException());

        Exception exception = assertThrows(ProjetoNaoEncontradoException.class, () -> criarTarefaUseCase.criar(idProjeto, tarefa));

        verify(buscarProjetoPorIdUseCase).buscar(idProjeto);

        verifyNoMoreInteractions(buscarProjetoPorIdUseCase);
        verifyNoInteractions(repository);

        assertEquals("Projeto não encontrado!", exception.getMessage());
    }

    private Tarefa criarTarefaModelo() {
        return new Tarefa(null, "Nova tarefa", "Descrição da nova tarefa",
                LocalDate.of(2024, 10, 25), LocalDate.of(2024, 10, 25),
                LocalDate.of(2024, 10, 25), Status.PLANEJAMENTO,
                Prioridade.BAIXA, "Nome responsavel", null);
    }

    private Projeto criarProjetoModelo() {
        return new Projeto(null, "Nome Projeto", "Projeto descição",
                LocalDate.of(2024, 10, 25), LocalDate.of(2025, 10, 25),
                Status.EXECUCAO, Prioridade.BAIXA);
    }

}