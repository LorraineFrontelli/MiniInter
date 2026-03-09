package websocket;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import dao.MensagemDAO;
import model.Mensagem;

import jakarta.websocket.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/chat/{idUsuario}/{tipoUsuario}")
public class ChatWebSocket {

    // ─── Mapa estático: compartilhado entre TODAS as instâncias da classe ────────
    // Chave  → "idUsuario_tipoUsuario"   ex: "1_ALUNO" | "3_PROFESSOR"
    // Valor  → Session aberta do WebSocket
    private static final Map<String, Session> sessoes = new ConcurrentHashMap<>();

    // =========================================================
    // CONEXÃO ABERTA — registra o usuário no mapa de sessões
    // =========================================================
    @OnOpen
    public void onOpen(Session session,
                       @PathParam("idUsuario") String idUsuario,
                       @PathParam("tipoUsuario") String tipoUsuario) {

        String chave = gerarChave(idUsuario, tipoUsuario);
        sessoes.put(chave, session);

        System.out.println("[WS OPEN] " + chave + " conectado. Total online: " + sessoes.size());
    }

    // =========================================================
    // MENSAGEM RECEBIDA — salva no banco e entrega ao destinatário
    // =========================================================
    @OnMessage
    public void onMessage(String payload,
                          Session session,
                          @PathParam("idUsuario") String idUsuario,
                          @PathParam("tipoUsuario") String tipoUsuario) {

        try {
            JsonObject json = JsonParser.parseString(payload).getAsJsonObject();

            int    idRemetente      = Integer.parseInt(idUsuario);
            String tipoRemetente    = tipoUsuario;
            int    idDestinatario   = json.get("idDestinatario").getAsInt();
            String tipoDestinatario = json.get("tipoDestinatario").getAsString();
            String texto            = json.get("mensagem").getAsString();

            Mensagem mensagem = new Mensagem();
            mensagem.setIdRemetente(idRemetente);
            mensagem.setTipoRemetente(tipoRemetente);
            mensagem.setIdDestinatario(idDestinatario);
            mensagem.setTipoDestinatario(tipoDestinatario);
            mensagem.setMensagem(texto);
            mensagem.setDataMensagem(Timestamp.from(Instant.now()));
            mensagem.setLida(false);

            MensagemDAO dao = new MensagemDAO();
            int idGerado = dao.inserir(mensagem);

            if (idGerado == -1) {
                enviarErro(session, "Erro ao salvar mensagem no banco.");
                return;
            }

            JsonObject resposta = new JsonObject();
            resposta.addProperty("id",               idGerado);
            resposta.addProperty("idRemetente",      idRemetente);
            resposta.addProperty("tipoRemetente",    tipoRemetente);
            resposta.addProperty("idDestinatario",   idDestinatario);
            resposta.addProperty("tipoDestinatario", tipoDestinatario);
            resposta.addProperty("mensagem",         texto);
            resposta.addProperty("dtMensagem",       mensagem.getDataMensagem().toString());
            resposta.addProperty("lida",             false);

            String respostaJson = resposta.toString();

            // Entrega ao destinatário se estiver online
            String  chaveDestinatario  = gerarChave(String.valueOf(idDestinatario), tipoDestinatario);
            Session sessaoDestinatario = sessoes.get(chaveDestinatario);

            System.out.println("[WS] Sessões ativas: " + sessoes.keySet());

            if (sessaoDestinatario != null && sessaoDestinatario.isOpen()) {
                sessaoDestinatario.getBasicRemote().sendText(respostaJson);
                System.out.println("[WS] Entregue ao destinatário: " + chaveDestinatario);
            }

            // Confirma ao remetente
            session.getBasicRemote().sendText(respostaJson);

            System.out.println("[WS MESSAGE] " + gerarChave(idUsuario, tipoUsuario)
                    + " → " + chaveDestinatario + ": " + texto);

        } catch (Exception e) {
            e.printStackTrace();
            enviarErro(session, "Erro ao processar mensagem.");
        }
    }

    // =========================================================
    // CONEXÃO FECHADA — remove do mapa de sessões
    // =========================================================
    @OnClose
    public void onClose(Session session,
                        @PathParam("idUsuario") String idUsuario,
                        @PathParam("tipoUsuario") String tipoUsuario) {

        String chave = gerarChave(idUsuario, tipoUsuario);
        sessoes.remove(chave);

        System.out.println("[WS CLOSE] " + chave + " desconectado. Total online: " + sessoes.size());
    }

    // =========================================================
    // ERRO NA CONEXÃO — loga e remove do mapa
    // =========================================================
    @OnError
    public void onError(Session session,
                        Throwable throwable,
                        @PathParam("idUsuario") String idUsuario,
                        @PathParam("tipoUsuario") String tipoUsuario) {

        String chave = gerarChave(idUsuario, tipoUsuario);
        System.err.println("[WS ERROR] " + chave + " → " + throwable.getMessage());
        sessoes.remove(chave);
    }

    // =========================================================
    // HELPERS
    // =========================================================

    // Gera a chave do mapa: "1_ALUNO", "3_PROFESSOR", etc.
    private String gerarChave(String idUsuario, String tipoUsuario) {
        return idUsuario + "_" + tipoUsuario;
    }

    // Envia um JSON de erro para a sessão que causou o problema
    private void enviarErro(Session session, String motivo) {
        try {
            JsonObject erro = new JsonObject();
            erro.addProperty("erro", motivo);
            session.getBasicRemote().sendText(erro.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}