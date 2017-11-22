package com.classichu.classichu2.widget.dateselect;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.TimePicker;
import com.classichu.classichu2.R;
import com.classichu.classichu2.tool.DateTimeTool;
import com.classichu.classichu2.tool.DateTool;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by louisgeek on 2016/6/5.
 */
public class DateTimeSelectPopupWindow extends PopupWindow {
    private static final String TAG = "DateSelectPopupWindow";

    private View view;
    private Context mContext;
    private TextView id_btn_date_ok;
    private TextView id_btn_date_cancel;
    private DatePicker id_date_picker;
    private TimePicker id_time_picker;

    private int mYear;
    private int mMonthOfYear;
    private int mDayOfMonth;
    private int mHourOfDay;
    private int mMinute;
    private int mSecond;

    private String mNowDateTextInner;
    private String mStartDateTextInner;
    private String mEndDateTextInner;

    public DateTimeSelectPopupWindow(Context context, String nowDateTextInner, String startDateTextInner, String endDateTextInner) {
        super(context);
        mContext = context;
        mNowDateTextInner = nowDateTextInner;
        mStartDateTextInner = startDateTextInner;
        mEndDateTextInner = endDateTextInner;
        initView();
    }

    private void initView() {
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.layout_popupwindow_date_time_pick, null);

        id_date_picker = (DatePicker) view.findViewById(R.id.id_date_picker);
        id_time_picker = (TimePicker) view.findViewById(R.id.id_time_picker);

        initDateTimePicker();

        id_btn_date_ok = (TextView) view.findViewById(R.id.id_btn_date_ok);
        id_btn_date_cancel = (TextView) view.findViewById(R.id.id_btn_date_cancel);
        id_btn_date_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateTimeSelectPopupWindow.this.dismiss();
                if (mOnDateSelectListener != null) {
                    mOnDateSelectListener.onDateSelect(mYear, mMonthOfYear, mDayOfMonth, mHourOfDay, mMinute, mSecond);
                }
            }
        });
        id_btn_date_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateTimeSelectPopupWindow.this.dismiss();
            }
        });

        //设置PopupWindow的View
        this.setContentView(view);
        //设置PopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置PopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setOutsideTouchable(true);
        this.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//必须设置  ps:xml bg和这个不冲突
        this.setAnimationStyle(R.style.ClassicDateSelectViewAnimation);
        this.setTouchable(true);
        this.setFocusable(true);//设置后  返回按钮先消失popupWindow
        ///####this.update();
    }

    private Calendar calendar;

    private void initDateTimePicker() {
        if (!TextUtils.isEmpty(mNowDateTextInner)) {
            //显示上一次选择数据
            Date date = DateTimeTool.parseDateStr2Date(mNowDateTextInner);
            calendar = DateTool.parseDate2Calendar(date);
        } else {
//            calendar = Calendar.getInstance();//初始化时间
            calendar = DateTool.parseStr2Calendar(DateTool.getChinaDateTime(), DateTool.FORMAT_DATE_TIME);
        }
        mYear = calendar.get(Calendar.YEAR);
        mMonthOfYear = calendar.get(Calendar.MONTH);
        mDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        mHourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        mMinute = calendar.get(Calendar.MINUTE);

        DatePicker.OnDateChangedListener dcl = new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                mYear = year;
                mMonthOfYear = monthOfYear;
                mDayOfMonth = dayOfMonth;
            }
        };
        //
        id_date_picker.init(mYear, mMonthOfYear, mDayOfMonth, dcl);
        //设置最小日期
        if (!TextUtils.isEmpty(mStartDateTextInner)) {
            if (DateTool.timeCompare(mNowDateTextInner, mStartDateTextInner) > 0) {
                Date date_s = DateTimeTool.parseDateStr2Date(mStartDateTextInner);
                Calendar calendar_s = DateTool.parseDate2Calendar(date_s);
                long time_s = calendar_s.getTimeInMillis();
                id_date_picker.setMinDate(time_s);
            }
        }
        //设置最大日期
        if (!TextUtils.isEmpty(mEndDateTextInner)) {
            if (DateTool.timeCompare(mNowDateTextInner, mEndDateTextInner) <= 0) {
                Date date_e = DateTimeTool.parseDateStr2Date(mEndDateTextInner);
                Calendar calendar_e = DateTool.parseDate2Calendar(date_e);
                long time_e = calendar_e.getTimeInMillis();
                id_date_picker.setMaxDate(time_e);
            }
        }

        if (mHourOfDay == 0 && mMinute == 0) {
            id_time_picker.setVisibility(View.GONE);
        } else {
            id_time_picker.setVisibility(View.VISIBLE);
            //设置时间
            id_time_picker.setIs24HourView(true);
            //
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)

            {
                id_time_picker.setHour(mHourOfDay);
                id_time_picker.setMinute(mMinute);
            } else

            {
                id_time_picker.setCurrentHour(mHourOfDay);
                id_time_picker.setCurrentMinute(mMinute);
            }
            //第一次setOnTimeChangedListener会回调一次
            id_time_picker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener()

            {
                @Override
                public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                    mHourOfDay = hourOfDay;
                    mMinute = minute;
                    mSecond = calendar.get(Calendar.SECOND);
                }
            });

        }
    }


    public interface OnDateSelectListener {
        void onDateSelect(int year, int monthOfYear, int dayOfMonth, int hourOfDay, int minute, int second);

    }

    public void setOnDateSelectListener(OnDateSelectListener onDateSelectListener) {
        mOnDateSelectListener = onDateSelectListener;
    }

    private OnDateSelectListener mOnDateSelectListener;

}
