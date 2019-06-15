package com.gaana.view.item;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.constants.Constants;
import com.fragments.ArtistFragment;
import com.fragments.BaseGaanaFragment;
import com.fragments.FavoritesFragment;
import com.fragments.ItemListingFragment;
import com.gaana.BaseActivity;
import com.gaana.R;
import com.gaana.localmedia.LocalMediaImageLoader;
import com.gaana.models.Artists.Artist;
import com.gaana.models.BusinessObject;
import com.library.controls.CrossFadeImageView;
import com.logging.GaanaLogger.SOURCE_TYPE;
import com.managers.GaanaSearchManager.SearchType;
import com.managers.URLManager;
import com.managers.ad;
import com.managers.aj;
import com.managers.an;
import com.managers.ap;
import com.models.ListingButton;
import com.utilities.Util;

public class ArtistItemView extends BaseItemView {
    private CrossFadeImageView mCrossFadeImageIcon;
    private String mSearchQuery;
    private String myGenreId;
    private String myGenreName;
    private TextView tvTopHeading;

    public static class ArtistItemHolder extends ViewHolder {
        private CrossFadeImageView mCrossFadeImageIcon;
        private ImageView optionImageView;
        private TextView tvTopHeading;

        public ArtistItemHolder(View view) {
            super(view);
            this.mCrossFadeImageIcon = (CrossFadeImageView) view.findViewById(R.id.imgProductIcon);
            this.tvTopHeading = (TextView) view.findViewById(R.id.tvTopHeading);
            this.optionImageView = (ImageView) view.findViewById(R.id.clickoptionImage);
        }
    }

