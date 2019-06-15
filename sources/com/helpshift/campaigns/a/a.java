package com.helpshift.campaigns.a;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PorterDuff.Mode;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.helpshift.campaigns.k.b;
import com.helpshift.e;
import com.helpshift.e.f;
import com.helpshift.e.h;
import com.helpshift.e.k;
import com.helpshift.util.v;
import com.helpshift.util.x;
import com.payu.custombrowser.util.CBConstant;
import java.util.HashMap;

public class a extends Adapter<a> {
    private Context a;
    private OnClickListener b;
    private int c = -1;
    private b d;

    public static class a extends ViewHolder implements OnCreateContextMenuListener {
        TextView a;
        TextView b;
        TextView c;
        ImageView d;
        private b e;

        public a(RelativeLayout relativeLayout, b bVar) {
            super(relativeLayout);
            this.a = (TextView) relativeLayout.findViewById(f.campaign_title);
            this.b = (TextView) relativeLayout.findViewById(f.campaign_body);
            this.c = (TextView) relativeLayout.findViewById(f.campaign_time);
            this.d = (ImageView) relativeLayout.findViewById(f.campaign_icon);
            relativeLayout.setOnCreateContextMenuListener(this);
            this.e = bVar;
        }

        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenuInfo contextMenuInfo) {
            contextMenu.add(0, f.delete_campaign, 0, k.hs__cam_delete);
            if (!this.e.e(getAdapterPosition()) && !this.e.f(getAdapterPosition())) {
                contextMenu.add(0, f.mark_campaign_as_read, 0, k.hs__cam_mark_as_read);
            }
        }
    }

    public a(b bVar, OnClickListener onClickListener) {
        this.d = bVar;
        this.b = onClickListener;
    }

    /* renamed from: a */
    public a onCreateViewHolder(ViewGroup viewGroup, int i) {
        this.a = viewGroup.getContext();
        RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(viewGroup.getContext()).inflate(h.hs__campaign_recycler_view_item, viewGroup, false);
        relativeLayout.setOnClickListener(this.b);
        return new a(relativeLayout, this.d);
    }

    /* renamed from: a */
    public void onBindViewHolder(final a aVar, int i) {
        aVar.a.setText(this.d.a(i));
        aVar.b.setText(this.d.d(i));
        HashMap c = this.d.c(i);
        boolean containsKey = c.containsKey(CBConstant.DEFAULT_VALUE);
        aVar.d.setImageBitmap((Bitmap) c.get("bitmap"));
        if (containsKey) {
            aVar.d.setColorFilter(v.a(this.a, e.b.hs__inboxIconBackgroundColor), Mode.SRC_OUT);
        } else {
            aVar.d.setColorFilter(v.a(this.a, e.b.hs__inboxIconBackgroundColor), Mode.DST_IN);
        }
        aVar.c.setText(x.a(this.d.g(i)));
        if (this.d.e(i) || this.d.f(i)) {
            aVar.a.setTextColor(v.a(this.a, e.b.hs__inboxTitleTextColor));
            aVar.a.setTypeface(aVar.a.getTypeface(), 0);
            aVar.c.setTextColor(v.a(this.a, e.b.hs__inboxTimeStampTextColor));
            aVar.c.setTypeface(aVar.c.getTypeface(), 0);
        } else {
            aVar.a.setTextColor(v.a(this.a, e.b.hs__inboxTitleUnreadTextColor));
            aVar.a.setTypeface(aVar.a.getTypeface(), 1);
            aVar.c.setTextColor(v.a(this.a, e.b.hs__inboxTimeStampUnreadTextColor));
            aVar.c.setTypeface(aVar.c.getTypeface(), 1);
        }
        aVar.itemView.setOnLongClickListener(new OnLongClickListener() {
            public boolean onLongClick(View view) {
                a.this.b(aVar.getAdapterPosition());
                return false;
            }
        });
        aVar.itemView.setTag(this.d.b(i));
    }

    /* renamed from: a */
    public void onViewRecycled(a aVar) {
        aVar.itemView.setOnLongClickListener(null);
        super.onViewRecycled(aVar);
    }

    public void a(int i, boolean z) {
        this.d.a(i, z);
        notifyItemRemoved(i);
    }

    public void a(int i) {
        this.d.h(i);
        notifyItemChanged(i);
    }

    public int getItemCount() {
        return this.d.e();
    }

    public int a() {
        return this.c;
    }

    public void b(int i) {
        this.c = i;
    }
}
