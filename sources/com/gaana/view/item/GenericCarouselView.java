package com.gaana.view.item;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import com.fragments.BaseGaanaFragment;
import com.fragments.BaseGaanaFragment.a;
import com.fragments.MyMusicFragment;
import com.fragments.PaymentDetailFragment;
import com.gaana.R;
import com.gaana.models.PaymentProductDetailModel.CarouselOfferConfig;
import com.gaana.models.PaymentProductModel.ProductItem;
import com.gaana.view.header.GenericCarouselAdapter;
import java.util.ArrayList;

public class GenericCarouselView extends BaseItemView {
    private Handler autoScrollHandler = null;
    private long carousalPagerTime = 40000;
    private GenericCarouselAdapter carouselAdapter = null;
    private ArrayList<CarouselOfferConfig> carouselData = null;
    OnPageChangeListener carouselPageChangeListener = new OnPageChangeListener() {
        public void onPageScrolled(int i, float f, int i2) {
        }

        public void onPageSelected(int i) {
            if (i < GenericCarouselView.this.carouselData.size()) {
                GenericCarouselView.this.carouselPager.setPadding(GenericCarouselView.this.paddingLeft, 0, GenericCarouselView.this.paddingRight, 0);
            }
        }

        public void onPageScrollStateChanged(int i) {
            if (i == 1) {
                GenericCarouselView.this.m_SCROLL_STATE_DRAGGING = true;
            }
        }
    };
    private ViewPager carouselPager = null;
    private boolean isTimerStart = false;
    private int itemLayoutID = -1;
    private Context mContext = null;
    private BaseGaanaFragment mFragment;
    private boolean m_SCROLL_STATE_DRAGGING = false;
    private int paddingLeft = 0;
    private int paddingRight = 0;
    private boolean scroll_animation = true;
    private long timeLeft = this.carousalPagerTime;

    public GenericCarouselView(Context context, BaseGaanaFragment baseGaanaFragment, int i, int i2, int i3) {
        super(context, baseGaanaFragment);
        this.mContext = context;
        this.mFragment = baseGaanaFragment;
        this.itemLayoutID = i;
        this.paddingLeft = dipToPixels(this.mContext, i2);
        this.paddingRight = dipToPixels(this.mContext, i3);
    }

    public void setCarouselData(ArrayList<CarouselOfferConfig> arrayList) {
        this.carouselData = arrayList;
    }

    public View getNewView(int i, ViewGroup viewGroup) {
        View newView = super.getNewView(i, viewGroup);
        this.carouselPager = (ViewPager) newView.findViewById(R.id.carouselPager);
        this.carouselAdapter = new GenericCarouselAdapter(this.mContext, this.carouselData);
        this.carouselAdapter.setLayoutId(this.itemLayoutID);
        this.carouselPager.setAdapter(this.carouselAdapter);
        this.carouselPager.addOnPageChangeListener(this.carouselPageChangeListener);
        this.carouselAdapter.setCarouselData(this.carouselData);
        this.carouselAdapter.setCarouselListener(this);
        this.mFragment.addFragmentListener("GENERIC_CAROUSEL_VIEW_FRAGMENT_LISTENER", new a() {
            public void onDestroy() {
                GenericCarouselView.this.carouselPager.removeOnPageChangeListener(GenericCarouselView.this.carouselPageChangeListener);
            }
        });
        this.carouselPager.setPadding(this.paddingLeft, 0, this.paddingRight, 0);
        if (this.autoScrollHandler == null) {
            this.autoScrollHandler = new Handler();
        }
        startAutoScroll();
        return newView;
    }

    private int dipToPixels(Context context, int i) {
        return (int) TypedValue.applyDimension(1, (float) i, context.getResources().getDisplayMetrics());
    }

    private void startAutoScroll() {
        this.autoScrollHandler.postDelayed(new Runnable() {
            public void run() {
                GenericCarouselView.this.timeLeft = 1;
                GenericCarouselView.this.autoScrollByHandler(GenericCarouselView.this.carouselPager);
            }
        }, 4000);
    }

    private void autoScrollByHandler(ViewPager viewPager) {
        if (this.timeLeft <= 0) {
            this.isTimerStart = false;
            return;
        }
        int count = this.carouselAdapter != null ? this.carouselAdapter.getCount() : this.carouselData.size();
        if ((!(this.mFragment instanceof PaymentDetailFragment) && !(this.mFragment instanceof MyMusicFragment)) || !this.mFragment.getUserVisibleHint()) {
            this.timeLeft = -1;
            this.isTimerStart = false;
        } else if (!this.m_SCROLL_STATE_DRAGGING) {
            int currentItem = viewPager.getCurrentItem();
            if (currentItem == count - 1) {
                currentItem = -1;
                this.scroll_animation = false;
            }
            if (this.scroll_animation) {
                viewPager.setCurrentItem(currentItem + 1, true);
            } else {
                viewPager.setCurrentItem(currentItem + 1, false);
                this.scroll_animation = true;
            }
            this.isTimerStart = true;
            handleAutoScrollRunnableCall(viewPager.getCurrentItem());
        }
    }

    private void handleAutoScrollRunnableCall(int i) {
        AnonymousClass4 anonymousClass4 = new Runnable() {
            public void run() {
                GenericCarouselView.this.autoScrollByHandler(GenericCarouselView.this.carouselPager);
            }
        };
        if (this.carouselData != null && this.carouselData.size() > 0) {
            this.autoScrollHandler.postDelayed(anonymousClass4, 4000);
        }
    }

    /* Access modifiers changed, original: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.autoScrollHandler != null) {
            this.autoScrollHandler.removeCallbacks(null);
        }
    }

    public void onClickPerformed(View view, ProductItem productItem) {
        if ((this.mFragment instanceof PaymentDetailFragment) && this.mFragment.getUserVisibleHint() && productItem != null) {
            ((PaymentDetailFragment) this.mFragment).b(productItem);
        } else if ((this.mFragment instanceof MyMusicFragment) && this.mFragment.getUserVisibleHint() && productItem != null) {
            ((MyMusicFragment) this.mFragment).a(productItem);
        }
    }
}
