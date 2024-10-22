package br.com.abreu.taskmanager.infra.data.jpa.entity;

import br.com.abreu.taskmanager.core.entities.Prioridade;
import br.com.abreu.taskmanager.core.entities.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tb_tarefa")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class TarefaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String titulo;
    private String descricao;
    private LocalDate prazo;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;
    private String responsavel;

    @ManyToOne
    @JoinColumn(name = "projeto_id")
    private ProjetoEntity projeto;
}