package servlet.AlunoServlet;

import dao.AlunoDAO;
import model.Aluno;
import dao.TelefoneDAO;
import model.Professor;
import model.Telefone;
import org.apache.poi.ss.formula.functions.T;
import utils.ValidacaoRegex;
import utils.HashSenha;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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

        HttpSession session = request.getSession();

        String matriculaParametro = request.getParameter("matricula");
        String tipo = (String) request.getSession().getAttribute("tipoUsuario");

        AlunoDAO dao = new AlunoDAO();
        Aluno aluno = dao.buscarPorMatricula(Integer.parseInt(matriculaParametro));
        int matricula = aluno.getMatricula();

        // ALUNO - atualiza só email e telefone
        if ("ALUNO".equalsIgnoreCase(tipo)) {

            String emailParametro = request.getParameter("email");
            String telefoneParametro = request.getParameter("telefone");

            try {

                if (emailParametro != null && !emailParametro.isBlank()) {

                    if (ValidacaoRegex.verificarEmail(emailParametro)) {

                        Aluno alunoAtualizar = new Aluno();
                        alunoAtualizar.setMatricula(matricula);
                        alunoAtualizar.setEmail(emailParametro);
                        dao.atualizarEmail(alunoAtualizar);

                        Aluno alunoLogado = (Aluno) request.getSession().getAttribute("usuario");
                        alunoLogado.setEmail(emailParametro);
                        request.getSession().setAttribute("usuario", alunoLogado);

                    } else {

                        request.getSession().setAttribute("mensagem", "Email inválido.");
                    }
                }

                if (telefoneParametro != null && !telefoneParametro.isBlank()) {

                    TelefoneDAO telefoneDAO = new TelefoneDAO();
                    telefoneDAO.atualizarPorIdAluno(matricula, telefoneParametro);
                }

                request.getSession().setAttribute("mensagem", "Dados atualizados com sucesso.");
                List<Telefone> telefonesAluno = (List<Telefone>) session.getAttribute("telefoneAluno");
                if (telefonesAluno != null && !telefonesAluno.isEmpty()) {
                    telefonesAluno.get(0).setNumero(telefoneParametro);
                    session.setAttribute("telefoneAluno", telefonesAluno);
                }

            } catch (Exception e) {

                e.printStackTrace();
                request.getSession().setAttribute("mensagem", "Erro ao atualizar dados.");
            }

            response.sendRedirect(request.getContextPath() + "/alunos?page=perfil-aluno");
            return;
        }

        // ADMIN - atualiza tudo
        String nomeParametro = request.getParameter("nome");
        String cpfParametro = request.getParameter("cpf");
        String emailParametro = request.getParameter("email");
        String senhaParametro = request.getParameter("senha");
        String dataParametro = request.getParameter("dataInicio");

        String nome = aluno.getNome();
        String cpf = aluno.getCpf();
        String email = aluno.getEmail();
        String senha = aluno.getSenha();
        Date data = aluno.getDataInicio();

        try {

            if (nomeParametro != null && !nomeParametro.isEmpty()) {
                nome = nomeParametro;
            }

            if (cpfParametro != null && !cpfParametro.isEmpty()) {
                cpf = cpfParametro;
            }

            if (emailParametro != null && !emailParametro.isEmpty()) {

                if (ValidacaoRegex.verificarEmail(emailParametro)) {
                    email = emailParametro;
                } else {
                    request.getSession().setAttribute("mensagem", "Email inválido.");
                }
            }

            if (senhaParametro != null && !senhaParametro.isEmpty()) {

                if (ValidacaoRegex.verificarSenha(senhaParametro)) {
                    senha = HashSenha.gerarHash(senhaParametro);
                } else {
                    request.getSession().setAttribute("mensagem", "Senha inválida.");
                }
            }

            if (dataParametro != null && !dataParametro.isEmpty()) {

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

            e.printStackTrace();
            request.getSession().setAttribute("mensagem", "Erro ao atualizar aluno.");
        }

        response.sendRedirect(request.getContextPath() + "/alunos");
    }
}