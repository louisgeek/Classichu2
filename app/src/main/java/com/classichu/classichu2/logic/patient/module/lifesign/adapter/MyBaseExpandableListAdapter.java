package com.classichu.classichu2.logic.patient.module.lifesign.adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.classichu.classichu2.R;
import com.classichu.classichu2.helper.VectorOrImageResHelper;
import com.classichu.classichu2.logic.patient.module.lifesign.bean.VitalSignTypeItemBean;
import com.classichu.classichu2.widget.ClassicFormInputLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Classichu on 2017/11/2.
 */

public class MyBaseExpandableListAdapter extends BaseExpandableListAdapter {


    public MyBaseExpandableListAdapter(List<VitalSignTypeItemBean> vitalSignTypeItemBeanList) {
        this.vitalSignTypeItemBeanList = vitalSignTypeItemBeanList;
    }

    private List<VitalSignTypeItemBean> vitalSignTypeItemBeanList;

    //获取当前父item的数据数量
    @Override
    public int getGroupCount() {
        return vitalSignTypeItemBeanList.size();
    }

    //获取当前父item下的子item的个数
    @Override
    public int getChildrenCount(int groupPosition) {
        return vitalSignTypeItemBeanList.get(groupPosition).LifeSignInputItemList.get(groupPosition).LifeSignControlItemList.size();
    }

    //获取当前父item的数据
    @Override
    public Object getGroup(int groupPosition) {
        return vitalSignTypeItemBeanList.get(groupPosition);
    }

    //得到子item需要关联的数据
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return vitalSignTypeItemBeanList.get(groupPosition).LifeSignInputItemList.get(groupPosition).LifeSignControlItemList.get(childPosition);
    }

    //得到父item的ID
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    //得到子item的ID
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        //return false;
        return true;
    }

    //设置父item组件
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_card_expandable, null);
        }
        TextView id_tv_item_title = (TextView) convertView
                .findViewById(R.id.id_tv_item_title);
        String parentName = vitalSignTypeItemBeanList.get(groupPosition).LBMC;
        id_tv_item_title.setText(parentName);

        ImageView id_iv_item_left = (ImageView) convertView.findViewById(R.id.id_iv_item_left);

        Drawable logoDrawable = VectorOrImageResHelper.getDrawable(R.drawable.ic_more_vert_black_24dp);
        logoDrawable.setColorFilter(ContextCompat.getColor(parent.getContext(),R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        id_iv_item_left.setImageDrawable(logoDrawable);

        ImageView id_iv_item_right = (ImageView) convertView.findViewById(R.id.id_iv_item_right);
        //判断isExpanded就可以控制是按下还是关闭，同时更换图片
        if (isExpanded) {
            Drawable upDrawable = VectorOrImageResHelper.getDrawable(R.drawable.ic_keyboard_arrow_up_black_24dp);
            upDrawable.setColorFilter(ContextCompat.getColor(parent.getContext(),R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
            id_iv_item_right.setImageDrawable(upDrawable);
        } else {
            Drawable downDrawable = VectorOrImageResHelper.getDrawable(R.drawable.ic_keyboard_arrow_down_black_24dp);
            downDrawable.setColorFilter(ContextCompat.getColor(parent.getContext(),R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
            id_iv_item_right.setImageDrawable(downDrawable);
        }
        return convertView;
    }

    //设置子item的组件
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
    /*    if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.child_layout, null);
        }*/
       /* LinearLayout linearLayout=new LinearLayout(parent.getContext());
        linearLayout.addView();*/
        String childName = vitalSignTypeItemBeanList.get(groupPosition).LifeSignInputItemList.get(childPosition).HHBZ;
        Context context=parent.getContext();

        ClassicFormInputLayout classicFormInputLayout3=new ClassicFormInputLayout(context);
        classicFormInputLayout3.addStartText("START");
        classicFormInputLayout3.addEndText("END");
        List<String> stringList=new ArrayList<>();
        stringList.add("212121");
        stringList.add("21223123123121");
        classicFormInputLayout3.addCenterEditView("456","HINT",null);
        classicFormInputLayout3.setBackgroundResource(R.drawable.selector_classic_text_item_underline_bg);

        //tv_items_child.setText(childName);
        return classicFormInputLayout3;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        // return false;
        return true;
    }
}