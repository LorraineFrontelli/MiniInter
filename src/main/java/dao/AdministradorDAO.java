package dao;

// imports da classe
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import conexao.Conexao;
import model.Administrador;

public class AdministradorDAO {

    // CREATE - inserir administrador
    public int inserir(Administrador admin) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        String sql = "INSERT INTO admin (login, senha, alunoCpf) VALUES (?, ?, ?)";
        int retorno;

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, admin.getLogin());
            pst.setString(2, admin.getSenha());

            String cpfs = String.join(",", admin.getAlunoCpf());
            pst.setString(3, cpfs);

            retorno = pst.executeUpdate();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
            retorno = -1;
        } finally {
            conexao.desconectar(con);
        }

        return retorno;
    }

    // READ - buscar administrador pelo login
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
                        rs.getString("senha")
                );

                String cpfs = rs.getString("alunoCpf");

                if (cpfs != null && !cpfs.isEmpty()) {

                    String[] lista = cpfs.split(",");

                    for (String cpf : lista) {
                        admin.adicionarCpf(cpf);
                    }
                }
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            conexao.desconectar(con);
        }

        return admin;
    }

    // READ - verificar se CPF está autorizado para cadastro
    public boolean cpfExiste(String cpf) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        boolean existe = false;

        String sql = "SELECT 1 FROM admin WHERE CONCAT(',', alunoCpf, ',') LIKE ?";

        try {

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, "%," + cpf + ",%");

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                existe = true;
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            conexao.desconectar(con);
        }

        return existe;
    }

    // READ - listar todos os administradores
    public List<Administrador> listar() {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        List<Administrador> admins = new ArrayList<>();
        String sql = "SELECT * FROM admin";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                Administrador admin = new Administrador(
                        rs.getInt("id"),
                        rs.getString("login"),
                        rs.getString("senha")
                );

                String cpfs = rs.getString("alunoCpf");

                if (cpfs != null && !cpfs.isEmpty()) {

                    String[] lista = cpfs.split(",");

                    for (String cpf : lista) {
                        admin.adicionarCpf(cpf);
                    }
                }

                admins.add(admin);
            }

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            conexao.desconectar(con);
        }

        return admins;
    }

    // UPDATE - atualizar administrador
    public int atualizar(Administrador admin) {
        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();
        int retorno;
        String sql = "UPDATE admin SET login=?, senha=?, alunoCpf=? WHERE id=?";

        try {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, admin.getLogin());
            pst.setString(2, admin.getSenha());

            String cpfs = String.join(",", admin.getAlunoCpf());
            pst.setString(3, cpfs);

            pst.setInt(4, admin.getId());

            retorno = pst.executeUpdate();

        } catch (SQLException sqle) {
            sqle.printStackTrace();
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
        String sql = "DELETE FROM admin WHERE id = ?";

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
