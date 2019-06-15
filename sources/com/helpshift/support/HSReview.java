package com.helpshift.support;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import com.helpshift.support.f.b;
import com.helpshift.support.f.g;
import java.util.List;

public final class HSReview extends FragmentActivity {
    private List<g> a;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(new View(this));
        this.a = b.a();
        b.a(null);
        new HSReviewFragment().show(getSupportFragmentManager(), "hs__review_dialog");
    }

    /* Access modifiers changed, original: protected */
    public void onDestroy() {
        super.onDestroy();
        b.a(this.a);
        com.helpshift.util.b.a();
    }

    /* Access modifiers changed, original: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(com.helpshift.util.b.f(context));
    }
}
