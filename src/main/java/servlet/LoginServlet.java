package servlet;

import dao.*;
import jakarta.servlet.http.HttpSession;
import model.Administrador;
import model.Aluno;
import model.Professor;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/views/autenticacao/login.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("login");
        String senha = request.getParameter("senha");

        HttpSession session = request.getSession(false);
        if (session != null) {
            String emailAuto = (String) session.getAttribute("login");
            String senhaAuto = (String) session.getAttribute("senha");

            if (emailAuto != null && senhaAuto != null) {
                email = emailAuto;
                senha = senhaAuto;
                session.removeAttribute("login");
                session.removeAttribute("senha");
            }
        }

        AdministradorDAO admDAO = new AdministradorDAO();
        AlunoDAO alunoDAO = new AlunoDAO();
        ProfessorDAO professorDAO = new ProfessorDAO();
        AlunoProfessorDAO alunoProfessorDAO = new AlunoProfessorDAO();
        TelefoneDAO  telefoneDAO = new TelefoneDAO();
        BoletimDAO boletimDAO = new BoletimDAO();
        TarefasDAO tarefasDAO = new TarefasDAO();

        try {

            List<Administrador> admins = admDAO.listar();

            for (Administrador admin : admins) {
                if (admin.getLogin().equals(email) &&
                        BCrypt.checkpw(senha, admin.getSenha())) {

                    request.getSession().setAttribute("usuario", admin);
                    response.sendRedirect(request.getContextPath() + "/administradores");
                    return;
                }
            }

            List<Aluno> alunos = alunoDAO.listar();

            for (Aluno aluno : alunos) {
                if (aluno.getEmail().equals(email) &&
                        BCrypt.checkpw(senha, aluno.getSenha())) {
                    request.getSession().setAttribute("usuario", aluno);
                    request.getSession().setAttribute("alunoProfessor", alunoProfessorDAO.buscarPorIdAluno(aluno.getMatricula()));
                    request.getSession().setAttribute("telefoneAluno", telefoneDAO.listarIdAluno(aluno.getMatricula()));
                    request.getSession().setAttribute("boletim", boletimDAO.buscarPorAluno(aluno.getMatricula()));
                    request.getSession().setAttribute("tarefas", tarefasDAO.listarIdAluno(aluno.getMatricula()));
                    response.sendRedirect(request.getContextPath() + "/alunos?page=perfil-aluno");
                    return;
                }
            }

            List<Professor> professores = professorDAO.listar();

            for (Professor professor : professores) {
                if (professor.getUsuario().equals(email) &&
                        BCrypt.checkpw(senha, professor.getSenha())) {
                    request.getSession().setAttribute("usuario", professor);
                    response.sendRedirect(request.getContextPath() + "/professores?page=perfil-professor");
                    return;
                }
            }

            request.setAttribute("mensagem", "Email ou senha inválidos.");
            request.getRequestDispatcher("/WEB-INF/views/autenticacao/login.jsp")
                    .forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute("mensagem", "Erro ao realizar login.");
            request.getRequestDispatcher("/WEB-INF/views/autenticacao/login.jsp")
                    .forward(request, response);
        }
    }
}
