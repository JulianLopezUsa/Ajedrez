

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
            // Obtener la fila y columna de la casilla seleccionada
            var fila = casilla.getAttribute('data-fila');
            var columna = casilla.getAttribute('data-columna');

			console.log("casillla-"+casilla.classList)
 			if (casilla.classList.contains('casilla-resaltada')) {
                // Enviar la solicitud al servidor para realizar el movimiento
                console.log("entral-"+fila+" "+columna)
                enviarSolicitud2(fila, columna);
                
            } else {
				 // Si no está resaltada, deseleccionar cualquier ficha seleccionada
                var fichaSeleccionada = document.querySelector('.ficha-seleccionada');
                if (fichaSeleccionada) {
                    fichaSeleccionada.classList.remove('ficha-seleccionada');
                }
                // Seleccionar la ficha si la casilla contiene una imagen
                var imagenFicha = casilla.querySelector('img');
                if (imagenFicha) {
                    imagenFicha.classList.add('ficha-seleccionada');
                }
                // Si no está resaltada, deseleccionar cualquier ficha seleccionada
                enviarSolicitud(fila, columna);

                //seleccionarFicha(document.querySelector(`.casilla[data-fila="${fila}"][data-columna="${columna}"]`));
            }

        });
    });
});

function enviarSolicitud(fila, columna) {
    // Definir el dato a enviar
    var datos = {
        fila: fila,
        columna: columna
    };

    // Realizar la petición POST
    $.ajax({
        type: "POST",
        url: "servletMain",
        data: datos,
        success: function(response) {
            // Manejar la respuesta del servidor
            console.log("Movimientos posibles:", response);
			
            // Resaltar los posibles movimientos en el tablero
            resaltarMovimientos(response);
        },
        error: function(xhr, status, error) {
            // Manejar errores
            console.error("Error en la petición:", error);
        }
    });
}

function enviarSolicitud2(fila, columna) {
    // Definir el dato a enviar
    var datos = {
        fila: fila,
        columna: columna
    };

    // Realizar la petición POST
    $.ajax({
        type: "POST",
        url: "servletMain",
        data: datos,
        success: function(response) {
            // Manejar la respuesta del servidor
            console.log("Movimientos posibles:", response);
			moverFicha(fila, columna)
          
        },
        error: function(xhr, status, error) {
            // Manejar errores
            console.error("Error en la petición:", error);
        }
    });
}

function resaltarMovimientos(movimientos) {
    // Remover resaltado de movimientos anteriores
    var casillasResaltadas = document.querySelectorAll('.casilla-resaltada');
    casillasResaltadas.forEach(function(casilla) {
        casilla.classList.remove('casilla-resaltada');
    });

    // Resaltar las casillas correspondientes a los movimientos recibidos
    movimientos.forEach(function(movimiento) {
        var fila = parseInt(movimiento.split(" ")[1]);
        console.log("fila: " + fila);
        var columnaLetra = movimiento.charAt(0); // Obtener la letra de la columna
        var columna = columnaLetra.charCodeAt(0) - 'a'.charCodeAt(0); // Convertir la letra de la columna a su equivalente ASCII y restar el valor ASCII de 'a'
        console.log("columna: " + columna);
        var casilla = document.querySelector('[data-fila="' + columna + '"][data-columna="' + fila + '"]');
        console.log(casilla);
        casilla.classList.add("casilla-resaltada");
    });
}


function moverFicha(fila, columna) {
	var casillasResaltadas = document.querySelectorAll('.casilla-resaltada');
    casillasResaltadas.forEach(function(casilla) {
        casilla.classList.remove('casilla-resaltada');
    });
    
    const fichaSeleccionada = document.querySelector('.ficha-seleccionada');
    console.log(fichaSeleccionada)
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
