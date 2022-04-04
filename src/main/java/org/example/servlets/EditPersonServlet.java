package org.example.servlets;

import org.example.dao.PersonDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "editPersonServlet", urlPatterns = {"/people/edit"})
public class EditPersonServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/jsp");
        String id = request.getParameter("id");
        if (id == null) {
            response.sendError(400, "INPUT PERSON ID");
            return;
        } else {
            request.setAttribute("person", PersonDAO.getInstance().getPersonById(Integer.valueOf(id)));
        }
        request.getRequestDispatcher("/WEB-INF/editPerson.jsp").forward(request, response);
    }
}
