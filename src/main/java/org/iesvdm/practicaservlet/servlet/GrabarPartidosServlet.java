package org.iesvdm.practicaservlet.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesvdm.practicaservlet.dao.PartidoDAO;
import org.iesvdm.practicaservlet.dao.PartidoDAOImpl;
import org.iesvdm.practicaservlet.model.Partido;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
@WebServlet(name = "GrabarPartidosServlet", value = "/GrabarPartidosServlet")
public class GrabarPartidosServlet extends HttpServlet {
    private PartidoDAO partidoDAO = new PartidoDAOImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/formularioPartido.jsp");
        dispatcher.forward(request, response);
    }

     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         RequestDispatcher dispatcher = null;
         Optional<Partido> optional = UtilServletPartido.validaGrabar(request);

         if (optional.isPresent()) {

             Partido partido = optional.get();
             this.partidoDAO.create(partido);
             List<Partido> listado = this.partidoDAO.getAll();
             request.setAttribute("listado", listado);
             request.setAttribute("newPartidoID", partido.getId() );
             dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/listadoPartidos.jsp");

         } else {

             request.setAttribute("error", "Error de validación!");
             dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/formularioPartido.jsp");
         }
         dispatcher.forward(request,response);
    }
}