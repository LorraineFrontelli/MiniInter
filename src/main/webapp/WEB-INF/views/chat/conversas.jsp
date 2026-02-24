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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/layout/conversas.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico">
    <script src="${pageContext.request.contextPath}/assets/js/script.js" defer></script>

    <title>Conversas - Monart</title>
</head>

<body>
    <main>
        <div class="cabecalhoPaginas">
            <img src="${pageContext.request.contextPath}/assets/img/painting-back-icon.svg" alt="Ícone de voltar" onclick="history.back()" class="pincelVoltar">
            <div class="buscarAlunos">
                <search>
                    <form action="">
                        <input type="text" class="buscar" placeholder="Procurar conversas">
                    </form>
                </search>
                <img src="${pageContext.request.contextPath}/assets/img/search-icon.svg" alt="">
            </div>
        </div>

        <div class="buscaContainer">
            <div class="resultadoBusca" onclick="window.location.href='chat.html'">
                <div>
                    <strong>Tainá Dias Martinelli</strong>
                </div>
                <a href="chat.html"><img src="${pageContext.request.contextPath}/assets/img/arrow-icon.svg" alt=""></a>
            </div>
            
            <div class="resultadoBusca" onclick="window.location.href='chat.html'">
                <div>
                    <strong>Tainá Dias Martinelli</strong>
                </div>
                <a href="chat.html"><img src="${pageContext.request.contextPath}/assets/img/arrow-icon.svg" alt=""></a>
            </div>
            
            <div class="resultadoBusca" onclick="window.location.href='chat.html'">
                <div>
                    <strong>Tainá Dias Martinelli</strong>
                </div>
                <a href="chat.html"><img src="${pageContext.request.contextPath}/assets/img/arrow-icon.svg" alt=""></a>
            </div>
        </div>
    </main>
</body>

</html>