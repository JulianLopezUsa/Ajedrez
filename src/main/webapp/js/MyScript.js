/**
 * 
 */
function myPostCall(){
	// Definir el dato a enviar
var dato = {
	m: $("#dato").val()
};

// Realizar la petición POST
$.ajax({
    type: "POST",
    url: "JoaMani",
    data: dato,
    success: function(response) {
        // Manejar la respuesta del servidor
        console.log("Respuesta del servidor:", response);
    },
    error: function(xhr, status, error) {
        // Manejar errores
        console.error("Error en la petición:", error);
    }
});
	
	
}