-- DML

INSERT INTO instituicao (nome, cnpj, cidade, estado, email, telefone) VALUES
    ('Escola Estadual João Pessoa', '11.222.333/0001-44', 'São Paulo',  'SP', 'contato@eejp.edu.br',      '(11) 3333-4444'),
    ('Faculdade Inova Tech',        '55.666.777/0001-88', 'Campinas',   'SP', 'secretaria@inovatech.edu.br','(19) 9999-8888');
 
INSERT INTO professor (instituicao_id, nome, cpf, email, titulacao) VALUES
    (1, 'Ana Paula Ferreira',   '123.456.789-00', 'ana.ferreira@eejp.edu.br',      'MESTRADO'),
    (2, 'Carlos Eduardo Lima',  '987.654.321-00', 'carlos.lima@inovatech.edu.br',   'DOUTORADO'),
    (1, 'Fernanda Souza',       '111.222.333-44', 'fernanda.souza@eejp.edu.br',     'ESPECIALIZACAO');
 
INSERT INTO aluno (nome, cpf, email, data_nascimento, telefone) VALUES
    ('Lucas Rodrigues Santos',   '222.333.444-55', 'lucas.santos@email.com',    '2006-03-15', '(11) 91111-2222'),
    ('Mariana Costa Oliveira',   '333.444.555-66', 'mariana.oliveira@email.com','2007-07-22', '(11) 93333-4444'),
    ('Pedro Henrique Alves',     '444.555.666-77', 'pedro.alves@email.com',     '2005-11-01', '(19) 95555-6666'),
    ('Juliana Martins Pereira',  '555.666.777-88', 'juliana.martins@email.com', '2006-09-10', '(11) 97777-8888');
 
INSERT INTO turma (instituicao_id, professor_id, nome, ano_letivo, turno, vagas) VALUES
    (1, 1, '3º A Manhã',     2025, 'MATUTINO',   35),
    (1, 3, '2º B Tarde',     2025, 'VESPERTINO', 30),
    (2, 2, 'SI Noturno T01', 2025, 'NOTURNO',    40);
 
INSERT INTO matricula (aluno_id, turma_id, situacao) VALUES
    (1, 1, 'ATIVA'),
    (2, 1, 'ATIVA'),
    (3, 2, 'ATIVA'),
    (4, 3, 'ATIVA'),
    (1, 3, 'ATIVA');
    
