package com.gaana.view.item;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import com.android.volley.Request.Priority;
import com.constants.c;
import com.fragments.BaseGaanaFragment;
import com.gaana.BaseActivity;
import com.gaana.R;
import com.gaana.localmedia.LocalMediaImageLoader;
import com.gaana.models.Albums.Album;
import com.gaana.models.BusinessObject;
import com.gaana.models.NextGenSearchAutoSuggests.AutoComplete;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Tracks.Track;
import com.gaana.view.item.SearchItemView.SearchCategory;
import com.i.i;
import com.library.controls.CrossFadeImageView;
import com.managers.DownloadManager;
import com.managers.DownloadManager.DownloadStatus;
import com.managers.GaanaSearchManager;
import com.managers.PlayerManager;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.models.PlayerTrack;
import com.services.l.s;
import com.services.l.u;
import java.util.ArrayList;
import java.util.Iterator;

public class RadioButtonGenericView extends BaseItemView {
    private CrossFadeImageView mCrossFadeImageIcon;
    private boolean mIsFromCuratedView;
    private int mLayoutId;
    private u mOnCheckedStateListener;
    private View mView;
    private CheckBox radioSong;
    private TextView tvAlbumName;
    private TextView tvSongName;

    public static class RadioSearchItemHolder extends ViewHolder {
        public View dividerLine;
        public CrossFadeImageView mCrossFadeImageIcon;
        public CheckBox radioSong;
        public TextView tvAlbumName;
        public TextView tvSongName;

        public RadioSearchItemHolder(View view) {
            super(view);
            this.dividerLine = view.findViewById(R.id.dividerLine);
            this.radioSong = (CheckBox) view.findViewById(R.id.radioSong);
            this.mCrossFadeImageIcon = (CrossFadeImageView) view.findViewById(R.id.imgProductIcon);
            this.tvSongName = (TextView) view.findViewById(R.id.tvSongName);
            this.tvAlbumName = (TextView) view.findViewById(R.id.tvAlbumName);
        }
    }

    public RadioButtonGenericView(Context context, BaseGaanaFragment baseGaanaFragment) {
        super(context, baseGaanaFragment);
        this.mView = null;
        this.mLayoutId = -1;
        this.mLayoutId = R.layout.view_item_playlist_radiobtn;
    }

    public RadioButtonGenericView(Context context, BaseGaanaFragment baseGaanaFragment, int i) {
        super(context, baseGaanaFragment);
        this.mView = null;
        this.mLayoutId = -1;
        this.mLayoutId = R.layout.view_item_playlist_radiobtn;
    }

    public View getPoplatedView(ViewHolder viewHolder, BusinessObject businessObject, ViewGroup viewGroup, ArrayList<Track> arrayList) {
        this.mView = viewHolder.itemView;
        this.mView.setOnClickListener(this);
        this.mView.setTag(businessObject);
        return getDataFilledView(viewHolder, businessObject, arrayList);
    }

    public View createViewHolder(ViewGroup viewGroup, int i) {
        this.mView = this.mInflater.inflate(this.mLayoutId, viewGroup, false);
        return this.mView;
    }

