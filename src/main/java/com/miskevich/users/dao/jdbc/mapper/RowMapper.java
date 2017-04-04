package com.miskevich.users.dao.jdbc.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface RowMapper <T>{

    T map(ResultSet resultSet) throws SQLException;
}
