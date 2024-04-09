package pl.coderslab.utils;

import pl.coderslab.utils.entity.User;
import pl.coderslab.utils.entity.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/list")
public class servletList extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User[] users = UserDao.findAll();
        req.setAttribute("users", users);
        getServletContext().getRequestDispatcher("/users/list.jsp").forward(req,resp);
    }
}
