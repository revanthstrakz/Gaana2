package com.logging;

import com.collapsible_header.SongParallexListingFragment;
import com.fragments.BaseGaanaFragment;
import com.fragments.DownloadDetailsFragment;
import com.fragments.LocalMediaFragment;
import com.fragments.PlayerFragmentV2;
import com.fragments.RevampedDetailListing;
import com.fragments.SearchEnchancedFragment;
import com.gaana.application.GaanaApplication;
import com.gaana.fragments.BaseFragment;
import com.gaana.localmedia.LocalMediaManager;
import com.gaana.models.Albums.Album;
import com.gaana.models.Artists.Artist;
import com.gaana.models.BusinessObject;
import com.gaana.models.Item;
import com.gaana.models.OfflineTrack;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Playlists.Playlist.PlaylistSourceType;
import com.gaana.models.Tracks.Track;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.logging.GaanaLogger.SOURCE_TYPE;
import com.managers.URLManager.BusinessObjectType;
import com.models.PlayerTrack;
import java.util.ArrayList;
import java.util.Iterator;

public class d {
    private static d a;
    private static GaanaApplication b;

    public static d a() {
        if (a == null) {
            a = new d();
            b = (GaanaApplication) GaanaApplication.getContext();
        }
        return a;
    }

    public PlayerTrack a(BaseGaanaFragment baseGaanaFragment, BusinessObject businessObject) {
        if (businessObject instanceof Track) {
            Track track = (Track) businessObject;
            String albumId = track.getAlbumId();
            String englishAlbumTitle = track.getEnglishAlbumTitle();
            SOURCE_TYPE source_type = SOURCE_TYPE.OTHER;
            boolean z = baseGaanaFragment instanceof RevampedDetailListing;
            BusinessObject a;
            BusinessObjectType parentBusinessObjType;
            if (!z && b.getListingComponents() != null && b.getListingComponents().a() != null) {
                a = b.getListingComponents().a();
                parentBusinessObjType = track.getParentBusinessObjType();
                if (parentBusinessObjType == BusinessObjectType.Albums) {
                    source_type = SOURCE_TYPE.ALBUM;
                } else if (parentBusinessObjType == BusinessObjectType.Artists) {
                    source_type = SOURCE_TYPE.ARTIST;
                    if (a instanceof Artist) {
                        albumId = a.getBusinessObjId();
                        englishAlbumTitle = a.getEnglishName();
                    }
                } else if (parentBusinessObjType == BusinessObjectType.Playlists || parentBusinessObjType == BusinessObjectType.TopCharts) {
                    source_type = SOURCE_TYPE.PLAYLIST;
                    if (a instanceof Playlist) {
                        if (((Playlist) a).getPlaylistSourceType() == PlaylistSourceType.HOURLY_PLAYLIST) {
                            source_type = SOURCE_TYPE.HOURLY_PLAYLIST;
                        }
                        albumId = a.getBusinessObjId();
                        englishAlbumTitle = a.getEnglishName();
                    }
                }
            } else if (z) {
                a = ((RevampedDetailListing) baseGaanaFragment).s();
                parentBusinessObjType = track.getParentBusinessObjType();
                if (parentBusinessObjType == BusinessObjectType.Albums) {
                    source_type = SOURCE_TYPE.ALBUM;
                } else if (parentBusinessObjType == BusinessObjectType.Artists) {
                    source_type = SOURCE_TYPE.ARTIST;
                    if (a instanceof Artist) {
                        albumId = a.getBusinessObjId();
                        englishAlbumTitle = a.getEnglishName();
                    }
                } else if (parentBusinessObjType == BusinessObjectType.Playlists || parentBusinessObjType == BusinessObjectType.TopCharts) {
                    source_type = SOURCE_TYPE.PLAYLIST;
                    if (a instanceof Playlist) {
                        if (((Playlist) a).getPlaylistSourceType() == PlaylistSourceType.HOURLY_PLAYLIST) {
                            source_type = SOURCE_TYPE.HOURLY_PLAYLIST;
                        }
                        albumId = a.getBusinessObjId();
                        englishAlbumTitle = a.getEnglishName();
                    }
                }
            }
            if (baseGaanaFragment == null) {
                albumId = businessObject.getBusinessObjId();
                englishAlbumTitle = "AndroidAuto";
            } else if (baseGaanaFragment instanceof SearchEnchancedFragment) {
                source_type = SOURCE_TYPE.SEARCH;
                albumId = businessObject.getBusinessObjId();
                englishAlbumTitle = businessObject.getEnglishName();
            } else if (baseGaanaFragment instanceof DownloadDetailsFragment) {
                source_type = SOURCE_TYPE.OTHER;
                albumId = businessObject.getBusinessObjId();
                englishAlbumTitle = "Your downloads";
            } else if (baseGaanaFragment instanceof LocalMediaFragment) {
                source_type = SOURCE_TYPE.LOCAL_MUSIC;
                albumId = businessObject.getBusinessObjId();
                englishAlbumTitle = "My music";
            } else {
                boolean z2 = baseGaanaFragment instanceof SongParallexListingFragment;
                if (z2 && PLAYOUT_SECTION_TYPE.HOME_CAROUSEL_VIEW.name().equalsIgnoreCase(((SongParallexListingFragment) baseGaanaFragment).a().p())) {
                    source_type = SOURCE_TYPE.SHOWCASE_VPL;
                } else if (z2 && "MADE_FOR_YOU".equalsIgnoreCase(baseGaanaFragment.getSectionNameForReturn())) {
                    englishAlbumTitle = baseGaanaFragment.getSourceNameForVPL();
                }
            }
            PlayerTrack playerTrack = new PlayerTrack(track, albumId, source_type.ordinal(), englishAlbumTitle);
            if (baseGaanaFragment != null) {
                playerTrack.f(baseGaanaFragment.getPageName());
            } else {
                playerTrack.f("AndroidAuto");
            }
            return playerTrack;
        } else if (!(businessObject instanceof OfflineTrack)) {
            return null;
        } else {
            PlayerTrack playerTrack2 = new PlayerTrack();
            playerTrack2.e(businessObject.getBusinessObjId());
            if (businessObject.isLocalMedia()) {
                playerTrack2.a(LocalMediaManager.getInstance(GaanaApplication.getContext()).getTrackFromLocalMedia((OfflineTrack) businessObject));
                playerTrack2.c("My music");
                playerTrack2.b(SOURCE_TYPE.LOCAL_MUSIC.ordinal());
            } else {
                playerTrack2.c("Your downloads");
                playerTrack2.b(SOURCE_TYPE.OTHER.ordinal());
            }
            if (baseGaanaFragment != null) {
                playerTrack2.f(baseGaanaFragment.getPageName());
            } else {
                playerTrack2.f("AndroidAuto");
            }
            return playerTrack2;
        }
    }

