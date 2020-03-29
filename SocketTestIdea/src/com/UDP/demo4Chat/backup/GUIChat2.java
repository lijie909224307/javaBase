package com.UDP.demo4Chat.backup;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lijie
 * @version 1.00
 * @Description: 聊天GUI界面 + 发送 + 记录 + 清屏
 * @date 2020/3/24 21:24
 */
public class GUIChat2 extends Frame {

    private Button send;
    private TextField tf;
    private TextArea viewText;
    private Button log;
    private Button clear;
    private Button shake;
    private TextArea sendText;
    private DatagramSocket socket;
    private BufferedWriter bw;

    public GUIChat2() {

        init();
        southPanel();
        centerPanel();
        event();
    }

    public void event() {

        // 关闭窗体
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    socket.close();
                    bw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                System.exit(0);
            }
        });

        // 发送
        send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    send();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        // 聊天记录
        log.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    logFile();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        // 清屏
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewText.setText("");
            }
        });

        // 抖动
        shake.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shake();
            }
        });

    }

    private void shake() {

    }

    /**
     * 获取聊天记录
     */
    private void logFile() throws IOException {
        bw.flush();
        FileInputStream fis = new FileInputStream("config.txt");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();   // 在内存中创建缓冲区

        int len;
        byte[] arr = new byte[8192];
        while ((len = fis.read(arr)) != -1){
            baos.write(arr,0,len);
        }
        String str = baos.toString();   // 将内存中的内容转换成字符串
        viewText.setText(str);
        fis.close();

    }

    private void send() throws IOException {
        String msg = sendText.getText();    // 获取发送区域的内容
        String ip = tf.getText();               // 获取 IP 地址
        // 随机端口号
        DatagramPacket packet = new DatagramPacket(msg.getBytes(),
                msg.getBytes().length,InetAddress.getByName(ip),9999);
        socket.send(packet);    // 发送数据

        // 将发送的信息添加到显示区域中, 同时清除发送框中的数据
        String time = getCurrentTime();
        String str = time + " 我对:" + ip + "说\r\n" + msg + "\r\n\r\n";
        viewText.append(str);
        bw.write(str);          // 将信息写道数据库(文本文档)中
        sendText.setText("");
    }

    public void init() {
        this.setLocation(500,50);
        this.setSize(400,600);
        new Receive().start();
        this.setVisible(true);
        try {
            socket = new DatagramSocket();

            // 创建聊天记录储存文件  ,  true,加载而不重建覆盖
            bw = new BufferedWriter(new FileWriter("config.txt",true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void centerPanel() {
        Panel center = new Panel();// 创建中间的Panel
        viewText = new TextArea(); // 显示的文本区域
        // 发送的文本区域,列设置参数无所谓
        sendText = new TextArea(5,1);
        center.setLayout(new BorderLayout());   // 设置为边界布局管理器
        center.add(sendText,BorderLayout.SOUTH);    // 发送的文本区域放在南边
        center.add(viewText,BorderLayout.CENTER);   // 显示的文本区域放在中间
        viewText.setEditable(false);    // 显示区域不可编辑
        viewText.setBackground(Color.WHITE);    // 设置显示区域背景颜色为白色
        viewText.setFont(new Font("xxx",Font.PLAIN,16));    // 设置字体大小
        sendText.setFont(new Font("xxx",Font.PLAIN,16));    // 设置字体大小
        this.add(center,BorderLayout.CENTER);
    }


    public void southPanel(){
        Panel south = new Panel();  // 创建南边的Panel
        tf = new TextField(15); // 创建文本字段存储IP地址

        tf.setText("127.0.0.1");    // 设置默认值本机

        send = new Button("发送"); // 创建发送按钮
        // 创建记录按钮
        log = new Button("记录");
        // 创建清屏按钮
        clear = new Button("清屏");
        // 创建抖动按钮
        shake = new Button("抖动");
        south.add(tf);
        south.add(send);
        south.add(log);
        south.add(clear);
        south.add(shake);
        this.add(south,BorderLayout.SOUTH);     // 将Panel放在Frame的南边
    }

    public static void main(String[] args) {
        new GUIChat2();
    }


    /**
     * 接收和发送需要同时执行,所以定义成多线程的
     */
    private class Receive extends Thread{
        @Override
        public void run() {
            try {
                DatagramSocket socket = new DatagramSocket(9999);
                DatagramPacket packet = new DatagramPacket(new byte[8192],8192);

                while (true){   // 不断接收
                    socket.receive(packet); // 接收信息
                    byte[] arr = packet.getData(); // 获取字节数据
                    int len = packet.getLength();    // 获取有效的字节数据
                    String msg = new String(arr, 0, len);

                    String time = getCurrentTime();
                    String ip = packet.getAddress().getHostAddress();// 获取IP地址 TODO 不是Socket中获取?
                    String str = time + " " + ip + " 对我说:\r\n" + msg + "\r\n\r\n";  // CTRL+ALT+V 抽取局部变量
                    viewText.append(str);
                    bw.write(str);
                }

            } catch (SocketException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }





    /**
     * @description 获取当前时间字符串
     * @author Lijie
     * @date 2020/3/28
     * @param
     * @return java.lang.String
     */
    private String getCurrentTime() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        return format.format(date);
    }
}

