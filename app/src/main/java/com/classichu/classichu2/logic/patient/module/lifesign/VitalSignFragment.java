package com.classichu.classichu2.logic.patient.module.lifesign;


import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import com.classichu.classichu2.R;
import com.classichu.classichu2.base.BaseMvpFragment;
import com.classichu.classichu2.logic.patient.module.lifesign.adapter.MyBaseExpandableListAdapter;
import com.classichu.classichu2.logic.patient.module.lifesign.bean.VitalSignTimePointBean;
import com.classichu.classichu2.logic.patient.module.lifesign.bean.VitalSignTypeItemBean;
import com.classichu.classichu2.tool.ToastTool;
import com.classichu.classichu2.widget.ClassicFormInputLayout;
import com.classichu.dialogview.manager.DialogManager;

import java.util.ArrayList;
import java.util.List;


public class VitalSignFragment extends BaseMvpFragment<VitalSignPresenter> implements VitalSignContract.View<Pair<List<VitalSignTimePointBean>,List<VitalSignTypeItemBean>>> {

    public VitalSignFragment() {
        // Required empty public constructor
    }

    public static VitalSignFragment newInstance(String param1, String param2) {
        VitalSignFragment fragment = new VitalSignFragment();
        Bundle args = new Bundle();
      /*  args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);*/
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
           /* mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);*/
        }
    }




    @Override
    protected int setupLayoutResId() {
        return R.layout.fragment_vital_sign;
    }

    @Override
    protected void initView(View rootLayout, Bundle savedInstanceState) {

        mPresenter.gainData();

    }


    @Override
    public void showLoading() {
        DialogManager.showCustomLoadingDialog(getActivity());
    }

    @Override
    public void hideLoading() {
        DialogManager.hideLoadingDialog();
    }

    @Override
    public void showMessage(String msg) {
        showSnack(msg);
    }

    @Override
    public void setupData(Pair<List<VitalSignTimePointBean>, List<VitalSignTypeItemBean>> listListPair) {

        List<VitalSignTimePointBean> vitalSignTimePointBeanList=listListPair.first;

        VitalSignTimePointBean first = new VitalSignTimePointBean();
        first.NAME = "";
        first.VALUE = 0;
        vitalSignTimePointBeanList.add(0, first);
        //
 /*       Spinner  spinner =new Spinner(mContext);
        spinner.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

//        spinner.setBackgroundResource(R.drawable.selector_classic_text_item_underline_bg);
        //适配器
        ArrayAdapter<VitalSignTimePointBean>  arrayAdapter= new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, vitalSignTimePointBeanList);
        //设置样式
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //加载适配器
        spinner.setAdapter(arrayAdapter);*/

  /*      Drawable downDrawable = VectorOrImageResHelper.getDrawable(R.drawable.ic_keyboard_arrow_down_black_24dp);
        downDrawable.setColorFilter(ContextCompat.getColor(mContext,R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        ViewCompat.setBackground(spinner,downDrawable);*/


        ClassicFormInputLayout classicFormInputLayout_TimePoint=new ClassicFormInputLayout(mContext);
        classicFormInputLayout_TimePoint.addStartText("时间点：", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastTool.show("时间点");
            }
        });
        //classicFormInputLayout.addEndText("END");
        List<Pair<String,String>> stringList=new ArrayList<>();
        for (VitalSignTimePointBean vitalSignTimePointBean : vitalSignTimePointBeanList) {
            stringList.add(Pair.create(String.valueOf(vitalSignTimePointBean.VALUE),vitalSignTimePointBean.NAME));
        }
        classicFormInputLayout_TimePoint.addCenterEditView(null,"请选择时间点",stringList,true);
        classicFormInputLayout_TimePoint.setBackgroundResource(R.drawable.selector_classic_text_item_underline_bg);


        List<VitalSignTypeItemBean> vitalSignTypeItemBeanList=listListPair.second;

        ExpandableListView expandableListView=new ExpandableListView(mContext);
        expandableListView.setGroupIndicator(null);
        MyBaseExpandableListAdapter  adapter=new MyBaseExpandableListAdapter(vitalSignTypeItemBeanList);
        expandableListView.setAdapter(adapter);

        LinearLayout linearLayout=findById(R.id.idxxx);
        linearLayout.addView(classicFormInputLayout_TimePoint);
        linearLayout.addView(expandableListView);

    }


    @Override
    protected VitalSignPresenter setupPresenter() {
        return new VitalSignPresenter(null,this);
    }


}
