package servlet.BoletimServlet;

import dao.BoletimDAO;
import model.Boletim;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/boletim-create")
public class InserirBoletimServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/view/Boletim/cadastrar.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String mensagem;
        BoletimDAO dao = new BoletimDAO();

        try {

            double nota1 = Double.parseDouble(request.getParameter("nota1"));
            double nota2 = Double.parseDouble(request.getParameter("nota2"));

            Date data = new SimpleDateFormat("yyyy-MM-dd")
                    .parse(request.getParameter("data"));

            Boletim boletim = new Boletim();

            boletim.setNota1(nota1);
            boletim.setDescricao1(request.getParameter("descricao1"));
            boletim.setNota2(nota2);
            boletim.setDescricao2(request.getParameter("descricao2"));
            boletim.setAprovado(Boolean.parseBoolean(request.getParameter("aprovado")));
            boletim.setObservacao(request.getParameter("observacao"));
            boletim.setDataLancamento(data);

            int retorno = dao.inserir(boletim);

            if (retorno > 0) {
                mensagem = "Boletim cadastrado!";
            } else {
                mensagem = "Erro ao cadastrar boletim.";
            }

            request.getSession().setAttribute("mensagem", mensagem);
            response.sendRedirect(request.getContextPath() + "/boletins");

        } catch (Exception e) {

            e.printStackTrace();
            request.getSession().setAttribute("mensagem", "Erro ao cadastrar boletim!");
            response.sendRedirect(request.getContextPath() + "/boletins");
        }
    }
}
