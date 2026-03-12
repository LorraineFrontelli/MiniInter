<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <script>document.documentElement.style.setProperty("--tema", localStorage.getItem("corTema") || "#242021");</script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/global.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/tokens.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/layout/crud.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico">
    <script type="module" src="${pageContext.request.contextPath}/assets/js/script.js" defer></script>

    <title>CRUD - Monart</title>
</head>

<body>
    <div class="meuPlaceholder"></div>

    <header class="headerLateral">
        <img src="${pageContext.request.contextPath}/assets/img/monart-logo.svg" class="logoMonart">
        <nav>
            <ul>
                <li><a href="${pageContext.request.contextPath}/administradores" class="pagina">
                    <img src="${pageContext.request.contextPath}/assets/img/admin-icon.svg" decoding="async" alt="">Administrador</a></li>
                <li><a href="${pageContext.request.contextPath}/alunos" class="pagina ativo">
                    <img src="${pageContext.request.contextPath}/assets/img/student-icon.svg" decoding="async" alt="">Aluno</a></li>
                <li><a href="${pageContext.request.contextPath}/professores" class="pagina">
                    <img src="${pageContext.request.contextPath}/assets/img/teacher-icon.svg" decoding="async" alt="">Professor</a></li>
                <li><a href="${pageContext.request.contextPath}/boletins" class="pagina">
                    <img src="${pageContext.request.contextPath}/assets/img/bulletin-icon.svg" decoding="async" alt="">Boletim</a></li>
                <li><a href="${pageContext.request.contextPath}/telefones" class="pagina">
                    <img src="${pageContext.request.contextPath}/assets/img/telephone-icon.svg" decoding="async" alt="">Telefone</a></li>
            </ul>
        </nav>
    </header>

    <main>
        <div class="cabecalhoPaginas">
            <a href="${pageContext.request.contextPath}/login-adm">
                <img src="${pageContext.request.contextPath}/assets/img/painting-back-icon.svg" alt="Ícone de voltar" class="pincelVoltar">
            </a>
            
            <div class="tituloPaginas">
                <h1>Aluno</h1>
            </div>
            
            <img src="${pageContext.request.contextPath}/assets/img/themes-icon.svg" class="abrirTemas">

        </div>

        <!-- Mensagem -->
        <c:if test="${not empty mensagem}">
            <p style="text-align:center; color:green;">${mensagem}</p>
        </c:if>

        <div class="componentizacao">
            <search>
                <form action="${pageContext.request.contextPath}/alunos" method="get">
                    <input type="search" class="buscarCrud" name="filtroNome" placeholder="Pesquisar por nome">
                </form>
            </search>

            <button class="botaoInsert" onclick="create.showModal()">
                Fazer inserção <img src="${pageContext.request.contextPath}/assets/img/plus-icon.svg">
            </button>
        </div>

        <div class="tabelaContainer">
            <table class="tabelaRead">
                <thead>
                <tr>
                    <th>Ações</th>
                    <th>Matrícula</th>
                    <th>Nome</th>
                    <th>E-mail</th>
                    <th>Senha</th>
                    <th>CPF</th>
                    <th>Data de início</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="aluno" items="${alunos}">
                    <tr>
                        <td class="opcoes">
                            <div>

                                <!-- UPDATE -->
                                <button type="button"
                                        onclick="abrirUpdate('${aluno.matricula}','${aluno.nome}','${aluno.email}','${aluno.cpf}')">
                                    <img src="${pageContext.request.contextPath}/assets/img/update-icon.svg">
                                </button>

                                <!-- DELETE -->
                                <button type="button"
                                        onclick="abrirDelete('${aluno.matricula}')">
                                    <img src="${pageContext.request.contextPath}/assets/img/delete-icon.svg">
                                </button>

                            </div>
                        </td>

                        <td>${aluno.matricula}</td>
                        <td>${aluno.nome}</td>
                        <td>${aluno.email}</td>
                        <td>********</td>
                        <td>${aluno.cpf}</td>
                        <td>${aluno.dataInicio}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </main>

    <!-- CREATE -->
    <dialog class="create" id="create">
        <button class="fecharPopUp" onclick="create.close()">X</button>

        <form action="${pageContext.request.contextPath}/aluno-create" method="post">
            <input type="hidden" name="acao" value="cadastrar">

            <div class="colunas">
                <div class="coluna">
                    <label>Nome</label>
                    <input type="text" name="nome">

                    <label>E-mail</label>
                    <input type="email" name="email">

                    <label>Senha</label>
                    <input type="password" name="senha">

                    <label>CPF</label>
                    <input type="text" name="cpf">
                </div>

                <div class="coluna">
                    <label>Matrícula</label>
                    <input type="number" name="matricula">

                    <label>Data de Início</label>
                    <input type="date" name="dataInicio">
                </div>
            </div>

            <button class="salvarInsercao" type="submit">Inserir</button>
        </form>
    </dialog>

    <!-- UPDATE -->
    <dialog class="update" id="update">
        <button class="fecharPopUp" onclick="update.close()">X</button>

        <form action="${pageContext.request.contextPath}/aluno-update" method="post">
            <input type="hidden" name="matricula" id="updateMatricula">

            <div class="colunas">
                <div class="coluna">
                    <label>Nome</label>
                    <input type="text" name="nome" id="updateNome">

                    <label>E-mail</label>
                    <input type="email" name="email" id="updateEmail">

                    <label>Senha</label>
                    <input type="password" name="senha">

                    <label>CPF</label>
                    <input type="text" name="cpf" id="updateCpf">
                </div>

                <div class="coluna">
                    <label>Data de Início</label>
                    <input type="date" name="dataInicio">
                </div>
            </div>

            <button class="salvarAlteracoes" type="submit">Atualizar</button>
        </form>
    </dialog>

    <!-- DELETE -->
    <dialog class="deletes" id="deletes">
        <button class="fecharPopUp" onclick="deletes.close()">X</button>

        <form action="${pageContext.request.contextPath}/aluno-delete" method="post">
            <input type="hidden" name="matricula" id="deleteMatricula">

            <h2>Deseja realmente excluir?</h2>
            <div>
                <button type="button" onclick="deletes.close()">Cancelar</button>
                <button type="submit">Excluir</button>
            </div>
        </form>
    </dialog>

    <script>
        function abrirUpdate(matricula, nome, email, cpf){
            document.getElementById("updateMatricula").value = matricula;
            document.getElementById("updateNome").value = nome;
            document.getElementById("updateEmail").value = email;
            document.getElementById("updateCpf").value = cpf;
            update.showModal();
        }

        function abrirDelete(matricula){
            document.getElementById("deleteMatricula").value = matricula;
            deletes.showModal();
        }
    </script>
</body>
</html>
