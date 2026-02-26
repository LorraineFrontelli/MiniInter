package servlet;

import dao.AdministradorDAO;
import dao.AlunoDAO;
import dao.ProfessorDAO;
import model.Administrador;
import model.Aluno;
import model.Professor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/view/Login/login.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        AdministradorDAO admDAO = new AdministradorDAO();
        AlunoDAO alunoDAO = new AlunoDAO();
        ProfessorDAO professorDAO = new ProfessorDAO();

        try {

            List<Administrador> admins = admDAO.listar();

            for (Administrador admin : admins) {
                if (admin.getLogin().equals(email) &&
                        admin.getSenha().equals(senha)) {

                    request.getSession().setAttribute("usuario", admin);
                    response.sendRedirect(request.getContextPath() + "/administradores");
                    return;
                }
            }

            List<Aluno> alunos = alunoDAO.listar();

            for (Aluno aluno : alunos) {
                if (aluno.getEmail().equals(email) &&
                        aluno.getSenha().equals(senha)) {

                    request.getSession().setAttribute("usuario", aluno);
                    response.sendRedirect(request.getContextPath() + "/alunos");
                    return;
                }
            }

            List<Professor> professores = professorDAO.listar();

            for (Professor professor : professores) {
                if (professor.getEmail().equals(email) &&
                        professor.getSenha().equals(senha)) {

                    request.getSession().setAttribute("usuario", professor);
                    response.sendRedirect(request.getContextPath() + "/professores");
                    return;
                }
            }

            request.setAttribute("mensagem", "Email ou senha inválidos.");
            request.getRequestDispatcher("/WEB-INF/view/Login/login.jsp")
                    .forward(request, response);

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute("mensagem", "Erro ao realizar login.");
            request.getRequestDispatcher("/WEB-INF/view/Login/login.jsp")
                    .forward(request, response);
        }
    }
}
