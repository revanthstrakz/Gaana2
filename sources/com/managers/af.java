package com.managers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.graphics.Palette;
import android.support.v7.graphics.Palette.PaletteAsyncListener;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.android.volley.VolleyError;
import com.constants.Constants;
import com.constants.Constants.REVAMPED_DETAIL_TYPE;
import com.constants.c.c;
import com.dynamicview.DynamicHomeFragment;
import com.fragments.AlbumDetailsMaterialListing;
import com.fragments.ArtistDetailsMaterialListing;
import com.fragments.ArtistFragment;
import com.fragments.BaseGaanaFragment;
import com.fragments.DownloadDetailsFragment;
import com.fragments.EditPlaylistFragment;
import com.fragments.FavoritesFragment;
import com.fragments.GaanaSpecialDetailsMaterialListing;
import com.fragments.ItemListingFragment;
import com.fragments.ListingFragment;
import com.fragments.MoreInfoFragment;
import com.fragments.MyMusicFragment;
import com.fragments.MyMusicItemFragment;
import com.fragments.PreScreenFragment;
import com.fragments.ProfileFragment;
import com.fragments.RadioDetailsMaterialListing;
import com.fragments.RevampedDetailListing;
import com.fragments.SettingsDetailFragment;
import com.fragments.SongsSelectionFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.Login;
import com.gaana.R;
import com.gaana.analytics.MoEngage;
import com.gaana.application.GaanaApplication;
import com.gaana.juke.JukePartyFragment;
import com.gaana.juke.JukePlaylist;
import com.gaana.juke.JukeSessionManager;
import com.gaana.localmedia.LocalMediaManager;
import com.gaana.localmedia.PlaylistSyncManager;
import com.gaana.login.FbLoginErrorDialog;
import com.gaana.login.LoginManager;
import com.gaana.login.LoginManager.IOnLoginCompleted;
import com.gaana.login.LoginManager.LOGIN_STATUS;
import com.gaana.login.UserInfo;
import com.gaana.models.Albums.Album;
import com.gaana.models.Artists;
import com.gaana.models.BusinessObject;
import com.gaana.models.Item;
import com.gaana.models.OfflineTrack;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Radios.Radio;
import com.gaana.models.Tracks;
import com.gaana.models.Tracks.Track;
import com.gaana.models.Tracks.Track.Artist;
import com.gaana.models.User.LoginType;
import com.gaana.view.item.CustomDialogView;
import com.gaana.view.item.CustomDialogView.OnButtonClickListener;
import com.gaana.view.item.DownloadSyncPopupItemView;
import com.gaana.view.item.PopupWindowView;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.i.i;
import com.library.managers.TaskManager.TaskListner;
import com.logging.GaanaLogger.SOURCE_TYPE;
import com.logging.d;
import com.managers.DownloadManager.DownloadStatus;
import com.managers.PlayerManager.PlayerType;
import com.managers.URLManager.BusinessObjectType;
import com.managers.ap.a;
import com.models.ListingButton;
import com.models.ListingComponents;
import com.models.PlayerTrack;
import com.player_framework.GaanaMusicService;
import com.player_framework.o;
import com.services.f;
import com.services.f.b;
import com.services.h;
import com.services.l.ad;
import com.services.l.as;
import com.services.l.m;
import com.services.l.n;
import com.services.l.r;
import com.services.l.s;
import com.utilities.Util;
import com.utilities.j;
import java.util.ArrayList;
import java.util.Iterator;

public class af {
    private static String k;
    s a = new s() {
        public void onErrorResponse(BusinessObject businessObject) {
        }

        public void onRetreivalComplete(BusinessObject businessObject) {
            ((BaseActivity) af.this.j).hideProgressDialog();
            if (businessObject == null || businessObject.getArrListBusinessObj() == null || businessObject.getArrListBusinessObj().size() == 0) {
                aj.a().a(af.this.j, af.this.j.getString(R.string.no_songs_to_add));
                return;
            }
            u.a().a("personalisation", "created", "playlist");
            ArrayList arrListBusinessObj = businessObject.getArrListBusinessObj();
            for (int size = arrListBusinessObj.size() - 1; size >= 0; size--) {
                Track track = (Track) arrListBusinessObj.get(size);
                if (track.getIslocal() != null && track.getIslocal().equals("1")) {
                    track = LocalMediaManager.getInstance(af.this.j).getLocalTrackFromHash(track.getBusinessObjId());
                    arrListBusinessObj.remove(size);
                    if (track != null) {
                        arrListBusinessObj.add(size, track);
                    }
                }
            }
            ap.a().a(af.this.j, arrListBusinessObj, false);
        }
    };
    s b = new s() {
        public void onErrorResponse(BusinessObject businessObject) {
        }

        public void onRetreivalComplete(BusinessObject businessObject) {
            ((BaseActivity) af.this.j).hideProgressDialog();
            if (businessObject == null || businessObject.getArrListBusinessObj() == null || businessObject.getArrListBusinessObj().size() == 0) {
                aj.a().a(af.this.j, af.this.j.getString(R.string.player_nosongs_toplay));
            } else if (af.this.n instanceof PreScreenFragment) {
                PlayerManager.a(af.this.j).a(af.this.j, businessObject.getArrListBusinessObj(), businessObject.getBusinessObjId(), ((PreScreenFragment) af.this.n).getPageName(), ((PreScreenFragment) af.this.n).getSectionName());
            } else {
                PlayerManager.a(af.this.j).a(af.this.l.getBusinessObjId(), d.a().a(af.this.l), af.this.l.getEnglishName(), af.this.l, businessObject.getArrListBusinessObj(), af.this.j);
            }
        }
    };
    s c = new s() {
        public void onErrorResponse(BusinessObject businessObject) {
        }

        public void onRetreivalComplete(BusinessObject businessObject) {
            ((BaseActivity) af.this.j).hideProgressDialog();
            if (businessObject == null || businessObject.getArrListBusinessObj() == null || businessObject.getArrListBusinessObj().size() == 0) {
                aj.a().a(af.this.j, af.this.j.getString(R.string.player_nosongs_toplay));
            } else if (af.this.n instanceof PreScreenFragment) {
                PlayerManager.a(af.this.j).a(af.this.j, businessObject.getArrListBusinessObj(), businessObject.getBusinessObjId(), ((PreScreenFragment) af.this.n).getPageName(), ((PreScreenFragment) af.this.n).getSectionName());
            } else {
                PlayerManager.a(af.this.j).a(af.this.l.getBusinessObjId(), d.a().a(af.this.l), af.this.l.getEnglishName(), af.this.l, businessObject.getArrListBusinessObj(), af.this.j, true);
            }
        }
    };
    s d = new s() {
        public void onErrorResponse(BusinessObject businessObject) {
        }

        public void onRetreivalComplete(BusinessObject businessObject) {
            ((BaseActivity) af.this.j).hideProgressDialog();
            if (businessObject == null || businessObject.getArrListBusinessObj() == null || businessObject.getArrListBusinessObj().size() == 0) {
                aj.a().a(af.this.j, af.this.j.getString(R.string.player_nosongs_toplay));
                return;
            }
            ((GaanaActivity) af.this.j).showProgressDialog(Boolean.valueOf(true), af.this.j.getString(R.string.dlg_msg_adding_to_player));
            if (Constants.cY) {
                JukeSessionManager.getInstance().addPlayNext(JukeSessionManager.getInstance().getJukeSessionPlaylist(), businessObject.getArrListBusinessObj());
            } else {
                PlayerManager.a(af.this.j).a(businessObject.getArrListBusinessObj(), af.this.l, af.this.j, af.this.s);
                ((GaanaActivity) af.this.j).setUpdatePlayerFragment();
            }
            ((GaanaActivity) af.this.j).hideProgressDialog();
        }
    };
    m e;
    OnClickListener f = new OnClickListener() {
        public void onClick(View view) {
            af.this.t.dismiss();
            int id = view.getId();
            if (id == R.id.fb_onboard_login_btn) {
                u.a().a("Login", "LoginPopup - AddToPlaylist", "FBLogin");
                if ((af.this.u instanceof Playlist) && af.this.u.getArrListBusinessObj() != null && af.this.u.getArrListBusinessObj().size() > 0) {
                    GaanaApplication.getInstance().setArrListTracksForPlaylist(af.this.u.getArrListBusinessObj());
                }
                af.this.a(false, true, false);
            } else if (id == R.id.pager_login_button) {
                u.a().a("Login", "LoginPopup - AddToPlaylist", "Login");
                if (af.this.u.getArrListBusinessObj() != null && af.this.u.getArrListBusinessObj().size() > 0) {
                    ap.a().a(af.this.j, af.this.u.getArrListBusinessObj(), af.this.u.isLocalMedia(), false);
                } else if (af.this.u instanceof Track) {
                    ap.a().a(af.this.j, (Track) af.this.u, af.this.u.isLocalMedia(), false);
                }
            } else if (id == R.id.pager_signup_button) {
                u.a().a("Login", "LoginPopup - AddToPlaylist", "Signup");
                if (af.this.u.getArrListBusinessObj() != null && af.this.u.getArrListBusinessObj().size() > 0) {
                    ap.a().a(af.this.j, af.this.u.getArrListBusinessObj(), af.this.u.isLocalMedia(), true);
                } else if (af.this.u instanceof Track) {
                    ap.a().a(af.this.j, (Track) af.this.u, af.this.u.isLocalMedia(), true);
                }
            }
        }
    };
    OnClickListener g = new OnClickListener() {
        public void onClick(View view) {
            af.this.t.dismiss();
            int id = view.getId();
            if (id == R.id.fb_onboard_login_btn) {
                u.a().a("Login", "My Music-sections", "FBLogin");
                af.this.a(true, false, false);
            } else if (id == R.id.pager_login_button) {
                u.a().a("Login", "My Music-sections", "Login");
                ((BaseActivity) af.this.j).checkSetLoginStatusFromBottomSheet(new ad() {
                    public void onLoginSuccess() {
                        if (af.this.e != null) {
                            af.this.e.onResponse(true);
                        }
                    }
                }, "MYMUSIC", "Create your personal music library \n access it anywhere", false, false);
            } else if (id == R.id.pager_signup_button) {
                u.a().a("Login", "My Music-sections", "Signup");
                ((BaseActivity) af.this.j).checkSetLoginStatusFromBottomSheet(new ad() {
                    public void onLoginSuccess() {
                        if (af.this.e != null) {
                            af.this.e.onResponse(true);
                        }
                    }
                }, "MYMUSIC", "Create your personal music library \n access it anywhere", true, false);
            }
        }
    };
    OnClickListener h = new OnClickListener() {
        public void onClick(View view) {
            af.this.t.dismiss();
            int id = view.getId();
            StringBuilder stringBuilder;
            if (id == R.id.fb_onboard_login_btn) {
                u.a().a("Login", "LoginPopup - Favourites", "FBLogin");
                af.this.a(true, false, false);
            } else if (id == R.id.pager_login_button) {
                u.a().a("Login", "LoginPopup - Favourites", "Login");
                stringBuilder = new StringBuilder();
                stringBuilder.append(GaanaApplication.getContext().getResources().getString(R.string.LOGIN_LAUNCHED_FOR_FAVORITE_1));
                stringBuilder.append(" ");
                stringBuilder.append(Util.a(af.this.l.getBusinessObjType()));
                stringBuilder.append(" ");
                stringBuilder.append(GaanaApplication.getContext().getResources().getString(R.string.LOGIN_LAUNCHED_FOR_FAVORITE_2));
                ((BaseActivity) af.this.j).checkSetLoginStatusFromBottomSheet(new ad() {
                    public void onLoginSuccess() {
                        af.this.l.setFavorite(Boolean.valueOf(true));
                        ((BaseActivity) af.this.j).addRemoveFav(af.this.l, Boolean.valueOf(false), false, af.this.q);
                        if (af.this.l.getBusinessObjType() == BusinessObjectType.Tracks) {
                            af.this.k();
                        }
                    }
                }, "FAVORITE", stringBuilder.toString(), false, false);
            } else if (id == R.id.pager_signup_button) {
                u.a().a("Login", "LoginPopup - Favourites", "Signup");
                stringBuilder = new StringBuilder();
                stringBuilder.append(GaanaApplication.getContext().getResources().getString(R.string.LOGIN_LAUNCHED_FOR_FAVORITE_1));
                stringBuilder.append(" ");
                stringBuilder.append(Util.a(af.this.l.getBusinessObjType()));
                stringBuilder.append(" ");
                stringBuilder.append(GaanaApplication.getContext().getResources().getString(R.string.LOGIN_LAUNCHED_FOR_FAVORITE_2));
                ((BaseActivity) af.this.j).checkSetLoginStatusFromBottomSheet(new ad() {
                    public void onLoginSuccess() {
                        af.this.l.setFavorite(Boolean.valueOf(true));
                        ((BaseActivity) af.this.j).addRemoveFav(af.this.l, Boolean.valueOf(false), false, af.this.q);
                        if (af.this.l.getBusinessObjType() == BusinessObjectType.Tracks) {
                            af.this.k();
                        }
                    }
                }, "FAVORITE", stringBuilder.toString(), true, false);
            }
        }
    };
    OnClickListener i = new OnClickListener() {
        public void onClick(View view) {
            af.this.t.dismiss();
            int id = view.getId();
            if (id == R.id.fb_onboard_login_btn) {
                if (af.this.w.equalsIgnoreCase("Trial_card")) {
                    af.this.w = "";
                    u.a().a("Login", "Trial activation card", "FBLogin");
                } else {
                    u.a().a("Login", "LoginPopup - Downloads", "FBLogin");
                }
                af.this.a(false, false, true);
            } else if (id == R.id.pager_login_button) {
                if (af.this.w.equalsIgnoreCase("Trial_card")) {
                    af.this.w = "";
                    u.a().a("Login", "Trial activation card", "Login");
                } else {
                    u.a().a("Login", "LoginPopup - Downloads", "Login");
                }
                ap.a().a(af.this.j, false, af.this.v);
            } else if (id == R.id.pager_signup_button) {
                if (af.this.w.equalsIgnoreCase("Trial_card")) {
                    af.this.w = "";
                    u.a().a("Login", "Trial activation card", "Signup");
                } else {
                    u.a().a("Login", "LoginPopup - Downloads", "Signup");
                }
                ap.a().a(af.this.j, true, af.this.v);
            }
        }
    };
    private Context j;
    private BusinessObject l;
    private GaanaApplication m;
    private BaseGaanaFragment n;
    private String o = null;
    private String p = null;
    private a q;
    private boolean r;
    private boolean s = false;
    private BottomSheetDialog t;
    private BusinessObject u;
    private ad v;
    private String w = "";

