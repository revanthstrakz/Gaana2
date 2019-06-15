package com.payu.custombrowser.widgets;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.payu.custombrowser.d.d;
import com.payu.custombrowser.d.e;
import com.payu.custombrowser.d.f;
import com.payu.custombrowser.d.h;
import com.payu.custombrowser.util.CBUtil;
import java.util.Timer;
import java.util.TimerTask;

public class a extends Dialog {
    Timer a = null;
    CBUtil b;
    private TextView c;
    private Activity d;

    public a(Context context) {
        super(context, h.cb_progress_dialog);
        this.d = (Activity) context;
        LayoutInflater from = LayoutInflater.from(context);
        this.b = new CBUtil();
        final Drawable[] drawableArr = new Drawable[]{a(context.getApplicationContext(), d.l_icon1), a(context.getApplicationContext(), d.l_icon2), a(context.getApplicationContext(), d.l_icon3), a(context.getApplicationContext(), d.l_icon4)};
        View inflate = from.inflate(f.cb_prog_dialog, null);
        setContentView(inflate);
        this.c = (TextView) inflate.findViewById(e.dialog_desc);
        final ImageView imageView = (ImageView) inflate.findViewById(e.imageView);
        setCancelable(true);
        setCanceledOnTouchOutside(false);
        this.b.cancelTimer(this.a);
        this.a = new Timer();
        this.a.scheduleAtFixedRate(new TimerTask() {
            int a = -1;

            public synchronized void run() {
                if (!(a.this.d == null || a.this.d.isFinishing())) {
                    a.this.d.runOnUiThread(new Runnable() {
                        public void run() {
                            if (a.this.d != null) {
                                AnonymousClass1 anonymousClass1 = AnonymousClass1.this;
                                anonymousClass1.a++;
                                if (AnonymousClass1.this.a >= drawableArr.length) {
                                    AnonymousClass1.this.a = 0;
                                }
                                imageView.setImageBitmap(null);
                                imageView.destroyDrawingCache();
                                imageView.refreshDrawableState();
                                imageView.setImageDrawable(drawableArr[AnonymousClass1.this.a]);
                            }
                        }
                    });
                }
            }
        }, 0, 500);
        setOnDismissListener(new OnDismissListener() {
            public void onDismiss(DialogInterface dialogInterface) {
                a.this.b.cancelTimer(a.this.a);
            }
        });
    }

    private Drawable a(Context context, int i) {
        if (VERSION.SDK_INT >= 21) {
            return context.getResources().getDrawable(i, context.getTheme());
        }
        return context.getResources().getDrawable(i);
    }

    public void a(String str) {
        this.c.setText(str);
    }
}
