package com.classichu.classichu2.logic.patient.module.nursingevaluate.bean;

import android.util.Pair;

import java.util.List;

/**
 * Created by Classichu on 2017/11/27.
 */

public class NursingEvaluateItem {
    public int id;
    public String XMMC;
    public String QZWB;
    public String HZWB;
    /**
     * MOB_XTPZ.DMLB=467
     1.分类标签
     2.项目标签
     3.基本信息项目
     4.签名项目
     5.组合项目
     6.数据关联项目
     7.常规项目
     */
    public String XMLB;
    /**
     * MOB_XTPZ.DMLB=468
     1,单行输入
     2.多行输入
     3,单项选择
     4,多项选择
     5.下拉列表
     6.标签显示
     7.表格
     9.无
     */
    public String XJKJLX;
    /**
     * MOB_XTPZ.DMLB=421
     1.数字
     2.字符
     3.日期
     */
    public String SJLX;
    public String SJGS;
    public String SJXM;
    /**
     * 0 分类标签
     1 分类标签下的第一级项目
     2 第一级项目下的下级项目
     3,4,5...以此类推
     */
    public String XMJB;
    public String XGBZ;
    public List<Pair<String,String>> XMXXPairList;
    public List<NursingEvaluateItem> items;
}
