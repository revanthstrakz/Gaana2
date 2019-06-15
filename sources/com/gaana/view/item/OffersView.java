package com.gaana.view.item;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import com.constants.Constants;
import com.fragments.BaseGaanaFragment;
import com.gaana.BaseActivity;
import com.gaana.R;
import com.gaana.models.BusinessObject;
import com.gaana.view.header.CirclePageIndicator;
import com.gaana.view.header.OffersPagerAdapter;
import com.managers.ab;
import com.managers.ab.a;

public class OffersView extends BaseItemView implements a {
    private OffersPagerAdapter mAdapter;
    private CirclePageIndicator mCirclePageIndicator;
    private ImageView mOfferBubble;
    private ViewGroup mOffersHeader;
    private ImageView mOffersHide;
    private ImageView mOffersOpen;
    private ViewPager mViewPager;
    private ViewGroup mViewPagerContainer;
    boolean offersVisible;

    public static class OffersViewHolder extends ViewHolder {
        public CirclePageIndicator circlePageIndicator;
        public ViewGroup headerContainer;
        public ImageView offerBubble;
        public ImageView offersHide;
        public ImageView offersOpen;
        public ViewGroup pagerContainer;
        public ViewPager viewPager;

        public OffersViewHolder(View view) {
            super(view);
            this.pagerContainer = (ViewGroup) view.findViewById(R.id.pager_container);
            this.viewPager = (ViewPager) view.findViewById(R.id.viewPager);
            this.circlePageIndicator = (CirclePageIndicator) view.findViewById(R.id.view_pager_indicator);
            this.headerContainer = (ViewGroup) view.findViewById(R.id.header_offer_view);
            this.offerBubble = (ImageView) view.findViewById(R.id.offer_view_header_bubble);
            this.offersOpen = (ImageView) view.findViewById(R.id.offer_view_header_toggle_up);
            this.offersHide = (ImageView) view.findViewById(R.id.offer_view_header_toggle_down);
        }
    }

    public OffersView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        this.offersVisible = true;
        this.mLayoutId = R.layout.view_offers;
        this.mAdapter = new OffersPagerAdapter(context);
        ab.a().a((a) this);
    }

    public View getOffersView() {
        return this.mView;
    }

    public OffersViewHolder getOffersViewHolder() {
        return new OffersViewHolder(inflateView(this.mLayoutId, null));
    }

    public View createViewHolder(ViewGroup viewGroup, int i) {
        return super.createViewHolder(viewGroup, i);
    }

    public View getPoplatedView(ViewHolder viewHolder, BusinessObject businessObject, ViewGroup viewGroup) {
        OffersViewHolder offersViewHolder = (OffersViewHolder) viewHolder;
        this.mOfferBubble = offersViewHolder.offerBubble;
        this.mOffersOpen = offersViewHolder.offersOpen;
        this.mOffersHide = offersViewHolder.offersHide;
        this.mViewPager = offersViewHolder.viewPager;
        this.mViewPagerContainer = offersViewHolder.pagerContainer;
        this.mCirclePageIndicator = offersViewHolder.circlePageIndicator;
        this.mOffersHeader = offersViewHolder.headerContainer;
        this.mViewPager.setPageMargin(30);
        this.mViewPager.setOffscreenPageLimit(2);
        this.mViewPager.setClipChildren(false);
        if (this.mViewPager.getAdapter() == null) {
            this.mViewPager.setAdapter(this.mAdapter);
        }
        this.mViewPagerContainer.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return OffersView.this.mViewPager.dispatchTouchEvent(motionEvent);
            }
        });
        this.mCirclePageIndicator.setViewPager(this.mViewPager);
        if (Constants.l) {
            this.mCirclePageIndicator.setFillColor(getResources().getColor(R.color.red_gaana));
        }
        this.mOffersHeader.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (OffersView.this.offersVisible) {
                    OffersView.collapse(OffersView.this.mViewPagerContainer);
                    OffersView.this.offersVisible = false;
                    OffersView.this.mOffersOpen.setVisibility(8);
                    OffersView.this.mOffersHide.setVisibility(0);
                    return;
                }
                OffersView.expand(OffersView.this.mViewPagerContainer);
                OffersView.this.offersVisible = true;
                OffersView.this.mOffersHide.setVisibility(8);
                OffersView.this.mOffersOpen.setVisibility(0);
            }
        });
        if (ab.a().e() > 0) {
            this.mOfferBubble.setVisibility(0);
            ab.a().a(true);
        } else {
            this.mOfferBubble.setVisibility(8);
            ab.a().a(false);
        }
        return viewHolder.itemView;
    }

    public void onAllOffersSeen() {
        if (this.mOfferBubble != null) {
            ((BaseActivity) this.mContext).sendGAEvent("NotificationScreen", "All offers seen", "NotificationScreen-All offers seen");
            this.mOfferBubble.setVisibility(8);
        }
    }

    public static void expand(final View view) {
        view.measure(-1, -2);
        final int measuredHeight = view.getMeasuredHeight();
        view.getLayoutParams().height = 1;
        view.setVisibility(0);
        AnonymousClass3 anonymousClass3 = new Animation() {
            public boolean willChangeBounds() {
                return true;
            }

            /* Access modifiers changed, original: protected */
            public void applyTransformation(float f, Transformation transformation) {
                view.getLayoutParams().height = f == 1.0f ? -2 : (int) (((float) measuredHeight) * f);
                view.requestLayout();
            }
        };
        anonymousClass3.setDuration((long) ((int) (((float) measuredHeight) / view.getContext().getResources().getDisplayMetrics().density)));
        view.startAnimation(anonymousClass3);
    }

    public static void collapse(final View view) {
        final int measuredHeight = view.getMeasuredHeight();
        AnonymousClass4 anonymousClass4 = new Animation() {
            public boolean willChangeBounds() {
                return true;
            }

            /* Access modifiers changed, original: protected */
            public void applyTransformation(float f, Transformation transformation) {
                if (f == 1.0f) {
                    view.setVisibility(8);
                    return;
                }
                view.getLayoutParams().height = measuredHeight - ((int) (((float) measuredHeight) * f));
                view.requestLayout();
            }
        };
        anonymousClass4.setDuration((long) ((int) (((float) measuredHeight) / view.getContext().getResources().getDisplayMetrics().density)));
        view.startAnimation(anonymousClass4);
    }
}
