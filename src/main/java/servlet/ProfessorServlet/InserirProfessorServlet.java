package servlet.ProfessorServlet;

import dao.ProfessorDAO;
import model.Professor;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/professor-create")
public class InserirProfessorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/view/Professor/cadastrarProfessor.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String mensagem;
        ProfessorDAO dao = new ProfessorDAO();

        try {

            String nome = request.getParameter("nome");
            String senha = request.getParameter("senha");

            if (nome == null || nome.isBlank() || senha == null || senha.isBlank()) {

                request.setAttribute("mensagem", "Campos obrigatórios não preenchidos!");

                RequestDispatcher dispatcher =
                        request.getRequestDispatcher("/WEB-INF/view/Professor/cadastrarProfessor.jsp");

                dispatcher.forward(request, response);
                return;
            }

            Professor professor = new Professor();

            professor.setNome(nome);
            professor.setDataContratacao(LocalDate.parse(request.getParameter("data")));
            professor.setEmail(request.getParameter("email"));
            professor.setSenha(senha);
            professor.setMateria(request.getParameter("materia"));
            professor.setUsuario(request.getParameter("usuario"));

            int id = dao.inserir(professor);

            if (id > 0) {
                mensagem = "Professor cadastrado!";
            } else {
                mensagem = "Erro ao cadastrar professor.";
            }

            request.getSession().setAttribute("mensagem", mensagem);
            response.sendRedirect(request.getContextPath() + "/professores");

        } catch (Exception e) {

            e.printStackTrace();
            request.getSession().setAttribute("mensagem", "Erro ao cadastrar professor!");
            response.sendRedirect(request.getContextPath() + "/professores");
        }
    }
}
