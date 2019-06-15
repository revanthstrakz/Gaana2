package android.arch.lifecycle;

import android.app.Activity;
import android.app.Application.ActivityLifecycleCallbacks;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.annotation.RestrictTo.Scope;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentManager.FragmentLifecycleCallbacks;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;

@RestrictTo({Scope.LIBRARY_GROUP})
public class HolderFragment extends Fragment {
    private static final a a = new a();
    private r b = new r();

    static class a {
        private Map<Activity, HolderFragment> a = new HashMap();
        private Map<Fragment, HolderFragment> b = new HashMap();
        private ActivityLifecycleCallbacks c = new b() {
            public void onActivityDestroyed(Activity activity) {
                if (((HolderFragment) a.this.a.remove(activity)) != null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Failed to save a ViewModel for ");
                    stringBuilder.append(activity);
                    Log.e("ViewModelStores", stringBuilder.toString());
                }
            }
        };
        private boolean d = false;
        private FragmentLifecycleCallbacks e = new FragmentLifecycleCallbacks() {
            public void onFragmentDestroyed(FragmentManager fragmentManager, Fragment fragment) {
                super.onFragmentDestroyed(fragmentManager, fragment);
                if (((HolderFragment) a.this.b.remove(fragment)) != null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Failed to save a ViewModel for ");
                    stringBuilder.append(fragment);
                    Log.e("ViewModelStores", stringBuilder.toString());
                }
            }
        };

        a() {
        }

        /* Access modifiers changed, original: 0000 */
        public void a(Fragment fragment) {
            Fragment parentFragment = fragment.getParentFragment();
            if (parentFragment != null) {
                this.b.remove(parentFragment);
                parentFragment.getFragmentManager().unregisterFragmentLifecycleCallbacks(this.e);
                return;
            }
            this.a.remove(fragment.getActivity());
        }
    }

    public HolderFragment() {
        setRetainInstance(true);
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        a.a((Fragment) this);
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    public void onDestroy() {
        super.onDestroy();
        this.b.a();
    }

    @NonNull
    public r getViewModelStore() {
        return this.b;
    }
}
