package dao;

import model.Turma;
import model.Aluno;

import java.util.List;

public interface ITurmaDAO {
    List<Turma> listarTodasTurmas();

    List<Aluno> listarAlunosPorTurma(int idTurma); // Esse idTurma é só uma variável qualquer ela não possui relação.

}
