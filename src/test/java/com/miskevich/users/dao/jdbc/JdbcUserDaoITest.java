//package com.miskevich.users.dao.jdbc;
//
//import com.miskevich.users.entity.User;
//import org.apache.commons.dbcp2.BasicDataSource;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.Test;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Properties;
//
//import static org.testng.Assert.*;
//
//public class JdbcUserDaoITest {
//
//    private JdbcUserDao jdbcUserDao = new JdbcUserDao();
//
//    @BeforeClass
//    private void initializeConnection() throws IOException {
//        Properties properties = new Properties();
//        properties.load(JdbcUserDaoITest.class.getResourceAsStream("/db.properties"));
//        BasicDataSource basicDataSource = new BasicDataSource();
//        basicDataSource.setUrl(properties.getProperty("db.url"));
//        basicDataSource.setUsername(properties.getProperty("db.user"));
//        basicDataSource.setPassword(properties.getProperty("db.password"));
//        basicDataSource.setInitialSize(5);
//        jdbcUserDao.setBasicDataSource(basicDataSource);
//    }
//
//    @Test
//    public void testGetAll() throws Exception {
//        List<User> users = jdbcUserDao.getAll();
//        for (User user : users){
//            assertNotNull(user.getId());
//            assertNotNull(user.getFirstName());
//            assertNotNull(user.getLastName());
//            assertNotNull(user.getDateOfBirth());
//            System.out.println(user);
//        }
//    }
//
//    @Test
//    public void testGetById(){
//        User user = jdbcUserDao.getById(5);
//        assertNotNull(user.getId());
//        assertNotNull(user.getFirstName());
//        assertNotNull(user.getLastName());
//        assertNotNull(user.getDateOfBirth());
//        System.out.println(user);
//    }
//
//    @Test(dataProvider = "provideUserForSave", dataProviderClass = DataProviderSource.class)
//    public void testSave(User user){
//        jdbcUserDao.save(user);
//    }
//
//    @Test(dataProvider = "provideUserForSave", dataProviderClass = DataProviderSource.class)
//    public void testEdit(User user){
//        jdbcUserDao.edit(user);
//    }
//
//}