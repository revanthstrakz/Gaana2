package com.cast_music.tracks.ui;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import com.cast_music.VideoCastManager;
import com.cast_music.b.d;
import com.facebook.share.internal.ShareConstants;
import com.gaana.R;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaTrack;
import java.util.ArrayList;
import java.util.List;

public class TracksChooserDialog extends DialogFragment {
    private VideoCastManager a;
    private long[] b = null;
    private MediaInfo c;
    private a d;
    private a e;
    private List<MediaTrack> f = new ArrayList();
    private List<MediaTrack> g = new ArrayList();
    private List<MediaTrack> h = new ArrayList();
    private int i = 0;
    private int j = -1;

    @NonNull
    public Dialog onCreateDialog(Bundle bundle) {
        Builder builder = new Builder(getActivity());
        View inflate = getActivity().getLayoutInflater().inflate(R.layout.custom_tracks_dialog_layout, null);
        a(inflate);
        builder.setView(inflate).setPositiveButton(getString(R.string.ccl_ok), new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                List arrayList = new ArrayList();
                MediaTrack a = TracksChooserDialog.this.d.a();
                if (a.getId() != -1) {
                    arrayList.add(a);
                }
                a = TracksChooserDialog.this.e.a();
                if (a != null) {
                    arrayList.add(a);
                }
                if (!TracksChooserDialog.this.h.isEmpty()) {
                    Object obj = null;
                    for (MediaTrack mediaTrack : TracksChooserDialog.this.h) {
                        for (long valueOf : TracksChooserDialog.this.a.U()) {
                            if (mediaTrack.getId() == Long.valueOf(valueOf).longValue()) {
                                arrayList.add(mediaTrack);
                                obj = 1;
                                continue;
                                break;
                            }
                        }
                        if (obj != null) {
                            break;
                        }
                    }
                }
                TracksChooserDialog.this.a.b(arrayList);
                TracksChooserDialog.this.getDialog().cancel();
            }
        }).setNegativeButton(R.string.ccl_cancel, new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                TracksChooserDialog.this.getDialog().cancel();
            }
        }).setOnCancelListener(new OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                TracksChooserDialog.this.getDialog().cancel();
            }
        });
        return builder.create();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setRetainInstance(true);
        this.c = d.a(getArguments().getBundle(ShareConstants.WEB_DIALOG_PARAM_MEDIA));
        this.a = VideoCastManager.y();
        this.b = this.a.U();
        List mediaTracks = this.c.getMediaTracks();
        if (mediaTracks == null || mediaTracks.isEmpty()) {
            d.a(getActivity(), (int) R.string.ccl_caption_no_tracks_available);
            dismiss();
        }
    }

    public void onDestroyView() {
        if (getDialog() != null && getRetainInstance()) {
            getDialog().setDismissMessage(null);
        }
        super.onDestroyView();
    }

    private void a(View view) {
        ListView listView = (ListView) view.findViewById(R.id.listview1);
        ListView listView2 = (ListView) view.findViewById(R.id.listview2);
        TextView textView = (TextView) view.findViewById(R.id.text_empty_message);
        TextView textView2 = (TextView) view.findViewById(R.id.audio_empty_message);
        b();
        this.d = new a(getActivity(), R.layout.tracks_row_layout, this.f, this.i);
        this.e = new a(getActivity(), R.layout.tracks_row_layout, this.g, this.j);
        listView.setAdapter(this.d);
        listView2.setAdapter(this.e);
        TabHost tabHost = (TabHost) view.findViewById(R.id.tabhost);
        tabHost.setup();
        TabSpec newTabSpec = tabHost.newTabSpec("tab1");
        if (this.f == null || this.f.isEmpty()) {
            listView.setVisibility(4);
            newTabSpec.setContent(R.id.text_empty_message);
        } else {
            textView.setVisibility(4);
            newTabSpec.setContent(R.id.listview1);
        }
        newTabSpec.setIndicator(getString(R.string.ccl_caption_subtitles));
        tabHost.addTab(newTabSpec);
        TabSpec newTabSpec2 = tabHost.newTabSpec("tab2");
        if (this.g == null || this.g.isEmpty()) {
            listView2.setVisibility(4);
            newTabSpec2.setContent(R.id.audio_empty_message);
        } else {
            textView2.setVisibility(4);
            newTabSpec2.setContent(R.id.listview2);
        }
        newTabSpec2.setIndicator(getString(R.string.ccl_caption_audio));
        tabHost.addTab(newTabSpec2);
    }

    private MediaTrack a() {
        return new MediaTrack.Builder(-1, 1).setName(getString(R.string.ccl_none)).setSubtype(2).setContentId("").build();
    }

    private void b() {
        List<MediaTrack> mediaTracks = this.c.getMediaTracks();
        this.g.clear();
        this.f.clear();
        this.h.clear();
        this.f.add(a());
        this.i = 0;
        this.j = -1;
        if (mediaTracks != null) {
            int i = 1;
            int i2 = 0;
            for (MediaTrack mediaTrack : mediaTracks) {
                switch (mediaTrack.getType()) {
                    case 1:
                        this.f.add(mediaTrack);
                        if (this.b != null) {
                            for (long j : this.b) {
                                if (j == mediaTrack.getId()) {
                                    this.i = i;
                                }
                            }
                        }
                        i++;
                        break;
                    case 2:
                        this.g.add(mediaTrack);
                        if (this.b != null) {
                            for (long j2 : this.b) {
                                if (j2 == mediaTrack.getId()) {
                                    this.j = i2;
                                }
                            }
                        }
                        i2++;
                        break;
                    case 3:
                        this.h.add(mediaTrack);
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
