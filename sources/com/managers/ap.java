package com.managers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;
import com.comscore.measurement.MeasurementDispatcher;
import com.constants.Constants;
import com.constants.c;
import com.dynamicview.DynamicViewManager;
import com.e.a.f;
import com.facebook.internal.AnalyticsEvents;
import com.fragments.AddToPlaylistFragment;
import com.fragments.AlbumDetailsMaterialListing;
import com.fragments.BaseGaanaFragment;
import com.fragments.GDPRCantUseAppFragment;
import com.fragments.GaanaSpecialDetailsMaterialListing;
import com.fragments.ItemListingFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.analytics.MoEngage;
import com.gaana.application.GaanaApplication;
import com.gaana.localmedia.FavouriteSyncManager;
import com.gaana.localmedia.PlaylistSyncManager;
import com.gaana.login.GaanaMiniSubDetails;
import com.gaana.login.LoginManager;
import com.gaana.login.LoginManager.IOnLoginCompleted;
import com.gaana.login.LoginManager.LOGIN_STATUS;
import com.gaana.login.UserInfo;
import com.gaana.login.UserSubscriptionData.DisableDownloadId;
import com.gaana.login.UserSubscriptionData.ProductProperties;
import com.gaana.models.Albums.Album;
import com.gaana.models.Artists.Artist;
import com.gaana.models.BusinessObject;
import com.gaana.models.EntityInfo;
import com.gaana.models.Item;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Radios.Radio;
import com.gaana.models.Tracks.Track;
import com.gaana.models.User.LoginType;
import com.gaana.view.item.AddToPlaylistItemView;
import com.gaana.view.item.PopupShareitemView;
import com.gaana.view.item.RadioButtonSongView;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.i.j;
import com.library.managers.TaskManager.TaskListner;
import com.managers.PlayerManager.PlayerType;
import com.managers.URLManager.BusinessObjectType;
import com.models.ListingButton;
import com.models.ListingComponents;
import com.services.d;
import com.services.f.b;
import com.services.h;
import com.services.l.ad;
import com.services.l.ah;
import com.services.l.y;
import com.utilities.Util;
import com.youtube.YouTubeVideos.YouTubeVideo;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

public class ap {
    private static ap a;
    private String b = "";
    private d c = d.a();
    private GaanaApplication d = ((GaanaApplication) GaanaApplication.getContext());
    private com.views.a e = null;
    private ah f = null;

    public interface a {
        void onFavoriteCompleted(BusinessObject businessObject, boolean z);
    }

    private ap() {
    }

    public static ap a() {
        if (a == null) {
            a = new ap();
        }
        return a;
    }

    public String b() {
        return this.b;
    }

    public void a(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append("_");
        this.b = stringBuilder.toString();
    }

    public boolean c() {
        return this.d.getCurrentUser().getLoginType() == LoginType.FB;
    }

