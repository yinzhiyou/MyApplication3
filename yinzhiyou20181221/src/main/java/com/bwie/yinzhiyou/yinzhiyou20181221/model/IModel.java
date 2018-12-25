package com.bwie.yinzhiyou.yinzhiyou20181221.model;

import java.util.Map;

public interface IModel {
    void getResultData(String trl, Map<String,String>params,Class clazz,MyCallBack myCallBack);
}
