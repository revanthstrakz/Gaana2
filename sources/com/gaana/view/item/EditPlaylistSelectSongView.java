package com.gaana.view.item;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.fragments.BaseGaanaFragment;
import com.gaana.R;
import com.gaana.models.BusinessObject;
import com.gaana.models.Tracks.Track;
import com.library.controls.CrossFadeImageView;
import com.services.l.az;

public class EditPlaylistSelectSongView extends BaseItemView {

    public static class EditPlaylistSelectSongViewHolder extends ViewHolder implements az {
        private ImageView deleteIcon;
        private ImageView holderIcon;
        private CrossFadeImageView mImageIcon;
        private TextView tvAlbumName;
        private TextView tvSongName;

        public void onItemClear(int i) {
        }

        public void onItemSelected() {
        }

        public EditPlaylistSelectSongViewHolder(View view) {
            super(view);
            this.mImageIcon = (CrossFadeImageView) view.findViewById(R.id.imgProductIcon);
            this.tvSongName = (TextView) view.findViewById(R.id.tvSongName);
            this.tvAlbumName = (TextView) view.findViewById(R.id.tvAlbumName);
            this.holderIcon = (ImageView) view.findViewById(R.id.img_edit_holder);
            this.deleteIcon = (ImageView) view.findViewById(R.id.img_delete);
        }
    }

    public EditPlaylistSelectSongView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        this.mFragment = baseGaanaFragment;
        this.mLayoutId = R.layout.item_edit_playlist_song;
    }

    public View getPoplatedView(ViewHolder viewHolder, BusinessObject businessObject, ViewGroup viewGroup) {
        EditPlaylistSelectSongViewHolder editPlaylistSelectSongViewHolder = (EditPlaylistSelectSongViewHolder) viewHolder;
        this.mView = viewHolder.itemView;
        return getDataFilledView(editPlaylistSelectSongViewHolder, businessObject);
    }

    private View getDataFilledView(final EditPlaylistSelectSongViewHolder editPlaylistSelectSongViewHolder, BusinessObject businessObject) {
        final Track track = (Track) businessObject;
        editPlaylistSelectSongViewHolder.itemView.setTag(track);
        editPlaylistSelectSongViewHolder.deleteIcon.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                track.setMarkedForDeletionFromPlaylist(track.isMarkedForDeletionFromPlaylist() ^ 1);
                TypedArray obtainStyledAttributes;
                Drawable drawable;
                if (track.isMarkedForDeletionFromPlaylist()) {
                    obtainStyledAttributes = EditPlaylistSelectSongView.this.getContext().obtainStyledAttributes(R.styleable.VectorDrawables);
                    drawable = ContextCompat.getDrawable(EditPlaylistSelectSongView.this.getContext(), obtainStyledAttributes.getResourceId(28, -1));
                    obtainStyledAttributes.recycle();
                    editPlaylistSelectSongViewHolder.deleteIcon.setImageDrawable(drawable);
                    editPlaylistSelectSongViewHolder.tvSongName.setPaintFlags(editPlaylistSelectSongViewHolder.tvSongName.getPaintFlags() | 16);
                    editPlaylistSelectSongViewHolder.tvAlbumName.setPaintFlags(editPlaylistSelectSongViewHolder.tvAlbumName.getPaintFlags() | 16);
                    editPlaylistSelectSongViewHolder.holderIcon.setVisibility(4);
                    editPlaylistSelectSongViewHolder.itemView.setAlpha(0.3f);
                    return;
                }
                obtainStyledAttributes = EditPlaylistSelectSongView.this.getContext().obtainStyledAttributes(R.styleable.VectorDrawables);
                drawable = ContextCompat.getDrawable(EditPlaylistSelectSongView.this.getContext(), obtainStyledAttributes.getResourceId(41, -1));
                obtainStyledAttributes.recycle();
                editPlaylistSelectSongViewHolder.deleteIcon.setImageDrawable(drawable);
                editPlaylistSelectSongViewHolder.tvSongName.setPaintFlags(editPlaylistSelectSongViewHolder.tvSongName.getPaintFlags() & -17);
                editPlaylistSelectSongViewHolder.tvAlbumName.setPaintFlags(editPlaylistSelectSongViewHolder.tvAlbumName.getPaintFlags() & -17);
                editPlaylistSelectSongViewHolder.holderIcon.setVisibility(0);
                editPlaylistSelectSongViewHolder.itemView.setAlpha(1.0f);
            }
        });
        editPlaylistSelectSongViewHolder.mImageIcon.bindImage(track.getArtwork(), this.mAppState.isAppInOfflineMode());
        editPlaylistSelectSongViewHolder.tvSongName.setText(track.getName());
        editPlaylistSelectSongViewHolder.tvAlbumName.setText(track.getArtistNames());
        TypedArray obtainStyledAttributes;
        Drawable drawable;
        if (track.isMarkedForDeletionFromPlaylist()) {
            obtainStyledAttributes = getContext().obtainStyledAttributes(R.styleable.VectorDrawables);
            drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(28, -1));
            obtainStyledAttributes.recycle();
            editPlaylistSelectSongViewHolder.deleteIcon.setImageDrawable(drawable);
            editPlaylistSelectSongViewHolder.tvSongName.setPaintFlags(editPlaylistSelectSongViewHolder.tvSongName.getPaintFlags() | 16);
            editPlaylistSelectSongViewHolder.tvAlbumName.setPaintFlags(editPlaylistSelectSongViewHolder.tvAlbumName.getPaintFlags() | 16);
            editPlaylistSelectSongViewHolder.holderIcon.setVisibility(4);
            editPlaylistSelectSongViewHolder.itemView.setAlpha(0.3f);
        } else {
            obtainStyledAttributes = getContext().obtainStyledAttributes(R.styleable.VectorDrawables);
            drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(41, -1));
            obtainStyledAttributes.recycle();
            editPlaylistSelectSongViewHolder.deleteIcon.setImageDrawable(drawable);
            editPlaylistSelectSongViewHolder.tvSongName.setPaintFlags(editPlaylistSelectSongViewHolder.tvSongName.getPaintFlags() & -17);
            editPlaylistSelectSongViewHolder.tvAlbumName.setPaintFlags(editPlaylistSelectSongViewHolder.tvAlbumName.getPaintFlags() & -17);
            editPlaylistSelectSongViewHolder.holderIcon.setVisibility(0);
            editPlaylistSelectSongViewHolder.itemView.setAlpha(1.0f);
        }
        return editPlaylistSelectSongViewHolder.itemView;
    }

    public void onClick(View view) {
        super.onClick(view);
    }
}
