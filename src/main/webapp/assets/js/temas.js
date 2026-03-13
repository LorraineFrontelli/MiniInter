// Configração de alternância de tmeas

document.addEventListener("DOMContentLoaded", () => {
    const dialogHtml = `
        <dialog class="paletaTemas" id="paletaTemas">
            <div class="botoesTemas">
                <h2>Selecione um tema</h2>
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
            </div>
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
    const botaoLembrete = document.querySelector(".botaoLembrete")
    const iconeLembrete = document.querySelector(".botaoLembrete img")
    const botoesMenu = document.querySelectorAll(".menuAluno button")
    const mensagensEnviadas = document.querySelectorAll(".enviada")
    const mensagemEnviadaTexto = document.querySelectorAll(".enviada *")
    const botaoInsert = document.querySelector(".botaoInsert")
    const iconeInsert = document.querySelector(".botaoInsert img")


    document.querySelectorAll(".opcaoTema").forEach(botao => {
        botao.addEventListener("click", () => {
            const cor = botao.dataset.cor;
            const corContraste = getContraste(cor);

            document.documentElement.style.setProperty("--tema", cor);
            
            aplicarTemaElemento(tituloPagina, tituloPaginaTexto, null, cor, corContraste)

            aplicarTemaElemento(botaoLembrete, botaoLembrete, iconeLembrete, cor, corContraste)

            aplicarTemaElemento(botaoInsert, botaoInsert, iconeInsert, cor, corContraste)
            
            aplicarTemaElemento(botaoEditar, botaoEditar, iconeEditar, cor, corContraste)
            
            aplicarTemaElementos(mensagensEnviadas, mensagemEnviadaTexto, null, cor, corContraste)
            
            aplicarTemaElementos(barrinhaTema, barrinhaTemaTexto, null, cor, corContraste)
            
            aplicarTemaElementos(botoesMenu, botoesMenu, null, cor, corContraste)
            
            localStorage.setItem("corTema", cor);
            
            paleta.close();
        });
    });
    
    const corSalva = localStorage.getItem("corTema");
    
    if (corSalva) {
        const corContraste = getContraste(corSalva);
        
        aplicarTemaElemento(tituloPagina, tituloPaginaTexto, null, corSalva, corContraste)
        
        aplicarTemaElemento(botaoLembrete, botaoLembrete, iconeLembrete, corSalva, corContraste)
        
        aplicarTemaElemento(botaoInsert, botaoInsert, iconeInsert, corSalva, corContraste)
        
        aplicarTemaElemento(botaoEditar, botaoEditar, iconeEditar, corSalva, corContraste)
        
        aplicarTemaElementos(mensagensEnviadas, mensagemEnviadaTexto, null, corSalva, corContraste)

        aplicarTemaElementos(barrinhaTema, barrinhaTemaTexto, null, corSalva, corContraste)

        aplicarTemaElementos(botoesMenu, botoesMenu, null, corSalva, corContraste)
    }
});

// Funções utilizadas

// Função de verificar se a fonte deve aer preta ou branca (de acordo com o tema atual)

function getContraste(corHex) {
    const hex = corHex.replace('#', '');
    
    const r = parseInt(hex.substring(0,2),16);
    const g = parseInt(hex.substring(2,4),16);
    const b = parseInt(hex.substring(4,6),16);
    
    const lum = 0.299*r + 0.587*g + 0.114*b;
    
    return lum > 186 ? 'var(--black)' : 'var(--white)';
}

// Função para aplicar temas a elementos (quando únicos por página)

function aplicarTemaElemento(elemento, textoElemento, iconeElemento, cor, corContraste) {
    
    if (elemento) {
        elemento.style.backgroundColor = cor;
    }
    
    if (textoElemento) {
        textoElemento.style.color = corContraste;
    }
    
    if (iconeElemento) {
        iconeElemento.style.filter = corContraste === "var(--black)" ? "invert(1)" : "invert(0)";
    }
}

// Função para aplicar temas a elementos (quando mais de um por página)

function aplicarTemaElementos(elementos, textoElementos, iconeElementos, cor, corContraste) {
    
    if (elementos) {
        elementos.forEach(elemento => {
            elemento.style.backgroundColor = cor;
    })}

    if (textoElementos) {
        textoElementos.forEach(textoElemento => {
            textoElemento.style.color = corContraste;
    })}
    
    if (iconeElementos) {
        iconeElementos.forEach(iconeElemento => {
            iconeElemento.style.filter = corContraste === "var(--black)" ? "invert(1)" : "invert(0)";
    })}
}