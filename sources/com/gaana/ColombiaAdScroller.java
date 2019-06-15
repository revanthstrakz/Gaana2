package com.gaana;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.gaana.view.BaseItemView;
import com.library.controls.CrossFadeImageView;
import com.til.colombia.android.service.AdView;
import com.til.colombia.android.service.Item;
import com.til.colombia.android.service.ItemResponse;
import com.utilities.Util;
import com.utilities.d;
import com.views.HorizontalRecyclerView;
import java.util.List;

public class ColombiaAdScroller extends BaseItemView {
    private HorizontalRecyclerView horizontalScroller;
    private Context mContext;
    List<Item> paidItems;
    private String subTitle;
    private String title;
    private View view;

    public class ColombiaAdScrollerItemHolder extends ViewHolder {
        CrossFadeImageView adImage = ((CrossFadeImageView) this.mView.findViewById(R.id.ad_image));
        Button ctaText = ((Button) this.mView.findViewById(R.id.cta_text));
        TextView description = ((TextView) this.mView.findViewById(R.id.description));
        View mView;

        public ColombiaAdScrollerItemHolder(View view) {
            super(view);
            this.mView = view;
        }
    }

    public ColombiaAdScroller(Context context, String str, String str2) {
        super(context, null);
        this.mContext = context;
        initUI();
    }

    public ColombiaAdScroller(Context context, AttributeSet attributeSet) {
        super(context, null, attributeSet);
        this.mContext = context;
        initUI();
    }

    public void setColombiaResponse(ItemResponse itemResponse) {
        this.paidItems = itemResponse.getPaidItems();
        final int size = this.paidItems.size();
        setHeaders(((Item) this.paidItems.get(0)).getTitle(), ((Item) this.paidItems.get(0)).getBrandText());
        this.horizontalScroller.setAdapter(new Adapter<ViewHolder>() {
            public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
                View newView;
                if (d.j()) {
                    newView = ColombiaAdScroller.this.getNewView(R.layout.colombia_scroller_item_view, viewGroup);
                } else {
                    newView = ColombiaAdScroller.this.getNewView(R.layout.colombia_scroller_item_view_kitkat, viewGroup);
                }
                AdView adView = new AdView(ColombiaAdScroller.this.mContext);
                adView.addView(newView);
                ColombiaAdScrollerItemHolder colombiaAdScrollerItemHolder = new ColombiaAdScrollerItemHolder(adView);
                colombiaAdScrollerItemHolder.ctaText.setTypeface(Util.h(ColombiaAdScroller.this.mContext));
                colombiaAdScrollerItemHolder.description.setTypeface(Util.h(ColombiaAdScroller.this.mContext));
                return colombiaAdScrollerItemHolder;
            }

            public void onBindViewHolder(ViewHolder viewHolder, int i) {
                Item item = (Item) ColombiaAdScroller.this.paidItems.get(i);
                ColombiaAdScrollerItemHolder colombiaAdScrollerItemHolder = (ColombiaAdScrollerItemHolder) viewHolder;
                colombiaAdScrollerItemHolder.description.setText(item.getTitle());
                colombiaAdScrollerItemHolder.adImage.bindImage(item.getImageUrl(), ScaleType.FIT_XY);
                TextView textView = colombiaAdScrollerItemHolder.description;
                LayoutParams layoutParams = (LayoutParams) textView.getLayoutParams();
                if (TextUtils.isEmpty(item.getCtaText())) {
                    colombiaAdScrollerItemHolder.ctaText.setVisibility(8);
                    layoutParams.addRule(14);
                    layoutParams.width = -1;
                    textView.setLayoutParams(layoutParams);
                    textView.setGravity(1);
                } else {
                    colombiaAdScrollerItemHolder.ctaText.setVisibility(0);
                    colombiaAdScrollerItemHolder.ctaText.setText(item.getCtaText());
                    layoutParams.width = Math.round(ColombiaAdScroller.this.getResources().getDimension(R.dimen.colombia_ad_scroller_item_width));
                    textView.setLayoutParams(layoutParams);
                }
                AdView adView = (AdView) colombiaAdScrollerItemHolder.mView;
                adView.setCallToActionView(adView.findViewById(R.id.cta_text));
                adView.commitItem(item);
            }

            public int getItemCount() {
                return size;
            }

            public int getItemViewType(int i) {
                return ((Item) ColombiaAdScroller.this.paidItems.get(i)).getItemType().ordinal();
            }
        });
    }

    public View getCarouselView() {
        return this.view;
    }

    private void initUI() {
        this.view = getNewView(R.layout.colombia_ad_scroller_view, this);
        this.horizontalScroller = (HorizontalRecyclerView) this.view.findViewById(R.id.horizontal_list_view);
        ((TextView) this.view.findViewById(R.id.title)).setTypeface(Util.h(this.mContext));
        ((TextView) this.view.findViewById(R.id.sub_title)).setTypeface(Util.h(this.mContext));
    }

    private void setHeaders(String str, String str2) {
        if (this.view != null) {
            this.title = str;
            this.subTitle = str2;
            TextView textView = (TextView) this.view.findViewById(R.id.sub_title);
            ((TextView) this.view.findViewById(R.id.title)).setText(this.subTitle);
            textView.setText(R.string.car_ad_subtitle);
        }
    }
}
