package com.example.WebApplication.Servlets;

import com.example.WebApplication.DAO.DaoCar;
import com.example.WebApplication.DAO.DaoManufacturer;
import com.example.WebApplication.Models.Car;
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

@WebServlet(name = "Manufacturer",
        description = "Manufacturer CRUD Servlet",
        urlPatterns = {"/manufacturer/*"})
public class ManufacturerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DaoManufacturer daoManufacturer;

    public void init() {
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
                    insertManufacturer(req, resp);
                    break;
                case "/delete":
                    deleteManufacturer(req, resp);
                    break;
                case "/edit":
                    showEditForm(req, resp);
                    break;
                case "/update":
                    updateManufacturer(req, resp);
                    break;
                default:
                    listManufacturers(req, resp);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void updateManufacturer(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");

        Manufacturer manufacturer = new Manufacturer(id, name);
        daoManufacturer.update(manufacturer);
        resp.sendRedirect("list");
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        Manufacturer existingManufacturer = daoManufacturer.find(id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("../WEB-INF/jsp/manufacturer-form.jsp");
        req.setAttribute("manufacturer", existingManufacturer);
        dispatcher.forward(req, resp);
    }

    private void deleteManufacturer(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        daoManufacturer.delete(id);
        resp.sendRedirect("list");
    }

    private void insertManufacturer(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");

        Manufacturer newManufacturer = new Manufacturer(id,name);
        daoManufacturer.insert(newManufacturer);
        resp.sendRedirect("list");
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("../WEB-INF/jsp/manufacturer-form.jsp");
        dispatcher.forward(req, resp);
    }

    private void listManufacturers(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        List<Manufacturer> listManufacturers = daoManufacturer.findAll();
        req.setAttribute("listManufacturers", listManufacturers);
        RequestDispatcher dispatcher = req.getRequestDispatcher("../WEB-INF/jsp/manufacturer-list.jsp");
        dispatcher.forward(req, resp);
    }
}

