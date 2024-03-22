package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Tablero.juegoAjedrez;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;

/**
 * Servlet implementation class AjedrezServlet
 */
@WebServlet("/servletMain")
public class AjedrezServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private juegoAjedrez modelo;
	
    @Override
    public void init() throws ServletException {
        super.init();
        modelo = new juegoAjedrez();
        this.modelo.modelo.inicializarCuadros();
    }
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjedrezServlet() {
        super();
        
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    	// Obtener parámetros de la solicitud
        String fila = request.getParameter("fila");
        String columna = request.getParameter("columna");

        // Convertir los parámetros a números enteros si es necesario
        int i = Integer.parseInt(fila);
        int j = Integer.parseInt(columna);

        System.out.println("Se recibieron los datos de i: " + i + " y j: " + j);
        modelo.modelo.verificaciones.posiblesMovimeintos.clear();
        modelo.procesarMovimiento(i, j, this);
        
        System.out.println("Se recibieron los datos de: "+ modelo.modelo.verificaciones.posiblesMovimeintos);
        
        ArrayList<String> movimientos =  modelo.modelo.verificaciones.posiblesMovimeintos;
            
        // Convertir la lista de movimientos a formato JSON
        Gson gson = new Gson();
        String movimientosJSON = gson.toJson(movimientos);

        //System.out.println(movimientosJSON);
        // Configurar la respuesta
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(movimientosJSON);
        
        
	}

}
