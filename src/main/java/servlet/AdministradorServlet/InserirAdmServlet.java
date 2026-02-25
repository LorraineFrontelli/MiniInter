package servlet.AdministradorServlet;

import dao.AdministradorDAO;
import model.Administrador;
import utils.ValidacaoRegex;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;

@WebServlet("/admin-create")
public class InserirAdmServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/view/Admin/cadastrarAdmin.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String mensagem = null;
        AdministradorDAO dao = new AdministradorDAO();

        try {

            String login = request.getParameter("login");
            String senha = request.getParameter("senha");
            String cpfs = request.getParameter("alunoCpf");

            if (login == null || senha == null || login.isBlank() || senha.isBlank()) {
                mensagem = "Campos obrigatórios não preenchidos!";
            }
            else if (!ValidacaoRegex.verificarEmail(login)) {
                mensagem = "Email inválido!";
            }
            else if (!ValidacaoRegex.verificarSenha(senha)) {
                mensagem = "Senha inválida!";
            }

            if (mensagem != null) {

                request.setAttribute("mensagem", mensagem);

                RequestDispatcher dispatcher =
                        request.getRequestDispatcher("/WEB-INF/view/Admin/cadastrarAdmin.jsp");

                dispatcher.forward(request, response);
                return;
            }

            Administrador admin = new Administrador();
            admin.setLogin(login);
            admin.setSenha(senha);

            if (cpfs != null && !cpfs.isBlank()) {

                String[] lista = cpfs.split(",");

                for (String cpf : lista) {
                    admin.adicionarCpf(cpf.trim());
                }
            }

            int retorno = dao.inserir(admin);

            if (retorno > 0) {
                mensagem = "Administrador criado com sucesso!";
            } else {
                mensagem = "Erro ao inserir registro.";
            }

            request.getSession().setAttribute("mensagem", mensagem);
            response.sendRedirect(request.getContextPath() + "/admins");

        } catch (Exception e) {

            e.printStackTrace();
            request.getSession().setAttribute("mensagem", "Erro ao inserir registro!");
            response.sendRedirect(request.getContextPath() + "/admins");
        }
    }
}
