package servlet.TelefoneServlet;

import dao.TelefoneDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/telefone-delete")
public class DeletarTelefoneServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect(request.getContextPath() + "/telefones");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParametro = request.getParameter("id");
        TelefoneDAO dao = new TelefoneDAO();
        String mensagem;

        try {

            if (idParametro == null || idParametro.isEmpty()) {
                mensagem = "ID do telefone não foi encontrado.";
            } else {

                int id = Integer.parseInt(idParametro);

                if (dao.deletar(id) > 0) {
                    mensagem = "Telefone deletado com sucesso!";
                } else {
                    mensagem = "Não foi possível deletar o telefone.";
                }
            }

        } catch (NumberFormatException e) {
            mensagem = "ID inválido.";
        } catch (Exception e) {
            e.printStackTrace();
            mensagem = "Erro inesperado ao tentar deletar.";
        }

        request.getSession().setAttribute("mensagem", mensagem);
        response.sendRedirect(request.getContextPath() + "/telefones");
    }
}
