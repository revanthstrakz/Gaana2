package com.gaana.view.item;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.gaana.BaseActivity;
import com.gaana.R;

public class ArtistDetailsTabs extends LinearLayout implements OnClickListener {
    public static final int ALBUMS_ID = 0;
    public static final int SONGS_ID = 1;
    private boolean albumSelected = true;
    private TextView albumsView;
    private boolean albumsViewCheck = true;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private OnTabChangedListener mOnTabChangedListener;
    private TextView songsView;

    public interface OnTabChangedListener {
        void OnButtonClicked(int i, ImageView imageView);

        void OnTabChanged(int i);
    }

    public void setOnTabChangedListener(OnTabChangedListener onTabChangedListener) {
        this.mOnTabChangedListener = onTabChangedListener;
    }

    public ArtistDetailsTabs(Context context) {
        super(context);
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mLayoutInflater.inflate(R.layout.artist_details_tab, this);
        this.albumsView = (TextView) findViewById(R.id.albumsButton);
        this.albumsView.setId(0);
        this.songsView = (TextView) findViewById(R.id.songsButton);
        this.songsView.setId(1);
        this.albumsView.setOnClickListener(this);
        this.songsView.setOnClickListener(this);
    }

    public void onClick(View view) {
        TypedValue typedValue;
        switch (view.getId()) {
            case 0:
                if (!(this.albumsViewCheck && this.albumSelected)) {
                    this.albumsViewCheck = true;
                    this.albumSelected = true;
                    this.albumsView.setTextColor(getResources().getColor(R.color.f17gaana.red));
                    typedValue = new TypedValue();
                    this.mContext.getTheme().resolveAttribute(R.attr.first_line_color, typedValue, true);
                    this.songsView.setTextColor(typedValue.data);
                }
                this.mOnTabChangedListener.OnTabChanged(0);
                return;
            case 1:
                if (this.albumsViewCheck || this.albumSelected) {
                    this.albumsViewCheck = false;
                    this.albumSelected = false;
                    typedValue = new TypedValue();
                    this.mContext.getTheme().resolveAttribute(R.attr.first_line_color, typedValue, true);
                    this.albumsView.setTextColor(typedValue.data);
                    this.songsView.setTextColor(getResources().getColor(R.color.f17gaana.red));
                }
                ((BaseActivity) this.mContext).currentItem = "Song";
                this.mOnTabChangedListener.OnTabChanged(1);
                return;
            default:
                return;
        }
    }
}
