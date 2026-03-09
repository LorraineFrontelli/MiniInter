package servlet.autentificacao;

import dao.AdministradorDAO;
import model.Administrador;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.util.List;

@WebServlet("/login-adm")
public class LoginAdmServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/views/autenticacao/login-administrador.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String login = request.getParameter("login");
        String senha = request.getParameter("senha");

        AdministradorDAO admDAO = new AdministradorDAO();

        try {

            List<Administrador> admins = admDAO.listar();

            for (Administrador admin : admins) {
                if (admin.getLogin().equals(login) &&
                        BCrypt.checkpw(senha, admin.getSenha())) {

                    request.getSession().setAttribute("usuario", admin);
                    request.getSession().setAttribute("tipoUsuario", "admin");

                    response.sendRedirect(request.getContextPath()
                            + "/administradores");
                    return;
                }
            }

            request.setAttribute("mensagem", "Login ou senha inválidos.");
            request.getRequestDispatcher("/WEB-INF/views/autenticacao/login-administrador.jsp")
                    .forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();

            request.setAttribute("mensagem", "Erro ao realizar login.");
            request.getRequestDispatcher("/WEB-INF/views/autenticacao/login-administrador.jsp")
                    .forward(request, response);
        }
    }
}
