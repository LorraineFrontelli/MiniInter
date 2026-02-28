package servlet.TelefoneServlet;

import dao.TelefoneDAO;
import model.Telefone;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;

@WebServlet("/telefone-create")
public class InserirTelefoneServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String mensagem;
        TelefoneDAO dao = new TelefoneDAO();

        try {

            String numeroStr = request.getParameter("numero");
            String tipo = request.getParameter("tipo");

            if (numeroStr == null || numeroStr.isBlank() || tipo == null) {

                request.getSession().setAttribute("mensagem", "Campos inválidos!");
                response.sendRedirect(request.getContextPath() + "/telefones");
                return;
            }

            Telefone telefone = new Telefone();

            telefone.setIdAluno(Integer.parseInt(request.getParameter("idAluno")));
            telefone.setNumero(Integer.parseInt(numeroStr));
            telefone.setTipo(tipo);

            int id = dao.inserir(telefone);

            if (id > 0) {
                mensagem = "Telefone cadastrado!";
            } else {
                mensagem = "Erro ao cadastrar telefone.";
            }

            request.getSession().setAttribute("mensagem", mensagem);
            response.sendRedirect(request.getContextPath() + "/telefones");

        } catch (NumberFormatException e) {

            e.printStackTrace();
            request.getSession().setAttribute("mensagem", "Número inválido!");
            response.sendRedirect(request.getContextPath() + "/telefones");

        } catch (Exception e) {

            e.printStackTrace();
            request.getSession().setAttribute("mensagem", "Erro ao cadastrar telefone!");
            response.sendRedirect(request.getContextPath() + "/telefones");
        }
    }
}
