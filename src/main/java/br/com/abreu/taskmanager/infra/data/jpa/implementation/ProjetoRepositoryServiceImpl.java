package br.com.abreu.taskmanager.infra.data.jpa.implementation;

import br.com.abreu.taskmanager.adapters.ProjetoRepositoryService;
import br.com.abreu.taskmanager.core.entities.Projeto;
import br.com.abreu.taskmanager.infra.data.jpa.converters.ProjetoRepositoryConverter;
import br.com.abreu.taskmanager.infra.data.jpa.entity.ProjetoEntity;
import br.com.abreu.taskmanager.infra.data.jpa.repository.ProjetoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProjetoRepositoryServiceImpl implements ProjetoRepositoryService {

    private final ProjetoRepository repository;
    private final ProjetoRepositoryConverter converter;

    @Autowired
    public ProjetoRepositoryServiceImpl(ProjetoRepository repository, ProjetoRepositoryConverter converter) {
        this.repository = repository;
        this.converter = converter;
    }

    @Override
    public Optional<Projeto> buscarPorId(UUID id) {
        simulateLatency();
        Optional<ProjetoEntity> entity = repository.findById(id);
        return entity.map(converter::mapToEntity);
    }

    @Override
    public List<Projeto> buscarPorNome(String nome) {
        simulateLatency();
        List<ProjetoEntity> entity = repository.findByNomeContainingIgnoreCase(nome);
        return entity.stream().map(converter::mapToEntity).toList();
    }

    @Override
    public List<Projeto> buscarTodos() {
        List<ProjetoEntity> entity = repository.findAll();
        return entity.stream().map(converter::mapToEntity).toList();
    }

    @Override
    public Projeto salvar(Projeto projeto) {
        ProjetoEntity entity = repository.save(converter.mapToTable(projeto));
        return converter.mapToEntity(entity);
    }

    @Override
    public void deletar(Projeto projeto) {
        repository.delete(converter.mapToTable(projeto));
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