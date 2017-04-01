package com.miskevich.users.dao.jdbc.utils;

import com.miskevich.users.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserRowMapper implements RowMapper<User> {

    public List<User> mapIntoList(ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();
        while (resultSet.next()){
            User user = new User();
            user.setId(resultSet.getInt("id"));
            user.setFirstName(resultSet.getString("firstName").trim());
            user.setLastName(resultSet.getString("lastName").trim());
            user.setSalary(resultSet.getDouble("salary"));
            user.setDateOfBirth(resultSet.getDate("dateOfBirth"));
            users.add(user);
        }
        return users;
    }


    public User mapIntoObject(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setFirstName(resultSet.getString("firstName").trim());
        user.setLastName(resultSet.getString("lastName").trim());
        user.setSalary(resultSet.getDouble("salary"));
        user.setDateOfBirth(resultSet.getDate("dateOfBirth"));
        return user;
    }

    public static Map<String, Object> generateParamsForQuery(User user){
        Map<String, Object> param = new HashMap<>();
        if(null != user.getId()){
            param.put(":id", user.getId());
        }
        if(null != user.getFirstName()){
            param.put(":firstName", user.getFirstName().trim());
        }
        if(null != user.getLastName()){
            param.put(":lastName", user.getLastName().trim());
        }
        if(null != user.getSalary()){
            param.put(":salary", user.getSalary());
        }else {
            param.put(":salary", 0.00);
        }
        if(null != user.getDateOfBirth()){
            param.put(":dateOfBirth", user.getDateOfBirth());
        }
        return param;
    }
}
