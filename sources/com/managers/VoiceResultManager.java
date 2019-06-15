package com.managers;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.speech.tts.UtteranceProgressListener;
import android.text.TextUtils;
import com.android.volley.Request.Priority;
import com.constants.Constants;
import com.constants.c;
import com.constants.c.d;
import com.fragments.BaseGaanaFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.localmedia.LocalMediaManager;
import com.gaana.login.LoginManager;
import com.gaana.models.Albums.Album;
import com.gaana.models.Artists.Artist;
import com.gaana.models.BusinessObject;
import com.gaana.models.NextGenSearchAutoSuggests;
import com.gaana.models.NextGenSearchAutoSuggests.AutoComplete;
import com.gaana.models.NextGenSearchAutoSuggests.GroupItem;
import com.gaana.models.OfflineTrack;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Radios;
import com.gaana.models.Radios.Radio;
import com.gaana.models.Tracks;
import com.gaana.models.Tracks.Track;
import com.google.android.gms.actions.SearchIntents;
import com.i.i;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSessionContext;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.logging.GaanaLogger.SOURCE_TYPE;
import com.managers.DownloadManager.DownloadStatus;
import com.managers.PlayerManager.PlayerType;
import com.managers.URLManager.BusinessObjectType;
import com.models.PlayerTrack;
import com.player_framework.PlayerConstants.PauseReasons;
import com.player_framework.PlayerStatus;
import com.player_framework.o;
import com.services.l.s;
import com.utilities.Util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

public class VoiceResultManager {
    private final Context a;
    private final TextToSpeech b = new TextToSpeech(this.a, new OnInitListener() {
        public void onInit(int i) {
            if (i == 0) {
                VoiceResultManager.this.a(VoiceResultManager.this.b);
                VoiceResultManager.this.b();
                VoiceResultManager.this.h = true;
            }
        }
    });
    private a c;
    private BaseGaanaFragment d;
    private AutoComplete e;
    private boolean f;
    private HashMap<String, String> g;
    private boolean h;

    enum SearchCategory {
        Track,
        Album,
        Artist,
        Playlist,
        Radio,
        OfflineTrack
    }

    public interface a {
        void a(String str);

        void a(ArrayList<Track> arrayList, BusinessObject businessObject, int i, String str, String str2);
    }

    public VoiceResultManager(Context context) {
        this.a = context;
    }

    public void a(a aVar) {
        this.c = aVar;
    }

    private void a(TextToSpeech textToSpeech) {
        int language = textToSpeech.setLanguage(new Locale("en", "IN"));
        if (language == -1 || language == -2) {
            textToSpeech.setLanguage(Locale.US);
        }
    }

