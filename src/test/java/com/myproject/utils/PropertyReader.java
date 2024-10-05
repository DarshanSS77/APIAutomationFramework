package com.myproject.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class PropertyReader {

    public static String readProperty(String key) {
        Properties properties = new Properties();

        try {
            //
            FileInputStream fileInputStream = new FileInputStream("src/test/resourses/data.properties");
            properties.load(fileInputStream);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return properties.getProperty(key);
    }
}
