package com.gaana.revampeddetail.view;

import android.content.Context;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnItemTouchListener;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.collapsible_header.ObservableRecyclerView;
import com.fragments.BaseGaanaFragment;
import com.fragments.RevampedDetailListing;
import com.gaana.R;
import com.gaana.models.BusinessObject;
import com.gaana.revampeddetail.adapter.RevampedCarouselPagerAdapter;
import com.gaana.revampeddetail.model.RevampedCarouselData.CarouselCardData;
import com.gaana.view.item.BaseItemView;
import com.utilities.Util;
import java.util.ArrayList;

public class RevampedCarouselItemView extends BaseItemView {
    private static final int SWIPE_DOWN_THRESHOLD = Util.b(100);
    private ArrayList<CarouselCardData> carouselData;
    private RecyclerView carouselPager = null;
    private RevampedCarouselPagerAdapter carouselPagerAdapter = null;
    private int mDetailType;
    private GestureDetectorCompat mGestureDetector;
    private int mItemClickedPosition = -1;
    private ObservableRecyclerView mTrackRecyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    public RevampedCarouselItemView(Context context, BaseGaanaFragment baseGaanaFragment, int i) {
        super(context, baseGaanaFragment);
        this.mContext = context;
        this.mFragment = baseGaanaFragment;
        this.mDetailType = i;
        this.mGestureDetector = new GestureDetectorCompat(this.mContext, new OnGestureListener() {
            public boolean onDown(MotionEvent motionEvent) {
                return false;
            }

            public void onLongPress(MotionEvent motionEvent) {
            }

            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                return false;
            }

            public void onShowPress(MotionEvent motionEvent) {
            }

            public boolean onSingleTapUp(MotionEvent motionEvent) {
                return false;
            }

            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                if (motionEvent2.getY() - motionEvent.getY() > ((float) RevampedCarouselItemView.SWIPE_DOWN_THRESHOLD) && RevampedCarouselItemView.this.swipeRefreshLayout != null) {
                    ((RevampedDetailListing) RevampedCarouselItemView.this.mFragment).onRefresh();
                }
                return false;
            }
        });
        this.swipeRefreshLayout = ((RevampedDetailListing) this.mFragment).b();
        this.mTrackRecyclerView = ((RevampedDetailListing) this.mFragment).a();
    }

    public View getNewView(int i, ViewGroup viewGroup) {
        View newView = super.getNewView(i, viewGroup);
        this.carouselData = new ArrayList();
        this.carouselPager = (RecyclerView) newView.findViewById(R.id.carouselPager);
        this.carouselPager.addOnItemTouchListener(new OnItemTouchListener() {
            public void onRequestDisallowInterceptTouchEvent(boolean z) {
            }

            public void onTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
            }

            public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
                if (RevampedCarouselItemView.this.mFragment != null && RevampedCarouselItemView.this.mFragment.isAdded()) {
                    if (RevampedCarouselItemView.this.mTrackRecyclerView != null) {
                        RevampedCarouselItemView.this.mTrackRecyclerView.onTouchEvent(motionEvent);
                    }
                    if (!(((RevampedDetailListing) RevampedCarouselItemView.this.mFragment).c() == null || ((RevampedDetailListing) RevampedCarouselItemView.this.mFragment).c().getCurrentRecyclerView() == null)) {
                        ((RevampedDetailListing) RevampedCarouselItemView.this.mFragment).c().getCurrentRecyclerView().onTouchEvent(motionEvent);
                    }
                    RevampedCarouselItemView.this.mGestureDetector.onTouchEvent(motionEvent);
                }
                return false;
            }
        });
        this.carouselPagerAdapter = new RevampedCarouselPagerAdapter(this.mContext, this.mFragment, this.carouselData, this.carouselPager);
        this.carouselPager.setLayoutManager(new LinearLayoutManager(this.mContext, 0, false));
        this.carouselPager.setHasFixedSize(true);
        new PagerSnapHelper().attachToRecyclerView(this.carouselPager);
        this.carouselPager.addItemDecoration(new RevampedCarouselIndicatorDecoration());
        this.carouselPager.setAdapter(this.carouselPagerAdapter);
        this.carouselPagerAdapter.setCarouselData(this.carouselData, 0, this.mDetailType);
        this.carouselPagerAdapter.setCarouselListener(this);
        return newView;
    }

    public void getPopulatedView(ArrayList<CarouselCardData> arrayList) {
        this.carouselData = arrayList;
        this.carouselPagerAdapter.setCarouselData(this.carouselData, this.carouselData.size(), this.mDetailType);
    }

    public void onClickPerformed(View view, BusinessObject businessObject) {
        super.onClick(view);
    }

    public void setItemPosition(int i) {
        this.mItemClickedPosition = i;
    }

    private int dipToPixels(Context context, int i) {
        return (int) TypedValue.applyDimension(1, (float) i, context.getResources().getDisplayMetrics());
    }

    private int getScreenWidthinDp(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return (int) (((float) displayMetrics.widthPixels) / displayMetrics.density);
    }

    public RevampedCarouselPagerAdapter getCarouselPagerAdapter() {
        return this.carouselPagerAdapter;
    }
}
