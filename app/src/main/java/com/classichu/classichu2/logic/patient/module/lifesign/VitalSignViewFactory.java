package com.classichu.classichu2.logic.patient.module.lifesign;

import android.content.Context;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.classichu.classichu2.R;
import com.classichu.classichu2.logic.patient.module.lifesign.adapter.MyMultipleItemQuickAdapter;
import com.classichu.classichu2.logic.patient.module.lifesign.adapter.MyMultipleItemQuickAdapter2;
import com.classichu.classichu2.logic.patient.module.lifesign.adapter.MySectionQuickAdapter;
import com.classichu.classichu2.logic.patient.module.lifesign.bean.MyGroupChildBean;
import com.classichu.classichu2.logic.patient.module.lifesign.bean.MyMultiItemEntity;
import com.classichu.classichu2.logic.patient.module.lifesign.bean.MySectionEntity;
import com.classichu.classichu2.logic.patient.module.lifesign.bean.VitalSignTimePointBean;
import com.classichu.classichu2.logic.patient.module.lifesign.bean.VitalSignTypeItemBean;
import com.classichu.classichu2.tool.SizeTool;
import com.classichu.classichu2.tool.ToastTool;
import com.classichu.classichu2.widget.ClassicFormInputLayout;
import com.classichu.classichu2.widget.pinned_section_itemdecoration.PinnedHeaderItemDecoration;
import com.classichu.classichu2.widget.pinned_section_itemdecoration.callback.OnHeaderClickAdapter;
import com.classichu.classichu2.widget.pinned_section_itemdecoration.callback.OnItemTouchListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Classichu on 2017/11/6.
 */

public class VitalSignViewFactory {


    public static void buildTimePointView(LinearLayout containerLayout, List<VitalSignTimePointBean> vitalSignTimePointBeanList) {
        Context context = containerLayout.getContext();
        LinearLayout root = new LinearLayout(context);
        LinearLayout.LayoutParams ll_lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        root.setLayoutParams(ll_lp);
        root.setOrientation(LinearLayout.HORIZONTAL);

        VitalSignTimePointBean first = new VitalSignTimePointBean();
        first.NAME = "";
        first.VALUE = 0;
        vitalSignTimePointBeanList.add(0, first);
        //
        ClassicFormInputLayout classicFormInputLayout_TimePoint = new ClassicFormInputLayout(context);
        classicFormInputLayout_TimePoint.addStartText("时间点:");
        //
        List<Pair<String, String>> stringList = new ArrayList<>();
        for (VitalSignTimePointBean vitalSignTimePointBean : vitalSignTimePointBeanList) {
            stringList.add(Pair.create(String.valueOf(vitalSignTimePointBean.VALUE), vitalSignTimePointBean.NAME));
        }
        classicFormInputLayout_TimePoint.addCenterEditView(null, "请选择时间点", stringList, false);
        classicFormInputLayout_TimePoint.setPadding(classicFormInputLayout_TimePoint.getPaddingLeft()+ SizeTool.dp2px(5),classicFormInputLayout_TimePoint.getPaddingTop(),classicFormInputLayout_TimePoint.getPaddingRight(),classicFormInputLayout_TimePoint.getPaddingBottom());
        // classicFormInputLayout_TimePoint.setBackgroundResource(R.drawable.selector_classic_text_item_underline_bg);
        root.addView(classicFormInputLayout_TimePoint);
         containerLayout.addView(root);
    }


