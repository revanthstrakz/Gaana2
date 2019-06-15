package com.services;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import com.collapsible_header.SongParallexListingFragment;
import com.constants.Constants;
import com.constants.c.d;
import com.dynamicview.DynamicHomeFragment;
import com.dynamicview.DynamicOccasionFragment;
import com.dynamicview.DynamicViewManager;
import com.dynamicview.DynamicViewManager.DynamicViewType;
import com.dynamicview.f.a;
import com.fragments.BaseGaanaFragment;
import com.fragments.GridActivityFragment;
import com.fragments.PersonaDedicationFragment;
import com.fragments.RadioActivityFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.Login;
import com.gaana.OnBoardArtistPreferenceActivity;
import com.gaana.OnBoardLanguagePreferenceActivityNew;
import com.gaana.R;
import com.gaana.SplashScreenActivity;
import com.gaana.analytics.MoEngage;
import com.gaana.application.GaanaApplication;
import com.gaana.juke.JukePartyFragment;
import com.gaana.juke.JukePlaylist;
import com.gaana.localmedia.LocalMediaManager;
import com.gaana.localmedia.PlaylistSyncManager;
import com.gaana.models.Artists.Artist;
import com.gaana.models.BusinessObject;
import com.gaana.models.Notifications.Notification;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.ProfileUsers.ProfileUser;
import com.gaana.models.PushNotification;
import com.gaana.models.Radios.Radio;
import com.gaana.models.Tracks.Track;
import com.gaana.models.UserRecentActivity;
import com.gaana.view.GaanaYourYearView.GAANA_ENTRY_PAGE;
import com.google.android.gms.analytics.ecommerce.Promotion;
import com.google.android.gms.plus.PlusShare;
import com.google.gson.GsonBuilder;
import com.i.i;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.logging.GaanaLogger.SOURCE_TYPE;
import com.managers.DownloadManager;
import com.managers.PlayerManager;
import com.managers.PlayerManager.PlayerType;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.ad;
import com.managers.af;
import com.managers.aj;
import com.managers.an;
import com.managers.ap;
import com.managers.f;
import com.managers.g;
import com.managers.l;
import com.managers.u;
import com.models.ListingButton;
import com.models.ListingComponents;
import com.models.ListingParams;
import com.models.PlayerTrack;
import com.player_framework.GaanaMusicService;
import com.player_framework.PlayerConstants.PauseReasons;
import com.player_framework.o;
import com.services.l.ag;
import com.services.l.ba;
import com.services.l.s;
import com.services.l.y;
import com.utilities.Util;
import com.youtube.YouTubePlayerActivity;
import com.youtube.YouTubePlayerActivity.Orientation;
import com.youtube.YouTubeVideos.YouTubeVideo;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONObject;

public class c {
    public static String a = "DeepLinkingManager";
    public static boolean b = false;
    public static boolean c = true;
    private static c d;
    private static PlayerTrack n;
    private GaanaApplication e = null;
    private BusinessObject f;
    private URLManager g;
    private String h = null;
    private String i = null;
    private String j = null;
    private String k = null;
    private String l = null;
    private boolean m = false;

    private c(Context context) {
    }

    public static c a(Context context) {
        return a(context, false);
    }

    public static c a(Context context, boolean z) {
        d = new c(context);
        d.e = (GaanaApplication) context.getApplicationContext();
        d.m = z;
        return d;
    }

    public boolean a() {
        return b;
    }

    public boolean a(Context context, GaanaApplication gaanaApplication, boolean z) {
        if (GaanaApplication.targetUri == null) {
            return false;
        }
        this.e = gaanaApplication;
        this.h = GaanaApplication.targetUri;
        GaanaApplication.targetUri = null;
        GaanaApplication.onBoardingSkipped = true;
        return c(context);
    }

    public boolean a(Context context, Intent intent, GaanaApplication gaanaApplication) {
        if (intent == null) {
            return false;
        }
        this.e = gaanaApplication;
        a(intent);
        Uri data = intent.getData();
        if (this.h == null) {
            Bundle extras = intent.getExtras();
            if (!(extras == null || TextUtils.isEmpty(extras.getString("data")))) {
                try {
                    JSONObject jSONObject = new JSONObject(extras.getString("data"));
                    this.h = jSONObject.getString("url");
                    if (jSONObject.has("title")) {
                        this.j = jSONObject.getString("title");
                    }
                    if (jSONObject.has("notificationTrackData")) {
                        u.a().b("Download Notification", "DN_Clicks");
                        this.i = jSONObject.getString("notificationTrackData");
                    }
                } catch (Exception unused) {
                    this.h = null;
                }
            }
        }
        if (!(data == null || data.getScheme() == null)) {
            String trim = data.getScheme().trim();
            String[] split;
            Track songByid;
            if (trim.equalsIgnoreCase("gaanagoogle")) {
                this.h = intent.getDataString();
                b = true;
                return b(context);
            } else if (trim.equalsIgnoreCase("content") && Constants.Q) {
                if (data.toString().contains("/audio/media/")) {
                    split = data.toString().split("/audio/media/");
                    if (split.length >= 2) {
                        songByid = LocalMediaManager.getInstance(context).getSongByid(split[split.length - 1]);
                        if (songByid != null) {
                            a(context, songByid);
                            return true;
                        }
                        aj.a().a(context, context.getResources().getString(R.string.UnableToPlay));
                        return false;
                    }
                }
            } else if (trim.equalsIgnoreCase("file") && Constants.Q) {
                split = data.getPath().split("/");
                if (split.length >= 2) {
                    songByid = LocalMediaManager.getInstance(context).getSongByTitle(split[split.length - 1]);
                    if (songByid != null) {
                        a(context, songByid);
                        return true;
                    }
                    aj.a().a(context, context.getResources().getString(R.string.UnableToPlay));
                    return false;
                }
            }
        }
        return c(context);
    }

    private void a(Context context, Track track) {
        n = new PlayerTrack(track, track.getBusinessObjId(), SOURCE_TYPE.LOCAL_MUSIC.ordinal(), track.getEnglishName());
        n.f(GaanaApplication.getInstance().getPageName());
        b(context, true);
    }

    public static PlayerTrack b() {
        return n;
    }

    public static void a(PlayerTrack playerTrack) {
        n = playerTrack;
    }

    private boolean b(Context context) {
        if (this.h != null) {
            this.h = this.h.replace("gaanagoogle://", "");
            if (this.h.contains("?")) {
                if (this.h.contains("moengage")) {
                    MoEngage.getInstance().reportNotificationClickedEvent(this.h.substring(8, this.h.indexOf("?")));
                }
                this.h = this.h.substring(0, this.h.indexOf("?"));
            }
            BusinessObject businessObject;
            if (this.h.trim().startsWith("song")) {
                businessObject = new BusinessObject();
                businessObject.setBusinessObjType(BusinessObjectType.Tracks);
                this.k = this.h.split("/")[1];
                a(context, businessObject, SOURCE_TYPE.IN_APP.ordinal());
            } else if (this.h.trim().startsWith("radio/")) {
                this.f = new BusinessObject();
                this.f.setBusinessObjType(BusinessObjectType.Radios);
                this.k = this.h.split("/")[1];
                b = false;
                a(context, false, (int) R.id.LeftMenuRadio);
            } else if (this.h.trim().startsWith("album") || this.h.trim().startsWith("playlist") || this.h.trim().startsWith("artist")) {
                businessObject = new BusinessObject();
                this.k = this.h.split("/")[1];
                if (this.h.startsWith("album")) {
                    businessObject.setBusinessObjType(BusinessObjectType.Albums);
                } else if (this.h.startsWith("playlist")) {
                    businessObject.setBusinessObjType(BusinessObjectType.Playlists);
                } else if (this.h.startsWith("artist")) {
                    businessObject.setBusinessObjType(BusinessObjectType.Artists);
                }
                a(context, businessObject, SOURCE_TYPE.PUSH_NOTIFICATION.ordinal());
            } else if (this.h.trim().startsWith("discover")) {
                b = false;
                a(context, false, (int) R.id.LeftMenuDiscover);
            } else {
                b = false;
                b(context, false);
            }
        }
        return true;
    }

    public boolean a(Context context, String str, GaanaApplication gaanaApplication) {
        this.h = str;
        this.e = gaanaApplication;
        return c(context);
    }

