package servlet.AlunoServlet;

import dao.AlunoDAO;
import model.Aluno;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

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

        String page = request.getParameter("page");

        if (page != null) {
            switch (page) {
                case "agenda":
                    request.getRequestDispatcher("/WEB-INF/views/aluno/agenda.jsp")
                            .forward(request, response);
                    break;
                case "boletim":
                    request.getRequestDispatcher("/WEB-INF/views/aluno/boletim.jsp")
                            .forward(request, response);
                    break;
                case "observacoes":
                    request.getRequestDispatcher("/WEB-INF/views/aluno/observacoes.jsp")
                            .forward(request, response);
                    break;
                case "perfil":
                    request.getRequestDispatcher("/WEB-INF/views/aluno/perfil-aluno.jsp")
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
