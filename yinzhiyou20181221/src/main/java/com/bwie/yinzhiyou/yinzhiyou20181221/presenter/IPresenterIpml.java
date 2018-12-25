package com.bwie.yinzhiyou.yinzhiyou20181221.presenter;

import com.bwie.yinzhiyou.yinzhiyou20181221.model.IModelImpl;
import com.bwie.yinzhiyou.yinzhiyou20181221.model.MyCallBack;
import com.bwie.yinzhiyou.yinzhiyou20181221.view.IView;

import java.util.Map;

public class IPresenterIpml implements IPresenter {
    private IView mIView;
    private IModelImpl iModel;

    public IPresenterIpml(IView mIView) {
        this.mIView = mIView;
        iModel=new IModelImpl();
    }

    @Override
    public void startResultData(String trl, Map<String, String> params, Class clazz) {
        iModel.getResultData(trl, params, clazz, new MyCallBack() {
            @Override
            public void setData(Object data) {
                mIView.showResultData(data);
            }
        });
    }
    public void onDetach(){
        if (mIView!=null){
            mIView=null;
        }
        if (iModel!=null){
            iModel=null;
        }
    }
}
