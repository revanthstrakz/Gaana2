package com.gaanavideo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.gaana.R;
import com.library.controls.CrossFadeImageView;
import com.managers.u;
import java.lang.ref.WeakReference;
import java.util.Formatter;
import java.util.Locale;

public class VideoControllerView extends RelativeLayout implements OnClickListener {
    private OnSeekBarChangeListener A;
    private OnClickListener B;
    private OnClickListener C;
    StringBuilder a;
    Formatter b;
    private a c;
    private Context d;
    private ViewGroup e;
    private View f;
    private ProgressBar g;
    private TextView h;
    private TextView i;
    private boolean j;
    private boolean k;
    private boolean l;
    private boolean m;
    private boolean n;
    private OnClickListener o;
    private OnClickListener p;
    private ImageButton q;
    private ImageButton r;
    private ImageButton s;
    private ImageButton t;
    private ImageButton u;
    private ImageButton v;
    private Handler w;
    private boolean x;
    private boolean y;
    private String z;

    public interface a {
        void a();

        void a(int i);

        void b();

        int c();

        int d();

        boolean e();

        boolean f();

        boolean g();

        boolean h();

        boolean i();

        void j();
    }

    private static class b extends Handler {
        private final WeakReference<VideoControllerView> a;

        b(VideoControllerView videoControllerView) {
            this.a = new WeakReference(videoControllerView);
        }

        public void handleMessage(Message message) {
            VideoControllerView videoControllerView = (VideoControllerView) this.a.get();
            if (videoControllerView != null && videoControllerView.c != null) {
                switch (message.what) {
                    case 1:
                        videoControllerView.d();
                        break;
                    case 2:
                        int f = videoControllerView.f();
                        if (!videoControllerView.k && videoControllerView.j && videoControllerView.c.e()) {
                            sendMessageDelayed(obtainMessage(2), (long) (1000 - (f % 1000)));
                            break;
                        }
                }
            }
        }
    }

