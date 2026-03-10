package servlet.AlunoServlet;

import dao.AdministradorDAO;
import dao.AlunoDAO;
import model.Aluno;
import utils.ValidacaoRegex;
import utils.HashSenha;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/aluno-create")
public class InserirAlunoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/views/autenticacao/cadastro.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String cpf = request.getParameter("cpf");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        AdministradorDAO admDAO = new AdministradorDAO();
        AlunoDAO alunoDAO = new AlunoDAO();

        try {

            if (cpf == null || cpf.isBlank()) {

                request.setAttribute("mensagem", "Digite um CPF.");

                request.getRequestDispatcher("/WEB-INF/views/autenticacao/cadastro.jsp")
                        .forward(request, response);
                return;
            }

            cpf = cpf.replaceAll("\\D", "");

            boolean autorizado = admDAO.cpfExiste(cpf);

            if (!autorizado) {

                request.setAttribute("mensagem", "CPF não autorizado para matrícula.");

                request.getRequestDispatcher("/WEB-INF/views/autenticacao/cadastro.jsp")
                        .forward(request, response);
                return;
            }

            if (!ValidacaoRegex.verificarEmail(email)) {

                request.setAttribute("mensagem", "Email inválido.");

                request.getRequestDispatcher("/WEB-INF/views/autenticacao/cadastro.jsp")
                        .forward(request, response);
                return;
            }

            if (!ValidacaoRegex.verificarSenha(senha)) {

                request.setAttribute("mensagem", "Senha inválida.");

                request.getRequestDispatcher("/WEB-INF/views/autenticacao/cadastro.jsp")
                        .forward(request, response);
                return;
            }

            String senhaHash = HashSenha.gerarHash(senha);

            Aluno aluno = new Aluno();

            aluno.setCpf(cpf);
            aluno.setEmail(email);
            aluno.setSenha(senhaHash);

            int retorno = alunoDAO.inserir(aluno);

            if (retorno > 0) {

                RequestDispatcher dispatcher =
                        request.getRequestDispatcher("/WEB-INF/views/aluno/agenda.jsp");

                dispatcher.forward(request, response);

            } else {

                request.setAttribute("mensagem", "Erro ao cadastrar aluno.");

                request.getRequestDispatcher("/WEB-INF/views/autenticacao/cadastro.jsp")
                        .forward(request, response);
            }

        } catch (Exception e) {

            e.printStackTrace();

            request.setAttribute("mensagem", "Erro interno do servidor.");

            request.getRequestDispatcher("/WEB-INF/views/autenticacao/cadastro.jsp")
                    .forward(request, response);
        }
    }
}
