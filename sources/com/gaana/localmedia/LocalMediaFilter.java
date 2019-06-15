package com.gaana.localmedia;

import com.e.a.c;
import com.gaana.adapter.ListAdapterSectionIndexer.OnFilterStarted;
import com.gaana.models.Albums.Album;
import com.gaana.models.BusinessObject;
import com.gaana.models.OfflineTrack;
import com.gaana.models.Tracks.Track;
import com.gaana.view.CustomListView.Header;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.x;
import java.util.ArrayList;

public class LocalMediaFilter {

    public static class AlbumFilter implements OnFilterStarted {
        private URLManager urlManager;

        public ArrayList<BusinessObject> onFilter(ArrayList<Object> arrayList, String str, boolean z, String str2, String str3, x xVar) {
            ArrayList<BusinessObject> arrayList2 = new ArrayList();
            if (!z) {
                ArrayList arrayList3 = new ArrayList();
                ArrayList arrayList4 = new ArrayList();
                for (int i = 0; i < arrayList.size(); i++) {
                    Album album = (Album) arrayList.get(i);
                    if (album.getRawName().toUpperCase().contains(str.toString().toUpperCase())) {
                        arrayList3.add(album);
                    } else if (album.getRawArtistNames().toUpperCase().contains(str.toString().toUpperCase())) {
                        arrayList4.add(album);
                    }
                }
                arrayList2.addAll(arrayList3);
                arrayList2.addAll(arrayList4);
                return arrayList2;
            } else if (xVar == null) {
                return c.a().a(BusinessObjectType.Albums, str, 0, 20, str2, str3).getArrListBusinessObj();
            } else {
                if (this.urlManager == null) {
                    this.urlManager = new URLManager();
                    this.urlManager.a(BusinessObjectType.Albums);
                }
                return xVar.a(this.urlManager, str, 0, 20, str2, str3).getArrListBusinessObj();
            }
        }
    }

    public static class GenericFilter implements OnFilterStarted {
        public ArrayList<BusinessObject> onFilter(ArrayList<Object> arrayList, String str, boolean z, String str2, String str3, x xVar) {
            ArrayList<BusinessObject> arrayList2 = new ArrayList();
            int i = 0;
            if (!z) {
                while (i < arrayList.size()) {
                    BusinessObject businessObject = (BusinessObject) arrayList.get(i);
                    if (!(businessObject instanceof Header) && businessObject.getRawName().toUpperCase().contains(str.toString().toUpperCase())) {
                        arrayList2.add(businessObject);
                    }
                    i++;
                }
                return arrayList2;
            } else if (arrayList == null || arrayList.size() <= 0) {
                return arrayList2;
            } else {
                BusinessObject businessObject2 = (BusinessObject) arrayList.get(0);
                if (businessObject2 == null || xVar == null) {
                    return businessObject2 != null ? c.a().a(businessObject2.getBusinessObjType(), str, 0, 20, str2, str3).getArrListBusinessObj() : arrayList2;
                } else {
                    URLManager uRLManager = new URLManager();
                    uRLManager.a(businessObject2.getBusinessObjType());
                    return xVar.a(uRLManager, str, 0, 20, str2, str3).getArrListBusinessObj();
                }
            }
        }
    }

    public static class SongFilter implements OnFilterStarted {
        private URLManager urlManager;

        public ArrayList<BusinessObject> onFilter(ArrayList<Object> arrayList, String str, boolean z, String str2, String str3, x xVar) {
            ArrayList arrayList2 = new ArrayList();
            ArrayList arrayList3 = new ArrayList();
            ArrayList arrayList4 = new ArrayList();
            ArrayList arrayList5 = new ArrayList();
            if (!z) {
                for (int i = 0; i < arrayList.size(); i++) {
                    Object obj = arrayList.get(i);
                    if (obj instanceof Track) {
                        Track track = (Track) obj;
                        if (track.getRawName().toUpperCase().contains(str.toString().toUpperCase())) {
                            arrayList3.add(track);
                        } else if (track.getArtistRawNames().toUpperCase().contains(str.toString().toUpperCase())) {
                            arrayList5.add(track);
                        } else if (track.getRawAlbumTitle().toUpperCase().contains(str.toString().toUpperCase())) {
                            arrayList4.add(track);
                        }
                    } else if (obj instanceof OfflineTrack) {
                        OfflineTrack offlineTrack = (OfflineTrack) obj;
                        if (offlineTrack.getRawName().toUpperCase().contains(str.toString().toUpperCase())) {
                            arrayList3.add(offlineTrack);
                        } else if (offlineTrack.getArtistRawName().toUpperCase().contains(str.toString().toUpperCase())) {
                            arrayList5.add(offlineTrack);
                        } else if (offlineTrack.getAlbumRawName() != null && offlineTrack.getAlbumName().toUpperCase().contains(str.toString().toUpperCase())) {
                            arrayList4.add(offlineTrack);
                        }
                    }
                }
            } else if (xVar != null) {
                if (this.urlManager == null) {
                    this.urlManager = new URLManager();
                    this.urlManager.a(BusinessObjectType.Tracks);
                }
                arrayList2 = xVar.a(this.urlManager, str, 0, 20, str2, str3).getArrListBusinessObj();
            } else {
                arrayList2 = c.a().a(BusinessObjectType.Tracks, str, 0, 20, str2, str3).getArrListBusinessObj();
            }
            if (arrayList2 == null) {
                arrayList2 = new ArrayList();
            }
            arrayList2.addAll(arrayList3);
            arrayList2.addAll(arrayList4);
            arrayList2.addAll(arrayList5);
            return arrayList2;
        }
    }
}
