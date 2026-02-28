package servlet.BoletimServlet;

import dao.BoletimDAO;
import model.Boletim;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.text.SimpleDateFormat;

@WebServlet("/boletim-update")
public class AtualizarBoletimServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParametro = request.getParameter("id");

        BoletimDAO dao = new BoletimDAO();

        try {

            if (idParametro == null || idParametro.isEmpty()) {
                request.getSession().setAttribute("mensagem", "ID inválido.");
            } else {

                int id = Integer.parseInt(idParametro);
                int idProfessor = Integer.parseInt(request.getParameter("idProfessor"));
                int idAluno = Integer.parseInt(request.getParameter("idAluno"));

                double nota1 = Double.parseDouble(request.getParameter("nota1"));
                double nota2 = Double.parseDouble(request.getParameter("nota2"));

                String desc1 = request.getParameter("descricao1");
                String desc2 = request.getParameter("descricao2");

                double media = (nota1 + nota2) / 2;
                boolean aprovado = media >= 6;

                String obs = request.getParameter("observacao");

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date data = sdf.parse(request.getParameter("data"));

                Boletim boletim = new Boletim(id, idProfessor, idAluno, nota1, desc1, nota2, desc2, aprovado, obs, data);

                if (dao.atualizar(boletim) > 0) {
                    request.getSession().setAttribute("mensagem", "Boletim atualizado.");
                } else {
                    request.getSession().setAttribute("mensagem", "Erro ao atualizar boletim.");
                }
            }

        } catch (Exception e) {
            request.getSession().setAttribute("mensagem", "Erro ao atualizar boletim.");
        }

        response.sendRedirect(request.getContextPath() + "/boletins");
    }
}
