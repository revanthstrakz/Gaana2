package com.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.constants.Constants;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.lrc.DefaultLrcBuilder;
import com.gaana.lrc.LrcRow;
import com.gaana.models.BusinessObject;
import com.gaana.models.LyricsObject;
import com.i.i;
import com.library.managers.TaskManager.TaskListner;
import com.managers.URLManager;
import com.managers.aj;
import com.services.f;
import com.services.f.b;
import com.services.h;
import com.services.l.af;
import com.utilities.Util;
import com.utilities.k;
import java.util.ArrayList;
import java.util.List;

public class LyricsDisplayFragment extends BaseGaanaFragment implements OnClickListener, a {
    private View a = null;
    private TextView b;
    private String c;
    private List<LrcRow> d;
    private String e;
    private LinearLayout f;
    private LyricsObject g;

    public void setGAScreenName(String str, String str2) {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.a == null) {
            super.onCreateView(layoutInflater, viewGroup, bundle);
            this.a = layoutInflater.inflate(R.layout.lyrics_display_fragment, viewGroup, false);
            a(getArguments());
        }
        return this.a;
    }

    private void a(Bundle bundle) {
        this.b = (TextView) this.a.findViewById(R.id.lyrics_text);
        this.b.setTypeface(Util.i(this.mContext));
        this.g = (LyricsObject) bundle.getSerializable("lyrics_object");
        c();
        b();
    }

    private String a(String str) {
        try {
            byte[] d = new k(Constants.bx).d(str);
            if (d != null) {
                return new String(d);
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    private void b() {
        String lyricsUrl = this.g.getLyricsUrl();
        final int lyricsType = this.g.getLyricsType();
        if (Util.j(this.mContext) && !GaanaApplication.getInstance().isAppInOfflineMode()) {
            ((GaanaActivity) this.mContext).showProgressDialog();
            URLManager uRLManager = new URLManager();
            uRLManager.a(lyricsUrl);
            uRLManager.b(false);
            uRLManager.a(String.class);
            i.a().a(new af() {
                public void onRetreivalComplete(Object obj) {
                    LyricsDisplayFragment.this.c = (String) obj;
                    if (lyricsType == 2) {
                        LyricsDisplayFragment.this.c = LyricsDisplayFragment.this.a(LyricsDisplayFragment.this.c);
                    }
                    h.a().a(new TaskListner() {
                        public void doBackGroundTask() {
                            int i = 0;
                            switch (lyricsType) {
                                case 2:
                                    LyricsDisplayFragment.this.d = new DefaultLrcBuilder().getLrcRows(LyricsDisplayFragment.this.c);
                                    while (i < LyricsDisplayFragment.this.d.size()) {
                                        LyricsDisplayFragment lyricsDisplayFragment;
                                        StringBuilder stringBuilder;
                                        if (LyricsDisplayFragment.this.e == null) {
                                            lyricsDisplayFragment = LyricsDisplayFragment.this;
                                            stringBuilder = new StringBuilder();
                                            stringBuilder.append(((LrcRow) LyricsDisplayFragment.this.d.get(i)).content);
                                            stringBuilder.append("\n");
                                            lyricsDisplayFragment.e = stringBuilder.toString();
                                        } else {
                                            lyricsDisplayFragment = LyricsDisplayFragment.this;
                                            stringBuilder = new StringBuilder();
                                            stringBuilder.append(LyricsDisplayFragment.this.e);
                                            stringBuilder.append(((LrcRow) LyricsDisplayFragment.this.d.get(i)).content);
                                            stringBuilder.append("\n");
                                            lyricsDisplayFragment.e = stringBuilder.toString();
                                        }
                                        i++;
                                    }
                                    return;
                                case 3:
                                    LyricsDisplayFragment.this.d = new ArrayList();
                                    String[] split = LyricsDisplayFragment.this.c.split("\n");
                                    int length = split.length;
                                    int i2 = 0;
                                    while (i < length) {
                                        i2++;
                                        LyricsDisplayFragment.this.d.add(new LrcRow(null, (long) i2, split[i]));
                                        i++;
                                    }
                                    LyricsDisplayFragment.this.e = LyricsDisplayFragment.this.c;
                                    return;
                                default:
                                    LyricsDisplayFragment.this.d = null;
                                    return;
                            }
                        }

                        public void onBackGroundTaskCompleted() {
                            switch (lyricsType) {
                                case 2:
                                case 3:
                                    LyricsDisplayFragment.this.b.setText(LyricsDisplayFragment.this.e);
                                    ((GaanaActivity) LyricsDisplayFragment.this.mContext).hideProgressDialog();
                                    return;
                                default:
                                    ((GaanaActivity) LyricsDisplayFragment.this.mContext).hideProgressDialog();
                                    return;
                            }
                        }
                    }, -1);
                }

                public void onErrorResponse(BusinessObject businessObject) {
                    LyricsDisplayFragment.this.c = null;
                    LyricsDisplayFragment.this.e = null;
                    LyricsDisplayFragment.this.d = null;
                    ((GaanaActivity) LyricsDisplayFragment.this.mContext).hideProgressDialog();
                }
            }, uRLManager);
        }
    }

    private void c() {
        this.f = (LinearLayout) this.a.findViewById(R.id.toolbar);
        this.f.findViewById(R.id.menu_icon_cross).setOnClickListener(this);
        this.f.findViewById(R.id.report_lrc_button).setOnClickListener(this);
        ((TextView) this.f.findViewById(R.id.track_name)).setTypeface(Util.i(this.mContext));
        ((TextView) this.f.findViewById(R.id.track_name)).setText(this.g.getTrackName());
        TextView textView = (TextView) this.f.findViewById(R.id.albumText);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.g.getTrackAlbumName());
        stringBuilder.append(" - ");
        stringBuilder.append(this.g.getArtistNames());
        textView.setText(stringBuilder.toString());
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.menu_icon_cross) {
            ((GaanaActivity) this.mContext).homeIconClick();
        } else if (id == R.id.report_lrc_button) {
            a();
        }
    }

    public void a() {
        new f(this.mContext).a(this.mContext.getString(R.string.gaana_text), getResources().getString(R.string.report_lyrics_text), Boolean.valueOf(true), getString(R.string.yes), getString(R.string.no), new b() {
            public void onCancelListner() {
            }

            public void onOkListner(String str) {
                URLManager uRLManager = new URLManager();
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("https://api.gaana.com/lyrics/report?track_id=");
                stringBuilder.append(LyricsDisplayFragment.this.g.getId());
                uRLManager.a(stringBuilder.toString());
                i.a().a(new af() {
                    public void onErrorResponse(BusinessObject businessObject) {
                    }

                    public void onRetreivalComplete(Object obj) {
                        aj.a().a(LyricsDisplayFragment.this.mContext, LyricsDisplayFragment.this.mContext.getResources().getString(R.string.thanks_for_report));
                    }
                }, uRLManager);
            }
        });
    }
}
