package com.gaana.view.item;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.constants.Constants;
import com.constants.c.c;
import com.constants.c.d;
import com.fragments.BaseGaanaFragment;
import com.fragments.RadioDetailsMaterialListing;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.analytics.MoEngage;
import com.gaana.juke.JukePlaylist;
import com.gaana.juke.JukeSessionManager;
import com.gaana.models.Albums.Album;
import com.gaana.models.Artists.Artist;
import com.gaana.models.BusinessObject;
import com.gaana.models.DiscoverTags.DiscoverTag;
import com.gaana.models.EntityInfo;
import com.gaana.models.Item;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Radios.Radio;
import com.gaana.models.Tracks.Track;
import com.gaana.view.item.BaseItemView.GridItemHolder;
import com.gaana.view.item.BaseItemView.TwoGridItemHolder;
import com.gaana.view.item.CustomDialogView.OnButtonClickListener;
import com.library.controls.CrossFadeImageView;
import com.logging.GaanaLogger.SOURCE_TYPE;
import com.managers.DownloadManager;
import com.managers.DownloadManager.DownloadStatus;
import com.managers.PlayerManager;
import com.managers.PlayerManager.PlayerType;
import com.managers.URLManager.BusinessObjectType;
import com.managers.ad;
import com.managers.af;
import com.managers.ap;
import com.managers.n;
import com.managers.u;
import com.services.l.s;
import com.utilities.Util;
import java.util.ArrayList;

public class DownloadGridItemView extends BaseItemView {
    private String artWork;
    private String headerText;
    private CrossFadeImageView imageThumb = null;
    private BusinessObject itemClicked;
    private TextView itemTitle = null;
    private BaseGaanaFragment mFragment;
    private int mLayoutResourceId = R.layout.grid_twoitem_view;
    private ProgressBar mProgressBar;

