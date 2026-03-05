<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Professor" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    List<Professor> professores =
            (List<Professor>) request.getAttribute("professores");
%>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>CRUD - Professor</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/css/global.css">
</head>

<body>

<h2>Professor</h2>

<!-- ======================= -->
<!-- MENSAGEM -->
<!-- ======================= -->

<c:if test="${not empty sessionScope.mensagem}">
    <p>${sessionScope.mensagem}</p>
    <c:remove var="mensagem" scope="session"/>
</c:if>

<!-- ======================= -->
<!-- BUSCA -->
<!-- ======================= -->

<form action="${pageContext.request.contextPath}/professores" method="get">
    <input type="text" name="filtroNome" placeholder="Buscar por nome">
    <input type="number" name="filtroId" placeholder="Buscar por ID">
    <button type="submit">Pesquisar</button>
</form>

<hr>

<!-- ======================= -->
<!-- TABELA -->
<!-- ======================= -->

<table border="1">
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

    <%
        if (professores != null) {
            for (Professor p : professores) {
    %>
    <tr>
        <td>

            <!-- UPDATE -->
            <form action="${pageContext.request.contextPath}/professor-update"
                  method="get" style="display:inline;">
                <input type="hidden" name="id" value="<%= p.getId() %>">
                <button type="submit">Editar</button>
            </form>

            <!-- DELETE -->
            <form action="${pageContext.request.contextPath}/professor-delete"
                  method="post" style="display:inline;">
                <input type="hidden" name="id" value="<%= p.getId() %>">
                <button type="submit">Excluir</button>
            </form>

        </td>

        <td><%= p.getId() %></td>
        <td><%= p.getNome() %></td>
        <td><%= p.getEmail() %></td>
        <td><%= p.getMateria() %></td>
        <td><%= p.getDataContratacao() %></td>
        <td><%= p.getUsuario() %></td>
    </tr>
    <%
            }
        }
    %>

    </tbody>
</table>

<hr>

<!-- ======================= -->
<!-- CREATE -->
<!-- ======================= -->

<h3>Cadastrar Professor</h3>

<form action="${pageContext.request.contextPath}/professor-create"
      method="post">

    Nome:
    <input type="text" name="nome" required><br><br>

    Email:
    <input type="email" name="email" required><br><br>

    Senha:
    <input type="password" name="senha" required><br><br>

    Matéria:
    <input type="text" name="materia"><br><br>

    Data Contratação:
    <input type="date" name="data"><br><br>

    Usuário:
    <input type="text" name="usuario"><br><br>

    <button type="submit">Inserir</button>
</form>

</body>
</html>
