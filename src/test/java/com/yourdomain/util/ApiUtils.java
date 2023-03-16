package com.yourdomain.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ApiUtils {

    private final String soapApiFIlePath = Paths.get("src", "main", "java", "com", "yourdomain","api","soap").toString() + File.separator;
    private final String restApiFIlePath = Paths.get("src", "main", "java", "com", "yourdomain","api","rest").toString() + File.separator;

    public String getFileContent(String filePath, String fileName){
        String fileContent = null;

        try {
            fileContent = new String(Files.readAllBytes(Paths.get(filePath + fileName)));
        }catch (IOException e){
            e.printStackTrace();
        }
        return fileContent;
    }
}
