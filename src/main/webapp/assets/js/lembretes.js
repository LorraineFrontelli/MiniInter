// Configuração de criação e salvamento de lembretes
const idProfessor = document.body.dataset.idProfessor; 
const idLembrete = "lembretes_" + idProfessor; 

const popUplembrete = document.querySelector(".criarLembrete");
const formLembrete = document.querySelector(".criarLembrete form");
const pegarLembrete = document.querySelector(".novoLembrete");
const mural = document.querySelector(".muralLembretes");
const mensagemSemLembretes = document.getElementById("semLembretes");

const MES_MILISSEGUNDOS = 30 * 24 * 60 * 60 * 1000;

let lembretes = JSON.parse(localStorage.getItem(idLembrete)) || [];

const agora = Date.now();

lembretes = lembretes.filter(lembrete => lembrete.expiraEm > agora);
localStorage.setItem(idLembrete, JSON.stringify(lembretes)); 

if (popUplembrete && formLembrete && pegarLembrete) {
    mostrarLembretes();
    mostrarSemLembretes(lembretes, mensagemSemLembretes);
    
    formLembrete.addEventListener("submit", novoLembrete => {
        novoLembrete.preventDefault();
        
        const textoInput = pegarLembrete.value.trim();
        if (!textoInput) return;
    
        const lembreteObj = {
            texto: textoInput,
            expiraEm: Date.now() + MES_MILISSEGUNDOS
        };
    
        lembretes.push(lembreteObj);
        localStorage.setItem(idLembrete, JSON.stringify(lembretes));
        
        mostrarLembretes();
        mostrarSemLembretes(lembretes, mensagemSemLembretes);
    
        pegarLembrete.value = "";
        popUplembrete.close();
    });
}

// Função para mostrar os lembretes no mural
function mostrarLembretes() {
    const paragrafos = mural.querySelectorAll("div");
    paragrafos.forEach(div => div.remove());
    
    lembretes.forEach((cadaLembrete, index) => {
        const lembrete = document.createElement("div");
        // Agora pega a propriedade 'texto' do objeto salvo
        lembrete.textContent = cadaLembrete.texto;

        const botaoApagar = document.createElement("button");
        botaoApagar.textContent = "X";
        botaoApagar.style.cursor = "pointer";
        
        botaoApagar.addEventListener("click", () => {
            lembretes.splice(index, 1);
            localStorage.setItem(idLembrete, JSON.stringify(lembretes));
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