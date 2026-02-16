package model;

public class AlunoProfessor {

    private int id;
    private int idProfessor;
    private int idAluno;
    private int serie;
    private String turma;

    // Métodos construtores
    public AlunoProfessor() {}

    public AlunoProfessor(int id, int idProfessor, int idAluno, int serie, String turma) {
        this.id = id;
        this.idProfessor = idProfessor;
        this.idAluno = idAluno;
        this.serie = serie;
        this.turma = turma;
    }

    // Métodos getters
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

    // Métodos setters
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

    // Método toString
    @Override
    public String toString() {
        return "ID: " + id +
                "\tProfessor: " + idProfessor +
                "\tAluno: " + idAluno +
                "\tSérie: " + serie +
                "\tTurma: " + turma;
    }
}
