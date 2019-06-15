package com.helpshift.campaigns.b;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.helper.ItemTouchHelper.SimpleCallback;
import android.view.View;
import com.helpshift.campaigns.fragments.CampaignListFragment;
import com.helpshift.e.b;
import com.helpshift.e.e;
import com.helpshift.util.v;

public class a extends SimpleCallback {
    private Drawable a;
    private Drawable b;
    private int c = this.b.getIntrinsicWidth();
    private int d = this.b.getIntrinsicWidth();
    private CampaignListFragment e;

    public boolean onMove(RecyclerView recyclerView, ViewHolder viewHolder, ViewHolder viewHolder2) {
        return false;
    }

    public a(Context context, CampaignListFragment campaignListFragment) {
        super(0, 16);
        this.e = campaignListFragment;
        this.a = new ColorDrawable(v.a(context, b.hs__inboxSwipeToDeleteBackgroundColor));
        this.b = ResourcesCompat.getDrawable(context.getResources(), e.hs__cam_delete_icon, null);
        v.a(context, this.b, b.hs__inboxSwipeToDeleteIconColor);
    }

    public void onSwiped(ViewHolder viewHolder, int i) {
        int adapterPosition = viewHolder.getAdapterPosition();
        if (i == 16) {
            this.e.a(adapterPosition, true);
        }
    }

    public void onChildDraw(Canvas canvas, RecyclerView recyclerView, ViewHolder viewHolder, float f, float f2, int i, boolean z) {
        super.onChildDraw(canvas, recyclerView, viewHolder, f, f2, i, z);
        View view = viewHolder.itemView;
        if (f < 0.0f) {
            this.a.setBounds(view.getRight() + ((int) f), view.getTop(), view.getRight(), view.getBottom());
            this.a.draw(canvas);
            int top = view.getTop();
            int right = view.getRight();
            right -= 16;
            top += ((view.getBottom() - top) - this.d) / 2;
            this.b.setBounds(right - this.c, top, right, this.d + top);
            this.b.draw(canvas);
        }
    }

    public int getSwipeDirs(RecyclerView recyclerView, ViewHolder viewHolder) {
        if ((viewHolder instanceof com.helpshift.campaigns.a.a.a) && viewHolder.getAdapterPosition() == this.e.e()) {
            return 0;
        }
        return super.getSwipeDirs(recyclerView, viewHolder);
    }
}
