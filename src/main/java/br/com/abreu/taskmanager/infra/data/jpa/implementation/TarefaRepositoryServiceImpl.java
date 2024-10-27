package br.com.abreu.taskmanager.infra.data.jpa.implementation;

import br.com.abreu.taskmanager.adapters.TarefaRepositoryService;
import br.com.abreu.taskmanager.core.entities.Status;
import br.com.abreu.taskmanager.core.entities.Tarefa;
import br.com.abreu.taskmanager.infra.data.jpa.converters.TarefaRepositoryConverter;
import br.com.abreu.taskmanager.infra.data.jpa.entity.TarefaEntity;
import br.com.abreu.taskmanager.infra.data.jpa.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TarefaRepositoryServiceImpl implements TarefaRepositoryService {

    private final TarefaRepository repository;
    private final TarefaRepositoryConverter converter;

    @Autowired
    public TarefaRepositoryServiceImpl(TarefaRepository repository, TarefaRepositoryConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public Optional<Tarefa> buscarPorId(UUID id) {
        Optional<TarefaEntity> entity = repository.findById(id);
        return entity.map(converter::mapToEntity);
    }

    @Override
    public List<Tarefa> buscarPorProjeto(UUID idProjeto) {
        List<TarefaEntity> entity = repository.findByProjetoId(idProjeto);
        return entity.stream().map(converter::mapToEntity).toList();
    }

    @Override
    public Tarefa salvar(Tarefa tarefa) {
        TarefaEntity entity = repository.save(converter.mapToTable(tarefa));
        return converter.mapToEntity(entity);
    }

    @Override
    public void deletar(Tarefa tarefa) {
        repository.delete(converter.mapToTable(tarefa));
    }

    @Override
    public List<Tarefa> filtarPorIdEStatus(UUID id, Status status) {
        List<TarefaEntity> entity = repository.findByProjetoIdAndStatus(id, status);
        return entity.stream().map(converter::mapToEntity).toList();
    }

    @Override
    public Boolean existeTarefaPorProjetoId(UUID idProjeto){
        return repository.existsByProjetoId(idProjeto);
    }

    private void simulateLatency() {
        System.out.println("Buscando projetos...");
        try {
            Long time = 1000L;
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalArgumentException(e);
        }
    }
}