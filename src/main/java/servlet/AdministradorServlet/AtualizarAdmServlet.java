package servlet.AdministradorServlet;

import dao.AdministradorDAO;
import model.Administrador;
import utils.ValidacaoRegex;
import utils.HashSenha;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;

@WebServlet("/admin-update")
@MultipartConfig
public class AtualizarAdmServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idParametro = request.getParameter("id");
        AdministradorDAO dao = new AdministradorDAO();

        try {

            if (idParametro == null || idParametro.isBlank()) {

                request.getSession().setAttribute("mensagem", "ID inválido.");
                response.sendRedirect(request.getContextPath() + "/administradores");
                return;
            }

            int id = Integer.parseInt(idParametro);

            String login = request.getParameter("login");
            String senha = request.getParameter("senha");

            if (login == null || login.isBlank() || senha == null || senha.isBlank()) {

                request.getSession().setAttribute("mensagem", "Campos obrigatórios não preenchidos.");
                response.sendRedirect(request.getContextPath() + "/administradores");
                return;
            }

            if (!ValidacaoRegex.verificarEmail(login)) {

                request.getSession().setAttribute("mensagem", "Email inválido.");
                response.sendRedirect(request.getContextPath() + "/administradores");
                return;
            }

            if (!ValidacaoRegex.verificarSenha(senha)) {

                request.getSession().setAttribute("mensagem", "Senha inválida.");
                response.sendRedirect(request.getContextPath() + "/administradores");
                return;
            }

            String senhaHash = HashSenha.gerarHash(senha);

            Administrador admin = new Administrador();
            admin.setId(id);
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

            if (dao.atualizar(admin) > 0) {

                request.getSession().setAttribute("mensagem", "Administrador atualizado.");

            } else {

                request.getSession().setAttribute("mensagem", "Erro ao atualizar administrador.");
            }

        } catch (Exception e) {

            e.printStackTrace();
            request.getSession().setAttribute("mensagem", "Erro ao atualizar administrador.");
        }

        response.sendRedirect(request.getContextPath() + "/administradores");
    }
}
