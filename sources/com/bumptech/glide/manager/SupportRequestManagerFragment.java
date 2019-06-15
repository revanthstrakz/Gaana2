package com.bumptech.glide.manager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import com.bumptech.glide.e;
import com.bumptech.glide.i;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class SupportRequestManagerFragment extends Fragment {
    private final a a;
    private final l b;
    private final HashSet<SupportRequestManagerFragment> c;
    @Nullable
    private SupportRequestManagerFragment d;
    @Nullable
    private i e;
    @Nullable
    private Fragment f;

    private class a implements l {
        a() {
        }

        public Set<i> a() {
            Set<SupportRequestManagerFragment> d = SupportRequestManagerFragment.this.d();
            HashSet hashSet = new HashSet(d.size());
            for (SupportRequestManagerFragment supportRequestManagerFragment : d) {
                if (supportRequestManagerFragment.b() != null) {
                    hashSet.add(supportRequestManagerFragment.b());
                }
            }
            return hashSet;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(super.toString());
            stringBuilder.append("{fragment=");
            stringBuilder.append(SupportRequestManagerFragment.this);
            stringBuilder.append("}");
            return stringBuilder.toString();
        }
    }

    public SupportRequestManagerFragment() {
        this(new a());
    }

    @SuppressLint({"ValidFragment"})
    public SupportRequestManagerFragment(a aVar) {
        this.b = new a();
        this.c = new HashSet();
        this.a = aVar;
    }

    public void a(i iVar) {
        this.e = iVar;
    }

    /* Access modifiers changed, original: 0000 */
    public a a() {
        return this.a;
    }

    @Nullable
    public i b() {
        return this.e;
    }

    public l c() {
        return this.b;
    }

    private void a(SupportRequestManagerFragment supportRequestManagerFragment) {
        this.c.add(supportRequestManagerFragment);
    }

    private void b(SupportRequestManagerFragment supportRequestManagerFragment) {
        this.c.remove(supportRequestManagerFragment);
    }

    public Set<SupportRequestManagerFragment> d() {
        if (this.d == null) {
            return Collections.emptySet();
        }
        if (this.d == this) {
            return Collections.unmodifiableSet(this.c);
        }
        HashSet hashSet = new HashSet();
        for (SupportRequestManagerFragment supportRequestManagerFragment : this.d.d()) {
            if (b(supportRequestManagerFragment.e())) {
                hashSet.add(supportRequestManagerFragment);
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

    private Fragment e() {
        Fragment parentFragment = getParentFragment();
        return parentFragment != null ? parentFragment : this.f;
    }

    private boolean b(Fragment fragment) {
        Fragment e = e();
        while (fragment.getParentFragment() != null) {
            if (fragment.getParentFragment() == e) {
                return true;
            }
            fragment = fragment.getParentFragment();
        }
        return false;
    }

    private void a(FragmentActivity fragmentActivity) {
        f();
        this.d = e.b((Context) fragmentActivity).h().a(fragmentActivity.getSupportFragmentManager(), null);
        if (this.d != this) {
            this.d.a(this);
        }
    }

    private void f() {
        if (this.d != null) {
            this.d.b(this);
            this.d = null;
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            a(getActivity());
        } catch (IllegalStateException e) {
            if (Log.isLoggable("SupportRMFragment", 5)) {
                Log.w("SupportRMFragment", "Unable to register fragment with root", e);
            }
        }
    }

    public void onDetach() {
        super.onDetach();
        this.f = null;
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
