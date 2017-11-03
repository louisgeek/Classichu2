package com.classichu.classichu2.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.classichu.adapter.listener.SimpleOnRVItemTouchListener;
import com.classichu.adapter.recyclerview.ClassicRecyclerViewAdapter;
import com.classichu.adapter.recyclerview.ClassicRecyclerViewHolder;
import com.classichu.classichu2.R;
import com.classichu.classichu2.helper.VectorOrImageResHelper;
import com.classichu.classichu2.tool.EmptyTool;
import com.classichu.classichu2.tool.SizeTool;
import com.fondesa.recyclerviewdivider.RecyclerViewDivider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Classichu on 2017/11/2.
 */

public class ClassicDropSelectEditView extends LinearLayout{
    private Context mContext;
    private boolean mEditAble;

    public ClassicDropSelectEditView(Context context, boolean editAble) {
        this(context, null, editAble);
    }

    public ClassicDropSelectEditView(Context context, AttributeSet attrs, boolean editAble) {
        this(context, attrs, 0, editAble);
    }

    public ClassicDropSelectEditView(Context context, AttributeSet attrs, int defStyleAttr, boolean editAble) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        mEditAble = editAble;
        init();
        initEditText();
        initDropDownImg();
        //
        if (mEditAble) {
            dropDownImg.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDropDownSelect(v);
                }
            });
            this.setBackgroundResource(R.drawable.selector_classic_item_primary_bg);
        } else {
            editText.setCursorVisible(false);
            //
            this.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDropDownSelect(v);
                }
            });
            this.setBackgroundResource(R.drawable.selector_classic_btn_item_click_bg);
        }

    }

    private void init() {
        //
        LinearLayout.LayoutParams ll_lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        ll_lp.gravity = Gravity.CENTER_VERTICAL;
        this.setLayoutParams(ll_lp);
        this.setGravity(Gravity.CENTER_VERTICAL);
    }

    private ImageView dropDownImg;

    private void initDropDownImg() {
        dropDownImg = new ImageView(mContext);
        int paddingTopBottomRight  = SizeTool.dp2px(10);//10dp
        dropDownImg.setPadding(0, paddingTopBottomRight, paddingTopBottomRight, paddingTopBottomRight);
        Drawable downDrawable = VectorOrImageResHelper.getDrawable(R.drawable.ic_keyboard_arrow_down_black_24dp);
        downDrawable.setColorFilter(ContextCompat.getColor(mContext, R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        dropDownImg.setImageDrawable(downDrawable);
        //###!!!! dropDownImg.setBackgroundResource(R.drawable.selector_classic_btn_item_click_bg);
        this.addView(dropDownImg);
    }

    private EditText editText;

    public void setHint(CharSequence hint) {
        editText.setHint(hint);
    }
    public void setSelectAllOnFocus(boolean selectAllOnFocus) {
        editText.setSelectAllOnFocus(selectAllOnFocus);
    }
    public void setText(CharSequence text) {
        editText.setText(text);
    }
    public void setError(CharSequence error) {
        editText.setError(error);
    }

    public String getText() {
        return editText.getText().toString();
    }

    private void initEditText() {
        editText = new EditText(mContext);
        LayoutParams ll_lp = new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        ll_lp.weight = 1.0f;
        //android 5.0 API 21上的EditText文字偏上 定义不同的dimen文件 兼容处理这个问题
        int api21_fixed_edittext_spacing = mContext.getResources().getDimensionPixelSize(R.dimen.api21_fixed_edittext_spacing);
        ll_lp.gravity = Gravity.CENTER_VERTICAL;
        editText.setGravity(Gravity.CENTER_VERTICAL);
        editText.setLayoutParams(ll_lp);
        editText.setLines(1);
        //设置左padding
        editText.setPadding(editText.getPaddingLeft() + SizeTool.dp2px(5),
                editText.getPaddingTop() + api21_fixed_edittext_spacing,
                editText.getPaddingRight(),
                editText.getPaddingBottom());
        editText.setHintTextColor(Color.parseColor("#42000000"));
        ViewCompat.setBackground(editText, null);
        if (mEditAble) {
            editText.setOnFocusChangeListener(new OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    // 会影响内部view 的 setSelected 状态
                    ClassicDropSelectEditView.this.setSelected(hasFocus);
                    // ClassicInputLayout.this.setActivated(hasFocus);
                }
            });
        }
        this.addView(editText);
    }

   /*  private TextView textView;

   private void initTextView() {
        textView = new TextView(mContext);
        LayoutParams ll_lp = new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        ll_lp.weight = 1.0f;
        ll_lp.gravity = Gravity.CENTER_VERTICAL;
        textView.setGravity(Gravity.CENTER_VERTICAL);
        textView.setLayoutParams(ll_lp);
        textView.setLines(1);
        //设置左padding
        textView.setPadding(textView.getPaddingLeft() + SizeTool.dp2px(5),
                textView.getPaddingTop(),
                textView.getPaddingRight(),
                textView.getPaddingBottom());
        textView.setHintTextColor(Color.parseColor("#42000000"));
        this.addView(textView);
    }*/

    public void setupDropDownSelectData(List<Pair<String,String>> stringList) {
        if (stringList != null && stringList.size() > 0) {
            dropDownImg.setVisibility(VISIBLE);
            this.stringList.clear();
            this.stringList.addAll(stringList);
        } else {
            dropDownImg.setVisibility(GONE);
        }

    }

    private PopupWindow popupWindow;
    private List<Pair<String,String>> stringList = new ArrayList<>();
    private SelectAdapter adapter;

    private void showDropDownSelect(View view) {
        //
        adapter = new SelectAdapter(stringList,
                R.layout.item_list_normal);

        RecyclerView recyclerView = new RecyclerView(mContext);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setHasFixedSize(true);
        recyclerView.setBackgroundResource(R.drawable.selector_classic_popup_bg);
        //hideLastDivider
        RecyclerViewDivider.with(mContext).color(Color.parseColor("#FFD1CFCF")).build().addTo(recyclerView);
        recyclerView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        recyclerView.addOnItemTouchListener(new SimpleOnRVItemTouchListener(recyclerView) {
            @Override
            public void onItemClick(View view, int position) {
                super.onItemClick(view, position);
                // ToastTool.show("onItemClick");
                Pair<String,String> value = adapter.getData(position);
                editText.setText(value.second);
                editText.setSelection(editText.length());//将光标移动最后一个字符后面
                //
                popupWindow.dismiss();
            }
        });
        recyclerView.setAdapter(adapter);
        //
        popupWindow = new PopupWindow(mContext);
        popupWindow.setContentView(recyclerView);
        int linearLayoutWidth = this.getMeasuredWidth();
       //## popupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setWidth(linearLayoutWidth);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //
        popupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.update();
        //
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
       /* int fixed = SizeTool.dp2px(10);//10dp
        popupWindow.showAsDropDown(editText, editText.getPaddingLeft(), -fixed);*/
        popupWindow.showAsDropDown(this, 0, -view.getHeight() / 5);

 /*       //显示类似于右对齐
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int x = location[0];
        int y = location[1];
        int xOffset = 5;
        int yOffset = 5;
        popupWindow.showAtLocation(view, Gravity.RIGHT | Gravity.END | Gravity.TOP,
                xOffset, y + view.getHeight() + yOffset);*/
      /*  //显示居中
        View contentView = popupWindow.getContentView();
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int contentViewWidth = contentView.getMeasuredWidth();
        //x轴处理居中
        popupWindow.showAsDropDown(view, -(contentViewWidth - view.getWidth()) / 2, -view.getHeight() / 5);*/
        //此法处理居中不行  y偏移不行
        // int statusBarHeight=ScreenTool.getStatusBarHeight();
        // popupWindow.showAtLocation(view,Gravity.CENTER_HORIZONTAL,0,-statusBarHeight);

    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (mEditAble) {
            return super.onInterceptTouchEvent(ev);
        }
        return true;
    }

    class SelectAdapter extends ClassicRecyclerViewAdapter<Pair<String,String>> {

        public SelectAdapter(List<Pair<String,String>> mDataList, int mItemLayoutId) {
            super(mDataList, mItemLayoutId);
        }

        @Override
        public void findBindView(int pos, ClassicRecyclerViewHolder classicRecyclerViewHolder) {
            if (!EmptyTool.isEmpty(mDataList)) {
                TextView id_tv_item_title = classicRecyclerViewHolder.findBindItemView(R.id.id_tv_item_title);
                id_tv_item_title.setText(mDataList.get(pos).second);
            }
        }
    }
}
