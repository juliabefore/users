package com.miskevich.users.web.converter;

import com.miskevich.users.entity.User;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;

public abstract class UserConverter {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);

    public static User getUserFromRequest(Map<String, String[]> parameterMap){
        User user = new User();
        for (Map.Entry<String, String[]> entry : parameterMap.entrySet()){
            String array = Arrays.toString(entry.getValue());
            String value = "";
            if(array.length() > 2){
                value = array.substring(1, array.length() - 1);
            }

            if("firstName".equals(entry.getKey())){
                user.setFirstName(value);
            }else if("lastName".equals(entry.getKey())){
                user.setLastName(value);
            }else if("dateOfBirth".equals(entry.getKey())){
                user.setDateOfBirth(convertStringToDate(value));
            }else if("salary".equals(entry.getKey())){
                if(!value.isEmpty()){
                    String withoutSpaces = value.replaceAll("\u00a0", "");
                    String withDots = withoutSpaces.replace(",", ".");
                    user.setSalary(Double.parseDouble(withDots));
                }
            }
        }
        return user;
    }

    private static LocalDate convertStringToDate(String dateOfBirth){
        return LocalDate.parse(dateOfBirth, DATE_FORMATTER);
    }

}
