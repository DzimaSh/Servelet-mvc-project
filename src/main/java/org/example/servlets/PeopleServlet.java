package org.example.servlets;

import org.example.dao.PersonDAO;
import org.example.models.Person;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import java.io.IOException;
import java.util.Set;

@WebServlet(name="allPeopleServlet", urlPatterns = {"/people"})
public class PeopleServlet extends HttpServlet {

    private PersonDAO personDAO = null;

    @Override
    public void init(ServletConfig config) {
        try {
            super.init();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        personDAO = PersonDAO.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id == null) {
            request.setAttribute("people", personDAO.getAllPeople());
            request.getRequestDispatcher("/WEB-INF/allPeople.jsp").forward(request, response);
        } else {
            Person person = personDAO.getPersonById(Integer.valueOf(id));
            request.setAttribute("person", person);
            request.getRequestDispatcher("/WEB-INF/person.jsp").forward(request, response);
        }
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        response.setContentType("application/jsp");
        String method = request.getParameter("_method");
        if (method != null) {
            if (method.equals("DELETE")) {
                this.doDelete(request, response);
                return;
            }
            if (method.equals("PUT")) {
                this.doPut(request, response);
                return;
            }
        }

        Person person = this.createNewPersonFromRequest(request);

        Set<ConstraintViolation<Person>> constraintViolations = personDAO.validatePerson(person);
        if (!constraintViolations.isEmpty()) {
            request.setAttribute("errors", constraintViolations);
            request.getRequestDispatcher("/WEB-INF/newPerson.jsp").forward(request, response);
        } else {
        personDAO.createPerson(person);

        request.setAttribute("method", "get");
        response.sendRedirect("/people");
        }
    }
    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        personDAO.delete(Integer.valueOf(request.getParameter("id")));
        request.setAttribute("method", "get");
        response.sendRedirect("/people");
    }
    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws  ServletException, IOException{
        Person person = this.createNewPersonFromRequest(request);

        Set<ConstraintViolation<Person>> constraintViolations = personDAO.validatePerson(person);
        if (!constraintViolations.isEmpty()) {
            person.setId(Integer.valueOf(request.getParameter("id")));
            request.setAttribute("person", person);
            request.setAttribute("errors", constraintViolations);
            request.getRequestDispatcher("/WEB-INF/editPerson.jsp").forward(request, response);
        } else {
            personDAO.updatePerson(Integer.valueOf(request.getParameter("id")), person);

            request.setAttribute("method", "get");
            response.sendRedirect("/people");
        }
    }

    protected Person createNewPersonFromRequest(HttpServletRequest request) {
        Integer age;
        try {
            age = Integer.valueOf(request.getParameter("age"));
        } catch(NumberFormatException ex) {
            age = null;
        }
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        return new Person(name, age, email);
    }
}