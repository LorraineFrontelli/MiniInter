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

@WebServlet("/aluno-professor-create")
public class InserirAlunoProfServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/view/AlunoProfessor/cadastrar.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String mensagem = null;
        AlunoProfessorDAO dao = new AlunoProfessorDAO();

        try {

            String idProfessorStr = request.getParameter("idProfessor");
            String idAlunoStr = request.getParameter("idAluno");
            String serieStr = request.getParameter("serie");
            String turma = request.getParameter("turma");

            if (idProfessorStr == null || idAlunoStr == null || turma == null || turma.isBlank()) {
                mensagem = "Campos obrigatórios não preenchidos!";
            }

            if (mensagem != null) {

                request.setAttribute("mensagem", mensagem);

                RequestDispatcher dispatcher =
                        request.getRequestDispatcher("/WEB-INF/view/AlunoProfessor/cadastrar.jsp");

                dispatcher.forward(request, response);
                return;
            }

            int idProfessor = Integer.parseInt(idProfessorStr);
            int idAluno = Integer.parseInt(idAlunoStr);
            int serie = Integer.parseInt(serieStr);

            AlunoProfessor ap = new AlunoProfessor();
            ap.setIdProfessor(idProfessor);
            ap.setIdAluno(idAluno);
            ap.setSerie(serie);
            ap.setTurma(turma);

            int retorno = dao.inserir(ap);

            if (retorno > 0) {
                mensagem = "Registro criado com sucesso!";
            } else {
                mensagem = "Erro ao inserir registro.";
            }

            request.getSession().setAttribute("mensagem", mensagem);
            response.sendRedirect(request.getContextPath() + "/aluno-professor");

        } catch (NumberFormatException e) {

            e.printStackTrace();
            request.getSession().setAttribute("mensagem", "Valores numéricos inválidos!");
            response.sendRedirect(request.getContextPath() + "/aluno-professor");

        } catch (Exception e) {

            e.printStackTrace();
            request.getSession().setAttribute("mensagem", "Erro ao inserir registro!");
            response.sendRedirect(request.getContextPath() + "/aluno-professor");
        }
    }
}
