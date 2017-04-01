package com.miskevich.users.dao.jdbc.utils;

import com.miskevich.users.dao.jdbc.DataProviderSource;
import org.testng.annotations.Test;

import java.util.Map;

import static org.testng.Assert.*;

public class NamedParameterStatementTest {

    private NamedParameterStatement namedParameterStatement = new NamedParameterStatement();

    @Test(dataProvider = "preparedStatement", dataProviderClass = DataProviderSource.class)
    public void testReplaceParameters(Map<String, String> map) throws Exception {
        for(Map.Entry<String, String> entry : map.entrySet()){
            String actual = namedParameterStatement.replaceParameters(entry.getKey());
            assertEquals(actual, entry.getValue());
        }
    }

}