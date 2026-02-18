<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Preloads -->
    <link rel="preload" as="image" href="${pageContext.request.contextPath}/assets/img/profile-icon.svg">
    <link rel="preload" as="image" href="${pageContext.request.contextPath}/assets/img/schedule-icon.svg">
    <link rel="preload" as="image" href="${pageContext.request.contextPath}/assets/img/grades-icon.svg">
    <link rel="preload" as="image" href="${pageContext.request.contextPath}/assets/img/statistics-icon.svg">
    
    <!-- Links -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/layout/notas.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/global.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/tokens.css">
    <link rel="icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/img/favicon.ico">
    <script src="${pageContext.request.contextPath}/assets/js/script.js" defer></script>

    <title>Suas notas - Monart</title>
</head>

<body>
    
    <div class="meuPlaceholder"></div>

    <header class="headerLateral">
        <img src="${pageContext.request.contextPath}/assets/img/monart-logo.svg" decoding="async" alt="" class="logoMonart">
        <nav>
            <ul>
                <li><a href="${pageContext.request.contextPath}/perfil.jsp" class="pagina"><img src="${pageContext.request.contextPath}/assets/img/profile-icon.svg" decoding="async" alt="">Perfil</a></li>
                <li><a href="${pageContext.request.contextPath}/agenda.jsp" class="pagina"><img src="${pageContext.request.contextPath}/assets/img/schedule-icon.svg" decoding="async" alt="">Agenda</a></li>
                <li><a href="${pageContext.request.contextPath}/notas.jsp" class="pagina ativo"><img src="${pageContext.request.contextPath}/assets/img/grades-icon.svg" decoding="async" alt="">Notas</a></li>
                <li><a href="${pageContext.request.contextPath}/observacoes.jsp" class="pagina"><img src="${pageContext.request.contextPath}/assets/img/statistics-icon.svg" decoding="async" alt="">Observações</a></li>
            </ul>
        </nav>
    </header>
    
    <main>
        <div class="tituloPaginas">
            <h1>Notas</h1>
        </div>

        <div class="tabelaContainer">
            <table class="tabelaNotas">
                <colgroup>
                    <col style="width: 27%">
                    <col style="width: 5%;">
                    <col style="width: 12%">
                    <col style="width: 12%">
                    <col style="width: 12%">
                    <col style="width: 5%;">
                    <col style="width: 27%">
                </colgroup>

                <thead>
                    <tr>
                        <th>Matéria</th>
                        <th></th>
                        <th>Nota 1</th>
                        <th>Nota 2</th>
                        <th>Média</th>
                        <th></th>
                        <th>Situação</th>
                    </tr>
                </thead>
            
                <tbody>
                    <tr>
                        <td class="materia">Teoria das Cores</td>
                        <td class="espaco"></td>
                        <td class="notasCentro nota1">9.45</td>
                        <td class="notasCentro nota2">7.50</td>
                        <td class="notasCentro media">8.48</td>
                        <td class="espaco"></td>
                        <td class="situacao">Em processo</td>
                    </tr>
                    
                    <tr>
                        <td class="materia">Teoria das Cores</td>
                        <td class="espaco"></td>
                        <td class="notasCentro nota1">9.45</td>
                        <td class="notasCentro nota2">7.50</td>
                        <td class="notasCentro media">8.48</td>
                        <td class="espaco"></td>
                        <td class="situacao">Em processo</td>
                    </tr>
                    
                    <tr>
                        <td class="materia">Teoria das Cores</td>
                        <td class="espaco"></td>
                        <td class="notasCentro nota1">9.45</td>
                        <td class="notasCentro nota2">7.50</td>
                        <td class="notasCentro media">8.48</td>
                        <td class="espaco"></td>
                        <td class="situacao">Reprovado pelo conselho</td>
                    </tr>
                    
                    <tr>
                        <td class="materia">Teoria das Cores</td>
                        <td class="espaco"></td>
                        <td class="notasCentro nota1">9.45</td>
                        <td class="notasCentro nota2">7.50</td>
                        <td class="notasCentro media">8.48</td>
                        <td class="espaco"></td>
                        <td class="situacao">Reprovado pelo conselho</td>
                    </tr>
                    
                    <tr>
                        <td class="materia">Teoria das Cores</td>
                        <td class="espaco"></td>
                        <td class="notasCentro nota1">9.45</td>
                        <td class="notasCentro nota2">7.50</td>
                        <td class="notasCentro media">8.48</td>
                        <td class="espaco"></td>
                        <td class="situacao">Reprovado pelo conselho</td>
                    </tr>
                    
                    <tr>
                        <td class="materia">Teoria das Cores</td>
                        <td class="espaco"></td>
                        <td class="notasCentro nota1">9.45</td>
                        <td class="notasCentro nota2">7.50</td>
                        <td class="notasCentro media">8.48</td>
                        <td class="espaco"></td>
                        <td class="situacao">Reprovado pelo conselho</td>
                    </tr>
                    
                    <tr>
                        <td class="materia">Teoria das Cores</td>
                        <td class="espaco"></td>
                        <td class="notasCentro nota1">9.45</td>
                        <td class="notasCentro nota2">7.50</td>
                        <td class="notasCentro media">8.48</td>
                        <td class="espaco"></td>
                        <td class="situacao">Reprovado pelo conselho</td>
                    </tr>
                    
                    <tr>
                        <td class="materia">Teoria das Cores</td>
                        <td class="espaco"></td>
                        <td class="notasCentro nota1">9.45</td>
                        <td class="notasCentro nota2">7.50</td>
                        <td class="notasCentro media">8.48</td>
                        <td class="espaco"></td>
                        <td class="situacao">Reprovado pelo conselho</td>
                    </tr>
                    
                    <tr>
                        <td class="materia">Teoria das Cores</td>
                        <td class="espaco"></td>
                        <td class="notasCentro nota1">9.45</td>
                        <td class="notasCentro nota2">7.50</td>
                        <td class="notasCentro media">8.48</td>
                        <td class="espaco"></td>
                        <td class="situacao">Reprovado pelo conselho</td>
                    </tr>
                    
                    <tr>
                        <td class="materia">Teoria das Cores</td>
                        <td class="espaco"></td>
                        <td class="notasCentro nota1">9.45</td>
                        <td class="notasCentro nota2">7.50</td>
                        <td class="notasCentro media">8.48</td>
                        <td class="espaco"></td>
                        <td class="situacao">Reprovado pelo conselho</td>
                    </tr>
                    
                    <tr>
                        <td class="materia">Teoria das Cores</td>
                        <td class="espaco"></td>
                        <td class="notasCentro nota1">9.45</td>
                        <td class="notasCentro nota2">7.50</td>
                        <td class="notasCentro media">8.48</td>
                        <td class="espaco"></td>
                        <td class="situacao">Reprovado pelo conselho</td>
                    </tr>
                    
                    <tr>
                        <td class="materia">Teoria das Cores</td>
                        <td class="espaco"></td>
                        <td class="notasCentro nota1">9.45</td>
                        <td class="notasCentro nota2">7.50</td>
                        <td class="notasCentro media">8.48</td>
                        <td class="espaco"></td>
                        <td class="situacao">Reprovado pelo conselho</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </main>
</body>

</html>