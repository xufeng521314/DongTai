package com.example.biyingxiangmu.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.biyingxiangmu.R;
import com.example.biyingxiangmu.base.BaseAdapter;

import java.util.List;

public class TrendsImgAdapter extends BaseAdapter {

    public TrendsImgAdapter(List<String> list, Context context){
        super(list,context);
    }

    @Override
    protected int getLauout() {
        return R.layout.layout_trends_image_item;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int position, Object o) {
        String imgUrl = (String) o;
        ImageView img = (ImageView) holder.getView(R.id.img);
        if(!TextUtils.isEmpty(imgUrl)){
            Glide.with(context).load(imgUrl).into(img);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }
}
