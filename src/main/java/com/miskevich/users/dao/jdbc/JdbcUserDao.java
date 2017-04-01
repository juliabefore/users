package com.miskevich.users.dao.jdbc;

import com.miskevich.users.dao.GenericDao;
import com.miskevich.users.dao.jdbc.utils.NamedParameterStatement;
import com.miskevich.users.dao.jdbc.utils.UserRowMapper;
import com.miskevich.users.entity.User;

import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class JdbcUserDao implements GenericDao<User, String>{

    private final String SElECT_ALL_USERS = "SELECT id, firstName, lastName, salary, dateOfBirth FROM users";

    public List<User> getAll() {

        try(Connection connection = getConnection()){
            NamedParameterStatement ps = new NamedParameterStatement();
            Map<String, Object> param = UserRowMapper.generateParamsForQuery(new User());
            return ps.queryForList(SElECT_ALL_USERS, param, new UserRowMapper(), connection);

        }catch (SQLException | IOException e){
            throw new RuntimeException(e);
        }
    }

    public void save(User value) {

    }

    public User getById(String id) {
        User user = new User();
        user.setId(id);
        String query = "SELECT id, firstName, lastName, salary, dateOfBirth FROM users WHERE id = :id";
        Map<String, Object> param = UserRowMapper.generateParamsForQuery(user);

        try(Connection connection = getConnection()){
            NamedParameterStatement ps = new NamedParameterStatement();
            user = ps.queryForObject(query, param, new UserRowMapper(), connection);
            return user;
        }catch (SQLException | IOException e){
            throw new RuntimeException(e);
        }
    }

    private Connection getConnection() throws SQLException, IOException {
        Properties properties = new Properties();
        properties.load(JdbcUserDao.class.getResourceAsStream("/users_servlet.properties"));
        return DriverManager.getConnection(properties.getProperty("DB_URL"), properties.getProperty("USER"), properties.getProperty("PASS"));
    }
}
