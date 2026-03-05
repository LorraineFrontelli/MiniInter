<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Telefone" %>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>CRUD - Telefone</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/global.css">
</head>

<body>

<h1>Telefones</h1>

<!-- ================= MENSAGEM ================= -->
<%
    String mensagem = (String) request.getAttribute("mensagem");
    if (mensagem != null) {
%>
<p><strong><%= mensagem %></strong></p>
<%
    }
%>

<!-- ================= BUSCA ================= -->
<form action="${pageContext.request.contextPath}/telefones" method="get">
    ID: <input type="text" name="filtroId">
    ID Aluno: <input type="text" name="filtroAluno">
    Número: <input type="text" name="filtroNumero">
    <button type="submit">Buscar</button>
</form>

<hr>

<!-- ================= TABELA ================= -->
<table border="1">
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

        if (telefones != null) {
            for (Telefone t : telefones) {
    %>
    <tr>
        <td>

            <!-- DELETE -->
            <form action="${pageContext.request.contextPath}/telefone-delete"
                  method="post" style="display:inline;">
                <input type="hidden" name="id" value="<%= t.getId() %>">
                <button type="submit">Excluir</button>
            </form>

        </td>

        <td><%= t.getId() %></td>
        <td><%= t.getIdAluno() %></td>
        <td><%= t.getNumero() %></td>
        <td><%= t.getTipo() %></td>
    </tr>
    <%
            }
        }
    %>
    </tbody>
</table>

<hr>

<!-- ================= INSERIR ================= -->
<h2>Novo Telefone</h2>

<form action="${pageContext.request.contextPath}/telefone-create" method="post">

    ID Aluno:
    <input type="number" name="idAluno" required><br><br>

    Número:
    <input type="text" name="numero" required><br><br>

    Tipo:
    <input type="text" name="tipo" required><br><br>

    <button type="submit">Inserir</button>
</form>

<hr>

<!-- ================= ATUALIZAR ================= -->
<h2>Atualizar Telefone</h2>

<form action="${pageContext.request.contextPath}/telefone-update" method="post">

    ID:
    <input type="number" name="id" required><br><br>

    Número:
    <input type="text" name="numero" required><br><br>

    Tipo:
    <input type="text" name="tipo" required><br><br>

    <button type="submit">Atualizar</button>
</form>

</body>
</html>
