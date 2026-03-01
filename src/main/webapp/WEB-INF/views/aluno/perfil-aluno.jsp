<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
    <link rel="preload" as="image" href="${pageContext.request.contextPath}/assets/img/schedule-icon.svg">
    <link rel="preload" as="image" href="${pageContext.request.contextPath}/assets/img/grades-icon.svg">
    <link rel="preload" as="image" href="${pageContext.request.contextPath}/assets/img/observations-icon.svg">

    <!-- Links -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/global.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/tokens.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/layout/perfil.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico">
    <script src="${pageContext.request.contextPath}/assets/js/script.js" defer></script>

    <title>Perfil - Monart</title>
</head>

<body>
    <div class="meuPlaceholder"></div>

    <header class="headerLateral">
        <img src="${pageContext.request.contextPath}/assets/img/monart-logo.svg" decoding="async" alt="" class="logoMonart">
        <nav>
            <ul>
                <li><a href="${pageContext.request.contextPath}/alunos?page=perfil-aluno" class="pagina ativo"><img src="${pageContext.request.contextPath}/assets/img/profile-icon.svg" decoding="async" alt="">Perfil</a></li>
                <li><a href="${pageContext.request.contextPath}/alunos?page=agenda" class="pagina"><img src="${pageContext.request.contextPath}/assets/img/schedule-icon.svg" decoding="async" alt="">Agenda</a></li>
                <li><a href="${pageContext.request.contextPath}/alunos?page=boletim" class="pagina"><img src="${pageContext.request.contextPath}/assets/img/grades-icon.svg" decoding="async" alt="">Notas</a></li>
                <li><a href="${pageContext.request.contextPath}/alunos?page=observacoes" class="pagina"><img src="${pageContext.request.contextPath}/assets/img/observations-icon.svg" decoding="async" alt="">Observações</a></li>
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
                <h1>Seja bem-vindo, ${fn:split(sessionScope.usuarioLogado.nome, ' ')[0]}!</h1>
            </div>
            <a href="${pageContext.request.contextPath}/mensagens?page=conversas"><img src="${pageContext.request.contextPath}/assets/img/chat-palette-icon.svg" alt="" class="abrirChat"></a>
        </div>

        <div class="perfil">
            <div class="pessoa">
                <img src="${pageContext.request.contextPath}/assets/img/art.png" alt="arte impressionista" class="arte">
                <div class="informacoes">
                    <h3>${sessionScope.usuario.nome}</h3>
                    <h3>Turma: ${sessionScope.alunoProfessor.serie}° série ${sessionScope.alunoProfessor.turma}</h3>
                    <h3>Telefone:
                        <c:forEach var="tel" items="${sessionScope.telefoneAluno}" varStatus="status">
                            ${tel.numero}<c:if test="${!status.last}"> / </c:if>
                        </c:forEach></h3>
                    <h3>Email: ${sessionScope.usuario.email}</h3>
                </div>
            </div>

            <button class="editar">Editar
                <img class="iconeEditar" src="${pageContext.request.contextPath}/assets/img/editar.svg" alt="ícone de editar">
            </button>
        </div>
        
        <section class="notificacoes">
            <div class="nova observacao">
                <div class="barrinhaTema">
                    <h2>Nova observação</h2>
                </div>

                <c:forEach items="${sessionScope.boletim}" var="bol" varStatus="status">
                    <c:if test="${status.last}">
                        <p>${bol.observacao}</p>
                        <h3>
                            Prof: ${bol.professor}
                        </h3>
                    </c:if>
                </c:forEach>

            </div>

            <div class="nova atividade">
                <div class="barrinhaTema">
                    <h2>Nova atividade</h2>
                    <h3><c:forEach var="tar" items="${sessionScope.tarefas}" varStatus="status">
                        ${tar.tarefas}<c:if test="${!status.last}"> / </c:if>
                    </c:forEach></h3>
                </div>

            </div>
        </section>
    </main>
</body>

</html>