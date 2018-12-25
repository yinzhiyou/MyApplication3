package com.bwie.yinzhiyou.yinzhiyou20181221.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.yinzhiyou.yinzhiyou20181221.R;
import com.bwie.yinzhiyou.yinzhiyou20181221.bean.ShowBean;

import java.util.ArrayList;
import java.util.List;

public class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.MyViewHolder> {
  private Context context;
  private List<ShowBean.DataBean> mList;

    public ShowAdapter(Context context) {
        this.context = context;
        mList=new ArrayList<>();
    }

    public void setmList(List<ShowBean.DataBean> data) {
        mList.clear();
        if (data!=null){
            mList.addAll(data);
        }
        notifyDataSetChanged();
    }
    public void addmList(List<ShowBean.DataBean> data) {

        if (data!=null){
            mList.addAll(data);
        }
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=View.inflate(context,R.layout.show_item,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
myViewHolder.title.setText(mList.get(i).getTitle());
myViewHolder.price.setText(mList.get(i).getPrice()+"");

        String images = mList.get(i).getImages();
        String[] split = images.split("\\|");
        Glide.with(context).load(split[0]).into(myViewHolder.imageView);
        myViewHolder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickPid!=null){
                    onClickPid.onCallBack(mList.get(i).getPid());
                }

            }
        });
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnClickListenerData!=null){
                    mOnClickListenerData.onCallBack(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView title,price;
        private Button button;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.show_image);
            title=itemView.findViewById(R.id.title1);
            price=itemView.findViewById(R.id.price);
            button=itemView.findViewById(R.id.addshop);
        }
    }
    private OnClickPid onClickPid;

    public void setOnClickPid(OnClickPid onClickPid) {
        this.onClickPid = onClickPid;
    }

    public interface OnClickPid{
        void onCallBack(int position);
    }
    private OnClickListenerData mOnClickListenerData;

    public void setmOnClickListenerData(OnClickListenerData mOnClickListenerData) {
        this.mOnClickListenerData = mOnClickListenerData;
    }

    public interface OnClickListenerData{
        void onCallBack(int position);
    }
}
