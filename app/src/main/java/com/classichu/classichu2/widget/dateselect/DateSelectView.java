package com.classichu.classichu2.widget.dateselect;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;

import com.classichu.classichu2.R;
import com.classichu.classichu2.helper.VectorOrImageResHelper;
import com.classichu.classichu2.tool.DateTool;
import com.classichu.classichu2.tool.KeyBoardTool;
import com.classichu.classichu2.tool.SizeTool;


/**
 * Created by louisgeek on 2016/6/5.
 */
public class DateSelectView extends AppCompatTextView {
    private static final String TAG = "DateSelectView";
    private Context mContext;
    private String mShowDateText;
    private String mStartDateText;
    private String mEndDateText;

    public DateSelectView(Context context) {
        this(context, null);
    }

    public DateSelectView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DateSelectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        initDate();//初始化

        int padding = SizeTool.dp2px(10);
        this.setPadding(this.getPaddingLeft() + padding, this.getPaddingTop() + padding, this.getPaddingRight() + padding, this.getPaddingBottom() + padding);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
                //
                KeyBoardTool.hideKeyboard(v);

                DateTimeSelectPopupWindow myPopupwindow = new DateTimeSelectPopupWindow(mContext, mShowDateText, mStartDateText, mEndDateText);
                myPopupwindow.showAtLocation(v, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                myPopupwindow.setOnDateSelectListener(new DateTimeSelectPopupWindow.OnDateSelectListener() {
                    @Override
                    public void onDateSelect(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minute, int second) {
                        if (hourOfDay == 0 && minute == 0 && second == 0) {
                            mShowDateText = DateTool.getChinaDateFromCalendar(year, monthOfYear, dayOfMonth);
                        } else {
                            mShowDateText = DateTool.getChinaDateTimeFromCalendar(year, monthOfYear, dayOfMonth, hourOfDay, minute, second);
                        }
                        ((DateSelectView) v).setText(mShowDateText);
                    }
                });
            }
        });
        Drawable drawableLeft = getCompoundDrawables()[0] != null ? getCompoundDrawables()[0] : VectorOrImageResHelper.getDrawable(R.drawable.ic_date_range_black_24dp);
        Drawable drawableTop = getCompoundDrawables()[1];
        Drawable drawableRight = getCompoundDrawables()[2] != null ? getCompoundDrawables()[0] : VectorOrImageResHelper.getDrawable(R.drawable.ic_expand_more_black_24dp);
        Drawable drawableBottom = getCompoundDrawables()[3];
        drawableLeft.setColorFilter(ContextCompat.getColor(mContext, R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        drawableRight.setColorFilter(ContextCompat.getColor(mContext, R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
        this.setCompoundDrawablePadding(SizeTool.dp2px(10));
        this.setCompoundDrawablesWithIntrinsicBounds(drawableLeft, drawableTop, drawableRight, drawableBottom);
        if (this.getBackground() == null) {
            this.setBackgroundResource(R.drawable.selector_classic_text_item_bg);
        }
        if (this.getGravity() == (Gravity.TOP | Gravity.START)) {
            this.setGravity(Gravity.CENTER_VERTICAL);
        }
    }

    /**
     * @param showDateText "yyyy-MM-dd"
     */
    public void setupDateText(String showDateText) {
        setupDateText(showDateText, null, null, null);
    }


    /**
     * @param showDateText "yyyy-MM-dd"
     */
    public void setupDateText(String showDateText, CharSequence hint) {
        setupDateText(showDateText, null, null, hint);
    }

    /**
     * @param showDateText "yyyy-MM-dd"
     */
    public void setupDateText(String showDateText, String startDateText, String endDateText) {
        setupDateText(showDateText, startDateText, endDateText, null);
    }

    /**
     * @param showDateText  "yyyy-MM-dd"
     * @param startDateText "yyyy-MM-dd"  or  null  or ""
     * @param endDateText   "yyyy-MM-dd"  or  null  or ""
     */
    public void setupDateText(String showDateText, String startDateText, String endDateText, CharSequence hint) {
        Log.i(TAG, "setupDateText: nowDateText:" + showDateText);
        Log.i(TAG, "setupDateText: startDateText:" + startDateText);
        Log.i(TAG, "setupDateText: endDateText:" + endDateText);
        //
        this.mShowDateText = showDateText;
        this.mStartDateText = startDateText;
        this.mEndDateText = endDateText;
        this.setText(mShowDateText);
        this.setHint(TextUtils.isEmpty(hint) ? "请选择时间" : hint);
    }

    private void initDate() {
        setupDateText(null);
    }

    @Deprecated
    public CharSequence getText() {
        return super.getText();
    }

    public String getNowSelectData() {
        String nowData = "";
        if (this.getText() != null) {
            nowData = this.getText().toString();
        }
        return nowData;
    }

    /**
     * 未设置的返回当前时间
     *
     * @return
     */
    public String getNowSelectDataFixedNowData() {
        return "".equals(getNowSelectData()) ? DateTool.getChinaDate() : getNowSelectData();
    }
}
