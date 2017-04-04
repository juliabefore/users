package com.miskevich.users.dao.jdbc.utils;

import com.miskevich.users.dao.jdbc.mapper.RowMapper;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NamedPreparedDataBaseExecutor {

    private static final Pattern PATTERN = Pattern.compile("(:\\w+)");
    private DataSource dataSource;

//    public NamedPreparedDataBaseExecutor(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    public <T>List<T> queryForList(String query, Map<String, Object> param, RowMapper<T> mapper){
        QueryAndParam queryAndParam = replaceParameters(query);
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = generatePreparedStatement(queryAndParam, param, connection);
            ResultSet resultSet = preparedStatement.executeQuery()){
            List<T> list = new ArrayList<>();
            while (resultSet.next()){
                list.add(mapper.map(resultSet));
            }
            return list;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public <T> T queryForObject(String query, Map<String, Object> param, RowMapper<T> mapper){
        QueryAndParam queryAndParam = replaceParameters(query);
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = generatePreparedStatement(queryAndParam, param, connection);
            ResultSet resultSet = preparedStatement.executeQuery()){
            resultSet.next();
            return mapper.map(resultSet);
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void queryForSave(String query, Map<String, Object> param){
        QueryAndParam queryAndParam = replaceParameters(query);
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = generatePreparedStatement(queryAndParam, param, connection)){
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private PreparedStatement setParametersIntoPreparedStatement(Map<String, Object> param, PreparedStatement preparedStatement, List<String> paramFromQuery) {
        try{
            for (int i = 1; i <= paramFromQuery.size(); i++) {
                Object value = param.get(paramFromQuery.get(i-1));

                if(value.getClass().equals(String.class)){
                    preparedStatement.setString(i, (String) value);
                }else if(value.getClass().equals(Double.class)){
                    preparedStatement.setDouble(i, (Double) value);
                }else if(value.getClass().equals(LocalDate.class)){
                    //Convert ???
                    preparedStatement.setDate(i, Date.valueOf((LocalDate) value), Calendar.getInstance());
                }else if(value.getClass().equals(Integer.class)){
                    preparedStatement.setInt(i, (Integer) value);
                }
            }
            return preparedStatement;
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    private PreparedStatement generatePreparedStatement(QueryAndParam queryAndParam, Map<String, Object> param, Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(queryAndParam.queryForExecute);
        setParametersIntoPreparedStatement(param, preparedStatement, queryAndParam.paramFromQuery);
        return preparedStatement;
    }

    QueryAndParam replaceParameters(String query){
        Matcher matcher = PATTERN.matcher(query);
        StringBuffer stringBuffer = new StringBuffer();
        List<String> paramFromQuery = new ArrayList<>();
        while (matcher.find())
        {
            String param = matcher.group(1);
            paramFromQuery.add(param);
            matcher.appendReplacement(stringBuffer, "?");
        }
        String queryForExecute = matcher.appendTail(stringBuffer).toString();
        return new QueryAndParam(queryForExecute, paramFromQuery);
    }

    private static class QueryAndParam{
        final List<String> paramFromQuery;
        final String queryForExecute;

        QueryAndParam(String queryForExecute, List<String> paramFromQuery) {
            this.paramFromQuery = paramFromQuery;
            this.queryForExecute = queryForExecute;
        }
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
