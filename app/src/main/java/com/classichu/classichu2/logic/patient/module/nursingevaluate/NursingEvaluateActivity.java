package com.classichu.classichu2.logic.patient.module.nursingevaluate;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Pair;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.classichu.classichu2.R;
import com.classichu.classichu2.logic.patient.module.nursingevaluate.bean.NursingEvaluateItem;
import com.classichu.classichu2.widget.ClassicFormInputLayout;

import java.util.ArrayList;
import java.util.List;

public class NursingEvaluateActivity extends AppCompatActivity {
    private LinearLayout idxxx;
    // private List<View> viewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nursing_evaluate);
        idxxx = (LinearLayout) findViewById(R.id.idxxx);
        idxxx.setFocusableInTouchMode(true);
        mContext = this;
        init();

        parseViewOut(neiList);

    }

    private Context mContext;
    private int margin = 40;

    private void parseViewOut(List<NursingEvaluateItem> neiList) {
        LinearLayout rootLayout = new LinearLayout(mContext);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams ll_lp_root = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ll_lp_root.setMargins(10, 10, 10, 10);
        rootLayout.setLayoutParams(ll_lp_root);
        parseView(rootLayout, neiList);
        idxxx.addView(rootLayout);
    }

    private void parseView(LinearLayout parentLayout, List<NursingEvaluateItem> neiList) {
        if (neiList == null || neiList.isEmpty()) {
            return;
        }

        LinearLayout organizeLayout = new LinearLayout(mContext);
        organizeLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams ll_lp_expand = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ll_lp_expand.setMargins(15, 15, 15, 15);
        organizeLayout.setLayoutParams(ll_lp_expand);
        organizeLayout.setBackgroundResource(R.drawable.shape_view_bg);
        for (NursingEvaluateItem nei : neiList) {

            if ("0".equals(nei.SJXM)) {
                //顶级分类
                LinearLayout classicLayout = new LinearLayout(mContext);
                classicLayout.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams ll_lp_classic = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                classicLayout.setGravity(Gravity.CENTER_VERTICAL);
                classicLayout.setLayoutParams(ll_lp_classic);
                classicLayout.setBackgroundResource(R.color.toastOkColor);
                ImageView imageView = new ImageView(mContext);
                imageView.setImageResource(R.mipmap.ic_launcher);
                TextView textView = new TextView(mContext);
                textView.setGravity(Gravity.CENTER_VERTICAL);
                textView.setTextColor(Color.WHITE);
                textView.setText(nei.XMMC);
                classicLayout.addView(imageView);
                classicLayout.addView(textView);
                organizeLayout.addView(classicLayout);
            } else {
                //项目
                String XJKJLX = nei.XJKJLX;
                if (!TextUtils.isEmpty(XJKJLX)) {

                    LinearLayout layout = new LinearLayout(mContext);
                    layout.setOrientation(LinearLayout.VERTICAL);
                    LinearLayout.LayoutParams ll_lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    ll_lp.leftMargin = margin * Integer.valueOf(nei.XMJB);
                    ll_lp.topMargin = 10;
                    layout.setLayoutParams(ll_lp);
                    layout.setGravity(Gravity.CENTER_VERTICAL);
                    switch (XJKJLX) {
                        case "1":
                            LinearLayout child = new LinearLayout(mContext);
                            LinearLayout.LayoutParams ll_lp_child = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            child.setLayoutParams(ll_lp_child);
                            child.setOrientation(LinearLayout.HORIZONTAL);
                            ClassicFormInputLayout classicFormInputLayout_1 = new ClassicFormInputLayout(mContext);
                            classicFormInputLayout_1.addStartText(nei.XMMC);
                            classicFormInputLayout_1.addCenterEditView(null, "请选择");
                            child.addView(classicFormInputLayout_1);
                            layout.addView(child);
                            break;
                        case "2":
                            LinearLayout child2 = new LinearLayout(mContext);
                            LinearLayout.LayoutParams ll_lp_child2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            child2.setLayoutParams(ll_lp_child2);
                            child2.setOrientation(LinearLayout.HORIZONTAL);
                            ClassicFormInputLayout classicFormInputLayout_2 = new ClassicFormInputLayout(mContext);
                            classicFormInputLayout_2.addStartText(nei.XMMC);
                            classicFormInputLayout_2.addCenterEditView(null, "请选择2");
                            child2.addView(classicFormInputLayout_2);
                            layout.addView(child2);
                            break;
                        case "3":

                            TextView tv2 = new TextView(mContext);
                            tv2.setText(nei.XMMC);
                            layout.addView(tv2);
                            RadioGroup radioLayout = new RadioGroup(mContext);
                            radioLayout.setOrientation(LinearLayout.VERTICAL);
                            radioLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                            List<Pair<String, String>> stringList4 = new ArrayList<>(nei.XMXXPairList);
                            for (Pair<String, String> stringStringPair : stringList4) {
                                RadioButton radioButton = new RadioButton(mContext);
                                radioButton.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                                radioButton.setText(stringStringPair.first);
                                radioLayout.addView(radioButton);
                            }
                            layout.addView(radioLayout);
                            break;
                        case "4":
                            TextView tv = new TextView(mContext);
                            tv.setText(nei.XMMC);
                            layout.addView(tv);
                            LinearLayout checkLayout = new LinearLayout(mContext);
                            checkLayout.setOrientation(LinearLayout.VERTICAL);
                            checkLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                            List<Pair<String, String>> stringList3 = new ArrayList<>(nei.XMXXPairList);
                            for (Pair<String, String> stringStringPair : stringList3) {
                                CheckBox checkBox = new CheckBox(mContext);
                                checkBox.setText(stringStringPair.first);
                                checkLayout.addView(checkBox);
                            }
                            layout.addView(checkLayout);

                            break;
                        case "5":
                            LinearLayout child3 = new LinearLayout(mContext);
                            LinearLayout.LayoutParams ll_lp_child3 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            child3.setLayoutParams(ll_lp_child3);
                            child3.setOrientation(LinearLayout.HORIZONTAL);
                            ClassicFormInputLayout classicFormInputLayout_5 = new ClassicFormInputLayout(mContext);
                            classicFormInputLayout_5.addStartText(nei.XMMC);
                            List<Pair<String, String>> stringList5 = new ArrayList<>(nei.XMXXPairList);
                            classicFormInputLayout_5.addCenterEditView(null, "请选择5", stringList5, false);
                            child3.addView(classicFormInputLayout_5);
                            layout.addView(child3);
                            break;
                        case "6":
                            LinearLayout child4 = new LinearLayout(mContext);
                            LinearLayout.LayoutParams ll_lp_child4 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            child4.setLayoutParams(ll_lp_child4);
                            child4.setOrientation(LinearLayout.HORIZONTAL);
                            ClassicFormInputLayout classicFormInputLayout_6 = new ClassicFormInputLayout(mContext);
                            TextView tv3 = new TextView(mContext);
                            tv3.setText(nei.XMMC);
                            classicFormInputLayout_6.addCenterView(tv3);
                            child4.addView(classicFormInputLayout_6);
                            layout.addView(child4);
                            break;
                        case "7":
                            break;
                        case "9":
                            break;
                        default:
                            break;
                    }
                    organizeLayout.addView(layout);
                    //}
                    // viewList.add(rootLayout);

                }

            }
            try {
                //迭代会重复添加 捕获异常直接使用第一次有效的添加
                parentLayout.addView(organizeLayout);
            } catch (Exception e) {
            }
            //迭代子项
            parseView(organizeLayout, nei.items);
        }

    }

    private List<NursingEvaluateItem> neiList = new ArrayList<>();

    private void init() {
        NursingEvaluateItem nei1 = new NursingEvaluateItem();
        nei1.XMMC = "基本资料";
        nei1.XJKJLX = "9";
        nei1.SJXM = "0";
        nei1.XMJB = "0";
        NursingEvaluateItem nei2 = new NursingEvaluateItem();
        nei2.XMMC = "既往病史及家族史";
        nei2.XJKJLX = "9";
        nei2.SJXM = "0";
        nei2.XMJB = "0";
        NursingEvaluateItem nei3 = new NursingEvaluateItem();
        nei3.XMMC = "既往病史及家族史2";
        nei3.XJKJLX = "9";
        nei3.SJXM = "0";
        nei3.XMJB = "0";

        List<NursingEvaluateItem> nei1List = new ArrayList<>();
        NursingEvaluateItem nei1_1 = new NursingEvaluateItem();
        nei1_1.XMMC = "床号";
        nei1_1.XJKJLX = "1";
        nei1_1.XMJB = "1";
        NursingEvaluateItem nei1_2 = new NursingEvaluateItem();
        nei1_2.XMMC = "入院诊断";
        nei1_2.XJKJLX = "2";
        nei1_2.XMJB = "1";
        NursingEvaluateItem nei1_3 = new NursingEvaluateItem();
        nei1_3.XMMC = "怀孕";
        nei1_3.XJKJLX = "3";
        nei1_3.XMJB = "1";
        List<Pair<String, String>> pList = new ArrayList<>();
        pList.add(Pair.create("是", "是"));
        pList.add(Pair.create("否", "否"));
        nei1_3.XMXXPairList = pList;
        NursingEvaluateItem nei1_4 = new NursingEvaluateItem();
        nei1_4.XMMC = "心理评估";
        nei1_4.XJKJLX = "4";
        nei1_4.XMJB = "1";
        List<Pair<String, String>> pList2 = new ArrayList<>();
        pList2.add(Pair.create("情绪稳定", "情绪稳定"));
        pList2.add(Pair.create("焦虑", "焦虑"));
        nei1_4.XMXXPairList = pList2;
        NursingEvaluateItem nei1_5 = new NursingEvaluateItem();
        nei1_5.XMMC = "沟通方式";
        nei1_5.XJKJLX = "5";
        nei1_5.XMJB = "1";
        List<Pair<String, String>> pList3 = new ArrayList<>();
        pList3.add(Pair.create("现场", "现场"));
        pList3.add(Pair.create("电话", "电话"));
        nei1_5.XMXXPairList = pList3;
        NursingEvaluateItem nei1_6 = new NursingEvaluateItem();
        nei1_6.XMMC = "标签显示";
        nei1_6.XJKJLX = "6";
        nei1_6.XMJB = "1";
        NursingEvaluateItem nei1_7 = new NursingEvaluateItem();
        nei1_7.XMMC = "表格";
        nei1_7.XJKJLX = "7";
        nei1_7.XMJB = "1";
        NursingEvaluateItem nei1_9 = new NursingEvaluateItem();
        nei1_9.XMMC = "无";
        nei1_9.XJKJLX = "9";
        nei1_9.XMJB = "1";
        //////
        List<NursingEvaluateItem> nei1_1List = new ArrayList<>();
        NursingEvaluateItem nei1_1_1 = new NursingEvaluateItem();
        nei1_1_1.XMMC = "床号_1";
        nei1_1_1.XJKJLX = "1";
        nei1_1_1.XMJB = "2";
        NursingEvaluateItem nei1_1_2 = new NursingEvaluateItem();
        nei1_1_2.XMMC = "入院诊断_1";
        nei1_1_2.XJKJLX = "2";
        nei1_1_2.XMJB = "2";
        NursingEvaluateItem nei1_1_3 = new NursingEvaluateItem();
        nei1_1_3.XMMC = "怀孕_1";
        nei1_1_3.XJKJLX = "3";
        nei1_1_3.XMJB = "2";
        /////
        List<NursingEvaluateItem> nei1_1_1List = new ArrayList<>();
        NursingEvaluateItem nei1_1_1_1 = new NursingEvaluateItem();
        nei1_1_1_1.XMMC = "床号_1_1";
        nei1_1_1_1.XJKJLX = "1";
        nei1_1_1_1.XMJB = "2";
        NursingEvaluateItem nei1_1_1_2 = new NursingEvaluateItem();
        nei1_1_1_2.XMMC = "入院诊断_1_1";
        nei1_1_1_2.XJKJLX = "2";
        nei1_1_1_2.XMJB = "2";
        NursingEvaluateItem nei1_1_1_3 = new NursingEvaluateItem();
        nei1_1_1_3.XMMC = "怀孕_1_1";
        nei1_1_1_3.XJKJLX = "3";
        nei1_1_1_3.XMJB = "2";
        List<Pair<String, String>> pList_1_1 = new ArrayList<>();
        pList_1_1.add(Pair.create("是_1_1", "是_1_1"));
        pList_1_1.add(Pair.create("否_1_1", "否_1_1"));
        nei1_1_1_3.XMXXPairList = pList_1_1;
        nei1_1_1List.add(nei1_1_1_1);
        nei1_1_1List.add(nei1_1_1_2);
        nei1_1_1List.add(nei1_1_1_3);
        ////
        nei1_1_3.items = nei1_1_1List;
        List<Pair<String, String>> pList_1 = new ArrayList<>();
        pList_1.add(Pair.create("是_1", "是_1"));
        pList_1.add(Pair.create("否_1", "否_1"));
        nei1_1_3.XMXXPairList = pList_1;
        NursingEvaluateItem nei1_1_4 = new NursingEvaluateItem();
        nei1_1_4.XMMC = "心理评估_1";
        nei1_1_4.XJKJLX = "4";
        nei1_1_4.XMJB = "2";
        List<Pair<String, String>> pList_12 = new ArrayList<>();
        pList_12.add(Pair.create("情绪稳定_1", "情绪稳定_1"));
        pList_12.add(Pair.create("焦虑_1", "焦虑_1"));
        nei1_1_4.XMXXPairList = pList_12;
        NursingEvaluateItem nei1_1_5 = new NursingEvaluateItem();
        nei1_1_5.XMMC = "沟通方式_1";
        nei1_1_5.XJKJLX = "5";
        nei1_1_5.XMJB = "2";
        List<Pair<String, String>> pList_13 = new ArrayList<>();
        pList_13.add(Pair.create("现场_1", "现场_1"));
        pList_13.add(Pair.create("电话_1", "电话_1"));
        nei1_1_5.XMXXPairList = pList_13;
        NursingEvaluateItem nei1_1_6 = new NursingEvaluateItem();
        nei1_1_6.XMMC = "标签显示_1";
        nei1_1_6.XJKJLX = "6";
        nei1_1_6.XMJB = "2";
        NursingEvaluateItem nei1_1_7 = new NursingEvaluateItem();
        nei1_1_7.XMMC = "表格_1";
        nei1_1_7.XJKJLX = "7";
        nei1_1_7.XMJB = "2";
        NursingEvaluateItem nei1_1_9 = new NursingEvaluateItem();
        nei1_1_9.XMMC = "无_1";
        nei1_1_9.XJKJLX = "9";
        nei1_1_9.XMJB = "2";
        nei1_1List.add(nei1_1_1);
        nei1_1List.add(nei1_1_2);
        nei1_1List.add(nei1_1_3);
        nei1_1List.add(nei1_1_4);
        nei1_1List.add(nei1_1_5);
        nei1_1List.add(nei1_1_6);
        nei1_1List.add(nei1_1_7);
        nei1_1List.add(nei1_1_9);

        nei1_4.items = nei1_1List;
        ////
        nei1List.add(nei1_1);
        nei1List.add(nei1_2);
        nei1List.add(nei1_3);
        nei1List.add(nei1_4);
        nei1List.add(nei1_5);
        nei1List.add(nei1_6);
        nei1List.add(nei1_7);
        nei1List.add(nei1_9);
        nei1.items = nei1List;
        nei2.items = nei1List;
        nei3.items = nei1List;
        //
        neiList.add(nei1);
        neiList.add(nei2);
        neiList.add(nei3);
    }
}
