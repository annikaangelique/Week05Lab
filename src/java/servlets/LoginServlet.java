package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.*;

/**
 *
 * @author Annika Vestre
 */
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session.getAttribute("user") != null && request.getParameter("logout") == null) {
            response.sendRedirect("home");
        } else if (request.getParameter("logout") != null) {
            session.invalidate();
            request.setAttribute("message", "You have been successfully logged out");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp")
                    .forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        AccountService authentication = new AccountService();
        User user;
        
        request.setAttribute("username", username);
        request.setAttribute("password", password);

        if ("".equals(username) || "".equals(password)) {
            request.setAttribute("message", "Please enter username and password");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        }
        
        user = new User(username, password);

        if (authentication.login(username, password) == null) {
            request.setAttribute("message", "Incorrect username or password");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        } else {
            session.setAttribute("user", user);
            response.sendRedirect("home");
        }        
    }
}
