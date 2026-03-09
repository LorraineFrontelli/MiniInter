package servlet.BoletimServlet;

import dao.BoletimDAO;
import model.Boletim;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/boletins")
public class BuscarBoletimServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session != null) {
            String mensagem = (String) session.getAttribute("mensagem");
            if (mensagem != null) {
                request.setAttribute("mensagem", mensagem);
                session.removeAttribute("mensagem");
            }
        }

        String filtro = request.getParameter("filtroAluno");

        BoletimDAO dao = new BoletimDAO();
        List<Boletim> boletins = new ArrayList<>();

        try {

            if (filtro != null && !filtro.trim().isEmpty()) {

                filtro = filtro.trim();

                try {
                    // Se for número → busca por ID
                    int idAluno = Integer.parseInt(filtro);
                    boletins = dao.buscarPorAluno(idAluno);

                } catch (NumberFormatException e) {
                    // Se não for número → busca por nome
                    boletins = dao.buscarPorNomeAluno(filtro);
                }

                if (boletins == null || boletins.isEmpty()) {
                    request.setAttribute("mensagem", "Nenhum boletim encontrado.");
                } else {
                    request.setAttribute("mensagem",
                            "Foram encontrados " + boletins.size() + " boletins.");
                }

            } else {
                // Se não houver filtro → listar todos
                boletins = dao.listar();

                if (boletins == null || boletins.isEmpty()) {
                    request.setAttribute("mensagem", "Nenhum boletim cadastrado.");
                } else {
                    request.setAttribute("mensagem",
                            "Foram encontrados " + boletins.size() + " boletins.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", "Erro ao buscar boletins.");
        }

        request.setAttribute("boletins", boletins);

        String tipo = null;

        if (session != null) {
            tipo = (String) session.getAttribute("tipoUsuario");
        }


        if ("admin".equals(tipo)) {
            request.getRequestDispatcher("/WEB-INF/views/administrador/tab-boletim.jsp")
                    .forward(request, response);
        } else {
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("/WEB-INF/views/Boletim/crudBoletim.jsp");

            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
