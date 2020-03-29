package com.TCP.inet;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author lijie
 * @version 1.00
 * @Description: 获取URL响应头信息
 * @date 2020/3/15 23:35
 */
public class UrlResponseHead {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://www.manong.com");
        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();

        Map<String, List<String>> fields = httpCon.getHeaderFields();
        Set<String> keys = fields.keySet();
        for(String key : keys){
            String value = httpCon.getHeaderField(key);
            System.out.println(key + " : " + value);
        }

        System.out.println("~~~~~~~~~~~~~~~~~~");

        Set<Map.Entry<String, List<String>>> set = fields.entrySet();
        Iterator<Map.Entry<String, List<String>>> iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, List<String>> next = iterator.next();
            System.out.println(next.getKey() + " : " + next.getValue().get(0));
        }

    }
}