    public void a(ArrayList<String> arrayList) {
        URLManager uRLManager = new URLManager();
        HashMap hashMap = new HashMap();
        hashMap.put("geoLocation", Constants.ct);
        hashMap.put(SearchIntents.EXTRA_QUERY, arrayList.get(0));
        hashMap.put("content_filter", InternalAvidAdSessionContext.AVID_API_LEVEL);
        hashMap.put("include", "allItems");
        if (!TextUtils.isEmpty(GaanaSearchManager.a().d())) {
            hashMap.put("usrLang", GaanaSearchManager.a().d());
        }
        hashMap.put("isRegSrch", GaanaSearchManager.a().e());
        hashMap.put("readPlayFlag", "1");
        hashMap.put("autoPlayFlag", "1");
        hashMap.put("interactionFlag", "1");
        if (GaanaApplication.getInstance().getCurrentUser().getLoginStatus()) {
            if (ap.a().e()) {
                hashMap.put("UserType", InternalAvidAdSessionContext.AVID_API_LEVEL);
            } else if (ap.a().i()) {
                hashMap.put("UserType", "1");
            } else {
                hashMap.put("UserType", "0");
            }
        }
        uRLManager.a(hashMap);
        uRLManager.a("https://gsearch.gaana.com/gaanasearch-api/mobilesuggest/autosuggest-lite-vi1?");
        uRLManager.b(1);
        uRLManager.i(false);
        uRLManager.a(NextGenSearchAutoSuggests.class);
        uRLManager.b(Boolean.valueOf(false));
        i.a().a(new s() {
            public void onRetreivalComplete(BusinessObject businessObject) {
                if (!(businessObject == null || VoiceResultManager.this.c == null)) {
                    BusinessObject businessObject2 = null;
                    ArrayList arrayList = new ArrayList();
                    NextGenSearchAutoSuggests nextGenSearchAutoSuggests = (NextGenSearchAutoSuggests) businessObject;
                    if (nextGenSearchAutoSuggests.getGroupItems() == null || nextGenSearchAutoSuggests.getGroupItems().size() <= 0) {
                        VoiceResultManager.this.a(VoiceResultManager.this.a.getResources().getString(R.string.voice_error_speech));
                        VoiceResultManager.this.c.a(VoiceResultManager.this.a.getResources().getString(R.string.voice_result_error_msg_no_results));
                    } else {
                        ArrayList autocomplete = ((GroupItem) nextGenSearchAutoSuggests.getGroupItems().get(0)).getAutocomplete();
                        if (autocomplete.size() > 1) {
                            Iterator it = autocomplete.iterator();
                            while (it.hasNext()) {
                                businessObject2 = VoiceResultManager.this.a((AutoComplete) it.next());
                                if (businessObject2 instanceof Track) {
                                    arrayList.add((Track) businessObject2);
                                }
                            }
                        } else {
                            businessObject2 = VoiceResultManager.this.a((AutoComplete) ((GroupItem) nextGenSearchAutoSuggests.getGroupItems().get(0)).getAutocomplete().get(0));
                        }
                        BusinessObject businessObject3 = businessObject2;
                        if (businessObject3 == null) {
                            VoiceResultManager.this.a(VoiceResultManager.this.a.getResources().getString(R.string.voice_error_speech));
                            VoiceResultManager.this.c.a(VoiceResultManager.this.a.getResources().getString(R.string.voice_result_error_msg_no_results));
                            return;
                        }
                        GaanaSearchManager.a().b(true);
                        URLManager uRLManager;
                        StringBuilder stringBuilder;
                        if (businessObject3.getBusinessObjType() == BusinessObjectType.Tracks) {
                            if (arrayList.size() == 0) {
                                arrayList.add((Track) businessObject3);
                            }
                            VoiceResultManager.this.c.a(arrayList, businessObject3, nextGenSearchAutoSuggests.getAction(), nextGenSearchAutoSuggests.getVoiceTxt(), nextGenSearchAutoSuggests.getSpeechText());
                            if (arrayList.size() <= 1) {
                                VoiceResultManager.this.a(businessObject3, nextGenSearchAutoSuggests.getAction(), false, nextGenSearchAutoSuggests.getVoiceTxt(), nextGenSearchAutoSuggests.getSpeechText());
                            }
                        } else if (businessObject3.getBusinessObjType() == BusinessObjectType.Artists) {
                            uRLManager = new URLManager();
                            uRLManager.a(BusinessObjectType.GenericItems);
                            uRLManager.b(BusinessObjectType.Artists);
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(c.q);
                            stringBuilder.append(businessObject3.getBusinessObjId());
                            uRLManager.a(stringBuilder.toString());
                            uRLManager.b(Boolean.valueOf(true));
                            uRLManager.a(true);
                            uRLManager.f(true);
                            uRLManager.a(Priority.HIGH);
                            uRLManager.i(true);
                            VoiceResultManager.this.a(uRLManager, arrayList, businessObject3, nextGenSearchAutoSuggests.getAction(), nextGenSearchAutoSuggests.getVoiceTxt(), nextGenSearchAutoSuggests.getSpeechText());
                        } else if (businessObject3.getBusinessObjType() == BusinessObjectType.Albums) {
                            uRLManager = new URLManager();
                            uRLManager.a(BusinessObjectType.Tracks);
                            uRLManager.b(BusinessObjectType.Albums);
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(c.s);
                            stringBuilder.append(businessObject3.getBusinessObjId());
                            uRLManager.a(stringBuilder.toString());
                            uRLManager.b(Boolean.valueOf(true));
                            uRLManager.f(true);
                            uRLManager.a(Priority.HIGH);
                            uRLManager.i(true);
                            VoiceResultManager.this.a(uRLManager, arrayList, businessObject3, nextGenSearchAutoSuggests.getAction(), nextGenSearchAutoSuggests.getVoiceTxt(), nextGenSearchAutoSuggests.getSpeechText());
                        } else if (businessObject3.getBusinessObjType() == BusinessObjectType.Playlists) {
                            uRLManager = new URLManager();
                            uRLManager.a(BusinessObjectType.Tracks);
                            uRLManager.b(BusinessObjectType.Playlists);
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(c.w);
                            stringBuilder.append("playlist_id=");
                            stringBuilder.append(businessObject3.getBusinessObjId());
                            stringBuilder.append("&playlist_type=");
                            stringBuilder.append(((Playlist) businessObject3).getPlaylistType());
                            uRLManager.a(stringBuilder.toString());
                            uRLManager.b(Boolean.valueOf(true));
                            uRLManager.f(true);
                            uRLManager.a(Priority.HIGH);
                            uRLManager.i(true);
                            VoiceResultManager.this.a(uRLManager, arrayList, businessObject3, nextGenSearchAutoSuggests.getAction(), nextGenSearchAutoSuggests.getVoiceTxt(), nextGenSearchAutoSuggests.getSpeechText());
                        } else if (businessObject3.getBusinessObjType() == BusinessObjectType.Radios) {
                            VoiceResultManager.this.a(businessObject3, nextGenSearchAutoSuggests.getAction(), false, nextGenSearchAutoSuggests.getVoiceTxt(), nextGenSearchAutoSuggests.getSpeechText());
                        }
                    }
                }
            }

            public void onErrorResponse(BusinessObject businessObject) {
                String speechText = businessObject instanceof NextGenSearchAutoSuggests ? ((NextGenSearchAutoSuggests) businessObject).getSpeechText() : null;
                VoiceResultManager voiceResultManager = VoiceResultManager.this;
                if (TextUtils.isEmpty(speechText)) {
                    speechText = VoiceResultManager.this.a.getResources().getString(R.string.voice_error_speech);
                }
                voiceResultManager.a(speechText);
                if (VoiceResultManager.this.c != null) {
                    VoiceResultManager.this.c.a(VoiceResultManager.this.a.getResources().getString(R.string.voice_result_error_msg_no_results));
                }
            }
        }, uRLManager);
    }

