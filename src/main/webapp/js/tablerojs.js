document.addEventListener('DOMContentLoaded', function() {
    // Obtener el contenedor del tablero
    var tablero = document.getElementById("tablero");

    // Booleano para alternar el color de las casillas
    var blanca = true;

    // Bucle para crear las casillas del tablero
    for (var i = 0; i < 8; i++) {
        for (var j = 0; j < 8; j++) {
            // Crear elemento div para la casilla
            var casilla = document.createElement("div");

            // Agregar clases para el estilo de la casilla
            casilla.className = "casilla " + (blanca ? "casilla-blanca" : "casilla-negra");

            // Establecer los atributos de datos para la fila y la columna
            casilla.setAttribute("data-fila", i);
            casilla.setAttribute("data-columna", j);

            // Lógica para determinar qué piezas colocar en las casillas según su posición en el tablero
            var pieza = obtenerPieza(i, j); // Función para obtener la pieza en la posición (i, j)

            // Si hay una pieza en esta posición, crear y agregar la imagen de la pieza
            if (pieza) {
                var imagenPieza = document.createElement("img");
                imagenPieza.src = pieza;
                imagenPieza.alt = "Pieza";
                casilla.appendChild(imagenPieza);
            }

            // Agregar la casilla al tablero
            tablero.appendChild(casilla);

            // Alternar el color de las casillas
            blanca = !blanca;
        }
        blanca = !blanca; // Cambiar de fila, invertir el color de la primera casilla
    }

    // Agregar evento de clic a todas las casillas
    var casillas = document.querySelectorAll('.casilla');
    casillas.forEach(function(casilla) {
        casilla.addEventListener('click', function() {
            var fila = casilla.getAttribute('data-fila');
            var columna = casilla.getAttribute('data-columna');
            console.log('Se hizo clic en la casilla en la fila: ' + fila + ' y columna: ' + columna);
            moverFicha(fila, columna);
        });
    });
});

function moverFicha(fila, columna) {
    var fichaSeleccionada = document.querySelector('.ficha-seleccionada');
    
    if (fichaSeleccionada) {
        // Obtener la casilla destino
        var casillaDestino = document.querySelector(`.casilla[data-fila="${fila}"][data-columna="${columna}"]`);

        // Mover la ficha a la casilla destino
        casillaDestino.appendChild(fichaSeleccionada);

        // Eliminar la clase 'ficha-seleccionada' de la ficha
        fichaSeleccionada.classList.remove('ficha-seleccionada');
    } else {
        // Obtener la ficha en la casilla seleccionada
        var ficha = document.querySelector(`.casilla[data-fila="${fila}"][data-columna="${columna}"] img`);

        if (ficha) {
            // Agregar la clase 'ficha-seleccionada' a la ficha
            ficha.classList.add('ficha-seleccionada');
        }
    }
}

function obtenerPieza(fila, columna) {
    // Definir la disposición inicial de las piezas en el tablero
    var disposicionInicial = [
        ["img/torre_negro.png", "img/caballo_negro.png", "img/alfil_negro.png", "img/dama_negro.png", "img/rey_negro.png", "img/alfil_negro.png", "img/caballo_negro.png", "img/torre_negro.png"],
        ["img/peon_negro.png", "img/peon_negro.png", "img/peon_negro.png", "img/peon_negro.png", "img/peon_negro.png", "img/peon_negro.png", "img/peon_negro.png", "img/peon_negro.png"],
        [null, null, null, null, null, null, null, null],
        [null, null, null, null, null, null, null, null],
        [null, null, null, null, null, null, null, null],
        [null, null, null, null, null, null, null, null],
        ["img/peon_blanco.png", "img/peon_blanco.png", "img/peon_blanco.png", "img/peon_blanco.png", "img/peon_blanco.png", "img/peon_blanco.png", "img/peon_blanco.png", "img/peon_blanco.png"],
        ["img/torre_blanco.png", "img/caballo_blanco.png", "img/alfil_blanco.png", "img/dama_blanco.png", "img/rey_blanco.png", "img/alfil_blanco.png", "img/caballo_blanco.png", "img/torre_blanco.png"]
    ];

    // Obtener la pieza en la posición (fila, columna) según la disposición inicial
    var pieza = disposicionInicial[fila][columna];

    // Devolver la ruta de la imagen de la pieza o null si no hay pieza en esa posición
    return pieza;
}

