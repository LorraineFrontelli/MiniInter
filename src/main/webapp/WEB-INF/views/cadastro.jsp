<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">

<head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/layout/autenticacao.css">
   <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/global.css">
   <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico">
   <script src="${pageContext.request.contextPath}/assets/js/script.js" defer></script>
   <title>Cadastro - Monart</title>
</head>

<body>
    <header class="headerAutenticacao">
        <img src="${pageContext.request.contextPath}/assets/img/monart-logo.svg" alt="Logo Monart" class="logoMonart">
        <a href="${pageContext.request.contextPath}/index.jsp">
            <img src="${pageContext.request.contextPath}/assets/img/painting-back-icon.svg" alt="Ícone de voltar" class="pincelVoltar">
        </a>
    </header>
    
    <main>
        <form action="" method="POST" class="formAutenticacao formCpf">
            <h1>Verificar CPF</h1>
            
            <label for="cpf" class="cpf">CPF
                <input type="text" name="cpf" id="cpf" class="inputAutenticacao" placeholder="Insira seu CPF" required>
            </label>
            <button type="submit">Verificar</button>
        </form>
    
        <form action="" method="POST" class="formAutenticacao formMatricula">
            <h1>Fazer matrícula</h1>

            <label for="email" class="email">E-mail
                <input type="email" name="email" id="email" class="inputAutenticacao" placeholder="Insira seu e-mail" required>
            </label>
            
            <label for="senha" class="senha">Senha
                <input type="password" name="senha" id="senha" class="inputAutenticacao" placeholder="Insira sua senha" required>
            </label>
            <button type="submit">Enviar</button>
        </form>
    </main>
</body>

</html>