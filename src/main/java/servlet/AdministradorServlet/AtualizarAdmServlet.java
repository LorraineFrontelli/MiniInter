package servlet.AdministradorServlet;

import dao.AdministradorDAO;
import model.Administrador;
import utils.ValidacaoRegex;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;

@WebServlet("/admin-update")
public class AtualizarAdmServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParametro = request.getParameter("id");

        AdministradorDAO dao = new AdministradorDAO();

        try {

            if (idParametro == null || idParametro.isEmpty()) {

                request.getSession().setAttribute("mensagem", "ID inválido.");

            } else {

                int id = Integer.parseInt(idParametro);

                String login = request.getParameter("login");
                String senha = request.getParameter("senha");

                if (login == null || login.isBlank() || senha == null || senha.isBlank()) {

                    request.getSession().setAttribute("mensagem", "Campos obrigatórios não preenchidos.");
                    response.sendRedirect(request.getContextPath() + "/admins");
                    return;
                }

                if (ValidacaoRegex.verificarEmail(login)) {

                    request.getSession().setAttribute("mensagem", "Email inválido.");
                    response.sendRedirect(request.getContextPath() + "/admins");
                    return;
                }

                if (ValidacaoRegex.verificarSenha(senha)) {

                    request.getSession().setAttribute("mensagem", "Senha inválida.");
                    response.sendRedirect(request.getContextPath() + "/admins");
                    return;
                }

                Administrador admin = new Administrador();
                admin.setId(id);
                admin.setLogin(login);
                admin.setSenha(senha);

                String[] cpfs = request.getParameterValues("alunoCpf");

                if (cpfs != null) {

                    for (String cpf : cpfs) {

                        if (cpf != null && !cpf.isBlank()) {
                            admin.adicionarCpf(cpf);
                        }
                    }
                }

                if (dao.atualizar(admin) > 0) {

                    request.getSession().setAttribute("mensagem", "Administrador atualizado.");

                } else {

                    request.getSession().setAttribute("mensagem", "Erro ao atualizar administrador.");
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
            request.getSession().setAttribute("mensagem", "Erro ao atualizar administrador.");
        }

        response.sendRedirect(request.getContextPath() + "/admins");
    }
}
