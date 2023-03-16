package com.yourdomain.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ReadConfigFile {

    public static Map<String, String> readProperties() throws FileNotFoundException, IOException {

        String propFilePath;

        String defaultEnv = System.getProperty("DefaultENV");
        System.out.println("prop file to read defined via CMD is-> " + defaultEnv);

        if (defaultEnv != null && defaultEnv.equalsIgnoreCase("DEV1")) {
            propFilePath = Paths.get("src", "test", "resources", "data", "DEV1", "user.properties").toString() + File.separator;
        } else if (defaultEnv != null && defaultEnv.equalsIgnoreCase("QA1")) {
            propFilePath = Paths.get("src", "test", "resources", "data", "QA1", "user.properties").toString() + File.separator;
        } else if (defaultEnv != null && defaultEnv.equalsIgnoreCase("UAT1")) {
            propFilePath = Paths.get("src", "test", "resources", "data", "UAT1", "user.properties").toString() + File.separator;
        } else if (defaultEnv == null) {
            System.out.println("Env not defined via CMD, hence defaulting to DEV1, if you want to default to a diff env, please make an update in ReadConfigFile.java");
            propFilePath = Paths.get("src", "test", "resources", "data", "DEV1", "user.properties").toString() + File.separator;
        } else {
            throw new RuntimeException("Default environment is not recognized");
        }

        Properties prop = new Properties();
        prop.load(readFile(propFilePath));

        Map<String, String> properties = new HashMap<String, String>();

        Enumeration<Object> KeyValues = prop.keys();
        while (KeyValues.hasMoreElements()) {
            String key = (String) KeyValues.nextElement();
            String value = prop.getProperty(key);
            properties.put(key, System.getProperty(key, value));
        }

        return properties;
    }

    public static FileInputStream readFile(String file) throws FileNotFoundException {
        return new FileInputStream(new File(file));
    }


}
