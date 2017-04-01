package com.miskevich.users.web.converter;

import com.miskevich.users.dao.jdbc.DataProviderSource;
import com.miskevich.users.entity.User;
import org.testng.annotations.Test;

import java.sql.Date;
import java.util.Map;

import static org.testng.Assert.*;

public class UserConverterTest {

    @Test(dataProvider = "provideUserFromRequest", dataProviderClass = DataProviderSource.class)
    public void testPopulateUserFromRequest(Map<String, String[]> parameterMap) throws Exception {
        User expected = new User();
        expected.setFirstName("firstNameTest");
        expected.setLastName("lastNameTest");
        expected.setSalary(100500.36);
        expected.setDateOfBirth(Date.valueOf("2017-04-15"));
        User actual = UserConverter.populateUserFromRequest(parameterMap);

        assertEquals(actual, expected);
    }

}