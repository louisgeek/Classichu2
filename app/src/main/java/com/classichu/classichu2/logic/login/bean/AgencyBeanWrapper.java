package com.classichu.classichu2.logic.login.bean;

import java.util.List;

/**
 * Created by Classichu on 2017-7-17.
 */

public class AgencyBeanWrapper {
    private List<AgencyBean> agencyBeanList;

    public AgencyBeanWrapper(List<AgencyBean> agencyBeanList) {
        this.agencyBeanList = agencyBeanList;
    }

    public List<AgencyBean> getAgencyBeanList() {
        return agencyBeanList;
    }
}
