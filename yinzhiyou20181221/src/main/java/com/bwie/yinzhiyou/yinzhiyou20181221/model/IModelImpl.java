package com.bwie.yinzhiyou.yinzhiyou20181221.model;

import com.bwie.yinzhiyou.yinzhiyou20181221.utils.OKHttpUtils;

import java.util.Map;

public class IModelImpl implements IModel {
    @Override
    public void getResultData(String trl, Map<String, String> params, Class clazz, final MyCallBack myCallBack) {
        OKHttpUtils.getInstance().postEnqueue(trl, params, clazz, new OKHttpUtils.ICallBack() {
            @Override
            public void onFailed(Exception e) {
                myCallBack.setData(e.getMessage());
            }

            @Override
            public void onSuccess(Object object) {
                myCallBack.setData(object);
            }
        });
    }
}
