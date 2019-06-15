package com.helpshift.support.a;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.helpshift.e.h;
import com.helpshift.support.Faq;
import java.util.List;

public class b extends Adapter<a> {
    private List<Faq> a;
    private OnClickListener b;

    protected static class a extends ViewHolder {
        TextView a;

        public a(TextView textView) {
            super(textView);
            this.a = textView;
        }
    }

    public b(List<Faq> list, OnClickListener onClickListener) {
        this.a = list;
        this.b = onClickListener;
    }

    /* renamed from: a */
    public a onCreateViewHolder(ViewGroup viewGroup, int i) {
        TextView textView = (TextView) LayoutInflater.from(viewGroup.getContext()).inflate(h.hs_simple_recycler_view_item, viewGroup, false);
        textView.setOnClickListener(this.b);
        return new a(textView);
    }

    /* renamed from: a */
    public void onBindViewHolder(a aVar, int i) {
        Faq faq = (Faq) this.a.get(i);
        aVar.a.setText(faq.b);
        aVar.a.setTag(faq.c);
    }

    public int getItemCount() {
        return this.a.size();
    }
}
