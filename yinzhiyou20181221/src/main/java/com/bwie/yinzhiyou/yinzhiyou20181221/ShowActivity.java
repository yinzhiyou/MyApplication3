package com.bwie.yinzhiyou.yinzhiyou20181221;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.yinzhiyou.yinzhiyou20181221.adapter.ShowAdapter;
import com.bwie.yinzhiyou.yinzhiyou20181221.bean.AddShopBean;
import com.bwie.yinzhiyou.yinzhiyou20181221.bean.ShowBean;
import com.bwie.yinzhiyou.yinzhiyou20181221.presenter.IPresenterIpml;
import com.bwie.yinzhiyou.yinzhiyou20181221.view.IView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.HashMap;
import java.util.Map;

public class ShowActivity extends AppCompatActivity implements IView,View.OnClickListener {

    private EditText mTopSearch;
    private ImageView mImageCut;
    private LinearLayout mLay;
    /**
     * 综合
     */
    private TextView mZonghe;
    /**
     * 销量
     */
    private TextView mXiaoliang;
    /**
     * 价格
     */
    private TextView mPrice;
    /**
     * 筛选
     */
    private TextView mSaixuan;
    private LinearLayout mTab;
    private XRecyclerView mXrecyclerview;
    private int page;
    private int pid;
    private IPresenterIpml iPresenterIpml;
    private ShowAdapter showAdapter;
    private LinearLayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        initView();
        initResult();

    }

    private void initResult() {
    mXrecyclerview.setLoadingMoreEnabled(true);
    mXrecyclerview.setPullRefreshEnabled(true);
    mXrecyclerview.setLoadingListener(new XRecyclerView.LoadingListener() {
        @Override
        public void onRefresh() {
            page=1;
            initData();
        }

        @Override
        public void onLoadMore() {
            initData();
        }
    });
        initData();
        showAdapter.setmOnClickListenerData(new ShowAdapter.OnClickListenerData() {
            @Override
            public void onCallBack(int position) {
                Intent intent=new Intent(ShowActivity.this,ShopCarActivity.class);
                startActivity(intent);
            }
        });
        showAdapter.setOnClickPid(new ShowAdapter.OnClickPid() {
            @Override
            public void onCallBack(int position) {
                String path="http://www.zhaoapi.cn/product/addCart";
                Map<String,String > map=new HashMap<>();
                map.put("uid","23013");
                map.put("pid",String.valueOf(position));
                iPresenterIpml.startResultData(path,map,AddShopBean.class);
                Toast.makeText(ShowActivity.this,"加入成功",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPresenterIpml.onDetach();
    }

    private void initData() {
        String path="http://www.zhaoapi.cn/product/searchProducts";
        Map<String,String> map=new HashMap<>();
        map.put("keywords","手机");
        map.put("page",page+"");
        iPresenterIpml.startResultData(path,map,ShowBean.class);
    }
    private void initPid(){

    }

    private void initView() {

        mTopSearch = (EditText) findViewById(R.id.top_search);
        mImageCut = (ImageView) findViewById(R.id.image_cut);
        mImageCut.setOnClickListener(this);
        mLay = (LinearLayout) findViewById(R.id.lay);
        mZonghe = (TextView) findViewById(R.id.zonghe);
        mZonghe.setOnClickListener(this);
        mXiaoliang = (TextView) findViewById(R.id.xiaoliang);
        mXiaoliang.setOnClickListener(this);
        mPrice = (TextView) findViewById(R.id.price);
        mPrice.setOnClickListener(this);
        mSaixuan = (TextView) findViewById(R.id.saixuan);
        mSaixuan.setOnClickListener(this);
        mTab = (LinearLayout) findViewById(R.id.tab);
        mXrecyclerview = (XRecyclerView) findViewById(R.id.xrecyclerview);
        iPresenterIpml = new IPresenterIpml(this);
        showAdapter = new ShowAdapter(this);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mXrecyclerview.setLayoutManager(layoutManager);
        mXrecyclerview.setAdapter(showAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.image_cut:
                break;
            case R.id.zonghe:
                break;
            case R.id.xiaoliang:
                break;
            case R.id.price:
                break;
            case R.id.saixuan:
                break;
        }
    }

    @Override
    public void showResultData(Object data) {
        if (data instanceof ShowBean){
            ShowBean showBean= (ShowBean) data;
            if (page==1){
                showAdapter.setmList(showBean.getData());
            }else {
                showAdapter.addmList(showBean.getData());
            }
            page++;
            mXrecyclerview.refreshComplete();
            mXrecyclerview.loadMoreComplete();
        }
    }
}