    private View getDataFilledView(ViewHolder viewHolder, BusinessObject businessObject, ArrayList<Track> arrayList) {
        RadioSearchItemHolder radioSearchItemHolder = (RadioSearchItemHolder) viewHolder;
        this.mCrossFadeImageIcon = radioSearchItemHolder.mCrossFadeImageIcon;
        this.radioSong = radioSearchItemHolder.radioSong;
        this.tvSongName = radioSearchItemHolder.tvSongName;
        this.tvAlbumName = radioSearchItemHolder.tvAlbumName;
        if (businessObject instanceof Track) {
            Track track = (Track) businessObject;
            this.tvSongName.setText(track.getName());
            this.tvAlbumName.setText(track.getArtistNames());
            PlayerTrack i = PlayerManager.a(this.mContext).i();
            if (i == null || i.b() == null || !track.getBusinessObjId().equalsIgnoreCase(i.h())) {
                TypedValue typedValue = new TypedValue();
                this.mContext.getTheme().resolveAttribute(R.attr.first_line_color, typedValue, true);
                this.tvSongName.setTextColor(typedValue.data);
            } else {
                this.tvSongName.setTextColor(this.mContext.getResources().getColor(R.color.gaana_orange_text));
            }
            if (!TextUtils.isEmpty(track.getArtwork())) {
                if (track.isLocalMedia()) {
                    this.mCrossFadeImageIcon.bindImageForLocalMedia(track.getArtwork(), null, new LocalMediaImageLoader(), this.mAppState.isAppInOfflineMode());
                } else {
                    this.mCrossFadeImageIcon.bindImage(track.getArtwork().replace("80x80", "175x175"), this.mAppState.isAppInOfflineMode());
                }
            }
            if (this.mAppState.getArrListForTrackIds() == null || this.mAppState.getArrListForTrackIds().size() <= 0 || !checkForPlaylistAndAlbumInPlaylist(track.getBusinessObjId(), this.mAppState.getArrListForTrackIds())) {
                this.radioSong.setChecked(false);
            } else {
                this.radioSong.setChecked(true);
            }
            if (arrayList != null && arrayList.size() > 0 && checkForTrackInPlaylist(track.getBusinessObjId(), arrayList)) {
                this.radioSong.setChecked(true);
                this.mView.setClickable(false);
            }
            if (this.mIsFromCuratedView) {
                TypedArray obtainStyledAttributes;
                Drawable drawable;
                if (DownloadManager.c().e(Integer.parseInt(businessObject.getBusinessObjId())) == DownloadStatus.DOWNLOADED) {
                    this.radioSong.setChecked(true);
                    this.mView.setClickable(false);
                    new int[1][0] = R.attr.download_button_disabled;
                    obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                    drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(12, -1));
                    obtainStyledAttributes.recycle();
                    this.radioSong.setButtonDrawable(drawable);
                } else {
                    this.mView.setClickable(true);
                    new int[1][0] = R.attr.palylist_checkbox_theme;
                    obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                    drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(65, -1));
                    obtainStyledAttributes.recycle();
                    this.radioSong.setButtonDrawable(drawable);
                }
            }
        } else if (businessObject instanceof AutoComplete) {
            AutoComplete autoComplete = (AutoComplete) businessObject;
            this.tvSongName.setText(autoComplete.getTitle());
            this.tvAlbumName.setText(autoComplete.getSubtitle());
            if (!TextUtils.isEmpty(autoComplete.getArtwork())) {
                if (autoComplete.isLocalMedia()) {
                    this.mCrossFadeImageIcon.bindImageForLocalMedia(autoComplete.getArtwork(), null, new LocalMediaImageLoader(), this.mAppState.isAppInOfflineMode());
                } else {
                    this.mCrossFadeImageIcon.bindImage(autoComplete.getArtwork().replace("80x80", "175x175"), this.mAppState.isAppInOfflineMode());
                }
            }
            if (autoComplete.getType().equalsIgnoreCase("Track")) {
                PlayerTrack i2 = PlayerManager.a(this.mContext).i();
                if (i2 == null || i2.b() == null || !autoComplete.getBusinessObjectId().equalsIgnoreCase(i2.h())) {
                    TypedValue typedValue2 = new TypedValue();
                    this.mContext.getTheme().resolveAttribute(R.attr.first_line_color, typedValue2, true);
                    this.tvSongName.setTextColor(typedValue2.data);
                } else {
                    this.tvSongName.setTextColor(this.mContext.getResources().getColor(R.color.gaana_orange_text));
                }
                if (this.mAppState.getArrListForTrackIds() == null || this.mAppState.getArrListForTrackIds().size() <= 0 || !checkForPlaylistAndAlbumInPlaylist(autoComplete.getBusinessObjectId(), this.mAppState.getArrListForTrackIds())) {
                    this.radioSong.setChecked(false);
                } else {
                    this.radioSong.setChecked(true);
                }
                if (arrayList != null && arrayList.size() > 0 && checkForTrackInPlaylist(autoComplete.getBusinessObjectId(), arrayList)) {
                    this.radioSong.setChecked(true);
                    this.mView.setClickable(false);
                }
            } else if (autoComplete.getType().equalsIgnoreCase("Album") || autoComplete.getType().equalsIgnoreCase("Playlist")) {
                if (this.mAppState.getArrListForPlaylistIds() == null || this.mAppState.getArrListForPlaylistIds().size() <= 0 || !checkForPlaylistAndAlbumInPlaylist(autoComplete.getBusinessObjectId(), this.mAppState.getArrListForPlaylistIds())) {
                    this.radioSong.setChecked(false);
                } else {
                    this.radioSong.setChecked(true);
                }
            }
        }
        return this.mView;
    }

    private boolean checkForTrackInPlaylist(String str, ArrayList<Track> arrayList) {
        int i = 0;
        while (i < arrayList.size()) {
            if (arrayList.get(i) != null && str.equalsIgnoreCase(((Track) arrayList.get(i)).getBusinessObjId())) {
                return true;
            }
            i++;
        }
        return false;
    }

    private boolean checkForPlaylistAndAlbumInPlaylist(String str, ArrayList<String> arrayList) {
        int i = 0;
        while (i < arrayList.size()) {
            if (arrayList.get(i) != null && str.equalsIgnoreCase((String) arrayList.get(i))) {
                return true;
            }
            i++;
        }
        return false;
    }

    public void selectAllItems(ArrayList<BusinessObject> arrayList) {
        if (!(this.mAppState.getArrListTracksForPlaylist() == null || this.mAppState.getArrListTracksForPlaylist().size() == 0)) {
            this.mAppState.getArrListTracksForPlaylist().clear();
        }
        if (!(this.mAppState.getArrListForTrackIds() == null || this.mAppState.getArrListForTrackIds().size() == 0)) {
            this.mAppState.getArrListForTrackIds().clear();
        }
        if (arrayList != null && arrayList.size() > 0) {
            for (int i = 0; i < arrayList.size(); i++) {
                BusinessObject businessObject = (BusinessObject) arrayList.get(i);
                if (!(businessObject == null || !(businessObject instanceof Track) || DownloadManager.c().e(Integer.parseInt(businessObject.getBusinessObjId())) == DownloadStatus.DOWNLOADED)) {
                    if (this.mAppState.getArrListTracksForPlaylist() == null) {
                        this.mAppState.setArrListTracksForPlaylist(new ArrayList());
                    }
                    this.mAppState.getArrListTracksForPlaylist().add((Track) businessObject);
                    this.mAppState.setArrListForTrackIds(businessObject.getBusinessObjId());
                }
            }
        }
    }

    public void unSelectAllItems(ArrayList<BusinessObject> arrayList) {
        if (!(this.mAppState.getArrListTracksForPlaylist() == null || this.mAppState.getArrListTracksForPlaylist().size() == 0)) {
            this.mAppState.getArrListTracksForPlaylist().clear();
        }
        if (this.mAppState.getArrListForTrackIds() != null && this.mAppState.getArrListForTrackIds().size() != 0) {
            this.mAppState.getArrListForTrackIds().clear();
        }
    }

    public void setCheckedStateListener(u uVar) {
        this.mOnCheckedStateListener = uVar;
    }

    public void setFromCuratedView(boolean z) {
        this.mIsFromCuratedView = z;
    }

    public void startDownload() {
        if (this.mAppState.getArrListTracksForPlaylist() != null && this.mAppState.getArrListTracksForPlaylist().size() > 0 && (this.mAppState.getArrListTracksForPlaylist().get(0) instanceof Track)) {
            ArrayList arrayList = new ArrayList();
            Iterator it = this.mAppState.getArrListTracksForPlaylist().iterator();
            while (it.hasNext()) {
                arrayList.add((Track) it.next());
            }
            if (arrayList.size() > 0 && !DownloadManager.c().a(arrayList, -100, true)) {
                DownloadManager.c().d();
            }
            if (!(this.mAppState.getArrListTracksForPlaylist() == null || this.mAppState.getArrListTracksForPlaylist().size() == 0)) {
                this.mAppState.getArrListTracksForPlaylist().clear();
            }
            if (this.mAppState.getArrListForTrackIds() != null && this.mAppState.getArrListForTrackIds().size() != 0) {
                this.mAppState.getArrListForTrackIds().clear();
            }
        }
    }

    public void onClick(View view) {
        CheckBox checkBox;
        BusinessObject convertToBusinessObject;
        super.onClick(view);
        if (view instanceof CheckBox) {
            checkBox = (CheckBox) view;
        } else {
            checkBox = (CheckBox) view.findViewById(R.id.radioSong);
        }
        if (view.getTag() instanceof AutoComplete) {
            if (GaanaSearchManager.a().g() != null) {
                GaanaSearchManager.a().g().a(this.mContext);
            }
            convertToBusinessObject = convertToBusinessObject((AutoComplete) view.getTag());
        } else {
            convertToBusinessObject = (BusinessObject) view.getTag();
        }
        boolean z;
        URLManager uRLManager;
        StringBuilder stringBuilder;
        if (checkBox.isChecked()) {
            int i = 0;
            checkBox.setChecked(false);
            if (this.mAppState.getArrListTracksForPlaylist() == null || this.mAppState.getArrListTracksForPlaylist().size() == 0) {
                if (this.mOnCheckedStateListener != null) {
                    this.mOnCheckedStateListener.onCheckedStateUnSelected(0);
                }
                return;
            }
            if (convertToBusinessObject instanceof Track) {
                ArrayList arrListTracksForPlaylist = this.mAppState.getArrListTracksForPlaylist();
                for (int i2 = 0; i2 < arrListTracksForPlaylist.size(); i2++) {
                    if (convertToBusinessObject.getBusinessObjId().equalsIgnoreCase(((Track) arrListTracksForPlaylist.get(i2)).getBusinessObjId())) {
                        this.mAppState.getArrListTracksForPlaylist().remove((Track) convertToBusinessObject);
                        break;
                    }
                }
                arrListTracksForPlaylist = this.mAppState.getArrListForTrackIds();
                if (arrListTracksForPlaylist != null) {
                    while (i < arrListTracksForPlaylist.size()) {
                        if (convertToBusinessObject.getBusinessObjId().equalsIgnoreCase((String) arrListTracksForPlaylist.get(i))) {
                            arrListTracksForPlaylist.remove(convertToBusinessObject.getBusinessObjId());
                            break;
                        }
                        i++;
                    }
                }
            } else {
                z = convertToBusinessObject instanceof Playlist;
                if (z || (convertToBusinessObject instanceof Album)) {
                    ArrayList arrListForPlaylistIds = this.mAppState.getArrListForPlaylistIds();
                    if (arrListForPlaylistIds != null) {
                        while (i < arrListForPlaylistIds.size()) {
                            if (convertToBusinessObject.getBusinessObjId().equalsIgnoreCase((String) arrListForPlaylistIds.get(i))) {
                                arrListForPlaylistIds.remove(convertToBusinessObject.getBusinessObjId());
                                break;
                            }
                            i++;
                        }
                    }
                    uRLManager = new URLManager();
                    uRLManager.a(Priority.HIGH);
                    uRLManager.a(BusinessObjectType.Tracks);
                    if (z) {
                        uRLManager.b(BusinessObjectType.Playlists);
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(c.w);
                        stringBuilder.append("playlist_id=");
                        stringBuilder.append(convertToBusinessObject.getBusinessObjId());
                        stringBuilder.append("&playlist_type=");
                        stringBuilder.append(((Playlist) convertToBusinessObject).getPlaylistType());
                        uRLManager.a(stringBuilder.toString());
                    } else {
                        uRLManager.b(BusinessObjectType.Albums);
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(c.s);
                        stringBuilder.append(convertToBusinessObject.getBusinessObjId());
                        uRLManager.a(stringBuilder.toString());
                    }
                    if (uRLManager != null) {
                        ((BaseActivity) this.mContext).showProgressDialog(Boolean.valueOf(true), this.mContext.getString(R.string.fetching_details));
                        i.a().a(new s() {
                            public void onRetreivalComplete(BusinessObject businessObject) {
                                ((BaseActivity) RadioButtonGenericView.this.mContext).hideProgressDialog();
                                if (businessObject != null && businessObject.getArrListBusinessObj() != null && businessObject.getArrListBusinessObj().size() > 0) {
                                    ArrayList arrListBusinessObj = businessObject.getArrListBusinessObj();
                                    for (int i = 0; i < arrListBusinessObj.size(); i++) {
                                        ArrayList arrListTracksForPlaylist = RadioButtonGenericView.this.mAppState.getArrListTracksForPlaylist();
                                        for (int i2 = 0; i2 < arrListTracksForPlaylist.size(); i2++) {
                                            if (((Track) arrListBusinessObj.get(i)).getBusinessObjId().equalsIgnoreCase(((Track) arrListTracksForPlaylist.get(i2)).getBusinessObjId())) {
                                                RadioButtonGenericView.this.mAppState.getArrListTracksForPlaylist().remove(arrListTracksForPlaylist.get(i2));
                                                break;
                                            }
                                        }
                                    }
                                }
                            }

                            public void onErrorResponse(BusinessObject businessObject) {
                                ((BaseActivity) RadioButtonGenericView.this.mContext).hideProgressDialog();
                            }
                        }, uRLManager);
                    }
                }
            }
            if (this.mOnCheckedStateListener != null) {
                this.mOnCheckedStateListener.onCheckedStateUnSelected(this.mAppState.getArrListTracksForPlaylist().size());
            }
        } else {
            checkBox.setChecked(true);
            if (convertToBusinessObject instanceof Track) {
                if (this.mAppState.getArrListTracksForPlaylist() == null) {
                    this.mAppState.setArrListTracksForPlaylist(new ArrayList());
                }
                this.mAppState.getArrListTracksForPlaylist().add((Track) convertToBusinessObject);
                this.mAppState.setArrListForTrackIds(convertToBusinessObject.getBusinessObjId());
            } else {
                z = convertToBusinessObject instanceof Playlist;
                if (z || (convertToBusinessObject instanceof Album)) {
                    this.mAppState.setArrListForPlaylistIds(convertToBusinessObject.getBusinessObjId());
                    if (this.mAppState.getArrListTracksForPlaylist() == null) {
                        this.mAppState.setArrListTracksForPlaylist(new ArrayList());
                    }
                    uRLManager = new URLManager();
                    uRLManager.a(Priority.HIGH);
                    uRLManager.a(BusinessObjectType.Tracks);
                    if (z) {
                        uRLManager.b(BusinessObjectType.Playlists);
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(c.w);
                        stringBuilder.append("playlist_id=");
                        stringBuilder.append(convertToBusinessObject.getBusinessObjId());
                        stringBuilder.append("&playlist_type=");
                        stringBuilder.append(((Playlist) convertToBusinessObject).getPlaylistType());
                        uRLManager.a(stringBuilder.toString());
                    } else {
                        uRLManager.b(BusinessObjectType.Albums);
                        stringBuilder = new StringBuilder();
                        stringBuilder.append(c.s);
                        stringBuilder.append(convertToBusinessObject.getBusinessObjId());
                        uRLManager.a(stringBuilder.toString());
                    }
                    if (uRLManager != null) {
                        ((BaseActivity) this.mContext).showProgressDialog(Boolean.valueOf(true), this.mContext.getString(R.string.fetching_details));
                        i.a().a(new s() {
                            public void onRetreivalComplete(BusinessObject businessObject) {
                                ((BaseActivity) RadioButtonGenericView.this.mContext).hideProgressDialog();
                                if (businessObject != null && businessObject.getArrListBusinessObj() != null && businessObject.getArrListBusinessObj().size() > 0) {
                                    ArrayList arrListBusinessObj = businessObject.getArrListBusinessObj();
                                    for (int i = 0; i < arrListBusinessObj.size(); i++) {
                                        RadioButtonGenericView.this.mAppState.getArrListTracksForPlaylist().add(arrListBusinessObj.get(i));
                                    }
                                }
                            }

                            public void onErrorResponse(BusinessObject businessObject) {
                                ((BaseActivity) RadioButtonGenericView.this.mContext).hideProgressDialog();
                            }
                        }, uRLManager);
                    }
                }
            }
            if (this.mOnCheckedStateListener != null) {
                this.mOnCheckedStateListener.onCheckedStateSelected(this.mAppState.getArrListTracksForPlaylist().size());
            }
        }
    }

    public BusinessObject convertToBusinessObject(AutoComplete autoComplete) {
        String rawTitle = autoComplete.getRawTitle();
        String language = autoComplete.getLanguage();
        String artwork = autoComplete.getArtwork();
        autoComplete.getRawSubtitle();
        String businessObjectId = autoComplete.getBusinessObjectId();
        BusinessObject businessObject = new BusinessObject();
        switch (SearchCategory.valueOf(autoComplete.getType())) {
            case Album:
                businessObject = new Album();
                businessObject.setBusinessObjType(BusinessObjectType.Albums);
                ((Album) businessObject).setArtwork(artwork);
                break;
            case Playlist:
                businessObject = new Playlist();
                businessObject.setBusinessObjType(BusinessObjectType.Playlists);
                Playlist playlist = (Playlist) businessObject;
                playlist.setArtwork(artwork);
                playlist.setPlaylistId(businessObjectId);
                break;
            case Track:
                businessObject = new Track();
                businessObject.setBusinessObjType(BusinessObjectType.Tracks);
                ((Track) businessObject).setArtwork(artwork);
                break;
        }
        businessObject.setBusinessObjId(businessObjectId);
        businessObject.setName(rawTitle);
        businessObject.setLanguage(language);
        businessObject.setLocalMedia(autoComplete.isLocalMedia());
        return businessObject;
    }
}