    /* renamed from: com.managers.af$26 */
    static /* synthetic */ class AnonymousClass26 {
        static final /* synthetic */ int[] a = new int[LOGIN_STATUS.values().length];

        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0051 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x005c */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x003d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0047 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x0067 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:25:0x0072 */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Can't wrap try/catch for region: R(24:0|1|2|3|5|6|7|(2:9|10)|11|13|14|15|16|17|18|19|20|21|22|23|24|25|26|28) */
        /* JADX WARNING: Can't wrap try/catch for region: R(24:0|1|2|3|5|6|7|(2:9|10)|11|13|14|15|16|17|18|19|20|21|22|23|24|25|26|28) */
        /* JADX WARNING: Can't wrap try/catch for region: R(23:0|1|2|3|(2:5|6)|7|(2:9|10)|11|13|14|15|16|17|18|19|20|21|22|23|24|25|26|28) */
        /* JADX WARNING: Can't wrap try/catch for region: R(23:0|1|2|3|(2:5|6)|7|(2:9|10)|11|13|14|15|16|17|18|19|20|21|22|23|24|25|26|28) */
        /* JADX WARNING: Can't wrap try/catch for region: R(22:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|13|14|15|16|17|18|19|20|21|22|23|24|25|26|28) */
        /* JADX WARNING: Can't wrap try/catch for region: R(22:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|13|14|15|16|17|18|19|20|21|22|23|24|25|26|28) */
        /* JADX WARNING: Missing block: B:29:?, code skipped:
            return;
     */
        static {
            /*
            r0 = com.managers.URLManager.BusinessObjectType.values();
            r0 = r0.length;
            r0 = new int[r0];
            b = r0;
            r0 = 1;
            r1 = b;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r2 = com.managers.URLManager.BusinessObjectType.Artists;	 Catch:{ NoSuchFieldError -> 0x0014 }
            r2 = r2.ordinal();	 Catch:{ NoSuchFieldError -> 0x0014 }
            r1[r2] = r0;	 Catch:{ NoSuchFieldError -> 0x0014 }
        L_0x0014:
            r1 = 2;
            r2 = b;	 Catch:{ NoSuchFieldError -> 0x001f }
            r3 = com.managers.URLManager.BusinessObjectType.Albums;	 Catch:{ NoSuchFieldError -> 0x001f }
            r3 = r3.ordinal();	 Catch:{ NoSuchFieldError -> 0x001f }
            r2[r3] = r1;	 Catch:{ NoSuchFieldError -> 0x001f }
        L_0x001f:
            r2 = 3;
            r3 = b;	 Catch:{ NoSuchFieldError -> 0x002a }
            r4 = com.managers.URLManager.BusinessObjectType.Tracks;	 Catch:{ NoSuchFieldError -> 0x002a }
            r4 = r4.ordinal();	 Catch:{ NoSuchFieldError -> 0x002a }
            r3[r4] = r2;	 Catch:{ NoSuchFieldError -> 0x002a }
        L_0x002a:
            r3 = com.gaana.login.LoginManager.LOGIN_STATUS.values();
            r3 = r3.length;
            r3 = new int[r3];
            a = r3;
            r3 = a;	 Catch:{ NoSuchFieldError -> 0x003d }
            r4 = com.gaana.login.LoginManager.LOGIN_STATUS.LOGIN_SUCCEDED;	 Catch:{ NoSuchFieldError -> 0x003d }
            r4 = r4.ordinal();	 Catch:{ NoSuchFieldError -> 0x003d }
            r3[r4] = r0;	 Catch:{ NoSuchFieldError -> 0x003d }
        L_0x003d:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x0047 }
            r3 = com.gaana.login.LoginManager.LOGIN_STATUS.LOGIN_ERROR_AUTHENTICATION_FAILED;	 Catch:{ NoSuchFieldError -> 0x0047 }
            r3 = r3.ordinal();	 Catch:{ NoSuchFieldError -> 0x0047 }
            r0[r3] = r1;	 Catch:{ NoSuchFieldError -> 0x0047 }
        L_0x0047:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x0051 }
            r1 = com.gaana.login.LoginManager.LOGIN_STATUS.LOGIN_FAILURE_SSO;	 Catch:{ NoSuchFieldError -> 0x0051 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0051 }
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x0051 }
        L_0x0051:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x005c }
            r1 = com.gaana.login.LoginManager.LOGIN_STATUS.LOGIN_FAILURE_SDK_NOT_INITIALIZED;	 Catch:{ NoSuchFieldError -> 0x005c }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x005c }
            r2 = 4;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x005c }
        L_0x005c:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x0067 }
            r1 = com.gaana.login.LoginManager.LOGIN_STATUS.LOGIN_ERROR_LAUNCH_TRAP_PAGE;	 Catch:{ NoSuchFieldError -> 0x0067 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0067 }
            r2 = 5;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x0067 }
        L_0x0067:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x0072 }
            r1 = com.gaana.login.LoginManager.LOGIN_STATUS.LOGIN_MANDATORY_FIELD_MISSING;	 Catch:{ NoSuchFieldError -> 0x0072 }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x0072 }
            r2 = 6;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x0072 }
        L_0x0072:
            r0 = a;	 Catch:{ NoSuchFieldError -> 0x007d }
            r1 = com.gaana.login.LoginManager.LOGIN_STATUS.LOGIN_EMAIL_MISSING_FB;	 Catch:{ NoSuchFieldError -> 0x007d }
            r1 = r1.ordinal();	 Catch:{ NoSuchFieldError -> 0x007d }
            r2 = 7;
            r0[r1] = r2;	 Catch:{ NoSuchFieldError -> 0x007d }
        L_0x007d:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.managers.af$AnonymousClass26.<clinit>():void");
        }
    }

    public static af a(Context context, BaseGaanaFragment baseGaanaFragment) {
        af afVar = new af();
        afVar.j = context;
        afVar.n = baseGaanaFragment;
        return afVar;
    }

    public void a(String str) {
        this.o = str;
    }

    public String a() {
        if (this.o == null) {
            return "";
        }
        return this.o;
    }

    public void b(String str) {
        this.p = str;
    }

    public String b() {
        if (this.p == null) {
            return "";
        }
        return this.p;
    }

    public void c() {
        this.o = null;
        this.p = null;
    }

    public boolean a(int i, BusinessObject businessObject, a aVar) {
        this.q = aVar;
        return a(i, businessObject);
    }

    public boolean a(int i, BusinessObject businessObject, boolean z) {
        this.r = z;
        return a(i, businessObject);
    }

    /* JADX WARNING: Missing block: B:51:0x00cd, code skipped:
            if (r1 != null) goto L_0x00d1;
     */
    /* JADX WARNING: Missing block: B:235:0x09ab, code skipped:
            a((com.gaana.models.Playlists.Playlist) r5, com.constants.Constants.e());
     */
    /* JADX WARNING: Missing block: B:424:0x112f, code skipped:
            if ((r5 instanceof com.gaana.models.Tracks.Track) == false) goto L_0x11ca;
     */
    /* JADX WARNING: Missing block: B:426:0x1135, code skipped:
            if ((r0.n instanceof com.fragments.RevampedDetailListing) == false) goto L_0x1141;
     */
    /* JADX WARNING: Missing block: B:427:0x1137, code skipped:
            ((com.fragments.RevampedDetailListing) r0.n).a("Go to Album", false);
     */
    /* JADX WARNING: Missing block: B:428:0x1141, code skipped:
            r4 = new java.lang.StringBuilder();
            r4.append(((com.gaana.BaseActivity) r0.j).currentScreen);
            r4.append(" - ");
            r4.append(((com.gaana.BaseActivity) r0.j).currentFavpage);
            r4.append(" - Album Detail");
            ((com.gaana.BaseActivity) r0.j).sendGAEvent(((com.gaana.BaseActivity) r0.j).currentScreen, "Album Detail", r4.toString());
     */
    /* JADX WARNING: Missing block: B:430:0x117d, code skipped:
            if (((com.gaana.GaanaActivity) r0.j).getmCurrentPlayerFragment() == null) goto L_0x1199;
     */
    /* JADX WARNING: Missing block: B:431:0x117f, code skipped:
            com.managers.an.a().a("click", "ac", "three dot menu", "player", "", "Album Detail", r29.getBusinessObjId(), "");
     */
    /* JADX WARNING: Missing block: B:432:0x1199, code skipped:
            com.managers.an.a().a("click", "ac", "three dot menu", "", "", "Album Detail", r29.getBusinessObjId(), "");
     */
    /* JADX WARNING: Missing block: B:433:0x11b2, code skipped:
            r1 = (com.gaana.models.Tracks.Track) r5;
            a(r1.getAlbumId(), r1.getAlbumTitle(), r1.getArtwork(), r1.getLanguage());
     */
    /* JADX WARNING: Missing block: B:435:0x11cc, code skipped:
            if ((r5 instanceof com.gaana.models.OfflineTrack) == false) goto L_0x1276;
     */
    /* JADX WARNING: Missing block: B:437:0x11d2, code skipped:
            if (r29.isLocalMedia() == false) goto L_0x11f6;
     */
    /* JADX WARNING: Missing block: B:438:0x11d4, code skipped:
            r1 = com.gaana.localmedia.LocalMediaManager.getInstance(r0.j).getTrackFromLocalMedia((com.gaana.models.OfflineTrack) r5);
            a(r1.getAlbumId(), r1.getAlbumTitle(), r1.getArtwork(), r1.getLanguage());
     */
    /* JADX WARNING: Missing block: B:440:0x11fa, code skipped:
            if ((r0.n instanceof com.fragments.RevampedDetailListing) == false) goto L_0x1206;
     */
    /* JADX WARNING: Missing block: B:441:0x11fc, code skipped:
            ((com.fragments.RevampedDetailListing) r0.n).a("Go to Album", false);
     */
    /* JADX WARNING: Missing block: B:442:0x1206, code skipped:
            r4 = new java.lang.StringBuilder();
            r4.append(((com.gaana.BaseActivity) r0.j).currentScreen);
            r4.append(" - ");
            r4.append(((com.gaana.BaseActivity) r0.j).currentFavpage);
            r4.append(" - Album Detail");
            ((com.gaana.BaseActivity) r0.j).sendGAEvent(((com.gaana.BaseActivity) r0.j).currentScreen, "Album Detail", r4.toString());
     */
    /* JADX WARNING: Missing block: B:443:0x123a, code skipped:
            com.managers.an.a().a("click", "ac", "three dot menu", "player", "", "Album Detail", r29.getBusinessObjId(), "");
            r1 = (com.gaana.models.Tracks.Track) com.managers.DownloadManager.c().a(r29.getBusinessObjId(), true);
            a(r1.getAlbumId(), r1.getAlbumTitle(), r1.getArtwork(), r1.getLanguage());
     */
    /* JADX WARNING: Missing block: B:444:0x1276, code skipped:
            c(r5);
     */
    /* JADX WARNING: Missing block: B:492:0x14b5, code skipped:
            return true;
     */
    public boolean a(int r28, com.gaana.models.BusinessObject r29) {
        /*
        r27 = this;
        r0 = r27;
        r1 = r28;
        r5 = r29;
        r2 = r0.j;
        r2 = r2.getApplicationContext();
        r2 = (com.gaana.application.GaanaApplication) r2;
        r0.m = r2;
        r0.l = r5;
        r2 = r29.isLocalMedia();
        r3 = 2131296423; // 0x7f0900a7 float:1.8210762E38 double:1.0530003437E-314;
        r4 = 0;
        if (r2 != 0) goto L_0x009d;
    L_0x001c:
        r2 = 2131297048; // 0x7f090318 float:1.821203E38 double:1.0530006525E-314;
        if (r1 == r2) goto L_0x009d;
    L_0x0021:
        if (r1 == r3) goto L_0x009d;
    L_0x0023:
        r2 = 2131296877; // 0x7f09026d float:1.8211683E38 double:1.053000568E-314;
        if (r1 == r2) goto L_0x009d;
    L_0x0028:
        r2 = 2131297007; // 0x7f0902ef float:1.8211947E38 double:1.053000632E-314;
        if (r1 == r2) goto L_0x009d;
    L_0x002d:
        r2 = 2131297981; // 0x7f0906bd float:1.8213922E38 double:1.0530011135E-314;
        if (r1 == r2) goto L_0x009d;
    L_0x0032:
        r2 = 2131296882; // 0x7f090272 float:1.8211693E38 double:1.0530005705E-314;
        if (r1 == r2) goto L_0x009d;
    L_0x0037:
        r2 = 2131298410; // 0x7f09086a float:1.8214792E38 double:1.0530013254E-314;
        if (r1 == r2) goto L_0x009d;
    L_0x003c:
        r2 = 2131296875; // 0x7f09026b float:1.821168E38 double:1.053000567E-314;
        if (r1 == r2) goto L_0x009d;
    L_0x0041:
        r2 = 2131297980; // 0x7f0906bc float:1.821392E38 double:1.053001113E-314;
        if (r1 == r2) goto L_0x009d;
    L_0x0046:
        r2 = 2131297983; // 0x7f0906bf float:1.8213926E38 double:1.0530011145E-314;
        if (r1 == r2) goto L_0x009d;
    L_0x004b:
        r2 = 2131298040; // 0x7f0906f8 float:1.8214042E38 double:1.0530011426E-314;
        if (r1 == r2) goto L_0x009d;
    L_0x0050:
        r2 = 2131298475; // 0x7f0908ab float:1.8214924E38 double:1.0530013575E-314;
        if (r1 == r2) goto L_0x009d;
    L_0x0055:
        r2 = 2131297982; // 0x7f0906be float:1.8213924E38 double:1.053001114E-314;
        if (r1 == r2) goto L_0x009d;
    L_0x005a:
        r2 = 2131297124; // 0x7f090364 float:1.8212184E38 double:1.05300069E-314;
        if (r1 == r2) goto L_0x009d;
    L_0x005f:
        r2 = 2131296448; // 0x7f0900c0 float:1.8210813E38 double:1.053000356E-314;
        if (r1 == r2) goto L_0x009d;
    L_0x0064:
        r2 = 2131297976; // 0x7f0906b8 float:1.8213912E38 double:1.053001111E-314;
        if (r1 == r2) goto L_0x009d;
    L_0x0069:
        r2 = 2131296420; // 0x7f0900a4 float:1.8210756E38 double:1.053000342E-314;
        if (r1 == r2) goto L_0x009d;
    L_0x006e:
        r2 = r0.m;
        r2 = r2.isAppInOfflineMode();
        if (r2 == 0) goto L_0x008b;
    L_0x0076:
        r1 = r0.j;
        r1 = (com.gaana.BaseActivity) r1;
        r2 = r0.j;
        r2 = r2.getResources();
        r3 = 2131821264; // 0x7f1102d0 float:1.9275266E38 double:1.0532596496E-314;
        r2 = r2.getString(r3);
        r1.displayFeatureNotAvailableOfflineDialog(r2);
        return r4;
    L_0x008b:
        r2 = r0.j;
        r2 = com.utilities.Util.j(r2);
        if (r2 != 0) goto L_0x009d;
    L_0x0093:
        r1 = com.managers.ap.a();
        r2 = r0.j;
        r1.f(r2);
        return r4;
    L_0x009d:
        r2 = 2131822138; // 0x7f11063a float:1.9277039E38 double:1.0532600814E-314;
        r6 = 2131822139; // 0x7f11063b float:1.927704E38 double:1.053260082E-314;
        r8 = 1;
        switch(r1) {
            case 2131296420: goto L_0x148e;
            case 2131296423: goto L_0x1329;
            case 2131296446: goto L_0x127b;
            case 2131296448: goto L_0x112d;
            case 2131296471: goto L_0x0f13;
            case 2131296484: goto L_0x0ef0;
            case 2131296875: goto L_0x0eeb;
            case 2131296876: goto L_0x0ebc;
            case 2131296877: goto L_0x0eac;
            case 2131296879: goto L_0x0e8e;
            case 2131296882: goto L_0x0e70;
            case 2131296968: goto L_0x0e4b;
            case 2131296972: goto L_0x0e35;
            case 2131297007: goto L_0x0deb;
            case 2131297015: goto L_0x0dc6;
            case 2131297048: goto L_0x0b37;
            case 2131297049: goto L_0x0b37;
            case 2131297124: goto L_0x0b32;
            case 2131297514: goto L_0x0b26;
            case 2131297545: goto L_0x0af0;
            case 2131297651: goto L_0x0a11;
            case 2131297976: goto L_0x0a0b;
            case 2131297980: goto L_0x0a06;
            case 2131297981: goto L_0x09b7;
            case 2131297982: goto L_0x09a7;
            case 2131297983: goto L_0x09a2;
            case 2131298039: goto L_0x08f4;
            case 2131298040: goto L_0x09ab;
            case 2131298168: goto L_0x0607;
            case 2131298209: goto L_0x05f8;
            case 2131298211: goto L_0x05d8;
            case 2131298383: goto L_0x04e7;
            case 2131298398: goto L_0x049b;
            case 2131298399: goto L_0x0475;
            case 2131298410: goto L_0x01c3;
            case 2131298415: goto L_0x01b3;
            case 2131298416: goto L_0x01a3;
            case 2131298453: goto L_0x00b5;
            case 2131298475: goto L_0x00a9;
            default: goto L_0x00a7;
        };
    L_0x00a7:
        goto L_0x14b5;
    L_0x00a9:
        r1 = r5;
        r1 = (com.gaana.models.Playlists.Playlist) r1;
        r2 = com.constants.Constants.e();
        r0.c(r1, r2);
        goto L_0x14b5;
    L_0x00b5:
        r1 = r5 instanceof com.gaana.models.OfflineTrack;
        if (r1 == 0) goto L_0x00d0;
    L_0x00b9:
        r1 = r29.isLocalMedia();
        if (r1 != 0) goto L_0x00d0;
    L_0x00bf:
        r1 = com.managers.DownloadManager.c();
        r2 = r29.getBusinessObjId();
        r1 = r1.a(r2, r8);
        r1 = (com.gaana.models.Tracks.Track) r1;
        if (r1 == 0) goto L_0x00d0;
    L_0x00cf:
        goto L_0x00d1;
    L_0x00d0:
        r1 = r5;
    L_0x00d1:
        r2 = r1 instanceof com.gaana.models.Tracks.Track;
        if (r2 == 0) goto L_0x14b5;
    L_0x00d5:
        r2 = r0.j;
        r2 = (com.gaana.BaseActivity) r2;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r4 = r0.j;
        r4 = (com.gaana.BaseActivity) r4;
        r4 = r4.currentScreen;
        r3.append(r4);
        r4 = " - Detail";
        r3.append(r4);
        r3 = r3.toString();
        r4 = "Song Info";
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = r0.j;
        r6 = (com.gaana.BaseActivity) r6;
        r6 = r6.currentScreen;
        r5.append(r6);
        r6 = " - Detail - Song Info";
        r5.append(r6);
        r5 = r5.toString();
        r2.sendGAEvent(r3, r4, r5);
        r2 = r0.j;
        r2 = (com.gaana.GaanaActivity) r2;
        r2 = r2.getmCurrentPlayerFragment();
        if (r2 == 0) goto L_0x0130;
    L_0x0116:
        r9 = com.managers.an.a();
        r10 = "click";
        r11 = "ac";
        r12 = "three dot menu";
        r13 = "player";
        r14 = "";
        r15 = "Song Info";
        r16 = r1.getBusinessObjId();
        r17 = "";
        r9.a(r10, r11, r12, r13, r14, r15, r16, r17);
        goto L_0x0149;
    L_0x0130:
        r18 = com.managers.an.a();
        r19 = "click";
        r20 = "ac";
        r21 = "three dot menu";
        r22 = "Song Info";
        r23 = "";
        r24 = "";
        r25 = r1.getBusinessObjId();
        r26 = "";
        r18.a(r19, r20, r21, r22, r23, r24, r25, r26);
    L_0x0149:
        r2 = new com.fragments.MoreInfoFragment;
        r2.<init>();
        r0.n = r2;
        r2 = new android.os.Bundle;
        r2.<init>();
        r3 = r1;
        r3 = (com.gaana.models.Tracks.Track) r3;
        r4 = r3.getBusinessObjId();
        r5 = "TRACKID";
        r2.putString(r5, r4);
        r4 = "ALBUMID";
        r5 = r3.getAlbumId();
        r2.putString(r4, r5);
        r4 = "ALBUM_NAME";
        r5 = r3.getRawAlbumTitle();
        r2.putString(r4, r5);
        r4 = "TRACK_TITLE";
        r5 = r3.getTrackTitle();
        r2.putString(r4, r5);
        r4 = "LYRICS_URL";
        r5 = r3.getLyricsUrl();
        r2.putString(r4, r5);
        r4 = "ARTIST_NAMES";
        r3 = r3.getArtistNames();
        r2.putString(r4, r3);
        r3 = "BUSINESS_OBJECT";
        r2.putSerializable(r3, r1);
        r1 = r0.n;
        r1.setArguments(r2);
        r1 = r0.j;
        r1 = (com.gaana.GaanaActivity) r1;
        r2 = r0.n;
        r1.displayFragment(r2);
        goto L_0x14b5;
    L_0x01a3:
        r1 = r0.m;
        r2 = com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE.ARTISTS;
        r2 = r2.name();
        r1.setPlayoutSectionName(r2);
        r27.i();
        goto L_0x14b5;
    L_0x01b3:
        r1 = r0.m;
        r2 = com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE.SIMILAR_ALBUM;
        r2 = r2.name();
        r1.setPlayoutSectionName(r2);
        r27.h();
        goto L_0x14b5;
    L_0x01c3:
        r1 = r29.isLocalMedia();
        if (r1 == 0) goto L_0x01ce;
    L_0x01c9:
        r1 = com.constants.Constants.cY;
        if (r1 == 0) goto L_0x01ce;
    L_0x01cd:
        return r8;
    L_0x01ce:
        r1 = r0.j;
        r1 = (com.gaana.BaseActivity) r1;
        r1 = r1.currentScreen;
        r3 = "Fav";
        r1 = r1.startsWith(r3);
        if (r1 == 0) goto L_0x0230;
    L_0x01dc:
        r1 = r0.j;
        r1 = (com.gaana.BaseActivity) r1;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r7 = r0.j;
        r7 = (com.gaana.BaseActivity) r7;
        r7 = r7.currentScreen;
        r3.append(r7);
        r7 = " Detail";
        r3.append(r7);
        r3 = r3.toString();
        r7 = "Play";
        r9 = new java.lang.StringBuilder;
        r9.<init>();
        r10 = r0.j;
        r10 = (com.gaana.BaseActivity) r10;
        r10 = r10.currentScreen;
        r9.append(r10);
        r10 = " Detail - ";
        r9.append(r10);
        r10 = r0.j;
        r10 = (com.gaana.BaseActivity) r10;
        r10 = r10.currentItem;
        r9.append(r10);
        r10 = " - ";
        r9.append(r10);
        r10 = r0.j;
        r10 = (com.gaana.BaseActivity) r10;
        r10 = r10.currentFavpage;
        r9.append(r10);
        r10 = " - ShufflePlay";
        r9.append(r10);
        r9 = r9.toString();
        r1.sendGAEvent(r3, r7, r9);
        goto L_0x0275;
    L_0x0230:
        r1 = r0.j;
        r1 = (com.gaana.BaseActivity) r1;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r7 = r0.j;
        r7 = (com.gaana.BaseActivity) r7;
        r7 = r7.currentScreen;
        r3.append(r7);
        r7 = " Detail";
        r3.append(r7);
        r3 = r3.toString();
        r7 = "Play";
        r9 = new java.lang.StringBuilder;
        r9.<init>();
        r10 = r0.j;
        r10 = (com.gaana.BaseActivity) r10;
        r10 = r10.currentScreen;
        r9.append(r10);
        r10 = " Detail - ";
        r9.append(r10);
        r10 = r0.j;
        r10 = (com.gaana.BaseActivity) r10;
        r10 = r10.currentItem;
        r9.append(r10);
        r10 = " - ShufflePlay";
        r9.append(r10);
        r9 = r9.toString();
        r1.sendGAEvent(r3, r7, r9);
    L_0x0275:
        r1 = r0.n;
        r1 = r1 instanceof com.fragments.RevampedDetailListing;
        if (r1 == 0) goto L_0x02b3;
    L_0x027b:
        r1 = r0.n;
        r1 = (com.fragments.RevampedDetailListing) r1;
        r1 = r1.c;
        r3 = "ArtistDetailScreen";
        r1 = r1.startsWith(r3);
        if (r1 == 0) goto L_0x02b3;
    L_0x0289:
        r9 = com.managers.an.a();
        r10 = "click";
        r11 = "ac";
        r1 = r0.n;
        r1 = (com.fragments.RevampedDetailListing) r1;
        r1 = r1.s();
        r12 = r1.getBusinessObjId();
        r1 = r0.n;
        r1 = (com.fragments.RevampedDetailListing) r1;
        r13 = r1.d();
        r14 = r29.getBusinessObjId();
        r15 = "shuffle";
        r16 = "";
        r17 = "";
        r9.a(r10, r11, r12, r13, r14, r15, r16, r17);
        goto L_0x02ca;
    L_0x02b3:
        r18 = com.managers.an.a();
        r19 = "click";
        r20 = "ac";
        r21 = "three dot menu";
        r22 = "";
        r23 = "";
        r24 = "shuffle";
        r25 = "";
        r26 = "";
        r18.a(r19, r20, r21, r22, r23, r24, r25, r26);
    L_0x02ca:
        r1 = r5 instanceof com.gaana.models.Tracks.Track;
        if (r1 == 0) goto L_0x02d6;
    L_0x02ce:
        r1 = r5;
        r1 = (com.gaana.models.Tracks.Track) r1;
        r0.a(r1);
        goto L_0x14b5;
    L_0x02d6:
        r1 = r5 instanceof com.gaana.models.OfflineTrack;
        if (r1 == 0) goto L_0x0305;
    L_0x02da:
        r1 = r29.isLocalMedia();
        if (r1 == 0) goto L_0x02f2;
    L_0x02e0:
        r1 = r0.j;
        r1 = com.gaana.localmedia.LocalMediaManager.getInstance(r1);
        r2 = r5;
        r2 = (com.gaana.models.OfflineTrack) r2;
        r1 = r1.getTrackFromLocalMedia(r2);
        r0.a(r1);
        goto L_0x14b5;
    L_0x02f2:
        r1 = com.managers.DownloadManager.c();
        r2 = r29.getBusinessObjId();
        r1 = r1.a(r2, r8);
        r1 = (com.gaana.models.Tracks.Track) r1;
        r0.a(r1);
        goto L_0x14b5;
    L_0x0305:
        r1 = r29.getArrListBusinessObj();
        if (r1 == 0) goto L_0x03a1;
    L_0x030b:
        r3 = new java.util.ArrayList;
        r3.<init>();
        r4 = r0.m;
        r4 = r4.isAppInOfflineMode();
        if (r4 != 0) goto L_0x0323;
    L_0x0318:
        r4 = r0.j;
        r4 = com.utilities.Util.j(r4);
        if (r4 != 0) goto L_0x0321;
    L_0x0320:
        goto L_0x0323;
    L_0x0321:
        r7 = r1;
        goto L_0x036b;
    L_0x0323:
        r4 = r1.iterator();
    L_0x0327:
        r7 = r4.hasNext();
        if (r7 == 0) goto L_0x0353;
    L_0x032d:
        r7 = r4.next();
        r7 = (com.gaana.models.Tracks.Track) r7;
        r9 = com.managers.DownloadManager.c();
        r10 = r7.getBusinessObjId();
        r10 = java.lang.Integer.parseInt(r10);
        r9 = r9.l(r10);
        r9 = r9.booleanValue();
        if (r9 != 0) goto L_0x034f;
    L_0x0349:
        r9 = r7.isLocalMedia();
        if (r9 == 0) goto L_0x0327;
    L_0x034f:
        r3.add(r7);
        goto L_0x0327;
    L_0x0353:
        r4 = r3.size();
        if (r4 != 0) goto L_0x036a;
    L_0x0359:
        r1 = com.managers.aj.a();
        r3 = r0.j;
        r4 = r0.j;
        r2 = r4.getString(r2);
        r1.a(r3, r2);
        goto L_0x14b5;
    L_0x036a:
        r7 = r3;
    L_0x036b:
        r1 = r1.size();
        if (r1 != 0) goto L_0x0382;
    L_0x0371:
        r1 = com.managers.aj.a();
        r2 = r0.j;
        r3 = r0.j;
        r3 = r3.getString(r6);
        r1.a(r2, r3);
        goto L_0x14b5;
    L_0x0382:
        r1 = r0.j;
        r1 = com.managers.PlayerManager.a(r1);
        r2 = r29.getBusinessObjId();
        r3 = com.logging.d.a();
        r3 = r3.a(r5);
        r4 = r29.getEnglishName();
        r9 = r0.j;
        r6 = r7;
        r7 = r9;
        r1.a(r2, r3, r4, r5, r6, r7);
        goto L_0x14b5;
    L_0x03a1:
        r2 = r29.isLocalMedia();
        if (r2 != 0) goto L_0x03f1;
    L_0x03a7:
        r2 = com.managers.DownloadManager.c();
        r2 = r2.b(r5);
        r2 = r2.booleanValue();
        if (r2 == 0) goto L_0x03f1;
    L_0x03b5:
        r1 = com.managers.DownloadManager.c();
        r2 = r29.getBusinessObjId();
        r1 = r1.a(r2, r4);
        if (r1 == 0) goto L_0x03ea;
    L_0x03c3:
        r2 = r1.getArrListBusinessObj();
        if (r2 == 0) goto L_0x03ea;
    L_0x03c9:
        r6 = r1.getArrListBusinessObj();
        r1 = r0.j;
        r1 = com.managers.PlayerManager.a(r1);
        r2 = r29.getBusinessObjId();
        r3 = com.logging.d.a();
        r3 = r3.a(r5);
        r4 = r29.getEnglishName();
        r7 = r0.j;
        r1.a(r2, r3, r4, r5, r6, r7);
        goto L_0x14b5;
    L_0x03ea:
        r1 = r0.b;
        r0.a(r5, r1);
        goto L_0x14b5;
    L_0x03f1:
        r2 = r29.isLocalMedia();
        if (r2 == 0) goto L_0x046e;
    L_0x03f7:
        r2 = r5 instanceof com.gaana.models.Artists.Artist;
        if (r2 == 0) goto L_0x040f;
    L_0x03fb:
        r1 = r0.j;
        r1 = com.gaana.localmedia.LocalMediaManager.getInstance(r1);
        r2 = r29.getBusinessObjId();
        r2 = java.lang.Long.parseLong(r2);
        r1 = r1.getSongsByArtist(r2);
    L_0x040d:
        r7 = r1;
        goto L_0x0435;
    L_0x040f:
        r2 = r5 instanceof com.gaana.models.Albums.Album;
        if (r2 == 0) goto L_0x0422;
    L_0x0413:
        r1 = r0.j;
        r1 = com.gaana.localmedia.LocalMediaManager.getInstance(r1);
        r2 = r29.getBusinessObjId();
        r1 = r1.getSongsByAlbum(r2);
        goto L_0x040d;
    L_0x0422:
        r2 = r5 instanceof com.gaana.models.Playlists.Playlist;
        if (r2 == 0) goto L_0x040d;
    L_0x0426:
        r1 = r0.j;
        r1 = com.gaana.localmedia.LocalMediaManager.getInstance(r1);
        r2 = r29.getBusinessObjId();
        r1 = r1.getSongsByPlaylist(r2);
        goto L_0x040d;
    L_0x0435:
        if (r7 == 0) goto L_0x045d;
    L_0x0437:
        r1 = r7.size();
        if (r1 != 0) goto L_0x043e;
    L_0x043d:
        goto L_0x045d;
    L_0x043e:
        r1 = r0.j;
        r1 = com.managers.PlayerManager.a(r1);
        r2 = r29.getBusinessObjId();
        r3 = com.logging.d.a();
        r3 = r3.a(r5);
        r4 = r29.getName();
        r9 = r0.j;
        r6 = r7;
        r7 = r9;
        r1.a(r2, r3, r4, r5, r6, r7);
        goto L_0x14b5;
    L_0x045d:
        r1 = com.managers.aj.a();
        r2 = r0.j;
        r3 = r0.j;
        r3 = r3.getString(r6);
        r1.a(r2, r3);
        goto L_0x14b5;
    L_0x046e:
        r1 = r0.b;
        r0.a(r5, r1);
        goto L_0x14b5;
    L_0x0475:
        r1 = com.managers.u.a();
        r2 = "Share";
        r3 = "story";
        r4 = "instagram";
        r1.a(r2, r3, r4);
        r1 = r5 instanceof com.gaana.models.OfflineTrack;
        if (r1 == 0) goto L_0x0492;
    L_0x0486:
        r1 = r5;
        r1 = (com.gaana.models.OfflineTrack) r1;
        r1 = r1.getImageUrl();
        r0.d(r1);
        goto L_0x14b5;
    L_0x0492:
        r1 = r29.getAtw();
        r0.d(r1);
        goto L_0x14b5;
    L_0x049b:
        r1 = r5 instanceof com.gaana.juke.JukePlaylist;
        if (r1 == 0) goto L_0x14b5;
    L_0x049f:
        r1 = com.managers.u.a();
        r2 = "PartyHub";
        r3 = "Party_3Dot";
        r4 = "Share_Playlist";
        r1.a(r2, r3, r4);
        r1 = r5;
        r1 = (com.gaana.juke.JukePlaylist) r1;
        r2 = r1.getQrUrl();
        r3 = new android.os.Bundle;
        r3.<init>();
        r4 = "QR_URL";
        r3.putString(r4, r2);
        r2 = "ACTION";
        r4 = "Share";
        r3.putString(r2, r4);
        r2 = "txt_name";
        r4 = r29.getName();
        r3.putString(r2, r4);
        r2 = "DL_URL";
        r1 = r1.getDlUrl();
        r3.putString(r2, r1);
        r1 = new com.fragments.PartyQRFragment;
        r1.<init>();
        r1.setArguments(r3);
        r2 = r0.j;
        r2 = (com.gaana.GaanaActivity) r2;
        r2.displayFragment(r1);
        goto L_0x14b5;
    L_0x04e7:
        r1 = r0.n;
        r1 = r1 instanceof com.fragments.RevampedDetailListing;
        if (r1 == 0) goto L_0x04f7;
    L_0x04ed:
        r1 = "Share ";
        r2 = r0.n;
        r2 = (com.fragments.RevampedDetailListing) r2;
        r2.a(r1, r8);
        goto L_0x053c;
    L_0x04f7:
        r1 = r0.j;
        r1 = (com.gaana.BaseActivity) r1;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = r0.j;
        r3 = (com.gaana.BaseActivity) r3;
        r3 = r3.currentScreen;
        r2.append(r3);
        r3 = " Detail";
        r2.append(r3);
        r2 = r2.toString();
        r3 = "Share";
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r6 = r0.j;
        r6 = (com.gaana.BaseActivity) r6;
        r6 = r6.currentScreen;
        r4.append(r6);
        r6 = "- Detail  ";
        r4.append(r6);
        r6 = r0.j;
        r6 = (com.gaana.BaseActivity) r6;
        r6 = r6.currentItem;
        r4.append(r6);
        r6 = "- Share";
        r4.append(r6);
        r4 = r4.toString();
        r1.sendGAEvent(r2, r3, r4);
    L_0x053c:
        r1 = r0.j;
        r1 = (com.gaana.GaanaActivity) r1;
        r1 = r1.getmCurrentPlayerFragment();
        if (r1 == 0) goto L_0x0560;
    L_0x0546:
        r9 = com.managers.an.a();
        r10 = "click";
        r11 = "ac";
        r12 = "three dot menu";
        r13 = "player";
        r14 = "";
        r15 = "Share";
        r16 = r29.getBusinessObjId();
        r17 = "";
        r9.a(r10, r11, r12, r13, r14, r15, r16, r17);
        goto L_0x05b9;
    L_0x0560:
        r1 = r0.n;
        r1 = r1 instanceof com.fragments.RevampedDetailListing;
        if (r1 == 0) goto L_0x059e;
    L_0x0566:
        r1 = r0.n;
        r1 = (com.fragments.RevampedDetailListing) r1;
        r1 = r1.c;
        r2 = "ArtistDetailScreen";
        r1 = r1.startsWith(r2);
        if (r1 == 0) goto L_0x059e;
    L_0x0574:
        r9 = com.managers.an.a();
        r10 = "click";
        r11 = "ac";
        r1 = r0.n;
        r1 = (com.fragments.RevampedDetailListing) r1;
        r1 = r1.s();
        r12 = r1.getBusinessObjId();
        r1 = r0.n;
        r1 = (com.fragments.RevampedDetailListing) r1;
        r13 = r1.d();
        r14 = r29.getBusinessObjId();
        r15 = "share";
        r16 = "";
        r17 = "";
        r9.a(r10, r11, r12, r13, r14, r15, r16, r17);
        goto L_0x05b9;
    L_0x059e:
        r18 = com.managers.an.a();
        r19 = "click";
        r20 = "ac";
        r21 = r29.getBusinessObjId();
        r22 = r29.getName();
        r23 = "";
        r24 = "Share";
        r25 = "";
        r26 = "";
        r18.a(r19, r20, r21, r22, r23, r24, r25, r26);
    L_0x05b9:
        r1 = r5 instanceof com.gaana.models.OfflineTrack;
        if (r1 == 0) goto L_0x05ca;
    L_0x05bd:
        r1 = com.managers.DownloadManager.c();
        r2 = r29.getBusinessObjId();
        r1 = r1.a(r2, r8);
        goto L_0x05cb;
    L_0x05ca:
        r1 = r5;
    L_0x05cb:
        r2 = com.managers.ap.a();
        r3 = r0.j;
        r4 = r0.n;
        r2.a(r3, r1, r4);
        goto L_0x14b5;
    L_0x05d8:
        r1 = r29.getName();
        r2 = r5;
        r2 = (com.gaana.juke.JukePlaylist) r2;
        r3 = com.managers.u.a();
        r4 = "PartyHub";
        r5 = "Party_3Dot";
        r6 = "Rename_Playlist";
        r3.a(r4, r5, r6);
        r3 = r0.j;
        r4 = new com.managers.af$31;
        r4.<init>(r2);
        com.gaana.juke.JukeSessionManager.getRenamePlaylistDialog(r3, r1, r4);
        goto L_0x14b5;
    L_0x05f8:
        r1 = r0.n;
        r1 = r1 instanceof com.gaana.juke.JukePartyFragment;
        if (r1 == 0) goto L_0x14b5;
    L_0x05fe:
        r1 = r0.n;
        r1 = (com.gaana.juke.JukePartyFragment) r1;
        r1.onItemDelete(r5);
        goto L_0x14b5;
    L_0x0607:
        r1 = r0.j;
        r1 = r1 instanceof com.gaana.GaanaActivity;
        if (r1 == 0) goto L_0x0638;
    L_0x060d:
        r1 = r0.j;
        r1 = (com.gaana.GaanaActivity) r1;
        r1 = r1.isFinishing();
        if (r1 != 0) goto L_0x0638;
    L_0x0617:
        r1 = r0.j;
        r1 = (com.gaana.GaanaActivity) r1;
        r1 = r1.isPlayerExpanded();
        if (r1 == 0) goto L_0x0638;
    L_0x0621:
        r1 = com.gaana.application.GaanaApplication.getContext();
        r1 = com.managers.PlayerManager.a(r1);
        r1 = r1.m();
        r2 = com.managers.PlayerManager.PlayerType.GAANA_RADIO;
        if (r1 == r2) goto L_0x0638;
    L_0x0631:
        r1 = r0.j;
        r1 = (com.gaana.GaanaActivity) r1;
        r1.popBackStackImmediate();
    L_0x0638:
        r1 = com.constants.Constants.cY;
        if (r1 == 0) goto L_0x0647;
    L_0x063c:
        r1 = r0.j;
        r2 = new com.managers.af$29;
        r2.<init>(r5);
        com.gaana.juke.JukeSessionManager.getErrorDialog(r1, r4, r2);
        return r8;
    L_0x0647:
        r1 = r5 instanceof com.gaana.models.Radios.Radio;
        if (r1 == 0) goto L_0x064f;
    L_0x064b:
        r0.d(r5);
        goto L_0x0683;
    L_0x064f:
        r1 = r5 instanceof com.gaana.models.Artists.Artist;
        if (r1 == 0) goto L_0x065f;
    L_0x0653:
        r1 = r0.m;
        r2 = com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE.ARTISTS;
        r2 = r2.name();
        r1.setPlayoutSectionName(r2);
        goto L_0x0683;
    L_0x065f:
        r1 = r0.m;
        r1 = r1.getPlayoutSectionName();
        r2 = android.text.TextUtils.isEmpty(r1);
        if (r2 != 0) goto L_0x0678;
    L_0x066b:
        r2 = com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE.SONG_RADIO;
        r2 = r1.equals(r2);
        if (r2 != 0) goto L_0x0678;
    L_0x0673:
        r2 = r0.m;
        r2.setPlayoutSectionNamePrevForSongradio(r1);
    L_0x0678:
        r1 = r0.m;
        r2 = com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE.SONG_RADIO;
        r2 = r2.name();
        r1.setPlayoutSectionName(r2);
    L_0x0683:
        r1 = r0.j;
        r1 = (com.gaana.BaseActivity) r1;
        r1 = r1.currentScreen;
        r2 = "Fav";
        r1 = r1.startsWith(r2);
        if (r1 == 0) goto L_0x073c;
    L_0x0691:
        r1 = r0.j;
        r1 = (com.gaana.BaseActivity) r1;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = r0.j;
        r3 = (com.gaana.BaseActivity) r3;
        r3 = r3.currentScreen;
        r2.append(r3);
        r3 = " Detail";
        r2.append(r3);
        r2 = r2.toString();
        r3 = "Play";
        r6 = new java.lang.StringBuilder;
        r6.<init>();
        r7 = r0.j;
        r7 = (com.gaana.BaseActivity) r7;
        r7 = r7.currentScreen;
        r6.append(r7);
        r7 = " Detail - ";
        r6.append(r7);
        r7 = r0.j;
        r7 = (com.gaana.BaseActivity) r7;
        r7 = r7.currentItem;
        r6.append(r7);
        r7 = " - ";
        r6.append(r7);
        r7 = r0.j;
        r7 = (com.gaana.BaseActivity) r7;
        r7 = r7.currentFavpage;
        r6.append(r7);
        r7 = " - Radio";
        r6.append(r7);
        r6 = r6.toString();
        r1.sendGAEvent(r2, r3, r6);
        r1 = r0.n;
        r1 = r1 instanceof com.fragments.RevampedDetailListing;
        if (r1 == 0) goto L_0x0723;
    L_0x06ea:
        r1 = r0.n;
        r1 = (com.fragments.RevampedDetailListing) r1;
        r1 = r1.c;
        r2 = "ArtistDetailScreen";
        r1 = r1.startsWith(r2);
        if (r1 == 0) goto L_0x0723;
    L_0x06f8:
        r9 = com.managers.an.a();
        r10 = "click";
        r11 = "ac";
        r1 = r0.n;
        r1 = (com.fragments.RevampedDetailListing) r1;
        r1 = r1.s();
        r12 = r1.getBusinessObjId();
        r1 = r0.n;
        r1 = (com.fragments.RevampedDetailListing) r1;
        r13 = r1.d();
        r14 = r29.getBusinessObjId();
        r15 = "Radio";
        r16 = "";
        r17 = "";
        r9.a(r10, r11, r12, r13, r14, r15, r16, r17);
        goto L_0x07d6;
    L_0x0723:
        r18 = com.managers.an.a();
        r19 = "click";
        r20 = "ac";
        r21 = "three dot menu";
        r22 = "player";
        r23 = "";
        r24 = "Radio";
        r25 = "";
        r26 = "";
        r18.a(r19, r20, r21, r22, r23, r24, r25, r26);
        goto L_0x07d6;
    L_0x073c:
        r1 = r0.n;
        r1 = r1 instanceof com.fragments.RevampedDetailListing;
        if (r1 == 0) goto L_0x077a;
    L_0x0742:
        r1 = r0.n;
        r1 = (com.fragments.RevampedDetailListing) r1;
        r1 = r1.c;
        r2 = "ArtistDetailScreen";
        r1 = r1.startsWith(r2);
        if (r1 == 0) goto L_0x077a;
    L_0x0750:
        r9 = com.managers.an.a();
        r10 = "click";
        r11 = "ac";
        r1 = r0.n;
        r1 = (com.fragments.RevampedDetailListing) r1;
        r1 = r1.s();
        r12 = r1.getBusinessObjId();
        r1 = r0.n;
        r1 = (com.fragments.RevampedDetailListing) r1;
        r13 = r1.d();
        r14 = r29.getBusinessObjId();
        r15 = "Radio";
        r16 = "";
        r17 = "";
        r9.a(r10, r11, r12, r13, r14, r15, r16, r17);
        goto L_0x0791;
    L_0x077a:
        r18 = com.managers.an.a();
        r19 = "click";
        r20 = "ac";
        r21 = "three dot menu";
        r22 = "player";
        r23 = "";
        r24 = "Radio";
        r25 = "";
        r26 = "";
        r18.a(r19, r20, r21, r22, r23, r24, r25, r26);
    L_0x0791:
        r1 = r0.j;
        r1 = (com.gaana.BaseActivity) r1;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = r0.j;
        r3 = (com.gaana.BaseActivity) r3;
        r3 = r3.currentScreen;
        r2.append(r3);
        r3 = " Detail";
        r2.append(r3);
        r2 = r2.toString();
        r3 = "Play";
        r6 = new java.lang.StringBuilder;
        r6.<init>();
        r7 = r0.j;
        r7 = (com.gaana.BaseActivity) r7;
        r7 = r7.currentScreen;
        r6.append(r7);
        r7 = " Detail - ";
        r6.append(r7);
        r7 = r0.j;
        r7 = (com.gaana.BaseActivity) r7;
        r7 = r7.currentItem;
        r6.append(r7);
        r7 = " - Radio";
        r6.append(r7);
        r6 = r6.toString();
        r1.sendGAEvent(r2, r3, r6);
    L_0x07d6:
        r1 = r0.j;
        r1 = (com.gaana.BaseActivity) r1;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = r0.j;
        r3 = (com.gaana.BaseActivity) r3;
        r3 = r3.currentScreen;
        r2.append(r3);
        r3 = " Detail";
        r2.append(r3);
        r2 = r2.toString();
        r3 = "Play";
        r6 = new java.lang.StringBuilder;
        r6.<init>();
        r7 = r0.j;
        r7 = (com.gaana.BaseActivity) r7;
        r7 = r7.currentScreen;
        r6.append(r7);
        r7 = " Detail - ";
        r6.append(r7);
        r7 = r0.j;
        r7 = (com.gaana.BaseActivity) r7;
        r7 = r7.currentItem;
        r6.append(r7);
        r7 = " - Radio";
        r6.append(r7);
        r6 = r6.toString();
        r1.sendGAEvent(r2, r3, r6);
        r1 = r5 instanceof com.gaana.models.Artists.Artist;
        if (r1 == 0) goto L_0x0862;
    L_0x081f:
        r1 = r0.j;
        r1 = (com.gaana.BaseActivity) r1;
        r2 = r0.j;
        r2 = (com.gaana.BaseActivity) r2;
        r2 = r2.currentScreen;
        r3 = "Artist Start Radio";
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r6 = r0.j;
        r6 = (com.gaana.BaseActivity) r6;
        r6 = r6.currentScreen;
        r4.append(r6);
        r6 = "- Artist - Radio";
        r4.append(r6);
        r4 = r4.toString();
        r1.sendGAEvent(r2, r3, r4);
        r1 = "https://api.gaana.com/radio.php?type=radio&subtype=artistradios&artist_id=<artist_id>&page=1&limit=10";
        r2 = "<artist_id>";
        r3 = r29.getBusinessObjId();
        r1 = r1.replace(r2, r3);
        r2 = r0.j;
        r2 = com.managers.ad.a(r2);
        r3 = com.logging.GaanaLogger.SOURCE_TYPE.ARTIST_RADIO;
        r3 = r3.ordinal();
        r2.a(r1, r3, r5);
        goto L_0x14b5;
    L_0x0862:
        r1 = r5 instanceof com.gaana.models.Tracks.Track;
        if (r1 != 0) goto L_0x086a;
    L_0x0866:
        r2 = r5 instanceof com.gaana.models.OfflineTrack;
        if (r2 == 0) goto L_0x14b5;
    L_0x086a:
        r2 = r0.n;
        r2 = r2 instanceof com.fragments.RevampedDetailListing;
        if (r2 == 0) goto L_0x087a;
    L_0x0870:
        r2 = "Track Radio";
        r3 = r0.n;
        r3 = (com.fragments.RevampedDetailListing) r3;
        r3.a(r2, r4);
        goto L_0x08a0;
    L_0x087a:
        r2 = r0.j;
        r2 = (com.gaana.BaseActivity) r2;
        r3 = r0.j;
        r3 = (com.gaana.BaseActivity) r3;
        r3 = r3.currentScreen;
        r4 = "Track Start Radio";
        r6 = new java.lang.StringBuilder;
        r6.<init>();
        r7 = r0.j;
        r7 = (com.gaana.BaseActivity) r7;
        r7 = r7.currentScreen;
        r6.append(r7);
        r7 = "- Artist - Radio";
        r6.append(r7);
        r6 = r6.toString();
        r2.sendGAEvent(r3, r4, r6);
    L_0x08a0:
        r2 = "https://api.gaana.com/radio.php?type=radio&subtype=songredios&track_id=<track_id>&page=1&limit=10";
        r3 = "<track_id>";
        r4 = r29.getBusinessObjId();
        r2 = r2.replace(r3, r4);
        r3 = r0.j;
        r3 = com.managers.ad.a(r3);
        r3.a(r8);
        if (r1 == 0) goto L_0x08c4;
    L_0x08b7:
        r1 = r0.j;
        r1 = com.managers.ad.a(r1);
        r3 = r5;
        r3 = (com.gaana.models.Tracks.Track) r3;
        r1.a(r3);
        goto L_0x08e3;
    L_0x08c4:
        r1 = r5 instanceof com.gaana.models.OfflineTrack;
        if (r1 == 0) goto L_0x08e3;
    L_0x08c8:
        r1 = com.managers.DownloadManager.c();
        r3 = r29.getBusinessObjId();
        r1 = r1.a(r3, r8);
        r3 = r1 instanceof com.gaana.models.Tracks.Track;
        if (r3 == 0) goto L_0x08e3;
    L_0x08d8:
        r3 = r0.j;
        r3 = com.managers.ad.a(r3);
        r1 = (com.gaana.models.Tracks.Track) r1;
        r3.a(r1);
    L_0x08e3:
        r1 = r0.j;
        r1 = com.managers.ad.a(r1);
        r3 = com.logging.GaanaLogger.SOURCE_TYPE.OTHER;
        r3 = r3.ordinal();
        r1.a(r2, r3, r5);
        goto L_0x14b5;
    L_0x08f4:
        r1 = r5 instanceof com.gaana.models.Playlists.Playlist;
        if (r1 == 0) goto L_0x14b5;
    L_0x08f8:
        r1 = r0.j;
        r1 = (com.gaana.BaseActivity) r1;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = r0.j;
        r3 = (com.gaana.BaseActivity) r3;
        r3 = r3.currentScreen;
        r2.append(r3);
        r3 = " - Detail";
        r2.append(r3);
        r2 = r2.toString();
        r3 = "Playlist Info";
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r6 = r0.j;
        r6 = (com.gaana.BaseActivity) r6;
        r6 = r6.currentScreen;
        r4.append(r6);
        r6 = " - Detail - Playlist Info";
        r4.append(r6);
        r4 = r4.toString();
        r1.sendGAEvent(r2, r3, r4);
        r1 = r0.j;
        r1 = (com.gaana.GaanaActivity) r1;
        r1 = r1.getmCurrentPlayerFragment();
        if (r1 == 0) goto L_0x0953;
    L_0x0939:
        r9 = com.managers.an.a();
        r10 = "click";
        r11 = "ac";
        r12 = "three dot menu";
        r13 = "player";
        r14 = "";
        r15 = "Playlist Info";
        r16 = r29.getBusinessObjId();
        r17 = "";
        r9.a(r10, r11, r12, r13, r14, r15, r16, r17);
        goto L_0x096c;
    L_0x0953:
        r18 = com.managers.an.a();
        r19 = "click";
        r20 = "ac";
        r21 = "three dot menu";
        r22 = "";
        r23 = "";
        r24 = "Playlist Info";
        r25 = r29.getBusinessObjId();
        r26 = "";
        r18.a(r19, r20, r21, r22, r23, r24, r25, r26);
    L_0x096c:
        r1 = new com.fragments.MoreInfoFragment;
        r1.<init>();
        r0.n = r1;
        r1 = new android.os.Bundle;
        r1.<init>();
        r2 = r5;
        r2 = (com.gaana.models.Playlists.Playlist) r2;
        r3 = r2.getBusinessObjId();
        r4 = "PLAYLISTID";
        r1.putString(r4, r3);
        r3 = "PLAYLIST_NAME";
        r2 = r2.getRawName();
        r1.putString(r3, r2);
        r2 = "BUSINESS_OBJECT";
        r1.putSerializable(r2, r5);
        r2 = r0.n;
        r2.setArguments(r1);
        r1 = r0.j;
        r1 = (com.gaana.GaanaActivity) r1;
        r2 = r0.n;
        r1.displayFragment(r2);
        goto L_0x14b5;
    L_0x09a2:
        r0.a(r8);
        goto L_0x14b5;
    L_0x09a7:
        r1 = "play";
        k = r1;
    L_0x09ab:
        r1 = r5;
        r1 = (com.gaana.models.Playlists.Playlist) r1;
        r2 = com.constants.Constants.e();
        r0.a(r1, r2);
        goto L_0x14b5;
    L_0x09b7:
        r1 = r5 instanceof com.gaana.models.Tracks.Track;
        if (r1 == 0) goto L_0x09bf;
    L_0x09bb:
        r0.e(r5);
        goto L_0x09eb;
    L_0x09bf:
        r1 = r5 instanceof com.gaana.models.OfflineTrack;
        if (r1 == 0) goto L_0x09eb;
    L_0x09c3:
        r1 = r29.isLocalMedia();
        if (r1 == 0) goto L_0x09da;
    L_0x09c9:
        r1 = r0.j;
        r1 = com.gaana.localmedia.LocalMediaManager.getInstance(r1);
        r2 = r5;
        r2 = (com.gaana.models.OfflineTrack) r2;
        r1 = r1.getTrackFromLocalMedia(r2);
        r0.e(r1);
        goto L_0x09eb;
    L_0x09da:
        r1 = com.managers.DownloadManager.c();
        r2 = r29.getBusinessObjId();
        r1 = r1.a(r2, r8);
        r1 = (com.gaana.models.Tracks.Track) r1;
        r0.e(r1);
    L_0x09eb:
        r9 = com.managers.an.a();
        r10 = "click";
        r11 = "ac";
        r12 = "three dot menu";
        r13 = "playnext";
        r14 = "";
        r15 = "";
        r16 = r29.getBusinessObjId();
        r17 = "";
        r9.a(r10, r11, r12, r13, r14, r15, r16, r17);
        goto L_0x14b5;
    L_0x0a06:
        r0.a(r4);
        goto L_0x14b5;
    L_0x0a0b:
        r1 = "play";
        k = r1;
        goto L_0x112d;
    L_0x0a11:
        r1 = r0.j;
        r1 = com.utilities.Util.j(r1);
        if (r1 == 0) goto L_0x0ae5;
    L_0x0a19:
        r1 = r0.m;
        r1 = r1.isAppInOfflineMode();
        if (r1 == 0) goto L_0x0a23;
    L_0x0a21:
        goto L_0x0ae5;
    L_0x0a23:
        r1 = r5 instanceof com.gaana.models.OfflineTrack;
        if (r1 == 0) goto L_0x0a34;
    L_0x0a27:
        r1 = com.managers.DownloadManager.c();
        r2 = r29.getBusinessObjId();
        r1 = r1.a(r2, r8);
        goto L_0x0a35;
    L_0x0a34:
        r1 = r5;
    L_0x0a35:
        r9 = com.managers.an.a();
        r10 = "click";
        r11 = "ac";
        r12 = "";
        r13 = "three dot menu";
        r14 = "";
        r15 = "lyrics";
        r16 = r1.getBusinessObjId();
        r17 = "";
        r9.a(r10, r11, r12, r13, r14, r15, r16, r17);
        r1 = (com.gaana.models.Tracks.Track) r1;
        r2 = r1.getLyricsType();
        r3 = r1.getLyricsUrl();
        r4 = android.text.TextUtils.isEmpty(r2);
        if (r4 != 0) goto L_0x0aba;
    L_0x0a5e:
        r4 = "html";
        r4 = r2.equalsIgnoreCase(r4);
        if (r4 == 0) goto L_0x0a67;
    L_0x0a66:
        goto L_0x0aba;
    L_0x0a67:
        r3 = android.text.TextUtils.isEmpty(r2);
        if (r3 != 0) goto L_0x14b5;
    L_0x0a6d:
        r3 = "lrc";
        r3 = r2.equalsIgnoreCase(r3);
        if (r3 != 0) goto L_0x0a7d;
    L_0x0a75:
        r3 = "txt";
        r2 = r2.equalsIgnoreCase(r3);
        if (r2 == 0) goto L_0x14b5;
    L_0x0a7d:
        r2 = r0.j;
        r2 = (com.gaana.GaanaActivity) r2;
        r2 = r2.getmCurrentPlayerFragment();
        r3 = r2 instanceof com.fragments.PlayerFragmentV4;
        if (r3 == 0) goto L_0x0a90;
    L_0x0a89:
        r2 = (com.fragments.PlayerFragmentV4) r2;
        r2.a(r1);
        goto L_0x14b5;
    L_0x0a90:
        r3 = r2 instanceof com.fragments.PlayerRadioFragmentV4;
        if (r3 == 0) goto L_0x0a9b;
    L_0x0a94:
        r2 = (com.fragments.PlayerRadioFragmentV4) r2;
        r2.a();
        goto L_0x14b5;
    L_0x0a9b:
        r2 = new com.fragments.LyricsDisplayFragment;
        r2.<init>();
        r1 = com.utilities.Util.a(r1);
        r3 = new android.os.Bundle;
        r3.<init>();
        r4 = "lyrics_object";
        r3.putSerializable(r4, r1);
        r2.setArguments(r3);
        r1 = r0.j;
        r1 = (com.gaana.GaanaActivity) r1;
        r1.displayFragment(r2);
        goto L_0x14b5;
    L_0x0aba:
        r1 = new android.content.Intent;
        r2 = r0.j;
        r4 = com.gaana.WebViewActivity.class;
        r1.<init>(r2, r4);
        r2 = "EXTRA_WEBVIEW_URL";
        r1.putExtra(r2, r3);
        r2 = "EXTRA_SHOW_ACTIONBAR";
        r1.putExtra(r2, r8);
        r2 = "EXTRA_SHOW_ACTIONBAR2";
        r1.putExtra(r2, r8);
        r2 = "EXTRA_SHOW_WEB_BARS";
        r1.putExtra(r2, r8);
        r2 = "title";
        r3 = "Lyrics";
        r1.putExtra(r2, r3);
        r2 = r0.j;
        r2.startActivity(r1);
        goto L_0x14b5;
    L_0x0ae5:
        r1 = com.managers.ap.a();
        r2 = r0.j;
        r1.f(r2);
        goto L_0x14b5;
    L_0x0af0:
        r1 = com.managers.u.a();
        r2 = "PartyHub";
        r3 = "Party_3Dot";
        r6 = "Leave_Playlist";
        r1.a(r2, r3, r6);
        r1 = r5 instanceof com.gaana.juke.JukePlaylist;
        if (r1 == 0) goto L_0x14b5;
    L_0x0b01:
        r1 = r0.j;
        r1 = (com.gaana.BaseActivity) r1;
        r2 = java.lang.Boolean.valueOf(r8);
        r3 = r0.j;
        r6 = 2131822778; // 0x7f1108ba float:1.9278337E38 double:1.0532603976E-314;
        r3 = r3.getString(r6);
        r1.showProgressDialog(r2, r3);
        r1 = com.gaana.juke.JukeSessionManager.getInstance();
        r2 = r5;
        r2 = (com.gaana.juke.JukePlaylist) r2;
        r3 = new com.managers.af$2;
        r3.<init>();
        r1.addRemoveJoinee(r2, r4, r4, r3);
        goto L_0x14b5;
    L_0x0b26:
        r1 = r5;
        r1 = (com.gaana.models.Playlists.Playlist) r1;
        r2 = com.constants.Constants.h();
        r0.b(r1, r2);
        goto L_0x14b5;
    L_0x0b32:
        r27.j();
        goto L_0x14b5;
    L_0x0b37:
        r3 = 2131297049; // 0x7f090319 float:1.8212032E38 double:1.053000653E-314;
        if (r1 != r3) goto L_0x0b3e;
    L_0x0b3c:
        r1 = r8;
        goto L_0x0b3f;
    L_0x0b3e:
        r1 = r4;
    L_0x0b3f:
        r0.s = r1;
        r3 = r0.s;
        if (r3 == 0) goto L_0x0b9b;
    L_0x0b45:
        r3 = r0.n;
        r3 = r3 instanceof com.fragments.RevampedDetailListing;
        if (r3 == 0) goto L_0x0b83;
    L_0x0b4b:
        r3 = r0.n;
        r3 = (com.fragments.RevampedDetailListing) r3;
        r3 = r3.c;
        r7 = "ArtistDetailScreen";
        r3 = r3.startsWith(r7);
        if (r3 == 0) goto L_0x0b83;
    L_0x0b59:
        r9 = com.managers.an.a();
        r10 = "click";
        r11 = "ac";
        r3 = r0.n;
        r3 = (com.fragments.RevampedDetailListing) r3;
        r3 = r3.s();
        r12 = r3.getBusinessObjId();
        r3 = r0.n;
        r3 = (com.fragments.RevampedDetailListing) r3;
        r13 = r3.d();
        r14 = r29.getBusinessObjId();
        r15 = "addtonext";
        r16 = "";
        r17 = "";
        r9.a(r10, r11, r12, r13, r14, r15, r16, r17);
        goto L_0x0bf0;
    L_0x0b83:
        r18 = com.managers.an.a();
        r19 = "click";
        r20 = "ac";
        r21 = "three dot menu";
        r22 = "";
        r23 = "";
        r24 = "addtonext";
        r25 = "";
        r26 = "";
        r18.a(r19, r20, r21, r22, r23, r24, r25, r26);
        goto L_0x0bf0;
    L_0x0b9b:
        r3 = r0.n;
        r3 = r3 instanceof com.fragments.RevampedDetailListing;
        if (r3 == 0) goto L_0x0bd9;
    L_0x0ba1:
        r3 = r0.n;
        r3 = (com.fragments.RevampedDetailListing) r3;
        r3 = r3.c;
        r7 = "ArtistDetailScreen";
        r3 = r3.startsWith(r7);
        if (r3 == 0) goto L_0x0bd9;
    L_0x0baf:
        r9 = com.managers.an.a();
        r10 = "click";
        r11 = "ac";
        r3 = r0.n;
        r3 = (com.fragments.RevampedDetailListing) r3;
        r3 = r3.s();
        r12 = r3.getBusinessObjId();
        r3 = r0.n;
        r3 = (com.fragments.RevampedDetailListing) r3;
        r13 = r3.d();
        r14 = r29.getBusinessObjId();
        r15 = "addtoqueue";
        r16 = "";
        r17 = "";
        r9.a(r10, r11, r12, r13, r14, r15, r16, r17);
        goto L_0x0bf0;
    L_0x0bd9:
        r18 = com.managers.an.a();
        r19 = "click";
        r20 = "ac";
        r21 = "three dot menu";
        r22 = "";
        r23 = "";
        r24 = "addtoqueue";
        r25 = "";
        r26 = "";
        r18.a(r19, r20, r21, r22, r23, r24, r25, r26);
    L_0x0bf0:
        r3 = r5 instanceof com.gaana.models.Tracks.Track;
        if (r3 == 0) goto L_0x0bf9;
    L_0x0bf4:
        r0.a(r5, r1);
        goto L_0x14b5;
    L_0x0bf9:
        r3 = r5 instanceof com.gaana.models.OfflineTrack;
        if (r3 == 0) goto L_0x0c28;
    L_0x0bfd:
        r2 = r29.isLocalMedia();
        if (r2 == 0) goto L_0x0c15;
    L_0x0c03:
        r2 = r0.j;
        r2 = com.gaana.localmedia.LocalMediaManager.getInstance(r2);
        r3 = r5;
        r3 = (com.gaana.models.OfflineTrack) r3;
        r2 = r2.getTrackFromLocalMedia(r3);
        r0.a(r2, r1);
        goto L_0x14b5;
    L_0x0c15:
        r2 = com.managers.DownloadManager.c();
        r3 = r29.getBusinessObjId();
        r2 = r2.a(r3, r8);
        r2 = (com.gaana.models.Tracks.Track) r2;
        r0.a(r2, r1);
        goto L_0x14b5;
    L_0x0c28:
        r3 = r29.getArrListBusinessObj();
        r7 = 2131821123; // 0x7f110243 float:1.927498E38 double:1.05325958E-314;
        if (r3 == 0) goto L_0x0cea;
    L_0x0c31:
        r4 = new java.util.ArrayList;
        r4.<init>();
        r9 = r0.m;
        r9 = r9.isAppInOfflineMode();
        if (r9 != 0) goto L_0x0c49;
    L_0x0c3e:
        r9 = r0.j;
        r9 = com.utilities.Util.j(r9);
        if (r9 != 0) goto L_0x0c47;
    L_0x0c46:
        goto L_0x0c49;
    L_0x0c47:
        r4 = r3;
        goto L_0x0c90;
    L_0x0c49:
        r9 = r3.iterator();
    L_0x0c4d:
        r10 = r9.hasNext();
        if (r10 == 0) goto L_0x0c79;
    L_0x0c53:
        r10 = r9.next();
        r10 = (com.gaana.models.Tracks.Track) r10;
        r11 = com.managers.DownloadManager.c();
        r12 = r10.getBusinessObjId();
        r12 = java.lang.Integer.parseInt(r12);
        r11 = r11.l(r12);
        r11 = r11.booleanValue();
        if (r11 != 0) goto L_0x0c75;
    L_0x0c6f:
        r11 = r10.isLocalMedia();
        if (r11 == 0) goto L_0x0c4d;
    L_0x0c75:
        r4.add(r10);
        goto L_0x0c4d;
    L_0x0c79:
        r9 = r4.size();
        if (r9 != 0) goto L_0x0c90;
    L_0x0c7f:
        r1 = com.managers.aj.a();
        r3 = r0.j;
        r4 = r0.j;
        r2 = r4.getString(r2);
        r1.a(r3, r2);
        goto L_0x14b5;
    L_0x0c90:
        r2 = r0.j;
        r2 = (com.gaana.GaanaActivity) r2;
        r9 = java.lang.Boolean.valueOf(r8);
        r10 = r0.j;
        r7 = r10.getString(r7);
        r2.showProgressDialog(r9, r7);
        r2 = r3.size();
        if (r2 != 0) goto L_0x0cb7;
    L_0x0ca7:
        r1 = com.managers.aj.a();
        r2 = r0.j;
        r3 = r0.j;
        r3 = r3.getString(r6);
        r1.a(r2, r3);
        goto L_0x0ce1;
    L_0x0cb7:
        r2 = com.constants.Constants.cY;
        if (r2 != 0) goto L_0x0cce;
    L_0x0cbb:
        r2 = r0.j;
        r2 = com.managers.PlayerManager.a(r2);
        r3 = r0.j;
        r2.a(r4, r5, r3, r1);
        r1 = r0.j;
        r1 = (com.gaana.GaanaActivity) r1;
        r1.setUpdatePlayerFragment();
        goto L_0x0ce1;
    L_0x0cce:
        r1 = com.gaana.juke.JukeSessionManager.getInstance();
        r2 = com.gaana.juke.JukeSessionManager.getInstance();
        r2 = r2.getJukeSessionPlaylist();
        r3 = r29.getArrListBusinessObj();
        r1.addPlayNext(r2, r3);
    L_0x0ce1:
        r1 = r0.j;
        r1 = (com.gaana.GaanaActivity) r1;
        r1.hideProgressDialog();
        goto L_0x14b5;
    L_0x0cea:
        r2 = r29.isLocalMedia();
        if (r2 != 0) goto L_0x0d61;
    L_0x0cf0:
        r2 = com.managers.DownloadManager.c();
        r2 = r2.b(r5);
        r2 = r2.booleanValue();
        if (r2 == 0) goto L_0x0d61;
    L_0x0cfe:
        r2 = com.managers.DownloadManager.c();
        r3 = r29.getBusinessObjId();
        r2 = r2.a(r3, r4);
        if (r2 == 0) goto L_0x0d5a;
    L_0x0d0c:
        r3 = r2.getArrListBusinessObj();
        if (r3 == 0) goto L_0x0d5a;
    L_0x0d12:
        r3 = r2.getArrListBusinessObj();
        r4 = r0.j;
        r4 = (com.gaana.GaanaActivity) r4;
        r6 = java.lang.Boolean.valueOf(r8);
        r9 = r0.j;
        r7 = r9.getString(r7);
        r4.showProgressDialog(r6, r7);
        r4 = com.constants.Constants.cY;
        if (r4 != 0) goto L_0x0d3e;
    L_0x0d2b:
        r2 = r0.j;
        r2 = com.managers.PlayerManager.a(r2);
        r4 = r0.j;
        r2.a(r3, r5, r4, r1);
        r1 = r0.j;
        r1 = (com.gaana.GaanaActivity) r1;
        r1.setUpdatePlayerFragment();
        goto L_0x0d51;
    L_0x0d3e:
        r1 = com.gaana.juke.JukeSessionManager.getInstance();
        r3 = com.gaana.juke.JukeSessionManager.getInstance();
        r3 = r3.getJukeSessionPlaylist();
        r2 = r2.getArrListBusinessObj();
        r1.addPlayNext(r3, r2);
    L_0x0d51:
        r1 = r0.j;
        r1 = (com.gaana.GaanaActivity) r1;
        r1.hideProgressDialog();
        goto L_0x14b5;
    L_0x0d5a:
        r1 = r0.d;
        r0.a(r5, r1);
        goto L_0x14b5;
    L_0x0d61:
        r2 = r29.isLocalMedia();
        if (r2 == 0) goto L_0x0dbf;
    L_0x0d67:
        r2 = com.constants.Constants.cY;
        if (r2 == 0) goto L_0x0d6c;
    L_0x0d6b:
        return r8;
    L_0x0d6c:
        r2 = r5 instanceof com.gaana.models.Albums.Album;
        if (r2 == 0) goto L_0x0d7f;
    L_0x0d70:
        r2 = r0.j;
        r2 = com.gaana.localmedia.LocalMediaManager.getInstance(r2);
        r3 = r29.getBusinessObjId();
        r3 = r2.getSongsByAlbum(r3);
        goto L_0x0d91;
    L_0x0d7f:
        r2 = r5 instanceof com.gaana.models.Playlists.Playlist;
        if (r2 == 0) goto L_0x0d91;
    L_0x0d83:
        r2 = r0.j;
        r2 = com.gaana.localmedia.LocalMediaManager.getInstance(r2);
        r3 = r29.getBusinessObjId();
        r3 = r2.getSongsByPlaylist(r3);
    L_0x0d91:
        if (r3 == 0) goto L_0x0dae;
    L_0x0d93:
        r2 = r3.size();
        if (r2 != 0) goto L_0x0d9a;
    L_0x0d99:
        goto L_0x0dae;
    L_0x0d9a:
        r2 = r0.j;
        r2 = com.managers.PlayerManager.a(r2);
        r4 = r0.j;
        r2.a(r3, r5, r4, r1);
        r1 = r0.j;
        r1 = (com.gaana.GaanaActivity) r1;
        r1.setUpdatePlayerFragment();
        goto L_0x14b5;
    L_0x0dae:
        r1 = com.managers.aj.a();
        r2 = r0.j;
        r3 = r0.j;
        r3 = r3.getString(r6);
        r1.a(r2, r3);
        goto L_0x14b5;
    L_0x0dbf:
        r1 = r0.d;
        r0.a(r5, r1);
        goto L_0x14b5;
    L_0x0dc6:
        r1 = com.services.d.a();
        r2 = "pref_juke_nick";
        r3 = "";
        r1 = r1.b(r2, r3, r4);
        r2 = com.managers.u.a();
        r3 = "PartyHub";
        r4 = "Party_3Dot";
        r5 = "Edit_NickName";
        r2.a(r3, r4, r5);
        r2 = r0.j;
        r3 = new com.managers.af$30;
        r3.<init>();
        com.gaana.juke.JukeSessionManager.getNickDialog(r2, r1, r3);
        goto L_0x14b5;
    L_0x0deb:
        r1 = r5;
        r1 = (com.gaana.models.Playlists.Playlist) r1;
        r2 = r1.getIsMiniPlaylist();
        r2 = android.text.TextUtils.isEmpty(r2);
        if (r2 != 0) goto L_0x0e30;
    L_0x0df8:
        r2 = r1.getIsMiniPlaylist();
        r3 = java.lang.Integer.valueOf(r4);
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0e07;
    L_0x0e06:
        goto L_0x0e30;
    L_0x0e07:
        r2 = r0.m;
        r2 = r2.isAppInOfflineMode();
        if (r2 == 0) goto L_0x0e19;
    L_0x0e0f:
        r1 = r0.j;
        r1 = (com.gaana.BaseActivity) r1;
        r2 = "This feature ";
        r1.displayFeatureNotAvailableOfflineDialog(r2);
        return r8;
    L_0x0e19:
        r2 = r0.j;
        r2 = com.utilities.Util.j(r2);
        if (r2 != 0) goto L_0x0e2b;
    L_0x0e21:
        r1 = com.managers.ap.a();
        r2 = r0.j;
        r1.f(r2);
        return r8;
    L_0x0e2b:
        r0.a(r1);
        goto L_0x14b5;
    L_0x0e30:
        r0.a(r1);
        goto L_0x14b5;
    L_0x0e35:
        r1 = com.managers.u.a();
        r2 = "PartyHub";
        r3 = "Party_3Dot";
        r4 = "Download_All_Songs";
        r1.a(r2, r3, r4);
        r1 = r5 instanceof com.gaana.juke.JukePlaylist;
        if (r1 == 0) goto L_0x14b5;
    L_0x0e46:
        r27.e();
        goto L_0x14b5;
    L_0x0e4b:
        r1 = com.gaana.analytics.MoEngage.getInstance();
        r1.reportDownload(r5);
        r9 = com.managers.an.a();
        r10 = "click";
        r11 = "ac";
        r12 = "three dot menu";
        r13 = "download";
        r14 = "";
        r15 = "";
        r16 = r29.getBusinessObjId();
        r17 = "";
        r9.a(r10, r11, r12, r13, r14, r15, r16, r17);
        r27.e();
        goto L_0x14b5;
    L_0x0e70:
        r1 = r5 instanceof com.gaana.models.Tracks.Track;
        if (r1 == 0) goto L_0x14b5;
    L_0x0e74:
        r1 = com.managers.u.a();
        r2 = "PlayerQueue";
        r3 = "Track Removed";
        r6 = "Player Queue - Track Removed";
        r1.a(r2, r3, r6);
        r1 = r0.j;
        r1 = com.managers.PlayerManager.a(r1);
        r2 = r0.j;
        r1.a(r5, r4, r2);
        goto L_0x14b5;
    L_0x0e8e:
        r1 = com.managers.u.a();
        r2 = "PartyHub";
        r3 = "Party_3Dot";
        r4 = "Delete_Playlist";
        r1.a(r2, r3, r4);
        r1 = r5 instanceof com.gaana.juke.JukePlaylist;
        if (r1 == 0) goto L_0x14b5;
    L_0x0e9f:
        r1 = r0.j;
        r2 = 5;
        r3 = new com.managers.af$3;
        r3.<init>();
        com.gaana.juke.JukeSessionManager.getDialog(r1, r2, r3);
        goto L_0x14b5;
    L_0x0eac:
        r1 = com.gaana.localmedia.PlaylistSyncManager.getInstance();
        r2 = r0.j;
        r2 = (android.app.Activity) r2;
        r3 = r5;
        r3 = (com.gaana.models.Playlists.Playlist) r3;
        r1.showDailogueAnddeletePlaylist(r2, r3);
        goto L_0x14b5;
    L_0x0ebc:
        r1 = r0.j;
        r1 = r1.getResources();
        r2 = 2131822705; // 0x7f110871 float:1.9278189E38 double:1.0532603616E-314;
        r1 = r1.getString(r2);
        r2 = r5 instanceof com.gaana.models.Playlists.Playlist;
        if (r2 == 0) goto L_0x0eda;
    L_0x0ecd:
        r1 = r0.j;
        r1 = r1.getResources();
        r2 = 2131822704; // 0x7f110870 float:1.9278187E38 double:1.053260361E-314;
        r1 = r1.getString(r2);
    L_0x0eda:
        r2 = new com.gaana.view.item.CustomDialogView;
        r3 = r0.j;
        r4 = new com.managers.af$28;
        r4.<init>();
        r2.<init>(r3, r1, r4);
        r2.show();
        goto L_0x14b5;
    L_0x0eeb:
        r27.m();
        goto L_0x14b5;
    L_0x0ef0:
        r1 = com.managers.u.a();
        r2 = "PartyHub";
        r3 = "Party_3Dot";
        r4 = "Auto_Reorder";
        r1.a(r2, r3, r4);
        r1 = r5;
        r1 = (com.gaana.juke.JukePlaylist) r1;
        r2 = r1.getVoteEnable();
        r2 = r2 ^ r8;
        r3 = com.gaana.juke.JukeSessionManager.getInstance();
        r4 = new com.managers.af$32;
        r4.<init>(r1);
        r3.toggleVoting(r1, r2, r8, r4);
        goto L_0x14b5;
    L_0x0f13:
        r1 = r5 instanceof com.gaana.models.Tracks.Track;
        r2 = 2131820688; // 0x7f110090 float:1.9274098E38 double:1.053259365E-314;
        r3 = 2131821944; // 0x7f110578 float:1.9276645E38 double:1.0532599856E-314;
        if (r1 == 0) goto L_0x0fe7;
    L_0x0f1d:
        r1 = r5;
        r1 = (com.gaana.models.Tracks.Track) r1;
        r6 = r1.getArtists();
        r6 = r6.size();
        if (r6 <= 0) goto L_0x0fbc;
    L_0x0f2a:
        r2 = r0.j;
        r2 = (com.gaana.BaseActivity) r2;
        r3 = r0.j;
        r3 = (com.gaana.BaseActivity) r3;
        r3 = r3.currentScreen;
        r6 = "Artist Detail";
        r7 = new java.lang.StringBuilder;
        r7.<init>();
        r9 = r0.j;
        r9 = (com.gaana.BaseActivity) r9;
        r9 = r9.currentScreen;
        r7.append(r9);
        r9 = " - ";
        r7.append(r9);
        r9 = r0.j;
        r9 = (com.gaana.BaseActivity) r9;
        r9 = r9.currentFavpage;
        r7.append(r9);
        r9 = " - Artist Detail";
        r7.append(r9);
        r7 = r7.toString();
        r2.sendGAEvent(r3, r6, r7);
        r2 = r0.j;
        r2 = (com.gaana.GaanaActivity) r2;
        r2 = r2.getmCurrentPlayerFragment();
        if (r2 == 0) goto L_0x0f82;
    L_0x0f68:
        r9 = com.managers.an.a();
        r10 = "click";
        r11 = "ac";
        r12 = "three dot menu";
        r13 = "player";
        r14 = "";
        r15 = "Artist Detail";
        r16 = r29.getBusinessObjId();
        r17 = "";
        r9.a(r10, r11, r12, r13, r14, r15, r16, r17);
        goto L_0x0f9b;
    L_0x0f82:
        r18 = com.managers.an.a();
        r19 = "click";
        r20 = "ac";
        r21 = "three dot menu";
        r22 = "";
        r23 = "";
        r24 = "Artist Detail";
        r25 = r29.getBusinessObjId();
        r26 = "";
        r18.a(r19, r20, r21, r22, r23, r24, r25, r26);
    L_0x0f9b:
        r2 = r1.getArtists();
        r2 = r2.get(r4);
        r2 = (com.gaana.models.Tracks.Track.Artist) r2;
        r2 = r2.artist_id;
        r3 = r1.getArtists();
        r3 = r3.get(r4);
        r3 = (com.gaana.models.Tracks.Track.Artist) r3;
        r3 = r3.name;
        r1 = r1.getLanguage();
        r0.a(r2, r3, r1);
        goto L_0x14b5;
    L_0x0fbc:
        r1 = com.managers.aj.a();
        r4 = r0.j;
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = r0.j;
        r3 = r6.getString(r3);
        r5.append(r3);
        r3 = " ";
        r5.append(r3);
        r3 = r0.j;
        r2 = r3.getString(r2);
        r5.append(r2);
        r2 = r5.toString();
        r1.a(r4, r2);
        goto L_0x14b5;
    L_0x0fe7:
        r1 = r5 instanceof com.gaana.models.OfflineTrack;
        if (r1 == 0) goto L_0x1128;
    L_0x0feb:
        r1 = r29.isLocalMedia();
        if (r1 == 0) goto L_0x1053;
    L_0x0ff1:
        r1 = r0.j;
        r1 = com.gaana.localmedia.LocalMediaManager.getInstance(r1);
        r5 = (com.gaana.models.OfflineTrack) r5;
        r1 = r1.getTrackFromLocalMedia(r5);
        r5 = r1.getArtists();
        r5 = r5.size();
        if (r5 <= 0) goto L_0x1028;
    L_0x1007:
        r2 = r1.getArtists();
        r2 = r2.get(r4);
        r2 = (com.gaana.models.Tracks.Track.Artist) r2;
        r2 = r2.artist_id;
        r3 = r1.getArtists();
        r3 = r3.get(r4);
        r3 = (com.gaana.models.Tracks.Track.Artist) r3;
        r3 = r3.name;
        r1 = r1.getLanguage();
        r0.a(r2, r3, r1);
        goto L_0x14b5;
    L_0x1028:
        r1 = com.managers.aj.a();
        r4 = r0.j;
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = r0.j;
        r3 = r6.getString(r3);
        r5.append(r3);
        r3 = " ";
        r5.append(r3);
        r3 = r0.j;
        r2 = r3.getString(r2);
        r5.append(r2);
        r2 = r5.toString();
        r1.a(r4, r2);
        goto L_0x14b5;
    L_0x1053:
        r1 = com.managers.DownloadManager.c();
        r6 = r29.getBusinessObjId();
        r1 = r1.a(r6, r8);
        r1 = (com.gaana.models.Tracks.Track) r1;
        r6 = r1.getArtists();
        r6 = r6.size();
        if (r6 <= 0) goto L_0x10fd;
    L_0x106b:
        r2 = r0.j;
        r2 = (com.gaana.BaseActivity) r2;
        r3 = r0.j;
        r3 = (com.gaana.BaseActivity) r3;
        r3 = r3.currentScreen;
        r6 = "Artist Detail";
        r7 = new java.lang.StringBuilder;
        r7.<init>();
        r9 = r0.j;
        r9 = (com.gaana.BaseActivity) r9;
        r9 = r9.currentScreen;
        r7.append(r9);
        r9 = " - ";
        r7.append(r9);
        r9 = r0.j;
        r9 = (com.gaana.BaseActivity) r9;
        r9 = r9.currentFavpage;
        r7.append(r9);
        r9 = " - Artist Detail";
        r7.append(r9);
        r7 = r7.toString();
        r2.sendGAEvent(r3, r6, r7);
        r2 = r0.j;
        r2 = (com.gaana.GaanaActivity) r2;
        r2 = r2.getmCurrentPlayerFragment();
        if (r2 == 0) goto L_0x10c3;
    L_0x10a9:
        r9 = com.managers.an.a();
        r10 = "click";
        r11 = "ac";
        r12 = "three dot menu";
        r13 = "player";
        r14 = "";
        r15 = "Artist Detail";
        r16 = r29.getBusinessObjId();
        r17 = "";
        r9.a(r10, r11, r12, r13, r14, r15, r16, r17);
        goto L_0x10dc;
    L_0x10c3:
        r18 = com.managers.an.a();
        r19 = "click";
        r20 = "ac";
        r21 = "three dot menu";
        r22 = "Artist Detail";
        r23 = "";
        r24 = "";
        r25 = r29.getBusinessObjId();
        r26 = "";
        r18.a(r19, r20, r21, r22, r23, r24, r25, r26);
    L_0x10dc:
        r2 = r1.getArtists();
        r2 = r2.get(r4);
        r2 = (com.gaana.models.Tracks.Track.Artist) r2;
        r2 = r2.artist_id;
        r3 = r1.getArtists();
        r3 = r3.get(r4);
        r3 = (com.gaana.models.Tracks.Track.Artist) r3;
        r3 = r3.name;
        r1 = r1.getLanguage();
        r0.a(r2, r3, r1);
        goto L_0x14b5;
    L_0x10fd:
        r1 = com.managers.aj.a();
        r4 = r0.j;
        r5 = new java.lang.StringBuilder;
        r5.<init>();
        r6 = r0.j;
        r3 = r6.getString(r3);
        r5.append(r3);
        r3 = " ";
        r5.append(r3);
        r3 = r0.j;
        r2 = r3.getString(r2);
        r5.append(r2);
        r2 = r5.toString();
        r1.a(r4, r2);
        goto L_0x14b5;
    L_0x1128:
        r0.c(r5);
        goto L_0x14b5;
    L_0x112d:
        r1 = r5 instanceof com.gaana.models.Tracks.Track;
        if (r1 == 0) goto L_0x11ca;
    L_0x1131:
        r1 = r0.n;
        r1 = r1 instanceof com.fragments.RevampedDetailListing;
        if (r1 == 0) goto L_0x1141;
    L_0x1137:
        r1 = "Go to Album";
        r2 = r0.n;
        r2 = (com.fragments.RevampedDetailListing) r2;
        r2.a(r1, r4);
        goto L_0x1175;
    L_0x1141:
        r1 = r0.j;
        r1 = (com.gaana.BaseActivity) r1;
        r2 = r0.j;
        r2 = (com.gaana.BaseActivity) r2;
        r2 = r2.currentScreen;
        r3 = "Album Detail";
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r6 = r0.j;
        r6 = (com.gaana.BaseActivity) r6;
        r6 = r6.currentScreen;
        r4.append(r6);
        r6 = " - ";
        r4.append(r6);
        r6 = r0.j;
        r6 = (com.gaana.BaseActivity) r6;
        r6 = r6.currentFavpage;
        r4.append(r6);
        r6 = " - Album Detail";
        r4.append(r6);
        r4 = r4.toString();
        r1.sendGAEvent(r2, r3, r4);
    L_0x1175:
        r1 = r0.j;
        r1 = (com.gaana.GaanaActivity) r1;
        r1 = r1.getmCurrentPlayerFragment();
        if (r1 == 0) goto L_0x1199;
    L_0x117f:
        r9 = com.managers.an.a();
        r10 = "click";
        r11 = "ac";
        r12 = "three dot menu";
        r13 = "player";
        r14 = "";
        r15 = "Album Detail";
        r16 = r29.getBusinessObjId();
        r17 = "";
        r9.a(r10, r11, r12, r13, r14, r15, r16, r17);
        goto L_0x11b2;
    L_0x1199:
        r18 = com.managers.an.a();
        r19 = "click";
        r20 = "ac";
        r21 = "three dot menu";
        r22 = "";
        r23 = "";
        r24 = "Album Detail";
        r25 = r29.getBusinessObjId();
        r26 = "";
        r18.a(r19, r20, r21, r22, r23, r24, r25, r26);
    L_0x11b2:
        r1 = r5;
        r1 = (com.gaana.models.Tracks.Track) r1;
        r2 = r1.getAlbumId();
        r3 = r1.getAlbumTitle();
        r4 = r1.getArtwork();
        r1 = r1.getLanguage();
        r0.a(r2, r3, r4, r1);
        goto L_0x14b5;
    L_0x11ca:
        r1 = r5 instanceof com.gaana.models.OfflineTrack;
        if (r1 == 0) goto L_0x1276;
    L_0x11ce:
        r1 = r29.isLocalMedia();
        if (r1 == 0) goto L_0x11f6;
    L_0x11d4:
        r1 = r0.j;
        r1 = com.gaana.localmedia.LocalMediaManager.getInstance(r1);
        r2 = r5;
        r2 = (com.gaana.models.OfflineTrack) r2;
        r1 = r1.getTrackFromLocalMedia(r2);
        r2 = r1.getAlbumId();
        r3 = r1.getAlbumTitle();
        r4 = r1.getArtwork();
        r1 = r1.getLanguage();
        r0.a(r2, r3, r4, r1);
        goto L_0x14b5;
    L_0x11f6:
        r1 = r0.n;
        r1 = r1 instanceof com.fragments.RevampedDetailListing;
        if (r1 == 0) goto L_0x1206;
    L_0x11fc:
        r1 = "Go to Album";
        r2 = r0.n;
        r2 = (com.fragments.RevampedDetailListing) r2;
        r2.a(r1, r4);
        goto L_0x123a;
    L_0x1206:
        r1 = r0.j;
        r1 = (com.gaana.BaseActivity) r1;
        r2 = r0.j;
        r2 = (com.gaana.BaseActivity) r2;
        r2 = r2.currentScreen;
        r3 = "Album Detail";
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r6 = r0.j;
        r6 = (com.gaana.BaseActivity) r6;
        r6 = r6.currentScreen;
        r4.append(r6);
        r6 = " - ";
        r4.append(r6);
        r6 = r0.j;
        r6 = (com.gaana.BaseActivity) r6;
        r6 = r6.currentFavpage;
        r4.append(r6);
        r6 = " - Album Detail";
        r4.append(r6);
        r4 = r4.toString();
        r1.sendGAEvent(r2, r3, r4);
    L_0x123a:
        r9 = com.managers.an.a();
        r10 = "click";
        r11 = "ac";
        r12 = "three dot menu";
        r13 = "player";
        r14 = "";
        r15 = "Album Detail";
        r16 = r29.getBusinessObjId();
        r17 = "";
        r9.a(r10, r11, r12, r13, r14, r15, r16, r17);
        r1 = com.managers.DownloadManager.c();
        r2 = r29.getBusinessObjId();
        r1 = r1.a(r2, r8);
        r1 = (com.gaana.models.Tracks.Track) r1;
        r2 = r1.getAlbumId();
        r3 = r1.getAlbumTitle();
        r4 = r1.getArtwork();
        r1 = r1.getLanguage();
        r0.a(r2, r3, r4, r1);
        goto L_0x14b5;
    L_0x1276:
        r0.c(r5);
        goto L_0x14b5;
    L_0x127b:
        r1 = r5 instanceof com.gaana.models.Albums.Album;
        if (r1 == 0) goto L_0x14b5;
    L_0x127f:
        r1 = r0.j;
        r1 = (com.gaana.BaseActivity) r1;
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = r0.j;
        r3 = (com.gaana.BaseActivity) r3;
        r3 = r3.currentScreen;
        r2.append(r3);
        r3 = " - Detail";
        r2.append(r3);
        r2 = r2.toString();
        r3 = "Album Info";
        r4 = new java.lang.StringBuilder;
        r4.<init>();
        r6 = r0.j;
        r6 = (com.gaana.BaseActivity) r6;
        r6 = r6.currentScreen;
        r4.append(r6);
        r6 = " - Detail - Album Info";
        r4.append(r6);
        r4 = r4.toString();
        r1.sendGAEvent(r2, r3, r4);
        r1 = r0.j;
        r1 = (com.gaana.GaanaActivity) r1;
        r1 = r1.getmCurrentPlayerFragment();
        if (r1 == 0) goto L_0x12da;
    L_0x12c0:
        r9 = com.managers.an.a();
        r10 = "click";
        r11 = "ac";
        r12 = "three dot menu";
        r13 = "player";
        r14 = "";
        r15 = "Album Info";
        r16 = r29.getBusinessObjId();
        r17 = "";
        r9.a(r10, r11, r12, r13, r14, r15, r16, r17);
        goto L_0x12f3;
    L_0x12da:
        r18 = com.managers.an.a();
        r19 = "click";
        r20 = "ac";
        r21 = "three dot menu";
        r22 = "Album Info";
        r23 = "";
        r24 = "";
        r25 = r29.getBusinessObjId();
        r26 = "";
        r18.a(r19, r20, r21, r22, r23, r24, r25, r26);
    L_0x12f3:
        r1 = new com.fragments.MoreInfoFragment;
        r1.<init>();
        r0.n = r1;
        r1 = new android.os.Bundle;
        r1.<init>();
        r2 = r5;
        r2 = (com.gaana.models.Albums.Album) r2;
        r3 = r2.getBusinessObjId();
        r4 = "ALBUMID";
        r1.putString(r4, r3);
        r3 = "ALBUM_NAME";
        r2 = r2.getRawName();
        r1.putString(r3, r2);
        r2 = "BUSINESS_OBJECT";
        r1.putSerializable(r2, r5);
        r2 = r0.n;
        r2.setArguments(r1);
        r1 = r0.j;
        r1 = (com.gaana.GaanaActivity) r1;
        r2 = r0.n;
        r1.displayFragment(r2);
        goto L_0x14b5;
    L_0x1329:
        r1 = r0.j;
        r1 = (com.gaana.GaanaActivity) r1;
        r1 = r1.getmCurrentPlayerFragment();
        if (r1 == 0) goto L_0x136f;
    L_0x1333:
        r1 = r29.isLocalMedia();
        if (r1 != 0) goto L_0x1354;
    L_0x1339:
        r9 = com.managers.an.a();
        r10 = "click";
        r11 = "ac";
        r12 = "three dot menu";
        r13 = "player";
        r14 = "";
        r15 = "Add to Playlist";
        r16 = r29.getBusinessObjId();
        r17 = "";
        r9.a(r10, r11, r12, r13, r14, r15, r16, r17);
        goto L_0x1420;
    L_0x1354:
        r18 = com.managers.an.a();
        r19 = "click";
        r20 = "ac";
        r21 = "three dot menu";
        r22 = "player";
        r23 = "";
        r24 = "Local - Add to Playlist";
        r25 = r29.getBusinessObjId();
        r26 = "";
        r18.a(r19, r20, r21, r22, r23, r24, r25, r26);
        goto L_0x1420;
    L_0x136f:
        r1 = r29.isLocalMedia();
        if (r1 != 0) goto L_0x13cb;
    L_0x1375:
        r1 = r0.n;
        r1 = r1 instanceof com.fragments.RevampedDetailListing;
        if (r1 == 0) goto L_0x13b3;
    L_0x137b:
        r1 = r0.n;
        r1 = (com.fragments.RevampedDetailListing) r1;
        r1 = r1.c;
        r2 = "ArtistDetailScreen";
        r1 = r1.startsWith(r2);
        if (r1 == 0) goto L_0x13b3;
    L_0x1389:
        r9 = com.managers.an.a();
        r10 = "click";
        r11 = "ac";
        r1 = r0.n;
        r1 = (com.fragments.RevampedDetailListing) r1;
        r1 = r1.s();
        r12 = r1.getBusinessObjId();
        r1 = r0.n;
        r1 = (com.fragments.RevampedDetailListing) r1;
        r13 = r1.d();
        r14 = r29.getBusinessObjId();
        r15 = "Add to Playlist";
        r16 = "";
        r17 = "";
        r9.a(r10, r11, r12, r13, r14, r15, r16, r17);
        goto L_0x1420;
    L_0x13b3:
        r18 = com.managers.an.a();
        r19 = "click";
        r20 = "ac";
        r21 = "three dot menu";
        r22 = "";
        r23 = "";
        r24 = "Add to Playlist";
        r25 = "";
        r26 = "";
        r18.a(r19, r20, r21, r22, r23, r24, r25, r26);
        goto L_0x1420;
    L_0x13cb:
        r1 = r0.n;
        r1 = r1 instanceof com.fragments.RevampedDetailListing;
        if (r1 == 0) goto L_0x1409;
    L_0x13d1:
        r1 = r0.n;
        r1 = (com.fragments.RevampedDetailListing) r1;
        r1 = r1.c;
        r2 = "ArtistDetailScreen";
        r1 = r1.startsWith(r2);
        if (r1 == 0) goto L_0x1409;
    L_0x13df:
        r9 = com.managers.an.a();
        r10 = "click";
        r11 = "ac";
        r1 = r0.n;
        r1 = (com.fragments.RevampedDetailListing) r1;
        r1 = r1.s();
        r12 = r1.getBusinessObjId();
        r1 = r0.n;
        r1 = (com.fragments.RevampedDetailListing) r1;
        r13 = r1.d();
        r14 = r29.getBusinessObjId();
        r15 = "Local - Add to Playlist";
        r16 = "";
        r17 = "";
        r9.a(r10, r11, r12, r13, r14, r15, r16, r17);
        goto L_0x1420;
    L_0x1409:
        r18 = com.managers.an.a();
        r19 = "click";
        r20 = "ac";
        r21 = "three dot menu";
        r22 = "";
        r23 = "";
        r24 = "Local - Add to Playlist";
        r25 = "";
        r26 = "";
        r18.a(r19, r20, r21, r22, r23, r24, r25, r26);
    L_0x1420:
        r1 = com.gaana.analytics.MoEngage.getInstance();
        r1.reportAddToPlaylist(r5);
        r1 = r27.g();
        if (r1 == 0) goto L_0x1488;
    L_0x142d:
        r2 = r1.isLocalMedia();
        if (r2 != 0) goto L_0x1451;
    L_0x1433:
        r2 = r0.m;
        r2 = r2.getCurrentUser();
        r2 = r2.getLoginStatus();
        if (r2 == 0) goto L_0x1440;
    L_0x143f:
        goto L_0x1451;
    L_0x1440:
        r2 = r0.j;
        r2 = r2.getResources();
        r5 = 2131821783; // 0x7f1104d7 float:1.9276319E38 double:1.053259906E-314;
        r2 = r2.getString(r5);
        r0.a(r3, r1, r2);
        return r4;
    L_0x1451:
        r2 = r1.getArrListBusinessObj();
        if (r2 == 0) goto L_0x1473;
    L_0x1457:
        r2 = r1.getArrListBusinessObj();
        r2 = r2.size();
        if (r2 <= 0) goto L_0x1473;
    L_0x1461:
        r2 = com.managers.ap.a();
        r3 = r0.j;
        r5 = r1.getArrListBusinessObj();
        r1 = r1.isLocalMedia();
        r2.a(r3, r5, r1);
        return r4;
    L_0x1473:
        r2 = r1 instanceof com.gaana.models.Tracks.Track;
        if (r2 == 0) goto L_0x1488;
    L_0x1477:
        r2 = com.managers.ap.a();
        r3 = r0.j;
        r5 = r1;
        r5 = (com.gaana.models.Tracks.Track) r5;
        r1 = r1.isLocalMedia();
        r2.a(r3, r5, r1);
        return r4;
    L_0x1488:
        r1 = r0.a;
        r0.a(r5, r1);
        goto L_0x14b5;
    L_0x148e:
        r1 = com.managers.u.a();
        r2 = "Playlist";
        r3 = "Add more songs";
        r1.b(r2, r3);
        r9 = com.managers.an.a();
        r10 = "click";
        r11 = "ac";
        r12 = "three dot menu";
        r13 = "player";
        r14 = "";
        r15 = "Add more songs";
        r16 = r29.getBusinessObjId();
        r17 = "";
        r9.a(r10, r11, r12, r13, r14, r15, r16, r17);
        r0.g(r5);
    L_0x14b5:
        return r8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.managers.af.a(int, com.gaana.models.BusinessObject):boolean");
    }

    private void d(String str) {
        if (Util.j(this.j)) {
            try {
                i.a().a(Util.e(this.j, str), new r() {
                    public void onErrorResponse(VolleyError volleyError) {
                    }

                    public void onSuccessfulResponse(final Bitmap bitmap) {
                        Palette.from(bitmap).maximumColorCount(16).generate(new PaletteAsyncListener() {
                            public void onGenerated(@NonNull Palette palette) {
                                String string = af.this.j.getResources().getString(R.string.story_top_color);
                                if (palette.getLightVibrantSwatch() != null) {
                                    string = String.format("#%06X", new Object[]{Integer.valueOf(palette.getLightVibrantSwatch().getRgb() & ViewCompat.MEASURED_SIZE_MASK)});
                                } else if (palette.getLightMutedSwatch() != null) {
                                    string = String.format("#%06X", new Object[]{Integer.valueOf(palette.getLightMutedSwatch().getRgb() & ViewCompat.MEASURED_SIZE_MASK)});
                                }
                                String str = "";
                                if (af.this.l instanceof Item) {
                                    Item item = (Item) af.this.l;
                                    if (item.getEntityType().equalsIgnoreCase(c.c)) {
                                        str = ((Track) Util.g(item)).getArtistNames();
                                    }
                                } else if (af.this.l instanceof Track) {
                                    str = ((Track) af.this.l).getArtistNames();
                                } else if (af.this.l instanceof OfflineTrack) {
                                    str = ((OfflineTrack) af.this.l).getArtistName();
                                }
                                Intent a = j.a(af.this.j, af.this.a(bitmap, af.this.l.getName(), str, bitmap.getWidth(), Color.parseColor(string)), string, false);
                                if (a != null) {
                                    ((GaanaActivity) af.this.j).startActivityForResult(a, 111);
                                } else {
                                    aj.a().a(af.this.j, af.this.j.getResources().getString(R.string.operation_not_supported));
                                }
                            }
                        });
                    }
                });
            } catch (OutOfMemoryError unused) {
            }
        } else {
            ap.a().f(this.j);
        }
    }

    private void a(final boolean z) {
        if (this.l != null) {
            if (this.l.isLocalMedia()) {
                d();
            } else {
                if (this.l instanceof Item) {
                    if (((Item) this.l).getEntityType().equals(c.a)) {
                        this.l = Util.b((Item) this.l);
                    } else if (((Item) this.l).getEntityType().equals(c.d)) {
                        this.l = Util.a((Item) this.l);
                    } else if (((Item) this.l).getEntityType().equals(c.b)) {
                        this.l = Util.c((Item) this.l);
                    }
                }
                BaseActivity baseActivity;
                StringBuilder stringBuilder;
                BusinessObject businessObject;
                if (this.l.getBusinessObjType() == BusinessObjectType.Artists) {
                    if (!(this.n instanceof DynamicHomeFragment)) {
                        baseActivity = (BaseActivity) this.j;
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(((BaseActivity) this.j).currentScreen);
                        stringBuilder.append(" Detail");
                        StringBuilder stringBuilder2 = new StringBuilder();
                        stringBuilder2.append(((BaseActivity) this.j).currentScreen);
                        stringBuilder2.append(" Detail - ");
                        stringBuilder2.append(((BaseActivity) this.j).currentScreen);
                        stringBuilder2.append(" - Shuffle Play");
                        baseActivity.sendGAEvent(stringBuilder.toString(), "Play", stringBuilder2.toString());
                    }
                    if (this.m.isAppInOfflineMode()) {
                        ((BaseActivity) this.j).displayFeatureNotAvailableOfflineDialog(this.j.getResources().getString(R.string.error_msg_feature_not_available_offline_prefix));
                    } else if (Util.j(this.j)) {
                        URLManager c = ((ListingButton) Constants.a("", this.l.isLocalMedia()).c().get(0)).c();
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(c.k());
                        stringBuilder.append(this.l.getBusinessObjId());
                        c.a(stringBuilder.toString());
                        ((BaseActivity) this.j).showProgressDialog(Boolean.valueOf(true), this.j.getString(R.string.loading));
                        i.a().a(new s() {
                            public void onErrorResponse(BusinessObject businessObject) {
                            }

                            public void onRetreivalComplete(BusinessObject businessObject) {
                                ((BaseActivity) af.this.j).hideProgressDialog();
                                if (businessObject == null) {
                                    return;
                                }
                                if (z) {
                                    PlayerManager.a(af.this.j).a(af.this.l.getBusinessObjId(), SOURCE_TYPE.ARTIST.ordinal(), af.this.l.getEnglishName(), af.this.l, businessObject.getArrListBusinessObj(), af.this.j);
                                } else {
                                    PlayerManager.a(af.this.j).a(af.this.l.getBusinessObjId(), SOURCE_TYPE.ARTIST.ordinal(), af.this.l.getEnglishName(), af.this.l, businessObject.getArrListBusinessObj(), af.this.j, true);
                                }
                            }
                        }, c);
                    } else {
                        ap.a().f(this.j);
                    }
                } else if (this.l instanceof Radio) {
                    businessObject = (Radio) this.l;
                    StringBuilder stringBuilder3;
                    if (businessObject.getType().equals(com.constants.c.d.c)) {
                        if (((BaseActivity) this.j).currentScreen.startsWith("Fav")) {
                            baseActivity = (BaseActivity) this.j;
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(((BaseActivity) this.j).currentScreen);
                            stringBuilder.append(" Detail");
                            stringBuilder3 = new StringBuilder();
                            stringBuilder3.append(((BaseActivity) this.j).currentScreen);
                            stringBuilder3.append(" RadioMirchi - ");
                            stringBuilder3.append(businessObject.getName());
                            stringBuilder3.append(" - ");
                            stringBuilder3.append(((BaseActivity) this.j).currentFavpage);
                            stringBuilder3.append(" - ShufflePlay");
                            baseActivity.sendGAEvent(stringBuilder.toString(), "Play", stringBuilder3.toString());
                        } else {
                            baseActivity = (BaseActivity) this.j;
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(((BaseActivity) this.j).currentScreen);
                            stringBuilder.append(" Detail");
                            stringBuilder3 = new StringBuilder();
                            stringBuilder3.append(((BaseActivity) this.j).currentScreen);
                            stringBuilder3.append(" RadioMirchi - ");
                            stringBuilder3.append(businessObject.getName());
                            stringBuilder3.append(" - ShufflePlay");
                            baseActivity.sendGAEvent(stringBuilder.toString(), "Play", stringBuilder3.toString());
                        }
                        ad.a(this.j).a(businessObject);
                    } else {
                        if (((BaseActivity) this.j).currentScreen.startsWith("Fav")) {
                            baseActivity = (BaseActivity) this.j;
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(((BaseActivity) this.j).currentScreen);
                            stringBuilder.append(" Detail");
                            stringBuilder3 = new StringBuilder();
                            stringBuilder3.append(((BaseActivity) this.j).currentScreen);
                            stringBuilder3.append(" GaanaRadio - ");
                            stringBuilder3.append(businessObject.getName());
                            stringBuilder3.append(" - ");
                            stringBuilder3.append(((BaseActivity) this.j).currentFavpage);
                            stringBuilder3.append(" - ShufflePlay");
                            baseActivity.sendGAEvent(stringBuilder.toString(), "Play", stringBuilder3.toString());
                        } else {
                            baseActivity = (BaseActivity) this.j;
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(((BaseActivity) this.j).currentScreen);
                            stringBuilder.append(" Detail");
                            stringBuilder3 = new StringBuilder();
                            stringBuilder3.append(((BaseActivity) this.j).currentScreen);
                            stringBuilder3.append(" GaanaRadio - ");
                            stringBuilder3.append(businessObject.getName());
                            stringBuilder3.append(" - ShufflePlay");
                            baseActivity.sendGAEvent(stringBuilder.toString(), "Play", stringBuilder3.toString());
                        }
                        ad.a(this.j).a("https://api.gaana.com/radio.php?type=radio&subtype=radiodetail&radio_id=<radio_id>&radio_type=<radio_type>&limit=0,50".replace("<radio_id>", businessObject.getBusinessObjId()).replace("<radio_type>", businessObject.getType()), SOURCE_TYPE.GAANA_RADIO.ordinal(), businessObject);
                    }
                } else {
                    BaseActivity baseActivity2;
                    StringBuilder stringBuilder4;
                    StringBuilder stringBuilder5;
                    if (((BaseActivity) this.j).currentScreen.startsWith("Fav")) {
                        baseActivity2 = (BaseActivity) this.j;
                        stringBuilder4 = new StringBuilder();
                        stringBuilder4.append(((BaseActivity) this.j).currentScreen);
                        stringBuilder4.append(" Detail");
                        stringBuilder5 = new StringBuilder();
                        stringBuilder5.append(((BaseActivity) this.j).currentScreen);
                        stringBuilder5.append(" Detail - ");
                        stringBuilder5.append(((BaseActivity) this.j).currentItem);
                        stringBuilder5.append(" - ");
                        stringBuilder5.append(((BaseActivity) this.j).currentFavpage);
                        stringBuilder5.append(" - ShufflePlay");
                        baseActivity2.sendGAEvent(stringBuilder4.toString(), "Play", stringBuilder5.toString());
                    } else if (!(this.n instanceof DynamicHomeFragment)) {
                        baseActivity2 = (BaseActivity) this.j;
                        stringBuilder4 = new StringBuilder();
                        stringBuilder4.append(((BaseActivity) this.j).currentScreen);
                        stringBuilder4.append(" Detail");
                        stringBuilder5 = new StringBuilder();
                        stringBuilder5.append(((BaseActivity) this.j).currentScreen);
                        stringBuilder5.append(" Detail - ");
                        stringBuilder5.append(((BaseActivity) this.j).currentItem);
                        stringBuilder5.append(" - ShufflePlay");
                        baseActivity2.sendGAEvent(stringBuilder4.toString(), "Play", stringBuilder5.toString());
                    }
                    if (this.l instanceof Track) {
                        a((Track) this.l);
                    } else if (this.l instanceof OfflineTrack) {
                        if (this.l.isLocalMedia()) {
                            a(LocalMediaManager.getInstance(this.j).getTrackFromLocalMedia((OfflineTrack) this.l));
                        } else {
                            a((Track) DownloadManager.c().a(this.l.getBusinessObjId(), true));
                        }
                    } else {
                        ArrayList arrListBusinessObj = this.l.getArrListBusinessObj();
                        if (arrListBusinessObj != null) {
                            ArrayList arrayList;
                            ArrayList arrayList2 = new ArrayList();
                            if (this.m.isAppInOfflineMode() || !Util.j(this.j)) {
                                Iterator it = arrListBusinessObj.iterator();
                                while (it.hasNext()) {
                                    Track track = (Track) it.next();
                                    if (DownloadManager.c().l(Integer.parseInt(track.getBusinessObjId())).booleanValue() || track.isLocalMedia()) {
                                        arrayList2.add(track);
                                    }
                                }
                                if (arrayList2.size() == 0) {
                                    aj.a().a(this.j, this.j.getString(R.string.player_nooffline_songs));
                                    return;
                                }
                                arrayList = arrayList2;
                            } else {
                                arrayList = arrListBusinessObj;
                            }
                            if (arrListBusinessObj.size() == 0) {
                                aj.a().a(this.j, this.j.getString(R.string.player_nosongs_toplay));
                            } else {
                                PlayerManager.a(this.j).a(this.l.getBusinessObjId(), d.a().a(this.l), this.l.getEnglishName(), this.l, arrayList, this.j, true);
                            }
                        } else if (this.l.isLocalMedia() || !DownloadManager.c().b(this.l).booleanValue()) {
                            a(this.l, this.c);
                        } else {
                            businessObject = DownloadManager.c().a(this.l.getBusinessObjId(), false);
                            if (businessObject == null || businessObject.getArrListBusinessObj() == null) {
                                a(this.l, this.c);
                            } else {
                                PlayerManager.a(this.j).a(this.l.getBusinessObjId(), d.a().a(this.l), this.l.getEnglishName(), this.l, businessObject.getArrListBusinessObj(), this.j, true);
                            }
                        }
                    }
                }
            }
        }
    }

    private void d() {
        if (Constants.cY) {
            aj.a().a(this.j, this.j.getResources().getString(R.string.err_local_songs_party));
            return;
        }
        if (this.l.getBusinessObjType() == BusinessObjectType.Artists) {
            ((BaseActivity) this.j).showProgressDialog(Boolean.valueOf(true), this.j.getString(R.string.loading));
            com.i.d.a(new Runnable() {
                public void run() {
                    final ArrayList songsByArtist = LocalMediaManager.getInstance(af.this.j).getSongsByArtist(Long.parseLong(af.this.l.getBusinessObjId()));
                    ((Activity) af.this.j).runOnUiThread(new Runnable() {
                        public void run() {
                            if (songsByArtist == null || songsByArtist.size() <= 0) {
                                aj.a().a(af.this.j, af.this.j.getString(R.string.player_nosongs_toplay));
                            } else {
                                PlayerManager.a(af.this.j).b(af.this.l.getBusinessObjId(), SOURCE_TYPE.ARTIST.ordinal(), af.this.l.getEnglishName(), af.this.l, songsByArtist, af.this.j);
                            }
                            ((BaseActivity) af.this.j).hideProgressDialog();
                        }
                    });
                }
            });
        } else {
            ArrayList arrListBusinessObj = this.l.getArrListBusinessObj();
            if (arrListBusinessObj == null || arrListBusinessObj.size() == 0) {
                if (this.l instanceof Album) {
                    arrListBusinessObj = LocalMediaManager.getInstance(this.j).getSongsByAlbum(this.l.getBusinessObjId());
                } else if (this.l instanceof Playlist) {
                    arrListBusinessObj = LocalMediaManager.getInstance(this.j).getSongsByPlaylist(this.l.getBusinessObjId());
                }
            }
            ArrayList arrayList = arrListBusinessObj;
            if (arrayList != null) {
                if (arrayList.size() == 0) {
                    aj.a().a(this.j, this.j.getString(R.string.player_nosongs_toplay));
                } else {
                    PlayerManager.a(this.j).b(this.l.getBusinessObjId(), ((BaseActivity) this.j).getSourceType(this.l), this.l.getEnglishName(), this.l, arrayList, this.j);
                }
            }
        }
    }

    private void e() {
        String str;
        if (!ap.a().a(this.l, null)) {
            str = "";
            if (ap.a().l()) {
                str = this.l instanceof Track ? "tr" : "pl";
            }
            Util.b(this.j, str, null, new as() {
                public void onTrialSuccess() {
                    af.this.b(true);
                    af.this.n.refreshDataandAds();
                    af.this.n.showSnackbartoOpenMyMusic();
                    ((GaanaActivity) af.this.j).updateSideBar();
                }
            });
        } else if (!ap.a().f() || !ap.a().o()) {
            b(true);
        } else if (ap.a().h()) {
            str = "";
            if (ap.a().f()) {
                str = this.l instanceof Track ? "tr" : "pl";
            }
            if (!(this.l instanceof Track)) {
                int i = 0;
                if (this.l.getArrListBusinessObj() != null) {
                    Iterator it = this.l.getArrListBusinessObj().iterator();
                    while (it.hasNext()) {
                        if (DownloadManager.c().e(Integer.valueOf(((Track) it.next()).getBusinessObjId()).intValue()) == null) {
                            i++;
                        }
                    }
                }
                if (this.l.getArrListBusinessObj() == null || ((i + DownloadManager.c().K()) + DownloadManager.c().B()) + DownloadManager.c().r() <= Integer.valueOf(this.m.getCurrentUser().getUserSubscriptionData().getProductProperties().getSongLimit()).intValue()) {
                    b(true);
                } else {
                    Util.b(this.j, str, "", "", new as() {
                        public void onTrialSuccess() {
                            af.this.b(true);
                            af.this.n.refreshDataandAds();
                            af.this.n.showSnackbartoOpenMyMusic();
                            ((GaanaActivity) af.this.j).updateSideBar();
                        }
                    });
                }
            } else if ((DownloadManager.c().K() + DownloadManager.c().B()) + DownloadManager.c().r() >= Integer.valueOf(this.m.getCurrentUser().getUserSubscriptionData().getProductProperties().getSongLimit()).intValue()) {
                Util.b(this.j, str, "", "", new as() {
                    public void onTrialSuccess() {
                        af.this.b(true);
                        af.this.n.refreshDataandAds();
                        af.this.n.showSnackbartoOpenMyMusic();
                        ((GaanaActivity) af.this.j).updateSideBar();
                    }
                });
            } else {
                b(true);
            }
        } else {
            Util.x(this.j);
        }
    }

    private void b(boolean z) {
        Util.i(this.j, "Download");
        final BaseGaanaFragment currentFragment = ((GaanaActivity) this.j).getCurrentFragment();
        if ((currentFragment instanceof AlbumDetailsMaterialListing) && !((GaanaActivity) this.j).isSlidingPanelExpanded()) {
            ((AlbumDetailsMaterialListing) currentFragment).b(z);
        } else if ((currentFragment instanceof RevampedDetailListing) && !((GaanaActivity) this.j).isSlidingPanelExpanded()) {
            ((RevampedDetailListing) currentFragment).a(z, null);
        } else if ((currentFragment instanceof GaanaSpecialDetailsMaterialListing) && !((GaanaActivity) this.j).isSlidingPanelExpanded()) {
            ((GaanaSpecialDetailsMaterialListing) currentFragment).a(z);
        } else if ((currentFragment instanceof MoreInfoFragment) && !((GaanaActivity) this.j).isSlidingPanelExpanded()) {
            ((MoreInfoFragment) currentFragment).a(z);
        } else if (Util.k(GaanaApplication.getContext()) == 0) {
            ((BaseActivity) this.j).hideProgressDialog();
            z = com.services.d.a().b("PREFERENCE_KEY_NIGHT_DATA_CONNECTION", false, true);
            if (!com.services.d.a().b("PREFERENCE_KEY_SYNC_OVER_DATA_CONNECTION", false, true)) {
                ((BaseActivity) this.j).mDialog = new f(this.j);
                ((BaseActivity) this.j).mDialog.a(this.j.getString(R.string.dlg_msg_gaanaplus), this.j.getString(R.string.dlg_msg_sync_data_disablde), Boolean.valueOf(true), this.j.getString(R.string.settings_text), this.j.getString(R.string.cancel), new b() {
                    public void onCancelListner() {
                    }

                    public void onOkListner(String str) {
                        BaseGaanaFragment currentFragment = ((GaanaActivity) af.this.j).getCurrentFragment();
                        if (!(currentFragment instanceof SettingsDetailFragment) || ((SettingsDetailFragment) currentFragment).a() != 1) {
                            currentFragment = new SettingsDetailFragment();
                            Bundle bundle = new Bundle();
                            bundle.putInt("KEY_SETTINGS", 1);
                            currentFragment.setArguments(bundle);
                            ((GaanaActivity) af.this.j).displayFragment(currentFragment);
                        }
                    }
                });
                return;
            } else if (z) {
                if (!Constants.V) {
                    aj.a().a(this.j, this.j.getString(R.string.schedule_songs_queue_msg));
                    Constants.V = true;
                }
            } else if (!Constants.W) {
                Constants.W = true;
                aj.a().a(this.j, this.j.getString(R.string.schedule_cta_text), this.j.getString(R.string.schedule_download_msg), new OnClickListener() {
                    public void onClick(View view) {
                        if ((currentFragment instanceof SettingsDetailFragment) && ((SettingsDetailFragment) currentFragment).a() == 1) {
                            PopupWindowView.getInstance(af.this.j, currentFragment).dismiss(true);
                            return;
                        }
                        Bundle bundle = new Bundle();
                        bundle.putInt("KEY_SETTINGS", 1);
                        BaseGaanaFragment settingsDetailFragment = new SettingsDetailFragment();
                        settingsDetailFragment.setArguments(bundle);
                        PopupWindowView.getInstance(af.this.j, currentFragment).dismiss(true);
                        ((GaanaActivity) af.this.j).displayFragment(settingsDetailFragment);
                    }
                });
            }
        }
        if (!DownloadManager.c().v()) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public void run() {
                    Util.w(af.this.j);
                }
            });
        } else if (Constants.t() && !Constants.U) {
            Constants.U = true;
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                public void run() {
                    Constants.U = false;
                    PopupWindowView.getInstance(af.this.j, currentFragment).dismiss(true);
                    new DownloadSyncPopupItemView(af.this.j).showDownloadSyncWelcomeScreenDialog();
                }
            }, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
        }
        if (this.l instanceof Track) {
            ((BaseActivity) this.j).hideProgressDialog();
            DownloadStatus e = DownloadManager.c().e(Integer.parseInt(this.l.getBusinessObjId()));
            if (e == DownloadStatus.PAUSED || e == DownloadStatus.TRIED_BUT_FAILED) {
                DownloadManager.c().b((Track) this.l);
            } else if (e == null) {
                MoEngage.getInstance().reportDownload(this.l);
                DownloadManager.c().a((Track) this.l, this.j);
            }
            if (e != DownloadStatus.DOWNLOADED) {
                aj.a().a(this.j, this.j.getResources().getString(R.string.player_song_download_toast));
            }
        }
        if (this.l instanceof JukePlaylist) {
            f();
        }
    }

    private void f() {
        final JukePlaylist jukePlaylist = (JukePlaylist) this.l;
        com.i.d.a(new Runnable() {
            public void run() {
                Gson create = new GsonBuilder().excludeFieldsWithModifiers(8, 4).create();
                if (jukePlaylist.getArrListBusinessObj() != null) {
                    boolean z = true;
                    do {
                        ArrayList b = DownloadManager.c().b(jukePlaylist.getArrListBusinessObj());
                        int i = 0;
                        if (b.size() < 30) {
                            z = false;
                        }
                        String a = DownloadManager.c().a(b, BusinessObjectType.Tracks);
                        if (!TextUtils.isEmpty(a)) {
                            com.services.i b2 = new com.services.j().b(a);
                            if (b2.b().booleanValue()) {
                                a = b2.a();
                                if (TextUtils.isEmpty(a)) {
                                    aj.a().a(af.this.j, af.this.j.getResources().getString(R.string.some_error_occurred));
                                    continue;
                                } else {
                                    Tracks tracks = (Tracks) create.fromJson(a, Tracks.class);
                                    if (!(tracks == null || tracks.getArrListBusinessObj() == null)) {
                                        tracks.setBusinessObjType(BusinessObjectType.Tracks);
                                        b = tracks.getArrListBusinessObj();
                                        while (i < b.size()) {
                                            ((BusinessObject) b.get(i)).setBusinessObjType(BusinessObjectType.Tracks);
                                            i++;
                                        }
                                        DownloadManager.c().b(b, -100, true);
                                        continue;
                                    }
                                }
                            } else {
                                aj.a().a(af.this.j, af.this.j.getResources().getString(R.string.some_error_occurred));
                                continue;
                            }
                        } else {
                            return;
                        }
                    } while (z);
                }
            }
        });
    }

    public void a(String str, String str2, BusinessObject businessObject) {
        String str3 = str;
        String str4 = str2;
        BusinessObject businessObject2 = businessObject;
        this.l = businessObject2;
        this.m = (GaanaApplication) this.j.getApplicationContext();
        aj a;
        Context context;
        StringBuilder stringBuilder;
        Track track;
        StringBuilder stringBuilder2;
        if (businessObject2 instanceof Album) {
            Album album = (Album) businessObject2;
            if (album.getPrimaryartist().size() > 0) {
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append(((BaseActivity) this.j).currentScreen);
                stringBuilder3.append(" - ");
                stringBuilder3.append(((BaseActivity) this.j).currentFavpage);
                stringBuilder3.append(" - Artist Detail");
                ((BaseActivity) this.j).sendGAEvent(((BaseActivity) this.j).currentScreen, "Artist Detail", stringBuilder3.toString());
                a(str3, str4, album.getLanguage());
                return;
            }
            a = aj.a();
            context = this.j;
            stringBuilder = new StringBuilder();
            stringBuilder.append(this.j.getString(R.string.no));
            stringBuilder.append(" ");
            stringBuilder.append(this.j.getString(R.string.artist));
            a.a(context, stringBuilder.toString());
        } else if (businessObject2 instanceof Track) {
            track = (Track) businessObject2;
            if (track.getArtists().size() > 0) {
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(((BaseActivity) this.j).currentScreen);
                stringBuilder2.append(" - ");
                stringBuilder2.append(((BaseActivity) this.j).currentFavpage);
                stringBuilder2.append(" - Artist Detail");
                ((BaseActivity) this.j).sendGAEvent(((BaseActivity) this.j).currentScreen, "Artist Detail", stringBuilder2.toString());
                a(str3, str4, track.getLanguage());
                if (((GaanaActivity) this.j).getmCurrentPlayerFragment() != null) {
                    an.a().a("click", "ac", "three dot menu", "player", "", "Artist Detail", businessObject.getBusinessObjId(), "");
                    return;
                }
                an.a().a("click", "ac", "three dot menu", "", "", "Artist Detail", businessObject.getBusinessObjId(), "");
                return;
            }
            a = aj.a();
            context = this.j;
            stringBuilder = new StringBuilder();
            stringBuilder.append(this.j.getString(R.string.no));
            stringBuilder.append(" ");
            stringBuilder.append(this.j.getString(R.string.artist));
            a.a(context, stringBuilder.toString());
        } else if (!(businessObject2 instanceof OfflineTrack)) {
            c(businessObject2);
        } else if (businessObject.isLocalMedia()) {
            track = LocalMediaManager.getInstance(this.j).getTrackFromLocalMedia((OfflineTrack) businessObject2);
            if (track.getArtists().size() > 0) {
                a(str3, str4, track.getLanguage());
                if (((GaanaActivity) this.j).getmCurrentPlayerFragment() != null) {
                    an.a().a("click", "ac", "three dot menu", "player", "", "Artist Detail", businessObject.getBusinessObjId(), "");
                    return;
                }
                an.a().a("click", "ac", "three dot menu", "", "", "Artist Detail", businessObject.getBusinessObjId(), "");
                return;
            }
            a = aj.a();
            context = this.j;
            stringBuilder = new StringBuilder();
            stringBuilder.append(this.j.getString(R.string.no));
            stringBuilder.append(" ");
            stringBuilder.append(this.j.getString(R.string.artist));
            a.a(context, stringBuilder.toString());
        } else {
            track = (Track) DownloadManager.c().a(businessObject.getBusinessObjId(), true);
            if (track.getArtists().size() > 0) {
                stringBuilder2 = new StringBuilder();
                stringBuilder2.append(((BaseActivity) this.j).currentScreen);
                stringBuilder2.append(" - ");
                stringBuilder2.append(((BaseActivity) this.j).currentFavpage);
                stringBuilder2.append(" - Artist Detail");
                ((BaseActivity) this.j).sendGAEvent(((BaseActivity) this.j).currentScreen, "Artist Detail", stringBuilder2.toString());
                a(str3, str4, track.getLanguage());
                if (((GaanaActivity) this.j).getmCurrentPlayerFragment() != null) {
                    an.a().a("click", "ac", "three dot menu", "player", "", "Artist Detail", businessObject.getBusinessObjId(), "");
                    return;
                }
                an.a().a("click", "ac", "three dot menu", "", "", "Artist Detail", businessObject.getBusinessObjId(), "");
                return;
            }
            a = aj.a();
            context = this.j;
            stringBuilder = new StringBuilder();
            stringBuilder.append(this.j.getString(R.string.no));
            stringBuilder.append(" ");
            stringBuilder.append(this.j.getString(R.string.artist));
            a.a(context, stringBuilder.toString());
        }
    }

    private BusinessObject g() {
        if (this.l.getArrListBusinessObj() != null && this.l.getArrListBusinessObj().size() > 0) {
            return this.l;
        }
        if (this.l instanceof Track) {
            return this.l;
        }
        BusinessObject trackFromLocalMedia;
        if (this.l instanceof OfflineTrack) {
            if (this.l.isLocalMedia()) {
                trackFromLocalMedia = LocalMediaManager.getInstance(this.j).getTrackFromLocalMedia((OfflineTrack) this.l);
            } else {
                trackFromLocalMedia = (Track) DownloadManager.c().a(this.l.getBusinessObjId(), true);
            }
            return trackFromLocalMedia;
        }
        if (!this.l.isLocalMedia() && DownloadManager.c().b(this.l).booleanValue()) {
            trackFromLocalMedia = DownloadManager.c().a(this.l.getBusinessObjId(), false);
            if (!(trackFromLocalMedia == null || trackFromLocalMedia.getArrListBusinessObj() == null)) {
                this.l.setArrListBusinessObj(trackFromLocalMedia.getArrListBusinessObj());
                return this.l;
            }
        } else if (this.l.isLocalMedia()) {
            if (this.l instanceof Album) {
                this.l.setArrListBusinessObj(LocalMediaManager.getInstance(this.j).getSongsByAlbum(this.l.getBusinessObjId()));
                return this.l;
            } else if (this.l instanceof Playlist) {
                this.l.setArrListBusinessObj(LocalMediaManager.getInstance(this.j).getSongsByPlaylist(this.l.getBusinessObjId()));
                return this.l;
            }
        } else if ((this.l instanceof Playlist) && PlaylistSyncManager.getInstance().isMyPlaylist((Playlist) this.l) && !((Playlist) this.l).getAutoDisplayHome()) {
            this.l.setArrListBusinessObj(PlaylistSyncManager.getInstance().getPlaylistDetails((Playlist) this.l).getArrListBusinessObj());
            return this.l;
        }
        return null;
    }

    private boolean h() {
        BaseActivity baseActivity;
        StringBuilder stringBuilder;
        StringBuilder stringBuilder2;
        if (((BaseActivity) this.j).currentScreen.startsWith("Fav")) {
            baseActivity = (BaseActivity) this.j;
            stringBuilder = new StringBuilder();
            stringBuilder.append(((BaseActivity) this.j).currentScreen);
            stringBuilder.append(" Detail");
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(((BaseActivity) this.j).currentScreen);
            stringBuilder2.append(" Detail - ");
            stringBuilder2.append(((BaseActivity) this.j).currentItem);
            stringBuilder2.append(" - ");
            stringBuilder2.append(((BaseActivity) this.j).currentFavpage);
            stringBuilder2.append(" - Similar Albums");
            baseActivity.sendGAEvent(stringBuilder.toString(), "Play", stringBuilder2.toString());
            an.a().a("click", "ac", "three dot menu", "player", "", "Similar Albums", "", "");
        } else {
            baseActivity = (BaseActivity) this.j;
            stringBuilder = new StringBuilder();
            stringBuilder.append(((BaseActivity) this.j).currentScreen);
            stringBuilder.append(" Detail");
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(((BaseActivity) this.j).currentScreen);
            stringBuilder2.append(" Detail - ");
            stringBuilder2.append(((BaseActivity) this.j).currentItem);
            stringBuilder2.append(" - Similar Albums");
            baseActivity.sendGAEvent(stringBuilder.toString(), "Similar Albums", stringBuilder2.toString());
            an.a().a("click", "ac", "three dot menu", "player", "", "Similar Albums", "", "");
        }
        Track track;
        if (this.l instanceof Track) {
            track = (Track) this.l;
            a(track.getAlbumId(), track.getAlbumTitle(), track.getArtwork(), track.getLanguage());
        } else if (this.l instanceof OfflineTrack) {
            track = (Track) DownloadManager.c().a(this.l.getBusinessObjId(), true);
            a(track.getAlbumId(), track.getAlbumTitle(), track.getArtwork(), track.getLanguage());
        } else {
            a(this.l);
        }
        return false;
    }

    private boolean i() {
        BaseActivity baseActivity;
        StringBuilder stringBuilder;
        StringBuilder stringBuilder2;
        if (((BaseActivity) this.j).currentScreen.startsWith("Fav")) {
            baseActivity = (BaseActivity) this.j;
            stringBuilder = new StringBuilder();
            stringBuilder.append(((BaseActivity) this.j).currentScreen);
            stringBuilder.append(" Detail");
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(((BaseActivity) this.j).currentScreen);
            stringBuilder2.append(" Detail - ");
            stringBuilder2.append(((BaseActivity) this.j).currentItem);
            stringBuilder2.append(" - ");
            stringBuilder2.append(((BaseActivity) this.j).currentFavpage);
            stringBuilder2.append(" - Similar Artists");
            baseActivity.sendGAEvent(stringBuilder.toString(), "Play", stringBuilder2.toString());
            if (((GaanaActivity) this.j).getmCurrentPlayerFragment() != null) {
                an.a().a("click", "ac", "three dot menu", "player", "", "Similar Artists", "", "");
            } else {
                an.a().a("click", "ac", "three dot menu", "Similar Artists", "", "", "", "");
            }
        } else {
            baseActivity = (BaseActivity) this.j;
            stringBuilder = new StringBuilder();
            stringBuilder.append(((BaseActivity) this.j).currentScreen);
            stringBuilder.append(" Detail");
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(((BaseActivity) this.j).currentScreen);
            stringBuilder2.append(" Detail - ");
            stringBuilder2.append(((BaseActivity) this.j).currentItem);
            stringBuilder2.append(" - Similar Artists");
            baseActivity.sendGAEvent(stringBuilder.toString(), "Similar Artists", stringBuilder2.toString());
            if (((GaanaActivity) this.j).getmCurrentPlayerFragment() != null) {
                an.a().a("click", "ac", "three dot menu", "player", "", "Similar Artists", "", "");
            } else {
                an.a().a("click", "en", this.l.getBusinessObjId(), "Similar Artists", this.l.getBusinessObjId(), "Similar Artists", "", "");
            }
        }
        Track track;
        if (this.l instanceof Track) {
            track = (Track) this.l;
            a(((Artist) track.getArtists().get(0)).artist_id, ((Artist) track.getArtists().get(0)).name, track.getLanguage());
        } else if (this.l instanceof OfflineTrack) {
            track = (Track) DownloadManager.c().a(this.l.getBusinessObjId(), true);
            a(((Artist) track.getArtists().get(0)).artist_id, ((Artist) track.getArtists().get(0)).name, track.getLanguage());
        } else {
            a(this.l);
        }
        return false;
    }

    private void j() {
        boolean z = this.l.getBusinessObjType() == BusinessObjectType.Tracks;
        if (this.l instanceof OfflineTrack) {
            if (this.l.isLocalMedia()) {
                this.l = LocalMediaManager.getInstance(this.j).getTrackFromLocalMedia((OfflineTrack) this.l);
            } else {
                this.l = (Track) DownloadManager.c().a(this.l.getBusinessObjId(), true);
            }
        }
        if (n.e(this.l)) {
            aj.a().a(this.j, this.j.getString(R.string.please_wait_while_previous_favorite_action_is_being_performed));
            return;
        }
        if (this.l.isFavorite().booleanValue()) {
            if (!(TextUtils.isEmpty(a()) || TextUtils.isEmpty(b()))) {
                n.a().b(a(), b());
            }
            c();
            this.l.setFavorite(Boolean.valueOf(false));
            ((BaseActivity) this.j).addRemoveFav(this.l, Boolean.valueOf(true), false, this.q);
            if ((this.n instanceof RevampedDetailListing) && ((RevampedDetailListing) this.n).c.startsWith("ArtistDetailScreen")) {
                an.a().a("click", "ac", ((RevampedDetailListing) this.n).s().getBusinessObjId(), ((RevampedDetailListing) this.n).d(), this.l.getBusinessObjId(), "unfav", "", "");
            } else {
                an.a().a("click", "ac", this.l.getBusinessObjId(), this.l.getName(), "", "unfav", "", "");
            }
            if (this.n != null) {
                if ((this.n instanceof ListingFragment) && ((((ListingFragment) this.n).e() instanceof MyMusicFragment) || (((ListingFragment) this.n).e() instanceof MyMusicItemFragment))) {
                    ((ListingFragment) this.n).a(this.l, true);
                } else if (this.n instanceof ListingFragment) {
                    ((ListingFragment) this.n).i();
                } else if (this.n instanceof FavoritesFragment) {
                    ((FavoritesFragment) this.n).a(this.l, true);
                } else if ((this.n instanceof RevampedDetailListing) || (this.n instanceof AlbumDetailsMaterialListing) || (this.n instanceof GaanaSpecialDetailsMaterialListing) || (this.n instanceof RadioDetailsMaterialListing)) {
                    this.n.refreshFavoriteCount(false);
                } else if (this.n instanceof MyMusicItemFragment) {
                    ((MyMusicItemFragment) this.n).a(this.l, true);
                } else {
                    this.n.refreshListView();
                }
            }
        } else {
            if (!(TextUtils.isEmpty(a()) || TextUtils.isEmpty(b()))) {
                n.a().a(a(), b());
            }
            c();
            if (this.m.getCurrentUser().getLoginStatus()) {
                this.l.setFavorite(Boolean.valueOf(true));
                ap.a().a(this.j, this.l, this.q);
                if ((this.n instanceof RevampedDetailListing) && ((RevampedDetailListing) this.n).c.startsWith("ArtistDetailScreen")) {
                    an.a().a("click", "ac", ((RevampedDetailListing) this.n).s().getBusinessObjId(), ((RevampedDetailListing) this.n).d(), this.l.getBusinessObjId(), "fav", "", "");
                } else {
                    an.a().a("click", "ac", this.l.getBusinessObjId(), this.l.getName(), "", "fav", "", "");
                }
            } else {
                an.a().a("click", "ac", "fav", "", "", "LOGIN", "", "");
                a((int) R.id.favoriteMenu, this.l, this.j.getResources().getString(R.string.login_bottom_sheet_favorite_text));
            }
            if (this.n != null) {
                if (this.n instanceof ListingFragment) {
                    ((ListingFragment) this.n).i();
                } else if (((this.n instanceof RevampedDetailListing) || (this.n instanceof RadioDetailsMaterialListing) || (this.n instanceof AlbumDetailsMaterialListing) || (this.n instanceof GaanaSpecialDetailsMaterialListing)) && this.m.getCurrentUser().getLoginStatus()) {
                    this.n.refreshFavoriteCount(true);
                } else if (!(this.n instanceof ProfileFragment)) {
                    this.n.refreshListView();
                }
            }
        }
        if (z) {
            k();
        }
    }

    private void k() {
        if (GaanaMusicService.u() && this.m.getCurrentUser().getLoginStatus()) {
            PlayerTrack i = PlayerManager.a(GaanaApplication.getContext()).i();
            if (i != null && i.b() != null && i.b().getBusinessObjId().equals(this.l.getBusinessObjId())) {
                o.g(GaanaApplication.getContext());
            }
        }
    }

    public void a(final n nVar) {
        View inflate = ((LayoutInflater) this.j.getSystemService("layout_inflater")).inflate(R.layout.auto_nightmode_bottom_sheet_display, null);
        this.t = new BottomSheetDialog(this.j);
        this.t.setContentView(inflate);
        BottomSheetBehavior from = BottomSheetBehavior.from((View) inflate.getParent());
        if (from != null) {
            from.setPeekHeight(this.j.getResources().getDimensionPixelSize(R.dimen.login_bottom_sheet_height));
        }
        Button button = (Button) inflate.findViewById(R.id.button_auto_theme);
        TextView textView = (TextView) inflate.findViewById(R.id.changetheme_only);
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                nVar.onEnableAutoNow();
                af.this.t.dismiss();
            }
        });
        textView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                nVar.onChangeThemeOnly();
                af.this.t.dismiss();
            }
        });
        com.services.d.a().a("pref_show_auto_night_mode_dialog", true, false);
        this.t.show();
    }

    public void a(int i, BusinessObject businessObject, String str) {
        a(i, businessObject, str, null);
    }

    public void a(int i, BusinessObject businessObject, String str, m mVar) {
        View inflate = ((LayoutInflater) this.j.getSystemService("layout_inflater")).inflate(R.layout.login_bottom_sheet_display, null);
        this.t = new BottomSheetDialog(this.j);
        this.t.setContentView(inflate);
        BottomSheetBehavior from = BottomSheetBehavior.from((View) inflate.getParent());
        if (from != null) {
            from.setPeekHeight(this.j.getResources().getDimensionPixelSize(R.dimen.login_bottom_sheet_height));
        }
        TextView textView = (TextView) inflate.findViewById(R.id.pager_login_button);
        TextView textView2 = (TextView) inflate.findViewById(R.id.pager_signup_button);
        Button button = (Button) inflate.findViewById(R.id.fb_onboard_login_btn);
        switch (i) {
            case R.id.MyMusicFavorites /*2131296320*/:
            case R.id.MyMusicMenuAlbums /*2131296321*/:
            case R.id.MyMusicMenuArtists /*2131296322*/:
            case R.id.MyMusicMenuPlaylists /*2131296326*/:
            case R.id.MyMusicMenuRadios /*2131296327*/:
            case R.id.MyMusicMenuSongs /*2131296328*/:
                this.e = mVar;
                textView.setOnClickListener(this.g);
                textView2.setOnClickListener(this.g);
                button.setOnClickListener(this.g);
                break;
            case R.id.addToPlaylistMenu /*2131296423*/:
                this.u = businessObject;
                textView.setOnClickListener(this.f);
                textView2.setOnClickListener(this.f);
                button.setOnClickListener(this.f);
                break;
            case R.id.favoriteMenu /*2131297124*/:
                textView.setOnClickListener(this.h);
                textView2.setOnClickListener(this.h);
                button.setOnClickListener(this.h);
                an.a().a("click", "ac", "three dot menu", "fav", "", "", "", "");
                break;
            case R.id.login_download /*2131297633*/:
                textView.setOnClickListener(this.i);
                textView2.setOnClickListener(this.i);
                button.setOnClickListener(this.i);
                break;
        }
        ((TextView) inflate.findViewById(R.id.login_title_text)).setText(str);
        this.t.show();
    }

    private void a(final boolean z, final boolean z2, final boolean z3) {
        MoEngage.getInstance().reportLoginStarted("FB");
        if (Util.j(this.j)) {
            LoginManager.getInstance().login((BaseActivity) this.j, LoginType.FB, new IOnLoginCompleted() {
                public void onLoginCompleted(LOGIN_STATUS login_status, UserInfo userInfo, Bundle bundle) {
                    switch (AnonymousClass26.a[login_status.ordinal()]) {
                        case 1:
                            if ((af.this.j instanceof GaanaActivity) && !((GaanaActivity) af.this.j).isFinishing()) {
                                ((GaanaActivity) af.this.j).updateSidebarUserDetails();
                            }
                            if (af.this.e != null) {
                                af.this.e.onResponse(true);
                                return;
                            } else if (z) {
                                af.this.l.setFavorite(Boolean.valueOf(true));
                                ((BaseActivity) af.this.j).addRemoveFav(af.this.l, Boolean.valueOf(false), false, af.this.q);
                                if (af.this.l.getBusinessObjType() == BusinessObjectType.Tracks) {
                                    af.this.k();
                                }
                                af.this.l();
                                return;
                            } else if (z2) {
                                if (af.this.u.getArrListBusinessObj() != null && af.this.u.getArrListBusinessObj().size() > 0) {
                                    ap.a().a(af.this.j, af.this.u.getArrListBusinessObj());
                                    return;
                                } else if (af.this.u instanceof Track) {
                                    ap.a().a(af.this.j, (Track) af.this.u, af.this.u.isLocalMedia(), false);
                                    ArrayList arrayList = new ArrayList();
                                    arrayList.add((Track) af.this.u);
                                    if (af.this.m == null) {
                                        af.this.m = (GaanaApplication) af.this.j.getApplicationContext();
                                    }
                                    af.this.m.setArrListTracksForPlaylist(arrayList);
                                    ap.a().a(af.this.j, false);
                                    af.this.l();
                                    return;
                                } else if (GaanaApplication.getInstance().getArrListTracksForPlaylist() != null && GaanaApplication.getInstance().getArrListTracksForPlaylist().size() > 0) {
                                    ap.a().a(af.this.j, GaanaApplication.getInstance().getArrListTracksForPlaylist());
                                    return;
                                } else {
                                    return;
                                }
                            } else if (z3 && af.this.v != null) {
                                af.this.v.onLoginSuccess();
                                return;
                            } else {
                                return;
                            }
                        case 2:
                        case 3:
                        case 4:
                            if (userInfo == null || userInfo.getError() == null) {
                                aj.a().a(af.this.j, af.this.j.getString(R.string.login_failed));
                                return;
                            } else {
                                aj.a().a(af.this.j, userInfo.getError());
                                return;
                            }
                        case 5:
                            af.this.a(userInfo);
                            return;
                        case 6:
                            if (af.this.j != null && !((Activity) af.this.j).isFinishing()) {
                                new f(af.this.j).a(af.this.j.getResources().getString(R.string.mandatory_field_missing));
                                return;
                            }
                            return;
                        case 7:
                            if (af.this.j != null && !((Activity) af.this.j).isFinishing()) {
                                FbLoginErrorDialog fbLoginErrorDialog = new FbLoginErrorDialog(af.this.j);
                                fbLoginErrorDialog.setOnLoginCompletedListener(this);
                                fbLoginErrorDialog.show();
                                return;
                            }
                            return;
                        default:
                            return;
                    }
                }
            });
        } else {
            ap.a().f(this.j);
        }
    }

    private void l() {
        Intent intent = new Intent(this.j, GaanaActivity.class);
        intent.setFlags(603979776);
        this.j.startActivity(intent);
    }

    private void a(UserInfo userInfo) {
        Intent intent = new Intent(this.j, Login.class);
        intent.setFlags(C.ENCODING_PCM_MU_LAW);
        intent.putExtra("temp_user_tag", LoginManager.getInstance().getLoginInfo());
        if (!(userInfo == null || userInfo.getError() == null)) {
            intent.putExtra("message", userInfo.getError());
        }
        this.j.startActivity(intent);
    }

    /* Access modifiers changed, original: protected */
    public void a(BusinessObject businessObject, s sVar) {
        this.l = businessObject;
        URLManager uRLManager = new URLManager();
        uRLManager.a(BusinessObjectType.Tracks);
        String str = "https://api.gaana.com/index.php?";
        boolean z = businessObject instanceof Playlist;
        StringBuilder stringBuilder;
        if (z) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append(com.constants.c.w);
            stringBuilder2.append("playlist_id=");
            stringBuilder2.append(businessObject.getBusinessObjId());
            stringBuilder2.append("&playlist_type=");
            Playlist playlist = (Playlist) businessObject;
            stringBuilder2.append(playlist.getPlaylistType());
            str = stringBuilder2.toString();
            if (playlist.getAutomated() != null && playlist.getAutomated().equalsIgnoreCase("1")) {
                stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append("&automated=1");
                str = stringBuilder.toString();
            }
        } else if (businessObject instanceof Album) {
            stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append("type=album&subtype=album_detail&album_id=");
            stringBuilder.append(businessObject.getBusinessObjId());
            str = stringBuilder.toString();
        }
        uRLManager.a(str);
        if (z) {
            Playlist playlist2 = (Playlist) businessObject;
            if (PlaylistSyncManager.getInstance().isMyPlaylist(playlist2) && !playlist2.getAutoDisplayHome()) {
                ((BaseActivity) this.j).showProgressDialog(Boolean.valueOf(true), this.j.getString(R.string.getting_playlist_details));
                ((BaseActivity) this.j).getMyPlaylistDetails(sVar, playlist2, uRLManager);
            }
        }
        if (this.m.isAppInOfflineMode()) {
            ((BaseActivity) this.j).displayFeatureNotAvailableOfflineDialog(this.j.getResources().getString(R.string.error_msg_feature_not_available_offline_prefix));
        } else if (Util.j(this.j)) {
            ((BaseActivity) this.j).showProgressDialog(Boolean.valueOf(true), this.j.getString(R.string.getting_details));
            i.a().a(sVar, uRLManager);
        } else {
            ap.a().f(this.j);
        }
    }

