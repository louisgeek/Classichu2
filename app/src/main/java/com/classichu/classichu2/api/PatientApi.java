package com.classichu.classichu2.api;


import com.classichu.classichu2.bean.BS_BaseBean;
import com.classichu.classichu2.bean.BS_BaseListBean;
import com.classichu.classichu2.logic.main.bean.PatientListBean;
import com.classichu.classichu2.logic.patient.bean.PatientInfoBean;
import com.classichu.classichu2.logic.patient.module.lifesign.bean.VitalSignTimePointBean;
import com.classichu.classichu2.logic.patient.module.lifesign.bean.VitalSignTypeItemBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Classichu on 2017-7-21.
 */

public interface PatientApi {

    @GET("auth/mobile/patient/get/list")
    Observable<BS_BaseListBean<PatientListBean>> getPatientList(@Query("bqid") String bqid, @Query("type") int type, @Query("starttime") int starttime,
                                                                @Query("endtime") int endtime, @Query("hsgh") String hsgh, @Query("jgid") String jgid);
    @GET("auth/mobile/patient/get/detail")
    Observable<BS_BaseBean<PatientInfoBean>> getPatientInfoDetail(@Query("zyh") String zyh, @Query("jgid") String jgid);


    @GET("auth/mobile/lifesign/get/getTimePointList")
    Observable<BS_BaseListBean<VitalSignTimePointBean>> getTimePointList(@Query("bqid") String bqid, @Query("jgid") String jgid);

    @GET("auth/mobile/lifesign/get/getLifeSignTypeItemList")
    Observable<BS_BaseListBean<VitalSignTypeItemBean>> getLifeSignTypeItemList(@Query("zyh") String zyh, @Query("bqid") String bqid, @Query("jgid") String jgid);
}
