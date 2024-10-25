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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CriarProjetoUseCaseImplTest {

    @InjectMocks
    private CriarProjetoUseCaseImpl criarProjetoUseCase;

    @Mock
    private ProjetoRepositoryService repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveRetornar_projeto_quandoCriarProjetoComValoresValidos() {
        Projeto projeto = criarProjetoModelo();

        when(repository.salvar(projeto)).thenReturn(projeto);

        Projeto resposta = criarProjetoUseCase.criar(projeto);

        verify(repository).salvar(projeto);
        verifyNoMoreInteractions(repository);

        assertEquals(projeto, resposta);
    }

    private Projeto criarProjetoModelo() {
        return new Projeto(null, "Nome Projeto", "Projeto descição",
                LocalDate.of(2024, 10, 25), LocalDate.of(2025, 10, 25),
                Status.EXECUCAO, Prioridade.BAIXA);
    }

}