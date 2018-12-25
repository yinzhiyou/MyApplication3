package com.bwie.yinzhiyou.yinzhiyou20181221;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bwie.yinzhiyou.yinzhiyou20181221.adapter.ShopCarAdapter;
import com.bwie.yinzhiyou.yinzhiyou20181221.bean.AddShopBean;
import com.bwie.yinzhiyou.yinzhiyou20181221.bean.ShopCarBean;
import com.bwie.yinzhiyou.yinzhiyou20181221.presenter.IPresenterIpml;
import com.bwie.yinzhiyou.yinzhiyou20181221.view.IView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.Map;

public class ShopCarActivity extends AppCompatActivity implements IView  {

    private RelativeLayout mTopT;
    private CheckBox mShopCar;
    /**
     * 123232
     */
    private TextView mTextCar;
    private XRecyclerView mCarXrecyclerview;
    private IPresenterIpml iPresenterIpml;
    private ShopCarAdapter shopCarAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_car);
        initView();
        initData();

    }

    private void initData() {
        String path="http://www.zhaoapi.cn/product/getCarts";
        Map<String,String > map=new HashMap<>();
        map.put("uid","23013");

        iPresenterIpml.startResultData(path,map,ShopCarBean.class);
    }

    private void initView() {

        mTopT = (RelativeLayout) findViewById(R.id.top_t);
        mShopCar = (CheckBox) findViewById(R.id.shop_car);
        mTextCar = (TextView) findViewById(R.id.text_car);
        mCarXrecyclerview = (XRecyclerView) findViewById(R.id.car_xrecyclerview);
        iPresenterIpml = new IPresenterIpml(this);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        shopCarAdapter = new ShopCarAdapter(this);
        mCarXrecyclerview.setLayoutManager(layoutManager);
        mCarXrecyclerview.setAdapter(shopCarAdapter);
    }

    @Override
    public void showResultData(Object data) {
        if (data instanceof ShopCarBean){
            ShopCarBean shopCarBean= (ShopCarBean) data;
            shopCarAdapter.setmList(shopCarBean.getData());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresenterIpml.onDetach();
    }
}
