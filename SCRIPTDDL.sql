-- DDL
-- Sistema escolar

CREATE SCHEMA sistema_escolar;
use sistema_escolar;

SELECT @@global.time_zone, @@session.time_zone;
select now();

-- 1. Instituição
create table instituicao (
	idInstituicao int auto_increment primary key,
    nome varchar(200) not null,
    cnpj varchar(18) not null unique,
    cidade varchar(100) not null,
    estado varchar(2) not null,
    email varchar(150) not null,
    telefone varchar(20),
    creaido_em datetime not null default current_timestamp
);

-- 2. Professor
create table professor (
	idProfessor int auto_increment primary key,
    instituicao_id int not null,
    nome varchar (150) not null,
    cpf varchar(14) not null unique,
    email varchar(150) not null unique,
    titulacao enum('GRADUACAO', 'ESPECIALIZACAO', 'MESTRADO', 'DOUTORADO') DEFAULT 'GRADUACAO',
    criado_em DATETIME NOT NULL DEFAULT current_timestamp,
    
    CONSTRAINT fk_prof_int foreign key (instituicao_id)
		REFERENCES instituicao(idInstituicao)
        -- CONSTRAINT = RESTRIÇÃO DE BANCO DE DADOS - UTILIZADO PARA GARANTIR A INTEGRIDADE E CONFIABILIDADE 
        -- DOS DADOS, IMPEDINDO A INSERÇÃO DE INFORMAÇÕES INVÁLIDAS
);

-- 3 aluno, salvando pro commit, parte dois

CREATE TABLE aluno(
	idAluno INT AUTO_INCREMENT PRIMARY KEY,
    nome varchar(150) not null,
    cpf varchar(14) unique,
    email varchar(150) not null unique,
    data_nascimento date not null,
    telefone varchar(20),
    criado_em datetime not null default current_timestamp
);

-- 4. Turma

CREATE TABLE turma(
	idTurma int AUTO_INCREMENT PRIMARY KEY,
    instituicao_id INT NOT NULL,
    professor_id int not null,
    nome varchar(80) not null, -- ex 3a manhã 2025
    ano_letivo YEAR NOT NULL,
    turno enum('MATUTINO', 'VESPERTINO', 'NOTURNO') not null,
    vagas smallint not null default 40,
    criado_em datetime not null default current_timestamp,
    
    CONSTRAINT fk_turma_inst FOREIGN KEY (instituicao_id)
		REFERENCES instituicao(idInstituicao),
	CONSTRAINT fk_turma_prof FOREIGN KEY (professor_id)
		REFERENCES professor(idProfessor)
    
);

-- 5 MATRICULA

CREATE TABLE matricula(
	idMatricula int auto_increment primary key,
    aluno_id int not null,
    turma_id int not null,
    data_matricula date not null default (CURRENT_DATE),
    situacao enum('ATIVA', 'TRANCADA', 'CANCELADA', 'CONCLUIDA') not null default 'ATIVA',
    
    UNIQUE KEY uq_aluno_turma (aluno_id, turma_id),
    
    CONSTRAINT fk_mat_aluno FOREIGN KEY (aluno_id)
		REFERENCES aluno(idAluno),
	CONSTRAINT fk_mat_turma FOREIGN KEY (turma_id)
		REFERENCES turma(idTurma)
);