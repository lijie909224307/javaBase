package com.inet;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author lijie
 * @version 1.00
 * @Description: 查询本机 IP 和计算机名称
 * http://www.manongjc.com/java_example/net_localip.html
 * @date 2020/3/15 18:05
 */
public class LocalIpAddress {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        System.out.println(inetAddress.getHostAddress());   // 192.168.254.1
        System.out.println(inetAddress.getHostName());      // 计算机名字 DESKTOP-7D7MVR2
    }
}
