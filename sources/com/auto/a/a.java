package com.auto.a;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore.Images.Media;
import android.support.v4.media.MediaBrowserCompat.MediaItem;
import android.support.v4.media.MediaDescriptionCompat.Builder;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v4.media.session.MediaSessionCompat.QueueItem;
import android.text.TextUtils;
import com.android.volley.VolleyError;
import com.android.volley.i.b;
import com.auto.AutoMediaBrowserService;
import com.constants.c.c;
import com.constants.c.d;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.localmedia.LocalMediaManager;
import com.gaana.models.Albums.Album;
import com.gaana.models.Artists.Artist;
import com.gaana.models.BusinessObject;
import com.gaana.models.Item;
import com.gaana.models.Items;
import com.gaana.models.LocalTrack;
import com.gaana.models.OfflineTrack;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Radios.Radio;
import com.gaana.models.Tracks.Track;
import com.i.i;
import com.i.j;
import com.managers.PlayerManager;
import com.managers.PlayerManager.PlayerType;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.models.PlayerTrack;
import com.services.l.s;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@TargetApi(21)
public class a implements com.android.volley.i.a, b {
    private int[] a = new int[]{R.string.home, R.string.radio, R.string.mymusic, R.string.queue};
    private int[] b = new int[]{R.drawable.auto_icon_home, R.drawable.auto_icon_radio, R.drawable.auto_icon_mymusic, R.drawable.auto_icon_queue};
    private int[] c = new int[]{R.string.top_charts_camel, R.string.trending_songs, R.string.new_releases};
    private int[] d = new int[]{R.drawable.auto_icon_topcharts, R.drawable.auto_icon_trending, R.drawable.auto_icon_newreleases};
    private int[] e = new int[]{R.string.radio_mirchi, R.string.gaana_radios};
    private int[] f = new int[]{R.string.songs, R.string.albums_camel, R.string.playlists, R.string.radios_title, R.string.artists_title, R.string.local_camel};
    private int[] g = new int[]{R.drawable.auto_icon_favsongs, R.drawable.auto_icon_favalbums, R.drawable.auto_icon_favplaylist, R.drawable.auto_icon_radio, R.drawable.auto_icon_favartist, R.drawable.auto_icon_favlocal};
    private List<BusinessObject> h = new ArrayList();
    private List<MediaMetadataCompat> i = new ArrayList();
    private String j;
    private boolean k = false;
    private a l;

    public interface a {
        void a(boolean z, String str);
    }

    public List<MediaItem> a() {
        ArrayList arrayList = new ArrayList(this.a.length);
        for (int i = 0; i < this.a.length; i++) {
            if (i != 3 || PlayerManager.a(GaanaApplication.getContext()).m() != PlayerType.GAANA_RADIO) {
                Builder builder = new Builder();
                String string = GaanaApplication.getContext().getResources().getString(this.a[i]);
                builder.setMediaId((String) com.auto.b.b.b.get(i));
                builder.setTitle(string);
                builder.setIconBitmap(BitmapFactory.decodeResource(GaanaApplication.getContext().getResources(), this.b[i]));
                arrayList.add(new MediaItem(builder.build(), 1));
            }
        }
        return arrayList;
    }

    public List<MediaItem> b() {
        ArrayList arrayList = new ArrayList(this.c.length);
        for (int i = 0; i < this.c.length; i++) {
            Builder builder = new Builder();
            String string = GaanaApplication.getContext().getResources().getString(this.c[i]);
            builder.setMediaId((String) com.auto.b.b.c.get(i));
            builder.setTitle(string);
            builder.setIconBitmap(BitmapFactory.decodeResource(GaanaApplication.getContext().getResources(), this.d[i]));
            arrayList.add(new MediaItem(builder.build(), 1));
        }
        return arrayList;
    }

