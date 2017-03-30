package com.miskevich.users.dao.jdbc.utils;

import com.miskevich.users.entity.User;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

public class UserRowMapperTest {

    private UserRowMapper userRowMapper = new UserRowMapper();

    @DataProvider(name = "provideUserList")
    public Object[][] provideUserList() {
        List<User> expectedUserList = new ArrayList<User>(){{
            add(createUser("38400000-8cf0-11bd-b23e-10b96e4ef00d", "firstName1", "lastName1", 100.25,
                    LocalDateTime.of(1900, Month.MAY, 15, 02, 45, 34)));
            add(createUser("38400000-8cf0-11bd-b23e-10b96e4ef00e", "firstName2", "lastName2", 200.72,
                    LocalDateTime.of(1800, Month.DECEMBER, 31, 15, 20, 22)));
            }};
        return new Object[][] {
                { expectedUserList }
        };
    }

    @DataProvider(name = "provideUser")
    public Object[][] provideUser() {
        User expectedUser = createUser("38400000-8cf0-11bd-b23e-10b96e4ef00c", "firstName3", "lastName3", 400,
                LocalDateTime.of(1700, Month.JANUARY, 22, 07, 12, 47));
        return new Object[][] {
                { expectedUser }
        };
    }

    @Test(dataProvider = "provideUserList")
    public void testMapIntoList(List<User> expectedUserList) throws Exception {
        ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.next()).thenReturn(true).thenReturn(true).thenReturn(false);
        when(resultSet.getString("id")).thenReturn("38400000-8cf0-11bd-b23e-10b96e4ef00d").thenReturn("38400000-8cf0-11bd-b23e-10b96e4ef00e");
        when(resultSet.getString("firstName")).thenReturn("firstName1").thenReturn("firstName2");
        when(resultSet.getString("lastName")).thenReturn("lastName1").thenReturn("lastName2");
        when(resultSet.getDouble("salary")).thenReturn(100.25).thenReturn(200.72);
        when(resultSet.getTimestamp("dateOfBirth")).thenReturn(Timestamp.valueOf(LocalDateTime.of(1900, Month.MAY, 15, 02, 45, 34)))
                .thenReturn(Timestamp.valueOf(LocalDateTime.of(1800, Month.DECEMBER, 31, 15, 20, 22)));
        List<User> actualUserList = userRowMapper.mapIntoList(resultSet);

        assertTrue(expectedUserList.size() == actualUserList.size());
        for (int i = 0; i < expectedUserList.size(); i++) {
            assertEquals(actualUserList.get(i), expectedUserList.get(i));
        }
    }

    @Test
    public void testMapIntoObject() throws Exception {

    }

    private User createUser(String id, String firstName, String lastName, double salary, LocalDateTime dateOfBirth){
        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setSalary(salary);
        user.setDateOfBirth(dateOfBirth);
        return user;
    }

}