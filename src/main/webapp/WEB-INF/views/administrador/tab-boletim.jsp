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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/layout/crud-boletim.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico">
    <script type="module" src="${pageContext.request.contextPath}/assets/js/script.js" defer></script>

</head>

<body>

<div class="meuPlaceholder"></div>

<main>

    <div class="cabecalhoPaginas">
        <a href="${pageContext.request.contextPath}/login-adm">
            <img src="${pageContext.request.contextPath}/assets/img/painting-back-icon.svg"
                 alt="Ícone de voltar" onclick="history.back()" class="pincelVoltar">
        </a>
        <div class="tituloPaginas">
            <h1>Boletim - ${nomeAluno}</h1>
        </div>

        <img src="${pageContext.request.contextPath}/assets/img/themes-icon.svg" class="abrirTemas">

    </div>


    <!-- MENSAGEM -->

    <c:if test="${not empty mensagem}">
        <p style="text-align:center;">${mensagem}</p>
    </c:if>


    <!-- BUSCA + BOTÃO -->

    <div class="componentizacao">

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
                <th>Matéria</th>
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
                    <td>${b.materia}</td>
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

            <input type="hidden" name="idAluno" value="${idAluno}">

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
