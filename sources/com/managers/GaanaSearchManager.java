package com.managers;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import com.android.volley.Request.Priority;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.constants.Constants;
import com.fragments.ListingFragment;
import com.fragments.SearchEnchancedFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.adapter.NextGenAutoSuggestAdapter;
import com.gaana.analytics.AppsFlyer;
import com.gaana.application.GaanaApplication;
import com.gaana.models.BusinessObject;
import com.gaana.models.Languages.Language;
import com.gaana.models.NextGenSearchAutoSuggests;
import com.gaana.models.NextGenSearchAutoSuggests.AutoComplete;
import com.gaana.models.RecentSearches;
import com.gaana.models.SearchData;
import com.gaana.models.SearchData.SearchEvents;
import com.google.android.gms.actions.SearchIntents;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.google.gson.GsonBuilder;
import com.i.i;
import com.i.j;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSessionContext;
import com.integralads.avid.library.inmobi.utils.AvidJSONUtil;
import com.logging.GaanaLogger.SOURCE_TYPE;
import com.managers.URLManager.BusinessObjectType;
import com.moengage.ActionMapperConstants;
import com.services.d;
import com.services.l.af;
import com.services.n;
import com.utilities.Util;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GaanaSearchManager {
    private static GaanaSearchManager b;
    private int A;
    private long B;
    private NextGenAutoSuggestAdapter C;
    private boolean D = false;
    boolean a = false;
    private d c = d.a();
    private RecentSearches d;
    private String e = "Track";
    private b f;
    private long g = 0;
    private long h = 0;
    private long i = 0;
    private String j = null;
    private a k;
    private SearchType l = SearchType.Generic;
    private boolean m = false;
    private String n = null;
    private int o;
    private ArrayList<Language> p = null;
    private boolean q = true;
    private boolean r = true;
    private boolean s = false;
    private String t = "0";
    private boolean u = false;
    private boolean v = false;
    private boolean w = false;
    private String x = "";
    private boolean y = false;
    private boolean z = false;

    public enum ACTION_DETAILS {
        ZERO,
        STARTED_TYPING,
        RECENT_SEARCH,
        TRENDING_SEARCH,
        RESULT,
        MORE_OPTIONS,
        VIEW_ALL_RESULT,
        VIEW_ALL_MORE_OPTIONS,
        RECENT_SEARCH_ITEM,
        SEARCH_QUERY,
        VOICE_SEARCH,
        VOICE_SEARCH_TAP,
        NULL_RESULT,
        SEARCH_CROSS_TAP,
        SEARCH_BACK_TAP
    }

    public enum ACTION_TYPE {
        ZERO,
        SEARCH_BEGIN,
        SEARCH_TAP,
        FIRST_TAP,
        OTHER_TAP,
        NETWORK_FAILURE,
        SEARCH_EXIT,
        VS_BEGIN,
        VS_FIRST_TAP,
        VS_OTHER_TAP,
        VS_EXIT,
        SEARCH_RESULT,
        SEARCH_CANCEL,
        PLAY
    }

    public enum MY_MUSIC_SEARCH_TYPE {
        ONLINE,
        DOWNLOADS,
        LOCAL,
        MY_PLAYLISTS
    }

    public enum SearchType {
        Generic,
        Radio,
        Playlist_Search,
        OnlySongs
    }

    private class a {
        private String b;
        private boolean c;

        private a() {
            this.b = "";
            this.c = false;
        }

        /* synthetic */ a(GaanaSearchManager gaanaSearchManager, AnonymousClass1 anonymousClass1) {
            this();
        }

        /* Access modifiers changed, original: protected */
        public void a(final Activity activity, final String str) {
            this.c = false;
            GaanaSearchManager.this.a = false;
            String trim = str.trim();
            GaanaSearchManager.this.D = true;
            final NextGenAutoSuggestAdapter nextGenAutoSuggestAdapter = new NextGenAutoSuggestAdapter(activity, str);
            NextGenSearchAutoSuggests nextGenSearchAutoSuggests = new NextGenSearchAutoSuggests();
            nextGenSearchAutoSuggests.setGroupItems(new ArrayList());
            nextGenAutoSuggestAdapter.setSearchSuggestions(nextGenSearchAutoSuggests, false);
            nextGenAutoSuggestAdapter.setSearchType(GaanaSearchManager.this.l);
            if (GaanaSearchManager.this.f != null) {
                GaanaSearchManager.this.f.a(nextGenAutoSuggestAdapter);
            }
            this.b = str;
            GaanaSearchManager.this.n = str;
            if (GaanaSearchManager.this.f != null) {
                GaanaSearchManager.this.f.a(true, GaanaSearchManager.this.m);
            }
            if (GaanaSearchManager.this.f instanceof SearchEnchancedFragment) {
                a(nextGenAutoSuggestAdapter, activity, MY_MUSIC_SEARCH_TYPE.DOWNLOADS, str);
            }
            if (!GaanaSearchManager.this.m) {
                if (GaanaApplication.getInstance().isAppInOfflineMode() || !Util.j((Context) activity)) {
                    if (!(GaanaSearchManager.this.f == null || (GaanaSearchManager.this.f instanceof SearchEnchancedFragment))) {
                        GaanaSearchManager.this.f.a(false, false);
                        GaanaSearchManager.this.f.a((Context) activity);
                        ap.a().f((Context) activity);
                    }
                    return;
                }
                String str2 = (!Constants.at || Constants.bV.equalsIgnoreCase("English") || GaanaSearchManager.this.y) ? Constants.at ? "https://gsearch.gaana.com/gaanasearch-api/mobilesuggest/autosuggest-lite-vs1?" : (TextUtils.isEmpty(Constants.bV) || Constants.bV.equalsIgnoreCase("English")) ? "https://gsearch.gaana.com/gaanasearch-api/mobilesuggest/autosuggest-lite-vltr-ro?" : "https://gsearch.gaana.com/gaanasearch-api/mobilesuggest/autosuggest-lite-vltr-ro?" : "https://tsearch.gaana.com/gaanasearch-api/mobilesuggest/autosuggest-lite-vs1?";
                Map hashMap = new HashMap();
                hashMap.put("geoLocation", Constants.ct);
                hashMap.put(SearchIntents.EXTRA_QUERY, trim);
                hashMap.put("content_filter", InternalAvidAdSessionContext.AVID_API_LEVEL);
                if (GaanaSearchManager.this.l == SearchType.Radio) {
                    hashMap.put("include", "track,artist");
                } else if (GaanaSearchManager.this.l == SearchType.Playlist_Search) {
                    hashMap.put("include", "track,playlist,album");
                } else if (GaanaSearchManager.this.l == SearchType.OnlySongs) {
                    hashMap.put("include", ActionMapperConstants.KEY_TRACK);
                } else {
                    hashMap.put("include", "allItems");
                }
                if (!TextUtils.isEmpty(GaanaSearchManager.this.d())) {
                    hashMap.put("usrLang", GaanaSearchManager.this.d());
                }
                hashMap.put("isRegSrch", GaanaSearchManager.this.t);
                hashMap.put("sid", String.valueOf(GaanaSearchManager.f(GaanaSearchManager.this)));
                hashMap.put("said", String.valueOf(GaanaSearchManager.this.A));
                hashMap.put("ssid", String.valueOf(GaanaSearchManager.this.B));
                if (GaanaApplication.getInstance().getCurrentUser().getLoginStatus()) {
                    if (ap.a().e()) {
                        hashMap.put("UserType", InternalAvidAdSessionContext.AVID_API_LEVEL);
                    } else if (ap.a().i()) {
                        hashMap.put("UserType", "1");
                    } else {
                        hashMap.put("UserType", "0");
                    }
                }
                GaanaSearchManager.this.g = Calendar.getInstance().getTimeInMillis();
                com.i.b bVar = new com.i.b(str2, NextGenSearchAutoSuggests.class, new com.i.e.a() {
                    public void onDataRetrieved(Object obj, boolean z) {
                        if (GaanaSearchManager.this.f != null) {
                            GaanaSearchManager.this.f.a(false, false);
                        }
                        if (obj instanceof NextGenSearchAutoSuggests) {
                            a.this.a(activity, (NextGenSearchAutoSuggests) obj, nextGenAutoSuggestAdapter);
                        }
                        if (GaanaSearchManager.this.f instanceof SearchEnchancedFragment) {
                            a.this.a(nextGenAutoSuggestAdapter, activity, MY_MUSIC_SEARCH_TYPE.LOCAL, str);
                        }
                    }

                    public void onErrorResponse(BusinessObject businessObject) {
                        if (GaanaSearchManager.this.f != null && !(GaanaSearchManager.this.f instanceof SearchEnchancedFragment)) {
                            GaanaSearchManager.this.f.a(false, false);
                            GaanaSearchManager.this.f.a(activity);
                            ap.a().a(activity, activity.getString(R.string.error_generic_unableto_process));
                        } else if (GaanaSearchManager.this.f instanceof SearchEnchancedFragment) {
                            GaanaSearchManager.this.a = true;
                            a.this.a(nextGenAutoSuggestAdapter, activity, MY_MUSIC_SEARCH_TYPE.LOCAL, str);
                        }
                        if (businessObject != null && businessObject.getVolleyError() != null) {
                            VolleyError volleyError = businessObject.getVolleyError();
                            if (!(volleyError instanceof ServerError) || volleyError.a == null) {
                                GaanaSearchManager.a().a(ACTION_TYPE.NETWORK_FAILURE.ordinal(), 0, 0, "", 0, volleyError.getMessage());
                                GaanaSearchManager.this.y = false;
                                return;
                            }
                            GaanaSearchManager.a().a(ACTION_TYPE.NETWORK_FAILURE.ordinal(), 0, 0, "", 0, String.valueOf(volleyError.a.a));
                        }
                    }
                });
                bVar.a(hashMap);
                bVar.a("search_autosuggest");
                bVar.a(Priority.IMMEDIATE);
                bVar.c(true);
                bVar.d(true);
                j.a().a((Object) "search_autosuggest");
                i.a().a(bVar);
            }
        }

        /* Access modifiers changed, original: protected */
        public void a(boolean z, Activity activity) {
            this.c = z;
            if (z && !GaanaSearchManager.this.m) {
                GaanaSearchManager.this.g = 0;
                j.a().a((Object) "search_autosuggest");
            }
        }

        private void a(Activity activity, NextGenSearchAutoSuggests nextGenSearchAutoSuggests, NextGenAutoSuggestAdapter nextGenAutoSuggestAdapter) {
            if (nextGenSearchAutoSuggests != null) {
                GaanaSearchManager.this.e = nextGenSearchAutoSuggests.getTopFacets();
                a(nextGenAutoSuggestAdapter, nextGenSearchAutoSuggests, activity, MY_MUSIC_SEARCH_TYPE.ONLINE);
            }
        }

        private void a(NextGenAutoSuggestAdapter nextGenAutoSuggestAdapter, NextGenSearchAutoSuggests nextGenSearchAutoSuggests, Activity activity, MY_MUSIC_SEARCH_TYPE my_music_search_type) {
            boolean z = false;
            Constants.dn = false;
            GaanaSearchManager.this.n = this.b;
            boolean z2 = my_music_search_type == MY_MUSIC_SEARCH_TYPE.ONLINE;
            nextGenAutoSuggestAdapter.setSearchSuggestions(nextGenSearchAutoSuggests, z2);
            nextGenAutoSuggestAdapter.setSearchType(GaanaSearchManager.this.l);
            if (GaanaSearchManager.this.f != null) {
                GaanaSearchManager.this.f.a(nextGenAutoSuggestAdapter);
            }
            if (GaanaSearchManager.this.f instanceof SearchEnchancedFragment) {
                if (z2) {
                    long timeInMillis = Calendar.getInstance().getTimeInMillis();
                    GaanaSearchManager.this.i = timeInMillis;
                    if (GaanaSearchManager.this.g != 0) {
                        Constants.a("Load", timeInMillis - GaanaSearchManager.this.g, "Search", "Autosuggest");
                        GaanaSearchManager.this.g = 0;
                    }
                }
                if (GaanaApplication.getInstance().isAppInOfflineMode() || !Util.j((Context) activity)) {
                    z = true;
                }
                if (my_music_search_type != MY_MUSIC_SEARCH_TYPE.DOWNLOADS && ((!z2 || !z) && (z2 || z))) {
                    String str = "Online-Autosuggest";
                    if (z) {
                        str = "Offline-Autosuggest";
                    }
                    CharSequence charSequence = "";
                    if (nextGenAutoSuggestAdapter.getItemCount() <= 0) {
                        charSequence = "Search_no result";
                        if (GaanaSearchManager.this.z) {
                            charSequence = "Voice_no result";
                        } else if (Constants.au) {
                            charSequence = "VoiceUI_no result";
                        }
                        GaanaSearchManager.this.a(ACTION_TYPE.SEARCH_RESULT.ordinal(), ACTION_DETAILS.NULL_RESULT.ordinal(), 0, "", 0, "");
                    }
                    if (!TextUtils.isEmpty(charSequence)) {
                        ((BaseActivity) activity).sendGAEvent(str, charSequence, this.b);
                    }
                } else {
                    return;
                }
            }
            if (!(nextGenSearchAutoSuggests == null || nextGenSearchAutoSuggests.getArrListBusinessObj() == null || nextGenSearchAutoSuggests.getArrListBusinessObj().size() <= 0)) {
                nextGenAutoSuggestAdapter.notifyDataSetChanged();
            }
        }

        private void a(NextGenAutoSuggestAdapter nextGenAutoSuggestAdapter, Activity activity, MY_MUSIC_SEARCH_TYPE my_music_search_type, String str) {
            final MY_MUSIC_SEARCH_TYPE my_music_search_type2 = my_music_search_type;
            final Activity activity2 = activity;
            final String str2 = str;
            final NextGenAutoSuggestAdapter nextGenAutoSuggestAdapter2 = nextGenAutoSuggestAdapter;
            com.i.d.a(new Runnable() {
                /* JADX WARNING: Missing block: B:69:0x0275, code skipped:
            if (r6.getAlbumRawName().toUpperCase().contains(r4.trim().toUpperCase()) != false) goto L_0x0295;
     */
                public void run() {
                    /*
                    r11 = this;
                    r0 = new java.util.ArrayList;
                    r0.<init>();
                    r1 = r2;
                    r2 = com.managers.GaanaSearchManager.MY_MUSIC_SEARCH_TYPE.DOWNLOADS;
                    r3 = 0;
                    r4 = 0;
                    if (r1 != r2) goto L_0x006d;
                L_0x000d:
                    r5 = com.managers.DownloadManager.c();
                    r6 = 0;
                    r7 = 1;
                    r8 = 0;
                    r9 = -1;
                    r10 = -1;
                    r1 = r5.a(r6, r7, r8, r9, r10);
                    r2 = com.managers.DownloadManager.c();
                    r2 = r2.g(r4);
                    r5 = com.managers.DownloadManager.c();
                    r5 = r5.f(r4);
                    r1 = r1.getArrListBusinessObj();
                    r0.addAll(r1);
                    r1 = r2.getArrListBusinessObj();
                    r0.addAll(r1);
                    r1 = r5.getArrListBusinessObj();
                    r1 = r1.iterator();
                L_0x0040:
                    r2 = r1.hasNext();
                    if (r2 == 0) goto L_0x0061;
                L_0x0046:
                    r2 = r1.next();
                    r2 = (com.gaana.models.BusinessObject) r2;
                    r5 = r2 instanceof com.gaana.models.Playlists.Playlist;
                    if (r5 == 0) goto L_0x0040;
                L_0x0050:
                    r5 = com.gaana.localmedia.PlaylistSyncManager.getInstance();
                    r6 = r2;
                    r6 = (com.gaana.models.Playlists.Playlist) r6;
                    r5 = r5.isMyPlaylist(r6);
                    if (r5 != 0) goto L_0x0040;
                L_0x005d:
                    r0.add(r2);
                    goto L_0x0040;
                L_0x0061:
                    r1 = com.e.a.f.a();
                    r1 = r1.c();
                    r0.addAll(r1);
                    goto L_0x00c5;
                L_0x006d:
                    r1 = r2;
                    r2 = com.managers.GaanaSearchManager.MY_MUSIC_SEARCH_TYPE.LOCAL;
                    if (r1 != r2) goto L_0x00b4;
                L_0x0073:
                    r1 = r3;
                    r1 = com.gaana.localmedia.LocalMediaManager.getInstance(r1);
                    r1 = r1.getLocalSongs(r4, r3);
                    r2 = r3;
                    r2 = com.gaana.localmedia.LocalMediaManager.getInstance(r2);
                    r2 = r2.getLocalAlbums(r4, r3);
                    r5 = r3;
                    r5 = com.gaana.localmedia.LocalMediaManager.getInstance(r5);
                    r5 = r5.getLocalArtists(r4, r3);
                    r6 = r3;
                    r6 = com.gaana.localmedia.LocalMediaManager.getInstance(r6);
                    r6 = r6.getMediaStorePlaylists(r4, r4);
                    r1 = r1.getArrListBusinessObj();
                    r0.addAll(r1);
                    r1 = r2.getArrListBusinessObj();
                    r0.addAll(r1);
                    r1 = r5.getArrListBusinessObj();
                    r0.addAll(r1);
                    r0.addAll(r6);
                    goto L_0x00c5;
                L_0x00b4:
                    r1 = r2;
                    r2 = com.managers.GaanaSearchManager.MY_MUSIC_SEARCH_TYPE.MY_PLAYLISTS;
                    if (r1 != r2) goto L_0x00c5;
                L_0x00ba:
                    r1 = com.gaana.localmedia.PlaylistSyncManager.getInstance();
                    r1 = r1.getMyPlaylistsFromDb(r3);
                    r0.addAll(r1);
                L_0x00c5:
                    r1 = new java.util.ArrayList;
                    r1.<init>();
                    r2 = r3;
                L_0x00cb:
                    r5 = r0.size();
                    if (r2 >= r5) goto L_0x02df;
                L_0x00d1:
                    r5 = r0.get(r2);
                    r5 = (com.gaana.models.BusinessObject) r5;
                    if (r5 == 0) goto L_0x02db;
                L_0x00d9:
                    r6 = com.managers.GaanaSearchManager.AnonymousClass2.a;
                    r7 = r5.getBusinessObjType();
                    r7 = r7.ordinal();
                    r6 = r6[r7];
                    switch(r6) {
                        case 1: goto L_0x0238;
                        case 2: goto L_0x01b1;
                        case 3: goto L_0x0138;
                        case 4: goto L_0x00ea;
                        default: goto L_0x00e8;
                    };
                L_0x00e8:
                    goto L_0x02db;
                L_0x00ea:
                    r6 = r5.getRawName();
                    if (r6 == 0) goto L_0x02db;
                L_0x00f0:
                    r6 = r5.getRawName();
                    r6 = r6.toUpperCase();
                    r7 = r4;
                    r7 = r7.trim();
                    r7 = r7.toUpperCase();
                    r6 = r6.contains(r7);
                    if (r6 == 0) goto L_0x02db;
                L_0x0108:
                    r6 = new com.gaana.models.NextGenSearchAutoSuggests$AutoComplete;
                    r7 = r5.getName();
                    r8 = "Artist";
                    r9 = r5.getBusinessObjId();
                    r9 = java.lang.Integer.parseInt(r9);
                    r10 = r5;
                    r10 = (com.gaana.models.Artists.Artist) r10;
                    r10 = r10.getArtwork();
                    r6.<init>(r7, r8, r9, r10);
                    r7 = "Artist";
                    r6.setType(r7);
                    r7 = "LOCAL_MUSIC";
                    r6.setSectionType(r7);
                    r5 = r5.isLocalMedia();
                    r6.setLocalMedia(r5);
                    r1.add(r6);
                    goto L_0x02db;
                L_0x0138:
                    r6 = r5.getRawName();
                    if (r6 == 0) goto L_0x02db;
                L_0x013e:
                    r6 = r5.getRawName();
                    r6 = r6.toUpperCase();
                    r7 = r4;
                    r7 = r7.trim();
                    r7 = r7.toUpperCase();
                    r6 = r6.contains(r7);
                    if (r6 == 0) goto L_0x02db;
                L_0x0156:
                    r6 = "Playlist";
                    r7 = r5 instanceof com.gaana.models.Playlists.Playlist;
                    if (r7 == 0) goto L_0x016b;
                L_0x015c:
                    r7 = com.gaana.localmedia.PlaylistSyncManager.getInstance();
                    r8 = r5;
                    r8 = (com.gaana.models.Playlists.Playlist) r8;
                    r7 = r7.isMyPlaylist(r8);
                    if (r7 == 0) goto L_0x016b;
                L_0x0169:
                    r6 = "My Playlist";
                L_0x016b:
                    r7 = new com.gaana.models.NextGenSearchAutoSuggests$AutoComplete;
                    r8 = r5.getName();
                    r9 = r5.getBusinessObjId();
                    r9 = java.lang.Integer.parseInt(r9);
                    r10 = r5;
                    r10 = (com.gaana.models.Playlists.Playlist) r10;
                    r10 = r10.getArtwork();
                    r7.<init>(r8, r6, r9, r10);
                    r6 = "Playlist";
                    r7.setType(r6);
                    r6 = r2;
                    r8 = com.managers.GaanaSearchManager.MY_MUSIC_SEARCH_TYPE.DOWNLOADS;
                    if (r6 != r8) goto L_0x0194;
                L_0x018e:
                    r6 = "MY_DOWNLOADS";
                    r7.setSectionType(r6);
                    goto L_0x01a5;
                L_0x0194:
                    r6 = r2;
                    r8 = com.managers.GaanaSearchManager.MY_MUSIC_SEARCH_TYPE.MY_PLAYLISTS;
                    if (r6 != r8) goto L_0x01a0;
                L_0x019a:
                    r6 = "MY_PLAYLISTS";
                    r7.setSectionType(r6);
                    goto L_0x01a5;
                L_0x01a0:
                    r6 = "LOCAL_MUSIC";
                    r7.setSectionType(r6);
                L_0x01a5:
                    r5 = r5.isLocalMedia();
                    r7.setLocalMedia(r5);
                    r1.add(r7);
                    goto L_0x02db;
                L_0x01b1:
                    r6 = r5.getRawName();
                    if (r6 == 0) goto L_0x01cf;
                L_0x01b7:
                    r6 = r5.getRawName();
                    r6 = r6.toUpperCase();
                    r7 = r4;
                    r7 = r7.trim();
                    r7 = r7.toUpperCase();
                    r6 = r6.contains(r7);
                    if (r6 != 0) goto L_0x01f0;
                L_0x01cf:
                    r6 = r5;
                    r6 = (com.gaana.models.Albums.Album) r6;
                    r7 = r6.getRawArtistNames();
                    if (r7 == 0) goto L_0x02db;
                L_0x01d8:
                    r6 = r6.getRawArtistNames();
                    r6 = r6.toUpperCase();
                    r7 = r4;
                    r7 = r7.trim();
                    r7 = r7.toUpperCase();
                    r6 = r6.contains(r7);
                    if (r6 == 0) goto L_0x02db;
                L_0x01f0:
                    r6 = new com.gaana.models.NextGenSearchAutoSuggests$AutoComplete;
                    r7 = r5.getName();
                    r8 = "Album";
                    r9 = r5.getBusinessObjId();
                    r9 = java.lang.Integer.parseInt(r9);
                    r10 = r5;
                    r10 = (com.gaana.models.Albums.Album) r10;
                    r10 = r10.getArtwork();
                    r6.<init>(r7, r8, r9, r10);
                    r7 = "Album";
                    r6.setType(r7);
                    r7 = r2;
                    r8 = com.managers.GaanaSearchManager.MY_MUSIC_SEARCH_TYPE.DOWNLOADS;
                    if (r7 != r8) goto L_0x021b;
                L_0x0215:
                    r7 = "MY_DOWNLOADS";
                    r6.setSectionType(r7);
                    goto L_0x022c;
                L_0x021b:
                    r7 = r2;
                    r8 = com.managers.GaanaSearchManager.MY_MUSIC_SEARCH_TYPE.MY_PLAYLISTS;
                    if (r7 != r8) goto L_0x0227;
                L_0x0221:
                    r7 = "MY_DOWNLOADS";
                    r6.setSectionType(r7);
                    goto L_0x022c;
                L_0x0227:
                    r7 = "LOCAL_MUSIC";
                    r6.setSectionType(r7);
                L_0x022c:
                    r5 = r5.isLocalMedia();
                    r6.setLocalMedia(r5);
                    r1.add(r6);
                    goto L_0x02db;
                L_0x0238:
                    r6 = r5.getRawName();
                    if (r6 == 0) goto L_0x0256;
                L_0x023e:
                    r6 = r5.getRawName();
                    r6 = r6.toUpperCase();
                    r7 = r4;
                    r7 = r7.trim();
                    r7 = r7.toUpperCase();
                    r6 = r6.contains(r7);
                    if (r6 != 0) goto L_0x0295;
                L_0x0256:
                    r6 = r5;
                    r6 = (com.gaana.models.OfflineTrack) r6;
                    r7 = r6.getAlbumRawName();
                    if (r7 == 0) goto L_0x0277;
                L_0x025f:
                    r7 = r6.getAlbumRawName();
                    r7 = r7.toUpperCase();
                    r8 = r4;
                    r8 = r8.trim();
                    r8 = r8.toUpperCase();
                    r7 = r7.contains(r8);
                    if (r7 != 0) goto L_0x0295;
                L_0x0277:
                    r7 = r6.getArtistRawName();
                    if (r7 == 0) goto L_0x02db;
                L_0x027d:
                    r6 = r6.getArtistRawName();
                    r6 = r6.toUpperCase();
                    r7 = r4;
                    r7 = r7.trim();
                    r7 = r7.toUpperCase();
                    r6 = r6.contains(r7);
                    if (r6 == 0) goto L_0x02db;
                L_0x0295:
                    r6 = new com.gaana.models.NextGenSearchAutoSuggests$AutoComplete;
                    r7 = r5.getName();
                    r8 = "Song";
                    r9 = r5.getBusinessObjId();
                    r9 = java.lang.Integer.parseInt(r9);
                    r10 = r5;
                    r10 = (com.gaana.models.OfflineTrack) r10;
                    r10 = r10.getImageUrl();
                    r6.<init>(r7, r8, r9, r10);
                    r7 = "Track";
                    r6.setType(r7);
                    r7 = r2;
                    r8 = com.managers.GaanaSearchManager.MY_MUSIC_SEARCH_TYPE.DOWNLOADS;
                    if (r7 != r8) goto L_0x02c0;
                L_0x02ba:
                    r7 = "MY_DOWNLOADS";
                    r6.setSectionType(r7);
                    goto L_0x02d1;
                L_0x02c0:
                    r7 = r2;
                    r8 = com.managers.GaanaSearchManager.MY_MUSIC_SEARCH_TYPE.MY_PLAYLISTS;
                    if (r7 != r8) goto L_0x02cc;
                L_0x02c6:
                    r7 = "MY_DOWNLOADS";
                    r6.setSectionType(r7);
                    goto L_0x02d1;
                L_0x02cc:
                    r7 = "LOCAL_MUSIC";
                    r6.setSectionType(r7);
                L_0x02d1:
                    r5 = r5.isLocalMedia();
                    r6.setLocalMedia(r5);
                    r1.add(r6);
                L_0x02db:
                    r2 = r2 + 1;
                    goto L_0x00cb;
                L_0x02df:
                    r0 = r1.size();
                    if (r0 <= 0) goto L_0x0354;
                L_0x02e5:
                    r4 = new com.gaana.models.NextGenSearchAutoSuggests$GroupItem;
                    r4.<init>();
                    r0 = r2;
                    r2 = com.managers.GaanaSearchManager.MY_MUSIC_SEARCH_TYPE.DOWNLOADS;
                    if (r0 != r2) goto L_0x0306;
                L_0x02f0:
                    r0 = "My Music";
                    r4.setType(r0);
                    r0 = r3;
                    r0 = r0.getResources();
                    r2 = 2131821915; // 0x7f11055b float:1.9276587E38 double:1.0532599713E-314;
                    r0 = r0.getString(r2);
                    r4.setDisplayTitle(r0);
                    goto L_0x032d;
                L_0x0306:
                    r0 = r2;
                    r2 = com.managers.GaanaSearchManager.MY_MUSIC_SEARCH_TYPE.LOCAL;
                    if (r0 != r2) goto L_0x0322;
                L_0x030c:
                    r0 = "Local Files";
                    r4.setType(r0);
                    r0 = r3;
                    r0 = r0.getResources();
                    r2 = 2131821773; // 0x7f1104cd float:1.9276299E38 double:1.053259901E-314;
                    r0 = r0.getString(r2);
                    r4.setDisplayTitle(r0);
                    goto L_0x032d;
                L_0x0322:
                    r0 = r2;
                    r2 = com.managers.GaanaSearchManager.MY_MUSIC_SEARCH_TYPE.MY_PLAYLISTS;
                    if (r0 != r2) goto L_0x032d;
                L_0x0328:
                    r0 = "My Playlists";
                    r4.setType(r0);
                L_0x032d:
                    r0 = 1;
                    r4.setLocalMedia(r0);
                    r2 = r1.size();
                    r5 = 10;
                    if (r2 <= r5) goto L_0x0351;
                L_0x0339:
                    r4.setViewAll(r0);
                    r0 = new com.managers.GaanaSearchManager$a$2$1;
                    r0.<init>();
                    java.util.Collections.sort(r1, r0);
                    r0 = new java.util.ArrayList;
                    r1 = r1.subList(r3, r5);
                    r0.<init>(r1);
                    r4.setAutocomplete(r0);
                    goto L_0x0354;
                L_0x0351:
                    r4.setAutocomplete(r1);
                L_0x0354:
                    r0 = new java.util.ArrayList;
                    r0.<init>();
                    if (r4 == 0) goto L_0x035e;
                L_0x035b:
                    r0.add(r4);
                L_0x035e:
                    r1 = new com.gaana.models.NextGenSearchAutoSuggests;
                    r1.<init>();
                    r1.setGroupItems(r0);
                    r0 = r3;
                    r2 = new com.managers.GaanaSearchManager$a$2$2;
                    r2.<init>(r1);
                    r0.runOnUiThread(r2);
                    return;
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.managers.GaanaSearchManager$a$AnonymousClass2.run():void");
                }
            });
        }
    }

    public interface b {
        void a(Context context);

        void a(View view);

        void a(NextGenAutoSuggestAdapter nextGenAutoSuggestAdapter);

        void a(String str, String str2);

        void a(boolean z, boolean z2);

        void e();
    }

    static /* synthetic */ int f(GaanaSearchManager gaanaSearchManager) {
        int i = gaanaSearchManager.o + 1;
        gaanaSearchManager.o = i;
        return i;
    }

    private GaanaSearchManager() {
    }

    public static GaanaSearchManager a() {
        if (b == null) {
            b = new GaanaSearchManager();
        }
        return b;
    }

    public void b() {
        this.d = null;
    }

    public void c() {
        this.o = 0;
        this.A = 0;
        this.B = Calendar.getInstance().getTimeInMillis();
    }

    public void a(ArrayList<Language> arrayList) {
        this.p = arrayList;
    }

    public String d() {
        String str = "";
        if (this.p != null) {
            for (int i = 0; i < this.p.size(); i++) {
                if (((Language) this.p.get(i)).isPrefered() == 1) {
                    if (TextUtils.isEmpty(str)) {
                        str = ((Language) this.p.get(i)).getLanguage();
                    } else {
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append(str);
                        stringBuilder.append(",");
                        stringBuilder.append(((Language) this.p.get(i)).getLanguage());
                        str = stringBuilder.toString();
                    }
                }
            }
        }
        return str;
    }

    public String e() {
        return this.t;
    }

    public RecentSearches f() {
        if (this.d == null) {
            String b = this.c.b("PREFF_RECENT_SEARCHES", null, false);
            if (!TextUtils.isEmpty(b)) {
                this.d = (RecentSearches) n.a(b);
            }
        }
        if (this.d != null) {
            this.d.checkAndRemoveDeletedLocalEntry();
            this.c.a("PREFF_RECENT_SEARCHES", n.a(this.d), false);
        }
        return this.d;
    }

    public void a(AutoComplete autoComplete) {
        if (this.d == null) {
            this.d = new RecentSearches();
        }
        this.d.add(autoComplete);
        this.c.a("PREFF_RECENT_SEARCHES", n.a(this.d), false);
        if (this.f != null) {
            this.f.e();
        }
    }

    public b g() {
        return this.f;
    }

    public void a(b bVar) {
        this.f = bVar;
        if (this.f instanceof SearchEnchancedFragment) {
            this.y = false;
        }
    }

    public SearchType h() {
        return this.l;
    }

    public void a(SearchType searchType) {
        this.l = searchType;
    }

    public boolean i() {
        return this.m;
    }

    public void a(boolean z) {
        this.s = z;
    }

    public void a(Activity activity, String str) {
        boolean z = GaanaApplication.getInstance().isAppInOfflineMode() || !Util.j((Context) activity);
        String str2 = "Online-Autosuggest";
        if (z) {
            str2 = "Offline-Autosuggest";
        }
        if (str.length() > 0 && str.length() < 3 && !this.m) {
            this.w = true;
        }
        if (!Constants.at && !Constants.au) {
            ((BaseActivity) activity).sendGAEvent(str2, "SearchTap", str);
            a(ACTION_TYPE.SEARCH_TAP.ordinal(), 0, 0, "", 0, "");
        }
    }

    public void a(Activity activity, String str, String str2) {
        String str3;
        String str4;
        Activity activity2 = activity;
        boolean z = GaanaApplication.getInstance().isAppInOfflineMode() || !Util.j((Context) activity);
        String str5 = "";
        this.t = str2;
        if (this.j != null || TextUtils.isEmpty(str) || str.length() <= 0) {
            str3 = str5;
        } else {
            str3 = z ? "Offline-SearchScreen" : "Online-SearchScreen";
            if (this.s) {
                this.s = false;
            } else if (!(!(this.f instanceof SearchEnchancedFragment) || Constants.at || Constants.au)) {
                ((BaseActivity) activity2).sendGAEvent(str3, "StartedTyping", "StartedTyping");
                a(ACTION_TYPE.SEARCH_BEGIN.ordinal(), ACTION_DETAILS.STARTED_TYPING.ordinal(), 0, "", 0, "");
            }
            this.q = true;
            this.r = true;
            this.z = false;
            this.u = true;
            this.v = false;
            this.o = 0;
            this.A++;
        }
        if (TextUtils.isEmpty(str)) {
            this.j = null;
            str4 = str;
        } else {
            str4 = str.trim();
            if (this.j == null && !this.m) {
                this.h = Calendar.getInstance().getTimeInMillis();
            }
            this.j = str4;
        }
        if (!(this.m && TextUtils.isEmpty(str4)) && (this.m || this.w || (!TextUtils.isEmpty(str4) && str4.length() >= 3))) {
            this.w = false;
            if (this.k != null) {
                this.k.a(true, activity2);
                this.C = null;
            }
            this.k = new a(this, null);
            this.k.a(activity2, str4);
            if (this.u) {
                if (Constants.at) {
                    ((BaseActivity) activity2).sendGAEvent(str3, "VoiceSearch", "VoiceSearch");
                    a(ACTION_TYPE.SEARCH_BEGIN.ordinal(), ACTION_DETAILS.VOICE_SEARCH.ordinal(), 0, "", 0, "");
                    this.z = true;
                    Constants.at = false;
                } else {
                    a().a(ACTION_TYPE.SEARCH_BEGIN.ordinal(), ACTION_DETAILS.SEARCH_QUERY.ordinal(), 0, "", 0, "");
                }
                this.u = false;
                this.v = true;
            }
            return;
        }
        if (this.k != null) {
            this.k.a(true, activity2);
            this.C = null;
        }
        this.D = false;
        NextGenAutoSuggestAdapter nextGenAutoSuggestAdapter = new NextGenAutoSuggestAdapter(activity2, str4);
        NextGenSearchAutoSuggests nextGenSearchAutoSuggests = new NextGenSearchAutoSuggests();
        nextGenSearchAutoSuggests.setGroupItems(new ArrayList());
        nextGenAutoSuggestAdapter.setSearchSuggestions(nextGenSearchAutoSuggests, false);
        nextGenAutoSuggestAdapter.setSearchType(this.l);
        if (this.f != null) {
            this.f.a(nextGenAutoSuggestAdapter);
        }
        this.n = str4;
        nextGenAutoSuggestAdapter.notifyDataSetChanged();
    }

    public boolean j() {
        return this.w;
    }

    public void k() {
        if (!this.m) {
            long timeInMillis = Calendar.getInstance().getTimeInMillis();
            if (this.i != 0) {
                Constants.a("search", timeInMillis - this.i, "autosuggest", "taptime");
                this.i = 0;
            }
            if (this.h != 0) {
                Constants.a("search", timeInMillis - this.h, "autosuggest", "searchtime");
                this.h = 0;
            }
        }
    }

    public boolean l() {
        return this.D;
    }

    public void a(Activity activity, String str, int i, String str2, boolean z, String str3, String str4) {
        String str5;
        StringBuilder stringBuilder;
        String str6 = str;
        boolean z2 = GaanaApplication.getInstance().isAppInOfflineMode() || !Util.j((Context) activity);
        if (z) {
            str5 = "Online-Viewall";
            if (z2) {
                str5 = "Offline-Viewall";
            }
        } else {
            str5 = "Online-Autosuggest";
            if (z2) {
                str5 = "Offline-Autosuggest";
            }
        }
        String str7 = str5;
        if ((str6.contains("Tap") || str6.contains("Moreoptions")) && this.q) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("First");
            stringBuilder.append(str6);
            str6 = stringBuilder.toString();
            this.q = false;
            a().k();
        }
        String str8 = str6;
        if (str7.startsWith("Online")) {
            int ordinal;
            int ordinal2;
            int i2 = -1;
            if (str8.startsWith("FirstTap")) {
                ordinal = (Constants.au ? ACTION_TYPE.VS_FIRST_TAP : ACTION_TYPE.FIRST_TAP).ordinal();
                if (z) {
                    ordinal2 = ACTION_DETAILS.VIEW_ALL_RESULT.ordinal();
                } else {
                    ordinal2 = ACTION_DETAILS.RESULT.ordinal();
                }
            } else if (str8.startsWith("Tap")) {
                ordinal = (Constants.au ? ACTION_TYPE.VS_OTHER_TAP : ACTION_TYPE.OTHER_TAP).ordinal();
                if (z) {
                    ordinal2 = ACTION_DETAILS.VIEW_ALL_RESULT.ordinal();
                } else {
                    ordinal2 = ACTION_DETAILS.RESULT.ordinal();
                }
            } else if (str8.startsWith("FirstMoreoptions")) {
                ordinal = (Constants.au ? ACTION_TYPE.VS_FIRST_TAP : ACTION_TYPE.FIRST_TAP).ordinal();
                if (z) {
                    ordinal2 = ACTION_DETAILS.VIEW_ALL_MORE_OPTIONS.ordinal();
                } else {
                    ordinal2 = ACTION_DETAILS.MORE_OPTIONS.ordinal();
                }
            } else if (str8.startsWith("Moreoptions")) {
                ordinal = (Constants.au ? ACTION_TYPE.VS_OTHER_TAP : ACTION_TYPE.OTHER_TAP).ordinal();
                if (z) {
                    ordinal2 = ACTION_DETAILS.VIEW_ALL_MORE_OPTIONS.ordinal();
                } else {
                    ordinal2 = ACTION_DETAILS.MORE_OPTIONS.ordinal();
                }
            } else {
                ordinal2 = -1;
                a(ordinal2, i2, SOURCE_TYPE.valueOf(str3.toUpperCase()).ordinal(), str4, i, "");
            }
            i2 = ordinal2;
            ordinal2 = ordinal;
            a(ordinal2, i2, SOURCE_TYPE.valueOf(str3.toUpperCase()).ordinal(), str4, i, "");
        }
        StringBuilder stringBuilder2;
        if (this.z) {
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Voice_");
            stringBuilder2.append(str8);
            str6 = stringBuilder2.toString();
        } else if (Constants.au) {
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("VoiceUI_");
            stringBuilder2.append(str8);
            str6 = stringBuilder2.toString();
        } else {
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Search_");
            stringBuilder2.append(str8);
            str6 = stringBuilder2.toString();
        }
        String str9 = str6;
        BaseActivity baseActivity = (BaseActivity) activity;
        stringBuilder = new StringBuilder();
        stringBuilder.append(this.n);
        stringBuilder.append("-");
        stringBuilder.append(i);
        stringBuilder.append("-");
        stringBuilder.append(str2);
        baseActivity.sendGAEvent(str7, str9, stringBuilder.toString());
    }

    public void a(String str, String str2, String str3) {
        AppsFlyer.getInstance().reportSearchSong(str, str2, this.m, str3);
    }

    public void b(Activity activity, String str, String str2) {
        String str3 = "Online-SearchScreen";
        if (GaanaApplication.getInstance().isAppInOfflineMode() || !Util.j((Context) activity)) {
            str3 = "Offline-SearchScreen";
        }
        ((BaseActivity) activity).sendGAEvent(str3, str, str2);
        a().a(ACTION_TYPE.SEARCH_BEGIN.ordinal(), ACTION_DETAILS.RECENT_SEARCH_ITEM.ordinal(), 0, "", 0, "");
    }

    /* JADX WARNING: Missing block: B:33:0x00bf, code skipped:
            return;
     */
    public void a(int r17, int r18, int r19, java.lang.String r20, int r21, java.lang.String r22) {
        /*
        r16 = this;
        r0 = r16;
        r14 = r17;
        r1 = com.constants.Constants.cG;
        if (r1 == 0) goto L_0x00bf;
    L_0x0008:
        r1 = r0.f;
        if (r1 == 0) goto L_0x0014;
    L_0x000c:
        r1 = r0.f;
        r1 = r1 instanceof com.fragments.SearchEnchancedFragment;
        if (r1 != 0) goto L_0x0014;
    L_0x0012:
        goto L_0x00bf;
    L_0x0014:
        r1 = com.gaana.application.GaanaApplication.getInstance();
        r1 = r1.isAppInOfflineMode();
        if (r1 != 0) goto L_0x00be;
    L_0x001e:
        r1 = com.gaana.application.GaanaApplication.getContext();
        r1 = com.utilities.Util.j(r1);
        if (r1 != 0) goto L_0x002a;
    L_0x0028:
        goto L_0x00be;
    L_0x002a:
        r1 = com.managers.GaanaSearchManager.ACTION_TYPE.SEARCH_EXIT;
        r1 = r1.ordinal();
        if (r14 != r1) goto L_0x003b;
    L_0x0032:
        r1 = r0.q;
        if (r1 == 0) goto L_0x003b;
    L_0x0036:
        r1 = r0.v;
        if (r1 != 0) goto L_0x003b;
    L_0x003a:
        return;
    L_0x003b:
        r15 = new com.gaana.models.SearchData$SearchEvents;
        r1 = r0.o;
        r2 = java.lang.String.valueOf(r1);
        r3 = r0.B;
        r3 = java.lang.String.valueOf(r3);
        r1 = r0.A;
        r4 = java.lang.String.valueOf(r1);
        r10 = r0.n;
        r1 = java.util.Calendar.getInstance();
        r12 = r1.getTimeInMillis();
        r1 = r15;
        r5 = r14;
        r6 = r18;
        r7 = r19;
        r8 = r20;
        r9 = r21;
        r11 = r22;
        r1.<init>(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12);
        r1 = r0.c;
        r2 = "PREFF_SEARCH_EVENTS";
        r3 = 0;
        r4 = 0;
        r1 = r1.b(r2, r4, r3);
        r2 = android.text.TextUtils.isEmpty(r1);
        if (r2 != 0) goto L_0x007f;
    L_0x0078:
        r1 = com.services.n.a(r1);
        r4 = r1;
        r4 = (com.gaana.models.SearchData) r4;
    L_0x007f:
        if (r4 != 0) goto L_0x0086;
    L_0x0081:
        r4 = new com.gaana.models.SearchData;
        r4.<init>();
    L_0x0086:
        r4.add(r15);
        r1 = r0.c;
        r2 = "PREFF_SEARCH_EVENTS";
        r5 = com.services.n.a(r4);
        r1.a(r2, r5, r3);
        r1 = r4.getSearchEvents();
        r1 = r1.size();
        r2 = 10;
        if (r1 >= r2) goto L_0x00b0;
    L_0x00a0:
        r1 = com.managers.GaanaSearchManager.ACTION_TYPE.SEARCH_EXIT;
        r1 = r1.ordinal();
        if (r14 == r1) goto L_0x00b0;
    L_0x00a8:
        r1 = com.managers.GaanaSearchManager.ACTION_TYPE.VS_EXIT;
        r1 = r1.ordinal();
        if (r14 != r1) goto L_0x00bd;
    L_0x00b0:
        r1 = com.gaana.application.GaanaApplication.getContext();
        r1 = com.utilities.Util.j(r1);
        if (r1 == 0) goto L_0x00bd;
    L_0x00ba:
        r16.o();
    L_0x00bd:
        return;
    L_0x00be:
        return;
    L_0x00bf:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.managers.GaanaSearchManager.a(int, int, int, java.lang.String, int, java.lang.String):void");
    }

    private void o() {
        int i = 0;
        ArrayList arrayList = null;
        String b = this.c.b("PREFF_SEARCH_EVENTS", null, false);
        SearchData searchData = !TextUtils.isEmpty(b) ? (SearchData) n.a(b) : null;
        if (searchData != null) {
            arrayList = searchData.getSearchEvents();
        }
        if (arrayList != null) {
            JSONArray jSONArray = new JSONArray();
            while (i < arrayList.size()) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.accumulate("search_req_id", ((SearchEvents) arrayList.get(i)).getSearchReqId());
                    jSONObject.accumulate("said", ((SearchEvents) arrayList.get(i)).getAttemptId());
                    jSONObject.accumulate("ssid", ((SearchEvents) arrayList.get(i)).getSessionId());
                    jSONObject.accumulate("action_type_id", Integer.valueOf(((SearchEvents) arrayList.get(i)).getActionTypeId()));
                    jSONObject.accumulate("action_detail_id", Integer.valueOf(((SearchEvents) arrayList.get(i)).getActionDetailId()));
                    jSONObject.accumulate("item_type", Integer.valueOf(((SearchEvents) arrayList.get(i)).getItemType()));
                    jSONObject.accumulate("item_id", ((SearchEvents) arrayList.get(i)).getItemID());
                    jSONObject.accumulate("position", Integer.valueOf(((SearchEvents) arrayList.get(i)).getPosition()));
                    if (TextUtils.isEmpty(((SearchEvents) arrayList.get(i)).getKeyword())) {
                        jSONObject.accumulate("keyword", ((SearchEvents) arrayList.get(i)).getKeyword());
                    } else {
                        jSONObject.accumulate("keyword", ((SearchEvents) arrayList.get(i)).getKeyword().trim());
                    }
                    jSONObject.accumulate("comments", ((SearchEvents) arrayList.get(i)).getComments());
                    jSONObject.accumulate(AvidJSONUtil.KEY_TIMESTAMP, Long.valueOf(((SearchEvents) arrayList.get(i)).getTimestamp()));
                    jSONObject.accumulate("nw", Util.p(GaanaApplication.getContext()));
                } catch (JSONException e) {
                    ThrowableExtension.printStackTrace(e);
                }
                jSONArray.put(jSONObject);
                i++;
            }
            URLManager uRLManager = new URLManager();
            uRLManager.a("https://logs.gaana.com/search/log/client");
            uRLManager.c(1);
            HashMap hashMap = new HashMap();
            String toJson = new GsonBuilder().excludeFieldsWithModifiers(8, 4).create().toJson(an.a().b());
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("u_info", new JSONObject(toJson));
                jSONObject2.accumulate("client_data", jSONArray);
            } catch (JSONException e2) {
                ThrowableExtension.printStackTrace(e2);
            }
            hashMap.put("data", jSONObject2.toString());
            uRLManager.a(hashMap);
            i.a().a(new af() {
                public void onErrorResponse(BusinessObject businessObject) {
                }

                public void onRetreivalComplete(Object obj) {
                    GaanaSearchManager.this.c.b("PREFF_SEARCH_EVENTS", false);
                }
            }, uRLManager);
        }
    }

    public void a(Activity activity) {
        a(activity, this.n, "0");
    }

    public void a(View view) {
        if (this.f != null) {
            this.f.a(view);
        }
    }

    public void a(Context context, String str) {
        a((Activity) context, str.trim());
        b(context, str);
    }

    public void b(Context context, String str) {
        a().a(false);
        EditText editText = (EditText) ((Activity) context).findViewById(16908290).findViewById(R.id.search_src_text);
        if (editText != null) {
            editText.requestFocus();
            editText.setText(str);
            editText.setSelection(editText.getText().length());
        }
    }

    public void a(String str) {
        this.x = str;
    }

    public boolean m() {
        return this.z;
    }

    public boolean n() {
        return this.r;
    }

    public void b(boolean z) {
        if (!z) {
            a(ACTION_TYPE.PLAY.ordinal(), ACTION_DETAILS.ZERO.ordinal(), 0, "", 0, "");
        }
        this.r = z;
    }

    public void a(String str, BusinessObjectType businessObjectType) {
        if (this.d != null) {
            BusinessObject deleteFromRecentSearches = this.d.deleteFromRecentSearches(str, businessObjectType);
            this.c.a("PREFF_RECENT_SEARCHES", n.a(this.d), false);
            if (ai.a() != null && (((GaanaActivity) ai.a()).getCurrentFragment() instanceof ListingFragment)) {
                ((GaanaActivity) ai.a()).refreshListView(deleteFromRecentSearches, true);
                ((GaanaActivity) ai.a()).setRefreshData(true);
            }
        }
    }
}
