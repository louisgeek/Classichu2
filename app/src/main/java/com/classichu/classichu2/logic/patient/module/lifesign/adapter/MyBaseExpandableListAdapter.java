package com.classichu.classichu2.logic.patient.module.lifesign.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.NumberKeyListener;
import android.util.Log;
import android.util.Pair;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.classichu.classichu2.R;
import com.classichu.classichu2.app.AppInfoDataManager;
import com.classichu.classichu2.helper.VectorOrImageResHelper;
import com.classichu.classichu2.logic.patient.module.lifesign.bean.LifeSignControlItem;
import com.classichu.classichu2.logic.patient.module.lifesign.bean.LifeSignInputItem;
import com.classichu.classichu2.logic.patient.module.lifesign.bean.LifeSignOptionItem;
import com.classichu.classichu2.logic.patient.module.lifesign.bean.VitalSignTypeItemBean;
import com.classichu.classichu2.tool.SizeTool;
import com.classichu.classichu2.widget.ClassicFormInputLayout;

import java.util.ArrayList;
import java.util.List;

import static com.classichu.classichu2.R.id.id_iv_item_right;

/**
 * Created by Classichu on 2017/11/2.
 */

@Deprecated
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
        return 1;//child使用动态生成的一个view
    }

    //获取当前父item的数据
    @Override
    public Object getGroup(int groupPosition) {
        return vitalSignTypeItemBeanList.get(groupPosition);
    }

    //得到子item需要关联的数据
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return vitalSignTypeItemBeanList.get(groupPosition).LifeSignInputItemList;
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
        GroupViewHolder groupViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_card_expandable, null);
            groupViewHolder = new GroupViewHolder();
            groupViewHolder.id_tv_item_title = (TextView) convertView.findViewById(R.id.id_tv_item_title);
            groupViewHolder.id_iv_item_left = (ImageView) convertView.findViewById(R.id.id_iv_item_left);
            groupViewHolder.id_iv_item_right = (ImageView) convertView.findViewById(id_iv_item_right);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }

        String parentName = vitalSignTypeItemBeanList.get(groupPosition).LBMC;
        groupViewHolder.id_tv_item_title.setText(parentName);

        Drawable logoDrawable = VectorOrImageResHelper.getDrawable(R.drawable.ic_more_vert_black_24dp);
        logoDrawable.setColorFilter(ContextCompat.getColor(parent.getContext(), R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        groupViewHolder.id_iv_item_left.setImageDrawable(logoDrawable);

        //判断isExpanded就可以控制是按下还是关闭，同时更换图片
        if (isExpanded) {
            Drawable upDrawable = VectorOrImageResHelper.getDrawable(R.drawable.ic_keyboard_arrow_up_black_24dp);
            upDrawable.setColorFilter(ContextCompat.getColor(parent.getContext(), R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
            groupViewHolder.id_iv_item_right.setImageDrawable(upDrawable);
        } else {
            Drawable downDrawable = VectorOrImageResHelper.getDrawable(R.drawable.ic_keyboard_arrow_down_black_24dp);
            downDrawable.setColorFilter(ContextCompat.getColor(parent.getContext(), R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
            groupViewHolder.id_iv_item_right.setImageDrawable(downDrawable);
        }
        return convertView;
    }

    //设置子item的组件
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        Log.i("zfq", "getChildView: ");
        LinearLayout root = new LinearLayout(parent.getContext());
        LinearLayout.LayoutParams ll_lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        root.setLayoutParams(ll_lp);
        root.setOrientation(LinearLayout.VERTICAL);
        //
        VitalSignTypeItemBean lifeSignTypeItem = vitalSignTypeItemBeanList.get(groupPosition);
        this.parserRoot(parent.getContext(), lifeSignTypeItem, root);
        return root;
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
        int leftRight= SizeTool.dp2px(10);
        int topBottom=SizeTool.dp2px(2);
        layout.setPadding(leftRight,topBottom,leftRight,topBottom);
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
                EditText editText = editLayout.getEditTextInCenterLayout();
                // 设置
                setNumListener(editText, lifeSignControlItem);
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
                editViewLayout.addCenterEditView(text, null, stringList, editAble);
              //  editViewLayout.addEndText("结束");
                EditText editView = editViewLayout.getEditTextInCenterLayout();
                // 设置
                setNumListener(editView, lifeSignControlItem);
                editView.setTag(lifeSignControlItem.TZXM);

                // 设置
                setNumListener(editView, lifeSignControlItem);
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
    private void setNumListener(EditText edit, LifeSignControlItem lifeSignControlItem) {
        if (edit == null) {
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
            edit.setKeyListener(new NumberKeyListener() {

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

            setNumLimit(edit, lifeSignControlItem);
        }
    }

    // 设置输入的区间限制
    private void setNumLimit(final EditText edit, final LifeSignControlItem lifeSignControlItem) {
        if (lifeSignControlItem.isMaxMinAble()) {

            edit.addTextChangedListener(new TextWatcher() {

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
                                edit.setTextColor(Color.BLACK);
                                edit.setError("数值超出正常下限");
                                //abnormityMap.put(lifeSignControlItem.TZXM, "-2");
                                break;
                            case 2:
                                edit.setTextColor(Color.RED);
                                // abnormityMap.put(lifeSignControlItem.TZXM, "-1");
                                break;
                            case 3:
                                edit.setTextColor(Color.BLACK);
                                // abnormityMap.put(lifeSignControlItem.TZXM, "0");
                                break;
                            case 4:
                                edit.setTextColor(Color.RED);
                                //  abnormityMap.put(lifeSignControlItem.TZXM, "1");
                                break;
                            case 5:
                                edit.setTextColor(Color.BLACK);
                                edit.setError("数值超出正常上限");
                                //  abnormityMap.put(lifeSignControlItem.TZXM, "2");
                                break;
                            default:
                                break;
                        }

                    }
                }

            });

          edit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
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
                                    edit.setText("");
                                    Toast.makeText(edit.getContext(), "输入数值超出下限",
                                            Toast.LENGTH_SHORT).show();
                                    edit.getLayoutParams().width = 200;
                                    break;
                                case 5:
                                    edit.setText("");
                                    Toast.makeText(edit.getContext(), "输入数值超出上限",
                                            Toast.LENGTH_SHORT).show();
                                    edit.getLayoutParams().width = 200;
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

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        // return false;
        return true;
    }

    class GroupViewHolder {
        TextView id_tv_item_title;
        ImageView id_iv_item_left;
        ImageView id_iv_item_right;
    }

    class ChildViewHolder {
//        LinearLayout root;
    }


}