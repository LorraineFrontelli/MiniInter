package servlet.ProfessorServlet;

import dao.ProfessorDAO;
import model.Professor;
import utils.ValidacaoRegex;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/professor-update")
public class AtualizarProfessorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParametro = request.getParameter("id");

        if (idParametro == null || idParametro.isEmpty()) {
            request.getSession().setAttribute("mensagem", "ID inválido.");
            response.sendRedirect(request.getContextPath() + "/professores");
            return;
        }

        try {

            int id = Integer.parseInt(idParametro);

            ProfessorDAO dao = new ProfessorDAO();
            Professor professor = dao.buscarPorId(id);

            if (professor == null) {
                request.getSession().setAttribute("mensagem", "Professor não encontrado.");
                response.sendRedirect(request.getContextPath() + "/professores");
                return;
            }

            request.setAttribute("professorParaEditar", professor);

            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("/WEB-INF/view/Professor/atualizarProfessor.jsp");

            dispatcher.forward(request, response);

        } catch (Exception e) {
            request.getSession().setAttribute("mensagem", "Erro ao carregar professor.");
            response.sendRedirect(request.getContextPath() + "/professores");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParametro = request.getParameter("id");
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String materia = request.getParameter("materia");
        String usuario = request.getParameter("usuario");
        String dataParametro = request.getParameter("dataContratacao");

        ProfessorDAO dao = new ProfessorDAO();

        try {

            if (idParametro == null || nome == null || email == null ||
                    senha == null || materia == null || usuario == null ||
                    dataParametro == null || nome.isBlank() || email.isBlank()) {

                request.getSession().setAttribute("mensagem", "Campos obrigatórios não preenchidos.");

            } else if (!ValidacaoRegex.verificarEmail(email)) {

                request.getSession().setAttribute("mensagem", "Email inválido.");

            } else if (!ValidacaoRegex.verificarSenha(senha)) {

                request.getSession().setAttribute("mensagem", "Senha inválida.");

            } else {

                int id = Integer.parseInt(idParametro);
                LocalDate data = LocalDate.parse(dataParametro);

                Professor professor = new Professor(id, nome, data, email, senha, materia, usuario);

                if (dao.atualizar(professor) > 0) {
                    request.getSession().setAttribute("mensagem", "Professor atualizado.");
                } else {
                    request.getSession().setAttribute("mensagem", "Erro ao atualizar professor.");
                }
            }

        } catch (Exception e) {
            request.getSession().setAttribute("mensagem", "Erro ao atualizar professor.");
        }

        response.sendRedirect(request.getContextPath() + "/professores");
    }
}
