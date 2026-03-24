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
            <a href="${pageContext.request.contextPath}/alunos">
                <img src="${pageContext.request.contextPath}/assets/img/painting-back-icon.svg"
                    alt="Ícone de voltar" onclick="history.back()" class="pincelVoltar">
            </a>
            <div class="tituloPaginas">
                <h1>Boletim - ${nomeAluno}</h1>
            </div>

            <img src="${pageContext.request.contextPath}/assets/img/themes-icon.svg" class="abrirTemas">

        </div>


        <c:if test="${not empty sessionScope.mensagem}">
            <p style="color:green; text-align:center;">
                ${sessionScope.mensagem}
            </p>
            <c:remove var="mensagem" scope="session"/>
        </c:if>


    <div class="componentizacao">
        <button class="botaoInsert" onclick="create.showModal()">
            Fazer inserção
            <img src="${pageContext.request.contextPath}/assets/img/plus-icon.svg">
        </button>

    </div>
        <div class="componentizacao">
            <button class="botaoInsert" onclick="create.showModal()">
                Inserir boletim
                <img src="${pageContext.request.contextPath}/assets/img/plus-icon.svg">
            </button>
        </div>


        <div class="tabelaContainer">
            <table class="tabelaRead">
                <thead>
                <tr>
                    <th>Ações</th>
                    <th>ID</th>
                    <th>ID Professor</th>
                    <th>Matéria</th>
                    <th>Nota 1</th>
                    <th>Descrição 1</th>
                    <th>Nota 2</th>
                    <th>Descrição 2</th>
                    <th>Situação</th>
                    <th>Data</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach var="b" items="${boletins}">
                    <tr>
                        <td class="opcoes">
                            <div>
                                <button type="button" style="background:none;border:none;"
                                        onclick="abrirUpdate('${b.id}', '${b.idProfessor}', '${b.nota1}', '${b.nota2}', '${b.descricao1}', '${b.descricao2}', '${b.observacao}', '${b.dataCriacao}')">
                                    <img src="${pageContext.request.contextPath}/assets/img/update-icon.svg">
                                </button>

                                <button type="button" style="background:none;border:none;"
                                        onclick="abrirDelete('${b.id}')">
                                    <img src="${pageContext.request.contextPath}/assets/img/delete-icon.svg">
                                </button>
                            </div>
                        </td>
                        
                        <td>${b.id}</td>
                        <td>${b.idProfessor}</td>
                        <td>${b.materia}</td>
                        <td>${b.nota1}</td>
                        <td>${b.desc1}</td>
                        <td>${b.nota2}</td>
                        <td>${b.desc2}</td>
                        <td>${b.aprovado ? "Aprovado" : "Reprovado"}</td>
                        <td>${b.dataCriacao}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <dialog id="create" class="create">
            <button class="fecharPopUp" onclick="create.close()">X</button>
            <form action="${pageContext.request.contextPath}/boletim-create" method="post">
                <div class="colunas">
                    <div class="coluna">
                        <label>ID Professor</label>
                        <input type="number" name="idProfessor" required>
                        <input type="hidden" name="idAluno" value="${idAluno}">
                        
                        <div class="colunaNotas">
                            <label>Nota 1</label>
                            <input type="number" step="0.01" name="nota1" required>
                            <label>Nota 2</label>
                            <input type="number" step="0.01" name="nota2" required>
                        </div>
                        
                        <label>Descrição 1</label>
                        <input type="text" name="descricao1">
                        <label>Descrição 2</label>
                        <input type="text" name="descricao2">
                    </div>
                    
                    <div class="coluna">
                        <label>Observação</label>
                        <textarea name="observacao"></textarea>
                        <label>Data</label>
                        <input type="date" name="data" required>
                    </div>
                </div>
                <button class="salvarInsercao" type="submit">Cadastrar</button>
            </form>
        </dialog>

        <dialog id="update" class="update">
            <button class="fecharPopUp" onclick="update.close()">X</button>
            <form action="${pageContext.request.contextPath}/boletim-update" method="post">
                <input type="hidden" name="id" id="updateId">
                <input type="hidden" name="idAluno" value="${idAluno}">
                
                <div class="colunas">
                    <div class="coluna">
                        <label>ID Professor</label>
                        <input type="number" name="idProfessor" id="updateIdProfessor" required>
                        
                        <div class="colunaNotas">
                            <label>Nota 1</label>
                            <input type="number" step="0.01" name="nota1" id="updateNota1" required>
                            <label>Nota 2</label>
                            <input type="number" step="0.01" name="nota2" id="updateNota2" required>
                        </div>
                        
                        <label>Descrição 1</label>
                        <input type="text" name="descricao1" id="updateDesc1">
                        <label>Descrição 2</label>
                        <input type="text" name="descricao2" id="updateDesc2">
                    </div>
                    
                    <div class="coluna">
                        <label>Observação</label>
                        <textarea name="observacao" id="updateObs"></textarea>
                        <label>Data</label>
                        <input type="date" name="data" id="updateData" required>
                    </div>
                </div>
                <button class="salvarAlteracoes" type="submit">Atualizar</button>
            </form>
        </dialog>

        <dialog id="deletes" class="deletes">
            <button class="fecharPopUp" onclick="deletes.close()">X</button>
            <form action="${pageContext.request.contextPath}/boletim-delete" method="post">
                <input type="hidden" name="id" id="deleteId">
                <h2>Deseja realmente excluir este boletim?</h2>
                <div>
                    <button class="cancelarDeletar" type="button" onclick="deletes.close()">Cancelar</button>
                    <button class="deletar" type="submit">Excluir</button>
                </div>
            </form>
        </dialog>

    </main>

    <script>
        function abrirUpdate(id, idProf, n1, n2, desc1, desc2, obs, data) {
            document.getElementById('updateId').value = id;
            document.getElementById('updateIdProfessor').value = idProf;
            document.getElementById('updateNota1').value = n1;
            document.getElementById('updateNota2').value = n2;
            document.getElementById('updateDesc1').value = desc1;
            document.getElementById('updateDesc2').value = desc2;
            document.getElementById('updateObs').value = obs;
            document.getElementById('updateData').value = data;
            document.getElementById('update').showModal();
        }

        function abrirDelete(id) {
            document.getElementById('deleteId').value = id;
            document.getElementById('deletes').showModal();
        }
    </script>

</body>
</html>