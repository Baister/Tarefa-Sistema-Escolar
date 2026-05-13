SELECT * FROM ALUNO;

ALTER TABLE matricula DROP FOREIGN KEY fk_mat_aluno;

ALTER TABLE matricula
ADD CONSTRAINT fk_mat_aluno
foreign key (aluno_id)
references aluno(idAluno)
on delete cascade;

ALTER TABLE matricula DROP FOREIGN KEY fk_mat_turma;

ALTER TABLE matricula
ADD CONSTRAINT fk_mat_turma
foreign key (turma_id)
references turma(idTurma)
on delete cascade;

