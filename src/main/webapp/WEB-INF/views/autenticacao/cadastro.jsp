<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/global.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/tokens.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/layout/autenticacao.css">

    <title>Cadastro - Monart</title>
</head>

<body>

<header class="headerAutenticacao">
    <img src="${pageContext.request.contextPath}/assets/img/monart-logo.svg" class="logoMonart">
</header>

<main>

    <form action="${pageContext.request.contextPath}/aluno-create" method="post" class="formAutenticacao formMatricula">

        <h1>Fazer matrícula</h1>

        <!-- mensagem de erro -->
        <c:if test="${not empty mensagem}">
            <p style="color:red">${mensagem}</p>
        </c:if>

        <label>CPF
            <input type="text" name="cpf" class="inputAutenticacao" placeholder="Insira seu CPF">
        </label>

        <label>E-mail
            <input type="email" name="email" class="inputAutenticacao" placeholder="Insira seu e-mail">
        </label>

        <label>Senha
            <input type="password" name="senha" class="inputAutenticacao" placeholder="Insira sua senha">
        </label>

        <button type="submit">Enviar</button>

    </form>

</main>

</body>
</html>