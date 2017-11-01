package com.classichu.classichu2.logic.patient.factory;

import android.util.SparseArray;

import com.classichu.classichu2.R;
import com.classichu.classichu2.base.BaseFragment;
import com.classichu.classichu2.logic.patient.PatientInfoFragment;
import com.classichu.classichu2.logic.patient.module.lifesign.VitalSignFragment;

/**
 * Created by Classichu on 2017-6-27.
 */

public class PatientAtyFragmentFactory {
    private static SparseArray<BaseFragment> fragments = new SparseArray<>();
    public static BaseFragment create(int itemId) {
        BaseFragment baseFragment=fragments.get(itemId);
        if (baseFragment == null) {//没有在集合中取到再进入实例化过程
            switch (itemId) {
                case R.id.id_menu_item_patient_info:
                    baseFragment= PatientInfoFragment.newInstance("","");
                    break;
                case R.id.id_menu_item_vital_sign:
                    baseFragment= VitalSignFragment.newInstance("","");
                    break;
                default:
                    break;
            }
            fragments.put(itemId,baseFragment);
        }
        return baseFragment;
    }
}
