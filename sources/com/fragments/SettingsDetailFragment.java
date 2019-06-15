package com.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.UnderlineSpan;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.actionbar.GenericBackActionBar;
import com.b.i;
import com.comscore.measurement.MeasurementDispatcher;
import com.constants.Constants;
import com.custom_card_response.CustomCard;
import com.custom_card_response.d;
import com.facebook.GraphResponse;
import com.fragments.WebViewsFragment.WebViewContent;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.GaanaActivity.ISleepTimerListener;
import com.gaana.R;
import com.gaana.analytics.MoEngage;
import com.gaana.analytics.UninstallIO;
import com.gaana.application.GaanaApplication;
import com.gaana.localmedia.FavouriteSyncManager;
import com.gaana.localmedia.PlaylistSyncManager;
import com.gaana.login.LoginManager.LOGIN_STATUS;
import com.gaana.models.BusinessObject;
import com.gaana.view.item.GaanaPlusItemView;
import com.gaana.view.item.LanguageSettingsItemView;
import com.gaana.view.item.RadioButtonDeviceListView;
import com.gaana.view.item.RedeemCouponItemView;
import com.gaana.view.item.ThemeSettingsItemView;
import com.google.api.client.http.HttpStatusCodes;
import com.integralads.avid.library.inmobi.session.internal.InternalAvidAdSessionContext;
import com.managers.DownloadManager;
import com.managers.PlayerManager;
import com.managers.aj;
import com.managers.ap;
import com.managers.k;
import com.managers.u;
import com.player_framework.PlayerConstants.PauseReasons;
import com.player_framework.PlayerStatus;
import com.player_framework.PlayerStatus.PlayerStates;
import com.player_framework.o;
import com.services.f;
import com.services.f.b;
import com.services.g;
import com.services.g.a;
import com.services.l;
import com.utilities.Util;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SettingsDetailFragment extends BaseGaanaFragment implements OnClickListener, a {
    private SwitchCompat A;
    private SwitchCompat B;
    private TextView C = null;
    private View D;
    private OnSeekBarChangeListener E = new OnSeekBarChangeListener() {
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            SettingsDetailFragment.this.mDeviceResManager.a("PREFERENCE_KEY_CROSSFADE_VALUE", seekBar.getProgress(), true);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("");
            stringBuilder.append(seekBar.getProgress());
            Util.b("cross_fade", stringBuilder.toString());
            LocalBroadcastManager.getInstance(GaanaApplication.getContext()).sendBroadcast(new Intent("broadcast_crossfade_status_changed"));
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (SettingsDetailFragment.this.C != null) {
                TextView a = SettingsDetailFragment.this.C;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("(");
                stringBuilder.append(i);
                stringBuilder.append(SettingsDetailFragment.this.getString(R.string.seconds));
                stringBuilder.append(")");
                a.setText(stringBuilder.toString());
            }
        }
    };
    private View F = null;
    private TextView G;
    private View H;
    private BroadcastReceiver I = null;
    private View J;
    private OnCheckedChangeListener K = new OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            SettingsDetailFragment.this.mDeviceResManager.a("PREFERENCE_KEY_GAPLESS_PLAYBACK", z, true);
            Util.b("gap_less_playback", z ? "1" : "0");
            if (z && SettingsDetailFragment.this.a != null) {
                SettingsDetailFragment.this.a.setEnabled(true);
            } else if (SettingsDetailFragment.this.a != null) {
                SettingsDetailFragment.this.a.setEnabled(false);
            }
            LocalBroadcastManager.getInstance(GaanaApplication.getContext()).sendBroadcast(new Intent("broadcast_crossfade_status_changed"));
        }
    };
    private OnCheckedChangeListener L = new OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (z) {
                compoundButton.setChecked(false);
                aj.a().a(SettingsDetailFragment.this.mContext, "Gapless playback has been temporarily disabled. It will soon be back.");
            }
            SettingsDetailFragment.this.mDeviceResManager.a("PREFERENCE_KEY_GAPLESS_PLAYBACK", false, true);
            if (SettingsDetailFragment.this.a != null) {
                SettingsDetailFragment.this.a.setEnabled(false);
            }
        }
    };
    private OnCheckedChangeListener M = new OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            SettingsDetailFragment.this.mDeviceResManager.a("PREFERENCE_KEY_SOCIAL_PRIVATE_SESSION", z, false);
            if (z) {
                SettingsDetailFragment.this.y.setChecked(false);
            }
        }
    };
    private OnCheckedChangeListener N = new OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            SettingsDetailFragment.this.mDeviceResManager.b("PREFERENCE_KEY_POST_TO_FACEBOOK", false);
            if (z) {
                SettingsDetailFragment.this.x.setChecked(false);
                g.a().a(SettingsDetailFragment.this.getActivity(), new a() {
                    public String OnAuthrizationSuccess() {
                        SettingsDetailFragment.this.mDeviceResManager.a("PREFERENCE_KEY_POST_TO_FACEBOOK", true, false);
                        SettingsDetailFragment.this.y.setChecked(true);
                        return null;
                    }

                    public void OnAuthorizationFailed(GraphResponse graphResponse, LOGIN_STATUS login_status) {
                        SettingsDetailFragment.this.y.setChecked(false);
                        aj.a().a(SettingsDetailFragment.this.mContext, SettingsDetailFragment.this.getString(R.string.error_while_logging_with_facebook));
                    }
                }, false);
            }
        }
    };
    private boolean O = false;
    private OnCheckedChangeListener P = new OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            SettingsDetailFragment.this.mDeviceResManager.a("PREFERENCE_KEY_NOTIFICATION_MUSIC_RECOMMENDATIONS", z, false);
            SettingsDetailFragment.this.O = true;
            SettingsDetailFragment.this.a("Music Recommendations", z);
            MoEngage.getInstance().MoSetUserAttributeBoolean("MusicRecommendations", z);
            Util.b("music_recommendations", z ? "1" : "0");
        }
    };
    private OnCheckedChangeListener Q = new OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            SettingsDetailFragment.this.mDeviceResManager.a("PREFERENCE_KEY_NOTIFICATION_FAVORITE_PLAYLIST", z, false);
            SettingsDetailFragment.this.O = true;
            SettingsDetailFragment.this.a("Someone favorites", z);
            MoEngage.getInstance().MoSetUserAttributeBoolean("FavoritePlaylist", z);
            Util.b("mark_fav_playlist", z ? "1" : "0");
        }
    };
    private OnCheckedChangeListener R = new OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            SettingsDetailFragment.this.mDeviceResManager.a("PREFERENCE_KEY_NOTIFICATION_FOLLOW_UPDATES", z, false);
            SettingsDetailFragment.this.O = true;
            SettingsDetailFragment.this.a("Someone follows", z);
            MoEngage.getInstance().MoSetUserAttributeBoolean("FollowsMe", z);
            Util.b("can_follow", z ? "1" : "0");
        }
    };
    private OnCheckedChangeListener S = new OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (!z || ap.a().o()) {
                if (z && SettingsDetailFragment.this.getArguments().getBoolean("isFromAutoSyncPopup")) {
                    u.a().a("Auto Sync", "Auto Sync Pop Up", "Activated");
                }
                SettingsDetailFragment.this.mDeviceResManager.a("PREFERENCE_KEY_SETTINGS_AUTO_SYNC_V5", z, true);
                Util.b("auto_sync", z ? "1" : "0");
                return;
            }
            SettingsDetailFragment.this.r.setChecked(false);
            SettingsDetailFragment.this.b.a(SettingsDetailFragment.this.getString(R.string.gaana_plus_feature), SettingsDetailFragment.this.getString(R.string.you_need_to_a_gaana_plus_user_to_access_this_feature), Boolean.valueOf(true), SettingsDetailFragment.this.getString(R.string.tell_me_more), SettingsDetailFragment.this.getString(R.string.cancel), new b() {
                public void onCancelListner() {
                }

                public void onOkListner(String str) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("KEY_SETTINGS", 1);
                    BaseGaanaFragment settingsDetailFragment = new SettingsDetailFragment();
                    settingsDetailFragment.setArguments(bundle);
                    ((GaanaActivity) SettingsDetailFragment.this.mContext).displayFragment(settingsDetailFragment);
                }
            });
        }
    };
    private OnCheckedChangeListener T = new OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            if (!z || ap.a().o()) {
                SettingsDetailFragment.this.mDeviceResManager.a("PREFERENCE_KEY_OFFLINE_MODE", z, false);
                if (z) {
                    SettingsDetailFragment.this.mDeviceResManager.a(System.currentTimeMillis(), "PREFERENCE_KEY_OFFLINE_MODE_START_TIME", true);
                    SettingsDetailFragment.this.mDeviceResManager.a(System.currentTimeMillis(), "PREFERENCE_KEY_OFFLINE_MODE_LAST_REMINDER_TIME", true);
                    SettingsDetailFragment.this.mAppState.setAppInOfflineMode(true);
                    DownloadManager.c().e();
                } else {
                    SettingsDetailFragment.this.mDeviceResManager.a(-1, "PREFERENCE_KEY_OFFLINE_MODE_START_TIME", true);
                    SettingsDetailFragment.this.mDeviceResManager.a(-1, "PREFERENCE_KEY_OFFLINE_MODE_LAST_REMINDER_TIME", true);
                    SettingsDetailFragment.this.mAppState.setAppInOfflineMode(false);
                    DownloadManager.c().d();
                    PlaylistSyncManager.getInstance().performSync();
                    FavouriteSyncManager.getInstance().performSync(new l.g() {
                        public void favouriteSyncCompleted() {
                        }
                    });
                }
                SettingsDetailFragment.this.k();
                if (compoundButton.isPressed()) {
                    ((GaanaActivity) SettingsDetailFragment.this.mContext).updateNavigationListView();
                    return;
                }
                return;
            }
            SettingsDetailFragment.this.m.setChecked(false);
            SettingsDetailFragment.this.b.a(SettingsDetailFragment.this.getString(R.string.gaana_plus_feature), SettingsDetailFragment.this.getString(R.string.you_need_to_a_gaana_plus_user_to_access_this_feature), Boolean.valueOf(true), SettingsDetailFragment.this.getString(R.string.tell_me_more), SettingsDetailFragment.this.getString(R.string.cancel), new b() {
                public void onCancelListner() {
                }

                public void onOkListner(String str) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("KEY_SETTINGS", 1);
                    BaseGaanaFragment settingsDetailFragment = new SettingsDetailFragment();
                    settingsDetailFragment.setArguments(bundle);
                    ((GaanaActivity) SettingsDetailFragment.this.mContext).displayFragment(settingsDetailFragment);
                }
            });
        }
    };
    private OnCheckedChangeListener U = new OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            SettingsDetailFragment.this.n.setChecked(z);
            SettingsDetailFragment.this.mAppState.setAppInDataSaveMode(z);
            SettingsDetailFragment.this.mDeviceResManager.a("PREFERENCE_KEY_DATA_SAVE_MODE", z, false);
            Util.b("data_save_mode", z ? "1" : "0");
            if (z) {
                SettingsDetailFragment.this.mDeviceResManager.a("PREFERENCE_LAST_DOWNLOAD_QUALITY_BEFORE_DATA_SAVE_MODE", SettingsDetailFragment.this.mDeviceResManager.b("PREFERENCE_KEY_SYNC_QUALITY", 0, true), true);
                SettingsDetailFragment.this.mDeviceResManager.a("PREFERENCE_KEY_SYNC_QUALITY", 0, true);
                Util.b("download_quality", "0");
                SettingsDetailFragment.this.mDeviceResManager.a("PREFERENCE_LAST_STREAMING_QUALITY_BEFORE_DATA_SAVE_MODE", SettingsDetailFragment.this.mDeviceResManager.b("PREFERENCE_KEY_STREAMING_QUALITY", 10000, false), false);
                SettingsDetailFragment.this.mDeviceResManager.a("PREFERENCE_KEY_STREAMING_QUALITY", 10000, false);
            } else {
                int b = SettingsDetailFragment.this.mDeviceResManager.b("PREFERENCE_LAST_DOWNLOAD_QUALITY_BEFORE_DATA_SAVE_MODE", 0, true);
                SettingsDetailFragment.this.mDeviceResManager.a("PREFERENCE_KEY_SYNC_QUALITY", b, true);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("");
                stringBuilder.append(b);
                Util.b("download_quality", stringBuilder.toString());
                SettingsDetailFragment.this.mDeviceResManager.a("PREFERENCE_KEY_STREAMING_QUALITY", SettingsDetailFragment.this.mDeviceResManager.b("PREFERENCE_LAST_STREAMING_QUALITY_BEFORE_DATA_SAVE_MODE", 10000, false), false);
            }
            PlayerManager.a I = PlayerManager.a(SettingsDetailFragment.this.mContext).I();
            if (I != null) {
                I.j();
            }
            if (compoundButton.isPressed()) {
                ((GaanaActivity) SettingsDetailFragment.this.mContext).updateNavigationListView();
            }
        }
    };
    private OnCheckedChangeListener V = new OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            SettingsDetailFragment.this.o.setChecked(z);
            SettingsDetailFragment.this.mAppState.setIsEndlessPlayback(z);
            SettingsDetailFragment.this.mDeviceResManager.a("PREFERENCE_KEY_ENDLESS_PLAYBACK", z, false);
            Util.b("recommend_song_queue", z ? "1" : "0");
            String str = "On";
            if (!z) {
                str = "Off";
            }
            u.a().a("CF TRACK", "Playback Settings - Endless Playback", str);
        }
    };
    private OnCheckedChangeListener W = new OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            SettingsDetailFragment.this.p.setChecked(z);
            SettingsDetailFragment.this.mAppState.setLyricsDisplay(z);
            SettingsDetailFragment.this.mDeviceResManager.a("PREFERENCE_LYRICS_DISPLAY", z, false);
        }
    };
    private OnCheckedChangeListener X = new OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            SettingsDetailFragment.this.q.setChecked(z);
            SettingsDetailFragment.this.mAppState.setIsVideoAutoplay(z);
            SettingsDetailFragment.this.mDeviceResManager.a("PREFERENCE_VIDEO_AUTOPLAY", z, true);
            LocalBroadcastManager.getInstance(GaanaApplication.getContext()).sendBroadcast(new Intent("broadcast_videoautoplay_status_changed"));
            u.a().a("Settings", "VideoAutoPlay", z ? "SwitchedOn" : "SwitchedOff");
        }
    };
    private OnCheckedChangeListener Y = new OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            SettingsDetailFragment.this.mDeviceResManager.a("PREFERENCE_KEY_AUTO_DOWNLOAD", z, true);
            if (z) {
                Util.b("smart_download", "1");
            } else {
                Util.b("smart_download", "0");
            }
        }
    };
    private OnCheckedChangeListener Z = new OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton compoundButton, final boolean z) {
            if (z && !ap.a().j()) {
                SettingsDetailFragment.this.mDeviceResManager.a("PREFERENCE_KEY_NIGHT_DATA_CONNECTION", false, true);
                Util.b("download_over_2G3G", "0");
                SettingsDetailFragment.this.b.a(SettingsDetailFragment.this.getString(R.string.gaana_plus_feature), SettingsDetailFragment.this.getString(R.string.you_need_to_a_gaana_plus_user_to_access_this_feature), Boolean.valueOf(true), SettingsDetailFragment.this.getString(R.string.tell_me_more), SettingsDetailFragment.this.getString(R.string.cancel), new b() {
                    public void onCancelListner() {
                    }

                    public void onOkListner(String str) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("KEY_SETTINGS", 1);
                        BaseGaanaFragment settingsDetailFragment = new SettingsDetailFragment();
                        settingsDetailFragment.setArguments(bundle);
                        ((GaanaActivity) SettingsDetailFragment.this.mContext).displayFragment(settingsDetailFragment);
                    }
                });
            } else if (z) {
                ((BaseActivity) SettingsDetailFragment.this.mContext).sendGAEvent("Download Settings", "Download over Data Settings", "Enable");
                if (!SettingsDetailFragment.this.mDeviceResManager.b("PREFERENCE_KEY_SYNC_OVER_DATA_CONNECTION", false, true)) {
                    SettingsDetailFragment.this.b.a(SettingsDetailFragment.this.getString(R.string.gaana), SettingsDetailFragment.this.getString(R.string.are_you_sure_you_want_to_sync_over_2g_or_3g), Boolean.valueOf(true), SettingsDetailFragment.this.getString(R.string.yes), SettingsDetailFragment.this.getString(R.string.no), new b() {
                        public void onOkListner(String str) {
                            ((BaseActivity) SettingsDetailFragment.this.mContext).sendGAEvent("Download Settings", "Download over Data Popup", "Yes");
                            SettingsDetailFragment.this.mDeviceResManager.a("PREFERENCE_KEY_SYNC_OVER_DATA_CONNECTION", z, true);
                            Util.b("download_over_2G3G", "1");
                            boolean b = SettingsDetailFragment.this.mDeviceResManager.b("PREFERENCE_KEY_SCHEDULE_DOWNLOAD_ENABLED", false, true);
                            SettingsDetailFragment.this.t.setChecked(b);
                            SettingsDetailFragment.this.t.setEnabled(true);
                            SettingsDetailFragment.this.t.setOnCheckedChangeListener(SettingsDetailFragment.this.aa);
                            if (b) {
                                SettingsDetailFragment.this.mDeviceResManager.a("PREFERENCE_KEY_NIGHT_DATA_CONNECTION", true, true);
                                if (SettingsDetailFragment.this.D != null) {
                                    SettingsDetailFragment.this.D.setVisibility(0);
                                }
                            } else {
                                SettingsDetailFragment.this.mDeviceResManager.a("PREFERENCE_KEY_NIGHT_DATA_CONNECTION", false, true);
                            }
                            DownloadManager.c().d();
                        }

                        public void onCancelListner() {
                            ((BaseActivity) SettingsDetailFragment.this.mContext).sendGAEvent("Download Settings", "Download over Data Popup", "No");
                            SettingsDetailFragment.this.u.setChecked(false);
                            SettingsDetailFragment.this.t.setChecked(false);
                            SettingsDetailFragment.this.t.setEnabled(false);
                            SettingsDetailFragment.this.t.setOnCheckedChangeListener(null);
                            if (SettingsDetailFragment.this.D != null) {
                                SettingsDetailFragment.this.D.setVisibility(8);
                            }
                            SettingsDetailFragment.this.mDeviceResManager.a("PREFERENCE_KEY_NIGHT_DATA_CONNECTION", false, true);
                            Util.b("download_over_2G3G", "0");
                        }
                    });
                }
            } else {
                ((BaseActivity) SettingsDetailFragment.this.mContext).sendGAEvent("Download Settings", "Download over Data Settings", "Disable");
                SettingsDetailFragment.this.mDeviceResManager.a("PREFERENCE_KEY_SYNC_OVER_DATA_CONNECTION", z, true);
                Util.b("download_over_2G3G", "0");
                SettingsDetailFragment.this.t.setChecked(false);
                SettingsDetailFragment.this.t.setEnabled(false);
                SettingsDetailFragment.this.t.setOnCheckedChangeListener(null);
                if (SettingsDetailFragment.this.D != null) {
                    SettingsDetailFragment.this.D.setVisibility(8);
                }
                SettingsDetailFragment.this.mDeviceResManager.a("PREFERENCE_KEY_NIGHT_DATA_CONNECTION", false, true);
                if (Util.k(GaanaApplication.getContext()) == 0) {
                    DownloadManager.c().e();
                }
            }
        }
    };
    SeekBar a;
    private OnCheckedChangeListener aa = new OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton compoundButton, final boolean z) {
            if (z && !ap.a().j()) {
                SettingsDetailFragment.this.mDeviceResManager.a("PREFERENCE_KEY_NIGHT_DATA_CONNECTION", false, true);
                SettingsDetailFragment.this.b.a(SettingsDetailFragment.this.getString(R.string.gaana_plus_feature), SettingsDetailFragment.this.getString(R.string.you_need_to_a_gaana_plus_user_to_access_this_feature), Boolean.valueOf(true), SettingsDetailFragment.this.getString(R.string.tell_me_more), SettingsDetailFragment.this.getString(R.string.cancel), new b() {
                    public void onCancelListner() {
                    }

                    public void onOkListner(String str) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("KEY_SETTINGS", 1);
                        BaseGaanaFragment settingsDetailFragment = new SettingsDetailFragment();
                        settingsDetailFragment.setArguments(bundle);
                        ((GaanaActivity) SettingsDetailFragment.this.mContext).displayFragment(settingsDetailFragment);
                    }
                });
            } else if (z) {
                ((BaseActivity) SettingsDetailFragment.this.mContext).sendGAEvent("Download Settings", "Schedule Downloads Settings", "Enable");
                boolean b = SettingsDetailFragment.this.mDeviceResManager.b("PREFERENCE_KEY_SYNC_OVER_DATA_CONNECTION", false, true);
                boolean b2 = SettingsDetailFragment.this.mDeviceResManager.b("PREFERENCE_KEY_SCHEDULE_DOWNLOAD_ENABLED", false, true);
                if (b && !b2) {
                    SettingsDetailFragment.this.b.a(SettingsDetailFragment.this.getString(R.string.gaana), SettingsDetailFragment.this.getString(R.string.are_you_sure_you_want_to_schedule_downloads), Boolean.valueOf(true), SettingsDetailFragment.this.getString(R.string.yes), SettingsDetailFragment.this.getString(R.string.no), new b() {
                        public void onOkListner(String str) {
                            ((BaseActivity) SettingsDetailFragment.this.mContext).sendGAEvent("Download Settings", "Schedule Downloads Popup", "Yes");
                            SettingsDetailFragment.this.mDeviceResManager.a("PREFERENCE_KEY_SCHEDULE_DOWNLOAD_ENABLED", z, true);
                            Util.b("schedule_downloads", "1");
                            if (SettingsDetailFragment.this.D != null) {
                                SettingsDetailFragment.this.D.setVisibility(0);
                            }
                            SettingsDetailFragment.this.mDeviceResManager.a("PREFERENCE_KEY_NIGHT_DATA_CONNECTION", true, true);
                        }

                        public void onCancelListner() {
                            ((BaseActivity) SettingsDetailFragment.this.mContext).sendGAEvent("Download Settings", "Schedule Downloads Popup", "No");
                            SettingsDetailFragment.this.t.setChecked(false);
                            if (SettingsDetailFragment.this.D != null) {
                                SettingsDetailFragment.this.D.setVisibility(8);
                            }
                            SettingsDetailFragment.this.mDeviceResManager.a("PREFERENCE_KEY_NIGHT_DATA_CONNECTION", false, true);
                        }
                    });
                } else if (b && b2) {
                    SettingsDetailFragment.this.mDeviceResManager.a("PREFERENCE_KEY_SCHEDULE_DOWNLOAD_ENABLED", z, true);
                    Util.b("schedule_downloads", "1");
                    if (SettingsDetailFragment.this.D != null) {
                        SettingsDetailFragment.this.D.setVisibility(0);
                    }
                    SettingsDetailFragment.this.mDeviceResManager.a("PREFERENCE_KEY_NIGHT_DATA_CONNECTION", true, true);
                } else {
                    SettingsDetailFragment.this.t.setChecked(false);
                    if (SettingsDetailFragment.this.D != null) {
                        SettingsDetailFragment.this.D.setVisibility(8);
                    }
                    SettingsDetailFragment.this.mDeviceResManager.a("PREFERENCE_KEY_NIGHT_DATA_CONNECTION", false, true);
                }
            } else {
                ((BaseActivity) SettingsDetailFragment.this.mContext).sendGAEvent("Download Settings", "Schedule Downloads Settings", "Disable");
                SettingsDetailFragment.this.mDeviceResManager.a("PREFERENCE_KEY_SCHEDULE_DOWNLOAD_ENABLED", z, true);
                Util.b("schedule_downloads", "0");
                if (SettingsDetailFragment.this.D != null) {
                    SettingsDetailFragment.this.D.setVisibility(8);
                }
                SettingsDetailFragment.this.mDeviceResManager.a("PREFERENCE_KEY_NIGHT_DATA_CONNECTION", false, true);
            }
        }
    };
    private OnCheckedChangeListener ab = new OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton compoundButton, final boolean z) {
            if (z && !ap.a().j()) {
                SettingsDetailFragment.this.b.a(SettingsDetailFragment.this.getString(R.string.gaana_plus_feature), SettingsDetailFragment.this.getString(R.string.you_need_to_a_gaana_plus_user_to_access_this_feature), Boolean.valueOf(true), SettingsDetailFragment.this.getString(R.string.tell_me_more), SettingsDetailFragment.this.getString(R.string.cancel), new b() {
                    public void onCancelListner() {
                    }

                    public void onOkListner(String str) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("KEY_SETTINGS", 1);
                        BaseGaanaFragment settingsDetailFragment = new SettingsDetailFragment();
                        settingsDetailFragment.setArguments(bundle);
                        ((GaanaActivity) SettingsDetailFragment.this.mContext).displayFragment(settingsDetailFragment);
                    }
                });
            } else if (!z) {
                SettingsDetailFragment.this.mDeviceResManager.a("PREFERENCE_KEY_DOWNLOAD_SYNC_OVER_DATA_CONNECTION", z, true);
                Util.b("sync_over_2G3G", "0");
                if (Util.k(GaanaApplication.getContext()) == 0) {
                    DownloadManager.c().e();
                }
            } else if (!SettingsDetailFragment.this.mDeviceResManager.b("PREFERENCE_KEY_DOWNLOAD_SYNC_OVER_DATA_CONNECTION", false, true)) {
                SettingsDetailFragment.this.b.a(SettingsDetailFragment.this.getString(R.string.gaana), SettingsDetailFragment.this.getString(R.string.are_you_sure_you_want_to_sync_over_2g_or_3g), Boolean.valueOf(true), SettingsDetailFragment.this.getString(R.string.yes), SettingsDetailFragment.this.getString(R.string.no), new b() {
                    public void onOkListner(String str) {
                        SettingsDetailFragment.this.mDeviceResManager.a("PREFERENCE_KEY_DOWNLOAD_SYNC_OVER_DATA_CONNECTION", z, true);
                        Util.b("sync_over_2G3G", "1");
                    }

                    public void onCancelListner() {
                        SettingsDetailFragment.this.w.setChecked(false);
                    }
                });
            }
        }
    };
    private boolean ac = false;
    private boolean ad;
    private boolean ae;
    protected f b;
    String c = "";
    TextView d = null;
    SwitchCompat e = null;
    private int f;
    private String g;
    private boolean h = false;
    private LinearLayout i;
    private LayoutInflater j;
    private int k;
    private View l;
    private SwitchCompat m;
    private SwitchCompat n;
    private SwitchCompat o;
    private SwitchCompat p;
    private SwitchCompat q;
    private SwitchCompat r;
    private TextView s;
    private SwitchCompat t;
    private SwitchCompat u;
    private SwitchCompat v;
    private SwitchCompat w;
    private SwitchCompat x;
    private SwitchCompat y;
    private SwitchCompat z;

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.F == null) {
            super.onCreateView(layoutInflater, viewGroup, bundle);
            this.b = new f(this.mContext);
            this.k = getArguments().getInt("KEY_SETTINGS", 0);
            this.ad = getArguments().getBoolean("NOT_DOWNLOAD", true);
            a(viewGroup);
            updateView();
            TypedValue typedValue = new TypedValue();
            this.mContext.getTheme().resolveAttribute(R.attr.second_line_color, typedValue, true);
            this.f = typedValue.data;
        } else if (this.k == 1 && this.ae) {
            ((ViewGroup) this.F).removeAllViews();
            a(viewGroup);
        }
        ((GaanaActivity) this.mContext).hideThemeBackground(false);
        return this.F;
    }

    public int a() {
        return this.k;
    }

    private void a(ViewGroup viewGroup) {
        this.j = LayoutInflater.from(this.mContext);
        String str = "";
        int i = this.k;
        if (i == 6) {
            this.F = setContentView(R.layout.settings, viewGroup);
            this.c = getString(R.string.coupons);
            this.g = "REDEEM_COUPON_UI_SCREEN";
            String str2 = null;
            if (getArguments() != null) {
                str2 = getArguments().getString("TAG_SETTINGS_REDEEM_COUPON_CODE");
            }
            a(str2);
            str = "settings - redeem screen";
        } else if (i == 9) {
            this.F = setContentView(R.layout.settings, viewGroup);
            this.c = getString(R.string.about_this_app);
            this.g = "ABOUT_US_SCREEN";
            f();
            str = "settings - about this app screen";
        } else if (i == 14) {
            this.F = setContentView(R.layout.settings, viewGroup);
            this.c = getString(R.string.title_songs_language);
            this.g = "LANGUAGE_SETTINGS_UI_SCREEN";
            m();
            str = "settings - languages screen";
            MoEngage.getInstance().reportSectionViewedEvent("LanguageSection");
        } else if (i == 141) {
            this.F = setContentView(R.layout.settings, viewGroup);
            this.c = getString(R.string.select_theme);
            this.g = "THEME_SETTINGS_UI_SCREEN";
            l();
            str = "settings - theme screen";
            MoEngage.getInstance().reportSectionViewedEvent("ThemeSection");
        } else if (i != 203) {
            switch (i) {
                case 0:
                    this.F = setContentView(R.layout.settings, viewGroup);
                    this.c = getString(R.string.playback);
                    this.g = "PLAYBACK_SETTINGS_SCREEN";
                    g();
                    str = "settings - playback settings  screen";
                    break;
                case 1:
                    this.F = setContentView(R.layout.settings, viewGroup);
                    this.h = getArguments().getBoolean("international_onboarding", false);
                    this.c = getString(R.string.purchase_gaana_plus);
                    if (this.h) {
                        this.c = getString(R.string.get_gaana_plus);
                    }
                    this.g = "PURCHASE_GAANA_UI_SCREEN";
                    getArguments().getBoolean("SHOW_PRICE_DIALOGUE", false);
                    this.ae = getArguments().getBoolean("LAUNCH_GAANA_PLUS", false);
                    b(getArguments().getString("SHOW_PRICE_DIALOGUE_TYPE"), this.ae);
                    str = "SubscriptionScreen";
                    updateView();
                    break;
                case 2:
                    this.F = setContentView(R.layout.settings, viewGroup);
                    this.c = getString(R.string.notifications);
                    this.g = "NOTIFICATIONS_SCREEN";
                    h();
                    str = "settings - notifications  screen";
                    break;
                case 3:
                    this.F = setContentView(R.layout.settings, viewGroup);
                    this.c = getString(R.string.manage_social);
                    this.g = "SOCIAL_UI_SCREEN";
                    j();
                    str = "settings - manage social screen";
                    break;
                default:
                    switch (i) {
                        case 20:
                            this.F = setContentView(R.layout.settings, viewGroup);
                            this.c = getString(R.string.sleep_timer);
                            this.g = "SLEEP_TIMER_SCREEN";
                            d();
                            str = "settings - sleep timer screen";
                            break;
                        case 21:
                            this.F = setContentView(R.layout.settings, viewGroup);
                            this.c = getString(R.string.user_data);
                            this.g = "USERDATA_SCREEN";
                            i();
                            str = "settings - userdata  screen";
                            break;
                    }
                    break;
            }
        } else {
            this.F = setContentView(R.layout.settings, viewGroup);
            this.c = getString(R.string.title_sync_downloads);
            this.g = "LANGUAGE_SETTINGS_UI_SCREEN";
            n();
            str = "settings - social preferences screen";
        }
        setGAScreenName("", str);
    }

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }

    private void d() {
        this.i = (LinearLayout) this.F.findViewById(R.id.settingsContainer);
        this.i.removeAllViews();
        View a = a("", null);
        this.d = (TextView) a.findViewById(R.id.headerText);
        this.d.setTextSize(16.0f);
        this.e = (SwitchCompat) a.findViewById(R.id.switchButton);
        if (((GaanaActivity) this.mContext).getSleepTime() > 0) {
            int currentSleepTime = ((GaanaActivity) this.mContext).getCurrentSleepTime();
            a(currentSleepTime / 60, currentSleepTime % 60);
            this.e.setChecked(true);
        } else {
            this.e.setEnabled(false);
            a(0, 0);
        }
        this.e.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (!z) {
                    ((GaanaActivity) SettingsDetailFragment.this.mContext).cancelSleepTimer();
                    SettingsDetailFragment.this.e.setEnabled(false);
                    SettingsDetailFragment.this.a(0, 0);
                }
            }
        });
        e();
        this.i.addView(a);
        this.i.addView(v());
        this.i.addView(a(15));
        this.i.addView(a(30));
        this.i.addView(a(60));
        this.i.addView(v());
    }

    private View a(int i) {
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        int dimension = (int) (getResources().getDimension(R.dimen.sleep_timer_item_margin) / getResources().getDisplayMetrics().density);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(i);
        stringBuilder.append(" ");
        stringBuilder.append(getResources().getString(R.string.minutes));
        TextView textView = (TextView) b(stringBuilder.toString());
        textView.setGravity(3);
        textView.setLayoutParams(layoutParams);
        textView.setTag(Integer.valueOf(i));
        textView.setPadding(dimension, dimension, dimension, dimension);
        textView.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (PlayerManager.a(SettingsDetailFragment.this.mContext).i() == null) {
                    Toast.makeText(SettingsDetailFragment.this.mContext, R.string.sleep_timer_message, 0).show();
                    return;
                }
                int parseInt = Integer.parseInt(String.valueOf(((TextView) view).getTag()));
                SettingsDetailFragment.this.a(parseInt, 0);
                SettingsDetailFragment.this.e.setEnabled(true);
                SettingsDetailFragment.this.e.setChecked(true);
                if (PlayerStatus.a(SettingsDetailFragment.this.mContext).a().equals(PlayerStates.PAUSED) || PlayerStatus.a(SettingsDetailFragment.this.mContext).a().equals(PlayerStates.STOPPED)) {
                    o.c(SettingsDetailFragment.this.mContext, PauseReasons.MEDIA_BUTTON_TAP);
                } else if (PlayerStatus.a(SettingsDetailFragment.this.mContext).a().equals(PlayerStates.LOADING)) {
                    ((GaanaActivity) SettingsDetailFragment.this.mContext).setSleepTimerOnPlayerPrepared(parseInt);
                    return;
                }
                ((GaanaActivity) SettingsDetailFragment.this.mContext).setSleepTimer(parseInt);
            }
        });
        return textView;
    }

    private void e() {
        ((GaanaActivity) this.mContext).setSleepTimerListener(new ISleepTimerListener() {
            public void SleepTimerCompleted() {
                SettingsDetailFragment.this.a(0, 0);
                SettingsDetailFragment.this.e.setEnabled(false);
                SettingsDetailFragment.this.e.setChecked(false);
            }

            public void SleepTimerTick(int i, int i2) {
                SettingsDetailFragment.this.a(i, i2);
            }
        });
    }

    private void a(int i, int i2) {
        String valueOf = String.valueOf(i);
        String valueOf2 = String.valueOf(i2);
        if (valueOf.length() == 1) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("0");
            stringBuilder.append(i);
            valueOf = stringBuilder.toString();
        }
        if (valueOf2.length() == 1) {
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("0");
            stringBuilder2.append(i2);
            valueOf2 = stringBuilder2.toString();
        }
        if (this.d != null) {
            TextView textView = this.d;
            StringBuilder stringBuilder3 = new StringBuilder();
            stringBuilder3.append(valueOf);
            stringBuilder3.append(":");
            stringBuilder3.append(valueOf2);
            textView.setText(stringBuilder3.toString());
        }
    }

    private void f() {
        this.i = (LinearLayout) this.F.findViewById(R.id.settingsContainer);
        this.i.removeAllViews();
        this.i.addView(a(getString(R.string.privacy_policy), 103));
        this.i.addView(s());
        this.i.addView(a(getString(R.string.terms_and_conditions), 104));
        this.i.addView(s());
        this.i.addView(a(getString(R.string.about_company), 105));
        this.i.addView(s());
    }

    private void a(String str) {
        this.i = (LinearLayout) this.F.findViewById(R.id.settingsContainer);
        this.i.removeAllViews();
        RedeemCouponItemView redeemCouponItemView = new RedeemCouponItemView(this.mContext, this);
        this.i.addView(redeemCouponItemView.getPoplatedView(null));
        if (str != null) {
            redeemCouponItemView.sendRedeemCouponRequest(str);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x03da  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x03cc  */
    private void g() {
        /*
        r17 = this;
        r0 = r17;
        r1 = r0.F;
        r2 = 2131298369; // 0x7f090841 float:1.821471E38 double:1.053001305E-314;
        r1 = r1.findViewById(r2);
        r1 = (android.widget.LinearLayout) r1;
        r0.i = r1;
        r1 = r0.i;
        r1.removeAllViews();
        r1 = 2131821422; // 0x7f11036e float:1.9275587E38 double:1.0532597277E-314;
        r1 = r0.getString(r1);
        r1 = r0.h(r1);
        r2 = r17.getResources();
        r3 = 2131165268; // 0x7f070054 float:1.7944748E38 double:1.0529355445E-314;
        r2 = r2.getDimension(r3);
        r2 = (int) r2;
        r4 = r17.getResources();
        r4 = r4.getDimension(r3);
        r4 = (int) r4;
        r5 = 0;
        r1.setPadding(r2, r5, r5, r4);
        r2 = r0.i;
        r2.addView(r1);
        r1 = 2131821424; // 0x7f110370 float:1.927559E38 double:1.0532597287E-314;
        r1 = r0.getString(r1);
        r2 = 2131822552; // 0x7f1107d8 float:1.9277879E38 double:1.053260286E-314;
        r2 = r0.getString(r2);
        r1 = r0.a(r1, r2, r5);
        r2 = r1.getLayoutParams();
        r4 = -2;
        r2.height = r4;
        r2 = r17.getResources();
        r6 = 2131165278; // 0x7f07005e float:1.7944769E38 double:1.0529355495E-314;
        r2 = r2.getDimension(r6);
        r2 = (int) r2;
        r7 = r17.getResources();
        r8 = 2131165269; // 0x7f070055 float:1.794475E38 double:1.052935545E-314;
        r7 = r7.getDimension(r8);
        r7 = (int) r7;
        r9 = r17.getResources();
        r9 = r9.getDimension(r8);
        r9 = (int) r9;
        r10 = r17.getResources();
        r10 = r10.getDimension(r8);
        r10 = (int) r10;
        r1.setPadding(r2, r7, r9, r10);
        r2 = r0.mDeviceResManager;
        r7 = "PREFERENCE_KEY_GAPLESS_PLAYBACK";
        r9 = 1;
        r2 = r2.b(r7, r5, r9);
        r7 = 2131297296; // 0x7f090410 float:1.8212533E38 double:1.053000775E-314;
        r10 = r1.findViewById(r7);
        r10 = (android.widget.TextView) r10;
        r11 = 1098907648; // 0x41800000 float:16.0 double:5.42932517E-315;
        r10.setTextSize(r11);
        r10 = 2131298536; // 0x7f0908e8 float:1.8215048E38 double:1.0530013877E-314;
        r12 = r1.findViewById(r10);
        r12 = (android.support.v7.widget.SwitchCompat) r12;
        r12.setChecked(r2);
        r13 = com.constants.Constants.C;
        if (r13 != r9) goto L_0x00b0;
    L_0x00aa:
        r13 = r0.K;
        r12.setOnCheckedChangeListener(r13);
        goto L_0x00b5;
    L_0x00b0:
        r13 = r0.L;
        r12.setOnCheckedChangeListener(r13);
    L_0x00b5:
        r12 = r0.i;
        r12.addView(r1);
        r12 = r0.i;
        r13 = r17.v();
        r12.addView(r13);
        r12 = 2131821050; // 0x7f1101fa float:1.9274832E38 double:1.053259544E-314;
        r12 = r0.getString(r12);
        r13 = 2131822884; // 0x7f110924 float:1.9278552E38 double:1.05326045E-314;
        r13 = r0.getString(r13);
        r14 = 2131821367; // 0x7f110337 float:1.9275475E38 double:1.0532597005E-314;
        r14 = r0.getString(r14);
        r12 = r0.a(r12, r13, r14);
        r13 = r12.findViewById(r7);
        r13 = (android.widget.TextView) r13;
        r13.setTextSize(r11);
        r13 = 2131298347; // 0x7f09082b float:1.8214665E38 double:1.0530012943E-314;
        r13 = r12.findViewById(r13);
        r13 = (android.widget.SeekBar) r13;
        r0.a = r13;
        r13 = 2131298361; // 0x7f090839 float:1.8214693E38 double:1.053001301E-314;
        r13 = r12.findViewById(r13);
        r13 = (android.widget.TextView) r13;
        r0.C = r13;
        r13 = r0.a;
        r14 = 15;
        r13.setMax(r14);
        r13 = r0.mDeviceResManager;
        r14 = "PREFERENCE_KEY_CROSSFADE_VALUE";
        r13 = r13.b(r14, r5, r9);
        r14 = r0.C;
        r15 = new java.lang.StringBuilder;
        r15.<init>();
        r10 = "(";
        r15.append(r10);
        r15.append(r13);
        r10 = " ";
        r15.append(r10);
        r10 = 2131822351; // 0x7f11070f float:1.927747E38 double:1.0532601867E-314;
        r10 = r0.getString(r10);
        r15.append(r10);
        r10 = ")";
        r15.append(r10);
        r10 = r15.toString();
        r14.setText(r10);
        r10 = r0.a;
        r10.setProgress(r13);
        r10 = r0.a;
        r13 = r0.E;
        r10.setOnSeekBarChangeListener(r13);
        r10 = r0.i;
        r10.addView(r12);
        if (r2 == 0) goto L_0x0151;
    L_0x0147:
        r2 = com.constants.Constants.C;
        if (r2 != r9) goto L_0x0151;
    L_0x014b:
        r2 = r0.a;
        r2.setEnabled(r9);
        goto L_0x0156;
    L_0x0151:
        r2 = r0.a;
        r2.setEnabled(r5);
    L_0x0156:
        r2 = r0.i;
        r10 = r17.v();
        r2.addView(r10);
        r2 = 2131822077; // 0x7f1105fd float:1.9276915E38 double:1.0532600513E-314;
        r2 = r0.getString(r2);
        r2 = r0.h(r2);
        r10 = r17.getResources();
        r10 = r10.getDimension(r3);
        r10 = (int) r10;
        r12 = r17.getResources();
        r3 = r12.getDimension(r3);
        r3 = (int) r3;
        r2.setPadding(r10, r5, r5, r3);
        r3 = r0.i;
        r3.addView(r2);
        r2 = 2131822079; // 0x7f1105ff float:1.927692E38 double:1.0532600523E-314;
        r2 = r0.getString(r2);
        r3 = 2131822078; // 0x7f1105fe float:1.9276917E38 double:1.053260052E-314;
        r3 = r0.getString(r3);
        r2 = r0.a(r2, r3, r9);
        r3 = r2.getLayoutParams();
        r3.height = r4;
        r3 = r17.getResources();
        r3 = r3.getDimension(r6);
        r3 = (int) r3;
        r4 = r17.getResources();
        r4 = r4.getDimension(r8);
        r4 = (int) r4;
        r6 = r17.getResources();
        r6 = r6.getDimension(r8);
        r6 = (int) r6;
        r10 = r17.getResources();
        r8 = r10.getDimension(r8);
        r8 = (int) r8;
        r2.setPadding(r3, r4, r6, r8);
        r3 = r0.mDeviceResManager;
        r4 = "pref_explicit_content";
        r3 = r3.b(r4, r5, r5);
        r1 = r1.findViewById(r7);
        r1 = (android.widget.TextView) r1;
        r1.setTextSize(r11);
        r1 = 2131298536; // 0x7f0908e8 float:1.8215048E38 double:1.0530013877E-314;
        r4 = r2.findViewById(r1);
        r4 = (android.support.v7.widget.SwitchCompat) r4;
        r4.setChecked(r3);
        r1 = new com.fragments.SettingsDetailFragment$15;
        r1.<init>();
        r4.setOnCheckedChangeListener(r1);
        r1 = r0.i;
        r1.addView(r2);
        r1 = r0.i;
        r2 = r17.v();
        r1.addView(r2);
        r1 = r0.i;
        r2 = 2131821068; // 0x7f11020c float:1.9274869E38 double:1.053259553E-314;
        r3 = r0.getString(r2);
        r3 = r0.h(r3);
        r1.addView(r3);
        r1 = r0.getString(r2);
        r2 = 0;
        r1 = r0.a(r1, r2);
        r3 = r1.findViewById(r7);
        r3 = (android.widget.TextView) r3;
        r3.setTextSize(r11);
        r3 = r0.mDeviceResManager;
        r4 = "PREFERENCE_KEY_DATA_SAVE_MODE";
        r3 = r3.b(r4, r5, r5);
        r4 = 2131298536; // 0x7f0908e8 float:1.8215048E38 double:1.0530013877E-314;
        r6 = r1.findViewById(r4);
        r6 = (android.support.v7.widget.SwitchCompat) r6;
        r0.n = r6;
        r4 = r0.n;
        r4.setChecked(r3);
        r3 = r0.n;
        r4 = r0.U;
        r3.setOnCheckedChangeListener(r4);
        r3 = r0.i;
        r3.addView(r1);
        r1 = r0.i;
        r3 = r17.v();
        r1.addView(r3);
        r1 = r0.i;
        r3 = 2131821193; // 0x7f110289 float:1.9275122E38 double:1.0532596145E-314;
        r3 = r0.getString(r3);
        r3 = r0.h(r3);
        r1.addView(r3);
        r1 = 2131822261; // 0x7f1106b5 float:1.9277288E38 double:1.053260142E-314;
        r1 = r0.getString(r1);
        r1 = r0.a(r1, r2);
        r3 = r1.findViewById(r7);
        r3 = (android.widget.TextView) r3;
        r3.setTextSize(r11);
        r3 = r0.mDeviceResManager;
        r4 = "PREFERENCE_KEY_ENDLESS_PLAYBACK";
        r3 = r3.b(r4, r9, r5);
        r4 = 2131298536; // 0x7f0908e8 float:1.8215048E38 double:1.0530013877E-314;
        r6 = r1.findViewById(r4);
        r6 = (android.support.v7.widget.SwitchCompat) r6;
        r0.o = r6;
        r4 = r0.o;
        r4.setChecked(r3);
        r4 = r0.o;
        r6 = r0.V;
        r4.setOnCheckedChangeListener(r6);
        r4 = r0.i;
        r4.addView(r1);
        r1 = com.gaana.application.GaanaApplication.getInstance();
        r1.setIsEndlessPlayback(r3);
        r1 = r0.i;
        r3 = 2131821484; // 0x7f1103ac float:1.9275712E38 double:1.0532597583E-314;
        r3 = r0.getString(r3);
        r3 = r0.h(r3);
        r1.addView(r3);
        r1 = r0.mDeviceResManager;
        r3 = "PREFERENCE_KEY_DOWNLOAD_IMAGE";
        r1 = r1.b(r3, r9, r9);
        r3 = 2131821153; // 0x7f110261 float:1.9275041E38 double:1.053259595E-314;
        r3 = r0.getString(r3);
        r4 = 2130903055; // 0x7f03000f float:1.7412917E38 double:1.052805994E-314;
        r6 = new com.fragments.SettingsDetailFragment$16;
        r6.<init>(r1);
        r1 = r0.a(r3, r4, r1, r6);
        r3 = 2131298702; // 0x7f09098e float:1.8215385E38 double:1.0530014697E-314;
        r3 = r1.findViewById(r3);
        r3 = (android.widget.TextView) r3;
        r3.setTextSize(r11);
        r3 = r0.i;
        r3.addView(r1);
        r1 = r0.i;
        r3 = r17.s();
        r1.addView(r3);
        r1 = r0.i;
        r3 = 2131821801; // 0x7f1104e9 float:1.9276355E38 double:1.053259915E-314;
        r3 = r0.getString(r3);
        r3 = r0.h(r3);
        r1.addView(r3);
        r1 = 2131822415; // 0x7f11074f float:1.92776E38 double:1.0532602183E-314;
        r1 = r0.getString(r1);
        r1 = r0.a(r1, r2);
        r3 = r1.findViewById(r7);
        r3 = (android.widget.TextView) r3;
        r3.setTextSize(r11);
        r3 = 2131298536; // 0x7f0908e8 float:1.8215048E38 double:1.0530013877E-314;
        r4 = r1.findViewById(r3);
        r4 = (android.support.v7.widget.SwitchCompat) r4;
        r0.p = r4;
        r3 = r0.mDeviceResManager;
        r4 = "PREFERENCE_LYRICS_DISPLAY";
        r3 = r3.b(r4, r9, r5);
        r4 = r0.p;
        r4.setChecked(r3);
        r4 = r0.p;
        r6 = r0.W;
        r4.setOnCheckedChangeListener(r6);
        r4 = r0.i;
        r4.addView(r1);
        r1 = com.gaana.application.GaanaApplication.getInstance();
        r1.setLyricsDisplay(r3);
        r1 = r0.i;
        r3 = 2131822806; // 0x7f1108d6 float:1.9278394E38 double:1.0532604115E-314;
        r3 = r0.getString(r3);
        r3 = r0.h(r3);
        r1.addView(r3);
        r1 = r0.mDeviceResManager;
        r3 = "PREFERENCE_VIDEO_AUTOPLAY";
        r1 = r1.b(r3, r9, r9);
        r3 = 2131822805; // 0x7f1108d5 float:1.9278392E38 double:1.053260411E-314;
        r3 = r0.getString(r3);
        r2 = r0.a(r3, r2);
        r3 = r2.findViewById(r7);
        r3 = (android.widget.TextView) r3;
        r3.setTextSize(r11);
        r3 = 2131298536; // 0x7f0908e8 float:1.8215048E38 double:1.0530013877E-314;
        r3 = r2.findViewById(r3);
        r3 = (android.support.v7.widget.SwitchCompat) r3;
        r0.q = r3;
        r3 = r0.q;
        r3.setChecked(r1);
        r3 = r0.q;
        r4 = r0.X;
        r3.setOnCheckedChangeListener(r4);
        r3 = r0.i;
        r3.addView(r2);
        r2 = com.gaana.application.GaanaApplication.getInstance();
        r2.setIsVideoAutoplay(r1);
        r1 = r0.i;
        r2 = r17.v();
        r1.addView(r2);
        r1 = r0.i;
        r2 = 2131822476; // 0x7f11078c float:1.9277725E38 double:1.0532602484E-314;
        r2 = r0.getString(r2);
        r2 = r0.h(r2);
        r1.addView(r2);
        r1 = r0.mDeviceResManager;
        r2 = "PREFERENCE_KEY_STREAMING_QUALITY";
        r3 = com.constants.Constants.s();
        r1 = r1.b(r2, r3, r5);
        r2 = 5;
        r3 = new int[r2];
        r3 = {10004, 10003, 10002, 10001, 10000};
        r4 = r3[r5];
        r6 = 4;
        r7 = 3;
        r8 = 2;
        if (r1 != r4) goto L_0x03ab;
    L_0x03a9:
        r1 = r5;
        goto L_0x03c2;
    L_0x03ab:
        r4 = r3[r9];
        if (r1 != r4) goto L_0x03b1;
    L_0x03af:
        r1 = r9;
        goto L_0x03c2;
    L_0x03b1:
        r4 = r3[r8];
        if (r1 != r4) goto L_0x03b7;
    L_0x03b5:
        r1 = r8;
        goto L_0x03c2;
    L_0x03b7:
        r4 = r3[r7];
        if (r1 != r4) goto L_0x03bd;
    L_0x03bb:
        r1 = r7;
        goto L_0x03c2;
    L_0x03bd:
        r4 = r3[r6];
        if (r1 != r4) goto L_0x03a9;
    L_0x03c1:
        r1 = r6;
    L_0x03c2:
        r4 = com.managers.ap.a();
        r4 = r4.s();
        if (r4 == 0) goto L_0x03da;
    L_0x03cc:
        r4 = r0.mContext;
        r4 = r4.getResources();
        r10 = 2131822516; // 0x7f1107b4 float:1.9277806E38 double:1.053260268E-314;
        r4 = r4.getString(r10);
        goto L_0x03e7;
    L_0x03da:
        r4 = r0.mContext;
        r4 = r4.getResources();
        r10 = 2131822517; // 0x7f1107b5 float:1.9277808E38 double:1.0532602687E-314;
        r4 = r4.getString(r10);
    L_0x03e7:
        r2 = new java.lang.String[r2];
        r10 = r0.mContext;
        r10 = r10.getResources();
        r11 = 2131822515; // 0x7f1107b3 float:1.9277804E38 double:1.0532602677E-314;
        r10 = r10.getString(r11);
        r2[r5] = r10;
        r2[r9] = r4;
        r4 = r0.mContext;
        r4 = r4.getResources();
        r5 = 2131822518; // 0x7f1107b6 float:1.927781E38 double:1.053260269E-314;
        r4 = r4.getString(r5);
        r2[r8] = r4;
        r4 = r0.mContext;
        r4 = r4.getResources();
        r5 = 2131822520; // 0x7f1107b8 float:1.9277814E38 double:1.05326027E-314;
        r4 = r4.getString(r5);
        r2[r7] = r4;
        r4 = r0.mContext;
        r4 = r4.getResources();
        r5 = 2131822519; // 0x7f1107b7 float:1.9277812E38 double:1.0532602697E-314;
        r4 = r4.getString(r5);
        r2[r6] = r4;
        r4 = r17.getActivity();
        r5 = new com.fragments.SettingsDetailFragment$17;
        r5.<init>(r1, r3);
        r1 = r0.a(r4, r2, r1, r5);
        r2 = r0.i;
        r2.addView(r1);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fragments.SettingsDetailFragment.g():void");
    }

    private void a(final AdapterView<?> adapterView, final int i) {
        if (GaanaApplication.sessionHistoryCount > 0) {
            Util.a(new Util.a() {
                public void onErrorResponse(BusinessObject businessObject) {
                }

                public void onRetreivalComplete(CustomCard customCard) {
                    CustomCard customCard2 = customCard;
                    if (customCard.a() == null) {
                        adapterView.setSelected(false);
                        adapterView.setSelection(i);
                        Util.a(SettingsDetailFragment.this.mContext, SettingsDetailFragment.this.mContext.getResources().getString(R.string.subscribe_gaanaplus_hdq_msg), "HDQuality");
                        return;
                    }
                    d a = customCard.a().a();
                    long intValue = (long) a.c().intValue();
                    int intValue2 = a.d().intValue();
                    String e = customCard.b().e();
                    int intValue3 = customCard.a().b().a().intValue();
                    long intValue4 = (long) customCard.a().b().b().intValue();
                    long intValue5 = (long) customCard.a().b().c().intValue();
                    d dVar = a;
                    long b = SettingsDetailFragment.this.mDeviceResManager.b(0, "ON_APP_LAUNCH_CUSTOMCARD_DISPLAY_INTERVAL", false);
                    int b2 = SettingsDetailFragment.this.mDeviceResManager.b(e, 0, false);
                    long j = intValue;
                    long currentTimeMillis = System.currentTimeMillis() / 1000;
                    com.custom_card_response.b c = customCard.a().c();
                    if (c.a().intValue() != 1 || c.b().intValue() != 1) {
                        Util.a(SettingsDetailFragment.this.mContext, SettingsDetailFragment.this.mContext.getResources().getString(R.string.subscribe_gaanaplus_hdq_msg), "HDQuality");
                    } else if (b2 >= intValue2 || intValue2 == 0 || intValue3 != 1 || currentTimeMillis < intValue4 || currentTimeMillis > intValue5 || (b != 0 && currentTimeMillis - b < j)) {
                        d dVar2 = dVar;
                        CustomCard customCard3 = customCard;
                        if (b2 >= intValue2 || intValue2 == 0 || intValue3 != 0 || (b != 0 && currentTimeMillis - b < j)) {
                            Util.a(SettingsDetailFragment.this.mContext, SettingsDetailFragment.this.mContext.getResources().getString(R.string.subscribe_gaanaplus_hdq_msg), "HDQuality");
                        } else {
                            SettingsDetailFragment.this.mDeviceResManager.a(e, b2 + 1, false);
                            SettingsDetailFragment.this.mDeviceResManager.a(currentTimeMillis, "ON_APP_LAUNCH_CUSTOMCARD_DISPLAY_INTERVAL", false);
                            SettingsDetailFragment.this.a(customCard3);
                            SettingsDetailFragment.this.a(dVar2.e());
                        }
                    } else {
                        SettingsDetailFragment.this.mDeviceResManager.a(e, b2 + 1, false);
                        SettingsDetailFragment.this.mDeviceResManager.a(currentTimeMillis, "ON_APP_LAUNCH_CUSTOMCARD_DISPLAY_INTERVAL", false);
                        SettingsDetailFragment.this.a(customCard);
                        SettingsDetailFragment.this.a(dVar.e());
                    }
                    adapterView.setSelected(false);
                    adapterView.setSelection(i);
                }
            });
            return;
        }
        adapterView.setSelected(false);
        adapterView.setSelection(i);
        Util.a(this.mContext, this.mContext.getResources().getString(R.string.subscribe_gaanaplus_hdq_msg), "HDQuality");
    }

    private void a(List<String> list) {
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                this.mDeviceResManager.a((String) list.get(i), 0, false);
            }
        }
    }

    private void a(CustomCard customCard) {
        CustomCardFragment customCardFragment = new CustomCardFragment();
        customCardFragment.a(customCard);
        customCardFragment.a("playback setting");
        customCardFragment.show(((GaanaActivity) this.mContext).getSupportFragmentManager().beginTransaction(), "CustomFragment");
    }

    private void a(String str, boolean z) {
        String stringBuilder;
        String str2 = "Set ";
        StringBuilder stringBuilder2;
        if (z) {
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(str2);
            stringBuilder2.append("ON");
            stringBuilder = stringBuilder2.toString();
        } else {
            stringBuilder2 = new StringBuilder();
            stringBuilder2.append(str2);
            stringBuilder2.append("OFF");
            stringBuilder = stringBuilder2.toString();
        }
        ((BaseActivity) this.mContext).sendGAEvent("Push notification", str, stringBuilder);
    }

    public String b() {
        return this.g;
    }

    private void h() {
        this.i = (LinearLayout) this.F.findViewById(R.id.settingsContainer);
        this.i.removeAllViews();
        View a = a(getString(R.string.music_recommendations), null);
        this.z = (SwitchCompat) a.findViewById(R.id.switchButton);
        this.z.setChecked(this.mDeviceResManager.b("PREFERENCE_KEY_NOTIFICATION_MUSIC_RECOMMENDATIONS", true, false));
        this.z.setOnCheckedChangeListener(this.P);
        View a2 = a(getString(R.string.someone_favorits_my_playlist), null);
        this.A = (SwitchCompat) a2.findViewById(R.id.switchButton);
        this.A.setChecked(this.mDeviceResManager.b("PREFERENCE_KEY_NOTIFICATION_FAVORITE_PLAYLIST", true, false));
        this.A.setOnCheckedChangeListener(this.Q);
        View a3 = a(getString(R.string.someone_follows_me), null);
        this.B = (SwitchCompat) a3.findViewById(R.id.switchButton);
        this.B.setChecked(this.mDeviceResManager.b("PREFERENCE_KEY_NOTIFICATION_FOLLOW_UPDATES", true, false));
        this.B.setOnCheckedChangeListener(this.R);
        this.i.addView(a);
        this.i.addView(a2);
        this.i.addView(a3);
    }

    private void i() {
        this.i = (LinearLayout) this.F.findViewById(R.id.settingsContainer);
        this.i.removeAllViews();
        this.i.addView(a(getString(R.string.download_my_data), 22, false));
        this.i.addView(v());
        this.i.addView(a(getString(R.string.delete_data), 23, false));
        this.i.addView(v());
    }

    private View a(String str, int i, boolean z) {
        View inflate = this.j.inflate(R.layout.view_settings_listitem_main_title, this.i, false);
        ((TextView) inflate.findViewById(R.id.headerText)).setText(str);
        if (z) {
            inflate.setBackgroundColor(0);
            inflate.getLayoutParams().height = -2;
            inflate.setPadding((int) getResources().getDimension(R.dimen.activity_horizontal_margin), (int) getResources().getDimension(R.dimen.activity_horizontal_margin_half), (int) getResources().getDimension(R.dimen.activity_horizontal_margin), 0);
        }
        inflate.setTag(Integer.valueOf(i));
        inflate.setOnClickListener(this);
        return inflate;
    }

    private void j() {
        this.i = (LinearLayout) this.F.findViewById(R.id.settingsContainer);
        this.i.removeAllViews();
        View a = a(getString(R.string.private_session), getString(R.string.hide_all_social_activities_for_six_hours_until_next_login), false);
        this.x = (SwitchCompat) a.findViewById(R.id.switchButton);
        boolean b = this.mDeviceResManager.b("PREFERENCE_KEY_SOCIAL_PRIVATE_SESSION", false, false);
        this.x.setChecked(b);
        this.x.setOnCheckedChangeListener(this.M);
        this.i.addView(a);
        a = a(getString(R.string.post_to_facebook), null);
        this.y = (SwitchCompat) a.findViewById(R.id.switchButton);
        if (b) {
            this.y.setChecked(false);
        } else {
            this.y.setChecked(this.mDeviceResManager.b("PREFERENCE_KEY_POST_TO_FACEBOOK", false, false));
        }
        this.y.setOnCheckedChangeListener(this.N);
        this.i.addView(a);
        this.i.addView(b(getString(R.string.we_dont_spam_your_social_pages)));
    }

    private void k() {
        if (this.m.isChecked()) {
            long b = this.mDeviceResManager.b(-1, "PREFERENCE_KEY_OFFLINE_MODE_START_TIME", true);
            if (b != -1) {
                b = 30 - ((System.currentTimeMillis() - b) / MeasurementDispatcher.MILLIS_PER_DAY);
                TextView textView = this.s;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(getString(R.string.you_must_go_online_in_the_next));
                stringBuilder.append(b);
                stringBuilder.append(" ");
                stringBuilder.append(getString(R.string.days));
                textView.setText(stringBuilder.toString());
            }
            this.s.setVisibility(0);
            return;
        }
        this.s.setVisibility(8);
    }

    private void l() {
        this.i = (LinearLayout) this.F.findViewById(R.id.settingsContainer);
        this.i.removeAllViews();
        updateView();
        this.i.setVisibility(0);
        this.F.findViewById(R.id.settingsContainer1).setVisibility(8);
        this.i.addView(new ThemeSettingsItemView(this.mContext, this).getPoplatedView(null, null, this.i));
    }

    private void m() {
        this.i = (LinearLayout) this.F.findViewById(R.id.settingsContainer);
        this.i.removeAllViews();
        updateView();
        this.i.setVisibility(0);
        this.F.findViewById(R.id.settingsContainer1).setVisibility(8);
        this.i.addView(new LanguageSettingsItemView(this.mContext, this).getPoplatedView(null, null, this.i));
    }

    private void n() {
        this.i = (LinearLayout) this.F.findViewById(R.id.settingsContainer);
        this.i.removeAllViews();
        updateView();
        this.i.setVisibility(0);
        this.F.findViewById(R.id.settingsContainer1).setVisibility(8);
        this.i.addView(new RadioButtonDeviceListView(this.mContext, this).getPoplatedView(null, null, this.i));
    }

    private void b(String str, boolean z) {
        CharSequence string;
        CharSequence string2;
        CharSequence string3;
        updateView();
        this.i = (LinearLayout) this.F.findViewById(R.id.settingsContainer);
        this.i.removeAllViews();
        setmToolbar((Toolbar) this.F.findViewById(R.id.main_toolbar));
        if (getArguments() != null) {
            string = getArguments().getString("item_id");
            string2 = getArguments().getString("product_id");
            string3 = getArguments().getString("purchase_coupon_code");
        } else {
            string = null;
            string2 = string;
            string3 = string2;
        }
        if (TextUtils.isEmpty(string) && TextUtils.isEmpty(string2) && ap.a().j() && !z) {
            View a;
            boolean b;
            if (ap.a().o()) {
                a = a(getString(R.string.go_offline), null);
                ((TextView) a.findViewById(R.id.headerText)).setTextSize(16.0f);
                b = this.mDeviceResManager.b("PREFERENCE_KEY_OFFLINE_MODE", false, false);
                this.s = (TextView) a.findViewById(R.id.subheaderText);
                this.m = (SwitchCompat) a.findViewById(R.id.switchButton);
                this.m.setChecked(b);
                k();
                this.m.setOnCheckedChangeListener(this.T);
                this.i.addView(a);
                this.i.addView(v());
            }
            this.i.addView(h(getString(R.string.download_settings)));
            a = a(getString(R.string.auto_download_setting), null);
            ((TextView) a.findViewById(R.id.headerText)).setTextSize(16.0f);
            this.v = (SwitchCompat) a.findViewById(R.id.switchButton);
            this.v.setChecked(this.mDeviceResManager.b("PREFERENCE_KEY_AUTO_DOWNLOAD", true, false));
            this.v.setOnCheckedChangeListener(this.Y);
            this.i.addView(a);
            if (ap.a().f()) {
                a.setVisibility(8);
            }
            a = a(getString(R.string.download_over_2g_or_3g), null);
            ((TextView) a.findViewById(R.id.headerText)).setTextSize(16.0f);
            this.u = (SwitchCompat) a.findViewById(R.id.switchButton);
            this.u.setChecked(this.mDeviceResManager.b("PREFERENCE_KEY_SYNC_OVER_DATA_CONNECTION", false, true));
            this.u.setOnCheckedChangeListener(this.Z);
            this.i.addView(a);
            a = a(getString(R.string.schedule_Download), null);
            this.D = p();
            ((TextView) a.findViewById(R.id.headerText)).setTextSize(16.0f);
            this.t = (SwitchCompat) a.findViewById(R.id.switchButton);
            if (this.mDeviceResManager.b("PREFERENCE_KEY_SYNC_OVER_DATA_CONNECTION", false, true)) {
                this.t.setEnabled(true);
                b = this.mDeviceResManager.b("PREFERENCE_KEY_SCHEDULE_DOWNLOAD_ENABLED", false, true);
                this.t.setChecked(b);
                this.t.setOnCheckedChangeListener(this.aa);
                if (b) {
                    this.mDeviceResManager.a("PREFERENCE_KEY_NIGHT_DATA_CONNECTION", true, true);
                    if (this.D != null) {
                        this.D.setVisibility(0);
                    }
                } else {
                    this.mDeviceResManager.a("PREFERENCE_KEY_NIGHT_DATA_CONNECTION", false, true);
                    if (this.D != null) {
                        this.D.setVisibility(8);
                    }
                }
            } else {
                this.t.setEnabled(false);
                this.t.setChecked(false);
                this.t.setOnCheckedChangeListener(null);
                if (this.D != null) {
                    this.D.setVisibility(8);
                }
                this.mDeviceResManager.a("PREFERENCE_KEY_NIGHT_DATA_CONNECTION", false, true);
            }
            this.i.addView(a);
            this.i.addView(this.D);
            final int b2 = this.mDeviceResManager.b("PREFERENCE_KEY_SYNC_QUALITY", 2, true);
            a = a(getString(R.string.downlaod_quality), (int) R.array.download_quality_array, b2, new OnItemSelectedListener() {
                int a = b2;

                public void onNothingSelected(AdapterView<?> adapterView) {
                }

                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                    if (!SettingsDetailFragment.this.mAppState.isAppInDataSaveMode() || i == 0) {
                        switch (i) {
                            case 0:
                                if (!SettingsDetailFragment.this.r()) {
                                    if (SettingsDetailFragment.this.mDeviceResManager.b("PREFERENCE_KEY_SYNC_QUALITY", 1, true) != 0) {
                                        u.a().a("Settings", "Set Download Quality", "Regular");
                                    }
                                    SettingsDetailFragment.this.b("PREFERENCE_KEY_SYNC_QUALITY", 0);
                                    if (this.a != i) {
                                        Util.b("download_quality", "0");
                                        break;
                                    }
                                }
                                break;
                            case 1:
                                if (!SettingsDetailFragment.this.r()) {
                                    if (SettingsDetailFragment.this.mDeviceResManager.b("PREFERENCE_KEY_SYNC_QUALITY", 1, true) != 1) {
                                        u.a().a("Settings", "Set Download Quality", "High");
                                    }
                                    SettingsDetailFragment.this.b("PREFERENCE_KEY_SYNC_QUALITY", 1);
                                    if (this.a != i) {
                                        Util.b("download_quality", "1");
                                        break;
                                    }
                                }
                                break;
                            case 2:
                                if (!SettingsDetailFragment.this.r()) {
                                    if (SettingsDetailFragment.this.mDeviceResManager.b("PREFERENCE_KEY_SYNC_QUALITY", 1, true) != 2) {
                                        u.a().a("Settings", "Set Download Quality", "Extreme");
                                    }
                                    SettingsDetailFragment.this.b("PREFERENCE_KEY_SYNC_QUALITY", 2);
                                    if (this.a != i) {
                                        Util.b("download_quality", InternalAvidAdSessionContext.AVID_API_LEVEL);
                                        break;
                                    }
                                }
                                break;
                        }
                        this.a = i;
                        return;
                    }
                    ((BaseActivity) SettingsDetailFragment.this.mContext).displayFeatureNotAvailableDataSaveModeDialog(-1, i);
                }
            });
            ((TextView) a.findViewById(R.id.tvHeader)).setTextSize(16.0f);
            this.i.addView(a);
            this.i.addView(s());
            if (ap.a().o()) {
                this.i.addView(v());
                this.i.addView(h(getString(R.string.sync_settings)));
                a = a(getString(R.string.sync_over_2g_or_3g), null);
                ((TextView) a.findViewById(R.id.headerText)).setTextSize(16.0f);
                this.w = (SwitchCompat) a.findViewById(R.id.switchButton);
                this.w.setChecked(this.mDeviceResManager.b("PREFERENCE_KEY_DOWNLOAD_SYNC_OVER_DATA_CONNECTION", false, true));
                this.w.setOnCheckedChangeListener(this.ab);
                this.i.addView(a);
                a = a(getString(R.string.auto_sync), null);
                ((TextView) a.findViewById(R.id.headerText)).setTextSize(16.0f);
                boolean b3 = this.mDeviceResManager.b("PREFERENCE_KEY_SETTINGS_AUTO_SYNC_V5", false, true);
                this.r = (SwitchCompat) a.findViewById(R.id.switchButton);
                this.r.setChecked(b3);
                this.r.setOnCheckedChangeListener(this.S);
                this.i.addView(a);
                TextView textView = new TextView(this.mContext);
                textView.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
                textView.setGravity(17);
                textView.setText(R.string.enjoy_a_seamless_experience_across_all_devices);
                textView.setTextColor(this.f);
                this.i.addView(textView);
                this.G = new TextView(this.mContext);
                this.G.setGravity(17);
                this.G.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
                this.G.setTextColor(this.f);
                o();
                this.i.addView(this.G);
                this.H = c(getString(R.string.sync_across_device));
                this.H.setTag(Integer.valueOf(203));
                this.H.setClickable(true);
                this.H.setOnClickListener(this);
                this.i.addView(this.H);
                this.J = d(getString(R.string.syncing));
                this.J.setVisibility(8);
                this.H.setVisibility(0);
                this.i.addView(this.J);
                this.I = new BroadcastReceiver() {
                    public void onReceive(Context context, Intent intent) {
                        if ("intent_download_sync_progress_update".equalsIgnoreCase(intent.getAction()) && SettingsDetailFragment.this.J != null) {
                            if (com.managers.l.a().f()) {
                                if (SettingsDetailFragment.this.J != null) {
                                    SettingsDetailFragment.this.J.setVisibility(0);
                                }
                                if (SettingsDetailFragment.this.H != null) {
                                    SettingsDetailFragment.this.H.setVisibility(8);
                                    return;
                                }
                                return;
                            }
                            if (SettingsDetailFragment.this.J != null) {
                                SettingsDetailFragment.this.J.setVisibility(8);
                            }
                            if (SettingsDetailFragment.this.H != null) {
                                SettingsDetailFragment.this.H.setVisibility(0);
                            }
                            SettingsDetailFragment.this.o();
                        }
                    }
                };
                LocalBroadcastManager.getInstance(this.mContext.getApplicationContext()).registerReceiver(this.I, new IntentFilter("intent_download_sync_progress_update"));
            }
            return;
        }
        LinearLayout linearLayout = (LinearLayout) this.F.findViewById(R.id.settingsParent);
        linearLayout.removeAllViews();
        linearLayout.setVisibility(0);
        this.F.findViewById(R.id.settingsContainer1).setVisibility(8);
        GaanaPlusItemView gaanaPlusItemView = new GaanaPlusItemView(this.mContext, this);
        gaanaPlusItemView.setProductAndItemId(string, string2);
        gaanaPlusItemView.showPriceDialog(str);
        if (!TextUtils.isEmpty(string3)) {
            gaanaPlusItemView.setCouponCode(string3);
        }
        linearLayout.addView(gaanaPlusItemView.getPoplatedView(null));
    }

    private void o() {
        if (this.G != null) {
            String c = this.mDeviceResManager.c("LAST_DOWNLOAD_SYNC_TIME_LOCAL", true);
            if (TextUtils.isEmpty(c)) {
                this.G.setVisibility(8);
                return;
            }
            this.G.setVisibility(0);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm aa");
            Date date = new Date(Long.parseLong(c));
            TextView textView = this.G;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(getString(R.string.last_synced_time));
            stringBuilder.append(simpleDateFormat.format(date));
            textView.setText(stringBuilder.toString());
        }
    }

    private View a(String str, int i) {
        View inflate = this.j.inflate(R.layout.view_settings_listitem_simple, this.i, false);
        ((TextView) inflate.findViewById(R.id.headerText)).setText(str);
        inflate.setTag(Integer.valueOf(i));
        inflate.setOnClickListener(this);
        return inflate;
    }

    private View a(String str, int i, int i2, OnItemSelectedListener onItemSelectedListener) {
        View inflate = this.j.inflate(R.layout.view_settings_listitem_spinner, this.i, false);
        ((TextView) inflate.findViewById(R.id.tvHeader)).setText(str);
        Spinner spinner = (Spinner) inflate.findViewById(R.id.spinner);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this.mContext, R.layout.view_settings_spinner_item, this.mContext.getResources().getStringArray(i));
        arrayAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setSelection(i2);
        spinner.setOnItemSelectedListener(onItemSelectedListener);
        return inflate;
    }

    private View a(Context context, String[] strArr, int i, OnItemSelectedListener onItemSelectedListener) {
        View inflate = this.j.inflate(R.layout.view_settings_text_spinner, this.i, false);
        Spinner spinner = (Spinner) inflate.findViewById(R.id.spinner);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this.mContext, R.layout.view_settings_spinner_text, strArr);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_dropdown_text);
        spinner.setAdapter(arrayAdapter);
        spinner.setSelection(i);
        spinner.setOnItemSelectedListener(onItemSelectedListener);
        return inflate;
    }

    private View p() {
        int b = this.mDeviceResManager.b("PREFERENCE_KEY_SCHEDULE_DOWNLOAD_FROM", 0, true);
        int b2 = this.mDeviceResManager.b("PREFERENCE_KEY_SCHEDULE_DOWNLOAD_TO", 4, true);
        View inflate = this.j.inflate(R.layout.schedule_download_spinner, this.i, false);
        final Spinner spinner = (Spinner) inflate.findViewById(R.id.spinner1);
        final Spinner spinner2 = (Spinner) inflate.findViewById(R.id.spinner2);
        if (VERSION.SDK_INT >= 16) {
            spinner.setDropDownVerticalOffset(com.cast_music.b.d.a(this.mContext, 30.0f));
            spinner2.setDropDownVerticalOffset(com.cast_music.b.d.a(this.mContext, 30.0f));
        }
        spinner.setSelection(b);
        spinner2.setSelection(b2);
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                if (i == SettingsDetailFragment.this.mDeviceResManager.b("PREFERENCE_KEY_SCHEDULE_DOWNLOAD_TO", 4, true)) {
                    spinner.setSelection(SettingsDetailFragment.this.mDeviceResManager.b("PREFERENCE_KEY_SCHEDULE_DOWNLOAD_FROM", 0, true));
                    aj.a().a(SettingsDetailFragment.this.mContext, "You cannot select the same time for both time slots");
                    return;
                }
                SettingsDetailFragment.this.b("PREFERENCE_KEY_SCHEDULE_DOWNLOAD_FROM", i);
            }
        });
        spinner2.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
                if (i == com.services.d.a().b("PREFERENCE_KEY_SCHEDULE_DOWNLOAD_FROM", 0, true)) {
                    spinner2.setSelection(SettingsDetailFragment.this.mDeviceResManager.b("PREFERENCE_KEY_SCHEDULE_DOWNLOAD_TO", 4, true));
                    aj.a().a(SettingsDetailFragment.this.mContext, "You cannot select the same time for both time slots");
                    return;
                }
                SettingsDetailFragment.this.b("PREFERENCE_KEY_SCHEDULE_DOWNLOAD_TO", i);
            }
        });
        return inflate;
    }

    private View a(String str, String str2) {
        View inflate = this.j.inflate(R.layout.view_settings_listitem_switch, this.i, false);
        ((TextView) inflate.findViewById(R.id.headerText)).setText(str);
        if (str2 != null) {
            inflate.findViewById(R.id.subheaderText).setVisibility(0);
            ((TextView) inflate.findViewById(R.id.subheaderText)).setText(str2);
        } else {
            inflate.findViewById(R.id.subheaderText).setVisibility(8);
        }
        return inflate;
    }

    private View b(String str) {
        View inflate = this.j.inflate(R.layout.view_settings_listitem_text, this.i, false);
        if (str != null) {
            inflate.findViewById(R.id.subheaderText).setVisibility(0);
            ((TextView) inflate.findViewById(R.id.subheaderText)).setText(str);
        } else {
            inflate.findViewById(R.id.subheaderText).setVisibility(8);
        }
        return inflate;
    }

    private View a(String str, String str2, boolean z) {
        View inflate = this.j.inflate(z ? R.layout.view_settings_big_listitem_switch : R.layout.view_settings_listitem_switch, this.i, false);
        ((TextView) inflate.findViewById(R.id.headerText)).setText(str);
        if (str2 != null) {
            inflate.findViewById(R.id.subheaderText).setVisibility(0);
            ((TextView) inflate.findViewById(R.id.subheaderText)).setText(str2);
        } else {
            inflate.findViewById(R.id.subheaderText).setVisibility(8);
        }
        return inflate;
    }

    private View c(String str) {
        View inflate = this.j.inflate(R.layout.view_settings_button, this.i, false);
        ((TextView) inflate.findViewById(R.id.button)).setText(str);
        return inflate;
    }

    private View d(String str) {
        return this.j.inflate(R.layout.view_settings_listitem_progressbar, this.i, false);
    }

    private View a(String str, String str2, String str3) {
        View inflate = this.j.inflate(R.layout.view_settings_listitem_seekbar, this.i, false);
        ((TextView) inflate.findViewById(R.id.headerText)).setText(str);
        ((TextView) inflate.findViewById(R.id.lowestSeek)).setText(str2);
        ((TextView) inflate.findViewById(R.id.highestSeek)).setText(str3);
        return inflate;
    }

    private void q() {
        DownloadManager.c().u();
        aj.a().a(this.mContext, getString(R.string.cache_cleared_successfully));
    }

    private void b(String str, int i) {
        this.mDeviceResManager.a(str, i, true);
    }

    private boolean r() {
        if (ap.a().j()) {
            return false;
        }
        this.b.a(getString(R.string.gaana_plus_feature), getString(R.string.you_need_to_a_gaana_plus_user_to_access_this_feature), Boolean.valueOf(true), getString(R.string.tell_me_more), getString(R.string.cancel), new b() {
            public void onCancelListner() {
            }

            public void onOkListner(String str) {
                Bundle bundle = new Bundle();
                bundle.putInt("KEY_SETTINGS", 1);
                BaseGaanaFragment settingsDetailFragment = new SettingsDetailFragment();
                settingsDetailFragment.setArguments(bundle);
                ((GaanaActivity) SettingsDetailFragment.this.mContext).displayFragment(settingsDetailFragment);
            }
        });
        return true;
    }

    private View s() {
        View view = new View(this.mContext);
        view.setLayoutParams(new ViewGroup.LayoutParams(-1, getResources().getDimensionPixelSize(R.dimen.setting_grey_line_height)));
        view.setBackgroundColor(getResources().getColor(17170445));
        return view;
    }

    public void onResume() {
        super.onResume();
        if (this.k == 20) {
            t();
        }
        ((GaanaActivity) this.mContext).title = this.c;
        Toolbar toolbar = (Toolbar) this.F.findViewById(R.id.main_toolbar);
        toolbar.setContentInsetsAbsolute(0, 0);
        if (this.k == 1 && ap.a().j() && !this.ad) {
            boolean b = this.mDeviceResManager.b("PREFERENCE_KEY_DOWNLOAD_SYNC_OVER_DATA_CONNECTION", false, true);
            if (this.w != null) {
                this.w.setChecked(b);
                this.w.setOnCheckedChangeListener(this.ab);
            }
            b = this.mDeviceResManager.b("PREFERENCE_KEY_AUTO_DOWNLOAD", true, false);
            if (this.v != null) {
                this.v.setChecked(b);
                this.v.setOnCheckedChangeListener(this.Y);
            }
            this.m.setChecked(this.mDeviceResManager.b("PREFERENCE_KEY_OFFLINE_MODE", false, false));
            k();
            this.m.setOnCheckedChangeListener(this.T);
            this.t.setChecked(this.mDeviceResManager.b("PREFERENCE_KEY_SCHEDULE_DOWNLOAD_ENABLED", false, true));
            this.t.setOnCheckedChangeListener(this.aa);
            this.r.setChecked(this.mDeviceResManager.b("PREFERENCE_KEY_SETTINGS_AUTO_SYNC_V5", false, true));
            this.r.setOnCheckedChangeListener(this.S);
            this.u.setChecked(this.mDeviceResManager.b("PREFERENCE_KEY_SYNC_OVER_DATA_CONNECTION", false, true));
            this.u.setOnCheckedChangeListener(this.Z);
        }
        View genericBackActionBar = new GenericBackActionBar(this.mContext, this.c);
        if (this.h) {
            genericBackActionBar = new GenericBackActionBar(this.mContext, this.c, true);
        }
        toolbar.removeAllViews();
        toolbar.addView(genericBackActionBar);
    }

    private void t() {
        if (((GaanaActivity) this.mContext).getCurrentSleepTime() == 0) {
            a(0, 0);
            this.e.setEnabled(false);
            this.e.setChecked(false);
            return;
        }
        e();
    }

    public void onPause() {
        super.onPause();
        if (this.k == 1 && ap.a().j() && !this.ad) {
            if (this.w != null) {
                this.w.setOnCheckedChangeListener(null);
            }
            this.t.setOnCheckedChangeListener(null);
            this.m.setOnCheckedChangeListener(null);
            this.u.setOnCheckedChangeListener(null);
            this.v.setOnCheckedChangeListener(null);
            this.r.setOnCheckedChangeListener(null);
        }
        ((GaanaActivity) this.mContext).removeSleepTimerCallback();
    }

    public void onDestroyView() {
        if (this.k == 2 && this.O) {
            com.fcm.a.b();
        }
        if (this.k == 1) {
            try {
                if (this.I != null) {
                    LocalBroadcastManager.getInstance(this.mContext.getApplicationContext()).unregisterReceiver(this.I);
                }
                if (getArguments() != null) {
                    getArguments().remove("item_id");
                    getArguments().remove("product_id");
                    getArguments().remove("purchase_coupon_code");
                }
            } catch (Exception unused) {
            }
        }
        if (this.k == 1 && this.mDeviceResManager.b("PREFERENCE_KEY_NIGHT_DATA_CONNECTION", false, true)) {
            k.a().b(this.mContext);
            int b = this.mDeviceResManager.b("PREFERENCE_KEY_SCHEDULE_DOWNLOAD_FROM", 0, true);
            int b2 = this.mDeviceResManager.b("PREFERENCE_KEY_SCHEDULE_DOWNLOAD_TO", 4, true);
            String[] stringArray = this.mContext.getResources().getStringArray(R.array.schedule_time_slots);
            aj.a().a(this.mContext, this.mContext.getResources().getString(R.string.schedule_time_msg, new Object[]{stringArray[b], stringArray[b2]}));
            String str = stringArray[b];
            String str2 = stringArray[b2];
            ((BaseActivity) this.mContext).sendGAEvent("Download Settings", "Start Time", str);
            ((BaseActivity) this.mContext).sendGAEvent("Download Settings", "End Time", str2);
        }
        if (((ViewGroup) this.F.getParent()) != null) {
            ((ViewGroup) this.F.getParent()).removeView(this.F);
        }
        if (Util.K()) {
            Util.L();
        }
        super.onDestroyView();
    }

    public void onClick(View view) {
        int intValue = ((Integer) view.getTag()).intValue();
        if (intValue != 203) {
            switch (intValue) {
                case 0:
                    b("PREFERENCE_KEY_SYNC_QUALITY", 0);
                    Util.a(this.l, (int) R.id.headerTextLow, (int) R.id.radioItemLow);
                    Util.b("download_quality", "0");
                    break;
                case 1:
                    b("PREFERENCE_KEY_SYNC_QUALITY", 1);
                    Util.a(this.l, (int) R.id.headerTextMedium, (int) R.id.radioItemMedium);
                    Util.b("download_quality", "1");
                    break;
                case 2:
                    b("PREFERENCE_KEY_SYNC_QUALITY", 2);
                    Util.a(this.l, (int) R.id.headerTextHigh, (int) R.id.radioItemHigh);
                    Util.b("download_quality", InternalAvidAdSessionContext.AVID_API_LEVEL);
                    break;
                default:
                    switch (intValue) {
                        case 22:
                            String str = "";
                            if (!(GaanaApplication.getInstance().getCurrentUser() == null || GaanaApplication.getInstance().getCurrentUser().getUserProfile() == null)) {
                                str = GaanaApplication.getInstance().getCurrentUser().getUserProfile().getEmail();
                            }
                            if (!TextUtils.isEmpty(str)) {
                                f(str);
                                break;
                            } else {
                                b(getString(R.string.provide_emailid_msg), getString(R.string.download_my_data));
                                break;
                            }
                        case 23:
                            u();
                            break;
                        default:
                            BaseGaanaFragment webViewsFragment;
                            Bundle bundle;
                            switch (intValue) {
                                case 102:
                                    break;
                                case 103:
                                    if (Util.j(this.mContext)) {
                                        webViewsFragment = new WebViewsFragment();
                                        bundle = new Bundle();
                                        bundle.putString("WebViewContent", WebViewContent.PRIVACY_POLICY.toString());
                                        webViewsFragment.setArguments(bundle);
                                        ((GaanaActivity) this.mContext).displayFragment(webViewsFragment);
                                        break;
                                    }
                                    ap.a().f(this.mContext);
                                    return;
                                case 104:
                                    if (Util.j(this.mContext)) {
                                        webViewsFragment = new WebViewsFragment();
                                        bundle = new Bundle();
                                        bundle.putString("WebViewContent", WebViewContent.TERMS_CONDITIONS.toString());
                                        webViewsFragment.setArguments(bundle);
                                        ((GaanaActivity) this.mContext).displayFragment(webViewsFragment);
                                        break;
                                    }
                                    ap.a().f(this.mContext);
                                    return;
                                case 105:
                                    if (Util.j(this.mContext)) {
                                        webViewsFragment = new WebViewsFragment();
                                        bundle = new Bundle();
                                        bundle.putString("WebViewContent", WebViewContent.ABOUT_TIL.toString());
                                        webViewsFragment.setArguments(bundle);
                                        ((GaanaActivity) this.mContext).displayFragment(webViewsFragment);
                                        break;
                                    }
                                    ap.a().f(this.mContext);
                                    return;
                                case 106:
                                    if (Util.j(this.mContext)) {
                                        webViewsFragment = new WebViewsFragment();
                                        bundle = new Bundle();
                                        bundle.putString("WebViewContent", WebViewContent.OUR_PARTNERS.toString());
                                        webViewsFragment.setArguments(bundle);
                                        ((GaanaActivity) this.mContext).displayFragment(webViewsFragment);
                                        break;
                                    }
                                    ap.a().f(this.mContext);
                                    return;
                                default:
                                    switch (intValue) {
                                        case 200:
                                            if (!ap.a().o()) {
                                                this.b.a(getString(R.string.gaana_plus_feature), getString(R.string.you_need_to_a_gaana_plus_user_to_access_this_feature), Boolean.valueOf(true), getString(R.string.tell_me_more), getString(R.string.cancel), new b() {
                                                    public void onCancelListner() {
                                                    }

                                                    public void onOkListner(String str) {
                                                        Bundle bundle = new Bundle();
                                                        bundle.putInt("KEY_SETTINGS", 1);
                                                        BaseGaanaFragment settingsDetailFragment = new SettingsDetailFragment();
                                                        settingsDetailFragment.setArguments(bundle);
                                                        ((GaanaActivity) SettingsDetailFragment.this.mContext).displayFragment(settingsDetailFragment);
                                                    }
                                                });
                                                break;
                                            }
                                            this.b.a(getString(R.string.gaana), getString(R.string.are_you_sure_you_want_to_delete_all_downlaoded_song), Boolean.valueOf(true), getString(R.string.yes), getString(R.string.no), new b() {
                                                public void onCancelListner() {
                                                }

                                                public void onOkListner(String str) {
                                                    SettingsDetailFragment.this.q();
                                                }
                                            });
                                            break;
                                        case HttpStatusCodes.STATUS_CODE_CREATED /*201*/:
                                            if (DownloadManager.c().q() == 0 || !ap.a().o()) {
                                                webViewsFragment = new DownloadHomeFragment();
                                                webViewsFragment.setArguments(new Bundle());
                                            } else {
                                                webViewsFragment = new DownloadDetailsFragment();
                                                webViewsFragment.setArguments(new Bundle());
                                            }
                                            ((GaanaActivity) this.mContext).displayFragment(webViewsFragment);
                                            break;
                                        default:
                                            switch (intValue) {
                                                case 10000:
                                                    u.a().a("Settings", "Set Streaming Quality", "Low");
                                                    b("PREFERENCE_KEY_STREAMING_QUALITY", 10000);
                                                    b("PREFERENCE_KEY_STREAMING_QUALITY_SELECTED", 10000);
                                                    Util.a(this.l, (int) R.id.headerTextLow, (int) R.id.radioItemLow);
                                                    break;
                                                case 10001:
                                                    u.a().a("Settings", "Set Streaming Quality", "Medium");
                                                    b("PREFERENCE_KEY_STREAMING_QUALITY", 10001);
                                                    b("PREFERENCE_KEY_STREAMING_QUALITY_SELECTED", 10001);
                                                    Util.a(this.l, (int) R.id.headerTextMedium, (int) R.id.radioItemMedium);
                                                    break;
                                                case 10002:
                                                    u.a().a("Settings", "Set Streaming Quality", "High");
                                                    b("PREFERENCE_KEY_STREAMING_QUALITY", 10002);
                                                    b("PREFERENCE_KEY_STREAMING_QUALITY_SELECTED", 10002);
                                                    Util.a(this.l, (int) R.id.headerTextHigh, (int) R.id.radioItemHigh);
                                                    break;
                                                case 10003:
                                                    if (!ap.a().s()) {
                                                        this.b.a(getString(R.string.gaana_plus_feature), getString(R.string.you_need_to_a_gaana_plus_user_to_access_this_feature), Boolean.valueOf(true), getString(R.string.tell_me_more), getString(R.string.cancel), new b() {
                                                            public void onCancelListner() {
                                                            }

                                                            public void onOkListner(String str) {
                                                                UninstallIO.sendPaymentScreenViewedEvent("HDQuality");
                                                                Bundle bundle = new Bundle();
                                                                bundle.putInt("KEY_SETTINGS", 1);
                                                                BaseGaanaFragment settingsDetailFragment = new SettingsDetailFragment();
                                                                settingsDetailFragment.setArguments(bundle);
                                                                ((GaanaActivity) SettingsDetailFragment.this.mContext).displayFragment(settingsDetailFragment);
                                                            }
                                                        });
                                                        break;
                                                    }
                                                    b("PREFERENCE_KEY_STREAMING_QUALITY", 10003);
                                                    b("PREFERENCE_KEY_STREAMING_QUALITY_SELECTED", 10003);
                                                    Util.a(this.l, (int) R.id.headerTextHd, (int) R.id.radioItemHd);
                                                    u.a().a("Settings", "Set Streaming Quality", "High Definition");
                                                    break;
                                            }
                                            break;
                                    }
                            }
                    }
            }
        } else if (!Util.j(this.mContext)) {
            ap.a().f(this.mContext);
        } else if (this.mAppState.isAppInOfflineMode()) {
            if (this.mContext instanceof BaseActivity) {
                ((BaseActivity) this.mContext).displayFeatureNotAvailableOfflineDialog(getString(R.string.download_sync));
            }
        } else if (Util.k(GaanaApplication.getContext()) != 0 || this.mDeviceResManager.b("PREFERENCE_KEY_DOWNLOAD_SYNC_OVER_DATA_CONNECTION", false, true)) {
            ((BaseActivity) this.mContext).sendGAEvent("Settings", "Download Settings", null);
            Bundle bundle2 = new Bundle();
            bundle2.putInt("KEY_SETTINGS", 203);
            BaseGaanaFragment settingsDetailFragment = new SettingsDetailFragment();
            settingsDetailFragment.setArguments(bundle2);
            ((GaanaActivity) this.mContext).displayFragment(settingsDetailFragment);
        } else {
            ap.a().a(this.mContext, getString(R.string.please_enable_sync_over_2g_or_3g_in_sync_settings_above));
        }
    }

    private void u() {
        this.i = (LinearLayout) this.F.findViewById(R.id.settingsContainer);
        this.i.removeAllViews();
        if (this.F != null) {
            Toolbar toolbar = (Toolbar) this.F.findViewById(R.id.main_toolbar);
            if (!(toolbar == null || toolbar.findViewById(R.id.actionbar_title) == null)) {
                ((TextView) toolbar.findViewById(R.id.actionbar_title)).setText(getString(R.string.delete_data));
            }
        }
        View inflate = this.j.inflate(R.layout.gdpr_deletedata_screen, this.i, false);
        Button button = (Button) inflate.findViewById(R.id.button_agree);
        TextView textView = (TextView) inflate.findViewById(R.id.tnc_text_layout);
        final CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.agreeTnC);
        TextView textView2 = (TextView) inflate.findViewById(R.id.message_text);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getString(R.string.delete_data_gaana_account));
        stringBuilder.append("\n");
        stringBuilder.append(getString(R.string.delete_data_erase_history));
        stringBuilder.append("\n");
        stringBuilder.append(getString(R.string.delete_data_tracksdata));
        stringBuilder.append("\n");
        stringBuilder.append(getString(R.string.delete_data_gaana_subs));
        stringBuilder.append("\n");
        stringBuilder.append(getString(R.string.delete_data_gaana_recommendations));
        stringBuilder.append("\n");
        stringBuilder.append(getString(R.string.delete_data_search_history));
        textView2.setText(stringBuilder.toString());
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        String string = getString(R.string.tnc_part_1);
        String string2 = getString(R.string.string_tnc);
        String string3 = getString(R.string.string_and);
        String string4 = getString(R.string.privacy_policy);
        String string5 = getString(R.string.tnc_part_2);
        spannableStringBuilder.append(string);
        int length = spannableStringBuilder.length();
        spannableStringBuilder.append(string2);
        spannableStringBuilder.setSpan(new UnderlineSpan(), length, spannableStringBuilder.length(), 33);
        spannableStringBuilder.setSpan(new ClickableSpan() {
            public void onClick(View view) {
                if (Util.j(SettingsDetailFragment.this.mContext)) {
                    BaseGaanaFragment webViewsFragment = new WebViewsFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("WebViewContent", WebViewContent.TERMS_CONDITIONS.toString());
                    webViewsFragment.setArguments(bundle);
                    ((GaanaActivity) SettingsDetailFragment.this.mContext).displayFragment(webViewsFragment);
                    return;
                }
                ap.a().f(SettingsDetailFragment.this.mContext);
            }
        }, length, spannableStringBuilder.length(), 33);
        spannableStringBuilder.append(string3);
        length = spannableStringBuilder.length();
        spannableStringBuilder.append(string4);
        spannableStringBuilder.setSpan(new UnderlineSpan(), length, spannableStringBuilder.length(), 33);
        spannableStringBuilder.setSpan(new ClickableSpan() {
            public void onClick(View view) {
                if (Util.j(SettingsDetailFragment.this.mContext)) {
                    BaseGaanaFragment webViewsFragment = new WebViewsFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("WebViewContent", WebViewContent.PRIVACY_POLICY.toString());
                    webViewsFragment.setArguments(bundle);
                    ((GaanaActivity) SettingsDetailFragment.this.mContext).displayFragment(webViewsFragment);
                    return;
                }
                ap.a().f(SettingsDetailFragment.this.mContext);
            }
        }, length, spannableStringBuilder.length(), 33);
        spannableStringBuilder.append(string5);
        spannableStringBuilder.append("\n");
        textView.setText(spannableStringBuilder);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (checkBox.isChecked()) {
                    String str = "";
                    if (!(GaanaApplication.getInstance().getCurrentUser() == null || GaanaApplication.getInstance().getCurrentUser().getUserProfile() == null)) {
                        str = GaanaApplication.getInstance().getCurrentUser().getUserProfile().getEmail();
                    }
                    if (TextUtils.isEmpty(str)) {
                        SettingsDetailFragment.this.b(SettingsDetailFragment.this.getString(R.string.provide_email_to_deletedata_msg), SettingsDetailFragment.this.getString(R.string.delete_data));
                    } else {
                        SettingsDetailFragment.this.e(str);
                    }
                    return;
                }
                aj.a().a(SettingsDetailFragment.this.mContext, SettingsDetailFragment.this.getString(R.string.agree_terms_conditions));
            }
        });
        this.i.addView(inflate);
    }

    private void e(final String str) {
        ((BaseActivity) getActivity()).mDialog.a(getString(R.string.delete_data_dialog_title), getString(R.string.delete_data_dialog_msg), Boolean.valueOf(true), getString(R.string.yes_text), getString(R.string.dlg_msg_cancel_cap), new b() {
            public void onCancelListner() {
            }

            public void onOkListner(String str) {
                Util.k(SettingsDetailFragment.this.mContext, str);
                ((BaseActivity) SettingsDetailFragment.this.getActivity()).mDialog.a("", SettingsDetailFragment.this.getString(R.string.delete_data_confirm_msg), Boolean.valueOf(false), new b() {
                    public void onCancelListner() {
                    }

                    public void onOkListner(String str) {
                        if (Util.v(SettingsDetailFragment.this.mContext)) {
                            Intent intent = new Intent(SettingsDetailFragment.this.mContext, GaanaActivity.class);
                            intent.setFlags(71303168);
                            SettingsDetailFragment.this.mContext.startActivity(intent);
                        }
                    }
                });
            }
        }, false);
    }

    private void f(final String str) {
        this.i = (LinearLayout) this.F.findViewById(R.id.settingsContainer);
        this.i.removeAllViews();
        if (this.F != null) {
            Toolbar toolbar = (Toolbar) this.F.findViewById(R.id.main_toolbar);
            if (!(toolbar == null || toolbar.findViewById(R.id.actionbar_title) == null)) {
                ((TextView) toolbar.findViewById(R.id.actionbar_title)).setText(getString(R.string.download_my_data));
            }
        }
        View inflate = this.j.inflate(R.layout.gdpr_downloadata_bottomsheet, this.i, false);
        TextView textView = (TextView) inflate.findViewById(R.id.emailId_text);
        Button button = (Button) inflate.findViewById(R.id.button_agree);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\"");
        stringBuilder.append(str);
        stringBuilder.append("\".");
        textView.setText(stringBuilder.toString());
        final CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.agreeTnC);
        TextView textView2 = (TextView) inflate.findViewById(R.id.tnc);
        TextView textView3 = (TextView) inflate.findViewById(R.id.privacy_policy);
        textView2.setPaintFlags(textView2.getPaintFlags() | 8);
        textView3.setPaintFlags(textView2.getPaintFlags() | 8);
        textView3.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (Util.j(SettingsDetailFragment.this.mContext)) {
                    BaseGaanaFragment webViewsFragment = new WebViewsFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("WebViewContent", WebViewContent.PRIVACY_POLICY.toString());
                    webViewsFragment.setArguments(bundle);
                    ((GaanaActivity) SettingsDetailFragment.this.mContext).displayFragment(webViewsFragment);
                    return;
                }
                ap.a().f(SettingsDetailFragment.this.mContext);
            }
        });
        textView2.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (Util.j(SettingsDetailFragment.this.mContext)) {
                    BaseGaanaFragment webViewsFragment = new WebViewsFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("WebViewContent", WebViewContent.TERMS_CONDITIONS.toString());
                    webViewsFragment.setArguments(bundle);
                    ((GaanaActivity) SettingsDetailFragment.this.mContext).displayFragment(webViewsFragment);
                    return;
                }
                ap.a().f(SettingsDetailFragment.this.mContext);
            }
        });
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (checkBox.isChecked()) {
                    SettingsDetailFragment.this.g(str);
                } else {
                    aj.a().a(SettingsDetailFragment.this.mContext, SettingsDetailFragment.this.getString(R.string.agree_terms_conditions));
                }
            }
        });
        this.i.addView(inflate);
    }

    private void g(String str) {
        Util.l(this.mContext, str);
        ((BaseActivity) getActivity()).mDialog.a("", getString(R.string.email_sent_download_data), Boolean.valueOf(false), new b() {
            public void onCancelListner() {
            }

            public void onOkListner(String str) {
            }
        }, true);
    }

    private void b(String str, String str2) {
        this.i = (LinearLayout) this.F.findViewById(R.id.settingsContainer);
        this.i.removeAllViews();
        View inflate = this.j.inflate(R.layout.gdpr_downloadmydata_email_prompt, this.i, false);
        TextView textView = (TextView) inflate.findViewById(R.id.tnc_text_layout);
        ((TextView) inflate.findViewById(R.id.message_text)).setText(str);
        final CheckBox checkBox = (CheckBox) inflate.findViewById(R.id.agreeTnC);
        Button button = (Button) inflate.findViewById(R.id.button_agree);
        final EditText editText = (EditText) inflate.findViewById(R.id.email_address);
        final TextInputLayout textInputLayout = (TextInputLayout) inflate.findViewById(R.id.email_address_layout);
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        String string = getString(R.string.tnc_part_1);
        String string2 = getString(R.string.string_tnc);
        String string3 = getString(R.string.string_and);
        String string4 = getString(R.string.privacy_policy);
        String string5 = getString(R.string.tnc_part_2);
        spannableStringBuilder.append(string);
        int length = spannableStringBuilder.length();
        spannableStringBuilder.append(string2);
        spannableStringBuilder.setSpan(new UnderlineSpan(), length, spannableStringBuilder.length(), 33);
        spannableStringBuilder.setSpan(new ClickableSpan() {
            public void onClick(View view) {
                if (Util.j(SettingsDetailFragment.this.mContext)) {
                    BaseGaanaFragment webViewsFragment = new WebViewsFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("WebViewContent", WebViewContent.TERMS_CONDITIONS.toString());
                    webViewsFragment.setArguments(bundle);
                    ((GaanaActivity) SettingsDetailFragment.this.mContext).displayFragment(webViewsFragment);
                    return;
                }
                ap.a().f(SettingsDetailFragment.this.mContext);
            }
        }, length, spannableStringBuilder.length(), 33);
        spannableStringBuilder.append(string3);
        length = spannableStringBuilder.length();
        spannableStringBuilder.append(string4);
        spannableStringBuilder.setSpan(new UnderlineSpan(), length, spannableStringBuilder.length(), 33);
        spannableStringBuilder.setSpan(new ClickableSpan() {
            public void onClick(View view) {
                if (Util.j(SettingsDetailFragment.this.mContext)) {
                    BaseGaanaFragment webViewsFragment = new WebViewsFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("WebViewContent", WebViewContent.PRIVACY_POLICY.toString());
                    webViewsFragment.setArguments(bundle);
                    ((GaanaActivity) SettingsDetailFragment.this.mContext).displayFragment(webViewsFragment);
                    return;
                }
                ap.a().f(SettingsDetailFragment.this.mContext);
            }
        }, length, spannableStringBuilder.length(), 33);
        spannableStringBuilder.append(string5);
        spannableStringBuilder.append("\n");
        textView.setText(spannableStringBuilder);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        this.i.addView(inflate);
        if (this.F != null) {
            Toolbar toolbar = (Toolbar) this.F.findViewById(R.id.main_toolbar);
            if (!(toolbar == null || toolbar.findViewById(R.id.actionbar_title) == null)) {
                ((TextView) toolbar.findViewById(R.id.actionbar_title)).setText(str2);
            }
        }
        string2 = str2;
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                String obj = editText.getText().toString();
                if (TextUtils.isEmpty(obj) || !com.services.o.b(obj).booleanValue()) {
                    textInputLayout.setError(SettingsDetailFragment.this.mContext.getString(R.string.invalid_email_id));
                } else if (checkBox.isChecked()) {
                    if (TextUtils.isEmpty(string2) || !string2.equalsIgnoreCase(SettingsDetailFragment.this.getString(R.string.download_my_data))) {
                        SettingsDetailFragment.this.e(obj);
                    } else {
                        SettingsDetailFragment.this.g(obj);
                    }
                } else {
                    aj.a().a(SettingsDetailFragment.this.mContext, SettingsDetailFragment.this.getString(R.string.agree_terms_conditions));
                }
            }
        });
    }

    private View h(String str) {
        View inflate = this.j.inflate(R.layout.view_settings_listitem_titlestrip_orange, this.i, false);
        ((TextView) inflate.findViewById(R.id.titleText)).setText(str);
        ((TextView) inflate.findViewById(R.id.titleText)).setTypeface(i.a(this.mContext.getAssets(), "fonts/Roboto-Medium.ttf"));
        return inflate;
    }

    private View v() {
        return this.j.inflate(R.layout.setting_horizontal_divider, this.i, false);
    }

    public void onDestroy() {
        if (Constants.T) {
            ((GaanaActivity) this.mContext).mDrawerLayout.setDrawerLockMode(0);
            Constants.T = false;
        }
        super.onDestroy();
    }

    public void a(boolean z) {
        if (getTitle().contains(getString(R.string.playback)) && this.n != null) {
            this.n.setChecked(z);
        }
    }

    public f c() {
        return this.b;
    }
}
