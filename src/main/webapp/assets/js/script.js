import "./validacao.js"
import "./temas.js"

// Ajuste visual do identificador de página atual

document.querySelectorAll('.pagina').forEach(identificadorPagina => {
    identificadorPagina.addEventListener('click', () => {
        identificadorPagina.classList.add('hoverizado');
    });
});

// Configuração pro Iframe aparecer quando 100% carregado

const dashboardProfessor = document.querySelector(".dashboardProfessor");
const carregandoDashboard = document.querySelector('.carregandoDashboard');

if (dashboardProfessor && carregandoDashboard) {
    dashboardProfessor.style.display = "none";
    
    dashboardProfessor.onload = () => {
        carregandoDashboard.style.display = "none";
        dashboardProfessor.style.display = "block";
    };
}

// Botão de enviar mensagem funcionar com enter

const inputMensagem = document.getElementById('inputMensagem');
const botaoEnviar = document.getElementById('btnEnviar');

inputMensagem.addEventListener('keypress', function(event) {
    if (event.key === 'Enter') {
        event.preventDefault();
        botaoEnviar.click();
    }
});