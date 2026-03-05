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

            String idAlunoParam = request.getParameter("idAluno");
            String numero = request.getParameter("numero");
            String tipo = request.getParameter("tipo");

            if (idAlunoParam == null || idAlunoParam.isBlank()
                    || numero == null || numero.isBlank()
                    || tipo == null || tipo.isBlank()) {

                mensagem = "Todos os campos são obrigatórios!";
                request.getSession().setAttribute("mensagem", mensagem);
                response.sendRedirect(request.getContextPath() + "/telefones");
                return;
            }

            int idAluno = Integer.parseInt(idAlunoParam);

            Telefone telefone = new Telefone();
            telefone.setIdAluno(idAluno);
            telefone.setNumero(numero);
            telefone.setTipo(tipo);

            int idGerado = dao.inserir(telefone);

            if (idGerado > 0) {
                mensagem = "Telefone cadastrado com sucesso!";
            } else {
                mensagem = "Erro ao cadastrar telefone.";
            }

        } catch (NumberFormatException e) {
            mensagem = "ID do aluno inválido!";
        } catch (Exception e) {
            e.printStackTrace();
            mensagem = "Erro inesperado ao cadastrar telefone.";
        }

        request.getSession().setAttribute("mensagem", mensagem);
        response.sendRedirect(request.getContextPath() + "/telefones");
    }
}
