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

public class ClassicFormInputLayout extends LinearLayout{

    public ClassicFormInputLayout(Context context) {
        this(context, null);
    }

    public ClassicFormInputLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClassicFormInputLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext=context;
        initStartLayout();
        initInputEditText();
        initEndLayout();
        //
        this.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        this.setGravity(Gravity.CENTER_VERTICAL);
        this.setBackgroundResource(R.drawable.selector_classic_item_primary_bg);
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
        mStartLayout.setLayoutParams(ll_lp);
        this.addView(mStartLayout);
    }
    private void initEndLayout() {
        mEndLayout = new LinearLayout(mContext);
        LayoutParams ll_lp = new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        ll_lp.gravity = Gravity.CENTER_VERTICAL;
        mEndLayout.setLayoutParams(ll_lp);
        this.addView(mEndLayout);
    }


    private void initInputEditText() {
        mInputEditText = new EditText(mContext);
        LayoutParams ll_lp = new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        ll_lp.weight = 1.0f;
        ll_lp.gravity = Gravity.CENTER_VERTICAL;
        mInputEditText.setLayoutParams(ll_lp);
        mInputEditText.setLines(1);
        //设置左padding
        mInputEditText.setPadding(mInputEditText.getPaddingLeft() + SizeTool.dp2px(5),
                mInputEditText.getPaddingTop(),
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
    public ClassicFormInputLayout setStartText(CharSequence text) {
        String myTag="tag_"+text;
        View txtView=mStartLayout.findViewWithTag(myTag);
        if (txtView!=null){
            mStartLayout.removeView(txtView);
        }
        TextView startTxt=new TextView(mContext);
        startTxt.setLines(1);
        startTxt.setText(text);
        startTxt.setTag(myTag);
        int padding = SizeTool.dp2px(10);//10dp
        startTxt.setPadding(padding,padding,padding,padding);
        mStartLayout.addView(startTxt);
        return this;
    }
    public ClassicFormInputLayout setStartDrawable(Drawable drawable) {
        String myTag="tag_"+drawable.hashCode();
        View imgView=mStartLayout.findViewWithTag(myTag);
        if (imgView!=null){
            mStartLayout.removeView(imgView);
        }
        ImageView startImg=new ImageView(mContext);
        startImg.setImageDrawable(drawable);
        mStartLayout.addView(startImg);
        return this;
    }
    public ClassicFormInputLayout setEndText(CharSequence text) {
        String myTag="tag_"+text;
        View txtView=mEndLayout.findViewWithTag(myTag);
        if (txtView!=null){
            mEndLayout.removeView(txtView);
        }
        TextView endTxt=new TextView(mContext);
        endTxt.setLines(1);
        endTxt.setText(text);
        endTxt.setTag(myTag);
        int padding = SizeTool.dp2px(10);//10dp
        endTxt.setPadding(padding,padding,padding,padding);
        mEndLayout.addView(endTxt);
        return this;
    }
    public ClassicFormInputLayout setEndDrawable(Drawable drawable) {
        String myTag="tag_"+drawable.hashCode();
        View imgView=mEndLayout.findViewWithTag(myTag);
        if (imgView!=null){
            mEndLayout.removeView(imgView);
        }
        ImageView endImg=new ImageView(mContext);
        endImg.setImageDrawable(drawable);
        mEndLayout.addView(endImg);
        return this;
    }
    public ClassicFormInputLayout setHintText(CharSequence hintText) {
        mInputEditText.setHint(hintText);
        return this;
    }
    //  End 2017/8/21
    ///////////////////////////////////////////////////////////////////////////
}
