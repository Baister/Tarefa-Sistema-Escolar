package dao;

import model.Aluno;

import java.util.List;

public interface IAlunoDAO {
    //CRUD
    // create
    void salvarAluno(Aluno aluno);

    //Read
    List<Aluno> listarTodosAlunos();

    //Update
    void atualizarAluno(Aluno aluno);

    //Delete
    void excluirAluno(Aluno aluno);
}
