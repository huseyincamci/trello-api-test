package com.trellorest.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {
    String fileName;
    String result = "";
    InputStream inputStream;


    public PropertyUtil(String fileName) {
        this.fileName = fileName;
    }

    public String getPropValue(String propertyName) throws IOException {
        try {
            Properties prop = new Properties();

            inputStream = getClass().getClassLoader().getResourceAsStream(fileName);

            if (inputStream != null) {
                prop.load(inputStream);
                result = prop.getProperty(propertyName);
            } else {
                throw new FileNotFoundException("property file '" + fileName + "' not found in the classpath");
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return result;
    }
}
