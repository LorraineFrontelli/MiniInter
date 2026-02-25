package servlet.ProfessorServlet;

import dao.ProfessorDAO;
import model.Professor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

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

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/view/Professor/crudProfessor.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
