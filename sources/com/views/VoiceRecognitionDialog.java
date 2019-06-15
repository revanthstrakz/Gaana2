package com.views;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.a.b;
import com.android.volley.VolleyError;
import com.bumptech.glide.e;
import com.constants.Constants;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.RecyclerTouchListener;
import com.gaana.RecyclerTouchListener.ClickListener;
import com.gaana.application.GaanaApplication;
import com.gaana.login.sso.SsoErrorCodes;
import com.gaana.models.Albums.Album;
import com.gaana.models.Artists;
import com.gaana.models.Artists.Artist;
import com.gaana.models.BusinessObject;
import com.gaana.models.Item;
import com.gaana.models.NextGenSearchAutoSuggests.AutoComplete;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Radios.Radio;
import com.gaana.models.Tracks.Track;
import com.gaana.view.item.BaseItemView.VoicePlaylistGridHolder;
import com.google.ads.interactivemedia.v3.api.AdEvent;
import com.i.i;
import com.library.controls.CrossFadeImageView;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.logging.GaanaLogger.SOURCE_TYPE;
import com.managers.GaanaSearchManager;
import com.managers.PlayerManager;
import com.managers.VoiceRecognition;
import com.managers.VoiceRecognition.VoiceCommand;
import com.managers.VoiceRecognition.a;
import com.managers.VoiceResultManager;
import com.managers.af;
import com.managers.ap;
import com.managers.aq;
import com.managers.u;
import com.player_framework.GaanaMusicService;
import com.player_framework.PlayerConstants.PauseReasons;
import com.player_framework.f;
import com.player_framework.m;
import com.player_framework.o;
import com.services.l.r;
import com.utilities.Util;
import com.utilities.h;
import com.views.HorizontalRecyclerView.c;
import java.util.ArrayList;
import java.util.List;

public class VoiceRecognitionDialog extends Dialog implements OnClickListener, a, VoiceResultManager.a {
    private boolean A;
    private String B;
    private TextView C;
    private boolean D;
    private CrossFadeImageView E;
    private CustomCircularProgressBar F;
    private TextView G;
    private View H;
    private m I = new m() {
        public void onAdEventUpdate(f fVar, AdEvent adEvent) {
        }

        public void onBufferingUpdate(f fVar, int i) {
        }

        public void onCompletion(f fVar) {
        }

        public void onError(f fVar, int i, int i2) {
        }

        public void onInfo(f fVar, int i, int i2) {
        }

        public void onPrepared(f fVar) {
            ((GaanaActivity) VoiceRecognitionDialog.this.a).runOnUiThread(new Runnable() {
                public void run() {
                    if (VoiceRecognitionDialog.this.b != null && VoiceRecognitionDialog.this.b.c()) {
                        return;
                    }
                    if (VoiceRecognitionDialog.this.k.a().isSpeaking() && VoiceRecognitionDialog.this.isShowing()) {
                        VoiceRecognitionDialog.this.k.a(VoiceRecognitionDialog.this.a, false);
                        VoiceRecognitionDialog.this.k.b(true);
                        return;
                    }
                    if (VoiceRecognitionDialog.this.r == ScreenMode.PLAY) {
                        TextView textView = (TextView) VoiceRecognitionDialog.this.findViewById(R.id.voice_play_state_text);
                        if (textView != null) {
                            textView.setText(VoiceRecognitionDialog.this.d.getString(R.string.playback_started));
                        }
                        VoiceRecognitionDialog.this.a((ImageView) VoiceRecognitionDialog.this.findViewById(R.id.voice_play_animation), VoiceRecognitionDialog.this.t, ScreenMode.PLAY);
                    } else if (VoiceRecognitionDialog.this.x != null) {
                        VoiceRecognitionDialog.this.a((ImageView) VoiceRecognitionDialog.this.x.findViewById(R.id.play_icon), VoiceRecognitionDialog.this.t, ScreenMode.SCROLL);
                    }
                    VoiceRecognitionDialog.this.a((int) SsoErrorCodes.SDK_NOT_INITIALIZED, false);
                }
            });
        }
    };
    private final Context a;
    private final VoiceRecognition b;
    private final SpannableStringBuilder c;
    private final Resources d;
    private final String e;
    private final GaanaApplication f;
    private ProgressBar g;
    private TextView h;
    private TextView i;
    private TextView j;
    private final VoiceResultManager k;
    private View l;
    private View m;
    private String n;
    private ImageView o;
    private HorizontalScrollView p;
    private View q;
    private ScreenMode r = ScreenMode.ACTION;
    private ArrayList<Track> s;
    private BusinessObject t;
    private CustomCircularProgressBar u;
    private CountDownTimer v;
    private boolean w;
    private View x;
    private int y = -1;
    private boolean z;

    private enum ScreenMode {
        ACTION,
        PLAY,
        SCROLL,
        COMMANDS
    }

