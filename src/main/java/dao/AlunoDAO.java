package dao;

// imports da classe
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import conexao.Conexao;
import model.Aluno;

public class AlunoDAO {

    // CREATE - inserir aluno
    public int inserir(Aluno aluno) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        String sql = "INSERT INTO aluno (matricula, nome, cpf, dt_inicio, email, senha) VALUES (?, ?, ?, ?, ?, ?)";
        int retorno;

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, aluno.getMatricula());
            pst.setString(2, aluno.getNome());
            pst.setString(3, aluno.getCpf());
            pst.setDate(4, new java.sql.Date(aluno.getDataInicio().getTime()));
            pst.setString(5, aluno.getEmail());
            pst.setString(6, aluno.getSenha());

            retorno = pst.executeUpdate();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            retorno = -1;
        } finally {
            conexao.desconectar(con);
        }

        return retorno;
    }

    // READ - buscar aluno pela matricula
    public Aluno buscarPorMatricula(int matricula) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        Aluno aluno = null;
        String sql = "SELECT * FROM aluno WHERE matricula = ?";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, matricula);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                aluno = new Aluno(
                        rs.getInt("matricula"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getDate("dt_inicio"),
                        rs.getString("email"),
                        rs.getString("senha")
                );
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            conexao.desconectar(con);
        }

        return aluno;
    }

    // READ - listar todos os alunos
    public List<Aluno> listar() {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM aluno";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                alunos.add(new Aluno(
                        rs.getInt("matricula"),
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getDate("dt_inicio"),
                        rs.getString("email"),
                        rs.getString("senha")
                ));
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            conexao.desconectar(con);
        }

        return alunos;
    }

    // UPDATE - atualizar aluno
    public int atualizar(Aluno aluno) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        int retorno;
        String sql = "UPDATE aluno SET nome=?, cpf=?, dt_inicio=?, email=?, senha=? WHERE matricula=?";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, aluno.getNome());
            pst.setString(2, aluno.getCpf());
            pst.setDate(3, new java.sql.Date(aluno.getDataInicio().getTime()));
            pst.setString(4, aluno.getEmail());
            pst.setString(5, aluno.getSenha());
            pst.setInt(6, aluno.getMatricula());

            retorno = pst.executeUpdate();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            retorno = -1;
        } finally {
            conexao.desconectar(con);
        }

        return retorno;
    }

    // DELETE - deletar aluno
    public int deletar(int matricula) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        int retorno;
        String sql = "DELETE FROM aluno WHERE matricula = ?";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, matricula);

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
