package com.miskevich.users.dao.jdbc;

import com.miskevich.users.dao.jdbc.utils.NamedPreparedDataBaseExecutor;
import com.miskevich.users.dao.jdbc.mapper.UserRowMapper;
import com.miskevich.users.entity.User;
import com.miskevich.users.dao.jdbc.utils.SQLMethod;

import java.util.HashMap;
import java.util.Map;

abstract class AbstractJdbcUserDao {

    private final static String INSERT_USER= "INSERT INTO users(firstName, lastName, salary, dateOfBirth) values(:firstName, :lastName, :salary, :dateOfBirth)";
    private final static String UPDATE_USER = "UPDATE users SET firstName = :firstName, lastName = :lastName, salary = :salary, dateOfBirth = :dateOfBirth where id = :id";

    private NamedPreparedDataBaseExecutor namedPreparedDataBaseExecutor;

    public NamedPreparedDataBaseExecutor getNamedPreparedDataBaseExecutor() {
        return namedPreparedDataBaseExecutor;
    }

    public void setNamedPreparedDataBaseExecutor(NamedPreparedDataBaseExecutor namedPreparedDataBaseExecutor) {
        this.namedPreparedDataBaseExecutor = namedPreparedDataBaseExecutor;
    }

    void saveOrUpdate(User value, SQLMethod method){
        Map<String, Object> param = generateParamsForQuery(value);
        if(method.equals(SQLMethod.INSERT)){
            namedPreparedDataBaseExecutor.queryForSave(INSERT_USER, param);
        }else if(method.equals(SQLMethod.UPDATE)){
            namedPreparedDataBaseExecutor.queryForSave(UPDATE_USER, param);
        }
    }

    private Map<String, Object> generateParamsForQuery(User user){
        Map<String, Object> param = new HashMap<>();
        param.put(":id", user.getId());
        param.put(":firstName", user.getFirstName().trim());
        param.put(":lastName", user.getLastName().trim());
        if(null != user.getSalary()){
            param.put(":salary", user.getSalary());
        }else {
            param.put(":salary", 0.00);
        }
        param.put(":dateOfBirth", user.getDateOfBirth());
        return param;
    }
}
