package com.inmobi.ads;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import java.lang.ref.WeakReference;

public class NativeRecyclerViewAdapter extends Adapter<a> implements ax {
    private static final String a = "NativeRecyclerViewAdapter";
    private final ao b;
    private au c;
    private SparseArray<WeakReference<View>> d;
    private boolean e = false;

    class a extends ViewHolder {
        private ViewGroup b;

        a(View view) {
            super(view);
            this.b = (ViewGroup) view;
        }
    }

    NativeRecyclerViewAdapter(@NonNull ao aoVar, @NonNull au auVar) {
        this.b = aoVar;
        this.c = auVar;
        this.d = new SparseArray();
    }

    @NonNull
    public a onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new a(new FrameLayout(this.c.a()));
    }

    /* JADX WARNING: Missing block: B:3:0x0016, code skipped:
            if (r1 == null) goto L_0x0018;
     */
    public void onBindViewHolder(@android.support.annotation.NonNull com.inmobi.ads.NativeRecyclerViewAdapter.a r5, int r6) {
        /*
        r4 = this;
        r0 = r4.b;
        r0 = r0.a(r6);
        r1 = r4.d;
        r1 = r1.get(r6);
        r1 = (java.lang.ref.WeakReference) r1;
        if (r1 == 0) goto L_0x0018;
    L_0x0010:
        r1 = r1.get();
        r1 = (android.view.View) r1;
        if (r1 != 0) goto L_0x0020;
    L_0x0018:
        r1 = r5.b;
        r1 = r4.buildScrollableView(r6, r1, r0);
    L_0x0020:
        if (r1 == 0) goto L_0x0045;
    L_0x0022:
        r0 = r4.getItemCount();
        r0 = r0 + -1;
        if (r6 == r0) goto L_0x0034;
    L_0x002a:
        r0 = r5.b;
        r2 = 16;
        r3 = 0;
        r0.setPadding(r3, r3, r2, r3);
    L_0x0034:
        r5 = r5.b;
        r5.addView(r1);
        r5 = r4.d;
        r0 = new java.lang.ref.WeakReference;
        r0.<init>(r1);
        r5.put(r6, r0);
    L_0x0045:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.inmobi.ads.NativeRecyclerViewAdapter.onBindViewHolder(com.inmobi.ads.NativeRecyclerViewAdapter$a, int):void");
    }

    public void onViewRecycled(@NonNull a aVar) {
        aVar.b.removeAllViews();
        super.onViewRecycled(aVar);
    }

    public ViewGroup buildScrollableView(int i, @NonNull ViewGroup viewGroup, @NonNull am amVar) {
        ViewGroup a = this.c.a(viewGroup, amVar);
        this.c.b(a, amVar);
        a.setLayoutParams(NativeViewFactory.a((ak) amVar, viewGroup));
        return a;
    }

    public int getItemCount() {
        return this.b.b();
    }

    public void destroy() {
        this.e = true;
    }
}
