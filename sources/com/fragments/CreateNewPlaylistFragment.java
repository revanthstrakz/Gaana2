package com.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import android.widget.TextView.OnEditorActionListener;
import com.b.i;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.localmedia.PlaylistSyncManager;
import com.gaana.localmedia.PlaylistSyncManager.PLAYLIST_STATUS;
import com.gaana.login.LoginManager;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Tracks.Track;
import com.gaana.models.Tracks.Track.Artist;
import com.gaana.models.Tracks.Track.Tags;
import com.i.d;
import com.library.managers.TaskManager.TaskListner;
import com.managers.DownloadManager;
import com.managers.af;
import com.managers.aj;
import com.managers.ap;
import com.managers.u;
import com.services.h;
import com.services.o;
import com.utilities.Util;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class CreateNewPlaylistFragment extends BaseGaanaFragment implements OnClickListener, a {
    private static Comparator<Entry<String, Integer>> r = new Comparator<Entry<String, Integer>>() {
        /* renamed from: a */
        public int compare(Entry<String, Integer> entry, Entry<String, Integer> entry2) {
            return ((Integer) entry.getValue()).compareTo((Integer) entry2.getValue());
        }
    };
    private EditText a;
    private TextView b;
    private TextView c;
    private TextView d;
    private TextView e;
    private int f;
    private int g;
    private PLAYLIST_STATUS h = PLAYLIST_STATUS.FAILED;
    private Playlist i;
    private PLAYLIST_STATUS j;
    private String k = "";
    private boolean l;
    private String m;
    private Handler n = new Handler(Looper.getMainLooper());
    private final Map<String, Integer> o = new HashMap();
    private final Map<String, Integer> p = new HashMap();
    private List<String> q = new ArrayList();

    public void setGAScreenName(String str, String str2) {
    }

    private void b() {
        this.o.put("romance", Integer.valueOf(0));
        this.o.put("party", Integer.valueOf(0));
        this.o.put("dance", Integer.valueOf(0));
        this.o.put("rock", Integer.valueOf(0));
        this.o.put("pop", Integer.valueOf(0));
        this.o.put("edm", Integer.valueOf(0));
        this.o.put("indipop", Integer.valueOf(0));
        this.o.put("retro", Integer.valueOf(0));
        this.p.put("70s", Integer.valueOf(0));
        this.p.put("80s", Integer.valueOf(0));
        this.p.put("90s", Integer.valueOf(0));
        this.p.put("2000s", Integer.valueOf(0));
        this.p.put("2010s", Integer.valueOf(0));
        this.q.add(this.mContext.getString(R.string.playlist_suggestion_1));
        this.q.add(this.mContext.getString(R.string.playlist_suggestion_2));
        this.q.add(this.mContext.getString(R.string.playlist_suggestion_3));
    }

    public static BaseGaanaFragment a(String str, boolean z) {
        CreateNewPlaylistFragment createNewPlaylistFragment = new CreateNewPlaylistFragment();
        Bundle bundle = new Bundle();
        if (!TextUtils.isEmpty(str)) {
            bundle.putString("fragment_pop_tag", str);
        }
        bundle.putBoolean("is_blank_playlist", z);
        createNewPlaylistFragment.setArguments(bundle);
        return createNewPlaylistFragment;
    }

    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.containerView == null || this.loginStatus != this.mAppState.getCurrentUser().getLoginStatus()) {
            super.onCreateView(layoutInflater, viewGroup, bundle);
            b();
            if (getArguments() != null) {
                this.k = getArguments().getString("fragment_pop_tag", "");
                this.l = getArguments().getBoolean("is_blank_playlist", false);
            }
            TypedValue typedValue = new TypedValue();
            this.mContext.getTheme().resolveAttribute(R.attr.first_line_color, typedValue, true);
            this.f = typedValue.data;
            this.mContext.getTheme().resolveAttribute(R.attr.second_line_color, typedValue, true);
            this.g = typedValue.data;
            if (this.l) {
                u.a().b("Playlist", "Create Playlist");
            }
        }
        updateView();
        return layoutInflater.inflate(R.layout.fragment_create_new_playlist, viewGroup, false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        super.onViewCreated(view, bundle);
        a(view);
    }

    private void a(View view) {
        ((TextView) view.findViewById(R.id.txt_heading)).setTypeface(i.a(this.mContext.getAssets(), "fonts/Roboto-Medium.ttf"));
        ((TextView) view.findViewById(R.id.txt_create)).setTypeface(i.a(this.mContext.getAssets(), "fonts/Roboto-Medium.ttf"));
        this.a = (EditText) view.findViewById(R.id.edt_playlist_name);
        this.a.setImeOptions(6);
        this.a.setRawInputType(1);
        this.a.setMaxLines(Integer.MAX_VALUE);
        this.a.setText("My Playlist ");
        this.a.setOnEditorActionListener(new OnEditorActionListener() {
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if ((i & 6) == 0) {
                    return false;
                }
                CreateNewPlaylistFragment.this.c();
                return true;
            }
        });
        this.b = (TextView) view.findViewById(R.id.txt_suggestion_1);
        this.b.setOnClickListener(this);
        this.c = (TextView) view.findViewById(R.id.txt_suggestion_2);
        this.c.setOnClickListener(this);
        this.d = (TextView) view.findViewById(R.id.txt_suggestion_3);
        this.d.setOnClickListener(this);
        this.e = (TextView) view.findViewById(R.id.txt_suggestion_4);
        this.e.setOnClickListener(this);
        view.findViewById(R.id.txt_create).setOnClickListener(this);
        view.findViewById(R.id.btnLeft).setOnClickListener(this);
        ((BaseActivity) this.mContext).showProgressDialog();
        d.a(new Runnable() {
            public void run() {
                CreateNewPlaylistFragment.this.m = PlaylistSyncManager.getInstance().nextSimilarPlaylistWithName("My Playlist ");
                ArrayList arrListTracksForPlaylist = GaanaApplication.getInstance().getArrListTracksForPlaylist();
                if (arrListTracksForPlaylist != null) {
                    CharSequence charSequence = "";
                    Iterator it = arrListTracksForPlaylist.iterator();
                    CharSequence charSequence2 = "";
                    int i = 1;
                    int i2 = i;
                    while (it.hasNext()) {
                        Track track = (Track) it.next();
                        if (track.getTags() != null) {
                            for (int i3 = 0; i3 < track.getTags().size(); i3++) {
                                Tags tags = (Tags) track.getTags().get(i3);
                                Integer num = (Integer) CreateNewPlaylistFragment.this.o.get(tags.tag_name.toLowerCase());
                                Integer num2 = (Integer) CreateNewPlaylistFragment.this.p.get(tags.tag_name.toLowerCase());
                                if (num != null) {
                                    CreateNewPlaylistFragment.this.o.put(tags.getEnglishName().toLowerCase(), Integer.valueOf(num.intValue() + 1));
                                }
                                if (num2 != null) {
                                    CreateNewPlaylistFragment.this.p.put(tags.getEnglishName().toLowerCase(), Integer.valueOf(num2.intValue() + 1));
                                }
                            }
                        }
                        if (track.getArtists() != null && track.getArtists().size() > 0) {
                            String englishName = ((Artist) track.getArtists().get(0)).getEnglishName();
                            if (TextUtils.isEmpty(charSequence)) {
                                charSequence = englishName;
                            } else if (!(i2 == 0 || charSequence.equalsIgnoreCase(englishName))) {
                                i2 = 0;
                            }
                        }
                        if (TextUtils.isEmpty(charSequence2)) {
                            charSequence2 = track.getLanguage();
                        } else if (!(i == 0 || charSequence2.equalsIgnoreCase(track.getLanguage()))) {
                            i = 0;
                        }
                    }
                    ArrayList arrayList = new ArrayList(CreateNewPlaylistFragment.this.o.entrySet());
                    Collections.sort(arrayList, CreateNewPlaylistFragment.r);
                    ArrayList arrayList2 = new ArrayList(CreateNewPlaylistFragment.this.p.entrySet());
                    Collections.sort(arrayList2, CreateNewPlaylistFragment.r);
                    Entry entry = (Entry) arrayList.get(arrayList.size() - 1);
                    Entry entry2 = (Entry) arrayList2.get(arrayList2.size() - 1);
                    ArrayList arrayList3 = new ArrayList();
                    if (((Integer) entry.getValue()).intValue() > 0) {
                        arrayList3.add(CreateNewPlaylistFragment.this.mContext.getResources().getString(R.string.my_mood_mix, new Object[]{entry.getKey()}));
                    }
                    if (((Integer) entry2.getValue()).intValue() > 0 && ((Integer) entry2.getValue()).intValue() == arrListTracksForPlaylist.size()) {
                        arrayList3.add(CreateNewPlaylistFragment.this.mContext.getResources().getString(R.string.era_hits, new Object[]{entry2.getKey()}));
                    }
                    if (!(i == 0 || TextUtils.isEmpty(charSequence2))) {
                        arrayList3.add(CreateNewPlaylistFragment.this.mContext.getResources().getString(R.string.top_language_songs, new Object[]{charSequence2}));
                    }
                    if (!(i2 == 0 || TextUtils.isEmpty(charSequence))) {
                        arrayList3.add(CreateNewPlaylistFragment.this.mContext.getResources().getString(R.string.artist_non_stop, new Object[]{charSequence}));
                    }
                    if (arrayList3.size() > 0) {
                        CreateNewPlaylistFragment.this.q.addAll(0, arrayList3);
                    }
                }
                CreateNewPlaylistFragment.this.n.post(new Runnable() {
                    public void run() {
                        if (CreateNewPlaylistFragment.this.isAdded()) {
                            ((BaseActivity) CreateNewPlaylistFragment.this.mContext).hideProgressDialog();
                            CreateNewPlaylistFragment.this.b.setText(Util.d((String) CreateNewPlaylistFragment.this.q.get(0)));
                            CreateNewPlaylistFragment.this.c.setText(Util.d((String) CreateNewPlaylistFragment.this.q.get(1)));
                            CreateNewPlaylistFragment.this.d.setText(Util.d((String) CreateNewPlaylistFragment.this.q.get(2)));
                            if (CreateNewPlaylistFragment.this.q.size() > 3) {
                                CreateNewPlaylistFragment.this.e.setText(Util.d((String) CreateNewPlaylistFragment.this.q.get(3)));
                                CreateNewPlaylistFragment.this.e.setVisibility(0);
                            } else {
                                CreateNewPlaylistFragment.this.e.setVisibility(8);
                            }
                            CreateNewPlaylistFragment.this.a.setText(CreateNewPlaylistFragment.this.m, BufferType.SPANNABLE);
                            CreateNewPlaylistFragment.this.a.requestFocus();
                            CreateNewPlaylistFragment.this.a.setSelection(0, CreateNewPlaylistFragment.this.a.getText().length());
                            Util.b(CreateNewPlaylistFragment.this.mContext, CreateNewPlaylistFragment.this.a);
                        }
                    }
                });
            }
        });
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.btnLeft) {
            u.a().b("Create Playlist Screen", "Close");
            ((GaanaActivity) this.mContext).popBackStackImmediate();
        } else if (id != R.id.txt_create) {
            u.a().b("Create Playlist Screen", "Suggested Playlist Click");
            b(view);
        } else {
            u.a().b("Create Playlist Screen", "Create");
            c();
        }
    }

    private void b(View view) {
        if (view.getId() == this.b.getId()) {
            this.b.setTextColor(this.f);
            this.c.setTextColor(this.g);
            this.d.setTextColor(this.g);
            this.e.setTextColor(this.g);
            this.a.setText(this.b.getText());
        } else if (view.getId() == this.c.getId()) {
            this.c.setTextColor(this.f);
            this.b.setTextColor(this.g);
            this.d.setTextColor(this.g);
            this.e.setTextColor(this.g);
            this.a.setText(this.c.getText());
        } else if (view.getId() == this.d.getId()) {
            this.d.setTextColor(this.f);
            this.b.setTextColor(this.g);
            this.c.setTextColor(this.g);
            this.e.setTextColor(this.g);
            this.a.setText(this.d.getText());
        } else if (view.getId() == this.e.getId()) {
            this.e.setTextColor(this.f);
            this.b.setTextColor(this.g);
            this.c.setTextColor(this.g);
            this.d.setTextColor(this.g);
            this.a.setText(this.e.getText());
        }
        this.a.requestFocus();
        this.a.setSelection(this.a.getText().length());
        Util.b(this.mContext, this.a);
    }

    private void c() {
        if (TextUtils.isEmpty(this.a.getText())) {
            aj.a().a(this.mContext, this.mContext.getString(R.string.enter_playlist_name));
        } else if (this.l) {
            a(this.mContext, this.a);
        } else {
            Playlist playlistFromName = PlaylistSyncManager.getInstance().getPlaylistFromName(this.a.getText().toString());
            if (playlistFromName != null) {
                a(playlistFromName);
            } else {
                a(this.mContext, this.a);
            }
        }
    }

    private void a(final Context context, View view) {
        Util.a(context, view);
        ((InputMethodManager) context.getSystemService("input_method")).hideSoftInputFromWindow(this.a.getWindowToken(), 0);
        if (o.a(this.a).booleanValue()) {
            aj.a().a(context, context.getString(R.string.select_or_create_new_playlist));
        } else if (a(this.a.getText().toString())) {
            aj.a().a(context, context.getString(R.string.special_characters_not_allowed_in_playlist));
        } else {
            this.i = new Playlist();
            if (this.l) {
                ((BaseActivity) context).showProgressDialog(Boolean.valueOf(true), context.getString(R.string.loading));
            } else {
                ((BaseActivity) context).showProgressDialog(Boolean.valueOf(true), context.getString(R.string.adding_to_playlist_text));
            }
            h.a().a(new TaskListner() {
                public void onBackGroundTaskCompleted() {
                    ((BaseActivity) context).hideProgressDialog();
                    switch (AnonymousClass6.a[CreateNewPlaylistFragment.this.h.ordinal()]) {
                        case 1:
                            aj.a().a(context, context.getResources().getString(R.string.Add_TO_PLAYLIST_SUCCESS_MSG));
                            break;
                        case 2:
                            aj.a().a(context, context.getResources().getString(R.string.Add_TO_PLAYLIST_FAILURE_MSG));
                            break;
                        case 3:
                            aj.a().a(context, context.getString(R.string.playlist_already_created));
                            break;
                    }
                    CreateNewPlaylistFragment.this.mAppState.setArrListTracksForPlaylist(null);
                    com.managers.o.a().c("https://api.gaana.com/user.php?type=myplaylists&subtype=myplaylist_favorites");
                    try {
                        if (CreateNewPlaylistFragment.this.h == PLAYLIST_STATUS.SUCCESS) {
                            LoginManager.getInstance().getTimesPointLogger().a("act6222361", CreateNewPlaylistFragment.this.i.getBusinessObjId(), CreateNewPlaylistFragment.this.i.getBusinessObjId(), null);
                        }
                        if (CreateNewPlaylistFragment.this.h != PLAYLIST_STATUS.PLAYLIST_ALREADY_ADDED) {
                            ((GaanaActivity) context).popBackStackImmediate(CreateNewPlaylistFragment.this.k, 1);
                        }
                        if (CreateNewPlaylistFragment.this.l && CreateNewPlaylistFragment.this.i.getBusinessObjId() != null) {
                            af.a(context, ((GaanaActivity) context).getCurrentFragment()).a((int) R.id.addMoreSongs, CreateNewPlaylistFragment.this.i, false);
                        }
                    } catch (Exception unused) {
                    }
                }

                public void doBackGroundTask() {
                    if (CreateNewPlaylistFragment.this.l) {
                        CreateNewPlaylistFragment.this.mAppState.setArrListTracksForPlaylist(null);
                    }
                    ArrayList arrListTracksForPlaylist = CreateNewPlaylistFragment.this.mAppState.getArrListTracksForPlaylist();
                    if (arrListTracksForPlaylist != null) {
                        CreateNewPlaylistFragment.this.h = PlaylistSyncManager.getInstance().createPlaylist(CreateNewPlaylistFragment.this.i, (Activity) context, CreateNewPlaylistFragment.this.a.getText().toString(), arrListTracksForPlaylist, CreateNewPlaylistFragment.this.l);
                    } else {
                        CreateNewPlaylistFragment.this.h = PLAYLIST_STATUS.FAILED;
                    }
                }
            }, -1);
        }
    }

    private boolean a(String str) {
        return Pattern.compile("[^a-z0-9 ]", 2).matcher(str).find();
    }

    private void a(final Playlist playlist) {
        final ArrayList arrListTracksForPlaylist = this.mAppState.getArrListTracksForPlaylist();
        Util.a(this.mContext, this.a);
        if (playlist == null) {
            aj.a().a(this.mContext, this.mContext.getString(R.string.select_or_create_new_playlist));
            return;
        }
        ((BaseActivity) this.mContext).showProgressDialog(Boolean.valueOf(false), this.mContext.getString(R.string.updating_text));
        h.a().a(new TaskListner() {
            public void onBackGroundTaskCompleted() {
                if (CreateNewPlaylistFragment.this.mContext != null) {
                    ((BaseActivity) CreateNewPlaylistFragment.this.mContext).hideProgressDialog();
                }
                CreateNewPlaylistFragment.this.mAppState.setArrListPlaylist(null);
                CreateNewPlaylistFragment.this.mAppState.setArrListTracksForPlaylist(null);
                CreateNewPlaylistFragment.this.d();
                com.managers.o a = com.managers.o.a();
                String[] strArr = new String[2];
                strArr[0] = "type=playlist&subtype=playlist_detail";
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("playlist_id=");
                stringBuilder.append(playlist.getBusinessObjId());
                strArr[1] = stringBuilder.toString();
                a.a(strArr);
                if (ap.a().o()) {
                    int a2 = Util.a(playlist.getBusinessObjId());
                    if (!(a2 == 0 || !DownloadManager.c().b(playlist).booleanValue() || arrListTracksForPlaylist == null)) {
                        DownloadManager.c().b(arrListTracksForPlaylist, a2, true);
                    }
                }
                if (CreateNewPlaylistFragment.this.j == PLAYLIST_STATUS.SUCCESS) {
                    PlaylistSyncManager.getInstance().updatePlaylistMemCache(Util.a(playlist.getBusinessObjId()));
                }
            }

            public void doBackGroundTask() {
                if (arrListTracksForPlaylist != null) {
                    String[] strArr = new String[arrListTracksForPlaylist.size()];
                    for (int i = 0; i < arrListTracksForPlaylist.size(); i++) {
                        strArr[i] = ((Track) arrListTracksForPlaylist.get(i)).getBusinessObjId();
                    }
                    CreateNewPlaylistFragment.this.j = PlaylistSyncManager.getInstance().addToPlaylist((Activity) CreateNewPlaylistFragment.this.mContext, playlist, arrListTracksForPlaylist);
                    return;
                }
                CreateNewPlaylistFragment.this.j = PLAYLIST_STATUS.FAILED;
            }
        }, -1);
    }

    private void d() {
        String str = "";
        switch (this.j) {
            case SUCCESS:
                str = this.mContext.getString(R.string.songs_added_to_playlist);
                break;
            case FAILED:
                str = this.mContext.getString(R.string.songs_add_failed);
                break;
            case ALREADY_ADDED:
                str = this.mContext.getString(R.string.songs_already_in_playlist);
                break;
            case PARTIALY_ADDED:
                str = this.mContext.getString(R.string.songs_added_once);
                break;
        }
        aj.a().a(this.mContext, str);
        try {
            ((GaanaActivity) this.mContext).popBackStackImmediate(this.k, 1);
        } catch (Exception unused) {
        }
    }

    public void onDestroyView() {
        this.n.removeCallbacks(null);
        super.onDestroyView();
    }
}
