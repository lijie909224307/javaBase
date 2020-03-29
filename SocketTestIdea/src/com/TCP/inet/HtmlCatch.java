package com.TCP.inet;

import java.io.*;
import java.net.URL;

/**
 * @author lijie
 * @version 1.00
 * @Description: 网页抓取 !!!!!
 * @date 2020/3/15 18:59
 */
public class HtmlCatch {


    public static void main(String[] args) throws IOException {

        URL url = new URL("http://www.manongjc.com");  // 需要协议 http:// 不然报错
        BufferedReader reader   = new BufferedReader(
                new InputStreamReader(url.openStream()));

        BufferedWriter writer = new BufferedWriter(new FileWriter("data.html"));
        String line ;
        while ((line = reader.readLine()) != null){
            System.out.println(line);
            writer.write(line);
            writer.newLine();
        }
        reader.close();
        writer.close();
    }

}
