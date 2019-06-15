package com.gaana.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import com.gaana.view.ScrollableViewPagerNew;
import com.models.ListingButton;
import com.models.ListingComponents;

public class ViewPagerAdapter extends PagerAdapter {
    private AddItemListner mAddItemListner;
    private int mCurrentPosition = -1;
    private ListingButton mListingButton = null;
    private ListingComponents mListingComponents = null;
    private int mPageCount = 0;

    public interface AddItemListner {
        Object addItem(ViewGroup viewGroup, int i);

        Object addItem(ViewGroup viewGroup, ListingButton listingButton);
    }

    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public int getCount() {
        return this.mPageCount;
    }

    public Object instantiateItem(ViewGroup viewGroup, int i) {
        if (this.mListingButton != null) {
            return this.mAddItemListner.addItem(viewGroup, this.mListingButton);
        }
        return this.mAddItemListner.addItem(viewGroup, i);
    }

    public void setPrimaryItem(@NonNull ViewGroup viewGroup, int i, @NonNull Object obj) {
        super.setPrimaryItem(viewGroup, i, obj);
        if ((viewGroup instanceof ScrollableViewPagerNew) && i != this.mCurrentPosition) {
            ScrollableViewPagerNew scrollableViewPagerNew = (ScrollableViewPagerNew) viewGroup;
            View view = (View) obj;
            if (view != null) {
                this.mCurrentPosition = i;
                scrollableViewPagerNew.measureCurrentView(view);
            }
        }
    }

    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }

    public void setAdapterParams(int i, AddItemListner addItemListner) {
        this.mPageCount = i;
        this.mAddItemListner = addItemListner;
    }

    public void setAdapterParams(int i, AddItemListner addItemListner, ListingComponents listingComponents) {
        this.mPageCount = i;
        this.mAddItemListner = addItemListner;
        this.mListingComponents = listingComponents;
    }

    public void setAdapterParams(ListingButton listingButton, AddItemListner addItemListner) {
        this.mPageCount = 1;
        this.mAddItemListner = addItemListner;
        this.mListingButton = listingButton;
    }

    public CharSequence getPageTitle(int i) {
        return (this.mListingComponents == null || this.mListingComponents.c() == null) ? null : ((ListingButton) this.mListingComponents.c().get(i)).d();
    }
}
