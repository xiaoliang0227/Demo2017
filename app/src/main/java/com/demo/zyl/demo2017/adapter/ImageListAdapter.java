package com.demo.zyl.demo2017.adapter;

import android.content.Context;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.zyl.demo2017.R;
import com.demo.zyl.demo2017.image.FullCache;
import com.demo.zyl.demo2017.image.ImageCache;
import com.demo.zyl.demo2017.image.ImageLoader;
import com.demo.zyl.demo2017.image.ImageUtil;
import com.demo.zyl.demo2017.image.bean.NewsBean;

import java.util.List;

/**
 * Created by zhaoyongliang on 2017/10/24.
 */

public class ImageListAdapter extends BaseAdapter {

    private ImageLoader imageLoader;

    private Context context;

    private List<NewsBean> newsList;

    public ImageListAdapter(Context context, List<NewsBean> newsList) {
        this.context = context;
        this.newsList = newsList;
        // 设置图片缓存
        initImageCache();
    }

    private void initImageCache() {
        imageLoader = new ImageLoader(context);
        String cacheDir = Environment.getExternalStorageDirectory() + "/demo2017/image/";
        ImageCache cache = new FullCache(cacheDir, 50 * 1024 * 1024);
        imageLoader.setmImageCache(cache);
    }

    @Override
    public int getCount() {
        return newsList.size();
    }

    @Override
    public Object getItem(int position) {
        return newsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (null == convertView) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.image_item, parent, false);
            holder.mImage = (ImageView) convertView.findViewById(R.id.m_image);
            holder.title = (TextView) convertView.findViewById(R.id.m_title);
            holder.subtitle = (TextView) convertView.findViewById(R.id.m_subtitle);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mImage.setImageResource(R.mipmap.ic_launcher);

        NewsBean item = newsList.get(position);

        holder.title.setText(item.getName());
        holder.subtitle.setText(item.getDescription());
        holder.mImage.setTag(ImageUtil.hashKeyFromUrl(item.getPicSmall()));
        imageLoader.display(item.getPicSmall(), holder.mImage);
        return convertView;
    }

    class ViewHolder {
        ImageView mImage;
        TextView title;
        TextView subtitle;
    }
}
