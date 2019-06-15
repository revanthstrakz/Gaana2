package android.arch.lifecycle;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.arch.lifecycle.Lifecycle.Event;
import android.os.Bundle;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;

@RestrictTo({Scope.LIBRARY_GROUP})
public class ReportFragment extends Fragment {
    private a a;

    interface a {
        void a();

        void b();

        void c();
    }

    public static void a(Activity activity) {
        FragmentManager fragmentManager = activity.getFragmentManager();
        if (fragmentManager.findFragmentByTag("android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag") == null) {
            fragmentManager.beginTransaction().add(new ReportFragment(), "android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag").commit();
            fragmentManager.executePendingTransactions();
        }
    }

    static ReportFragment b(Activity activity) {
        return (ReportFragment) activity.getFragmentManager().findFragmentByTag("android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag");
    }

    private void b(a aVar) {
        if (aVar != null) {
            aVar.a();
        }
    }

    private void c(a aVar) {
        if (aVar != null) {
            aVar.b();
        }
    }

    private void d(a aVar) {
        if (aVar != null) {
            aVar.c();
        }
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        b(this.a);
        a(Event.ON_CREATE);
    }

    public void onStart() {
        super.onStart();
        c(this.a);
        a(Event.ON_START);
    }

    public void onResume() {
        super.onResume();
        d(this.a);
        a(Event.ON_RESUME);
    }

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
        this.a = null;
    }

    private void a(Event event) {
        Activity activity = getActivity();
        if (activity instanceof g) {
            ((g) activity).a().a(event);
            return;
        }
        if (activity instanceof e) {
            Lifecycle lifecycle = ((e) activity).getLifecycle();
            if (lifecycle instanceof f) {
                ((f) lifecycle).a(event);
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(a aVar) {
        this.a = aVar;
    }
}
