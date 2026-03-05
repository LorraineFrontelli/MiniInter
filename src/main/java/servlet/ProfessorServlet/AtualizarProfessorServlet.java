package servlet.ProfessorServlet;

import java.io.IOException;
import java.time.LocalDate;

import dao.ProfessorDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Professor;
import utils.ValidacaoRegex;

@WebServlet("/professor-update")
public class AtualizarProfessorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        Professor professorSession = (Professor) session.getAttribute("usuario");

        int id = professorSession.getId();
        String nome = professorSession.getNome();
        LocalDate dataContratacao = professorSession.getDataContratacao();
        String email = professorSession.getEmail();
        String senha = professorSession.getSenha();
        String materia = professorSession.getMateria();
        String usuario = professorSession.getUsuario();

        String nomeForm = request.getParameter("nome");
        String emailForm = request.getParameter("email");
        String senhaForm = request.getParameter("senha");
        String materiaForm = request.getParameter("materia");
        String usuarioForm = request.getParameter("usuario");
        String dataForm = request.getParameter("dataContratacao");

        System.out.println("email JSP : " + email);

        if (nomeForm != null && !nomeForm.isBlank()) {
            nome = nomeForm;
        }

        if (emailForm != null && !emailForm.isBlank()) {

            if (!ValidacaoRegex.verificarEmail(emailForm)) {
                session.setAttribute("mensagem", "Email inválido.");
                response.sendRedirect(request.getContextPath() + "/professores?page=perfil-professor");
                return;
            }

            email = emailForm;
        }

        if (senhaForm != null && !senhaForm.isBlank()) {

            if (!ValidacaoRegex.verificarSenha(senhaForm)) {
                session.setAttribute("mensagem", "Senha inválida.");
                response.sendRedirect(request.getContextPath() + "/professores?page=perfil-professor");
                return;
            }

            senha = senhaForm;
        }

        if (materiaForm != null && !materiaForm.isBlank()) {
            materia = materiaForm;
        }

        if (usuarioForm != null && !usuarioForm.isBlank()) {
            usuario = usuarioForm;
        }

        if (dataForm != null && !dataForm.isBlank()) {
            dataContratacao = LocalDate.parse(dataForm);
        }

        ProfessorDAO dao = new ProfessorDAO();

        try {

            if (nome == null || email == null ||
                    senha == null || materia == null || usuario == null ||
                    dataContratacao == null || nome.isBlank() || email.isBlank()) {

                request.getSession().setAttribute("mensagem", "Campos obrigatórios não preenchidos.");

            } else {


                Professor professor = new Professor(id, nome, dataContratacao, email, senha, materia, usuario);

                if (dao.atualizar(professor) > 0) {
                    request.getSession().setAttribute("mensagem", "Professor atualizado.");
                    session.setAttribute("usuario", professor);
                    System.out.println("erro 4");
                } else {
                    request.getSession().setAttribute("mensagem", "Erro ao atualizar professor.");
                    System.out.println("erro 5");
                }
            }

        } catch (Exception e) {
            request.getSession().setAttribute("mensagem", "Erro ao atualizar professor.");
            System.out.println("erro 6");
        }

        response.sendRedirect(request.getContextPath() + "/professores?page=perfil-professor");
    }
}
