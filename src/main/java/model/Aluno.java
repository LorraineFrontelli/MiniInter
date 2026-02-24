package model;

import java.util.Date;

public class Aluno {

    // ATRIBUTOS
    private int matricula;
    private String nome;
    private String cpf;
    private Date dataInicio;
    private String email;
    private String senha;

    // CONSTRUTOR VAZIO
    public Aluno() {
    }

    // CONSTRUTOR SEM ID (para novos registros)
    public Aluno(String nome, String cpf, Date dataInicio, String email, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.dataInicio = dataInicio;
        this.email = email;
        this.senha = senha;
    }

    // CONSTRUTOR COMPLETO (quando for carregar do BD)
    public Aluno(int matricula, String nome, String cpf, Date dataInicio, String email, String senha) {
        this.matricula = matricula;
        this.nome = nome;
        this.cpf = cpf;
        this.dataInicio = dataInicio;
        this.email = email;
        this.senha = senha;
    }

    // GETTERS
    public int getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    // SETTERS
    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    // TO STRING
    @Override
    public String toString() {
        return "Aluno: " +
                "matricula=" + matricula +
                ", nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", email='" + email + '\'';
    }
}
