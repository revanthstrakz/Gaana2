package com.gaana.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import com.constants.Constants;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.localmedia.LocalMediaImageLoader;
import com.gaana.models.BusinessObject;
import com.gaana.models.Tracks.Track;
import com.gaana.view.DiscreteScrollView;
import com.gaana.view.DiscreteScrollView.OnItemChangedListener;
import com.gaana.view.item.CustomCardView;
import com.gaana.view.item.SquareImageByHeight;
import com.logging.GaanaLogger;
import com.managers.PlayerManager;
import com.managers.PlayerManager.PlayerType;
import com.managers.ap;
import com.managers.f;
import com.managers.u;
import com.models.PlayerTrack;
import com.player_framework.GaanaMusicService;
import com.player_framework.PlayerConstants.PauseReasons;
import com.player_framework.o;
import com.utilities.Util;
import com.utilities.Util.BLOCK_ACTION;
import com.youtube.YouTubePlayerActivity;
import com.youtube.YouTubePlayerActivity.Orientation;
import java.util.ArrayList;

public class CardPagerAdapter extends Adapter<ViewHolder> {
    private final Context mContext;
    private Fragment mFragment;
    private final PlayerManager mPlayerManager;
    private String mPreviousRequestId = "";
    private ArrayList<PlayerTrack> mTrackList;
    private DiscreteScrollView mViewPager;
    private boolean shouldUpdateList = false;

    protected static class CardViewHolder extends ViewHolder {
        private Button btnVideo;
        private SquareImageByHeight imgArtwork;
        private ImageView imgPlayPause;
        private LinearLayout llSocialFavorite;

        public CardViewHolder(View view) {
            super(view);
            this.btnVideo = (Button) view.findViewById(R.id.btn_play_video);
            this.llSocialFavorite = (LinearLayout) view.findViewById(R.id.card_player_title_container);
            this.imgArtwork = (SquareImageByHeight) view.findViewById(R.id.card_player_image);
            this.imgPlayPause = (ImageView) view.findViewById(R.id.card_play_icon);
        }
    }

    public CardPagerAdapter(Context context, Fragment fragment, DiscreteScrollView discreteScrollView, ArrayList<PlayerTrack> arrayList) {
        this.mContext = context;
        this.mFragment = fragment;
        this.mPlayerManager = PlayerManager.a(this.mContext);
        this.mTrackList = arrayList;
        this.mViewPager = discreteScrollView;
        this.mViewPager.addOnItemChangedListener(new OnItemChangedListener<ViewHolder>() {
            public void onCurrentItemChanged(@Nullable ViewHolder viewHolder, int i) {
                CardViewHolder cardViewHolder = (CardViewHolder) viewHolder;
                PlayerTrack i2 = PlayerManager.a(GaanaApplication.getContext()).i();
                if (cardViewHolder != null && i2 != null) {
                    cardViewHolder.llSocialFavorite.setVisibility(4);
                }
            }
        });
    }

    public void setShouldUpdate(boolean z) {
        this.shouldUpdateList = z;
        notifyDataSetChanged();
    }

    public ArrayList<PlayerTrack> getTrackList() {
        return this.mTrackList;
    }

    public int getCount() {
        if (this.mTrackList != null) {
            return !this.shouldUpdateList ? 1 : this.mTrackList.size();
        } else {
            return 0;
        }
    }

    public void updateAndNotifyArrayList(ArrayList<PlayerTrack> arrayList) {
        updateArrayList(arrayList);
        notifyDataSetChanged();
    }

    public void updateArrayList(ArrayList<PlayerTrack> arrayList) {
        this.mTrackList = arrayList;
    }

    private void setTrackDetail(CardViewHolder cardViewHolder, Track track) {
        if (track != null && cardViewHolder.itemView != null && !track.getBusinessObjId().equals(this.mPlayerManager.i().h())) {
            cardViewHolder.llSocialFavorite.setVisibility(4);
            cardViewHolder.imgPlayPause.setVisibility(0);
            cardViewHolder.imgPlayPause.setImageResource(R.drawable.vector_circle_play_button_overlay);
        } else if (cardViewHolder.itemView != null && !GaanaMusicService.s().l()) {
            cardViewHolder.llSocialFavorite.setVisibility(0);
            cardViewHolder.imgPlayPause.setVisibility(8);
        } else if (cardViewHolder.itemView != null) {
            cardViewHolder.llSocialFavorite.setVisibility(0);
            cardViewHolder.imgPlayPause.setVisibility(8);
        }
        if (track != null && track.isLocalMedia()) {
            cardViewHolder.llSocialFavorite.setVisibility(4);
        }
        if (track != null && cardViewHolder.btnVideo != null) {
            if (Constants.cW && Constants.cF && !TextUtils.isEmpty(track.getYoutubeId())) {
                cardViewHolder.btnVideo.setVisibility(0);
            } else {
                cardViewHolder.btnVideo.setVisibility(8);
            }
        }
    }

