package com.collapsible_header;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import com.fragments.BaseGaanaFragment;
import com.gaana.adapter.ListAdapter;

public class e extends ListAdapter {
    private View a;
    private View b;
    private boolean c;

    public static class a extends ViewHolder {
        public a(View view) {
            super(view);
        }
    }

    public static class b extends ViewHolder {
        public b(View view) {
            super(view);
        }
    }

    public e(Context context, View view, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        this.a = view;
    }

    public void setFooterView(View view) {
        this.b = view;
    }

    public int getItemCount() {
        if (this.a == null) {
            return super.getItemCount();
        }
        if (this.b != null) {
            return super.getItemCount() + 2;
        }
        if (this.c) {
            return super.getItemCount() + 2;
        }
        return super.getItemCount() + 1;
    }

    public int getItemViewType(int i) {
        if (this.c && i == 1) {
            return 321;
        }
        if (this.b != null && i == super.getItemCount() + 1) {
            return 101;
        }
        if (i == 0) {
            i = 0;
        } else {
            i = super.getItemViewType(i);
        }
        return i;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (i == 0) {
            return new b(this.a);
        }
        if (this.b == null || i != 101) {
            return super.onCreateViewHolder(viewGroup, i);
        }
        return new a(this.b);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        if (this.a != null) {
            int i2 = 1;
            if (this.c && i == 1) {
                super.onBindViewHolder(viewHolder, i);
                return;
            } else if (i != 0 && (this.b == null || !(viewHolder instanceof a))) {
                if (this.c) {
                    i2 = 2;
                }
                super.onBindViewHolder(viewHolder, i - i2);
            } else {
                return;
            }
        }
        super.onBindViewHolder(viewHolder, i);
    }

    public void setSortItem(boolean z) {
        this.c = z;
        super.setSortItem(z);
    }
}
