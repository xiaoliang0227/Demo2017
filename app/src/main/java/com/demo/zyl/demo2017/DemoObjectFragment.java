package com.demo.zyl.demo2017;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by zhaoyongliang on 2017/4/25.
 */

public class DemoObjectFragment extends Fragment {

    public static final String ARG_OBJECT = "object";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_collect_object, container, false);
        Bundle args = getArguments();
        TextView text1 = (TextView) rootView.findViewById(R.id.text1);
        text1.setText(Integer.toString(args.getInt(ARG_OBJECT)));
        return rootView;
    }
}
