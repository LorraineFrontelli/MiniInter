// Incluindo Modais de Descrição de Nota no Form Geral de Notas

const formPrincipal = document.querySelector(".notasAluno form");

const descricaoNota1 = document.getElementById("descricaoNota1");
const descricaoNota2 = document.getElementById("descricaoNota2");

const descricaoNota1Hidden = document.getElementById("descricaoNota1Hidden");
const descricaoNota2Hidden = document.getElementById("descricaoNota2Hidden");

if (descricaoNota1 && descricaoNota1Hidden) {
    descricaoNota1.addEventListener("input", () => {
        descricaoNota1Hidden.value = descricaoNota1.value;
    });
}

if (descricaoNota2 && descricaoNota2Hidden) {
    descricaoNota2.addEventListener("input", () => {
        descricaoNota2Hidden.value = descricaoNota2.value;
    });
}

// Validação de Preenchimento de Modais de Descrição de Nota

formPrincipal.addEventListener("submit", (event) => {
    const valorDescricao1 = descricaoNota1Hidden.value.trim();
    const valorDescricao2 = descricaoNota2Hidden.value.trim();
    
    if (valorDescricao1 === "" || valorDescricao2 === "") {
        event.preventDefault();
        
        alert("Preencha a descrição das duas notas antes de salvar!");
        
        if (valorDescricao1 === "") {
            document.getElementById("verTipoNota1").showModal();
        } else if (valorDescricao2 === "") {
            document.getElementById("verTipoNota2").showModal();
        }
    }
});

