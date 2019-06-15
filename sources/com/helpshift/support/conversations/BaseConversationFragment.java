package com.helpshift.support.conversations;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import com.helpshift.e.k;
import com.helpshift.support.d.c;
import com.helpshift.support.fragments.MainFragment;
import com.helpshift.support.fragments.SupportFragment;
import com.helpshift.support.fragments.a;
import com.helpshift.support.h.e;
import com.helpshift.support.util.AppSessionConstants.Screen;
import com.helpshift.support.util.f;
import com.helpshift.support.util.g;
import com.helpshift.util.l;

public abstract class BaseConversationFragment extends MainFragment implements OnMenuItemClickListener, a {
    private Snackbar a;
    private Snackbar b;

    public abstract int a();

    public abstract void a(int i);

    public boolean b() {
        return true;
    }

    public abstract Screen c();

    public abstract String d();

    public boolean onMenuItemClick(MenuItem menuItem) {
        return false;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        e().g();
    }

    public void onStart() {
        super.onStart();
        e.b().a("current_open_screen", c());
    }

    public void onResume() {
        super.onResume();
        b(d());
    }

    /* Access modifiers changed, original: protected */
    public SupportFragment e() {
        return (SupportFragment) getParentFragment();
    }

    /* Access modifiers changed, original: protected */
    public c f() {
        return e().c();
    }

    /* Access modifiers changed, original: protected */
    public void a(boolean z) {
        a(z, 3);
    }

    public void a(boolean z, int i) {
        String str;
        switch (i) {
            case 2:
                str = "android.permission.READ_EXTERNAL_STORAGE";
                break;
            case 3:
                str = "android.permission.WRITE_EXTERNAL_STORAGE";
                break;
            default:
                str = null;
                break;
        }
        if (z && str != null) {
            com.helpshift.support.util.e.a(getContext(), getView());
            this.a = f.a(getParentFragment(), new String[]{str}, i, getView());
        } else if (!isDetached()) {
            g.a(getView(), k.hs__permission_not_granted, -1);
        }
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        boolean z = false;
        if (iArr.length == 1 && iArr[0] == 0) {
            z = true;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("onRequestPermissionsResult : request: ");
        stringBuilder.append(i);
        stringBuilder.append(", result: ");
        stringBuilder.append(z);
        l.a("Helpshift_BaseConvFrag", stringBuilder.toString());
        if (z) {
            a(i);
            return;
        }
        this.b = com.helpshift.views.c.a(getView(), k.hs__permission_denied_message, -1).setAction(k.hs__permission_denied_snackbar_action, new OnClickListener() {
            public void onClick(View view) {
                f.a(BaseConversationFragment.this.getContext());
            }
        });
        this.b.show();
    }

    public void onViewCreated(View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        e().a((a) this);
    }

    public void onDestroyView() {
        g.a(getView());
        e().b((a) this);
        super.onDestroyView();
    }

    public void onPause() {
        if (this.a != null && this.a.isShown()) {
            this.a.dismiss();
        }
        if (this.b != null && this.b.isShown()) {
            this.b.dismiss();
        }
        super.onPause();
    }

    public void onStop() {
        Screen screen = (Screen) e.b().a("current_open_screen");
        if (screen != null && screen.equals(c())) {
            e.b().b("current_open_screen");
        }
        b(getString(k.hs__help_header));
        super.onStop();
    }
}
