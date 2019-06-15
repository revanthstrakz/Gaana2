package com.gaana.view.item;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.collapsible_header.SongParallexListingFragment;
import com.constants.Constants;
import com.e.a.h;
import com.fragments.AlbumDetailsMaterialListing;
import com.fragments.ArtistDetailsMaterialListing;
import com.fragments.BaseGaanaFragment;
import com.fragments.DownloadDetailsFragment;
import com.fragments.FavoritesFragment;
import com.fragments.GaanaSpecialDetailsMaterialListing;
import com.fragments.MyMusicItemFragment;
import com.fragments.RadioActivityFragment;
import com.fragments.RadioDetailsMaterialListing;
import com.fragments.RevampedDetailListing;
import com.fragments.SearchEnchancedFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.juke.JukePartyFragment;
import com.gaana.juke.JukePlaylist;
import com.gaana.localmedia.LocalMediaManager;
import com.gaana.models.Albums.Album;
import com.gaana.models.Artists.Artist;
import com.gaana.models.BusinessObject;
import com.gaana.models.OfflineTrack;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.RadioMoods;
import com.gaana.models.RadioMoods.RadioMood;
import com.gaana.models.Radios.Radio;
import com.gaana.models.Tracks.Track;
import com.gaana.view.ArtistNamesView;
import com.gaana.view.ArtistNamesView.IArtistClickHandler;
import com.gaana.view.PopUpMenuListView;
import com.gaana.view.item.CustomDialogView.OnButtonClickListener;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.library.controls.CrossFadeImageView;
import com.logging.GaanaLogger.PLAYOUT_SECTION_TYPE;
import com.logging.GaanaLogger.SOURCE_TYPE;
import com.managers.DownloadManager;
import com.managers.DownloadManager.DownloadStatus;
import com.managers.GaanaSearchManager;
import com.managers.PlayerManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.ad;
import com.managers.af;
import com.managers.an;
import com.managers.ap.a;
import com.managers.aq;
import com.managers.u;
import com.models.PlayerTrack;
import com.utilities.f;
import com.utilities.j;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class PopupItemView extends BottomSheetDialog implements OnClickListener {
    private final int ADDMORESONGS;
    private final int ADDTOPLYALIST;
    private final int ADD_TO_PAYLIST;
    private final int ALBUM;
    private final int ALBUMINFO;
    private final int ARTIST;
    private final int AUTO_REORDER;
    private final int CLEARQUEUE;
    private final int DELETE;
    private final int DELETEDOWNLOAD;
    private final int DELETEPLAYLIST;
    private final int DELETE_PARTY;
    private final int DOWNLOAD_ALL_SONGS;
    private final int DOWNLOAD_SONGS;
    private final int EDITPLAYLIST;
    private final int EDIT_NICK_NAME;
    private final int ENQUEUE;
    private final int ENQUEUENEXT;
    private final int FAVORITE;
    private final int LEAVE_PLAYLIST;
    private final int LYRICS;
    private final int PLAYLISTINFO;
    private final int PLAYNEXT;
    private final int RADIO;
    private final int REMOVE_FROM_LIST;
    private final int REMOVE_FROM_PARTY;
    private final int RENAME_PLAYLIST;
    private final int SHARE;
    private final int SHARESTORY;
    private final int SHARE_PLAYLIST;
    private final int SHUFFLE;
    private final int SIMILARALBUM;
    private final int SIMILARARTIST;
    private final int SONGINFO;
    private int[] albumMoreoptionsIndex;
    private int[] albumMoreoptionsIndexShuffle;
    private int[] artistMoreoptionsIndex;
    private int[] automatedPlaylist;
    private int[] currentArrayOfOptions;
    private int[] drawableAttrIds;
    private Drawable[] drawables;
    private boolean isPlayerQueue;
    private boolean isQueueOpen;
    private boolean isRemoveRecentSearch;
    private boolean isRemoveRecentlyPlayed;
    private boolean isUserCreatedPlaylist;
    private int[] jukeAdminPlaylistMoreOptionsIndex;
    private int[] jukeGuestPlaylistMoreOptionsIndex;
    private int[] jukeSongsMoreoptionsIndex;
    private PlayerQueueAdapter listAdapter;
    private GaanaApplication mAppState;
    private BusinessObject mBusinessObject;
    private Context mContext;
    private DownloadPopupListener mDownloadPopupListener;
    private DownloadSongsItemView mDownloadSongsItemView;
    private BaseGaanaFragment mFragment;
    private IDismissPopup mIDismissPopup;
    private LayoutInflater mInflater;
    private PopUpMenuListView mListView;
    private a mListener;
    private HashMap<String, Integer> mRadioMoodsIconMap;
    private View mView;
    private int[] menuId;
    private int[] myMusicMoreoptionsIndex;
    private int[] myMusicMoreoptionsIndexTrack;
    private int[] myPlaylistMoreoptionsIndex;
    private int[] myPlaylistMoreoptionsIndexGaanaMini;
    private int[] playerMoreoptionsIndex;
    private int[] playlistMoreoptionsIndex;
    private int[] playlistMoreoptionsIndexShuffle;
    private int[] radioMoreoptionsIndex;
    private int[] selectedMoreoptionsIndex;
    private int[] songsMoreoptionsIndex;
    private String[] textArray;

    public interface DownloadPopupListener {
        void onPopupClicked(String str, BusinessObject businessObject);
    }

    public interface IDismissPopup {
        void dismiss(boolean z);
    }

    class PlayerQueueAdapter extends BaseAdapter {
        private int count;
        private ArrayList<Object> mArrrListItems;

        public long getItemId(int i) {
            return (long) i;
        }

        PlayerQueueAdapter() {
        }

        public ArrayList<Object> getAdapterArrayList() {
            return this.mArrrListItems;
        }

        public void setAdapterArrayList(ArrayList<?> arrayList) {
            this.mArrrListItems = arrayList;
            this.count = this.mArrrListItems.size();
        }

        public void removeItem(Object obj) {
            this.mArrrListItems.remove(obj);
            notifyDataSetChanged();
        }

        public void clearAdapter() {
            this.mArrrListItems.clear();
            notifyDataSetChanged();
        }

        public void updateAdapterArrayList(ArrayList<?> arrayList) {
            if (!(arrayList == null || arrayList.size() == 0)) {
                for (int i = 0; i < arrayList.size(); i++) {
                    this.mArrrListItems.add(arrayList.get(i));
                }
            }
            notifyDataSetChanged();
        }

        public int getCount() {
            return this.count;
        }

        public void setCount(int i) {
            this.count = i;
        }

        public Object getItem(int i) {
            Object obj = this.mArrrListItems.get(i);
            return obj instanceof PlayerTrack ? ((PlayerTrack) obj).a(true) : obj;
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            if (PopupItemView.this.mBusinessObject == null) {
                return PopupItemView.this.mDownloadSongsItemView.getPoplatedView(view, (BusinessObject) getItem(i), viewGroup, true);
            }
            return PopupItemView.this.populateSongsMenu(i, view, viewGroup);
        }
    }

    class RadioMoodsAdapter extends BaseAdapter {
        private final LayoutInflater mLayoutInflator;
        private final ArrayList<RadioMood> mRadiomoods;
        private final int mResId;

        public long getItemId(int i) {
            return (long) i;
        }

        public RadioMoodsAdapter(Context context, int i, ArrayList<RadioMood> arrayList) {
            this.mRadiomoods = arrayList;
            PopupItemView.this.setIconsOnTouchRadio();
            this.mResId = i;
            this.mLayoutInflator = LayoutInflater.from(PopupItemView.this.mContext);
        }

        public int getCount() {
            return this.mRadiomoods.size();
        }

        @Nullable
        public RadioMood getItem(int i) {
            return (RadioMood) this.mRadiomoods.get(i);
        }

        @NonNull
        public View getView(int i, View view, ViewGroup viewGroup) {
            view = this.mLayoutInflator.inflate(this.mResId, null, false);
            ImageView imageView = (ImageView) view.findViewById(R.id.radio_moods_row_image);
            RadioMood radioMood = (RadioMood) this.mRadiomoods.get(i);
            ((TextView) view.findViewById(R.id.radio_moods_row_text)).setText(radioMood.getName());
            String entityId = radioMood.getEntityId();
            if (entityId.equalsIgnoreCase("166")) {
                TypedArray obtainStyledAttributes = PopupItemView.this.mContext.obtainStyledAttributes(new int[]{R.attr.drive, R.attr.party});
                if (radioMood.getName().startsWith("d") || radioMood.getName().startsWith("D")) {
                    imageView.setImageResource(obtainStyledAttributes.getResourceId(0, 0));
                } else {
                    imageView.setImageResource(obtainStyledAttributes.getResourceId(1, 0));
                }
                obtainStyledAttributes.recycle();
            } else {
                imageView.setImageResource(((Integer) PopupItemView.this.mRadioMoodsIconMap.get(entityId)).intValue());
            }
            return view;
        }
    }

    public PopupItemView(Context context, BaseGaanaFragment baseGaanaFragment) {
        Context context2 = context;
        super(context2, 16973836);
        this.mListView = null;
        this.listAdapter = null;
        this.drawableAttrIds = new int[]{38, 49, 55, 57, 36, 35, 47, 45, 41, 40, 39, 41, 34, 58, 47, 41, 38, 41, 44, 37, 54, 54, 54, 46, 57, 45, 44, 1, 96, 45, 41, 41, 56, 41};
        this.drawables = new Drawable[this.drawableAttrIds.length];
        this.textArray = new String[]{GaanaApplication.getContext().getResources().getString(R.string.add_to_playlist), GaanaApplication.getContext().getResources().getString(R.string.add_to_mymusic), GaanaApplication.getContext().getResources().getString(R.string.play_similar_songs), GaanaApplication.getContext().getResources().getString(R.string.opt_share), GaanaApplication.getContext().getResources().getString(R.string.opt_similar_artists), GaanaApplication.getContext().getResources().getString(R.string.opt_similar_albums), GaanaApplication.getContext().getResources().getString(R.string.opt_play_next), GaanaApplication.getContext().getResources().getString(R.string.opt_edit_playlist), GaanaApplication.getContext().getResources().getString(R.string.opt_delete_playlist), GaanaApplication.getContext().getResources().getString(R.string.more_info_composers), GaanaApplication.getContext().getResources().getString(R.string.opt_albums), GaanaApplication.getContext().getResources().getString(R.string.opt_delete_downloads), GaanaApplication.getContext().getResources().getString(R.string.opt_lyrics), GaanaApplication.getContext().getResources().getString(R.string.opt_shuffle_play), GaanaApplication.getContext().getResources().getString(R.string.opt_play_next_string), GaanaApplication.getContext().getResources().getString(R.string.opt_delete), GaanaApplication.getContext().getResources().getString(R.string.opt_add_to_playlist), GaanaApplication.getContext().getResources().getString(R.string.opt_clear_queue), GaanaApplication.getContext().getResources().getString(R.string.download_camelcase), GaanaApplication.getContext().getResources().getString(R.string.add_more_songs_menu), GaanaApplication.getContext().getResources().getString(R.string.songInfo), GaanaApplication.getContext().getResources().getString(R.string.albumInfo), GaanaApplication.getContext().getResources().getString(R.string.playlistInfo), GaanaApplication.getContext().getResources().getString(R.string.opt_play_first), GaanaApplication.getContext().getResources().getString(R.string.opt_share_playlist), GaanaApplication.getContext().getResources().getString(R.string.edit_my_nick), GaanaApplication.getContext().getResources().getString(R.string.download_all_songs), GaanaApplication.getContext().getResources().getString(R.string.opt_leave_playlist), GaanaApplication.getContext().getResources().getString(R.string.opt_auto_reorder), GaanaApplication.getContext().getResources().getString(R.string.opt_rename_playlist), GaanaApplication.getContext().getResources().getString(R.string.opt_remove_from_party), GaanaApplication.getContext().getResources().getString(R.string.opt_delete_party), GaanaApplication.getContext().getResources().getString(R.string.opt_share_story), GaanaApplication.getContext().getResources().getString(R.string.opt_remove_from_list)};
        this.menuId = new int[]{R.id.addToPlaylistMenu, R.id.favoriteMenu, R.id.radioMenu, R.id.shareMenu, R.id.similarArtistMenu, R.id.similarAlbumMenu, R.id.enqueueMenu, R.id.editPlaylistMenu, R.id.deletePlaylistMenu, R.id.artistMenu, R.id.albumMenu, R.id.deleteDownloadMenu, R.id.lyricsMenu, R.id.shuffleMenu, R.id.playNextMenu, R.id.deleteLocalItemsMenu, R.id.menu_add_playlist, R.id.clearQueue, R.id.download_songs, R.id.addMoreSongs, R.id.songInfoMenu, R.id.albumInfoMenu, R.id.playlistInfoMenu, R.id.enqueueNextMenu, R.id.share_playlist, R.id.edit_nick, R.id.download_all_songs, R.id.leave_playlist, R.id.auto_reorder, R.id.rename_playlist, R.id.remove_from_party, R.id.delete_party, R.id.share_story, R.id.remove_from_list};
        this.currentArrayOfOptions = null;
        this.ADDTOPLYALIST = 0;
        this.FAVORITE = 1;
        this.RADIO = 2;
        this.SHARE = 3;
        this.SIMILARARTIST = 4;
        this.SIMILARALBUM = 5;
        this.ENQUEUE = 6;
        this.EDITPLAYLIST = 7;
        this.DELETEPLAYLIST = 8;
        this.ARTIST = 9;
        this.ALBUM = 10;
        this.DELETEDOWNLOAD = 11;
        this.LYRICS = 12;
        this.SHUFFLE = 13;
        this.PLAYNEXT = 14;
        this.DELETE = 15;
        this.ADD_TO_PAYLIST = 16;
        this.CLEARQUEUE = 17;
        this.DOWNLOAD_SONGS = 18;
        this.ADDMORESONGS = 19;
        this.SONGINFO = 20;
        this.ALBUMINFO = 21;
        this.PLAYLISTINFO = 22;
        this.ENQUEUENEXT = 23;
        this.SHARE_PLAYLIST = 24;
        this.EDIT_NICK_NAME = 25;
        this.DOWNLOAD_ALL_SONGS = 26;
        this.LEAVE_PLAYLIST = 27;
        this.AUTO_REORDER = 28;
        this.RENAME_PLAYLIST = 29;
        this.REMOVE_FROM_PARTY = 30;
        this.DELETE_PARTY = 31;
        this.SHARESTORY = 32;
        this.REMOVE_FROM_LIST = 33;
        this.albumMoreoptionsIndex = new int[]{1, 11, 3, 0, 33, 13, 23, 6, 5, 9, 21};
        this.albumMoreoptionsIndexShuffle = new int[]{1, 11, 3, 0, 33, 13, 5, 9, 21};
        this.jukeGuestPlaylistMoreOptionsIndex = new int[]{24, 25, 26, 27};
        this.jukeAdminPlaylistMoreOptionsIndex = new int[]{24, 29, 25, 26, 28, 31};
        this.songsMoreoptionsIndex = new int[]{1, 18, 3, 32, 0, 33, 23, 6, 2, 9, 10, 12, 20};
        this.jukeSongsMoreoptionsIndex = new int[]{1, 18, 3, 32, 0, 23, 30, 9, 10, 12, 20};
        this.artistMoreoptionsIndex = new int[]{1, 3, 33, 4};
        this.radioMoreoptionsIndex = new int[]{1, 3};
        this.playlistMoreoptionsIndex = new int[]{1, 11, 3, 0, 33, 13, 23, 6, 22};
        this.playlistMoreoptionsIndexShuffle = new int[]{1, 11, 3, 0, 33, 13, 22};
        this.myPlaylistMoreoptionsIndex = new int[]{1, 11, 3, 0, 19, 13, 7, 23, 6, 8};
        this.myPlaylistMoreoptionsIndexGaanaMini = new int[]{1, 11, 3, 0, 13, 7, 23, 6};
        this.automatedPlaylist = new int[]{6, 23, 11, 0, 13};
        this.myMusicMoreoptionsIndexTrack = new int[]{6, 23, 0, 9, 10, 15};
        this.myMusicMoreoptionsIndex = new int[]{6, 23, 0, 13, 7, 15};
        this.playerMoreoptionsIndex = new int[]{17};
        this.isUserCreatedPlaylist = false;
        this.isQueueOpen = false;
        this.mFragment = baseGaanaFragment;
        this.mAppState = GaanaApplication.getInstance();
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context2;
        requestWindowFeature(1);
        init(context);
    }

    public PopupItemView(Context context, BusinessObject businessObject, BaseGaanaFragment baseGaanaFragment) {
        super(context);
        this.mListView = null;
        this.listAdapter = null;
        this.drawableAttrIds = new int[]{38, 49, 55, 57, 36, 35, 47, 45, 41, 40, 39, 41, 34, 58, 47, 41, 38, 41, 44, 37, 54, 54, 54, 46, 57, 45, 44, 1, 96, 45, 41, 41, 56, 41};
        this.drawables = new Drawable[this.drawableAttrIds.length];
        this.textArray = new String[]{GaanaApplication.getContext().getResources().getString(R.string.add_to_playlist), GaanaApplication.getContext().getResources().getString(R.string.add_to_mymusic), GaanaApplication.getContext().getResources().getString(R.string.play_similar_songs), GaanaApplication.getContext().getResources().getString(R.string.opt_share), GaanaApplication.getContext().getResources().getString(R.string.opt_similar_artists), GaanaApplication.getContext().getResources().getString(R.string.opt_similar_albums), GaanaApplication.getContext().getResources().getString(R.string.opt_play_next), GaanaApplication.getContext().getResources().getString(R.string.opt_edit_playlist), GaanaApplication.getContext().getResources().getString(R.string.opt_delete_playlist), GaanaApplication.getContext().getResources().getString(R.string.more_info_composers), GaanaApplication.getContext().getResources().getString(R.string.opt_albums), GaanaApplication.getContext().getResources().getString(R.string.opt_delete_downloads), GaanaApplication.getContext().getResources().getString(R.string.opt_lyrics), GaanaApplication.getContext().getResources().getString(R.string.opt_shuffle_play), GaanaApplication.getContext().getResources().getString(R.string.opt_play_next_string), GaanaApplication.getContext().getResources().getString(R.string.opt_delete), GaanaApplication.getContext().getResources().getString(R.string.opt_add_to_playlist), GaanaApplication.getContext().getResources().getString(R.string.opt_clear_queue), GaanaApplication.getContext().getResources().getString(R.string.download_camelcase), GaanaApplication.getContext().getResources().getString(R.string.add_more_songs_menu), GaanaApplication.getContext().getResources().getString(R.string.songInfo), GaanaApplication.getContext().getResources().getString(R.string.albumInfo), GaanaApplication.getContext().getResources().getString(R.string.playlistInfo), GaanaApplication.getContext().getResources().getString(R.string.opt_play_first), GaanaApplication.getContext().getResources().getString(R.string.opt_share_playlist), GaanaApplication.getContext().getResources().getString(R.string.edit_my_nick), GaanaApplication.getContext().getResources().getString(R.string.download_all_songs), GaanaApplication.getContext().getResources().getString(R.string.opt_leave_playlist), GaanaApplication.getContext().getResources().getString(R.string.opt_auto_reorder), GaanaApplication.getContext().getResources().getString(R.string.opt_rename_playlist), GaanaApplication.getContext().getResources().getString(R.string.opt_remove_from_party), GaanaApplication.getContext().getResources().getString(R.string.opt_delete_party), GaanaApplication.getContext().getResources().getString(R.string.opt_share_story), GaanaApplication.getContext().getResources().getString(R.string.opt_remove_from_list)};
        this.menuId = new int[]{R.id.addToPlaylistMenu, R.id.favoriteMenu, R.id.radioMenu, R.id.shareMenu, R.id.similarArtistMenu, R.id.similarAlbumMenu, R.id.enqueueMenu, R.id.editPlaylistMenu, R.id.deletePlaylistMenu, R.id.artistMenu, R.id.albumMenu, R.id.deleteDownloadMenu, R.id.lyricsMenu, R.id.shuffleMenu, R.id.playNextMenu, R.id.deleteLocalItemsMenu, R.id.menu_add_playlist, R.id.clearQueue, R.id.download_songs, R.id.addMoreSongs, R.id.songInfoMenu, R.id.albumInfoMenu, R.id.playlistInfoMenu, R.id.enqueueNextMenu, R.id.share_playlist, R.id.edit_nick, R.id.download_all_songs, R.id.leave_playlist, R.id.auto_reorder, R.id.rename_playlist, R.id.remove_from_party, R.id.delete_party, R.id.share_story, R.id.remove_from_list};
        this.currentArrayOfOptions = null;
        this.ADDTOPLYALIST = 0;
        this.FAVORITE = 1;
        this.RADIO = 2;
        this.SHARE = 3;
        this.SIMILARARTIST = 4;
        this.SIMILARALBUM = 5;
        this.ENQUEUE = 6;
        this.EDITPLAYLIST = 7;
        this.DELETEPLAYLIST = 8;
        this.ARTIST = 9;
        this.ALBUM = 10;
        this.DELETEDOWNLOAD = 11;
        this.LYRICS = 12;
        this.SHUFFLE = 13;
        this.PLAYNEXT = 14;
        this.DELETE = 15;
        this.ADD_TO_PAYLIST = 16;
        this.CLEARQUEUE = 17;
        this.DOWNLOAD_SONGS = 18;
        this.ADDMORESONGS = 19;
        this.SONGINFO = 20;
        this.ALBUMINFO = 21;
        this.PLAYLISTINFO = 22;
        this.ENQUEUENEXT = 23;
        this.SHARE_PLAYLIST = 24;
        this.EDIT_NICK_NAME = 25;
        this.DOWNLOAD_ALL_SONGS = 26;
        this.LEAVE_PLAYLIST = 27;
        this.AUTO_REORDER = 28;
        this.RENAME_PLAYLIST = 29;
        this.REMOVE_FROM_PARTY = 30;
        this.DELETE_PARTY = 31;
        this.SHARESTORY = 32;
        this.REMOVE_FROM_LIST = 33;
        this.albumMoreoptionsIndex = new int[]{1, 11, 3, 0, 33, 13, 23, 6, 5, 9, 21};
        this.albumMoreoptionsIndexShuffle = new int[]{1, 11, 3, 0, 33, 13, 5, 9, 21};
        this.jukeGuestPlaylistMoreOptionsIndex = new int[]{24, 25, 26, 27};
        this.jukeAdminPlaylistMoreOptionsIndex = new int[]{24, 29, 25, 26, 28, 31};
        this.songsMoreoptionsIndex = new int[]{1, 18, 3, 32, 0, 33, 23, 6, 2, 9, 10, 12, 20};
        this.jukeSongsMoreoptionsIndex = new int[]{1, 18, 3, 32, 0, 23, 30, 9, 10, 12, 20};
        this.artistMoreoptionsIndex = new int[]{1, 3, 33, 4};
        this.radioMoreoptionsIndex = new int[]{1, 3};
        this.playlistMoreoptionsIndex = new int[]{1, 11, 3, 0, 33, 13, 23, 6, 22};
        this.playlistMoreoptionsIndexShuffle = new int[]{1, 11, 3, 0, 33, 13, 22};
        this.myPlaylistMoreoptionsIndex = new int[]{1, 11, 3, 0, 19, 13, 7, 23, 6, 8};
        this.myPlaylistMoreoptionsIndexGaanaMini = new int[]{1, 11, 3, 0, 13, 7, 23, 6};
        this.automatedPlaylist = new int[]{6, 23, 11, 0, 13};
        this.myMusicMoreoptionsIndexTrack = new int[]{6, 23, 0, 9, 10, 15};
        this.myMusicMoreoptionsIndex = new int[]{6, 23, 0, 13, 7, 15};
        this.playerMoreoptionsIndex = new int[]{17};
        this.isUserCreatedPlaylist = false;
        this.isQueueOpen = false;
        this.mBusinessObject = businessObject;
        this.mAppState = GaanaApplication.getInstance();
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mFragment = baseGaanaFragment;
        requestWindowFeature(1);
        init(context);
    }

    private void init(Context context) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(R.styleable.VectorDrawables);
        for (int i = 0; i < this.drawableAttrIds.length; i++) {
            this.drawables[i] = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(this.drawableAttrIds[i], -1));
        }
        obtainStyledAttributes.recycle();
    }

    public void setOnDismissListener(IDismissPopup iDismissPopup) {
        this.mIDismissPopup = iDismissPopup;
    }

    public View getView(boolean z) {
        this.isPlayerQueue = z;
        if (this.mView == null && this.mBusinessObject != null) {
            populateViewSongs();
        }
        return this.mView;
    }

    public View getOneTouchRadioOptionView() {
        if (this.mView == null) {
            this.mView = this.mInflater.inflate(R.layout.popup_context_menu, null);
        }
        setContentView(this.mView);
        this.mListView = (PopUpMenuListView) this.mView.findViewById(R.id.playerrQueueListView);
        BottomSheetBehavior.from(this.mView.findViewById(R.id.container)).setState(3);
        final RadioMoods radioMoods = (RadioMoods) this.mBusinessObject;
        View inflate = this.mInflater.inflate(R.layout.view_one_touch_popup, null);
        inflate.setPadding(this.mContext.getResources().getDimensionPixelOffset(R.dimen.activity_horizontal_margin), this.mContext.getResources().getDimensionPixelOffset(R.dimen.activity_horizontal_margin), 0, this.mContext.getResources().getDimensionPixelOffset(R.dimen.activity_horizontal_margin_half));
        ((TextView) inflate.findViewById(R.id.textView)).setText(R.string.choose_mood);
        TypedValue typedValue = new TypedValue();
        this.mContext.getTheme().resolveAttribute(R.attr.second_line_color, typedValue, true);
        ((TextView) inflate.findViewById(R.id.textView)).setTextColor(typedValue.data);
        RadioMoodsAdapter radioMoodsAdapter = new RadioMoodsAdapter(this.mContext, R.layout.radio_moods_row_xml, radioMoods.getArrListItem());
        this.mListView.addHeaderView(inflate);
        this.mListView.setAdapter(radioMoodsAdapter);
        this.mListView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (i != 0) {
                    i -= PopupItemView.this.mListView.getHeaderViewsCount();
                    String str = "0";
                    if (PopupItemView.this.mAppState.getCurrentUser().getLoginStatus() && PopupItemView.this.mAppState.getCurrentUser().getUserProfile() != null) {
                        str = PopupItemView.this.mAppState.getCurrentUser().getUserProfile().getUserId();
                    }
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("https://api.gaana.com/home/one-touch-songs/");
                    stringBuilder.append(((RadioMood) radioMoods.getArrListItem().get(i)).getEntityId());
                    String stringBuilder2 = stringBuilder.toString();
                    PopupItemView.this.dismiss();
                    StringBuilder stringBuilder3 = new StringBuilder();
                    stringBuilder3.append("One Touch Radio - item - ");
                    stringBuilder3.append(((RadioMood) radioMoods.getArrListItem().get(i)).getEntityId());
                    String stringBuilder4 = stringBuilder3.toString();
                    StringBuilder stringBuilder5 = new StringBuilder();
                    stringBuilder5.append("RadioScreen - One Touch Radio - item - ");
                    stringBuilder5.append(((RadioMood) radioMoods.getArrListItem().get(i)).getEntityId());
                    ((BaseActivity) PopupItemView.this.mContext).sendGAEvent("RadioScreen", stringBuilder4, stringBuilder5.toString());
                    an.a().a("click", "en", BusinessObjectType.RadioMoods.toString(), an.a().a(an.a().a), ((RadioMood) radioMoods.getArrListItem().get(i)).getEntityId(), "", "", "");
                    BusinessObject businessObject = new BusinessObject();
                    businessObject.setName("One Touch Radio");
                    businessObject.setBusinessObjId(str);
                    ad.a(PopupItemView.this.mContext).a(stringBuilder2, SOURCE_TYPE.ONE_TOUCH_RADIO.ordinal(), businessObject);
                }
            }
        });
        return this.mView;
    }

    public View getPlayerOptionView() {
        if (this.mView == null) {
            this.mView = this.mInflater.inflate(R.layout.popup_context_menu, null);
        }
        setContentView(this.mView);
        this.mListView = (PopUpMenuListView) this.mView.findViewById(R.id.playerrQueueListView);
        BottomSheetBehavior.from(this.mListView).setState(3);
        this.listAdapter = new PlayerQueueAdapter();
        this.currentArrayOfOptions = this.playerMoreoptionsIndex;
        this.listAdapter.setCount(this.currentArrayOfOptions.length);
        this.mListView.setAdapter(this.listAdapter);
        return this.mView;
    }

    private void populateViewSongs() {
        Track track = null;
        if (this.mView == null) {
            this.mView = this.mInflater.inflate(R.layout.popup_context_menu, null);
        }
        setContentView(this.mView);
        View inflate = this.mInflater.inflate(R.layout.popupmenu_header, null);
        View findViewById = inflate.findViewById(R.id.song_share);
        findViewById.setTag(Integer.valueOf(R.id.shareMenu));
        findViewById.setOnClickListener(this);
        View findViewById2 = inflate.findViewById(R.id.song_favorite);
        findViewById2.setTag(Integer.valueOf(R.id.favoriteMenu));
        findViewById2.setOnClickListener(this);
        if (this.mBusinessObject.isFavorite().booleanValue()) {
            new int[1][0] = R.attr.moreoptions_favorited;
            TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
            Drawable drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(51, -1));
            obtainStyledAttributes.recycle();
            ((ImageView) findViewById2).setImageDrawable(drawable);
        }
        this.mListView = (PopUpMenuListView) this.mView.findViewById(R.id.playerrQueueListView);
        ViewGroup viewGroup = (ViewGroup) this.mView.findViewById(R.id.container);
        BottomSheetBehavior.from(viewGroup).setState(3);
        final TextView textView = (TextView) inflate.findViewById(R.id.f43download.item.tv.trackname);
        textView.setTypeface(textView.getTypeface(), 1);
        TextView textView2 = (TextView) inflate.findViewById(R.id.f40download.item.tv.genere);
        this.listAdapter = new PlayerQueueAdapter();
        if ((this.mBusinessObject instanceof Track) || (this.mBusinessObject instanceof OfflineTrack)) {
            if (this.mBusinessObject instanceof Track) {
                track = (Track) this.mBusinessObject;
            } else if (this.mBusinessObject instanceof OfflineTrack) {
                if (this.mBusinessObject.isLocalMedia()) {
                    track = LocalMediaManager.getInstance(this.mContext).getTrackFromLocalMedia((OfflineTrack) this.mBusinessObject);
                } else {
                    track = (Track) DownloadManager.c().a(this.mBusinessObject.getBusinessObjId(), true);
                }
            }
            if (track != null) {
                int i;
                String name = track.getName();
                String playCount = track.getPlayCount();
                String albumTitle = track.getAlbumTitle();
                String artwork = track.getArtwork();
                String artistNames = track.getArtistNames();
                if (!TextUtils.isEmpty(artwork)) {
                    ((CrossFadeImageView) inflate.findViewById(R.id.f38download.item.img.thumb)).bindImage(artwork.replace("80x80", "175x175"), this.mAppState.isAppInOfflineMode());
                }
                textView.setText(name);
                textView.setTypeface(textView.getTypeface(), 1);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(albumTitle);
                stringBuilder.append(" - ");
                stringBuilder.append(artistNames);
                textView2.setText(stringBuilder.toString());
                TextView textView3 = (TextView) inflate.findViewById(R.id.f41download.item.tv.plays);
                if (f.b(this.mContext).equalsIgnoreCase("English")) {
                    i = 0;
                } else {
                    i = 0;
                    textView.setIncludeFontPadding(false);
                    textView2.setIncludeFontPadding(false);
                    textView3.setIncludeFontPadding(false);
                }
                if (TextUtils.isEmpty(playCount)) {
                    textView3.setVisibility(8);
                } else {
                    textView3.setVisibility(i);
                    textView3.setText(" ".concat(playCount));
                }
                this.currentArrayOfOptions = this.songsMoreoptionsIndex;
                if (this.mFragment instanceof JukePartyFragment) {
                    this.currentArrayOfOptions = this.jukeSongsMoreoptionsIndex;
                }
            } else {
                return;
            }
        } else if (this.mBusinessObject instanceof Album) {
            Album album = (Album) this.mBusinessObject;
            if (!TextUtils.isEmpty(album.getArtwork())) {
                ((CrossFadeImageView) inflate.findViewById(R.id.f38download.item.img.thumb)).bindImage(album.getArtwork().replace("80x80", "175x175"), this.mAppState.isAppInOfflineMode());
            }
            textView.setText(album.getName());
            textView2.setText(album.getArtistNames());
            if (Constants.ab) {
                this.currentArrayOfOptions = this.albumMoreoptionsIndexShuffle;
            } else {
                this.currentArrayOfOptions = this.albumMoreoptionsIndex;
            }
        } else if (this.mBusinessObject instanceof Artist) {
            Artist artist = (Artist) this.mBusinessObject;
            if (!TextUtils.isEmpty(artist.getArtwork())) {
                ((CrossFadeImageView) inflate.findViewById(R.id.f38download.item.img.thumb)).bindImage(artist.getArtwork().replace("80x80", "175x175"), this.mAppState.isAppInOfflineMode());
            }
            textView.setText(artist.getName());
            textView2.setText(artist.getDescription());
            this.currentArrayOfOptions = this.artistMoreoptionsIndex;
        } else if (this.mBusinessObject instanceof Playlist) {
            this.isUserCreatedPlaylist = isUserCreatedPlaylist();
            if (this.isUserCreatedPlaylist) {
                if (((Playlist) this.mBusinessObject).getAutomated() != null && ((Playlist) this.mBusinessObject).getAutomated().equalsIgnoreCase("1")) {
                    this.currentArrayOfOptions = this.automatedPlaylist;
                    findViewById.setVisibility(8);
                    findViewById.setOnClickListener(null);
                } else if (((Playlist) this.mBusinessObject).getIsMiniPlaylist() == null || !((Playlist) this.mBusinessObject).getIsMiniPlaylist().equalsIgnoreCase("1")) {
                    this.currentArrayOfOptions = this.myPlaylistMoreoptionsIndex;
                } else {
                    this.currentArrayOfOptions = this.myPlaylistMoreoptionsIndexGaanaMini;
                }
                findViewById2.setVisibility(8);
                findViewById2.setOnClickListener(null);
            } else if (Constants.ab) {
                this.currentArrayOfOptions = this.playlistMoreoptionsIndexShuffle;
            } else {
                this.currentArrayOfOptions = this.playlistMoreoptionsIndex;
            }
            Playlist playlist = (Playlist) this.mBusinessObject;
            if (!TextUtils.isEmpty(playlist.getArtwork())) {
                ((CrossFadeImageView) inflate.findViewById(R.id.f38download.item.img.thumb)).bindImage(playlist.getArtwork().replace("80x80", "175x175"), this.mAppState.isAppInOfflineMode());
            }
            textView.setText(playlist.getName());
            if (!TextUtils.isEmpty(playlist.getCreatedby())) {
                StringBuilder stringBuilder2 = new StringBuilder();
                stringBuilder2.append("By ");
                stringBuilder2.append(playlist.getCreatedby());
                textView2.setText(stringBuilder2.toString());
            }
        } else if (this.mBusinessObject instanceof Radio) {
            Radio radio = (Radio) this.mBusinessObject;
            ((CrossFadeImageView) inflate.findViewById(R.id.f38download.item.img.thumb)).bindImage(radio.getArtwork().replace("80x80", "175x175"), this.mAppState.isAppInOfflineMode());
            textView.setText(radio.getName());
            textView2.setVisibility(8);
            this.currentArrayOfOptions = this.radioMoreoptionsIndex;
        } else if (this.mBusinessObject instanceof JukePlaylist) {
            JukePlaylist jukePlaylist = (JukePlaylist) this.mBusinessObject;
            inflate.findViewById(R.id.f38download.item.img.thumb).setVisibility(8);
            textView.setGravity(49);
            textView.setText(jukePlaylist.getName());
            textView2.setGravity(49);
            if (jukePlaylist.getAdmin()) {
                this.currentArrayOfOptions = this.jukeAdminPlaylistMoreOptionsIndex;
            } else {
                this.currentArrayOfOptions = this.jukeGuestPlaylistMoreOptionsIndex;
            }
            if (TextUtils.isEmpty(jukePlaylist.getOwnerName())) {
                inflate.findViewById(R.id.f40download.item.tv.genere).setVisibility(8);
            } else {
                TextView textView4 = (TextView) inflate.findViewById(R.id.f40download.item.tv.genere);
                StringBuilder stringBuilder3 = new StringBuilder();
                stringBuilder3.append(this.mContext.getString(R.string.by));
                stringBuilder3.append(jukePlaylist.getOwnerName());
                textView4.setText(stringBuilder3.toString());
            }
        }
        if (this.mBusinessObject.isLocalMedia()) {
            if ((this.mBusinessObject instanceof OfflineTrack) || (this.mBusinessObject instanceof Track)) {
                this.currentArrayOfOptions = this.myMusicMoreoptionsIndexTrack;
            } else {
                this.currentArrayOfOptions = this.myMusicMoreoptionsIndex;
            }
            findViewById2.setVisibility(8);
            findViewById.setVisibility(8);
        }
        this.listAdapter.setCount(this.currentArrayOfOptions.length);
        viewGroup.addView(inflate, 0);
        this.mListView.setAdapter(this.listAdapter);
        textView.setEllipsize(TruncateAt.MARQUEE);
        textView.setMarqueeRepeatLimit(3);
        textView.setSingleLine(true);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                textView.setSelected(true);
            }
        }, AdaptiveTrackSelection.DEFAULT_MIN_TIME_BETWEEN_BUFFER_REEVALUTATION_MS);
    }

    public void refreshListView() {
        if (this.mListView != null && this.listAdapter != null) {
            this.listAdapter.setAdapterArrayList(PlayerManager.a(this.mContext).n());
            this.listAdapter.notifyDataSetChanged();
        }
    }

    public void notifyDataSetChange() {
        if (this.listAdapter != null) {
            this.listAdapter.notifyDataSetChanged();
        }
    }

    private void populateView(ArrayList<?> arrayList) {
        if (this.mView == null) {
            this.mView = this.mInflater.inflate(R.layout.popup_player_queue, null);
        }
        setContentView(this.mView);
        this.mListView = (PopUpMenuListView) this.mView.findViewById(R.id.playerrQueueListView);
        this.mDownloadSongsItemView = new DownloadSongsItemView(this.mContext, null);
        this.listAdapter = new PlayerQueueAdapter();
        this.listAdapter.setAdapterArrayList(arrayList);
        this.mListView.setAdapter(this.listAdapter);
    }

    public ArrayList<?> getAdapterArrayList() {
        return this.listAdapter != null ? this.listAdapter.getAdapterArrayList() : null;
    }

    private View showHideView(View view, int i) {
        ImageView imageView;
        TypedArray obtainStyledAttributes;
        Drawable drawable;
        if (i == R.id.favoriteMenu) {
            if (this.mFragment instanceof DownloadDetailsFragment) {
                return new View(this.mContext);
            }
            TextView textView = (TextView) view.findViewById(R.id.textView);
            imageView = (ImageView) view.findViewById(R.id.imageView);
            if (this.mBusinessObject instanceof Artist) {
                if (this.mBusinessObject.isFavorite().booleanValue()) {
                    textView.setText(this.mContext.getResources().getString(R.string.unfollow_artist));
                    new int[1][0] = R.attr.moreoptions_unfollow;
                    obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                    drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(59, -1));
                    obtainStyledAttributes.recycle();
                    imageView.setImageDrawable(drawable);
                } else {
                    textView.setText(this.mContext.getResources().getString(R.string.follow_artist));
                    new int[1][0] = R.attr.moreoptions_follow;
                    obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                    drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(53, -1));
                    obtainStyledAttributes.recycle();
                    imageView.setImageDrawable(drawable);
                }
            } else if (this.mBusinessObject.isFavorite().booleanValue()) {
                textView.setText(this.mContext.getResources().getString(R.string.remove_from_mymusic));
                new int[1][0] = R.attr.moreoptions_favorited;
                obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(51, -1));
                obtainStyledAttributes.recycle();
                imageView.setImageDrawable(drawable);
            } else {
                textView.setText(this.mContext.getResources().getString(R.string.add_to_mymusic));
                new int[1][0] = R.attr.moreoptions_favorite;
                obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(49, -1));
                obtainStyledAttributes.recycle();
                imageView.setImageDrawable(drawable);
            }
        } else if (i == R.id.deleteDownloadMenu) {
            if (this.mFragment instanceof MyMusicItemFragment) {
                DownloadStatus h = DownloadManager.c().h(Integer.parseInt(this.mBusinessObject.getBusinessObjId()));
                if (h == DownloadStatus.DOWNLOADED || h == DownloadStatus.PAUSED || h == DownloadStatus.PARTIALLY_DOWNLOADED || h == DownloadStatus.QUEUED) {
                    return getDownloadStatus(view);
                }
                return new View(this.mContext);
            } else if (this.mFragment instanceof DownloadDetailsFragment) {
                return getDownloadStatus(view);
            } else {
                return new View(this.mContext);
            }
        } else if (i == R.id.artistMenu) {
            if (this.mBusinessObject instanceof Album) {
                Album album = (Album) this.mBusinessObject;
                album.getComposers();
                if (album.getComposers() == null || album.getComposers().size() == 0) {
                    return new View(this.mContext);
                }
                return view;
            }
        } else if (i == R.id.deleteLocalItemsMenu) {
            return (((this.mBusinessObject instanceof Playlist) && !this.mBusinessObject.getBusinessObjId().equals(LocalMediaManager.PLYALIST_RECENTLY_ADDED_ID)) || (this.mBusinessObject instanceof Track) || (this.mBusinessObject instanceof OfflineTrack)) ? view : new View(this.mContext);
        } else {
            PlayerTrack i2;
            if (i == R.id.editPlaylistMenu || i == R.id.addMoreSongs) {
                if (!(this.mBusinessObject instanceof Playlist) || this.mBusinessObject.getBusinessObjId().equals(LocalMediaManager.PLYALIST_RECENTLY_ADDED_ID)) {
                    return new View(this.mContext);
                }
                return view;
            } else if (i == R.id.lyricsMenu) {
                CharSequence charSequence = null;
                if (this.mBusinessObject instanceof Track) {
                    charSequence = ((Track) this.mBusinessObject).getLyricsUrl();
                } else if ((this.mBusinessObject instanceof OfflineTrack) && !this.mBusinessObject.isLocalMedia()) {
                    charSequence = ((Track) DownloadManager.c().a(this.mBusinessObject.getBusinessObjId(), true)).getLyricsUrl();
                }
                if (TextUtils.isEmpty(charSequence)) {
                    return new View(this.mContext);
                }
                return view;
            } else if (i == R.id.albumMenu) {
                if (!this.isPlayerQueue && (this.mFragment instanceof AlbumDetailsMaterialListing)) {
                    return ((AlbumDetailsMaterialListing) this.mFragment).c() instanceof Album ? new View(this.mContext) : view;
                } else {
                    if (!this.isPlayerQueue && (this.mFragment instanceof GaanaSpecialDetailsMaterialListing)) {
                        return ((GaanaSpecialDetailsMaterialListing) this.mFragment).d() instanceof Album ? new View(this.mContext) : view;
                    } else {
                        if (!this.isPlayerQueue && (this.mFragment instanceof RevampedDetailListing)) {
                            return ((RevampedDetailListing) this.mFragment).s() instanceof Album ? new View(this.mContext) : view;
                        }
                    }
                }
            } else if (i == R.id.enqueueMenu) {
                if (this.isPlayerQueue) {
                    PlayerTrack i3 = PlayerManager.a(this.mContext).i();
                    if (i3 != null && i3.b() != null && this.mBusinessObject.getBusinessObjId().equalsIgnoreCase(i3.h())) {
                        return new View(this.mContext);
                    }
                    ((TextView) view.findViewById(R.id.textView)).setText(R.string.remove_from_queue);
                    imageView = (ImageView) view.findViewById(R.id.imageView);
                    new int[1][0] = R.attr.moreoptions_dequeue;
                    obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                    drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(43, -1));
                    obtainStyledAttributes.recycle();
                    imageView.setImageDrawable(drawable);
                    view.setTag(Integer.valueOf(R.id.dequeueMenu));
                }
            } else if (i == R.id.playNextMenu) {
                if (this.isPlayerQueue) {
                    i2 = PlayerManager.a(this.mContext).i();
                    if (!(i2 == null || i2.b() == null || !this.mBusinessObject.getBusinessObjId().equalsIgnoreCase(i2.h()))) {
                        return new View(this.mContext);
                    }
                }
            } else if (i == R.id.download_songs) {
                if (this.mBusinessObject.isLocalMedia) {
                    return new View(this.mContext);
                }
                DownloadStatus e = DownloadManager.c().e(Integer.parseInt(this.mBusinessObject.getBusinessObjId()));
                TextView textView2 = (TextView) view.findViewById(R.id.textView);
                imageView = (ImageView) view.findViewById(R.id.imageView);
                if (e != null) {
                    if (e == DownloadStatus.DOWNLOADING) {
                        return new View(this.mContext);
                    }
                    if (e == DownloadStatus.DOWNLOADED) {
                        new int[1][0] = R.attr.moreoptions_delete;
                        obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                        drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(41, -1));
                        obtainStyledAttributes.recycle();
                        textView2.setText(this.mContext.getResources().getString(R.string.opt_delete_downloads));
                        imageView.setImageDrawable(drawable);
                    } else if (e == DownloadStatus.QUEUED) {
                        textView2.setText(this.mContext.getResources().getString(R.string.download_queued));
                        imageView.setImageResource(R.drawable.vector_download_button_queued);
                    } else if (e == DownloadStatus.TRIED_BUT_FAILED) {
                        obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                        drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(91, -1));
                        obtainStyledAttributes.recycle();
                        imageView.setImageDrawable(drawable);
                    } else if ((this.mFragment instanceof DownloadDetailsFragment) || (this.mFragment instanceof MyMusicItemFragment)) {
                        return new View(this.mContext);
                    } else {
                        new int[1][0] = R.attr.download_button_paused;
                        obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                        drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(44, -1));
                        obtainStyledAttributes.recycle();
                        textView2.setText(this.mContext.getResources().getString(R.string.download_camelcase));
                        imageView.setImageDrawable(drawable);
                    }
                } else if ((this.mFragment instanceof DownloadDetailsFragment) || (this.mFragment instanceof MyMusicItemFragment)) {
                    return new View(this.mContext);
                } else {
                    new int[1][0] = R.attr.download_button_paused;
                    obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                    drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(44, -1));
                    obtainStyledAttributes.recycle();
                    textView2.setText(this.mContext.getResources().getString(R.string.download_camelcase));
                    imageView.setImageDrawable(drawable);
                }
            } else if (i == R.id.enqueueNextMenu) {
                if (this.isPlayerQueue) {
                    i2 = PlayerManager.a(this.mContext).i();
                    if (!(i2 == null || i2.b() == null || !this.mBusinessObject.getBusinessObjId().equalsIgnoreCase(i2.h()))) {
                        return new View(this.mContext);
                    }
                }
            } else if (i == R.id.remove_from_party && (this.mFragment instanceof JukePartyFragment)) {
                BusinessObject parentBusinessObject = ((JukePartyFragment) this.mFragment).getParentBusinessObject();
                if ((parentBusinessObject instanceof JukePlaylist) && !((JukePlaylist) parentBusinessObject).getAdmin()) {
                    return new View(this.mContext);
                }
            } else if (i == R.id.share_story) {
                if (!j.a(this.mContext)) {
                    return new View(this.mContext);
                }
            } else if (!(i != R.id.remove_from_list || this.isRemoveRecentlyPlayed || this.isRemoveRecentSearch)) {
                return new View(this.mContext);
            }
        }
        if (Constants.cY) {
            if (this.mBusinessObject.isLocalMedia && (i == R.id.enqueueMenu || i == R.id.shuffleMenu || i == R.id.radioMenu || i == R.id.enqueueNextMenu)) {
                return new View(this.mContext);
            }
            if (i == R.id.radioMenu) {
                return new View(this.mContext);
            }
            if (i == R.id.enqueueMenu) {
                ((TextView) view.findViewById(R.id.textView)).setText(R.string.opt_add_to_party);
            }
        }
        return view;
    }

    public View getDownloadStatus(View view) {
        if (this.mBusinessObject.isLocalMedia) {
            return new View(this.mContext);
        }
        DownloadStatus h = DownloadManager.c().h(Integer.parseInt(this.mBusinessObject.getBusinessObjId()));
        TextView textView = (TextView) view.findViewById(R.id.textView);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        TypedArray obtainStyledAttributes;
        Drawable drawable;
        if (h == null) {
            new int[1][0] = R.attr.download_button_paused;
            obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
            drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(13, -1));
            obtainStyledAttributes.recycle();
            textView.setText(this.mContext.getResources().getString(R.string.download_camelcase));
            imageView.setImageDrawable(drawable);
        } else if (h == DownloadStatus.DOWNLOADING) {
            return new View(this.mContext);
        } else {
            if (h == DownloadStatus.DOWNLOADED || h == DownloadStatus.PARTIALLY_DOWNLOADED || h == DownloadStatus.PAUSED || h == DownloadStatus.QUEUED) {
                new int[1][0] = R.attr.moreoptions_delete;
                obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                Drawable drawable2 = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(41, -1));
                obtainStyledAttributes.recycle();
                textView.setText(this.mContext.getResources().getString(R.string.opt_delete_downloads));
                imageView.setImageDrawable(drawable2);
            } else if (h == DownloadStatus.QUEUED) {
                textView.setText(this.mContext.getResources().getString(R.string.download_queued));
                imageView.setImageResource(R.drawable.vector_download_button_queued);
            } else if (h == DownloadStatus.TRIED_BUT_FAILED) {
                obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                Drawable drawable3 = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(91, -1));
                obtainStyledAttributes.recycle();
                imageView.setImageDrawable(drawable3);
            } else {
                new int[1][0] = R.attr.download_button_paused;
                obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
                drawable = ContextCompat.getDrawable(getContext(), obtainStyledAttributes.getResourceId(13, -1));
                obtainStyledAttributes.recycle();
                textView.setText(this.mContext.getResources().getString(R.string.download_camelcase));
                imageView.setImageDrawable(drawable);
            }
        }
        return view;
    }

    private View populateSongsMenu(int i, View view, ViewGroup viewGroup) {
        int i2 = this.menuId[this.currentArrayOfOptions[i]];
        View inflate = this.mInflater.inflate(R.layout.popup_songs, viewGroup, false);
        if (this.menuId[this.currentArrayOfOptions[i]] == R.id.artistMenu) {
            ((TextView) inflate.findViewById(R.id.textView)).setVisibility(8);
            ArtistNamesView artistNamesView = (ArtistNamesView) inflate.findViewById(R.id.singer_names_layout);
            artistNamesView.initContainer(inflate);
            artistNamesView.setVisibility(0);
            artistNamesView.setBussinessObject(this.mBusinessObject);
            if (this.mBusinessObject instanceof Album) {
                artistNamesView.setTitle(inflate, this.mContext.getResources().getString(R.string.more_info_composers));
            } else {
                artistNamesView.setTitle(inflate, this.mContext.getResources().getString(R.string.more_info_singers));
            }
            artistNamesView.setArtistClickListener(new IArtistClickHandler() {
                public void ArtistClicked(String str, String str2) {
                    PopupItemView.this.dismiss();
                    if (PopupItemView.this.mFragment != null && (PopupItemView.this.mFragment instanceof RevampedDetailListing)) {
                        ((RevampedDetailListing) PopupItemView.this.mFragment).a("Go to Artist", false);
                    }
                    if ((PopupItemView.this.mBusinessObject instanceof OfflineTrack) && !PopupItemView.this.mBusinessObject.isLocalMedia()) {
                        Track d = new h(PopupItemView.this.mContext).d(PopupItemView.this.mBusinessObject.getBusinessObjId());
                        if (d != null) {
                            Iterator it = d.getArtists().iterator();
                            while (it.hasNext()) {
                                Track.Artist artist = (Track.Artist) it.next();
                                if (artist.name.trim().equalsIgnoreCase(str2.trim())) {
                                    str = artist.artist_id;
                                }
                            }
                        }
                    }
                    af.a(PopupItemView.this.mContext, PopupItemView.this.mFragment).a(str, str2, PopupItemView.this.mBusinessObject);
                }
            });
        } else {
            ((LinearLayout) inflate.findViewById(R.id.singer_names_layout)).setVisibility(8);
            TextView textView = (TextView) inflate.findViewById(R.id.textView);
            try {
                ((ImageView) inflate.findViewById(R.id.imageView)).setImageDrawable(this.drawables[this.currentArrayOfOptions[i]]);
                if (i2 != R.id.auto_reorder || !(this.mBusinessObject instanceof JukePlaylist)) {
                    textView.setText(this.textArray[this.currentArrayOfOptions[i]]);
                } else if (((JukePlaylist) this.mBusinessObject).getVoteEnable()) {
                    textView.setText(String.format(this.textArray[this.currentArrayOfOptions[i]], new Object[]{"Off"}));
                } else {
                    textView.setText(String.format(this.textArray[this.currentArrayOfOptions[i]], new Object[]{"On"}));
                }
                inflate.setTag(Integer.valueOf(i2));
                if (!(i2 != R.id.addToPlaylistMenu || this.isRemoveRecentlyPlayed || this.isRemoveRecentSearch) || i2 == R.id.radioMenu || i2 == R.id.remove_from_party || i2 == R.id.remove_from_list) {
                    inflate.findViewById(R.id.horizontalLine).setVisibility(0);
                }
            } catch (Exception unused) {
            }
        }
        inflate.setOnClickListener(this);
        return showHideView(inflate, i2);
    }

    public void setFavoriteCompletedListner(a aVar) {
        this.mListener = aVar;
    }

    public void setQueueOpen(boolean z) {
        this.isQueueOpen = z;
    }

    public void setRemoveRecentSearch(boolean z) {
        this.isRemoveRecentSearch = z;
    }

    public void setRemoveRecentlyPlayed(boolean z) {
        this.isRemoveRecentlyPlayed = z;
    }

    public void onClick(View view) {
        Object tag = view.getTag();
        if (tag != null) {
            int intValue = ((Integer) tag).intValue();
            if (intValue != 0) {
                if (intValue == R.id.clearQueue) {
                    dismiss();
                    clearQueue();
                } else if (intValue == R.id.download_songs) {
                    dismiss();
                    if (this.mDownloadPopupListener != null) {
                        setSectionNameForMenuItem(this.mBusinessObject, intValue);
                        this.mDownloadPopupListener.onPopupClicked(this.mBusinessObject.getBusinessObjId(), this.mBusinessObject);
                    }
                } else if (intValue == R.id.enqueueMenu || intValue == R.id.dequeueMenu || intValue == R.id.playNextMenu || intValue == R.id.favoriteMenu || intValue == R.id.deleteLocalItemsMenu || intValue == R.id.addMoreSongs || intValue == R.id.enqueueNextMenu || intValue == R.id.menu_add_playlist || intValue == R.id.addToPlaylistMenu || !this.isPlayerQueue) {
                    dismiss();
                    setSectionNameForMenuItem(this.mBusinessObject, intValue);
                    if (intValue == R.id.menu_add_playlist || intValue == R.id.addToPlaylistMenu) {
                        String str = "Song ";
                        String str2 = "Add to Playlist";
                        StringBuilder stringBuilder;
                        if (this.mBusinessObject instanceof Track) {
                            if (this.mFragment instanceof GaanaSpecialDetailsMaterialListing) {
                                stringBuilder = new StringBuilder();
                                stringBuilder.append("Episode ");
                                stringBuilder.append(this.mBusinessObject.getBusinessObjId());
                                str = stringBuilder.toString();
                            } else {
                                stringBuilder = new StringBuilder();
                                stringBuilder.append("Song ");
                                stringBuilder.append(this.mBusinessObject.getBusinessObjId());
                                str = stringBuilder.toString();
                            }
                        } else if (this.mBusinessObject instanceof Playlist) {
                            if (((Playlist) this.mBusinessObject).isGaanaSpecial()) {
                                stringBuilder = new StringBuilder();
                                stringBuilder.append("Gaana Special ");
                                stringBuilder.append(this.mBusinessObject.getBusinessObjId());
                                str = stringBuilder.toString();
                            } else {
                                stringBuilder = new StringBuilder();
                                stringBuilder.append("Playlist ");
                                stringBuilder.append(this.mBusinessObject.getBusinessObjId());
                                str = stringBuilder.toString();
                            }
                        } else if (this.mBusinessObject instanceof Album) {
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("Album ");
                            stringBuilder.append(this.mBusinessObject.getBusinessObjId());
                            str = stringBuilder.toString();
                        } else if (this.mBusinessObject instanceof Artist) {
                            stringBuilder = new StringBuilder();
                            stringBuilder.append("Artist ");
                            stringBuilder.append(this.mBusinessObject.getBusinessObjId());
                            str = stringBuilder.toString();
                        }
                        String str3 = this.isPlayerQueue ? this.isQueueOpen ? "Context Menu Queue Screen" : "Context Menu Player Screen" : this.mFragment instanceof DownloadDetailsFragment ? "My Music Downloads" : this.mFragment instanceof FavoritesFragment ? "My Music Favorites" : this.mFragment instanceof MyMusicItemFragment ? LocalMediaManager.MY_MUSIC : this.mFragment instanceof SearchEnchancedFragment ? "Search" : "Context Menu";
                        u.a().a(str2, str3, str);
                    }
                    if (intValue != R.id.favoriteMenu) {
                        af.a(this.mContext, this.mFragment).a(intValue, this.mBusinessObject);
                    } else {
                        af a = af.a(this.mContext, this.mFragment);
                        a.a("Context Menu");
                        StringBuilder stringBuilder2;
                        if (this.isPlayerQueue) {
                            if (this.isQueueOpen) {
                                stringBuilder2 = new StringBuilder();
                                stringBuilder2.append("Queue ");
                                stringBuilder2.append(this.mBusinessObject.getBusinessObjId());
                                a.b(stringBuilder2.toString());
                            } else if (ad.a(this.mAppState).m()) {
                                stringBuilder2 = new StringBuilder();
                                stringBuilder2.append("Radio Player ");
                                stringBuilder2.append(this.mBusinessObject.getBusinessObjId());
                                a.b(stringBuilder2.toString());
                            } else {
                                stringBuilder2 = new StringBuilder();
                                stringBuilder2.append("Player ");
                                stringBuilder2.append(this.mBusinessObject.getBusinessObjId());
                                a.b(stringBuilder2.toString());
                            }
                        } else if (((GaanaActivity) this.mContext).getCurrentFragment() instanceof AlbumDetailsMaterialListing) {
                            stringBuilder2 = new StringBuilder();
                            stringBuilder2.append("Playlist ");
                            stringBuilder2.append(this.mBusinessObject.getBusinessObjId());
                            a.b(stringBuilder2.toString());
                        } else if (((GaanaActivity) this.mContext).getCurrentFragment() instanceof GaanaSpecialDetailsMaterialListing) {
                            stringBuilder2 = new StringBuilder();
                            stringBuilder2.append("Gaana Special ");
                            stringBuilder2.append(this.mBusinessObject.getBusinessObjId());
                            a.b(stringBuilder2.toString());
                        } else if ((((GaanaActivity) this.mContext).getCurrentFragment() instanceof RadioDetailsMaterialListing) || (((GaanaActivity) this.mContext).getCurrentFragment() instanceof RadioActivityFragment)) {
                            stringBuilder2 = new StringBuilder();
                            stringBuilder2.append("Radio ");
                            stringBuilder2.append(this.mBusinessObject.getBusinessObjId());
                            a.b(stringBuilder2.toString());
                        } else if (((GaanaActivity) this.mContext).getCurrentFragment() instanceof ArtistDetailsMaterialListing) {
                            stringBuilder2 = new StringBuilder();
                            stringBuilder2.append("Artist ");
                            stringBuilder2.append(this.mBusinessObject.getBusinessObjId());
                            a.b(stringBuilder2.toString());
                        } else if (((GaanaActivity) this.mContext).getCurrentFragment() instanceof SearchEnchancedFragment) {
                            stringBuilder2 = new StringBuilder();
                            stringBuilder2.append("Search ");
                            stringBuilder2.append(this.mBusinessObject.getBusinessObjId());
                            a.b(stringBuilder2.toString());
                        } else if (((GaanaActivity) this.mContext).getCurrentFragment() instanceof SongParallexListingFragment) {
                            stringBuilder2 = new StringBuilder();
                            stringBuilder2.append("Song Listing ");
                            stringBuilder2.append(this.mBusinessObject.getBusinessObjId());
                            a.b(stringBuilder2.toString());
                        }
                        a.a(intValue, this.mBusinessObject, this.mListener);
                    }
                    if (intValue == R.id.enqueueMenu) {
                        u.a().a("Context Menu", "Add to Queue", this.isPlayerQueue ? "Queue" : this.mBusinessObject.getBusinessObjType().name());
                    } else if (intValue == R.id.enqueueNextMenu) {
                        u.a().a("Context Menu", "Play Next", this.isPlayerQueue ? "Queue" : this.mBusinessObject.getBusinessObjType().name());
                    }
                    if (intValue == R.id.remove_from_list) {
                        dismiss();
                        if (this.isRemoveRecentSearch && this.mBusinessObject != null) {
                            GaanaSearchManager.a().a(this.mBusinessObject.getBusinessObjId(), this.mBusinessObject.getBusinessObjType());
                        } else if (this.isRemoveRecentlyPlayed && this.mBusinessObject != null) {
                            aq.a().a(this.mBusinessObject.getBusinessObjId());
                        }
                    }
                } else {
                    dismiss();
                    if (this.mIDismissPopup != null) {
                        this.mIDismissPopup.dismiss(true);
                    }
                    setSectionNameForMenuItem(this.mBusinessObject, intValue);
                    af.a(this.mContext, this.mFragment).a(intValue, this.mBusinessObject);
                }
            }
        }
    }

    private void setSectionNameForMenuItem(BusinessObject businessObject, int i) {
        if (businessObject == null || businessObject.isLocalMedia()) {
            this.mAppState.setPlayoutSectionName(PLAYOUT_SECTION_TYPE.LOCAL.name());
        } else if (i == R.id.albumMenu) {
            this.mAppState.setPlayoutSectionName(PLAYOUT_SECTION_TYPE.ALBUMS.name());
        } else if (i == R.id.artistMenu) {
            this.mAppState.setPlayoutSectionName(PLAYOUT_SECTION_TYPE.ARTISTS.name());
        }
    }

    public boolean isUserCreatedPlaylist() {
        if (this.mAppState.getCurrentUser().getLoginStatus()) {
            String creatorUserId = ((Playlist) this.mBusinessObject).getCreatorUserId();
            String str = "";
            if (this.mAppState.getCurrentUser().getUserProfile() != null) {
                str = this.mAppState.getCurrentUser().getUserProfile().getUserId();
            }
            if (creatorUserId != null && creatorUserId.equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    private void clearQueue() {
        u.a().a("PlayerQueue", "Clear queue", "PlayerQueue - Clear queue");
        new CustomDialogView(this.mContext, this.mContext.getResources().getString(R.string.toast_clear_player_queue), new OnButtonClickListener() {
            public void onNegativeButtonClick() {
            }

            public void onPositiveButtonClick() {
                PlayerManager.a(PopupItemView.this.mContext).B();
            }
        }).show();
    }

    public void setDownloadPopupListener(DownloadPopupListener downloadPopupListener) {
        this.mDownloadPopupListener = downloadPopupListener;
    }

    private void setIconsOnTouchRadio() {
        if (this.mRadioMoodsIconMap == null) {
            TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(new int[]{R.attr.chartbusters, R.attr.coffe, R.attr.devotional, R.attr.drive, R.attr.energetic, R.attr.feelgood, R.attr.meditation, R.attr.popular, R.attr.retro, R.attr.romance, R.attr.workout, R.attr.soulful, R.attr.party});
            this.mRadioMoodsIconMap = new HashMap();
            this.mRadioMoodsIconMap.put("3", Integer.valueOf(obtainStyledAttributes.getResourceId(0, 0)));
            this.mRadioMoodsIconMap.put("161", Integer.valueOf(obtainStyledAttributes.getResourceId(1, 0)));
            this.mRadioMoodsIconMap.put("17", Integer.valueOf(obtainStyledAttributes.getResourceId(2, 0)));
            this.mRadioMoodsIconMap.put("166", Integer.valueOf(obtainStyledAttributes.getResourceId(3, 0)));
            this.mRadioMoodsIconMap.put("12", Integer.valueOf(obtainStyledAttributes.getResourceId(4, 0)));
            this.mRadioMoodsIconMap.put("16", Integer.valueOf(obtainStyledAttributes.getResourceId(5, 0)));
            this.mRadioMoodsIconMap.put("151", Integer.valueOf(obtainStyledAttributes.getResourceId(6, 0)));
            this.mRadioMoodsIconMap.put("166", Integer.valueOf(obtainStyledAttributes.getResourceId(7, 0)));
            this.mRadioMoodsIconMap.put("35", Integer.valueOf(obtainStyledAttributes.getResourceId(8, 0)));
            this.mRadioMoodsIconMap.put("22", Integer.valueOf(obtainStyledAttributes.getResourceId(9, 0)));
            this.mRadioMoodsIconMap.put("31", Integer.valueOf(obtainStyledAttributes.getResourceId(10, 0)));
            this.mRadioMoodsIconMap.put("25", Integer.valueOf(obtainStyledAttributes.getResourceId(11, 0)));
            this.mRadioMoodsIconMap.put("34", Integer.valueOf(obtainStyledAttributes.getResourceId(12, 0)));
            obtainStyledAttributes.recycle();
        }
    }
}
