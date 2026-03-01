package servlet.ProfessorServlet;

import dao.ProfessorDAO;



import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;

@WebServlet("/professor-delete")
public class DeletarProfessorServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect(request.getContextPath() + "/professores");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParametro = request.getParameter("id");
        ProfessorDAO dao = new ProfessorDAO();
        String mensagem;

        try {

            if (idParametro == null || idParametro.isEmpty()) {
                mensagem = "ID do professor não foi encontrado.";
            } else {

                int id = Integer.parseInt(idParametro);

                if (dao.deletar(id) > 0) {
                    mensagem = "Professor deletado com sucesso!";
                } else {
                    mensagem = "Não foi possível deletar o professor.";
                }
            }

        } catch (NumberFormatException e) {
            mensagem = "ID inválido.";
        } catch (Exception e) {
            e.printStackTrace();
            mensagem = "Erro inesperado ao tentar deletar.";
        }

        request.getSession().setAttribute("mensagem", mensagem);
        response.sendRedirect(request.getContextPath() + "/professores");
    }
}
