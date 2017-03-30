package com.miskevich.users.dao.jdbc.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface RowMapper <T>{
    List<T> mapIntoList(ResultSet resultSet) throws SQLException;

    T mapIntoObject(ResultSet resultSet) throws SQLException;
}
