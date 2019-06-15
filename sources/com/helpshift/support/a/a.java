package com.helpshift.support.a;

import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.helpshift.e.h;
import com.helpshift.support.f.g;
import java.util.List;

public class a extends Adapter<a> {
    private List<g> a;
    private OnClickListener b;

    protected static class a extends ViewHolder {
        TextView a;

        public a(TextView textView) {
            super(textView);
            this.a = textView;
        }
    }

    public a(List<g> list, OnClickListener onClickListener) {
        this.a = list;
        this.b = onClickListener;
    }

    /* renamed from: a */
    public a onCreateViewHolder(ViewGroup viewGroup, int i) {
        TextView textView = (TextView) LayoutInflater.from(viewGroup.getContext()).inflate(h.hs__simple_list_item_1, viewGroup, false);
        textView.setOnClickListener(this.b);
        return new a(textView);
    }

    /* renamed from: a */
    public void onBindViewHolder(a aVar, int i) {
        CharSequence string;
        g gVar = (g) this.a.get(i);
        if (gVar.a() != 0) {
            string = aVar.a.getResources().getString(gVar.a());
        } else {
            string = gVar.b();
        }
        aVar.a.setText(string);
        aVar.a.setTag(Integer.valueOf(i));
    }

    public int getItemCount() {
        return this.a.size();
    }
}
