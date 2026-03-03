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
                <li><a href="tab-aluno-professor.jsp" class="pagina ativo"><img src="${pageContext.request.contextPath}/assets/img/schedule-icon.svg" decoding="async" alt="">Aluno Professor</a></li>
                <li><a href="tab-boletim.jsp" class="pagina"><img src="${pageContext.request.contextPath}/assets/img/schedule-icon.svg" decoding="async" alt="">Boletim</a></li>
                <li><a href="tab-telefone.jsp" class="pagina"><img src="${pageContext.request.contextPath}/assets/img/schedule-icon.svg" decoding="async" alt="">Telefone</a></li>
            </ul>
        </nav>
    </header>

    <main>
        <div class="cabecalhoPaginas">
            <a href="${pageContext.request.contextPath}/autenticacao/login.jsp">
                <img src="${pageContext.request.contextPath}/assets/img/painting-back-icon.svg" alt="Ícone de voltar" class="pincelVoltar">
            </a>
            <div class="tituloPaginas">
                <h1>Aluno Professor</h1>
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
                        <th>ID do Professor</th>
                        <th>ID do Aluno</th>
                        <th>Série</th>
                        <th>Turma</th>
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
                        <td>10</td>
                        <td>25</td>
                        <td>2º Ano</td>
                        <td>A</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </main>

    <!-- Create -->
    <dialog class="create" id="create">
        <button class="fecharPopUp" onclick="create.close()">X</button>
        <form action="">
            <div class="colunas">
                <div class="coluna">
                    <label for="createIdProfessor">ID do Professor</label>
                    <input type="number" name="createIdProfessor" id="createIdProfessor">
                
                    <label for="createIdAluno">ID do Aluno</label>
                    <input type="number" name="createIdAluno" id="createIdAluno">
                </div>
                <div class="coluna">
                    <label for="createSerie">Série</label>
                    <input type="number" name="createSerie" id="createSerie">
                    <label for="createTurma">Turma</label>
                    <input type="text" name="createTurma" id="createTurma" maxlength="1">
                </div>
            </div>

            <button class="salvarInsercao">Inserir</button>
        </form>
    </dialog>

    <!-- Update -->
    <dialog class="update" id="update">
        <button class="fecharPopUp" onclick="update.close()">X</button>
        <form action="">
            <div class="colunas">
                <div class="coluna">
                    <label for="updateIdProfessor">ID do Professor</label>
                    <input type="number" name="updateIdProfessor" id="updateIdProfessor">
                
                    <label for="updateIdAluno">ID do Aluno</label>
                    <input type="number" name="updateIdAluno" id="updateIdAluno">
                </div>
                <div class="coluna">
                    <label for="updateSerie">Série</label>
                    <input type="number" name="updateSerie" id="updateSerie">
                    <label for="updateTurma">Turma</label>
                    <input type="text" name="updateTurma" id="updateTurma" maxlength="1">
                </div>
            </div>

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