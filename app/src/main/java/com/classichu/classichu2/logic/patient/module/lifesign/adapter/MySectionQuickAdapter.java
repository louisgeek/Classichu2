package com.classichu.classichu2.logic.patient.module.lifesign.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.NumberKeyListener;
import android.util.Log;
import android.util.Pair;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.classichu.classichu2.R;
import com.classichu.classichu2.app.AppInfoDataManager;
import com.classichu.classichu2.logic.patient.module.lifesign.bean.LifeSignControlItem;
import com.classichu.classichu2.logic.patient.module.lifesign.bean.LifeSignInputItem;
import com.classichu.classichu2.logic.patient.module.lifesign.bean.LifeSignOptionItem;
import com.classichu.classichu2.logic.patient.module.lifesign.bean.MySectionEntity;
import com.classichu.classichu2.logic.patient.module.lifesign.bean.VitalSignTypeItemBean;
import com.classichu.classichu2.tool.SizeTool;
import com.classichu.classichu2.widget.ClassicFormInputLayout;
import com.classichu.classichu2.widget.ClassicInputLayout;
import com.classichu.classichu2.widget.pinned_section_itemdecoration.utils.FullSpanUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Classichu on 2017/11/7.
 */

public class MySectionQuickAdapter extends BaseSectionQuickAdapter<MySectionEntity, BaseViewHolder> {

    /**
     * 配合PinnedHeaderItemDecoration实现粘性头部，保证SECTION_HEADER_VIEW一致
     */
    public static final int SECTION_HEADER_VIEW = BaseSectionQuickAdapter.SECTION_HEADER_VIEW;//protected=》public

    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param layoutResId      The layout resource id of each item.
     * @param sectionHeadResId The section head layout id for each item
     * @param data             A new list is created out of this one to avoid mutable list
     */
    public MySectionQuickAdapter(int layoutResId, int sectionHeadResId, List<MySectionEntity> data) {
        super(layoutResId, sectionHeadResId, data);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        FullSpanUtil.onAttachedToRecyclerView(recyclerView, this, BaseSectionQuickAdapter.SECTION_HEADER_VIEW);
    }

    @Override
    public void onViewAttachedToWindow(BaseViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        FullSpanUtil.onViewAttachedToWindow(holder, this, BaseSectionQuickAdapter.SECTION_HEADER_VIEW);
    }

    @Override
    protected void convertHead(BaseViewHolder helper, MySectionEntity item) {
        helper.setText(R.id.id_tv_item_title, item.header);
    }

    @Override
    protected void convert(BaseViewHolder helper, MySectionEntity item) {
        //
        Context context = helper.itemView.getContext();
        LinearLayout root = new LinearLayout(context);
        LinearLayout.LayoutParams ll_lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        root.setLayoutParams(ll_lp);
        root.setOrientation(LinearLayout.VERTICAL);
        //
        VitalSignTypeItemBean vitalSignTypeItemBean = item.t;
        this.parserRoot(context, vitalSignTypeItemBean, root);
        //
        FrameLayout id_frame_layout = (FrameLayout) helper.itemView.findViewById(R.id.id_frame_layout);
        id_frame_layout.addView(root);
    }


