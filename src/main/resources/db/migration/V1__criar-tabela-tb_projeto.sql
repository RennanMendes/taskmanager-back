CREATE TABLE tb_projeto(
     id UUID PRIMARY KEY,
     nome VARCHAR,
     descricao VARCHAR,
     data_inicio DATE,
     data_fim DATE,
     status VARCHAR(12),
     prioridade VARCHAR(5)
);

CREATE INDEX idx_projeto_nome_status ON tb_projeto (nome, status);