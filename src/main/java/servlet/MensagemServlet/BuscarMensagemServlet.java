package servlet.MensagemServlet;

import dao.MensagemDAO;
import model.Mensagem;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/mensagens")
public class BuscarMensagemServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String mensagem = (String) session.getAttribute("mensagem");

        if (mensagem != null) {
            request.setAttribute("mensagem", mensagem);
            session.removeAttribute("mensagem");
        }

        String idRemetenteParam = request.getParameter("idRemetente");
        String tipoRemetente = request.getParameter("tipoRemetente");
        String idDestinatarioParam = request.getParameter("idDestinatario");
        String tipoDestinatario = request.getParameter("tipoDestinatario");
        String nomeFiltro = request.getParameter("nomeFiltro");

        MensagemDAO mensagemDAO = new MensagemDAO();
        List<Mensagem> mensagens = new ArrayList<>();

        try {
            if(idDestinatarioParam != null && !idDestinatarioParam.trim().isEmpty() &&
            tipoDestinatario != null && !tipoDestinatario.trim().isEmpty() &&
            tipoRemetente != null && !tipoRemetente.trim().isEmpty() &&
            idRemetenteParam != null && !idRemetenteParam.trim().isEmpty()) {

                int idDestinatario = Integer.parseInt(idDestinatarioParam);
                int idRemetente = Integer.parseInt(idRemetenteParam);

                mensagens = mensagemDAO.listarConversa(idRemetente, tipoRemetente, idDestinatario, tipoDestinatario);

                if (!mensagens.isEmpty()) {
                    request.setAttribute("mensagem", "Conversa encontrada com sucesso!");
                } else{
                    request.setAttribute("mensagem", "Conversa não encontrada!");
                }
                request.setAttribute("idRemetente", idRemetente);
                request.setAttribute("tipoRemetente", tipoRemetente);
                request.setAttribute("idDestinatario", idDestinatario);
                request.setAttribute("tipoDestinatario", tipoDestinatario);
                request.setAttribute("mensagens", mensagens);

                request.getRequestDispatcher("/WEB-INF/views/chat/chat.jsp").forward(request, response);
                return;

            } else if(idRemetenteParam != null && !idRemetenteParam.trim().isEmpty() &&
            tipoRemetente != null && !tipoRemetente.trim().isEmpty() &&
            nomeFiltro != null && !nomeFiltro.trim().isEmpty()) {

                int idRemetente = Integer.parseInt(idRemetenteParam);

                mensagens = mensagemDAO.listarPorNome(idRemetente, tipoRemetente, nomeFiltro);

                if (!mensagens.isEmpty()) {
                    request.setAttribute("mensagem", "Conversas encontradas com sucesso!");
                } else{
                    request.setAttribute("mensagem", "Nenhuma conversa encontrada!");
                }

            }else if(idRemetenteParam != null && !idRemetenteParam.trim().isEmpty() &&
                    tipoRemetente != null && !tipoRemetente.trim().isEmpty()) {

                int idRemetente = Integer.parseInt(idRemetenteParam);

                mensagens = mensagemDAO.listarHistorico(idRemetente, tipoRemetente);

                if (!mensagens.isEmpty()) {
                    request.setAttribute("mensagem", "Conversas encontradas com sucesso!");
                } else{
                    request.setAttribute("mensagem", "Nenhuma conversa encontrada!");
                }

            } else{
                request.setAttribute("mensagem", "Nenhuma usuário na sessão.!");
            }

        } catch (NumberFormatException e) {
            request.setAttribute("mensagem", "IDs inválidos.");
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", "Erro ao buscar mensagens.");
        }

        request.setAttribute("mensagens", mensagens);

        request.getRequestDispatcher("/WEB-INF/views/chat/conversas.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
