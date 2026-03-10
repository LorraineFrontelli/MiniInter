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

    // CREATE - inserir administrador
    public int inserir(Administrador admin) {

        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();

        String sql = "INSERT INTO admin (login, senha, aluno_cpf) VALUES (?, ?, ?)";

        int retorno;

        try {

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, admin.getLogin());
            pst.setString(2, admin.getSenha());

            // remover formatação dos cpfs
            List<String> cpfsLimpos = new ArrayList<>();

            for (String cpf : admin.getAlunoCpf()) {
                cpf = cpf.replaceAll("\\D", "");
                cpfsLimpos.add(cpf);
            }

            String cpfs = String.join(",", cpfsLimpos);

            pst.setString(3, cpfs);

            retorno = pst.executeUpdate();

        } catch (SQLException e) {

            e.printStackTrace();
            retorno = -1;

        } finally {

            conexao.desconectar(con);

        }

        return retorno;
    }


    // READ - Buscar por login
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

                String cpfs = rs.getString("aluno_cpf");

                if (cpfs != null && !cpfs.isEmpty()) {

                    String[] lista = cpfs.split(",");

                    for (String cpf : lista) {

                        cpf = cpf.replaceAll("\\D", "");

                        admin.adicionarCpf(cpf);
                    }

                }
            }

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            conexao.desconectar(con);

        }

        return admin;
    }


    // Verificar cpf
    public boolean cpfExiste(String cpf) {

        cpf = cpf.replaceAll("\\D", "");

        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();

        boolean existe = false;

        String sql = "SELECT 1 FROM admin WHERE CONCAT(',', aluno_cpf, ',') LIKE ?";

        try {

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, "%," + cpf + ",%");

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                existe = true;

            }

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            conexao.desconectar(con);

        }

        return existe;
    }


    // READ - Listar todos
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

                String cpfs = rs.getString("aluno_cpf");

                if (cpfs != null && !cpfs.isEmpty()) {

                    String[] lista = cpfs.split(",");

                    for (String cpf : lista) {

                        cpf = cpf.replaceAll("\\D", "");

                        admin.adicionarCpf(cpf);
                    }

                }

                admins.add(admin);

            }

        } catch (SQLException e) {

            e.printStackTrace();

        } finally {

            conexao.desconectar(con);

        }

        return admins;
    }


    // UPDATE
    public int atualizar(Administrador admin) {

        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();

        String sql = "UPDATE admin SET login=?, senha=?, aluno_cpf=? WHERE id=?";

        int retorno;

        try {

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, admin.getLogin());
            pst.setString(2, admin.getSenha());

            List<String> cpfsLimpos = new ArrayList<>();

            for (String cpf : admin.getAlunoCpf()) {

                cpf = cpf.replaceAll("\\D", "");
                cpfsLimpos.add(cpf);

            }

            String cpfs = String.join(",", cpfsLimpos);

            pst.setString(3, cpfs);

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


    // DELETE
    public int deletar(int id) {

        Conexao conexao = new Conexao();
        Connection con = conexao.conectar();

        String sql = "DELETE FROM admin WHERE id=?";

        int retorno;

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