    private boolean c(Context context) {
        Context context2 = context;
        if (this.h != null) {
            if (this.h.contains("?")) {
                if (this.h.contains("moengage")) {
                    MoEngage.getInstance().reportNotificationClickedEvent(this.h.substring(8, this.h.indexOf("?")));
                }
                if (this.h.contains("autoplay=true")) {
                    u.a().b("Google Assistant", "App Open");
                    this.l = "play";
                }
                this.h = this.h.substring(0, this.h.indexOf("?"));
            }
            String str;
            Intent intent;
            Bundle bundle;
            if (this.h.contains("/juke/")) {
                str = this.h.split("/juke/")[1];
                JukePlaylist jukePlaylist = new JukePlaylist();
                if (context2 instanceof GaanaActivity) {
                    ((GaanaActivity) context2).displayFragment(JukePartyFragment.newInstance(jukePlaylist, -1, str, false));
                    return true;
                } else if (context2 instanceof SplashScreenActivity) {
                    intent = new Intent(context2, GaanaActivity.class);
                    bundle = new Bundle();
                    bundle.putString("DEEPLINKING_SCREEN_EXTRA_PARAM", this.h);
                    intent.putExtras(bundle);
                    context2.startActivity(intent);
                    ((SplashScreenActivity) context2).finish();
                    return true;
                } else if (!(context2 instanceof OnBoardArtistPreferenceActivity)) {
                    return false;
                } else {
                    intent = new Intent(context2, GaanaActivity.class);
                    bundle = new Bundle();
                    bundle.putString("DEEPLINKING_SCREEN_EXTRA_PARAM", this.h);
                    intent.putExtras(bundle);
                    context2.startActivity(intent);
                    ((OnBoardArtistPreferenceActivity) context2).finish();
                    return true;
                }
            } else if (this.h.contains("gaana.com/weblink")) {
                a(context2, false, (int) R.id.LeftMenuWebView, this.h, this.j);
                return true;
            } else {
                if (this.h.contains("https://gaana.com/")) {
                    this.h = this.h.replace("https://gaana.com", Promotion.ACTION_VIEW);
                } else if (this.h.contains("http://gaana.com/")) {
                    this.h = this.h.replace("http://gaana.com", Promotion.ACTION_VIEW);
                } else if (this.h.contains("https://touch.gaana.com/")) {
                    this.h = this.h.replace("https://touch.gaana.com", Promotion.ACTION_VIEW);
                } else if (this.h.contains("http://touch.gaana.com/")) {
                    this.h = this.h.replace("http://touch.gaana.com", Promotion.ACTION_VIEW);
                } else if (this.h.contains("https://www.gaana.com/")) {
                    this.h = this.h.replace("https://www.gaana.com", Promotion.ACTION_VIEW);
                } else if (this.h.contains("http://www.gaana.com/")) {
                    this.h = this.h.replace("http://www.gaana.com", Promotion.ACTION_VIEW);
                } else if (this.h.contains("https://m.gaana.com/")) {
                    this.h = this.h.replace("https://m.gaana.com", Promotion.ACTION_VIEW);
                } else if (this.h.contains("http://m.gaana.com/")) {
                    this.h = this.h.replace("http://m.gaana.com", Promotion.ACTION_VIEW);
                } else if (this.h.contains("https://touch.gaana.com/")) {
                    this.h = this.h.replace("https://touch.gaana.com", Promotion.ACTION_VIEW);
                } else if (this.h.contains("http://touch.gaana.com/")) {
                    this.h = this.h.replace("http://touch.gaana.com", Promotion.ACTION_VIEW);
                } else if (this.h.contains("https://ipad.gaana.com/")) {
                    this.h = this.h.replace("https://ipad.gaana.com", Promotion.ACTION_VIEW);
                } else if (this.h.contains("http://ipad.gaana.com/")) {
                    this.h = this.h.replace("http://ipad.gaana.com", Promotion.ACTION_VIEW);
                } else if (this.h.contains("https://www.get.gaana.com/")) {
                    this.h = this.h.replace("https://ipad.gaana.com", "view/browse");
                } else if (this.h.contains("http://www.get.gaana.com/")) {
                    this.h = this.h.replace("http://ipad.gaana.com", "view/browse");
                }
                String[] split;
                if (this.h.contains("view/browse")) {
                    a(context2, false, (int) R.id.LeftMenuHome);
                    return true;
                } else if (this.h.contains("http://") || this.h.contains("https://")) {
                    this.h = this.h.replace("gaana://", "");
                    a(context2, false, (int) R.id.LeftMenuWebView, this.h, this.j);
                    return true;
                } else if (this.h.contains("view/themeSettings")) {
                    a(context2, false, (int) R.id.LeftTheme);
                } else if (this.h.contains("view/getGaanaStatus")) {
                    a(context2, false, (int) R.id.GetGaanaStatus);
                    return true;
                } else if (this.h.contains("view/jukepage")) {
                    a(context2, false, (int) R.id.LeftPartyHub);
                    return true;
                } else if (this.h.contains("view/purchase")) {
                    split = this.h.split("view/purchase/");
                    if (split.length == 1) {
                        an.a().e("click", "ac", "", "DL", "", "PYMT_PLAN", "", "");
                        a(context2, false, (int) R.id.LeftMenuPurchase, null, null);
                    } else if (split.length == 2) {
                        split = split[1].split("/");
                        if (split.length == 1) {
                            a(context2, false, R.id.LeftMenuPurchase, split[0], null);
                        } else if (split.length == 2) {
                            if (this.h.contains("gcm_coupon")) {
                                a(context2, false, R.id.LeftMenuPurchaseCoupon, split[0], split[1]);
                            } else {
                                a(context2, false, R.id.LeftMenuPurchase, split[0], split[1]);
                            }
                        }
                    } else {
                        a(context2, false, (int) R.id.LeftMenuPurchase, null, null);
                    }
                    return true;
                } else if (this.h.contains("view/studentpack/verifyeligibilityscreen")) {
                    a(context2, false, (int) R.id.StudentPack_Verifyeligibilityscreen);
                    return true;
                } else {
                    String str2 = null;
                    if (this.h.contains("view/hermespurchase")) {
                        split = this.h.split("view/hermespurchase/");
                        if (split.length > 1) {
                            str2 = split[1];
                        }
                        a(context2, false, (int) R.id.LeftMenuHermesPurchase, str2);
                        return true;
                    } else if (this.h.contains("view/paytmpurchase")) {
                        split = this.h.split("view/paytmpurchase/");
                        if (split.length > 1) {
                            str2 = split[1];
                        }
                        a(context2, false, (int) R.id.LeftMenuPaytmPurchase, str2);
                        return true;
                    } else if (this.h.contains("view/googlepurchase")) {
                        split = this.h.split("view/googlepurchase/");
                        if (split.length > 1) {
                            str2 = split[1];
                        }
                        a(context2, false, (int) R.id.LeftMenuGooglePurchase, str2);
                        return true;
                    } else if (this.h.contains("view/settings")) {
                        a(context2, false, (int) R.id.LeftMenuSettings);
                        return true;
                    } else if (this.h.contains("view/addtofavorite")) {
                        split = this.h.split("view/addtofavorite/");
                        if (split.length > 1) {
                            str2 = split[1];
                        }
                        a(context2, false, (int) R.id.LaunchLoginForFavorite, str2);
                        return true;
                    } else if (this.h.contains("view/player")) {
                        a(context2, false, (int) R.id.ExpandPlayerMenu);
                    } else if (this.h.contains("view/language")) {
                        a(context2, false, (int) R.id.LanguageSettingsDetail);
                        return true;
                    } else if (this.h.contains("seeall")) {
                        return b(context2, this.h);
                    } else {
                        if (this.h.contains("homepage")) {
                            return a(context2, this.h);
                        }
                        if (this.h.contains("view/friendsactivity")) {
                            a(context2, false, (int) R.id.LeftMenuFriendsActivity);
                            return true;
                        } else if (this.h.contains("view/discover")) {
                            a(context2, false, (int) R.id.LeftMenuDiscover);
                            return true;
                        } else if (this.h.contains("view/mymusic/playlists")) {
                            split = this.h.split("view/mymusic/playlists/");
                            str2 = "";
                            if (split.length > 1) {
                                str2 = split[1];
                            }
                            a(context2, false, (int) R.id.MyMusicMenuPlaylists, str2);
                            return true;
                        } else if (this.h.contains("view/mymusic/albums")) {
                            split = this.h.split("view/mymusic/albums/");
                            str2 = "";
                            if (split.length > 1) {
                                str2 = split[1];
                            }
                            a(context2, false, (int) R.id.MyMusicMenuAlbums, str2);
                            return true;
                        } else if (this.h.contains("view/mymusic/songs")) {
                            split = this.h.split("view/mymusic/songs/");
                            a(context2, false, (int) R.id.MyMusicMenuSongs, split.length > 1 ? split[1] : "", this.i != null ? this.i : null);
                            return true;
                        } else if (this.h.contains("view/mymusic/radios")) {
                            a(context2, false, (int) R.id.MyMusicMenuRadios);
                            return true;
                        } else if (this.h.contains("view/mymusic/artists")) {
                            a(context2, false, (int) R.id.MyMusicMenuArtists);
                            return true;
                        } else if (this.h.contains("view/mymusic/downloads") || this.h.contains("view/downloads")) {
                            split = this.h.split("view/mymusic/downloads/");
                            str2 = "";
                            if (split.length > 1) {
                                str2 = split[1];
                            }
                            a(context2, false, (int) R.id.MyMusicMenuDownloads, str2);
                            return true;
                        } else if (this.h.contains("view/mymusic/favorites")) {
                            a(context2, false, (int) R.id.MyMusicFavorites);
                            return true;
                        } else if (this.h.contains("view/mymusic/phonemusic")) {
                            a(context2, false, (int) R.id.MyMusicMenuPhoneMusic);
                            return true;
                        } else if (this.h.contains("view/mymusic")) {
                            a(context2, false, (int) R.id.LeftMenuMyMusic);
                            return true;
                        } else if (this.h.contains("view/search")) {
                            a(context2, false, (int) R.id.TopTabSearch);
                            return true;
                        } else if (this.h.contains("view/socialfeed")) {
                            a(context2, false, (int) R.id.SocialFeed);
                            return true;
                        } else if (this.h.contains("view/refer") || this.h.contains("view/invitefriends")) {
                            a(context2, false, (int) R.id.LeftMenuReferFriend);
                            return true;
                        } else if (this.h.contains("view/yourzone/") || this.h.contains("/myzone")) {
                            split = this.h.split("/yourzone/");
                            if (split.length > 1) {
                                str = split[1];
                                this.f = new BusinessObject();
                                this.f.setName(str);
                                this.f.setBusinessObjId(str);
                                this.f.setBusinessObjType(BusinessObjectType.ProfileUsers);
                                a(context2, this.f, SOURCE_TYPE.DEEP_LINKING.ordinal());
                            } else {
                                a(context2, false, (int) R.id.rlProfileSideBar);
                            }
                            return true;
                        } else if (this.h.contains("view/myprofile/")) {
                            split = this.h.split("/myprofile/");
                            if (split.length > 1) {
                                str = split[1];
                                this.f = new BusinessObject();
                                this.f.setName(str);
                                this.f.setBusinessObjId(str);
                                this.f.setBusinessObjType(BusinessObjectType.ProfileUsers);
                                a(context2, this.f, SOURCE_TYPE.DEEP_LINKING.ordinal());
                            } else {
                                a(context2, false, (int) R.id.rlProfileSideBar);
                            }
                            return true;
                        } else if (this.h.contains("view/gaanaplusprice") || this.h.contains("view/subscribe")) {
                            split = this.h.split("view/gaanaplusprice/");
                            if (split.length > 1) {
                                a(context2, false, (int) R.id.DeepLinkingGaanaPlus, split[1]);
                            } else {
                                a(context2, false, (int) R.id.DeepLinkingGaanaPlus);
                            }
                            return true;
                        } else if (this.h.contains("view/downloadsync")) {
                            if (l.a().g()) {
                                a(context2, false, (int) R.id.DeepLinkingDownloadSync);
                            } else {
                                a(context2, false, (int) R.id.DeepLinkingGaanaPlusSettings);
                            }
                            return true;
                        } else if (this.h.contains("view/gaanaplussettings")) {
                            a(context2, false, (int) R.id.DeepLinkingGaanaPlusSettings);
                            return true;
                        } else if (this.h.contains("view/gaanarewards") || this.h.contains("view/rewarddetails")) {
                            a(context2, false, (int) R.id.LeftMenuRewards);
                            return true;
                        } else if (this.h.contains("view/redeem/")) {
                            split = this.h.split("/redeem/");
                            if (split.length > 1) {
                                str2 = split[1];
                            }
                            a(context2, false, (int) R.id.DeepLinkingRedeemCoupon, str2);
                            return true;
                        } else if (this.h.contains("view/rate")) {
                            a(context2, false, (int) R.id.DeepLinkingRateApp);
                            return true;
                        } else if (this.h.contains("view/restore")) {
                            a(context2, false, (int) R.id.DeepLinkingRestorePurchase);
                            return true;
                        } else if (this.h.contains("view/videos")) {
                            split = this.h.split("/videos/");
                            return a(context2, split.length > 1 ? split[1] : null, null);
                        } else if (this.h.contains("view/yearvideo") || this.h.contains("view/music2017")) {
                            boolean a = DynamicViewManager.a().a(context2);
                            if (!a) {
                                a = com.dynamicview.c.a().a(context2);
                            }
                            if (!a) {
                                this.h = this.h.replace(Promotion.ACTION_VIEW, "https://gaana.com");
                                a(context2, false, (int) R.id.LeftMenuWebView, this.h, this.j);
                                a = true;
                            }
                            return a;
                        } else if (this.h.contains("view/occasion")) {
                            return d(context2, this.h);
                        } else {
                            if (this.h.contains("/view/persona")) {
                                if (context2 instanceof SplashScreenActivity) {
                                    intent = new Intent(context2, GaanaActivity.class);
                                    bundle = new Bundle();
                                    bundle.putString("DEEPLINKING_SCREEN_EXTRA_PARAM", "persona");
                                    intent.putExtras(bundle);
                                    context2.startActivity(intent);
                                    ((SplashScreenActivity) context2).finish();
                                    return true;
                                } else if (!(context2 instanceof GaanaActivity)) {
                                    return false;
                                } else {
                                    ((GaanaActivity) context2).displayFragment(new PersonaDedicationFragment());
                                    return true;
                                }
                            } else if (this.h.contains("view/curateddownload")) {
                                if (context2 instanceof SplashScreenActivity) {
                                    intent = new Intent(context2, GaanaActivity.class);
                                    bundle = new Bundle();
                                    bundle.putString("DEEPLINKING_SCREEN_EXTRA_PARAM", "curateddownload");
                                    intent.putExtras(bundle);
                                    context2.startActivity(intent);
                                    ((SplashScreenActivity) context2).finish();
                                    return true;
                                } else if (!(context2 instanceof GaanaActivity)) {
                                    return false;
                                } else {
                                    g.a(context2, null, null);
                                    return true;
                                }
                            } else if (this.h.contains("view/gaanamini/")) {
                                split = this.h.split("view/gaanamini/");
                                if (split.length <= 1) {
                                    return false;
                                }
                                split = split[1].split("/");
                                a(context2, false, -1, split[0], split[1], null, null, false);
                                return true;
                            } else if (this.h.contains("view/phonelogin")) {
                                if (!(this.e.getCurrentUser().getLoginStatus() || context2 == null)) {
                                    intent = new Intent(context2, Login.class);
                                    bundle = new Bundle();
                                    bundle.putBoolean("DEEPLINKING_PHONE_LOGIN", true);
                                    boolean z = context2 instanceof SplashScreenActivity;
                                    if (z && GaanaApplication.onBoardingSkipped && GaanaApplication.sessionHistoryCount == 0) {
                                        bundle.putBoolean("IS_FROM_DEFERRED_DEEPLINK", true);
                                    }
                                    intent.putExtras(bundle);
                                    context2.startActivity(intent);
                                    if (z) {
                                        ((SplashScreenActivity) context2).finish();
                                    }
                                    return true;
                                }
                            } else if (this.h.contains("view/voiceassistant")) {
                                a(context2, false, (int) R.id.LaunchGaanaVoice);
                                return true;
                            } else if (this.h.contains("view/educativehd")) {
                                a(context2, false, (int) R.id.EducativeHD);
                                return true;
                            } else if (this.h.contains("view/benefits")) {
                                a(context2, false, (int) R.id.Benefits);
                                return true;
                            } else if (this.h.contains("/deep-link/virtual-playlist")) {
                                return c(context2, this.h);
                            } else {
                                if (this.h.contains("view/demo") && this.h.contains("demo=")) {
                                    if (this.h.split("demo=").length > 1) {
                                        str = this.h.split("demo=")[1];
                                        if (str.contains("/")) {
                                            str = str.substring(0, str.indexOf("/"));
                                        }
                                        GaanaApplication.getInstance().setAdsEnv(str);
                                    }
                                } else if (!this.h.contains("view/videofeed")) {
                                    BusinessObject b = b(this.h);
                                    if (b == null) {
                                        b = a(this.h);
                                    }
                                    if (b == null) {
                                        this.h = this.h.replace("view/", "http://gaana.com/");
                                        if (this.h.contains("gaana://")) {
                                            this.h = this.h.replace("gaana://", "");
                                        }
                                    }
                                    return a(context2, b, SOURCE_TYPE.DEEP_LINKING.ordinal());
                                } else if (context2 instanceof GaanaActivity) {
                                    Util.a(context2, new YouTubeVideo(), GAANA_ENTRY_PAGE.DEEP_LINK.name());
                                    return true;
                                } else if (!(context2 instanceof SplashScreenActivity)) {
                                    return false;
                                } else {
                                    intent = new Intent(context2, GaanaActivity.class);
                                    intent.putExtra("LAUNCH_VIDEO_FEED_FRAGMENT", true);
                                    context2.startActivity(intent);
                                    ((SplashScreenActivity) context2).finish();
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean a(Context context, GaanaApplication gaanaApplication, String str, String str2) {
        if (str.equals(com.constants.c.c.i)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("view/occasion/");
            stringBuilder.append(str2);
            return d(context, stringBuilder.toString());
        } else if (str.equals(com.constants.c.c.f)) {
            return a(context, str2, null);
        } else {
            BusinessObject businessObject = new BusinessObject();
            if (str.equals(com.constants.c.c.a)) {
                businessObject.setBusinessObjType(BusinessObjectType.Playlists);
            } else if (str.equals(com.constants.c.c.b)) {
                businessObject.setBusinessObjType(BusinessObjectType.Albums);
            } else if (str.equals(d.d)) {
                businessObject = new Radio();
                businessObject.setBusinessObjType(BusinessObjectType.Radios);
                businessObject.setType(d.d);
            } else if (str.equals(d.c)) {
                businessObject = new Radio();
                businessObject.setBusinessObjType(BusinessObjectType.Radios);
                businessObject.setType(d.c);
            } else if (str.equals(com.constants.c.c.c)) {
                businessObject.setBusinessObjType(BusinessObjectType.Tracks);
            } else if (str.equals(com.constants.c.c.d)) {
                businessObject.setBusinessObjType(BusinessObjectType.Artists);
            }
            if (TextUtils.isEmpty(str2)) {
                if (context instanceof BaseActivity) {
                    ((BaseActivity) context).hideProgressDialog();
                }
                return false;
            }
            this.k = str2;
            businessObject.setName(str2);
            if (businessObject != null) {
                return a(context, businessObject, SOURCE_TYPE.DEEP_LINKING.ordinal());
            }
            if (context instanceof BaseActivity) {
                ((BaseActivity) context).hideProgressDialog();
            }
            return false;
        }
    }

    private boolean a(Context context, String str, String str2) {
        Intent intent = new Intent(context, GaanaActivity.class);
        if (context instanceof BaseActivity) {
            ((BaseActivity) context).hideProgressDialog();
        }
        if (!TextUtils.isEmpty(str)) {
            if (context instanceof SplashScreenActivity) {
                intent.putExtra("video_id", str);
                intent.putExtra("launch_video_activity", true);
                context.startActivity(intent);
                ((SplashScreenActivity) context).finish();
                return true;
            } else if (context instanceof GaanaActivity) {
                intent = new Intent(context, YouTubePlayerActivity.class);
                intent.putExtra("orientation", Orientation.AUTO_START_WITH_LANDSCAPE);
                intent.putExtra("video_id", str);
                intent.putExtra("browser_url", str2);
                if (GaanaMusicService.t()) {
                    o.a(context, PauseReasons.MEDIA_BUTTON_TAP);
                    Constants.dc = true;
                }
                if (f.v().w()) {
                    f.v().F();
                    Constants.dc = true;
                }
                ((Activity) context).startActivityForResult(intent, 101);
                return true;
            }
        }
        return false;
    }

    private boolean d(final Context context, String str) {
        str = str.substring(str.lastIndexOf("/") + 1);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("https://api.gaana.com/home/occasion/meta/v2/");
        stringBuilder.append(str);
        final String stringBuilder2 = stringBuilder.toString();
        if (stringBuilder2 == null || !stringBuilder2.contains(str)) {
            if (context instanceof BaseActivity) {
                ((BaseActivity) context).hideProgressDialog();
            }
            return false;
        } else if (!Util.j(context) || this.e.isAppInOfflineMode()) {
            if (context instanceof BaseActivity) {
                ((BaseActivity) context).hideProgressDialog();
            }
            aj.a().a(context, context.getResources().getString(R.string.error_download_no_internet));
            return false;
        } else {
            com.dynamicview.c.a().a(new ag() {
                public void onOccasionResponse() {
                    if (context instanceof BaseActivity) {
                        ((BaseActivity) context).hideProgressDialog();
                    }
                    Bundle bundle;
                    if (context instanceof GaanaActivity) {
                        BaseGaanaFragment dynamicOccasionFragment = new DynamicOccasionFragment();
                        bundle = new Bundle();
                        bundle.putString("OCCASION_URL", stringBuilder2);
                        bundle.putString("OCCASION_REFRESH_INTERVAL", "240");
                        dynamicOccasionFragment.setArguments(bundle);
                        ((GaanaActivity) context).displayFragment(dynamicOccasionFragment);
                    } else if ((context instanceof SplashScreenActivity) || (context instanceof OnBoardLanguagePreferenceActivityNew)) {
                        Intent intent = new Intent(context, GaanaActivity.class);
                        bundle = new Bundle();
                        bundle.putBoolean("LAUNCH_OCCASION_FRAGMENT", true);
                        bundle.putString("OCCASION_URL", stringBuilder2);
                        bundle.putString("OCCASION_REFRESH_INTERVAL", "240");
                        intent.putExtras(bundle);
                        context.startActivity(intent);
                        if (context instanceof SplashScreenActivity) {
                            ((SplashScreenActivity) context).finish();
                        }
                    }
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(str);
                    stringBuilder.append("_Click");
                    u.a().b("Browse", stringBuilder.toString());
                }

                public void onOccasionError() {
                    if (context instanceof BaseActivity) {
                        ((BaseActivity) context).hideProgressDialog();
                    }
                    aj.a().a(context, context.getResources().getString(R.string.error_download_no_internet));
                    if (context instanceof SplashScreenActivity) {
                        Intent intent = new Intent(context, GaanaActivity.class);
                        intent.putExtras(new Bundle());
                        context.startActivity(intent);
                        ((SplashScreenActivity) context).finish();
                    }
                }
            }, stringBuilder2, "240", true);
            return true;
        }
    }

    public boolean a(Context context, String str) {
        if (this.e.isAppInOfflineMode()) {
            Util.b(context, context.getString(R.string.this_feature));
            return false;
        } else if (Util.j(context)) {
            int i;
            if (str.contains("view/view")) {
                str = str.replace("view/view", "/view");
            }
            if (str.contains("gaana://")) {
                str = str.replace("gaana:/", "");
            }
            if (str.contains("/view/")) {
                str = str.replace("/view/", "");
            }
            str = str.trim();
            ArrayList e = DynamicViewManager.a().e();
            Bundle bundle = new Bundle();
            String str2 = "";
            String[] split = str.split("/");
            if (split.length > 0) {
                str2 = split[split.length - 1];
            }
            if (e == null || e.size() <= 0) {
                i = -1;
            } else {
                Iterator it = e.iterator();
                int i2 = 0;
                while (it.hasNext()) {
                    String x = ((a) it.next()).x();
                    if (x != null && x.contains("gaana://")) {
                        x = x.replace("gaana://view/", "").trim();
                        String[] split2 = x.split("/");
                        if (split2.length > 0) {
                            x = split2[0];
                        }
                    }
                    if (x != null && x.equalsIgnoreCase(str2)) {
                        bundle.putString("LAUNCH_PAGE", "Home");
                        i = i2;
                        break;
                    }
                    i2++;
                }
                i = -1;
                ArrayList g = DynamicViewManager.a().g();
                if (i == -1) {
                    Iterator it2 = g.iterator();
                    while (it2.hasNext()) {
                        String p = ((a) it2.next()).p();
                        if (p != null && p.equalsIgnoreCase(str2)) {
                            bundle.putString("LAUNCH_PAGE", "Radio");
                            i = i2;
                            break;
                        }
                    }
                }
            }
            if (i == -1) {
                return false;
            }
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(i);
            bundle.putString("DEEPLINKING_SCREEN_EXTRA_PARAM", stringBuilder.toString());
            if (!(context instanceof Activity)) {
                return false;
            }
            if (context instanceof GaanaActivity) {
                BaseGaanaFragment dynamicHomeFragment;
                if (bundle.getString("LAUNCH_PAGE").equals("Home")) {
                    dynamicHomeFragment = new DynamicHomeFragment();
                    dynamicHomeFragment.setArguments(bundle);
                    ((GaanaActivity) context).displayFragment(dynamicHomeFragment);
                } else if (!bundle.getString("LAUNCH_PAGE").equals("Radio")) {
                    return false;
                } else {
                    dynamicHomeFragment = new RadioActivityFragment();
                    dynamicHomeFragment.setArguments(bundle);
                    ((GaanaActivity) context).displayFragment(dynamicHomeFragment);
                }
            } else if (context instanceof SplashScreenActivity) {
                Intent intent = new Intent(context, GaanaActivity.class);
                intent.putExtras(bundle);
                intent.setFlags(71303168);
                context.startActivity(intent);
                ((SplashScreenActivity) context).finish();
            } else {
                ((Activity) context).finish();
            }
            return true;
        } else {
            ap.a().f(context);
            return false;
        }
    }

    public boolean b(final Context context, String str) {
        if (str.contains("view/view")) {
            str = str.replace("view/view", "/view");
        }
        if (str.contains("gaana://")) {
            str = str.replace("gaana://", "");
        }
        str = str.trim();
        ArrayList e = DynamicViewManager.a().e();
        ArrayList g = DynamicViewManager.a().g();
        if (e != null && e.size() != 0) {
            return a(context, str, e, g);
        }
        DynamicViewManager.a().a(new y() {
            public void OnDynamicViewDataFetched() {
                DynamicViewManager.a().b((y) this);
                c.this.a(context, str, DynamicViewManager.a().e(), DynamicViewManager.a().g());
            }
        });
        DynamicViewManager.a().a(false);
        return false;
    }

    private boolean a(Context context, String str, ArrayList<a> arrayList, ArrayList<a> arrayList2) {
        a aVar = null;
        if (arrayList != null && arrayList.size() > 0) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                a aVar2 = (a) it.next();
                String x = aVar2.x();
                if (x != null && x.contains("gaana://") && str.equalsIgnoreCase(x.replace("gaana://", "").trim())) {
                    aVar = aVar2;
                    break;
                }
            }
            if (aVar == null) {
                it = arrayList2.iterator();
                while (it.hasNext()) {
                    a aVar3 = (a) it.next();
                    String x2 = aVar3.x();
                    if (x2 != null && x2.contains("gaana://") && str.equalsIgnoreCase(x2.replace("gaana://", ""))) {
                        aVar = aVar3;
                        break;
                    }
                }
            }
        }
        if (aVar == null) {
            return false;
        }
        URLManager uRLManager = new URLManager();
        uRLManager.a(BusinessObjectType.GenericItems);
        if (aVar.o() == null) {
            return false;
        }
        uRLManager.a(aVar.o());
        if (aVar.o().contains("editorspick")) {
            uRLManager.c(true);
        }
        return a(context, str, uRLManager, aVar);
    }

    private boolean a(Context context, String str, URLManager uRLManager, a aVar) {
        if (this.e.isAppInOfflineMode()) {
            Util.b(context, context.getString(R.string.this_feature));
            return false;
        } else if (!Util.j(context)) {
            ap.a().f(context);
            return false;
        } else if (uRLManager == null || !(context instanceof Activity)) {
            return false;
        } else {
            Bundle bundle = new Bundle();
            bundle.putParcelable("EXTRA_URL_MANAGER", uRLManager);
            bundle.putBoolean("EXTRA_SHOW_LOADMORE", aVar.t());
            bundle.putString("EXTRA_GASECTION_NAME", aVar.p());
            if (aVar.u() != null) {
                aVar.u();
            }
            bundle.putString("EXTRA_ACTIONBAR_TITLE", aVar.w());
            bundle.putString("EXTRA_GRID_SEE_ALL_AD_CODE", aVar.i());
            bundle.putString("VIEW_TYPE_SEE_ALL", aVar.r());
            bundle.putString("EXTRA_URI_PATH", str);
            if (context instanceof GaanaActivity) {
                BaseGaanaFragment songParallexListingFragment;
                if ((!TextUtils.isEmpty(aVar.r()) && !aVar.r().equals(DynamicViewType.grid_rect.name()) && !aVar.r().equals(DynamicViewType.grid.name())) || TextUtils.isEmpty(aVar.m()) || aVar.m().equals(DynamicViewType.user_activity.name())) {
                    songParallexListingFragment = new SongParallexListingFragment();
                    ListingParams listingParams = new ListingParams();
                    listingParams.e(false);
                    listingParams.f(true);
                    listingParams.h(false);
                    listingParams.d(true);
                    listingParams.i(false);
                    listingParams.a(true);
                    listingParams.a(aVar.i());
                    listingParams.b(aVar.p());
                    ListingButton listingButton = (ListingButton) Constants.e().c().get(0);
                    listingButton.b(aVar.w());
                    listingButton.a(aVar.w());
                    URLManager c = listingButton.c();
                    c.g(true);
                    c.a(uRLManager.k());
                    c.d(false);
                    c.a(true);
                    c.a(BusinessObjectType.GenericItems);
                    if (!TextUtils.isEmpty(aVar.m()) && aVar.m().equals(DynamicViewType.user_activity.name())) {
                        c.a(UserRecentActivity.class);
                    }
                    uRLManager.h(true);
                    listingParams.a(listingButton);
                    songParallexListingFragment.a(listingParams);
                    ListingComponents listingComponents = new ListingComponents();
                    new ArrayList().add(listingButton);
                    this.e.setListingComponents(listingComponents);
                    if (aVar.j() != null && ap.a().b(context)) {
                        if (aVar.j().get("url_logo_banner") != null) {
                            bundle.putString("EXTRA_VIEW_ALL_BANNER_AD_IMG", (String) aVar.j().get("url_logo_banner"));
                        }
                        if (aVar.j().get("bottom_banner_off") != null) {
                            bundle.putBoolean("SEE_ALL_BOTTOM_BANNER_OFF", ((String) aVar.j().get("bottom_banner_off")).equals("1"));
                        }
                    }
                    bundle.putString("EXTRA_DYNAMIC_SECTION_UID", aVar.y());
                    bundle.putString("EXTRA_SOURCE_NAME", aVar.p());
                    songParallexListingFragment.setArguments(bundle);
                    ((GaanaActivity) context).displayFragment(songParallexListingFragment);
                } else {
                    songParallexListingFragment = new GridActivityFragment();
                    songParallexListingFragment.setArguments(bundle);
                    ((GaanaActivity) context).displayFragment(songParallexListingFragment);
                }
            } else if (context instanceof SplashScreenActivity) {
                Intent intent = new Intent(context, GaanaActivity.class);
                bundle.putBoolean("launch_see_all", true);
                intent.putExtras(bundle);
                intent.setFlags(71303168);
                context.startActivity(intent);
                ((SplashScreenActivity) context).finish();
            } else {
                ((Activity) context).finish();
            }
            return true;
        }
    }

    public boolean b(Context context, Intent intent, GaanaApplication gaanaApplication) {
        if (intent == null) {
            return false;
        }
        this.e = gaanaApplication;
        Bundle extras = intent.getExtras();
        if (extras == null) {
            return false;
        }
        String string = extras.getString("data");
        if (string != null) {
            PushNotification pushNotification = (PushNotification) new GsonBuilder().excludeFieldsWithModifiers(8, 4).create().fromJson(string, PushNotification.class);
            if (pushNotification != null) {
                if (!"g".equalsIgnoreCase(pushNotification.getType())) {
                    return a(context, a(pushNotification.getUrl()), SOURCE_TYPE.PUSH_NOTIFICATION.ordinal());
                }
                a(context, false, -1, "g", null, null, null, false);
            }
        }
        return false;
    }

    public boolean a(final Context context, BusinessObject businessObject, final int i) {
        if (i == SOURCE_TYPE.PUSH_NOTIFICATION.ordinal()) {
            this.e.setPlayoutSectionName(PLAYOUT_SECTION_TYPE.PUSH.name());
        } else if (i == SOURCE_TYPE.DEEP_LINKING.ordinal()) {
            this.e.setPlayoutSectionName(PLAYOUT_SECTION_TYPE.SHARE.name());
        }
        if (businessObject == null) {
            return false;
        }
        boolean z;
        String businessObjId = businessObject.getBusinessObjId();
        if (businessObjId != null) {
            z = false;
        } else if (TextUtils.isEmpty(this.k)) {
            return false;
        } else {
            businessObjId = this.k;
            z = true;
        }
        if (businessObject instanceof Radio) {
            this.g = Constants.a(((Radio) businessObject).getType(), businessObjId, z);
        } else {
            this.g = Constants.a(businessObject.getBusinessObjType(), businessObjId, z);
        }
        if (businessObject.getBusinessObjType() == BusinessObjectType.ProfileUsers && "0".equals(businessObject.getBusinessObjId())) {
            BusinessObject profileUser = new ProfileUser();
            profileUser.setBusinessObjId(businessObject.getBusinessObjId());
            b(context, profileUser);
            return true;
        } else if (this.g == null) {
            return false;
        } else {
            i.a().a(new s() {
                public void onRetreivalComplete(BusinessObject businessObject) {
                    if (context instanceof BaseActivity) {
                        ((BaseActivity) context).hideProgressDialog();
                    }
                    if (businessObject == null || businessObject.getArrListBusinessObj() == null || businessObject.getArrListBusinessObj().size() <= 0) {
                        c.this.b(context, false);
                        return;
                    }
                    businessObject = (BusinessObject) businessObject.getArrListBusinessObj().get(0);
                    if (businessObject == null || TextUtils.isEmpty(businessObject.getBusinessObjId())) {
                        aj.a().a(context, context.getString(R.string.content_not_available));
                        c.this.b(context, false);
                    } else if (businessObject instanceof Track) {
                        StringBuilder stringBuilder = new StringBuilder();
                        if (TextUtils.isEmpty(c.this.l)) {
                            stringBuilder.append("play/");
                        } else {
                            stringBuilder.append(c.this.l);
                            stringBuilder.append("/");
                        }
                        stringBuilder.append(businessObject.getBusinessObjId());
                        if (c.this.l != null && c.this.l.contains("download")) {
                            stringBuilder.append("/track");
                        }
                        c.this.l = stringBuilder.toString();
                        BusinessObject businessObject2 = new BusinessObject();
                        businessObject2.setBusinessObjId(((Track) businessObject).getAlbumId());
                        businessObject2.setBusinessObjType(BusinessObjectType.Albums);
                        c.this.a(context, businessObject2, i);
                    } else if (businessObject instanceof ProfileUser) {
                        c.this.b(context, businessObject);
                    } else if (businessObject instanceof Radio) {
                        c.this.a(context, businessObject);
                    } else {
                        c.this.a(context, businessObject);
                    }
                }

                public void onErrorResponse(BusinessObject businessObject) {
                    if (context instanceof BaseActivity) {
                        ((BaseActivity) context).hideProgressDialog();
                    }
                    c.this.b(context, false);
                }
            }, this.g);
            return true;
        }
    }

    private void c(Context context, BusinessObject businessObject, int i) {
        aj a;
        StringBuilder stringBuilder;
        if (i == SOURCE_TYPE.RADIO_SEARCH_SONG.ordinal()) {
            a = aj.a();
            stringBuilder = new StringBuilder();
            stringBuilder.append(context.getString(R.string.radio_for_song));
            stringBuilder.append(businessObject.getName());
            a.a(context, stringBuilder.toString());
            ad.a(context).a("https://api.gaana.com/radio.php?type=radio&subtype=songredios&track_id=<track_id>&page=1&limit=10".replace("<track_id>", businessObject.getBusinessObjId()), SOURCE_TYPE.RADIO_SEARCH_SONG.ordinal(), businessObject);
        } else if (i == SOURCE_TYPE.RADIO_SEARCH_ARTIST.ordinal()) {
            a = aj.a();
            stringBuilder = new StringBuilder();
            stringBuilder.append(context.getString(R.string.radio_for_artist));
            stringBuilder.append(businessObject.getName());
            a.a(context, stringBuilder.toString());
            ad.a(context).a("https://api.gaana.com/radio.php?type=radio&subtype=artistradios&artist_id=<artist_id>&page=1&limit=10".replace("<artist_id>", businessObject.getBusinessObjId()), SOURCE_TYPE.RADIO_SEARCH_ARTIST.ordinal(), businessObject);
        }
    }

    private boolean a(BusinessObject businessObject) {
        if (businessObject.getBusinessObjType() == BusinessObjectType.Tracks && DownloadManager.c().e(Integer.parseInt(businessObject.getBusinessObjId())) != null) {
            return true;
        }
        if ((businessObject.getBusinessObjType() == BusinessObjectType.Playlists || businessObject.getBusinessObjType() == BusinessObjectType.Albums) && DownloadManager.c().h(Integer.parseInt(businessObject.getBusinessObjId())) != null) {
            return true;
        }
        return false;
    }

    private boolean b(BusinessObject businessObject) {
        return businessObject.getBusinessObjType() == BusinessObjectType.Playlists && PlaylistSyncManager.getInstance().isMyPlaylist((Playlist) businessObject);
    }

    public boolean a(Context context, BusinessObject businessObject, int i, boolean z) {
        if (businessObject == null) {
            return false;
        }
        boolean z2;
        String businessObjId = businessObject.getBusinessObjId();
        if (businessObjId == null) {
            businessObjId = this.k;
            z2 = true;
        } else {
            z2 = false;
        }
        if (i == SOURCE_TYPE.SEARCH.ordinal()) {
            if (businessObject.isLocalMedia() || a(businessObject) || b(businessObject)) {
                this.g = null;
            } else if (businessObject instanceof Radio) {
                this.g = Constants.a(((Radio) businessObject).getType(), businessObjId, z2);
            } else {
                this.g = Constants.a(businessObject.getBusinessObjType(), businessObjId, z2);
            }
            if (this.g != null) {
                a(context, this.g, i, z);
                return true;
            } else if (!businessObject.isLocalMedia() && !a(businessObject) && !b(businessObject)) {
                return false;
            } else {
                if (businessObject.isLocalMedia()) {
                    businessObject = LocalMediaManager.getInstance(context).getLocalItemById(businessObject.getBusinessObjType(), businessObject.getBusinessObjId());
                } else if (a(businessObject)) {
                    businessObject = DownloadManager.c().a(businessObject.getBusinessObjType(), businessObject.getBusinessObjId());
                } else {
                    businessObject = PlaylistSyncManager.getInstance().getPlaylistDetails(businessObject.getBusinessObjId());
                }
                b(context, businessObject, i, z);
                return true;
            }
        }
        c(context, businessObject, i);
        return false;
    }

    public boolean b(Context context, BusinessObject businessObject, int i) {
        return a(context, businessObject, i, true);
    }

    private void a(final Context context, URLManager uRLManager, final int i, final boolean z) {
        i.a().a(new s() {
            public void onErrorResponse(BusinessObject businessObject) {
            }

            public void onRetreivalComplete(BusinessObject businessObject) {
                if (businessObject == null || businessObject.getArrListBusinessObj() == null || businessObject.getArrListBusinessObj().size() <= 0) {
                    if (context instanceof BaseActivity) {
                        ((BaseActivity) context).hideProgressDialog();
                    }
                    aj.a().a(context, context.getString(R.string.content_not_available));
                    c.this.b(context, false);
                    return;
                }
                c.this.b(context, (BusinessObject) businessObject.getArrListBusinessObj().get(0), i, z);
            }
        }, uRLManager);
    }

    private void b(Context context, BusinessObject businessObject, int i, boolean z) {
        if (businessObject == null) {
            aj.a().a(context, context.getString(R.string.deeplink_error));
            if (context instanceof BaseActivity) {
                ((BaseActivity) context).hideProgressDialog();
            }
            return;
        }
        if (businessObject instanceof Track) {
            if (businessObject.isLocalMedia() || a(businessObject) || b(businessObject)) {
                a(context, businessObject, i, z, true);
            } else {
                final Context context2 = context;
                final BusinessObject businessObject2 = businessObject;
                final int i2 = i;
                final boolean z2 = z;
                Util.a(context, (Track) businessObject, null, new ba() {
                    public void onPlaySong(View view, Track track) {
                        c.this.a(context2, businessObject2, i2, z2, true);
                    }
                });
            }
        } else if (businessObject instanceof Radio) {
            if (context instanceof BaseActivity) {
                ((BaseActivity) context).hideProgressDialog();
            }
            a(context, (Radio) businessObject);
        } else {
            if (context instanceof BaseActivity) {
                ((BaseActivity) context).hideProgressDialog();
            }
            i = R.id.albumMenu;
            if (businessObject instanceof Artist) {
                i = R.id.artistMenu;
            } else if (businessObject instanceof Playlist) {
                i = R.id.playlistMenu;
            }
            af.a(context, ((GaanaActivity) context).getCurrentFragment()).a(i, businessObject);
        }
    }

    private void a(Context context, BusinessObject businessObject, int i, boolean z, boolean z2) {
        ArrayList arrayList = new ArrayList();
        Track track = (Track) businessObject;
        PlayerTrack playerTrack = new PlayerTrack(track, track.getBusinessObjId(), i, track.getEnglishName());
        playerTrack.f(GaanaApplication.getInstance().getPageName());
        playerTrack.d(true);
        arrayList.add(playerTrack);
        PlayerManager.a(context).a(arrayList, playerTrack, 999999, true);
        PlayerManager.a(context).q();
        PlayerManager.a(context).a(PlayerType.GAANA, context);
        GaanaActivity gaanaActivity = (GaanaActivity) context;
        gaanaActivity.setUpdatePlayerFragment();
        if (Constants.cY) {
            aj.a().a(context, context.getResources().getString(R.string.playing_song_party));
        }
        if ((this.e.isAppInOfflineMode() || !Util.j(context)) && !businessObject.isLocalMedia() && DownloadManager.c().h(Integer.parseInt(track.getAlbumId())) == null) {
            if (context instanceof BaseActivity) {
                ((BaseActivity) context).hideProgressDialog();
            }
            z = false;
        }
        if (z) {
            BusinessObject businessObject2 = new BusinessObject();
            businessObject2.setBusinessObjId(track.getAlbumId());
            businessObject2.setBusinessObjType(BusinessObjectType.Albums);
            businessObject2.setName(track.getAlbumTitle());
            businessObject2.setLanguage(track.getLanguage());
            businessObject2.setLocalMedia(businessObject.isLocalMedia());
            if (businessObject.isLocalMedia()) {
                if (context instanceof BaseActivity) {
                    ((BaseActivity) context).hideProgressDialog();
                }
                af.a(context, gaanaActivity.getCurrentFragment()).a((int) R.id.albumMenu, businessObject);
                return;
            }
            this.g = Constants.a(businessObject2.getBusinessObjType(), businessObject2.getBusinessObjId(), false);
            a(context, this.g, SOURCE_TYPE.SEARCH.ordinal(), z);
        }
    }

    private void a(Intent intent) {
        if (b(intent)) {
            if (Constants.b) {
                Log.d(a, "===>>>GooglePlus deeplinking");
            }
        } else if (c(intent) && Constants.b) {
            Log.d(a, "===>>>Facebook deeplinking");
        }
    }

    private boolean b(Intent intent) {
        this.h = PlusShare.getDeepLinkId(intent);
        return this.h != null;
    }

    private boolean c(Intent intent) {
        Uri data = intent.getData();
        if (data == null) {
            return false;
        }
        this.h = data.toString();
        return true;
    }

    private void a(Context context, BusinessObject businessObject) {
        ListingComponents a;
        boolean z = businessObject instanceof Radio;
        if (z) {
            a = a(this.g, businessObject);
        } else {
            a = a(this.g);
        }
        a.a(businessObject);
        if (businessObject.getName() != null) {
            a.b(businessObject.getName());
        } else {
            a.b(businessObject.getName());
        }
        Iterator it = a.c().iterator();
        while (it.hasNext()) {
            ListingButton listingButton = (ListingButton) it.next();
            String k = listingButton.c().k();
            StringBuilder stringBuilder;
            if (businessObject instanceof Playlist) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(k);
                stringBuilder.append("playlist_id=");
                stringBuilder.append(businessObject.getBusinessObjId());
                stringBuilder.append("&playlist_type=");
                stringBuilder.append(((Playlist) businessObject).getPlaylistType());
                k = stringBuilder.toString();
            } else if (!z) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(k);
                stringBuilder.append(businessObject.getBusinessObjId());
                k = stringBuilder.toString();
            }
            listingButton.c().a(k);
        }
        this.e.setListingComponents(a);
        if (z) {
            a(context, false, -1, this.l, null, (Radio) businessObject, null, true);
        } else {
            a(context, false, -1, this.l, null, null, null, true);
        }
        this.l = null;
        if (context instanceof SplashScreenActivity) {
            ((SplashScreenActivity) context).finish();
        }
    }

    private void a(Context context, boolean z, int i, String str, String str2, Radio radio, ProfileUser profileUser, boolean z2) {
        Intent intent = new Intent(context, GaanaActivity.class);
        intent.setFlags(67108864);
        intent.putExtra("PLAY_DEEPLINKING_SONG", z);
        intent.putExtra("DEEPLINKING_SCREEN", i);
        intent.putExtra("DEEPLINKING_SCREEN_EXTRA_PARAM", str);
        intent.putExtra("DEEPLINKING_SCREEN_EXTRA_PARAM2", str2);
        intent.putExtra("PLAY_DEEPLINKING_RADIO", radio);
        intent.putExtra("SHOW_PROFILE_USER", profileUser);
        intent.putExtra("LAUNCH_DETAIL_PAGE", z2);
        this.e.setAppLaucnhedFromDeeplinking(true);
        if ((context instanceof GaanaActivity) && i != -1) {
            ((GaanaActivity) context).changeFragment(i, str, str2);
        } else if (this.m) {
            ((GaanaActivity) context).handleDeeplinkingRequest(intent.getExtras());
        } else {
            intent.setFlags(603979776);
            intent.putExtra("LAUNCH_FROM_DEEPLINK", true);
            context.startActivity(intent);
            if (context instanceof SplashScreenActivity) {
                ((SplashScreenActivity) context).finish();
            }
        }
    }

    private void a(Context context, boolean z, int i, String str, String str2) {
        a(context, z, i, str, str2, null, null, false);
    }

    private void a(Context context, boolean z, int i, String str) {
        a(context, z, i, str, null, null, null, false);
    }

    private void a(Context context, boolean z, int i) {
        a(context, z, i, null, null, null, null, false);
    }

    public void b(Context context, boolean z) {
        a(context, z, -1, null, null, null, null, false);
    }

    private void b(Context context, BusinessObject businessObject) {
        a(context, false, -1, null, null, null, (ProfileUser) businessObject, false);
    }

    private void a(Context context, Radio radio) {
        a(context, false, -1, null, null, radio, null, true);
    }

    private ListingComponents a(URLManager uRLManager, BusinessObject businessObject) {
        return uRLManager.i() == BusinessObjectType.Radios ? Constants.a((Radio) businessObject) : new ListingComponents();
    }

    private ListingComponents a(URLManager uRLManager) {
        ListingComponents listingComponents = new ListingComponents();
        if (uRLManager.i() == BusinessObjectType.Albums) {
            return Constants.b();
        }
        if (uRLManager.i() == BusinessObjectType.Artists) {
            return Constants.a("", uRLManager.n());
        }
        return uRLManager.i() == BusinessObjectType.Playlists ? Constants.e() : listingComponents;
    }

    public BusinessObject a(String str) {
        CharSequence str2;
        this.f = null;
        if (str2.contains("?")) {
            if (str2.contains("moengage")) {
                MoEngage.getInstance().reportNotificationClickedEvent(str2.substring(8, str2.indexOf("?")));
            }
            str2 = str2.substring(0, str2.indexOf("?"));
        }
        if (!TextUtils.isEmpty(str2) && str2.contains("/share")) {
            String[] split = str2.split("/");
            if (split.length > 0) {
                String str3 = split[split.length - 1];
                int lastIndexOf = str3.lastIndexOf("I");
                if (lastIndexOf > 0) {
                    this.f = new BusinessObject();
                    String substring = str3.substring(1, lastIndexOf);
                    String substring2 = str3.substring(lastIndexOf + 1);
                    Radio radio;
                    if (str3.startsWith("A")) {
                        this.f.setBusinessObjType(BusinessObjectType.Albums);
                    } else if (str3.startsWith(TtmlNode.TAG_P)) {
                        this.f.setBusinessObjType(BusinessObjectType.Playlists);
                    } else if (str3.startsWith("a")) {
                        this.f.setBusinessObjType(BusinessObjectType.Artists);
                    } else if (str3.startsWith("t")) {
                        this.f.setBusinessObjType(BusinessObjectType.Tracks);
                    } else if (str3.startsWith("u")) {
                        this.f.setBusinessObjType(BusinessObjectType.ProfileUsers);
                    } else if (str3.startsWith("RL")) {
                        radio = new Radio();
                        radio.setBusinessObjType(BusinessObjectType.Radios);
                        radio.setBusinessObjId(substring2);
                        radio.setType(d.d);
                        return radio;
                    } else if (str3.startsWith("RM")) {
                        radio = new Radio();
                        radio.setBusinessObjType(BusinessObjectType.Radios);
                        radio.setBusinessObjId(substring2);
                        radio.setType(d.c);
                        return radio;
                    }
                    if (TextUtils.isEmpty(substring2)) {
                        this.f = null;
                    }
                    if (this.f != null) {
                        this.f.setName(substring);
                        this.f.setBusinessObjId(substring2);
                        if (this.f.getBusinessObjType() != BusinessObjectType.Albums && this.f.getBusinessObjType() != BusinessObjectType.Playlists && this.f.getBusinessObjType() != BusinessObjectType.Tracks) {
                            this.l = null;
                        } else if (!str2.contains("/download/")) {
                            this.l = "play";
                        } else if (split.length > 1) {
                            this.l = split[split.length - 2];
                        }
                    }
                }
            }
        }
        return this.f;
    }

    private BusinessObject b(String str) {
        this.f = null;
        if (!TextUtils.isEmpty(str)) {
            this.f = new BusinessObject();
            String[] split;
            Radio radio;
            if (str.contains("/album/")) {
                this.f.setBusinessObjType(BusinessObjectType.Albums);
                split = str.split("/album/");
                if (split.length >= 2) {
                    this.k = split[1];
                }
            } else if (str.contains("/playlist/")) {
                this.f.setBusinessObjType(BusinessObjectType.Playlists);
                split = str.split("/playlist/");
                if (split.length >= 2) {
                    this.k = split[1];
                }
            } else if (str.contains("/song/")) {
                this.f.setBusinessObjType(BusinessObjectType.Tracks);
                split = str.split("/song/");
                if (split.length >= 2) {
                    this.k = split[1];
                }
            } else if (str.contains("/artist/")) {
                this.f.setBusinessObjType(BusinessObjectType.Artists);
                split = str.split("/artist/");
                if (split.length >= 2) {
                    this.k = split[1];
                }
            } else if (str.contains("/gaanaradio/")) {
                radio = new Radio();
                radio.setBusinessObjType(BusinessObjectType.Radios);
                radio.setType(d.d);
                this.f = radio;
                split = str.split("/gaanaradio/");
                if (split.length >= 2) {
                    this.k = split[1];
                }
            } else if (str.contains("/radio/")) {
                radio = new Radio();
                radio.setBusinessObjType(BusinessObjectType.Radios);
                radio.setType(d.c);
                this.f = radio;
                split = str.split("/radio/");
                if (split.length >= 2) {
                    this.k = split[1];
                }
            } else if (str.contains("/webradio/")) {
                radio = new Radio();
                radio.setBusinessObjType(BusinessObjectType.Radios);
                radio.setType(d.c);
                this.f = radio;
                split = str.split("/webradio/");
                if (split.length >= 2) {
                    this.k = split[1];
                }
            } else if (str.contains("/yourzone/")) {
                this.f.setBusinessObjType(BusinessObjectType.ProfileUsers);
                split = str.split("/yourzone/");
                if (split.length >= 2) {
                    this.k = split[1];
                }
            }
        }
        if (TextUtils.isEmpty(this.k)) {
            this.f = null;
        }
        if (this.f != null) {
            this.f.setName(this.k);
        }
        return this.f;
    }

    public void a(Context context, Notification notification, GaanaApplication gaanaApplication) {
        this.e = gaanaApplication;
        a(notification);
        if (this.f != null && this.f.getBusinessObjType() != null) {
            if (context instanceof BaseActivity) {
                ((BaseActivity) context).showProgressDialog(Boolean.valueOf(true), context.getString(R.string.loading));
            }
            a(context, this.f, SOURCE_TYPE.IN_APP.ordinal());
        } else if (notification != null && !TextUtils.isEmpty(notification.getActionUrlMobile())) {
            a(context, notification.getActionUrlMobile(), gaanaApplication);
        }
    }

    private BusinessObject a(Notification notification) {
        this.f = null;
        if (notification != null) {
            this.f = new BusinessObject();
            String type = notification.getType();
            String itemid = notification.getItemid();
            if (type != null) {
                if (type.equalsIgnoreCase("album")) {
                    this.f.setBusinessObjType(BusinessObjectType.Albums);
                } else if (type.equalsIgnoreCase("playlist")) {
                    this.f.setBusinessObjType(BusinessObjectType.Playlists);
                } else if (type.equalsIgnoreCase("artist")) {
                    this.f.setBusinessObjType(BusinessObjectType.Artists);
                } else if (type.equalsIgnoreCase("song")) {
                    this.f.setBusinessObjType(BusinessObjectType.Tracks);
                } else if (type.equalsIgnoreCase("radio")) {
                    this.f.setBusinessObjType(BusinessObjectType.Radios);
                } else if (type.equalsIgnoreCase("user")) {
                    this.f.setBusinessObjType(BusinessObjectType.ProfileUsers);
                }
            }
            this.f.setName("notifications");
            if (itemid != null) {
                this.f.setBusinessObjId(itemid);
            }
        }
        return this.f;
    }

    public boolean c(Context context, String str) {
        if (this.e.isAppInOfflineMode()) {
            Util.b(context, context.getString(R.string.this_feature));
            return false;
        } else if (!Util.j(context)) {
            ap.a().f(context);
            return false;
        } else if (!(context instanceof Activity)) {
            return false;
        } else {
            Bundle bundle = new Bundle();
            bundle.putString("EXTRA_URI_PATH", str);
            if (context instanceof GaanaActivity) {
                if (str.contains("gaana://")) {
                    str = str.replace("gaana://", "");
                }
                str = str.replace("view/", "https://apiv2.gaana.com/");
                BaseGaanaFragment songParallexListingFragment = new SongParallexListingFragment();
                ListingParams listingParams = new ListingParams();
                listingParams.e(false);
                listingParams.f(true);
                listingParams.h(false);
                listingParams.d(true);
                listingParams.i(false);
                listingParams.a(true);
                ListingButton listingButton = (ListingButton) Constants.e().c().get(0);
                URLManager c = listingButton.c();
                c.g(true);
                c.a(str);
                c.d(false);
                c.a(true);
                c.a(BusinessObjectType.GenericItems);
                c.h(false);
                listingButton.a(c);
                listingParams.a(listingButton);
                songParallexListingFragment.a(listingParams);
                ListingComponents listingComponents = new ListingComponents();
                new ArrayList().add(listingButton);
                this.e.setListingComponents(listingComponents);
                this.e.setAppLaucnhedFromDeeplinking(true);
                ((GaanaActivity) context).displayFragment(songParallexListingFragment);
            } else if (context instanceof SplashScreenActivity) {
                Intent intent = new Intent(context, GaanaActivity.class);
                bundle.putBoolean("launch_vpl_section", true);
                intent.putExtras(bundle);
                intent.setFlags(71303168);
                context.startActivity(intent);
                ((SplashScreenActivity) context).finish();
            } else {
                ((Activity) context).finish();
            }
            return true;
        }
    }
}
