package servlet.TelefoneServlet;

import dao.TelefoneDAO;
import model.Telefone;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/telefone-update")
public class AtualizarTelefoneServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String mensagem;
        TelefoneDAO dao = new TelefoneDAO();

        try {

            String idParam = request.getParameter("id");
            String numero = request.getParameter("numero");
            String tipo = request.getParameter("tipo");

            if (idParam == null || idParam.isBlank()
                    || numero == null || numero.isBlank()
                    || tipo == null || tipo.isBlank()) {

                mensagem = "Todos os campos são obrigatórios!";
                request.getSession().setAttribute("mensagem", mensagem);
                response.sendRedirect(request.getContextPath() + "/telefones");
                return;
            }

            int id = Integer.parseInt(idParam);

            Telefone telefone = new Telefone();
            telefone.setId(id);
            telefone.setNumero(numero);
            telefone.setTipo(tipo);

            int linhasAfetadas = dao.atualizar(telefone);

            if (linhasAfetadas > 0) {
                mensagem = "Telefone atualizado com sucesso!";
            } else {
                mensagem = "Telefone não encontrado para atualização.";
            }

        } catch (NumberFormatException e) {
            mensagem = "ID inválido!";
        } catch (Exception e) {
            e.printStackTrace();
            mensagem = "Erro inesperado ao atualizar telefone.";
        }

        request.getSession().setAttribute("mensagem", mensagem);
        response.sendRedirect(request.getContextPath() + "/telefones");
    }
}
