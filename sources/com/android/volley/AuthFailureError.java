package com.android.volley;

import android.content.Intent;
import com.gaana.R;
import com.gaana.application.GaanaApplication;

public class AuthFailureError extends VolleyError {
    private Intent b;

    public AuthFailureError(g gVar) {
        super(gVar);
    }

    public String getMessage() {
        if (this.b != null) {
            return GaanaApplication.getInstance().getString(R.string.user_need_reenter_credentials);
        }
        return super.getMessage();
    }
}
