package com.inet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author lijie
 * @version 1.00
 * @Description: TODO
 * @date 2020/3/15 17:46
 */
public class TestClient {
    public static void main(String[] args) throws IOException {
        try {
            Socket s = new Socket("127.0.0.1", 1234);
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            String msg ;;
            while ((msg = br.readLine()) != null){
                System.out.println( msg );
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