    public void a(Context context, BusinessObject businessObject, BaseGaanaFragment baseGaanaFragment) {
        StringBuilder stringBuilder;
        new Intent("android.intent.action.SEND").setType("text/plain");
        String str = "https://gaana.com/";
        String str2 = "";
        String str3 = businessObject.getName().split("\\s+")[0];
        StringBuilder stringBuilder2;
        Track track;
        switch (businessObject.getBusinessObjType()) {
            case Albums:
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(context.getString(R.string.listen_album_txt));
                stringBuilder2.append(businessObject.getName());
                stringBuilder2.toString();
                Album album = (Album) businessObject;
                album.getArtwork();
                stringBuilder = new StringBuilder();
                stringBuilder.append(context.getString(R.string.bo_albums));
                stringBuilder.append(businessObject.getName());
                stringBuilder.toString();
                businessObject.getName();
                album.getArtistNames();
                stringBuilder = new StringBuilder();
                stringBuilder.append("album/");
                stringBuilder.append(businessObject.getBusinessObjId());
                stringBuilder.toString();
                stringBuilder = new StringBuilder();
                stringBuilder.append("album/");
                stringBuilder.append(album.getSeokey());
                str2 = stringBuilder.toString();
                break;
            case Artists:
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(context.getString(R.string.listen_song_txt));
                stringBuilder2.append(businessObject.getName());
                stringBuilder2.toString();
                Artist artist = (Artist) businessObject;
                artist.getArtwork();
                stringBuilder = new StringBuilder();
                stringBuilder.append(context.getString(R.string.bo_artists));
                stringBuilder.append(businessObject.getName());
                stringBuilder.toString();
                businessObject.getName();
                artist.getDescription();
                stringBuilder = new StringBuilder();
                stringBuilder.append("artist/");
                stringBuilder.append(businessObject.getBusinessObjId());
                stringBuilder.toString();
                stringBuilder = new StringBuilder();
                stringBuilder.append("artist/");
                stringBuilder.append(artist.getSeokey());
                str2 = stringBuilder.toString();
                break;
            case Playlists:
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(context.getString(R.string.listen_playlist_txt));
                stringBuilder2.append(businessObject.getName());
                stringBuilder2.toString();
                Playlist playlist = (Playlist) businessObject;
                playlist.getArtwork();
                stringBuilder = new StringBuilder();
                stringBuilder.append(context.getString(R.string.bo_playlists));
                stringBuilder.append(businessObject.getName());
                stringBuilder.toString();
                businessObject.getName();
                stringBuilder = new StringBuilder();
                stringBuilder.append("By ");
                stringBuilder.append(playlist.getCreatedby());
                stringBuilder.toString();
                stringBuilder = new StringBuilder();
                stringBuilder.append("playlist/");
                stringBuilder.append(businessObject.getBusinessObjId());
                stringBuilder.toString();
                stringBuilder = new StringBuilder();
                stringBuilder.append("playlist/");
                stringBuilder.append(playlist.getSeokey());
                str2 = stringBuilder.toString();
                break;
            case Tracks:
                track = (Track) businessObject;
                track.getArtwork();
                stringBuilder = new StringBuilder();
                stringBuilder.append(context.getString(R.string.listen_txt));
                stringBuilder.append(track.getTrackTitle());
                stringBuilder.append(context.getString(R.string.frm_album_txt));
                stringBuilder.append(track.getAlbumTitle());
                stringBuilder.toString();
                stringBuilder = new StringBuilder();
                stringBuilder.append(context.getString(R.string.bo_tracks));
                stringBuilder.append(track.getTrackTitle());
                stringBuilder.toString();
                businessObject.getName();
                track.getAlbumTitle();
                stringBuilder = new StringBuilder();
                stringBuilder.append("track/");
                stringBuilder.append(track.getBusinessObjId());
                stringBuilder.toString();
                stringBuilder = new StringBuilder();
                stringBuilder.append("song/");
                stringBuilder.append(track.getSeokey());
                str2 = stringBuilder.toString();
                break;
            case Radios:
                Radio radio = (Radio) businessObject;
                if (radio.getType().equals(c.d.c)) {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("radio/mirchi/");
                    stringBuilder.append(businessObject.getBusinessObjId());
                    stringBuilder.toString();
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("radio/");
                    stringBuilder.append(radio.getSeokey());
                    str3 = stringBuilder.toString();
                } else {
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("radio/live/");
                    stringBuilder.append(businessObject.getBusinessObjId());
                    stringBuilder.toString();
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("gaanaradio/");
                    stringBuilder.append(radio.getSeokey());
                    str3 = stringBuilder.toString();
                }
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append(context.getString(R.string.listen_song_txt));
                stringBuilder3.append(businessObject.getName());
                stringBuilder3.toString();
                radio.getArtwork();
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(context.getString(R.string.bo_radio));
                stringBuilder2.append(businessObject.getName());
                stringBuilder2.toString();
                businessObject.getName();
                str2 = str3;
                break;
            case YouTubeVideos:
                if (!(businessObject instanceof YouTubeVideo)) {
                    if (!(businessObject instanceof Track)) {
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(context.getString(R.string.watch_this_video));
                        stringBuilder.append(businessObject.getName());
                        stringBuilder.toString();
                        businessObject.getAtw();
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(context.getString(R.string.video_text));
                        stringBuilder.append(businessObject.getName());
                        stringBuilder.toString();
                        businessObject.getName();
                        break;
                    }
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append(context.getString(R.string.watch_this_video));
                    stringBuilder2.append(businessObject.getName());
                    stringBuilder2.toString();
                    track = (Track) businessObject;
                    track.getArtwork();
                    stringBuilder = new StringBuilder();
                    stringBuilder.append(context.getString(R.string.video_text));
                    stringBuilder.append(businessObject.getName());
                    stringBuilder.toString();
                    businessObject.getName();
                    stringBuilder = new StringBuilder();
                    stringBuilder.append("videos/");
                    stringBuilder.append(track.getYoutubeId());
                    str2 = stringBuilder.toString();
                    break;
                }
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(context.getString(R.string.watch_this_video));
                stringBuilder2.append(businessObject.getName());
                stringBuilder2.toString();
                YouTubeVideo youTubeVideo = (YouTubeVideo) businessObject;
                youTubeVideo.d();
                stringBuilder = new StringBuilder();
                stringBuilder.append(context.getString(R.string.video_text));
                stringBuilder.append(businessObject.getName());
                stringBuilder.toString();
                businessObject.getName();
                stringBuilder = new StringBuilder();
                stringBuilder.append("videos/");
                stringBuilder.append(youTubeVideo.c());
                str2 = stringBuilder.toString();
                break;
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append(str2);
        new PopupShareitemView(context, stringBuilder.toString()).shareOnOther();
        MoEngage.getInstance().reportShareItem(businessObject);
    }

    public void a(Context context, BusinessObject businessObject, String str, String str2, String str3) {
        String stringBuilder;
        StringBuilder stringBuilder2;
        String str4;
        new Intent("android.intent.action.SEND").setType("text/plain");
        String str5 = "https://gaana.com/";
        String str6 = "";
        String str7 = "";
        StringBuilder stringBuilder3;
        StringBuilder stringBuilder4;
        switch (businessObject.getBusinessObjType()) {
            case Albums:
                Album album = (Album) businessObject;
                str7 = album.getArtwork();
                stringBuilder3 = new StringBuilder();
                stringBuilder3.append(context.getString(R.string.bo_albums));
                stringBuilder3.append(businessObject.getName());
                stringBuilder = stringBuilder3.toString();
                stringBuilder4 = new StringBuilder();
                stringBuilder4.append("album/");
                stringBuilder4.append(album.getSeokey());
                str6 = stringBuilder4.toString();
                break;
            case Artists:
                Artist artist = (Artist) businessObject;
                str7 = artist.getArtwork();
                stringBuilder3 = new StringBuilder();
                stringBuilder3.append(context.getString(R.string.bo_artists));
                stringBuilder3.append(businessObject.getName());
                stringBuilder = stringBuilder3.toString();
                stringBuilder4 = new StringBuilder();
                stringBuilder4.append("artist/");
                stringBuilder4.append(artist.getSeokey());
                str6 = stringBuilder4.toString();
                break;
            case Playlists:
                Playlist playlist = (Playlist) businessObject;
                str7 = playlist.getArtwork();
                stringBuilder3 = new StringBuilder();
                stringBuilder3.append(context.getString(R.string.bo_playlists));
                stringBuilder3.append(businessObject.getName());
                stringBuilder = stringBuilder3.toString();
                stringBuilder4 = new StringBuilder();
                stringBuilder4.append("playlist/");
                stringBuilder4.append(playlist.getSeokey());
                str6 = stringBuilder4.toString();
                break;
            case Tracks:
                Track track = (Track) businessObject;
                str7 = track.getArtwork();
                stringBuilder3 = new StringBuilder();
                stringBuilder3.append(context.getString(R.string.bo_tracks));
                stringBuilder3.append(track.getTrackTitle());
                stringBuilder = stringBuilder3.toString();
                stringBuilder4 = new StringBuilder();
                stringBuilder4.append("song/");
                stringBuilder4.append(track.getSeokey());
                str6 = stringBuilder4.toString();
                break;
            case Radios:
                Radio radio = (Radio) businessObject;
                if (radio.getType().equals(c.d.c)) {
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("radio/");
                    stringBuilder2.append(radio.getSeokey());
                    str7 = stringBuilder2.toString();
                } else {
                    stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("gaanaradio/");
                    stringBuilder2.append(radio.getSeokey());
                    str7 = stringBuilder2.toString();
                }
                str6 = radio.getArtwork();
                stringBuilder3 = new StringBuilder();
                stringBuilder3.append(context.getString(R.string.bo_radio));
                stringBuilder3.append(businessObject.getName());
                stringBuilder = stringBuilder3.toString();
                str4 = str6;
                str6 = str7;
                break;
            default:
                stringBuilder = null;
                break;
        }
        str4 = str7;
        String str8 = stringBuilder;
        stringBuilder2 = new StringBuilder();
        stringBuilder2.append(str5);
        stringBuilder2.append(str6);
        String stringBuilder5 = stringBuilder2.toString();
        com.services.a aVar = new com.services.a(context);
        StringBuilder stringBuilder6 = new StringBuilder();
        stringBuilder6.append(str2);
        stringBuilder6.append("\n");
        stringBuilder6.append(stringBuilder5);
        String stringBuilder7 = stringBuilder6.toString();
        stringBuilder6 = new StringBuilder();
        stringBuilder6.append(str);
        stringBuilder6.append("\n");
        stringBuilder6.append(stringBuilder5);
        aVar.a(str3, stringBuilder6.toString(), stringBuilder7, str4, str8, stringBuilder5);
        MoEngage.getInstance().reportShareItem(businessObject);
    }

    public void a(final Context context, final Track track, boolean z, boolean z2) {
        if (z) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(track);
            if (this.d == null) {
                this.d = (GaanaApplication) context.getApplicationContext();
            }
            this.d.setArrListTracksForPlaylist(arrayList);
            a(context, false);
            return;
        }
        ((BaseActivity) context).checkSetLoginStatusFromBottomSheet(new ad() {
            public void onLoginSuccess() {
                ArrayList arrayList = new ArrayList();
                arrayList.add(track);
                if (ap.this.d == null) {
                    ap.this.d = (GaanaApplication) context.getApplicationContext();
                }
                ap.this.d.setArrListTracksForPlaylist(arrayList);
                ap.this.a(context, false);
            }
        }, "PLAYLIST", GaanaApplication.getContext().getResources().getString(R.string.LOGIN_LAUNCHED_FOR_ADD_TO_PLAYLIST), z2, false);
    }

