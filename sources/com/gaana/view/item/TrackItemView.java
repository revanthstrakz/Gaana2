package com.gaana.view.item;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.constants.Constants;
import com.fragments.BaseGaanaFragment;
import com.fragments.DownloadDetailsFragment;
import com.fragments.MyMusicFragment;
import com.fragments.MyMusicItemFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.models.BusinessObject;
import com.gaana.models.Item;
import com.gaana.models.Tracks.Track;
import com.gaana.view.item.BaseItemView.GridItemHolder;
import com.gaana.view.item.BaseItemView.PlaylistGridHolder;
import com.library.controls.CrossFadeImageView;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.logging.GaanaLogger.SOURCE_TYPE;
import com.logging.d;
import com.managers.PlayerManager;
import com.managers.PlayerManager.PlayerType;
import com.managers.aq;
import com.models.PlayerTrack;
import com.services.l.ai;
import com.services.l.ba;
import com.utilities.Util;
import java.util.ArrayList;
import java.util.Iterator;

public class TrackItemView extends BaseItemView {
    private int albumPosition;
    private CrossFadeImageView mCrossFadeImageIcon;
    ai onPlayNextItemClicked;
    private TextView tvBottomHeading;
    private TextView tvTopHeading;

    class TagExtra {
        BusinessObject businessObject;
        int index;

        TagExtra() {
        }

        public BusinessObject getBusinessObject() {
            return this.businessObject;
        }

        public void setBusinessObject(BusinessObject businessObject) {
            this.businessObject = businessObject;
        }

        public int getIndex() {
            return this.index;
        }

        public void setIndex(int i) {
            this.index = i;
        }
    }

    public int getAlbumPosition() {
        return this.albumPosition;
    }

