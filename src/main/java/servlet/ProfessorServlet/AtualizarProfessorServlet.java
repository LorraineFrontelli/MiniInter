package servlet.ProfessorServlet;

import java.io.IOException;
import java.time.LocalDate;

import dao.ProfessorDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Professor;

@WebServlet("/professor-update")
public class AtualizarProfessorServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        try {

            int id = Integer.parseInt(request.getParameter("id"));
            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            String senha = request.getParameter("senha");
            String materia = request.getParameter("materia");
            String usuario = request.getParameter("usuario");
            String dataStr = request.getParameter("data");

            LocalDate data = null;

            if (dataStr != null && !dataStr.isBlank()) {
                data = LocalDate.parse(dataStr);
            }

            Professor professor =
                    new Professor(id, nome, data, email, senha, materia, usuario);

            ProfessorDAO dao = new ProfessorDAO();

            if (dao.atualizar(professor) > 0) {

                session.setAttribute("mensagem", "Professor atualizado com sucesso!");

            } else {

                session.setAttribute("mensagem", "Erro ao atualizar professor.");
            }

        } catch (Exception e) {

            e.printStackTrace();
            session.setAttribute("mensagem", "Erro ao atualizar professor.");

        }

        response.sendRedirect(request.getContextPath() + "/professores");

    }

}
