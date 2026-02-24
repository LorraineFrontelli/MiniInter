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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/layout/buscar.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico">
    <script src="${pageContext.request.contextPath}/assets/js/script.js" defer></script>

    <title>Estatística - Monart</title>
</head>

<body>
    <div class="meuPlaceholder"></div>

    <header class="headerLateral">
        <img src="${pageContext.request.contextPath}/assets/img/monart-logo.svg" decoding="async" alt="" class="logoMonart">
        <nav>
            <ul>
                <li><a href="perfil-professor.html" class="pagina"><img src="${pageContext.request.contextPath}/assets/img/profile-icon.svg" decoding="async" alt="">Perfil</a></li>
                <li><a href="buscar.html" class="pagina"><img src="${pageContext.request.contextPath}/assets/img/search-icon.svg" decoding="async" alt="">Buscar</a></li></li>
                <li><a href="lembretes.html" class="pagina"><img src="${pageContext.request.contextPath}/assets/img/reminder-icon.svg" decoding="async" alt="">Lembretes</a></li></li>
                <li><a href="estatisticas.html" class="pagina ativo"><img src="${pageContext.request.contextPath}/assets/img/statistics-icon.svg" decoding="async" alt="">Estatísticas</a></li>
            </ul>
        </nav>
        <a href="../autenticacao/login.html">
            <img src="${pageContext.request.contextPath}/assets/img/painting-back-icon.svg" alt="Ícone de voltar" class="pincelVoltar">
        </a>
    </header>
    
    <main>
        <iframe src="https://dbc-120abf1e-f271.cloud.databricks.com/dashboardsv3/01f101c174041bb7a0fc8363ad1c6f62/published?o=7474645983685222" class="dashboardProfessor"></iframe>
    </main>
</body>

</html>