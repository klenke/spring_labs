package io.pivotal.stockservice;

import java.io.*;
import java.net.URL;


public class URLReader {

    public static void read(URL url) throws Exception {

        //access url and gather data into data.txt
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
