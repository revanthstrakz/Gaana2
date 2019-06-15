package com.managers;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.os.Environment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import com.gaana.view.AutoPlayVideoView.AutoVideoViewHolder;
import com.utilities.d;

public class c {
    boolean a;
    private Activity b;
    private boolean c = false;
    private boolean d = true;
    private boolean e = false;
    private float f = 100.0f;
    private String g;
    private RecyclerView h;

    public c() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Environment.getExternalStorageDirectory());
        stringBuilder.append("/Video");
        this.g = stringBuilder.toString();
        this.a = false;
    }

    public void a(Context context, RecyclerView recyclerView, boolean z, boolean z2, boolean z3, float f) {
        this.b = (Activity) context;
        this.c = z;
        this.d = z2;
        this.e = z3;
        this.f = f;
        this.h = recyclerView;
    }

    public void a(int i) {
        if (d.g() && i == 0) {
            i = ((LinearLayoutManager) this.h.getLayoutManager()).findFirstVisibleItemPosition();
            int findLastVisibleItemPosition = ((LinearLayoutManager) this.h.getLayoutManager()).findLastVisibleItemPosition();
            if (i >= 0) {
                Rect rect = new Rect();
                this.h.getGlobalVisibleRect(rect);
                while (i <= findLastVisibleItemPosition) {
                    ViewHolder findViewHolderForAdapterPosition = this.h.findViewHolderForAdapterPosition(i);
                    try {
                        if (findViewHolderForAdapterPosition instanceof AutoVideoViewHolder) {
                            AutoVideoViewHolder autoVideoViewHolder = (AutoVideoViewHolder) findViewHolderForAdapterPosition;
                            if (i >= 0 && autoVideoViewHolder != null && (autoVideoViewHolder.getVideoUrl().endsWith(".mp4") || !this.e)) {
                                int[] iArr = new int[2];
                                autoVideoViewHolder.getAah_vi().getLocationOnScreen(iArr);
                                Rect rect2 = new Rect(iArr[0], iArr[1], iArr[0] + autoVideoViewHolder.getAah_vi().getWidth(), iArr[1] + autoVideoViewHolder.getAah_vi().getHeight());
                                if (((((float) Math.max(0, Math.min(rect2.right, rect.right) - Math.max(rect2.left, rect.left))) * ((float) Math.max(0, Math.min(rect2.bottom, rect.bottom) - Math.max(rect2.top, rect.top)))) / ((float) ((rect2.right - rect2.left) * (rect2.bottom - rect2.top)))) * 100.0f < this.f) {
                                    ((AutoVideoViewHolder) findViewHolderForAdapterPosition).pauseVideo();
                                } else if (!((AutoVideoViewHolder) findViewHolderForAdapterPosition).isPaused()) {
                                    ((AutoVideoViewHolder) findViewHolderForAdapterPosition).playVideo();
                                }
                            }
                        }
                    } catch (Exception unused) {
                    }
                    i++;
                }
            }
        }
    }

    public void a() {
        for (int i = 0; i < this.h.getChildCount(); i++) {
            if (this.h.findViewHolderForAdapterPosition(i) instanceof AutoVideoViewHolder) {
                AutoVideoViewHolder autoVideoViewHolder = (AutoVideoViewHolder) this.h.findViewHolderForAdapterPosition(i);
                if (!(autoVideoViewHolder == null || autoVideoViewHolder.getVideoUrl() == null || autoVideoViewHolder.getVideoUrl().equalsIgnoreCase("null") || autoVideoViewHolder.getVideoUrl().isEmpty() || (!autoVideoViewHolder.getVideoUrl().endsWith(".mp4") && this.e))) {
                    autoVideoViewHolder.destroyVideo();
                }
            }
        }
    }

    public void b() {
        int findLastVisibleItemPosition = ((LinearLayoutManager) this.h.getLayoutManager()).findLastVisibleItemPosition();
        for (int findFirstVisibleItemPosition = ((LinearLayoutManager) this.h.getLayoutManager()).findFirstVisibleItemPosition(); findFirstVisibleItemPosition <= findLastVisibleItemPosition; findFirstVisibleItemPosition++) {
            ViewHolder findViewHolderForAdapterPosition = this.h.findViewHolderForAdapterPosition(findFirstVisibleItemPosition);
            if (findViewHolderForAdapterPosition instanceof AutoVideoViewHolder) {
                AutoVideoViewHolder autoVideoViewHolder = (AutoVideoViewHolder) findViewHolderForAdapterPosition;
                if (!(autoVideoViewHolder == null || autoVideoViewHolder.getVideoUrl() == null || autoVideoViewHolder.getVideoUrl().equalsIgnoreCase("null") || autoVideoViewHolder.getVideoUrl().isEmpty() || (!autoVideoViewHolder.getVideoUrl().endsWith(".mp4") && this.e))) {
                    autoVideoViewHolder.pauseVideo();
                }
            }
        }
    }
}
