package servlet.MensagemServlet;

import dao.MensagemDAO;
import model.Mensagem;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.sql.Timestamp;

@WebServlet("/mensagem-create")
public class InserirMensagemServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String mensagemRetorno;
        MensagemDAO dao = new MensagemDAO();

        try {

            String texto = request.getParameter("mensagem");

            if (texto == null || texto.isBlank()) {

                request.getSession().setAttribute("mensagem", "Mensagem vazia!");
                response.sendRedirect(request.getContextPath() + "/mensagens");
                return;
            }

            Mensagem mensagem = new Mensagem();

            mensagem.setIdProfessor(Integer.parseInt(request.getParameter("idProfessor")));
            mensagem.setIdAluno(Integer.parseInt(request.getParameter("idAluno")));
            mensagem.setMensagem(texto);
            mensagem.setDataMensagem(new Timestamp(System.currentTimeMillis()));

            int id = dao.inserir(mensagem);

            if (id > 0) {
                mensagemRetorno = "Mensagem enviada!";
            } else {
                mensagemRetorno = "Erro ao enviar mensagem.";
            }

            request.getSession().setAttribute("mensagem", mensagemRetorno);
            response.sendRedirect(request.getContextPath() + "/mensagens");

        } catch (Exception e) {

            e.printStackTrace();
            request.getSession().setAttribute("mensagem", "Erro ao enviar mensagem!");
            response.sendRedirect(request.getContextPath() + "/mensagens");
        }
    }
}
