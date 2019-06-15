package com.gaana;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.volley.Request.Priority;
import com.fragments.BaseGaanaFragment;
import com.fragments.ListingFragment;
import com.gaana.localmedia.PlaylistSyncManager;
import com.gaana.localmedia.PlaylistSyncManager.PLAYLIST_STATUS;
import com.gaana.models.BusinessObject;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Tracks;
import com.gaana.view.BaseItemView;
import com.i.i;
import com.library.managers.TaskManager.TaskListner;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.aj;
import com.services.h;
import com.services.l.af;

public class AutomatedPlaylistItemView extends BaseItemView {
    private PLAYLIST_STATUS asyncResult;
    private AutomatedPlaylistItemRowHolder mHolder;
    View mView;

    public static class AutomatedPlaylistItemRowHolder extends ViewHolder {
        protected TextView desc_header_text;
        protected RelativeLayout mainLayout;
        protected TextView playlistName;
        protected RecyclerView recycler_view_list;
        protected TextView save_button;
        protected TextView songs_count;

        public AutomatedPlaylistItemRowHolder(View view) {
            super(view);
            this.recycler_view_list = (RecyclerView) view.findViewById(R.id.horizontal_recyclerView);
            this.save_button = (TextView) view.findViewById(R.id.save_button);
            this.songs_count = (TextView) view.findViewById(R.id.songs_count);
            this.playlistName = (TextView) view.findViewById(R.id.playlistName);
            this.desc_header_text = (TextView) view.findViewById(R.id.desc_header_text);
            this.mainLayout = (RelativeLayout) view.findViewById(R.id.mainLayout);
        }
    }

    public AutomatedPlaylistItemView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
    }

    public View getPoplatedView(ViewHolder viewHolder, boolean z) {
        this.mHolder = null;
        if (z) {
            AutomatedPlaylistItemRowHolder automatedPlaylistItemRowHolder = (AutomatedPlaylistItemRowHolder) viewHolder;
            this.mHolder = automatedPlaylistItemRowHolder;
            initView(automatedPlaylistItemRowHolder);
        }
        return viewHolder.itemView;
    }

    private void initView(final AutomatedPlaylistItemRowHolder automatedPlaylistItemRowHolder) {
        hideLayout();
        URLManager uRLManager = new URLManager();
        uRLManager.a(BusinessObjectType.Tracks);
        uRLManager.a("https://api.gaana.com/playlist/automatic");
        uRLManager.b(Boolean.valueOf(false));
        uRLManager.a(Tracks.class);
        uRLManager.a(Priority.IMMEDIATE);
        i.a().a(new af() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(Object obj) {
                if (obj == null || !(obj instanceof Tracks)) {
                    AutomatedPlaylistItemView.this.hideLayout();
                    return;
                }
                AutomatedPlaylistItemView.this.loadView(automatedPlaylistItemRowHolder, (Tracks) obj);
            }
        }, uRLManager);
    }

    public void hideLayout() {
        if (this.mHolder != null && this.mHolder.itemView.getLayoutParams() != null) {
            this.mHolder.itemView.getLayoutParams().height = 1;
        }
    }

    private void loadView(final AutomatedPlaylistItemRowHolder automatedPlaylistItemRowHolder, final Tracks tracks) {
        if (tracks == null || tracks.getArrListBusinessObj() == null || tracks.getArrListBusinessObj().size() < 1) {
            hideLayout();
            return;
        }
        automatedPlaylistItemRowHolder.mainLayout.setVisibility(0);
        TextView textView = automatedPlaylistItemRowHolder.songs_count;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("");
        stringBuilder.append(tracks.getSongsCount());
        stringBuilder.append(" ");
        stringBuilder.append(this.mContext.getResources().getString(R.string.songs_text));
        textView.setText(stringBuilder.toString());
        automatedPlaylistItemRowHolder.playlistName.setText(tracks.getHeaderMessage());
        AutomatedPlaylistAdapter automatedPlaylistAdapter = new AutomatedPlaylistAdapter(this.mContext, this.mFragment, tracks.getArrListBusinessObj());
        automatedPlaylistItemRowHolder.recycler_view_list.setHasFixedSize(true);
        automatedPlaylistItemRowHolder.recycler_view_list.setLayoutManager(new LinearLayoutManager(this.mContext, 0, false));
        automatedPlaylistItemRowHolder.recycler_view_list.setAdapter(automatedPlaylistAdapter);
        automatedPlaylistItemRowHolder.itemView.getLayoutParams().width = -1;
        automatedPlaylistItemRowHolder.itemView.getLayoutParams().height = -2;
        automatedPlaylistItemRowHolder.save_button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                AutomatedPlaylistItemView.this.createPlayList(tracks, automatedPlaylistItemRowHolder);
            }
        });
    }

    private void createPlayList(final Tracks tracks, AutomatedPlaylistItemRowHolder automatedPlaylistItemRowHolder) {
        ((BaseActivity) this.mContext).showProgressDialog(Boolean.valueOf(true), this.mContext.getString(R.string.loading));
        h.a().a(new TaskListner() {
            public void onBackGroundTaskCompleted() {
                ((BaseActivity) AutomatedPlaylistItemView.this.mContext).hideProgressDialog();
                switch (AutomatedPlaylistItemView.this.asyncResult) {
                    case SUCCESS:
                        aj.a().a(AutomatedPlaylistItemView.this.mContext, AutomatedPlaylistItemView.this.getResources().getString(R.string.Add_TO_PLAYLIST_SUCCESS_MSG));
                        if (AutomatedPlaylistItemView.this.mFragment instanceof ListingFragment) {
                            ((ListingFragment) AutomatedPlaylistItemView.this.mFragment).a(true);
                            ((ListingFragment) AutomatedPlaylistItemView.this.mFragment).i();
                        }
                        AutomatedPlaylistItemView.this.hideLayout();
                        return;
                    case FAILED:
                        aj.a().a(AutomatedPlaylistItemView.this.mContext, AutomatedPlaylistItemView.this.getResources().getString(R.string.Add_TO_PLAYLIST_FAILURE_MSG));
                        ((ListingFragment) AutomatedPlaylistItemView.this.mFragment).a(false);
                        return;
                    default:
                        return;
                }
            }

            public void doBackGroundTask() {
                if (tracks.getArrListBusinessObj() != null) {
                    AutomatedPlaylistItemView.this.asyncResult = PlaylistSyncManager.getInstance().createPlaylist(new Playlist(), (Activity) AutomatedPlaylistItemView.this.mContext, tracks.getHeaderMessage(), tracks.getArrListBusinessObj(), false);
                } else {
                    AutomatedPlaylistItemView.this.asyncResult = PLAYLIST_STATUS.FAILED;
                }
            }
        }, -1);
    }

    public View createViewHolder(ViewGroup viewGroup, int i) {
        if (this.mView == null) {
            this.mView = LayoutInflater.from(this.mContext).inflate(R.layout.automated_playlist_item_view, null);
        }
        return this.mView;
    }
}
