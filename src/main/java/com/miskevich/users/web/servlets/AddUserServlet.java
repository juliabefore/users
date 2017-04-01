package com.miskevich.users.web.servlets;


import com.miskevich.users.entity.User;
import com.miskevich.users.enums.HttpMethod;
import com.miskevich.users.service.IUserService;
import com.miskevich.users.web.converter.UserConverter;
import com.miskevich.users.web.templater.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



public class AddUserServlet extends HttpServlet {

    private IUserService userService;

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {

        //request parse
        Map<String, Object> pageVariables = createPageVariablesMap(request, HttpMethod.GET);
        pageVariables.put("message", "");

        //response generate
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

        BufferedWriter bufferedWriter = new BufferedWriter(response.getWriter());
        bufferedWriter.write(PageGenerator.instance().getPage("add_user.html", pageVariables));
        bufferedWriter.flush();
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
            User user = UserConverter.populateUserFromRequest(parameterMap);
            userService.save(user);
        }
        pageVariables.put("parameters", parameterMap.toString());
        return pageVariables;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
}
