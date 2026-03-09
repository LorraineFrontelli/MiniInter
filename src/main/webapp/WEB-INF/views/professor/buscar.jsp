<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="preload" as="image" href="${pageContext.request.contextPath}assets/img/statistics-icon.svg">

    <!-- Links -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/global.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/tokens.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/layout/buscar.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico">
    <script src="${pageContext.request.contextPath}/assets/js/script.js" defer></script>

    <title>Buscar alunos - Monart</title>
</head>

<body>
<div class="meuPlaceholder"></div>

<header class="headerLateral">
    <img src="${pageContext.request.contextPath}/assets/img/monart-logo.svg" decoding="async" alt="" class="logoMonart">
    <nav>
        <ul>
            <li><a href="${pageContext.request.contextPath}/professores?page=perfil-professor" class="pagina"><img src="${pageContext.request.contextPath}assets/img/profile-icon.svg" decoding="async" alt="">Perfil</a></li>
            <li><a href="${pageContext.request.contextPath}/professores?page=buscar" class="pagina ativo"><img src="${pageContext.request.contextPath}assets/img/search-icon.svg" decoding="async" alt="">Buscar</a></li>
            <li><a href="${pageContext.request.contextPath}/professores?page=lembretes" class="pagina"><img src="${pageContext.request.contextPath}assets/img/reminder-icon.svg" decoding="async" alt="">Lembretes</a></li></li>
            <li><a href="${pageContext.request.contextPath}/professores?page=estatisticas" class="pagina"><img src="${pageContext.request.contextPath}assets/img/statistics-icon.svg" decoding="async" alt="">Estatísticas</a></li>
        </ul>
    </nav>
    <a href="${pageContext.request.contextPath}/autenticacao/login.jsp">
        <img src="${pageContext.request.contextPath}/assets/img/painting-back-icon.svg" alt="Ícone de voltar" class="pincelVoltar">
    </a>
</header>

<main>
    <div class="cabecalhoPaginas">
        <img src="${pageContext.request.contextPath}/assets/img/themes-icon.svg" alt="" class="abrirTemas">
        <div class="buscarAlunos">
            <search>
                <form action="${pageContext.request.contextPath}/alunos" method="POST">
                    <input name="filtroMatricula" type="text" class="buscar" placeholder="Inserir matrícula">
                </form>
            </search>
            <img src="${pageContext.request.contextPath}/assets/img/search-icon.svg" alt="">
        </div>
        <img src="${pageContext.request.contextPath}/assets/img/chat-palette-icon.svg"
             alt="" class="abrirChat"
             onclick="window.location.href='${pageContext.request.contextPath}/mensagens?idRemetente=${sessionScope.usuario.id}&tipoRemetente=${sessionScope.tipoUsuario}'">
    </div>


    <c:if test="${not empty alunos}">
        <p>${mensagem}</p>
    </c:if>

    <div class="buscaContainer">
        <c:forEach items="${alunos}" var="aluno">
            <div class="resultadoBusca">
                <div>
                    <strong>Aluno: ${aluno.nome}</strong>
                    <br>
                    <span><b>Matrícula:</b> ${aluno.matricula}</span>
                </div>
                <a href="${pageContext.request.contextPath}/professores?page=notas&matricula=${aluno.matricula}">
                    <img src="${pageContext.request.contextPath}/assets/img/see-more-icon.svg" alt="Ver mais">
                </a>
            </div>
        </c:forEach>
    </div>
    </div>
</main>
</body>

</html>