package com.classichu.classichu2.logic.patient.module.lifesign.bean;

import com.chad.library.adapter.base.entity.SectionEntity;

/**
 * Created by Classichu on 2017/11/7.
 */

public class MySectionEntity extends SectionEntity<VitalSignTypeItemBean> {


    public MySectionEntity(boolean isHeader, String header) {
        super(isHeader, header);
    }

    public MySectionEntity(VitalSignTypeItemBean vitalSignTypeItemBean) {
        super(vitalSignTypeItemBean);
    }
}
