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
    <link rel="preload" as="image" href="${pageContext.request.contextPath}/assets/img/schedule-icon.svg">
    <link rel="preload" as="image" href="${pageContext.request.contextPath}/assets/img/grades-icon.svg">
    <link rel="preload" as="image" href="${pageContext.request.contextPath}/assets/img/observations-icon.svg">

    <!-- Links -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/global.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/tokens.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/layout/observacoes.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico">
    <script src="${pageContext.request.contextPath}/assets/js/script.js" defer></script>

    <title>Observações - Monart</title>
</head>

<body>
    <div class="meuPlaceholder"></div>

    <header class="headerLateral">
        <img src="${pageContext.request.contextPath}/assets/img/monart-logo.svg" decoding="async" alt="" class="logoMonart">
        <nav>
            <ul>
                <li><a href="${pageContext.request.contextPath}/alunos?page=perfil-aluno" class="pagina"><img src="${pageContext.request.contextPath}/assets/img/profile-icon.svg" decoding="async" alt="">Perfil</a></li>
                <li><a href="${pageContext.request.contextPath}/alunos?page=agenda" class="pagina"><img src="${pageContext.request.contextPath}/assets/img/schedule-icon.svg" decoding="async" alt="">Agenda</a></li>
                <li><a href="${pageContext.request.contextPath}/alunos?page=boletim" class="pagina"><img src="${pageContext.request.contextPath}/assets/img/grades-icon.svg" decoding="async" alt="">Notas</a></li>
                <li><a href="${pageContext.request.contextPath}/alunos?page=observacoes" class="pagina ativo"><img src="${pageContext.request.contextPath}/assets/img/observations-icon.svg" decoding="async" alt="">Observações</a></li>
            </ul>
        </nav>
        <a href="${pageContext.request.contextPath}/autenticacao/login.html">
            <img src="${pageContext.request.contextPath}/assets/img/painting-back-icon.svg" alt="Ícone de voltar" class="pincelVoltar">
        </a>
    </header>

    <main>
        <div class="cabecalhoPaginas">
            <img src="${pageContext.request.contextPath}/assets/img/themes-icon.svg" alt="" class="abrirTemas">
            <div class="tituloPaginas">
                <h1>Observações</h1>
            </div>
            <a href="${pageContext.request.contextPath}/mensagens?page=conversas"><img src="${pageContext.request.contextPath}/assets/img/chat-palette-icon.svg" alt="" class="abrirChat"></a>
        </div>

        <section class="observacoes">

            <c:forEach items="${sessionScope.boletim}" var="b">
                <div class="observacao">
                    <div class="barrinhaTema"></div>

                    <div class="mensagemObservacao">
                        <div class="tituloObservacao">
                            <h2>${b.materia}</h2>
                            <p>
                                Prof: <b>${b.professor}</b>
                            </p>
                        </div>

                        <hr>

                        <div class="dadosObservacao">
                            <p>${b.observacao}</p>

                            <time datetime="<fmt:formatDate value='${b.dataCriacao}' pattern='yyyy-MM-dd'/>">
                                <fmt:formatDate value="${b.dataCriacao}" pattern="dd/MM/yyyy" />
                            </time>
                        </div>
                    </div>
                </div>
            </c:forEach>

        </section>
    </main>
</body>

</html>