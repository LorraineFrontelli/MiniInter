package servlet.MensagemServlet;

import dao.MensagemDAO;
import model.Mensagem;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/mensagens")
public class BuscarMensagemServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String mensagem = (String) session.getAttribute("mensagem");

        if (mensagem != null) {
            request.setAttribute("mensagem", mensagem);
            session.removeAttribute("mensagem");
        }

        String aluno = request.getParameter("filtroAluno");
        String professor = request.getParameter("filtroProfessor");

        MensagemDAO dao = new MensagemDAO();
        List<Mensagem> mensagens = new ArrayList<>();

        try {

            if (aluno != null && !aluno.trim().isEmpty()
                    && professor != null && !professor.trim().isEmpty()) {

                int idAluno = Integer.parseInt(aluno);
                int idProfessor = Integer.parseInt(professor);

                mensagens = dao.listarHistoricoAlunoProfessor(idAluno, idProfessor);

                if (mensagens == null || mensagens.isEmpty()) {
                    request.setAttribute("mensagem", "Nenhuma mensagem encontrada.");
                } else {
                    request.setAttribute("mensagem",
                            "Foram encontradas " + mensagens.size() + " mensagens.");
                }

            } else {

                mensagens = dao.listar();

                if (mensagens == null || mensagens.isEmpty()) {
                    request.setAttribute("mensagem", "Nenhuma mensagem cadastrada.");
                } else {
                    request.setAttribute("mensagem",
                            "Foram encontradas " + mensagens.size() + " mensagens.");
                }
            }

        } catch (NumberFormatException e) {
            request.setAttribute("mensagem", "IDs inválidos.");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", "Erro ao buscar mensagens.");
        }

        request.setAttribute("mensagens", mensagens);

        String page = request.getParameter("page");

        if (page != null) {
            switch (page) {
                case "chat":
                    request.getRequestDispatcher("/WEB-INF/views/chat/chat.jsp")
                            .forward(request, response);
                    break;
                case "conversas":
                    request.getRequestDispatcher("/WEB-INF/views/chat/conversas.jsp")
                            .forward(request, response);
                    break;
                default:
                    request.getRequestDispatcher("/WEB-INF/views/autenticacao/login")
                            .forward(request, response);
                    break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
