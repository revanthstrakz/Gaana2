package android.arch.lifecycle;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.Lifecycle.Event;
import android.arch.lifecycle.Lifecycle.State;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.FragmentLifecycleCallbacks;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

class LifecycleDispatcher {
    private static AtomicBoolean a = new AtomicBoolean(false);

    public static class DestructionReportFragment extends Fragment {
        public void onPause() {
            super.onPause();
            a(Event.ON_PAUSE);
        }

        public void onStop() {
            super.onStop();
            a(Event.ON_STOP);
        }

        public void onDestroy() {
            super.onDestroy();
            a(Event.ON_DESTROY);
        }

        /* Access modifiers changed, original: protected */
        public void a(Event event) {
            LifecycleDispatcher.b(getParentFragment(), event);
        }
    }

    @VisibleForTesting
    static class a extends b {
        private final b a = new b();

        a() {
        }

        public void onActivityCreated(Activity activity, Bundle bundle) {
            if (activity instanceof FragmentActivity) {
                ((FragmentActivity) activity).getSupportFragmentManager().registerFragmentLifecycleCallbacks(this.a, true);
            }
            ReportFragment.a(activity);
        }

        public void onActivityStopped(Activity activity) {
            if (activity instanceof FragmentActivity) {
                LifecycleDispatcher.b((FragmentActivity) activity, State.CREATED);
            }
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
            if (activity instanceof FragmentActivity) {
                LifecycleDispatcher.b((FragmentActivity) activity, State.CREATED);
            }
        }
    }

    @VisibleForTesting
    static class b extends FragmentLifecycleCallbacks {
        b() {
        }

        public void onFragmentCreated(FragmentManager fragmentManager, Fragment fragment, Bundle bundle) {
            LifecycleDispatcher.b(fragment, Event.ON_CREATE);
            if ((fragment instanceof g) && fragment.getChildFragmentManager().findFragmentByTag("android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag") == null) {
                fragment.getChildFragmentManager().beginTransaction().add(new DestructionReportFragment(), "android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag").commit();
            }
        }

        public void onFragmentStarted(FragmentManager fragmentManager, Fragment fragment) {
            LifecycleDispatcher.b(fragment, Event.ON_START);
        }

        public void onFragmentResumed(FragmentManager fragmentManager, Fragment fragment) {
            LifecycleDispatcher.b(fragment, Event.ON_RESUME);
        }
    }

    static void a(Context context) {
        if (!a.getAndSet(true)) {
            ((Application) context.getApplicationContext()).registerActivityLifecycleCallbacks(new a());
        }
    }

    private static void a(FragmentManager fragmentManager, State state) {
        List<Object> fragments = fragmentManager.getFragments();
        if (fragments != null) {
            for (Object obj : fragments) {
                if (obj != null) {
                    a(obj, state);
                    if (obj.isAdded()) {
                        a(obj.getChildFragmentManager(), state);
                    }
                }
            }
        }
    }

    private static void a(Object obj, State state) {
        if (obj instanceof g) {
            ((g) obj).a().a(state);
        }
    }

    private static void b(FragmentActivity fragmentActivity, State state) {
        a((Object) fragmentActivity, state);
        a(fragmentActivity.getSupportFragmentManager(), state);
    }

    private static void b(Fragment fragment, Event event) {
        if (fragment instanceof g) {
            ((g) fragment).a().a(event);
        }
    }
}
