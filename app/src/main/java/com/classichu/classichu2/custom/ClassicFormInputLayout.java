package com.classichu.classichu2.custom;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.classichu.classichu2.R;
import com.classichu.classichu2.tool.SizeTool;


/**
 * Created by louisgeek on 2017/8/21.
 */

public class ClassicFormInputLayout extends LinearLayout {

    public ClassicFormInputLayout(Context context) {
        this(context, null);
    }

    public ClassicFormInputLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClassicFormInputLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        //
        LinearLayout.LayoutParams ll_lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        ll_lp.gravity = Gravity.CENTER_VERTICAL;
        this.setLayoutParams(ll_lp);
        this.setGravity(Gravity.CENTER_VERTICAL);
        this.setBackgroundResource(R.drawable.selector_classic_item_primary_bg);
        //
        initStartLayout();
        initInputEditText();
        initEndLayout();
    }

    private LinearLayout mStartLayout;
    private LinearLayout mEndLayout;
    private EditText mInputEditText;
    private Context mContext;

    private void initStartLayout() {
        mStartLayout = new LinearLayout(mContext);
        LayoutParams ll_lp = new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        ll_lp.gravity = Gravity.CENTER_VERTICAL;
        mStartLayout.setGravity(Gravity.CENTER_VERTICAL);
        mStartLayout.setLayoutParams(ll_lp);
        this.addView(mStartLayout);
    }

    private void initEndLayout() {
        mEndLayout = new LinearLayout(mContext);
        LayoutParams ll_lp = new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        ll_lp.gravity = Gravity.CENTER_VERTICAL;
        mEndLayout.setGravity(Gravity.CENTER_VERTICAL);
        mEndLayout.setLayoutParams(ll_lp);
        this.addView(mEndLayout);
    }


    private void initInputEditText() {
        mInputEditText = new EditText(mContext);
        LayoutParams ll_lp = new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        ll_lp.weight = 1.0f;
        //android 5.0 API 21上的EditText文字偏上 定义不同的dimen文件 兼容处理这个问题
        int api21_fixed_edittext_spacing= mContext.getResources().getDimensionPixelSize(R.dimen.api21_fixed_edittext_spacing);
        ll_lp.gravity = Gravity.CENTER_VERTICAL;
        mInputEditText.setGravity(Gravity.CENTER_VERTICAL);
        mInputEditText.setLayoutParams(ll_lp);
        mInputEditText.setLines(1);
        //设置左padding
        mInputEditText.setPadding(mInputEditText.getPaddingLeft() + SizeTool.dp2px(5),
                mInputEditText.getPaddingTop()+api21_fixed_edittext_spacing,
                mInputEditText.getPaddingRight(),
                mInputEditText.getPaddingBottom());
        mInputEditText.setHintTextColor(Color.parseColor("#42000000"));
        ViewCompat.setBackground(mInputEditText, null);
        mInputEditText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                // 会影响内部view 的 setSelected 状态
                ClassicFormInputLayout.this.setSelected(hasFocus);
                // ClassicInputLayout.this.setActivated(hasFocus);
            }
        });
        this.addView(mInputEditText);
    }


    ///////////////////////////////////////////////////////////////////////////
    //  Start 2017/8/21
    public ClassicFormInputLayout addStartText(CharSequence text) {
        return addStartText(text, null);
    }

    public ClassicFormInputLayout addStartText(CharSequence text, View.OnClickListener listener) {
        TextView startTxt = new TextView(mContext);
        startTxt.setLines(1);
        startTxt.setText(text);
        int padding = SizeTool.dp2px(10);//10dp
        startTxt.setPadding(padding, padding, padding, padding);
        startTxt.setOnClickListener(listener);
        mStartLayout.addView(startTxt);
        return this;
    }

    public ClassicFormInputLayout addStartDrawable(Drawable drawable) {
        return addStartDrawable(drawable, null);
    }

    public ClassicFormInputLayout addStartDrawable(Drawable drawable, View.OnClickListener listener) {
        ImageView startImg = new ImageView(mContext);
        startImg.setImageDrawable(drawable);
        startImg.setOnClickListener(listener);
        mStartLayout.addView(startImg);
        return this;
    }

    public ClassicFormInputLayout addEndText(CharSequence text) {
        return addEndText(text, null);
    }

    public ClassicFormInputLayout addEndText(CharSequence text, View.OnClickListener listener) {
        TextView endTxt = new TextView(mContext);
        endTxt.setLines(1);
        endTxt.setText(text);
        int padding = SizeTool.dp2px(10);//10dp
        endTxt.setPadding(padding, padding, padding, padding);
        endTxt.setOnClickListener(listener);
        mEndLayout.addView(endTxt);
        return this;
    }

    public ClassicFormInputLayout addEndDrawable(Drawable drawable) {
        return addEndDrawable(drawable, null);
    }

    public ClassicFormInputLayout addEndDrawable(Drawable drawable, View.OnClickListener listener) {
        ImageView endImg = new ImageView(mContext);
        endImg.setImageDrawable(drawable);
        endImg.setOnClickListener(listener);
        mEndLayout.addView(endImg);
        return this;
    }

    public ClassicFormInputLayout setHintText(CharSequence hintText) {
        mInputEditText.setHint(hintText);
        return this;
    }

    public EditText getInputEditText() {
        return mInputEditText;
    }

    public String getInputText() {
        return mInputEditText.getText().toString();
    }
    //  End 2017/8/21
    ///////////////////////////////////////////////////////////////////////////
}
