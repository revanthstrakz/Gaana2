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
import com.gaana.models.Tracks.Track;
import com.library.controls.CrossFadeImageView;

public class RadioButtonSongView extends BaseItemView {
    private CrossFadeImageView mImageIcon;
    private CheckBox radioBtn;
    private TextView tvAlbumName;
    private TextView tvSongName;

    public static class RadioButtonSongHolder extends ViewHolder {
        private CrossFadeImageView mImageIcon;
        private CheckBox radioBtn;
        private TextView tvAlbumName;
        private TextView tvSongName;

        public RadioButtonSongHolder(View view) {
            super(view);
            this.mImageIcon = (CrossFadeImageView) view.findViewById(R.id.imgProductIcon);
            this.tvSongName = (TextView) view.findViewById(R.id.tvSongName);
            this.tvAlbumName = (TextView) view.findViewById(R.id.tvAlbumName);
            this.radioBtn = (CheckBox) view.findViewById(R.id.radioSong);
        }
    }

    public RadioButtonSongView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        this.mLayoutId = R.layout.view_item_song_radiobtn;
    }

    public View getPoplatedView(View view, BusinessObject businessObject, ViewGroup viewGroup) {
        if (view == null) {
            view = super.createNewBaseView(this.mLayoutId, view, viewGroup);
        }
        super.getPoplatedView(view, businessObject);
        return getDataFilledView(view, businessObject);
    }

    public View getPoplatedView(ViewHolder viewHolder, BusinessObject businessObject, ViewGroup viewGroup) {
        RadioButtonSongHolder radioButtonSongHolder = (RadioButtonSongHolder) viewHolder;
        this.mView = radioButtonSongHolder.itemView;
        super.getPoplatedView(this.mView, businessObject);
        return getDataFilledView(radioButtonSongHolder, businessObject);
    }

    private View getDataFilledView(RadioButtonSongHolder radioButtonSongHolder, BusinessObject businessObject) {
        Track track = (Track) businessObject;
        this.mImageIcon = radioButtonSongHolder.mImageIcon;
        this.tvSongName = radioButtonSongHolder.tvSongName;
        this.tvAlbumName = radioButtonSongHolder.tvAlbumName;
        this.radioBtn = radioButtonSongHolder.radioBtn;
        if (track.isSelected().booleanValue()) {
            this.radioBtn.setChecked(true);
        } else {
            this.radioBtn.setChecked(false);
        }
        this.mImageIcon.bindImage(track.getArtwork(), this.mAppState.isAppInOfflineMode());
        this.tvSongName.setText(track.getName());
        this.tvAlbumName.setText(track.getArtistNames());
        return this.mView;
    }

    private View getDataFilledView(View view, BusinessObject businessObject) {
        Track track = (Track) businessObject;
        this.mImageIcon = (CrossFadeImageView) view.findViewById(R.id.imgProductIcon);
        this.tvSongName = (TextView) view.findViewById(R.id.tvSongName);
        this.tvAlbumName = (TextView) view.findViewById(R.id.tvAlbumName);
        this.radioBtn = (CheckBox) view.findViewById(R.id.radioSong);
        if (track.isSelected().booleanValue()) {
            this.radioBtn.setChecked(true);
        } else {
            this.radioBtn.setChecked(false);
        }
        this.mImageIcon.bindImage(track.getArtwork(), this.mAppState.isAppInOfflineMode());
        this.tvSongName.setText(track.getName());
        this.tvAlbumName.setText(track.getArtistNames());
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
        Track track = (Track) view.getTag();
        if (!checkBox.isChecked()) {
            track.setIsSelected(Boolean.valueOf(true));
            checkBox.setChecked(true);
            this.mAppState.getArrListTracksForPlaylist().add(track);
        } else if (this.mAppState.getArrListTracksForPlaylist().indexOf(track) != -1) {
            track.setIsSelected(Boolean.valueOf(false));
            this.mAppState.getArrListTracksForPlaylist().remove(this.mAppState.getArrListTracksForPlaylist().indexOf(track));
            checkBox.setChecked(false);
        }
    }
}
