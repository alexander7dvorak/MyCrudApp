package com.example.WebApplication.Servlets;

import com.example.WebApplication.DAO.DaoCarEngine;
import com.example.WebApplication.DAO.DaoManufacturer;
import com.example.WebApplication.Models.CarEngine;
import com.example.WebApplication.Models.Manufacturer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(
        description = "Example Servlet Using Annotations",
        urlPatterns = {"/engine/*"})
public class CarEngineServlet extends HttpServlet {
    private static final long serialVersionUID = -8873770298180956201L;
    private DaoCarEngine daoCarEngine;
    private DaoManufacturer daoManufacturer;

    public void init() {
        daoCarEngine = new DaoCarEngine();
        daoManufacturer = new DaoManufacturer();
    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String action = req.getPathInfo();
        try {
            switch (action) {
                case "/new":
                    showNewForm(req, resp);
                    break;
                case "/insert":
                    insertEngine(req, resp);
                    break;
                case "/delete":
                    deleteEngine(req, resp);
                    break;
                case "/edit":
                    showEditForm(req, resp);
                    break;
                case "/update":
                    updateEngines(req, resp);
                    break;
                default:
                    listEngines(req, resp);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void updateEngines(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        int manufacturerId = Integer.parseInt(req.getParameter("manufacturer_id"));
        String name = req.getParameter("name");
        CarEngine carEngine = new CarEngine(id, manufacturerId, name);
        daoCarEngine.update(carEngine);
        resp.sendRedirect("list");
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        CarEngine existingCarEngine = daoCarEngine.find(id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("../WEB-INF/jsp/engine-form.jsp");
        req.setAttribute("carEngine", existingCarEngine);
        List<Manufacturer> listManufacturers = daoManufacturer.findAll();
        req.setAttribute("listManufacturers", listManufacturers);
        dispatcher.forward(req, resp);
    }

    private void deleteEngine(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        daoCarEngine.delete(id);
        resp.sendRedirect("list");
    }

    private void insertEngine(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        int manufacturerId = Integer.parseInt(req.getParameter("manufacturer_id"));
        String name = req.getParameter("name");
        CarEngine newCarEngine = new CarEngine(id, manufacturerId, name);
        daoCarEngine.insert(newCarEngine);
        resp.sendRedirect("list");
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("../WEB-INF/jsp/engine-form.jsp");
        List<Manufacturer> listManufacturers = daoManufacturer.findAll();
        req.setAttribute("listManufacturers", listManufacturers);
        dispatcher.forward(req, resp);
    }

    private void listEngines(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        List<CarEngine> listEngines = daoCarEngine.findAll();
        req.setAttribute("listEngines", listEngines);
        String action = req.getPathInfo();
        RequestDispatcher dispatcher = req.getRequestDispatcher("../WEB-INF/jsp/engine-list.jsp");
        dispatcher.forward(req, resp);
    }
}
