package controller;

import dao.ConnectionProperty;
import dao.DbConnectionBuilder;
import dao.FilmDbDAO;
import exception.DAOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deletefilm")
public class DeleteFilmServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ConnectionProperty prop;

    public DeleteFilmServlet() {
        super();
        prop = new ConnectionProperty();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String strId = request.getParameter("id");
            if (strId != null) {
                Long deleteId = Long.parseLong(strId);
                FilmDbDAO filmDao = new FilmDbDAO(new DbConnectionBuilder(prop));
                filmDao.delete(deleteId);
            }
        } catch (DAOException e) {
            throw new ServletException(e);
        }
        response.sendRedirect(request.getContextPath() + "/films");
    }
}
