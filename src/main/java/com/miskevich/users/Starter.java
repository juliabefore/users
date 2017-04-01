package com.miskevich.users;

import com.miskevich.users.dao.jdbc.JdbcUserDao;
import com.miskevich.users.service.UserService;
import com.miskevich.users.web.servlets.AllUsersServlet;

public class Starter {

    public static void main(String[] args) {

        JdbcUserDao jdbcUserDao = new JdbcUserDao();
        UserService userService = new UserService();
        userService.setUserDao(jdbcUserDao);

        AllUsersServlet allUsersServlet = new AllUsersServlet();
        allUsersServlet.setUserService(userService);


    }
}
