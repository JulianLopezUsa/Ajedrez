document.addEventListener('DOMContentLoaded', function() {
    const casillas = document.querySelectorAll('.casilla');

    casillas.forEach(function(casilla) {
        casilla.addEventListener('click', function() {
            const fila = casilla.getAttribute('data-fila');
            const columna = casilla.getAttribute('data-columna');
            console.log('Se hizo clic en la casilla en la fila: ' + fila + ' y columna: ' + columna);
            moverFicha(fila, columna);
        });
    });
});

        
function moverFicha(fila, columna) {
    const fichaSeleccionada = document.querySelector('.ficha-seleccionada');
    
    if (fichaSeleccionada) {
        // Obtener la casilla destino
        const casillaDestino = document.querySelector(`.casilla[data-fila="${fila}"][data-columna="${columna}"]`);

        // Mover la ficha a la casilla destino
        casillaDestino.appendChild(fichaSeleccionada);

        // Eliminar la clase 'ficha-seleccionada' de la ficha
        fichaSeleccionada.classList.remove('ficha-seleccionada');
    } else {
        // Obtener la ficha en la casilla seleccionada
        const ficha = document.querySelector(`.casilla[data-fila="${fila}"][data-columna="${columna}"] img`);

        if (ficha) {
            // Agregar la clase 'ficha-seleccionada' a la ficha
            ficha.classList.add('ficha-seleccionada');
        }
    }
}