    private boolean b(BusinessObject businessObject) {
        if (businessObject.getBusinessObjType() == BusinessObjectType.Tracks && DownloadManager.c().e(Integer.parseInt(businessObject.getBusinessObjId())) != null) {
            return true;
        }
        if ((businessObject.getBusinessObjType() == BusinessObjectType.Playlists || businessObject.getBusinessObjType() == BusinessObjectType.Albums) && DownloadManager.c().h(Integer.parseInt(businessObject.getBusinessObjId())) != null) {
            return true;
        }
        return false;
    }

    public BusinessObject a(AutoComplete autoComplete) {
        this.e = autoComplete;
        String rawTitle = autoComplete.getRawTitle();
        String language = autoComplete.getLanguage();
        String replace = TextUtils.isEmpty(autoComplete.getAtw()) ? autoComplete.getArtwork().replace("80x80", "480x480") : Util.r(autoComplete.getAtw());
        String rawSubtitle = autoComplete.getRawSubtitle();
        String businessObjectId = autoComplete.getBusinessObjectId();
        BusinessObject businessObject = new BusinessObject();
        if (TextUtils.isEmpty(autoComplete.getAutoType())) {
            return null;
        }
        switch (SearchCategory.valueOf(autoComplete.getAutoType())) {
            case Artist:
                businessObject = new Artist();
                businessObject.setBusinessObjType(BusinessObjectType.Artists);
                ((Artist) businessObject).setArtwork(replace);
                break;
            case Radio:
                businessObject = new Radio();
                businessObject.setBusinessObjType(BusinessObjectType.Radios);
                Radio radio = (Radio) businessObject;
                radio.setArtwork(replace);
                radio.setType(autoComplete.getRadioType());
                GaanaSearchManager.a().a(autoComplete);
                break;
            case Album:
                businessObject = new Album();
                businessObject.setBusinessObjType(BusinessObjectType.Albums);
                ((Album) businessObject).setArtwork(replace);
                break;
            case Playlist:
                businessObject = new Playlist();
                businessObject.setBusinessObjType(BusinessObjectType.Playlists);
                Playlist playlist = (Playlist) businessObject;
                playlist.setArtwork(replace);
                playlist.setPlaylistId(businessObjectId);
                break;
            case Track:
                businessObject = new Track();
                businessObject.setBusinessObjType(BusinessObjectType.Tracks);
                Track track = (Track) businessObject;
                track.setArtwork(replace);
                track.setAlbumName(rawSubtitle);
                GaanaSearchManager.a().a(autoComplete);
                break;
            case OfflineTrack:
                businessObject = new OfflineTrack(businessObjectId, rawTitle, rawSubtitle);
                businessObject.setBusinessObjType(BusinessObjectType.Tracks);
                break;
        }
        businessObject.setBusinessObjId(businessObjectId);
        businessObject.setName(rawTitle);
        businessObject.setLanguage(language);
        businessObject.setLocalMedia(autoComplete.isLocalMedia());
        return businessObject;
    }

