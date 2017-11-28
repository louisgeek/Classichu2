package com.classichu.classichu2.logic.patient.module.nursingevaluate;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.classichu.classichu2.R;
import com.classichu.classichu2.logic.patient.module.nursingevaluate.bean.NursingEvaluateItem;
import com.classichu.classichu2.tool.SizeTool;
import com.classichu.classichu2.widget.CheckBoxGroupLayout;
import com.classichu.classichu2.widget.ClassicFormInputLayout;
import com.classichu.classichu2.widget.ClassicInputLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class NursingEvaluateActivity extends AppCompatActivity {
    private LinearLayout idxxx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nursing_evaluate);
        idxxx = (LinearLayout) findViewById(R.id.idxxx);
        Button id_btn_load = (Button) findViewById(R.id.id_btn_load);
        id_btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              /*  getAllChildValue(idxxx);
                String json = new Gson().toJson(pairList);
                Log.i("zfq", "id_btn_load: " + json);*/
                getAllChildValue(idxxx);
                String json = new Gson().toJson(neiList);
                json = json.replace("XMQZ\":\"", "XMQZ\":\"设置值_");
                List<NursingEvaluateItem> neiListNew = new Gson().fromJson(json, new TypeToken<List<NursingEvaluateItem>>() {
                }.getType());
                setAllChildValueByTag(neiListNew);
            }
        });
        Button id_btn_save = (Button) findViewById(R.id.id_btn_save);
        id_btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getAllChildValue(idxxx);
                String json = new Gson().toJson(neiList);
                Log.i("zfq", "id_btn_save: " + json);
            }
        });
        idxxx.setFocusableInTouchMode(true);
        mContext = this;
        init();

        parseViewOut(neiList);

    }

    private Context mContext;

    private void parseViewOut(List<NursingEvaluateItem> neiList) {
        LinearLayout rootLayout = new LinearLayout(mContext);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams ll_lp_root = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int margin = SizeTool.dp2px(5);
        ll_lp_root.setMargins(margin, margin, margin, margin);
        //rootLayout.setBackgroundResource(R.drawable.shape_view_bg);
        rootLayout.setLayoutParams(ll_lp_root);
        parseView(rootLayout, neiList);
        idxxx.addView(rootLayout);
    }

    private LinearLayout nowClassicLayout;

    private void parseView(LinearLayout parentLayout, List<NursingEvaluateItem> neiList) {
        if (neiList == null || neiList.isEmpty()) {
            return;
        }
        LinearLayout organizeLayout = new LinearLayout(mContext);
        organizeLayout.setOrientation(LinearLayout.VERTICAL);
        LinearLayout.LayoutParams ll_lp_organize = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int margin = SizeTool.dp2px(5);
        ll_lp_organize.setMargins(margin, margin, margin, margin);
        organizeLayout.setLayoutParams(ll_lp_organize);
        organizeLayout.setBackgroundResource(R.drawable.shape_view_bg);
        for (int i = 0; i < neiList.size(); i++) {
            NursingEvaluateItem nei = neiList.get(i);
            if ("0".equals(nei.SJXM)) {
                //顶级分类
                LinearLayout classicLayout = new LinearLayout(mContext);
                classicLayout.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams ll_lp_classic = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                classicLayout.setGravity(Gravity.CENTER_VERTICAL);
                int padding = SizeTool.dp2px(5);
                classicLayout.setPadding(padding, padding, padding, padding);
                classicLayout.setLayoutParams(ll_lp_classic);
                classicLayout.setBackgroundResource(R.drawable.shape_expand_group_bg);
                TextView textView = new TextView(mContext);
                LinearLayout.LayoutParams ll_lp_txt = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
                ll_lp_txt.weight = 1;
                textView.setLayoutParams(ll_lp_txt);
                textView.setGravity(Gravity.CENTER_VERTICAL);
                textView.setTextColor(Color.WHITE);
                textView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_add_box_black_24dp, 0, 0, 0);
                textView.setText(nei.XMMC);
                ImageView expandImageView = new ImageView(mContext);
                expandImageView.setImageResource(R.drawable.selector_expand_group);
                classicLayout.addView(textView);
                classicLayout.addView(expandImageView);
                classicLayout.setTag(R.id.id_holder_view_classicLayout_expandImageView, expandImageView);
                organizeLayout.addView(classicLayout);
                nowClassicLayout = classicLayout;//赋值当前分类 供子项使用
            } else {
                //项目
                String XJKJLX = nei.XJKJLX;
                if (!TextUtils.isEmpty(XJKJLX)) {

                    LinearLayout layout = new LinearLayout(mContext);
                    layout.setOrientation(LinearLayout.VERTICAL);
                    LinearLayout.LayoutParams ll_lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    int mar = SizeTool.dp2px(3);
                    ll_lp.setMargins(margin * Integer.valueOf(nei.XMJB), mar, mar, mar);
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
                            classicFormInputLayout_1.addCenterEditView(nei.XMMC, "请选择");
                            classicFormInputLayout_1.setTag(R.id.id_holder_view_data_nei, nei);
                            classicFormInputLayout_1.setTag(nei.XMBH);
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
                            classicFormInputLayout_2.addCenterEditView(nei.XMMC, "请选择2");
                            classicFormInputLayout_2.setTag(R.id.id_holder_view_data_nei, nei);
                            classicFormInputLayout_2.setTag(nei.XMBH);
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
                                radioButton.setTag(R.id.id_holder_view_radioButton_key, stringStringPair.first);
                                radioButton.setText(stringStringPair.second);
                                radioLayout.addView(radioButton);
                            }
                            radioLayout.setTag(R.id.id_holder_view_data_nei, nei);
                            radioLayout.setTag(nei.XMBH);
                            layout.addView(radioLayout);
                            break;
                        case "4":
                            TextView tv = new TextView(mContext);
                            tv.setText(nei.XMMC);
                            layout.addView(tv);
                            CheckBoxGroupLayout checkBoxGroupLayout = new CheckBoxGroupLayout(mContext);
                            checkBoxGroupLayout.setOrientation(LinearLayout.VERTICAL);
                            checkBoxGroupLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                            List<Pair<String, String>> stringList3 = new ArrayList<>(nei.XMXXPairList);
                            for (Pair<String, String> stringStringPair : stringList3) {
                                CheckBox checkBox = new CheckBox(mContext);
                                checkBox.setTag(R.id.id_holder_view_checkBox_key, stringStringPair.first);
                                checkBox.setText(stringStringPair.second);
                                checkBoxGroupLayout.addView(checkBox);
                                checkBoxGroupLayout.addCheckBox(checkBox);
                            }
                            checkBoxGroupLayout.setTag(R.id.id_holder_view_data_nei, nei);
                            checkBoxGroupLayout.setTag(nei.XMBH);
                            layout.addView(checkBoxGroupLayout);
                            break;
                        case "5":
                            LinearLayout child3 = new LinearLayout(mContext);
                            LinearLayout.LayoutParams ll_lp_child3 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            child3.setLayoutParams(ll_lp_child3);
                            child3.setOrientation(LinearLayout.HORIZONTAL);
                            ClassicFormInputLayout classicFormInputLayout_5 = new ClassicFormInputLayout(mContext);
                            classicFormInputLayout_5.addStartText(nei.XMMC);
                            List<Pair<String, String>> stringList5 = new ArrayList<>(nei.XMXXPairList);
                            classicFormInputLayout_5.addCenterEditView(nei.XMMC, "请选择5", stringList5, false);
                            classicFormInputLayout_5.setTag(R.id.id_holder_view_data_nei, nei);
                            classicFormInputLayout_5.setTag(nei.XMBH);
                            child3.addView(classicFormInputLayout_5);
                            layout.addView(child3);
                            break;
                        case "6":
                            LinearLayout child4 = new LinearLayout(mContext);
                            LinearLayout.LayoutParams ll_lp_child4 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            child4.setLayoutParams(ll_lp_child4);
                            child4.setOrientation(LinearLayout.HORIZONTAL);
                            TextView tv3 = new TextView(mContext);
                            tv3.setText(nei.XMMC);
                            tv3.setTag(R.id.id_holder_view_data_nei, nei);
                            tv3.setTag(nei.XMBH);
                            child4.addView(tv3);
                            layout.addView(child4);
                            break;
                        case "7":
                            break;
                        case "9":
                            break;
                        case "10":
                            LinearLayout child5 = new LinearLayout(mContext);
                            LinearLayout.LayoutParams ll_lp_child5 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            child5.setLayoutParams(ll_lp_child5);
                            child5.setOrientation(LinearLayout.HORIZONTAL);
                            EditText et = new EditText(mContext);
                            et.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                            et.setText(nei.XMMC);
                            et.setTag(R.id.id_holder_view_data_nei, nei);
                            et.setTag(nei.XMBH);
                            child5.addView(et);
                            layout.addView(child5);
                            break;
                        default:
                            break;
                    }
                    organizeLayout.addView(layout);
                    /**
                     * 设置当前分类的监听
                     */
                    nowClassicLayout.setTag(R.id.id_holder_view_organizeLayout, organizeLayout);//当前organizeLayout子项分组layout
                    nowClassicLayout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //
                            LinearLayout classicLayout = (LinearLayout) v;
                            ImageView expandImageView = (ImageView) classicLayout.getTag(R.id.id_holder_view_classicLayout_expandImageView);
                            //
                            LinearLayout organizeLayout = (LinearLayout) v.getTag(R.id.id_holder_view_organizeLayout);
                            if (organizeLayout.getVisibility() == View.VISIBLE) {
                                organizeLayout.setVisibility(View.GONE);
                                expandImageView.setSelected(true);
                            } else {
                                organizeLayout.setVisibility(View.VISIBLE);
                                expandImageView.setSelected(false);
                            }
                        }
                    });
                }

            }

            //迭代子项
            parseView(organizeLayout, nei.items);

            try {
                //迭代会重复添加 捕获异常直接使用第一次有效的添加
                parentLayout.addView(organizeLayout);
            } catch (Exception e) {
            }
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
        nei1_1.XMBH = "111";
        NursingEvaluateItem nei1_2 = new NursingEvaluateItem();
        nei1_2.XMMC = "入院诊断";
        nei1_2.XJKJLX = "2";
        nei1_2.XMJB = "1";
        nei1_2.XMBH = "112";
        NursingEvaluateItem nei1_3 = new NursingEvaluateItem();
        nei1_3.XMMC = "怀孕";
        nei1_3.XJKJLX = "3";
        nei1_3.XMJB = "1";
        nei1_3.XMBH = "113";
        List<Pair<String, String>> pList = new ArrayList<>();
        pList.add(Pair.create("yes", "是"));
        pList.add(Pair.create("no", "否"));
        nei1_3.XMXXPairList = pList;
        NursingEvaluateItem nei1_4 = new NursingEvaluateItem();
        nei1_4.XMMC = "心理评估";
        nei1_4.XJKJLX = "4";
        nei1_4.XMJB = "1";
        nei1_4.XMBH = "114";
        List<Pair<String, String>> pList2 = new ArrayList<>();
        pList2.add(Pair.create("key1", "情绪稳定"));
        pList2.add(Pair.create("key2", "焦虑"));
        nei1_4.XMXXPairList = pList2;
        NursingEvaluateItem nei1_5 = new NursingEvaluateItem();
        nei1_5.XMMC = "沟通方式";
        nei1_5.XJKJLX = "5";
        nei1_5.XMJB = "1";
        nei1_5.XMBH = "115";
        List<Pair<String, String>> pList3 = new ArrayList<>();
        pList3.add(Pair.create("key11", "现场"));
        pList3.add(Pair.create("key11", "电话"));
        nei1_5.XMXXPairList = pList3;
        NursingEvaluateItem nei1_6 = new NursingEvaluateItem();
        nei1_6.XMMC = "标签显示";
        nei1_6.XJKJLX = "6";
        nei1_6.XMJB = "1";
        nei1_6.XMBH = "116";
        NursingEvaluateItem nei1_7 = new NursingEvaluateItem();
        nei1_7.XMMC = "表格";
        nei1_7.XJKJLX = "7";
        nei1_7.XMJB = "1";
        NursingEvaluateItem nei1_9 = new NursingEvaluateItem();
        nei1_9.XMMC = "无";
        nei1_9.XJKJLX = "9";
        nei1_9.XMJB = "1";
        NursingEvaluateItem nei1_10 = new NursingEvaluateItem();
        nei1_10.XMMC = "et";
        nei1_10.XJKJLX = "10";
        nei1_10.XMJB = "1";
        nei1_10.XMBH = "1110";
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
        nei1List.add(nei1_10);
        nei1.items = nei1List;
        nei2.items = nei1List;
        nei3.items = nei1List;
        //
        neiList.add(nei1);
        neiList.add(nei2);
        neiList.add(nei3);
    }

    private List<Pair<String, String>> pairList = new ArrayList<>();

    private void getAllChildValue(ViewGroup vp) {
        if (vp.getChildCount() <= 0) {
            return;
        }
        for (int i = 0; i < vp.getChildCount(); i++) {
            View view = vp.getChildAt(i);
            if (view instanceof ClassicFormInputLayout) {
                if (view.getTag(R.id.id_holder_view_data_nei) != null) {
                    NursingEvaluateItem nei = (NursingEvaluateItem) view.getTag(R.id.id_holder_view_data_nei);
                    String key = nei.XMBH;
                    ClassicFormInputLayout cfil = (ClassicFormInputLayout) view;
                    ClassicInputLayout cil = cfil.getInputLayoutInCenterLayout();
                    if (cil != null && cil.getInput() != null) {
                        String value = cil.getInput().getText().toString();
                        pairList.add(Pair.create(key, value + "ClassicFormInputLayout"));
                        nei.XMQZ = value;
                    }

                }
            } else if (view instanceof CheckBoxGroupLayout) {
                if (view.getTag(R.id.id_holder_view_data_nei) != null) {
                    NursingEvaluateItem nei = (NursingEvaluateItem) view.getTag(R.id.id_holder_view_data_nei);
                    String key = nei.XMBH;
                    CheckBoxGroupLayout cbgl = (CheckBoxGroupLayout) view;
                    Pair<String, String> keyValuePair = cbgl.getCheckedCheckBoxKeyValue();
                    if (keyValuePair != null) {
                        pairList.add(keyValuePair);
                        nei.XMQZ = keyValuePair.second;
                    }
                }
            } else if (view instanceof EditText) {
                if (view.getTag(R.id.id_holder_view_data_nei) != null) {
                    NursingEvaluateItem nei = (NursingEvaluateItem) view.getTag(R.id.id_holder_view_data_nei);
                    String key = nei.XMBH;
                    EditText et = (EditText) view;
                    String value = et.getText().toString();
                    pairList.add(Pair.create(key, value + "EditText"));
                    nei.XMQZ = value;
                }
            } else if (view instanceof RadioGroup) {
                if (view.getTag(R.id.id_holder_view_data_nei) != null) {
                    NursingEvaluateItem nei = (NursingEvaluateItem) view.getTag(R.id.id_holder_view_data_nei);
                    String key = nei.XMBH;
                    RadioGroup rg = (RadioGroup) view;
                    RadioButton rb = (RadioButton) idxxx.findViewById(rg.getCheckedRadioButtonId());
                    if (rb != null) {
                        String value = rb.getText().toString();
                        pairList.add(Pair.create(key, value + "RadioGroup"));
                        nei.XMQZ = value;
                    }
                }
            } else if (view instanceof CheckBox) {
                if (view.getTag(R.id.id_holder_view_data_nei) != null) {
                    NursingEvaluateItem nei = (NursingEvaluateItem) view.getTag(R.id.id_holder_view_data_nei);
                    String key = nei.XMBH;
                    CheckBox cb = (CheckBox) view;
                    if (cb.isChecked()) {
                        String value = cb.getText().toString();
                        pairList.add(Pair.create(key, value + "CheckBox"));
                        nei.XMQZ = value;
                    }
                }
            } else if (view instanceof TextView) {
                if (view.getTag(R.id.id_holder_view_data_nei) != null) {
                    NursingEvaluateItem nei = (NursingEvaluateItem) view.getTag(R.id.id_holder_view_data_nei);
                    String key = nei.XMBH;
                    TextView tv = (TextView) view;
                    String value = tv.getText().toString();
                    pairList.add(Pair.create(key, value + "TextView"));
                    nei.XMQZ = value;
                }
            } else if (view instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) view;
                getAllChildValue(viewGroup);
            }
        }

    }


    @Deprecated
    private void setAllChildValue(ViewGroup vp, List<NursingEvaluateItem> neiList_In) {
        if (vp.getChildCount() <= 0 || neiList_In == null || neiList_In.isEmpty()) {
            return;
        }
        for (NursingEvaluateItem nei_In : neiList_In) {

            //////
            String XMBH_In = nei_In.XMBH;
            String XMQZ_In = nei_In.XMQZ;
            for (int i = 0; i < vp.getChildCount(); i++) {
                View view = vp.getChildAt(i);
                if (view instanceof ClassicFormInputLayout) {
                    if (view.getTag(R.id.id_holder_view_data_nei) != null) {
                        NursingEvaluateItem nei = (NursingEvaluateItem) view.getTag(R.id.id_holder_view_data_nei);
                        if (nei.XMBH.equals(XMBH_In)) {
                            nei.XMQZ = XMQZ_In;
                            ClassicFormInputLayout cfil = (ClassicFormInputLayout) view;
                            ClassicInputLayout cil = cfil.getInputLayoutInCenterLayout();
                            if (cil != null && cil.getInput() != null) {
                                cil.getInput().setText(nei.XMQZ);
                            }
                        }
                    }
                } else if (view instanceof CheckBoxGroupLayout) {
                    if (view.getTag(R.id.id_holder_view_data_nei) != null) {
                        NursingEvaluateItem nei = (NursingEvaluateItem) view.getTag(R.id.id_holder_view_data_nei);
                        if (nei.XMBH.equals(XMBH_In)) {
                            nei.XMQZ = XMQZ_In;
                            CheckBoxGroupLayout cbgl = (CheckBoxGroupLayout) view;
                            cbgl.setCheckedCheckBoxKey(nei.XMQZ);
                        }
                    }
                } else if (view instanceof EditText) {
                    if (view.getTag(R.id.id_holder_view_data_nei) != null) {
                        NursingEvaluateItem nei = (NursingEvaluateItem) view.getTag(R.id.id_holder_view_data_nei);
                        if (nei.XMBH.equals(XMBH_In)) {
                            nei.XMQZ = XMQZ_In;
                            EditText et = (EditText) view;
                            et.setText(nei.XMQZ);
                        }
                    }
                } else if (view instanceof RadioGroup) {
                    if (view.getTag(R.id.id_holder_view_data_nei) != null) {
                        NursingEvaluateItem nei = (NursingEvaluateItem) view.getTag(R.id.id_holder_view_data_nei);
                        if (nei.XMBH.equals(XMBH_In)) {
                            nei.XMQZ = XMQZ_In;
                            RadioGroup rg = (RadioGroup) view;
                            for (int j = 0; j < rg.getChildCount(); j++) {
                                RadioButton radioButton = (RadioButton) rg.getChildAt(j);
                                String key = (String) radioButton.getTag(R.id.id_holder_view_radioButton_key);
                                if (key.equals(nei.XMQZ)) {
                                    radioButton.setChecked(true);
                                    break;
                                }
                            }
                        }
                    }
                } else if (view instanceof CheckBox) {
                    if (view.getTag(R.id.id_holder_view_data_nei) != null) {
                        NursingEvaluateItem nei = (NursingEvaluateItem) view.getTag(R.id.id_holder_view_data_nei);
                        if (nei.XMBH.equals(XMBH_In)) {
                            nei.XMQZ = XMQZ_In;
                            CheckBox cb = (CheckBox) view;
                            cb.setChecked(true);
                        }
                    }
                } else if (view instanceof TextView) {
                    if (view.getTag(R.id.id_holder_view_data_nei) != null) {
                        NursingEvaluateItem nei = (NursingEvaluateItem) view.getTag(R.id.id_holder_view_data_nei);
                        if (nei.XMBH.equals(XMBH_In)) {
                            nei.XMQZ = XMQZ_In;
                            TextView tv = (TextView) view;
                            tv.setText(nei.XMQZ);
                        }
                    }
                } else if (view instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) view;
                    setAllChildValue(viewGroup, nei_In.items);
                }
            }
            //////
        }
    }

    private void setAllChildValueByTag(List<NursingEvaluateItem> neiList_In) {
        if (neiList_In == null || neiList_In.isEmpty()) {
            return;
        }
        for (NursingEvaluateItem nei_In : neiList_In) {
            View view = idxxx.findViewWithTag(nei_In.XMBH);
            if (view != null) {
                /////
                if (view instanceof ClassicFormInputLayout) {
                    if (view.getTag(R.id.id_holder_view_data_nei) != null) {
                        NursingEvaluateItem nei = (NursingEvaluateItem) view.getTag(R.id.id_holder_view_data_nei);
                        nei.XMQZ = nei_In.XMQZ;
                        ClassicFormInputLayout cfil = (ClassicFormInputLayout) view;
                        ClassicInputLayout cil = cfil.getInputLayoutInCenterLayout();
                        if (cil != null && cil.getInput() != null) {
                            cil.getInput().setText(nei.XMQZ);
                        }
                    }
                } else if (view instanceof CheckBoxGroupLayout) {
                    if (view.getTag(R.id.id_holder_view_data_nei) != null) {
                        NursingEvaluateItem nei = (NursingEvaluateItem) view.getTag(R.id.id_holder_view_data_nei);
                        nei.XMQZ = nei_In.XMQZ;
                        CheckBoxGroupLayout cbgl = (CheckBoxGroupLayout) view;
                        cbgl.setCheckedCheckBoxKey(nei.XMQZ);
                    }
                } else if (view instanceof EditText) {
                    if (view.getTag(R.id.id_holder_view_data_nei) != null) {
                        NursingEvaluateItem nei = (NursingEvaluateItem) view.getTag(R.id.id_holder_view_data_nei);
                        nei.XMQZ = nei_In.XMQZ;
                        EditText et = (EditText) view;
                        et.setText(nei.XMQZ);
                    }
                } else if (view instanceof RadioGroup) {
                    if (view.getTag(R.id.id_holder_view_data_nei) != null) {
                        NursingEvaluateItem nei = (NursingEvaluateItem) view.getTag(R.id.id_holder_view_data_nei);
                        nei.XMQZ = nei_In.XMQZ;
                        RadioGroup rg = (RadioGroup) view;
                        for (int j = 0; j < rg.getChildCount(); j++) {
                            RadioButton radioButton = (RadioButton) rg.getChildAt(j);
                            String key = (String) radioButton.getTag(R.id.id_holder_view_radioButton_key);
                            if (key.equals(nei.XMQZ)) {
                                radioButton.setChecked(true);
                                break;
                            }
                        }
                    }
                } else if (view instanceof CheckBox) {
                    if (view.getTag(R.id.id_holder_view_data_nei) != null) {
                        NursingEvaluateItem nei = (NursingEvaluateItem) view.getTag(R.id.id_holder_view_data_nei);
                        nei.XMQZ = nei_In.XMQZ;
                        CheckBox cb = (CheckBox) view;
                        if (!TextUtils.isEmpty(nei.XMQZ)) {
                            cb.setChecked(true);
                        }
                    }
                } else if (view instanceof TextView) {
                    if (view.getTag(R.id.id_holder_view_data_nei) != null) {
                        NursingEvaluateItem nei = (NursingEvaluateItem) view.getTag(R.id.id_holder_view_data_nei);
                        nei.XMQZ = nei_In.XMQZ;
                        TextView tv = (TextView) view;
                        tv.setText(nei.XMQZ);
                    }
                }

                ////
            }
            setAllChildValueByTag(nei_In.items);
        }


    }
}
