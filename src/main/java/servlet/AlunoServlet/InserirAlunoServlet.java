package servlet.AlunoServlet;

import dao.AdministradorDAO;
import dao.AlunoDAO;
import model.Aluno;
import utils.ValidacaoRegex;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/aluno-create")
public class InserirAlunoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/view/Aluno/verificarCpf.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");

        if ("verificarCpf".equals(acao)) {

            String cpf = request.getParameter("cpf");
            AdministradorDAO admDAO = new AdministradorDAO();

            try {

                if (cpf == null || cpf.isBlank()) {

                    request.setAttribute("mensagem", "Digite um CPF.");
                    request.getRequestDispatcher("/WEB-INF/view/Aluno/verificarCpf.jsp")
                            .forward(request, response);
                    return;
                }

                boolean existe = admDAO.cpfExiste(cpf);

                if (!existe) {

                    request.setAttribute("mensagem", "CPF não autorizado.");
                    request.getRequestDispatcher("/WEB-INF/view/Aluno/verificarCpf.jsp")
                            .forward(request, response);
                    return;
                }

                request.setAttribute("cpf", cpf);

                RequestDispatcher dispatcher =
                        request.getRequestDispatcher("/WEB-INF/view/Aluno/cadastrarAluno.jsp");

                dispatcher.forward(request, response);

            } catch (Exception e) {

                e.printStackTrace();
                request.setAttribute("mensagem", "Erro ao verificar CPF.");
                request.getRequestDispatcher("/WEB-INF/view/Aluno/verificarCpf.jsp")
                        .forward(request, response);
            }

        } else if ("cadastrar".equals(acao)) {

            String mensagem = null;
            AlunoDAO dao = new AlunoDAO();

            try {

                String matriculaStr = request.getParameter("matricula");
                String nome = request.getParameter("nome");
                String cpf = request.getParameter("cpf");
                String data = request.getParameter("dataInicio");
                String email = request.getParameter("email");
                String senha = request.getParameter("senha");

                if (nome == null || nome.isBlank() ||
                        email == null || email.isBlank() ||
                        senha == null || senha.isBlank()) {

                    mensagem = "Campos obrigatórios não preenchidos!";

                } else if (!ValidacaoRegex.verificarEmail(email)) {

                    mensagem = "Email inválido!";

                } else if (!ValidacaoRegex.verificarSenha(senha)) {

                    mensagem = "Senha inválida!";
                }

                if (mensagem != null) {

                    request.setAttribute("mensagem", mensagem);
                    request.getRequestDispatcher("/WEB-INF/view/Aluno/cadastrarAluno.jsp")
                            .forward(request, response);
                    return;
                }

                int matricula = Integer.parseInt(matriculaStr);
                Date dataInicio = new SimpleDateFormat("yyyy-MM-dd").parse(data);

                Aluno aluno = new Aluno();
                aluno.setMatricula(matricula);
                aluno.setNome(nome);
                aluno.setCpf(cpf);
                aluno.setDataInicio(dataInicio);
                aluno.setEmail(email);
                aluno.setSenha(senha);

                int retorno = dao.inserir(aluno);

                if (retorno > 0) {
                    mensagem = "Aluno cadastrado com sucesso!";
                } else {
                    mensagem = "Erro ao cadastrar aluno.";
                }

                request.getSession().setAttribute("mensagem", mensagem);
                response.sendRedirect(request.getContextPath() + "/alunos");

            } catch (Exception e) {

                e.printStackTrace();
                request.getSession().setAttribute("mensagem", "Erro ao cadastrar aluno!");
                response.sendRedirect(request.getContextPath() + "/alunos");
            }
        }
    }
}
