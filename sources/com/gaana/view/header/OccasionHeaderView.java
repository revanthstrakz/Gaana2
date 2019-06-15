package com.gaana.view.header;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.volley.VolleyError;
import com.b.i;
import com.dynamicview.f.a;
import com.fragments.BaseGaanaFragment;
import com.gaana.R;
import com.gaana.view.BaseItemView;
import com.gaana.view.ImageCardView;
import com.gaana.view.item.BaseItemView.ItemAdViewHolder;
import com.library.controls.CrossFadeImageView;
import com.services.d;
import com.services.l.r;

public class OccasionHeaderView extends BaseItemView {
    public boolean isVisible = false;
    private final boolean mIsHeading;
    a mOccasionDynamicView = null;

    public OccasionHeaderView(Context context, BaseGaanaFragment baseGaanaFragment, a aVar, boolean z) {
        super(context, baseGaanaFragment);
        this.mOccasionDynamicView = aVar;
        this.mIsHeading = z;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ItemAdViewHolder(this.mInflater.inflate(R.layout.occasion_headerview, viewGroup, false));
    }

    public a getDynamicView() {
        return this.mOccasionDynamicView;
    }

    public View getPopulatedView(int i, ViewHolder viewHolder, ViewGroup viewGroup) {
        final View view = viewHolder.itemView;
        final CrossFadeImageView crossFadeImageView = (CrossFadeImageView) view.findViewById(R.id.occasion_header_image);
        if (this.mIsHeading) {
            int b = (int) (((float) d.a().b()) * 0.8f);
            viewHolder.itemView.findViewById(R.id.occasion_header).setLayoutParams(new LayoutParams(-1, b));
            viewHolder.itemView.findViewById(R.id.occasion_text_overlay).setVisibility(0);
            crossFadeImageView.setAdjustViewBounds(true);
            crossFadeImageView.setLayoutParams(new RelativeLayout.LayoutParams(-1, b));
            TextView textView = (TextView) viewHolder.itemView.findViewById(R.id.txt_section_header);
            textView.setVisibility(0);
            textView.setText(getDynamicView().k());
            textView.setTypeface(i.a(this.mContext.getAssets(), "fonts/Roboto-Medium.ttf"));
            textView.setTypeface(((TextView) view.findViewById(R.id.txt_section_header)).getTypeface(), 1);
        } else {
            crossFadeImageView.setScaleType(ScaleType.CENTER_CROP);
            crossFadeImageView.setAdjustViewBounds(true);
            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(d.a().b() - this.mContext.getResources().getDimensionPixelSize(R.dimen.page_left_right_margin), ImageCardView.getCardHeight(this.mContext, this.mOccasionDynamicView.e()));
            layoutParams.leftMargin = (int) this.mContext.getResources().getDimension(R.dimen.page_left_right_margin);
            layoutParams.rightMargin = (int) this.mContext.getResources().getDimension(R.dimen.page_left_right_margin);
            layoutParams.bottomMargin = (int) this.mContext.getResources().getDimension(R.dimen.bw_section_vert_padding_half);
            layoutParams.topMargin = (int) this.mContext.getResources().getDimension(R.dimen.bw_section_vert_padding_half);
            crossFadeImageView.setLayoutParams(layoutParams);
        }
        if (!(this.mOccasionDynamicView == null || this.mOccasionDynamicView.d() == null)) {
            com.i.i.a().a(this.mOccasionDynamicView.d(), new r() {
                public void onErrorResponse(VolleyError volleyError) {
                }

                public void onSuccessfulResponse(Bitmap bitmap) {
                    crossFadeImageView.setBitmapToImageView(bitmap);
                    view.setVisibility(0);
                }
            }, true);
        }
        return viewHolder.itemView;
    }
}
