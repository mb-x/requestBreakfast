package com.devgrafix.requestbreakfast.RecyclerView;

import android.view.View;

/**
 * Created by PC-MA13 on 11/09/2016.
 */
public interface ClickListener {
    void onClick(View view, int position);

    void onLongClick(View view, int position);
}
