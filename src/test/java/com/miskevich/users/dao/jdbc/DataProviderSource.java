package com.miskevich.users.dao.jdbc;

import com.miskevich.users.entity.User;
import org.testng.annotations.DataProvider;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataProviderSource {

    @DataProvider(name = "provideUserList")
    public static Object[][] provideUserList() {
        List<User> expectedUserList = new ArrayList<User>(){{
            add(createUser(1, "firstName1", "lastName1", 100.25,
                    Date.valueOf("1900-05-15")));
            add(createUser(2, "firstName2", "lastName2", 200.72,
                    Date.valueOf("1800-12-31")));
        }};
        return new Object[][] {
                { expectedUserList }
        };
    }

    @DataProvider(name = "provideUser")
    public static Object[][] provideUser() {
        User expectedUser = createUser(3, "firstName3", "lastName3", 400.0,
                Date.valueOf("1700-01-22"));
        return new Object[][] {
                { expectedUser }
        };
    }

    @DataProvider(name = "preparedStatement")
    public static Object[][] providePreparedStatement() {

        Map<String, String> map = new HashMap<>();
        map.put("SELECT id, firstName, lastName, salary, dateOfBirth FROM users WHERE id = :id",
                "SELECT id, firstName, lastName, salary, dateOfBirth FROM users WHERE id = ?");
        map.put("INSERT INTO users(id, firstName, lastName, salary, dateOfBirth) values(:id, :firstName, :lastName, :salary, :dateOfBirth)",
                "INSERT INTO users(id, firstName, lastName, salary, dateOfBirth) values(?, ?, ?, ?, ?)");
        map.put("UPDATE users SET firstName = :firstName, lastName = :lastName, salary = :salary, dateOfBirth = :dateOfBirth where id = :id",
                "UPDATE users SET firstName = ?, lastName = ?, salary = ?, dateOfBirth = ? where id = ?");

        return new Object[][] {
                { map }
        };
    }

    @DataProvider(name = "provideUserForSave")
    public static Object[][] provideUserForSave() {
        User user = new User();
        user.setFirstName("firstNameTest");
        user.setLastName("firstNameTest");
        user.setSalary(100500.36);
        user.setDateOfBirth(Date.valueOf("1700-01-22"));
        return new Object[][] {
                { user }
        };
    }

    @DataProvider(name = "provideUserFromRequest")
    public static Object[][] provideUserFromRequest() {
        String[] firstName = new String[]{"firstNameTest"};
        String[] lastName = new String[]{"lastNameTest"};
        String[] salary = new String[]{"100500.36"};
        String[] dateOfBirth = new String[]{"15/04/2017"};
        Map<String, String[]> parameterMap = new HashMap<>();
        parameterMap.put("firstName", firstName);
        parameterMap.put("lastName", lastName);
        parameterMap.put("salary", salary);
        parameterMap.put("dateOfBirth", dateOfBirth);
        return new Object[][] {
                { parameterMap }
        };
    }

    private static User createUser(int id, String firstName, String lastName, double salary, Date dateOfBirth){
        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setSalary(salary);
        user.setDateOfBirth(dateOfBirth);
        return user;
    }
}