    private void parserRoot(Context context, VitalSignTypeItemBean lifeSignTypeItem, LinearLayout root) {
        String zyh = AppInfoDataManager.getInstance().getZyh();
        String jgid = AppInfoDataManager.getInstance().getJGID();

        if (null != lifeSignTypeItem.LifeSignInputItemList && lifeSignTypeItem.LifeSignInputItemList.size() > 0) {
            for (LifeSignInputItem lifeSignInputItem : lifeSignTypeItem.LifeSignInputItemList) {
                String tsbz = "0";
                String tzxm = "0";
                if (null != lifeSignInputItem.LifeSignControlItemList && lifeSignInputItem.LifeSignControlItemList.size() > 0) {
                    for (LifeSignControlItem lifeSignControlItem : lifeSignInputItem.LifeSignControlItemList) {
                        if (lifeSignControlItem.TSBZ != null && !lifeSignControlItem.TSBZ.equals("0")) {
                            tsbz = lifeSignControlItem.TSBZ;
                        }
                        if (lifeSignControlItem.TZXM != null && !lifeSignControlItem.TZXM.isEmpty() && !lifeSignControlItem.TZXM.equals("0")) {
                            tzxm = lifeSignControlItem.TZXM;
                        }
                    }
                }
                View tempView;
                switch (tsbz) {
                    case "1":
                        // 时间控件
                        tempView = parserInputItem(context, lifeSignInputItem, zyh, jgid);
                        if (tempView != null) {
                            root.addView(tempView);
                        }
                        break;
                  /*  case "2":
                        // 体温类型控件
                        ExceptView exceptView = new ExceptView(context, root, lifeSignInputItem,
                                zyh, application, jgid);
                        linearLayout.addView(exceptView);
                        exceptViewList.add(exceptView);
                        break;*/
                    default:
                        // 普通类型控件
                        tempView = parserInputItem(context, lifeSignInputItem, zyh, jgid);
                        if (tempView != null) {
                            root.addView(tempView);
                        }
                        break;
                }
                if (tzxm.equals("1") || tzxm.equals("502")) {
                  /*  // 复测控件
                    DoubleCheckView doubleckview = new DoubleCheckView(context,
                            root, zyh, application, tzxm, jgid);
                    linearLayout.addView(doubleckview);
                    doubleCheckViewList.add(doubleckview);*/
                }
            }
        }
    }

    private LinearLayout parserInputItem(Context context, LifeSignInputItem lifeSignInputItem, String zyh, String jgid) {

        /*HorizontalScrollView scrollView = new HorizontalScrollView(context);
        scrollView.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));*/
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        layout.setGravity(Gravity.CENTER_VERTICAL);
        int leftRight = SizeTool.dp2px(10);
        int topBottom = SizeTool.dp2px(2);
        layout.setPadding(leftRight, topBottom, leftRight, topBottom);
        if (null != lifeSignInputItem.LifeSignControlItemList && lifeSignInputItem.LifeSignControlItemList.size() > 0) {
            for (LifeSignControlItem lifeSignControlItem : lifeSignInputItem.LifeSignControlItemList) {
                int tsbz = lifeSignControlItem.TSBZ == null ? 0 : Integer.parseInt(lifeSignControlItem.TSBZ);
                View inputView = this.parserItem(context, lifeSignControlItem, tsbz, zyh, jgid);
                if (null != inputView) {
                    layout.addView(inputView);
                }
            }
        }
        layout.setId(Integer.parseInt(lifeSignInputItem.SRXH));
        //#@#@#@ inputItemMap.put(Integer.parseInt(lifeSignInputItem.SRXH), view);
        ///  scrollView.addView(view);
        return layout;
    }

