package model;

import java.time.LocalDate;

public class Aluno {
    private int id;
    private String nome;
    private String cpf;
    private String email;
    private LocalDate data_Nascimento;
    private String telefone;

    //Construtor para Criar um novo aluno.
    public Aluno(String nome, String cpf, String email, LocalDate data_Nascimento, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.data_Nascimento = data_Nascimento;
        this.telefone = telefone;
    }

    //Construtor para Ler um aluno.
    public Aluno(int id, String nome, String cpf, String email, LocalDate data_Nascimento, String telefone) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.data_Nascimento = data_Nascimento;
        this.telefone = telefone;
    }

    //getters
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getData_Nascimento() {
        return data_Nascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    //setters
    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setData_Nascimento(LocalDate data_Nascimento) {
        this.data_Nascimento = data_Nascimento;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return String.format(
                "Aluno: id=%d - nome=%s - cpf%s - email=%s - nascimento=%s - telefone=%s",
                id, nome, cpf, email, data_Nascimento, telefone
        );
    }
}