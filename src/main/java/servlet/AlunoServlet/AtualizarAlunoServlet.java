package servlet.AlunoServlet;

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

@WebServlet("/aluno-update")
public class AtualizarAlunoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String matriculaParametro = request.getParameter("matricula");

        if (matriculaParametro == null || matriculaParametro.isEmpty()) {
            request.getSession().setAttribute("mensagem", "Matrícula não informada.");
            response.sendRedirect(request.getContextPath() + "/alunos");
            return;
        }

        try {

            int matricula = Integer.parseInt(matriculaParametro);

            AlunoDAO dao = new AlunoDAO();
            Aluno aluno = dao.buscarPorMatricula(matricula);

            if (aluno == null) {
                request.getSession().setAttribute("mensagem", "Aluno não encontrado.");
                response.sendRedirect(request.getContextPath() + "/alunos");
                return;
            }

            request.setAttribute("alunoParaEditar", aluno);

            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("/WEB-INF/view/Aluno/atualizarAluno.jsp");

            dispatcher.forward(request, response);

        } catch (Exception e) {
            request.getSession().setAttribute("mensagem", "Erro ao carregar aluno.");
            response.sendRedirect(request.getContextPath() + "/alunos");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String matriculaParametro = request.getParameter("matricula");
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String dataParametro = request.getParameter("dataInicio");

        AlunoDAO dao = new AlunoDAO();

        try {

            if (matriculaParametro == null || nome == null || cpf == null ||
                    email == null || senha == null || dataParametro == null ||
                    nome.isBlank() || cpf.isBlank() || email.isBlank() || senha.isBlank()) {

                request.getSession().setAttribute("mensagem", "Campos obrigatórios não preenchidos.");

            } else if (!ValidacaoRegex.verificarEmail(email)) {

                request.getSession().setAttribute("mensagem", "Email inválido.");

            } else if (!ValidacaoRegex.verificarSenha(senha)) {

                request.getSession().setAttribute("mensagem", "Senha inválida.");

            } else {

                int matricula = Integer.parseInt(matriculaParametro);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                java.util.Date data = sdf.parse(dataParametro);

                Aluno aluno = new Aluno(matricula, nome, cpf, data, email, senha);

                if (dao.atualizar(aluno) > 0) {
                    request.getSession().setAttribute("mensagem", "Aluno atualizado com sucesso.");
                } else {
                    request.getSession().setAttribute("mensagem", "Erro ao atualizar aluno.");
                }
            }

        } catch (Exception e) {
            request.getSession().setAttribute("mensagem", "Erro ao atualizar aluno.");
        }

        response.sendRedirect(request.getContextPath() + "/alunos");
    }
}
