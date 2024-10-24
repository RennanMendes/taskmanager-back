package br.com.abreu.taskmanager.core.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Projeto {
    private UUID id;
    private String nome;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Status status;
    private Prioridade prioridade;

    public Projeto atualizar(Projeto dto) {
        this.nome = dto.getNome() != null ? dto.getNome() : this.nome;
        this.descricao = dto.getDescricao() != null ? dto.getDescricao() : this.descricao;
        this.dataInicio = dto.getDataInicio() != null ? dto.getDataInicio() : this.dataInicio;
        this.dataFim = dto.getDataFim() != null ? dto.getDataFim() : this.dataFim;
        this.status = dto.getStatus() != null ? dto.getStatus() : this.status;
        this.prioridade = dto.getPrioridade() != null ? dto.getPrioridade() : this.prioridade;
        return this;
    }
}