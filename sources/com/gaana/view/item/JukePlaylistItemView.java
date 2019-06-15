package com.gaana.view.item;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.b.i;
import com.fragments.BaseGaanaFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.juke.JukePartyFragment;
import com.gaana.juke.JukePlaylist;
import com.gaana.view.item.BaseItemView.PlaylistGridHolder;
import com.managers.ap;
import com.utilities.Util;
import java.util.Random;

public class JukePlaylistItemView extends BaseItemView {
    private String gaTitle;
    private final int[] mColorBGArray;
    private Context mContext;

    public JukePlaylistItemView(Context context, BaseGaanaFragment baseGaanaFragment, String str) {
        super(context, baseGaanaFragment);
        this.mContext = context;
        this.gaTitle = str;
        this.mLayoutId = R.layout.view_item_playlist;
        ((BaseActivity) this.mContext).currentItem = "Playlist";
        this.mColorBGArray = new int[]{Color.parseColor("#13b6cb"), Color.parseColor("#f5a623")};
    }

    public View getPopulatedView(ViewHolder viewHolder, JukePlaylist jukePlaylist) {
        View view = viewHolder.itemView;
        view.setOnClickListener(this);
        view.setTag(jukePlaylist);
        PlaylistGridHolder playlistGridHolder = (PlaylistGridHolder) viewHolder;
        TextView textView = playlistGridHolder.tvTopHeading;
        textView.setVisibility(0);
        textView.setText(this.mContext.getResources().getString(R.string.by).concat(!TextUtils.isEmpty(jukePlaylist.getOwnerName()) ? jukePlaylist.getOwnerName() : ""));
        view.findViewById(R.id.playlist_status).setVisibility(jukePlaylist.getPlStatus() == 2 ? 0 : 8);
        if (TextUtils.isEmpty(jukePlaylist.getAtw())) {
            playlistGridHolder.crossFadeImageView.setImageDrawable(getColorBG());
            if (playlistGridHolder.mOverlayBg != null) {
                playlistGridHolder.mOverlayBg.setVisibility(4);
            }
        } else {
            playlistGridHolder.crossFadeImageView.bindImage(jukePlaylist.getAtw());
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setCornerRadius((float) Util.b(4));
            gradientDrawable.setColor(this.mContext.getResources().getColor(R.color.black_alfa_60));
            if (playlistGridHolder.mOverlayBg != null) {
                if (playlistGridHolder.mOverlayBg instanceof ImageView) {
                    ((ImageView) playlistGridHolder.mOverlayBg).setImageDrawable(gradientDrawable);
                } else {
                    playlistGridHolder.mOverlayBg.setBackgroundDrawable(gradientDrawable);
                }
                playlistGridHolder.mOverlayBg.setVisibility(0);
            }
        }
        playlistGridHolder.mImgIndictor.setVisibility(4);
        TextView textView2 = (TextView) view.findViewById(R.id.playlist_party_name);
        textView2.setVisibility(0);
        textView2.setText(jukePlaylist.getName());
        textView2.setTypeface(i.a(this.mContext.getAssets(), "fonts/Roboto-Medium.ttf"));
        return viewHolder.itemView;
    }

    public void onClick(View view) {
        super.onClick(view);
        if (Util.j(this.mContext)) {
            JukePlaylist jukePlaylist = (JukePlaylist) view.getTag();
            jukePlaylist.setPartySource(this.gaTitle);
            jukePlaylist.setSourcePlayListId(jukePlaylist.getBusinessObjId());
            ((GaanaActivity) this.mContext).displayFragment(JukePartyFragment.newInstance(jukePlaylist, -1, "", false));
            return;
        }
        ap.a().f(this.mContext);
    }

    private GradientDrawable getColorBG() {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius((float) Util.b(4));
        gradientDrawable.setColor(this.mColorBGArray[new Random().nextInt(this.mColorBGArray.length)]);
        return gradientDrawable;
    }
}
