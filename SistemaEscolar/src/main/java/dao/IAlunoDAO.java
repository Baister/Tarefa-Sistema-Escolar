package dao;

import model.Aluno;

import java.util.List;
import java.util.Optional;

public interface IAlunoDAO {
    //CRUD
    // create
    void salvarAluno(Aluno aluno);

    //Read
    List<Aluno> listarTodosAlunos();

    //Update
    Optional<Aluno> atualizarAluno(int id);

    //Delete
    void excluirAluno(Aluno aluno);

    //BuscarAluno getById
    Optional<Aluno> buscarPorId(int id);
}
