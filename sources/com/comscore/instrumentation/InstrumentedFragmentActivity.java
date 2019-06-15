package com.comscore.instrumentation;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.comscore.analytics.comScore;

public class InstrumentedFragmentActivity extends FragmentActivity {
    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        comScore.setAppContext(getApplicationContext());
    }

    /* Access modifiers changed, original: protected */
    public void onPause() {
        super.onPause();
        comScore.onExitForeground();
    }

    /* Access modifiers changed, original: protected */
    public void onResume() {
        super.onResume();
        comScore.getCore().setCurrentActivityName(getClass().getSimpleName());
        comScore.onEnterForeground();
    }
}
