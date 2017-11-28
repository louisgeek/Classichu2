package com.classichu.classichu2.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Pair;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import com.classichu.classichu2.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Classichu on 2017/11/28.
 * 单纯的为了取值方便
 */

public class CheckBoxGroupLayout extends LinearLayout {


    private List<CheckBox> checkBoxList = new ArrayList<>();

    public void addCheckBox(CheckBox checkBox) {
        checkBoxList.add(checkBox);
    }

    public void setCheckedCheckBoxKey(String checkedKey) {
        if (checkBoxList == null || checkBoxList.isEmpty()) {
            return;
        }
        String[] key_in_list = null;
        if (!TextUtils.isEmpty(checkedKey)) {
            key_in_list = checkedKey.split(",");
        }
        if (key_in_list != null) {
            for (CheckBox checkBox : checkBoxList) {
                String key = (String) checkBox.getTag(R.id.id_holder_view_checkBox_key);
                for (String key_in : key_in_list) {
                    if (key.equals(key_in)) {
                        checkBox.setChecked(true);
                        break;
                    }
                }
            }
        }
    }

    public Pair<String, String> getCheckedCheckBoxKeyValue() {
        if (checkBoxList == null || checkBoxList.isEmpty()) {
            return null;
        }
        StringBuilder sb_key = new StringBuilder();
        StringBuilder sb_value = new StringBuilder();
        for (CheckBox checkBox : checkBoxList) {
            if (checkBox.isChecked()) {
                String key = (String) checkBox.getTag(R.id.id_holder_view_checkBox_key);
                String value = checkBox.getText().toString();
                sb_key.append(key);
                sb_key.append(",");
                sb_value.append(value);
                sb_value.append(",");
            }
        }
        String key = sb_key.toString();
        String value = sb_value.toString();

        if (!TextUtils.isEmpty(key) && key.endsWith(",")) {
            key = key.substring(0, key.length() - 1);
        }
        if (!TextUtils.isEmpty(value) && value.endsWith(",")) {
            value = value.substring(0, value.length() - 1);
        }
        if (TextUtils.isEmpty(key)) {
            key = null;
        }
        if (TextUtils.isEmpty(value)) {
            value = null;
        }
        return Pair.create(key, value);
    }

    public void setCheckedCheckBoxList(List<Pair<String, String>> checkedPairList) {
        if (checkBoxList == null || checkBoxList.isEmpty()) {
            return;
        }
        if (checkedPairList != null) {
            for (CheckBox checkBox : checkBoxList) {
                String key = (String) checkBox.getTag(R.id.id_holder_view_checkBox_key);
                for (Pair<String, String> checkedPair : checkedPairList) {
                    if (key.equals(checkedPair.first)) {
                        checkBox.setChecked(true);
                        break;
                    }
                }
            }
        }
    }

    public List<Pair<String, String>> getCheckedCheckBoxList() {
        if (checkBoxList == null || checkBoxList.isEmpty()) {
            return null;
        }
        List<Pair<String, String>> pairList = new ArrayList<>();
        for (CheckBox checkBox : checkBoxList) {
            if (checkBox.isChecked()) {
                String key = (String) checkBox.getTag(R.id.id_holder_view_checkBox_key);
                String value = checkBox.getText().toString();
                pairList.add(Pair.create(key, value));
            }
        }
        return pairList;
    }

    public CheckBoxGroupLayout(Context context) {
        this(context, null);
    }

    public CheckBoxGroupLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CheckBoxGroupLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
