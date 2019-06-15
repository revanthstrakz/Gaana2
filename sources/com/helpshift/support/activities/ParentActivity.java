package com.helpshift.support.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import com.helpshift.activities.MainActivity;
import com.helpshift.e.f;
import com.helpshift.e.h;
import com.helpshift.support.fragments.SupportFragment;
import java.util.List;

public class ParentActivity extends MainActivity {
    FragmentManager a;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(h.hs__parent_activity);
        setSupportActionBar((Toolbar) findViewById(f.toolbar));
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.a = getSupportFragmentManager();
        if (bundle == null) {
            FragmentTransaction beginTransaction = this.a.beginTransaction();
            beginTransaction.add(f.support_fragment_container, SupportFragment.a(getIntent().getExtras()));
            beginTransaction.commit();
        }
    }

    public void onBackPressed() {
        List<Fragment> fragments = this.a.getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                if (fragment != null && fragment.isVisible() && (fragment instanceof SupportFragment)) {
                    if (!((SupportFragment) fragment).h()) {
                        FragmentManager childFragmentManager = fragment.getChildFragmentManager();
                        if (childFragmentManager.getBackStackEntryCount() > 0) {
                            childFragmentManager.popBackStack();
                            return;
                        }
                    } else {
                        return;
                    }
                }
            }
        }
        super.onBackPressed();
    }

    /* Access modifiers changed, original: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        List<Fragment> fragments = this.a.getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                if (fragment instanceof SupportFragment) {
                    ((SupportFragment) fragment).b(intent.getExtras());
                }
            }
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return true;
    }
}
