package br.com.abreu.taskmanager.infra.data.jpa.repository;

import br.com.abreu.taskmanager.core.entities.Status;
import br.com.abreu.taskmanager.infra.data.jpa.entity.TarefaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TarefaRepository extends JpaRepository<TarefaEntity, UUID> {
    List<TarefaEntity> findByProjetoId(UUID idProjeto);
    List<TarefaEntity> findByProjetoIdAndStatus(UUID idProjeto, Status status);
    Boolean existsByProjetoId(UUID idProjeto);
}