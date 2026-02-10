package model;

import java.time.LocalDate;

public class Professor {

    // ATRIBUTOS
    private int id;
    private String nome;
    private LocalDate dataContratacao;
    private String email;
    private String senha;
    private String materia;
    private String usuario;

    // CONSTRUTOR VAZIO

    public Professor() {
    }

    // CONSTRUTOR SEM ID (para novos registros)

    public Professor(String nome, LocalDate dataContratacao, String email, String senha, String materia, String usuario) {
        this.nome = nome;
        this.dataContratacao = dataContratacao;
        this.email = email;
        this.senha = senha;
        this.materia = materia;
        this.usuario = usuario;
    }

    // CONSTRUTOR COMPLETO (quando for carregar do BD)

    public Professor(int id, String nome, LocalDate dataContratacao, String email, String senha, String materia, String usuario) {
        this.id = id;
        this.nome = nome;
        this.dataContratacao = dataContratacao;
        this.email = email;
        this.senha = senha;
        this.materia = materia;
        this.usuario = usuario;
    }

    // GETTERS

    public int getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public LocalDate getDataContratacao() {
        return this.dataContratacao;
    }

    public String getEmail() {
        return this.email;
    }

    public String getSenha() {
        return this.senha;
    }

    public String getMateria() {
        return this.materia;
    }

    public String getUsuario() {
        return this.usuario;
    }

    // SETTERS

    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDataContratacao(LocalDate dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    // TO STRING

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nome='" + getNome() + "'" +
            ", dataContratacao='" + getDataContratacao() + "'" +
            ", email='" + getEmail() + "'" +
            ", senha='" + getSenha() + "'" +
            ", materia='" + getMateria() + "'" +
            ", usuario='" + getUsuario() + "'" +
            "}";
    }

}
