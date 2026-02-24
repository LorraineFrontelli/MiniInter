package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import conexao.Conexao;
import model.AlunoProfessor;

public class AlunoProfessorDAO {

    // CREATE - inserir AlunoProfessor
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

        } catch (SQLException e) {
            e.printStackTrace();
            retorno = -1;
        } finally {
            conexao.desconectar(con);
        }

        return retorno;
    }

    // READ - Listar todos
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

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.desconectar(con);
        }

        return lista;
    }

    // UPDATE - atualizar AlunoProfessor
    public int atualizar(AlunoProfessor ap) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        int retorno;

        String sql = "UPDATE aluno_professor SET id_professor=?, id_aluno=?, serie=?, turma=? WHERE id=?";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, ap.getIdProfessor());
            pst.setInt(2, ap.getIdAluno());
            pst.setInt(3, ap.getSerie());
            pst.setString(4, ap.getTurma());
            pst.setInt(5, ap.getId());

            retorno = pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            retorno = -1;
        } finally {
            conexao.desconectar(con);
        }

        return retorno;
    }

    // DELETE - deletar AlunoProfessor
    public int deletar(int id) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        int retorno;

        String sql = "DELETE FROM aluno_professor WHERE id=?";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);

            retorno = pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            retorno = -1;
        } finally {
            conexao.desconectar(con);
        }

        return retorno;
    }
}
