package com.classichu.classichu2.api;


import com.classichu.classichu2.bean.BS_BaseBean;
import com.classichu.classichu2.bean.BS_BaseListBean;
import com.classichu.classichu2.logic.login.bean.AgencyBean;
import com.classichu.classichu2.logic.login.bean.UserLoginBean;
import com.classichu.classichu2.logic.patient.bean.PatientInfoBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Classichu on 2017-7-17.
 */

public interface UserApi {
    @POST("mobile/user/login")
    Observable<BS_BaseBean<UserLoginBean>> userLogin(@Query("urid") String urid, @Query("pwd") String pwd, @Query("jgid") String jgid);
    @GET("mobile/user/get/agencys")
    Observable<BS_BaseListBean<AgencyBean>> getAgency(@Query("urid") String urid);
    @GET("auth/mobile/patient/get/detail")
    Observable<BS_BaseBean<PatientInfoBean>> getPatientInfoDetail(@Query("zyh") String zyh, @Query("jgid") String jgid);

}
