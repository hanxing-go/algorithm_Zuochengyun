package Test;

import javax.xml.crypto.Data;
import java.io.*;
import java.net.Socket;
import java.sql.Time;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Main2 {
    public static void main(String[] args) {
        // 定义端口
        int port = 6666;

        try(
                // 创建端口 客户端套接字
                Socket socket = new Socket("localhost", port);
                // 创建本地输入流
                BufferedInputStream bis = new BufferedInputStream(
                        new FileInputStream("a.txt"));
                // 创建输出流
                OutputStream os = socket.getOutputStream();
        ) {
            // 先从本地读入文件
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = bis.read(bytes)) != -1) {
                os.write(bytes, 0, len);
                //再发送文件
//                System.out.println(new String(bytes, 0, len));
            }

            // 确保数据发送完成
            os.flush();
            // 打印发送完毕
            System.out.println("发送完毕");
            socket.shutdownOutput();//要关闭输出流，才可以让服务端认为数据已经传输完毕，否者会一直主阻塞

            // 获取服务器返回信息
            InputStream isByService = socket.getInputStream();
            while ((len = isByService.read(bytes)) != -1) {
                System.out.println(new String(bytes, 0, len));
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
