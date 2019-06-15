package com.gaana.view.item;

import android.content.Context;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.SpannableStringBuilder;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.b.e;
import com.b.i;
import com.fragments.BaseGaanaFragment;
import com.fragments.CreateNewPlaylistFragment;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.fragments.BaseFragment;
import com.gaana.models.BusinessObject;
import com.gaana.models.Tracks.Track;
import com.library.controls.CrossFadeImageView;
import com.managers.u;
import java.util.ArrayList;

public class AddToPlaylistSongsView extends BaseItemView {
    private String fragmentTagToPop;
    private ArrayList<Track> trackArrayList;

    public static class AddToPlaylistSongsViewHolder extends ViewHolder {
        public Button mBtnCreate;
        public CrossFadeImageView mImgCenter;
        public CrossFadeImageView mImgLeft;
        public CrossFadeImageView mImgRight;
        public TextView mTxtHeader;

        public AddToPlaylistSongsViewHolder(View view) {
            super(view);
            this.mTxtHeader = (TextView) view.findViewById(R.id.txt_header);
            this.mBtnCreate = (Button) view.findViewById(R.id.btn_create_playlist);
            this.mBtnCreate.setTypeface(i.a(view.getContext().getAssets(), "fonts/Roboto-Medium.ttf"));
            this.mImgLeft = (CrossFadeImageView) view.findViewById(R.id.crossFadeLeft);
            this.mImgCenter = (CrossFadeImageView) view.findViewById(R.id.crossFadeCenter);
            this.mImgRight = (CrossFadeImageView) view.findViewById(R.id.crossFadeRight);
        }
    }

    public AddToPlaylistSongsView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        this.fragmentTagToPop = "";
        this.mLayoutId = R.layout.item_add_playlist_header;
    }

    public AddToPlaylistSongsView(Context context, BaseFragment baseFragment, int i) {
        super(context, baseFragment, i);
        this.fragmentTagToPop = "";
        this.mLayoutId = R.layout.item_add_playlist_header;
    }

    public void setFragmentTagToPop(String str) {
        this.fragmentTagToPop = str;
    }

    public View getPoplatedView(ViewHolder viewHolder, BusinessObject businessObject, ViewGroup viewGroup) {
        this.trackArrayList = this.mAppState.getArrListTracksForPlaylist();
        AddToPlaylistSongsViewHolder addToPlaylistSongsViewHolder = (AddToPlaylistSongsViewHolder) viewHolder;
        TextView textView = addToPlaylistSongsViewHolder.mTxtHeader;
        if (this.trackArrayList != null && this.trackArrayList.size() > 0) {
            TextAppearanceSpan textAppearanceSpan = new TextAppearanceSpan(this.mContext, R.style.gaana_item_firstline_normal);
            e eVar = new e(i.a(this.mContext.getAssets(), "fonts/Roboto-Medium.ttf"));
            TextAppearanceSpan textAppearanceSpan2 = new TextAppearanceSpan(this.mContext, R.style.gaana_item_secondline);
            SpannableStringBuilder spannableStringBuilder;
            if (this.trackArrayList.size() == 1) {
                addToPlaylistSongsViewHolder.mImgCenter.setVisibility(0);
                addToPlaylistSongsViewHolder.mImgCenter.bindImage(((Track) this.trackArrayList.get(0)).getArtwork());
                addToPlaylistSongsViewHolder.mImgLeft.setVisibility(4);
                addToPlaylistSongsViewHolder.mImgRight.setVisibility(4);
                spannableStringBuilder = new SpannableStringBuilder();
                spannableStringBuilder.append(((Track) this.trackArrayList.get(0)).getName());
                spannableStringBuilder.append("\n").append(((Track) this.trackArrayList.get(0)).getAlbumTitle());
                spannableStringBuilder.setSpan(textAppearanceSpan, 0, ((Track) this.trackArrayList.get(0)).getName().length(), 17);
                spannableStringBuilder.setSpan(eVar, 0, ((Track) this.trackArrayList.get(0)).getName().length(), 17);
                spannableStringBuilder.setSpan(textAppearanceSpan2, ((Track) this.trackArrayList.get(0)).getName().length(), spannableStringBuilder.length(), 17);
                textView.setText(spannableStringBuilder);
            } else if (this.trackArrayList.size() == 2) {
                addToPlaylistSongsViewHolder.mImgLeft.setVisibility(0);
                addToPlaylistSongsViewHolder.mImgRight.setVisibility(0);
                addToPlaylistSongsViewHolder.mImgLeft.bindImage(((Track) this.trackArrayList.get(0)).getArtwork());
                addToPlaylistSongsViewHolder.mImgRight.bindImage(((Track) this.trackArrayList.get(1)).getArtwork());
                addToPlaylistSongsViewHolder.mImgCenter.setVisibility(4);
                spannableStringBuilder = new SpannableStringBuilder();
                spannableStringBuilder.append(((Track) this.trackArrayList.get(0)).getName());
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(((Track) this.trackArrayList.get(0)).getAlbumTitle());
                stringBuilder.append(", ");
                stringBuilder.append(((Track) this.trackArrayList.get(1)).getAlbumTitle());
                spannableStringBuilder.append("\n").append(stringBuilder.toString());
                spannableStringBuilder.setSpan(textAppearanceSpan, 0, ((Track) this.trackArrayList.get(0)).getName().length(), 17);
                spannableStringBuilder.setSpan(eVar, 0, ((Track) this.trackArrayList.get(0)).getName().length(), 17);
                spannableStringBuilder.setSpan(textAppearanceSpan2, ((Track) this.trackArrayList.get(0)).getName().length(), spannableStringBuilder.toString().length(), 17);
                textView.setText(spannableStringBuilder);
            } else {
                addToPlaylistSongsViewHolder.mImgLeft.setVisibility(0);
                addToPlaylistSongsViewHolder.mImgRight.setVisibility(0);
                addToPlaylistSongsViewHolder.mImgCenter.setVisibility(0);
                addToPlaylistSongsViewHolder.mImgLeft.bindImage(((Track) this.trackArrayList.get(0)).getArtwork());
                addToPlaylistSongsViewHolder.mImgCenter.bindImage(((Track) this.trackArrayList.get(1)).getArtwork());
                addToPlaylistSongsViewHolder.mImgRight.bindImage(((Track) this.trackArrayList.get(2)).getArtwork());
                spannableStringBuilder = new SpannableStringBuilder();
                spannableStringBuilder.append(((Track) this.trackArrayList.get(1)).getName());
                spannableStringBuilder.append("\n").append(String.format(this.mContext.getString(R.string.num_other_songs), new Object[]{String.valueOf(this.trackArrayList.size() - 1)}));
                spannableStringBuilder.setSpan(textAppearanceSpan, 0, ((Track) this.trackArrayList.get(1)).getName().length(), 17);
                spannableStringBuilder.setSpan(eVar, 0, ((Track) this.trackArrayList.get(1)).getName().length(), 17);
                spannableStringBuilder.setSpan(textAppearanceSpan2, ((Track) this.trackArrayList.get(1)).getName().length(), spannableStringBuilder.toString().length(), 17);
                textView.setText(spannableStringBuilder);
            }
        }
        addToPlaylistSongsViewHolder.mBtnCreate.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                u.a().b("Add to Playlist Screen", "Create Playlist");
                ((GaanaActivity) AddToPlaylistSongsView.this.mContext).displayFragment(CreateNewPlaylistFragment.a(AddToPlaylistSongsView.this.fragmentTagToPop, false));
            }
        });
        return super.getPoplatedView(viewHolder, businessObject, viewGroup);
    }
}