    public VoiceRecognitionDialog(@NonNull Context context) {
        super(context, R.style.voice_recog_dialog_theme);
        this.a = context;
        this.d = this.a.getResources();
        this.f = GaanaApplication.getInstance();
        setContentView(R.layout.voice_recognition);
        getWindow().setLayout(-1, -1);
        this.b = new VoiceRecognition(context);
        this.b.a((a) this);
        this.k = new VoiceResultManager(this.a);
        this.k.a((VoiceResultManager.a) this);
        o.a("listener_voice_search", this.I);
        this.c = b();
        a();
        this.e = this.f.getPlayoutSectionName();
        u.a().b("VoiceInteraction", "Overlay");
    }

    private void a() {
        this.g = (ProgressBar) findViewById(R.id.voice_recog_progress);
        this.g.setIndeterminateDrawable(b.a(this.a));
        this.q = findViewById(R.id.voice_interact_container);
        this.o = (ImageView) findViewById(R.id.voice_recog_button_mic);
        this.o.setOnClickListener(this);
        this.h = (TextView) findViewById(R.id.voice_recog_command);
        this.i = (TextView) findViewById(R.id.voice_action_command_text);
        this.j = (TextView) findViewById(R.id.voice_action_status);
        this.C = (TextView) findViewById(R.id.voice_recog_type_search);
        this.C.setText(d());
        this.C.setOnClickListener(this);
        this.C.setPaintFlags(8);
        this.E = (CrossFadeImageView) findViewById(R.id.voice_action_image_anim);
        e.c(this.a.getApplicationContext()).asGif().load(Integer.valueOf(R.drawable.speech)).into(this.E);
        findViewById(R.id.voice_recog_close).setOnClickListener(this);
        findViewById(R.id.voice_recog_help_img).setOnClickListener(this);
        findViewById(R.id.voice_recog_help_text).setOnClickListener(this);
        findViewById(R.id.voice_recog_mic_overlay).setOnClickListener(this);
        this.u = (CustomCircularProgressBar) findViewById(R.id.voice_recog_close_progress);
        this.u.setOnClickListener(this);
        this.u.getCircularProgressBar().setStrokeWidth(this.d.getDimension(R.dimen.dp1) * 2.0f);
        int dimension = (int) this.d.getDimension(R.dimen.activity_horizontal_margin_small);
        ImageView imageView = new ImageView(this.a);
        imageView.setPadding(dimension, dimension, dimension, dimension);
        imageView.setImageResource(Constants.l ? R.drawable.player_cross_white : R.drawable.player_cross);
        this.u.setExtraView(imageView);
        a(ScreenMode.ACTION);
        onClick(this.o);
        ((GaanaActivity) this.a).getWindow().addFlags(128);
    }

