package com.yourdomain.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ApiUtils {

    /**
     * @param soapApiFIlePath: json file path
     * @param restApiFIlePath: xml file path
     * */
    private final String soapApiFIlePath = Paths.get("src", "main", "java", "com", "yourdomain","api","soap").toString() + File.separator;
    private final String restApiFIlePath = Paths.get("src", "main", "java", "com", "yourdomain","api","rest").toString() + File.separator;

    /**
     * @param filePath: The location of the targeted file
     * @param fileName: The name of the file to fetch from the targeted location above
     * @return fileContent: The file content fetched from the above targeted location
     * */
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
