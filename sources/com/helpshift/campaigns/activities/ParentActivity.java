package com.helpshift.campaigns.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.helpshift.activities.MainActivity;
import com.helpshift.campaigns.fragments.InboxFragment;
import com.helpshift.e.f;
import com.helpshift.e.h;

public class ParentActivity extends MainActivity {
    FragmentManager a;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(h.hs__campaign_parent_activity);
        setSupportActionBar((Toolbar) findViewById(f.toolbar));
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
        this.a = getSupportFragmentManager();
        if (bundle == null) {
            FragmentTransaction beginTransaction = this.a.beginTransaction();
            beginTransaction.add(f.campaigns_fragment_container, InboxFragment.a(getIntent().getExtras()));
            beginTransaction.commit();
        }
    }

    public void onBackPressed() {
        if (((InboxFragment) getSupportFragmentManager().findFragmentById(f.campaigns_fragment_container)).c()) {
            super.onBackPressed();
        }
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        onBackPressed();
        return true;
    }

    public void onContextMenuClosed(Menu menu) {
        InboxFragment inboxFragment = (InboxFragment) getSupportFragmentManager().findFragmentById(f.campaigns_fragment_container);
        if (inboxFragment != null) {
            inboxFragment.b(menu);
        }
        super.onContextMenuClosed(menu);
    }
}