    private void a(Track track) {
        if (!track.isLocalMedia()) {
            if ("1".equalsIgnoreCase(track.getLocationAvailability()) && "0".equalsIgnoreCase(track.getDeviceAvailability())) {
                ap.a().a(this.j, this.j.getString(R.string.error_msg_content_unavailable_for_device));
                return;
            } else if ("0".equalsIgnoreCase(track.getLocationAvailability()) && "1".equalsIgnoreCase(track.getDeviceAvailability())) {
                ap.a().a(this.j, this.j.getString(R.string.error_msg_content_unavailable_for_location));
                return;
            } else if (this.m.isAppInOfflineMode() && !DownloadManager.c().l(Integer.parseInt(track.getBusinessObjId())).booleanValue()) {
                ((BaseActivity) this.j).displayFeatureNotAvailableOfflineDialog("This song");
                return;
            } else if (!(Util.j(this.j) || DownloadManager.c().l(Integer.parseInt(track.getBusinessObjId())).booleanValue())) {
                ap.a().f(this.j);
                return;
            }
        }
        PlayerTrack playerTrack = new PlayerTrack(track, track.getAlbumId(), SOURCE_TYPE.OTHER.ordinal(), this.l.getEnglishName());
        if (this.n != null) {
            playerTrack.f(this.n.getPageName());
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(playerTrack);
        PlayerManager.a(this.j).a(arrayList, playerTrack, 999999);
        PlayerManager.a(this.j).a(PlayerType.GAANA, this.j);
        ((GaanaActivity) this.j).setUpdatePlayerFragment();
    }

    private void a(final Playlist playlist) {
        ((GaanaActivity) this.j).showProgressDialog(Boolean.valueOf(true), this.j.getString(R.string.getting_playlist_details));
        h.a().a(new TaskListner() {
            private ArrayList c;

            public void doBackGroundTask() {
                if (playlist.isLocalMedia()) {
                    this.c = LocalMediaManager.getInstance(af.this.j).getSongsByPlaylist(playlist.getBusinessObjId());
                    return;
                }
                if (!af.this.m.isAppInOfflineMode() && Util.j(af.this.j)) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(com.constants.c.w);
                    stringBuilder.append("playlist_id=");
                    stringBuilder.append(playlist.getBusinessObjId());
                    stringBuilder.append("&playlist_type=");
                    stringBuilder.append(playlist.getPlaylistType());
                    String stringBuilder2 = stringBuilder.toString();
                    String authToken = af.this.m.getCurrentUser().getAuthToken();
                    StringBuilder stringBuilder3 = new StringBuilder();
                    stringBuilder3.append(stringBuilder2);
                    stringBuilder3.append("&token=");
                    stringBuilder3.append(authToken);
                    com.services.i a = new com.services.j().a(stringBuilder3.toString(), true);
                    if (!(a == null || !a.b().booleanValue() || a.a() == null)) {
                        try {
                            BusinessObject businessObject = (BusinessObject) new GsonBuilder().excludeFieldsWithModifiers(8, 4).create().fromJson(a.a(), Tracks.class);
                            if (businessObject != null) {
                                this.c = businessObject.getArrListBusinessObj();
                                for (int size = this.c.size() - 1; size >= 0; size--) {
                                    Track track = (Track) this.c.get(size);
                                    if (track.getIslocal() != null && track.getIslocal().equals("1")) {
                                        track = LocalMediaManager.getInstance(af.this.j).getLocalTrackFromHash(track.getBusinessObjId());
                                        this.c.remove(size);
                                        if (track != null) {
                                            this.c.add(size, track);
                                        }
                                    }
                                }
                            }
                        } catch (Exception unused) {
                        }
                    }
                }
                if (this.c == null || this.c.size() == 0) {
                    Tracks playlistDetails = PlaylistSyncManager.getInstance().getPlaylistDetails(playlist);
                    if (playlistDetails != null) {
                        this.c = playlistDetails.getArrListBusinessObj();
                    }
                }
            }

            public void onBackGroundTaskCompleted() {
                ((GaanaActivity) af.this.j).hideProgressDialog();
                if (this.c == null || this.c.size() == 0) {
                    aj.a().a(af.this.j, af.this.j.getString(R.string.no_tracks_to_edit));
                    return;
                }
                try {
                    if (af.this.l instanceof Playlist) {
                        for (int size = this.c.size() - 1; size >= 0; size--) {
                            Track track = (Track) this.c.get(size);
                            if (track.getIslocal() != null && track.getIslocal().equals("1")) {
                                track = LocalMediaManager.getInstance(af.this.j).getLocalTrackFromHash(track.getBusinessObjId());
                                this.c.remove(size);
                                if (track != null) {
                                    this.c.add(size, track);
                                }
                            }
                        }
                    }
                } catch (Exception unused) {
                }
                af.this.a((Playlist) af.this.l, this.c);
            }
        }, -1);
    }

