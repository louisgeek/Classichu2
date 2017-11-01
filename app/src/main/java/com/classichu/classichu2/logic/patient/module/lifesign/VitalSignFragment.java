package com.classichu.classichu2.logic.patient.module.lifesign;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.classichu.classichu2.R;
import com.classichu.classichu2.base.BaseFragment;
import com.classichu.classichu2.custom.ClassicFormInputLayout;
import com.classichu.classichu2.helper.VectorOrImageResHelper;
import com.classichu.classichu2.tool.ToastTool;


public class VitalSignFragment extends BaseFragment {

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
        Drawable leftDrawable=
                VectorOrImageResHelper.getDrawable(R.drawable.ic_image_black_24dp);

        ClassicFormInputLayout classicFormInputLayout=new ClassicFormInputLayout(mContext);
        classicFormInputLayout.addStartText("开始", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastTool.show("开始");
            }
        });
        classicFormInputLayout.addStartDrawable(leftDrawable, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastTool.show("leftDrawable");
            }
        });
        classicFormInputLayout.setBackgroundResource(R.drawable.selector_classic_text_item_underline_bg);

        classicFormInputLayout.setHintText("Hint");
        classicFormInputLayout.addEndText("结束");
        classicFormInputLayout.addEndText("结束2");

        ClassicFormInputLayout classicFormInputLayout2=new ClassicFormInputLayout(mContext);
        classicFormInputLayout2.addEndDrawable(leftDrawable, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastTool.show("leftDrawable");
            }
        });
        classicFormInputLayout2.setHintText("HINT");
        classicFormInputLayout2.addStartText("START");
        classicFormInputLayout2.addEndText("END");
        classicFormInputLayout2.setBackgroundResource(R.drawable.selector_classic_text_item_underline_bg);
        LinearLayout linearLayout=findById(R.id.idxxx);
        linearLayout.addView(classicFormInputLayout);
        linearLayout.addView(classicFormInputLayout2);
    }


}
