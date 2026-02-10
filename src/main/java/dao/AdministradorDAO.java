package dao;

// imports da classe
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import conexao.Conexao;
import model.Administrador;

public class AdministradorDAO {

    // CREATE - inserir administrador
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

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            retorno = -1;
        } finally {
            conexao.desconectar(con);
        }

        return retorno;
    }

    // READ - buscar administrador por login
    public Administrador buscarPorLogin(String login) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        Administrador admin = null;
        String sql = "SELECT * FROM admin WHERE login = ?";

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

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            conexao.desconectar(con);
        }

        return admin;
    }
}
