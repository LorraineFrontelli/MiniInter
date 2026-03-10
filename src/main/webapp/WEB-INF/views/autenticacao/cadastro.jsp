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

        <a href="${pageContext.request.contextPath}/login" class="iconeVoltar">
            <img src="${pageContext.request.contextPath}/assets/img/painting-back-icon.svg" alt="Ícone de voltar" class="pincelVoltar">
        </a>
    </header>

    <main>

        <form action="${pageContext.request.contextPath}/aluno-create" method="post" class="formAutenticacao formMatricula">

            <h1 class="tituloMatricula">Fazer matrícula</h1>

            <!-- mensagem de erro -->
            <c:if test="${not empty mensagem}">
                <p style="color:red">${mensagem}</p>
            </c:if>

            <label for="cpf" class="cpf">CPF
                <input type="text" name="cpf" class="inputAutenticacao" placeholder="Insira seu CPF">
            </label>

            <label for="email" class="email">E-mail
                <input type="email" name="email" class="inputAutenticacao" placeholder="Insira seu e-mail">
            </label>

            <label for="senha" class="senha">Senha
                <input type="password" name="senha" class="inputAutenticacao" placeholder="Insira sua senha">
            </label>

            <button type="submit">Enviar</button>

        </form>

    </main>

</body>
</html>