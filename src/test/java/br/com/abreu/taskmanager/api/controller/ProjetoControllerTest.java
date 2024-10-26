package br.com.abreu.taskmanager.api.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import br.com.abreu.taskmanager.api.converter.ProjetoConverter;
import br.com.abreu.taskmanager.api.dto.ProjetoRequestDto;
import br.com.abreu.taskmanager.core.cases.projeto.*;
import br.com.abreu.taskmanager.core.entities.Prioridade;
import br.com.abreu.taskmanager.core.entities.Projeto;
import br.com.abreu.taskmanager.core.entities.Status;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.graphql.test.tester.GraphQlTester;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@GraphQlTest(ProjetoController.class)
public class ProjetoControllerTest {

    @Autowired
    private GraphQlTester graphQlTester;

    @MockBean
    private BuscarProjetoPorIdUseCase buscarProjetoPorIdUseCase;

    @MockBean
    private BuscarProjetoPorNomeUseCase buscarProjetoPorNomeUseCase;

    @MockBean
    private BuscarTodosProjetosUseCase buscarTodosProjetosUseCase;

    @MockBean
    private CriarProjetoUseCase criarProjetoUseCase;

    @MockBean
    private ExcluirProjetoUseCase excluirProjetoUseCase;

    @MockBean
    private AtualizarProjetoUseCase atualizarProjetoUseCase;

