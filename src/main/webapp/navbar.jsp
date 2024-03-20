<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg bg-body-tertiary"
	data-bs-theme="dark">
	<div class="container-fluid">
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarTogglerDemo03"
			aria-controls="navbarTogglerDemo03" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<a class="navbar-brand" href="#"> <img
			src="<%= request.getContextPath() %>/img/Logo.png" alt="Logo"
			width="30" height="24" class="d-inline-block align-text-top"> <a
			class="navbar-brand" href="#">MyLocalChess</a>
		</a>
		<div class="collapse navbar-collapse" id="navbarTogglerDemo03">
			<ul class="navbar-nav me-auto mb-2 mb-lg-0">
				<li class="nav-item"><a class="nav-link active"
					aria-current="page" href="Menu.jsp">Inicio</a></li>
				<li class="nav-item"><a class="nav-link" href="AcercaDe.jsp">Acerca
						de</a></li>
				<li class="nav-item"><a class="nav-link" href="index.jsp">Salir</a>
				</li>
			</ul>
		</div>
	</div>
</nav>