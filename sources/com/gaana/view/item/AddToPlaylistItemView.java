package com.gaana.view.item;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.fragments.AddToPlaylistFragment;
import com.fragments.BaseGaanaFragment;
import com.fragments.ItemListingFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.fragments.BaseFragment;
import com.gaana.localmedia.PlaylistSyncManager;
import com.gaana.localmedia.PlaylistSyncManager.PLAYLIST_STATUS;
import com.gaana.models.BusinessObject;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Tracks.Track;
import com.library.controls.CrossFadeImageView;
import com.library.managers.TaskManager.TaskListner;
import com.managers.DownloadManager;
import com.managers.aj;
import com.managers.ap;
import com.managers.o;
import com.managers.u;
import com.services.h;
import com.utilities.Util;
import java.util.ArrayList;

public class AddToPlaylistItemView extends BaseItemView {
    private String fragmentTagToPop;
    private String gaAction;
    private String gaCategory;
    private String gaLabel;
    private PLAYLIST_STATUS playlistStatus;

    public static class AddToPlaylistItemViewHolder extends ViewHolder {
        public CrossFadeImageView mCrossFadeImageView;
        public TextView mTxtTitle;

        public AddToPlaylistItemViewHolder(View view) {
            super(view);
            this.mCrossFadeImageView = (CrossFadeImageView) view.findViewById(R.id.img_artwork);
            this.mTxtTitle = (TextView) view.findViewById(R.id.txt_name);
        }
    }

    public AddToPlaylistItemView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        this.fragmentTagToPop = "";
        this.gaLabel = "";
        this.gaCategory = "";
        this.gaAction = "";
        this.mLayoutId = R.layout.item_add_playlist;
    }

    public AddToPlaylistItemView(Context context, BaseFragment baseFragment, int i) {
        super(context, baseFragment, i);
        this.fragmentTagToPop = "";
        this.gaLabel = "";
        this.gaCategory = "";
        this.gaAction = "";
        this.mLayoutId = R.layout.item_add_playlist;
    }

    public void setFragmentTagToPop(String str) {
        this.fragmentTagToPop = str;
    }

    public void setGaEvent(String str, String str2, String str3) {
        this.gaAction = str;
        this.gaCategory = str2;
        this.gaLabel = str3;
    }

    public View getPoplatedView(ViewHolder viewHolder, final BusinessObject businessObject, ViewGroup viewGroup) {
        View poplatedView = super.getPoplatedView(viewHolder, businessObject, viewGroup);
        AddToPlaylistItemViewHolder addToPlaylistItemViewHolder = (AddToPlaylistItemViewHolder) viewHolder;
        addToPlaylistItemViewHolder.mTxtTitle.setText(businessObject.getName());
        Playlist playlist = (Playlist) businessObject;
        if (!TextUtils.isEmpty(playlist.getArtwork())) {
            addToPlaylistItemViewHolder.mCrossFadeImageView.bindImage(Util.d(this.mContext, playlist.getArtwork()));
        }
        poplatedView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                String str = (String) view.getTag(R.id.ga_category);
                String str2 = (String) view.getTag(R.id.ga_action);
                if (!(TextUtils.isEmpty(str) || TextUtils.isEmpty(str2))) {
                    u.a().b(str, str2);
                }
                AddToPlaylistItemView.this.savePlayList((Playlist) businessObject);
            }
        });
        return poplatedView;
    }

    private void savePlayList(final Playlist playlist) {
        final ArrayList arrListTracksForPlaylist = this.mAppState.getArrListTracksForPlaylist();
        if (playlist == null) {
            aj.a().a(this.mContext, this.mContext.getString(R.string.select_or_create_new_playlist));
            return;
        }
        ((BaseActivity) this.mContext).showProgressDialog(Boolean.valueOf(false), this.mContext.getString(R.string.updating_text));
        h.a().a(new TaskListner() {
            public void onBackGroundTaskCompleted() {
                if (AddToPlaylistItemView.this.mContext != null) {
                    ((BaseActivity) AddToPlaylistItemView.this.mContext).hideProgressDialog();
                }
                AddToPlaylistItemView.this.mAppState.setArrListPlaylist(null);
                AddToPlaylistItemView.this.mAppState.setArrListTracksForPlaylist(null);
                AddToPlaylistItemView.this.showSuccessMsg();
                o a = o.a();
                String[] strArr = new String[2];
                strArr[0] = "type=playlist&subtype=playlist_detail";
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("playlist_id=");
                stringBuilder.append(playlist.getBusinessObjId());
                strArr[1] = stringBuilder.toString();
                a.a(strArr);
                if (ap.a().o()) {
                    int a2 = Util.a(playlist.getBusinessObjId());
                    if (!(a2 == 0 || !DownloadManager.c().b(playlist).booleanValue() || arrListTracksForPlaylist == null)) {
                        DownloadManager.c().b(arrListTracksForPlaylist, a2, true);
                    }
                }
                if (AddToPlaylistItemView.this.playlistStatus == PLAYLIST_STATUS.SUCCESS) {
                    PlaylistSyncManager.getInstance().updatePlaylistMemCache(Util.a(playlist.getBusinessObjId()));
                }
            }

            public void doBackGroundTask() {
                if (arrListTracksForPlaylist != null) {
                    String[] strArr = new String[arrListTracksForPlaylist.size()];
                    for (int i = 0; i < arrListTracksForPlaylist.size(); i++) {
                        strArr[i] = ((Track) arrListTracksForPlaylist.get(i)).getBusinessObjId();
                    }
                    AddToPlaylistItemView.this.playlistStatus = PlaylistSyncManager.getInstance().addToPlaylist((Activity) AddToPlaylistItemView.this.mContext, playlist, arrListTracksForPlaylist);
                    return;
                }
                AddToPlaylistItemView.this.playlistStatus = PLAYLIST_STATUS.FAILED;
            }
        }, -1);
    }

    private void showSuccessMsg() {
        String str = "";
        switch (this.playlistStatus) {
            case SUCCESS:
                str = this.mContext.getString(R.string.songs_added_to_playlist);
                break;
            case FAILED:
                str = this.mContext.getString(R.string.songs_add_failed);
                break;
            case ALREADY_ADDED:
                str = this.mContext.getString(R.string.songs_already_in_playlist);
                break;
            case PARTIALY_ADDED:
                str = this.mContext.getString(R.string.songs_added_once);
                break;
        }
        aj.a().a(this.mContext, str);
        try {
            if (((GaanaActivity) this.mContext).getCurrentFragment() == null) {
                return;
            }
            if ((!(((GaanaActivity) this.mContext).getCurrentFragment() instanceof AddToPlaylistFragment) && !(((GaanaActivity) this.mContext).getCurrentFragment() instanceof ItemListingFragment)) || !((GaanaActivity) this.mContext).getCurrentFragment().isVisible()) {
                return;
            }
            if (TextUtils.isEmpty(this.fragmentTagToPop)) {
                ((GaanaActivity) this.mContext).popBackStackImmediate();
            } else {
                ((GaanaActivity) this.mContext).popBackStackImmediate(this.fragmentTagToPop, 1);
            }
        } catch (Exception unused) {
        }
    }
}
