<%@ include file="navbar.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyLocalChess - Partida</title>
<link rel="stylesheet" href="css/estiloTablero.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container mt-3">
        <div class="row">
            <div class="col-md-6">
                <div id="tablero" class="tablero"></div>
            </div>
            <div class="col-md-6">
                <div class="row">
                    <div class="col-md-12">
                        <h2>Jugador: <%= request.getParameter("jugador") %></h2>
                        <h5>Jugadas</h5>
                    </div>
                    <div class="col-md-12">
                        <div class="contenedor-texto">
                            <textarea id="texto" rows="20" cols="50" readonly>

                            </textarea>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript" src="js/tablerojs.js"></script>
</body>
</html>
