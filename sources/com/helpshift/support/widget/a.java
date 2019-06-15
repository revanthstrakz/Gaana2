package com.helpshift.support.widget;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnShowListener;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import com.helpshift.analytics.AnalyticsEventType;
import com.helpshift.e.f;
import com.helpshift.e.h;
import com.helpshift.e.j;
import com.helpshift.e.k;
import com.helpshift.util.o;

public class a extends Dialog implements OnDismissListener, OnShowListener, OnClickListener, OnTouchListener {
    private Context a;
    private CSATView b;
    private RatingBar c;
    private TextView d;
    private EditText e;
    private float f;
    private boolean g = false;

    public a(Context context) {
        super(context);
        this.a = context;
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        setContentView(h.hs__csat_dialog);
        setOnShowListener(this);
        setOnDismissListener(this);
        this.c = (RatingBar) findViewById(f.ratingBar);
        com.helpshift.support.util.h.c(getContext(), this.c.getProgressDrawable());
        this.c.setOnTouchListener(this);
        this.d = (TextView) findViewById(f.like_status);
        this.e = (EditText) findViewById(f.additional_feedback);
        ((Button) findViewById(f.submit)).setOnClickListener(this);
    }

    public void onShow(DialogInterface dialogInterface) {
        o.d().f().a(AnalyticsEventType.START_CSAT_RATING);
        this.c.setRating(this.f);
        String quantityString = this.a.getResources().getQuantityString(j.hs__csat_rating_value, (int) this.f, new Object[]{Integer.valueOf((int) this.f)});
        if (((double) this.f) > 2.0d) {
            this.d.setText(k.hs__csat_like_message);
        } else {
            this.d.setText(k.hs__csat_dislike_message);
        }
        this.c.setContentDescription(quantityString);
    }

    public void onDismiss(DialogInterface dialogInterface) {
        if (this.g) {
            this.b.a();
            return;
        }
        o.d().f().a(AnalyticsEventType.CANCEL_CSAT_RATING);
        this.b.getRatingBar().setRating(0.0f);
    }

    public void onClick(View view) {
        if (view.getId() == f.submit) {
            this.b.a(this.c.getRating(), this.e.getText().toString());
            this.g = true;
            dismiss();
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        return view.getId() == f.ratingBar;
    }

    /* Access modifiers changed, original: protected */
    public void a(CSATView cSATView) {
        this.b = cSATView;
        this.f = cSATView.getRatingBar().getRating();
        show();
    }
}
