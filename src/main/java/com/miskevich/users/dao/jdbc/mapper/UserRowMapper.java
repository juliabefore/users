package com.miskevich.users.dao.jdbc.mapper;

import com.miskevich.users.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    public User map(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setFirstName(resultSet.getString("firstName").trim());
        user.setLastName(resultSet.getString("lastName").trim());
        user.setSalary(resultSet.getDouble("salary"));
        user.setDateOfBirth(resultSet.getDate("dateOfBirth").toLocalDate());
        return user;
    }
}
