package com.facebook.ads.internal.view.g.c;

import android.content.Context;
import android.widget.TextView;
import com.facebook.ads.internal.l.f;
import com.facebook.ads.internal.view.g.b.n;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class c extends com.facebook.ads.internal.view.g.a.c {
    private final TextView a;
    private final String b;
    private final f<n> c = new f<n>() {
        public Class<n> a() {
            return n.class;
        }

        public void a(n nVar) {
            if (c.this.getVideoView() != null) {
                c.this.a.setText(c.this.a((long) (c.this.getVideoView().getDuration() - c.this.getVideoView().getCurrentPositionInMillis())));
            }
        }
    };

    public c(Context context, String str) {
        super(context);
        this.a = new TextView(context);
        this.b = str;
        addView(this.a);
    }

    private String a(long j) {
        if (j <= 0) {
            return "00:00";
        }
        long toMinutes = TimeUnit.MILLISECONDS.toMinutes(j);
        j = TimeUnit.MILLISECONDS.toSeconds(j % 60000);
        if (this.b.isEmpty()) {
            return String.format(Locale.US, "%02d:%02d", new Object[]{Long.valueOf(toMinutes), Long.valueOf(j)});
        }
        return this.b.replace("{{REMAINING_TIME}}", String.format(Locale.US, "%02d:%02d", new Object[]{Long.valueOf(toMinutes), Long.valueOf(j)}));
    }

    /* Access modifiers changed, original: protected */
    public void a() {
        super.a();
        if (getVideoView() != null) {
            getVideoView().getEventBus().a(this.c);
        }
    }

    /* Access modifiers changed, original: protected */
    public void b() {
        if (getVideoView() != null) {
            getVideoView().getEventBus().b(this.c);
        }
        super.b();
    }

    public void setCountdownTextColor(int i) {
        this.a.setTextColor(i);
    }
}
