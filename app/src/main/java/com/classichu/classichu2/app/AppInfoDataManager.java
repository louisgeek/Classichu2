package com.classichu.classichu2.app;


import com.classichu.classichu2.logic.login.bean.AuthBean;
import com.classichu.classichu2.logic.login.bean.UserLoginBean;
import com.classichu.classichu2.logic.main.bean.PatientListBean;
import com.classichu.classichu2.tool.EmptyTool;

import java.util.Vector;

/**
 * Created by Classichu on 2017-6-5.
 */

public class AppInfoDataManager {

    /**
     * 双重校验锁 单例
     */
    private volatile static AppInfoDataManager instance;

    private AppInfoDataManager() {
    }

    public static AppInfoDataManager getInstance() {
        if (instance == null) {
            synchronized (AppInfoDataManager.class) {
                if (instance == null) {
                    instance = new AppInfoDataManager();
                }
            }
        }
        return instance;
    }

    ///////////////////////////////////////////////////////////////////////////
    // UserLoginBean相关 Start 2017/8/11
    private UserLoginBean userLoginBean;

    public void setUserLoginBean(UserLoginBean userLoginBean) {
        this.userLoginBean = userLoginBean;
    }

    public UserLoginBean getUserLoginBean() {
        return userLoginBean;
    }

    public String getJGID() {
        return getUserLoginBean().getLonginUser().getJGID();
    }

    // UserLoginBean相关 Start 2017/8/11
    ///////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////
    // AuthBean相关 Start 2017/8/11
    private AuthBean authBean = new AuthBean();

    public AuthBean getAuthBean() {
        return authBean;
    }
    // AuthBean相关 Start 2017/8/11
    ///////////////////////////////////////////////////////////////////////////

    ///////////////////////////////////////////////////////////////////////////
    // AreaBean相关 Start 2017/8/11
    private UserLoginBean.AreasBean areasBean;

    public void setAreasBean(UserLoginBean.AreasBean areasBean) {
        this.areasBean = areasBean;
    }

    public UserLoginBean.AreasBean getAreaBean() {
        if (areasBean != null) {
            return areasBean;
        }
        if (EmptyTool.isNotEmpty(areasBeanVector)) {
            areasBean = areasBeanVector.get(0);//先给个默认
            for (UserLoginBean.AreasBean ab :
                    areasBeanVector) {
                if (ab.getMRBZ() == 1) {
                    areasBean = ab;
                    break;
                }
            }
        }
        return areasBean;
    }

    public String getAreaBeanId() {
        return getAreaBean().getKSDM();
    }
    // AreaBean相关 End 2017/8/11
    ///////////////////////////////////////////////////////////////////////////


    private PatientListBean patientListBean;

    public void setPatientListBean(PatientListBean patientListBean) {
        this.patientListBean = patientListBean;
    }

    public PatientListBean getPatientListBean() {
        return patientListBean;
    }

    private Vector<UserLoginBean.AreasBean> areasBeanVector = new Vector<>();

    public Vector<UserLoginBean.AreasBean> getAreasBeanVector() {
        return areasBeanVector;
    }

    public void setAreasBeanVector(Vector<UserLoginBean.AreasBean> areasBeanVector) {
        this.areasBeanVector = areasBeanVector;
    }

    private long betweenTime;

    public void setBetweenTime(long betweenTime) {
        this.betweenTime = betweenTime;
    }

    public long getBetweenTime() {
        return betweenTime;
    }


    public String getZyh() {
        return getPatientListBean().getZYH();
    }

}
