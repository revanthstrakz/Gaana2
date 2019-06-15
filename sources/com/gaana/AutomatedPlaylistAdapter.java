package com.gaana;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import com.fragments.BaseGaanaFragment;
import com.gaana.models.BusinessObject;
import com.gaana.models.Tracks.Track;
import com.library.controls.CrossFadeImageView;
import com.managers.af;
import java.util.ArrayList;

public class AutomatedPlaylistAdapter extends Adapter<AutomatedPlaylistViewHolder> {
    private ArrayList<Track> mBusinessobjList;
    private Context mContext;
    private BaseGaanaFragment mFragment;

    public class AutomatedPlaylistViewHolder extends ViewHolder {
        protected CrossFadeImageView crossFadeImageView;
        protected TextView songNameText;

        public AutomatedPlaylistViewHolder(View view) {
            super(view);
            this.crossFadeImageView = (CrossFadeImageView) view.findViewById(R.id.songsartwork);
            this.songNameText = (TextView) view.findViewById(R.id.songNameText);
        }
    }

    public AutomatedPlaylistAdapter(Context context, BaseGaanaFragment baseGaanaFragment, ArrayList<Track> arrayList) {
        this.mContext = context;
        this.mBusinessobjList = arrayList;
        this.mFragment = baseGaanaFragment;
    }

    public AutomatedPlaylistViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new AutomatedPlaylistViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.horizontal_automated_item_view, null));
    }

    public void onBindViewHolder(AutomatedPlaylistViewHolder automatedPlaylistViewHolder, final int i) {
        automatedPlaylistViewHolder.crossFadeImageView.bindImage(((Track) this.mBusinessobjList.get(i)).getArtwork());
        automatedPlaylistViewHolder.songNameText.setText(((Track) this.mBusinessobjList.get(i)).getTrackTitle());
        automatedPlaylistViewHolder.itemView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                af.a(AutomatedPlaylistAdapter.this.mContext, AutomatedPlaylistAdapter.this.mFragment).a((int) R.id.playMenu, (BusinessObject) AutomatedPlaylistAdapter.this.mBusinessobjList.get(i));
            }
        });
    }

    public int getItemCount() {
        return this.mBusinessobjList != null ? this.mBusinessobjList.size() : 0;
    }
}
