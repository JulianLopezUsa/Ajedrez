<%@ include file="navbar.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>MyLocalChess - Partida</title>
    <link rel="stylesheet" href="css/estiloTablero.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" >

</head>
<body>
    <div class="container mt-3">
        <div class="row">
            <div class="col-md-6">
                <div class="tablero">
                    <% 
                        boolean blanca = true;
                        for(int i = 0; i < 8; i++) {
                            for(int j = 0; j < 8; j++) {
                                String[] piezas = new String[8]; // Array para almacenar las imágenes de las piezas en la casilla
                                int indice = 0; // Índice para el array de piezas
                                if(blanca) { %>
                                    <div class="casilla casilla-blanca" data-fila="<%= i %>" data-columna="<%= j %>">
                                <% } else { %>
                                    <div class="casilla casilla-negra" data-fila="<%= i %>" data-columna="<%= j %>">
                                <% }
                                    if (i == 1 && (j >= 0 && j <= 7)) { piezas[indice++] = "img/peon_blanco.png"; }
                                    if (i == 0 && (j == 0 || j == 7)) { piezas[indice++] = "img/torre_blanco.png"; }
                                    if (i == 0 && (j == 1 || j == 6)) { piezas[indice++] = "img/caballo_blanco.png"; }
                                    if (i == 0 && (j == 2 || j == 5)) { piezas[indice++] = "img/alfil_blanco.png"; }
                                    if (i == 0 && j == 3) { piezas[indice++] = "img/dama_blanco.png"; }
                                    if (i == 0 && j == 4) { piezas[indice++] = "img/rey_blanco.png"; }
                                    if (i == 6 && (j >= 0 && j <= 7)) { piezas[indice++] = "img/peon_negro.png"; }
                                    if (i == 7 && (j == 0 || j == 7)) { piezas[indice++] = "img/torre_negro.png"; }
                                    if (i == 7 && (j == 1 || j == 6)) { piezas[indice++] = "img/caballo_negro.png"; }
                                    if (i == 7 && (j == 2 || j == 5)) { piezas[indice++] = "img/alfil_negro.png"; }
                                    if (i == 7 && j == 3) { piezas[indice++] = "img/dama_negro.png"; }
                                    if (i == 7 && j == 4) { piezas[indice++] = "img/rey_negro.png"; }
                                    // Iterar sobre las imágenes de las piezas y mostrarlas en la casilla
                                    for (String pieza : piezas) {
                                        if (pieza != null) { %>
                                            <img src="<%= pieza %>" alt="Pieza">
                                        <% }
                                    }
                                %>
                                    </div>
                                <% 
                                blanca = !blanca;
                            }
                            blanca = !blanca; // Cambiar de fila, invertir el color de la primera casilla
                        }
                    %>
                </div>
            </div>
            <div class="col-md-6">
                <h2>Nombre del Jugador</h2>
                <h2>Jugadas</h2>
            </div>
        </div>
    </div>
  </div> 
</body>
<script type="text/javascript" src="js/tablerojs.js"></script>
</html>
