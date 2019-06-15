package com.gaana.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.constants.Constants;
import com.fragments.ArtistFragment;
import com.fragments.BaseGaanaFragment;
import com.gaana.BaseActivity;
import com.gaana.R;
import com.gaana.localmedia.LocalMediaImageLoader;
import com.gaana.models.BusinessObject;
import com.gaana.models.FavoriteOccasions.FavoriteOccasion;
import com.gaana.view.item.BaseItemView;
import com.library.controls.CrossFadeImageView;
import com.managers.URLManager;
import com.managers.ap;
import com.models.ListingButton;
import com.services.c;
import com.utilities.Util;

public class FavoriteOccasionItemView extends BaseItemView {
    private CrossFadeImageView mCrossFadeImageIcon;
    private String mSearchQuery;
    private String myGenreId;
    private String myGenreName;
    private TextView tvTopHeading;

    public static class FavoriteOccasionItemHolder extends ViewHolder {
        private CrossFadeImageView mCrossFadeImageIcon;
        private ImageView optionImageView;
        private TextView tvTopHeading;

        public FavoriteOccasionItemHolder(View view) {
            super(view);
            this.mCrossFadeImageIcon = (CrossFadeImageView) view.findViewById(R.id.imgProductIcon);
            this.tvTopHeading = (TextView) view.findViewById(R.id.tvTopHeading);
            this.optionImageView = (ImageView) view.findViewById(R.id.clickoptionImage);
        }
    }

