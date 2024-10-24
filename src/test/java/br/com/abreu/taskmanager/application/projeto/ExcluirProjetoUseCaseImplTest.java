package br.com.abreu.taskmanager.application.projeto;

import br.com.abreu.taskmanager.adapters.ProjetoRepositoryService;
import br.com.abreu.taskmanager.application.exception.ProjetoNaoEncontradoException;
import br.com.abreu.taskmanager.core.cases.projeto.BuscarProjetoPorIdUseCase;
import br.com.abreu.taskmanager.core.entities.Prioridade;
import br.com.abreu.taskmanager.core.entities.Projeto;
import br.com.abreu.taskmanager.core.entities.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExcluirProjetoUseCaseImplTest {

    @InjectMocks
    ExcluirProjetoUseCaseImpl excluirProjetoUseCase;

    @Mock
    BuscarProjetoPorIdUseCase buscarProjetoPorIdUseCase;

    @Mock
    ProjetoRepositoryService repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornar_projeto_quandoAtualizarProjetoComIdValido() {
        UUID id = UUID.randomUUID();
        Projeto projeto = criarProjetoModelo();
        when(buscarProjetoPorIdUseCase.buscar(id)).thenReturn(projeto);

        excluirProjetoUseCase.excluir(id);

        verify(buscarProjetoPorIdUseCase).buscar(id);
        verify(repository).deletar(projeto);

        verifyNoMoreInteractions(buscarProjetoPorIdUseCase, repository);
    }

    @Test
    void deveRetornar_ProjetoNaoEncontradoException_quandoAtualizarProjetoComIdInvalido() {
        UUID id = UUID.randomUUID();

        when(buscarProjetoPorIdUseCase.buscar(id)).thenThrow(new ProjetoNaoEncontradoException());

        assertThrows(ProjetoNaoEncontradoException.class, () -> excluirProjetoUseCase.excluir(id));

        verify(buscarProjetoPorIdUseCase).buscar(id);
        verifyNoMoreInteractions(buscarProjetoPorIdUseCase);
        verifyNoInteractions(repository);
    }

    private Projeto criarProjetoModelo() {
        return new Projeto(null, "Nome Projeto", "Projeto descição",
                LocalDate.of(2024, 10, 25), LocalDate.of(2025, 10, 25),
                Status.EXECUCAO, Prioridade.BAIXA);
    }

}