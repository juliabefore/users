package com.miskevich.users.dao.jdbc.utils;

import com.miskevich.users.dao.jdbc.DataProviderSource;
import com.miskevich.users.entity.User;
import org.testng.annotations.Test;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class UserRowMapperTest {

    private UserRowMapper userRowMapper = new UserRowMapper();

    @Test(dataProvider = "provideUserList", dataProviderClass = DataProviderSource.class)
    public void testMapIntoList(List<User> expectedUserList) throws Exception {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getString("id")).thenReturn("38400000-8cf0-11bd-b23e-10b96e4ef00d").thenReturn("38400000-8cf0-11bd-b23e-10b96e4ef00e");
        when(resultSet.getString("firstName")).thenReturn("firstName1").thenReturn("firstName2");
        when(resultSet.getString("lastName")).thenReturn("lastName1").thenReturn("lastName2");
        when(resultSet.getDouble("salary")).thenReturn(100.25).thenReturn(200.72);
        when(resultSet.getDate("dateOfBirth")).thenReturn(Date.valueOf("1900-05-15")).thenReturn(Date.valueOf("1800-12-31"));
        List<User> actualUserList = userRowMapper.mapIntoList(resultSet);

        assertTrue(expectedUserList.size() == actualUserList.size());
        for (int i = 0; i < expectedUserList.size(); i++) {
            assertEquals(actualUserList.get(i), expectedUserList.get(i));
        }
    }

    @Test(dataProvider = "provideUser", dataProviderClass = DataProviderSource.class)
    public void testMapIntoObject(User expected) throws Exception {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getString("id")).thenReturn("38400000-8cf0-11bd-b23e-10b96e4ef00c");
        when(resultSet.getString("firstName")).thenReturn("firstName3");
        when(resultSet.getString("lastName")).thenReturn("lastName3");
        when(resultSet.getDouble("salary")).thenReturn(400.0);
        when(resultSet.getDate("dateOfBirth")).thenReturn(Date.valueOf("1700-01-22"));
        User actual = userRowMapper.mapIntoObject(resultSet);

        assertEquals(actual, expected);
    }

    @Test(dataProvider = "provideUser", dataProviderClass = DataProviderSource.class)
    public void testGenerateParamsForQuery(User user){
        Map<String, Object> expected = new HashMap<>();
        expected.put(":id", "38400000-8cf0-11bd-b23e-10b96e4ef00c");
        expected.put(":firstName", "firstName3");
        expected.put(":lastName", "lastName3");
        expected.put(":salary", 400.0);
        expected.put(":dateOfBirth", Date.valueOf("1700-01-22"));
        Map<String, Object> actual = userRowMapper.generateParamsForQuery(user);

        assertEquals(actual, expected);
    }

}