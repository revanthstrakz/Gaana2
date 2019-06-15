package com.gaana.view.item;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import com.constants.c;
import com.fragments.BaseGaanaFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.localmedia.LocalMediaManager;
import com.gaana.localmedia.PlaylistSyncManager;
import com.gaana.models.Albums.Album;
import com.gaana.models.BusinessObject;
import com.gaana.models.OfflineTrack;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Tracks.Track;
import com.i.i;
import com.library.controls.CrossFadeImageView;
import com.managers.DownloadManager;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.af;
import com.managers.aj;
import com.managers.ap;
import com.managers.u;
import com.services.l.s;
import com.utilities.Util;
import java.util.ArrayList;
import java.util.HashMap;

public class FriendsPlaylistButtonView extends BaseItemView implements OnClickListener {
    private TextView firstTitleText;
    private boolean lastItem;
    private BusinessObject mBusinessObject;
    private Button mButton;
    private CrossFadeImageView mImageIcon;
    private int mLayoutId;
    private CheckBox mRadioBtn;
    private View mView;
    s onBusinessObjectRetrieved;
    private TextView secondTitleText;
    private HashMap<String, Integer> selectedMap;
    private TextView songsCount;
    String updatedText;
    private View view_playlist_feed_divider;

    public FriendsPlaylistButtonView(Context context, BaseGaanaFragment baseGaanaFragment, boolean z) {
        super(context, baseGaanaFragment);
        this.mView = null;
        this.mLayoutId = -1;
        this.selectedMap = new HashMap();
        this.updatedText = "";
        this.lastItem = false;
        this.onBusinessObjectRetrieved = new s() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(BusinessObject businessObject) {
                ((BaseActivity) FriendsPlaylistButtonView.this.mContext).hideProgressDialog();
                if (businessObject == null || businessObject.getArrListBusinessObj() == null || businessObject.getArrListBusinessObj().size() == 0) {
                    aj.a().a(FriendsPlaylistButtonView.this.mContext, FriendsPlaylistButtonView.this.mContext.getString(R.string.no_songs_to_add));
                    return;
                }
                u.a().a("personalisation", "created", "playlist");
                ArrayList arrListBusinessObj = businessObject.getArrListBusinessObj();
                for (int size = arrListBusinessObj.size() - 1; size >= 0; size--) {
                    Track track = (Track) arrListBusinessObj.get(size);
                    if (track.getIslocal() != null && track.getIslocal().equals("1")) {
                        track = LocalMediaManager.getInstance(FriendsPlaylistButtonView.this.mContext).getLocalTrackFromHash(track.getBusinessObjId());
                        arrListBusinessObj.remove(size);
                        if (track != null) {
                            arrListBusinessObj.add(size, track);
                        }
                    }
                }
                ap.a().a(FriendsPlaylistButtonView.this.mContext, arrListBusinessObj, false);
            }
        };
        this.mLayoutId = R.layout.view_friends_playlist_button;
        this.lastItem = z;
    }

    public View getPoplatedView(View view, BusinessObject businessObject, ViewGroup viewGroup) {
        if (view == null) {
            view = this.mInflater.inflate(this.mLayoutId, null);
        }
        view.setTag(businessObject);
        return getDataFilledView(view, businessObject);
    }

    private View getDataFilledView(View view, BusinessObject businessObject) {
        this.mImageIcon = (CrossFadeImageView) view.findViewById(R.id.profileImage);
        this.firstTitleText = (TextView) view.findViewById(R.id.firstTitleText);
        this.secondTitleText = (TextView) view.findViewById(R.id.secondTitleText);
        this.view_playlist_feed_divider = view.findViewById(R.id.view_playlist_feed_divider);
        int i = 0;
        this.secondTitleText.setVisibility(0);
        this.mButton = (Button) view.findViewById(R.id.clickButton);
        this.mButton.setTag(businessObject);
        this.mButton.setOnClickListener(this);
        if (this.lastItem) {
            this.view_playlist_feed_divider.setVisibility(4);
        }
        if (businessObject.getBusinessObjType() == BusinessObjectType.Playlists) {
            Playlist playlist = (Playlist) businessObject;
            this.mImageIcon.bindImage(playlist.getArtwork(), this.mAppState.isAppInOfflineMode());
            this.firstTitleText.setText(playlist.getName());
            String[] split = playlist.getTrackids().split(",");
            TextView textView = this.secondTitleText;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(split.length);
            stringBuilder.append(" Songs | ");
            stringBuilder.append(playlist.getCreatedby());
            textView.setText(stringBuilder.toString());
            if (this.selectedMap.containsKey(playlist.getBusinessObjId())) {
                i = ((Integer) this.selectedMap.get(playlist.getBusinessObjId())).intValue();
            }
            if (i == 1) {
                this.mButton.setText(getContext().getString(R.string.saved_text));
                this.mButton.setBackgroundResource(R.drawable.social_requested);
                this.mButton.setTextColor(getContext().getResources().getColor(R.color.on_board_language_save_button_orange_color));
            } else {
                this.mButton.setText(getContext().getString(R.string.save_text));
                this.mButton.setBackgroundResource(R.drawable.rounded_button_follow);
                this.mButton.setTextColor(getContext().getResources().getColor(R.color.white));
            }
        }
        view.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                BusinessObject businessObject = (BusinessObject) view.getTag();
                af.a(FriendsPlaylistButtonView.this.mContext, ((GaanaActivity) FriendsPlaylistButtonView.this.mContext).getCurrentFragment()).a((int) R.id.playlistMenu, businessObject);
            }
        });
        return view;
    }

    public void onClick(View view) {
        this.mBusinessObject = (BusinessObject) ((Button) view).getTag();
        BusinessObject detailItems = getDetailItems();
        if (detailItems != null && (detailItems.isLocalMedia() || this.mAppState.getCurrentUser().getLoginStatus())) {
            if (detailItems.getArrListBusinessObj() != null && detailItems.getArrListBusinessObj().size() > 0) {
                ap.a().a(this.mContext, detailItems.getArrListBusinessObj(), detailItems.isLocalMedia());
            } else if (detailItems instanceof Track) {
                ap.a().a(this.mContext, (Track) detailItems, detailItems.isLocalMedia());
            }
        }
        retrieveFeed(this.mBusinessObject, this.onBusinessObjectRetrieved);
    }

    /* Access modifiers changed, original: protected */
    public void retrieveFeed(BusinessObject businessObject, s sVar) {
        this.mBusinessObject = businessObject;
        URLManager uRLManager = new URLManager();
        uRLManager.a(BusinessObjectType.Tracks);
        String str = "https://api.gaana.com/index.php?";
        boolean z = businessObject instanceof Playlist;
        StringBuilder stringBuilder;
        if (z) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(c.w);
            stringBuilder2.append("playlist_id=");
            stringBuilder2.append(businessObject.getBusinessObjId());
            stringBuilder2.append("&playlist_type=");
            Playlist playlist = (Playlist) businessObject;
            stringBuilder2.append(playlist.getPlaylistType());
            str = stringBuilder2.toString();
            if (playlist.getAutomated() != null && playlist.getAutomated().equalsIgnoreCase("1")) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append("&automated=1");
                str = stringBuilder.toString();
            }
        } else if (businessObject instanceof Album) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("type=album&subtype=album_detail&album_id=");
            stringBuilder.append(businessObject.getBusinessObjId());
            str = stringBuilder.toString();
        }
        uRLManager.a(str);
        if (z) {
            Playlist playlist2 = (Playlist) businessObject;
            if (PlaylistSyncManager.getInstance().isMyPlaylist(playlist2)) {
                ((BaseActivity) this.mContext).showProgressDialog(Boolean.valueOf(true), this.mContext.getString(R.string.getting_playlist_details));
                ((BaseActivity) this.mContext).getMyPlaylistDetails(sVar, playlist2, uRLManager);
            }
        }
        if (this.mAppState.isAppInOfflineMode()) {
            ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(this.mContext.getResources().getString(R.string.error_msg_feature_not_available_offline_prefix));
        } else if (Util.j(this.mContext)) {
            ((BaseActivity) this.mContext).showProgressDialog(Boolean.valueOf(true), this.mContext.getString(R.string.getting_details));
            i.a().a(sVar, uRLManager);
        } else {
            ap.a().f(this.mContext);
        }
    }

    private BusinessObject getDetailItems() {
        if (this.mBusinessObject.getArrListBusinessObj() != null && this.mBusinessObject.getArrListBusinessObj().size() > 0) {
            return this.mBusinessObject;
        }
        if (this.mBusinessObject instanceof Track) {
            return this.mBusinessObject;
        }
        BusinessObject trackFromLocalMedia;
        if (this.mBusinessObject instanceof OfflineTrack) {
            if (this.mBusinessObject.isLocalMedia()) {
                trackFromLocalMedia = LocalMediaManager.getInstance(this.mContext).getTrackFromLocalMedia((OfflineTrack) this.mBusinessObject);
            } else {
                trackFromLocalMedia = (Track) DownloadManager.c().a(this.mBusinessObject.getBusinessObjId(), true);
            }
            return trackFromLocalMedia;
        }
        if (!this.mBusinessObject.isLocalMedia() && DownloadManager.c().b(this.mBusinessObject).booleanValue()) {
            trackFromLocalMedia = DownloadManager.c().a(this.mBusinessObject.getBusinessObjId(), false);
            if (!(trackFromLocalMedia == null || trackFromLocalMedia.getArrListBusinessObj() == null)) {
                this.mBusinessObject.setArrListBusinessObj(trackFromLocalMedia.getArrListBusinessObj());
                return this.mBusinessObject;
            }
        } else if (this.mBusinessObject.isLocalMedia()) {
            if (this.mBusinessObject instanceof Album) {
                this.mBusinessObject.setArrListBusinessObj(LocalMediaManager.getInstance(this.mContext).getSongsByAlbum(this.mBusinessObject.getBusinessObjId()));
                return this.mBusinessObject;
            } else if (this.mBusinessObject instanceof Playlist) {
                this.mBusinessObject.setArrListBusinessObj(LocalMediaManager.getInstance(this.mContext).getSongsByPlaylist(this.mBusinessObject.getBusinessObjId()));
                return this.mBusinessObject;
            }
        } else if ((this.mBusinessObject instanceof Playlist) && PlaylistSyncManager.getInstance().isMyPlaylist((Playlist) this.mBusinessObject)) {
            this.mBusinessObject.setArrListBusinessObj(PlaylistSyncManager.getInstance().getPlaylistDetails((Playlist) this.mBusinessObject).getArrListBusinessObj());
            return this.mBusinessObject;
        }
        return null;
    }

    private String checkScreenType() {
        Bundle arguments = ((GaanaActivity) this.mContext).getCurrentFragment().getArguments();
        return arguments != null ? arguments.getString("TYPE_OF_SCREEN") : "";
    }

    private void callFollowApi(String str, final String str2, final Button button) {
        Util.a(this.mContext, getContext().getString(R.string.loading));
        URLManager uRLManager = new URLManager();
        uRLManager.a(str);
        uRLManager.b(Boolean.valueOf(false));
        uRLManager.a(BusinessObjectType.BasicResponse);
        i.a().a(new s() {
            public void onRetreivalComplete(BusinessObject businessObject) {
                button.setText(str2);
                button.setBackgroundResource(R.drawable.social_requested);
                button.setTextColor(FriendsPlaylistButtonView.this.getContext().getResources().getColor(R.color.on_board_language_save_button_orange_color));
                button.setOnClickListener(null);
                Util.b();
            }

            public void onErrorResponse(BusinessObject businessObject) {
                Util.b();
                aj.a().a(FriendsPlaylistButtonView.this.mContext, FriendsPlaylistButtonView.this.getContext().getString(R.string.request_not_completed));
            }
        }, uRLManager);
    }
}
