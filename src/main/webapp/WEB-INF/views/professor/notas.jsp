<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/layout/notas.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico">
    <script src="${pageContext.request.contextPath}/assets/js/script.js" defer></script>
    
    <title>Notas do aluno - Monart</title>
</head>

<body>
    <div class="meuPlaceholder"></div>

    <header class="headerLateral">
        <img src="${pageContext.request.contextPath}/assets/img/monart-logo.svg" decoding="async" alt="" class="logoMonart">
        <nav>
            <ul>
                <li><a href="perfil-professor.jsp" class="pagina"><img src="${pageContext.request.contextPath}/assets/img/profile-icon.svg" decoding="async" alt="">Perfil</a></li>
                <li><a href="buscar.jsp" class="pagina"><img src="${pageContext.request.contextPath}/assets/img/search-icon.svg" decoding="async" alt="">Buscar</a></li>
                <li><a href="lembretes.jsp" class="pagina"><img src="${pageContext.request.contextPath}/assets/img/reminder-icon.svg" decoding="async" alt="">Lembretes</a></li></li>
                <li><a href="estatisticas.jsp" class="pagina"><img src="${pageContext.request.contextPath}/assets/img/statistics-icon.svg" decoding="async" alt="">Estatísticas</a></li>
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
                <h1>Fulaninho de tal</h1>
            </div>
            <a href="${pageContext.request.contextPath}/chat/conversas.jsp"><img src="${pageContext.request.contextPath}/assets/img/chat-palette-icon.svg" alt="" class="abrirChat"></a>
        </div>

        <div class="pessoa">
            <img src="${pageContext.request.contextPath}/assets/img/art.png" alt="arte impressionista" class="arte">
            <div class="informacoes">
                <h3>Fulano da Silva</h3>
                <h3>Turma: 1° série A</h3>
                <h3>Telefone: (11)xxxxx-xxxx</h3>
                <h3>Email: fulano.silva@monart.com</h3>   
            </div>
        </div>

        <section class="notasAluno">
            <div class="titulos">
                <h3>Nota 1</h3>
                <h3>Nota 2</h3>
                <h3>Média</h3>
                <h3 class="tituloSituacao">Situação</h3>
            </div>

            <form action="">
                <div class="situacaoAluno">
                    <input type="number" class="notas nota2" id="nota1" name="nota1" max="10">
                    <input type="number" class="notas nota1" id="nota2" name="nota2" max="10">
                    <input type="number" class="notas media" disabled>
                    <input type="number" class="notas situacao" disabled>
                </div>

                <div class="menuAluno">
                    <button type="button" onclick="window.location.href='buscar.jsp'">Voltar</button>

                    <div class="observacoes">
                        <h3 class="tituloObservacoes">Observações</h3>
                        <textarea type="text" class="quadroObservacao" id="observacao" name="observacao"></textarea>
                    </div>

                    <button type="submit" onclick class="salvarNotas">Salvar</button>
                </div>
            </form>
        </section>
    </main>
</body>
</html>