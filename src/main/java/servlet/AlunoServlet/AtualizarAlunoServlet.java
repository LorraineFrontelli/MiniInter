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
import java.time.LocalDate;
import java.util.Date;

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
                    request.getRequestDispatcher("/WEB-INF/views/Aluno/atualizarAluno.jsp");

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
        String nomeParametro = request.getParameter("nome");
        String cpfParametro = request.getParameter("cpf");
        String emailParametro = request.getParameter("email");
        String senhaParametro = request.getParameter("senha");
        String dataParametro = request.getParameter("dataInicio");

        AlunoDAO dao = new AlunoDAO();
        Aluno aluno = dao.buscarPorMatricula(Integer.parseInt(matriculaParametro));

        int matricula = aluno.getMatricula();
        String nome = aluno.getNome();
        String cpf = aluno.getCpf();
        String email = aluno.getEmail();
        String senha = aluno.getSenha();
        Date data = aluno.getDataInicio();

        try {
            if(nomeParametro!=null && !nomeParametro.isEmpty()){
                nome = nomeParametro;
            }
            if(cpfParametro!=null && !cpfParametro.isEmpty()){
                cpf = cpfParametro;
            }
            if(emailParametro!=null && !emailParametro.isEmpty()){
                if(ValidacaoRegex.verificarEmail(emailParametro)) {
                    email = emailParametro;
                } else{
                    request.getSession().setAttribute("mensagem", "Senha inválida.");
                }
            }
            if(senhaParametro!=null && !senhaParametro.isEmpty()){
                if(ValidacaoRegex.verificarSenha(senhaParametro)) {
                    senha = senhaParametro;
                } else{
                    request.getSession().setAttribute("mensagem", "Senha inválida.");
                }
            }

            if(dataParametro!=null  && !dataParametro.isEmpty()){
                LocalDate localDate = LocalDate.parse(dataParametro);
                data = java.sql.Date.valueOf(localDate);
            }
                aluno = new Aluno(matricula, nome, cpf, data, email, senha);

                if (dao.atualizar(aluno) > 0) {
                    request.getSession().setAttribute("mensagem", "Aluno atualizado com sucesso.");
                } else {
                    request.getSession().setAttribute("mensagem", "Erro ao atualizar aluno.");
                }
        } catch (Exception e) {
            request.getSession().setAttribute("mensagem", "Erro ao atualizar aluno.");
        }
        response.sendRedirect(request.getContextPath() + "/alunos");
    }
}
