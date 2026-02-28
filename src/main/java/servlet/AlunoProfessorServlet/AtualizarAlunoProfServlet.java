package servlet.AlunoProfessorServlet;

import dao.AlunoProfessorDAO;
import model.AlunoProfessor;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;

@WebServlet("/aluno-professor-update")
public class AtualizarAlunoProfServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParametro = request.getParameter("id");

        if (idParametro == null || idParametro.isEmpty()) {
            request.getSession().setAttribute("mensagem", "ID não informado.");
            response.sendRedirect(request.getContextPath() + "/aluno-professor");
            return;
        }

        try {

            int id = Integer.parseInt(idParametro);

            AlunoProfessorDAO dao = new AlunoProfessorDAO();
            AlunoProfessor ap = dao.buscarPorId(id);

            if (ap == null) {
                request.getSession().setAttribute("mensagem", "Registro não encontrado.");
                response.sendRedirect(request.getContextPath() + "/aluno-professor");
                return;
            }

            request.setAttribute("apParaEditar", ap);

            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("/WEB-INF/view/AlunoProfessor/atualizar.jsp");

            dispatcher.forward(request, response);

        } catch (Exception e) {
            request.getSession().setAttribute("mensagem", "Erro ao carregar registro.");
            response.sendRedirect(request.getContextPath() + "/aluno-professor");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParametro = request.getParameter("id");
        String idProfessorParametro = request.getParameter("idProfessor");
        String idAlunoParametro = request.getParameter("idAluno");
        String serieParametro = request.getParameter("serie");
        String turma = request.getParameter("turma");

        AlunoProfessorDAO dao = new AlunoProfessorDAO();

        try {

            if (idParametro == null || idProfessorParametro == null || idAlunoParametro == null ||
                    serieParametro == null || turma == null || turma.isBlank()) {

                request.getSession().setAttribute("mensagem", "Campos obrigatórios não preenchidos.");
            } else {

                int id = Integer.parseInt(idParametro);
                int idProfessor = Integer.parseInt(idProfessorParametro);
                int idAluno = Integer.parseInt(idAlunoParametro);
                int serie = Integer.parseInt(serieParametro);

                AlunoProfessor ap = new AlunoProfessor(id, idProfessor, idAluno, serie, turma);

                if (dao.atualizar(ap) > 0) {
                    request.getSession().setAttribute("mensagem", "Registro atualizado com sucesso.");
                } else {
                    request.getSession().setAttribute("mensagem", "Erro ao atualizar registro.");
                }
            }

        } catch (Exception e) {
            request.getSession().setAttribute("mensagem", "Erro ao atualizar registro.");
        }

        response.sendRedirect(request.getContextPath() + "/aluno-professor");
    }
}