    public ArtistItemView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        this.myGenreId = "";
        this.myGenreName = null;
        this.mLayoutId = R.layout.view_item_artist;
        ((BaseActivity) this.mContext).currentItem = "Artist";
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
        ArtistItemHolder artistItemHolder = (ArtistItemHolder) viewHolder;
        this.mView = artistItemHolder.itemView;
        super.getPoplatedView(this.mView, businessObject);
        return getDataFilledView(artistItemHolder, businessObject);
    }

    private View getDataFilledView(ArtistItemHolder artistItemHolder, BusinessObject businessObject) {
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
        Artist artist = (Artist) businessObject;
        this.mCrossFadeImageIcon = artistItemHolder.mCrossFadeImageIcon;
        this.tvTopHeading = artistItemHolder.tvTopHeading;
        if (artist.getArtwork() == null) {
            TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{R.attr.placeholder_album_artwork_large});
            Drawable drawable = obtainStyledAttributes.getDrawable(0);
            obtainStyledAttributes.recycle();
            this.mCrossFadeImageIcon.setImageDrawable(drawable);
        } else if (artist.isLocalMedia()) {
            this.mCrossFadeImageIcon.bindImageForLocalMedia(artist.getArtwork(), null, new LocalMediaImageLoader(), this.mAppState.isAppInOfflineMode());
        } else {
            this.mCrossFadeImageIcon.bindImage(artist.getArtwork().replace("80x80", "175x175"), this.mAppState.isAppInOfflineMode());
        }
        if (this.mFragment instanceof ArtistFragment) {
            this.tvTopHeading.setText(Util.c(this.mSearchQuery, this.mContext.getString(R.string.go_to_artist)));
        } else {
            this.tvTopHeading.setText(Util.c(this.mSearchQuery, artist.getName()));
        }
        if (businessObject.isLocalMedia()) {
            artistItemHolder.optionImageView.setVisibility(4);
        } else {
            artistItemHolder.optionImageView.setVisibility(0);
            artistItemHolder.optionImageView.setTag(businessObject);
            artistItemHolder.optionImageView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (ArtistItemView.this.mFragment instanceof ItemListingFragment) {
                        an.a().a("click", "ac", "Similar Artist", ((ItemListingFragment) ArtistItemView.this.mFragment).a(), ((BusinessObject) view.getTag()).getBusinessObjId(), "three dot menu", "", "");
                    }
                    ArtistItemView.this.showOptionMenu(view);
                }
            });
        }
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
        Artist artist = (Artist) businessObject;
        this.mCrossFadeImageIcon = (CrossFadeImageView) view.findViewById(R.id.imgProductIcon);
        this.tvTopHeading = (TextView) view.findViewById(R.id.tvTopHeading);
        if (artist.getArtwork() == null) {
            TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{R.attr.placeholder_album_artwork_large});
            Drawable drawable = obtainStyledAttributes.getDrawable(0);
            obtainStyledAttributes.recycle();
            this.mCrossFadeImageIcon.setImageDrawable(drawable);
        } else if (artist.isLocalMedia()) {
            this.mCrossFadeImageIcon.bindImageForLocalMedia(artist.getArtwork(), null, new LocalMediaImageLoader(), this.mAppState.isAppInOfflineMode());
        } else {
            this.mCrossFadeImageIcon.bindImage(artist.getArtwork().replace("80x80", "175x175"), this.mAppState.isAppInOfflineMode());
        }
        if (this.mFragment instanceof ArtistFragment) {
            this.tvTopHeading.setText(Util.c(this.mSearchQuery, this.mContext.getString(R.string.go_to_artist)));
        } else {
            this.tvTopHeading.setText(Util.c(this.mSearchQuery, artist.getName()));
        }
        if (businessObject.isLocalMedia()) {
            view.findViewById(R.id.clickoptionImage).setVisibility(4);
        } else {
            view.findViewById(R.id.clickoptionImage).setVisibility(0);
            view.findViewById(R.id.clickoptionImage).setTag(businessObject);
            view.findViewById(R.id.clickoptionImage).setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    ArtistItemView.this.showOptionMenu(view);
                }
            });
        }
        return view;
    }

    public void onClick(View view) {
        Util.a(this.mContext, view);
        this.mBusinessObject = (BusinessObject) view.getTag();
        if (this.mFragment instanceof FavoritesFragment) {
            an.a().a("click", "ac", this.mBusinessObject.getBusinessObjId(), "", this.mBusinessObject.getBusinessObjType().name(), "fav", "", "");
        } else if (this.mFragment instanceof ItemListingFragment) {
            an.a().a("click", "ac", ((ItemListingFragment) this.mFragment).a(), "Similar Artist", "Similar Artist", this.mBusinessObject.getBusinessObjId(), "", "");
        }
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
        if (this.mAppState.getListingComponents().f() == SearchType.Radio) {
            aj a = aj.a();
            Context context = this.mContext;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.mContext.getString(R.string.starting_radio_for_artist));
            stringBuilder.append(this.mBusinessObject.getName());
            a.a(context, stringBuilder.toString());
            ad.a(this.mContext).a("https://api.gaana.com/radio.php?type=radio&subtype=artistradios&artist_id=<artist_id>&page=1&limit=10".replace("<artist_id>", this.mBusinessObject.getBusinessObjId()), SOURCE_TYPE.RADIO_SEARCH_ARTIST.ordinal(), this.mBusinessObject);
        } else {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(((BaseActivity) this.mContext).currentScreen);
            stringBuilder2.append(" - ");
            stringBuilder2.append(((BaseActivity) this.mContext).currentFavpage);
            stringBuilder2.append(" - Artist Detail");
            ((BaseActivity) this.mContext).sendGAEvent(((BaseActivity) this.mContext).currentScreen, "Artist Detail", stringBuilder2.toString());
            this.mListingComponents = Constants.a(this.myGenreId, this.mBusinessObject.isLocalMedia());
            this.mAppState.setListingComponents(this.mListingComponents);
            populateArtistListing(this.mBusinessObject);
        }
    }

    public View getPoplatedViewForGrid(View view, BusinessObject businessObject, ViewGroup viewGroup, int i) {
        if (view == null) {
            view = super.getNewView(i, viewGroup, businessObject);
        }
        Artist artist = (Artist) businessObject;
        this.mCrossFadeImageIcon = (CrossFadeImageView) view.findViewById(R.id.imgProductIcon);
        TextView textView = (TextView) view.findViewById(R.id.tvTopHeading);
        this.mCrossFadeImageIcon.bindImage(artist.getArtwork().replace("80x80", "175x175"), this.mAppState.isAppInOfflineMode());
        if (this.mFragment instanceof ArtistFragment) {
            textView.setText(this.mContext.getString(R.string.go_to_artist));
        } else {
            textView.setText(artist.getName());
        }
        view.setTag(businessObject);
        view.setOnClickListener(this);
        return view;
    }

    public View getPoplatedViewForGrid(View view, BusinessObject businessObject, ViewGroup viewGroup) {
        if (view == null) {
            view = super.getNewView(R.layout.view_featured_album_item, viewGroup, businessObject);
        }
        Artist artist = (Artist) businessObject;
        this.mCrossFadeImageIcon = (CrossFadeImageView) view.findViewById(R.id.imgProductIcon);
        TextView textView = (TextView) view.findViewById(R.id.tvTopHeading);
        this.mCrossFadeImageIcon.bindImage(artist.getArtwork().replace("80x80", "175x175"), this.mAppState.isAppInOfflineMode());
        if (this.mFragment instanceof ArtistFragment) {
            textView.setText(this.mContext.getString(R.string.go_to_artist));
        } else {
            textView.setText(artist.getName());
        }
        view.setTag(businessObject);
        view.setOnClickListener(this);
        return view;
    }
}
