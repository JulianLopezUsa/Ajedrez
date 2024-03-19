<%@ include file="navbar.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>MyLocalChess - Partida</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <style>
        .tablero {
            display: grid;
            grid-template-columns: repeat(8, 70px);
            grid-template-rows: repeat(8, 70px);
        }
        .casilla-blanca {
            background-color: #f0d9b5;
        }
        .casilla-negra {
            background-color: #b58863;
        }
    </style>
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
                                if(blanca) { %>
                                    <div class="casilla-blanca"></div>
                                <% } else { %>
                                    <div class="casilla-negra"></div>
                                <% }
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
</body>
</html>
