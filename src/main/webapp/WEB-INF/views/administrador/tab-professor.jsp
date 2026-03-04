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
                <li><a href="tab-professor.jsp" class="pagina ativo"><img src="${pageContext.request.contextPath}/assets/img/schedule-icon.svg" decoding="async" alt="">Professor</a></li>
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
                <h1>Professor</h1>
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
                        <th>Nome</th>
                        <th>E-mail</th>
                        <th>Senha</th>
                        <th>Matéria</th>
                        <th>Turmas</th>
                        <th>Data de Contratação</th>
                        <th>Usuário</th>
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
                        <td>Leonardo da Vinci</td>
                        <td>leo.vinci@monart.com</td>
                        <td>********</td>
                        <td>Pintura Renascentista</td>
                        <td><img src="${pageContext.request.contextPath}/assets/img/see-more-icon.svg" alt="" class="botaoVerTurmas" onclick="verTurmas.showModal()"></td>
                        <td>15/04/2023</td>
                        <td>davinci_art</td>
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
                    <label for="createNome">Nome</label>
                    <input type="text" name="createNome" id="createNome">
                
                    <label for="createEmail">E-mail</label>
                    <input type="email" name="createEmail" id="createEmail">
                
                    <label for="createSenha">Senha</label>
                    <input type="password" name="createSenha" id="createSenha">
                    
                    <label for="createMateria">Matéria</label>
                    <input type="text" name="createMateria" id="createMateria">
                </div>
                
                <div class="coluna">
                    
                    <label for="createDtContratacao">Data Contratação</label>
                    <input type="date" name="createDtContratacao" id="createDtContratacao">
                
                    <label for="createUsuario">Usuário</label>
                    <input type="text" name="createUsuario" id="createUsuario">

                    <details class="turmas">
                        <summary>Turmas do professor</summary>
                      
                        <div class="turmasSelecao">
                            <label class="turmaItem" for="createTurmas">
                                <input type="checkbox" name="createTurmas" id="createTurmas" value="1A">
                                <span>1º A</span>
                            </label>
                        
                            <label class="turmaItem" for="createTurmas">
                                <input type="checkbox" name="createTurmas" id="createTurmas" value="1B">
                                <span>1º B</span>
                            </label>
                        
                            <label class="turmaItem" for="createTurmas">
                                <input type="checkbox" name="createTurmas" id="createTurmas" value="2A">
                                <span>2º A</span>
                            </label>
                      
                            <label class="turmaItem" for="createTurmas">
                                <input type="checkbox" name="createTurmas" id="createTurmas" value="2B">
                                <span>2º B</span>
                            </label>
                      
                            <label class="turmaItem" for="createTurmas">
                                <input type="checkbox" name="createTurmas" id="createTurmas" value="3A">
                                <span>3º A</span>
                            </label>
                        </div>
                    </details>
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
                    <label for="updateNome">Nome</label>
                    <input type="text" name="updateNome" id="updateNome">

                    <label for="updateEmail">E-mail</label>
                    <input type="email" name="updateEmail" id="updateEmail">
                    
                    <label for="updateSenha">Senha</label>
                    <input type="password" name="updateSenha" id="updateSenha">

                    <label for="updateMateria">Matéria</label>
                    <input type="text" name="updateMateria" id="updateMateria">
                </div>
                
                <div class="coluna">
                    <label for="updateDtContratacao">Data Contratação</label>
                    <input type="date" name="updateDtContratacao" id="updateDtContratacao">

                    <label for="updateUsuario">Usuário</label>
                    <input type="text" name="updateUsuario" id="updateUsuario">

                    <details class="turmas">
                        <summary>Turmas do professor</summary>
                      
                        <div class="turmasSelecao">
                            <label class="turmaItem" for="updateTurmas">
                                <input type="checkbox" name="updateTurmas" id="updateTurmas" value="1A">
                                <span>1º A</span>
                            </label>
                        
                            <label class="turmaItem" for="updateTurmas">
                                <input type="checkbox" name="updateTurmas" id="updateTurmas" value="1B">
                                <span>1º B</span>
                            </label>
                        
                            <label class="turmaItem" for="updateTurmas">
                                <input type="checkbox" name="updateTurmas" id="updateTurmas" value="2A">
                                <span>2º A</span>
                            </label>
                      
                            <label class="turmaItem" for="updateTurmas">
                                <input type="checkbox" name="updateTurmas" id="updateTurmas" value="2B">
                                <span>2º B</span>
                            </label>
                      
                            <label class="turmaItem" for="updateTurmas">
                                <input type="checkbox" name="updateTurmas" id="updateTurmas" value="3A">
                                <span>3º A</span>
                            </label>
                        </div>
                    </details>
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

    <!-- Modal Ver Turmas -->
    <dialog class="verTurmas" id="verTurmas">
        <button class="fecharPopUp" onclick="verTurmas.close()">X</button>



    </dialog>
</body>
</html>