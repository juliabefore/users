package com.miskevich.users.dao.jdbc.utils;

import com.miskevich.users.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRowMapper implements RowMapper<User> {

    public List<User> mapIntoList(ResultSet resultSet) throws SQLException {
        List<User> users = new ArrayList<>();
        while (resultSet.next()){
            User user = new User();
            user.setId(resultSet.getString("id").trim());
            user.setFirstName(resultSet.getString("firstName").trim());
            user.setLastName(resultSet.getString("lastName").trim());
            user.setSalary(resultSet.getDouble("salary"));
            user.setDateOfBirth(resultSet.getTimestamp("dateOfBirth").toLocalDateTime());
            users.add(user);
        }
        return users;
    }


    public User mapIntoObject(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getString("id").trim());
        user.setFirstName(resultSet.getString("firstName").trim());
        user.setLastName(resultSet.getString("lastName").trim());
        user.setSalary(resultSet.getDouble("salary"));
        user.setDateOfBirth(resultSet.getTimestamp("dateOfBirth").toLocalDateTime());
        return user;
    }


}