    public void a(Playlist playlist, ArrayList<?> arrayList) {
        ListingComponents i = Constants.i();
        i.a((BusinessObject) playlist);
        i.b(playlist.getName());
        Iterator it = i.c().iterator();
        while (it.hasNext()) {
            ListingButton listingButton = (ListingButton) it.next();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(listingButton.c().k());
            stringBuilder.append("playlist_id=");
            stringBuilder.append(playlist.getBusinessObjId());
            stringBuilder.append("&playlist_type=");
            stringBuilder.append(playlist.getPlaylistType());
            listingButton.c().a(stringBuilder.toString());
            listingButton.a((ArrayList) arrayList);
        }
        this.m.setListingComponents(i);
        Bundle bundle = new Bundle();
        bundle.putBoolean("ITEM_LISTING_IS_IN_EDIT_MODE", true);
        BaseGaanaFragment editPlaylistFragment = new EditPlaylistFragment();
        editPlaylistFragment.setArguments(bundle);
        ((GaanaActivity) this.j).displayFragment(editPlaylistFragment);
    }

    private void m() {
        final String businessObjId = this.l.getBusinessObjId();
        new CustomDialogView(this.j, (int) R.string.dialog_deletdownload_text, new OnButtonClickListener() {
            public void onNegativeButtonClick() {
            }

            public void onPositiveButtonClick() {
                if ((af.this.l instanceof Track) || (af.this.l instanceof OfflineTrack)) {
                    DownloadManager.c().d(af.this.l.getBusinessObjId());
                } else {
                    DownloadManager.c().p(Integer.parseInt(businessObjId));
                    DownloadManager.c().d(Integer.parseInt(businessObjId));
                }
                if (af.this.n instanceof DownloadDetailsFragment) {
                    ((DownloadDetailsFragment) af.this.n).d();
                } else if (af.this.n instanceof MyMusicItemFragment) {
                    ((MyMusicItemFragment) af.this.n).d();
                } else if (af.this.n instanceof ListingFragment) {
                    ((ListingFragment) af.this.n).i();
                } else {
                    ((BaseActivity) af.this.j).refreshListView();
                }
            }
        }).show();
    }