    private View parserItem(Context context, final LifeSignControlItem lifeSignControlItem, int tsbz,
                            final String zyh, final String jgid) {
        if (lifeSignControlItem.KJLX == null) {
            Log.e("tag", "itemType IS NULL IN ViewUtil's parserItem method");
            return null;
        }
        switch (lifeSignControlItem.KJLX) {
            case "1":
                TextView textView = new TextView(context);
                textView.setTextColor(ContextCompat.getColor(context, R.color.text_black_second));
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, context.getResources().getDimensionPixelSize(R.dimen.classic_text_size_secondary));
                textView.setText(lifeSignControlItem.KJNR);
                return textView;
            case "2":
                ClassicFormInputLayout editLayout = new ClassicFormInputLayout(context);
                //  editLayout.addStartText("开始");
                editLayout.addCenterEditView(null);
                //  editLayout.addEndText("结束");
                ClassicInputLayout classicInputLayout = editLayout.getInputLayoutInCenterLayout();
                // 设置
                setNumListener(classicInputLayout, lifeSignControlItem);
                editLayout.setTag(lifeSignControlItem.TZXM);
                return editLayout;
            case "4":

                List<Pair<String, String>> stringList = new ArrayList<>();
                for (LifeSignOptionItem lifeSignOptionItem : lifeSignControlItem.LifeSignOptionItemList) {
                    stringList.add(Pair.create(lifeSignOptionItem.XZH, lifeSignOptionItem.XZNR));
                }

                boolean editAble;
                String text = null;
                if ("1".equals(lifeSignControlItem.QTSR)) {
                    // pullEditView.setEditIsAdble(true);
                    text = lifeSignControlItem.KJNR;
                    editAble = true;
                }/*else  if ("1".equals(lifeSignControlItem.TSBZ)) {
                // 时间控件 唯一
                pullEditView.setEditIsAdble(false);
              ///##  timeEdit = pullEditView;
               }*/ else {
                    //   pullEditView.setEditIsAdble(false);
                    // 默认选择第一个选项
                    if (null != lifeSignControlItem.LifeSignOptionItemList
                            && lifeSignControlItem.LifeSignOptionItemList.size() > 0) {
                       /* pullEditView.getEditText().setText(
                                lifeSignControlItem.LifeSignOptionItemList.get(0).XZNR);*/
                        text = lifeSignControlItem.LifeSignOptionItemList.get(0).XZNR;
                    }
                    editAble = false;
                }


            /*    if ("1".equals(lifeSignControlItem.TSBZ)) {
                    // 时间控件 唯一
                    pullEditView.setEditIsAdble(false);
                    timeEdit = pullEditView;
                } else {
                    pullEditViewList.add(pullEditView);
                }*/
                ClassicFormInputLayout editViewLayout = new ClassicFormInputLayout(context);
                //  editViewLayout.addStartText("开始");
                editViewLayout.addCenterEditView(text, null, stringList, editAble,false);
                //  editViewLayout.addEndText("结束");
                ClassicInputLayout centerLayout = editViewLayout.getInputLayoutInCenterLayout();
                // 设置
                setNumListener(centerLayout, lifeSignControlItem);
                centerLayout.setTag(lifeSignControlItem.TZXM);

                // 设置
                setNumListener(centerLayout, lifeSignControlItem);
                editViewLayout.setTag(lifeSignControlItem.TZXM);
                return editViewLayout;

          /*     case "3"://活动控件实现
             PullEditView dynamicPullEditView = new PullEditView(context);
                dynamicPullEditView.popWidth = Integer.parseInt(lifeSignControlItem.KJCD) * wid + 70;
                dynamicPullEditView.setOnSelectListener(new OnSelectListener() {
                    @Override
                    public void doSelect(final String srxh) {
                        final LinearLayout layout = inputItemMap
                                .get(Integer.parseInt(lifeSignControlItem.SRXH));
                        new AsyncTask<Void, Void, Response<LifeSignInputItem>>() {
                            @Override
                            protected void onPreExecute() {
                                if (null != emptyProgress) {
                                    emptyProgress.setVisibility(View.VISIBLE);
                                }
                            }

                            @Override
                            protected Response<LifeSignInputItem> doInBackground(Void... arg0) {
                                Response<LifeSignInputItem> sresult = LifeSignApi
                                        .getInstance(context).getLifeSignItem(srxh, zyh, jgid, Config.sysType);
                                return sresult;
                            }

                            @Override
                            protected void onPostExecute(Response<LifeSignInputItem> result) {
                                if (null != result) {
                                    if (result.ReType == 100) {
                                        new AgainLoginUtil(context, application).showLoginDialog();
                                    } else if (result.ReType == 0) {
                                        View view = buildDynamicBox(result.Data, zyh, jgid);
                                        layout.removeAllViews();
                                        layout.addView(view);
                                    }
                                }
                                if (null != emptyProgress) {
                                    emptyProgress.setVisibility(View.GONE);
                                }
                            }
                        }.execute();
                    }
                });
                if (null != lifeSignControlItem.LifeSignOptionItemList) {
                    dynamicPullEditView.setDataList(lifeSignControlItem.LifeSignOptionItemList);
                }
                dynamicPullEditView.setLayoutParams(new LinearLayout.LayoutParams(
                        Integer.parseInt(lifeSignControlItem.KJCD) * wid, LayoutParams.WRAP_CONTENT));
                // 默认不能编辑
                dynamicPullEditView.setEditIsAdble(false);
                pullEditViewList.add(dynamicPullEditView);
                return dynamicPullEditView;*/
            default:
                break;
        }
        return null;
    }

    // 是否设置数字输入
    private void setNumListener(ClassicInputLayout classicInputLayout, LifeSignControlItem lifeSignControlItem) {
        if (classicInputLayout == null) {
            return;
        }
    /*
    升级编号【56010033】============================================= start
    【其他】输入修正
    ================= Classichu 2017/10/24 16:52
    */
        ////########2017-9-8 15:52:20 if ("1".equals(lifeSignControlItem.SZSR)) {
        if ("1".equals(lifeSignControlItem.SZSR) && !"1".equals(lifeSignControlItem.QTSR)) {
            /* =============================================================== end */
            classicInputLayout.getInput().setKeyListener(new NumberKeyListener() {

                @Override
                protected char[] getAcceptedChars() {
                    char[] numberChars = {'1', '2', '3', '4', '5', '6', '7',
                            '8', '9', '0', '.'};
                    return numberChars;
                }

                @Override
                public int getInputType() {
                    return android.text.InputType.TYPE_CLASS_PHONE;
                }
            });

            setNumLimit(classicInputLayout, lifeSignControlItem);
        }
    }

    // 设置输入的区间限制
    private void setNumLimit(final ClassicInputLayout classicInputLayout, final LifeSignControlItem lifeSignControlItem) {
        if (lifeSignControlItem.isMaxMinAble()) {

            classicInputLayout.getInput().addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence s, int start,
                                          int before, int count) {
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start,
                                              int count, int after) {
                }

                @Override
                public void afterTextChanged(Editable s) {

                    if (!TextUtils.isEmpty(s)) {
                        //拒测等非数字时 不再转化数值进行各种验证
                        if (!s.toString().matches("^[0-9]*(.[0-9]+)?$")) {
                            return;
                        }
                        float inputText;
                        try {
                            inputText = Float.valueOf(s.toString());
                        } catch (Exception e) {
                            return;
                        }
                        int value = lifeSignControlItem.getMaxMinStatue(inputText);
                        switch (value) {
                            case 1:
                                classicInputLayout.setTextColor(Color.BLACK);
                                classicInputLayout.setError("数值超出正常下限");
                                //abnormityMap.put(lifeSignControlItem.TZXM, "-2");
                                break;
                            case 2:
                                classicInputLayout.setTextColor(Color.RED);
                                // abnormityMap.put(lifeSignControlItem.TZXM, "-1");
                                break;
                            case 3:
                                classicInputLayout.setTextColor(Color.BLACK);
                                // abnormityMap.put(lifeSignControlItem.TZXM, "0");
                                break;
                            case 4:
                                classicInputLayout.setTextColor(Color.RED);
                                //  abnormityMap.put(lifeSignControlItem.TZXM, "1");
                                break;
                            case 5:
                                classicInputLayout.setTextColor(Color.BLACK);
                                classicInputLayout.setError("数值超出正常上限");
                                //  abnormityMap.put(lifeSignControlItem.TZXM, "2");
                                break;
                            default:
                                break;
                        }

                    }
                }

            });

            classicInputLayout.getInput().setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus) {
                        String str = ((EditText) v).getText().toString();
                        if (!TextUtils.isEmpty(str)) {
                            //拒测等非数字时 不再转化数值进行各种验证
                            if (!str.matches("^[0-9]*(.[0-9]+)?$")) {
                                return;
                            }
                            float inputText;
                            try {
                                inputText = Float.valueOf(str);
                            } catch (Exception e) {
                                return;
                            }
                            int value = lifeSignControlItem.getMaxMinStatue(inputText);
                            switch (value) {
                                case 1:
                                    classicInputLayout.setText("");
                                    Toast.makeText(classicInputLayout.getContext(), "输入数值超出下限",
                                            Toast.LENGTH_SHORT).show();
                                    classicInputLayout.getLayoutParams().width = 200;
                                    break;
                                case 5:
                                    classicInputLayout.setText("");
                                    Toast.makeText(classicInputLayout.getContext(), "输入数值超出上限",
                                            Toast.LENGTH_SHORT).show();
                                    classicInputLayout.getLayoutParams().width = 200;
                                    break;

                                default:
                                    break;
                            }
                        }

                    }
                }
            });
        }
    }

}
