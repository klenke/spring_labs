package io.pivotal.stockservice;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class URLReader {

    public static void main(String[] args) throws Exception {

        File file = new File("src/main/resources/data.txt");
        if(file.createNewFile()){
            System.out.println("File created");
        } else {
            System.out.println("File not created");
        }

        URL url = new URL("https://bootcamp-training-files.cfapps.io/week2/week2-stocks.json");
        BufferedInputStream in = new BufferedInputStream(url.openStream());
        FileOutputStream out = new FileOutputStream("src/main/resources/data.txt");
        byte[] data = new byte[1024];
        int count;

        while((count = in.read(data, 0, 1024)) != -1){
            out.write(data, 0, count);
        }
        out.close();
        in.close();
    }
}
