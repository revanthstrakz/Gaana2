package com.gaana.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.fragments.AlbumDetailsMaterialListing;
import com.fragments.BaseGaanaFragment;
import com.gaana.R;
import com.gaana.adapter.CustomListAdapter;
import com.gaana.models.BusinessObject;
import com.gaana.models.Tracks.Track;
import com.gaana.view.item.DownloadSongsItemView.AddMoreSongsItemHolder;
import com.managers.aj;
import com.managers.aj.b;
import com.managers.u;
import com.services.l.j;
import java.util.ArrayList;
import java.util.Set;

public class ExpandableListAdapter extends Adapter<ViewHolder> implements j {
    public static final int CHILD = 1;
    public static final int HEADER = 0;
    private static final int LEFT = 4;
    private ArrayList<Track> dataList;
    private Context mContext;
    private int mDeletedPosition;
    private Set<Object> mDeletedSet;
    private Track mDeletedTrack;
    private BaseGaanaFragment mFragment;

    public int getItemViewType(int i) {
        return 0;
    }

    public void onComplete(int i) {
    }

    public ExpandableListAdapter(ArrayList<Track> arrayList, BaseGaanaFragment baseGaanaFragment) {
        this.dataList = arrayList;
        this.mFragment = baseGaanaFragment;
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        this.mContext = viewGroup.getContext();
        return new AddMoreSongsItemHolder(((LayoutInflater) viewGroup.getContext().getSystemService("layout_inflater")).inflate(R.layout.view_item_add_more_songs, viewGroup, false));
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        AddMoreSongsItemHolder addMoreSongsItemHolder = (AddMoreSongsItemHolder) viewHolder;
        addMoreSongsItemHolder.tvTitle.setText(((Track) this.dataList.get(i)).getTrackTitle());
        addMoreSongsItemHolder.tvSubtitle.setText(((Track) this.dataList.get(i)).getArtistNames());
        addMoreSongsItemHolder.mCrossFadeImageIcon.bindImage(((Track) this.dataList.get(i)).getArtwork());
        addMoreSongsItemHolder.addText.setTag(this.dataList.get(i));
        addMoreSongsItemHolder.addText.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (ExpandableListAdapter.this.mFragment != null && ExpandableListAdapter.this.mFragment.isAdded() && (ExpandableListAdapter.this.mFragment instanceof AlbumDetailsMaterialListing)) {
                    Track track = (Track) view.getTag();
                    ArrayList f = ((AlbumDetailsMaterialListing) ExpandableListAdapter.this.mFragment).f();
                    CustomListAdapter g = ((AlbumDetailsMaterialListing) ExpandableListAdapter.this.mFragment).g();
                    if (f != null && g != null) {
                        int indexOf = ExpandableListAdapter.this.dataList.indexOf(track);
                        ExpandableListAdapter.this.dataList.remove(indexOf);
                        ExpandableListAdapter.this.notifyItemRemoved(indexOf);
                        track.setAddedToPlaylist(true);
                        ((AlbumDetailsMaterialListing) ExpandableListAdapter.this.mFragment).a(track, true);
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Song ");
                        stringBuilder.append(track.getBusinessObjId());
                        u.a().a("Add to Playlist", "Recommendation User Playlist", stringBuilder.toString());
                        ExpandableListAdapter.this.handleUndoAddAction(g, f, indexOf, track);
                    }
                }
            }
        });
    }

    public int getItemCount() {
        return this.dataList.size();
    }

    public boolean onItemMove(int i, int i2) {
        notifyItemMoved(i, i2);
        return true;
    }

    public void onItemDelete(int i, int i2) {
        if (!(i2 == 4 ? "Left" : "Right").equals("Right") && this.dataList.size() > 0) {
            this.mDeletedPosition = i;
            this.mDeletedTrack = (Track) this.dataList.get(this.mDeletedPosition);
            this.dataList.remove(i);
            notifyItemRemoved(i);
            aj.a().a(this.mContext, this.mContext.getResources().getString(R.string.single_song_delete_message), new b() {
                public void undoSnackBar() {
                    if (ExpandableListAdapter.this.mDeletedPosition > -1 && ExpandableListAdapter.this.mDeletedTrack != null) {
                        ExpandableListAdapter.this.dataList.add(ExpandableListAdapter.this.mDeletedPosition, ExpandableListAdapter.this.mDeletedTrack);
                        ExpandableListAdapter.this.notifyItemInserted(ExpandableListAdapter.this.mDeletedPosition);
                        ExpandableListAdapter.this.mDeletedPosition = -1;
                        ExpandableListAdapter.this.mDeletedTrack = null;
                    }
                }
            });
        }
    }

    private void handleUndoAddAction(CustomListAdapter customListAdapter, ArrayList<BusinessObject> arrayList, final int i, final Track track) {
        if (this.dataList != null) {
            aj.a().a(this.mContext, this.mContext.getResources().getString(R.string.string_song_added_from_cf), new b() {
                public void undoSnackBar() {
                    if (i > -1 && track != null) {
                        ExpandableListAdapter.this.dataList.add(i, track);
                        ExpandableListAdapter.this.notifyItemInserted(i);
                        track.setAddedToPlaylist(false);
                        ((AlbumDetailsMaterialListing) ExpandableListAdapter.this.mFragment).a(track, false);
                        u.a().a("Add to Playlist", "CF Song Add Undo", track.getBusinessObjId());
                    }
                }
            });
        }
    }
}
