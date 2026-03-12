<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Telefone" %>

<!DOCTYPE html>

<html lang="pt-BR">

<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>CRUD - Telefone</title>

    <script>
        document.documentElement.style.setProperty("--tema", localStorage.getItem("corTema") || "#242021");
    </script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/global.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/tokens.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/layout/crud.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico">
    <script type="module" src="${pageContext.request.contextPath}/assets/js/script.js" defer></script>

</head>

<body>

<div class="meuPlaceholder"></div>

<header class="headerLateral">

    <img src="${pageContext.request.contextPath}/assets/img/monart-logo.svg" class="logoMonart">

    <nav>
        <ul>
            <li><a href="${pageContext.request.contextPath}/administradores" class="pagina">
                <img src="${pageContext.request.contextPath}/assets/img/admin-icon.svg" decoding="async" alt="">Administrador</a></li>
            <li><a href="${pageContext.request.contextPath}/alunos" class="pagina">
                <img src="${pageContext.request.contextPath}/assets/img/student-icon.svg" decoding="async" alt="">Aluno</a></li>
            <li><a href="${pageContext.request.contextPath}/professores" class="pagina">
                <img src="${pageContext.request.contextPath}/assets/img/teacher-icon.svg" decoding="async" alt="">Professor</a></li>
            <li><a href="${pageContext.request.contextPath}/boletins" class="pagina">
                <img src="${pageContext.request.contextPath}/assets/img/bulletin-icon.svg" decoding="async" alt="">Boletim</a></li>
            <li><a href="${pageContext.request.contextPath}/telefones" class="pagina ativo">
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
            <h1>Telefone</h1>
        </div>

        <img src="${pageContext.request.contextPath}/assets/img/themes-icon.svg" class="abrirTemas">

    </div>

    <!-- MENSAGEM -->

    <%
        String mensagem = (String) request.getAttribute("mensagem");
        if(mensagem != null){
    %>

    <p style="text-align:center;"><%=mensagem%></p>

    <%
        }
    %>

    <!-- BUSCA -->

    <div class="componentizacao">
        <search>
            <form action="${pageContext.request.contextPath}/telefones" method="get">
                <input type="search" class="buscarCrud" name="filtroNome" placeholder="Pesquisar por nome">
            </form>
        </search>

        <button class="botaoInsert" onclick="create.showModal()">
            Fazer inserção <img src="${pageContext.request.contextPath}/assets/img/plus-icon.svg">
        </button>
    </div>

    <!-- TABELA -->

    <div class="tabelaContainer">

        <table class="tabelaRead">

            <thead>

            <tr>

                <th>Ações</th>
                <th>ID</th>
                <th>ID Aluno</th>
                <th>Número</th>
                <th>Tipo</th>

            </tr>

            </thead>

            <tbody>

            <%

                List<Telefone> telefones = (List<Telefone>) request.getAttribute("telefones");

                if(telefones != null){
                    for(Telefone t : telefones){

            %>

            <tr>

                <td class="opcoes">

                    <div>

                        <!-- UPDATE -->

                        <button
                                type="button"
                                class="botaoUpdate"
                                onclick="abrirUpdate(
                                        '<%=t.getId()%>',
                                        '<%=t.getIdAluno()%>',
                                        '<%=t.getNumero()%>',
                                        '<%=t.getTipo()%>'
                                        )">

                            <img src="${pageContext.request.contextPath}/assets/img/update-icon.svg">

                        </button>

                        <!-- DELETE -->

                        <form action="${pageContext.request.contextPath}/telefone-delete" method="post">

                            <input type="hidden" name="id" value="<%=t.getId()%>">

                            <button type="submit" style="background:none;border:none;">
                                <img src="${pageContext.request.contextPath}/assets/img/delete-icon.svg">
                            </button>

                        </form>

                    </div>

                </td>

                <td><%=t.getId()%></td>
                <td><%=t.getIdAluno()%></td>
                <td><%=t.getNumero()%></td>
                <td><%=t.getTipo()%></td>

            </tr>

            <%
                    }
                }
            %>

            </tbody>

        </table>

    </div>

    <!-- MODAL CREATE -->

    <dialog id="create" class="create">

        <button class="fecharPopUp" onclick="create.close()">X</button>

        <form action="${pageContext.request.contextPath}/telefone-create" method="post">

            <label>ID Aluno</label> <input type="number" name="idAluno" required>

            <label>Número</label> <input type="text" name="numero" required>

            <label>Tipo</label> <input type="text" name="tipo" required>

            <button class="salvarInsercao" type="submit">
                Cadastrar
            </button>

        </form>

    </dialog>

    <!-- MODAL UPDATE -->

    <dialog id="update" class="create">

        <button class="fecharPopUp" onclick="update.close()">X</button>

        <form action="${pageContext.request.contextPath}/telefone-update" method="post">

            <input type="hidden" name="id" id="u_id">

            <label>ID Aluno</label> <input type="number" name="idAluno" id="u_idAluno" required>

            <label>Número</label> <input type="text" name="numero" id="u_numero" required>

            <label>Tipo</label> <input type="text" name="tipo" id="u_tipo" required>

            <button class="salvarInsercao" type="submit">
                Atualizar
            </button>

        </form>

    </dialog>

</main>

<script>

    function abrirUpdate(id,idAluno,numero,tipo){

        document.getElementById("u_id").value = id
        document.getElementById("u_idAluno").value = idAluno
        document.getElementById("u_numero").value = numero
        document.getElementById("u_tipo").value = tipo

        update.showModal()

    }

</script>

</body>
</html>
