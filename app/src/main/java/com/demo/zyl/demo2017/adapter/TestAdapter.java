package com.demo.zyl.demo2017.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.demo.zyl.demo2017.R;

import java.util.List;

/**
 * Created by zhaoyongliang on 2018/1/8.
 */

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {

    private Context context;

    private List<String> data;

    public TestAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public TestAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.test_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(TestAdapter.ViewHolder holder, int position) {
        String item = data.get(position);
        holder.index.setText(String.valueOf(position + 1));
        holder.label.setText(item);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView index;
        TextView label;

        public ViewHolder(View view) {
            super(view);

            index = (TextView) view.findViewById(R.id.index);
            label = (TextView) view.findViewById(R.id.label);
        }
    }
}
