package com.til.colombia.android.service;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import com.til.colombia.android.R;

final class z implements OnClickListener {
    final /* synthetic */ v a;

    z(v vVar) {
        this.a = vVar;
    }

    public final void onClick(View view) {
        if (((NativeItem) this.a.p).isOnCall()) {
            Toast.makeText(this.a.a, this.a.a.getString(R.string.video_click_error_during_call), 0).show();
            return;
        }
        if (this.a.r.getVisibility() == 0) {
            if (this.a.c == null || !this.a.c.b) {
                this.a.a(true);
            } else {
                v.b(this.a, true);
            }
        }
    }
}
