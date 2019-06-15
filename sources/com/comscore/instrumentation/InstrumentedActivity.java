package com.comscore.instrumentation;

import android.app.Activity;
import android.os.Bundle;
import com.comscore.analytics.comScore;

public class InstrumentedActivity extends Activity {
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
