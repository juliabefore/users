package com.miskevich.users.dao.jdbc;

import com.miskevich.users.dao.jdbc.utils.NamedParameterStatement;
import com.miskevich.users.dao.jdbc.utils.UserRowMapper;
import com.miskevich.users.entity.User;
import com.miskevich.users.enums.SQLMethod;

import java.sql.Connection;
import java.util.Map;

abstract class AbstractJdbcUserDao {

    private final String INSERT_USER= "INSERT INTO users(firstName, lastName, salary, dateOfBirth) values(:firstName, :lastName, :salary, :dateOfBirth)";
    private final String UPDATE_USER = "UPDATE users SET firstName = :firstName, lastName = :lastName, salary = :salary, dateOfBirth = :dateOfBirth where id = :id";

    void change(User value, Connection connection, SQLMethod method){
        NamedParameterStatement ps = new NamedParameterStatement();
        Map<String, Object> param = UserRowMapper.generateParamsForQuery(value);
        if(method.equals(SQLMethod.INSERT)){
            ps.queryForSave(INSERT_USER, param, connection);
        }else if(method.equals(SQLMethod.UPDATE)){
            ps.queryForSave(UPDATE_USER, param, connection);
        }
    }
}
