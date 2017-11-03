package com.classichu.classichu2.logic.patient.module.lifesign.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Classichu on 2017/11/2.
 */

public class VitalSignTypeItemBean  implements Serializable {

    //类别号
    public String LBH;

    //类别名称
    public String LBMC;

    public List<LifeSignInputItem> LifeSignInputItemList;
}
