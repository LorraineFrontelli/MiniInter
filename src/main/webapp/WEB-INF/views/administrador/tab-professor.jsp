<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html lang="pt-BR">

<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>CRUD - Professor</title>

    <script>
        document.documentElement.style.setProperty("--tema", localStorage.getItem("corTema") || "#242021");
    </script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/global.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/tokens.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/layout/crud.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico">

</head>

<body>

<div class="meuPlaceholder"></div>

<header class="headerLateral">

    <img src="${pageContext.request.contextPath}/assets/img/monart-logo.svg" class="logoMonart">

    <nav>
        <ul>
            <li><a href="${pageContext.request.contextPath}/administradores" class="pagina ativo">
                <img src="${pageContext.request.contextPath}/assets/img/admin-icon.svg" decoding="async" alt="">Administrador</a></li>
            <li><a href="${pageContext.request.contextPath}/alunos" class="pagina">
                <img src="${pageContext.request.contextPath}/assets/img/student-icon.svg" decoding="async" alt="">Aluno</a></li>
            <li><a href="${pageContext.request.contextPath}/professores" class="pagina">
                <img src="${pageContext.request.contextPath}/assets/img/teacher-icon.svg" decoding="async" alt="">Professor</a></li>
            <li><a href="${pageContext.request.contextPath}/telefones" class="pagina">
                <img src="${pageContext.request.contextPath}/assets/img/telephone-icon.svg" decoding="async" alt="">Telefone</a></li>
        </ul>
    </nav>

</header>

<main>

    <div class="cabecalhoPaginas">

        <a href="${pageContext.request.contextPath}/login-adm">
            <img src="${pageContext.request.contextPath}/assets/img/painting-back-icon.svg" class="pincelVoltar">
        </a>

        <div class="tituloPaginas">
            <h1>Professor</h1>
        </div>

        <img src="${pageContext.request.contextPath}/assets/img/themes-icon.svg" class="abrirTemas">

    </div>

    <c:if test="${not empty mensagem}">

        <p style="text-align:center;">${mensagem}</p>
    </c:if>

    <div class="componentizacao">

        <search>

            <form action="${pageContext.request.contextPath}/professores" method="get">

                <input
                        type="search"
                        class="buscarCrud"
                        name="filtroNome"
                        placeholder="Pesquisar professor">

            </form>

        </search>

        <button class="botaoInsert" onclick="create.showModal()">
            Inserir
            <img src="${pageContext.request.contextPath}/assets/img/plus-icon.svg">
        </button>

    </div>

    <div class="tabelaContainer">

        <table class="tabelaRead">

            <thead>

            <tr>

                <th>Ações</th>
                <th>ID</th>
                <th>Nome</th>
                <th>Email</th>
                <th>Matéria</th>
                <th>Data Contratação</th>
                <th>Usuário</th>

            </tr>

            </thead>

            <tbody>

            <c:forEach var="p" items="${professores}">

                <tr>

                    <td class="opcoes">

                        <div>

                            <button type="button"
                                    class="botaoUpdate"
                                    onclick="abrirUpdate(
                                            '${p.id}',
                                            '${p.nome}',
                                            '${p.email}',
                                            '${p.senha}',
                                            '${p.materia}',
                                            '${p.usuario}',
                                            '${p.dataContratacao}'
                                            )"> <img src="${pageContext.request.contextPath}/assets/img/update-icon.svg"> </button>

                            <form action="${pageContext.request.contextPath}/professor-delete" method="post">

                                <input type="hidden" name="id" value="${p.id}">

                                <button type="submit" style="background:none;border:none;">
                                    <img src="${pageContext.request.contextPath}/assets/img/delete-icon.svg">
                                </button>

                            </form>

                        </div>

                    </td>

                    <td>${p.id}</td>
                    <td>${p.nome}</td>
                    <td>${p.email}</td>
                    <td>${p.materia}</td>
                    <td>${p.dataContratacao}</td>
                    <td>${p.usuario}</td>

                </tr>

            </c:forEach>

            </tbody>

        </table>

    </div>

    <!-- MODAL CREATE -->

    <dialog id="create" class="create">

        <button class="fecharPopUp" onclick="create.close()">X</button>

        <form action="${pageContext.request.contextPath}/professor-create" method="post">

            <div class="colunas">

                <div class="coluna">

                    <label>Nome</label> <input type="text" name="nome" required>

                    <label>Email</label> <input type="email" name="email" required>

                    <label>Senha</label> <input type="password" name="senha" required>

                    <label>Matéria</label> <input type="text" name="materia">

                </div>

                <div class="coluna">

                    <label>Data Contratação</label> <input type="date" name="data">

                    <label>Usuário</label> <input type="text" name="usuario">

                </div>

            </div>

            <button class="salvarInsercao" type="submit">
                Cadastrar
            </button>

        </form>

    </dialog>

    <!-- MODAL UPDATE -->

    <dialog id="update" class="create">

        <button class="fecharPopUp" onclick="update.close()">X</button>

        <form action="${pageContext.request.contextPath}/professor-update" method="post">

            <input type="hidden" name="id" id="u_id">

            <div class="colunas">

                <div class="coluna">

                    <label>Nome</label> <input type="text" name="nome" id="u_nome" required>

                    <label>Email</label> <input type="email" name="email" id="u_email" required>

                    <label>Senha</label> <input type="password" name="senha" id="u_senha" required>

                    <label>Matéria</label> <input type="text" name="materia" id="u_materia">

                </div>

                <div class="coluna">

                    <label>Data Contratação</label> <input type="date" name="data" id="u_data">

                    <label>Usuário</label> <input type="text" name="usuario" id="u_usuario">

                </div>

            </div>

            <button class="salvarInsercao" type="submit">
                Atualizar
            </button>

        </form>

    </dialog>

</main>

<script>

    function abrirUpdate(id,nome,email,senha,materia,usuario,data){

        document.getElementById("u_id").value = id
        document.getElementById("u_nome").value = nome
        document.getElementById("u_email").value = email
        document.getElementById("u_senha").value = senha
        document.getElementById("u_materia").value = materia
        document.getElementById("u_usuario").value = usuario
        document.getElementById("u_data").value = data

        update.showModal()

    }

</script>

</body>
</html>
