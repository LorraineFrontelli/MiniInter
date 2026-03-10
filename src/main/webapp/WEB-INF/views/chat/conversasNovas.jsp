<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script>document.documentElement.style.setProperty("--tema",localStorage.getItem("corTema")||"#242021");</script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/global.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/tokens.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/layout/conversas.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico">
    <title>Nova Conversa - Monart</title>
</head>
<body>
<main>
    <div class="cabecalhoPaginas">
        <img src="${pageContext.request.contextPath}/assets/img/painting-back-icon.svg"
             alt="Ícone de voltar" onclick="history.back()" class="pincelVoltar">

        <div class="buscarAlunos">
            <search>
                <!-- busca no frontend, sem servlet -->
                <input type="text" id="inputBusca" class="buscar" placeholder="Procurar pessoas">
            </search>
            <img src="${pageContext.request.contextPath}/assets/img/search-icon.svg" alt="">
        </div>
    </div>

    <div class="buscaContainer" id="listaUsuarios">
        <c:forEach items="${usuarios}" var="u">
            <div class="resultadoBusca usuario"
                 data-nome="${u.nome}"
                 onclick="window.location.href='${pageContext.request.contextPath}/mensagens?idRemetente=${idAtual}&tipoRemetente=${tipoAtual}&idDestinatario=${u.id}&tipoDestinatario=${u.tipo}'">
                <div>
                    <strong>${u.nome}</strong>
                    <br>
                    <span class="tipoUsuario">${u.tipo}</span>
                </div>
                <img src="${pageContext.request.contextPath}/assets/img/arrow-icon.svg" alt="Iniciar conversa">
            </div>
        </c:forEach>
    </div>
</main>

<script>
    // busca por frontend — filtra sem ir ao servidor
    document.getElementById('inputBusca').addEventListener('input', function() {
        const termo = this.value.toLowerCase();
        document.querySelectorAll('.usuario').forEach(function(el) {
            const nome = el.getAttribute('data-nome').toLowerCase();
            el.style.display = nome.includes(termo) ? '' : 'none';
        });
    });
</script>
</body>
</html>