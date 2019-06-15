package com.gaana.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.gaana.R;

public class MoreInfoListing extends LinearLayout {
    View _itemView;
    Context _mContext;
    RecyclerView _recyclerView;

    public MoreInfoListing(Context context) {
        super(context);
        init(context);
    }

    public MoreInfoListing(Context context, String str) {
        super(context);
        init(context);
        setHeader(str);
    }

    private void init(Context context) {
        this._mContext = context;
        this._itemView = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.more_info_listing_layout, null);
        this._itemView.setLayoutParams(new LayoutParams(-1, -2));
        setLayoutParams(new LayoutParams(-1, -2));
        addView(this._itemView);
        this._recyclerView = (RecyclerView) this._itemView.findViewById(R.id.recyclerView);
    }

    public void setHeader(String str) {
        ((TextView) this._itemView.findViewById(R.id.header)).setText(str);
    }

    public RecyclerView getRecyclerView() {
        this._recyclerView.setLayoutManager(new LinearLayoutManager(this._mContext, 0, false));
        return this._recyclerView;
    }
}
