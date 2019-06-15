package android.support.v4.app;

import android.arch.lifecycle.r;
import java.util.List;

public class FragmentManagerNonConfig {
    private final List<FragmentManagerNonConfig> mChildNonConfigs;
    private final List<Fragment> mFragments;
    private final List<r> mViewModelStores;

    FragmentManagerNonConfig(List<Fragment> list, List<FragmentManagerNonConfig> list2, List<r> list3) {
        this.mFragments = list;
        this.mChildNonConfigs = list2;
        this.mViewModelStores = list3;
    }

    /* Access modifiers changed, original: 0000 */
    public List<Fragment> getFragments() {
        return this.mFragments;
    }

    /* Access modifiers changed, original: 0000 */
    public List<FragmentManagerNonConfig> getChildNonConfigs() {
        return this.mChildNonConfigs;
    }

    /* Access modifiers changed, original: 0000 */
    public List<r> getViewModelStores() {
        return this.mViewModelStores;
    }
}
