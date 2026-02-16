package model;

import java.util.Date;

public class Aluno {
    private int matricula;
    private String nome;
    private String cpf;
    private Date dataInicio;
    private String email;
    private String senha;

    // Métodos contrutores
    public Aluno() {}

    public Aluno(int matricula, String nome, String cpf, Date dataInicio, String email, String senha) {
        this.matricula = matricula;
        this.nome = nome;
        this.cpf = cpf;
        this.dataInicio = dataInicio;
        this.email = email;
        this.senha = senha;
    }

    // Métodos getters
    public int getMatricula() {
        return matricula;
    }
    public String getNome() {
        return  nome;
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

    // Métodos setters
    public void setNome(String nome) {
        this.nome = nome;
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

    // Método toString
    public String toString() {
        return "Matrícula: " + matricula + "\tNome: " + nome + "\tCPF: " + cpf + "\tData de ínicio: " + dataInicio + "\tEmail: " + email + "\tSenha: " + senha;
    }
}
