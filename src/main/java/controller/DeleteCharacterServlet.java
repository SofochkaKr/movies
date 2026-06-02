package controller;

import dao.CharacterDbDAO;
import dao.ConnectionProperty;
import dao.DbConnectionBuilder;
import exception.DAOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deletecharacter")
public class DeleteCharacterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ConnectionProperty prop;

    public DeleteCharacterServlet() {
        super();
        prop = new ConnectionProperty();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String strId = request.getParameter("id");
            if (strId != null) {
                Long deleteId = Long.parseLong(strId);
                CharacterDbDAO characterDao = new CharacterDbDAO(new DbConnectionBuilder(prop));
                characterDao.delete(deleteId);
            }
        } catch (DAOException e) {
            throw new ServletException(e);
        }
        response.sendRedirect(request.getContextPath() + "/characters");
    }
}
