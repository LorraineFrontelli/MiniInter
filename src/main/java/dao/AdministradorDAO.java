package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import conexao.Conexao;
import model.Administrador;

public class AdministradorDAO {

    // CREATE - inserir Administrador
    public int inserir(Administrador admin) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        String sql = "INSERT INTO admin (login, senha, aluno) VALUES (?, ?, ?)";
        int retorno;

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, admin.getLogin());
            pst.setString(2, admin.getSenha());
            pst.setString(3, admin.getAluno());

            retorno = pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            retorno = -1;
        } finally {
            conexao.desconectar(con);
        }

        return retorno;
    }

    // READ - buscar administrador
    public Administrador buscarPorLogin(String login) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        Administrador admin = null;

        String sql = "SELECT * FROM admin WHERE login=?";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, login);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                admin = new Administrador(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("senha"),
                        rs.getString("aluno")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.desconectar(con);
        }

        return admin;
    }

    // READ - listar todos os administradores
    public List<Administrador> listar() {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        List<Administrador> lista = new ArrayList<>();

        String sql = "SELECT * FROM admin";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                lista.add(new Administrador(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("senha"),
                        rs.getString("aluno")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            conexao.desconectar(con);
        }

        return lista;
    }

    // UPDATE - atualizar administrador
    public int atualizar(Administrador admin) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        int retorno;

        String sql = "UPDATE admin SET login=?, senha=?, aluno=? WHERE id=?";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, admin.getLogin());
            pst.setString(2, admin.getSenha());
            pst.setString(3, admin.getAluno());
            pst.setInt(4, admin.getId());

            retorno = pst.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            retorno = -1;
        } finally {
            conexao.desconectar(con);
        }

        return retorno;
    }

    // DELETE - deletar administrador 
    public int deletar(int id) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        int retorno;

        String sql = "DELETE FROM admin WHERE id=?";

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
