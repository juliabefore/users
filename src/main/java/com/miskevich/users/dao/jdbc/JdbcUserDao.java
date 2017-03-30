package com.miskevich.users.dao.jdbc;

import com.miskevich.users.dao.GenericDao;
import com.miskevich.users.dao.jdbc.utils.UserRowMapper;
import com.miskevich.users.entity.User;

import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class JdbcUserDao implements GenericDao<User, String>{

    public List<User> getAll() {
        String query = "select * from users";

        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery()
        ){
            UserRowMapper rowMapper = new UserRowMapper();
            return rowMapper.mapIntoList(resultSet);

        }catch (SQLException | IOException e){
            throw new RuntimeException(e);
        }
    }

    public void save(User value) {

    }

    public User getById(String id) {
        return null;
    }

    private Connection getConnection() throws SQLException, IOException {
        Properties properties = new Properties();
        properties.load(JdbcUserDao.class.getResourceAsStream("/users_servlet.properties"));
        return DriverManager.getConnection(properties.getProperty("DB_URL"), properties.getProperty("USER"), properties.getProperty("PASS"));
    }
}
