package com.helpshift.support.compositions;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.helpshift.support.FaqTagFilter;
import com.helpshift.support.Section;
import com.helpshift.support.fragments.QuestionListFragment;
import java.util.List;

public class a extends FragmentStatePagerAdapter {
    private FaqTagFilter a;
    private List<Section> b;

    public a(FragmentManager fragmentManager, List<Section> list, FaqTagFilter faqTagFilter) {
        super(fragmentManager);
        this.b = list;
        this.a = faqTagFilter;
    }

    public Fragment getItem(int i) {
        Bundle bundle = new Bundle();
        bundle.putString("sectionPublishId", ((Section) this.b.get(i)).a());
        bundle.putSerializable("withTagsMatching", this.a);
        return QuestionListFragment.a(bundle);
    }

    public int getCount() {
        return this.b.size();
    }

    public CharSequence getPageTitle(int i) {
        return ((Section) this.b.get(i)).b();
    }
}