    public static void buildVitalSignTypeItemView(final LinearLayout containerLayout, List<VitalSignTypeItemBean> vitalSignTypeItemBeanList, final boolean isTempTime, final String serverDate) {
/* ExpandableListView 中会遇到 输入框的焦点和内容相关奇葩问题       ExpandableListView expandableListView = new ExpandableListView(containerLayout.getContext());
        expandableListView.setGroupIndicator(null);
       // expandableListView.setDividerHeight(0);
        MyBaseExpandableListAdapter adapter = new MyBaseExpandableListAdapter(vitalSignTypeItemBeanList);
        expandableListView.setAdapter(adapter);
        //遍历所有group,将所有项设置成默认展开
        for (int i=0; i<adapter.getGroupCount(); i++)
        {
            expandableListView.expandGroup(i);
        }
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;//消费点击事件，不让收缩
            }
        });
        if (isTempTime) {
            DateSelectView dateSelectView = new DateSelectView(containerLayout.getContext());
            dateSelectView.setupDateText(serverDate);
            expandableListView.addHeaderView(dateSelectView);
        }
        //
        containerLayout.addView(expandableListView);*/
/**
 * 多布局1  实现MultiItemEntity的类包装业务Bean
 */
        List<MyMultiItemEntity<VitalSignTypeItemBean>> myMultiItemEntityList = new ArrayList<>();
        for (VitalSignTypeItemBean vitalSignTypeItemBean : vitalSignTypeItemBeanList) {
            myMultiItemEntityList.add(new MyMultiItemEntity<>(MyMultiItemEntity.GROUP,vitalSignTypeItemBean));//一个父类
            myMultiItemEntityList.add(new MyMultiItemEntity<>(MyMultiItemEntity.CHILD,vitalSignTypeItemBean));//一个子类(子类集合以一个view实现)
        }
        RecyclerView recyclerView = new RecyclerView(containerLayout.getContext());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(containerLayout.getContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setBackgroundResource(R.drawable.selector_classic_popup_bg);
        //hideLastDivider
//        RecyclerViewDivider.with(mContext).color(Color.parseColor("#FFD1CFCF")).build().addTo(recyclerView);
        recyclerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        MyMultipleItemQuickAdapter myMultipleItemQuickAdapter = new MyMultipleItemQuickAdapter(myMultiItemEntityList);
        recyclerView.setAdapter(myMultipleItemQuickAdapter);

        //### containerLayout.addView(recyclerView);
/**
 * 多布局2  MyGroupChildBean包装业务Bean
 */
        List<MyGroupChildBean<VitalSignTypeItemBean>> myGroupChildBeanList = new ArrayList<>();
        for (VitalSignTypeItemBean vitalSignTypeItemBean : vitalSignTypeItemBeanList) {
            myGroupChildBeanList.add(new MyGroupChildBean<VitalSignTypeItemBean>(Pair.create(vitalSignTypeItemBean.LBH,vitalSignTypeItemBean.LBMC)));//一个父类
            myGroupChildBeanList.add(new MyGroupChildBean<>(vitalSignTypeItemBean));//一个子类(子类集合以一个view实现)
        }
        RecyclerView recyclerView2 = new RecyclerView(containerLayout.getContext());
        recyclerView2.setItemAnimator(new DefaultItemAnimator());
        recyclerView2.setLayoutManager(new LinearLayoutManager(containerLayout.getContext()));
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setBackgroundResource(R.drawable.selector_classic_popup_bg);
        //hideLastDivider
//        RecyclerViewDivider.with(mContext).color(Color.parseColor("#FFD1CFCF")).build().addTo(recyclerView);
        recyclerView2.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        MyMultipleItemQuickAdapter2 myMultipleItemQuickAdapter2 = new MyMultipleItemQuickAdapter2(myGroupChildBeanList);
        recyclerView2.setAdapter(myMultipleItemQuickAdapter2);

       //### containerLayout.addView(recyclerView2);
/**
 * 分类布局（头部分类） + 粘性头部
 */
        final Context context=containerLayout.getContext();
        final List<MySectionEntity> mySectionEntityList = new ArrayList<>();
        for (VitalSignTypeItemBean vitalSignTypeItemBean : vitalSignTypeItemBeanList) {
            mySectionEntityList.add(new MySectionEntity(true,vitalSignTypeItemBean.LBMC));//一个父类
            mySectionEntityList.add(new MySectionEntity(vitalSignTypeItemBean));//一个子类(子类集合以一个view实现)
        }
        RecyclerView recyclerView3 = new RecyclerView(containerLayout.getContext());
        recyclerView3.setItemAnimator(new DefaultItemAnimator());
        recyclerView3.setLayoutManager(new LinearLayoutManager(context));
        recyclerView3.setHasFixedSize(true);
        recyclerView3.setBackgroundResource(R.drawable.selector_classic_popup_bg);
        //hideLastDivider
//        RecyclerViewDivider.with(mContext).color(Color.parseColor("#FFD1CFCF")).build().addTo(recyclerView);
        recyclerView3.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
//        recyclerView3.addItemDecoration(new DividerItemDecoration(context,DividerItemDecoration.HORIZONTAL));

       /* StickyHeadContainer stickyHeadContainer=new StickyHeadContainer(context);
        stickyHeadContainer.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        View header=LayoutInflater.from(context).inflate(R.layout.item_list_card_expandable,null);
        final TextView id_tv_item_title= (TextView) header.findViewById(R.id.id_tv_item_title);
        stickyHeadContainer.addView(header);
        stickyHeadContainer.setDataCallback(new StickyHeadContainer.DataCallback() {
            @Override
            public void onDataChange(int pos) {
                //mStickyPosition = pos;
                MySectionEntity item = mySectionEntityList.get(pos);
                id_tv_item_title.setText(item.header);
               // checkBox.setChecked(item.check);
            }
        });
        stickyHeadContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "点击了粘性头部：" + id_tv_item_title.getText(), Toast.LENGTH_SHORT).show();
            }
        });
        //
        recyclerView3.addItemDecoration(new StickyItemDecoration(stickyHeadContainer, StickyItemDecoration.SECTION_HEADER_VIEW));*/
        MySectionQuickAdapter mySectionQuickAdapter = new MySectionQuickAdapter(R.layout.item_list_child,R.layout.item_list_card_expandable,mySectionEntityList);
        mySectionQuickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastTool.show("onItemClick "+position);
            }
        });
        recyclerView3.setAdapter(mySectionQuickAdapter);


        OnHeaderClickAdapter headerClickListener = new OnHeaderClickAdapter() {
            @Override
            public void onHeaderClick(View view, int id, int position) {
                switch (id) {
                    //case R.id.fl:
                        case OnItemTouchListener.HEADER_ID:
                        Toast.makeText(context, "click, tag: " + mySectionEntityList.get(position).header, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.id_iv_item_right:
                        Toast.makeText(context, "click " + mySectionEntityList.get(position).header + "'s more button", Toast.LENGTH_SHORT)
                                .show();
                        break;
                /*    case R.id.iv_more:
                        Toast.makeText(context, "click " + mAdapter.getData().get(position).pinnedHeaderName + "'s more button", Toast.LENGTH_SHORT)
                                .show();
                        break;*/
                /*    case R.id.checkbox:
                        final CheckBox checkBox = (CheckBox) view;
                        checkBox.setChecked(!checkBox.isChecked());
                        // 刷新ItemDecorations，导致重绘刷新头部
                        mRecyclerView.invalidateItemDecorations();

                        mAdapter.getData().get(position).check = checkBox.isChecked();
                        mAdapter.notifyItemChanged(position + mHeaderItemDecoration.getDataPositionOffset());

                        break;*/
                }
            }
        };
        recyclerView3.addItemDecoration(
                // 设置粘性标签对应的类型
                new PinnedHeaderItemDecoration.Builder(MySectionQuickAdapter.SECTION_HEADER_VIEW)
                        // 设置分隔线资源ID
                        .setDividerId(R.drawable.divider)
                        // 开启绘制分隔线，默认关闭
                        .enableDivider(true)
                        // 通过传入包括标签和其内部的子控件的ID设置其对应的点击事件
                        .setClickIds(R.id.id_tv_item_title)
                        // 是否关闭标签点击事件，默认开启
                        .disableHeaderClick(false)
                        // 设置标签和其内部的子控件的监听，若设置点击监听不为null，但是disableHeaderClick(true)的话，还是不会响应点击事件
                        .setHeaderClickListener(headerClickListener)
                        .create());

        containerLayout.addView(recyclerView3);
    }
}
