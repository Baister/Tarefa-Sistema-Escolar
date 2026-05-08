package org.example;

import dao.IAlunoDAO;
import daoImplements.AlunoDAOImplements;
import database.sqlConn;
import model.Aluno;

import java.sql.SQLOutput;
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
            System.out.println("5. Buscar Aluno;");
            System.out.println("0. Sair do a programa.");

            opcao = sc.nextInt();
            sc.nextLine();

            switch(opcao){
                case 1:
                    System.out.println("Cadastro do aluno");
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
                    System.out.println("Buscar Aluno");
                    System.out.print("Digite o ID aluno: ");
                    int idBuscado = sc.nextInt();

                    List<Aluno> todosAlunosDois = alunoDaoMethods.listarTodosAlunos();
                    if(todosAlunosDois.isEmpty()){
                        System.out.println("Nenhum aluno encontrado!");
                    }else{
                        for(Aluno aluno : todosAlunosDois) {
                            if(aluno.getId() == idBuscado){
                                Aluno alunoBuscado = aluno;
                            }else{
                                System.out.println("Aluno não encontrado!");
                            }
                        }
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