    /* Access modifiers changed, original: protected */
    public void a(BusinessObject businessObject) {
        ListingComponents d;
        switch (businessObject.getBusinessObjType()) {
            case Artists:
                d = Constants.d();
                d.b(this.j.getString(R.string.similar_artists));
                break;
            case Albums:
                d = Constants.c();
                d.b(this.j.getString(R.string.similar_albums));
                break;
            default:
                d = null;
                break;
        }
        Iterator it = d.c().iterator();
        while (it.hasNext()) {
            ListingButton listingButton = (ListingButton) it.next();
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(listingButton.c().k());
            stringBuilder.append(businessObject.getBusinessObjId());
            listingButton.c().a(stringBuilder.toString());
        }
        ((GaanaApplication) this.j.getApplicationContext()).setListingComponents(d);
        Bundle bundle = new Bundle();
        BaseGaanaFragment itemListingFragment = new ItemListingFragment();
        bundle.putString("ArtistID", businessObject.getBusinessObjId());
        itemListingFragment.setArguments(bundle);
        ((GaanaActivity) this.j).displayFragment(itemListingFragment);
    }

    private void e(BusinessObject businessObject) {
        if (this.m.isAppInOfflineMode() && !DownloadManager.c().l(Integer.parseInt(businessObject.getBusinessObjId())).booleanValue()) {
            ((BaseActivity) this.j).displayFeatureNotAvailableOfflineDialog("This song");
        } else if (Util.j(this.j) || DownloadManager.c().l(Integer.parseInt(businessObject.getBusinessObjId())).booleanValue()) {
            PlayerTrack a = d.a().a(this.n, businessObject);
            if (PlayerManager.a(this.j).a(a, this.j) || !GaanaApplication.getInstance().getPlayerStatus()) {
                PlayerManager.a(this.j).a(PlayerType.GAANA);
                PlayerManager.a(this.j).a(null, a, 999999);
                PlayerManager.a(this.j).e(true);
                PlayerManager.a(this.j).j();
                ((GaanaActivity) this.j).setUpdatePlayerFragment();
            }
        } else {
            ap.a().f(this.j);
        }
    }

