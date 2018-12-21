package com.bwie.renzhili.presenter;

import java.util.Map;

public interface IPresenter {
    void getRequeryData(String url, Map<String,String> params, Class clazz);
}
