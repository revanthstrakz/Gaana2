package com.gaana.actionbar;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.fragments.BaseGaanaFragment;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.localmedia.PlaylistSyncManager;
import com.gaana.models.Playlists.Playlist;
import com.managers.aj;
import com.models.ListingButton;
import java.util.ArrayList;

public class EditPlaylistActionbar extends LinearLayout implements OnClickListener {
    private Context mContext;
    private EditText mEditPlaylistName = ((EditText) findViewById(R.id.etPlaylistName));

    public View getActionBar() {
        return this;
    }

    public EditPlaylistActionbar(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context);
        new int[1][0] = R.attr.ic_action_accept;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(R.styleable.VectorDrawables);
        Drawable drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(19, -1));
        obtainStyledAttributes.recycle();
        this.mContext = context;
        LayoutInflater.from(context).inflate(R.layout.ab_edit_playlist, this);
        ((TextView) findViewById(R.id.tvCurrentViewTag)).setText(R.string.opt_edit_playlist);
        Playlist playlist = (Playlist) GaanaApplication.getInstance().getListingComponents().a();
        this.mEditPlaylistName.setText(playlist.getName());
        if (playlist.getIsMiniPlaylist() != null && playlist.getIsMiniPlaylist().equals("1")) {
            this.mEditPlaylistName.setEnabled(false);
        }
        findViewById(R.id.btnLeft).setOnClickListener(this);
        ((ImageView) findViewById(R.id.btnRight)).setImageDrawable(drawable);
        findViewById(R.id.btnRight).setOnClickListener(this);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnLeft) {
            ((GaanaActivity) this.mContext).popBackStack();
        } else if (id == R.id.btnRight) {
            GaanaApplication gaanaApplication = (GaanaApplication) GaanaApplication.getContext();
            Playlist playlist = (Playlist) gaanaApplication.getListingComponents().a();
            ArrayList g = ((ListingButton) gaanaApplication.getListingComponents().c().get(0)).g();
            String obj = this.mEditPlaylistName.getText().toString();
            if (TextUtils.isEmpty(obj) || TextUtils.isEmpty(obj.trim())) {
                aj.a().a(getContext(), getContext().getResources().getString(R.string.enter_playlist_name));
                return;
            }
            boolean z;
            if (playlist.getName().equals(obj.trim())) {
                z = false;
            } else {
                playlist.setName(obj.trim());
                z = true;
            }
            playlist.setArrList(g);
            playlist.setChanged(true);
            if (TextUtils.isEmpty(playlist.getIsMiniPlaylist()) || playlist.getIsMiniPlaylist().equals(Integer.valueOf(0))) {
                PlaylistSyncManager.getInstance().showDialogAndEditPlaylist((Activity) this.mContext, playlist, z, g);
            } else {
                PlaylistSyncManager.getInstance().showDialogAndEditPlaylistOnline((Activity) this.mContext, playlist, z, g);
            }
        }
    }
}