    private void a(BusinessObject businessObject, boolean z) {
        if (!businessObject.isLocalMedia()) {
            if (this.m.isAppInOfflineMode() && !DownloadManager.c().l(Integer.parseInt(businessObject.getBusinessObjId())).booleanValue()) {
                ((BaseActivity) this.j).displayFeatureNotAvailableOfflineDialog("This song");
                return;
            } else if (!(Util.j(this.j) || DownloadManager.c().l(Integer.parseInt(businessObject.getBusinessObjId())).booleanValue())) {
                ap.a().f(this.j);
                return;
            }
        }
        if (Constants.cY) {
            JukeSessionManager.getInstance().addPlayNext(JukeSessionManager.getInstance().getJukeSessionPlaylist(), businessObject.getBusinessObjId());
            return;
        }
        PlayerTrack a = d.a().a(this.n, businessObject);
        int a2 = PlayerManager.a(this.j).a(a, this.j, z);
        if (a2 == 1 || !(a2 == -1 || GaanaMusicService.s().isPlaying() || GaanaMusicService.s().l())) {
            PlayerManager.a(this.j).a(null, a, 999999);
            PlayerManager.a(this.j).a(PlayerType.GAANA);
            PlayerManager.a(this.j).e(true);
            PlayerManager.a(this.j).j();
            ((GaanaActivity) this.j).setUpdatePlayerFragment();
        }
    }