    public PlayerTrack a(BaseFragment baseFragment, BusinessObject businessObject, boolean z) {
        if (businessObject instanceof Track) {
            Track track = (Track) businessObject;
            String albumId = track.getAlbumId();
            String seedTrackId = track.getSeedTrackId();
            String englishAlbumTitle = track.getEnglishAlbumTitle();
            SOURCE_TYPE source_type = SOURCE_TYPE.OTHER;
            if (!(b.getListingComponents() == null || b.getListingComponents().a() == null)) {
                BusinessObject a = b.getListingComponents().a();
                BusinessObjectType parentBusinessObjType = track.getParentBusinessObjType();
                if (parentBusinessObjType == BusinessObjectType.Albums) {
                    source_type = SOURCE_TYPE.ALBUM;
                } else if (parentBusinessObjType == BusinessObjectType.Artists) {
                    source_type = SOURCE_TYPE.ARTIST;
                    if (a instanceof Artist) {
                        albumId = a.getBusinessObjId();
                        englishAlbumTitle = a.getEnglishName();
                    }
                } else if (parentBusinessObjType == BusinessObjectType.Playlists || parentBusinessObjType == BusinessObjectType.TopCharts) {
                    source_type = SOURCE_TYPE.PLAYLIST;
                    if (a instanceof Playlist) {
                        if (((Playlist) a).getPlaylistSourceType() == PlaylistSourceType.HOURLY_PLAYLIST) {
                            source_type = SOURCE_TYPE.HOURLY_PLAYLIST;
                        }
                        albumId = a.getBusinessObjId();
                        englishAlbumTitle = a.getEnglishName();
                    }
                }
            }
            if (baseFragment == null) {
                albumId = businessObject.getBusinessObjId();
                englishAlbumTitle = "AndroidAuto";
            } else if (baseFragment instanceof PlayerFragmentV2) {
                source_type = SOURCE_TYPE.SEARCH;
                albumId = businessObject.getBusinessObjId();
                englishAlbumTitle = businessObject.getEnglishName();
            }
            PlayerTrack playerTrack = new PlayerTrack(track, albumId, source_type.ordinal(), englishAlbumTitle);
            playerTrack.b(SOURCE_TYPE.CF_TRACK.ordinal());
            playerTrack.b(seedTrackId);
            playerTrack.a(track.getSmartDownload());
            if (baseFragment != null) {
                playerTrack.f("Player");
            } else {
                playerTrack.f("AndroidAuto");
            }
            return playerTrack;
        } else if (!(businessObject instanceof OfflineTrack)) {
            return null;
        } else {
            PlayerTrack playerTrack2 = new PlayerTrack();
            playerTrack2.b(SOURCE_TYPE.CF_TRACK.ordinal());
            playerTrack2.e(businessObject.getBusinessObjId());
            OfflineTrack offlineTrack = (OfflineTrack) businessObject;
            playerTrack2.a(offlineTrack.getSmartDownload());
            if (businessObject.isLocalMedia()) {
                playerTrack2.a(LocalMediaManager.getInstance(GaanaApplication.getContext()).getTrackFromLocalMedia(offlineTrack));
                playerTrack2.c("My music");
                playerTrack2.b(SOURCE_TYPE.LOCAL_MUSIC.ordinal());
            } else {
                playerTrack2.c("Your downloads");
                playerTrack2.b(SOURCE_TYPE.OTHER.ordinal());
            }
            if (baseFragment != null) {
                playerTrack2.f("Player");
            } else {
                playerTrack2.f("AndroidAuto");
            }
            return playerTrack2;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0070  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x008e  */
    public com.models.PlayerTrack b(com.fragments.BaseGaanaFragment r7, com.gaana.models.BusinessObject r8) {
        /*
        r6 = this;
        r0 = r8 instanceof com.gaana.models.Item;
        r1 = 0;
        if (r0 == 0) goto L_0x00b8;
    L_0x0005:
        r0 = r8;
        r0 = (com.gaana.models.Item) r0;
        r2 = com.logging.GaanaLogger.SOURCE_TYPE.OTHER;
        r3 = b;
        r3 = r3.getListingComponents();
        if (r3 == 0) goto L_0x006b;
    L_0x0012:
        r3 = b;
        r3 = r3.getListingComponents();
        r3 = r3.a();
        if (r3 == 0) goto L_0x006b;
    L_0x001e:
        r3 = b;
        r3 = r3.getListingComponents();
        r3 = r3.a();
        r4 = r0.getParentBusinessObjType();
        r5 = com.managers.URLManager.BusinessObjectType.Albums;
        if (r4 != r5) goto L_0x0033;
    L_0x0030:
        r2 = com.logging.GaanaLogger.SOURCE_TYPE.ALBUM;
        goto L_0x006b;
    L_0x0033:
        r5 = com.managers.URLManager.BusinessObjectType.Artists;
        if (r4 != r5) goto L_0x0046;
    L_0x0037:
        r2 = com.logging.GaanaLogger.SOURCE_TYPE.ARTIST;
        r4 = r3 instanceof com.gaana.models.Artists.Artist;
        if (r4 == 0) goto L_0x006b;
    L_0x003d:
        r1 = r3.getBusinessObjId();
        r3 = r3.getEnglishName();
        goto L_0x006c;
    L_0x0046:
        r5 = com.managers.URLManager.BusinessObjectType.Playlists;
        if (r4 == r5) goto L_0x004e;
    L_0x004a:
        r5 = com.managers.URLManager.BusinessObjectType.TopCharts;
        if (r4 != r5) goto L_0x006b;
    L_0x004e:
        r2 = com.logging.GaanaLogger.SOURCE_TYPE.PLAYLIST;
        r4 = r3 instanceof com.gaana.models.Playlists.Playlist;
        if (r4 == 0) goto L_0x006b;
    L_0x0054:
        r1 = r3;
        r1 = (com.gaana.models.Playlists.Playlist) r1;
        r1 = r1.getPlaylistSourceType();
        r4 = com.gaana.models.Playlists.Playlist.PlaylistSourceType.HOURLY_PLAYLIST;
        if (r1 != r4) goto L_0x0062;
    L_0x005f:
        r1 = com.logging.GaanaLogger.SOURCE_TYPE.HOURLY_PLAYLIST;
        r2 = r1;
    L_0x0062:
        r1 = r3.getBusinessObjId();
        r3 = r3.getEnglishName();
        goto L_0x006c;
    L_0x006b:
        r3 = r1;
    L_0x006c:
        r4 = r7 instanceof com.fragments.SearchFragment;
        if (r4 == 0) goto L_0x008e;
    L_0x0070:
        r1 = b;
        r1 = r1.getListingComponents();
        r1 = r1.f();
        r2 = com.managers.GaanaSearchManager.SearchType.Radio;
        if (r1 != r2) goto L_0x0082;
    L_0x007e:
        r1 = com.logging.GaanaLogger.SOURCE_TYPE.RADIO_SEARCH_SONG;
    L_0x0080:
        r2 = r1;
        goto L_0x0085;
    L_0x0082:
        r1 = com.logging.GaanaLogger.SOURCE_TYPE.SEARCH;
        goto L_0x0080;
    L_0x0085:
        r1 = r8.getBusinessObjId();
        r3 = r8.getEnglishName();
        goto L_0x00a7;
    L_0x008e:
        r4 = r7 instanceof com.fragments.DownloadDetailsFragment;
        if (r4 == 0) goto L_0x009b;
    L_0x0092:
        r2 = com.logging.GaanaLogger.SOURCE_TYPE.OTHER;
        r1 = r8.getBusinessObjId();
        r3 = "Your downloads";
        goto L_0x00a7;
    L_0x009b:
        r4 = r7 instanceof com.fragments.LocalMediaFragment;
        if (r4 == 0) goto L_0x00a7;
    L_0x009f:
        r2 = com.logging.GaanaLogger.SOURCE_TYPE.LOCAL_MUSIC;
        r1 = r8.getBusinessObjId();
        r3 = "My music";
    L_0x00a7:
        r8 = new com.models.PlayerTrack;
        r2 = r2.ordinal();
        r8.<init>(r0, r1, r2, r3);
        r7 = r7.getPageName();
        r8.f(r7);
        return r8;
    L_0x00b8:
        r0 = r8 instanceof com.gaana.models.OfflineTrack;
        if (r0 == 0) goto L_0x0104;
    L_0x00bc:
        r0 = new com.models.PlayerTrack;
        r0.<init>();
        r1 = r8.getBusinessObjId();
        r0.e(r1);
        r1 = r8.isLocalMedia();
        if (r1 == 0) goto L_0x00ee;
    L_0x00ce:
        r1 = com.gaana.application.GaanaApplication.getContext();
        r1 = com.gaana.localmedia.LocalMediaManager.getInstance(r1);
        r8 = (com.gaana.models.OfflineTrack) r8;
        r8 = r1.getTrackFromLocalMedia(r8);
        r0.a(r8);
        r8 = "My music";
        r0.c(r8);
        r8 = com.logging.GaanaLogger.SOURCE_TYPE.LOCAL_MUSIC;
        r8 = r8.ordinal();
        r0.b(r8);
        goto L_0x00fc;
    L_0x00ee:
        r8 = "Your downloads";
        r0.c(r8);
        r8 = com.logging.GaanaLogger.SOURCE_TYPE.OTHER;
        r8 = r8.ordinal();
        r0.b(r8);
    L_0x00fc:
        r7 = r7.getPageName();
        r0.f(r7);
        return r0;
    L_0x0104:
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.logging.d.b(com.fragments.BaseGaanaFragment, com.gaana.models.BusinessObject):com.models.PlayerTrack");
    }

    public ArrayList<PlayerTrack> a(BaseGaanaFragment baseGaanaFragment, ArrayList<BusinessObject> arrayList) {
        if (arrayList == null) {
            return null;
        }
        int i = 0;
        if (arrayList.size() > 0) {
            i = arrayList.get(0) instanceof Item;
        }
        ArrayList<PlayerTrack> arrayList2 = new ArrayList();
        Iterator it;
        if (i != 0) {
            try {
                it = arrayList.iterator();
                while (it.hasNext()) {
                    arrayList2.add(b(baseGaanaFragment, (BusinessObject) it.next()));
                }
                return arrayList2;
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
                return arrayList2;
            }
        }
        it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(a(baseGaanaFragment, (BusinessObject) it.next()));
        }
        return arrayList2;
    }

    public ArrayList<PlayerTrack> a(BaseGaanaFragment baseGaanaFragment, ArrayList<BusinessObject> arrayList, int i) {
        if (arrayList == null) {
            return null;
        }
        ArrayList<PlayerTrack> arrayList2 = new ArrayList();
        while (i < arrayList.size()) {
            try {
                arrayList2.add(a(baseGaanaFragment, (BusinessObject) arrayList.get(i)));
                i++;
            } catch (Exception e) {
                ThrowableExtension.printStackTrace(e);
                return arrayList2;
            }
        }
        return arrayList2;
    }

    public int a(BusinessObject businessObject) {
        SOURCE_TYPE source_type = SOURCE_TYPE.OTHER;
        if (businessObject instanceof Album) {
            source_type = SOURCE_TYPE.ALBUM;
        } else if (businessObject instanceof Artist) {
            source_type = SOURCE_TYPE.ARTIST;
        } else if (businessObject instanceof Playlist) {
            if (((Playlist) businessObject).getPlaylistSourceType() == PlaylistSourceType.HOURLY_PLAYLIST) {
                source_type = SOURCE_TYPE.HOURLY_PLAYLIST;
            } else {
                source_type = SOURCE_TYPE.PLAYLIST;
            }
        }
        return source_type.ordinal();
    }
}
