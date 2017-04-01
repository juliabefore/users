package com.miskevich.users.dao.jdbc.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NamedParameterStatement {

    private List<String> paramFromQuery;

    public NamedParameterStatement(){
        this.paramFromQuery = new ArrayList<>();
    }

    public <T>List<T> queryForList(String query, Map<String, Object> param, RowMapper<T> mapper, Connection connection){
        try{
            PreparedStatement preparedStatement = generatePreparedStatement(query, connection);
            setParametersIntoPreparedStatement(param, preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            return mapper.mapIntoList(resultSet);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public <T> T queryForObject(String query, Map<String, Object> param, RowMapper<T> mapper, Connection connection){
        try(PreparedStatement preparedStatement = generatePreparedStatement(query, connection)){
            setParametersIntoPreparedStatement(param, preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return mapper.mapIntoObject(resultSet);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void queryForSave(String query, Map<String, Object> param, Connection connection){
        try(PreparedStatement preparedStatement = generatePreparedStatement(query, connection)){
            setParametersIntoPreparedStatement(param, preparedStatement);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private PreparedStatement setParametersIntoPreparedStatement(Map<String, Object> param, PreparedStatement preparedStatement) {
        try{
            for (int i = 1; i <= paramFromQuery.size(); i++) {
                Object value = param.get(paramFromQuery.get(i-1));

                if(value.getClass().equals(String.class)){
                    preparedStatement.setString(i, (String) value);
                }else if(value.getClass().equals(Double.class)){
                    preparedStatement.setDouble(i, (Double) value);
                }else if(value.getClass().equals(Date.class)){
                    preparedStatement.setDate(i, (Date) value, Calendar.getInstance());
                }else if(value.getClass().equals(Integer.class)){
                    preparedStatement.setInt(i, (Integer) value);
                }
            }
            return preparedStatement;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private PreparedStatement generatePreparedStatement(String query, Connection connection) {
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(replaceParameters(query));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return preparedStatement;
    }

    String replaceParameters(String query){
        Pattern pattern = Pattern.compile("(:\\w+)");
        Matcher matcher = pattern.matcher(query);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find())
        {
            String param = matcher.group(1);
            paramFromQuery.add(param);
            matcher.appendReplacement(stringBuffer, "?");
        }
        return matcher.appendTail(stringBuffer).toString();
    }
}
