package com.miskevich.users.dao.jdbc;

import com.miskevich.users.entity.User;
import org.testng.annotations.Test;

import java.util.List;
import java.util.UUID;

import static org.testng.Assert.*;

public class JdbcUserDaoITest {

    private JdbcUserDao jdbcUserDao = new JdbcUserDao();

    @Test
    public void testGetAll() throws Exception {
        List<User> users = jdbcUserDao.getAll();
        for (User user : users){
            assertNotNull(user.getId());
            assertNotNull(user.getFirstName());
            assertNotNull(user.getLastName());
            assertNotNull(user.getDateOfBirth());
            System.out.println(user);
        }
    }

    @Test
    public void testGetById(){
        User user = jdbcUserDao.getById("5938ccdb-d0f0-443c-adae-fd1ce18d41a0");
        assertNotNull(user.getId());
        assertNotNull(user.getFirstName());
        assertNotNull(user.getLastName());
        assertNotNull(user.getDateOfBirth());
        System.out.println(user);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 6; i++) {
            System.out.println(UUID.randomUUID());
        }
    }

}