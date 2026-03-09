<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Aplicando tema salvo -->
    <script>document.documentElement.style.setProperty("--tema",localStorage.getItem("corTema")||"#FF7E7E");</script>

    <!-- Preloads -->
    <link rel="preload" as="image" href="${pageContext.request.contextPath}/assets/img/painting-back-icon.svg">
    <link rel="preload" as="image" href="${pageContext.request.contextPath}/assets/img/profile-icon.svg">
    <link rel="preload" as="image" href="${pageContext.request.contextPath}/assets/img/search-icon.svg">
    <link rel="preload" as="image" href="${pageContext.request.contextPath}/assets/img/reminder-icon.svg">
    <link rel="preload" as="image" href="${pageContext.request.contextPath}/assets/img/statistics-icon.svg">

    <!-- Links -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/global.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/tokens.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/layout/perfil.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico">
    <script type="module" src="${pageContext.request.contextPath}/assets/js/script.js" defer></script>

    <title>Perfil - Monart</title>
</head>

<body>
    <div class="meuPlaceholder"></div>

    <header class="headerLateral">
        <img src="${pageContext.request.contextPath}/assets/img/monart-logo.svg" decoding="async" alt="" class="logoMonart">
        <nav>
            <ul>
                <li><a href="${pageContext.request.contextPath}/professores?page=perfil-professor" class="pagina ativo"><img src="${pageContext.request.contextPath}/assets/img/profile-icon.svg" decoding="async" alt="">Perfil</a></li>
                <li><a href="${pageContext.request.contextPath}/professores?page=buscar" class="pagina"><img src="${pageContext.request.contextPath}/assets/img/search-icon.svg" decoding="async" alt="">Buscar</a></li></li>
                <li><a href="${pageContext.request.contextPath}/professores?page=lembretes" class="pagina"><img src="${pageContext.request.contextPath}/assets/img/reminder-icon.svg" decoding="async" alt="">Lembretes</a></li></li>
                <li><a href="${pageContext.request.contextPath}/professores?page=estatisticas" class="pagina"><img src="${pageContext.request.contextPath}/assets/img/statistics-icon.svg" decoding="async" alt="">Estatísticas</a></li>
            </ul>
        </nav>
        <a href="${pageContext.request.contextPath}/autenticacao/login.jsp">
            <img src="${pageContext.request.contextPath}/assets/img/painting-back-icon.svg" alt="Ícone de voltar" class="pincelVoltar">
        </a>
    </header>
    
    <main>
        <div class="cabecalhoPaginas">
            <img src="${pageContext.request.contextPath}/assets/img/themes-icon.svg" alt="" class="abrirTemas">
            <div class="tituloPaginas">
                <h1>Seja bem-vindo, ${fn:split(sessionScope.usuario.nome, ' ')[0]}!</h1>
            </div>
            <img src="${pageContext.request.contextPath}/assets/img/chat-palette-icon.svg"
                 alt="" class="abrirChat"
                 onclick="window.location.href='${pageContext.request.contextPath}/mensagens?idRemetente=${sessionScope.usuario.id}&tipoRemetente=${sessionScope.tipoUsuario}'">
        </div>

        <div class="perfil">
            <div class="pessoa">
                <img src="${pageContext.request.contextPath}/assets/img/art.png" alt="arte impressionista" class="arte">
                <div class="informacoes">
                    <h3>${sessionScope.usuario.nome}</h3>
                    <h3>Disciplina: ${sessionScope.usuario.materia}</h3>
                    <h3>Email: ${sessionScope.usuario.email}</h3>
                </div>
            </div>

            <button class="editar" onclick="editarPerfil.showModal()">Editar
                <img class="iconeEditar" src="${pageContext.request.contextPath}/assets/img/editar.svg" alt="ícone de editar">
            </button>
        </div>

        <section class="notificacoes">
            <div class="nova mensagem">
                <div class="barrinhaTema">
                    <h2>Mensagens recentes</h2>
                </div>
                <div class="listaRecentes">
                    <c:choose>
                        <c:when test="${not empty mensagensRecentes}">
                            <c:forEach items="${mensagensRecentes}" var="m">
                                <div class="itemRecente" onclick="window.location.href='${pageContext.request.contextPath}/mensagens?idRemetente=${sessionScope.usuario.id}&tipoRemetente=${sessionScope.usuarioTipo}&idDestinatario=${m.idDestinatario}&tipoDestinatario=${m.tipoDestinatario}'">
                                    <strong>${m.nome}</strong>
                                    <c:if test="${m.temNaoLidas}">
                                        <span class="badgeNaoLida">🔴</span>
                                    </c:if>
                                    <p>${m.mensagem}</p>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <p class="semMensagens">Nenhuma mensagem recente.</p>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </section>
    </main>

    <dialog class="editarPerfil" id="editarPerfil">
        <button class="fecharPopUpEditar" onclick="editarPerfil.close()">X</button>
        <form action="${pageContext.request.contextPath}/professor-update" method="post">

            <label for="alterarEmail">E-mail</label>
            <input type="email" name="email" id="alterarEmail">

            <button class="salvarAlteracoes">Salvar</button>
        </form>
        <p>${sessionScope.mensagem}</p>
    </dialog>
</body>

</html>