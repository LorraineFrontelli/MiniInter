<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>CRUD - Boletim</title>

    <script>
        document.documentElement.style.setProperty("--tema", localStorage.getItem("corTema") || "#242021");
    </script>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/global.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/tokens.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/layout/crud.css">

</head>

<body>

<div class="meuPlaceholder"></div>

<header class="headerLateral">

    <img src="${pageContext.request.contextPath}/assets/img/monart-logo.svg" class="logoMonart">

    <nav>
        <ul>

            <li>
                <a href="${pageContext.request.contextPath}/administradores" class="pagina">
                    <img src="${pageContext.request.contextPath}/assets/img/schedule-icon.svg">
                    Administrador
                </a>
            </li>

            <li>
                <a href="${pageContext.request.contextPath}/alunos" class="pagina">
                    <img src="${pageContext.request.contextPath}/assets/img/schedule-icon.svg">
                    Aluno
                </a>
            </li>

            <li>
                <a href="${pageContext.request.contextPath}/professores" class="pagina">
                    <img src="${pageContext.request.contextPath}/assets/img/schedule-icon.svg">
                    Professor
                </a>
            </li>

            <li>
                <a href="${pageContext.request.contextPath}/boletins" class="pagina ativo">
                    <img src="${pageContext.request.contextPath}/assets/img/schedule-icon.svg">
                    Boletim
                </a>
            </li>

            <li>
                <a href="${pageContext.request.contextPath}/telefones" class="pagina">
                    <img src="${pageContext.request.contextPath}/assets/img/schedule-icon.svg">
                    Telefone
                </a>
            </li>

        </ul>
    </nav>

</header>

<main>

    <div class="cabecalhoPaginas">

        <div class="tituloPaginas">
            <h1>Boletim</h1>
        </div>

        <img src="${pageContext.request.contextPath}/assets/img/themes-icon.svg" class="abrirTemas">

    </div>


    <!-- MENSAGEM -->

    <c:if test="${not empty mensagem}">
        <p style="text-align:center;">${mensagem}</p>
    </c:if>


    <!-- BUSCA + BOTÃO -->

    <div class="componentizacao">

        <search>
            <form action="${pageContext.request.contextPath}/boletins" method="get">

                <input
                        type="search"
                        class="buscarCrud"
                        name="filtroAluno"
                        placeholder="Pesquisar aluno"
                >

            </form>
        </search>

        <button class="botaoInsert" onclick="create.showModal()">
            Inserir
            <img src="${pageContext.request.contextPath}/assets/img/plus-icon.svg">
        </button>

    </div>


    <!-- TABELA -->

    <div class="tabelaContainer">

        <table class="tabelaRead">

            <thead>

            <tr>

                <th>Ações</th>
                <th>ID</th>
                <th>ID Professor</th>
                <th>ID Aluno</th>
                <th>Nota 1</th>
                <th>Nota 2</th>
                <th>Situação</th>
                <th>Data</th>

            </tr>

            </thead>


            <tbody>

            <c:forEach var="b" items="${boletins}">

                <tr>

                    <td class="opcoes">

                        <div>

                            <!-- UPDATE -->

                            <form action="${pageContext.request.contextPath}/boletim-update" method="post">

                                <input type="hidden" name="id" value="${b.id}">
                                <input type="hidden" name="idProfessor" value="${b.idProfessor}">
                                <input type="hidden" name="idAluno" value="${b.idAluno}">
                                <input type="hidden" name="nota1" value="${b.nota1}">
                                <input type="hidden" name="nota2" value="${b.nota2}">
                                <input type="hidden" name="descricao1" value="${b.descricao1}">
                                <input type="hidden" name="descricao2" value="${b.descricao2}">
                                <input type="hidden" name="observacao" value="${b.observacao}">
                                <input type="hidden" name="data" value="${b.dataCriacao}">

                                <button type="submit" style="background:none;border:none;">
                                    <img src="${pageContext.request.contextPath}/assets/img/update-icon.svg">
                                </button>

                            </form>


                            <!-- DELETE -->

                            <form action="${pageContext.request.contextPath}/boletim-delete" method="post">

                                <input type="hidden" name="id" value="${b.id}">

                                <button type="submit" style="background:none;border:none;">
                                    <img src="${pageContext.request.contextPath}/assets/img/delete-icon.svg">
                                </button>

                            </form>

                        </div>

                    </td>

                    <td>${b.id}</td>
                    <td>${b.idProfessor}</td>
                    <td>${b.idAluno}</td>
                    <td>${b.nota1}</td>
                    <td>${b.nota2}</td>
                    <td>${b.aprovado ? "Aprovado" : "Reprovado"}</td>
                    <td>${b.dataCriacao}</td>

                </tr>

            </c:forEach>

            </tbody>

        </table>

    </div>



    <!-- MODAL CREATE -->

    <dialog id="create" class="create">

        <button class="fecharPopUp" onclick="create.close()">X</button>

        <form action="${pageContext.request.contextPath}/boletim-create" method="post">

            <label>ID Professor</label>
            <input type="number" name="idProfessor" required>

            <label>ID Aluno</label>
            <input type="number" name="idAluno" required>

            <label>Nota 1</label>
            <input type="number" step="0.01" name="nota1" required>

            <label>Descrição 1</label>
            <input type="text" name="descricao1">

            <label>Nota 2</label>
            <input type="number" step="0.01" name="nota2" required>

            <label>Descrição 2</label>
            <input type="text" name="descricao2">

            <label>Observação</label>
            <textarea name="observacao"></textarea>

            <label>Data</label>
            <input type="date" name="data" required>

            <button class="salvarInsercao" type="submit">
                Cadastrar
            </button>

        </form>

    </dialog>

</main>

</body>
</html>