    public VideoControllerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.w = new b(this);
        this.x = false;
        this.y = false;
        this.A = new OnSeekBarChangeListener() {
            public void onStartTrackingTouch(SeekBar seekBar) {
                VideoControllerView.this.a(3600000);
                VideoControllerView.this.k = true;
                VideoControllerView.this.w.removeMessages(2);
            }

            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (VideoControllerView.this.c != null && z) {
                    i = (int) ((((long) VideoControllerView.this.c.c()) * ((long) i)) / 1000);
                    VideoControllerView.this.c.a(i);
                    if (VideoControllerView.this.i != null) {
                        VideoControllerView.this.i.setText(VideoControllerView.this.b(i));
                    }
                }
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
                VideoControllerView.this.k = false;
                VideoControllerView.this.f();
                VideoControllerView.this.g();
                VideoControllerView.this.a(3000);
                VideoControllerView.this.w.sendEmptyMessage(2);
            }
        };
        this.B = new OnClickListener() {
            public void onClick(View view) {
                if (VideoControllerView.this.c != null) {
                    VideoControllerView.this.c.a(VideoControllerView.this.c.d() - 5000);
                    VideoControllerView.this.f();
                    VideoControllerView.this.a(3000);
                }
            }
        };
        this.C = new OnClickListener() {
            public void onClick(View view) {
                if (VideoControllerView.this.c != null) {
                    VideoControllerView.this.c.a(VideoControllerView.this.c.d() + 15000);
                    VideoControllerView.this.f();
                    VideoControllerView.this.a(3000);
                }
            }
        };
        this.f = null;
        this.d = context;
        this.l = true;
        this.m = true;
    }

    public VideoControllerView(Context context, boolean z) {
        super(context);
        this.w = new b(this);
        this.x = false;
        this.y = false;
        this.A = /* anonymous class already generated */;
        this.B = /* anonymous class already generated */;
        this.C = /* anonymous class already generated */;
        this.d = context;
        this.l = z;
    }

    public VideoControllerView(Context context) {
        this(context, true);
    }

    public void onFinishInflate() {
        if (this.f != null) {
            a(this.f);
        }
    }

    public void setMediaPlayer(a aVar) {
        this.c = aVar;
        g();
        h();
    }

    public void setAnchorView(ViewGroup viewGroup) {
        this.e = viewGroup;
        LayoutParams layoutParams = new LayoutParams(-1, -1);
        removeAllViews();
        addView(a(), layoutParams);
    }

    /* Access modifiers changed, original: protected */
    public View a() {
        this.f = ((LayoutInflater) this.d.getSystemService("layout_inflater")).inflate(R.layout.media_controller, null);
        a(this.f);
        return this.f;
    }

    public void setShareUrl(String str) {
        this.z = str;
    }

    private void a(View view) {
        ((CrossFadeImageView) view.findViewById(R.id.cross_video_controller)).setOnClickListener(this);
        ((CrossFadeImageView) view.findViewById(R.id.share_video_controller)).setOnClickListener(this);
        this.q = (ImageButton) view.findViewById(R.id.pause);
        if (this.q != null) {
            this.q.requestFocus();
            this.q.setOnClickListener(this);
        }
        this.v = (ImageButton) view.findViewById(R.id.fullscreen);
        if (this.v != null) {
            this.v.requestFocus();
            this.v.setOnClickListener(this);
        }
        this.g = (SeekBar) view.findViewById(R.id.mediacontroller_progress);
        if (this.g != null) {
            if (this.g instanceof SeekBar) {
                ((SeekBar) this.g).setOnSeekBarChangeListener(this.A);
            }
            this.g.setMax(1000);
        }
        this.h = (TextView) view.findViewById(R.id.time);
        this.i = (TextView) view.findViewById(R.id.time_current);
        this.a = new StringBuilder();
        this.b = new Formatter(this.a, Locale.getDefault());
        l();
    }

    public void b() {
        a(3000);
    }

    private void i() {
        if (this.c != null) {
            try {
                if (!(this.q == null || this.c.f())) {
                    this.q.setEnabled(false);
                }
                if (!(this.s == null || this.c.g())) {
                    this.s.setEnabled(false);
                }
                if (!(this.r == null || this.c.h())) {
                    this.r.setEnabled(false);
                }
            } catch (IncompatibleClassChangeError unused) {
            }
        }
    }

    public void a(int i) {
        if (!(this.j || this.e == null)) {
            f();
            if (this.q != null) {
                this.q.requestFocus();
            }
            i();
            this.e.addView(this, new LayoutParams(-1, -1, 80));
            this.j = true;
        }
        g();
        h();
        this.w.sendEmptyMessage(2);
        Message obtainMessage = this.w.obtainMessage(1);
        if (i == 0) {
            return;
        }
        if (this.c == null || this.c.e()) {
            this.w.removeMessages(1);
            this.w.sendMessageDelayed(obtainMessage, (long) i);
        }
    }

    public boolean c() {
        return this.j;
    }

    public void d() {
        if (this.e != null) {
            try {
                this.e.removeView(this);
                this.w.removeMessages(2);
            } catch (IllegalArgumentException unused) {
            }
            this.j = false;
        }
    }

    public void e() {
        if (c()) {
            d();
        } else {
            b();
        }
    }

    private String b(int i) {
        i /= 1000;
        int i2 = i % 60;
        int i3 = (i / 60) % 60;
        i /= 3600;
        this.a.setLength(0);
        if (i > 0) {
            return this.b.format("%d:%02d:%02d", new Object[]{Integer.valueOf(i), Integer.valueOf(i3), Integer.valueOf(i2)}).toString();
        }
        return this.b.format("%02d:%02d", new Object[]{Integer.valueOf(i3), Integer.valueOf(i2)}).toString();
    }

    public void setMediaPlayerPreparing(boolean z) {
        this.x = z;
    }

    public int f() {
        if (this.c == null || this.k || this.x) {
            return 0;
        }
        int d = this.c.d();
        int c = this.c.c();
        if (this.g != null && c > 0) {
            this.g.setProgress((int) ((1000 * ((long) d)) / ((long) c)));
        }
        if (this.h != null) {
            this.h.setText(b(c));
        }
        if (this.i != null) {
            this.i.setText(b(d));
        }
        return d;
    }

    public void setSeekbarBufferProgress(int i) {
        this.g.setSecondaryProgress(i * 10);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            e();
        }
        return true;
    }

    public boolean onTrackballEvent(MotionEvent motionEvent) {
        a(3000);
        return false;
    }

    public boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (this.c == null) {
            return true;
        }
        int keyCode = keyEvent.getKeyCode();
        boolean z = keyEvent.getRepeatCount() == 0 && keyEvent.getAction() == 0;
        if (keyCode == 79 || keyCode == 85 || keyCode == 62) {
            if (z) {
                j();
                a(3000);
                if (this.q != null) {
                    this.q.requestFocus();
                }
            }
            return true;
        } else if (keyCode == 126) {
            if (z && !this.c.e()) {
                this.c.a();
                g();
                a(3000);
            }
            return true;
        } else if (keyCode == 86 || keyCode == 127) {
            if (z && this.c.e()) {
                this.c.b();
                g();
                a(3000);
            }
            return true;
        } else if (keyCode == 25 || keyCode == 24 || keyCode == 164) {
            return super.dispatchKeyEvent(keyEvent);
        } else {
            if (keyCode == 4 || keyCode == 82) {
                if (z) {
                    d();
                }
                return true;
            }
            a(3000);
            return super.dispatchKeyEvent(keyEvent);
        }
    }

    public void setMediaCompletionStatus(boolean z) {
        this.y = z;
    }

    public void g() {
        if (this.f != null && this.q != null && this.c != null) {
            if (this.c.e()) {
                this.q.setImageResource(R.drawable.video_pause_icon);
            } else {
                this.c.d();
                this.c.c();
                if (this.y) {
                    this.q.setImageResource(R.drawable.video_replay_icon);
                } else {
                    this.q.setImageResource(R.drawable.video_play_icon);
                }
            }
        }
    }

    public void h() {
        if (this.f != null && this.v != null && this.c != null) {
            if (this.c.i()) {
                this.v.setImageResource(R.drawable.ic_media_fullscreen_shrink);
            } else {
                this.v.setImageResource(R.drawable.ic_media_fullscreen_stretch);
            }
        }
    }

    private void j() {
        if (this.c != null) {
            if (this.c.e()) {
                this.c.b();
            } else {
                this.c.a();
            }
            g();
        }
    }

    private void k() {
        if (this.c != null) {
            this.c.j();
        }
    }

    public void setEnabled(boolean z) {
        ImageButton imageButton;
        if (this.q != null) {
            this.q.setEnabled(z);
        }
        if (this.r != null) {
            this.r.setEnabled(z);
        }
        if (this.s != null) {
            this.s.setEnabled(z);
        }
        boolean z2 = false;
        if (this.t != null) {
            imageButton = this.t;
            boolean z3 = z && this.o != null;
            imageButton.setEnabled(z3);
        }
        if (this.u != null) {
            imageButton = this.u;
            if (z && this.p != null) {
                z2 = true;
            }
            imageButton.setEnabled(z2);
        }
        if (this.g != null) {
            this.g.setEnabled(z);
        }
        i();
        super.setEnabled(z);
    }

    public void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        accessibilityEvent.setClassName(VideoControllerView.class.getName());
    }

    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo accessibilityNodeInfo) {
        super.onInitializeAccessibilityNodeInfo(accessibilityNodeInfo);
        accessibilityNodeInfo.setClassName(VideoControllerView.class.getName());
    }

    private void l() {
        boolean z = false;
        if (this.t != null) {
            this.t.setOnClickListener(this.o);
            this.t.setEnabled(this.o != null);
        }
        if (this.u != null) {
            this.u.setOnClickListener(this.p);
            ImageButton imageButton = this.u;
            if (this.p != null) {
                z = true;
            }
            imageButton.setEnabled(z);
        }
    }

    public void setPrevNextListeners(OnClickListener onClickListener, OnClickListener onClickListener2) {
        this.o = onClickListener;
        this.p = onClickListener2;
        this.n = true;
        if (this.f != null) {
            l();
            if (!(this.t == null || this.m)) {
                this.t.setVisibility(0);
            }
            if (this.u != null && !this.m) {
                this.u.setVisibility(0);
            }
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.cross_video_controller) {
            ((Activity) this.d).finish();
        } else if (id == R.id.fullscreen) {
            k();
            a(3000);
        } else if (id == R.id.pause) {
            j();
            a(3000);
        } else if (id == R.id.share_video_controller) {
            u.a().b("YIM_Video", "YIM_Video_Share_OP");
            Intent intent = new Intent("android.intent.action.SEND");
            intent.setType("text/plain");
            String str = this.z;
            intent.putExtra("android.intent.extra.SUBJECT", this.d.getString(R.string.my_year_in_gaana));
            intent.putExtra("android.intent.extra.TEXT", str);
            this.d.startActivity(Intent.createChooser(intent, this.d.getString(R.string.share_via)));
        }
    }
}