    public DownloadGridItemView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        this.mFragment = baseGaanaFragment;
    }

    public View getPoplatedView(View view, BusinessObject businessObject, ViewGroup viewGroup, boolean z, Boolean bool) {
        ArrayList arrListBusinessObj = businessObject.getArrListBusinessObj();
        if (arrListBusinessObj.size() > 0) {
            this.mLayoutResourceId = getLayoutId((BusinessObject) arrListBusinessObj.get(0), true);
        }
        if (view == null) {
            view = super.createNewBaseView(this.mLayoutResourceId, view, viewGroup);
        }
        if (z) {
            view.findViewById(R.id.f55header.text).setVisibility(0);
        } else {
            view.findViewById(R.id.f55header.text).setVisibility(8);
        }
        if (bool.booleanValue()) {
            return getDefaultPoplatedView(view, businessObject);
        }
        return getPoplatedView(view, businessObject, viewGroup);
    }

    public View getPoplatedView(ViewHolder viewHolder, BusinessObject businessObject, ViewGroup viewGroup, boolean z, Boolean bool) {
        TwoGridItemHolder twoGridItemHolder = (TwoGridItemHolder) viewHolder;
        businessObject.getArrListBusinessObj();
        if (z) {
            twoGridItemHolder.headerText.setVisibility(0);
        } else {
            twoGridItemHolder.headerText.setVisibility(8);
        }
        if (bool.booleanValue()) {
            return getDefaultPoplatedView(twoGridItemHolder, businessObject);
        }
        return getPoplatedView((ViewHolder) twoGridItemHolder, businessObject, viewGroup);
    }

    public View getDefaultPoplatedView(View view, BusinessObject businessObject) {
        BusinessObject businessObject2;
        TypedArray obtainStyledAttributes;
        View view2 = view;
        View findViewById = view2.findViewById(R.id.f58ll.grid.firstitem);
        if (findViewById != null) {
            TypedArray obtainStyledAttributes2;
            Drawable drawable;
            findViewById.setVisibility(0);
            TypedArray obtainStyledAttributes3 = this.mContext.obtainStyledAttributes(new int[]{R.attr.placeholder_album_artwork_large});
            Drawable drawable2 = obtainStyledAttributes3.getDrawable(0);
            obtainStyledAttributes3.recycle();
            ((SquareImageView) findViewById.findViewById(R.id.imgProductIcon)).setImageDrawable(drawable2);
            businessObject2 = (BusinessObject) businessObject.getArrListBusinessObj().get(0);
            this.itemTitle = (TextView) findViewById.findViewById(R.id.f54grid.item.tv.name);
            this.itemTitle.setText(businessObject2.getName());
            if (findViewById.findViewById(R.id.f51grid.item.image.favorite).getVisibility() != 0) {
                findViewById.findViewById(R.id.f51grid.item.image.favorite).setVisibility(4);
            } else if (businessObject2.getBusinessObjType() != BusinessObjectType.Artists) {
                new int[1][0] = R.attr.moreoptions_favorite;
                obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                Drawable drawable3 = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(49, -1));
                obtainStyledAttributes.recycle();
                ((ImageView) findViewById.findViewById(R.id.f51grid.item.image.favorite)).setImageDrawable(drawable3);
            } else {
                new int[1][0] = R.attr.moreoptions_follow;
                obtainStyledAttributes2 = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes2.getResourceId(53, -1));
                obtainStyledAttributes2.recycle();
                ((ImageView) findViewById.findViewById(R.id.f51grid.item.image.favorite)).setImageDrawable(drawable);
            }
            if (findViewById.findViewById(R.id.f50grid.item.image.download).getVisibility() == 0) {
                new int[1][0] = R.attr.download_button_paused;
                obtainStyledAttributes2 = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes2.getResourceId(13, -1));
                obtainStyledAttributes2.recycle();
                ((ImageView) findViewById.findViewById(R.id.f50grid.item.image.download)).setImageDrawable(drawable);
            } else {
                findViewById.findViewById(R.id.f50grid.item.image.download).setVisibility(4);
            }
            findViewById.findViewById(R.id.download_ProgressBar).setVisibility(4);
            if (findViewById.findViewById(R.id.f52grid.item.text.favoritecount) != null) {
                updateFavoriteCount((TextView) findViewById.findViewById(R.id.f52grid.item.text.favoritecount), businessObject2, findViewById.findViewById(R.id.f51grid.item.image.favorite).getVisibility());
            }
        } else {
            businessObject2 = null;
        }
        View findViewById2 = view2.findViewById(R.id.f60ll.grid.seconditem);
        if (findViewById2 != null) {
            if (businessObject.getArrListBusinessObj().size() == 1) {
                findViewById2.setVisibility(8);
            } else {
                findViewById2.setVisibility(0);
                obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{R.attr.placeholder_album_artwork_large});
                Drawable drawable4 = obtainStyledAttributes.getDrawable(0);
                obtainStyledAttributes.recycle();
                ((SquareImageView) findViewById2.findViewById(R.id.imgProductIcon)).setImageDrawable(drawable4);
                businessObject2 = (BusinessObject) businessObject.getArrListBusinessObj().get(1);
                this.itemTitle = (TextView) findViewById2.findViewById(R.id.f54grid.item.tv.name);
                this.itemTitle.setText(businessObject2.getName());
                if (findViewById2.findViewById(R.id.f51grid.item.image.favorite).getVisibility() != 0) {
                    findViewById2.findViewById(R.id.f51grid.item.image.favorite).setVisibility(4);
                } else if (businessObject2.getBusinessObjType() != BusinessObjectType.Artists) {
                    new int[1][0] = R.attr.moreoptions_favorite;
                    TypedArray obtainStyledAttributes4 = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                    Drawable drawable5 = ContextCompat.getDrawable(getContext(), obtainStyledAttributes4.getResourceId(49, -1));
                    obtainStyledAttributes4.recycle();
                    ((ImageView) findViewById.findViewById(R.id.f51grid.item.image.favorite)).setImageDrawable(drawable5);
                } else {
                    new int[1][0] = R.attr.moreoptions_follow;
                    TypedArray obtainStyledAttributes5 = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                    Drawable drawable6 = ContextCompat.getDrawable(getContext(), obtainStyledAttributes5.getResourceId(53, -1));
                    obtainStyledAttributes5.recycle();
                    ((ImageView) findViewById.findViewById(R.id.f51grid.item.image.favorite)).setImageDrawable(drawable6);
                }
                if (findViewById2.findViewById(R.id.f50grid.item.image.download).getVisibility() == 0) {
                    new int[1][0] = R.attr.download_button_paused;
                    TypedArray obtainStyledAttributes6 = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                    Drawable drawable7 = ContextCompat.getDrawable(getContext(), obtainStyledAttributes6.getResourceId(13, -1));
                    obtainStyledAttributes6.recycle();
                    ((ImageView) findViewById2.findViewById(R.id.f50grid.item.image.download)).setImageDrawable(drawable7);
                } else {
                    findViewById2.findViewById(R.id.f50grid.item.image.download).setVisibility(4);
                }
                findViewById2.findViewById(R.id.download_ProgressBar).setVisibility(4);
                if (findViewById2.findViewById(R.id.f52grid.item.text.favoritecount) != null) {
                    updateFavoriteCount((TextView) findViewById2.findViewById(R.id.f52grid.item.text.favoritecount), businessObject2, findViewById2.findViewById(R.id.f51grid.item.image.favorite).getVisibility());
                }
            }
        }
        getHeaderText(businessObject2);
        if (view2.findViewById(R.id.f55header.text) != null) {
            ((TextView) view2.findViewById(R.id.f55header.text)).setText(this.headerText);
        }
        return view2;
    }

    public View getDefaultPoplatedView(TwoGridItemHolder twoGridItemHolder, BusinessObject businessObject) {
        TypedArray obtainStyledAttributes;
        BusinessObject businessObject2;
        View view = twoGridItemHolder.itemView;
        GridItemHolder gridItemHolder = twoGridItemHolder.firstHolder;
        View view2 = gridItemHolder.itemView;
        if (gridItemHolder != null) {
            view2.setVisibility(0);
            obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{R.attr.placeholder_album_artwork_large});
            Drawable drawable = obtainStyledAttributes.getDrawable(0);
            obtainStyledAttributes.recycle();
            gridItemHolder.imageViewThumb.setImageDrawable(drawable);
            businessObject2 = (BusinessObject) businessObject.getArrListBusinessObj().get(0);
            this.itemTitle = gridItemHolder.tvName;
            this.itemTitle.setText(businessObject2.getName());
            TextView textView = gridItemHolder.albumName;
            StringBuilder stringBuilder;
            if (businessObject.getBusinessObjType() == BusinessObjectType.Albums) {
                Album album = (Album) businessObject;
                stringBuilder = new StringBuilder();
                stringBuilder.append(album.getFavoriteCount());
                stringBuilder.append(this.mContext.getString(R.string.favorites));
                textView.setText(stringBuilder.toString());
            } else if (businessObject.getBusinessObjType() == BusinessObjectType.Playlists) {
                Playlist playlist = (Playlist) businessObject;
                stringBuilder = new StringBuilder();
                stringBuilder.append(playlist.getFavoriteCount());
                stringBuilder.append(this.mContext.getString(R.string.favorites));
                textView.setText(stringBuilder.toString());
            }
        } else {
            businessObject2 = null;
        }
        gridItemHolder = twoGridItemHolder.secondHolder;
        View view3 = gridItemHolder.itemView;
        if (view3 != null) {
            if (businessObject.getArrListBusinessObj().size() == 1) {
                view3.setVisibility(8);
            } else {
                view3.setVisibility(0);
                obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{R.attr.placeholder_album_artwork_large});
                Drawable drawable2 = obtainStyledAttributes.getDrawable(0);
                obtainStyledAttributes.recycle();
                gridItemHolder.imageViewThumb.setImageDrawable(drawable2);
                businessObject2 = (BusinessObject) businessObject.getArrListBusinessObj().get(1);
                this.itemTitle = gridItemHolder.tvName;
                this.itemTitle.setText(businessObject2.getName());
            }
        }
        getHeaderText(businessObject2);
        if (twoGridItemHolder.headerText != null) {
            twoGridItemHolder.headerText.setText(this.headerText);
        }
        return view;
    }

    private void updateFavoriteCount(TextView textView, BusinessObject businessObject, int i) {
        String favorite_count = businessObject instanceof Radio ? ((Radio) businessObject).getFavorite_count() : businessObject instanceof DiscoverTag ? ((DiscoverTag) businessObject).getFavorite_count() : null;
        if (favorite_count == null) {
            textView.setVisibility(8);
            return;
        }
        if (!favorite_count.equalsIgnoreCase("0")) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(favorite_count);
            stringBuilder.append(getContext().getString(R.string.favorites));
            textView.setText(stringBuilder.toString());
        } else if (i == 0) {
            textView.setText(R.string.favorite_now);
        } else {
            textView.setText(R.string.download_now);
        }
        textView.setVisibility(0);
    }

    public View getNewView(int i, ViewGroup viewGroup, BusinessObject businessObject) {
        return super.createNewBaseView(this.mLayoutResourceId, null, viewGroup);
    }

    public View getPoplatedView(View view, BusinessObject businessObject, ViewGroup viewGroup) {
        if (view == null) {
            view = super.createNewBaseView(this.mLayoutResourceId, view, viewGroup);
        }
        view.setClickable(false);
        if (businessObject != null) {
            ArrayList arrListBusinessObj = businessObject.getArrListBusinessObj();
            int size = arrListBusinessObj.size();
            View findViewById = view.findViewById(R.id.f58ll.grid.firstitem);
            findViewById.setVisibility(0);
            if (arrListBusinessObj.size() > 0) {
                initialiseGridItem(findViewById, (BusinessObject) arrListBusinessObj.get(0));
            }
            ((TextView) view.findViewById(R.id.f55header.text)).setText(this.headerText);
            if (size == 2) {
                View findViewById2 = view.findViewById(R.id.f60ll.grid.seconditem);
                findViewById2.setVisibility(0);
                initialiseGridItem(findViewById2, (BusinessObject) arrListBusinessObj.get(1));
            } else {
                view.findViewById(R.id.f60ll.grid.seconditem).setVisibility(8);
            }
        }
        return view;
    }

    public View getPoplatedView(ViewHolder viewHolder, BusinessObject businessObject, ViewGroup viewGroup) {
        TwoGridItemHolder twoGridItemHolder = (TwoGridItemHolder) viewHolder;
        twoGridItemHolder.itemView.setClickable(false);
        if (businessObject != null) {
            ArrayList arrListBusinessObj = businessObject.getArrListBusinessObj();
            int size = arrListBusinessObj.size();
            GridItemHolder gridItemHolder = twoGridItemHolder.firstHolder;
            gridItemHolder.itemView.setVisibility(0);
            if (arrListBusinessObj.size() > 0) {
                initialiseGridItem(gridItemHolder, (BusinessObject) arrListBusinessObj.get(0));
            }
            twoGridItemHolder.headerText.setText(this.headerText);
            gridItemHolder = twoGridItemHolder.secondHolder;
            if (size == 2) {
                gridItemHolder.itemView.setVisibility(0);
                initialiseGridItem(gridItemHolder, (BusinessObject) arrListBusinessObj.get(1));
            } else {
                gridItemHolder.itemView.setVisibility(8);
            }
        }
        return twoGridItemHolder.itemView;
    }

    private int getLayoutId(BusinessObject businessObject, boolean z) {
        return (!(businessObject instanceof Radio) || ((Radio) businessObject).getFavorite_count() == null) ? (!(businessObject instanceof DiscoverTag) || ((DiscoverTag) businessObject).getFavorite_count() == null) ? z ? R.layout.grid_twoitem_view : R.layout.view_grid_item : z ? R.layout.grid_twoitem_view_favorite : R.layout.view_grid_item_favoritecount : z ? R.layout.grid_twoitem_view_favorite : R.layout.view_grid_item_favoritecount;
    }

    private void getHeaderText(BusinessObject businessObject) {
        if (businessObject instanceof Item) {
            String entityType = ((Item) businessObject).getEntityType();
            if (entityType.equals(d.c) || entityType.equals(d.d)) {
                if (this.mFragment instanceof RadioDetailsMaterialListing) {
                    this.headerText = getContext().getString(R.string.other_radio_stations);
                } else {
                    this.headerText = getContext().getString(R.string.gaana_radios);
                }
            } else if (entityType.equals(c.b)) {
                this.headerText = getContext().getString(R.string.popular_albums);
            } else if (entityType.equals(c.a)) {
                this.headerText = getContext().getString(R.string.popular_playlists);
            }
        } else if (businessObject.getBusinessObjType() == BusinessObjectType.Artists) {
            this.headerText = getContext().getString(R.string.popular_artists);
        } else if (businessObject.getBusinessObjType() == BusinessObjectType.Tracks) {
            this.headerText = getContext().getString(R.string.popular_songs);
        } else if (businessObject.getBusinessObjType() == BusinessObjectType.Discover) {
            this.headerText = "Re";
        }
    }

    private void initialiseGridItem(GridItemHolder gridItemHolder, BusinessObject businessObject) {
        View view = gridItemHolder.itemView;
        this.imageThumb = gridItemHolder.imageViewThumb;
        this.itemTitle = gridItemHolder.tvName;
        view.setTag(businessObject);
        this.imageThumb.setTag(businessObject);
        view.setOnClickListener(this);
        if (businessObject instanceof Item) {
            Item item = (Item) businessObject;
            this.artWork = item.getArtwork();
            this.itemTitle.setText(item.getName());
            getHeaderText(businessObject);
        } else if (businessObject.getBusinessObjType() == BusinessObjectType.Artists) {
            Artist artist = (Artist) businessObject;
            this.artWork = artist.getArtwork();
            this.itemTitle.setText(artist.getName());
            getHeaderText(businessObject);
        } else if (businessObject.getBusinessObjType() == BusinessObjectType.Tracks) {
            Track track = (Track) businessObject;
            this.artWork = track.getArtwork();
            this.itemTitle.setText(track.getName());
            getHeaderText(businessObject);
        }
        if (this.artWork != null) {
            this.artWork = this.artWork.replace("80x80", "175x200");
        }
        this.imageThumb.bindImage(this.artWork, this.imageThumb.getScaleType());
        initFavoriteDownloadUI(view, businessObject);
    }

    private void initialiseGridItem(View view, BusinessObject businessObject) {
        this.imageThumb = (CrossFadeImageView) view.findViewById(R.id.imgProductIcon);
        this.itemTitle = (TextView) view.findViewById(R.id.f54grid.item.tv.name);
        TextView textView = (TextView) view.findViewById(R.id.f52grid.item.text.favoritecount);
        view.setTag(businessObject);
        this.imageThumb.setTag(businessObject);
        view.setOnClickListener(this);
        if (businessObject.getBusinessObjType() == BusinessObjectType.Radios) {
            Radio radio = (Radio) businessObject;
            this.artWork = radio.getArtwork();
            this.itemTitle.setText(radio.getName());
            getHeaderText(businessObject);
        } else if (businessObject.getBusinessObjType() == BusinessObjectType.Artists) {
            Artist artist = (Artist) businessObject;
            this.artWork = artist.getArtwork();
            this.itemTitle.setText(artist.getName());
            getHeaderText(businessObject);
        } else if (businessObject.getBusinessObjType() == BusinessObjectType.Tracks) {
            Track track = (Track) businessObject;
            this.artWork = track.getArtwork();
            this.itemTitle.setText(track.getName());
            getHeaderText(businessObject);
        } else if (businessObject.getBusinessObjType() == BusinessObjectType.Albums) {
            Album album = (Album) businessObject;
            this.artWork = album.getArtwork();
            this.itemTitle.setText(album.getName());
            getHeaderText(businessObject);
        } else if (businessObject.getBusinessObjType() == BusinessObjectType.Playlists) {
            Playlist playlist = (Playlist) businessObject;
            this.artWork = playlist.getArtwork();
            this.itemTitle.setText(playlist.getName());
            getHeaderText(businessObject);
        }
        if (this.artWork != null) {
            this.artWork = this.artWork.replace("80x80", "175x175");
        }
        this.imageThumb.bindImage(this.artWork, this.mAppState.isAppInOfflineMode());
        initFavoriteDownloadUI(view, businessObject);
        if (textView != null) {
            updateFavoriteCount(textView, businessObject, view.findViewById(R.id.f51grid.item.image.favorite).getVisibility());
        }
    }

    private void initFavoriteDownloadUI(View view, BusinessObject businessObject) {
        this.mProgressBar = (ProgressBar) view.findViewById(R.id.download_ProgressBar);
        this.mProgressBar.setVisibility(8);
        ImageView imageView = (ImageView) view.findViewById(R.id.f50grid.item.image.download);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.f51grid.item.image.favorite);
        imageView.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        if (businessObject instanceof Item) {
            Item item = (Item) businessObject;
            if (item.getEntityType().equals(c.a)) {
                businessObject = populatePlaylistClicked(item);
            } else if (item.getEntityType().equals(c.b)) {
                businessObject = populateAlbumClicked(item);
            } else if (item.getEntityType().equals(d.c) || item.getEntityType().equals(d.d)) {
                businessObject = populateRadioClicked(item);
            }
        }
        if (businessObject.getBusinessObjType() != BusinessObjectType.Artists && !(businessObject instanceof Radio)) {
            DownloadStatus e;
            imageView.setVisibility(0);
            imageView2.setVisibility(8);
            imageView.setTag(businessObject);
            imageView.setOnClickListener(this);
            if (businessObject instanceof Track) {
                e = DownloadManager.c().e(Integer.parseInt(businessObject.getBusinessObjId()));
            } else if (businessObject instanceof Item) {
                e = DownloadManager.c().h(Integer.parseInt(((Item) businessObject).getEntityId()));
            } else {
                e = DownloadManager.c().h(Integer.parseInt(businessObject.getBusinessObjId()));
            }
            TypedArray obtainStyledAttributes;
            Drawable drawable;
            if (e == DownloadStatus.DOWNLOADED) {
                if (!ap.a().k() || Util.a(businessObject)) {
                    imageView.setImageResource(R.drawable.vector_download_completed);
                    return;
                }
                obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                drawable = ContextCompat.getDrawable(this.mContext, obtainStyledAttributes.getResourceId(90, -1));
                obtainStyledAttributes.recycle();
                imageView.setImageDrawable(drawable);
            } else if (e == DownloadStatus.QUEUED) {
                imageView.setImageResource(R.drawable.vector_download_queued);
            } else if (e == DownloadStatus.PAUSED) {
                new int[1][0] = R.attr.download_button_paused;
                obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(13, -1));
                obtainStyledAttributes.recycle();
                imageView.setImageDrawable(drawable);
            } else if (e == DownloadStatus.DOWNLOADING) {
                imageView.setVisibility(8);
                this.mProgressBar.setVisibility(0);
            } else if (e == DownloadStatus.TRIED_BUT_FAILED) {
                obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(91, -1));
                obtainStyledAttributes.recycle();
                imageView.setImageDrawable(drawable);
            } else {
                new int[1][0] = R.attr.download_button_paused;
                obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(13, -1));
                obtainStyledAttributes.recycle();
                imageView.setImageDrawable(drawable);
            }
        } else if ((businessObject instanceof Playlist) && this.mAppState.getCurrentUser().getLoginStatus()) {
            handleUserCreatedPlaylist(businessObject, imageView, imageView2);
        } else {
            imageView2.setVisibility(0);
            imageView.setVisibility(8);
            imageView2.setTag(businessObject);
            imageView2.setOnClickListener(this);
            TypedArray obtainStyledAttributes2;
            Drawable drawable2;
            if (n.e(businessObject)) {
                imageView2.setVisibility(8);
                this.mProgressBar.setVisibility(0);
            } else if (businessObject.isFavorite().booleanValue()) {
                if (businessObject.getBusinessObjType() != BusinessObjectType.Artists) {
                    new int[1][0] = R.attr.moreoptions_favorited;
                    obtainStyledAttributes2 = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                    drawable2 = ContextCompat.getDrawable(getContext(), obtainStyledAttributes2.getResourceId(51, -1));
                    obtainStyledAttributes2.recycle();
                    imageView2.setImageDrawable(drawable2);
                    return;
                }
                new int[1][0] = R.attr.moreoptions_unfollow;
                obtainStyledAttributes2 = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                drawable2 = ContextCompat.getDrawable(getContext(), obtainStyledAttributes2.getResourceId(59, -1));
                obtainStyledAttributes2.recycle();
                imageView2.setImageDrawable(drawable2);
            } else if (businessObject.getBusinessObjType() != BusinessObjectType.Artists) {
                new int[1][0] = R.attr.moreoptions_favorite;
                obtainStyledAttributes2 = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                drawable2 = ContextCompat.getDrawable(getContext(), obtainStyledAttributes2.getResourceId(49, -1));
                obtainStyledAttributes2.recycle();
                imageView2.setImageDrawable(drawable2);
            } else {
                new int[1][0] = R.attr.moreoptions_follow;
                obtainStyledAttributes2 = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                drawable2 = ContextCompat.getDrawable(getContext(), obtainStyledAttributes2.getResourceId(53, -1));
                obtainStyledAttributes2.recycle();
                imageView2.setImageDrawable(drawable2);
            }
        }
    }

    private void handleUserCreatedPlaylist(BusinessObject businessObject, ImageView imageView, ImageView imageView2) {
        Item item = (Item) businessObject;
        ArrayList arrayList = (ArrayList) item.getEntityInfo();
        String str = "";
        if (arrayList != null) {
            int size = arrayList.size();
            String str2 = str;
            for (int i = 0; i < size; i++) {
                if (((EntityInfo) arrayList.get(i)).getKey().equals("created_by_user_id")) {
                    str2 = (String) ((EntityInfo) arrayList.get(i)).getValue();
                }
            }
            str = str2;
        }
        if (this.mAppState.getCurrentUser().getLoginStatus()) {
            String str3 = "";
            if (this.mAppState.getCurrentUser().getUserProfile() != null) {
                str3 = this.mAppState.getCurrentUser().getUserProfile().getUserId();
            }
            TypedArray obtainStyledAttributes;
            if (str == null || !str.equalsIgnoreCase(str3)) {
                imageView.setVisibility(8);
                imageView2.setVisibility(0);
                imageView2.setTag(businessObject);
                imageView2.setOnClickListener(this);
                Drawable drawable;
                if (n.e(businessObject)) {
                    imageView2.setVisibility(8);
                    this.mProgressBar.setVisibility(0);
                    return;
                } else if (businessObject.isFavorite().booleanValue()) {
                    if (businessObject.getBusinessObjType() != BusinessObjectType.Artists) {
                        new int[1][0] = R.attr.moreoptions_favorited;
                        obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                        drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(51, -1));
                        obtainStyledAttributes.recycle();
                        imageView2.setImageDrawable(drawable);
                        return;
                    }
                    new int[1][0] = R.attr.moreoptions_unfollow;
                    obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                    drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(59, -1));
                    obtainStyledAttributes.recycle();
                    imageView2.setImageDrawable(drawable);
                    return;
                } else if (businessObject.getBusinessObjType() != BusinessObjectType.Artists) {
                    new int[1][0] = R.attr.moreoptions_favorite;
                    obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                    drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(49, -1));
                    obtainStyledAttributes.recycle();
                    imageView2.setImageDrawable(drawable);
                    return;
                } else {
                    new int[1][0] = R.attr.moreoptions_follow;
                    obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                    drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(59, -1));
                    obtainStyledAttributes.recycle();
                    imageView2.setImageDrawable(drawable);
                    return;
                }
            }
            imageView.setVisibility(0);
            imageView2.setVisibility(8);
            imageView.setTag(businessObject);
            imageView.setOnClickListener(this);
            DownloadStatus h = DownloadManager.c().h(Integer.parseInt(item.getBusinessObjId()));
            Drawable drawable2;
            if (h == DownloadStatus.DOWNLOADED) {
                if (!ap.a().k() || Util.a(businessObject)) {
                    imageView.setImageResource(R.drawable.vector_download_completed);
                    return;
                }
                obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                drawable2 = ContextCompat.getDrawable(this.mContext, obtainStyledAttributes.getResourceId(90, -1));
                obtainStyledAttributes.recycle();
                imageView.setImageDrawable(drawable2);
            } else if (h == DownloadStatus.QUEUED) {
                imageView.setImageResource(R.drawable.vector_download_queued);
            } else if (h == DownloadStatus.PAUSED) {
                new int[1][0] = R.attr.download_button_paused;
                obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                drawable2 = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(13, -1));
                obtainStyledAttributes.recycle();
                imageView.setImageDrawable(drawable2);
            } else if (h == DownloadStatus.DOWNLOADING) {
                imageView.setVisibility(8);
                this.mProgressBar.setVisibility(0);
            } else if (h == DownloadStatus.TRIED_BUT_FAILED) {
                obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                drawable2 = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(91, -1));
                obtainStyledAttributes.recycle();
                imageView.setImageDrawable(drawable2);
            } else {
                new int[1][0] = R.attr.download_button_paused;
                obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                drawable2 = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(13, -1));
                obtainStyledAttributes.recycle();
                imageView.setImageDrawable(drawable2);
            }
        }
    }

    public void onClick(final View view) {
        this.itemClicked = (BusinessObject) view.getTag();
        if (this.itemClicked != null) {
            super.onClick(view);
            if (this.itemClicked instanceof Item) {
                Item item = (Item) this.itemClicked;
                String entityType = ((Item) this.itemClicked).getEntityType();
                if (entityType.equals(c.a)) {
                    this.itemClicked = (Playlist) populatePlaylistClicked(item);
                } else if (entityType.equals(c.b)) {
                    this.itemClicked = (Album) populateAlbumClicked(item);
                } else if (entityType.equals(d.c) || entityType.equals(d.d)) {
                    this.itemClicked = (Radio) populateRadioClicked(item);
                } else if (entityType.equals(c.c)) {
                    this.itemClicked = (Track) populateTrackClicked(item);
                }
            }
            if ("1".equalsIgnoreCase(this.itemClicked.getLocationAvailability()) && "0".equalsIgnoreCase(this.itemClicked.getDeviceAvailability())) {
                ap.a().a(this.mContext, this.mContext.getString(R.string.error_msg_content_unavailable_for_device));
            } else if ("0".equalsIgnoreCase(this.itemClicked.getLocationAvailability()) && "1".equalsIgnoreCase(this.itemClicked.getDeviceAvailability())) {
                ap.a().a(this.mContext, this.mContext.getString(R.string.error_msg_content_unavailable_for_location));
            } else {
                String str = ((BaseActivity) this.mContext).currentScreen;
                if (!TextUtils.isEmpty(this.headerText)) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(str);
                    stringBuilder.append(" - ");
                    stringBuilder.append(this.headerText);
                    str = stringBuilder.toString();
                }
                switch (view.getId()) {
                    case R.id.f50grid.item.image.download /*2131297273*/:
                        ((BaseActivity) this.mContext).sendGAEvent(((BaseActivity) this.mContext).currentScreen, "Download", str);
                        MoEngage.getInstance().reportDownload(this.itemClicked);
                        DownloadStatus h = DownloadManager.c().h(Integer.parseInt(this.itemClicked.getBusinessObjId()));
                        if (h != null && h != DownloadStatus.PAUSED && h != DownloadStatus.PARTIALLY_DOWNLOADED && h != DownloadStatus.TRIED_BUT_FAILED) {
                            if (h != DownloadStatus.DOWNLOADED) {
                                if (h != DownloadStatus.QUEUED) {
                                    if (h == DownloadStatus.DOWNLOADING) {
                                        new CustomDialogView(this.mContext, this.mContext.getResources().getString(R.string.toast_stop_download), new OnButtonClickListener() {
                                            public void onNegativeButtonClick() {
                                            }

                                            public void onPositiveButtonClick() {
                                                view.setVisibility(0);
                                                DownloadManager.c().r(Integer.parseInt(DownloadGridItemView.this.itemClicked.getBusinessObjId()));
                                                DownloadGridItemView.this.updateDownloadImage((ImageView) view, DownloadManager.c().h(Integer.parseInt(DownloadGridItemView.this.itemClicked.getBusinessObjId())));
                                                DownloadGridItemView.this.updateOfflineTracksStatus();
                                            }
                                        }).show();
                                        break;
                                    }
                                }
                                DownloadManager.c().r(Integer.parseInt(this.itemClicked.getBusinessObjId()));
                                updateDownloadImage((ImageView) view, DownloadManager.c().h(Integer.parseInt(this.itemClicked.getBusinessObjId())));
                                updateOfflineTracksStatus();
                                break;
                            } else if (ap.a().k() && !Util.a(this.itemClicked)) {
                                Util.a(this.mContext, null);
                                u.a().a("Expired Download", "Click", "Album");
                                break;
                            } else {
                                new CustomDialogView(this.mContext, this.mContext.getResources().getString(R.string.toast_delete_downloaded_album), new OnButtonClickListener() {
                                    public void onNegativeButtonClick() {
                                    }

                                    public void onPositiveButtonClick() {
                                        DownloadGridItemView.this.deleteDownload(DownloadGridItemView.this.itemClicked);
                                        DownloadGridItemView.this.updateDownloadImage((ImageView) view, DownloadManager.c().h(Integer.parseInt(DownloadGridItemView.this.itemClicked.getBusinessObjId())));
                                    }
                                }).show();
                                break;
                            }
                        }
                        ((BaseActivity) this.mContext).showProgressDialog(Boolean.valueOf(true), this.mContext.getString(R.string.loading));
                        startDownload(this.itemClicked);
                        break;
                        break;
                    case R.id.f51grid.item.image.favorite /*2131297274*/:
                        if (this.mAppState.getCurrentUser().getLoginStatus() && (this.itemClicked instanceof Radio)) {
                            String favorite_count = ((Radio) this.itemClicked).getFavorite_count();
                            if (!TextUtils.isEmpty(favorite_count)) {
                                if (this.itemClicked.isFavorite().booleanValue()) {
                                    ((Radio) this.itemClicked).setFavoriteCount(String.valueOf(Integer.parseInt(favorite_count) - 1));
                                } else {
                                    ((Radio) this.itemClicked).setFavoriteCount(String.valueOf(Integer.parseInt(favorite_count) + 1));
                                }
                            }
                        }
                        af.a(this.mContext, this.mFragment).a((int) R.id.favoriteMenu, this.itemClicked);
                        break;
                    case R.id.f58ll.grid.firstitem /*2131297578*/:
                    case R.id.f60ll.grid.seconditem /*2131297580*/:
                    case R.id.view_grid_item_relative /*2131298869*/:
                        showDetailPage();
                        break;
                }
            }
        }
    }

    private boolean isItemAvailableForOffline(BusinessObject businessObject) {
        if (businessObject.getBusinessObjType() == BusinessObjectType.Radios || businessObject.getBusinessObjType() == BusinessObjectType.Artists) {
            return false;
        }
        if (businessObject.getBusinessObjType() == BusinessObjectType.Tracks) {
            return DownloadManager.c().l(Integer.parseInt(businessObject.getBusinessObjId())).booleanValue();
        }
        if (businessObject.getBusinessObjType() == BusinessObjectType.Albums || businessObject.getBusinessObjType() == BusinessObjectType.Playlists) {
            return DownloadManager.c().b(businessObject).booleanValue();
        }
        return false;
    }

    private void showDetailPage() {
        boolean isItemAvailableForOffline = isItemAvailableForOffline(this.itemClicked);
        if (this.mAppState.isAppInOfflineMode() && !isItemAvailableForOffline) {
            String str = null;
            if (this.itemClicked instanceof Album) {
                str = getContext().getString(R.string.this_album);
            } else if (this.itemClicked.getBusinessObjType() == BusinessObjectType.Tracks) {
                str = getContext().getString(R.string.this_track);
            } else if (this.itemClicked instanceof Playlist) {
                str = getContext().getString(R.string.this_playlist);
            } else if (this.itemClicked instanceof Radio) {
                str = getContext().getString(R.string.this_radio);
            } else if (this.itemClicked.getBusinessObjType() == BusinessObjectType.Artists) {
                str = getContext().getString(R.string.this_artist);
            }
            if (str != null) {
                ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(str);
                return;
            }
        } else if (!(Util.j(this.mContext) || isItemAvailableForOffline)) {
            ap.a().f(this.mContext);
            return;
        }
        BusinessObject businessObject;
        BaseActivity baseActivity;
        String str2;
        StringBuilder stringBuilder;
        String stringBuilder2;
        StringBuilder stringBuilder3;
        String artwork;
        if (this.itemClicked instanceof Radio) {
            if (Constants.cY) {
                JukeSessionManager.getErrorDialog(this.mContext, 0, new OnButtonClickListener() {
                    public void onNegativeButtonClick() {
                    }

                    public void onPositiveButtonClick() {
                        JukeSessionManager.getInstance().stopJukeSession(new s() {
                            public void onErrorResponse(BusinessObject businessObject) {
                            }

                            public void onRetreivalComplete(BusinessObject businessObject) {
                                if (((JukePlaylist) businessObject).getPlStatus() == 1) {
                                    DownloadGridItemView.this.showDetailPage();
                                }
                            }
                        });
                    }
                });
                return;
            }
            businessObject = (Radio) this.itemClicked;
            if (businessObject.getType().equals(d.c)) {
                ad.a(this.mContext).a(businessObject);
            } else {
                ad.a(this.mContext).a("https://api.gaana.com/radio.php?type=radio&subtype=radiodetail&radio_id=<radio_id>&radio_type=<radio_type>&limit=0,50".replace("<radio_id>", businessObject.getBusinessObjId()).replace("<radio_type>", businessObject.getType()), SOURCE_TYPE.GAANA_RADIO.ordinal(), businessObject);
            }
            if (this.headerText != null) {
                baseActivity = (BaseActivity) this.mContext;
                str2 = ((BaseActivity) this.mContext).currentScreen;
                stringBuilder = new StringBuilder();
                stringBuilder.append("Radio Detail  : ");
                stringBuilder.append(this.itemClicked.getEnglishName());
                stringBuilder2 = stringBuilder.toString();
                stringBuilder3 = new StringBuilder();
                stringBuilder3.append(((BaseActivity) this.mContext).currentScreen);
                stringBuilder3.append(" - ");
                stringBuilder3.append(this.headerText);
                baseActivity.sendGAEvent(str2, stringBuilder2, stringBuilder3.toString());
            } else {
                baseActivity = (BaseActivity) this.mContext;
                str2 = ((BaseActivity) this.mContext).currentScreen;
                stringBuilder = new StringBuilder();
                stringBuilder.append("Radio Detail  : ");
                stringBuilder.append(this.itemClicked.getEnglishName());
                baseActivity.sendGAEvent(str2, stringBuilder.toString(), ((BaseActivity) this.mContext).currentScreen);
            }
            this.mListingComponents = Constants.a((Radio) businessObject);
            this.mListingComponents.a(businessObject);
            populateRadioListing(businessObject);
        } else if (this.itemClicked instanceof Playlist) {
            businessObject = (Playlist) this.itemClicked;
            this.mListingComponents = Constants.e();
            this.mListingComponents.a(businessObject);
            artwork = businessObject.getArtwork();
            if (artwork.contains("80x80")) {
                artwork.replace("80x80", "175x175");
            }
            if (this.headerText != null) {
                baseActivity = (BaseActivity) this.mContext;
                str2 = ((BaseActivity) this.mContext).currentScreen;
                stringBuilder = new StringBuilder();
                stringBuilder.append("Playlist Detail  : ");
                stringBuilder.append(this.itemClicked.getEnglishName());
                stringBuilder2 = stringBuilder.toString();
                stringBuilder3 = new StringBuilder();
                stringBuilder3.append(((BaseActivity) this.mContext).currentScreen);
                stringBuilder3.append(" - ");
                stringBuilder3.append(this.headerText);
                baseActivity.sendGAEvent(str2, stringBuilder2, stringBuilder3.toString());
            } else {
                baseActivity = (BaseActivity) this.mContext;
                str2 = ((BaseActivity) this.mContext).currentScreen;
                stringBuilder = new StringBuilder();
                stringBuilder.append("Playlist Detail  : ");
                stringBuilder.append(this.itemClicked.getEnglishName());
                baseActivity.sendGAEvent(str2, stringBuilder.toString(), ((BaseActivity) this.mContext).currentScreen);
            }
            if (businessObject.isGaanaSpecial()) {
                populateSpecialGaanaListing(businessObject);
            } else {
                populatePlaylistListing(businessObject);
            }
        } else if (this.itemClicked instanceof Track) {
            businessObject = (Track) this.itemClicked;
            ArrayList arrayList = new ArrayList();
            arrayList.add(businessObject);
            PlayerManager.a(this.mContext).a(com.logging.d.a().a(this.mFragment, arrayList), com.logging.d.a().a(this.mFragment, businessObject));
            if (this.headerText != null) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(((BaseActivity) this.mContext).currentScreen);
                stringBuilder.append(" - ");
                stringBuilder.append(this.headerText);
                ((BaseActivity) this.mContext).sendGAEvent(((BaseActivity) this.mContext).currentScreen, "Play", stringBuilder.toString());
            } else {
                ((BaseActivity) this.mContext).sendGAEvent(((BaseActivity) this.mContext).currentScreen, "Play", ((BaseActivity) this.mContext).currentScreen);
            }
            PlayerManager.a(this.mContext).a(PlayerType.GAANA, this.mContext);
            ((GaanaActivity) this.mContext).setUpdatePlayerFragment();
        } else if (this.itemClicked instanceof Album) {
            this.mListingComponents = Constants.b();
            this.mListingComponents.a((Album) this.itemClicked);
            BaseActivity baseActivity2;
            StringBuilder stringBuilder4;
            if (this.headerText != null) {
                baseActivity2 = (BaseActivity) this.mContext;
                artwork = ((BaseActivity) this.mContext).currentScreen;
                stringBuilder4 = new StringBuilder();
                stringBuilder4.append("Album Detail : ");
                stringBuilder4.append(this.itemClicked.getEnglishName());
                str2 = stringBuilder4.toString();
                stringBuilder = new StringBuilder();
                stringBuilder.append(((BaseActivity) this.mContext).currentScreen);
                stringBuilder.append(" - ");
                stringBuilder.append(this.headerText);
                baseActivity2.sendGAEvent(artwork, str2, stringBuilder.toString());
            } else {
                baseActivity2 = (BaseActivity) this.mContext;
                artwork = ((BaseActivity) this.mContext).currentScreen;
                stringBuilder4 = new StringBuilder();
                stringBuilder4.append("Album Detail : ");
                stringBuilder4.append(this.itemClicked.getEnglishName());
                baseActivity2.sendGAEvent(artwork, stringBuilder4.toString(), ((BaseActivity) this.mContext).currentScreen);
            }
            this.mBusinessObject = this.itemClicked;
            populateAlbumListing(this.mBusinessObject);
        } else if (this.itemClicked.getBusinessObjType() == BusinessObjectType.Artists) {
            Artist artist = (Artist) this.itemClicked;
            this.mListingComponents = Constants.a("", artist.isLocalMedia());
            this.mAppState.setListingComponents(this.mListingComponents);
            if (this.headerText != null) {
                stringBuilder3 = new StringBuilder();
                stringBuilder3.append(((BaseActivity) this.mContext).currentScreen);
                stringBuilder3.append(" - ");
                stringBuilder3.append(this.headerText);
                ((BaseActivity) this.mContext).sendGAEvent(((BaseActivity) this.mContext).currentScreen, "Artist Detail", stringBuilder3.toString());
            } else {
                ((BaseActivity) this.mContext).sendGAEvent(((BaseActivity) this.mContext).currentScreen, "Artist Detail", ((BaseActivity) this.mContext).currentScreen);
            }
            populateArtistListing(artist);
        }
    }

    public void getPopulatedViewGrid(View view, BusinessObject businessObject) {
        SquareImageView squareImageView = (SquareImageView) view.findViewById(R.id.imgProductIcon);
        TextView textView = (TextView) view.findViewById(R.id.f54grid.item.tv.name);
        View findViewById = view.findViewById(R.id.view_grid_item_relative);
        TextView textView2 = (TextView) view.findViewById(R.id.f52grid.item.text.favoritecount);
        findViewById.setTag(businessObject);
        findViewById.setOnClickListener(this);
        squareImageView.setTag(businessObject);
        initFavoriteDownloadUI(view, businessObject);
        String artwork;
        if (businessObject instanceof Album) {
            Album album = (Album) businessObject;
            artwork = album.getArtwork();
            if (artwork != null) {
                artwork = artwork.replace("80x80", "175x175");
            }
            squareImageView.bindImage(artwork, this.mAppState.isAppInOfflineMode());
            textView.setText(album.getName());
        } else if (businessObject instanceof Playlist) {
            Playlist playlist = (Playlist) businessObject;
            artwork = playlist.getArtwork();
            if (artwork != null) {
                artwork = artwork.replace("80x80", "175x175");
            }
            squareImageView.bindImage(artwork, this.mAppState.isAppInOfflineMode());
            textView.setText(playlist.getName());
        } else if (businessObject instanceof Radio) {
            Radio radio = (Radio) businessObject;
            squareImageView.bindImage(radio.getArtwork(), this.mAppState.isAppInOfflineMode());
            textView.setText(radio.getName());
        }
        if (textView2 != null) {
            updateFavoriteCount(textView2, businessObject, view.findViewById(R.id.f51grid.item.image.favorite).getVisibility());
        }
        view.setOnClickListener(this);
        super.getPoplatedView(view, businessObject, null);
    }
}
