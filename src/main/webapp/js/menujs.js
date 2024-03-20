// script.js

// Obtener el botón por su ID
var btnIniciar = document.getElementById("btnEnviar");

// Agregar un evento de clic al botón
btnIniciar.addEventListener("click", function() {
	console.log("Botón hacer clic");
    // Redirigir a la página deseada
    window.location.href = "http://localhost:8080/AjedrezServlets_Ajedrez/VistaTablero.jsp";
});
