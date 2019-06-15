package com.til.colombia.android.service;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.til.colombia.android.R;
import com.til.colombia.android.internal.a.h;
import com.til.colombia.android.utils.b;
import java.util.List;

public final class ab extends Adapter<ac> implements OnClickListener {
    private List<Item> a;
    private Context b;

    public final /* synthetic */ void onBindViewHolder(ViewHolder viewHolder, int i) {
        ac acVar = (ac) viewHolder;
        Item item = (Item) this.a.get(i);
        if (!h.a(item.getTitle())) {
            acVar.b.setText(item.getTitle());
        }
        Bitmap a = com.til.colombia.android.commons.a.h.a(item.getImageUrl());
        if (a != null) {
            acVar.c.setImageBitmap(a);
        } else {
            new b().a(acVar.c, item.getImageUrl());
        }
        if (!h.a(item.getBrandText())) {
            acVar.d.setText(item.getBrandText());
        }
        if (h.a(item.getCtaText())) {
            acVar.e.setVisibility(8);
        } else {
            acVar.e.setText(item.getCtaText());
            acVar.e.setVisibility(0);
            acVar.e.setTag(item);
            acVar.e.setOnClickListener(this);
        }
        acVar.a.setTag(item);
        acVar.a.setOnClickListener(this);
    }

    public ab(Context context, List<Item> list) {
        this.b = context;
        this.a = list;
    }

    public final int getItemCount() {
        return this.a != null ? this.a.size() : 0;
    }

    private void a(ac acVar, int i) {
        Item item = (Item) this.a.get(i);
        if (!h.a(item.getTitle())) {
            acVar.b.setText(item.getTitle());
        }
        Bitmap a = com.til.colombia.android.commons.a.h.a(item.getImageUrl());
        if (a != null) {
            acVar.c.setImageBitmap(a);
        } else {
            new b().a(acVar.c, item.getImageUrl());
        }
        if (!h.a(item.getBrandText())) {
            acVar.d.setText(item.getBrandText());
        }
        if (h.a(item.getCtaText())) {
            acVar.e.setVisibility(8);
        } else {
            acVar.e.setText(item.getCtaText());
            acVar.e.setVisibility(0);
            acVar.e.setTag(item);
            acVar.e.setOnClickListener(this);
        }
        acVar.a.setTag(item);
        acVar.a.setOnClickListener(this);
    }

    private static ac a(ViewGroup viewGroup) {
        return new ac((ViewGroup) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.carousel_item_layout, viewGroup, false));
    }

    public final void onClick(View view) {
        NativeItem nativeItem = (NativeItem) view.getTag();
        bi.a();
        bi.a(nativeItem, false);
    }

    public final /* synthetic */ ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ac((ViewGroup) LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.carousel_item_layout, viewGroup, false));
    }
}
