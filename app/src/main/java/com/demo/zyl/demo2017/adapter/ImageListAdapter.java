package com.demo.zyl.demo2017.adapter;

import android.content.Context;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.demo.zyl.demo2017.CommonUtil;
import com.demo.zyl.demo2017.R;
import com.demo.zyl.demo2017.image.FullCache;
import com.demo.zyl.demo2017.image.ImageCache;
import com.demo.zyl.demo2017.image.ImageLoader;

import java.util.List;

/**
 * Created by zhaoyongliang on 2017/10/24.
 */

public class ImageListAdapter extends BaseAdapter {

    private ImageLoader imageLoader;

    private Context context;

    private List<String> imageUrlList;

    public ImageListAdapter(Context context, List<String> imageUrlList) {
        this.context = context;
        this.imageUrlList = imageUrlList;
        // 设置图片缓存
        initImageCache();
    }

    private void initImageCache() {
        imageLoader = new ImageLoader(context);
        String cacheDir = Environment.getExternalStorageDirectory() + "/demo2017/image/";
        ImageCache cache = new FullCache(cacheDir);
        imageLoader.setmImageCache(cache);
    }

    @Override
    public int getCount() {
        return null == imageUrlList ? 0 : imageUrlList.size();
    }

    @Override
    public Object getItem(int position) {
        return null == imageUrlList ? null : imageUrlList.get(position);
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
            holder.mLabel = (TextView) convertView.findViewById(R.id.m_label);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mImage.setImageBitmap(null);
        holder.mLabel.setText(String.format("测试图片%d", position));
        String url = imageUrlList.get(position);
        imageLoader.display(url, CommonUtil.getImageNameByUrl(url), holder.mImage);
        return convertView;
    }

    class ViewHolder {
        ImageView mImage;
        TextView mLabel;
    }
}
