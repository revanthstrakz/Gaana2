package com.comscore.instrumentation;

import android.os.Bundle;
import com.comscore.analytics.comScore;
import com.google.android.maps.MapActivity;

public class InstrumentedMapActivity extends MapActivity {
    /* Access modifiers changed, original: protected */
    public boolean isRouteDisplayed() {
        return false;
    }

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
