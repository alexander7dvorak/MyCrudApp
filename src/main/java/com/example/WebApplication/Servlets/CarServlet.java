package com.example.WebApplication.Servlets;

import com.example.WebApplication.DAO.DaoCar;
import com.example.WebApplication.DAO.DaoCarEngine;
import com.example.WebApplication.DAO.DaoManufacturer;
import com.example.WebApplication.Models.Car;
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

@WebServlet(name = "Car",
        description = "Car CRUD Servlet",
        urlPatterns = {"/car/*"})
public class CarServlet extends HttpServlet {
    private static final long serialVersionUID = -2249542189532262859L;
    private DaoCar daoCar;
    private DaoCarEngine daoEngine;
    private DaoManufacturer daoManufacturer;

    public void init() {
        daoCar = new DaoCar();
        daoEngine = new DaoCarEngine();
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
                    insertCar(req, resp);
                    break;
                case "/delete":
                    deleteCar(req, resp);
                    break;
                case "/edit":
                    showEditForm(req, resp);
                    break;
                case "/update":
                    updateCars(req, resp);
                    break;
                default:
                    listCars(req, resp);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void updateCars(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        int manufacturerId = Integer.parseInt(req.getParameter("manufacturer_id"));
        int engineId = Integer.parseInt(req.getParameter("engine_id"));
        String name = req.getParameter("name");

        Car car = new Car(id, manufacturerId, engineId, name);
        daoCar.update(car);
        resp.sendRedirect("list");
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        Car existingCar = daoCar.find(id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("../WEB-INF/jsp/car-form.jsp");
        req.setAttribute("car", existingCar);
        List<Manufacturer> listManufacturers = daoManufacturer.findAll();
        req.setAttribute("listManufacturers", listManufacturers);
        List<CarEngine> listEngines = daoEngine.findAll();
        req.setAttribute("listEngines", listEngines);
        dispatcher.forward(req, resp);
    }

    private void deleteCar(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        daoCar.delete(id);
        resp.sendRedirect("list");
    }

    private void insertCar(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(req.getParameter("id"));
        int manufacturerId = Integer.parseInt(req.getParameter("manufacturer_id"));
        int engineId = Integer.parseInt(req.getParameter("engine_id"));
        String name = req.getParameter("name");

        Car newCar = new Car(id, manufacturerId, engineId, name);
        daoCar.insert(newCar);
        resp.sendRedirect("list");
    }

    private void showNewForm(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("../WEB-INF/jsp/car-form.jsp");
        List<Manufacturer> listManufacturers = daoManufacturer.findAll();
        req.setAttribute("listManufacturers", listManufacturers);
        List<CarEngine> listEngines = daoEngine.findAll();
        req.setAttribute("listEngines", listEngines);
        dispatcher.forward(req, resp);
    }

    private void listCars(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        List<Car> listCars = daoCar.findAll();
        req.setAttribute("listCars", listCars);
        RequestDispatcher dispatcher = req.getRequestDispatcher("../WEB-INF/jsp/car-list.jsp");
        dispatcher.forward(req, resp);
    }
}
