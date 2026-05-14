package daoImplements;

import dao.ITurmaDAO;
import database.sqlConn;
import model.Aluno;
import model.Turma;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.SQLException;

public class TurmaDAOImplements implements ITurmaDAO {
    @Override
    public List<Turma> listarTodasTurmas() {
        String sql = "SELECT * FROM turma ORDER BY turno DESC, nome ASC";

        List<Turma> turmasEncontradas = new ArrayList<>();

        try(Connection conn = sqlConn.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                turmasEncontradas.add(new Turma(
                    rs.getInt("idTurma"),
                    rs.getInt("instituicao_id"),
                    rs.getInt("professor_id"),
                    rs.getString("nome"),
                    rs.getInt("ano_letivo"),
                    rs.getString("turno"),
                    rs.getInt("vagas")
                ));
            }
        }catch (SQLException ex ){
            System.err.println("Erro ao lsitar todas as turmas: " + ex.getMessage());
        }
        return turmasEncontradas;
    }

    @Override
    public List<Aluno> listarAlunosPorTurma(int turmaId) {
        String sql = "SELECT a.* FROM matricula m INNER JOIN aluno a ON m.aluno_id = a.idAluno WHERE m.turma_id = ? ORDER BY a.nome ASC";

        List<Aluno> alunosEncontrados = new ArrayList<>();

        try (Connection conn = sqlConn.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, turmaId);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                alunosEncontrados.add(new Aluno(
                   rs.getInt("idAluno"),
                   rs.getString("nome"),
                   rs.getString("cpf"),
                   rs.getString("email"),
                   rs.getDate("data_nascimento").toLocalDate(),
                   rs.getString("telefone")
                ));
            }

        }catch (SQLException ex){
            System.err.println("Erro ao listar todos os alunos: " + ex.getMessage());
        }
        return alunosEncontrados;
    }
}
