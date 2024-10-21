CREATE TABLE tb_tarefa (
    id UUID PRIMARY KEY,
    titulo VARCHAR,
    descricao TEXT,
    prazo DATE,
    data_inicio DATE,
    data_fim DATE,
    status VARCHAR(12),
    prioridade VARCHAR(5),
    responsavel VARCHAR,
    projeto_id UUID,

    CONSTRAINT fk_projeto FOREIGN KEY (projeto_id) REFERENCES tb_projeto(id) ON DELETE CASCADE
);

CREATE INDEX idx_tarefa_projeto_id ON tb_tarefa(projeto_id);

