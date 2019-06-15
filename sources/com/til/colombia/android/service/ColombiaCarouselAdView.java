package com.til.colombia.android.service;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.til.colombia.android.R;
import com.til.colombia.android.internal.a.h;
import com.til.colombia.android.utils.b;
import java.util.ArrayList;
import java.util.List;

public class ColombiaCarouselAdView extends FrameLayout {
    private List<Item> adItems;
    private ImageView imgView;
    private ItemResponse itemResponse;
    private LinearLayoutManager linearLayoutManager;
    private Context mContext;
    private RecyclerView recyclerView;
    OnScrollListener scrollChangeListener = new al(this);
    private TextView txtView;

    public ColombiaCarouselAdView(Context context) {
        super(context);
        this.mContext = context;
    }

    public ColombiaCarouselAdView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
    }

    public ColombiaCarouselAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
    }

    @TargetApi(21)
    public ColombiaCarouselAdView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mContext = context;
    }

    public void setAdResponse(ItemResponse itemResponse) {
        this.itemResponse = itemResponse;
        this.adItems = new ArrayList();
        if (itemResponse.getPaidItems() != null) {
            this.adItems.addAll(itemResponse.getPaidItems());
        }
        if (itemResponse.getOrganicItems() != null) {
            this.adItems.addAll(itemResponse.getOrganicItems());
        }
    }

    public void commit() {
        if (this.adItems != null && !this.adItems.isEmpty()) {
            View inflate = inflate(this.mContext, R.layout.carousel_ad_layout, null);
            addView(inflate);
            initView(inflate);
            populatRecyclerView();
        }
    }

    private void initView(View view) {
        this.linearLayoutManager = new LinearLayoutManager(this.mContext, 0, false);
        this.recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        this.recyclerView.setHasFixedSize(true);
        this.recyclerView.setLayoutManager(this.linearLayoutManager);
        this.txtView = (TextView) view.findViewById(R.id.c_title);
        this.imgView = (ImageView) view.findViewById(R.id.logo);
        this.txtView.setText(this.itemResponse.getResponseTitle());
        if (h.a(this.itemResponse.getResponseImgUrl())) {
            this.imgView.setVisibility(8);
        } else {
            this.imgView.setVisibility(0);
            Bitmap a = com.til.colombia.android.commons.a.h.a(this.itemResponse.getResponseImgUrl());
            if (a != null) {
                this.imgView.setImageBitmap(a);
            } else {
                new b().a(this.imgView, this.itemResponse.getResponseImgUrl());
            }
        }
        bi.a().a(this.itemResponse, (Item) this.adItems.get(0), this);
        this.itemResponse.putCarouselView(this);
    }

    private void populatRecyclerView() {
        this.recyclerView.addOnScrollListener(this.scrollChangeListener);
        ab abVar = new ab(this.mContext, this.adItems);
        this.recyclerView.setAdapter(abVar);
        abVar.notifyDataSetChanged();
    }
}
