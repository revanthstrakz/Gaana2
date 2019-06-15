package com.gaana;

import android.app.Activity;
import android.content.AsyncQueryHandler;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore.Audio.Media;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import com.b.b;
import com.constants.Constants;
import com.facebook.share.internal.ShareConstants;
import com.moe.pushlibrary.providers.MoEDataContract.BaseColumns;
import com.services.d;
import java.io.IOException;

public class GaanaAudioPreview extends Activity implements OnCompletionListener, OnErrorListener, OnPreparedListener {
    private static final int OPEN_IN_MUSIC = 1;
    private static final String TAG = "AudioPreview";
    private OnAudioFocusChangeListener mAudioFocusListener = new OnAudioFocusChangeListener() {
        public void onAudioFocusChange(int i) {
            if (GaanaAudioPreview.this.mPlayer == null) {
                GaanaAudioPreview.this.mAudioManager.abandonAudioFocus(this);
                return;
            }
            if (i != 1) {
                switch (i) {
                    case -3:
                    case -2:
                        if (GaanaAudioPreview.this.mPlayer.isPlaying()) {
                            GaanaAudioPreview.this.mPausedByTransientLossOfFocus = true;
                            GaanaAudioPreview.this.mPlayer.pause();
                            break;
                        }
                        break;
                    case -1:
                        GaanaAudioPreview.this.mPausedByTransientLossOfFocus = false;
                        GaanaAudioPreview.this.mPlayer.pause();
                        break;
                }
            } else if (GaanaAudioPreview.this.mPausedByTransientLossOfFocus) {
                GaanaAudioPreview.this.mPausedByTransientLossOfFocus = false;
                GaanaAudioPreview.this.start();
            }
            GaanaAudioPreview.this.updatePlayPause();
        }
    };
    private AudioManager mAudioManager;
    private int mDuration;
    private TextView mLoadingText;
    private long mMediaId = -1;
    private boolean mPausedByTransientLossOfFocus;
    private PreviewPlayer mPlayer;
    private Handler mProgressRefresher;
    private SeekBar mSeekBar;
    private OnSeekBarChangeListener mSeekListener = new OnSeekBarChangeListener() {
        public void onStartTrackingTouch(SeekBar seekBar) {
            GaanaAudioPreview.this.mSeeking = true;
        }

        public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
            if (z && GaanaAudioPreview.this.mPlayer != null) {
                GaanaAudioPreview.this.mPlayer.seekTo(i);
            }
        }

