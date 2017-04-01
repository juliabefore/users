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


public class EditUserServlet extends HttpServlet {

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
        bufferedWriter.write(PageGenerator.instance().getPage("edit_user.html", pageVariables));
        bufferedWriter.flush();
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        createPageVariablesMap(request, HttpMethod.POST);
        ServletHelper.defaultPost(request, response);
    }

    private Map<String, Object> createPageVariablesMap(HttpServletRequest request, HttpMethod method) {
        Map<String, Object> pageVariables = new HashMap<>();
        pageVariables.put("pathInfo", request.getPathInfo());
        User currentUser = userService.getById(getUserId(pageVariables));
        pageVariables.put("user", currentUser);
        if(method.equals(HttpMethod.POST)){
            Map<String, String[]> editedParameterMap = request.getParameterMap();
            User editedUser = UserConverter.populateUserFromRequest(editedParameterMap);
            editedUser.setId(currentUser.getId());
            userService.edit(editedUser);
        }
        return pageVariables;
    }

    private int getUserId(Map<String, Object> pageVariables){
        String pathInfo = String.valueOf(pageVariables.get("pathInfo")).substring(1);
        return Integer.parseInt(pathInfo);
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
}
