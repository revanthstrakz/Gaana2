package com.gaana.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.gaana.R;
import com.gaana.models.EntityInfo;
import com.gaana.models.PreferedArtists.PreferedArtist;
import java.util.ArrayList;
import java.util.Iterator;

public class ArtistSelectionAdapter extends Adapter<ViewHolder> {
    private Context mContext;
    private ArrayList<PreferedArtist> preferedArtistList = new ArrayList();
    private PreferedArtistSelectedListener preferedArtistSelectedListener;
    ArrayList<PreferedArtist> preferedArtists;

    public interface PreferedArtistSelectedListener {
        void ArtistSeclected(boolean z);
    }

    public void setPreferedArtistSelectedListener(PreferedArtistSelectedListener preferedArtistSelectedListener) {
        this.preferedArtistSelectedListener = preferedArtistSelectedListener;
    }

    public void setAdapterParameters(ArrayList<PreferedArtist> arrayList, Context context) {
        this.preferedArtists = arrayList;
        this.mContext = context;
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            PreferedArtist preferedArtist = (PreferedArtist) it.next();
            for (EntityInfo entityInfo : preferedArtist.getEntityInfo()) {
                if (entityInfo.getKey().equalsIgnoreCase("device_mapped_artist") && entityInfo.getValue().equals("1")) {
                    preferedArtist.setIsPrefered(true);
                    this.preferedArtistList.add(preferedArtist);
                }
            }
        }
    }

    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ArtistSelectionViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.moreinfo_item_view, viewGroup, false));
    }

    public ArrayList<PreferedArtist> getSelectedArtists() {
        return this.preferedArtistList;
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final ArtistSelectionViewHolder artistSelectionViewHolder = (ArtistSelectionViewHolder) viewHolder;
        final PreferedArtist preferedArtist = (PreferedArtist) this.preferedArtists.get(i);
        artistSelectionViewHolder.itemImg.bindImage(preferedArtist.getAtw());
        artistSelectionViewHolder.artistName.setText(preferedArtist.getName());
        artistSelectionViewHolder.itemImg.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (ArtistSelectionAdapter.this.preferedArtistList.contains(preferedArtist)) {
                    preferedArtist.setIsPrefered(false);
                    ArtistSelectionAdapter.this.preferedArtistList.remove(preferedArtist);
                    artistSelectionViewHolder.favourite_item.setVisibility(8);
                    if (ArtistSelectionAdapter.this.preferedArtistSelectedListener != null) {
                        ArtistSelectionAdapter.this.preferedArtistSelectedListener.ArtistSeclected(false);
                        return;
                    }
                    return;
                }
                preferedArtist.setIsPrefered(true);
                ArtistSelectionAdapter.this.preferedArtistList.add(preferedArtist);
                artistSelectionViewHolder.favourite_item.setVisibility(0);
                if (ArtistSelectionAdapter.this.preferedArtistSelectedListener != null) {
                    ArtistSelectionAdapter.this.preferedArtistSelectedListener.ArtistSeclected(true);
                }
            }
        });
        artistSelectionViewHolder.favourite_item.setVisibility(8);
        for (EntityInfo entityInfo : preferedArtist.getEntityInfo()) {
            if (entityInfo.getKey().equalsIgnoreCase("device_mapped_artist") && entityInfo.getValue().equals("1")) {
                artistSelectionViewHolder.favourite_item.setVisibility(0);
            }
        }
    }

    public int getItemCount() {
        return this.preferedArtists.size();
    }
}
