<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>CRUD - Boletim</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/global.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/tokens.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/layout/crud.css">
</head>

<body>

<h2>Boletim</h2>

<!-- MENSAGEM -->
<c:if test="${not empty mensagem}">
    <p>${mensagem}</p>
</c:if>

<!-- BUSCA -->
<form action="${pageContext.request.contextPath}/boletins" method="get">
    <input type="text" name="filtroAluno" placeholder="ID ou nome do aluno">
    <button type="submit">Pesquisar</button>
</form>

<br>

<!-- TABELA -->
<table border="1">
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
            <td>
                <!-- DELETE -->
                <form action="${pageContext.request.contextPath}/boletim-delete" method="post" style="display:inline;">
                    <input type="hidden" name="id" value="${b.id}">
                    <button type="submit">Excluir</button>
                </form>

                <!-- UPDATE -->
                <form action="${pageContext.request.contextPath}/boletim-update" method="post" style="display:inline;">
                    <input type="hidden" name="id" value="${b.id}">
                    <input type="hidden" name="idProfessor" value="${b.idProfessor}">
                    <input type="hidden" name="idAluno" value="${b.idAluno}">
                    <input type="hidden" name="nota1" value="${b.nota1}">
                    <input type="hidden" name="nota2" value="${b.nota2}">
                    <input type="hidden" name="descricao1" value="${b.descricao1}">
                    <input type="hidden" name="descricao2" value="${b.descricao2}">
                    <input type="hidden" name="observacao" value="${b.observacao}">
                    <input type="hidden" name="data" value="${b.dataCriacao}">
                    <button type="submit">Atualizar</button>
                </form>
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

<br>

<!-- CREATE -->
<h3>Novo Boletim</h3>

<form action="${pageContext.request.contextPath}/boletim-create" method="post">

    <input type="number" name="idProfessor" placeholder="ID Professor" required>
    <input type="number" name="idAluno" placeholder="ID Aluno" required>

    <input type="number" step="0.01" name="nota1" placeholder="Nota 1" required>
    <input type="text" name="descricao1" placeholder="Descrição 1">

    <input type="number" step="0.01" name="nota2" placeholder="Nota 2" required>
    <input type="text" name="descricao2" placeholder="Descrição 2">

    <textarea name="observacao" placeholder="Observação"></textarea>

    <input type="date" name="data" required>

    <button type="submit">Cadastrar</button>

</form>

</body>
</html>
