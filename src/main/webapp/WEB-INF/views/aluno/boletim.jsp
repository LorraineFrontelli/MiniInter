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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/layout/boletim.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico">
    <script type="module" src="${pageContext.request.contextPath}/assets/js/script.js" defer></script>

    <title>Suas notas - Monart</title>
</head>

<body>
    <div class="meuPlaceholder"></div>

    <header class="headerLateral">
        <img src="${pageContext.request.contextPath}/assets/img/monart-logo.svg" decoding="async" alt="" class="logoMonart">
        <nav>
            <ul>
                <li><a href="${pageContext.request.contextPath}/alunos?page=perfil-aluno" class="pagina"><img src="${pageContext.request.contextPath}/assets/img/profile-icon.svg" decoding="async" alt="">Perfil</a></li>
                <li><a href="${pageContext.request.contextPath}/alunos?page=agenda" class="pagina"><img src="${pageContext.request.contextPath}/assets/img/schedule-icon.svg" decoding="async" alt="">Agenda</a></li>
                <li><a href="${pageContext.request.contextPath}/alunos?page=boletim" class="pagina ativo"><img src="${pageContext.request.contextPath}/assets/img/grades-icon.svg" decoding="async" alt="">Notas</a></li>
                <li><a href="${pageContext.request.contextPath}/alunos?page=observacoes" class="pagina"><img src="${pageContext.request.contextPath}/assets/img/observations-icon.svg" decoding="async" alt="">Observações</a></li>
            </ul>
        </nav>
        <a href="${pageContext.request.contextPath}/logout">
            <img src="${pageContext.request.contextPath}/assets/img/painting-back-icon.svg" alt="Ícone de voltar" class="pincelVoltar">
        </a>
    </header>
    
    <main>
        <div class="cabecalhoPaginas">
            <img src="${pageContext.request.contextPath}/assets/img/themes-icon.svg" alt="" class="abrirTemas">
            <div class="tituloPaginas">
                <h1>Notas</h1>
            </div>
            <img src="${pageContext.request.contextPath}/assets/img/chat-palette-icon.svg"
                 alt="" class="abrirChat"
                 onclick="window.location.href='${pageContext.request.contextPath}/mensagens?idRemetente=${sessionScope.usuario.id}&tipoRemetente=${sessionScope.tipoUsuario}'">
        </div>

        <div class="tabelaContainer">
            <table class="tabelaNotas">

                <colgroup>
                    <col style="width: 27%">
                    <col style="width: 5%;">
                    <col style="width: 12%">
                    <col style="width: 12%">
                    <col style="width: 12%">
                    <col style="width: 5%;">
                    <col style="width: 27%">
                </colgroup>

                <thead>
                <tr>
                    <th>Matéria</th>
                    <th></th>
                    <th>Nota 1</th>
                    <th>Nota 2</th>
                    <th>Média</th>
                    <th></th>
                    <th>Situação</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${sessionScope.boletim}" var="b">
                    <tr>
                        <td class="materia">
                                ${b.materia}
                        </td>

                        <td class="espaco"></td>

                        <td class="notasCentro nota1">
                            <fmt:formatNumber value="${b.nota1}" minFractionDigits="2" />
                            <button class="abrirNota1" data-descricao="${b.descricao1}" onclick="abrirModalNota1(this)">
                                <img src="${pageContext.request.contextPath}/assets/img/heavy-plus-icon.svg" alt="">
                            </button>
                        </td>
                        
                        <td class="notasCentro nota2">
                            <fmt:formatNumber value="${b.nota2}" minFractionDigits="2" />
                            <button class="abrirNota2" data-descricao="${b.descricao2}" onclick="abrirModalNota2(this)">
                                <img src="${pageContext.request.contextPath}/assets/img/heavy-plus-icon.svg" alt="">
                            </button>
                        </td>

                        <td class="notasCentro media">
                            <fmt:formatNumber
                                    value="${(b.nota1 + b.nota2) / 2}"
                                    minFractionDigits="2" />
                        </td>

                        <td class="espaco"></td>

                        <td class="situacao">
                            <c:choose>
                                <c:when test="${b.aprovado}">
                                    Aprovado
                                </c:when>
                                <c:otherwise>
                                    Reprovado
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>

            </table>
        </div>
    </main>

    <!-- Modal Ver Tipo Nota 1 -->
    <dialog class="verTipoNota1" id="verTipoNota1">
        <form method="dialog">
            <button formmethod="dialog" class="fecharPopUp">X</button>
            
            <label for="descricaoNota1">Descrição Nota 1</label>
            <input type="text" name="descricaoNota1" id="descricaoNota1" disabled>
        </form>
    </dialog>

    <!-- Modal Ver Tipo Nota 2 -->
    <dialog class="verTipoNota2" id="verTipoNota2">
        <form method="dialog">
            <button formmethod="dialog" class="fecharPopUp">X</button>
            
            <label for="descricaoNota2">Descrição Nota 2</label>
            <input type="text" name="descricaoNota2" id="descricaoNota2" disabled>
        </form>
    </dialog>

    <script>
        function abrirModalNota1(botao) {
            const descricao = botao.getAttribute("data-descricao");            
            document.getElementById("descricaoNota1").value = descricao ? descricao : "Sem descrição";
            document.getElementById("verTipoNota1").showModal();
        }

        function abrirModalNota2(botao) {
            const descricao = botao.getAttribute("data-descricao");
            document.getElementById("descricaoNota2").value = descricao ? descricao : "Sem descrição";
            document.getElementById("verTipoNota2").showModal();
        }
    </script>
</body>

</html>
