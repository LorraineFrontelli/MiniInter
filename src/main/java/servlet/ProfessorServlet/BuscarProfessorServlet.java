package servlet.ProfessorServlet;

import dao.*;
import model.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/professores")
public class BuscarProfessorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String mensagem = (String) session.getAttribute("mensagem");
        Professor usuario = (Professor) session.getAttribute("usuario");

        if (mensagem != null) {
            request.setAttribute("mensagem", mensagem);
            session.removeAttribute("mensagem");
        }

        String nome = request.getParameter("filtroNome");
        String idParam = request.getParameter("filtroId");

        ProfessorDAO dao = new ProfessorDAO();
        List<Professor> professores = new ArrayList<>();

        try {

            if (idParam != null && !idParam.trim().isEmpty()) {

                int id = Integer.parseInt(idParam);
                Professor professor = dao.buscarPorId(id);

                if (professor != null) {
                    professores.add(professor);
                }

            } else if (nome != null && !nome.trim().isEmpty()) {

                professores = dao.listarProfessorPorNome(nome);

            } else {

                professores = dao.listar();

            }

            if (professores == null || professores.isEmpty()) {
                request.setAttribute("mensagem", "Nenhum professor encontrado.");
            } else {
                request.setAttribute("mensagem",
                        "Foram encontrados " + professores.size() + " professores.");
            }

        } catch (NumberFormatException e) {
            request.setAttribute("mensagem", "ID inválido.");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", "Erro ao buscar professores.");
            professores = new ArrayList<>();
        }

        request.setAttribute("professores", professores);

        String page = request.getParameter("page");

        if (page != null) {
            AlunoDAO alunoDAO = new AlunoDAO();
            switch (page) {
                case "notas":

                    String matriculaParam = request.getParameter("matricula");
                    int matricula = Integer.parseInt(matriculaParam);

                    Aluno aluno = alunoDAO.buscarPorMatriculaEProfessor(matricula, usuario.getId());
                    BoletimDAO boletimDAO = new BoletimDAO();
                    Boletim boletim = boletimDAO.buscarPorAlunoEProfessor(matricula, usuario.getId());
                    TelefoneDAO telefoneDAO = new TelefoneDAO();
                    List<Telefone> telefones = telefoneDAO.listarIdAluno(matricula);
                    AlunoProfessorDAO alunoProfessorDAO = new AlunoProfessorDAO();
                    AlunoProfessor alunoProfessor = alunoProfessorDAO.buscarPorIdAluno(matricula);

                    request.setAttribute("aluno", aluno);
                    request.setAttribute("boletim", boletim);
                    request.setAttribute("telefones", telefones);
                    request.setAttribute("alunoProfessor", alunoProfessor);

                    request.getRequestDispatcher("/WEB-INF/views/professor/notas.jsp")
                            .forward(request, response);
                    break;
                case "lembretes":
                    request.getRequestDispatcher("/WEB-INF/views/professor/lembretes.jsp")
                            .forward(request, response);
                    break;
                case "estatisticas":
                    request.getRequestDispatcher("/WEB-INF/views/professor/estatisticas.jsp")
                            .forward(request, response);
                    break;
                case "perfil-professor":
                    MensagemDAO mensagemDAO = new MensagemDAO();
                    List<Mensagem> mensagensRecentes = mensagemDAO.listarHistoricoRecente(
                            usuario.getId(), "PROFESSOR", 5
                    );
                    request.setAttribute("mensagensRecentes", mensagensRecentes);
                    request.getRequestDispatcher("/WEB-INF/views/professor/perfil-professor.jsp")
                            .forward(request, response);
                    break;
                case "buscar":
                        List<Aluno> alunos = alunoDAO.buscarPorProf(usuario.getId());

                        request.setAttribute("alunos", alunos);

                        if (alunos == null || alunos.isEmpty()) {
                            request.setAttribute("mensagem", "Nenhum aluno encontrado.");
                        } else {
                            request.setAttribute("mensagem",
                                    "Foram encontrados " + alunos.size() + " alunos.");
                        }

                    request.getRequestDispatcher("/WEB-INF/views/professor/buscar.jsp")
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
