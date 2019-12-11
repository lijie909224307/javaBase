package com.demo;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;

/**
 * @author lijie
 * @version 1.00
 * @Description: TODO
 * @date 2019-08-26 14:19
 */
public class IncomingReader  implements Runnable{


    JTextArea incoming;
    ObjectInputStream ois;

    public IncomingReader() {
    }

    public IncomingReader(JTextArea incoming, ObjectInputStream ois) {
        this.incoming = incoming;
        this.ois = ois;
    }

    @Override
    public void run() {
        Info info;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            while ((info = (Info)ois.readObject()) != null) {
                incoming.append(info.getFromWho()+"  "+format.format(info.getDate())+"\n");
                incoming.append("  "+info.getMsg()+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public JTextArea getIncoming() {
        return incoming;
    }

    public void setIncoming(JTextArea incoming) {
        this.incoming = incoming;
    }

    public ObjectInputStream getOis() {
        return ois;
    }

    public void setOis(ObjectInputStream ois) {
        this.ois = ois;
    }
}
