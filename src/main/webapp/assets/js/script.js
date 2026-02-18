// Ajuste visual do identificador de página atual

document.querySelectorAll('.pagina').forEach(identificadorPagina => {
    identificadorPagina.addEventListener('click', () => {
        identificadorPagina.classList.add('hoverizado');
    });
});

