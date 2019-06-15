package com.collapsible_header;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;
import com.gaana.GaanaActivity;
import com.gaana.application.GaanaApplication;
import com.google.android.exoplayer2.C;

public abstract class b extends FragmentStatePagerAdapter {
    private static final String STATE_PAGES = "pages";
    private static final String STATE_PAGE_INDEX_PREFIX = "pageIndex:";
    private static final String STATE_PAGE_KEY_PREFIX = "page:";
    protected static final String STATE_SUPER_STATE = "superState";
    private FragmentManager mFm;
    private SparseArray<Fragment> mPages = new SparseArray();

    public abstract Fragment createItem(int i);

    public b(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.mFm = fragmentManager;
    }

    public Parcelable saveState() {
        Parcelable saveState = super.saveState();
        Bundle bundle = new Bundle();
        bundle.putParcelable(STATE_SUPER_STATE, saveState);
        bundle.putInt(STATE_PAGES, this.mPages.size());
        if (this.mPages.size() > 0) {
            for (int i = 0; i < this.mPages.size(); i++) {
                int keyAt = this.mPages.keyAt(i);
                bundle.putInt(createCacheIndex(i), keyAt);
                this.mFm.putFragment(bundle, createCacheKey(keyAt), (Fragment) this.mPages.get(keyAt));
            }
        }
        return bundle;
    }

    public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
        try {
            Bundle bundle = (Bundle) parcelable;
            int i = bundle.getInt(STATE_PAGES);
            if (i > 0) {
                for (int i2 = 0; i2 < i; i2++) {
                    int i3 = bundle.getInt(createCacheIndex(i2));
                    this.mPages.put(i3, this.mFm.getFragment(bundle, createCacheKey(i3)));
                }
            }
            super.restoreState(bundle.getParcelable(STATE_SUPER_STATE), classLoader);
        } catch (Exception unused) {
            Intent intent = new Intent(GaanaApplication.getContext(), GaanaActivity.class);
            intent.addFlags(67141632);
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
            GaanaApplication.getContext().startActivity(intent);
        }
    }

    public Fragment getItem(int i) {
        Fragment createItem = createItem(i);
        this.mPages.put(i, createItem);
        return createItem;
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        if (this.mPages.indexOfKey(i) >= 0) {
            this.mPages.remove(i);
        }
        super.destroyItem(viewGroup, i, obj);
    }

    public Fragment getItemAt(int i) {
        return (Fragment) this.mPages.get(i);
    }

    /* Access modifiers changed, original: protected */
    public String createCacheIndex(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(STATE_PAGE_INDEX_PREFIX);
        stringBuilder.append(i);
        return stringBuilder.toString();
    }

    /* Access modifiers changed, original: protected */
    public String createCacheKey(int i) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(STATE_PAGE_KEY_PREFIX);
        stringBuilder.append(i);
        return stringBuilder.toString();
    }
}
