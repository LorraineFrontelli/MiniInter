<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">

<head>
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">

   <!-- Preloads -->
   <link rel="preload" as="image" href="../../../assets/img/quadro-login.svg">

   <!-- Links -->
   <link rel="stylesheet" href="../../../assets/css/global.css">
   <link rel="stylesheet" href="../../../assets/css/tokens.css">
   <link rel="stylesheet" href="../../../assets/css/layout/autenticacao.css">
   <link rel="icon" type="image/x-icon" href="../../../assets/img/favicon.ico">
   <script src="../../../assets/js/script.js" defer></script>

   <title>Cadastro - Monart</title>
</head>

<body>
    <header class="headerAutenticacao">
        <img src="../../../assets/img/monart-logo.svg" alt="Logo Monart" class="logoMonart">
        <a href="../../../index.html" class="iconeVoltar">
            <img src="../../../assets/img/painting-back-icon.svg" alt="Ícone de voltar" class="pincelVoltar">
        </a>
    </header>
    
    <main>
        <form action="" class="formAutenticacao formCpf">
            <h1>Verificar CPF</h1>
            
            <label for="cpf" class="cpf">CPF
                <input type="text" name="cpf" id="cpf" class="inputAutenticacao" placeholder="Insira seu CPF">
            </label>
            <button>Verificar</button>
        </form>
    
        <form action="" class="formAutenticacao formMatricula">
            <h1>Fazer matrícula</h1>

            <label for="email" class="email">E-mail
                <input type="email" name="email" id="email" class="inputAutenticacao" placeholder="Insira seu e-mail">
            </label>
            
            <label for="senha" class="senha">Senha
                <input type="password" name="senha" id="senha" class="inputAutenticacao" placeholder="Insira sua senha">
            </label>
            <button>Enviar</button>
        </form>
    </main>
</body>

</html>