<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="pt-BR">
    
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <script>document.documentElement.style.setProperty("--tema", localStorage.getItem("corTema") || "#FF7E7E");</script>

    <link rel="preload" as="image" href="${pageContext.request.contextPath}/assets/img/painting-back-icon.svg">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/global.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/tokens.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/layout/chat.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico">
    <script type="module" src="${pageContext.request.contextPath}/assets/js/script.js"></script>

    <title>Chat - Monart</title>
</head>

<body>
    <main>
        <div class="cabecalhoPaginas">
            <img src="${pageContext.request.contextPath}/assets/img/painting-back-icon.svg"
                 alt="Ícone de voltar" onclick="location.href=document.referrer" class="pincelVoltar">
    
            <div class="tituloPaginas">
                <c:set var="nomeContato" value="Conversa"/>
                <c:forEach items="${mensagens}" var="m">
                    <c:if test="${m.idRemetente != sessionScope.usuario.id || m.tipoRemetente != sessionScope.tipoUsuario}">
                        <c:set var="nomeContato" value="${m.nome}"/>
                    </c:if>
                </c:forEach>
                <h1>${nomeContato}</h1>
            </div>
    
            <img src="${pageContext.request.contextPath}/assets/img/themes-icon.svg" alt="" class="abrirTemas">
        </div>

        <section class="fundoChat">
            <ul class="conversa" id="listaConversa">

                <c:forEach items="${mensagens}" var="m">
                    <c:choose>
                        <c:when test="${m.idRemetente == sessionScope.usuario.id && m.tipoRemetente == sessionScope.tipoUsuario}">
                            <li class="mensagem enviada">
                                <p>${m.mensagem}</p>
                                <time datetime="<fmt:formatDate value='${m.dataMensagem}' pattern='yyyy-MM-dd HH:mm'/>">
                                    <fmt:formatDate value="${m.dataMensagem}" pattern="HH:mm"/>
                                </time>
                            </li>
                        </c:when>
                        <c:otherwise>
                            <li class="mensagem recebida">
                                <p>${m.mensagem}</p>
                                <time datetime="<fmt:formatDate value='${m.dataMensagem}' pattern='yyyy-MM-dd HH:mm'/>">
                                    <fmt:formatDate value="${m.dataMensagem}" pattern="HH:mm"/>
                                </time>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

            </ul>

            <div class="barraMensagem">
                <input type="text" id="inputMensagem" class="escreverMensagem"
                    placeholder="Digite sua mensagem..." autocomplete="off">
                <button type="button" class="enviar" id="btnEnviar">
                    <img src="${pageContext.request.contextPath}/assets/img/send-icon.svg" alt="Enviar">
                </button>
            </div>
        </section>
    </main>

    <script>
        // pegando os dados da sessão e da URL
        const idUsuario        = '${sessionScope.usuario.id}';
        const tipoUsuario      = '${sessionScope.tipoUsuario}';
        const idDestinatario   = '${param.idDestinatario}';
        const tipoDestinatario = '${param.tipoDestinatario}';

        // URL do WebSocket local
        const wsUrl = 'ws://' + window.location.host + '${pageContext.request.contextPath}/chat/' + idUsuario + '/' + tipoUsuario;

        // Abrindo a conexão
        const ws = new WebSocket(wsUrl);

        // mostrando no console a conexão e rolando para o fim da lista de mesagens
        ws.onopen = function () {
            console.log('[WS] Conectado como ' + idUsuario + ' (' + tipoUsuario + ')');
            rolarParaBaixo();
        };

    // dispara sempre que uma nova mensagem é recebida do servidor
    ws.onmessage = function (event) {
        console.log('[WS] Mensagem recebida raw:', event.data);

            // onmessage do servidor envia um JSON e é transformado para um objeto
            const dados = JSON.parse(event.data);

            // impede de mostrar mensagens de erro do servidor ao usuario
            if (dados.erro) {
                console.warn('[WS] Erro do servidor:', dados.erro);
                return;
            }

            // descobre se é mensagem do usuario ou não
            const ehPropriaMsg = String(dados.idRemetente) === String(idUsuario)
                && dados.tipoRemetente === tipoUsuario;

            adicionarMensagem(dados.mensagem, dados.dtMensagem, ehPropriaMsg);
        };

        // fecha conexão
        ws.onclose = function () {
            console.log('[WS] Conexão encerrada.');
        };

        // mostra erro caso aconteça
        ws.onerror = function (err) {
            console.error('[WS] Erro na conexão:', err);
        };

        // envio de mensagens

        // botão de enviar mensagem chama a função de enviar
        document.getElementById('btnEnviar').addEventListener('click', enviar);

        function enviar() {
            // pega o texto digitado
            const input = document.getElementById('inputMensagem');
            const texto = input.value;

            // se não existir texto, nada acontece
            if (!texto) return;

            // avisa ao usuario caso a conexão não esteja ativa
            if (ws.readyState !== WebSocket.OPEN) {
                alert('Conexão perdida. Recarregue a página.');
                return;
            }

            // ADICIONAR: desabilita botão enquanto envia
            const btn = document.getElementById('btnEnviar');
            btn.disabled = true;
            btn.classList.add('enviando');

            // Monta o objeto JS e converte para string JSON para ser enviado ao servidor
            const payload = JSON.stringify({
                idDestinatario:   idDestinatario,
                tipoDestinatario: tipoDestinatario,
                mensagem:         texto
            });

            // Envia o JSON ao servidor
            ws.send(payload);
            input.value = '';

            // ADICIONAR: restaura botão após envio
            setTimeout(() => {
                btn.disabled = false;
                btn.classList.remove('enviando');
            }, 500);
        }
        function adicionarMensagem(texto, dtMensagem, ehPropriaMsg) {
            // pega <ul> de mensagens
            const lista = document.getElementById('listaConversa');

            //cria o <li> da mensagem
            const li = document.createElement('li');

            // 'enviada' = direita (quem está logado)
            // 'recebida' = esquerda (o contato)
            li.classList.add('mensagem', ehPropriaMsg ? 'enviada' : 'recebida');

            // formatação de data
            const hora = dtMensagem
                ? new Date(dtMensagem).toLocaleTimeString('pt-BR', { hour: '2-digit', minute: '2-digit' })
                : new Date().toLocaleTimeString('pt-BR', { hour: '2-digit', minute: '2-digit' });

            // monta o html dentro do <li>
            li.innerHTML = '<p>' + escaparHtml(texto) + '</p>'
                + '<time>' + hora + '</time>';

            // insere o li e volta para mostrar a nova mensagem adicionda
            lista.appendChild(li);
            rolarParaBaixo();
        }

        // força o scroll a ir até o fim
        function rolarParaBaixo() {
            const lista = document.getElementById('listaConversa');
            lista.scrollTop = lista.scrollHeight;
        }

        // impede arquivos maliciosos de serem rodados
        function escaparHtml(texto) {
            const div = document.createElement('div');
            div.appendChild(document.createTextNode(texto));
            return div.innerHTML;
        }

        inputMensagem.addEventListener('keydown', function (e) {
            if (e.key === 'Enter' && !e.shiftKey) {
                e.preventDefault();
                enviar();
            }
        });
    </script>

</body>
</html>
