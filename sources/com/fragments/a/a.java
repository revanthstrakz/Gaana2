package com.fragments.a;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.Fragment.SavedState;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import com.dynamicview.DynamicHomeFragment;
import com.fragments.TabbedFragment;
import com.gaana.R;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class a {
    private int a;
    private Deque<String> b = new ArrayDeque();
    private Map<String, Fragment> c = new HashMap();
    private Map<String, SavedState> d = new HashMap();
    private FragmentManager e;
    private a f;
    private boolean g = true;
    private final String h = "home";

    interface a {
    }

    public a(FragmentManager fragmentManager, @IdRes int i, Bundle bundle, a aVar) {
        this.e = fragmentManager;
        this.a = i;
        this.f = aVar;
        if (bundle != null) {
            b(bundle);
        }
    }

    public void a(Fragment fragment, boolean z) {
        if (fragment == null) {
            return;
        }
        if (a(fragment)) {
            String fragmentStackName = ((com.constants.a.a) fragment).getFragmentStackName();
            if (this.b.contains(fragmentStackName)) {
                a(fragmentStackName, (String) this.b.peek(), z);
                return;
            }
            TabbedFragment tabbedFragment = (TabbedFragment) TabbedFragment.a(fragmentStackName);
            tabbedFragment.a(fragment);
            String str = !this.b.isEmpty() ? (String) this.b.peek() : null;
            this.b.push(fragmentStackName);
            this.c.put(fragmentStackName, tabbedFragment);
            a(fragmentStackName, str, z);
        } else if (this.b.isEmpty()) {
            a(fragment, "deeplink", 2, z);
        } else {
            TabbedFragment tabbedFragment2 = (TabbedFragment) this.c.get(this.b.peek());
            if (!tabbedFragment2.isAdded()) {
                a(tabbedFragment2, (String) this.b.peek(), 2, z);
            }
            tabbedFragment2.b(fragment);
        }
    }

    public void a(String str, boolean z) {
        a(str, !this.b.isEmpty() ? (String) this.b.peek() : null, z);
    }

    public boolean a() {
        return a(null);
    }

    public boolean a(String str) {
        return a(str, 0);
    }

    public boolean a(String str, int i) {
        boolean z = false;
        if (!TextUtils.isEmpty(str)) {
            if (!this.b.isEmpty()) {
                TabbedFragment tabbedFragment = (TabbedFragment) this.c.get(this.b.peek());
                if (tabbedFragment.b(str)) {
                    return tabbedFragment.a(str, i);
                }
            }
            return false;
        } else if (this.b.isEmpty()) {
            return e();
        } else {
            if (((TabbedFragment) this.c.get((String) this.b.peek())).e() || d()) {
                z = true;
            }
            return z;
        }
    }

    public void b(String str) {
        if (!this.b.isEmpty() && this.b.remove(str)) {
            this.c.remove(str);
            this.d.remove(str);
        }
    }

    public void b() {
        if (!this.g) {
            try {
                this.e.beginTransaction().commitAllowingStateLoss();
            } catch (IllegalStateException unused) {
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0081  */
    private void a(java.lang.String r5, java.lang.String r6, boolean r7) {
        /*
        r4 = this;
        r0 = r4.c;
        r0 = r0.get(r5);
        r0 = (android.support.v4.app.Fragment) r0;
        r1 = r4.b;
        r1 = r1.isEmpty();
        r2 = 1;
        if (r1 != 0) goto L_0x007e;
    L_0x0011:
        r1 = android.text.TextUtils.isEmpty(r6);
        if (r1 != 0) goto L_0x007f;
    L_0x0017:
        r1 = r6.equals(r5);
        if (r1 != 0) goto L_0x0064;
    L_0x001d:
        r1 = r4.c;
        r1 = r1.get(r6);
        r1 = (android.support.v4.app.Fragment) r1;
        r1 = r1.isAdded();
        if (r1 == 0) goto L_0x003e;
    L_0x002b:
        r1 = r4.e;
        r3 = r4.c;
        r3 = r3.get(r6);
        r3 = (android.support.v4.app.Fragment) r3;
        r1 = r1.saveFragmentInstanceState(r3);
        r3 = r4.d;
        r3.put(r6, r1);
    L_0x003e:
        r1 = "player";
        r1 = r6.equals(r1);
        if (r1 == 0) goto L_0x0056;
    L_0x0046:
        r1 = r4.b;
        r1.remove(r6);
        r1 = r4.c;
        r1.remove(r6);
        r1 = r4.d;
        r1.remove(r6);
        goto L_0x007f;
    L_0x0056:
        r1 = "search";
        r1 = r6.equals(r1);
        if (r1 == 0) goto L_0x007f;
    L_0x005e:
        r1 = r4.d;
        r1.remove(r6);
        goto L_0x007f;
    L_0x0064:
        r6 = r0;
        r6 = (com.fragments.TabbedFragment) r6;
        r1 = r6.d();
        if (r1 != 0) goto L_0x007e;
    L_0x006d:
        r1 = r6.c();
        r1 = r1 instanceof com.constants.a.a;
        if (r1 == 0) goto L_0x007e;
    L_0x0075:
        r6 = r6.c();
        r6 = (com.constants.a.a) r6;
        r6.onFragmentScroll();
    L_0x007e:
        r2 = 0;
    L_0x007f:
        if (r2 == 0) goto L_0x00a0;
    L_0x0081:
        r4.a(r5, r0);
        r6 = r4.b;
        r6 = r6.peek();
        r6 = (java.lang.String) r6;
        r6 = r6.equals(r5);
        if (r6 != 0) goto L_0x009c;
    L_0x0092:
        r6 = r4.b;
        r6.remove(r5);
        r6 = r4.b;
        r6.push(r5);
    L_0x009c:
        r6 = 2;
        r4.a(r0, r5, r6, r7);
    L_0x00a0:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fragments.a.a.a(java.lang.String, java.lang.String, boolean):void");
    }

    private boolean d() {
        String str;
        if (this.b.size() > 1) {
            str = (String) this.b.pop();
            this.c.remove(str);
            this.d.remove(str);
            if (!this.b.isEmpty()) {
                str = (String) this.b.peek();
                Fragment fragment = (Fragment) this.c.get(str);
                a(str, fragment);
                return a(fragment, str, 2, true);
            }
        } else if (!this.b.isEmpty()) {
            str = (String) this.b.peek();
            if (!str.equals("home")) {
                this.b.remove(str);
                this.d.remove(str);
                this.c.remove(str);
                e();
                return true;
            }
        }
        return false;
    }

    private void a(String str, Fragment fragment) {
        try {
            if (!TextUtils.isEmpty(str) && this.d.get(str) != null && !fragment.isAdded()) {
                fragment.setInitialSavedState((SavedState) this.d.get(str));
            }
        } catch (IllegalStateException unused) {
        }
    }

    private boolean a(@NonNull Fragment fragment, @NonNull String str, int i, boolean z) {
        FragmentTransaction beginTransaction = this.e.beginTransaction();
        this.g = false;
        if (i == 4) {
            beginTransaction.remove(fragment);
        } else if (i == 8) {
            beginTransaction.hide(fragment);
        } else if (i == 16) {
            beginTransaction.show(fragment);
        } else if (i == 32) {
            beginTransaction.detach(fragment);
        } else if (i != 64) {
            switch (i) {
                case 1:
                    beginTransaction.add(this.a, fragment, str);
                    break;
                case 2:
                    if (z) {
                        if (str.equals("player")) {
                            beginTransaction.setCustomAnimations(R.anim.slide_up_from_bottom, R.anim.no_animation);
                        } else {
                            beginTransaction.setCustomAnimations(R.anim.fade_in, R.anim.fade_out);
                        }
                    }
                    beginTransaction.replace(this.a, fragment, str);
                    break;
            }
        } else {
            beginTransaction.attach(fragment);
        }
        try {
            beginTransaction.commitAllowingStateLoss();
            this.g = true;
            return true;
        } catch (IllegalStateException unused) {
            return false;
        }
    }

    private boolean a(Fragment fragment) {
        return fragment instanceof com.constants.a.a;
    }

    public void a(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        if (!this.b.isEmpty()) {
            if (this.d.size() > 0) {
                SavedState[] savedStateArr = new SavedState[this.d.size()];
                String[] strArr = new String[this.d.size()];
                int i = 0;
                for (Entry entry : this.d.entrySet()) {
                    savedStateArr[i] = (SavedState) entry.getValue();
                    int i2 = i + 1;
                    strArr[i] = (String) entry.getKey();
                    i = i2;
                }
                bundle2.putParcelableArray("frag_states", savedStateArr);
                bundle2.putStringArray("frag_tag_states", strArr);
            }
            String[] strArr2 = new String[this.b.size()];
            this.b.toArray(strArr2);
            bundle2.putStringArray("stack_states", strArr2);
        }
        bundle.putParcelable("fc_state", bundle2);
    }

    public void b(@NonNull Bundle bundle) {
        bundle = (Bundle) bundle.getParcelable("fc_state");
        if (bundle != null) {
            Parcelable[] parcelableArray = bundle.getParcelableArray("frag_states");
            String[] stringArray = bundle.getStringArray("frag_tag_states");
            int i = 0;
            if (!(parcelableArray == null || stringArray == null)) {
                this.c.clear();
                this.d.clear();
                int i2 = 0;
                int i3 = i2;
                while (i2 < parcelableArray.length && i3 < stringArray.length) {
                    this.d.put(stringArray[i3], (SavedState) parcelableArray[i2]);
                    i2++;
                    i3++;
                }
            }
            String[] stringArray2 = bundle.getStringArray("stack_states");
            if (stringArray2 != null) {
                this.b.clear();
                while (i < stringArray2.length) {
                    this.b.push(stringArray2[i]);
                    this.c.put(stringArray2[i], TabbedFragment.a(stringArray2[i]));
                    i++;
                }
            }
            if (!this.b.isEmpty()) {
                Fragment findFragmentByTag = this.e.findFragmentByTag((String) this.b.peek());
                if (findFragmentByTag != null) {
                    this.c.put(this.b.peek(), findFragmentByTag);
                }
            }
        }
    }

    public boolean c(String str) {
        return this.b.contains(str);
    }

    public boolean c() {
        return this.c.get("player") != null && ((TabbedFragment) this.c.get("player")).b() == 1;
    }

    private boolean e() {
        a(new DynamicHomeFragment(), true);
        return true;
    }
}
