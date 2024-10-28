package br.com.abreu.taskmanager.infra.data.jpa.repository;

import br.com.abreu.taskmanager.infra.data.jpa.entity.ProjetoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ProjetoRepository extends JpaRepository<ProjetoEntity, UUID> {
    List<ProjetoEntity> findByNomeContainingIgnoreCase(String nome);
}