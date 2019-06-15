package com.moe.pushlibrary.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import com.moe.pushlibrary.MoEHelper;

public abstract class MoEBaseActivity extends FragmentActivity {
    protected MoEHelper mHelper = null;

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mHelper = MoEHelper.getInstance(this);
    }

    /* Access modifiers changed, original: protected */
    public void onStart() {
        super.onStart();
        this.mHelper.onStart(this);
    }

    /* Access modifiers changed, original: protected */
    public void onResume() {
        super.onResume();
        this.mHelper.onResume(this);
    }

    /* Access modifiers changed, original: protected */
    public void onStop() {
        this.mHelper.onStop(this);
        super.onStop();
    }

    /* Access modifiers changed, original: protected */
    public void onPause() {
        super.onPause();
    }

    /* Access modifiers changed, original: protected */
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.mHelper.onSaveInstanceState(bundle);
    }

    /* Access modifiers changed, original: protected */
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        this.mHelper.onRestoreInstanceState(bundle);
    }
}
