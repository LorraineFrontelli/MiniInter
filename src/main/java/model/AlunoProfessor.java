package model;

public class AlunoProfessor {

    // ATRIBUTOS
    private int id;
    private int idProfessor;
    private int idAluno;
    private int serie;
    private String turma;

    // CONSTRUTOR VAZIO
    public AlunoProfessor() {
    }

    // CONSTRUTOR SEM ID (para novos registros)
    public AlunoProfessor(int idProfessor, int idAluno, int serie, String turma) {
        this.idProfessor = idProfessor;
        this.idAluno = idAluno;
        this.serie = serie;
        this.turma = turma;
    }

    // CONSTRUTOR COMPLETO (quando for carregar do BD)
    public AlunoProfessor(int id, int idProfessor, int idAluno, int serie, String turma) {
        this.id = id;
        this.idProfessor = idProfessor;
        this.idAluno = idAluno;
        this.serie = serie;
        this.turma = turma;
    }

    // GETTERS
    public int getId() {
        return id;
    }

    public int getIdProfessor() {
        return idProfessor;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public int getSerie() {
        return serie;
    }

    public String getTurma() {
        return turma;
    }

    // SETTERS
    public void setId(int id) {
        this.id = id;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public void setSerie(int serie) {
        this.serie = serie;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    // TO STRING
    @Override
    public String toString() {
        return "AlunoProfessor: " +
                "id=" + id +
                ", idProfessor=" + idProfessor +
                ", idAluno=" + idAluno +
                ", serie=" + serie +
                ", turma='" + turma + '\'';
    }
}
