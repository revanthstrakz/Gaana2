package com.bumptech.glide.manager;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.util.Log;
import com.bumptech.glide.e;
import com.bumptech.glide.i;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class RequestManagerFragment extends Fragment {
    private final a a;
    private final l b;
    private final HashSet<RequestManagerFragment> c;
    @Nullable
    private i d;
    @Nullable
    private RequestManagerFragment e;
    @Nullable
    private Fragment f;

    private class a implements l {
        a() {
        }

        public Set<i> a() {
            Set<RequestManagerFragment> d = RequestManagerFragment.this.d();
            HashSet hashSet = new HashSet(d.size());
            for (RequestManagerFragment requestManagerFragment : d) {
                if (requestManagerFragment.b() != null) {
                    hashSet.add(requestManagerFragment.b());
                }
            }
            return hashSet;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(super.toString());
            stringBuilder.append("{fragment=");
            stringBuilder.append(RequestManagerFragment.this);
            stringBuilder.append("}");
            return stringBuilder.toString();
        }
    }

    public RequestManagerFragment() {
        this(new a());
    }

    @SuppressLint({"ValidFragment"})
    RequestManagerFragment(a aVar) {
        this.b = new a();
        this.c = new HashSet();
        this.a = aVar;
    }

    public void a(i iVar) {
        this.d = iVar;
    }

    /* Access modifiers changed, original: 0000 */
    public a a() {
        return this.a;
    }

    @Nullable
    public i b() {
        return this.d;
    }

    public l c() {
        return this.b;
    }

    private void a(RequestManagerFragment requestManagerFragment) {
        this.c.add(requestManagerFragment);
    }

    private void b(RequestManagerFragment requestManagerFragment) {
        this.c.remove(requestManagerFragment);
    }

    @TargetApi(17)
    public Set<RequestManagerFragment> d() {
        if (this.e == this) {
            return Collections.unmodifiableSet(this.c);
        }
        if (this.e == null || VERSION.SDK_INT < 17) {
            return Collections.emptySet();
        }
        HashSet hashSet = new HashSet();
        for (RequestManagerFragment requestManagerFragment : this.e.d()) {
            if (b(requestManagerFragment.getParentFragment())) {
                hashSet.add(requestManagerFragment);
            }
        }
        return Collections.unmodifiableSet(hashSet);
    }

    /* Access modifiers changed, original: 0000 */
    public void a(Fragment fragment) {
        this.f = fragment;
        if (fragment != null && fragment.getActivity() != null) {
            a(fragment.getActivity());
        }
    }

    @TargetApi(17)
    private Fragment e() {
        Fragment parentFragment = VERSION.SDK_INT >= 17 ? getParentFragment() : null;
        return parentFragment != null ? parentFragment : this.f;
    }

    @TargetApi(17)
    private boolean b(Fragment fragment) {
        Fragment parentFragment = getParentFragment();
        while (fragment.getParentFragment() != null) {
            if (fragment.getParentFragment() == parentFragment) {
                return true;
            }
            fragment = fragment.getParentFragment();
        }
        return false;
    }

    private void a(Activity activity) {
        f();
        this.e = e.b((Context) activity).h().a(activity.getFragmentManager(), null);
        if (this.e != this) {
            this.e.a(this);
        }
    }

    private void f() {
        if (this.e != null) {
            this.e.b(this);
            this.e = null;
        }
    }

    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            a(activity);
        } catch (IllegalStateException e) {
            if (Log.isLoggable("RMFragment", 5)) {
                Log.w("RMFragment", "Unable to register fragment with root", e);
            }
        }
    }

    public void onDetach() {
        super.onDetach();
        f();
    }

    public void onStart() {
        super.onStart();
        this.a.a();
    }

    public void onStop() {
        super.onStop();
        this.a.b();
    }

    public void onDestroy() {
        super.onDestroy();
        this.a.c();
        f();
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(super.toString());
        stringBuilder.append("{parent=");
        stringBuilder.append(e());
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
}
