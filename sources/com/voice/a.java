package com.voice;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.constants.Constants;
import com.constants.Constants.SortOrder;
import com.e.a.g;
import com.fragments.BaseGaanaFragment;
import com.fragments.ListingFragment;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.models.Tracks.Track;
import com.gaana.view.item.DownloadSongsItemView;
import com.i.j;
import com.library.controls.CrossFadeImageView;
import com.library.managers.TaskManager.TaskListner;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.managers.URLManager.BusinessObjectType;
import com.managers.af;
import com.managers.u;
import com.models.ListingButton;
import com.models.ListingComponents;
import com.models.ListingParams;
import com.player_framework.GaanaMusicService;
import com.player_framework.PlayerConstants.PauseReasons;
import com.player_framework.o;
import com.services.d;
import com.services.h;
import java.util.ArrayList;

public class a extends Dialog implements OnClickListener, com.voice.b.a {
    private Context a;
    private boolean b;
    private b c = b.a();
    private RippleBackground d;
    private TextView e;
    private TextView f;
    private ConstraintLayout g;
    private ConstraintLayout h;
    private Track i;
    private boolean j;
    private a k;

    private class a {
        private TextView b;
        private TextView c;
        private CrossFadeImageView d;
        private ImageView e;
        private TextView f;
        private LinearLayout g;
        private LinearLayout h;
        private ImageView i;
        private LinearLayout j;
        private LinearLayout k;
        private LinearLayout l;
        private LinearLayout m;
        private LinearLayout n;
        private LinearLayout o;
        private TextView p;

        a(View view) {
            this.b = (TextView) view.findViewById(R.id.song_name);
            this.c = (TextView) view.findViewById(R.id.album_artist_details);
            this.d = (CrossFadeImageView) view.findViewById(R.id.result_artwork);
            this.e = (ImageView) view.findViewById(R.id.cross_result_screen);
            this.f = (TextView) view.findViewById(R.id.view_album_button);
            this.g = (LinearLayout) view.findViewById(R.id.play_now);
            this.h = (LinearLayout) view.findViewById(R.id.history);
            this.i = (ImageView) view.findViewById(R.id.favoriteImg);
            this.j = (LinearLayout) view.findViewById(R.id.favorite);
            this.k = (LinearLayout) view.findViewById(R.id.add_to_playlist);
            this.l = (LinearLayout) view.findViewById(R.id.play_next);
            this.m = (LinearLayout) view.findViewById(R.id.add_to_queue);
            this.n = (LinearLayout) view.findViewById(R.id.info);
            this.o = (LinearLayout) view.findViewById(R.id.share);
            this.p = (TextView) view.findViewById(R.id.identifySongButton);
        }
    }

    public a(@NonNull Context context) {
        super(context, R.style.voice_recog_dialog_theme);
        this.a = context;
        setContentView(R.layout.song_identifier_dialog);
        getWindow().setLayout(-1, -1);
        this.c.a(this.a, this);
        a();
        u.a().a("Identify song", "Action Started", "Hamburger");
    }

