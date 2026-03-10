<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/layout/estatisticas.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico">
    <script type="module" src="${pageContext.request.contextPath}/assets/js/script.js" defer></script>

    <title>Estatística - Monart</title>
</head>

<body>
    <div class="meuPlaceholder"></div>

    <header class="headerLateral">
        <img src="${pageContext.request.contextPath}/assets/img/monart-logo.svg" decoding="async" alt="" class="logoMonart">
        <nav>
            <ul>
                <li><a href="${pageContext.request.contextPath}/professores?page=perfil-professor" class="pagina"><img src="${pageContext.request.contextPath}/assets/img/profile-icon.svg" decoding="async" alt="">Perfil</a></li>
                <li><a href="${pageContext.request.contextPath}/professores?page=buscar" class="pagina"><img src="${pageContext.request.contextPath}/assets/img/search-icon.svg" decoding="async" alt="">Buscar</a></li></li>
                <li><a href="${pageContext.request.contextPath}/professores?page=lembretes" class="pagina"><img src="${pageContext.request.contextPath}/assets/img/reminder-icon.svg" decoding="async" alt="">Lembretes</a></li></li>
                <li><a href="${pageContext.request.contextPath}/professores?page=estatisticas" class="pagina ativo"><img src="${pageContext.request.contextPath}/assets/img/statistics-icon.svg" decoding="async" alt="">Estatísticas</a></li>
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
                <h1>Estatísticas</h1>
            </div>
            <img src="${pageContext.request.contextPath}/assets/img/chat-palette-icon.svg"
                 alt="" class="abrirChat"
                 onclick="window.location.href='${pageContext.request.contextPath}/mensagens?idRemetente=${sessionScope.usuario.id}&tipoRemetente=${sessionScope.tipoUsuario}'">
        </div>

        <h1 class="carregandoDashboard" id="carregandoDashboard">Carregando estatísticas...</h1>

        <iframe src="https://jonesprado.github.io/dashboard-professor/" class="dashboardProfessor"></iframe>
    </main>
</body>

</html>