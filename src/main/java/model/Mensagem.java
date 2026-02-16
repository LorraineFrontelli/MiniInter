package model;


import java.sql.Timestamp;

public class Mensagem{

    // ATRIBUTOS
    private int id;
    private int idProfessor;
    private int idAluno;
    private String mensagem;
    private Timestamp dataMensagem;

    // CONSTRUTOR VAZIO

    public Mensagem() {
    }

    // CONSTRUTOR SEM ID (para novos registros)

    public Mensagem(int idProfessor, int idAluno, String mensagem,  Timestamp dataMensagem) {
        this.idProfessor = idProfessor;
        this.idAluno = idAluno;
        this.mensagem = mensagem;
        this.dataMensagem =  dataMensagem;
    }

    // CONSTRUTOR COMPLETO (quando for carregar do BD)

    public Mensagem(int id, int idProfessor, int idAluno, String mensagem,  Timestamp dataMensagem) {
        this.id = id;
        this.idProfessor = idProfessor;
        this.idAluno = idAluno;
        this.mensagem = mensagem;
        this.dataMensagem = dataMensagem;
    }

    // GETTERS

    public int getId() {
        return this.id;
    }


    public int getIdProfessor() {
        return this.idProfessor;
    }

 
    public int getIdAluno() {
        return this.idAluno;
    }

 
    public String getMensagem() {
        return this.mensagem;
    }

    public Timestamp getDataMensagem() {
        return this.dataMensagem;
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

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public void setDataMensagem(Timestamp dataMensagem) {
        this.dataMensagem = dataMensagem;
    }

    // TO STRING

    @Override
    public String toString() {
        return "Mensagem{" +
                "id=" + id +
                ", idProfessor=" + idProfessor +
                ", idAluno=" + idAluno +
                ", mensagem='" + mensagem + '\'' +
                ", dataMensagem=" + dataMensagem +
                '}';
    }
}