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
        try (Connection conn = sqlConn.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)){
           // int rs = stmt.executeUpdate(); // ERRO: executou ANTES DE SETAR os parâmetros e os ? ainda estão vazios aqui


            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            stmt.setString(3, aluno.getEmail());
            stmt.setDate(4, Date.valueOf(aluno.getData_Nascimento()));
            stmt.setString(5, aluno.getTelefone());
            //stmt.executeUpdate();// Executa de novo, tá duplicado
            int linhasAfetadas = stmt.executeUpdate(); //Esse é o único execute que deve ocorrer

            if (linhasAfetadas > 0){
                System.out.println("Aluno criado com sucesso!");
            }


        }catch (SQLException e){
            System.err.print("Erro ao criar o alunos: " + e.getMessage());
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
    public void atualizarAluno(Aluno aluno){
        String sql = "UPDATE aluno SET nome=?, cpf=?, email=?, telefone=? WHERE idAluno=?";

        try (Connection conn = sqlConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getCpf());
            stmt.setString(3, aluno.getEmail());
            stmt.setString(4, aluno.getTelefone());
            stmt.setInt(5, aluno.getId()); //Id do aluno a atualizar

            int linhasAfetadas = stmt.executeUpdate(); // Update usa ExecuteUpdate

            if (linhasAfetadas > 0){
                System.out.println("Aluno atualizado!");
            }else{
                System.out.println("Nenhum aluno encontrado com esse ID.");
            }

        }catch(SQLException e){
            System.err.println("Erro ao atualizar aluno: " + e.getMessage());
        }
    }

    @Override
    public void excluirAluno(Aluno aluno) {
        String sql = "DELETE FROM aluno WHERE idAluno=?";
        try (Connection conn = sqlConn.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, aluno.getId());
            stmt.executeUpdate();
            System.out.println("Aluno excluído com sucesso!");

        }catch (SQLException e ){
            System.err.println("Erro ao excluir aluno: " + e.getMessage());
        }
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
