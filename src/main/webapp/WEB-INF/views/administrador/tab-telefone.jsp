<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Aplicando tema salvo -->
    <script>document.documentElement.style.setProperty("--tema", localStorage.getItem("corTema") || "#242021");</script>

    <!-- Preloads -->


    <!-- Links -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/global.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/tokens.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/layout/crud.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico">
    <script src="${pageContext.request.contextPath}/assets/js/script.js" defer></script>

    <title>CRUD - Monart</title>
</head>

<body>
    <div class="meuPlaceholder"></div>

    <header class="headerLateral">
        <img src="${pageContext.request.contextPath}/assets/img/monart-logo.svg" decoding="async" alt="" class="logoMonart">
        <nav>
            <ul>
                <li><a href="tab-administrador.jsp" class="pagina"><img src="${pageContext.request.contextPath}/assets/img/schedule-icon.svg" decoding="async" alt="">Administrador</a></li>
                <li><a href="tab-aluno.jsp" class="pagina"><img src="${pageContext.request.contextPath}/assets/img/schedule-icon.svg" decoding="async" alt="">Aluno</a></li>
                <li><a href="tab-professor.jsp" class="pagina"><img src="${pageContext.request.contextPath}/assets/img/schedule-icon.svg" decoding="async" alt="">Professor</a></li>
                <li><a href="tab-boletim.jsp" class="pagina"><img src="${pageContext.request.contextPath}/assets/img/schedule-icon.svg" decoding="async" alt="">Boletim</a></li>
                <li><a href="tab-telefone.jsp" class="pagina ativo"><img src="${pageContext.request.contextPath}/assets/img/schedule-icon.svg" decoding="async" alt="">Telefone</a></li>
            </ul>
        </nav>
    </header>

    <main>
        <div class="cabecalhoPaginas">
            <a href="${pageContext.request.contextPath}/autenticacao/login.jsp">
                <img src="${pageContext.request.contextPath}/assets/img/painting-back-icon.svg" alt="Ícone de voltar" class="pincelVoltar">
            </a>
            <div class="tituloPaginas">
                <h1>Telefone</h1>
            </div>
            <img src="${pageContext.request.contextPath}/assets/img/themes-icon.svg" alt="" class="abrirTemas">
        </div>
        
        <div class="componentizacao">
            <search>
                <form action="" method="">
                    <input type="search" id="buscarCrud" class="buscarCrud" name="buscarCrud" placeholder="Pesquisar">
                </form>
            </search>

            <button class="botaoInsert" onclick="create.showModal()">
                Fazer inserção
                <img src="${pageContext.request.contextPath}/assets/img/plus-icon.svg" alt="">
            </button>
        </div>

        <div class="tabelaContainer">
            <table class="tabelaRead">
                <thead>
                    <tr>
                        <th>Ações</th>
                        <th>ID</th>
                        <th>ID do Aluno</th>
                        <th>Nome</th>
                        <th>Número</th>
                        <th>Tipo</th>
                    </tr>
              </thead>
              
              <tbody>
                    <tr>
                        <td class="opcoes">
                            <div>
                                <img src="${pageContext.request.contextPath}/assets/img/update-icon.svg" alt="" class="botaoUpdate" onclick="update.showModal()">
                                <img src="${pageContext.request.contextPath}/assets/img/delete-icon.svg" alt="" class="botaoDelete" onclick="deletes.showModal()">
                            </div>
                        </td>
                        <td>1</td>
                        <td>25</td>
                        <td>Maria Souza</td>
                        <td>(11) 98765-4321</td>
                        <td>Celular/Pessoal</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </main>

    <!-- Create -->
    <dialog class="create" id="create">
        <button class="fecharPopUp" onclick="create.close()">X</button>
        <form action="">
            <label for="createIdAluno">ID do Aluno</label>
            <input type="number" name="createIdAluno" id="createIdAluno">
            
            <label for="createNome">Nome</label>
            <input type="text" name="createNome" id="createNome">

            <label for="createNumero">Número</label>
            <input type="tel" name="createNumero" id="createNumero">

            <label for="createTipo">Tipo</label>
            <input type="text" name="createTipo" id="createTipo">

            <button class="salvarInsercao">Inserir</button>
        </form>
    </dialog>
    
    <!-- Update -->
    <dialog class="update" id="update">
        <button class="fecharPopUp" onclick="update.close()">X</button>
        <form action="">
            <label for="updateIdAluno">ID do Aluno</label>
            <input type="number" name="updateIdAluno" id="updateIdAluno">
            
            <label for="updateNome">Nome do Contato</label>
            <input type="text" name="updateNome" id="updateNome">

            <label for="updateNumero">Número</label>
            <input type="tel" name="updateNumero" id="updateNumero">

            <label for="updateTipo">Tipo</label>
            <input type="text" name="updateTipo" id="updateTipo">

            <button class="salvarAlteracoes">Atualizar</button>
        </form>
    </dialog>

    <!-- Delete -->
    <dialog class="deletes" id="deletes">
        <button class="fecharPopUp" onclick="deletes.close()">X</button>
        <form action="">
            <h2>Deseja realmente excluir?</h2>

            <div>
                <button class="cancelarDeletar" type="button" onclick="deletes.close()">Cancelar</button>
                <button class="deletar">Excluir</button>
            </div>
        </form>
    </dialog>
</body>
</html>