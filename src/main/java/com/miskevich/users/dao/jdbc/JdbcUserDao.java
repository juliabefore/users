package com.miskevich.users.dao.jdbc;

import com.miskevich.users.dao.GenericDao;
import com.miskevich.users.dao.jdbc.mapper.RowMapper;
import com.miskevich.users.dao.jdbc.mapper.UserRowMapper;
import com.miskevich.users.entity.User;
import com.miskevich.users.dao.jdbc.utils.SQLMethod;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcUserDao extends AbstractJdbcUserDao implements GenericDao<User, Integer>{


    private static final String SElECT_ALL_USERS = "SELECT id, firstName, lastName, salary, dateOfBirth FROM users";
    private static final String SELECT_BY_ID = "SELECT id, firstName, lastName, salary, dateOfBirth FROM users WHERE id = :id";
    private static final RowMapper<User> USER_ROW_MAPPER = new UserRowMapper();

    public List<User> getAll() {
        Map<String, Object> param = new HashMap<>();
        return getNamedPreparedDataBaseExecutor().queryForList(SElECT_ALL_USERS, param, USER_ROW_MAPPER);
    }

    public void save(User value) {
        saveOrUpdate(value, SQLMethod.INSERT);
    }

    public void edit(User value){
        saveOrUpdate(value, SQLMethod.UPDATE);
    }

    public User getById(Integer id) {
        Map<String, Object> param = new HashMap<>();
        param.put(":id", id);
        return getNamedPreparedDataBaseExecutor().queryForObject(SELECT_BY_ID, param, USER_ROW_MAPPER);
    }
}
