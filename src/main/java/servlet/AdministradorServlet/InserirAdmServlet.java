package servlet.AdministradorServlet;

import dao.AdministradorDAO;
import model.Administrador;
import utils.ValidacaoRegex;
import utils.HashSenha;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;

@WebServlet("/admin-create")
@MultipartConfig
public class InserirAdmServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/WEB-INF/views/administrador/tab-administrador.jsp");

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
                        request.getRequestDispatcher("/WEB-INF/views/administrador/tab-administrador.jsp");

                dispatcher.forward(request, response);
                return;
            }

            String senhaHash = HashSenha.gerarHash(senha);

            Administrador admin = new Administrador();
            admin.setLogin(login);
            admin.setSenha(senhaHash);

            Part arquivo = request.getPart("alunoCpf");

            if (arquivo != null && arquivo.getSize() > 0) {

                InputStream input = arquivo.getInputStream();

                Workbook workbook = new XSSFWorkbook(input);
                Sheet sheet = workbook.getSheetAt(0);

                for (Row row : sheet) {

                    Cell cell = row.getCell(0);

                    if (cell == null) continue;

                    String cpf = "";

                    if (cell.getCellType() == CellType.STRING) {
                        cpf = cell.getStringCellValue();
                    } else if (cell.getCellType() == CellType.NUMERIC) {
                        cpf = String.valueOf((long) cell.getNumericCellValue());
                    }

                    cpf = cpf.trim();
                    cpf = cpf.replaceAll("[^0-9]", "");

                    if (!cpf.isEmpty()) {
                        admin.adicionarCpf(cpf);
                    }
                }

                workbook.close();
            }

            int retorno = dao.inserir(admin);

            if (retorno > 0) {
                mensagem = "Administrador criado com sucesso!";
            } else {
                mensagem = "Erro ao inserir registro.";
            }

            request.getSession().setAttribute("mensagem", mensagem);

            response.sendRedirect(request.getContextPath() + "/administradores");

        } catch (Exception e) {

            e.printStackTrace();

            request.getSession().setAttribute("mensagem", "Erro ao inserir registro!");

            response.sendRedirect(request.getContextPath() + "/administradores");
        }
    }
}
