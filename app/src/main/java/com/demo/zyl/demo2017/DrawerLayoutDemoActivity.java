package com.demo.zyl.demo2017;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by zhaoyongliang on 2017/4/25.
 */

public class DrawerLayoutDemoActivity extends AppCompatActivity {

    private String[] planetTitles;

    private DrawerLayout drawerLayout;

    private ListView leftDrawer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_drawer_layout_demo);
        init();
    }

    private void init() {
        initView();
        initLeftDrawer();
    }

    private void initView() {
        planetTitles = getResources().getStringArray(R.array.planets_array);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        leftDrawer = (ListView) findViewById(R.id.left_drawer);
    }

    private void initLeftDrawer() {
        leftDrawer.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_list_item, planetTitles));
        leftDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);
            }
        });
    }

    private void selectItem(int position) {
        Fragment fragment = new PlanetFragment();
        Bundle args = new Bundle();
        args.putString("content", planetTitles[position]);
        fragment.setArguments(args);

        FragmentManager manager = getSupportFragmentManager();
        manager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        leftDrawer.setItemChecked(position, true);
        drawerLayout.closeDrawer(leftDrawer);
    }
}
