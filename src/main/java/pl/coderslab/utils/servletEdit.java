package pl.coderslab.utils;

import pl.coderslab.utils.entity.User;
import pl.coderslab.utils.entity.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/edit")
public class servletEdit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id =Integer.parseInt(req.getParameter("id"));
        User user = UserDao.read(id);
        req.setAttribute("user", user);
        getServletContext().getRequestDispatcher("/users/edit.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = Integer.parseInt(req.getParameter("id"));
       User user = UserDao.read(id);
       user.setUserName(req.getParameter("userName"));
       user.setEmail(req.getParameter("email"));
       UserDao.update(user);
        resp.sendRedirect("/user/list");
    }
}
