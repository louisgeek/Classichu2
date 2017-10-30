package com.classichu.classichu2.api;


import com.classichu.classichu2.bean.BaseListBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Classichu on 2017/5/22.
 */

public interface TestApi {
    @GET("project/primary/list.json")
    Observable<BaseListBean> list();
}
