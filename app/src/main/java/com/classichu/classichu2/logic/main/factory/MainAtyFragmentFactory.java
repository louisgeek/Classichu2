package com.classichu.classichu2.logic.main.factory;

import android.util.SparseArray;

import com.classichu.classichu2.base.BaseFragment;
import com.classichu.classichu2.logic.main.MainFragment;

/**
 * Created by Classichu on 2017-6-27.
 */

public class MainAtyFragmentFactory {
    private static SparseArray<BaseFragment>  fragments = new SparseArray<>();
    public static BaseFragment create(int pos) {
        BaseFragment baseFragment=fragments.get(pos);
        if (baseFragment == null) {//没有在集合中取到再进入实例化过程
            switch (pos) {
                case 0:
                    baseFragment= MainFragment.newInstance("病人列表", "");
                    break;
            /*    case 1:
                    baseFragment=MainFragment.newInstance("联系人", "", 1);
                    break;
                case 2:
                    baseFragment=BFragment.newInstance("动态", "", 2);
                    break;
                case 3:
                    baseFragment=MainFragment.newInstance("我的", "", 3);
                    break;*/
                default:
                    break;
            }
            fragments.put(pos,baseFragment);
        }
        return baseFragment;
    }
}
