package com.demo.zyl.demo2017;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.demo.zyl.demo2017.adapter.ImageListAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhaoyongliang on 2017/10/24.
 */

public class ImageLoadActivity extends AppCompatActivity {

    private static List<String> urlList = new ArrayList<>();

    static {
        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1508839958633&di=0315c5a6473f09751ea09ba5fac7f181&imgtype=0&src=http%3A%2F%2Fpic.yesky.com%2FuploadImages%2F2014%2F337%2F46%2F8L637SH3D020.jpg");
        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1508839958633&di=824ce7a7188f3ca8845d3e127fbb12d6&imgtype=0&src=http%3A%2F%2Fpic.yesky.com%2FuploadImages%2F2015%2F165%2F34%2FOU9YDOQWDX57.jpg");
        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1508839958633&di=f0aa422876ec47cb2e28ccc5ecb846e3&imgtype=0&src=http%3A%2F%2Fpic.yesky.com%2FuploadImages%2F2014%2F287%2F22%2F06GVJ65S8H28.jpg");
        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1508839958633&di=9af56ac35d9d2001b2cc8af650dd7da7&imgtype=0&src=http%3A%2F%2Fwww.ld12.com%2Fupimg358%2Fallimg%2F20160503%2Fhqfrr3wzbki2124.jpg");
        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1508839958632&di=67d750053d18b77de77de11ccebf9caf&imgtype=0&src=http%3A%2F%2Fws2.cdn.caijing.com.cn%2F2013-11-11%2F113550645.jpg");
        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1508839958632&di=18e8365912be42f89fe966dc50c49ba2&imgtype=0&src=http%3A%2F%2Fpic.5442.com%3A82%2F2014%2F0911%2F15%2F02.jpg%2521960.jpg");
        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1508839958632&di=6662ae489ba6c6f8dad68960b666c0c9&imgtype=0&src=http%3A%2F%2Fimg.pconline.com.cn%2Fimages%2Fupload%2Fupc%2Ftx%2Fwallpaper%2F1307%2F19%2Fc0%2F23509344_1374219743117.jpg");
        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1508839958632&di=27750ba9fa81007bab70306b1ae998c4&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2Fa%2F584a75f18ac99.jpg");
        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1508839958632&di=229030af793dd8b43bdb7f9179662e0b&imgtype=0&src=http%3A%2F%2Fimg5.caijing.com.cn%2F2013-11-11%2F113550635.jpg");
        urlList.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=160103731,3393293556&fm=27&gp=0.jpg");
        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1508839958632&di=c8286704e44d49cb352dae8b4a5f7d4a&imgtype=0&src=http%3A%2F%2Fpic.yesky.com%2FuploadImages%2F2014%2F287%2F28%2FRFEPGISDC5NV.jpg");
        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1508839958631&di=1d880bf476830d98cdae8fba8fdbbd72&imgtype=0&src=http%3A%2F%2Ftx1.cdn.caijing.com.cn%2F2013-11-11%2F113550633.jpg");
        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1508839958631&di=f9f76b5e32f6c945d88ff4b561512dd6&imgtype=0&src=http%3A%2F%2Fimg.520zhxx.com%3A8033%2Fmeinv%2F14%2F09%2F22%2F23%2F20140922235254649.jpg");
        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1508839958631&di=9bb830d5ad06429079a31c3344aeacf1&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F4%2F584a75154e3f1.jpg");
        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1508839958631&di=dac649792b0ee2abf02f0bb7474d80c5&imgtype=0&src=http%3A%2F%2Fpic.5442.com%2F2014%2F0911%2F15%2F04.jpg%2521960.jpg");
        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1508839958630&di=9c12eed91c78410c187ac8a38b104a2f&imgtype=0&src=http%3A%2F%2Fpic.5442.com%2F2014%2F0911%2F15%2F03.jpg%2521960.jpg");
        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1508839958630&di=d88c2b7ad68d450d9114c9b7e73ed8c9&imgtype=0&src=http%3A%2F%2Fn1.itc.cn%2Fimg8%2Fwb%2Frecom%2F2016%2F08%2F19%2F147156019559901111.JPEG");
        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1508839958630&di=5c1955a013eed5c347469e034d1de2ff&imgtype=0&src=http%3A%2F%2Fimg5.duitang.com%2Fuploads%2Fitem%2F201508%2F16%2F20150816145427_ekHQC.jpeg");
        urlList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1508839958628&di=b5a676e0d7184c111f7012af15294474&imgtype=0&src=http%3A%2F%2Fimage.tianjimedia.com%2FuploadImages%2F2014%2F337%2F39%2F65SU5929SGYG.jpg");
    }

    private ListView mImageList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_image_load);
        mImageList = (ListView) findViewById(R.id.m_image_list);

        showImageData();
    }

    private void showImageData() {
        ImageListAdapter adapter = new ImageListAdapter(this, urlList);
        mImageList.setAdapter(adapter);
    }
}
