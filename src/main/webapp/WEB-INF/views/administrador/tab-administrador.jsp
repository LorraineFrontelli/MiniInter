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
    <img src="${pageContext.request.contextPath}/assets/img/monart-logo.svg" decoding="async" alt="" class="logoMonart">
    <nav>
        <ul>
            <li><a href="${pageContext.request.contextPath}/administradores" class="pagina ativo">
                <img src="${pageContext.request.contextPath}/assets/img/schedule-icon.svg" decoding="async" alt="">Administrador</a></li>
            <li><a href="${pageContext.request.contextPath}/alunos" class="pagina">
                <img src="${pageContext.request.contextPath}/assets/img/schedule-icon.svg" decoding="async" alt="">Aluno</a></li>
            <li><a href="${pageContext.request.contextPath}/professores" class="pagina">
                <img src="${pageContext.request.contextPath}/assets/img/schedule-icon.svg" decoding="async" alt="">Professor</a></li>
            <li><a href="${pageContext.request.contextPath}/boletins" class="pagina">
                <img src="${pageContext.request.contextPath}/assets/img/schedule-icon.svg" decoding="async" alt="">Boletim</a></li>
            <li><a href="${pageContext.request.contextPath}/telefones" class="pagina">
                <img src="${pageContext.request.contextPath}/assets/img/schedule-icon.svg" decoding="async" alt="">Telefone</a></li>
        </ul>
    </nav>
</header>

<main>
    <div class="cabecalhoPaginas">
        <a href="${pageContext.request.contextPath}/login-adm">
            <img src="${pageContext.request.contextPath}/assets/img/painting-back-icon.svg" alt="Ícone de voltar" class="pincelVoltar">
        </a>
        <div class="tituloPaginas">
            <h1>Administrador</h1>
        </div>
        <img src="${pageContext.request.contextPath}/assets/img/themes-icon.svg" alt="" class="abrirTemas">
    </div>

    <!-- Mensagem -->
    <c:if test="${not empty mensagem}">
        <p style="color: green; text-align:center;">${mensagem}</p>
    </c:if>

    <div class="componentizacao">
        <search>
            <form action="${pageContext.request.contextPath}/administradores" method="get">
                <input type="search" id="buscarCrud" class="buscarCrud"
                       name="filtroLogin" placeholder="Pesquisar">
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
                <th>Login</th>
                <th>Senha</th>
                <th>CPF do Aluno</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach var="admin" items="${administradores}">
                <tr>
                    <td class="opcoes">
                        <div>
                            <!-- Update -->
                            <button type="button"
                                    onclick="abrirUpdate('${admin.id}','${admin.login}')">
                                <img src="${pageContext.request.contextPath}/assets/img/update-icon.svg" alt="">
                            </button>

                            <!-- Delete -->
                            <button type="button"
                                    onclick="abrirDelete('${admin.id}')">
                                <img src="${pageContext.request.contextPath}/assets/img/delete-icon.svg" alt="">
                            </button>
                        </div>
                    </td>
                    <td>${admin.id}</td>
                    <td>${admin.login}</td>
                    <td>********</td>
                    <td>
                        <c:forEach var="cpf" items="${admin.alunoCpf}">
                            ${cpf}<br>
                        </c:forEach>
                    </td>
                </tr>
            </c:forEach>
            </tbody>

        </table>
    </div>
</main>

<!-- Create -->
<dialog class="create" id="create">
    <button class="fecharPopUp" onclick="create.close()">X</button>
    <form action="${pageContext.request.contextPath}/admin-create" method="post">
        <label>Login</label>
        <input type="text" name="login">

        <label>Senha</label>
        <input type="password" name="senha" min="8">

            <label for="createCpfAluno">CPF do Aluno (Arquivo Excel .xlsx)</label>
            <input type="file" name="alunoCpf" id="createCpf" accept=".xlsx">

        <button class="salvarInsercao" type="submit">Inserir</button>
    </form>
</dialog>

<!-- Update -->
<dialog class="update" id="update">
    <button class="fecharPopUp" onclick="update.close()">X</button>
    <form action="${pageContext.request.contextPath}/admin-update" method="post">
        <input type="hidden" name="id" id="updateId">

        <label>Login</label>
        <input type="text" name="login" id="updateLogin">

        <label>Senha</label>
        <input type="password" name="senha" min="8">

            <label for="updateCpfAluno">CPF do Aluno (Arquivo Excel .xlsx)</label>
            <input type="file" name="alunoCpf" id="updateCpf" accept=".xlsx">

        <button class="salvarAlteracoes" type="submit">Atualizar</button>
    </form>
</dialog>

<!-- Delete -->
<dialog class="deletes" id="deletes">
    <button class="fecharPopUp" onclick="deletes.close()">X</button>
    <form action="${pageContext.request.contextPath}/admin-delete" method="post">
        <input type="hidden" name="id" id="deleteId">

        <h2>Deseja realmente excluir?</h2>
        <div>
            <button class="cancelarDeletar" type="button" onclick="deletes.close()">Cancelar</button>
            <button class="deletar" type="submit">Excluir</button>
        </div>
    </form>
</dialog>

<script>
    function abrirUpdate(id, login) {
        document.getElementById("updateId").value = id;
        document.getElementById("updateLogin").value = login;
        update.showModal();
    }

    function abrirDelete(id) {
        document.getElementById("deleteId").value = id;
        deletes.showModal();
    }
</script>

</body>
</html>
