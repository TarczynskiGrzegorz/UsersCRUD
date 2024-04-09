package pl.coderslab.utils;

import pl.coderslab.utils.entity.User;
import pl.coderslab.utils.entity.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/add")
public class servletAdd extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/users/add.jsp").forward(req,resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().append(req.getParameter("userName"));
        resp.getWriter().append(req.getParameter("email"));
        resp.getWriter().append(req.getParameter("password"));
        User user = new User();
        user.setUserName(req.getParameter("userName"));
        user.setEmail(req.getParameter("email"));
        user.setPassword(req.getParameter("password"));
        UserDao.create(user);
        resp.sendRedirect("/user/list");
    }
}
