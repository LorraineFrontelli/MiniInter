// Ajuste visual do identificador de página atual

document.querySelectorAll('.pagina').forEach(identificadorPagina => {
    identificadorPagina.addEventListener('click', () => {
        identificadorPagina.classList.add('hoverizado');
    });
});

document.addEventListener("DOMContentLoaded", () => {
    const dialogHtml = `
        <dialog class="paletaTemas" id="paletaTemas">
            <div class="opcoesTema">
                <button data-cor="#5956A3" class="mediumBlue opcaoTema"></button>
                <button data-cor="#FF7B7B" class="pastelCoral opcaoTema"></button>
                <button data-cor="#75FF9C" class="pastelGreen opcaoTema"></button>
                <button data-cor="#726DFF" class="pastelBlue opcaoTema"></button>
                <button data-cor="#FFA1FD" class="pastelPink opcaoTema"></button>
                <button data-cor="#87FFEF" class="pastelCyan opcaoTema"></button>
                <button data-cor="#906A1E" class="vintageBrown opcaoTema"></button>
                <button data-cor="#D4B76F" class="vintageSand opcaoTema"></button>
                <button data-cor="#FFBA30" class="vintageOrange opcaoTema"></button>
                <button data-cor="#242021" class="vintageMustache opcaoTema"></button>
            </div>
            <button class="fecharTemas">X</button>
        </dialog>
    `;
    document.body.insertAdjacentHTML('beforeend', dialogHtml);

    const paleta = document.getElementById("paletaTemas");

    document.querySelector(".abrirTemas").addEventListener("click", () => {
        paleta.showModal();
    });

    document.querySelector(".fecharTemas").addEventListener("click", () => {
        paleta.close();
    });

    const barrinhaTema = document.querySelectorAll(".barrinhaTema");
    const barrinhaTemaTexto = document.querySelectorAll(".barrinhaTema *");
    const tituloPagina = document.querySelector(".tituloPaginas");
    const tituloPaginaTexto = document.querySelector(".tituloPaginas *");
    const botaoEditar = document.querySelector(".editar");
    const iconeEditar = document.querySelector(".iconeEditar")

    document.querySelectorAll(".opcaoTema").forEach(botao => {
        botao.addEventListener("click", () => {
            const cor = botao.dataset.cor;
            const corContraste = getContraste(cor);
            
            tituloPagina.style.backgroundColor = cor;
            tituloPaginaTexto.style.color = corContraste;
            
            if (iconeEditar) {
                iconeEditar.style.filter = corContraste === "var(--black)" ? "invert(1)" : "invert(0)";
            }
            
            if (botaoEditar) {
                botaoEditar.style.backgroundColor = cor;
                botaoEditar.style.color = corContraste;
            }

            barrinhaTema.forEach(barrinha => {
                barrinha.style.backgroundColor = cor;
            });

            barrinhaTemaTexto.forEach(barrinhaTexto => {
                barrinhaTexto.style.color = corContraste;
            });

            localStorage.setItem("corTema", cor);

            paleta.close();
        });
    });

    const corSalva = localStorage.getItem("corTema");

    if (corSalva) {
        const corContraste = getContraste(corSalva);
        
        tituloPagina.style.backgroundColor = corSalva;
        tituloPaginaTexto.style.color = corContraste;

        if (iconeEditar) {
            iconeEditar.style.filter = corContraste === "var(--black)" ? "invert(1)" : "invert(0)";
        }

        if (botaoEditar) {
            botaoEditar.style.backgroundColor = corSalva;
            botaoEditar.style.color = corContraste;
        }

        barrinhaTema.forEach(barrinha => {
            barrinha.style.backgroundColor = corSalva;
        });

        barrinhaTemaTexto.forEach(barrinhaTexto => {
            barrinhaTexto.style.color = corContraste;
        });
    }
});

function getContraste(corHex) {
    const hex = corHex.replace('#', '');

    const r = parseInt(hex.substring(0,2),16);
    const g = parseInt(hex.substring(2,4),16);
    const b = parseInt(hex.substring(4,6),16);

    const lum = 0.299*r + 0.587*g + 0.114*b;

    return lum > 186 ? 'var(--black)' : 'var(--white)';
}