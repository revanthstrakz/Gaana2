package com.helpshift.support.fragments;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import com.helpshift.e.c;
import com.helpshift.e.g;
import com.helpshift.e.k;
import com.helpshift.util.b;
import com.helpshift.util.l;
import com.helpshift.util.o;
import com.helpshift.views.d;
import java.lang.reflect.Field;

public abstract class MainFragment extends Fragment {
    private static final String a = SupportFragment.class.getSimpleName();
    private static boolean f;
    private FragmentManager b;
    protected String c = getClass().getName();
    private boolean d;
    private boolean e;

    public abstract boolean b();

    public FragmentManager k() {
        if (!f) {
            return getChildFragmentManager();
        }
        if (this.b == null) {
            this.b = getChildFragmentManager();
        }
        return this.b;
    }

    public boolean l() {
        return this.d;
    }

    public Context getContext() {
        Context context = super.getContext();
        if (context != null) {
            return context;
        }
        return o.b();
    }

    public void onAttach(Context context) {
        super.onAttach(b.f(context));
        try {
            setRetainInstance(true);
        } catch (Exception unused) {
            f = true;
        }
        if (o.b() == null) {
            o.a(context.getApplicationContext());
        }
        this.e = getResources().getBoolean(c.is_screen_large);
        if (f && this.b != null) {
            try {
                Field declaredField = Fragment.class.getDeclaredField("mChildFragmentManager");
                declaredField.setAccessible(true);
                declaredField.set(this, this.b);
            } catch (NoSuchFieldException e) {
                l.a(a, "NoSuchFieldException", e);
            } catch (IllegalAccessException e2) {
                l.a(a, "IllegalAccessException", e2);
            }
        }
    }

    public void onStart() {
        super.onStart();
        if (b()) {
            SupportFragment a = com.helpshift.support.util.c.a((Fragment) this);
            if (a != null) {
                a.a(this.c);
            }
        }
    }

    public void onPause() {
        this.d = a(this).isChangingConfigurations();
        super.onPause();
    }

    public Activity a(Fragment fragment) {
        if (fragment == null) {
            return null;
        }
        while (fragment.getParentFragment() != null) {
            fragment = fragment.getParentFragment();
        }
        return fragment.getActivity();
    }

    public boolean m() {
        return this.e;
    }

    public void b(String str) {
        SupportFragment a = com.helpshift.support.util.c.a((Fragment) this);
        if (a != null) {
            a.f(str);
        }
    }

    public void onStop() {
        if (b()) {
            SupportFragment a = com.helpshift.support.util.c.a((Fragment) this);
            if (a != null) {
                a.d(this.c);
            }
        }
        super.onStop();
    }

    /* Access modifiers changed, original: protected */
    public void c(String str) {
        ((ClipboardManager) getContext().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("Copy Text", str));
        d.a(getContext(), getString(k.hs__copied_to_clipboard), 0).show();
    }

    public Animation onCreateAnimation(int i, boolean z, int i2) {
        if (com.helpshift.k.b.a().a.j.booleanValue() || z || isRemoving()) {
            return super.onCreateAnimation(i, z, i2);
        }
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 1.0f);
        alphaAnimation.setDuration((long) getResources().getInteger(g.hs_animation_duration));
        return alphaAnimation;
    }
}