    public FavoriteOccasionItemView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        this.myGenreId = "";
        this.myGenreName = null;
        this.mLayoutId = R.layout.view_item_artist;
        ((BaseActivity) this.mContext).currentItem = "Occasion";
    }

    public View getView(ViewGroup viewGroup) {
        return super.createNewBaseView(this.mLayoutId, null, viewGroup);
    }

    public void setSearchQuery(String str) {
        this.mSearchQuery = str;
    }

    public View getPoplatedView(View view, BusinessObject businessObject, ViewGroup viewGroup) {
        if (view == null) {
            view = super.createNewBaseView(this.mLayoutId, view, viewGroup);
        }
        super.getPoplatedView(view, businessObject);
        return getDataFilledView(view, businessObject);
    }

    public View getPoplatedView(ViewHolder viewHolder, BusinessObject businessObject, ViewGroup viewGroup) {
        FavoriteOccasionItemHolder favoriteOccasionItemHolder = (FavoriteOccasionItemHolder) viewHolder;
        this.mView = favoriteOccasionItemHolder.itemView;
        super.getPoplatedView(this.mView, businessObject);
        return getDataFilledView(favoriteOccasionItemHolder, businessObject);
    }

    private View getDataFilledView(FavoriteOccasionItemHolder favoriteOccasionItemHolder, BusinessObject businessObject) {
        if (!(this.mAppState.getListingComponents().c() == null || this.mAppState.getListingComponents().c().get(0) == null)) {
            URLManager c = ((ListingButton) this.mAppState.getListingComponents().c().get(0)).c();
            if (c == null || c.k() == null || !c.k().contains("genre")) {
                this.myGenreName = null;
                this.mAppState.setCurrentGenreName(this.myGenreName);
            } else {
                int indexOf = c.k().indexOf("genre_id=");
                try {
                    this.myGenreId = c.k().substring(indexOf + 9, c.k().indexOf("&limit="));
                } catch (StringIndexOutOfBoundsException unused) {
                    this.myGenreId = null;
                }
                if (this.myGenreId == null || this.myGenreId.length() <= 0) {
                    this.myGenreName = null;
                } else {
                    this.myGenreName = this.mAppState.getCurrentGenreName();
                }
            }
        }
        FavoriteOccasion favoriteOccasion = (FavoriteOccasion) businessObject;
        this.mCrossFadeImageIcon = favoriteOccasionItemHolder.mCrossFadeImageIcon;
        this.tvTopHeading = favoriteOccasionItemHolder.tvTopHeading;
        if (favoriteOccasion.getArtwork() == null) {
            TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{R.attr.placeholder_album_artwork_large});
            Drawable drawable = obtainStyledAttributes.getDrawable(0);
            obtainStyledAttributes.recycle();
            this.mCrossFadeImageIcon.setImageDrawable(drawable);
        } else if (favoriteOccasion.isLocalMedia()) {
            this.mCrossFadeImageIcon.bindImageForLocalMedia(favoriteOccasion.getArtwork(), null, new LocalMediaImageLoader(), this.mAppState.isAppInOfflineMode());
        } else {
            this.mCrossFadeImageIcon.bindImage(favoriteOccasion.getArtwork().replace("80x80", "175x175"), this.mAppState.isAppInOfflineMode());
        }
        if (this.mFragment instanceof ArtistFragment) {
            this.tvTopHeading.setText(Util.c(this.mSearchQuery, this.mContext.getString(R.string.go_to_artist)));
        } else {
            this.tvTopHeading.setText(Util.c(this.mSearchQuery, favoriteOccasion.getName()));
        }
        favoriteOccasionItemHolder.optionImageView.setVisibility(4);
        return this.mView;
    }

    private View getDataFilledView(View view, BusinessObject businessObject) {
        if (!(this.mAppState.getListingComponents().c() == null || this.mAppState.getListingComponents().c().get(0) == null)) {
            URLManager c = ((ListingButton) this.mAppState.getListingComponents().c().get(0)).c();
            if (c == null || c.k() == null || !c.k().contains("genre")) {
                this.myGenreName = null;
                this.mAppState.setCurrentGenreName(this.myGenreName);
            } else {
                int indexOf = c.k().indexOf("genre_id=");
                try {
                    this.myGenreId = c.k().substring(indexOf + 9, c.k().indexOf("&limit="));
                } catch (StringIndexOutOfBoundsException unused) {
                    this.myGenreId = null;
                }
                if (this.myGenreId == null || this.myGenreId.length() <= 0) {
                    this.myGenreName = null;
                } else {
                    this.myGenreName = this.mAppState.getCurrentGenreName();
                }
            }
        }
        FavoriteOccasion favoriteOccasion = (FavoriteOccasion) businessObject;
        this.mCrossFadeImageIcon = (CrossFadeImageView) view.findViewById(R.id.imgProductIcon);
        this.tvTopHeading = (TextView) view.findViewById(R.id.tvTopHeading);
        if (favoriteOccasion.getArtwork() == null) {
            TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{R.attr.placeholder_album_artwork_large});
            Drawable drawable = obtainStyledAttributes.getDrawable(0);
            obtainStyledAttributes.recycle();
            this.mCrossFadeImageIcon.setImageDrawable(drawable);
        } else if (favoriteOccasion.isLocalMedia()) {
            this.mCrossFadeImageIcon.bindImageForLocalMedia(favoriteOccasion.getArtwork(), null, new LocalMediaImageLoader(), this.mAppState.isAppInOfflineMode());
        } else {
            this.mCrossFadeImageIcon.bindImage(favoriteOccasion.getArtwork().replace("80x80", "175x175"), this.mAppState.isAppInOfflineMode());
        }
        if (this.mFragment instanceof ArtistFragment) {
            this.tvTopHeading.setText(Util.c(this.mSearchQuery, this.mContext.getString(R.string.go_to_artist)));
        } else {
            this.tvTopHeading.setText(Util.c(this.mSearchQuery, favoriteOccasion.getName()));
        }
        view.findViewById(R.id.clickoptionImage).setVisibility(4);
        return view;
    }

    public void onClick(View view) {
        Util.a(this.mContext, view);
        this.mBusinessObject = (BusinessObject) view.getTag();
        if (!this.mBusinessObject.isLocalMedia()) {
            if (this.mAppState.isAppInOfflineMode()) {
                ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(this.mContext.getString(R.string.this_feature));
                return;
            } else if (!Util.j(this.mContext)) {
                ap.a().f(this.mContext);
                return;
            }
        }
        Constants.i = false;
        Constants.j = "";
        super.onClick(view);
        if (this.mBusinessObject instanceof FavoriteOccasion) {
            String seoKey = ((FavoriteOccasion) this.mBusinessObject).getSeoKey();
            if (!TextUtils.isEmpty(seoKey)) {
                c.a(this.mContext, true).a(this.mContext, this.mAppState, com.constants.c.c.i, seoKey);
            }
        }
    }

    public View getPoplatedViewForGrid(View view, BusinessObject businessObject, ViewGroup viewGroup, int i) {
        if (view == null) {
            view = super.getNewView(i, viewGroup, businessObject);
        }
        FavoriteOccasion favoriteOccasion = (FavoriteOccasion) businessObject;
        this.mCrossFadeImageIcon = (CrossFadeImageView) view.findViewById(R.id.imgProductIcon);
        TextView textView = (TextView) view.findViewById(R.id.tvTopHeading);
        this.mCrossFadeImageIcon.bindImage(favoriteOccasion.getArtwork().replace("80x80", "175x175"), this.mAppState.isAppInOfflineMode());
        if (this.mFragment instanceof ArtistFragment) {
            textView.setText(this.mContext.getString(R.string.go_to_occasion));
        } else {
            textView.setText(favoriteOccasion.getName());
        }
        view.setTag(businessObject);
        view.setOnClickListener(this);
        return view;
    }

    public View getPoplatedViewForGrid(View view, BusinessObject businessObject, ViewGroup viewGroup) {
        if (view == null) {
            view = super.getNewView(R.layout.view_featured_album_item, viewGroup, businessObject);
        }
        FavoriteOccasion favoriteOccasion = (FavoriteOccasion) businessObject;
        this.mCrossFadeImageIcon = (CrossFadeImageView) view.findViewById(R.id.imgProductIcon);
        TextView textView = (TextView) view.findViewById(R.id.tvTopHeading);
        this.mCrossFadeImageIcon.bindImage(favoriteOccasion.getArtwork().replace("80x80", "175x175"), this.mAppState.isAppInOfflineMode());
        if (this.mFragment instanceof ArtistFragment) {
            textView.setText(this.mContext.getString(R.string.go_to_occasion));
        } else {
            textView.setText(favoriteOccasion.getName());
        }
        view.setTag(businessObject);
        view.setOnClickListener(this);
        return view;
    }
}