    /* Access modifiers changed, original: protected */
    public void a(String str, String str2, String str3, String str4) {
        BusinessObject album = new Album();
        album.setBusinessObjId(str);
        album.setName(str2);
        album.setLanguage(str4);
        album.setBusinessObjType(BusinessObjectType.Albums);
        album.setArtwork(str3);
        album.setLocalMedia(this.l.isLocalMedia());
        c(album);
    }

    /* Access modifiers changed, original: protected */
    public void a(Playlist playlist, ListingComponents listingComponents) {
        if (playlist.isLocalMedia()) {
            f((BusinessObject) playlist);
        } else {
            Bundle a;
            BaseGaanaFragment albumDetailsMaterialListing;
            boolean isMyPlaylist = PlaylistSyncManager.getInstance().isMyPlaylist(playlist);
            if (!isMyPlaylist || playlist.getAutoDisplayHome()) {
                if (this.m.isAppInOfflineMode()) {
                    if (!DownloadManager.c().b((BusinessObject) playlist).booleanValue()) {
                        ((BaseActivity) this.j).displayFeatureNotAvailableOfflineDialog("");
                        return;
                    }
                } else if (!(Util.j(this.j) || DownloadManager.c().b((BusinessObject) playlist).booleanValue())) {
                    ap.a().f(this.j);
                    return;
                }
                if ((this.m.isAppInOfflineMode() || !Util.j(this.j)) && !ap.a().a((BusinessObject) playlist, null)) {
                    aj.a().a(this.j, this.j.getResources().getString(R.string.toast_subscription_expired));
                    return;
                }
            }
            if (isMyPlaylist || Constants.Z) {
                a = AlbumDetailsMaterialListing.a((BusinessObject) playlist, k);
                a.putBoolean("DETAIL_PAGE_FROM_MYPLAYLIST", isMyPlaylist);
                albumDetailsMaterialListing = new AlbumDetailsMaterialListing();
            } else {
                a = RevampedDetailListing.a((BusinessObject) playlist, k, REVAMPED_DETAIL_TYPE.PLAYLIST.getNumVal());
                albumDetailsMaterialListing = new RevampedDetailListing();
            }
            k = null;
            albumDetailsMaterialListing.setArguments(a);
            ((GaanaActivity) this.j).displayFragment(albumDetailsMaterialListing);
        }
    }

