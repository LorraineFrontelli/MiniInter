package servlet.TelefoneServlet;

import dao.TelefoneDAO;
import model.Telefone;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/telefone-update")
public class AtualizarTelefoneServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParametro = request.getParameter("id");
        String numeroParametro = request.getParameter("numero");
        String tipo = request.getParameter("tipo");

        TelefoneDAO dao = new TelefoneDAO();

        try {

            if (idParametro == null || numeroParametro == null || tipo == null || tipo.isBlank()) {
                request.getSession().setAttribute("mensagem", "Campos inválidos.");
            } else {

                int id = Integer.parseInt(idParametro);
                int numero = Integer.parseInt(numeroParametro);

                Telefone telefone = new Telefone();
                telefone.setId(id);
                telefone.setNumero(numero);
                telefone.setTipo(tipo);

                if (dao.atualizar(telefone) > 0) {
                    request.getSession().setAttribute("mensagem", "Telefone atualizado.");
                } else {
                    request.getSession().setAttribute("mensagem", "Erro ao atualizar telefone.");
                }
            }

        } catch (Exception e) {
            request.getSession().setAttribute("mensagem", "Erro ao atualizar telefone.");
        }

        response.sendRedirect(request.getContextPath() + "/telefones");
    }
}
