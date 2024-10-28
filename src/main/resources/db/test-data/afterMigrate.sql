-- Limpar dados das tabelas "tb_tarefa" e "tb_projeto"
TRUNCATE TABLE tb_tarefa CASCADE;
TRUNCATE TABLE tb_projeto CASCADE;

INSERT INTO tb_projeto (id, nome, descricao, data_inicio, data_fim, status, prioridade) VALUES
    ('1d8cb938-ec6b-11ed-affa-0242ac120002', 'Projeto Alpha', 'Descrição do Projeto Alpha', '2024-01-01', '2024-06-01', 'PLANEJAMENTO', 'ALTA'),
    ('2b9cc294-ec6b-11ed-affa-0242ac120002', 'Projeto Beta', 'Descrição do Projeto Beta', '2024-02-01', '2024-07-01', 'EXECUCAO', 'MEDIA'),
    ('3dacb2de-ec6b-11ed-affa-0242ac120002', 'Projeto Gama', 'Descrição do Projeto Gama', '2024-03-01', '2024-08-01', 'FINALIZADO', 'BAIXA');

INSERT INTO tb_tarefa (id, titulo, descricao, prazo, data_inicio, data_fim, status, prioridade, responsavel, projeto_id) VALUES
    ('4eac43f0-ec6b-11ed-affa-0242ac120002', 'Alpha tarefa 1', 'Descrição da Tarefa 1 do Projeto Alpha', '2024-02-15', '2024-01-01', NULL, 'PLANEJAMENTO', 'ALTA', 'Responsável A', '1d8cb938-ec6b-11ed-affa-0242ac120002'),
    ('5fb34320-ec6b-11ed-affa-0242ac120002', 'Alpha tarefa 2', 'Descrição da Tarefa 2 do Projeto Alpha', '2024-03-15', '2024-01-10', NULL, 'EXECUCAO', 'MEDIA', 'Responsável B', '1d8cb938-ec6b-11ed-affa-0242ac120002'),
    ('6abc573e-ec6b-11ed-affa-0242ac120002', 'Alpha tarefa 3', 'Descrição da Tarefa 3 do Projeto Alpha', '2024-04-10', '2024-02-01', NULL, 'FINALIZADO', 'BAIXA', 'Responsável C', '1d8cb938-ec6b-11ed-affa-0242ac120002');

INSERT INTO tb_tarefa (id, titulo, descricao, prazo, data_inicio, data_fim, status, prioridade, responsavel, projeto_id) VALUES
    ('7cde2b74-ec6b-11ed-affa-0242ac120002', 'Beta tarefa 1', 'Descrição da Tarefa 1 do Projeto Beta', '2024-05-01', '2024-02-01', NULL, 'IMPEDIMENTO', 'ALTA', 'Responsável D', '2b9cc294-ec6b-11ed-affa-0242ac120002');