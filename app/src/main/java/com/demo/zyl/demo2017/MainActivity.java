package com.demo.zyl.demo2017;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

    public void dynamicDialogTestClicked(View view) {
        Intent intent = new Intent(this, DynamicDialogTestActivity.class);
        startActivity(intent);
    }

    public void collapsingToolbarTestClicked(View view) {
        Intent intent = new Intent(this, CollapsingToolbarActivity.class);
        startActivity(intent);
    }
}
