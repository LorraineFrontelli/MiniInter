package servlet.AdministradorServlet;

import dao.AdministradorDAO;
import model.Administrador;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/administradores")
public class BuscarAdmServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String mensagem = (String) session.getAttribute("mensagem");

        if (mensagem != null) {
            request.setAttribute("mensagem", mensagem);
            session.removeAttribute("mensagem");
        }

        String login = request.getParameter("filtroLogin");

        AdministradorDAO dao = new AdministradorDAO();
        List<Administrador> administradores = new ArrayList<>();

        try {

            if (login != null && !login.trim().isEmpty()) {

                Administrador admin = dao.buscarPorLogin(login);

                if (admin == null) {
                    request.setAttribute("mensagem", "Administrador não encontrado.");
                } else {
                    administradores.add(admin);
                    request.setAttribute("mensagem", "Administrador encontrado.");
                }

            } else {

                administradores = dao.listar();

                if (administradores == null || administradores.isEmpty()) {
                    request.setAttribute("mensagem", "Nenhum administrador cadastrado.");
                } else {
                    request.setAttribute("mensagem",
                            "Foram encontrados " + administradores.size() + " administradores.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", "Erro ao buscar administradores.");
        }

        request.setAttribute("administradores", administradores);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/view/Administrador/crudAdministrador.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
