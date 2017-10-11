package com.demo.zyl.demo2017.service.socket;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

/**
 * Created by zhaoyongliang on 2017/10/10.
 */

public class TCPServerService extends Service {

    private boolean mIsServiceDestoryed = false;

    private String[] mDefinedMessages = new String[] {
            "你好啊，哈哈",
            "请问你叫什么名字？",
            "今天上海天气不错啊，Tom",
            "你知道吗？我可是可以和多个人同时聊天的哦",
            "给你讲个笑话吧：据说爱笑的人运气不会太差，你说呢？"
    };

    @Override
    public void onCreate() {
        new Thread(new TcpServer()).start();
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        mIsServiceDestoryed = true;
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private class TcpServer implements Runnable {

        @Override
        public void run() {
            ServerSocket serverSocket = null;
            // 监听本地8888端口
            try {
                serverSocket = new ServerSocket(8888);
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }

            while (!mIsServiceDestoryed) {
                // 接收客户端请求
                try {
                    final Socket client = serverSocket.accept();
                    System.out.println("****************accept");
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                responseClient(client);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void responseClient(Socket client) throws IOException {
            // 接收客户端消息
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            // 向客户端发消息
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(client.getOutputStream())), true);
            out.println("欢迎来到聊天室！");
            while (!mIsServiceDestoryed) {
                String str = in.readLine();
                System.out.println(String.format("****************message from client:%s", str));
                if (TextUtils.isEmpty(str)) {
                    // 客户端断开连接
                    System.out.println("****************客户端断开连接");
                    break;
                }
                int i = new Random().nextInt(mDefinedMessages.length);
                String msg = mDefinedMessages[i];
                out.println(msg);
                System.out.println(String.format("****************server send message:%s", msg));
            }
            System.out.println("****************client quit");
            // 关闭流
            in.close();
            out.close();
            client.close();
        }
    }
}
