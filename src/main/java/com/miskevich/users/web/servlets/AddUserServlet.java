package com.miskevich.users.web.servlets;


import com.miskevich.users.entity.User;
import com.miskevich.users.enums.HttpMethod;
import com.miskevich.users.web.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



public class AddUserServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {

        Map<String, Object> pageVariables = createPageVariablesMap(request, HttpMethod.GET);
        pageVariables.put("message", "");

        response.getWriter().println(PageGenerator.instance().getPage("add_user.html", pageVariables));

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        createPageVariablesMap(request, HttpMethod.POST);
        ServletHelper.defaultPost(request, response);
    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request, HttpMethod method) {
        Map<String, Object> pageVariables = new HashMap<>();
        Map<String, String[]> parameterMap = request.getParameterMap();
        if(method.equals(HttpMethod.POST)){
//            String query = QueryGenerator.createSQLInsert(parameterMap);
//            User user = UserService.populateUser(parameterMap);
//            SQLHelper.changeUser(pooledConnectionServlet, query, user, SQLMethod.INSERT);
        }

        pageVariables.put("parameters", parameterMap.toString());
        return pageVariables;
    }
}
