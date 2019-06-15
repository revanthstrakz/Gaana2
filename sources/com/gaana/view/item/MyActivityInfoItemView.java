package com.gaana.view.item;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.constants.Constants;
import com.constants.c.c;
import com.fragments.AlbumDetailsMaterialListing;
import com.fragments.BaseGaanaFragment;
import com.fragments.GaanaSpecialDetailsMaterialListing;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.models.BusinessObject;
import com.gaana.models.Item;
import com.gaana.models.Tracks.Track;
import com.gaana.view.item.GenericItemView.TagObject;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.library.controls.CircularImageView;
import com.library.controls.CrossFadeImageView;
import com.logging.GaanaLogger.SOURCE_TYPE;
import com.logging.d;
import com.managers.DownloadManager;
import com.managers.DownloadManager.DownloadStatus;
import com.managers.GaanaSearchManager.SearchType;
import com.managers.PlayerManager;
import com.managers.PlayerManager.PlayerType;
import com.managers.ad;
import com.managers.aj;
import com.managers.ap;
import com.managers.q;
import com.managers.u;
import com.models.ListingComponents;
import com.models.PlayerTrack;
import com.utilities.Util;
import java.util.ArrayList;
import java.util.Iterator;

public class MyActivityInfoItemView extends GenericItemView {

    public static class MyActivityInfoListingHolder extends ViewHolder {
        private ImageView crown_user_badge;
        private RelativeLayout linearLayout;
        private ImageView playImage;
        private TextView socialPostDescription;
        private CrossFadeImageView socialPostImage;
        private TextView songName;
        private CircularImageView userImage;

        public MyActivityInfoListingHolder(View view) {
            super(view);
            this.socialPostImage = (CrossFadeImageView) view.findViewById(R.id.social_post_image);
            this.socialPostDescription = (TextView) view.findViewById(R.id.feedDesciption);
            this.songName = (TextView) view.findViewById(R.id.songName);
            this.userImage = (CircularImageView) view.findViewById(R.id.user_image);
            this.linearLayout = (RelativeLayout) view.findViewById(R.id.playSong);
            this.playImage = (ImageView) view.findViewById(R.id.playImage);
            this.crown_user_badge = (ImageView) view.findViewById(R.id.crown_user_badge);
        }
    }

