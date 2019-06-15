package com.helpshift.campaigns.fragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import com.helpshift.campaigns.m.a;
import com.helpshift.e.c;
import com.helpshift.util.b;
import com.helpshift.util.l;
import com.helpshift.util.o;
import com.helpshift.util.v;
import java.lang.reflect.Field;
import java.util.ArrayList;

public abstract class MainFragment extends Fragment {
    private static boolean f;
    private int a = 0;
    private Toolbar b = null;
    private boolean c;
    private boolean d;
    private FragmentManager e;

    /* Access modifiers changed, original: protected */
    public void a(Menu menu) {
    }

    /* Access modifiers changed, original: protected */
    public boolean a() {
        return true;
    }

    /* Access modifiers changed, original: protected */
    public int d() {
        return 0;
    }

    public FragmentManager k() {
        if (!f) {
            return getChildFragmentManager();
        }
        if (this.e == null) {
            this.e = getChildFragmentManager();
        }
        return this.e;
    }

    public boolean l() {
        return this.c;
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
        if (a()) {
            try {
                setRetainInstance(true);
            } catch (Exception unused) {
                f = true;
            }
        }
        if (o.b() == null) {
            o.a(context.getApplicationContext());
        }
        this.d = getResources().getBoolean(c.is_dual_pane);
        if (f && this.e != null) {
            try {
                Field declaredField = Fragment.class.getDeclaredField("mChildFragmentManager");
                declaredField.setAccessible(true);
                declaredField.set(this, this.e);
            } catch (NoSuchFieldException e) {
                l.a("MainFragment", "NoSuchFieldException", e);
            } catch (IllegalAccessException e2) {
                l.a("MainFragment", "IllegalAccessException", e2);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public boolean m() {
        return getResources().getBoolean(c.is_screen_large);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        bundle = getArguments();
        if (bundle != null) {
            this.a = bundle.getInt("toolbarId");
        }
        if (this.a == 0 && d() != 0) {
            setHasOptionsMenu(true);
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        if (this.a != 0 && d() != 0) {
            this.b = (Toolbar) getActivity().findViewById(this.a);
            Menu menu = this.b.getMenu();
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < menu.size(); i++) {
                arrayList.add(Integer.valueOf(menu.getItem(i).getItemId()));
            }
            this.b.inflateMenu(d());
            a(this.b.getMenu());
        }
    }

    public void onStop() {
        super.onStop();
        if (VERSION.SDK_INT >= 11) {
            this.c = a((Fragment) this).isChangingConfigurations();
        }
    }

    public void onDetach() {
        b.a();
        super.onDetach();
    }

    public void onDestroyView() {
        super.onDestroyView();
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(d(), menu);
        a(menu);
        super.onCreateOptionsMenu(menu, menuInflater);
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

    public boolean n() {
        return this.d && m();
    }

    public void d(String str) {
        if (this instanceof InboxFragment) {
            ((InboxFragment) this).a(str);
            return;
        }
        InboxFragment a = a.a(this);
        if (a != null) {
            a.a(str);
        }
    }

    public void b(boolean z) {
        if (VERSION.SDK_INT >= 21) {
            a(z);
        }
    }

    @TargetApi(21)
    private void a(boolean z) {
        if (this.b == null) {
            ActionBar supportActionBar = ((AppCompatActivity) a((Fragment) this)).getSupportActionBar();
            if (supportActionBar == null) {
                return;
            }
            if (z) {
                supportActionBar.setElevation(v.a(getContext(), 4.0f));
            } else {
                supportActionBar.setElevation(0.0f);
            }
        } else if (z) {
            this.b.setElevation(v.a(getContext(), 4.0f));
        } else {
            this.b.setElevation(0.0f);
        }
    }
}
