package org.example.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "newPersonServlet", urlPatterns = "/people/new")
public class NewPersonServlet extends HttpServlet {

    public NewPersonServlet(){
        super();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("application/jsp");
        request.getRequestDispatcher("/WEB-INF/newPerson.jsp").forward(request, response);
    }
}
