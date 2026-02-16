package model;

public class Administrador {

    private int id;
    private String login;
    private String senha;
    private String aluno;

    // Métodos construtores
    public Administrador() {}

    public Administrador(int id, String login, String senha, String aluno) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.aluno = aluno;
    }

    // Métodos getters
    public int getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public String getAluno() {
        return aluno;
    }

    // Métodos setters
    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setAluno(String aluno) {
        this.aluno = aluno;
    }

    // Método toString
    @Override
    public String toString() {
        return "ID: " + id +
                "\tLogin: " + login +
                "\tAluno: " + aluno;
    }
}
