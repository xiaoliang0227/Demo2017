package com.demo.zyl.demo2017;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.demo.zyl.demo2017.service.socket.TCPServerService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;

/**
 * Created by zhaoyongliang on 2017/10/10.
 */

public class SocketDemoActivity extends AppCompatActivity {

    private static final int MESSAGE_RECEIVE_NEW_MSG = 1000;

    private static final int MESSAGE_SOCKET_CONNECTED = 1001;

    private TextView message;

    private EditText sendMsg;

    private Button btnSend;

    private Socket mClientSocket;

    private PrintWriter mPrintWriter;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_RECEIVE_NEW_MSG:
                    message.setText(message.getText() + "\n" + (String) msg.obj);
                    break;
                case MESSAGE_SOCKET_CONNECTED:
                    btnSend.setEnabled(true);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_socket_demo);
        init();
    }

    private void init() {
        initField();
        startSocketService();
        delayConnectTcpServer();
    }

    private void delayConnectTcpServer() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                connectTcpServer();
            }
        }, 1000);
    }

    private void connectTcpServer() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Socket socket = null;
                while (socket == null) {
                    try {
                        socket = new Socket("localhost", 8888);
                        if (socket.isConnected()) {
                            mClientSocket = socket;
                            mPrintWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                            mHandler.sendEmptyMessage(MESSAGE_SOCKET_CONNECTED);
                            System.out.println("****************connect to tcp server success");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        SystemClock.sleep(1000);
                        System.out.println("****************connect to tcp server failure,retry...");
                    }
                }

                // 接收消息
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    while (!SocketDemoActivity.this.isFinishing()) {
                        String msg = br.readLine();
                        System.out.println("****************receive msg:" + msg);
                        if (!TextUtils.isEmpty(msg)) {
                            String time = new SimpleDateFormat("HH:mm:ss").format(System.currentTimeMillis());
                            final String showMsg = String.format("server(%s):%s", time, msg);
                            mHandler.obtainMessage(MESSAGE_RECEIVE_NEW_MSG, showMsg).sendToTarget();
                        }
                    }
                    System.out.println("****************quit...");
                    mPrintWriter.close();
                    br.close();
                    socket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void startSocketService() {
        Intent intent = new Intent(this, TCPServerService.class);
        startService(intent);
    }

    private void initField() {
        message = (TextView) findViewById(R.id.message);
        sendMsg = (EditText) findViewById(R.id.send_msg);
        btnSend = (Button) findViewById(R.id.btn_send);
    }

    public void sendMessage(View view) {
        final String msg = sendMsg.getText().toString();
        if (!TextUtils.isEmpty(msg) && null != mPrintWriter) {
            mPrintWriter.println(msg);
            sendMsg.setText("");
            String time = new SimpleDateFormat("HH:mm:ss").format(System.currentTimeMillis());
            final String showMsg = String.format("client(%s):%s", time, msg);
            message.setText(message.getText() + "\n" + showMsg);
        }
    }

    @Override
    protected void onDestroy() {
        if (mClientSocket != null) {
            try {
                mClientSocket.shutdownInput();
                mClientSocket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.onDestroy();
    }
}
