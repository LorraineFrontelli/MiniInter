// Configuração de criação e salvamento de lembretes

const popUplembrete = document.querySelector(".criarLembrete");
const formLembrete = document.querySelector(".criarLembrete form");
const pegarLembrete = document.querySelector(".novoLembrete");

let lembretes = JSON.parse(localStorage.getItem("lembretes")) || [];

const mural = document.querySelector(".muralLembretes");

const mensagemSemLembretes = document.getElementById("semLembretes");

if (popUplembrete && formLembrete && pegarLembrete) {
    mostrarLembretes();
    mostrarSemLembretes(lembretes, mensagemSemLembretes);
    
    formLembrete.addEventListener("submit", novoLembrete => {
        novoLembrete.preventDefault();
        
        const lembrete = pegarLembrete.value.trim();
        if (!lembrete) return;
    
        lembretes.push(lembrete);
        
        localStorage.setItem("lembretes", JSON.stringify(lembretes));
        
        mostrarLembretes();
        mostrarSemLembretes(lembretes, mensagemSemLembretes);
    
        pegarLembrete.value = "";
    
        popUplembrete.close();
    });
};

// Funções utilizadas

// Função para mostrar os lembretes no mural

function mostrarLembretes() {
    
    const paragrafos = mural.querySelectorAll("div");
    paragrafos.forEach(div => div.remove());
    
    lembretes.forEach((cadaLembrete, index) => {
        const lembrete = document.createElement("div");
        lembrete.textContent = cadaLembrete;

        const botaoApagar = document.createElement("button");
        botaoApagar.textContent = "X";
        botaoApagar.style.cursor = "pointer";
        
        botaoApagar.addEventListener("click", () => {

            lembretes.splice(index, 1);

            localStorage.setItem("lembretes", JSON.stringify(lembretes));

            mostrarLembretes();
            mostrarSemLembretes(lembretes, mensagemSemLembretes);
        });

        lembrete.appendChild(botaoApagar);
        mural.appendChild(lembrete);
    });
}

// Função para mostrar mensagem de mural de lembretes vazio

function mostrarSemLembretes(lembretes, mensagemSemLembretes) {
    
    if (lembretes.length === 0) {
        mensagemSemLembretes.style.display = "block";
    } else {
        mensagemSemLembretes.style.display = "none";
    }
}