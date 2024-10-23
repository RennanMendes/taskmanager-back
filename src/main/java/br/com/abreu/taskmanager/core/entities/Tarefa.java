package br.com.abreu.taskmanager.core.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Tarefa {
    private UUID id;
    private String titulo;
    private String descricao;
    private LocalDate prazo;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Status status;
    private Prioridade prioridade;
    private String responsavel;
    private Projeto projeto;

    public Tarefa atualizar(Tarefa dto) {
        this.titulo = dto.getTitulo() != null ? dto.getTitulo() : this.titulo;
        this.descricao = dto.getDescricao() != null ? dto.getDescricao() : this.descricao;
        this.prazo = dto.getPrazo() != null ? dto.getPrazo() : this.prazo;
        this.dataInicio = dto.getDataInicio() != null ? dto.getDataInicio() : this.dataInicio;
        this.dataFim = dto.getDataFim() != null ? dto.getDataFim() : this.dataFim;
        this.status = dto.getStatus() != null ? dto.getStatus() : this.status;
        this.prioridade = dto.getPrioridade() != null ? dto.getPrioridade() : this.prioridade;
        this.responsavel = dto.getResponsavel() != null ? dto.getResponsavel() : this.responsavel;
        return this;
    }

}