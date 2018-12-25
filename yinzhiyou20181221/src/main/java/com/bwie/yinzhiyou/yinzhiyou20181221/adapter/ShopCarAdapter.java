package com.bwie.yinzhiyou.yinzhiyou20181221.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bwie.yinzhiyou.yinzhiyou20181221.R;
import com.bwie.yinzhiyou.yinzhiyou20181221.bean.ShopCarBean;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ShopCarAdapter extends RecyclerView.Adapter<ShopCarAdapter.MyViewHolder> {
    private Context context;
    private List<ShopCarBean.DataBean> mList;

    public ShopCarAdapter(Context context) {
        this.context = context;
        mList=new ArrayList<>();
    }

    public void setmList(List<ShopCarBean.DataBean> data) {
        if (data!=null){
            mList.addAll(data);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=View.inflate(context,R.layout.activity_shop_car,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
myViewHolder.textView.setText(mList.get(i).getSellerName());
myViewHolder.checkBox.setChecked(mList.get(i).isCheck());
ProcuAdapter procuAdapter=new ProcuAdapter(context,mList.get(i).getList());
        LinearLayoutManager layoutManager=new LinearLayoutManager(context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myViewHolder.xRecyclerView.setLayoutManager(layoutManager);
        myViewHolder.xRecyclerView.setAdapter(procuAdapter);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private CheckBox checkBox;
        private TextView textView;
        private XRecyclerView xRecyclerView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox=itemView.findViewById(R.id.shop_car);
            textView=itemView.findViewById(R.id.text_car);
            xRecyclerView=itemView.findViewById(R.id.car_xrecyclerview);
        }
    }
}
