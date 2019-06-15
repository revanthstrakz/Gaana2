package com.gaana.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.dynamicview.f.a;
import com.fragments.BaseGaanaFragment;
import com.gaana.R;
import com.gaana.models.BusinessObject;
import com.gaana.models.Item;
import com.gaana.models.Items;
import com.i.i;
import com.managers.URLManager;
import com.services.l.af;
import java.util.ArrayList;

public class SponsoredOccasionCardView extends BaseItemView {
    private boolean isFirstCall = false;
    private ArrayList<Item> itemlist;
    private a mDynamicView;
    ViewHolder mHolder = null;

    public class SponsoredOccasionCardViewHolder extends ViewHolder {
        public LinearLayout llImgParentLayout;
        protected RecyclerView recycler_view_list;

        public SponsoredOccasionCardViewHolder(View view) {
            super(view);
            this.llImgParentLayout = (LinearLayout) view.findViewById(R.id.llImgParentLayout);
            this.recycler_view_list = (RecyclerView) view.findViewById(R.id.recycler_view_list);
        }
    }

    public SponsoredOccasionCardView(Context context, BaseGaanaFragment baseGaanaFragment, a aVar) {
        super(context, baseGaanaFragment);
        this.mDynamicView = aVar;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        this.mHolder = new SponsoredOccasionCardViewHolder(this.mInflater.inflate(R.layout.sponsored_occasion_card_view, viewGroup, false));
        callApi((SponsoredOccasionCardViewHolder) this.mHolder);
        return this.mHolder;
    }

    public a getDynamicView() {
        return this.mDynamicView;
    }

    public View getPopulatedView(int i, ViewHolder viewHolder, ViewGroup viewGroup) {
        SponsoredOccasionCardViewHolder sponsoredOccasionCardViewHolder = (SponsoredOccasionCardViewHolder) viewHolder;
        if (this.isFirstCall) {
            callApi(sponsoredOccasionCardViewHolder);
        }
        sponsoredOccasionCardViewHolder.itemView.setVisibility(0);
        return sponsoredOccasionCardViewHolder.itemView;
    }

    public void setPositionToBeRefreshed(int i) {
        this.isFirstCall = true;
    }

    private void callApi(final SponsoredOccasionCardViewHolder sponsoredOccasionCardViewHolder) {
        if (this.mDynamicView != null && this.mDynamicView.l() != null) {
            URLManager uRLManager = new URLManager();
            uRLManager.l(this.mDynamicView.z());
            uRLManager.a(this.mDynamicView.l());
            uRLManager.a(Items.class);
            uRLManager.c(Boolean.valueOf(this.isFirstCall));
            if (TextUtils.isEmpty(this.mDynamicView.g())) {
                uRLManager.b(Boolean.valueOf(false));
            } else {
                uRLManager.a(Integer.parseInt(this.mDynamicView.g()));
                uRLManager.b(Boolean.valueOf(true));
            }
            i.a().a(new af() {
                public void onErrorResponse(BusinessObject businessObject) {
                }

                public void onRetreivalComplete(Object obj) {
                    if (obj != null && (obj instanceof Items)) {
                        SponsoredOccasionCardView.this.itemlist = ((Items) obj).getArrListBusinessObj();
                        if (SponsoredOccasionCardView.this.itemlist != null && SponsoredOccasionCardView.this.itemlist.size() > 0) {
                            SponsorOccasionItemView sponsorOccasionItemView = new SponsorOccasionItemView(SponsoredOccasionCardView.this.mContext, SponsoredOccasionCardView.this.itemlist, SponsoredOccasionCardView.this.mDynamicView);
                            sponsoredOccasionCardViewHolder.recycler_view_list.setHasFixedSize(true);
                            sponsoredOccasionCardViewHolder.recycler_view_list.setLayoutManager(new LinearLayoutManager(SponsoredOccasionCardView.this.mContext, 0, false));
                            sponsoredOccasionCardViewHolder.recycler_view_list.setAdapter(sponsorOccasionItemView);
                        }
                    }
                }
            }, uRLManager);
            this.isFirstCall = false;
        }
    }

    public void setFirstCall(boolean z) {
        this.isFirstCall = z;
    }
}
