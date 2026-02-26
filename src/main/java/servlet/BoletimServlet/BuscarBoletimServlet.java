package servlet.BoletimServlet;

import dao.BoletimDAO;
import model.Boletim;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/boletins")
public class BuscarBoletimServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String mensagem = (String) session.getAttribute("mensagem");

        if (mensagem != null) {
            request.setAttribute("mensagem", mensagem);
            session.removeAttribute("mensagem");
        }

        String filtro = request.getParameter("filtroAluno");

        BoletimDAO dao = new BoletimDAO();
        List<Boletim> boletins = new ArrayList<>();

        try {

            if (filtro != null && !filtro.trim().isEmpty()) {

                try {
                    int idAluno = Integer.parseInt(filtro);
                    boletins = dao.buscarPorAluno(idAluno);

                } catch (NumberFormatException e) {
                    boletins = dao.buscarPorNomeAluno(filtro);
                }

                if (boletins == null || boletins.isEmpty()) {
                    request.setAttribute("mensagem", "Nenhum boletim encontrado.");
                } else {
                    request.setAttribute("mensagem",
                            "Foram encontrados " + boletins.size() + " boletins.");
                }

            } else {

                request.setAttribute("mensagem", "Informe o ID ou nome do aluno.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", "Erro ao buscar boletins.");
        }

        request.setAttribute("boletins", boletins);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/view/Boletim/crudBoletim.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
