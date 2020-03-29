package com.TCP.chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author lijie
 * @version 1.00
 * @Description: 客户端
 * @date 2019-08-27 9:53
 */
public class VerySimpleChatClient {

    JTextArea incoming;
    JTextField outgoing;
    PrintWriter writer;
    BufferedReader reader;
    Socket sock;

    public static void main(String[] args) {
        VerySimpleChatClient client = new VerySimpleChatClient();
        client.go();
    }

    /**
     * @description 运行主入口
     * @author Lijie
     * @date 2019-08-27
     * @param
     * @return void
     */
    public void go(){
        JFrame frame = new JFrame("lijie simple demo client");
        JPanel mainPanel = new JPanel();
        incoming = new JTextArea(20,50);
        incoming.setLineWrap(true);
        incoming.setWrapStyleWord(true);
        incoming.setEditable(false);
        JScrollPane qScroller = new JScrollPane(incoming);
        qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        outgoing = new JTextField(20);
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new SendButtonListener());
        mainPanel.add(qScroller);
        mainPanel.add(outgoing);
        mainPanel.add(sendButton);

        // 连接
        setUpNetworking();

        // 监听接收消息
        Thread readerThread = new Thread(new IncomingReader());
        readerThread.start();

        frame.getContentPane().add(BorderLayout.CENTER , mainPanel);
        frame.setSize(600,500);
        frame.setVisible(true);
    }

    /**
     * @description 网络设置连接
     * @author Lijie
     * @date 2019-08-27
     * @param
     * @return void
     */
    private void setUpNetworking() {
        try {
            sock = new Socket("127.0.0.1",5000);
            InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
            reader = new BufferedReader(streamReader);
            writer = new PrintWriter(sock.getOutputStream());
            System.out.println("network established");
        }catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * @description 监听发送按钮事件
     * @author Lijie
     * @date 2019-08-27
     */
    public class SendButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
//				System.out.println(outgoing.getText());
                writer.println(outgoing.getText());
                writer.flush();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            outgoing.setText("");
            outgoing.requestFocus();
        }
    }


    /**
     * @description 接收消息线程
     * @author Lijie
     * @date 2019-08-27
     */
    public class IncomingReader implements Runnable{
        @Override
        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
//					System.out.println("read " + message);
                    incoming.append(message+"\n" );
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
