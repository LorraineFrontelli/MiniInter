<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Aplicando tema salvo -->
        <script>document.documentElement.style.setProperty("--tema",localStorage.getItem("corTema")||"#FF7E7E");</script>

        <!-- Preloads -->
        <link rel="preload" as="image" href="../../../assets/img/painting-back-icon.svg">
        <link rel="preload" as="image" href="../../../assets/img/profile-icon.svg">
        <link rel="preload" as="image" href="../../../assets/img/search-icon.svg">
        <link rel="preload" as="image" href="../../../assets/img/reminder-icon.svg">
        <link rel="preload" as="image" href="../../../assets/img/statistics-icon.svg">

        <!-- Links -->
        <link rel="stylesheet" href="../../../assets/css/global.css">
        <link rel="stylesheet" href="../../../assets/css/tokens.css">
        <link rel="stylesheet" href="../../../assets/css/layout/chat.css">
        <link rel="icon" type="image/x-icon" href="../../../assets/img/favicon.ico">
        <script src="../../../assets/js/script.js" defer></script>

        <title>Notas do aluno - Monart</title>
    </head>

    <body>
        <main>
            <div class="cabecalhoPaginas">
                <img src="../../../assets/img/painting-back-icon.svg" alt="Ícone de voltar" onclick="history.back()" class="pincelVoltar">
                <div class="tituloPaginas">
                    <h1>Fulaninho de tal</h1>
                </div>
                <img src="../../../assets/img/themes-icon.svg" alt="" class="abrirTemas">
            </div>

            <section class="fundoChat">
                <ul class="conversa">
                    <li class="mensagem aluno">
                        <p>Olá professor fulano!</p>
                    </li>
                    <li class="mensagem aluno">
                        <p>Como foi formada a média da sua disciplina?</p>
                    </li>
                    <li class="mensagem prof">
                        <p>Olá, ciclano!</p>
                    </li>
                    <li class="mensagem prof">
                        <p>A média da minha disciplina foi formada a partir das
                            notas das avaliações e do projeto
                            interdisciplinar.</p>
                    </li>
                    <li class="mensagem aluno">
                        <p>Ok, muito obrigado!</p>
                    </li>
                </ul>
                
                <form class="barraMensagem">
                    <input type="text" name="mensagem" id="mensagem" class="escreverMensagem">
                    <button type="submit" class="enviar"><img src="../../../assets/img/send-icon.svg" alt=""></button>
                </form>
            </section>
        </main>
    </body>
    
</html>