package com.example.valdeslab.learningapp.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.valdeslab.learningapp.R;


public class ItemViewHolder extends RecyclerView.ViewHolder {

    public LinearLayout regularLayout;
    public LinearLayout swipeLayout;
    public TextView listItem;
    public TextView undo;

    public ItemViewHolder(View view) {
        super(view);

        // regular_row_item.xml
        regularLayout = (LinearLayout) view.findViewById(R.id.regularLayout);
        // regular_row_item.xml
        listItem = (TextView) view.findViewById(R.id.list_item);
        // swipe_row_item.xml
        swipeLayout = (LinearLayout) view.findViewById(R.id.swipeLayout);
        // swipe_row_item.xml
        undo = (TextView) view.findViewById(R.id.undo);

    }
}
