package daoImplements;

import dao.IAlunoDAO;
import database.sqlConn;
import model.Aluno;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AlunoDAOImplements implements IAlunoDAO {
    @Override
    public void salvarAluno(Aluno aluno) {
        String sql = "INSERT INTO aluno(nome, cpf, email, data_nascimento, telefone) VALUES(?,?,?,?,?)";
        try (Connection conn = sqlConn.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            stmt.setString(3, aluno.getEmail());
            stmt.setDate(4, Date.valueOf(aluno.getData_Nascimento()));
            stmt.setString(5, aluno.getTelefone());
            stmt.executeUpdate();
            System.out.println("Criado com sucesso!");


        }catch (SQLException e){
            System.err.print("Erro aolistar os alunos: " + e.getMessage());
        }
    }

    @Override
    public List<Aluno> listarTodosAlunos() {
        String sql = "SELECT * FROM aluno ORDER BY nome ASC";
        List<Aluno> alunos = new ArrayList<>();

        try (Connection conn = sqlConn.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()){
                alunos.add(new Aluno(
                        rs.getInt("idAluno"),//Representa uma coluna da tabela de aluno, a ID
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("email"),
                        rs.getDate("data_nascimento").toLocalDate(),
                        rs.getString("telefone")
                ));
            }

        }catch (SQLException e){
            System.err.print("Erro ao listar os alunos: " + e.getMessage());
        }
        return alunos;
    }

    @Override
    public Optional<Aluno> atualizarAluno(int id) {
        String sql = "UPDATE aluno SET nome=?,cpf=?,email=? WHERE idAluno = ?";

        try(Connection conn = sqlConn.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(4, id);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()) {
                Aluno aluno = new Aluno (
                        rs.getInt("idAluno"),//Representa uma coluna da tabela de aluno, a ID
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getString("email"),
                        rs.getDate("data_nascimento").toLocalDate(),
                        rs.getString("telefone")
                );
                return Optional.of(aluno);
            }

        }catch (SQLException e){
            System.err.println("Erro ao buscar aluno " + e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public void excluirAluno(Aluno aluno) {

    }
    @Override
    public Optional<Aluno> buscarPorId(int id){
        String sql = "SELECT * FROM aluno WHERE idAluno = ?";

        try(Connection conn = sqlConn.getConnection()){
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

        if(rs.next()) {
            Aluno aluno = new Aluno (
                    rs.getInt("idAluno"),//Representa uma coluna da tabela de aluno, a ID
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("email"),
                    rs.getDate("data_nascimento").toLocalDate(),
                    rs.getString("telefone")
            );
            return Optional.of(aluno);
        }

        }catch (SQLException e){
            System.err.println("Erro ao buscar aluno " + e.getMessage());
        }
        return Optional.empty();
    }

//    @Override
//    public Aluno buscarAluno(int id){
//        String sql = "SELECT * FROM aluno WHERE id = ?";
//        try (Connection conn = sqlConn.getConnection()){
//            PreparedStatement stmt = conn.prepareStatement(sql);
//
//            stmt.setInt(1,id);
//            ResultSet rs = stmt.executeQuery();
//
//            if(rs.next()){
//                Aluno aluno = new Aluno(rs.getInt("id"),//Representa uma coluna da tabela de aluno, a ID
//                        rs.getString("nome"),
//                        rs.getString("cpf"),
//                        rs.getString("email"),
//                        rs.getDate("data_nascimento").toLocalDate(),
//                        rs.getString("telefone"));
//                return aluno;
//            }
//
//        }catch (SQLException e){
//            System.err.print("Erro ao listar os alunos: " + e.getMessage());
//        }
//        return null;
//    }
}