    public List<MediaItem> c() {
        ArrayList arrayList = new ArrayList(this.e.length);
        for (int i = 0; i < this.e.length; i++) {
            Builder builder = new Builder();
            String string = GaanaApplication.getContext().getResources().getString(this.e[i]);
            builder.setMediaId((String) com.auto.b.b.d.get(i));
            builder.setTitle(string);
            arrayList.add(new MediaItem(builder.build(), 1));
        }
        return arrayList;
    }

    public List<BusinessObject> d() {
        return this.h;
    }

    public List<QueueItem> e() {
        ArrayList arrayList = new ArrayList();
        ArrayList n = PlayerManager.a(GaanaApplication.getContext()).n();
        if (n != null && n.size() > 0) {
            Iterator it = n.iterator();
            while (it.hasNext()) {
                PlayerTrack playerTrack = (PlayerTrack) it.next();
                Builder builder = new Builder();
                Track a = playerTrack.a(true);
                if (!(playerTrack == null || a == null)) {
                    String stringBuilder;
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("TR");
                    stringBuilder2.append(playerTrack.h());
                    builder.setMediaId(stringBuilder2.toString());
                    builder.setTitle(playerTrack.a(true).getName());
                    builder.setDescription(playerTrack.a(true).getAlbumTitle());
                    builder.setSubtitle(playerTrack.a(true).getAlbumTitle());
                    if (a.isLocalMedia) {
                        StringBuilder stringBuilder3 = new StringBuilder();
                        stringBuilder3.append("content://media/external/audio/albumart/");
                        stringBuilder3.append(a.getAlbumId());
                        stringBuilder = stringBuilder3.toString();
                    } else {
                        stringBuilder = playerTrack.a(true).getArtwork();
                    }
                    builder.setIconUri(Uri.parse(stringBuilder));
                    arrayList.add(new QueueItem(builder.build(), Long.valueOf(playerTrack.h()).longValue()));
                }
            }
        }
        return arrayList;
    }

