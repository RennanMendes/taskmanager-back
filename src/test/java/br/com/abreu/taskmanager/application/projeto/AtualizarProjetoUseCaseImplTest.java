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
import static org.mockito.Mockito.verifyNoMoreInteractions;

class AtualizarProjetoUseCaseImplTest {

    @InjectMocks
    AtualizarProjetoUseCaseImpl atualizarProjetoUseCase;

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
        Projeto projeto = criarProjetoModelo("Nome Projeto");
        Projeto resultadoEsperado = criarProjetoModelo("Nome do Projeto atualizado");

        when(buscarProjetoPorIdUseCase.buscar(id)).thenReturn(projeto);
        when(repository.salvar(projeto)).thenReturn(resultadoEsperado);

        Projeto resposta = atualizarProjetoUseCase.atualizar(id, new Projeto(null, "Nome do Projeto atualizado", null, null, null, null, null));

        verify(buscarProjetoPorIdUseCase).buscar(id);
        verify(repository).salvar(projeto);

        verifyNoMoreInteractions(buscarProjetoPorIdUseCase, repository);

        assertEquals(resultadoEsperado, resposta);
    }

    @Test
    void deveRetornar_ProjetoNaoEncontradoException_quandoAtualizarProjetoComIdInvalido() {
        UUID id = UUID.randomUUID();
        Projeto projeto = criarProjetoModelo("Nome Projeto");

        when(buscarProjetoPorIdUseCase.buscar(id)).thenThrow(new ProjetoNaoEncontradoException());

        assertThrows(ProjetoNaoEncontradoException.class, () -> atualizarProjetoUseCase.atualizar(id, projeto));

        verify(buscarProjetoPorIdUseCase).buscar(id);
        verifyNoMoreInteractions(buscarProjetoPorIdUseCase);
        verifyNoInteractions(repository);
    }

    private Projeto criarProjetoModelo(String nome) {
        return new Projeto(null, nome, "Projeto descição",
                LocalDate.of(2024, 10, 25), LocalDate.of(2025, 10, 25),
                Status.EXECUCAO, Prioridade.BAIXA);
    }

}