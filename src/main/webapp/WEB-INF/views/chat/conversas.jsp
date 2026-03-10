<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Aplicando tema salvo -->
    <script>document.documentElement.style.setProperty("--tema",localStorage.getItem("corTema")||"#242021");</script>
 
    <!-- Preloads -->
    <link rel="preload" as="image" href="${pageContext.request.contextPath}/assets/img/painting-back-icon.svg">
    <link rel="preload" as="image" href="${pageContext.request.contextPath}/assets/img/profile-icon.svg">
    <link rel="preload" as="image" href="${pageContext.request.contextPath}/assets/img/search-icon.svg">
    <link rel="preload" as="image" href="${pageContext.request.contextPath}/assets/img/reminder-icon.svg">
    <link rel="preload" as="image" href="${pageContext.request.contextPath}/assets/img/statistics-icon.svg">

    <!-- Links -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/global.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/tokens.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/layout/conversas.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico">
    <script type="module" src="${pageContext.request.contextPath}/assets/js/script.js" defer></script>

    <title>Conversas - Monart</title>
</head>

<body>
<main>
    <div class="cabecalhoPaginas">
        <img src="${pageContext.request.contextPath}/assets/img/painting-back-icon.svg"
             alt="Ícone de voltar" onclick="history.back()" class="pincelVoltar">



        <div class="buscarAlunos">
            <search>
                <form action="${pageContext.request.contextPath}/mensagens" method="get">
                    <input type="hidden" name="idRemetente"  value="${sessionScope.usuario.id}">
                    <input type="hidden" name="tipoRemetente" value="${sessionScope.tipoUsuario}">
                    <input type="text"   name="nomeFiltro" class="buscar" placeholder="Procurar conversas"
                           value="${param.nomeFiltro}">
                </form>
            </search>
            <img src="${pageContext.request.contextPath}/assets/img/search-icon.svg" alt="">
        </div>
        <img src="${pageContext.request.contextPath}/assets/img/arrow-icon.svg"
             alt="Nova conversa"
             onclick="window.location.href='${pageContext.request.contextPath}/mensagem-create'"
             class="novaConversa">
    </div>

    <c:if test="${not empty mensagem}">
        <p>${mensagem}</p>
    </c:if>

    <div class="buscaContainer">
        <c:choose>
            <c:when test="${not empty mensagens}">
                <c:forEach items="${mensagens}" var="m">
                    <div class="resultadoBusca"
                         onclick="window.location.href='${pageContext.request.contextPath}/mensagens?idRemetente=${sessionScope.usuario.id}&tipoRemetente=${sessionScope.tipoUsuario}&idDestinatario=${m.idDestinatario}&tipoDestinatario=${m.tipoDestinatario}'">

                        <div>
                            <strong>${m.nome}</strong>

                            <c:if test="${m.temNaoLidas}">
                                <span class="badgeNaoLida">🔴</span>
                            </c:if>

                            <br>

                            <time datetime="<fmt:formatDate value='${m.dataUltimaMensagem}' pattern='yyyy-MM-dd HH:mm'/>">
                                <fmt:formatDate value="${m.dataUltimaMensagem}" pattern="dd/MM/yyyy HH:mm"/>
                            </time>
                        </div>

                            <img src="${pageContext.request.contextPath}/assets/img/arrow-icon.svg" alt="Ver conversa">

                    </div>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <p>Nenhuma conversa encontrada.</p>
            </c:otherwise>
        </c:choose>
    </div>
</main>
</body>
<script>
    setInterval(function() {
    fetch('${pageContext.request.contextPath}/mensagens?idRemetente=${sessionScope.usuario.id}&tipoRemetente=${sessionScope.tipoUsuario}')
        .then(response => response.text())
        .then(html => {
            // atualiza só o container de conversas
            let parser = new DOMParser();
            let doc = parser.parseFromString(html, 'text/html');
            document.querySelector('.buscaContainer').innerHTML =
                doc.querySelector('.buscaContainer').innerHTML;
        });
}, 5000);</script>
</html>