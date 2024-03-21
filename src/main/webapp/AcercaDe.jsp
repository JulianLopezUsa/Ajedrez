<%@ include file="navbar.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MyLocalChess</title>
<link rel="stylesheet" href="css/style.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
</head>
<body>
	<h2>Proyecto presentado por:<b></b></h2>
	<div class="card-container">
		<div class="card" style="width: 15rem;">
			<img src="<%= request.getContextPath() %>/img/Logo.png"
				class="card-img-top" alt="...">
			<div class="card-body">
				<h5 class="card-title">Laura Garcia</h5>
				<p class="card-text">No hizo nada en el proyecto.</p>
				<a href="#" class="btn btn-primary">Go somewhere</a>
			</div>
		</div>
		<div class="card" style="width: 15rem;">
			<img src="<%= request.getContextPath() %>/img/Logo.png"
				class="card-img-top" alt="...">
			<div class="card-body">
				<h5 class="card-title">Carlos Bermudez</h5>
				<p class="card-text">Joa mani</p>
				<a href="#" class="btn btn-primary">Go somewhere</a>
			</div>
		</div>
		<div class="card" style="width: 15rem;">
			<img src="<%= request.getContextPath() %>/img/Logo.png"
				class="card-img-top" alt="...">
			<div class="card-body">
				<h5 class="card-title">Julián López</h5>
				<p class="card-text">Front</p>
				<a href="#" class="btn btn-primary">Go somewhere</a>
			</div>
		</div>
	</div>

</body>
<script type="text/javascript" src="navbar.jsp"></script>
</html>