        public void onStopTrackingTouch(SeekBar seekBar) {
            GaanaAudioPreview.this.mSeeking = false;
        }
    };
    private boolean mSeeking = false;
    private TextView mTextLine1;
    private TextView mTextLine2;
    private boolean mUiPaused = true;
    private Uri mUri;

    private static class PreviewPlayer extends MediaPlayer implements OnPreparedListener {
        GaanaAudioPreview mActivity;
        boolean mIsPrepared;

        private PreviewPlayer() {
            this.mIsPrepared = false;
        }

        /* synthetic */ PreviewPlayer(AnonymousClass1 anonymousClass1) {
            this();
        }

        public void setActivity(GaanaAudioPreview gaanaAudioPreview) {
            this.mActivity = gaanaAudioPreview;
            setOnPreparedListener(this);
            setOnErrorListener(this.mActivity);
            setOnCompletionListener(this.mActivity);
        }

        public void setDataSourceAndPrepare(Uri uri) throws IllegalArgumentException, SecurityException, IllegalStateException, IOException {
            setDataSource(this.mActivity, uri);
            prepareAsync();
        }

        public void onPrepared(MediaPlayer mediaPlayer) {
            this.mIsPrepared = true;
            this.mActivity.onPrepared(mediaPlayer);
        }

        /* Access modifiers changed, original: 0000 */
        public boolean isPrepared() {
            return this.mIsPrepared;
        }
    }

    class ProgressRefresher implements Runnable {
        ProgressRefresher() {
        }

        public void run() {
            if (!(GaanaAudioPreview.this.mPlayer == null || GaanaAudioPreview.this.mSeeking || GaanaAudioPreview.this.mDuration == 0)) {
                GaanaAudioPreview.this.mSeekBar.setProgress(GaanaAudioPreview.this.mPlayer.getCurrentPosition());
            }
            GaanaAudioPreview.this.mProgressRefresher.removeCallbacksAndMessages(null);
            if (!GaanaAudioPreview.this.mUiPaused) {
                GaanaAudioPreview.this.mProgressRefresher.postDelayed(new ProgressRefresher(), 200);
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(b.a(context));
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Constants.Q = d.a().b("PREFERENCE_IS_LOCAL_MEDIA", true, false);
        if (Constants.Q) {
            Intent intent = getIntent();
            if (intent == null) {
                finish();
                return;
            }
            this.mUri = intent.getData();
            if (this.mUri == null) {
                finish();
                return;
            }
            String scheme = this.mUri.getScheme();
            setVolumeControlStream(3);
            requestWindowFeature(1);
            setContentView(R.layout.audiopreview);
            this.mTextLine1 = (TextView) findViewById(R.id.line1);
            this.mTextLine2 = (TextView) findViewById(R.id.line2);
            this.mLoadingText = (TextView) findViewById(R.id.loading);
            if (scheme.equals("http")) {
                this.mLoadingText.setText(R.string.connecting_msg);
            } else {
                this.mLoadingText.setVisibility(8);
            }
            this.mSeekBar = (SeekBar) findViewById(R.id.progress);
            this.mProgressRefresher = new Handler();
            this.mAudioManager = (AudioManager) getSystemService("audio");
            PreviewPlayer previewPlayer = (PreviewPlayer) getLastNonConfigurationInstance();
            if (previewPlayer == null) {
                this.mPlayer = new PreviewPlayer();
                this.mPlayer.setActivity(this);
                try {
                    this.mPlayer.setDataSourceAndPrepare(this.mUri);
                } catch (Exception unused) {
                    Toast.makeText(this, R.string.unable_to_play, 0).show();
                    finish();
                    return;
                }
            }
            this.mPlayer = previewPlayer;
            this.mPlayer.setActivity(this);
            AnonymousClass1 anonymousClass1 = new AsyncQueryHandler(getContentResolver()) {
                /* Access modifiers changed, original: protected */
                public void onQueryComplete(int i, Object obj, Cursor cursor) {
                    if (cursor != null && cursor.moveToFirst()) {
                        i = cursor.getColumnIndex("title");
                        int columnIndex = cursor.getColumnIndex("artist");
                        int columnIndex2 = cursor.getColumnIndex(BaseColumns._ID);
                        int columnIndex3 = cursor.getColumnIndex("_display_name");
                        if (columnIndex2 >= 0) {
                            GaanaAudioPreview.this.mMediaId = cursor.getLong(columnIndex2);
                        }
                        if (i >= 0) {
                            GaanaAudioPreview.this.mTextLine1.setText(cursor.getString(i));
                            if (columnIndex >= 0) {
                                GaanaAudioPreview.this.mTextLine2.setText(cursor.getString(columnIndex));
                            }
                        } else if (columnIndex3 >= 0) {
                            GaanaAudioPreview.this.mTextLine1.setText(cursor.getString(columnIndex3));
                        }
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                    GaanaAudioPreview.this.setNames();
                }
            };
            if (scheme.equals("content")) {
                if (this.mUri.getAuthority() == ShareConstants.WEB_DIALOG_PARAM_MEDIA) {
                    anonymousClass1.startQuery(0, null, this.mUri, new String[]{"title", "artist"}, null, null, null);
                } else {
                    anonymousClass1.startQuery(0, null, this.mUri, null, null, null, null);
                }
            } else if (scheme.equals("file")) {
                scheme = this.mUri.getPath();
                int i = 0;
                anonymousClass1.startQuery(i, null, Media.EXTERNAL_CONTENT_URI, new String[]{BaseColumns._ID, "title", "artist"}, "_data=?", new String[]{scheme}, null);
            } else if (this.mPlayer.isPrepared()) {
                setNames();
            }
            return;
        }
        startActivity(new Intent(this, SplashScreenActivity.class));
        finish();
    }

    public void onPause() {
        super.onPause();
        this.mUiPaused = true;
        if (this.mProgressRefresher != null) {
            this.mProgressRefresher.removeCallbacksAndMessages(null);
        }
    }

    public void onResume() {
        super.onResume();
        this.mUiPaused = false;
        if (this.mPlayer.isPrepared()) {
            showPostPrepareUI();
        }
    }

    public Object onRetainNonConfigurationInstance() {
        PreviewPlayer previewPlayer = this.mPlayer;
        this.mPlayer = null;
        return previewPlayer;
    }

    public void onDestroy() {
        stopPlayback();
        super.onDestroy();
    }

    private void stopPlayback() {
        if (this.mProgressRefresher != null) {
            this.mProgressRefresher.removeCallbacksAndMessages(null);
        }
        if (this.mPlayer != null) {
            this.mPlayer.release();
            this.mPlayer = null;
            this.mAudioManager.abandonAudioFocus(this.mAudioFocusListener);
        }
    }

    public void onUserLeaveHint() {
        stopPlayback();
        finish();
        super.onUserLeaveHint();
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        if (!isFinishing()) {
            this.mPlayer = (PreviewPlayer) mediaPlayer;
            setNames();
            this.mPlayer.start();
            showPostPrepareUI();
        }
    }

    private void showPostPrepareUI() {
        ((ProgressBar) findViewById(R.id.spinner)).setVisibility(8);
        this.mDuration = this.mPlayer.getDuration();
        if (this.mDuration != 0) {
            this.mSeekBar.setMax(this.mDuration);
            this.mSeekBar.setVisibility(0);
            if (!this.mSeeking) {
                this.mSeekBar.setProgress(this.mPlayer.getCurrentPosition());
            }
        }
        this.mSeekBar.setOnSeekBarChangeListener(this.mSeekListener);
        this.mLoadingText.setVisibility(8);
        findViewById(R.id.titleandbuttons).setVisibility(0);
        this.mAudioManager.requestAudioFocus(this.mAudioFocusListener, 3, 2);
        if (this.mProgressRefresher != null) {
            this.mProgressRefresher.removeCallbacksAndMessages(null);
            this.mProgressRefresher.postDelayed(new ProgressRefresher(), 200);
        }
        updatePlayPause();
    }

    private void start() {
        this.mAudioManager.requestAudioFocus(this.mAudioFocusListener, 3, 2);
        this.mPlayer.start();
        this.mProgressRefresher.postDelayed(new ProgressRefresher(), 200);
    }

    public void setNames() {
        if (TextUtils.isEmpty(this.mTextLine1.getText())) {
            this.mTextLine1.setText(this.mUri.getLastPathSegment());
        }
        if (TextUtils.isEmpty(this.mTextLine2.getText())) {
            this.mTextLine2.setVisibility(8);
        } else {
            this.mTextLine2.setVisibility(0);
        }
    }

    private void updatePlayPause() {
        ImageView imageView = (ImageView) findViewById(R.id.playpause);
        if (imageView != null && this.mPlayer != null) {
            if (this.mPlayer.isPlaying()) {
                imageView.setImageResource(R.drawable.miniplayer_pause_white);
                return;
            }
            imageView.setImageResource(R.drawable.miniplayer_play);
            this.mProgressRefresher.removeCallbacksAndMessages(null);
        }
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        Toast.makeText(this, R.string.unable_to_play, 0).show();
        finish();
        return true;
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        this.mSeekBar.setProgress(this.mDuration);
        updatePlayPause();
    }

    public void playPauseClicked(View view) {
        if (this.mPlayer != null) {
            if (this.mPlayer.isPlaying()) {
                this.mPlayer.pause();
            } else {
                start();
            }
            updatePlayPause();
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, 1, 0, R.string.open_in_music);
        return true;
    }

    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem findItem = menu.findItem(1);
        if (this.mMediaId >= 0) {
            findItem.setVisible(true);
            return true;
        }
        findItem.setVisible(false);
        return false;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i != 4) {
            if (i != 79) {
                switch (i) {
                    case 85:
                        break;
                    case 86:
                        break;
                    case 87:
                    case 88:
                    case 89:
                    case 90:
                        return true;
                    default:
                        switch (i) {
                            case 126:
                                start();
                                updatePlayPause();
                                return true;
                            case 127:
                                if (this.mPlayer.isPlaying()) {
                                    this.mPlayer.pause();
                                }
                                updatePlayPause();
                                return true;
                            default:
                                return super.onKeyDown(i, keyEvent);
                        }
                }
            }
            if (this.mPlayer.isPlaying()) {
                this.mPlayer.pause();
            } else {
                start();
            }
            updatePlayPause();
            return true;
        }
        stopPlayback();
        finish();
        return true;
    }
}
