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


    // map que registra todos os usuarios logados(instancia de classe)
    private static final Map<String, Session> sessoes = new ConcurrentHashMap<>();

    // abre conexão e registra o usuario no map
    @OnOpen
    public void onOpen(Session session,
                       @PathParam("idUsuario") String idUsuario,
                       @PathParam("tipoUsuario") String tipoUsuario) {

        String chave = gerarChave(idUsuario, tipoUsuario);
        sessoes.put(chave, session);

        System.out.println("[WS OPEN] " + chave + " conectado. Total online: " + sessoes.size());
    }

    @OnMessage
    public void onMessage(String payload,
                          Session session,
                          @PathParam("idUsuario") String idUsuario,
                          @PathParam("tipoUsuario") String tipoUsuario) {

        try {
            // transforma a String em JSON
            JsonObject json = JsonParser.parseString(payload).getAsJsonObject();

            // Pega as variaveis da URL e do JSON
            int    idRemetente      = Integer.parseInt(idUsuario);
            String tipoRemetente    = tipoUsuario;
            int    idDestinatario   = json.get("idDestinatario").getAsInt();
            String tipoDestinatario = json.get("tipoDestinatario").getAsString();
            String texto            = json.get("mensagem").getAsString();

            //Cria um objeto Mensagem com os dados acima
            Mensagem mensagem = new Mensagem();
            mensagem.setIdRemetente(idRemetente);
            mensagem.setTipoRemetente(tipoRemetente);
            mensagem.setIdDestinatario(idDestinatario);
            mensagem.setTipoDestinatario(tipoDestinatario);
            mensagem.setMensagem(texto);
            mensagem.setDataMensagem(Timestamp.from(Instant.now()));
            mensagem.setLida(false);

            // Insere a mensagem no BD
            MensagemDAO dao = new MensagemDAO();
            int idGerado = dao.inserir(mensagem);

            if (idGerado == -1) {
                enviarErro(session, "Erro ao salvar mensagem no banco.");
                return;
            }

            // Prepara um JSON que é a resposta a quem enviou para a mensagem aparecer na tela
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

            // marca com lida se o destinatario estiver online
            if (sessaoDestinatario != null && sessaoDestinatario.isOpen()) {
                sessaoDestinatario.getBasicRemote().sendText(respostaJson);
                System.out.println("[WS] Entregue ao destinatário: " + chaveDestinatario);
                dao.atualizarLidas(idRemetente, tipoRemetente, idDestinatario, tipoDestinatario);
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

    // remove usuario do map e fecha conexão
    @OnClose
    public void onClose(Session session,
                        @PathParam("idUsuario") String idUsuario,
                        @PathParam("tipoUsuario") String tipoUsuario) {

        String chave = gerarChave(idUsuario, tipoUsuario);
        sessoes.remove(chave);

        System.out.println("[WS CLOSE] " + chave + " desconectado. Total online: " + sessoes.size());
    }

    // caso aconteça erro na conexão, acisa ao usuario e remove ele da sessão
    @OnError
    public void onError(Session session,
                        Throwable throwable,
                        @PathParam("idUsuario") String idUsuario,
                        @PathParam("tipoUsuario") String tipoUsuario) {

        String chave = gerarChave(idUsuario, tipoUsuario);
        System.err.println("[WS ERROR] " + chave + " → " + throwable.getMessage());
        sessoes.remove(chave);
    }


    // gera a chave do map (1_ALUNO/34_PROFESSOR)
    private String gerarChave(String idUsuario, String tipoUsuario) {
        return idUsuario + "_" + tipoUsuario;
    }

    // Envia um JSON de erro para a sessão
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