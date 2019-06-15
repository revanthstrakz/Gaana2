package com.gaana.view.item;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.fragments.BaseGaanaFragment;
import com.fragments.ListingFragment;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.fragments.BaseFragment;
import com.gaana.models.BusinessObject;
import com.gaana.view.item.AddToPlaylistItemView.AddToPlaylistItemViewHolder;
import com.gaana.view.item.DownloadAlbumItemView.DownloadAlbumItemHolder;
import com.gaana.view.item.DownloadSongsItemView.AlbumDetailItemHolder;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.a;
import com.models.ListingButton;
import com.models.ListingParams;
import java.util.ArrayList;

public class VerticalListingView extends BaseItemView {
    private ArrayList<BusinessObject> arrayList;
    private String footerText;
    private String headerText;
    private BaseItemView mBaseItemView;

    class VerticalListAdapter extends Adapter<ViewHolder> {
        VerticalListAdapter() {
        }

        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            if (VerticalListingView.this.mBaseItemView instanceof DownloadSongsItemView) {
                return new AlbumDetailItemHolder(VerticalListingView.this.mBaseItemView.createViewHolder(viewGroup, i, R.layout.view_item_download_transparent));
            }
            if (VerticalListingView.this.mBaseItemView instanceof DownloadAlbumItemView) {
                return new DownloadAlbumItemHolder(VerticalListingView.this.mBaseItemView.createViewHolder(viewGroup, i, R.layout.view_item_download_transparent));
            }
            return VerticalListingView.this.mBaseItemView instanceof AddToPlaylistItemView ? new AddToPlaylistItemViewHolder(VerticalListingView.this.mBaseItemView.createViewHolder(viewGroup, i)) : null;
        }

        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            if (VerticalListingView.this.mBaseItemView instanceof DownloadSongsItemView) {
                VerticalListingView.this.mBaseItemView = new DownloadSongsItemView(VerticalListingView.this.mContext, VerticalListingView.this.mFragment);
            }
            VerticalListingView.this.mBaseItemView.getPoplatedView(viewHolder, (BusinessObject) VerticalListingView.this.arrayList.get(i), null);
        }

        public int getItemCount() {
            return VerticalListingView.this.arrayList != null ? VerticalListingView.this.arrayList.size() : 0;
        }
    }

    public static class VerticalListingHolder extends ViewHolder {
        public RecyclerView mRecyclerView;
        public TextView mTxtDescription;
        public TextView mTxtTitle;

        public VerticalListingHolder(View view) {
            super(view);
            this.mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
            this.mTxtTitle = (TextView) view.findViewById(R.id.txt_title);
            this.mTxtDescription = (TextView) view.findViewById(R.id.txt_desc);
        }
    }

    public VerticalListingView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        this.mLayoutId = R.layout.view_vertical_listing;
        this.mBaseItemView = new DownloadSongsItemView(context, baseGaanaFragment);
    }

    public VerticalListingView(Context context, BaseFragment baseFragment, int i) {
        super(context, baseFragment, i);
        this.mLayoutId = R.layout.view_vertical_listing;
    }

    public void setHeaderText(String str) {
        this.headerText = str;
    }

    public void setFooterText(String str) {
        this.footerText = str;
    }

    public View getNewView(int i, ViewGroup viewGroup) {
        return super.getNewView(this.mLayoutId, viewGroup);
    }

    public void setBaseItemView(BaseItemView baseItemView) {
        this.mBaseItemView = baseItemView;
    }

    public View getPoplatedView(ViewHolder viewHolder, final BusinessObject businessObject, ViewGroup viewGroup) {
        if (!(businessObject == null || businessObject.getArrListBusinessObj() == null)) {
            Context context;
            int i;
            this.arrayList = businessObject.getArrListBusinessObj();
            VerticalListingHolder verticalListingHolder = (VerticalListingHolder) viewHolder;
            verticalListingHolder.mRecyclerView.setHasFixedSize(true);
            new LinearLayoutManager(this.mContext).setAutoMeasureEnabled(false);
            verticalListingHolder.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
            verticalListingHolder.mRecyclerView.setAdapter(new VerticalListAdapter());
            if (businessObject.getBusinessObjType() == BusinessObjectType.Tracks) {
                context = this.mContext;
                i = R.string.songs_text;
            } else {
                context = this.mContext;
                i = R.string.albums_camel;
            }
            String string = context.getString(i);
            if (!TextUtils.isEmpty(string)) {
                verticalListingHolder.mTxtTitle.setText(string);
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.mContext.getString(R.string.see_all_camel));
            stringBuilder.append(" ");
            stringBuilder.append(string);
            verticalListingHolder.mTxtDescription.setText(stringBuilder.toString());
            verticalListingHolder.mTxtDescription.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    URLManager uRLManager = new URLManager();
                    uRLManager.a(businessObject.getBusinessObjType());
                    uRLManager.b(businessObject.getName());
                    ListingButton listingButton = new ListingButton();
                    listingButton.a(new a());
                    listingButton.a(uRLManager);
                    listingButton.a(businessObject.getName());
                    listingButton.b(businessObject.getName());
                    if (uRLManager.i() == BusinessObjectType.Albums) {
                        listingButton.c(DownloadAlbumItemView.class.getName());
                    } else {
                        listingButton.c(DownloadSongsItemView.class.getName());
                    }
                    listingButton.b(true);
                    ListingParams listingParams = new ListingParams();
                    listingParams.d(true);
                    listingParams.a(true);
                    listingParams.k(false);
                    listingParams.a(listingButton);
                    BaseGaanaFragment listingFragment = new ListingFragment();
                    listingFragment.a(listingParams);
                    ((GaanaActivity) VerticalListingView.this.mContext).displayFragment(listingFragment);
                }
            });
        }
        return super.getPoplatedView(viewHolder, businessObject, viewGroup);
    }
}
