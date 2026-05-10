package org.example;

import dao.IAlunoDAO;
import daoImplements.AlunoDAOImplements;
import database.sqlConn;
import model.Aluno;

import java.sql.SQLOutput;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        sqlConn.testeConnection();
        AlunoDAOImplements  alunoDaoMethods = new AlunoDAOImplements();
        Scanner sc = new Scanner(System.in);

        int opcao;

        do{
            System.out.println("===== MENU =====");
            System.out.println("1, Cadastrar Aluno;");
            System.out.println("2. Atualizar Aluno;");
            System.out.println("3. Excluir Aluno;");
            System.out.println("4. Listar Alunos;");
            System.out.println("5. Listar Aluno por id;");
            System.out.println("0. Sair do a programa.");
            System.out.print("Digite o que deseja fazer: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch(opcao){
                case 1:
                    String novoNome, novoCpf, novoEmail, novoDataNasc,novoTelefone;

                    System.out.println("Cadastro do aluno");
                    System.out.println("Digite o nome: ");
                    novoNome = sc.nextLine();
                     // Para limpar a memória de cache
                    System.out.println("Digite o cpf: ");
                    novoCpf = sc.nextLine();
                    System.out.println("Digite o email: ");
                    novoEmail = sc.nextLine();

                    System.out.println("Digite o data nascimento: ");
                    novoDataNasc = sc.nextLine();
                    DateTimeFormatter formatandoData = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate data = LocalDate.parse(novoDataNasc, formatandoData);

                    System.out.println("Telefone: ");
                    novoTelefone = sc.nextLine();

                    Aluno novoAluno = new Aluno(novoNome, novoCpf, novoEmail, data, novoTelefone);
                    alunoDaoMethods.salvarAluno(novoAluno);
                    break;

                case 2:
                    System.out.println("Atualizar aluno");
                    System.out.println("Digite o ID do aluno que deseja atualizar: ");
                    int idAtualizar = sc.nextInt();
                    sc.nextLine();

                    //Busca o aluno atual no banco
                    Optional<Aluno> alunoParaAtualizar = alunoDaoMethods.buscarPorId(idAtualizar);

                    if (alunoParaAtualizar.isEmpty()){
                        System.out.println("Nenhum aluno encontrado com esse ID.");
                        break;
                    }
                    //Pega o objeto encontrado
                    Aluno alunoAtual = alunoParaAtualizar.get();
                    System.out.println("Aluno encontrado: " + alunoAtual);
                    System.out.println("(Deixe em branco para manter o valor atual)");

                    //Pergunta campo por campo
                    System.out.println("Novo nome [" + alunoAtual.getNome() + "]: ");
                    String nomeAtualizado = sc.nextLine();
                    if (!nomeAtualizado.isBlank()){
                        alunoAtual.setNome(nomeAtualizado);
                    }
                    System.out.println("Novo CPF ["+ alunoAtual.getCpf() +"]: ");
                    String cpfAtualizado = sc.nextLine();
                    if (!cpfAtualizado.isBlank()){
                        alunoAtual.setCpf(cpfAtualizado);
                    }
                    System.out.println("Novo email ["+ alunoAtual.getEmail() +"]:");
                    String emailAtualizado = sc.nextLine();
                    if (!emailAtualizado.isBlank()){
                        alunoAtual.setEmail(emailAtualizado);
                    }
                    System.out.println("Novo telefone ["+ alunoAtual.getTelefone() +"]:");
                    String telefoneAtualizado = sc.nextLine();
                    if(!telefoneAtualizado.isBlank()){
                        alunoAtual.setTelefone(telefoneAtualizado);
                    }

                    //Manda o objeto já modificado para o DAO
                    alunoDaoMethods.atualizarAluno(alunoAtual);

                    break;
                case 3:
                    System.out.println("==== Excluir aluno ====");
                    System.out.print("Digite o ID do aluno que deseja excluir: ");
                    int idDeletar = sc.nextInt();
                    sc.nextLine();

                    Optional<Aluno> alunoParaDeletar = alunoDaoMethods.buscarPorId(idDeletar);

                    if(alunoParaDeletar.isEmpty()){
                        System.out.println("Aluno não encontrado!");
                        break;
                    }
                    Aluno alunoDel = alunoParaDeletar.get();

                    //Mostrando quem vai ser deletado
                    System.out.println("Aluno encontrado: " + alunoDel);
                    System.out.println("Tem certeza que deseja excluir? (s/n): ");
                    String confirmacao = sc.nextLine();

                    if (confirmacao.equalsIgnoreCase("s")){
                        alunoDaoMethods.excluirAluno(alunoDel);
                    }else {
                        System.out.println("Exclusão cancelada.");
                    }



                    break;
                case 4:
                    System.out.println("Listar alunos");

                    List<Aluno> todosAlunos = alunoDaoMethods.listarTodosAlunos();

                    if(todosAlunos.isEmpty()){
                        System.out.println("Nenhum aluno encontrado!");
                    }else{
                        for(Aluno aluno : todosAlunos) {
                            System.out.println(aluno);
                        }
                    }
                    break;

                case 5:
                    System.out.println("Listar Aluno por ID. Informe um ID para pesquisar: ");

                    System.out.print("Digite o ID aluno: ");
                    int idBusca = sc.nextInt();

                    Optional<Aluno> alunoEncontrado = alunoDaoMethods.buscarPorId(idBusca);

                    if (alunoEncontrado.isPresent()){
                        System.out.println(alunoEncontrado); // toString();
                    }else{
                        System.out.println("Nenhum aluno encontrado!");
                    }

                    break;

                case 0:
                    System.out.println("Finalizando Programa...");
                    break;

                default:
                    System.out.println("Favor selecionar uma opção válida!");
                    break;

            }

        } while (opcao != 0);

        //List<Aluno> todosAlunos = alunoDaoMethod.listarTodosAlunos();
//        if (todosAlunos.isEmpty()){
//            System.out.println("Nenhum aluno encontrado");
//        }else{
//            for(Aluno aluno : todosAlunos){
//                System.out.println(aluno);
//            }
//        }

//        for(int i = 0; i < alunodao.listarTodosAlunos().size(); i++){
//            System.out.println(alunodao.listarTodosAlunos().get(i));
//        }


    }

}