    public void a(BusinessObject businessObject, int i, boolean z, String str, String str2) {
        if (businessObject.getBusinessObjType() == BusinessObjectType.Radios) {
            a(businessObject, i, str, str2);
        } else {
            if (businessObject.getBusinessObjType() == BusinessObjectType.Tracks) {
                if ("1".equalsIgnoreCase(businessObject.getLocationAvailability()) && "0".equalsIgnoreCase(businessObject.getDeviceAvailability())) {
                    if (GaanaSearchManager.a().g() != null) {
                        GaanaSearchManager.a().g().a(this.a);
                    }
                    ap.a().a(this.a, this.a.getString(R.string.error_msg_content_unavailable_for_device));
                    return;
                } else if ("0".equalsIgnoreCase(businessObject.getLocationAvailability()) && "1".equalsIgnoreCase(businessObject.getDeviceAvailability())) {
                    if (GaanaSearchManager.a().g() != null) {
                        GaanaSearchManager.a().g().a(this.a);
                    }
                    ap.a().a(this.a, this.a.getString(R.string.error_msg_content_unavailable_for_location));
                    return;
                } else if (!(DownloadManager.c().e(Integer.parseInt(businessObject.getBusinessObjId())) != DownloadStatus.DOWNLOADED || !Util.v() || DownloadManager.c().f(Integer.parseInt(businessObject.getBusinessObjId())) || ap.a().o() || DownloadManager.c().j(businessObject.getBusinessObjId()).booleanValue())) {
                    if (GaanaSearchManager.a().g() != null) {
                        GaanaSearchManager.a().g().a(this.a);
                    }
                    aj.a().a(this.a, this.a.getResources().getString(R.string.downloaded_songs_stream_online));
                }
            }
            if ((!GaanaApplication.getInstance().isAppInOfflineMode() && Util.j(this.a)) || businessObject.isLocalMedia() || b(businessObject)) {
                this.d = ((GaanaActivity) this.a).getCurrentFragment();
                if (i != 1 || z) {
                    GaanaApplication.getInstance().setPlayoutSectionName(PLAYOUT_SECTION_TYPE.VOICEINT_PLAY.name());
                } else {
                    GaanaApplication.getInstance().setPlayoutSectionName(PLAYOUT_SECTION_TYPE.VOICEINT_AUTOPLAY.name());
                }
                if (i == 1) {
                    a((Track) businessObject);
                }
            } else {
                if (GaanaSearchManager.a().g() != null) {
                    GaanaSearchManager.a().g().a(this.a);
                }
                ap.a().f(this.a);
            }
        }
    }

