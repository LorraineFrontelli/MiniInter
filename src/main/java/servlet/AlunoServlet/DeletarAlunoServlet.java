package servlet.AlunoServlet;

import dao.AlunoDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/aluno-delete")
public class DeletarAlunoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect(request.getContextPath() + "/alunos");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String matriculaParametro = request.getParameter("matricula");
        AlunoDAO dao = new AlunoDAO();
        String mensagem;

        try {

            if (matriculaParametro == null || matriculaParametro.isEmpty()) {
                mensagem = "Matrícula não encontrada.";
            } else {

                int matricula = Integer.parseInt(matriculaParametro);

                if (dao.deletar(matricula) > 0) {
                    mensagem = "Aluno deletado com sucesso!";
                } else {
                    mensagem = "Não foi possível deletar.";
                }
            }

        } catch (NumberFormatException e) {
            mensagem = "Matrícula inválida.";
        } catch (Exception e) {
            e.printStackTrace();
            mensagem = "Erro ao deletar aluno.";
        }

        request.getSession().setAttribute("mensagem", mensagem);
        response.sendRedirect(request.getContextPath() + "/alunos");
    }
}
