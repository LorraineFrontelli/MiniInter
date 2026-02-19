// Ajuste visual do identificador de página atual

document.querySelectorAll('.pagina').forEach(identificadorPagina => {
    identificadorPagina.addEventListener('click', () => {
        identificadorPagina.classList.add('hoverizado');
    });
});

const barrinhaTema = document.querySelectorAll(".barrinhaTema");

document.querySelectorAll(".opcaoTema").forEach(botao => {
    botao.addEventListener("click", () => {
        const cor = botao.dataset.cor;

        barrinhaTema.forEach(barrinha => {
            barrinha.style.backgroundColor = cor;
        });

        localStorage.setItem("corTema", cor);
    });
});

const corSalva = localStorage.getItem("corTema");

if (corSalva) {
    barrinhaTema.forEach(barrinha => {
        barrinha.style.backgroundColor = corSalva;
    });
};