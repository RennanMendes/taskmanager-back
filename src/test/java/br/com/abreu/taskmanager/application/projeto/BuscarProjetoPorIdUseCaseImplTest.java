package br.com.abreu.taskmanager.application.projeto;

import br.com.abreu.taskmanager.adapters.ProjetoRepositoryService;
import br.com.abreu.taskmanager.application.exception.ProjetoNaoEncontradoException;
import br.com.abreu.taskmanager.core.entities.Prioridade;
import br.com.abreu.taskmanager.core.entities.Projeto;
import br.com.abreu.taskmanager.core.entities.Status;
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

class BuscarProjetoPorIdUseCaseImplTest {

    @InjectMocks
    BuscarProjetoPorIdUseCaseImpl buscarProjetoPorIdUseCase;

    @Mock
    ProjetoRepositoryService repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornar_projeto_quandoBuscarProjetoPorIdValido() {
        UUID id = UUID.randomUUID();
        Projeto projeto = criarProjetoModelo();

        when(repository.buscarPorId(id)).thenReturn(Optional.of(projeto));

        Projeto resposta = buscarProjetoPorIdUseCase.buscar(id);

        verify(repository).buscarPorId(id);
        verifyNoMoreInteractions(repository);

        assertEquals(projeto, resposta);
    }

    @Test
    void deveRetornar_ProjetoNaoEncontradoException_quandoBuscarProjetoPorIdInvalido() {
        UUID id = UUID.randomUUID();

        when(repository.buscarPorId(id)).thenThrow(new ProjetoNaoEncontradoException());

        assertThrows(ProjetoNaoEncontradoException.class, () -> buscarProjetoPorIdUseCase.buscar(id));

        verify(repository).buscarPorId(id);
        verifyNoMoreInteractions(repository);
    }

    private Projeto criarProjetoModelo() {
        return new Projeto(null, "Nome Projeto", "Projeto descição",
                LocalDate.of(2024, 10, 25), LocalDate.of(2025, 10, 25),
                Status.EXECUCAO, Prioridade.BAIXA);
    }

}