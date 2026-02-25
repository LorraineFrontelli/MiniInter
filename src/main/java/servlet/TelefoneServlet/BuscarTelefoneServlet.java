package servlet.TelefoneServlet;

import dao.TelefoneDAO;
import model.Telefone;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/telefones")
public class BuscarTelefoneServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String mensagem = (String) session.getAttribute("mensagem");

        if (mensagem != null) {
            request.setAttribute("mensagem", mensagem);
            session.removeAttribute("mensagem");
        }

        String id = request.getParameter("filtroId");
        String idAluno = request.getParameter("filtroAluno");
        String numero = request.getParameter("filtroNumero");

        TelefoneDAO telefoneDAO = new TelefoneDAO();
        List<Telefone> telefones = new ArrayList<>();

        try {

            if (id != null && !id.trim().isEmpty()) {

                Telefone telefone = telefoneDAO.buscarPorId(Integer.parseInt(id));

                if (telefone == null) {
                    request.setAttribute("mensagem", "Nenhum telefone encontrado.");
                } else {
                    telefones.add(telefone);
                    request.setAttribute("mensagem", "Telefone encontrado.");
                }

            } else if (idAluno != null && !idAluno.trim().isEmpty()) {

                telefones = telefoneDAO.listarIdAluno(Integer.parseInt(idAluno));

                if (telefones == null || telefones.isEmpty()) {
                    request.setAttribute("mensagem", "Nenhum telefone encontrado para este aluno.");
                } else {
                    request.setAttribute("mensagem", "Foram encontrados " + telefones.size() + " telefones.");
                }

            } else if (numero != null && !numero.trim().isEmpty()) {

                telefones = telefoneDAO.buscarPorNumero(Integer.parseInt(numero));

                if (telefones == null || telefones.isEmpty()) {
                    request.setAttribute("mensagem", "Nenhum telefone encontrado com esse número.");
                } else {
                    request.setAttribute("mensagem", "Foram encontrados " + telefones.size() + " telefones.");
                }

            } else {

                telefones = telefoneDAO.listar();

                if (telefones == null || telefones.isEmpty()) {
                    request.setAttribute("mensagem", "Nenhum telefone cadastrado.");
                } else {
                    request.setAttribute("mensagem", "Foram encontrados " + telefones.size() + " telefones.");
                }

            }

        } catch (NumberFormatException e) {
            request.setAttribute("mensagem", "Valor numérico inválido.");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", "Erro ao buscar telefones.");
        }

        request.setAttribute("telefones", telefones);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/view/Telefone/crudTelefone.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
