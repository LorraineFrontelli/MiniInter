package servlet.AdministradorServlet;

import dao.AdministradorDAO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/admin-delete")
public class DeletarAdmServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect(request.getContextPath() + "/admins");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParametro = request.getParameter("id");
        AdministradorDAO dao = new AdministradorDAO();
        String mensagem;

        try {

            if (idParametro == null || idParametro.isEmpty()) {
                mensagem = "ID do administrador não foi encontrado.";
            } else {

                int id = Integer.parseInt(idParametro);

                if (dao.deletar(id) > 0) {
                    mensagem = "Administrador deletado com sucesso!";
                } else {
                    mensagem = "Não foi possível deletar o administrador.";
                }
            }

        } catch (NumberFormatException e) {
            mensagem = "ID inválido.";
        } catch (Exception e) {
            e.printStackTrace();
            mensagem = "Erro inesperado ao tentar deletar.";
        }

        request.getSession().setAttribute("mensagem", mensagem);
        response.sendRedirect(request.getContextPath() + "/admins");
    }
}
