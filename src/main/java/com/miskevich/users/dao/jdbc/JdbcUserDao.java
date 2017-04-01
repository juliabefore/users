package com.miskevich.users.dao.jdbc;

import com.miskevich.users.dao.GenericDao;
import com.miskevich.users.dao.jdbc.utils.NamedParameterStatement;
import com.miskevich.users.dao.jdbc.utils.UserRowMapper;
import com.miskevich.users.entity.User;
import com.miskevich.users.enums.SQLMethod;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.List;
import java.util.Map;

public class JdbcUserDao extends AbstractJdbcUserDao implements GenericDao<User, Integer>{

    private BasicDataSource basicDataSource;

    private final String SElECT_ALL_USERS = "SELECT id, firstName, lastName, salary, dateOfBirth FROM users";
    private final String SELECT_BY_ID = "SELECT id, firstName, lastName, salary, dateOfBirth FROM users WHERE id = :id";

    public List<User> getAll() {

        try(Connection connection = basicDataSource.getConnection()){
            NamedParameterStatement ps = new NamedParameterStatement();
            Map<String, Object> param = UserRowMapper.generateParamsForQuery(new User());
            return ps.queryForList(SElECT_ALL_USERS, param, new UserRowMapper(), connection);

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void save(User value) {
        try(Connection connection = basicDataSource.getConnection()){
            change(value, connection, SQLMethod.INSERT);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void edit(User value){
        try(Connection connection = basicDataSource.getConnection()){
            change(value, connection, SQLMethod.UPDATE);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public User getById(Integer id) {
        User user = new User();
        user.setId(id);

        Map<String, Object> param = UserRowMapper.generateParamsForQuery(user);

        try(Connection connection = basicDataSource.getConnection()){
            NamedParameterStatement ps = new NamedParameterStatement();
            user = ps.queryForObject(SELECT_BY_ID, param, new UserRowMapper(), connection);
            return user;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void setBasicDataSource(BasicDataSource basicDataSource) {
        this.basicDataSource = basicDataSource;
    }
}