    public MyActivityInfoItemView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        this.mLayoutId = R.layout.myactivity_card_view;
    }

    public View getPoplatedView(ViewHolder viewHolder, BusinessObject businessObject, ViewGroup viewGroup) {
        MyActivityInfoListingHolder myActivityInfoListingHolder = (MyActivityInfoListingHolder) viewHolder;
        this.mView = viewHolder.itemView;
        this.mView = super.getPoplatedView(this.mView, businessObject);
        return getDataFilledView(myActivityInfoListingHolder, businessObject);
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x0143  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0131  */
    private android.view.View getDataFilledView(com.gaana.view.item.MyActivityInfoItemView.MyActivityInfoListingHolder r14, com.gaana.models.BusinessObject r15) {
        /*
        r13 = this;
        r0 = r15 instanceof com.gaana.models.SocialFeed.FeedData;
        if (r0 == 0) goto L_0x0180;
    L_0x0004:
        r15 = (com.gaana.models.SocialFeed.FeedData) r15;
        r0 = r15.getFeedType();
        r1 = 15;
        if (r0 != r1) goto L_0x0180;
    L_0x000e:
        r0 = r14.socialPostImage;
        r1 = r14.userImage;
        r2 = r14.linearLayout;
        r3 = r14.socialPostDescription;
        r4 = r14.songName;
        r14 = r14.playImage;
        r5 = r15.getFollowPic();
        r6 = r13.mAppState;
        r6 = r6.isAppInOfflineMode();
        r1.bindImage(r5, r6);
        r1 = r15.getFeedDescription();
        r3.setText(r1);
        r15 = r15.getFeedEntity();
        r1 = 0;
        if (r15 == 0) goto L_0x007a;
    L_0x0041:
        r3 = r15.get(r1);
        r3 = (com.gaana.models.Item) r3;
        r3 = r3.getEntityType();
        r5 = "TR";
        r3 = r3.equals(r5);
        if (r3 == 0) goto L_0x0057;
    L_0x0053:
        r14.setVisibility(r1);
        goto L_0x005c;
    L_0x0057:
        r3 = 8;
        r14.setVisibility(r3);
    L_0x005c:
        r14 = r15.get(r1);
        r14 = (com.gaana.models.Item) r14;
        r14 = r14.getArtworkMedium();
        r3 = r0.getScaleType();
        r0.bindImage(r14, r3);
        r14 = r15.get(r1);
        r14 = (com.gaana.models.Item) r14;
        r14 = r14.getName();
        r4.setText(r14);
    L_0x007a:
        r2.setOnClickListener(r13);
        r14 = new com.gaana.view.item.GenericItemView$TagObject;
        r0 = r15.get(r1);
        r0 = (com.gaana.models.BusinessObject) r0;
        r3 = -1;
        r5 = 0;
        r14.<init>(r0, r3, r5);
        r2.setTag(r14);
        r14 = r15.get(r1);
        r14 = (com.gaana.models.Item) r14;
        r14 = r14.getEntityType();
        r0 = "PL";
        r14 = r14.equals(r0);
        if (r14 == 0) goto L_0x00b3;
    L_0x009f:
        r14 = r15.get(r1);
        r14 = (com.gaana.models.Item) r14;
        r14 = com.utilities.Util.b(r14);
        r15 = r14;
        r15 = (com.gaana.models.Playlists.Playlist) r15;
        r15 = r15.isParentalWarningEnabled();
    L_0x00b0:
        r9 = r14;
        goto L_0x012f;
    L_0x00b3:
        r14 = r15.get(r1);
        r14 = (com.gaana.models.Item) r14;
        r14 = r14.getEntityType();
        r0 = "TR";
        r14 = r14.equals(r0);
        if (r14 == 0) goto L_0x00d7;
    L_0x00c5:
        r14 = r15.get(r1);
        r14 = (com.gaana.models.Item) r14;
        r14 = com.utilities.Util.g(r14);
        r15 = r14;
        r15 = (com.gaana.models.Tracks.Track) r15;
        r15 = r15.isParentalWarningEnabled();
        goto L_0x00b0;
    L_0x00d7:
        r14 = r15.get(r1);
        r14 = (com.gaana.models.Item) r14;
        r14 = r14.getEntityType();
        r0 = "AL";
        r14 = r14.equals(r0);
        if (r14 == 0) goto L_0x00fb;
    L_0x00e9:
        r14 = r15.get(r1);
        r14 = (com.gaana.models.Item) r14;
        r14 = com.utilities.Util.c(r14);
        r15 = r14;
        r15 = (com.gaana.models.Albums.Album) r15;
        r15 = r15.isParentalWarningEnabled();
        goto L_0x00b0;
    L_0x00fb:
        r14 = r15.get(r1);
        r14 = (com.gaana.models.Item) r14;
        r14 = r14.getEntityType();
        r0 = "RM";
        r14 = r14.equals(r0);
        if (r14 != 0) goto L_0x0123;
    L_0x010d:
        r14 = r15.get(r1);
        r14 = (com.gaana.models.Item) r14;
        r14 = r14.getEntityType();
        r0 = "RL";
        r14 = r14.equals(r0);
        if (r14 == 0) goto L_0x0120;
    L_0x011f:
        goto L_0x0123;
    L_0x0120:
        r15 = r1;
        r9 = r5;
        goto L_0x012f;
    L_0x0123:
        r14 = r15.get(r1);
        r14 = (com.gaana.models.Item) r14;
        r14 = com.utilities.Util.d(r14);
        r9 = r14;
        r15 = r1;
    L_0x012f:
        if (r15 == 0) goto L_0x0143;
    L_0x0131:
        r14 = r13.mContext;
        r14 = r14.getResources();
        r15 = com.utilities.Util.Y();
        r14 = r14.getDrawable(r15);
        r4.setCompoundDrawablesWithIntrinsicBounds(r14, r5, r5, r5);
        goto L_0x0146;
    L_0x0143:
        r4.setCompoundDrawablesWithIntrinsicBounds(r5, r5, r5, r5);
    L_0x0146:
        r14 = new com.gaanasocial.views.CardBottomLayout;
        r7 = r13.mContext;
        r8 = r13.mFragment;
        r10 = 0;
        r11 = "Activity_Play_";
        r12 = 0;
        r6 = r14;
        r6.<init>(r7, r8, r9, r10, r11, r12);
        r15 = new com.gaanasocial.views.CardBottomLayout$a;
        r15.<init>(r14);
        r14.a(r15);
        r15 = r13.mView;
        r0 = 2131296524; // 0x7f09010c float:1.8210967E38 double:1.0530003936E-314;
        r15 = r15.findViewById(r0);
        r15 = (android.widget.FrameLayout) r15;
        r15.setVisibility(r1);
        r15 = r13.mView;
        r15 = r15.findViewById(r0);
        r15 = (android.widget.FrameLayout) r15;
        r15.removeAllViews();
        r15 = r13.mView;
        r15 = r15.findViewById(r0);
        r15 = (android.widget.FrameLayout) r15;
        r15.addView(r14);
    L_0x0180:
        r14 = r13.mView;
        return r14;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.view.item.MyActivityInfoItemView.getDataFilledView(com.gaana.view.item.MyActivityInfoItemView$MyActivityInfoListingHolder, com.gaana.models.BusinessObject):android.view.View");
    }

    public void onClick(View view) {
        if (view.getTag() instanceof TagObject) {
            this.mBusinessObject = ((TagObject) view.getTag()).getBusinessObject();
            BusinessObject businessObject = this.mBusinessObject;
            if (this.mBusinessObject instanceof Item) {
                if (((Item) this.mBusinessObject).getEntityType().equals(c.c)) {
                    businessObject = (Track) populateTrackClicked((Item) this.mBusinessObject);
                }
                if (businessObject instanceof Track) {
                    Track track = (Track) businessObject;
                    ArrayList arrayList = null;
                    int i = 0;
                    if (this.mAppState.getCurrentBusObjInListView() != null) {
                        arrayList = new ArrayList();
                        this.mAppState.setCurrentBusObjInListView(new ArrayList());
                        ArrayList currentBusObjInListView = this.mAppState.getCurrentBusObjInListView();
                        if (currentBusObjInListView != null && currentBusObjInListView.size() > 0) {
                            arrayList.addAll(currentBusObjInListView);
                        }
                        i = arrayList.indexOf(track);
                    }
                    if (track.isLocalMedia()) {
                        setPlayerQueueAndPlay(track, i, arrayList);
                        return;
                    } else {
                        checkOfflineAndplayTrack(track, i, arrayList);
                        return;
                    }
                }
                super.onClick(view);
            }
        }
    }

    public void checkOfflineAndplayTrack(Track track, int i, ArrayList<BusinessObject> arrayList) {
        if ("1".equalsIgnoreCase(track.getLocationAvailability()) && "0".equalsIgnoreCase(track.getDeviceAvailability())) {
            ap.a().a(this.mContext, this.mContext.getString(R.string.error_msg_content_unavailable_for_device));
        } else if ("0".equalsIgnoreCase(track.getLocationAvailability()) && "1".equalsIgnoreCase(track.getDeviceAvailability())) {
            ap.a().a(this.mContext, this.mContext.getString(R.string.error_msg_content_unavailable_for_location));
        } else if (this.mAppState.isAppInOfflineMode() && !DownloadManager.c().l(Integer.parseInt(track.getBusinessObjId())).booleanValue()) {
            ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog("This song");
        } else if (!Util.j(this.mContext) && !DownloadManager.c().l(Integer.parseInt(track.getBusinessObjId())).booleanValue()) {
            ap.a().f(this.mContext);
        } else if ((this.mAppState.isAppInOfflineMode() || !Util.j(this.mContext)) && !ap.a().j()) {
            aj.a().a(this.mContext, this.mContext.getResources().getString(R.string.toast_subscription_expired));
        } else {
            StringBuilder stringBuilder;
            if (!(DownloadManager.c().e(Integer.parseInt(track.getBusinessObjId())) != DownloadStatus.DOWNLOADED || !Util.v() || DownloadManager.c().f(Integer.parseInt(track.getBusinessObjId())) || ap.a().o() || DownloadManager.c().j(track.getBusinessObjId()).booleanValue())) {
                aj.a().a(this.mContext, this.mContext.getResources().getString(R.string.downloaded_songs_stream_online));
            }
            if (this.mAppState.getListingComponents() == null) {
                this.mAppState.setListingComponents(new ListingComponents());
            }
            if (this.mAppState.getListingComponents().f() == SearchType.Radio) {
                if (!(this.mFragment instanceof AlbumDetailsMaterialListing)) {
                    super.onClick(null);
                }
                if (!(this.mFragment instanceof GaanaSpecialDetailsMaterialListing)) {
                    super.onClick(null);
                }
                aj a = aj.a();
                Context context = this.mContext;
                stringBuilder = new StringBuilder();
                stringBuilder.append(this.mContext.getString(R.string.start_radio_for_songs));
                stringBuilder.append(": ");
                stringBuilder.append(track.getName());
                a.a(context, stringBuilder.toString());
                String replace = "https://api.gaana.com/radio.php?type=radio&subtype=songredios&track_id=<track_id>&page=1&limit=10".replace("<track_id>", track.getBusinessObjId());
                ad.a(this.mContext).a(true);
                ad.a(this.mContext).a(track);
                ad.a(this.mContext).a(replace, SOURCE_TYPE.RADIO_SEARCH_SONG.ordinal(), (BusinessObject) track);
            } else {
                setPlayerQueueAndPlay(track, i, arrayList);
            }
            if (TextUtils.isEmpty(((BaseActivity) this.mContext).currentFavpage)) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(((BaseActivity) this.mContext).currentScreen);
                stringBuilder.append(" - Play");
                ((BaseActivity) this.mContext).sendGAEvent(((BaseActivity) this.mContext).currentScreen, "Play", stringBuilder.toString());
            } else {
                stringBuilder = new StringBuilder();
                stringBuilder.append(((BaseActivity) this.mContext).currentScreen);
                stringBuilder.append(" - ");
                stringBuilder.append(((BaseActivity) this.mContext).currentFavpage);
                stringBuilder.append(" - Play");
                ((BaseActivity) this.mContext).sendGAEvent(((BaseActivity) this.mContext).currentScreen, "Play", stringBuilder.toString());
            }
        }
    }

    public void setPlayerQueueAndPlay(Track track, int i, ArrayList<BusinessObject> arrayList) {
        if (this.isPlayerQueue) {
            PlayerTrack playerTrack = null;
            int i2 = 0;
            while (i2 < PlayerManager.a(this.mContext).n().size()) {
                if (track.getBusinessObjId().equals(((PlayerTrack) PlayerManager.a(this.mContext).n().get(i2)).h())) {
                    playerTrack = (PlayerTrack) PlayerManager.a(this.mContext).n().get(i2);
                    break;
                }
                i2++;
            }
            i2 = 0;
            u.a().a("PlayerQueue", "Track Clicked", i2 > PlayerManager.a(this.mContext).s() ? "Up Next" : "Previous");
            playerTrack.c(false);
            if (PlayerManager.a(this.mContext).E() != null) {
                PlayerManager.a(this.mContext).j(false);
            }
            play(playerTrack);
            return;
        }
        PlayerTrack a = d.a().a(this.mFragment, (BusinessObject) track);
        ArrayList arrayList2 = new ArrayList();
        ArrayList currentBusObjInListView = this.mAppState.getCurrentBusObjInListView();
        if (arrayList != null && currentBusObjInListView != null && currentBusObjInListView.size() > 0 && (currentBusObjInListView.get(0) instanceof Item)) {
            arrayList2 = arrayList;
        } else if (currentBusObjInListView != null && currentBusObjInListView.size() > 0 && (currentBusObjInListView.get(0) instanceof Item)) {
            arrayList2.addAll(currentBusObjInListView);
        } else if (currentBusObjInListView != null) {
            arrayList2 = currentBusObjInListView;
        }
        if (!(arrayList2 == null || checkForContains(arrayList2, track))) {
            arrayList2.add(track);
        }
        ArrayList a2 = d.a().a(this.mFragment, arrayList2);
        if (a2 != null) {
            PlayerManager.a(this.mContext).b(a2, a, 0);
        }
        PlayerManager.a(this.mContext).g(true);
        play(a);
        PlayerManager.a(this.mContext).g(false);
    }

    public void play(PlayerTrack playerTrack) {
        if (!(!Constants.t() || Constants.U || playerTrack == null || playerTrack.b() == null || playerTrack.b().getBusinessObjId() == null || DownloadManager.c().e(Integer.parseInt(playerTrack.b().getBusinessObjId())) != DownloadStatus.DOWNLOADED)) {
            Constants.U = true;
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                public void run() {
                    Constants.U = false;
                    new DownloadSyncPopupItemView(MyActivityInfoItemView.this.mContext).showDownloadSyncWelcomeScreenDialog();
                }
            }, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
        }
        if (!(playerTrack == null || playerTrack.b() == null || playerTrack.b().getEnglishName() == null || TextUtils.isEmpty(playerTrack.b().getEnglishName()))) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("play:");
            stringBuilder.append(playerTrack.b().getEnglishName());
            q.a().a("ua", stringBuilder.toString());
        }
        playerTrack.d(true);
        PlayerManager.a(this.mContext).c();
        PlayerManager.a(this.mContext).a(null, playerTrack, 999999);
        PlayerManager.a(this.mContext).a(PlayerType.GAANA, this.mContext);
        ((GaanaActivity) this.mContext).setUpdatePlayerFragment();
    }

    private boolean checkForContains(ArrayList<BusinessObject> arrayList, Track track) {
        if (!(arrayList == null || track == null)) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                if (((BusinessObject) it.next()).getBusinessObjId().equals(track.getBusinessObjId())) {
                    return true;
                }
            }
        }
        return false;
    }
}
