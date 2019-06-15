package com.gaana.view.item;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import com.fragments.BaseGaanaFragment;
import com.gaana.R;
import com.gaana.models.BusinessObject;
import com.gaana.models.Playlists.Playlist;
import com.library.controls.CrossFadeImageView;
import java.util.ArrayList;

public class RadioButtonPlaylistView extends BaseItemView {
    private CrossFadeImageView mImageIcon;
    private CheckBox mRadioBtn;
    private View mView;
    private TextView tvAlbumName;
    private TextView tvSongName;

    public static class RadioButtonPlaylistViewHolder extends ViewHolder {
        private CrossFadeImageView mImageIcon;
        private CheckBox mRadioBtn;
        private CheckBox radioBtn;
        private TextView tvAlbumName;
        private TextView tvSongName;

        public RadioButtonPlaylistViewHolder(View view) {
            super(view);
            this.mImageIcon = (CrossFadeImageView) view.findViewById(R.id.imgProductIcon);
            this.tvSongName = (TextView) view.findViewById(R.id.tvSongName);
            this.tvSongName.setClickable(false);
            this.tvAlbumName = (TextView) view.findViewById(R.id.tvAlbumName);
            this.tvAlbumName.setVisibility(8);
            this.mRadioBtn = (CheckBox) view.findViewById(R.id.radioSong);
        }
    }

    public RadioButtonPlaylistView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        this.mView = null;
        this.mLayoutId = R.layout.view_item_playlist_radiobtn;
    }

    public View getPoplatedView(View view, BusinessObject businessObject, ViewGroup viewGroup) {
        if (view == null) {
            view = super.createNewBaseView(this.mLayoutId, view, viewGroup);
        }
        view.setOnClickListener(this);
        view.setTag(businessObject);
        return getDataFilledView(view, businessObject);
    }

    public View getPoplatedView(ViewHolder viewHolder, BusinessObject businessObject, ViewGroup viewGroup) {
        RadioButtonPlaylistViewHolder radioButtonPlaylistViewHolder = (RadioButtonPlaylistViewHolder) viewHolder;
        this.mView = radioButtonPlaylistViewHolder.itemView;
        super.getPoplatedView(this.mView, businessObject);
        return getDataFilledView(radioButtonPlaylistViewHolder, businessObject);
    }

    private View getDataFilledView(RadioButtonPlaylistViewHolder radioButtonPlaylistViewHolder, BusinessObject businessObject) {
        Playlist playlist = (Playlist) businessObject;
        this.mImageIcon = radioButtonPlaylistViewHolder.mImageIcon;
        this.tvSongName = radioButtonPlaylistViewHolder.tvSongName;
        this.tvSongName.setClickable(false);
        this.tvAlbumName = radioButtonPlaylistViewHolder.tvAlbumName;
        this.tvAlbumName.setVisibility(8);
        this.mRadioBtn = radioButtonPlaylistViewHolder.mRadioBtn;
        if (playlist.isSelected().booleanValue()) {
            this.mRadioBtn.setChecked(true);
        } else {
            this.mRadioBtn.setChecked(false);
        }
        this.mImageIcon.bindImage(playlist.getArtwork(), this.mAppState.isAppInOfflineMode());
        this.tvSongName.setText(playlist.getName());
        TextView textView = this.tvAlbumName;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getContext().getString(R.string.createdBy));
        stringBuilder.append(":");
        stringBuilder.append(playlist.getCreatedby());
        textView.setText(stringBuilder.toString());
        return this.mView;
    }

    private View getDataFilledView(View view, BusinessObject businessObject) {
        Playlist playlist = (Playlist) businessObject;
        this.mImageIcon = (CrossFadeImageView) view.findViewById(R.id.imgProductIcon);
        this.tvSongName = (TextView) view.findViewById(R.id.tvSongName);
        this.tvSongName.setClickable(false);
        this.tvAlbumName = (TextView) view.findViewById(R.id.tvAlbumName);
        this.tvAlbumName.setVisibility(8);
        this.mRadioBtn = (CheckBox) view.findViewById(R.id.radioSong);
        if (playlist.isSelected().booleanValue()) {
            this.mRadioBtn.setChecked(true);
        } else {
            this.mRadioBtn.setChecked(false);
        }
        this.mImageIcon.bindImage(playlist.getArtwork(), this.mAppState.isAppInOfflineMode());
        this.tvSongName.setText(playlist.getName());
        TextView textView = this.tvAlbumName;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getContext().getString(R.string.createdBy));
        stringBuilder.append(":");
        stringBuilder.append(playlist.getCreatedby());
        textView.setText(stringBuilder.toString());
        return view;
    }

    public void onClick(View view) {
        CheckBox checkBox;
        super.onClick(view);
        if (view instanceof CheckBox) {
            checkBox = (CheckBox) view;
        } else {
            checkBox = (CheckBox) view.findViewById(R.id.radioSong);
        }
        Playlist playlist = (Playlist) view.getTag();
        if (!checkBox.isChecked()) {
            checkBox.setChecked(true);
            playlist.setIsSelected(Boolean.valueOf(true));
            if (this.mAppState.getArrListPlaylist() == null) {
                this.mAppState.setArrListPlaylist(new ArrayList());
            }
            this.mAppState.getArrListPlaylist().add(playlist);
        } else if (this.mAppState.getArrListPlaylist() != null) {
            this.mAppState.getArrListPlaylist().remove(this.mAppState.getArrListPlaylist().indexOf(playlist));
            checkBox.setChecked(false);
            playlist.setIsSelected(Boolean.valueOf(false));
        }
    }
}
