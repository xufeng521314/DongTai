package com.example.biyingxiangmu.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.biyingxiangmu.R;
import com.example.biyingxiangmu.base.BaseAdapter;
import com.example.biyingxiangmu.bean.TrendsBean;

import java.util.Arrays;
import java.util.List;

public class TrendsAdapter extends BaseAdapter {

    public TrendsAdapter(List<TrendsBean.DataBean> list, Context context){
        super(list,context);
    }

    @Override
    protected int getLauout() {
        return R.layout.layout_trends_item;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int position, Object o) {
        //当前的动态数据
        TrendsBean.DataBean data = (TrendsBean.DataBean) o;
        ConstraintLayout layoutUser = (ConstraintLayout) holder.getView(R.id.layout_userinfo);
        //更新头像
        ImageView imgHead = (ImageView) layoutUser.findViewById(R.id.img_head);
        if(!TextUtils.isEmpty((CharSequence) data.getAvater())){
            RequestOptions options = RequestOptions.circleCropTransform();
            options.placeholder(R.mipmap.ic_launcher);
            Glide.with(context).load(data.getAvater()).apply(options).into(imgHead);
        }
        //更新用户昵称
        TextView txtNickname = (TextView) layoutUser.findViewById(R.id.txt_nickname);
        if(!TextUtils.isEmpty(data.getUsername())){
            txtNickname.setText(data.getUsername());
        }
        //显示创建动态的时间
        TextView txtTime = (TextView) holder.getView(R.id.txt_time);
        txtTime.setText(String.valueOf(data.getTime()));
        //内容显示
        TextView txtContent = (TextView) holder.getView(R.id.txt_content);
        if(!TextUtils.isEmpty(data.getContent())){
            txtContent.setText(data.getContent());
        }
        //图片内容
        RecyclerView recyImgs = (RecyclerView) holder.getView(R.id.recy_imgs);
        if(!TextUtils.isEmpty(data.getResources())){
            recyImgs.setVisibility(View.VISIBLE);
            String[] imgs = data.getResources().split("$");
            List<String> list = Arrays.asList(imgs);
            recyImgs.setLayoutManager(new GridLayoutManager(context,3));
            TrendsImgAdapter trendsImgAdapter = new TrendsImgAdapter(list,context);
            recyImgs.setAdapter(trendsImgAdapter);
        }else{
            recyImgs.setVisibility(View.GONE);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }
}
