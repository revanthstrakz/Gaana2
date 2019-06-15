package com.til.colombia.android.service;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.til.colombia.android.R;

public final class ac extends ViewHolder {
    public View a;
    public TextView b;
    public ImageView c;
    public TextView d;
    public Button e;

    public ac(View view) {
        super(view);
        this.a = view;
        this.b = (TextView) view.findViewById(R.id.citem_title);
        this.c = (ImageView) view.findViewById(R.id.citem_img);
        this.d = (TextView) view.findViewById(R.id.citem_brand);
        this.e = (Button) view.findViewById(R.id.citem_cta);
    }
}
