package org.example;

import dao.IAlunoDAO;
import daoImplements.AlunoDAOImplements;
import database.sqlConn;
import model.Aluno;

import java.sql.SQLOutput;
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

            opcao = sc.nextInt();
            sc.nextLine();

            switch(opcao){
                case 1:
                    String novoNome, novoCpf, novoEmail,telefone;

                    System.out.println("Cadastro do aluno");
                    System.out.println("Digite o nome: ");
                    novoNome = sc.nextLine();
                    sc.nextLine(); // Para limpar a memória de cache
                    System.out.println("Digite o cpf: ");
                    novoCpf = sc.nextLine();
                    sc.nextLine();
                    System.out.println("Digite o email: ");
                    novoEmail = sc.nextLine();
                    sc.nextLine();
                    System.out.println("Digite o data nascimento: ");

                    System.out.println("Telefone: ");

                    break;
                case 2:
                    System.out.println("Atualizar aluno");
                    break;
                case 3:
                    System.out.println("Excluir aluno");
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
