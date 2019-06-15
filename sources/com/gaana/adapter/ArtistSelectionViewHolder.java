package com.gaana.adapter;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.gaana.R;
import com.library.controls.CircularImageView;

class ArtistSelectionViewHolder extends ViewHolder {
    public TextView artistName;
    public ImageView favourite_item;
    public CircularImageView itemImg;

    ArtistSelectionViewHolder(View view) {
        super(view);
        this.itemImg = (CircularImageView) view.findViewById(R.id.itemImg);
        this.artistName = (TextView) view.findViewById(R.id.title);
        this.favourite_item = (ImageView) view.findViewById(R.id.favourite_item);
    }
}
