package servlet.AlunoProfessorServlet;

import dao.AlunoProfessorDAO;
import model.AlunoProfessor;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/alunoProfessor")
public class BuscarAlunoProfServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String mensagem = (String) session.getAttribute("mensagem");

        if (mensagem != null) {
            request.setAttribute("mensagem", mensagem);
            session.removeAttribute("mensagem");
        }

        AlunoProfessorDAO dao = new AlunoProfessorDAO();
        List<AlunoProfessor> lista = dao.listar();

        if (lista.isEmpty()) {
            request.setAttribute("mensagem", "Nenhuma relação encontrada.");
        } else {
            request.setAttribute("mensagem",
                    "Foram encontradas " + lista.size() + " relações.");
        }

        request.setAttribute("relacoes", lista);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/view/AlunoProfessor/crudAlunoProfessor.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
