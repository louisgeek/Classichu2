package com.classichu.classichu2.logic.patient.module.lifesign.bean;

import java.io.Serializable;
import java.util.List;


public class LifeSignControlItem implements Serializable {


    //控件号
    public String KJH;

    //输入项号
    public String SRXH;

    //控件类型(1：显示控件；2：输入控件；3：活动控件；4：下拉控件 5：特殊控件)
    public String KJLX;

    //控件长度-字符长度
    public String KJCD;

    //控件内容
    public String KJNR;

    //数字输入
    public String SZSR;

    //其它输入
    public String QTSR;

    //正常下线
    public String ZCXX;

    //正常上线
    public String ZCSX;

    //监控下线
    public String JKXX;

    //监控上线
    public String JKSX;

    //非法下线
    public String FFXX;

    //非法上线
    public String FFSX;

    //顺序号
    public String SXH;

    //显示类别
    public String XSLB;

    //体征项目号
    public String TZXM;

    //控件说明
    public String KJSM;

    //特殊标识
    public String TSBZ;

    //下拉选择项目
    public List<LifeSignOptionItem> LifeSignOptionItemList;

    public boolean isMaxMinAble() {
        return ZCXX != null || ZCSX != null || FFXX != null || FFSX != null;
    }

    /**
     * 1-5
     *
     * @param input
     * @return
     */
    public int getMaxMinStatue(float input) {
        float ffxx = FFXX != null ? Float.parseFloat(FFXX) : -1;
        float ffsx = FFSX != null ? Float.parseFloat(FFSX) : -1;
        float zcxx = ZCXX != null ? Float.parseFloat(ZCXX) : -1;
        float zcsx = ZCSX != null ? Float.parseFloat(ZCSX) : -1;
        //小于最小值
        if (ffxx != -1 && input < ffxx) {
            return 1;
        }
        //大于最大值
        if (ffsx != -1 && input > ffsx) {
            return 5;
        }
        // 在 最小值 和正常小值之间
        if (ffxx != -1 && zcxx != -1 && input > ffxx && input <= zcxx) {
            return 2;
        }
        // 在 最大值 和正常大值之间
        if (ffsx != -1 && zcsx != -1 && input <= ffsx && input > zcsx) {
            return 4;
        }
        return 3;
    }
}
