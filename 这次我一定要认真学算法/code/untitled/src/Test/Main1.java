package Test;

import sun.security.acl.AclEntryImpl;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

public class Main1 {
    public static void main(String[] args){
        // 服务端
        // 先定义一个端口port
        int port = 6666;
        try(
                // 在端口创建一个服务器套接字
                ServerSocket serverSocket = new ServerSocket(port);
                // 监听来自端口的连接
                Socket socket = serverSocket.accept();

                // 创建输入流
                InputStream is = socket.getInputStream();
                // 创建输出流
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("b.txt"));

        ) {
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = is.read(bytes)) != -1) {
                bos.write(bytes, 0, len);
                System.out.println(new String(bytes, 0, len));
                System.out.println("执行到这里了");
            }

            bos.flush();
            System.out.println("写入完毕");
            //将上传完毕的信息返回给客户端
            OutputStream os = socket.getOutputStream();
            os.write("写入完毕".getBytes());
            os.flush(); // 确保消息发送

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
