package com.inet;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author lijie
 * @version 1.00
 * @Description: 解析URL
 * @date 2020/3/15 23:44
 */
public class UrlParse {
    public static void main(String[] args) throws MalformedURLException {

        URL url = new URL("http://www.manong.com");

        System.out.println(url.toString());
        System.out.println(url.getDefaultPort());


    }
}
