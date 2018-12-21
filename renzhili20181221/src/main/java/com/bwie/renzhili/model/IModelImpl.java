package com.bwie.renzhili.model;

import com.bwie.renzhili.utils.ICallBack;
import com.bwie.renzhili.utils.OkHttpUtils;

import java.util.Map;

public class IModelImpl implements IModel {
    private MyCallBack myCallBack;
    @Override
    public void getRequeryData(String url, Map<String, String> params, Class clazz, final MyCallBack myCallBack) {
        this.myCallBack=myCallBack;
        OkHttpUtils.getmInsaner().postEnqueue(url, params, clazz, new ICallBack() {
            @Override
            public void success(Object obj) {
                myCallBack.setData(obj);
            }

            @Override
            public void failed(Exception e) {
                myCallBack.setData(e.getMessage());
            }
        });
    }
}
