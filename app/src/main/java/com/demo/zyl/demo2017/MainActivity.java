package com.demo.zyl.demo2017;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn1, btn2, btn3, btn4, btnDemoSocket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        initView();
        initEvent();
    }

    private void initView() {
        btn1 = (Button) findViewById(R.id.btn_1);
        btn2 = (Button) findViewById(R.id.btn_2);
        btn3 = (Button) findViewById(R.id.btn_3);
        btn4 = (Button) findViewById(R.id.btn_4);
        btnDemoSocket = (Button) findViewById(R.id.btn_demo_socket);
    }

    private void initEvent() {
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btnDemoSocket.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_1:
                pageJump("tabStrip");
                break;
            case R.id.btn_2:
                pageJump("titleStrip");
                break;
            case R.id.btn_3:
                intent = new Intent(this, DrawerLayoutDemoActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_4:
                intent = new Intent(this, DrawerLayoutDemoActivity2.class);
                startActivity(intent);
                break;
            case R.id.btn_demo_socket:
                intent = new Intent(this, SocketDemoActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void pageJump(String type) {
        Intent intent = new Intent(this, CollectionDemoActivity.class);
        intent.putExtra("type", type);
        startActivity(intent);
    }
}
