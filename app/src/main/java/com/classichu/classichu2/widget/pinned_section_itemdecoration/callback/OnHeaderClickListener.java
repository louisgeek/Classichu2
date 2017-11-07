package com.classichu.classichu2.widget.pinned_section_itemdecoration.callback;

import android.view.View;

public interface OnHeaderClickListener {

    void onHeaderClick(View view, int id, int position);

    void onHeaderLongClick(View view, int id, int position);

    void onHeaderDoubleClick(View view, int id, int position);

}
