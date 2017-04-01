package com.miskevich.users.web.converter;

import com.miskevich.users.entity.User;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;

public abstract class UserConverter {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);

    public static User populateUserFromRequest(Map<String, String[]> parameterMap){
        User user = new User();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()){
            String array = Arrays.toString(entry.getValue());
            String value = "";
            if(array.length() > 2){
                value = array.substring(1, array.length() - 1);
            }

            if(entry.getKey().equals("firstName")){
                user.setFirstName(value);
            }else if(entry.getKey().equals("lastName")){
                user.setLastName(value);
            }else if(entry.getKey().equals("dateOfBirth")){
                user.setDateOfBirth(convertStringToDate(value));
            }else if(entry.getKey().equals("salary")){
                if(!value.isEmpty()){
                    String withoutSpaces = value.replaceAll("\u00a0", "");
                    String withDots = withoutSpaces.replace(",", ".");
                    user.setSalary(Double.parseDouble(withDots));
                }
            }
        }
        return user;
    }

    private static Date convertStringToDate(String dateOfBirth){
        LocalDate localDate = LocalDate.parse(dateOfBirth, DATE_FORMATTER);
        return Date.valueOf(localDate);
    }

}
