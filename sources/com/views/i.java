package com.views;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.fragments.MiniPlayerFragment;
import com.fragments.MiniPlayerFragmentV4;
import com.gaana.GaanaActivity;
import com.gaana.R;

public class i {
    private Context a;
    private int b = 0;

    public i(Context context) {
        this.a = context;
    }

    public int a() {
        return this.b;
    }

    private void c() {
        Fragment miniPlayer = ((GaanaActivity) this.a).getMiniPlayer();
        if (miniPlayer != null) {
            if (miniPlayer instanceof MiniPlayerFragment) {
                ((MiniPlayerFragment) miniPlayer).a(null);
            } else if (miniPlayer instanceof MiniPlayerFragmentV4) {
                ((MiniPlayerFragmentV4) miniPlayer).a(null);
            }
        }
        if (((GaanaActivity) this.a).findViewById(R.id.bottom_bar).getVisibility() != 0) {
            ((GaanaActivity) this.a).findViewById(R.id.bottom_bar).setVisibility(0);
        }
        this.b = 0;
    }

    private void d() {
        ((GaanaActivity) this.a).findViewById(R.id.bottom_bar).setVisibility(8);
        this.b = 1;
    }

    public void a(int i) {
        if (i == 2) {
            e();
        } else if (i == 0) {
            c();
        } else if (i == 1) {
            d();
        } else if (i == 3) {
            f();
        }
    }

    public void b() {
        if (this.b == 1) {
            c();
        }
        this.b = 2;
    }

    private void e() {
        if (this.b == 1) {
            c();
        }
        ((GaanaActivity) this.a).findViewById(R.id.bottom_bar).setVisibility(8);
        this.b = 2;
    }

    private void f() {
        if (this.b == 1) {
            c();
        }
        FragmentManager supportFragmentManager = ((GaanaActivity) this.a).getSupportFragmentManager();
        Fragment findFragmentByTag = supportFragmentManager.findFragmentByTag("player_fragment");
        if (findFragmentByTag != null) {
            FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
            beginTransaction.remove(findFragmentByTag);
            beginTransaction.setCustomAnimations(R.anim.fade_out, R.anim.fade_in);
            beginTransaction.commitAllowingStateLoss();
        }
    }

    public void b(int i) {
        this.b = i;
    }
}
