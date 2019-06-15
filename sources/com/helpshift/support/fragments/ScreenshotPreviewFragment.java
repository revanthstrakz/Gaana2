package com.helpshift.support.fragments;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.helpshift.common.domain.a.a;
import com.helpshift.common.exception.RootAPIException;
import com.helpshift.conversation.dto.c;
import com.helpshift.e.f;
import com.helpshift.e.h;
import com.helpshift.e.k;
import com.helpshift.support.c.d;
import com.helpshift.support.h.e;
import com.helpshift.support.util.AppSessionConstants.Screen;
import com.helpshift.support.util.g;
import com.helpshift.util.o;

public class ScreenshotPreviewFragment extends MainFragment implements OnClickListener, a {
    private static final Screen k = Screen.SCREENSHOT_PREVIEW;
    c a;
    ProgressBar b;
    LaunchSource d;
    private d e;
    private int f;
    private ImageView g;
    private Button h;
    private View i;
    private View j;
    private String l;

    public enum LaunchSource {
        ATTACHMENT_DRAFT,
        GALLERY_APP
    }

    public enum ScreenshotAction {
        ADD,
        SEND,
        REMOVE,
        CHANGE
    }

    public boolean b() {
        return true;
    }

    public static ScreenshotPreviewFragment a(d dVar) {
        ScreenshotPreviewFragment screenshotPreviewFragment = new ScreenshotPreviewFragment();
        screenshotPreviewFragment.e = dVar;
        return screenshotPreviewFragment;
    }

    public void a(@NonNull Bundle bundle, c cVar, LaunchSource launchSource) {
        this.f = bundle.getInt("key_screenshot_mode");
        this.l = bundle.getString("key_refers_id");
        this.a = cVar;
        this.d = launchSource;
        a();
    }

    public void b(d dVar) {
        this.e = dVar;
    }

    @Nullable
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return layoutInflater.inflate(h.hs__screenshot_preview_fragment, viewGroup, false);
    }

    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        this.g = (ImageView) view.findViewById(f.screenshot_preview);
        ((Button) view.findViewById(f.change)).setOnClickListener(this);
        this.h = (Button) view.findViewById(f.secondary_button);
        this.h.setOnClickListener(this);
        this.b = (ProgressBar) view.findViewById(f.screenshot_loading_indicator);
        this.i = view.findViewById(f.button_containers);
        this.j = view.findViewById(f.buttons_separator);
    }

    public void onStart() {
        super.onStart();
        e.b().a("current_open_screen", k);
    }

    public void onResume() {
        super.onResume();
        a(this.h, this.f);
        a();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == 1 && i == 4 && ScreenshotPreviewFragment.this.d == LaunchSource.GALLERY_APP) {
                    o.d().u().a(ScreenshotPreviewFragment.this.a);
                }
                return false;
            }
        });
    }

    public void onStop() {
        super.onStop();
        Screen screen = (Screen) e.b().a("current_open_screen");
        if (screen != null && screen.equals(k)) {
            e.b().b("current_open_screen");
        }
    }

    private static void a(Button button, int i) {
        CharSequence string;
        Resources resources = button.getResources();
        switch (i) {
            case 1:
                string = resources.getString(k.hs__screenshot_add);
                break;
            case 2:
                string = resources.getString(k.hs__screenshot_remove);
                break;
            case 3:
                string = resources.getString(k.hs__send_msg_btn);
                break;
            default:
                string = "";
                break;
        }
        button.setText(string);
    }

    private void a() {
        if (isResumed()) {
            if (this.a == null) {
                if (this.e != null) {
                    this.e.b();
                }
            } else if (this.a.b != null) {
                a(this.a.b);
            } else if (this.a.a != null) {
                a(true);
                o.d().u().a(this.a, this.l, this);
            }
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(String str) {
        Bitmap a = com.helpshift.support.util.a.a(str, -1);
        if (a != null) {
            this.g.setImageBitmap(a);
        } else if (this.e != null) {
            this.e.b();
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == f.secondary_button && this.a != null) {
            switch (this.f) {
                case 1:
                    this.e.a(this.a);
                    return;
                case 2:
                    o.d().u().a(this.a);
                    this.e.a();
                    return;
                case 3:
                    this.e.a(this.a, this.l);
                    return;
                default:
                    return;
            }
        } else if (id == f.change) {
            if (this.f == 2) {
                this.f = 1;
            }
            o.d().u().a(this.a);
            Bundle bundle = new Bundle();
            bundle.putInt("key_screenshot_mode", this.f);
            bundle.putString("key_refers_id", this.l);
            this.e.a(bundle);
        }
    }

    public void a(RootAPIException rootAPIException) {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    ScreenshotPreviewFragment.this.b.setVisibility(8);
                    g.a(ScreenshotPreviewFragment.this.getView(), k.hs__screenshot_cloud_attach_error, -2);
                }
            });
        }
    }

    public void onPause() {
        g.a(getView());
        super.onPause();
    }

    public void a(final c cVar) {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                public void run() {
                    ScreenshotPreviewFragment.this.a(false);
                    ScreenshotPreviewFragment.this.a(cVar.b);
                }
            });
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void a(boolean z) {
        if (z) {
            this.b.setVisibility(0);
            this.i.setVisibility(8);
            this.j.setVisibility(8);
            this.g.setVisibility(8);
            return;
        }
        this.b.setVisibility(8);
        this.i.setVisibility(0);
        this.j.setVisibility(0);
        this.g.setVisibility(0);
    }
}
