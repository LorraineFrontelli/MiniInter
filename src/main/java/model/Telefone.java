package model;


public class Telefone{

    // ATRIBUTOS
    private int id;
    private int idAluno;
    private int numero;
    private String tipo;

    // CONSTRUTOR VAZIO

    public Telefone() {
    }

    // CONSTRUTOR SEM ID (para novos registros)

    public Telefone(int idAluno, int numero, String tipo) {
        this.idAluno = idAluno;
        this.numero = numero;
        this.tipo = tipo;
    }

    // CONSTRUTOR COMPLETO (quando for carregar do BD)

    public Telefone(int id, int idAluno, int numero, String tipo) {
        this.id = id;
        this.idAluno = idAluno;
        this.numero = numero;
        this.tipo = tipo;
    }

    // GETTERS

    public int getId() {
        return id;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public int getNumero() {
        return numero;
    }

    public String getTipo() {
        return tipo;
    }

    // SETTERS

    public void setId(int id) {
        this.id = id;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    // TO STRING

    @Override
    public String toString() {
        return "Professor{" +
                "id=" + id +
                ", idAluno=" + idAluno +
                ", numero=" + numero +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}