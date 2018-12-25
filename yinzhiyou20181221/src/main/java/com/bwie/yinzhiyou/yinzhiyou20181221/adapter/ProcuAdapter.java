package com.bwie.yinzhiyou.yinzhiyou20181221.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.yinzhiyou.yinzhiyou20181221.R;
import com.bwie.yinzhiyou.yinzhiyou20181221.bean.ShopCarBean;

import java.util.ArrayList;
import java.util.List;

public class ProcuAdapter extends RecyclerView.Adapter<ProcuAdapter.MyViewHolder> {
    private Context context;
    private List<ShopCarBean.DataBean.ListBean>mList=new ArrayList<>();

    public ProcuAdapter(Context context, List<ShopCarBean.DataBean.ListBean> mList) {
        this.context = context;
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=View.inflate(context,R.layout.prucutom_item,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.textView_title.setText(mList.get(i).getTitle());
        myViewHolder.price.setText(mList.get(i).getPrice()+"");
        String images = mList.get(i).getImages();
        String[] split = images.split("\\|");
       Glide.with(context).load(split[0]).into(myViewHolder.imageView);
       // Glide.with(context).load(mList.get(i).getImages());
        myViewHolder.checkBox.setChecked(mList.get(i).isCheck());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        TextView textView_title,price;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.pro_image);
            textView_title=itemView.findViewById(R.id.pro_title);
            price=itemView.findViewById(R.id.pro_price);
            checkBox=itemView.findViewById(R.id.pro_check);
        }
    }
}
