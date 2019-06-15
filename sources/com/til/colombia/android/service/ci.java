package com.til.colombia.android.service;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import com.til.colombia.android.internal.a;
import org.json.JSONObject;

final class ci implements OnClickListener {
    final /* synthetic */ LeadGenActivity a;

    ci(LeadGenActivity leadGenActivity) {
        this.a = leadGenActivity;
    }

    public final void onClick(View view) {
        if (a.b(this.a.mContext)) {
            JSONObject jSONObject = new JSONObject();
            try {
                if (this.a.formIsValid(this.a.formLayout, jSONObject)) {
                    this.a.postForm(jSONObject);
                }
                return;
            } catch (Exception unused) {
                this.a.finish();
                return;
            }
        }
        Toast.makeText(this.a.mContext, "Network is not available", 0).show();
        this.a.finish();
    }
}
