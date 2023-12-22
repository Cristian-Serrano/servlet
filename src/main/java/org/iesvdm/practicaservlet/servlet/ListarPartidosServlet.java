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

@WebServlet(name = "ListarPartidosServlet", value = "/ListarPartidosServlet")
public class ListarPartidosServlet extends HttpServlet {

    private PartidoDAO partidoDAO = new PartidoDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/listadoPartidos.jsp");

        List<Partido> listado = this.partidoDAO.getAll();
        request.setAttribute("listado", listado);

        dispatcher.forward(request, response);
    }

}