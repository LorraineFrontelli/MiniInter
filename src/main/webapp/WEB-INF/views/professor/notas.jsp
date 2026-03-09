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
    <script type="module" src="${pageContext.request.contextPath}/assets/js/script.js"></script>
    
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
                <label for="nota1" class="tituloNota1"><h3>Nota 1</h3></label>
                <label for="nota2" class="tituloNota2"><h3>Nota 2</h3></label>
                <label for="media" class="tituloMedia"><h3>Média</h3></label>
                <h3 class="tituloSituacao">Situação</h3>
            </div>

            <form action="">
                <div class="situacaoAluno">
                    <div class="caixaNota">
                        <input type="number" class="notas nota1" id="nota1" name="nota1" min="0" max="10" step="0.01" size="2" required>
                        <button type="button" class="abrirNota1" onclick="verTipoNota1.showModal()"><img src="${pageContext.request.contextPath}/assets/img/heavy-plus-icon.svg" alt=""></button>
                    </div>

                    <div class="caixaNota">
                        <input type="number" class="notas nota2" id="nota2" name="nota2" min="0" max="10" step="0.01" size="2" required>
                        <button type="button" class="abrirNota2" onclick="verTipoNota2.showModal()"><img src="${pageContext.request.contextPath}/assets/img/heavy-plus-icon.svg" alt=""></button>
                    </div>

                    <input type="hidden" name="descricaoNota1" id="descricaoNota1Hidden" required>
                    <input type="hidden" name="descricaoNota2" id="descricaoNota2Hidden" required>
                
                    <input type="number" class="notas media" id="media" name="media" readonly>
                    <input type="number" class="notas situacao" readonly>
                </div>

                <div class="menuAluno">
                    <button type="button" onclick="window.location.href='buscar.jsp'">Voltar</button>

                    <div class="observacoes">
                        <h3 class="tituloObservacoes">Observações</h3>
                        <textarea type="text" class="quadroObservacao" id="observacao" name="observacao" required></textarea>
                    </div>

                    <button type="submit" class="salvarNotas">Salvar</button>
                </div>
            </form>
        </section>
    </main>

    <!-- Modal Ver Tipo Nota 1 -->
    <dialog class="verTipoNota1" id="verTipoNota1">
        <form method="dialog">
            <button formmethod="dialog" class="fecharPopUp">X</button>
            
            <label for="descricaoNota1">Descrição Nota 1</label>
            <input type="text" name="descricaoNota1" id="descricaoNota1" required>
        </form>
    </dialog>

    <!-- Modal Ver Tipo Nota 2 -->
    <dialog class="verTipoNota2" id="verTipoNota2">
        <form method="dialog">
            <button formmethod="dialog" class="fecharPopUp">X</button>
            
            <label for="descricaoNota2">Descrição Nota 2</label>
            <input type="text" name="descricaoNota2" id="descricaoNota2" required>
        </form>
    </dialog>
</body>
</html>