    public TrackItemView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        this.albumPosition = -1;
        this.mLayoutId = R.layout.view_item_similar_item;
        ((BaseActivity) this.mContext).currentItem = "Album";
    }

    public View getPoplatedView(View view, BusinessObject businessObject, ViewGroup viewGroup) {
        if (view == null) {
            view = super.createNewBaseView(R.layout.view_item_similar_item, view, viewGroup);
        }
        super.getPoplatedView(view, businessObject);
        view.findViewById(R.id.clickoptionImage).setTag(businessObject);
        view.findViewById(R.id.clickoptionImage).setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                TrackItemView.this.showOptionMenu(view);
            }
        });
        return getDataFilledView(view, businessObject);
    }

    private View getDataFilledView(View view, BusinessObject businessObject) {
        Track track = (Track) businessObject;
        this.mCrossFadeImageIcon = (CrossFadeImageView) view.findViewById(R.id.imgProductIcon);
        this.tvTopHeading = (TextView) view.findViewById(R.id.tvTopHeading);
        this.tvBottomHeading = (TextView) view.findViewById(R.id.tvBottomHeading);
        this.mCrossFadeImageIcon.bindImage(track.getArtwork().replace("80x80", "175x175"), this.mAppState.isAppInOfflineMode());
        this.tvTopHeading.setText(track.getName());
        this.tvBottomHeading.setText(track.getArtistNames().toUpperCase());
        int i = Constants.l ? R.drawable.vector_ic_explicit_content_white : R.drawable.vector_ic_explicit_content;
        if (track.isParentalWarningEnabled()) {
            this.tvBottomHeading.setCompoundDrawablesWithIntrinsicBounds(this.mContext.getResources().getDrawable(i), null, null, null);
        } else {
            this.tvBottomHeading.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
        this.tvTopHeading.setTextAppearance(this.mContext, R.style.grid_caption);
        this.tvBottomHeading.setTextAppearance(this.mContext, R.style.item_text_second_line);
        return view;
    }

    private View getDataFilledView(PlaylistGridHolder playlistGridHolder, BusinessObject businessObject) {
        Track track = (Track) businessObject;
        this.mCrossFadeImageIcon = playlistGridHolder.crossFadeImageView;
        this.tvTopHeading = playlistGridHolder.tvTopHeading;
        this.mCrossFadeImageIcon.bindImage(track.getArtwork().replace("80x80", "175x175"), this.mAppState.isAppInOfflineMode());
        if (track.isParentalWarningEnabled()) {
            Util.a(this.tvTopHeading, track.getName());
        } else {
            this.tvTopHeading.setText(track.getName());
        }
        return playlistGridHolder.itemView;
    }

    private View getDataFilledView(GridItemHolder gridItemHolder, BusinessObject businessObject) {
        Track track = (Track) businessObject;
        this.mCrossFadeImageIcon = gridItemHolder.imageViewThumb;
        this.tvTopHeading = gridItemHolder.tvName;
        this.mCrossFadeImageIcon.bindImage(track.getArtwork().replace("80x80", "175x175"));
        if (track.isParentalWarningEnabled()) {
            Util.a(this.tvTopHeading, track.getName());
        } else {
            this.tvTopHeading.setText(track.getName());
        }
        if (TextUtils.isEmpty(track.getPlayCount())) {
            gridItemHolder.mImgIndicator.setVisibility(4);
            gridItemHolder.mTxtPlayCount.setVisibility(4);
        } else {
            gridItemHolder.mImgIndicator.setImageDrawable(ContextCompat.getDrawable(this.mContext, R.drawable.vector_ic_tracks_indicator));
            gridItemHolder.mTxtPlayCount.setText(track.getPlayCount());
            gridItemHolder.mImgIndicator.setVisibility(0);
            gridItemHolder.mTxtPlayCount.setVisibility(0);
        }
        gridItemHolder.play_icon.setVisibility(0);
        return gridItemHolder.itemView;
    }

    public View getPoplatedViewForGrid(ViewHolder viewHolder, BusinessObject businessObject, ViewGroup viewGroup, ai aiVar) {
        PlaylistGridHolder playlistGridHolder = (PlaylistGridHolder) viewHolder;
        this.mView = playlistGridHolder.itemView;
        this.mView.setTag(businessObject);
        this.mView.setOnClickListener(this);
        this.onPlayNextItemClicked = aiVar;
        super.getPoplatedView(this.mView, businessObject, viewGroup);
        return getDataFilledView(playlistGridHolder, businessObject);
    }

    public View getPoplatedViewForGrid(ViewHolder viewHolder, BusinessObject businessObject, ViewGroup viewGroup, ai aiVar, int i) {
        PlaylistGridHolder playlistGridHolder = (PlaylistGridHolder) viewHolder;
        this.mView = playlistGridHolder.itemView;
        TagExtra tagExtra = new TagExtra();
        tagExtra.businessObject = businessObject;
        tagExtra.index = i;
        this.mView.setOnClickListener(this);
        this.onPlayNextItemClicked = aiVar;
        super.getPoplatedView(this.mView, businessObject, viewGroup);
        this.mView.setTag(tagExtra);
        return getDataFilledView(playlistGridHolder, businessObject);
    }

    public View getPoplatedViewForGrid(ViewHolder viewHolder, BusinessObject businessObject, ViewGroup viewGroup) {
        GridItemHolder gridItemHolder = (GridItemHolder) viewHolder;
        this.mView = gridItemHolder.itemView;
        TagExtra tagExtra = new TagExtra();
        tagExtra.businessObject = businessObject;
        this.mView.setOnClickListener(this);
        super.getPoplatedView(this.mView, businessObject, viewGroup);
        this.mView.setTag(tagExtra);
        return getDataFilledView(gridItemHolder, businessObject);
    }

    public void onClick(View view) {
        TagExtra tagExtra = (TagExtra) view.getTag();
        if (this.onPlayNextItemClicked != null) {
            this.onPlayNextItemClicked.a((Track) tagExtra.businessObject, tagExtra.index);
            return;
        }
        if (tagExtra.businessObject instanceof Track) {
            Util.a(this.mContext, (Track) tagExtra.businessObject, view, new ba() {
                public void onPlaySong(View view, Track track) {
                    TrackItemView.this.playTrackClickedSong(track);
                }
            });
        }
    }

    private void playTrackClickedSong(Track track) {
        PlayerTrack playerTrack = new PlayerTrack(track, track.getBusinessObjId(), SOURCE_TYPE.OTHER.ordinal(), track.getEnglishName());
        playerTrack.f(this.mFragment.getPageName());
        playerTrack.d(this.mFragment.getSectionName());
        ArrayList arrayList = new ArrayList();
        ArrayList b = aq.a().b();
        if (b != null && b.size() > 0) {
            Iterator it = b.iterator();
            while (it.hasNext()) {
                arrayList.add(populateTrackClicked((Item) ((BusinessObject) it.next())));
            }
        }
        arrayList = d.a().a(this.mFragment, arrayList);
        if (arrayList != null) {
            arrayList.add(0, playerTrack);
            PlayerManager.a(this.mContext).b(arrayList, playerTrack, 0);
        }
        PlayerManager.a(this.mContext).a(PlayerType.GAANA, this.mContext);
        ((GaanaActivity) this.mContext).setUpdatePlayerFragment();
    }

    public String getSectionName() {
        if (this.mFragment instanceof DownloadDetailsFragment) {
            return PLAYOUT_SECTION_TYPE.DOWNLOADS.name();
        }
        if (this.mFragment instanceof MyMusicFragment) {
            return PLAYOUT_SECTION_TYPE.LOCAL.name();
        }
        if (this.mFragment instanceof MyMusicItemFragment) {
            return PLAYOUT_SECTION_TYPE.MYMUSIC_ALBUMS.name();
        }
        return PLAYOUT_SECTION_TYPE.SIMILAR_ALBUM.name();
    }
}
