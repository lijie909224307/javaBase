package com.TCP.inet;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author lijie
 * @version 1.00
 * @Description: 查询主机IP地址
 * http://www.manongjc.com/java_example/net_address.html
 * @date 2020/3/15 17:53
 */
public class IpAddressSearch {
    public static void main(String[] args) {

        InetAddress inetAddress = null;

        try {
            inetAddress = InetAddress.getByName("www.baidu.com");
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.exit(2);
        }
        System.out.println(inetAddress.getHostName() + " > " + inetAddress.getHostAddress());

    }
}