    /* Access modifiers changed, original: protected */
    public void b(Playlist playlist, ListingComponents listingComponents) {
        if (!playlist.isLocalMedia()) {
            boolean isMyPlaylist = PlaylistSyncManager.getInstance().isMyPlaylist(playlist);
            if (!isMyPlaylist || playlist.getAutoDisplayHome()) {
                if (this.m.isAppInOfflineMode()) {
                    if (!DownloadManager.c().b((BusinessObject) playlist).booleanValue()) {
                        ((BaseActivity) this.j).displayFeatureNotAvailableOfflineDialog("");
                        return;
                    }
                } else if (!(Util.j(this.j) || DownloadManager.c().b((BusinessObject) playlist).booleanValue())) {
                    ap.a().f(this.j);
                    return;
                }
                if ((this.m.isAppInOfflineMode() || !Util.j(this.j)) && !ap.a().a((BusinessObject) playlist, null)) {
                    aj.a().a(this.j, this.j.getResources().getString(R.string.toast_subscription_expired));
                    return;
                }
            }
            u.a().a("PartyHub", "Create_Suggestion_Tap", playlist != null ? playlist.getBusinessObjId() : "New");
            if (isMyPlaylist) {
                ((GaanaActivity) this.j).displayFragment(JukePartyFragment.newInstance(JukeSessionManager.getJukePlaylist(playlist), 0, "", true));
            } else {
                ((GaanaActivity) this.j).displayFragment(JukePartyFragment.newInstance(playlist, 0, "", false));
            }
            k = null;
        }
    }

    /* Access modifiers changed, original: protected */
    public void c(Playlist playlist, ListingComponents listingComponents) {
        if (playlist.isLocalMedia()) {
            f((BusinessObject) playlist);
        } else {
            if (!PlaylistSyncManager.getInstance().isMyPlaylist(playlist)) {
                if (this.m.isAppInOfflineMode()) {
                    if (!DownloadManager.c().b((BusinessObject) playlist).booleanValue()) {
                        ((BaseActivity) this.j).displayFeatureNotAvailableOfflineDialog("");
                        return;
                    }
                } else if (!(Util.j(this.j) || DownloadManager.c().b((BusinessObject) playlist).booleanValue())) {
                    ap.a().f(this.j);
                    return;
                }
                if ((this.m.isAppInOfflineMode() || !Util.j(this.j)) && !ap.a().a((BusinessObject) playlist, null)) {
                    aj.a().a(this.j, this.j.getResources().getString(R.string.toast_subscription_expired));
                    return;
                }
            }
            Bundle a = GaanaSpecialDetailsMaterialListing.a((BusinessObject) playlist, k);
            k = null;
            BaseGaanaFragment gaanaSpecialDetailsMaterialListing = new GaanaSpecialDetailsMaterialListing();
            gaanaSpecialDetailsMaterialListing.setArguments(a);
            ((GaanaActivity) this.j).displayFragment(gaanaSpecialDetailsMaterialListing);
        }
    }

    /* Access modifiers changed, original: protected */
    public void b(BusinessObject businessObject) {
        if (businessObject != null && !TextUtils.isEmpty(businessObject.getBusinessObjId())) {
            if (businessObject.isLocalMedia()) {
                f(businessObject);
            } else {
                BaseGaanaFragment radioDetailsMaterialListing;
                Bundle a;
                if (this.m.isAppInOfflineMode()) {
                    if (!DownloadManager.c().b(businessObject).booleanValue()) {
                        ((BaseActivity) this.j).displayFeatureNotAvailableOfflineDialog("");
                        return;
                    }
                } else if (!(Util.j(this.j) || DownloadManager.c().b(businessObject).booleanValue())) {
                    ap.a().f(this.j);
                    return;
                }
                this.l = businessObject;
                BaseGaanaFragment currentFragment = ((GaanaActivity) this.j).getCurrentFragment();
                if ((currentFragment instanceof RadioDetailsMaterialListing) && (businessObject instanceof Radio)) {
                    BusinessObject e = ((RadioDetailsMaterialListing) currentFragment).e();
                    if (e != null && businessObject.getBusinessObjId().equals(e.getBusinessObjId())) {
                        return;
                    }
                }
                if (Constants.Z) {
                    radioDetailsMaterialListing = new RadioDetailsMaterialListing();
                    a = RadioDetailsMaterialListing.a(this.l, null);
                } else {
                    radioDetailsMaterialListing = new RevampedDetailListing();
                    a = RevampedDetailListing.a(this.l, null, REVAMPED_DETAIL_TYPE.RADIO.getNumVal());
                }
                radioDetailsMaterialListing.setArguments(a);
                ((GaanaActivity) this.j).displayFragment(radioDetailsMaterialListing);
            }
        }
    }

    public void c(String str) {
        k = str;
    }

    /* Access modifiers changed, original: protected */
    public void c(BusinessObject businessObject) {
        if (!TextUtils.isEmpty(businessObject.getBusinessObjId())) {
            BaseGaanaFragment currentFragment = ((GaanaActivity) this.j).getCurrentFragment();
            if (!((GaanaActivity) this.j).isPlayerExpanded()) {
                if ((currentFragment instanceof AlbumDetailsMaterialListing) && (businessObject instanceof Album)) {
                    AlbumDetailsMaterialListing albumDetailsMaterialListing = (AlbumDetailsMaterialListing) currentFragment;
                    if (albumDetailsMaterialListing.c() != null) {
                        if (((Album) businessObject).getBusinessObjId().equals(albumDetailsMaterialListing.c().getBusinessObjId())) {
                            return;
                        }
                    }
                }
                if ((currentFragment instanceof ArtistDetailsMaterialListing) && (businessObject instanceof Artists.Artist)) {
                    ArtistDetailsMaterialListing artistDetailsMaterialListing = (ArtistDetailsMaterialListing) currentFragment;
                    if (artistDetailsMaterialListing.c() != null && ((Artists.Artist) businessObject).getBusinessObjId().equals(artistDetailsMaterialListing.c().getBusinessObjId())) {
                        return;
                    }
                }
            }
            if (businessObject.isLocalMedia()) {
                f(businessObject);
            } else {
                if (this.m.isAppInOfflineMode()) {
                    if (!DownloadManager.c().b(businessObject).booleanValue()) {
                        ((BaseActivity) this.j).displayFeatureNotAvailableOfflineDialog("");
                        return;
                    }
                } else if (!(Util.j(this.j) || DownloadManager.c().b(businessObject).booleanValue())) {
                    ap.a().f(this.j);
                    return;
                }
                boolean z = businessObject instanceof Artists.Artist;
                Bundle a;
                if (z && (this.n instanceof ListingFragment) && (((ListingFragment) this.n).e() instanceof MyMusicFragment)) {
                    ((GaanaActivity) this.j).displayFragment((ArtistFragment) ArtistFragment.a((Artists.Artist) businessObject));
                } else if (z) {
                    if (Constants.Z) {
                        currentFragment = new ArtistDetailsMaterialListing();
                        a = ArtistDetailsMaterialListing.a(businessObject, k);
                    } else {
                        currentFragment = new RevampedDetailListing();
                        a = RevampedDetailListing.a(businessObject, k, REVAMPED_DETAIL_TYPE.ARTIST.getNumVal());
                    }
                    currentFragment.setArguments(a);
                    ((GaanaActivity) this.j).displayFragment(currentFragment);
                } else {
                    int numVal = REVAMPED_DETAIL_TYPE.ALBUM.getNumVal();
                    if (businessObject instanceof Playlist) {
                        numVal = REVAMPED_DETAIL_TYPE.PLAYLIST.getNumVal();
                    } else if (businessObject instanceof Album) {
                        numVal = REVAMPED_DETAIL_TYPE.ALBUM.getNumVal();
                    }
                    if (Constants.Z) {
                        currentFragment = new AlbumDetailsMaterialListing();
                        a = AlbumDetailsMaterialListing.a(businessObject, k);
                    } else {
                        BaseGaanaFragment revampedDetailListing = new RevampedDetailListing();
                        a = RevampedDetailListing.a(businessObject, k, numVal);
                        currentFragment = revampedDetailListing;
                    }
                    currentFragment.setArguments(a);
                    currentFragment.setArguments(a);
                    ((GaanaActivity) this.j).displayFragment(currentFragment);
                }
            }
        }
    }

    private void f(BusinessObject businessObject) {
        this.l = businessObject;
        Bundle a;
        BaseGaanaFragment artistDetailsMaterialListing;
        if (businessObject instanceof Artists.Artist) {
            a = ArtistDetailsMaterialListing.a(businessObject, k);
            artistDetailsMaterialListing = new ArtistDetailsMaterialListing();
            artistDetailsMaterialListing.setArguments(a);
            ((GaanaActivity) this.j).displayFragment(artistDetailsMaterialListing);
        } else {
            a = AlbumDetailsMaterialListing.a(businessObject, k);
            artistDetailsMaterialListing = new AlbumDetailsMaterialListing();
            artistDetailsMaterialListing.setArguments(a);
            ((GaanaActivity) this.j).displayFragment(artistDetailsMaterialListing);
        }
        k = null;
    }

    public void d(BusinessObject businessObject) {
        this.m = (GaanaApplication) this.j.getApplicationContext();
        this.l = businessObject;
        if (this.l instanceof Radio) {
            b((Radio) this.l);
        }
    }

    private void a(String str, String str2, String str3) {
        BusinessObject artist = new Artists.Artist();
        artist.setBusinessObjId(str);
        artist.setName(str2);
        artist.setLanguage(str3);
        artist.setBusinessObjType(BusinessObjectType.Artists);
        artist.setLocalMedia(this.l.isLocalMedia());
        c(artist);
    }

    private void g(BusinessObject businessObject) {
        BaseGaanaFragment songsSelectionFragment = new SongsSelectionFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("BUSINESS_OBJECT", businessObject);
        bundle.putInt("tab_position", 0);
        if (this.r) {
            bundle.putInt("source_type", 0);
        }
        songsSelectionFragment.setArguments(bundle);
        ((GaanaActivity) this.j).displayFragment(songsSelectionFragment);
    }

    public Bitmap a(Bitmap bitmap, String str, String str2, int i, int i2) {
        CharSequence charSequence;
        float dimension = this.j.getResources().getDimension(R.dimen.activity_horizontal_margin);
        TextPaint textPaint = new TextPaint(65);
        textPaint.setStyle(Style.FILL);
        textPaint.setTypeface(Typeface.create(Typeface.DEFAULT, 1));
        textPaint.setColor(-1);
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(dimension);
        TextPaint textPaint2 = new TextPaint(65);
        textPaint2.setStyle(Style.FILL);
        textPaint2.setColor(ContextCompat.getColor(this.j, R.color.white_alfa_80));
        textPaint2.setAntiAlias(true);
        textPaint2.setTextSize(0.7f * dimension);
        int i3 = i;
        float f = ((float) i3) - (dimension / 4.0f);
        int i4 = i3;
        StaticLayout staticLayout = new StaticLayout(TextUtils.ellipsize(str, textPaint, f, TruncateAt.END).toString(), textPaint, i4, Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
        if (TextUtils.isEmpty(str2)) {
            charSequence = str2;
        } else {
            charSequence = TextUtils.ellipsize(str2, textPaint2, f, TruncateAt.END).toString();
        }
        StaticLayout staticLayout2 = new StaticLayout(charSequence, textPaint2, i3, Alignment.ALIGN_CENTER, 1.0f, 0.0f, false);
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), (int) (((float) bitmap.getHeight()) + (3.0f * dimension)), Config.ARGB_8888);
        Canvas canvas = new Canvas(createBitmap);
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, new Paint());
        canvas.save();
        canvas.translate(0.0f, ((float) bitmap.getHeight()) + (dimension / 2.0f));
        staticLayout.draw(canvas);
        canvas.restore();
        if (!TextUtils.isEmpty(charSequence)) {
            canvas.save();
            canvas.translate(0.0f, ((float) bitmap.getHeight()) + (dimension * 1.8f));
            staticLayout2.draw(canvas);
            canvas.restore();
        }
        return createBitmap;
    }
}
