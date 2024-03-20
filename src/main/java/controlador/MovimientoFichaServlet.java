package controlador;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MovimientoFichaServlet
 */
@WebServlet("/MovimientoFichaServlet")
public class MovimientoFichaServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MovimientoFichaServlet() {
        super();
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String origen = request.getParameter("origen");
        String destino = request.getParameter("destino");

        // Aquí iría la lógica para mover la ficha y generar la respuesta adecuada
        String respuesta = "Ficha movida de " + origen + " a " + destino;

        PrintWriter out = response.getWriter();
        out.print(respuesta);
        out.flush();
    }
}