    public void play(PlayerTrack playerTrack, int i) {
        if (i == this.mPlayerManager.s()) {
            o.c(this.mContext, PauseReasons.MEDIA_BUTTON_TAP);
            return;
        }
        u.a().b("BoxQueue", "Song Play");
        if (i > PlayerManager.a(this.mContext).s()) {
            u.a().a("BoxQueue", "Song Play", "Up Next");
        } else if (PlayerManager.a(this.mContext).s() - i > 100) {
            u.a().a("BoxQueue", "Song Play", "History");
        } else {
            u.a().a("BoxQueue", "Song Play", "History Last 100");
        }
        PlayerManager.a(this.mContext).c();
        playerTrack.d(true);
        PlayerManager.a(this.mContext).a(null, playerTrack, i);
        PlayerManager.a(this.mContext).a(PlayerType.GAANA, this.mContext);
        ((GaanaActivity) this.mContext).setUpdatePlayerFragment();
    }

    /* Access modifiers changed, original: protected */
    public void launchYouTubePlayer(String str, String str2) {
        if (!Util.j(this.mContext) || GaanaApplication.getInstance().isAppInOfflineMode()) {
            ap.a().f(this.mContext);
            return;
        }
        Intent intent = new Intent(this.mContext, YouTubePlayerActivity.class);
        intent.putExtra("orientation", Orientation.AUTO_START_WITH_LANDSCAPE);
        intent.putExtra("video_id", str);
        intent.putExtra("browser_url", str2);
        if (GaanaMusicService.t()) {
            o.a(this.mContext, PauseReasons.MEDIA_BUTTON_TAP);
            Constants.dc = true;
        }
        if (f.v().w()) {
            f.v().F();
            Constants.dc = true;
        }
        ((Activity) this.mContext).startActivityForResult(intent, 101);
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new CardViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_adapter, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        Track a;
        if (this.shouldUpdateList || PlayerManager.a(GaanaApplication.getContext()).s() >= this.mTrackList.size()) {
            a = ((PlayerTrack) this.mTrackList.get(i)).a(true);
        } else {
            a = ((PlayerTrack) this.mTrackList.get(PlayerManager.a(GaanaApplication.getContext()).s())).a(true);
        }
        CardViewHolder cardViewHolder = (CardViewHolder) viewHolder;
        if (a != null) {
            View view = viewHolder.itemView;
            CustomCardView customCardView = (CustomCardView) view.findViewById(R.id.cardView);
            String f = Util.f(this.mContext, a.getArtworkLarge());
            if (a.isLocalMedia()) {
                cardViewHolder.imgArtwork.bindImageForLocalMedia(a.getArtwork(), null, new LocalMediaImageLoader(), false);
            } else {
                cardViewHolder.imgArtwork.bindImage((BusinessObject) a, f, ScaleType.CENTER_CROP);
            }
            setTrackDetail(cardViewHolder, a);
            customCardView.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    if (Constants.aa && ((GaanaActivity) CardPagerAdapter.this.mContext).isSlidingPanelExpanded()) {
                        u.a().a("Shuffle Product", "Gaana+ popup", "Player");
                        Util.a(CardPagerAdapter.this.mContext, BLOCK_ACTION.SKIP);
                    } else if (CardPagerAdapter.this.mPlayerManager == null || CardPagerAdapter.this.mPlayerManager.m() != PlayerType.GAANA_RADIO) {
                        GaanaLogger.a().a(CardPagerAdapter.this.mContext, true, false);
                        CardPagerAdapter.this.play((PlayerTrack) CardPagerAdapter.this.mTrackList.get(i), i);
                    }
                }
            });
            cardViewHolder.btnVideo.setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    u.a().b("Player", "Video Played");
                    Util.a(CardPagerAdapter.this.mContext, a.getYoutubeId(), "", a, 0);
                }
            });
            view.setTag(a.getBusinessObjId());
        }
    }

    public int getItemCount() {
        if (this.mTrackList != null) {
            return !this.shouldUpdateList ? 1 : this.mTrackList.size();
        } else {
            return 0;
        }
    }
}
