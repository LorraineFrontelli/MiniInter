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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/layout/agenda.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico">
    <script src="${pageContext.request.contextPath}/assets/js/script.js" defer></script>

    <title>Agenda - Monart</title>
</head>

<body>
    <div class="meuPlaceholder"></div>

    <header class="headerLateral">
        <img src="../../../assets/img/monart-logo.svg" decoding="async" alt="" class="logoMonart">
        <nav>
            <ul>
                <li><a href="${pageContext.request.contextPath}/alunos?page=perfil-aluno" class="pagina"><img src="${pageContext.request.contextPath}/assets/img/profile-icon.svg" decoding="async" alt="">Perfil</a></li>
                <li><a href="${pageContext.request.contextPath}/alunos?page=agenda" class="pagina ativo"><img src="${pageContext.request.contextPath}/assets/img/schedule-icon.svg" decoding="async" alt="">Agenda</a></li>
                <li><a href="${pageContext.request.contextPath}/alunos?page=boletim" class="pagina"><img src="${pageContext.request.contextPath}/assets/img/grades-icon.svg" decoding="async" alt="">Notas</a></li>
                <li><a href="${pageContext.request.contextPath}/alunos?page=observacoes" class="pagina"><img src="${pageContext.request.contextPath}/assets/img/observations-icon.svg" decoding="async" alt="">Observações</a></li>
            </ul>
        </nav>
        <a href="${pageContext.request.contextPath}/login">
            <img src="${pageContext.request.contextPath}/assets/img/painting-back-icon.svg" alt="Ícone de voltar" class="pincelVoltar">
        </a>
    </header>

    <main>
        <div class="cabecalhoPaginas">
            <img src="${pageContext.request.contextPath}/assets/img/themes-icon.svg" alt="" class="abrirTemas">
            <div class="tituloPaginas">
                <h1>Semana</h1>
            </div>
            <a href="${pageContext.request.contextPath}/mensagens?page=conversas"><img src="../../../assets/img/chat-palette-icon.svg" alt="" class="abrirChat"></a>
        </div>
        
        <section class="sectionSemana">
            <div>
                <div class="tarefas">
                    <h2>Tarefas</h2>

                    <c:if test="${empty sessionScope.tarefas}">
                        <p>Nenhuma tarefa cadastrada.</p>
                    </c:if>

                    <c:forEach var="tar" items="${sessionScope.tarefas}">
                        <div class="tarefa">
                            <h3>${tar.tarefa}</h3>
                            <p>${tar.dtEntrega}</p>
                        </div>
                    </c:forEach>
                </div>

            </div>

            <div class="dias">

                <div>
                    <!-- SEGUNDA -->
                    <div class="semana segunda">
                        <h2>Segunda</h2>
                        <c:forEach var="tarefa" items="${sessionScope.tarefas}">
                            <c:if test="${tarefa.dtEntrega.dayOfWeek.value == 1}">
                                <div class="tarefa">${tarefa.tarefa}</div>
                            </c:if>
                        </c:forEach>
                    </div>

                    <!-- QUARTA -->
                    <div class="semana quarta">
                        <h2>Quarta</h2>
                        <c:forEach var="tarefa" items="${sessionScope.tarefas}">
                            <c:if test="${tarefa.dtEntrega.dayOfWeek.value == 3}">
                                <div class="tarefa">${tarefa.tarefa}</div>
                            </c:if>
                        </c:forEach>
                    </div>

                    <!-- SEXTA -->
                    <div class="semana sexta">
                        <h2>Sexta</h2>
                        <c:forEach var="tarefa" items="${sessionScope.tarefas}">
                            <c:if test="${tarefa.dtEntrega.dayOfWeek.value == 5}">
                                <div class="tarefa">${tarefa.tarefa}</div>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>

                <div>
                    <!-- TERÇA -->
                    <div class="semana terca">
                        <h2>Terça</h2>
                        <c:forEach var="tarefa" items="${sessionScope.tarefas}">
                            <c:if test="${tarefa.dtEntrega.dayOfWeek.value == 2}">
                                <div class="tarefa">${tarefa.tarefa}</div>
                            </c:if>
                        </c:forEach>
                    </div>

                    <!-- QUINTA -->
                    <div class="semana quinta">
                        <h2>Quinta</h2>
                        <c:forEach var="tarefa" items="${sessionScope.tarefas}">
                            <c:if test="${tarefa.dtEntrega.dayOfWeek.value == 4}">
                                <div class="tarefa">${tarefa.tarefa}</div>
                            </c:if>
                        </c:forEach>
                    </div>

                    <!-- FIM DE SEMANA -->
                    <div class="semana fimDeSemana">
                        <h2>Fim de semana</h2>
                        <c:forEach var="tarefa" items="${sessionScope.tarefas}">
                            <c:if test="${tarefa.dtEntrega.dayOfWeek.value == 6 || tarefa.dtEntrega.dayOfWeek.value == 7}">
                                <div class="tarefa">${tarefa.tarefa}</div>
                            </c:if>
                        </c:forEach>
                    </div>
                </div>

            </div>
        </section>
    </main>
</body>

</html>