    private void a() {
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.identify_song_boarding);
        this.d = (RippleBackground) findViewById(R.id.listening_state);
        if (d.a().b("PREFERENCE_SONG_IDENTIFY_DIALOG_SHOWN", false, false)) {
            constraintLayout.setVisibility(8);
            this.d.setVisibility(0);
            if (GaanaMusicService.s().isPlaying()) {
                o.a(this.a, PauseReasons.MEDIA_BUTTON_TAP);
                this.j = true;
            }
            ((GaanaActivity) this.a).getWindow().addFlags(128);
            this.e = (TextView) findViewById(R.id.song_listening_textview);
            this.f = (TextView) findViewById(R.id.song_listening_textview_hint);
            this.d.a();
            this.d.setOnClickListener(this);
            this.g = (ConstraintLayout) findViewById(R.id.success_response_layout);
            this.h = (ConstraintLayout) findViewById(R.id.failure_response_layout);
            this.h.setOnClickListener(this);
            this.h.setOnClickListener(this);
            a(true);
            return;
        }
        constraintLayout.setVisibility(0);
        this.d.setVisibility(8);
        d.a().a("PREFERENCE_SONG_IDENTIFY_DIALOG_SHOWN", true, false);
        constraintLayout.findViewById(R.id.cross_result_screen).setOnClickListener(this);
        constraintLayout.findViewById(R.id.identifySongGotitButton).setOnClickListener(this);
    }

    public void dismiss() {
        super.dismiss();
        ((GaanaActivity) this.a).getWindow().clearFlags(128);
        if (this.j) {
            o.c(this.a, PauseReasons.MEDIA_BUTTON_TAP);
            this.j = false;
        }
    }

    private void a(boolean z) {
        if (z) {
            this.e.setText(this.a.getString(R.string.listening_text));
            this.f.setText(this.a.getResources().getString(R.string.identify_song_help_text));
            this.f.setVisibility(0);
            this.b = this.c.b();
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    a.this.b();
                }
            }, (long) Constants.dr);
            ((Activity) this.a).getWindow().addFlags(128);
            return;
        }
        this.b = false;
        this.c.d();
        j.a().a((Object) "song_identify");
        ((Activity) this.a).getWindow().clearFlags(128);
    }

    private void b() {
        if (this.b) {
            this.e.setText(this.a.getResources().getString(R.string.searching_text));
            this.f.setVisibility(8);
            this.b = false;
            this.c.d();
            this.c.e();
        }
    }

    public void onClick(View view) {
        a(view);
    }

    public void a(View view) {
        switch (view.getId()) {
            case R.id.add_to_playlist /*2131296425*/:
                u.a().a("Identify song", "Match song clicked", "Add to Playlist");
                af.a(this.a, null).a((int) R.id.addToPlaylistMenu, this.i);
                dismiss();
                return;
            case R.id.add_to_queue /*2131296426*/:
                u.a().a("Identify song", "Match song clicked", "Add to Queue");
                af.a(this.a, null).a((int) R.id.enqueueMenu, this.i);
                dismiss();
                return;
            case R.id.cross_result_screen /*2131296814*/:
                dismiss();
                return;
            case R.id.failure_response_layout /*2131297114*/:
                dismiss();
                return;
            case R.id.favorite /*2131297122*/:
                u.a().a("Identify song", "Match song clicked", "Favorite");
                af a = af.a(this.a, null);
                a.a("Identify song");
                a.b(this.i.getBusinessObjId());
                a.a((int) R.id.favoriteMenu, this.i);
                dismiss();
                return;
            case R.id.history /*2131297315*/:
                u.a().a("Identify song", "Match song clicked", "History");
                c();
                return;
            case R.id.identifySongButton /*2131297376*/:
                u.a().a("Identify song", "Identify another song", "Success");
                a(true);
                if (GaanaMusicService.s().isPlaying()) {
                    o.a(this.a, PauseReasons.MEDIA_BUTTON_TAP);
                    this.j = true;
                }
                this.g.setVisibility(8);
                this.d.setVisibility(0);
                return;
            case R.id.identifySongGotitButton /*2131297377*/:
            case R.id.identify_song_boarding /*2131297378*/:
                a();
                return;
            case R.id.info /*2131297465*/:
                u.a().a("Identify song", "Match song clicked", "Info");
                af.a(this.a, null).a((int) R.id.songInfoMenu, this.i);
                dismiss();
                return;
            case R.id.listen_again_layout /*2131297573*/:
                u.a().a("Identify song", "Identify another song", "Failure");
                a(true);
                if (GaanaMusicService.s().isPlaying()) {
                    o.a(this.a, PauseReasons.MEDIA_BUTTON_TAP);
                    this.j = true;
                }
                this.h.setVisibility(8);
                this.d.setVisibility(0);
                return;
            case R.id.listening_state /*2131297574*/:
                a(false);
                dismiss();
                return;
            case R.id.play_next /*2131297987*/:
                u.a().a("Identify song", "Match song clicked", "Play Next");
                af.a(this.a, null).a((int) R.id.playNextMenu, this.i);
                dismiss();
                return;
            case R.id.play_now /*2131297988*/:
                u.a().a("Identify song", "Match song clicked", "Play");
                af.a(this.a, null).a((int) R.id.playMenu, this.i);
                dismiss();
                return;
            case R.id.share /*2131298377*/:
                u.a().a("Identify song", "Match song clicked", "Share");
                af.a(this.a, null).a((int) R.id.shareMenu, this.i);
                dismiss();
                return;
            case R.id.view_album_button /*2131298862*/:
                u.a().a("Identify song", "Match song clicked", "View Album");
                af.a(this.a, null).a((int) R.id.albumMenu, this.i);
                dismiss();
                return;
            default:
                return;
        }
    }

    public void a(Track track) {
        u.a().a("Identify song", "Song Match", "Success");
        ((Activity) this.a).getWindow().clearFlags(128);
        if (track != null) {
            this.i = track;
            this.i.setBusinessObjType(BusinessObjectType.Tracks);
            g.a().a(track);
            if (this.k == null) {
                this.k = new a(this.g);
            }
            this.k.b.setText(track.getName());
            TextView b = this.k.c;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(track.getAlbumTitle());
            stringBuilder.append(" - ");
            stringBuilder.append(track.getArtistNames());
            b.setText(stringBuilder.toString());
            this.k.d.bindImage(track.getArtwork(), ScaleType.CENTER_CROP);
            this.k.e.setOnClickListener(this);
            this.k.f.setOnClickListener(this);
            this.k.g.setOnClickListener(this);
            this.k.h.setOnClickListener(this);
            if (this.i.isFavorite().booleanValue()) {
                this.k.i.setImageResource(R.drawable.favorited_track);
            } else {
                this.k.i.setImageResource(R.drawable.vector_ab_favorite_white);
            }
            this.k.j.setOnClickListener(this);
            this.k.k.setOnClickListener(this);
            this.k.l.setOnClickListener(this);
            this.k.m.setOnClickListener(this);
            this.k.n.setOnClickListener(this);
            this.k.o.setOnClickListener(this);
            this.k.p.setOnClickListener(this);
            this.d.setVisibility(8);
            this.g.setVisibility(0);
        }
    }

    public void a(String str) {
        u.a().a("Identify song", "Song Match", "Failure");
        ((Activity) this.a).getWindow().clearFlags(128);
        this.d.setVisibility(8);
        this.h.setVisibility(0);
        this.h.findViewById(R.id.listen_again_layout).setOnClickListener(this);
    }

    private void c() {
        ((GaanaActivity) this.a).showProgressDialog(Boolean.valueOf(true), this.a.getResources().getString(R.string.loading_history_text));
        h.a().a(new TaskListner() {
            ArrayList<Track> a;

            public void doBackGroundTask() {
                Process.setThreadPriority(-2);
                this.a = g.a().b();
            }

            public void onBackGroundTaskCompleted() {
                ((GaanaActivity) a.this.a).hideProgressDialog();
                BaseGaanaFragment listingFragment = new ListingFragment();
                listingFragment.a(SortOrder.Default);
                listingFragment.setAnimateFragmentElements(true);
                ListingParams listingParams = new ListingParams();
                listingParams.d(true);
                listingParams.f(true);
                listingParams.a(true);
                listingParams.b(PLAYOUT_SECTION_TYPE.SONG_IDENTIFY_HISTORY.name());
                ListingButton listingButton = new ListingButton();
                listingButton.b(a.this.a.getString(R.string.tab_history));
                listingButton.a(a.this.a.getString(R.string.tab_history));
                listingButton.c(DownloadSongsItemView.class.getName());
                listingButton.d(true);
                listingButton.a(this.a);
                listingParams.a(listingButton);
                listingFragment.a(listingParams);
                ListingComponents listingComponents = new ListingComponents();
                new ArrayList().add(listingButton);
                GaanaApplication.getInstance().setListingComponents(listingComponents);
                ((GaanaActivity) a.this.a).displayFragment(listingFragment);
                a.this.dismiss();
            }
        }, -1);
    }
}
