package servlet.MensagemServlet;

import dao.MensagemDAO;
import model.Mensagem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/mensagem-update")
public class AtualizarMensagemServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParametro = request.getParameter("id");
        String texto = request.getParameter("mensagem");

        MensagemDAO dao = new MensagemDAO();

        try {

            if (idParametro == null || texto == null || texto.isBlank()) {
                request.getSession().setAttribute("mensagem", "Mensagem inválida.");
            } else {

                int id = Integer.parseInt(idParametro);

                Timestamp data = new Timestamp(System.currentTimeMillis());

                Mensagem mensagem = new Mensagem();
                mensagem.setId(id);
                mensagem.setMensagem(texto);
                mensagem.setDataMensagem(data);

                if (dao.atualizar(mensagem) > 0) {
                    request.getSession().setAttribute("mensagem", "Mensagem atualizada.");
                } else {
                    request.getSession().setAttribute("mensagem", "Erro ao atualizar mensagem.");
                }
            }

        } catch (Exception e) {
            request.getSession().setAttribute("mensagem", "Erro ao atualizar mensagem.");
        }

        response.sendRedirect(request.getContextPath() + "/mensagens");
    }
}
