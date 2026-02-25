package servlet.AlunoServlet;

import dao.AlunoDAO;
import model.Aluno;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/alunos")
public class BuscarAlunoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String mensagem = (String) session.getAttribute("mensagem");

        if (mensagem != null) {
            request.setAttribute("mensagem", mensagem);
            session.removeAttribute("mensagem");
        }

        String nome = request.getParameter("filtroNome");
        String matriculaParam = request.getParameter("filtroMatricula");

        AlunoDAO dao = new AlunoDAO();
        List<Aluno> alunos = new ArrayList<>();

        try {

            if (matriculaParam != null && !matriculaParam.trim().isEmpty()) {

                int matricula = Integer.parseInt(matriculaParam);
                Aluno aluno = dao.buscarPorMatricula(matricula);

                if (aluno == null) {
                    request.setAttribute("mensagem", "Aluno não encontrado.");
                } else {
                    alunos.add(aluno);
                    request.setAttribute("mensagem", "Aluno encontrado.");
                }

            } else if (nome != null && !nome.trim().isEmpty()) {

                alunos = dao.buscarPorNome(nome);

                if (alunos == null || alunos.isEmpty()) {
                    request.setAttribute("mensagem", "Nenhum aluno encontrado.");
                } else {
                    request.setAttribute("mensagem", "Foram encontrados " + alunos.size() + " alunos.");
                }

            } else {

                alunos = dao.listar();

                if (alunos == null || alunos.isEmpty()) {
                    request.setAttribute("mensagem", "Nenhum aluno cadastrado.");
                } else {
                    request.setAttribute("mensagem", "Foram encontrados " + alunos.size() + " alunos.");
                }
            }

        } catch (NumberFormatException e) {
            request.setAttribute("mensagem", "Matrícula inválida.");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", "Erro ao buscar alunos.");
        }

        request.setAttribute("alunos", alunos);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/view/Aluno/crudAluno.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
