package servlet.MensagemServlet;

import dao.MensagemDAO;
import jakarta.servlet.http.HttpSession;
import model.Aluno;
import model.Mensagem;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Professor;


import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Object usuario   = session.getAttribute("usuario");
        String tipoAtual = (String) session.getAttribute("tipoUsuario");
        int idAtual;
        if (tipoAtual.equals("ALUNO")) {
            idAtual = ((Aluno) usuario).getMatricula();
        } else {
            idAtual = ((Professor) usuario).getId();
        }

        MensagemDAO dao = new MensagemDAO();
        List<Map<String, Object>> usuarios = dao.listarTodosUsuarios(idAtual, tipoAtual);

        request.setAttribute("usuarios", usuarios);
        request.setAttribute("idAtual", idAtual);
        request.setAttribute("tipoAtual", tipoAtual);
        request.getRequestDispatcher("/WEB-INF/views/chat/conversasNovas.jsp")
                .forward(request, response);
    }
}
