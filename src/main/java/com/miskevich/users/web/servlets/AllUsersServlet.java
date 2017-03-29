package com.miskevich.users.web.servlets;

import com.miskevich.servletexample.db.core.SQLHelper;
import com.miskevich.servletexample.entity.User;
import com.miskevich.users.web.templater.PageGenerator;
import com.miskevich.users.entity.User;
import com.miskevich.users.service.IUserService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.miskevich.servletexample.app.MyApp.pooledConnectionServlet;

public class AllUsersServlet extends HttpServlet {

    private IUserService userService;

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {

        //request parse
        Map<String, Object> pageVariables = createPageVariablesMap();
        pageVariables.put("message", "");

        List<User> users = userService.getAll();

        //response generate
        response.getWriter().println(PageGenerator.instance().getPage("all_users.html", pageVariables));

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

    }

    private Map<String, Object> createPageVariablesMap() {
        Map<String, Object> pageVariables = new HashMap<>();
        List<User> users = SQLHelper.getAllUsers(pooledConnectionServlet);
        pageVariables.put("users", users);
        return pageVariables;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
}
