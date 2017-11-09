package com.demo.zyl.demo2017;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
            case R.id.btn_image_demo:
                intent = new Intent(this, ImageLoadActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void pageJump(String type) {
        Intent intent = new Intent(this, CollectionDemoActivity.class);
        intent.putExtra("type", type);
        startActivity(intent);
    }

    public void btn1Clicked(View view) {
        pageJump("tabStrip");
    }

    public void btn2Clicked(View view) {
        pageJump("titleStrip");
    }

    public void btn3Clicked(View view) {
        Intent intent = new Intent(this, DrawerLayoutDemoActivity.class);
        startActivity(intent);
    }

    public void btn4Clicked(View view) {
        Intent intent = new Intent(this, DrawerLayoutDemoActivity2.class);
        startActivity(intent);
    }

    public void socketDemoClicked(View view) {
        Intent intent = new Intent(this, SocketDemoActivity.class);
        startActivity(intent);
    }

    public void imageDemoClicked(View view) {
        Intent intent = new Intent(this, ImageLoadActivity.class);
        startActivity(intent);
    }

    public void triangleViewTestClicked(View view) {
        Intent intent = new Intent(this, TriangleViewTestActivity.class);
        startActivity(intent);
    }
}
