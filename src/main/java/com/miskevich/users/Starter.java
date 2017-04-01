package com.miskevich.users;

import com.miskevich.users.dao.jdbc.JdbcUserDao;
import com.miskevich.users.service.UserService;
import com.miskevich.users.web.servlets.AddUserServlet;
import com.miskevich.users.web.servlets.AllUsersServlet;
import com.miskevich.users.web.servlets.EditUserServlet;
import org.apache.commons.dbcp2.BasicDataSource;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.util.Properties;

public class Starter {

    public static void main(String[] args) throws Exception {

        //data source
        Properties properties = new Properties();
        properties.load(Starter.class.getResourceAsStream("/users_servlet.properties"));
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(properties.getProperty("DB_URL"));
        basicDataSource.setUsername(properties.getProperty("USER"));
        basicDataSource.setPassword(properties.getProperty("PASS"));
        basicDataSource.setInitialSize(5);

        //dao
        JdbcUserDao jdbcUserDao = new JdbcUserDao();
        jdbcUserDao.setBasicDataSource(basicDataSource);

        //service
        UserService userService = new UserService();
        userService.setUserDao(jdbcUserDao);

        //servlet config
        AllUsersServlet allUsersServlet = new AllUsersServlet();
        allUsersServlet.setUserService(userService);
        AddUserServlet addUserServlet = new AddUserServlet();
        addUserServlet.setUserService(userService);
        EditUserServlet editUserServlet = new EditUserServlet();
        editUserServlet.setUserService(userService);

        //jetty config
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        String pwdPath = System.getProperty("user.dir");
        context.setResourceBase(pwdPath + "/templates");
        context.setContextPath("/");
        context.addServlet(new ServletHolder(allUsersServlet), "/user/all");
        context.addServlet(new ServletHolder(addUserServlet), "/user/add");
        context.addServlet(new ServletHolder(editUserServlet), "/user/edit/*");
        //ServletContextHandler should have a DefaultServlet added to its servlet tree
        // (this is what actually serves static resources)
        ServletHolder holderPwd = new ServletHolder("default", DefaultServlet.class);
        holderPwd.setInitParameter("dirAllowed","true");
        context.addServlet(holderPwd,"/");

        Server server = new Server(8080);
        server.setHandler(context);

        server.start();
    }
}
