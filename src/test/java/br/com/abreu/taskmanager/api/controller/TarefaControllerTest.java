package br.com.abreu.taskmanager.api.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import br.com.abreu.taskmanager.api.converter.TarefaConverter;
import br.com.abreu.taskmanager.api.dto.TarefaRequestDto;
import br.com.abreu.taskmanager.api.handler.GraphQLExceptionMapperRegistry;
import br.com.abreu.taskmanager.core.cases.tarefa.*;
import br.com.abreu.taskmanager.core.entities.Prioridade;
import br.com.abreu.taskmanager.core.entities.Status;
import br.com.abreu.taskmanager.core.entities.Tarefa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@GraphQlTest(TarefaController.class)
class TarefaControllerTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @MockBean
    private GraphQLExceptionMapperRegistry mapperRegistry;

    @MockBean
    private BuscarTarefaPorIdUseCase buscarTarefaPorIdUseCase;

    @MockBean
    private BuscarTarefasPorProjetoUseCase buscarTarefasPorProjetoUseCase;

    @MockBean
    private CriarTarefaUseCase criarTarefaUseCase;

    @MockBean
    private ExcluirTarefaUseCase excluirTarefaUseCase;

    @MockBean
    private FiltarTarefaPorStatusUseCase filtarTarefaPorStatusUseCase;

    @MockBean
    private AtualizarTarefaUseCase atualizarTarefaUseCase;

    @MockBean
    private TarefaConverter converter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveRetornarTarefaPorId() {
        Tarefa tarefa = criarTarefa();
        UUID id = tarefa.getId();

        when(buscarTarefaPorIdUseCase.buscarPorId(id)).thenReturn(tarefa);

        String query = """
                    query {
                        buscarTarefaPorId(id: "%s") {
                            id
                            titulo
                            descricao
                            prazo
                            dataInicio
                            dataFim
                            status
                            prioridade
                            responsavel
                        }
                    }
                """.formatted(id);

        graphQlTester.document(query)
                .execute()
                .path("buscarTarefaPorId")
                .entity(Tarefa.class)
                .satisfies(t -> {
                    assertThat(t.getId()).isEqualTo(id);
                    assertThat(t.getTitulo()).isEqualTo("Título da Tarefa");
                });

        verify(buscarTarefaPorIdUseCase).buscarPorId(id);

        verifyNoMoreInteractions(buscarTarefaPorIdUseCase);

        verifyNoInteractions(excluirTarefaUseCase, atualizarTarefaUseCase, converter);
        verifyNoInteractions(buscarTarefasPorProjetoUseCase, filtarTarefaPorStatusUseCase, criarTarefaUseCase);
    }

    @Test
    public void deveRetornarTarefasPorProjetoId() {
        UUID idProjeto = UUID.randomUUID();
        List<Tarefa> tarefas = List.of(criarTarefa());

        when(buscarTarefasPorProjetoUseCase.buscarPorProjeto(idProjeto)).thenReturn(tarefas);

        String query = """
                    query {
                        buscarTarefasPorProjetoId(idProjeto: "%s") {
                            id
                            titulo
                            descricao
                            prazo
                            dataInicio
                            dataFim
                            status
                            prioridade
                            responsavel
                        }
                    }
                """.formatted(idProjeto);

        graphQlTester.document(query)
                .execute()
                .path("buscarTarefasPorProjetoId")
                .entityList(Tarefa.class)
                .satisfies(lista -> {
                    assertThat(lista).isNotEmpty();
                    assertThat(lista.get(0).getTitulo()).isEqualTo("Título da Tarefa");
                });

        verify(buscarTarefasPorProjetoUseCase).buscarPorProjeto(idProjeto);

        verifyNoMoreInteractions(buscarTarefasPorProjetoUseCase);

        verifyNoInteractions(excluirTarefaUseCase, atualizarTarefaUseCase, converter);
        verifyNoInteractions(buscarTarefaPorIdUseCase, filtarTarefaPorStatusUseCase, criarTarefaUseCase);
    }

    @Test
    public void deveCriarTarefa() {
        UUID idProjeto = UUID.randomUUID();
        Tarefa tarefaCriada = criarTarefa();
        TarefaRequestDto dto = criarTarefaDto();

        when(converter.dtoToEntity(any())).thenReturn(tarefaCriada);
        when(criarTarefaUseCase.criar(idProjeto, tarefaCriada)).thenReturn(tarefaCriada);

        String mutation = """
                mutation {
                    criarTarefa(idProjeto: "%s", tarefa: { 
                        titulo: "%s", 
                        descricao: "%s", 
                        prazo: "%s", 
                        dataInicio: "%s", 
                        dataFim: "%s", 
                        status: %s,
                        prioridade: %s, 
                        responsavel: "%s" 
                    }) {
                        id
                        titulo
                        descricao
                        prazo
                        dataInicio
                        dataFim
                        status
                        prioridade
                        responsavel
                    }
                }
                """.formatted(
                idProjeto, dto.titulo(), dto.descricao(),
                dto.prazo().toString(), dto.dataInicio().toString(), dto.dataFim().toString(),
                dto.status(), dto.prioridade(), dto.responsavel());

        graphQlTester.document(mutation)
                .execute()
                .path("criarTarefa")
                .entity(Tarefa.class)
                .satisfies(t -> {
                    assertThat(t.getId()).isEqualTo(tarefaCriada.getId());
                    assertThat(t.getTitulo()).isEqualTo("Título da Tarefa");
                    assertThat(t.getDescricao()).isEqualTo("Descrição da Tarefa");
                });

        verify(converter).dtoToEntity(any());
        verify(criarTarefaUseCase).criar(idProjeto, tarefaCriada);

        verifyNoMoreInteractions(converter, criarTarefaUseCase);

        verifyNoInteractions(buscarTarefaPorIdUseCase, buscarTarefasPorProjetoUseCase);
        verifyNoInteractions(filtarTarefaPorStatusUseCase, excluirTarefaUseCase, atualizarTarefaUseCase);
    }

    @Test
    public void deveAtualizarTarefa() {
        TarefaRequestDto tarefaDto = criarTarefaDto();
        Tarefa tarefaAtualizada = criarTarefa();
        UUID id = tarefaAtualizada.getId();

        when(converter.dtoToEntity(tarefaDto)).thenReturn(tarefaAtualizada);
        when(atualizarTarefaUseCase.atualizar(id, tarefaAtualizada)).thenReturn(tarefaAtualizada);

        String mutation = """
                mutation {
                    atualizarTarefa(id: "%s", tarefa: { 
                        titulo: "%s", 
                        descricao: "%s", 
                        prazo: "%s", 
                        dataInicio: "%s", 
                        dataFim: "%s", 
                        status: %s,
                        prioridade: %s, 
                        responsavel: "%s" 
                    }) {
                        id
                        titulo
                        descricao
                        prazo
                        dataInicio
                        dataFim
                        status
                        prioridade
                        responsavel
                    }
                }
                """.formatted(
                id, tarefaDto.titulo(), tarefaDto.descricao(),
                tarefaDto.prazo().toString(), tarefaDto.dataInicio().toString(), tarefaDto.dataFim().toString(),
                tarefaDto.status(), tarefaDto.prioridade(), tarefaDto.responsavel());

        graphQlTester.document(mutation)
                .execute()
                .path("atualizarTarefa")
                .entity(Tarefa.class)
                .satisfies(t -> {
                    assertThat(t.getTitulo()).isEqualTo(tarefaDto.titulo());
                    assertThat(t.getDescricao()).isEqualTo(tarefaDto.descricao());
                });

        verify(atualizarTarefaUseCase).atualizar(id, tarefaAtualizada);
        verify(converter).dtoToEntity(tarefaDto);

        verifyNoMoreInteractions(atualizarTarefaUseCase, converter);

        verifyNoInteractions(buscarTarefaPorIdUseCase, buscarTarefasPorProjetoUseCase);
        verifyNoInteractions(filtarTarefaPorStatusUseCase, criarTarefaUseCase, excluirTarefaUseCase);
    }

    @Test
    public void deveExcluirTarefa() {
        UUID id = UUID.randomUUID();

        String mutation = """
                    mutation {
                        excluirTarefa(id: "%s")
                    }
                """.formatted(id);

        graphQlTester.document(mutation)
                .execute()
                .path("excluirTarefa")
                .entity(Boolean.class)
                .isEqualTo(true);

        verify(excluirTarefaUseCase).excluir(id);

        verifyNoMoreInteractions(excluirTarefaUseCase);

        verifyNoInteractions(criarTarefaUseCase, atualizarTarefaUseCase, converter);
        verifyNoInteractions(buscarTarefaPorIdUseCase, buscarTarefasPorProjetoUseCase, filtarTarefaPorStatusUseCase);
    }

    private TarefaRequestDto criarTarefaDto() {
        return new TarefaRequestDto(
                "Título da Tarefa",
                "Descrição da Tarefa",
                LocalDate.now().plusDays(5), // prazo
                LocalDate.now(), // dataInicio
                LocalDate.now().plusDays(3), // dataFim
                Status.PLANEJAMENTO,
                Prioridade.MEDIA,
                "responsavel@exemplo.com"
        );
    }

    private Tarefa criarTarefa() {
        return new Tarefa(
                UUID.randomUUID(),
                "Título da Tarefa",
                "Descrição da Tarefa",
                LocalDate.now().plusDays(5),
                LocalDate.now(),
                LocalDate.now().plusDays(3),
                Status.EXECUCAO,
                Prioridade.MEDIA,
                "responsavel@exemplo.com",
                null
        );
    }
}