    private void a(BusinessObject businessObject, ArrayList<Track> arrayList, int i) {
        CharSequence charSequence;
        String str;
        CharSequence charSequence2;
        BusinessObject businessObject2 = businessObject;
        int i2 = i;
        this.z = false;
        String str2 = null;
        String artwork;
        String trackTitle;
        String albumTitle;
        if (businessObject2 instanceof Track) {
            Track track = (Track) businessObject2;
            str2 = track.getArtworkLarge();
            artwork = track.getArtwork();
            if (!TextUtils.isEmpty(artwork)) {
                artwork = artwork.replace("80x80", "175x175");
            }
            trackTitle = track.getTrackTitle();
            albumTitle = track.getAlbumTitle();
            u.a().a("VoiceInteraction", "Track", trackTitle);
            charSequence = albumTitle;
            str = artwork;
            charSequence2 = trackTitle;
        } else if (businessObject2 instanceof Radio) {
            Radio radio = (Radio) businessObject2;
            artwork = radio.getArtwork();
            trackTitle = radio.getArtwork();
            albumTitle = radio.getName();
            u.a().a("VoiceInteraction", "Radio", albumTitle);
            charSequence = null;
            str = trackTitle;
            str2 = artwork;
            charSequence2 = albumTitle;
        } else if (businessObject2 instanceof Album) {
            Album album = (Album) businessObject2;
            str2 = album.getArtwork();
            str = album.getArtwork();
            charSequence2 = album.getName();
            charSequence = this.d.getString(R.string.album_text);
            if (i2 == 1) {
                this.f.setPlayoutSectionName(PLAYOUT_SECTION_TYPE.VOICEINT_AUTOPLAY.name());
            } else {
                this.f.setPlayoutSectionName(PLAYOUT_SECTION_TYPE.VOICEINT_PLAY.name());
            }
            u.a().a("VoiceInteraction", "Album", (String) charSequence2);
            if (!(this.s == null || this.s.isEmpty())) {
                PlayerManager.a(this.a).a(this.t.getBusinessObjId(), SOURCE_TYPE.ALBUM.ordinal(), this.t.getEnglishName(), this.t, this.s, this.a, true);
            }
        } else if (businessObject2 instanceof Playlist) {
            Playlist playlist = (Playlist) businessObject2;
            str2 = playlist.getArtwork();
            str = playlist.getArtwork();
            charSequence2 = playlist.getName();
            charSequence = this.d.getString(R.string.playlist_text);
            if (i2 == 1) {
                this.f.setPlayoutSectionName(PLAYOUT_SECTION_TYPE.VOICEINT_AUTOPLAY.name());
            } else {
                this.f.setPlayoutSectionName(PLAYOUT_SECTION_TYPE.VOICEINT_PLAY.name());
            }
            u.a().a("VoiceInteraction", "Playlist", (String) charSequence2);
            if (!(this.s == null || this.s.isEmpty())) {
                PlayerManager.a(this.a).a(this.t.getBusinessObjId(), SOURCE_TYPE.PLAYLIST.ordinal(), this.t.getEnglishName(), this.t, this.s, this.a, true);
            }
        } else {
            charSequence2 = null;
            str = charSequence2;
            charSequence = str;
        }
        a(ScreenMode.PLAY);
        findViewById(R.id.voice_play_result).setOnClickListener(this);
        findViewById(R.id.voice_interact_play_container).setOnClickListener(this);
        c(str2);
        if (!TextUtils.isEmpty(str)) {
            ((CrossFadeImageView) findViewById(R.id.voice_play_image)).bindImage(str, ScaleType.CENTER_CROP);
        }
        if (!TextUtils.isEmpty(charSequence2)) {
            ((TextView) findViewById(R.id.voice_play_text)).setText(charSequence2);
        }
        if (!TextUtils.isEmpty(charSequence)) {
            ((TextView) findViewById(R.id.voice_play_subtext)).setText(charSequence);
        }
        findViewById(R.id.voice_play_animation).setVisibility(8);
        findViewById(R.id.voice_play_loader).setVisibility(0);
        ((TextView) findViewById(R.id.voice_play_state_text)).setText(this.d.getString(R.string.starting_playback));
        TextView textView = (TextView) findViewById(R.id.voice_play_result);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.d.getString(R.string.voice_view_more_result));
        stringBuilder.append(this.n.replaceFirst("play ", ""));
        stringBuilder.append("”");
        textView.setText(stringBuilder.toString());
        ((TextView) findViewById(R.id.voice_play_result)).setPaintFlags(8);
        a((ImageView) findViewById(R.id.voice_play_animation), businessObject2, ScreenMode.PLAY);
    }

    private void a(final ArrayList<Track> arrayList, BusinessObject businessObject, final int i, String str) {
        CharSequence str2;
        String str3 = null;
        this.B = null;
        a(ScreenMode.SCROLL);
        this.D = false;
        if (businessObject != null) {
            if (businessObject instanceof Album) {
                str3 = ((Album) businessObject).getArtwork();
                this.B = "Album";
                u.a().a("VoiceInteraction", this.B, businessObject.getName());
            } else if (businessObject instanceof Playlist) {
                str3 = ((Playlist) businessObject).getArtwork();
                this.B = "Playlist";
                u.a().a("VoiceInteraction", this.B, businessObject.getName());
            } else if (businessObject instanceof Artist) {
                str3 = ((Artist) businessObject).getArtwork();
                this.B = "Artist";
                this.D = true;
                u.a().a("VoiceInteraction", this.B, businessObject.getName());
            } else if (businessObject instanceof Track) {
                Track track = (Track) businessObject;
                str3 = track.getArtworkLarge();
                if (str3 != null) {
                    str3 = track.getArtwork();
                }
                this.D = true;
                u.a().b("VoiceInteraction", "Track");
            }
        }
        c(str3);
        findViewById(R.id.voice_scroll_result).setOnClickListener(this);
        final float dimension = this.d.getDimension(R.dimen.dp10);
        HorizontalRecyclerView horizontalRecyclerView = (HorizontalRecyclerView) findViewById(R.id.voice_scroll);
        HorizontalRecyclerView.b a = horizontalRecyclerView.a(this.a, arrayList.size());
        horizontalRecyclerView.setCount(arrayList.size());
        a.a(new c() {
            public View getCompatibleView(int i, int i2, int i3, ViewHolder viewHolder) {
                Track track = (Track) arrayList.get(i3);
                VoicePlaylistGridHolder voicePlaylistGridHolder = (VoicePlaylistGridHolder) viewHolder;
                voicePlaylistGridHolder.play_icon.setVisibility(0);
                if (VoiceRecognitionDialog.this.y != i3) {
                    voicePlaylistGridHolder.play_icon.setBackgroundResource(0);
                    voicePlaylistGridHolder.play_icon.setVisibility(0);
                    if (voicePlaylistGridHolder.play_icon.getAnimation() != null) {
                        voicePlaylistGridHolder.play_icon.getAnimation().cancel();
                    }
                    voicePlaylistGridHolder.play_icon.setImageResource(R.drawable.vector_circle_play_button_overlay);
                    voicePlaylistGridHolder.play_icon.setPadding(0, 0, 0, 0);
                    voicePlaylistGridHolder.itemView.findViewById(R.id.play_loader).setVisibility(8);
                } else {
                    i = (int) VoiceRecognitionDialog.this.d.getDimension(R.dimen.queue_animation_padding);
                    voicePlaylistGridHolder.play_icon.setPadding(i, i, i, i);
                    voicePlaylistGridHolder.play_icon.setBackgroundResource(R.drawable.voice_equalizer_bg);
                    VoiceRecognitionDialog.this.a(voicePlaylistGridHolder.play_icon, VoiceRecognitionDialog.this.t, ScreenMode.SCROLL);
                }
                if (track.isParentalWarningEnabled()) {
                    Util.a(voicePlaylistGridHolder.tvTopHeading, track.getTrackTitle());
                } else {
                    voicePlaylistGridHolder.tvTopHeading.setText(track.getTrackTitle());
                }
                voicePlaylistGridHolder.tvBottomHeading.setText(track.getAlbumTitle());
                String artwork = track.getArtwork();
                if (!TextUtils.isEmpty(track.getArtwork())) {
                    artwork = artwork.replace("80x80", "175x175");
                }
                voicePlaylistGridHolder.crossFadeImageView.bindImage(artwork, ScaleType.CENTER_CROP);
                if (i3 == 0) {
                    if (VoiceRecognitionDialog.this.x == null) {
                        VoiceRecognitionDialog.this.x = voicePlaylistGridHolder.itemView;
                    }
                    voicePlaylistGridHolder.itemView.setPadding((int) (dimension + dimension), 0, 0, 0);
                    if (VoiceRecognitionDialog.this.D && i == 1) {
                        VoiceRecognitionDialog.this.D = false;
                        VoiceRecognitionDialog.this.a(voicePlaylistGridHolder.itemView, track, i, i3, true);
                        u.a().a("VoiceInteraction", "AutoPlay", VoiceRecognitionDialog.this.t.getBusinessObjType().name());
                    }
                } else {
                    voicePlaylistGridHolder.itemView.setPadding((int) dimension, 0, 0, 0);
                }
                return voicePlaylistGridHolder.itemView;
            }

            public ViewHolder createViewHolder(ViewHolder viewHolder, ViewGroup viewGroup, int i, int i2) {
                View inflate = LayoutInflater.from(VoiceRecognitionDialog.this.a).inflate(R.layout.voice_playlist_grid_115x115, null, false);
                ProgressBar progressBar = (ProgressBar) inflate.findViewById(R.id.play_loader);
                progressBar.setIndeterminate(true);
                progressBar.setIndeterminateDrawable(ContextCompat.getDrawable(VoiceRecognitionDialog.this.a, R.drawable.player_progress_bar));
                return new VoicePlaylistGridHolder(inflate);
            }
        });
        horizontalRecyclerView.setAdapter(a);
        horizontalRecyclerView.addOnItemTouchListener(new RecyclerTouchListener(this.a, horizontalRecyclerView, new ClickListener() {
            public void onLongClick(View view, int i) {
            }

            public void onClick(View view, int i) {
                if (i < arrayList.size()) {
                    VoiceRecognitionDialog.this.a(view, (Track) arrayList.get(i), i, i, false);
                    if (i == 0 && !TextUtils.isEmpty(VoiceRecognitionDialog.this.B)) {
                        if (VoiceRecognitionDialog.this.A) {
                            u.a().a("VoiceInteraction", "Play_first", VoiceRecognitionDialog.this.B);
                            VoiceRecognitionDialog.this.A = false;
                            return;
                        }
                        u.a().a("VoiceInteraction", "Play", VoiceRecognitionDialog.this.B);
                    }
                }
            }
        }));
        TextView textView = (TextView) findViewById(R.id.voice_scroll_titleText);
        if (TextUtils.isEmpty(str2)) {
            str2 = this.d.getString(R.string.top_matches);
        }
        textView.setText(str2);
        textView = (TextView) findViewById(R.id.voice_scroll_result);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.d.getString(R.string.voice_view_more_result));
        stringBuilder.append(this.n.replaceFirst("play ", ""));
        stringBuilder.append("”");
        textView.setText(stringBuilder.toString());
        ((TextView) findViewById(R.id.voice_scroll_result)).setPaintFlags(8);
        this.H = findViewById(R.id.voice_scroll_autoplay);
        if (i == 0) {
            findViewById(R.id.voice_scroll_autoplay_cancel).setOnClickListener(new OnClickListener() {
                public void onClick(View view) {
                    u.a().b("VoiceInteraction", "Autoplay_Cancel");
                    VoiceRecognitionDialog.this.c();
                    VoiceRecognitionDialog.this.H.setVisibility(8);
                }
            });
            this.H.setVisibility(0);
            this.F = (CustomCircularProgressBar) findViewById(R.id.voice_scroll_autoplay_progressbar);
            this.G = (TextView) findViewById(R.id.voice_scroll_autoplay_progresstext);
            a((int) SsoErrorCodes.SDK_NOT_INITIALIZED, true);
            return;
        }
        this.H.setVisibility(8);
    }

    private void a(View view, Track track, int i, int i2, boolean z) {
        a(track);
        this.z = false;
        this.y = i2;
        if (this.t != null) {
            if (this.t instanceof Artists) {
                u.a().a("VoiceInteraction", "PlaySongArtist", String.valueOf(i2));
            } else if (this.t instanceof Track) {
                u.a().a("VoiceInteraction", "PlaySongMood", String.valueOf(i2));
            }
        }
        c();
        if (z) {
            b(1, i != 1);
        } else {
            this.k.a((BusinessObject) track, 1, i != 1, null, null);
        }
        if (this.x != null) {
            ImageView imageView = (ImageView) this.x.findViewById(R.id.play_icon);
            imageView.setBackgroundResource(0);
            imageView.setVisibility(0);
            if (imageView.getAnimation() != null) {
                imageView.getAnimation().cancel();
            }
            imageView.setImageResource(R.drawable.vector_circle_play_button_overlay);
            imageView.setPadding(0, 0, 0, 0);
            this.x.findViewById(R.id.play_loader).setVisibility(8);
        }
        this.x = view;
        if (this.x != null) {
            ImageView imageView2 = (ImageView) this.x.findViewById(R.id.play_icon);
            imageView2.setVisibility(8);
            int dimension = (int) this.d.getDimension(R.dimen.queue_animation_padding);
            imageView2.setPadding(dimension, dimension, dimension, dimension);
            imageView2.setBackgroundResource(R.drawable.voice_equalizer_bg);
            this.x.findViewById(R.id.play_loader).setVisibility(0);
        }
    }

    private void a(Track track) {
        AutoComplete autoComplete = new AutoComplete(track.getTrackTitle(), track.getAlbumTitle(), Integer.valueOf(track.getBusinessObjId()).intValue(), track.getArtwork());
        autoComplete.setBusinessObjType(track.getBusinessObjType());
        autoComplete.setBusinessObjId(track.getBusinessObjId());
        autoComplete.setStreamUrls(track.getStreamUrls());
        autoComplete.setAutoType("Track");
        GaanaSearchManager.a().a(autoComplete);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.voice_interact_play_container /*2131298895*/:
                if (this.t instanceof Radio) {
                    af.a(this.a, null).d(this.t);
                } else {
                    this.k.a(this.t);
                }
                dismiss();
                break;
            case R.id.voice_play_result /*2131298901*/:
            case R.id.voice_scroll_result /*2131298920*/:
                u.a().a("VoiceInteraction", "View all tap", this.n);
                u.a().a("VoiceInteraction", "SearchResult", this.t.getBusinessObjType().name());
                b(this.n.replaceFirst("play ", ""));
                break;
            case R.id.voice_recog_button_mic /*2131298905*/:
                if (h.a((Activity) this.a) && !this.b.c()) {
                    if (this.k.a().isSpeaking()) {
                        this.k.a().stop();
                    }
                    this.k.b(false);
                    c();
                    if (Util.j(this.a)) {
                        if (GaanaMusicService.s().isPlaying()) {
                            o.a(this.a, PauseReasons.MEDIA_BUTTON_TAP);
                            this.z = true;
                        }
                        a(ScreenMode.ACTION);
                        this.b.a();
                        this.g.setVisibility(8);
                        this.E.setVisibility(0);
                        this.j.setText("");
                        this.o.setVisibility(4);
                        u.a().b("VoiceInteraction", "MicTap");
                        this.C.setVisibility(8);
                        break;
                    }
                    ap.a().f(this.a);
                    return;
                }
            case R.id.voice_recog_close /*2131298906*/:
            case R.id.voice_recog_close_progress /*2131298907*/:
            case R.id.voice_recog_mic_overlay /*2131298911*/:
                u.a().b("VoiceInteraction", "Exit");
                dismiss();
                break;
            case R.id.voice_recog_help_img /*2131298909*/:
                dismiss();
                ((GaanaActivity) this.a).changeFragment(R.id.TopTabSearch, "isTrending", null);
                break;
            case R.id.voice_recog_help_text /*2131298910*/:
                if (this.p != null && this.p.getVisibility() != 8) {
                    a(false);
                    break;
                }
                a(true);
                u.a().a("VoiceInteraction", "Tips", "QuestionMark");
                break;
                break;
            case R.id.voice_recog_type_search /*2131298913*/:
                u.a().a("VoiceInteraction", "Text Search", this.n);
                ((GaanaActivity) this.a).changeFragment(R.id.VoiceSearchResult, null, null);
                dismiss();
                break;
        }
    }

    public void a(VoiceCommand voiceCommand, String str) {
        if (voiceCommand != VoiceCommand.SPEECH_STARTED) {
            if (voiceCommand == VoiceCommand.SPEECH_ENDED) {
                this.j.setText(this.d.getString(R.string.getting_results));
                this.E.setVisibility(8);
                this.g.setVisibility(0);
            } else if (voiceCommand == VoiceCommand.ERROR) {
                this.k.a(this.a.getResources().getString(R.string.voice_error_speech));
                u.a().b("VoiceInteraction", "Voice_to_text error");
                if (this.p != null && this.p.getVisibility() == 0) {
                    a(ScreenMode.ACTION);
                }
                this.g.setVisibility(8);
                this.o.setVisibility(0);
                this.i.setGravity(1);
                this.j.setText("");
                this.E.setVisibility(8);
                if (!TextUtils.isEmpty(str)) {
                    this.i.setText(str);
                }
            }
        }
    }

    public void a(ArrayList<String> arrayList) {
        if (this.p != null && this.p.getVisibility() == 0) {
            a(ScreenMode.ACTION);
        }
        this.n = (String) arrayList.get(0);
        this.i.setGravity(17);
        this.i.setText(this.n);
        this.i.setTextColor(Constants.l ? ViewCompat.MEASURED_STATE_MASK : -1);
        this.k.a((ArrayList) arrayList);
        u.a().b("VoiceInteraction", "Analysing");
    }

    private void b(final String str) {
        ((GaanaActivity) this.a).changeFragment(R.id.TopTabSearch, "isFromVoiceSearch", null);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                GaanaSearchManager.a().b(VoiceRecognitionDialog.this.a, str);
                VoiceRecognitionDialog.this.dismiss();
            }
        }, 100);
    }

    public void dismiss() {
        super.dismiss();
        this.f.setPlayoutSectionName(this.e);
        ((GaanaActivity) this.a).getWindow().clearFlags(128);
        if (this.b != null) {
            this.b.d();
        }
        c();
        this.k.a(null);
        this.b.a(null);
        this.k.b(false);
        this.k.a(false);
        if (this.k.a() != null) {
            if (this.k.a().isSpeaking()) {
                this.k.a().stop();
            }
            this.k.a().setOnUtteranceProgressListener(null);
            this.k.a().shutdown();
        }
        o.d("listener_voice_search");
        if (this.z) {
            o.c(this.a, PauseReasons.MEDIA_BUTTON_TAP);
            this.z = false;
        }
    }

    public void a(ArrayList<Track> arrayList, BusinessObject businessObject, int i, String str, String str2) {
        this.k.a(str2);
        if (i == 0) {
            this.A = true;
        } else {
            u.a().b("VoiceInteraction", "PlayDetectServer");
        }
        this.g.setVisibility(8);
        this.o.setVisibility(0);
        this.j.setText("");
        this.s = arrayList;
        this.t = businessObject;
        if (((businessObject instanceof Track) && arrayList.size() == 1 && i == 1) || (businessObject instanceof Radio)) {
            a(businessObject, (ArrayList) arrayList, i);
            u.a().a("VoiceInteraction", "AutoPlay", this.t.getBusinessObjType().name());
        } else if (i == 1 && ((businessObject instanceof Album) || (businessObject instanceof Playlist))) {
            a(businessObject, (ArrayList) arrayList, i);
            u.a().a("VoiceInteraction", "AutoPlay", this.t.getBusinessObjType().name());
        } else if (arrayList == null || arrayList.isEmpty()) {
            a(this.d.getString(R.string.voice_result_error_msg_no_results));
        } else {
            a(arrayList, businessObject, i, str);
        }
    }

    public void a(String str) {
        this.C.setVisibility(0);
        u.a().a("VoiceInteraction", "No Match", this.n);
        this.g.setVisibility(8);
        this.o.setVisibility(0);
        if (this.p != null && this.p.getVisibility() == 0) {
            a(ScreenMode.ACTION);
        }
        this.i.setGravity(1);
        this.j.setText("");
        if (!TextUtils.isEmpty(str)) {
            this.i.setText(str);
            this.i.setTextColor(Constants.l ? ViewCompat.MEASURED_STATE_MASK : -1);
        }
    }

    private void a(boolean z) {
        if (this.p == null) {
            String[] stringArray = this.d.getStringArray(R.array.voice_support_commands);
            this.p = (HorizontalScrollView) findViewById(R.id.voice_action_horizontalScroll);
            LinearLayout linearLayout = new LinearLayout(this.a);
            LayoutParams layoutParams = new LayoutParams(-2, -2);
            layoutParams.setMargins((int) this.d.getDimension(R.dimen.activity_horizontal_margin_small), 0, 0, 0);
            for (CharSequence text : stringArray) {
                Drawable drawable;
                View inflate = LayoutInflater.from(this.a).inflate(R.layout.command_item_layout, null, false);
                ((TextView) inflate.findViewById(R.id.command_item_text)).setText(text);
                if (Constants.l) {
                    drawable = ContextCompat.getDrawable(this.a, R.drawable.button_rounded_command_black);
                } else {
                    drawable = ContextCompat.getDrawable(this.a, R.drawable.button_rounded_command_white);
                }
                inflate.setBackgroundDrawable(drawable);
                inflate.setLayoutParams(layoutParams);
                linearLayout.addView(inflate);
            }
            this.p.addView(linearLayout);
        }
        if (z) {
            a(ScreenMode.COMMANDS);
        } else {
            a(this.r);
        }
    }

    private SpannableStringBuilder b() {
        CharSequence charSequence;
        StringBuilder stringBuilder;
        String str;
        List<com.models.c.a> voiceHelpList = ((GaanaActivity) this.a).getVoiceHelpList();
        CharSequence charSequence2 = null;
        if (voiceHelpList != null) {
            CharSequence charSequence3 = null;
            charSequence = charSequence3;
            for (com.models.c.a aVar : voiceHelpList) {
                if (aVar.b().equals("help_text_7")) {
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(aVar.a());
                    stringBuilder2.append("\n\n");
                    String stringBuilder3 = stringBuilder2.toString();
                } else if (aVar.b().equals("help_text_8")) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(aVar.a());
                    stringBuilder.append("\n");
                    charSequence3 = stringBuilder.toString();
                } else if (aVar.b().equals("help_text_9")) {
                    charSequence = aVar.a();
                }
            }
        } else {
            str = null;
            charSequence = str;
        }
        ArrayList b = aq.a().b();
        StringBuilder stringBuilder4 = new StringBuilder();
        stringBuilder4.append("Play ");
        stringBuilder4.append(b.size() > 0 ? ((Item) b.get(0)).getName() : "Bom Diggy Diggy");
        String stringBuilder5 = stringBuilder4.toString();
        StringBuilder stringBuilder6 = new StringBuilder();
        stringBuilder6.append("Play ");
        stringBuilder6.append(b.size() > 1 ? ((Item) b.get(1)).getName() : "Dil Diyan Gallan");
        String stringBuilder7 = stringBuilder6.toString();
        int i = Constants.l ? ViewCompat.MEASURED_STATE_MASK : -1;
        int dimensionPixelSize = this.d.getDimensionPixelSize(R.dimen.text_size_16);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (TextUtils.isEmpty(charSequence2)) {
            charSequence2 = this.d.getString(R.string.voice_help_first_line);
        }
        spannableStringBuilder.append(charSequence2);
        spannableStringBuilder.setSpan(new ForegroundColorSpan(i), 0, spannableStringBuilder.length(), 33);
        spannableStringBuilder.setSpan(new AbsoluteSizeSpan(dimensionPixelSize), 0, spannableStringBuilder.length(), 33);
        int length = spannableStringBuilder.length();
        StringBuilder stringBuilder8 = new StringBuilder();
        stringBuilder8.append("\"");
        if (TextUtils.isEmpty(str)) {
            str = stringBuilder5;
        }
        stringBuilder8.append(str);
        stringBuilder8.append("\"\n");
        spannableStringBuilder.append(stringBuilder8.toString());
        spannableStringBuilder.setSpan(new ForegroundColorSpan(i), length, spannableStringBuilder.length(), 33);
        spannableStringBuilder.setSpan(new StyleSpan(1), length, spannableStringBuilder.length(), 33);
        length = spannableStringBuilder.length();
        stringBuilder = new StringBuilder();
        stringBuilder.append("\"");
        if (!TextUtils.isEmpty(charSequence)) {
            stringBuilder7 = charSequence;
        }
        stringBuilder.append(stringBuilder7);
        stringBuilder.append("\"");
        spannableStringBuilder.append(stringBuilder.toString());
        spannableStringBuilder.setSpan(new ForegroundColorSpan(i), length, spannableStringBuilder.length(), 33);
        spannableStringBuilder.setSpan(new StyleSpan(1), length, spannableStringBuilder.length(), 33);
        return spannableStringBuilder;
    }

    private void c(String str) {
        this.q.setBackgroundDrawable(new BitmapDrawable(this.d, Util.a(this.a, BitmapFactory.decodeResource(this.d, R.drawable.placeholder_album_artwork_large))));
        if (!TextUtils.isEmpty(str)) {
            i.a().a(str, new r() {
                public void onErrorResponse(VolleyError volleyError) {
                }

                public void onSuccessfulResponse(Bitmap bitmap) {
                    VoiceRecognitionDialog.this.q.setBackgroundDrawable(new BitmapDrawable(VoiceRecognitionDialog.this.d, Util.a(VoiceRecognitionDialog.this.a, bitmap)));
                }
            });
        }
    }

    private void a(ScreenMode screenMode) {
        this.q.setVisibility(8);
        this.y = -1;
        this.x = null;
        if (this.l != null) {
            this.l.setVisibility(8);
        }
        if (this.m != null) {
            this.m.setVisibility(8);
        }
        if (this.p != null) {
            this.p.setVisibility(8);
        }
        this.i.setVisibility(8);
        this.h.setVisibility(8);
        findViewById(R.id.voice_interact_actions).setVisibility(8);
        findViewById(R.id.voice_recog_help_img).setVisibility(0);
        findViewById(R.id.voice_recog_help_text).setVisibility(8);
        this.i.setLineSpacing((float) Util.b(3), 1.0f);
        if (screenMode == ScreenMode.ACTION) {
            this.r = screenMode;
            this.i.setVisibility(0);
            findViewById(R.id.voice_interact_actions).setVisibility(0);
            this.i.setText(this.c);
            this.i.setGravity(1);
            if (Constants.l) {
                this.i.setTextColor(ContextCompat.getColor(this.a, R.color.black_alfa_60));
            } else {
                this.i.setTextColor(ContextCompat.getColor(this.a, R.color.white_alfa_60));
            }
            this.i.setTextSize(2, 20.0f);
            this.i.setLineSpacing((float) Util.b(10), 1.0f);
        } else if (screenMode == ScreenMode.PLAY) {
            this.r = screenMode;
            if (this.l == null) {
                this.l = ((ViewStub) findViewById(R.id.voice_interact_play)).inflate();
            }
            this.h.setVisibility(0);
            if (!TextUtils.isEmpty(this.n)) {
                this.h.setText(this.n);
            }
            this.q.setVisibility(0);
            this.l.setVisibility(0);
        } else if (screenMode == ScreenMode.SCROLL) {
            this.r = screenMode;
            if (this.m == null) {
                this.m = ((ViewStub) findViewById(R.id.voice_interact_scroll)).inflate();
            }
            this.h.setVisibility(0);
            if (!TextUtils.isEmpty(this.n)) {
                this.h.setText(this.n);
            }
            this.q.setVisibility(0);
            this.m.setVisibility(0);
        } else if (screenMode == ScreenMode.COMMANDS) {
            findViewById(R.id.voice_interact_actions).setVisibility(0);
            this.i.setVisibility(0);
            this.p.setVisibility(0);
            this.i.setText(this.d.getString(R.string.support_commands_text));
            this.i.setGravity(3);
            if (Constants.l) {
                this.i.setTextColor(ContextCompat.getColor(this.a, R.color.black_alfa_50));
            } else {
                this.i.setTextColor(ContextCompat.getColor(this.a, R.color.white_alfa_50));
            }
            this.i.setTextSize(2, 14.0f);
            findViewById(R.id.voice_recog_help_img).setVisibility(8);
            findViewById(R.id.voice_recog_help_text).setVisibility(0);
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(ImageView imageView, BusinessObject businessObject, ScreenMode screenMode) {
        if (imageView != null) {
            int i = Constants.l ? R.drawable.voice_equalizer_black : R.drawable.voice_equalizer_white;
            if (GaanaMusicService.s().isPlaying()) {
                if (this.r == ScreenMode.PLAY) {
                    findViewById(R.id.voice_play_loader).setVisibility(8);
                } else if (this.x != null) {
                    i = R.drawable.ic_equalizer_white_36dp;
                    this.x.findViewById(R.id.play_loader).setVisibility(8);
                }
                AnimationDrawable animationDrawable = (AnimationDrawable) ContextCompat.getDrawable(this.a, i);
                imageView.setImageDrawable(animationDrawable);
                imageView.setVisibility(0);
                animationDrawable.start();
            } else if (imageView.getVisibility() == 0) {
                imageView.setVisibility(8);
                if (imageView.getAnimation() != null) {
                    imageView.getAnimation().cancel();
                }
            }
        }
    }

    private void a(int i, boolean z) {
        if (!this.b.c()) {
            if (this.w) {
                c();
            }
            i += 1000;
            this.w = true;
            if (z) {
                this.F.setMax(i);
            } else {
                this.u.setVisibility(0);
                this.u.setMax(i);
                findViewById(R.id.voice_recog_close).setVisibility(8);
            }
            final boolean z2 = z;
            this.v = new CountDownTimer((long) i, 50) {
                public void onTick(long j) {
                    if (z2) {
                        VoiceRecognitionDialog.this.F.setProgress((int) j);
                        VoiceRecognitionDialog.this.G.setText(String.valueOf(j / 1000));
                        return;
                    }
                    VoiceRecognitionDialog.this.u.setProgress((int) j);
                }

                public void onFinish() {
                    if (!z2) {
                        VoiceRecognitionDialog.this.u.setProgress(0);
                        u.a().b("VoiceInteraction", "AutoExit");
                    }
                    VoiceRecognitionDialog.this.w = true;
                    if (!z2) {
                        VoiceRecognitionDialog.this.dismiss();
                    } else if (VoiceRecognitionDialog.this.H != null) {
                        VoiceRecognitionDialog.this.H.setVisibility(8);
                        VoiceRecognitionDialog.this.a(VoiceRecognitionDialog.this.x, (Track) VoiceRecognitionDialog.this.s.get(0), 1, 0, true);
                    }
                }
            };
            this.v.start();
        }
    }

    private void c() {
        this.u.setVisibility(8);
        findViewById(R.id.voice_recog_close).setVisibility(0);
        if (this.H != null) {
            this.H.setVisibility(8);
        }
        if (this.w && this.v != null) {
            this.v.cancel();
        }
        this.w = false;
    }

    private SpannableStringBuilder d() {
        int dimensionPixelSize = this.d.getDimensionPixelSize(R.dimen.design_bottom_navigation_text_size);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append(this.d.getString(R.string.voice_or));
        spannableStringBuilder.setSpan(new AbsoluteSizeSpan(dimensionPixelSize), 0, spannableStringBuilder.length(), 33);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(" ");
        stringBuilder.append(this.d.getString(R.string.voice_type_your_search));
        spannableStringBuilder.append(stringBuilder.toString());
        return spannableStringBuilder;
    }

    private void b(int i, boolean z) {
        if (i != 1 || z) {
            this.f.setPlayoutSectionName(PLAYOUT_SECTION_TYPE.VOICEINT_PLAY.name());
        } else {
            this.f.setPlayoutSectionName(PLAYOUT_SECTION_TYPE.VOICEINT_AUTOPLAY.name());
        }
        u.a().a("VoiceInteraction", "Add to Queue", this.t.getBusinessObjType().name());
        PlayerManager.a(this.a).a(this.t.getBusinessObjId(), SOURCE_TYPE.ARTIST.ordinal(), this.t.getEnglishName(), this.t, this.s, this.a, true);
    }
}
