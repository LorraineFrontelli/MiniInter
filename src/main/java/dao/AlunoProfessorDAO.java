package dao;

// imports da classe
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import conexao.Conexao;
import model.AlunoProfessor;

public class AlunoProfessorDAO {

    // CREATE - inserir relacao aluno professor
    public int inserir(AlunoProfessor ap) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        String sql = "INSERT INTO aluno_professor (id_professor, id_aluno, serie, turma) VALUES (?, ?, ?, ?)";
        int retorno;

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, ap.getIdProfessor());
            pst.setInt(2, ap.getIdAluno());
            pst.setInt(3, ap.getSerie());
            pst.setString(4, ap.getTurma());

            retorno = pst.executeUpdate();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            retorno = -1;
        } finally {
            conexao.desconectar(con);
        }

        return retorno;
    }

    // READ - listar relacoes
    public List<AlunoProfessor> listar() {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        List<AlunoProfessor> lista = new ArrayList<>();
        String sql = "SELECT * FROM aluno_professor";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                lista.add(new AlunoProfessor(
                        rs.getInt("id"),
                        rs.getInt("id_professor"),
                        rs.getInt("id_aluno"),
                        rs.getInt("serie"),
                        rs.getString("turma")
                ));
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            conexao.desconectar(con);
        }

        return lista;
    }

    // DELETE - deletar relacao
    public int deletar(int id) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        int retorno;
        String sql = "DELETE FROM aluno_professor WHERE id = ?";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);

            retorno = pst.executeUpdate();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            retorno = -1;
        } finally {
            conexao.desconectar(con);
        }

        return retorno;
    }
}
