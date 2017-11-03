package com.classichu.classichu2.logic.patient.module.lifesign.bean;

/**
 * Created by Classichu on 2017/11/2.
 */

public class VitalSignTimePointBean {
    /**
     * 时刻，例如：00:00，08:00
     */
    public String NAME;

    /**
     * 时刻所对应的值
     */
    public int VALUE;

    @Override
    public String toString() {
        return NAME;
    }
}