    public void a(URLManager uRLManager, ArrayList<Track> arrayList, BusinessObject businessObject, int i, String str, String str2) {
        final ArrayList<Track> arrayList2 = arrayList;
        final BusinessObject businessObject2 = businessObject;
        final int i2 = i;
        final String str3 = str;
        final String str4 = str2;
        i.a().a(new s() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(BusinessObject businessObject) {
                if (businessObject != null) {
                    ArrayList arrListBusinessObj = ((Tracks) businessObject).getArrListBusinessObj();
                    if (arrListBusinessObj != null) {
                        arrayList2.addAll(arrListBusinessObj);
                        if (VoiceResultManager.this.c != null) {
                            VoiceResultManager.this.c.a(arrayList2, businessObject2, i2, str3, str4);
                        }
                    } else if (VoiceResultManager.this.c != null) {
                        VoiceResultManager.this.a(VoiceResultManager.this.a.getResources().getString(R.string.voice_error_speech));
                        VoiceResultManager.this.c.a(VoiceResultManager.this.a.getResources().getString(R.string.voice_result_error_msg_no_results));
                    }
                }
            }
        }, uRLManager);
    }

    private void a(Track track) {
        if (!track.isLocalMedia()) {
            if ("1".equalsIgnoreCase(track.getLocationAvailability()) && "0".equalsIgnoreCase(track.getDeviceAvailability())) {
                ap.a().a(this.a, this.a.getString(R.string.error_msg_content_unavailable_for_device));
                return;
            } else if ("0".equalsIgnoreCase(track.getLocationAvailability()) && "1".equalsIgnoreCase(track.getDeviceAvailability())) {
                ap.a().a(this.a, this.a.getString(R.string.error_msg_content_unavailable_for_location));
                return;
            } else if (GaanaApplication.getInstance().isAppInOfflineMode() && !DownloadManager.c().l(Integer.parseInt(track.getBusinessObjId())).booleanValue()) {
                ((BaseActivity) this.a).displayFeatureNotAvailableOfflineDialog("This song");
                return;
            } else if (!(Util.j(this.a) || DownloadManager.c().l(Integer.parseInt(track.getBusinessObjId())).booleanValue())) {
                ap.a().f(this.a);
                return;
            }
        }
        PlayerTrack playerTrack = new PlayerTrack(track, track.getAlbumId(), SOURCE_TYPE.OTHER.ordinal(), track.getName());
        if (this.d != null) {
            playerTrack.f(this.d.getPageName());
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(playerTrack);
        PlayerManager.a(this.a).a(arrayList, playerTrack, 999999);
        PlayerManager.a(this.a).a(PlayerType.GAANA, this.a);
        ((GaanaActivity) this.a).setUpdatePlayerFragment();
    }

    private void a(BusinessObject businessObject, final int i, final String str, final String str2) {
        URLManager a = Constants.a(((Radio) businessObject).getType(), businessObject.getBusinessObjId(), false);
        if (a != null) {
            ((BaseActivity) this.a).showProgressDialog();
            i.a().a(new s() {
                public void onRetreivalComplete(BusinessObject businessObject) {
                    ((BaseActivity) VoiceResultManager.this.a).hideProgressDialog();
                    if (businessObject != null && businessObject.getArrListBusinessObj() != null && businessObject.getArrListBusinessObj().size() > 0) {
                        VoiceResultManager.this.a((Radio) ((Radios) businessObject).getArrListBusinessObj().get(0), i, str, str2);
                    }
                }

                public void onErrorResponse(BusinessObject businessObject) {
                    ((BaseActivity) VoiceResultManager.this.a).hideProgressDialog();
                }
            }, a);
        }
    }

    private void a(Radio radio, int i, String str, String str2) {
        if (this.c != null) {
            this.c.a(null, radio, i, str, str2);
        }
        StringBuilder stringBuilder;
        if (radio.getType().equals(d.c)) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(((BaseActivity) this.a).currentScreen);
            stringBuilder.append(" - RadioMirchi - ");
            stringBuilder.append(radio.getEnglishName());
            ((BaseActivity) this.a).sendGAEvent(((BaseActivity) this.a).currentScreen, "Play", stringBuilder.toString());
            ad.a(this.a).a((BusinessObject) radio);
            return;
        }
        stringBuilder = new StringBuilder();
        stringBuilder.append(((BaseActivity) this.a).currentScreen);
        stringBuilder.append(" - GaanaRadio - ");
        stringBuilder.append(radio.getEnglishName());
        ((BaseActivity) this.a).sendGAEvent(((BaseActivity) this.a).currentScreen, "Play", stringBuilder.toString());
        ad.a(this.a).a("https://api.gaana.com/radio.php?type=radio&subtype=radiodetail&radio_id=<radio_id>&radio_type=<radio_type>&limit=0,50".replace("<radio_id>", radio.getBusinessObjId()).replace("<radio_type>", radio.getType()), SOURCE_TYPE.GAANA_RADIO.ordinal(), (BusinessObject) radio);
    }

    public void a(BusinessObject businessObject) {
        if (businessObject != null) {
            if (this.e != null && (businessObject.getBusinessObjType() == BusinessObjectType.Albums || businessObject.getBusinessObjType() == BusinessObjectType.Playlists)) {
                GaanaSearchManager.a().a(this.e);
            }
            if (!businessObject.isLocalMedia() && b(businessObject)) {
                af.a(this.a, null).a((int) R.id.albumMenu, DownloadManager.c().a(businessObject.getBusinessObjType(), businessObject.getBusinessObjId()), false);
            } else if (businessObject.isLocalMedia()) {
                af.a(this.a, null).a((int) R.id.albumMenu, LocalMediaManager.getInstance(this.a).getLocalItemById(businessObject.getBusinessObjType(), businessObject.getBusinessObjId()), false);
            } else {
                URLManager a;
                if (businessObject.getBusinessObjType() == BusinessObjectType.Radios) {
                    a = Constants.a(((Radio) businessObject).getType(), businessObject.getBusinessObjId(), false);
                } else {
                    a = Constants.a(businessObject.getBusinessObjType(), businessObject.getBusinessObjId(), false);
                }
                if (a != null) {
                    ((BaseActivity) this.a).showProgressDialog(Boolean.valueOf(true), this.a.getString(R.string.fetching_details));
                    i.a().a(new s() {
                        public void onRetreivalComplete(BusinessObject businessObject) {
                            ((BaseActivity) VoiceResultManager.this.a).hideProgressDialog();
                            if (businessObject != null && businessObject.getArrListBusinessObj() != null && businessObject.getArrListBusinessObj().size() > 0) {
                                af.a(VoiceResultManager.this.a, null).a((int) R.id.albumMenu, (BusinessObject) businessObject.getArrListBusinessObj().get(0), false);
                            }
                        }

                        public void onErrorResponse(BusinessObject businessObject) {
                            ((BaseActivity) VoiceResultManager.this.a).hideProgressDialog();
                        }
                    }, a);
                }
            }
        }
    }

    public void a(boolean z) {
        this.h = z;
    }

    public TextToSpeech a() {
        return this.b;
    }

    public void a(String str) {
        if (this.g == null) {
            this.g = new HashMap();
            this.g.put("utteranceId", LoginManager.TAG_SUBTYPE_GAANA);
        }
        if (!TextUtils.isEmpty(str) && this.h) {
            this.b.speak(str, 0, this.g);
        }
    }

    private void b() {
        this.b.setOnUtteranceProgressListener(new UtteranceProgressListener() {
            public void onError(String str) {
            }

            public void onStart(String str) {
            }

            public void onDone(String str) {
                if (VoiceResultManager.this.f) {
                    VoiceResultManager.this.f = false;
                    VoiceResultManager.this.a(VoiceResultManager.this.a, true);
                }
            }
        });
    }

    public void b(boolean z) {
        this.f = z;
    }

    public void a(Context context, boolean z) {
        if (!f.v().w()) {
            if (z) {
                if (PlayerStatus.a(this.a).d()) {
                    o.a(this.a);
                }
            } else if (PlayerStatus.a(context).c() || PlayerStatus.a(context).b()) {
                o.a(this.a, PauseReasons.MEDIA_BUTTON_TAP);
            }
        }
    }
}
