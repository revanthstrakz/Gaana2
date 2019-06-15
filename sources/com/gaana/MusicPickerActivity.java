package com.gaana;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Audio.Media;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import com.b.b;
import com.constants.Constants;
import com.moe.pushlibrary.providers.MoEDataContract.BaseColumns;
import com.services.d;
import java.io.IOException;

public class MusicPickerActivity extends Activity implements OnCompletionListener, OnClickListener {
    Cursor cursor = null;
    ListView listView;
    int mCount = -1;
    LayoutInflater mLayoutInflater;
    MediaPlayer mMediaPlayer;
    long mPlayingId = -1;
    Uri mSelectedUri;
    PickerAdapter pickerAdapter;
    private int selectedIndex = -1;

    class PickerAdapter extends BaseAdapter {
        TextView albumName;
        String artistName;
        ImageView pauseView;
        RadioButton radioButton;
        TextView songName;

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        PickerAdapter() {
        }

        public int getCount() {
            return MusicPickerActivity.this.mCount;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = MusicPickerActivity.this.mLayoutInflater.inflate(R.layout.activity_music_picker_item, null);
            }
            this.songName = (TextView) view.findViewById(R.id.tvSongName);
            this.albumName = (TextView) view.findViewById(R.id.tvAlbumName);
            this.radioButton = (RadioButton) view.findViewById(R.id.radioButton);
            this.pauseView = (ImageView) view.findViewById(R.id.pauseView);
            if (MusicPickerActivity.this.cursor != null) {
                MusicPickerActivity.this.cursor.moveToPosition(i);
                this.songName.setText(MusicPickerActivity.this.cursor.getString(MusicPickerActivity.this.cursor.getColumnIndex("title")));
                this.artistName = MusicPickerActivity.this.cursor.getString(MusicPickerActivity.this.cursor.getColumnIndex("artist"));
                if (TextUtils.isEmpty(this.artistName) || this.artistName.equalsIgnoreCase("<unknown>")) {
                    this.artistName = MusicPickerActivity.this.getString(R.string.various_artists);
                }
                this.albumName.setText(this.artistName);
            }
            if (MusicPickerActivity.this.selectedIndex == i) {
                this.radioButton.setChecked(true);
                if (MusicPickerActivity.this.mMediaPlayer == null || !MusicPickerActivity.this.mMediaPlayer.isPlaying()) {
                    this.pauseView.setVisibility(4);
                } else {
                    this.pauseView.setVisibility(0);
                }
            } else {
                this.radioButton.setChecked(false);
                this.pauseView.setVisibility(4);
            }
            return view;
        }
    }

    /* Access modifiers changed, original: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(b.a(context));
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Constants.Q = d.a().b("PREFERENCE_IS_LOCAL_MEDIA", true, false);
        if (Constants.Q) {
            setContentView(R.layout.activity_music_picker);
            this.listView = (ListView) findViewById(R.id.listView);
            Button button = (Button) findViewById(R.id.cancelButton);
            ((Button) findViewById(R.id.okButton)).setOnClickListener(this);
            button.setOnClickListener(this);
            this.mLayoutInflater = LayoutInflater.from(this);
            getLocalSongs();
            this.pickerAdapter = new PickerAdapter();
            this.listView.setAdapter(this.pickerAdapter);
            this.listView.setOnItemClickListener(new OnItemClickListener() {
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    MusicPickerActivity.this.selectedIndex = i;
                    if (MusicPickerActivity.this.cursor != null) {
                        MusicPickerActivity.this.cursor.moveToPosition(i);
                        MusicPickerActivity.this.setSelected();
                        MusicPickerActivity.this.pickerAdapter.notifyDataSetChanged();
                    }
                }
            });
            return;
        }
        startActivity(new Intent(this, SplashScreenActivity.class));
        finish();
    }

    /* Access modifiers changed, original: protected */
    public void onPause() {
        super.onPause();
        stopMediaPlayer();
        this.pickerAdapter.notifyDataSetChanged();
    }

    /* Access modifiers changed, original: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.cursor != null) {
            this.cursor.close();
        }
    }

    private void getLocalSongs() {
        try {
            this.cursor = getContentResolver().query(Media.EXTERNAL_CONTENT_URI, new String[]{BaseColumns._ID, "title", "artist", "mime_type"}, "mime_type NOT NULL AND is_music != 0", null, "title");
            if (this.cursor != null) {
                this.mCount = this.cursor.getCount();
            }
        } catch (Exception unused) {
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void setSelected() {
        Uri uri = Media.EXTERNAL_CONTENT_URI;
        long j = this.cursor.getLong(this.cursor.getColumnIndex(BaseColumns._ID));
        this.mSelectedUri = ContentUris.withAppendedId(uri, j);
        if (j != this.mPlayingId || this.mMediaPlayer == null) {
            stopMediaPlayer();
            this.mMediaPlayer = new MediaPlayer();
            try {
                this.mMediaPlayer.setDataSource(this, this.mSelectedUri);
                this.mMediaPlayer.setOnCompletionListener(this);
                this.mMediaPlayer.setAudioStreamType(3);
                this.mMediaPlayer.prepare();
                this.mMediaPlayer.start();
                this.mPlayingId = j;
            } catch (IOException unused) {
            }
        } else if (this.mMediaPlayer != null) {
            stopMediaPlayer();
        }
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        if (this.mMediaPlayer == mediaPlayer) {
            mediaPlayer.stop();
            mediaPlayer.release();
            this.mMediaPlayer = null;
            this.mPlayingId = -1;
            this.pickerAdapter.notifyDataSetChanged();
        }
    }

    /* Access modifiers changed, original: 0000 */
    public void stopMediaPlayer() {
        if (this.mMediaPlayer != null) {
            this.mMediaPlayer.stop();
            this.mMediaPlayer.release();
            this.mMediaPlayer = null;
            this.mPlayingId = -1;
        }
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.cancelButton) {
            finish();
        } else if (id == R.id.okButton && this.selectedIndex >= 0) {
            setResult(-1, new Intent().setData(this.mSelectedUri));
            finish();
        }
    }
}