    public List<MediaItem> f() {
        boolean loginStatus = GaanaApplication.getInstance().getCurrentUser().getLoginStatus();
        ArrayList arrayList = new ArrayList();
        if (loginStatus) {
            arrayList.add("Songs");
            arrayList.add("Albums");
            arrayList.add("Playlists");
            arrayList.add("Radios");
            arrayList.add("Artists");
            arrayList.add("Local");
        } else {
            arrayList.add("Local");
        }
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            Builder builder = new Builder();
            builder.setMediaId(loginStatus ? (String) com.auto.b.b.a.get(arrayList.indexOf(str)) : "Fav Local");
            builder.setTitle(GaanaApplication.getContext().getResources().getString(this.f[loginStatus ? arrayList.indexOf(str) : this.f.length - 1]));
            builder.setIconBitmap(BitmapFactory.decodeResource(GaanaApplication.getContext().getResources(), this.g[loginStatus ? arrayList.indexOf(str) : this.g.length - 1]));
            arrayList2.add(new MediaItem(builder.build(), 1));
        }
        return arrayList2;
    }

    public List<MediaItem> a(String str) {
        ArrayList arrayList = new ArrayList();
        if ("_parent_".equals(str)) {
            arrayList.addAll(a());
        } else if ("Home".equalsIgnoreCase(str)) {
            arrayList.addAll(b());
        } else if ("Radio".equalsIgnoreCase(str)) {
            arrayList.addAll(c());
        } else if (LocalMediaManager.MY_MUSIC.equalsIgnoreCase(str)) {
            arrayList.addAll(f());
        } else if ("Queue".equalsIgnoreCase(str)) {
            arrayList.addAll(h());
        } else {
            arrayList.addAll(c(str));
        }
        return arrayList;
    }

    private Collection<? extends MediaItem> c(String str) {
        if (this.i == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (MediaMetadataCompat description : this.i) {
            arrayList.add(new MediaItem(description.getDescription(), 2));
        }
        return arrayList;
    }

    public void onErrorResponse(VolleyError volleyError) {
        this.k = false;
        if (this.l != null) {
            this.l.a(false, this.j);
        }
    }

    public void onResponse(Object obj) {
        int i = 0;
        this.k = false;
        BusinessObject a = a((BusinessObject) obj);
        this.i.clear();
        if (!(a == null || a.getArrListBusinessObj() == null)) {
            this.h.clear();
            this.h.addAll(a.getArrListBusinessObj());
            while (i < a.getArrListBusinessObj().size()) {
                i++;
                this.i.add(a((BusinessObject) a.getArrListBusinessObj().get(i), i));
            }
        }
        if (this.l != null) {
            this.l.a(true, this.j);
        }
    }

    public void a(a aVar, String str) {
        b(aVar, str);
    }

    private synchronized void b(a aVar, String str) {
        try {
            if (com.auto.b.b.a.contains(str)) {
                b(str, aVar);
            } else {
                a(str, aVar);
            }
        } catch (Exception unused) {
        }
    }

    private MediaMetadataCompat a(Track track, int i) {
        long parseDouble;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("PT");
        stringBuilder.append(track.getBusinessObjId());
        String stringBuilder2 = stringBuilder.toString();
        long j = 0;
        try {
            parseDouble = (long) Double.parseDouble(track.getDuration());
        } catch (Exception unused) {
            parseDouble = 0;
        }
        if (!(track instanceof LocalTrack)) {
            parseDouble *= 1000;
        }
        if (PlayerManager.a(GaanaApplication.getContext()).m() != PlayerType.GAANA_RADIO) {
            j = parseDouble;
        }
        Bitmap bitmap = null;
        CharSequence charSequence = "";
        if (track.isLocalMedia()) {
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append("content://media/external/audio/albumart/");
            stringBuilder3.append(track.getAlbumId());
            charSequence = stringBuilder3.toString();
            if (!AutoMediaBrowserService.a()) {
                try {
                    bitmap = Media.getBitmap(GaanaApplication.getContext().getContentResolver(), Uri.parse(charSequence));
                } catch (Exception e) {
                    if (e.getMessage().contains("No entry for content")) {
                        bitmap = BitmapFactory.decodeResource(GaanaApplication.getContext().getResources(), R.drawable.placeholder_album_artwork_large);
                    }
                }
            }
        }
        MediaMetadataCompat.Builder putString = new MediaMetadataCompat.Builder().putString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID, stringBuilder2).putString(MediaMetadataCompat.METADATA_KEY_ALBUM, track.getAlbumTitle()).putString("android.media.metadata.ARTIST", track.getArtistNames()).putLong("android.media.metadata.DURATION", j).putString("android.media.metadata.TITLE", track.getTrackTitle()).putLong("android.media.metadata.TRACK_NUMBER", (long) i).putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_SUBTITLE, track.getArtistNames());
        if (bitmap != null) {
            putString.putBitmap(MediaMetadataCompat.METADATA_KEY_ALBUM_ART, bitmap).putBitmap(MediaMetadataCompat.METADATA_KEY_ART, bitmap).putBitmap(MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON, bitmap);
        } else if (TextUtils.isEmpty(charSequence)) {
            putString.putString(MediaMetadataCompat.METADATA_KEY_ALBUM_ART_URI, track.getArtworkLarge()).putString(MediaMetadataCompat.METADATA_KEY_ART_URI, track.getArtwork()).putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON_URI, track.getArtwork());
        } else {
            putString.putString(MediaMetadataCompat.METADATA_KEY_ALBUM_ART_URI, charSequence).putString(MediaMetadataCompat.METADATA_KEY_ART_URI, charSequence).putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON_URI, charSequence);
        }
        return putString.build();
    }

    public MediaMetadataCompat g() {
        PlayerTrack i = PlayerManager.a(GaanaApplication.getContext()).i();
        ArrayList n = PlayerManager.a(GaanaApplication.getContext()).n();
        if (i == null) {
            return null;
        }
        Track a = i.a(true);
        if (a == null || n == null || n.size() < 1) {
            return null;
        }
        int i2 = 0;
        for (int i3 = 0; i3 < n.size(); i3++) {
            if (((PlayerTrack) n.get(i3)).a(true).getBusinessObjId().equals(a.getBusinessObjId())) {
                i2 = i3;
                break;
            }
        }
        return a(a, i2 + 1);
    }

    private MediaMetadataCompat a(BusinessObject businessObject, int i) {
        Bitmap bitmap;
        BusinessObject businessObject2 = businessObject;
        String str = "";
        String str2 = "";
        String str3 = "";
        String str4 = "";
        String str5 = "";
        String str6 = "";
        String str7 = "";
        Bitmap bitmap2 = null;
        long j = 0;
        StringBuilder stringBuilder;
        if (businessObject2 instanceof Track) {
            stringBuilder = new StringBuilder();
            stringBuilder.append("TR");
            stringBuilder.append(businessObject.getBusinessObjId());
            str7 = stringBuilder.toString();
            Track track = (Track) businessObject2;
            str2 = track.getTrackTitle();
            str3 = track.getArtistNames();
            str4 = track.getAlbumTitle();
            str5 = track.getAlbumTitle();
            try {
                j = (long) Double.parseDouble(((Track) businessObject2).getDuration());
            } catch (NumberFormatException unused) {
            }
            bitmap = null;
            String artwork = track.getArtwork();
            str6 = track.getArtworkLarge();
            str = str2;
            str2 = str5;
            str5 = artwork;
        } else {
            String englishName;
            if (businessObject2 instanceof Playlist) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("PL");
                stringBuilder.append(businessObject.getBusinessObjId());
                str7 = stringBuilder.toString();
                str = businessObject.getEnglishName();
                str2 = businessObject.getEnglishName();
                Playlist playlist = (Playlist) businessObject2;
                str5 = playlist.getArtwork();
                str6 = playlist.getArtwork();
            } else if (businessObject2 instanceof Album) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("AL");
                stringBuilder.append(businessObject.getBusinessObjId());
                str7 = stringBuilder.toString();
                str = businessObject.getEnglishName();
                Album album = (Album) businessObject2;
                str2 = album.getArtistNames();
                str3 = album.getArtistNames();
                str5 = album.getArtwork();
                str6 = album.getArtwork();
            } else if (businessObject2 instanceof Artist) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("AR");
                stringBuilder.append(businessObject.getBusinessObjId());
                str7 = stringBuilder.toString();
                str = businessObject.getEnglishName();
                str2 = businessObject.getName();
                Artist artist = (Artist) businessObject2;
                str5 = artist.getArtwork();
                str6 = artist.getArtwork();
            } else if (businessObject2 instanceof Radio) {
                stringBuilder = new StringBuilder();
                Radio radio = (Radio) businessObject2;
                stringBuilder.append(radio.getType());
                stringBuilder.append(businessObject.getBusinessObjId());
                str7 = stringBuilder.toString();
                str = businessObject.getEnglishName();
                englishName = businessObject.getEnglishName();
                str5 = radio.getArtwork();
                str6 = radio.getArtwork();
                str2 = englishName;
            } else if (businessObject2 instanceof OfflineTrack) {
                stringBuilder = new StringBuilder();
                stringBuilder.append("TR");
                stringBuilder.append(businessObject.getBusinessObjId());
                str7 = stringBuilder.toString();
                str = businessObject.getName();
                OfflineTrack offlineTrack = (OfflineTrack) businessObject2;
                str3 = offlineTrack.getArtistName();
                str4 = offlineTrack.getAlbumName();
                str2 = offlineTrack.getAlbumName();
                englishName = offlineTrack.getImageUrl();
                if (!TextUtils.isEmpty(englishName) && englishName.contains("http")) {
                    str5 = englishName;
                } else if (!TextUtils.isEmpty(englishName)) {
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("content://media/external/audio/albumart/");
                    stringBuilder2.append(englishName);
                    str5 = stringBuilder2.toString();
                }
                bitmap = BitmapFactory.decodeResource(GaanaApplication.getContext().getResources(), R.drawable.placeholder_album_artwork_large);
                if (!AutoMediaBrowserService.a()) {
                    try {
                        bitmap2 = Media.getBitmap(GaanaApplication.getContext().getContentResolver(), Uri.parse(str5));
                    } catch (Exception e) {
                        if (e.getMessage().contains("No entry for content")) {
                            bitmap2 = bitmap;
                        }
                    }
                }
            }
            bitmap = null;
        }
        MediaMetadataCompat.Builder builder = new MediaMetadataCompat.Builder();
        builder.putString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID, str7).putString(MediaMetadataCompat.METADATA_KEY_ALBUM, str4).putString("android.media.metadata.ARTIST", str3).putLong("android.media.metadata.DURATION", j).putString("android.media.metadata.TITLE", str).putLong("android.media.metadata.TRACK_NUMBER", (long) i).putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_SUBTITLE, str2);
        if (TextUtils.isEmpty(str6)) {
            str6 = str5;
        }
        if (bitmap2 != null) {
            builder.putBitmap(MediaMetadataCompat.METADATA_KEY_ART, bitmap).putBitmap(MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON, bitmap2).putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON_URI, str5).putBitmap(MediaMetadataCompat.METADATA_KEY_ALBUM_ART, bitmap);
        } else {
            builder.putString(MediaMetadataCompat.METADATA_KEY_ALBUM_ART_URI, str6).putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_ICON_URI, str5).putString(MediaMetadataCompat.METADATA_KEY_ART_URI, str6);
        }
        return builder.build();
    }

    public Collection<? extends MediaItem> h() {
        ArrayList<MediaMetadataCompat> arrayList = new ArrayList();
        ArrayList n = PlayerManager.a(GaanaApplication.getContext()).n();
        if (n != null) {
            int size = n.size();
            for (int i = 0; i < size; i++) {
                Track a = ((PlayerTrack) n.get(i)).a(true);
                if (a != null) {
                    arrayList.add(a(a, i + 1));
                }
            }
        }
        n = new ArrayList();
        for (MediaMetadataCompat description : arrayList) {
            n.add(new MediaItem(description.getDescription(), 2));
        }
        return n;
    }

    private BusinessObject a(BusinessObject businessObject) {
        BusinessObject businessObject2 = new BusinessObject();
        if (!(businessObject instanceof Items)) {
            return businessObject;
        }
        Items items = (Items) businessObject;
        ArrayList arrayList = new ArrayList();
        Iterator it = items.getArrListBusinessObj().iterator();
        while (it.hasNext()) {
            Object obj = (Item) it.next();
            if (obj.getEntityType().equals(c.c)) {
                obj = (Track) com.auto.b.a.a(obj);
            } else if (obj.getEntityType().equals(c.b)) {
                obj = (Album) com.auto.b.a.c(obj);
            } else if (obj.getEntityType().equals(c.a)) {
                obj = (Playlist) com.auto.b.a.d(obj);
            } else if (obj.getEntityType().equals(d.d) || obj.getEntityType().equals(d.c)) {
                obj = (Radio) com.auto.b.a.b(obj);
            }
            arrayList.add(obj);
            businessObject2.setArrListBusinessObj(arrayList);
        }
        return businessObject2;
    }

    private void a(String str, a aVar) {
        this.l = aVar;
        if (this.k && !TextUtils.isEmpty(this.j)) {
            j.a().a(this.j);
        }
        URLManager uRLManager = new URLManager();
        if (str.equals("Trending Songs")) {
            uRLManager.a("https://apiv2.gaana.com/home/trending/songs");
            uRLManager.a(true);
            this.k = true;
        } else if (str.equals("Top Charts")) {
            uRLManager.a("https://apiv2.gaana.com/home/playlist/top-charts");
            this.k = true;
        } else if (str.equals("New Releases")) {
            uRLManager.a("https://apiv2.gaana.com/home/album/featured");
            this.k = true;
        } else if (str.equals("Gaana Radio")) {
            uRLManager.a("https://api.gaana.com/home/gaana-radio-listing?limit=0,20");
            this.k = true;
        } else if (str.equals("Radio Mirchi")) {
            uRLManager.a("https://api.gaana.com/home/radio/mirchi");
            uRLManager.c(Boolean.valueOf(true));
            this.k = true;
        }
        uRLManager.a(BusinessObjectType.GenericItems);
        i.a().a(uRLManager, str, (b) this, (com.android.volley.i.a) this);
        this.j = str;
    }

    private void b(final String str, final a aVar) {
        URLManager uRLManager = new URLManager();
        if (str.equals("Fav Songs")) {
            uRLManager.a(BusinessObjectType.Tracks);
        } else if (str.equals("Fav Albums")) {
            uRLManager.a(BusinessObjectType.Albums);
        } else if (str.equals("Fav PLaylists")) {
            uRLManager.a(BusinessObjectType.Playlists);
        } else if (str.equals("Fav Radios")) {
            uRLManager.a(BusinessObjectType.Radios);
        } else if (str.equals("Fav Artists")) {
            uRLManager.a(BusinessObjectType.Artists);
        } else if (str.equals("Fav Local")) {
            uRLManager.d(true);
        }
        if (uRLManager.n() && str.equals("Fav Local")) {
            com.i.d.a(new Runnable() {
                public void run() {
                    try {
                        BusinessObject localSongs = LocalMediaManager.getInstance(GaanaApplication.getContext()).getLocalSongs("", true);
                        a.this.i.clear();
                        if (localSongs != null && localSongs.getArrListBusinessObj() != null) {
                            a.this.h.clear();
                            a.this.h.addAll(localSongs.getArrListBusinessObj());
                            int i = 0;
                            while (i < localSongs.getArrListBusinessObj().size()) {
                                i++;
                                a.this.i.add(a.this.a((BusinessObject) localSongs.getArrListBusinessObj().get(i), i));
                            }
                            if (aVar != null) {
                                aVar.a(true, str);
                            }
                        }
                    } catch (Exception unused) {
                        if (aVar != null) {
                            aVar.a(false, str);
                        }
                    }
                }
            });
        } else {
            i.a().a(new s() {
                public void onRetreivalComplete(BusinessObject businessObject) {
                    businessObject = a.this.a(businessObject);
                    a.this.i.clear();
                    if (!(businessObject == null || businessObject.getArrListBusinessObj() == null)) {
                        a.this.h.clear();
                        a.this.h.addAll(businessObject.getArrListBusinessObj());
                        int i = 0;
                        while (i < businessObject.getArrListBusinessObj().size()) {
                            i++;
                            a.this.i.add(a.this.a((BusinessObject) businessObject.getArrListBusinessObj().get(i), i));
                        }
                    }
                    if (aVar != null) {
                        aVar.a(true, str);
                    }
                }

                public void onErrorResponse(BusinessObject businessObject) {
                    if (aVar != null) {
                        aVar.a(false, str);
                    }
                }
            }, uRLManager, "", 0, 50, "added_on", "desc");
        }
    }

    public BusinessObject b(String str) {
        if (this.h != null) {
            for (int i = 0; i < this.h.size(); i++) {
                BusinessObject businessObject = (BusinessObject) this.h.get(i);
                if (businessObject.getBusinessObjId().equals(str)) {
                    return businessObject;
                }
            }
        }
        return null;
    }
}
