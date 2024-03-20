<%@ include file="navbar.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyLocalChess</title>
<link rel="stylesheet" href="css/estiloIndex.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
</head>
<body>

	<div class="container mt-3">
		<div class="form-container">
			<form>
				<div class="mb-3">
					<label for="exampleInputEmail1">Nombre de Jugador</label> <input
						type="email" class="form-control" id="exampleInputEmail1"
						aria-describedby="emailHelp">
					<div id="emailHelp" class="form-text"></div>
				</div>
				<div class="mb-3">
					<label for="exampleSelect">Color de fichas</label> <select
						class="form-select" id="exampleSelect">
						<option selected>Seleccione el color de fichas</option>
						<option value="1">Fichas Negras</option>
						<option value="2">Fichas Blancas</option>
					</select>
				</div>
				<button id="btnEnviar" class="btn btn-primary">Enviar</button>
			</form>
		</div>
	</div>
</body>
<script type="text/javascript" src="js/menujs.js"></script>
</html>
