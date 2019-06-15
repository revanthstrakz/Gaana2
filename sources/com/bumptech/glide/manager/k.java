package com.bumptech.glide.manager;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.app.FragmentManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import android.view.View;
import com.bumptech.glide.e;
import com.bumptech.glide.f.h;
import com.bumptech.glide.i;
import com.payu.custombrowser.util.CBConstant;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class k implements Callback {
    private static final a i = new a() {
        public i a(e eVar, h hVar, l lVar, Context context) {
            return new i(eVar, hVar, lVar, context);
        }
    };
    final Map<FragmentManager, RequestManagerFragment> a = new HashMap();
    final Map<android.support.v4.app.FragmentManager, SupportRequestManagerFragment> b = new HashMap();
    private volatile i c;
    private final Handler d;
    private final a e;
    private final ArrayMap<View, Fragment> f = new ArrayMap();
    private final ArrayMap<View, android.app.Fragment> g = new ArrayMap();
    private final Bundle h = new Bundle();

    public interface a {
        i a(e eVar, h hVar, l lVar, Context context);
    }

    public k(@Nullable a aVar) {
        if (aVar == null) {
            aVar = i;
        }
        this.e = aVar;
        this.d = new Handler(Looper.getMainLooper(), this);
    }

    private i b(Context context) {
        if (this.c == null) {
            synchronized (this) {
                if (this.c == null) {
                    this.c = this.e.a(e.b(context.getApplicationContext()), new b(), new g(), context.getApplicationContext());
                }
            }
        }
        return this.c;
    }

    public i a(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("You cannot start a load on a null Context");
        }
        if (com.bumptech.glide.f.i.c() && !(context instanceof Application)) {
            if (context instanceof FragmentActivity) {
                return a((FragmentActivity) context);
            }
            if (context instanceof Activity) {
                return a((Activity) context);
            }
            if (context instanceof ContextWrapper) {
                return a(((ContextWrapper) context).getBaseContext());
            }
        }
        return b(context);
    }

    public i a(FragmentActivity fragmentActivity) {
        if (com.bumptech.glide.f.i.d()) {
            return a(fragmentActivity.getApplicationContext());
        }
        b((Activity) fragmentActivity);
        return a((Context) fragmentActivity, fragmentActivity.getSupportFragmentManager(), null);
    }

    public i a(Fragment fragment) {
        h.a(fragment.getActivity(), "You cannot start a load on a fragment before it is attached or after it is destroyed");
        if (com.bumptech.glide.f.i.d()) {
            return a(fragment.getActivity().getApplicationContext());
        }
        return a(fragment.getActivity(), fragment.getChildFragmentManager(), fragment);
    }

    public i a(Activity activity) {
        if (com.bumptech.glide.f.i.d()) {
            return a(activity.getApplicationContext());
        }
        b(activity);
        return a((Context) activity, activity.getFragmentManager(), null);
    }

    public i a(View view) {
        if (com.bumptech.glide.f.i.d()) {
            return a(view.getContext().getApplicationContext());
        }
        h.a((Object) view);
        h.a(view.getContext(), "Unable to obtain a request manager for a view without a Context");
        Activity c = c(view.getContext());
        if (c == null) {
            return a(view.getContext().getApplicationContext());
        }
        if (c instanceof FragmentActivity) {
            Fragment a = a(view, (FragmentActivity) c);
            return a != null ? a(a) : a(c);
        }
        android.app.Fragment a2 = a(view, c);
        if (a2 == null) {
            return a(c);
        }
        return a(a2);
    }

    private static void a(@Nullable Collection<Fragment> collection, Map<View, Fragment> map) {
        if (collection != null) {
            for (Fragment fragment : collection) {
                if (fragment != null) {
                    if (fragment.getView() != null) {
                        map.put(fragment.getView(), fragment);
                        a(fragment.getChildFragmentManager().getFragments(), (Map) map);
                    }
                }
            }
        }
    }

    @Nullable
    private Fragment a(View view, FragmentActivity fragmentActivity) {
        this.f.clear();
        a(fragmentActivity.getSupportFragmentManager().getFragments(), this.f);
        View findViewById = fragmentActivity.findViewById(16908290);
        Fragment fragment = null;
        while (!view.equals(findViewById)) {
            fragment = (Fragment) this.f.get(view);
            if (fragment != null || !(view.getParent() instanceof View)) {
                break;
            }
            view = (View) view.getParent();
        }
        this.f.clear();
        return fragment;
    }

    @Nullable
    private android.app.Fragment a(View view, Activity activity) {
        this.g.clear();
        a(activity.getFragmentManager(), this.g);
        View findViewById = activity.findViewById(16908290);
        android.app.Fragment fragment = null;
        while (!view.equals(findViewById)) {
            fragment = (android.app.Fragment) this.g.get(view);
            if (fragment != null || !(view.getParent() instanceof View)) {
                break;
            }
            view = (View) view.getParent();
        }
        this.g.clear();
        return fragment;
    }

    @TargetApi(26)
    private void a(FragmentManager fragmentManager, ArrayMap<View, android.app.Fragment> arrayMap) {
        if (VERSION.SDK_INT >= 26) {
            for (android.app.Fragment fragment : fragmentManager.getFragments()) {
                if (fragment.getView() != null) {
                    arrayMap.put(fragment.getView(), fragment);
                    a(fragment.getChildFragmentManager(), (ArrayMap) arrayMap);
                }
            }
            return;
        }
        b(fragmentManager, arrayMap);
    }

    private void b(FragmentManager fragmentManager, ArrayMap<View, android.app.Fragment> arrayMap) {
        int i = 0;
        while (true) {
            int i2 = i + 1;
            this.h.putInt(CBConstant.KEY, i);
            android.app.Fragment fragment = null;
            try {
                fragment = fragmentManager.getFragment(this.h, CBConstant.KEY);
            } catch (Exception unused) {
            }
            if (fragment != null) {
                if (fragment.getView() != null) {
                    arrayMap.put(fragment.getView(), fragment);
                    if (VERSION.SDK_INT >= 17) {
                        a(fragment.getChildFragmentManager(), (ArrayMap) arrayMap);
                    }
                }
                i = i2;
            } else {
                return;
            }
        }
    }

    private Activity c(Context context) {
        if (context instanceof Activity) {
            return (Activity) context;
        }
        return context instanceof ContextWrapper ? c(((ContextWrapper) context).getBaseContext()) : null;
    }

    @TargetApi(17)
    private static void b(Activity activity) {
        if (VERSION.SDK_INT >= 17 && activity.isDestroyed()) {
            throw new IllegalArgumentException("You cannot start a load for a destroyed activity");
        }
    }

    @TargetApi(17)
    public i a(android.app.Fragment fragment) {
        if (fragment.getActivity() == null) {
            throw new IllegalArgumentException("You cannot start a load on a fragment before it is attached");
        } else if (com.bumptech.glide.f.i.d() || VERSION.SDK_INT < 17) {
            return a(fragment.getActivity().getApplicationContext());
        } else {
            return a(fragment.getActivity(), fragment.getChildFragmentManager(), fragment);
        }
    }

    /* Access modifiers changed, original: 0000 */
    @TargetApi(17)
    public RequestManagerFragment a(FragmentManager fragmentManager, android.app.Fragment fragment) {
        RequestManagerFragment requestManagerFragment = (RequestManagerFragment) fragmentManager.findFragmentByTag("com.bumptech.glide.manager");
        if (requestManagerFragment != null) {
            return requestManagerFragment;
        }
        requestManagerFragment = (RequestManagerFragment) this.a.get(fragmentManager);
        if (requestManagerFragment != null) {
            return requestManagerFragment;
        }
        requestManagerFragment = new RequestManagerFragment();
        requestManagerFragment.a(fragment);
        this.a.put(fragmentManager, requestManagerFragment);
        fragmentManager.beginTransaction().add(requestManagerFragment, "com.bumptech.glide.manager").commitAllowingStateLoss();
        this.d.obtainMessage(1, fragmentManager).sendToTarget();
        return requestManagerFragment;
    }

    private i a(Context context, FragmentManager fragmentManager, android.app.Fragment fragment) {
        RequestManagerFragment a = a(fragmentManager, fragment);
        i b = a.b();
        if (b != null) {
            return b;
        }
        b = this.e.a(e.b(context), a.a(), a.c(), context);
        a.a(b);
        return b;
    }

    /* Access modifiers changed, original: 0000 */
    public SupportRequestManagerFragment a(android.support.v4.app.FragmentManager fragmentManager, Fragment fragment) {
        SupportRequestManagerFragment supportRequestManagerFragment = (SupportRequestManagerFragment) fragmentManager.findFragmentByTag("com.bumptech.glide.manager");
        if (supportRequestManagerFragment != null) {
            return supportRequestManagerFragment;
        }
        supportRequestManagerFragment = (SupportRequestManagerFragment) this.b.get(fragmentManager);
        if (supportRequestManagerFragment != null) {
            return supportRequestManagerFragment;
        }
        Fragment supportRequestManagerFragment2 = new SupportRequestManagerFragment();
        supportRequestManagerFragment2.a(fragment);
        this.b.put(fragmentManager, supportRequestManagerFragment2);
        fragmentManager.beginTransaction().add(supportRequestManagerFragment2, "com.bumptech.glide.manager").commitAllowingStateLoss();
        this.d.obtainMessage(2, fragmentManager).sendToTarget();
        return supportRequestManagerFragment2;
    }

    private i a(Context context, android.support.v4.app.FragmentManager fragmentManager, Fragment fragment) {
        SupportRequestManagerFragment a = a(fragmentManager, fragment);
        i b = a.b();
        if (b != null) {
            return b;
        }
        b = this.e.a(e.b(context), a.a(), a.c(), context);
        a.a(b);
        return b;
    }

    public boolean handleMessage(Message message) {
        Object obj = null;
        boolean z = true;
        Object remove;
        switch (message.what) {
            case 1:
                obj = (FragmentManager) message.obj;
                remove = this.a.remove(obj);
                break;
            case 2:
                obj = (android.support.v4.app.FragmentManager) message.obj;
                remove = this.b.remove(obj);
                break;
            default:
                z = false;
                remove = null;
                break;
        }
        if (z && remove == null && Log.isLoggable("RMRetriever", 5)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Failed to remove expected request manager fragment, manager: ");
            stringBuilder.append(obj);
            Log.w("RMRetriever", stringBuilder.toString());
        }
        return z;
    }
}