    public void a(final Context context, final ArrayList<Track> arrayList, boolean z, boolean z2) {
        if (z) {
            a(context, (ArrayList) arrayList);
        } else {
            ((BaseActivity) context).checkSetLoginStatusFromBottomSheet(new ad() {
                public void onLoginSuccess() {
                    ap.this.a(context, arrayList);
                }
            }, "PLAYLIST", GaanaApplication.getContext().getResources().getString(R.string.LOGIN_LAUNCHED_FOR_ADD_TO_PLAYLIST), z2, false);
        }
    }

    public void a(final Context context, final Track track, boolean z) {
        if (z) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(track);
            if (this.d == null) {
                this.d = (GaanaApplication) context.getApplicationContext();
            }
            this.d.setArrListTracksForPlaylist(arrayList);
            a(context, false);
            return;
        }
        ((BaseActivity) context).checkSetLoginStatus(new ad() {
            public void onLoginSuccess() {
                ArrayList arrayList = new ArrayList();
                arrayList.add(track);
                if (ap.this.d == null) {
                    ap.this.d = (GaanaApplication) context.getApplicationContext();
                }
                ap.this.d.setArrListTracksForPlaylist(arrayList);
                ap.this.a(context, false);
            }
        }, GaanaApplication.getContext().getResources().getString(R.string.LOGIN_LAUNCHED_FOR_ADD_TO_PLAYLIST));
    }

    public void a(final Context context, final ArrayList<Track> arrayList, boolean z) {
        if (z) {
            a(context, (ArrayList) arrayList);
        } else {
            ((BaseActivity) context).checkSetLoginStatus(new ad() {
                public void onLoginSuccess() {
                    ap.this.a(context, arrayList);
                }
            }, GaanaApplication.getContext().getResources().getString(R.string.LOGIN_LAUNCHED_FOR_ADD_TO_PLAYLIST));
        }
    }

    public void a(Context context, ArrayList<Track> arrayList) {
        if (this.d == null) {
            this.d = (GaanaApplication) context.getApplicationContext();
        }
        this.d.setArrListTracksForPlaylist(arrayList);
        ListingComponents listingComponents = new ListingComponents();
        listingComponents.b(context.getResources().getString(R.string.select_songs));
        listingComponents.a(Boolean.valueOf(true));
        ArrayList arrayList2 = new ArrayList();
        ListingButton listingButton = new ListingButton();
        listingButton.c(RadioButtonSongView.class.getName());
        listingButton.a((ArrayList) arrayList);
        listingButton.a(context.getResources().getString(R.string.select_songs));
        arrayList2.add(listingButton);
        listingComponents.a(arrayList2);
        this.d.setListingComponents(listingComponents);
        Bundle bundle = new Bundle();
        bundle.putBoolean("ITEM_LISTING_ADD_TO_PLAYLIST_NEXT", true);
        bundle.putBoolean("should_hide_bottom_bar", true);
        BaseGaanaFragment itemListingFragment = new ItemListingFragment();
        itemListingFragment.setArguments(bundle);
        ((GaanaActivity) context).displayFragment(itemListingFragment);
    }

    public void a(Context context, boolean z) {
        if (this.d == null) {
            this.d = (GaanaApplication) context.getApplicationContext();
        }
        ListingComponents listingComponents = new ListingComponents();
        listingComponents.b(context.getResources().getString(R.string.opt_add_to_playlist));
        listingComponents.a(Boolean.valueOf(true));
        ArrayList arrayList = new ArrayList();
        ListingButton listingButton = new ListingButton();
        listingButton.c(AddToPlaylistItemView.class.getName());
        URLManager uRLManager = new URLManager();
        uRLManager.a(BusinessObjectType.Playlists);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://api.gaana.com/user.php?type=myplaylists&subtype=myplaylists&token=");
        stringBuilder.append(this.d.getCurrentUser().getAuthToken());
        uRLManager.a(stringBuilder.toString());
        uRLManager.b(Boolean.valueOf(false));
        listingButton.a(uRLManager);
        listingButton.a(context.getResources().getString(R.string.select_songs));
        if (!Util.j(GaanaApplication.getContext()) || this.d.isAppInOfflineMode()) {
            listingButton.a(PlaylistSyncManager.getInstance().getMyPlaylistsFromDb(false));
        }
        arrayList.add(listingButton);
        listingComponents.a(arrayList);
        this.d.setListingComponents(listingComponents);
        Bundle bundle = new Bundle();
        bundle.putBoolean("ITEM_LISTING_ADD_TO_PLAYLIST_SAVE", true);
        bundle.putBoolean("ITEM_LISTING_ADD_TO_PLAYLIST_ISFROMHEADER", z);
        BaseGaanaFragment addToPlaylistFragment = new AddToPlaylistFragment();
        addToPlaylistFragment.setArguments(bundle);
        ((GaanaActivity) context).displayFragment(addToPlaylistFragment);
    }

    public void a(final Context context, final BusinessObject businessObject, final boolean z) {
        BaseActivity baseActivity = (BaseActivity) context;
        AnonymousClass9 anonymousClass9 = new ad() {
            public void onLoginSuccess() {
                ((BaseActivity) context).addRemoveFav(businessObject, Boolean.valueOf(false), z);
            }
        };
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(GaanaApplication.getContext().getResources().getString(R.string.LOGIN_LAUNCHED_FOR_FAVORITE_1));
        stringBuilder.append(" ");
        stringBuilder.append(Util.a(businessObject.getBusinessObjType()));
        stringBuilder.append(" ");
        stringBuilder.append(GaanaApplication.getContext().getResources().getString(R.string.LOGIN_LAUNCHED_FOR_FAVORITE_2));
        baseActivity.checkSetLoginStatus(anonymousClass9, stringBuilder.toString());
    }

    public void a(final Context context, final BusinessObject businessObject, final a aVar) {
        BaseActivity baseActivity = (BaseActivity) context;
        AnonymousClass10 anonymousClass10 = new ad() {
            public void onLoginSuccess() {
                ((BaseActivity) context).addRemoveFav(businessObject, Boolean.valueOf(false), false, aVar);
            }
        };
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(GaanaApplication.getContext().getResources().getString(R.string.LOGIN_LAUNCHED_FOR_FAVORITE_1));
        stringBuilder.append(" ");
        stringBuilder.append(Util.a(businessObject.getBusinessObjType()));
        stringBuilder.append(" ");
        stringBuilder.append(GaanaApplication.getContext().getResources().getString(R.string.LOGIN_LAUNCHED_FOR_FAVORITE_2));
        baseActivity.checkSetLoginStatus(anonymousClass10, stringBuilder.toString());
    }

    public void a(Context context, boolean z, final ad adVar) {
        ((BaseActivity) context).checkSetLoginStatusFromBottomSheet(new ad() {
            public void onLoginSuccess() {
                adVar.onLoginSuccess();
            }
        }, "DOWNLOAD", "Download Your songs", z, false);
    }

    public String b(String str) {
        String str2 = "";
        try {
            return new JSONObject(str).optString(AnalyticsEvents.PARAMETER_SHARE_ERROR_MESSAGE, "");
        } catch (JSONException e) {
            ThrowableExtension.printStackTrace(e);
            return str2;
        }
    }

    public String c(String str) {
        String str2 = "";
        String str3 = "";
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("status")) {
                str3 = jSONObject.getString("status");
            }
            if (jSONObject.has("data")) {
                jSONObject.getString("data");
            }
            if (jSONObject.has("state")) {
                str2 = jSONObject.getString("state");
            }
            if (jSONObject.has("verificationStatus") && jSONObject.getInt("verificationStatus") == 0) {
                str2 = "99";
            }
            if (str3.equalsIgnoreCase("1") && TextUtils.isEmpty(str2) && str2 != null) {
                return str2;
            }
            return str2;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            str2 = null;
        }
    }

    public String d(String str) {
        String str2 = "";
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("status")) {
                return jSONObject.getString("status");
            }
            return str2;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    public int e(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("follow_type")) {
                return jSONObject.getInt("follow_type");
            }
            return -1;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return -1;
        }
    }

    public String f(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.has("message")) {
                return jSONObject.getString("message");
            }
            return null;
        } catch (Exception e) {
            ThrowableExtension.printStackTrace(e);
            return null;
        }
    }

    public void a(final Context context, boolean z, final IOnLoginCompleted iOnLoginCompleted, final LOGIN_STATUS login_status) {
        if (context instanceof BaseActivity) {
            ((BaseActivity) context).showProgressDialog(Boolean.valueOf(true), context.getString(R.string.logging_out));
        }
        MoEngage.getInstance().reportOnLogout();
        aa.a().e();
        ab.a().f();
        h.a().a(new TaskListner() {
            public void onBackGroundTaskCompleted() {
                o.a().b();
                ap.this.d.setSidebarActiveBtn(R.id.GaanaHome);
                w.a(ap.this.d).a(context, null, false);
                q.a().f();
                q.a().a("ua", "freeuser");
                q.a().c();
                aa.a().a(null);
                if (context != null && Util.j(context.getApplicationContext()) && ap.a().b(context) && !GaanaApplication.getInstance().getColombiaSdkInit()) {
                    Util.F();
                    Util.G();
                }
                ap.this.c.b("PREFERENCE_KEY_NOTIFICATIONS", true);
                ap.this.c.b("PREFERENCE_KEY_NOTIFICATION_MUSIC_RECOMMENDATIONS", false);
                ap.this.c.b("PREFERENCE_KEY_NOTIFICATION_FAVORITE_PLAYLIST", false);
                ap.this.c.b("PREFERENCE_KEY_NOTIFICATION_FOLLOW_UPDATES", false);
                ap.this.c.b("PREFERENCE_KEY_POST_TO_FACEBOOK", false);
                ap.this.c.b("PREFERENCE_KEY_SOCIAL_PRIVATE_SESSION", false);
                ap.this.c.b("PREFERENCE_KEY_TOTAL_DOWNLOAD_COUNT", true);
                ap.this.c.b("PREFERENCE_KEY_MONTH_DOWNLOAD_COUNT", true);
                ap.this.c.b("FAVORITE_SONGS_DOWNLOADED", true);
                ap.this.c.b("PREFF_CAMPAIGN_COUPON", true);
                ap.this.c.b("PREFF_CAMPAIGN_PROCESS_SUCCESSFUL", true);
                ap.this.c.b("PREFF_CAMPAIGN_PROMO_REFERRAL", false);
                ap.this.c.b("PREF_DOWNLOAD_LIST_SHOW_PARAMETER_DOWNLOADED", true);
                ap.this.c.b("PREF_DOWNLOAD_LIST_SHOW_PARAMETER_GAANA_MINI", true);
                ap.this.c.b("PREF_DOWNLOAD_LIST_SHOW_PARAMETER_QUEUED", true);
                ap.this.c.b("PREFF_RECENT_SEARCHES", false);
                ap.this.c.b("SILENT_PUSH_DATA_SENT", false);
                GaanaSearchManager.a().b();
                d.a().b();
                v.a().b(context);
                ap.this.c.b("PREFERENCE_LANGUAGE_SETTINGS", false);
                l.a().h();
                f.a().b();
                FavouriteSyncManager.getInstance().clear();
                com.fcm.a.b();
                LoginManager.getInstance().getTimesPointLogger().b();
                ap.a().a("NO_USER");
                LoginManager.getInstance().saveUserInfoInSharedPreff();
                u.a().c();
                an.a().a("click", "ac", "LOGOUT", "", "HOME", "", "", "");
                j.a().a("https://apiv2.gaana.com/radio/metadata");
                DynamicViewManager.a().a(new y() {
                    public void OnDynamicViewDataFetched() {
                        if (context instanceof BaseActivity) {
                            ((BaseActivity) context).hideProgressDialog();
                        }
                        Toast.makeText(GaanaApplication.getContext(), R.string.logged_out, 0).show();
                        DownloadManager.c().e();
                        ap.this.d.setCurrentSessionId(UUID.randomUUID().toString());
                        if (login_status == LOGIN_STATUS.LAUNCH_GDPR_DELETE_PROGRESS) {
                            if ((context instanceof AppCompatActivity) && !((AppCompatActivity) context).isFinishing()) {
                                try {
                                    GDPRCantUseAppFragment gDPRCantUseAppFragment = new GDPRCantUseAppFragment();
                                    gDPRCantUseAppFragment.show(((AppCompatActivity) context).getSupportFragmentManager(), gDPRCantUseAppFragment.getClass().getSimpleName());
                                } catch (Exception unused) {
                                }
                            }
                        } else if (!(context instanceof Activity)) {
                        } else {
                            Intent intent;
                            if (ap.this.d.getCountryData().getEuRegion() == 1) {
                                ap.this.c.b("PREFF_USER_JOURNEY_EVENTS", false);
                                j.a().c().d().a();
                                Constants.ek = 0;
                                d.a().a("PREF_CONSENT_STATUS", Constants.ek, false);
                                intent = new Intent(context, GaanaActivity.class);
                                intent.setFlags(268468224);
                                context.startActivity(intent);
                                return;
                            }
                            intent = new Intent(context, GaanaActivity.class);
                            intent.setFlags(71303168);
                            context.startActivity(intent);
                        }
                    }
                }, context);
            }

            public void doBackGroundTask() {
                if (ap.this.d.getCurrentUser().getLoginStatus()) {
                    LoginManager.getInstance().logout((Activity) context, iOnLoginCompleted);
                    ap.this.d.getCurrentUser().setLoginStatus(false);
                    ap.a().a(context);
                    ap.this.c.b("PREFERENCE_KEY_POST_TO_GOOGLE", false);
                    ap.this.c.b("PREFERENCE_KEY_SOCIAL_PRIVATE_SESSION", false);
                    ap.this.c.b("PREFERENCE_KEY_POST_TO_FACEBOOK", false);
                }
            }
        }, -1);
    }

    public void a(Context context) {
        if (this.d == null) {
            this.d = (GaanaApplication) context.getApplicationContext();
        }
        this.d.setAppInOfflineMode(false);
        this.c.a("PREFERENCE_KEY_OFFLINE_MODE", false, false);
        this.c.a(-1, "PREFERENCE_KEY_OFFLINE_MODE_START_TIME", true);
        this.c.a(-1, "PREFERENCE_KEY_OFFLINE_MODE_LAST_REMINDER_TIME", true);
        this.c.a("PREFERENCE_KEY_SYNC_OVER_DATA_CONNECTION", false, true);
        Util.b("download_over_2G3G", "0");
        this.c.a("PREFERENCE_KEY_DOWNLOAD_SYNC_OVER_DATA_CONNECTION", false, true);
        Util.b("sync_over_2G3G", "0");
        this.c.a("PREFERENCE_KEY_SETTINGS_AUTO_SYNC_V5", false, true);
        Util.b("auto_sync", "0");
        if (a().d()) {
            Constants.ab = false;
            Constants.aa = false;
            d.a().a(Constants.ac, false, true);
            d.a().a(Constants.ad, false, true);
        }
    }

    public boolean d() {
        boolean z = false;
        if (!this.d.getCurrentUser().getLoginStatus()) {
            return false;
        }
        if (this.d.getCurrentUser().getUserSubscriptionData().getAccountType() == 3 || this.d.getCurrentUser().getUserSubscriptionData().getAccountType() == 2) {
            z = true;
        }
        return z;
    }

    public boolean e() {
        if (this.d.getCurrentUser().getLoginStatus() && this.d.getCurrentUser().getUserSubscriptionData().getAccountType() == 3) {
            return true;
        }
        return false;
    }

    public boolean f() {
        if (this.d.getCurrentUser().getLoginStatus()) {
            return this.d.getCurrentUser().getUserSubscriptionData().getProductProperties().getProductType().equals("gplus_mini");
        }
        return false;
    }

    public boolean g() {
        return !f() || DownloadManager.c().K() <= Integer.valueOf(GaanaApplication.getInstance().getCurrentUser().getUserSubscriptionData().getProductProperties().getSongLimit()).intValue();
    }

    public boolean h() {
        if (!f() || DownloadManager.c().K() <= Integer.valueOf(GaanaApplication.getInstance().getCurrentUser().getUserSubscriptionData().getProductProperties().getSongLimit()).intValue() + 1) {
            return true;
        }
        return false;
    }

    public boolean i() {
        boolean z = false;
        if (!this.d.getCurrentUser().getLoginStatus()) {
            return false;
        }
        if (this.d.getCurrentUser().getUserSubscriptionData().getAccountType() == 2) {
            z = true;
        }
        return z;
    }

    public boolean a(int i) {
        int accountType = (this.d.getCurrentUser() == null || this.d.getCurrentUser().getUserSubscriptionData() == null) ? 0 : this.d.getCurrentUser().getUserSubscriptionData().getAccountType();
        switch (i) {
            case 0:
                return true;
            case 1:
                if (accountType == 1 || accountType == 0) {
                    return true;
                }
                break;
            case 2:
                if (accountType == 2) {
                    return true;
                }
                break;
            case 3:
                if (accountType == 3) {
                    return true;
                }
                break;
            case 4:
                if (accountType == 1 || accountType == 0 || accountType == 2) {
                    return true;
                }
                break;
            case 5:
                if (accountType == 3 || accountType == 2) {
                    return true;
                }
                break;
        }
        return false;
    }

    public boolean j() {
        return o() || m();
    }

    public boolean a(BusinessObject businessObject) {
        if (l()) {
            if (businessObject instanceof Track) {
                Track track = (Track) businessObject;
                if (TextUtils.isEmpty(track.getVgid())) {
                    return true;
                }
                ArrayList disable_download = GaanaApplication.getInstance().getCurrentUser().getUserSubscriptionData().getDisable_download();
                if (disable_download == null || disable_download.size() <= 0) {
                    return true;
                }
                Iterator it = disable_download.iterator();
                while (it.hasNext()) {
                    DisableDownloadId disableDownloadId = (DisableDownloadId) it.next();
                    if (disableDownloadId != null && !TextUtils.isEmpty(disableDownloadId.getVgid()) && disableDownloadId.getVgid().equals(track.getVgid())) {
                        return false;
                    }
                }
            } else {
                ArrayList disable_download2 = GaanaApplication.getInstance().getCurrentUser().getUserSubscriptionData().getDisable_download();
                if (disable_download2 == null || disable_download2.size() == 0) {
                    return true;
                }
                return false;
            }
        }
        return true;
    }

    public boolean a(BusinessObject businessObject, BusinessObject businessObject2) {
        if (!a(businessObject)) {
            return false;
        }
        if (o()) {
            return true;
        }
        if (!this.d.getCurrentUser().getLoginStatus() || !m()) {
            return false;
        }
        String businessObjId = businessObject.getBusinessObjId();
        String str = c.c.a;
        if (businessObject instanceof Album) {
            str = c.c.b;
        } else if (businessObject instanceof Artist) {
            str = c.c.d;
        }
        ArrayList artists;
        int i;
        if (businessObject instanceof Track) {
            artists = ((Track) businessObject).getArtists();
            ArrayList gaanaMiniSubDetails = this.d.getCurrentUser().getUserSubscriptionData().getGaanaMiniSubDetails();
            int i2 = 0;
            while (i2 < artists.size()) {
                if (gaanaMiniSubDetails != null) {
                    i = 0;
                    while (i < gaanaMiniSubDetails.size()) {
                        if (((GaanaMiniSubDetails) gaanaMiniSubDetails.get(i)).getEntityType().equalsIgnoreCase("AR") && ((GaanaMiniSubDetails) gaanaMiniSubDetails.get(i)).getEntityId().equalsIgnoreCase(((Track.Artist) artists.get(i2)).artist_id)) {
                            return true;
                        }
                        i++;
                    }
                    continue;
                }
                i2++;
            }
        } else {
            artists = this.d.getCurrentUser().getUserSubscriptionData().getGaanaMiniSubDetails();
            if (artists != null) {
                i = 0;
                while (i < artists.size()) {
                    if (businessObjId.equalsIgnoreCase(((GaanaMiniSubDetails) artists.get(i)).getEntityId()) && str.equalsIgnoreCase(((GaanaMiniSubDetails) artists.get(i)).getEntityType())) {
                        return true;
                    }
                    if (((GaanaMiniSubDetails) artists.get(i)).getEntityType().equalsIgnoreCase(c.c.d) && businessObjId.equalsIgnoreCase(((GaanaMiniSubDetails) artists.get(i)).getPlaylistId()) && str.equalsIgnoreCase("PL")) {
                        return true;
                    }
                    i++;
                }
            }
        }
        return false;
    }

    public boolean k() {
        boolean z = false;
        if (this.d.getCurrentUser() == null || this.d.getCurrentUser().getUserSubscriptionData() == null || this.d.getCurrentUser().getUserSubscriptionData().getExpiryDate() == null) {
            return false;
        }
        if (this.d.getCurrentUser().getUserSubscriptionData().getExpiryDate().getTime() - Calendar.getInstance().getTimeInMillis() < 0) {
            z = true;
        }
        return z;
    }

    public boolean l() {
        return (GaanaApplication.getInstance().getCurrentUser() == null || GaanaApplication.getInstance().getCurrentUser().getUserSubscriptionData() == null || GaanaApplication.getInstance().getCurrentUser().getUserSubscriptionData().getSubscriptionType() == null || GaanaApplication.getInstance().getCurrentUser().getUserSubscriptionData().getExpiryDate() == null || GaanaApplication.getInstance().getCurrentUser().getUserSubscriptionData().getExpiryDate().getTime() - Calendar.getInstance().getTimeInMillis() <= 0) ? false : "Gaana Plus*".contains(GaanaApplication.getInstance().getCurrentUser().getUserSubscriptionData().getSubscriptionType());
    }

    public boolean m() {
        UserInfo currentUser = this.d.getCurrentUser();
        if (currentUser.getLoginStatus() && currentUser.getUserSubscriptionData().getAccountType() == 1) {
            ArrayList gaanaMiniSubDetails = currentUser.getUserSubscriptionData().getGaanaMiniSubDetails();
            if (gaanaMiniSubDetails != null && gaanaMiniSubDetails.size() > 0 && currentUser.getUserSubscriptionData().isDeviceLinked()) {
                return true;
            }
        }
        return false;
    }

    public boolean n() {
        UserInfo currentUser = this.d.getCurrentUser();
        return currentUser.getLoginStatus() && currentUser.getUserSubscriptionData().getProductProperties().getIsDeviceLinkingEnabled();
    }

    public boolean o() {
        if (!this.d.getCurrentUser().getLoginStatus() || this.d.getCurrentUser().getUserSubscriptionData().getAccountType() == 1) {
            return false;
        }
        ProductProperties productProperties = this.d.getCurrentUser().getUserSubscriptionData().getProductProperties();
        if (productProperties != null) {
            return productProperties.isDownloadEnabled();
        }
        return d();
    }

    public boolean b(Context context) {
        if (GaanaApplication.sessionHistoryCount == 0) {
            return false;
        }
        if (Util.j() && GaanaApplication.sessionHistoryCount <= Constants.cP) {
            return false;
        }
        try {
            if ((context instanceof GaanaActivity) && ((((GaanaActivity) context).getCurrentFragment() instanceof AlbumDetailsMaterialListing) || (((GaanaActivity) context).getCurrentFragment() instanceof GaanaSpecialDetailsMaterialListing))) {
                if (!p() || Constants.i) {
                    return false;
                }
                return true;
            }
        } catch (Exception unused) {
        }
        if (!this.d.getCurrentUser().getLoginStatus()) {
            return true;
        }
        ProductProperties productProperties = this.d.getCurrentUser().getUserSubscriptionData().getProductProperties();
        if (productProperties != null) {
            return productProperties.isAdEnabled();
        }
        return d() ^ 1;
    }

    public boolean c(Context context) {
        return d.a().b("PREFERENCE_SESSION_HISTORY_COUNT", 0, false) != 0;
    }

    public boolean p() {
        if (GaanaApplication.sessionHistoryCount == 0) {
            return false;
        }
        if (!this.d.getCurrentUser().getLoginStatus() || this.d.getCurrentUser().getUserSubscriptionData().getAccountType() == 1) {
            return true;
        }
        ProductProperties productProperties = this.d.getCurrentUser().getUserSubscriptionData().getProductProperties();
        if (productProperties != null) {
            return productProperties.isAdEnabled();
        }
        return d() ^ 1;
    }

    public boolean q() {
        if (!this.d.getCurrentUser().getLoginStatus()) {
            return true;
        }
        ProductProperties productProperties = this.d.getCurrentUser().getUserSubscriptionData().getProductProperties();
        if (productProperties != null) {
            return productProperties.isAdEnabled();
        }
        return d() ^ 1;
    }

    public boolean r() {
        if (this.d.getCurrentUser().getLoginStatus()) {
            ProductProperties productProperties = this.d.getCurrentUser().getUserSubscriptionData().getProductProperties();
            if (!(productProperties == null || productProperties.isDownloadEnabled() || productProperties.isAdEnabled())) {
                return true;
            }
        }
        return false;
    }

    public boolean s() {
        if (!this.d.getCurrentUser().getLoginStatus()) {
            return false;
        }
        ProductProperties productProperties = this.d.getCurrentUser().getUserSubscriptionData().getProductProperties();
        if (productProperties != null) {
            return productProperties.isHighQualityStreamingEnabled();
        }
        return d();
    }

    public void a(Context context, ah ahVar) {
        Context context2 = context;
        this.f = ahVar;
        if (this.d == null) {
            this.d = (GaanaApplication) context.getApplicationContext();
        }
        long b = this.c.b(-1, "PREFERENCE_KEY_OFFLINE_MODE_START_TIME", true);
        long b2 = this.c.b(-1, "PREFERENCE_KEY_OFFLINE_MODE_LAST_REMINDER_TIME", true);
        if (System.currentTimeMillis() < b || System.currentTimeMillis() < b2) {
            new com.services.f(context2).a(context2.getString(R.string.app_name), context.getResources().getString(R.string.error_msg_device_time_changed), Boolean.valueOf(false), context2.getString(R.string.go_online), null, new b() {
                public void onCancelListner() {
                }

                public void onOkListner(String str) {
                    ap.this.v();
                }
            }, false);
            return;
        }
        long currentTimeMillis = (System.currentTimeMillis() - b) / MeasurementDispatcher.MILLIS_PER_DAY;
        long currentTimeMillis2 = b2 != -1 ? (System.currentTimeMillis() - b2) / MeasurementDispatcher.MILLIS_PER_DAY : currentTimeMillis;
        long time = (this.d.getCurrentUser().getUserSubscriptionData().getExpiryDate().getTime() - b) / MeasurementDispatcher.MILLIS_PER_DAY;
        if (time > 30) {
            time = 30;
        }
        if (currentTimeMillis2 >= 20 && currentTimeMillis <= time) {
            new com.services.f(context2).a(context2.getString(R.string.gaana_offline_mode), String.format(context.getResources().getString(R.string.reminder_msg_go_to_online_mode), new Object[]{Long.valueOf(currentTimeMillis)}), Boolean.valueOf(true), context.getResources().getString(R.string.go_online), context.getResources().getString(R.string.cancel), new b() {
                public void onOkListner(String str) {
                    ap.this.v();
                }

                public void onCancelListner() {
                    ap.this.c.a(System.currentTimeMillis(), "PREFERENCE_KEY_OFFLINE_MODE_LAST_REMINDER_TIME", true);
                }
            });
        }
        if (currentTimeMillis > time) {
            new com.services.f(context2).a(context2.getString(R.string.gaana_offline_mode_expired), context.getResources().getString(R.string.error_msg_offline_mode_expired), Boolean.valueOf(false), context.getResources().getString(R.string.go_online), null, new b() {
                public void onCancelListner() {
                }

                public void onOkListner(String str) {
                    ap.this.v();
                }
            }, false);
        } else {
            this.f.onOfflineModeValidated(true);
        }
    }

    private void v() {
        this.d.setAppInOfflineMode(false);
        this.c.a("PREFERENCE_KEY_OFFLINE_MODE", false, false);
        this.c.a(-1, "PREFERENCE_KEY_OFFLINE_MODE_START_TIME", true);
        this.c.a(-1, "PREFERENCE_KEY_OFFLINE_MODE_LAST_REMINDER_TIME", true);
        this.f.onOfflineModeValidated(false);
    }

    public void t() {
        try {
            DownloadManager.c().t();
        } catch (Exception unused) {
        }
    }

    public void d(Context context) {
        Date expiryDate = this.d.getCurrentUser().getUserSubscriptionData().getExpiryDate();
        Date expiryDateWithGrace = this.d.getCurrentUser().getUserSubscriptionData().getExpiryDateWithGrace();
        Date date = new Date();
        CharSequence string = context.getResources().getString(R.string.error_msg_gaana_plus_expiry_in_5_days);
        if (!(expiryDate == null || expiryDateWithGrace == null || ((!expiryDate.before(date) || !date.before(expiryDateWithGrace)) && (!date.before(expiryDateWithGrace) || !expiryDate.equals(expiryDateWithGrace))))) {
            string = this.d.getCurrentUser().getUserSubscriptionData().getGaanaPlusGraceMessage();
        }
        if (((int) ((((double) (this.d.getCurrentUser().getUserSubscriptionData().getExpiryDate().getTime() - new Date().getTime())) / 8.64E7d) + 0.5d)) == 5) {
            Toast.makeText(context, string, 0).show();
        }
    }

    public void e(Context context) {
        if (this.d.getCurrentUser().getUserSubscriptionData() != null && this.d.getCurrentUser().getUserSubscriptionData().getExpiryDate() != null) {
            String serverAccountType = this.d.getCurrentUser().getUserSubscriptionData().getServerAccountType();
            if (serverAccountType != null) {
                if ((serverAccountType.equalsIgnoreCase("paid") || serverAccountType.equalsIgnoreCase("trial")) && ((double) (this.d.getCurrentUser().getUserSubscriptionData().getExpiryDate().getTime() - System.currentTimeMillis())) / 8.64E7d < 0.0d) {
                    this.d.getCurrentUser().getUserSubscriptionData().setServerAccountType("free");
                    this.d.getCurrentUser().getUserSubscriptionData().setAccountType(1);
                    LoginManager.getInstance().saveUserInfoInSharedPreff();
                    q.a().b(this.d.getCurrentUser());
                    a(context);
                    t();
                }
            }
        }
    }

    private void a(Context context, String str, com.views.a.a aVar) {
        aj.a().a(context, str);
    }

    public void f(Context context) {
        if (context != null) {
            a(context, context.getResources().getString(R.string.error_msg_no_connection), com.views.a.c);
        }
    }

    public void a(Context context, String str) {
        if (context != null && str != null && str.length() != 0) {
            a(context, str, com.views.a.b);
        }
    }

    public boolean b(BusinessObject businessObject) {
        boolean z = true;
        if (businessObject == null) {
            return true;
        }
        String str = "";
        String str2 = "";
        String str3;
        if (businessObject instanceof Item) {
            ArrayList arrayList = (ArrayList) ((Item) businessObject).getEntityInfo();
            if (arrayList != null) {
                int size = arrayList.size();
                str3 = str2;
                str2 = str;
                for (int i = 0; i < size; i++) {
                    if (((EntityInfo) arrayList.get(i)).getKey().equals("country")) {
                        str2 = (String) ((EntityInfo) arrayList.get(i)).getValue();
                    } else if (((EntityInfo) arrayList.get(i)).getKey().equals("mobile")) {
                        str3 = (String) ((EntityInfo) arrayList.get(i)).getValue();
                    }
                    if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(r5)) {
                        break;
                    }
                }
            } else {
                str3 = str2;
                str2 = str;
            }
        } else {
            str2 = businessObject.getLocationAvailability();
            str3 = businessObject.getDeviceAvailability();
        }
        if (!("1".equalsIgnoreCase(str2) && "1".equalsIgnoreCase(r5))) {
            z = false;
        }
        return z;
    }

    public boolean c(BusinessObject businessObject) {
        boolean z = true;
        if (businessObject == null) {
            return true;
        }
        boolean z2 = false;
        if (!Constants.cL || PlayerManager.a(GaanaApplication.getContext()).m() == PlayerType.GAANA_RADIO) {
            return false;
        }
        if (businessObject instanceof Item) {
            ArrayList arrayList = (ArrayList) ((Item) businessObject).getEntityInfo();
            if (arrayList != null) {
                int size = arrayList.size();
                int i = 0;
                while (i < size) {
                    if (((EntityInfo) arrayList.get(i)).getKey().equals("parental_warning")) {
                        if (Double.compare(((Double) ((EntityInfo) arrayList.get(i)).getValue()).doubleValue(), 1.0d) != 0) {
                            z = false;
                        }
                        z2 = z;
                    } else {
                        i++;
                    }
                }
            }
        } else if (businessObject instanceof Track) {
            z2 = ((Track) businessObject).isParentalWarningEnabled();
        }
        return z2;
    }

    public boolean a(Album album) {
        return "1".equalsIgnoreCase(album.getLocationAvailability()) && "1".equalsIgnoreCase(album.getDeviceAvailability());
    }

    public boolean u() {
        if (!this.d.getCurrentUser().getLoginStatus() || this.d.getCurrentUser().getUserSubscriptionData() == null || !"paid".equalsIgnoreCase(this.d.getCurrentUser().getUserSubscriptionData().getServerAccountType()) || a().r()) {
            return false;
        }
        boolean z = true;
        try {
            if (Long.parseLong(this.d.getCurrentUser().getUserSubscriptionData().getValidUpTo()) < System.currentTimeMillis() / 1000) {
                z = false;
            }
            return z;
        } catch (Exception unused) {
            return true;
        }
    }

    public String g(String str) {
        String str2 = "";
        ArrayList gaanaMiniSubDetails = this.d.getCurrentUser().getUserSubscriptionData().getGaanaMiniSubDetails();
        if (gaanaMiniSubDetails != null) {
            int i = 0;
            while (i < gaanaMiniSubDetails.size()) {
                if (((GaanaMiniSubDetails) gaanaMiniSubDetails.get(i)).getEntityType().equalsIgnoreCase("AR") && ((GaanaMiniSubDetails) gaanaMiniSubDetails.get(i)).getPlaylistId().equalsIgnoreCase(str)) {
                    return ((GaanaMiniSubDetails) gaanaMiniSubDetails.get(i)).getEntityId();
                }
                i++;
            }
        }
        return str2;
    }
}
