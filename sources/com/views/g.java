package com.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.gaana.R;
import com.gaana.models.BusinessObject;
import com.i.i;
import com.managers.URLManager;
import com.managers.u;
import com.payment.PaytmRenewal;
import com.services.l.af;

public class g extends BottomSheetDialog implements OnClickListener {
    String a = null;
    int b;
    int c;
    private boolean d;
    private String e = null;
    private String f = null;

    public g(@NonNull Context context, PaytmRenewal paytmRenewal, String str) {
        super(context);
        this.f = str;
        a(context, paytmRenewal);
    }

    private void a(Context context, PaytmRenewal paytmRenewal) {
        setContentView((int) R.layout.popup_paytm_renewal_view_layout);
        BottomSheetBehavior.from((RelativeLayout) findViewById(R.id.layout)).setState(3);
        TextView textView = (TextView) findViewById(R.id.titleText);
        TextView textView2 = (TextView) findViewById(R.id.titleSubText);
        TextView textView3 = (TextView) findViewById(R.id.okayButton);
        textView3.setTypeface(null, 1);
        TextView textView4 = (TextView) findViewById(R.id.letterText);
        textView4.setTypeface(null, 1);
        textView.setText(paytmRenewal.a());
        textView.setTypeface(null, 1);
        textView2.setText(paytmRenewal.i());
        textView3.setText(paytmRenewal.c().a().b());
        textView3.setOnClickListener(this);
        if (paytmRenewal.c().b().c().intValue() == 1) {
            textView4.setText(paytmRenewal.c().b().b());
            this.c = paytmRenewal.c().b().a().intValue();
        } else if (paytmRenewal.c().c().c().intValue() == 1) {
            textView4.setText(paytmRenewal.c().c().b());
            this.c = paytmRenewal.c().c().a().intValue();
        }
        textView4.setOnClickListener(this);
        this.a = paytmRenewal.k();
        this.b = paytmRenewal.c().a().a().intValue();
        long b = paytmRenewal.b();
        long d = paytmRenewal.d();
        long currentTimeMillis = System.currentTimeMillis() / 1000;
        if (currentTimeMillis <= b) {
            this.e = "Before expiry;";
        } else if (currentTimeMillis > b && currentTimeMillis <= d) {
            this.e = "In Grace;";
        }
    }

    public void onClick(View view) {
        int i;
        StringBuilder stringBuilder;
        if (view.getId() == R.id.okayButton) {
            i = this.b;
            stringBuilder = new StringBuilder();
            stringBuilder.append(this.e);
            stringBuilder.append(" ");
            stringBuilder.append(this.f);
            u.a().a("Paytm Consent", "Yes", stringBuilder.toString());
        } else if (view.getId() == R.id.letterText) {
            i = this.c;
            stringBuilder = new StringBuilder();
            stringBuilder.append(this.e);
            stringBuilder.append(" ");
            stringBuilder.append(this.f);
            u.a().a("Paytm Consent", "Later", stringBuilder.toString());
        } else {
            i = 0;
        }
        a(i);
        this.d = true;
        dismiss();
    }

    private void a(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://pay.gaana.com/paytm/paytm_consent.php?type=capture_consent&consent=");
        stringBuilder.append(i);
        stringBuilder.append("&access_key=");
        stringBuilder.append(this.a);
        String stringBuilder2 = stringBuilder.toString();
        URLManager uRLManager = new URLManager();
        uRLManager.a(PaytmRenewal.class);
        uRLManager.a(stringBuilder2);
        i.a().a(new af() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(Object obj) {
            }
        }, uRLManager);
    }

    public void dismiss() {
        super.dismiss();
        if (!this.d) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.e);
            stringBuilder.append(" ");
            stringBuilder.append(this.f);
            u.a().a("Paytm Consent", "Cancel", stringBuilder.toString());
        }
        this.d = false;
    }
}
