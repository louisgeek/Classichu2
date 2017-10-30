package com.classichu.classichu2.helper;

import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.classichu.classichu2.logic.login.adapter.AgencyBaseAdapter;
import com.classichu.classichu2.logic.login.bean.AgencyBean;
import com.classichu.dialogview.ui.ClassicDialogFragment;

import java.util.List;

import static android.R.attr.id;
import static com.classichu.dialogview.manager.DialogManager.hideLoadingDialog;

/**
 * Created by louisgeek on 2017/1/19.
 * 解决IllegalStateException: Can not perform this action after onSaveInstanceState
 */

public class DialogFragmentShowHelper {
    /**
     *
     * @param fragmentManager
     * @param dialogFragment
     * @param tag
     */
    public static void show(FragmentManager fragmentManager, DialogFragment dialogFragment, String tag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(dialogFragment,tag);
        transaction.commitAllowingStateLoss();
    }



}