    @MockBean
    private ProjetoConverter converter;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        SimpleModule module = new SimpleModule();
        module.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(module);
    }

    @Test
    public void deveRetornarProjetoPorId() {
        Projeto projeto = criarProjeto();
        UUID id = projeto.getId();

        when(buscarProjetoPorIdUseCase.buscar(id)).thenReturn(projeto);

        String query = """
                    query {
                        buscarProjetoPorId(id: "%s") {
                            id
                            nome
                            descricao
                        }
                    }
                """.formatted(id);

        graphQlTester.document(query)
                .execute()
                .path("buscarProjetoPorId")
                .entity(Projeto.class)
                .satisfies(proj -> {
                    assertThat(proj.getId()).isEqualTo(id);
                    assertThat(proj.getNome()).isEqualTo("Nome Projeto");
                });

        verify(buscarProjetoPorIdUseCase).buscar(id);

        verifyNoMoreInteractions(buscarProjetoPorIdUseCase);

        verifyNoInteractions(buscarProjetoPorNomeUseCase, buscarTodosProjetosUseCase);
        verifyNoInteractions(criarProjetoUseCase, excluirProjetoUseCase, atualizarProjetoUseCase, converter);
    }

    @Test
    public void deveRetornarListaDeProjetosPorNome() {
        String nome = "Projeto Teste";
        List<Projeto> projetos = List.of(criarProjeto());

        when(buscarProjetoPorNomeUseCase.buscar(nome)).thenReturn(projetos);

        String query = """
                    query {
                        buscarProjetoPorNome(nome: "%s") {
                            id
                            nome
                            descricao
                        }
                    }
                """.formatted(nome);

        graphQlTester.document(query)
                .execute()
                .path("buscarProjetoPorNome")
                .entityList(Projeto.class)
                .satisfies(lista -> {
                    assertThat(lista).isNotEmpty();
                    assertThat(lista.get(0).getNome()).isEqualTo("Nome Projeto");
                });

        verify(buscarProjetoPorNomeUseCase).buscar(nome);

        verifyNoMoreInteractions(buscarProjetoPorNomeUseCase);

        verifyNoInteractions(buscarProjetoPorIdUseCase, buscarTodosProjetosUseCase);
        verifyNoInteractions(criarProjetoUseCase, excluirProjetoUseCase, atualizarProjetoUseCase, converter);
    }

    @Test
    public void deveRetornarListaDeTodosOsProjetos() {
        List<Projeto> projetos = List.of(criarProjeto());

        when(buscarTodosProjetosUseCase.buscar()).thenReturn(projetos);

        String query = """
                    query {
                        buscarTodosOsProjetos {
                            id
                            nome
                            descricao
                        }
                    }
                """;

        graphQlTester.document(query)
                .execute()
                .path("buscarTodosOsProjetos")
                .entityList(Projeto.class)
                .satisfies(lista -> {
                    assertThat(lista).isNotEmpty();
                    assertThat(lista.get(0).getNome()).isEqualTo("Nome Projeto");
                });

        verify(buscarTodosProjetosUseCase).buscar();

        verifyNoMoreInteractions(buscarTodosProjetosUseCase);

        verifyNoInteractions(buscarProjetoPorIdUseCase, buscarProjetoPorNomeUseCase);
        verifyNoInteractions(criarProjetoUseCase, excluirProjetoUseCase, atualizarProjetoUseCase, converter);
    }

    @Test
    public void deveCriarProjeto() {
        ProjetoRequestDto projetoDto = criarProjetoDto();
        Projeto projetoCriado = criarProjeto();

        Map projetoMap = objectMapper.convertValue(projetoDto, Map.class);

        when(converter.dtoToEntity(any())).thenReturn(projetoCriado);
        when(criarProjetoUseCase.criar(any())).thenReturn(projetoCriado);

        String mutation = """
                    mutation($projeto: Projeto!) {
                        criarProjeto(projeto: $projeto) {
                            id
                            nome
                            descricao
                        }
                    }
                """;

        graphQlTester.document(mutation)
                .variable("projeto", projetoMap)
                .execute()
                .path("criarProjeto")
                .entity(Projeto.class)
                .satisfies(proj -> {
                    assertThat(proj.getId()).isEqualTo(projetoCriado.getId());
                    assertThat(proj.getNome()).isEqualTo("Nome Projeto");
                    assertThat(proj.getDescricao()).isEqualTo("Projeto descrição");
                });

        verify(converter).dtoToEntity(projetoDto);
        verify(criarProjetoUseCase).criar(projetoCriado);

        verifyNoMoreInteractions(converter, criarProjetoUseCase);

        verifyNoInteractions(buscarProjetoPorIdUseCase, buscarProjetoPorNomeUseCase);
        verifyNoInteractions(buscarTodosProjetosUseCase, excluirProjetoUseCase, atualizarProjetoUseCase);
    }

    @Test
    public void deveAtualizarProjeto() {
        ProjetoRequestDto projetoDto = criarProjetoDto();
        Projeto projetoAtualizado = criarProjeto();
        UUID id = projetoAtualizado.getId();

        when(converter.dtoToEntity(projetoDto)).thenReturn(projetoAtualizado);
        when(atualizarProjetoUseCase.atualizar(eq(id), any())).thenReturn(projetoAtualizado);

        String mutation = """
        mutation {
            atualizarProjeto(id: "%s", projeto: { 
                nome: "%s", 
                descricao: "%s" 
            }) {
                id
                nome
                descricao
            }
        }
    """.formatted(id, projetoDto.nome(), projetoDto.descricao());

        graphQlTester.document(mutation)
                .execute()
                .path("atualizarProjeto")
                .entity(Projeto.class)
                .satisfies(proj -> {
                    assertThat(proj.getNome()).isEqualTo(projetoDto.nome());
                    assertThat(proj.getDescricao()).isEqualTo(projetoDto.descricao());
                });

        verify(atualizarProjetoUseCase).atualizar(eq(id), any());
        verify(converter).dtoToEntity(any());

        verifyNoMoreInteractions(atualizarProjetoUseCase, converter);

        verifyNoInteractions(buscarProjetoPorNomeUseCase, buscarTodosProjetosUseCase, excluirProjetoUseCase);
        verifyNoInteractions(criarProjetoUseCase, buscarProjetoPorIdUseCase);
    }

    @Test
    public void deveExcluirProjeto() {
        UUID id = UUID.randomUUID();

        String mutation = """
                    mutation {
                        excluirProjeto(id: "%s")
                    }
                """.formatted(id);

        graphQlTester.document(mutation)
                .execute()
                .path("excluirProjeto")
                .entity(Boolean.class)
                .isEqualTo(true);

        verify(excluirProjetoUseCase).excluir(id);

        verifyNoMoreInteractions(excluirProjetoUseCase);

        verifyNoInteractions(buscarProjetoPorNomeUseCase, buscarTodosProjetosUseCase);
        verifyNoInteractions(criarProjetoUseCase, buscarProjetoPorIdUseCase, atualizarProjetoUseCase, converter);
    }

    private ProjetoRequestDto criarProjetoDto() {
        return new ProjetoRequestDto("Nome Projeto", "Projeto descrição",
                LocalDate.now(), LocalDate.now().plusDays(30), Status.PLANEJAMENTO,
                Prioridade.MEDIA);
    }

    private Projeto criarProjeto() {
        return new Projeto(UUID.randomUUID(), "Nome Projeto", "Projeto descrição",
                LocalDate.now(), LocalDate.now().plusDays(30), Status.PLANEJAMENTO,
                Prioridade.MEDIA);
    }

}