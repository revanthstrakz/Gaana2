package com.til.colombia.android.service;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import com.til.colombia.android.R;
import com.til.colombia.android.commons.COLOMBIA_PLAYER_STATE;
import com.til.colombia.android.commons.CommonUtil.VideoPauseMode;
import com.til.colombia.android.internal.a;

final class y implements OnClickListener {
    final /* synthetic */ v a;

    y(v vVar) {
        this.a = vVar;
    }

    public final void onClick(View view) {
        if (this.a.c != null) {
            if (((NativeItem) this.a.p).isOnCall()) {
                Toast.makeText(this.a.a, this.a.a.getString(R.string.video_click_error_during_call), 0).show();
            } else if (this.a.c.isPlaying()) {
                this.a.c.h();
                this.a.j();
            } else if (this.a.c.d() == VideoPauseMode.USER_PAUSE || this.a.c.e == COLOMBIA_PLAYER_STATE.COMPLETED) {
                if (this.a.c.d) {
                    Toast.makeText(this.a.a, "Some error occurred", 0).show();
                    return;
                }
                this.a.s();
                this.a.c.a(false);
            } else if (!a.b(this.a.a)) {
                Toast.makeText(this.a.a, "Network is not available", 0).show();
            } else if (this.a.c.e == COLOMBIA_PLAYER_STATE.PREPARED) {
                v.b(this.a, false);
                this.a.s();
                this.a.c.a(false);
            } else if (this.a.c.e == COLOMBIA_PLAYER_STATE.PREPARING) {
                v.b(this.a, false);
                this.a.s();
                this.a.i.setVisibility(0);
                this.a.i.bringToFront();
            } else {
                if (this.a.c.e == COLOMBIA_PLAYER_STATE.INITIALIZED) {
                    this.a.k.setVisibility(8);
                    this.a.k.setBackgroundResource(R.drawable.play);
                    this.a.i.setVisibility(0);
                    try {
                        this.a.c.prepareAsync();
                    } catch (IllegalStateException unused) {
                        this.a.f();
                    }
                }
            }
        }
    }
}
