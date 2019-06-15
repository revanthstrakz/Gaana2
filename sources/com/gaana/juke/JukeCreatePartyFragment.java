package com.gaana.juke;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.actionbar.JukeActionBar;
import com.actionbar.JukeActionBar.a;
import com.android.volley.VolleyError;
import com.android.volley.i.b;
import com.collapsible_header.ObservableRecyclerView;
import com.constants.Constants;
import com.facebook.share.internal.ShareConstants;
import com.fragments.BaseGaanaFragment;
import com.fragments.PartyQRFragment;
import com.fragments.SongsSelectionFragment;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.adapter.CustomListAdapter;
import com.gaana.adapter.CustomListAdapter.IAddListItemView;
import com.gaana.application.GaanaApplication;
import com.gaana.juke.JukeSessionManager.JukeSyncListener;
import com.gaana.localmedia.PlaylistSyncManager;
import com.gaana.models.AdsUJData;
import com.gaana.models.BusinessObject;
import com.gaana.models.Playlists.Playlist;
import com.gaana.models.Tracks;
import com.gaana.models.UserMessage;
import com.gaana.view.item.BaseItemView;
import com.gaana.view.item.BaseItemView.ItemAdViewHolder;
import com.gaana.view.item.BaseItemView.ItemEmptyMessageHolder;
import com.gaana.view.item.CustomDialogView;
import com.gaana.view.item.EditTextDialog.OnButtonClickListener;
import com.gaana.view.item.VotingSongsItemView;
import com.gaana.view.item.VotingSongsItemView.VotingSongsItemViewHolder;
import com.google.android.gms.appindexing.AppIndex;
import com.i.i;
import com.managers.ColombiaAdViewManager;
import com.managers.DownloadManager;
import com.managers.PlayerManager;
import com.managers.URLManager;
import com.managers.URLManager.BusinessObjectType;
import com.managers.aj;
import com.managers.ap;
import com.managers.u;
import com.models.ListingButton;
import com.models.ListingComponents;
import com.player_framework.PlayerConstants.PauseReasons;
import com.player_framework.o;
import com.services.d;
import com.services.l.ad;
import com.services.l.ao;
import com.services.l.k;
import com.services.l.s;
import com.utilities.Util;
import com.utilities.e;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class JukeCreatePartyFragment extends BaseGaanaFragment implements OnRefreshListener, OnClickListener, IAddListItemView, JukeSyncListener, ao, k {
    private static final String HAS_TRACKS_LISTING = "has_tr";
    public static final String IS_MY_PLAYLIST = "is_my_pl";
    private static final int LEFT = 4;
    private static final int PAGE_OFFSET_EMPTY_VIEW = 1;
    public static final String PAGE_TYPE = "page_type";
    private static final int VIEW_TYPE_EMPTY = 5;
    private static final int VIEW_TYPE_LAST_ITEM = 6;
    private static final int VIEW_TYPE_TRACK_ITEMS = 1;
    private String APP_URL = "";
    private String WEB_URL = "";
    private View containerView = null;
    private String extraListingAction;
    int greyColorRes;
    private long initialTime;
    private boolean isFirstTime = true;
    private boolean isFromMyPlaylist = false;
    private boolean isReOrderRequired = false;
    private boolean isRefreshing = false;
    private boolean isViewDestroyed = false;
    private JukeActionBar mActionBar;
    private BaseItemView mBaseItemView;
    private Button mBtnAddMore;
    private ArrayList<BusinessObject> mBusinessObjectList = new ArrayList();
    private e mCallback;
    private Button mCreatePlaylist;
    private int mCurrentPlayingIndex = -1;
    private CustomListAdapter mCustomListAdapter;
    private int mDeleteResId = -1;
    private boolean mEmptyView;
    private int mFirstVisibleItem = -1;
    private ItemTouchHelper mItemTouchHelper;
    private TextView mJukePartyPrevious;
    private ListingComponents mListingComponents;
    private int mPageType = -1;
    private final Paint mPaint = new Paint();
    private BusinessObject mParentBusinessObject;
    private Button mPartyButton;
    private PlayerManager mPlayerManager;
    private ProgressBar mProgressbar;
    private ObservableRecyclerView mRecyclerView;
    private int mSizeOfList = 0;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private EditText mTitleView;
    private TextView mTotalSongs;
    private String partySource = null;
    private Bundle savedInstanceState = null;
    private boolean shouldReorder = false;
    private boolean shouldSendGaScreenName;
    private String sourcePlayListId = null;
    private OnTouchListener touchListener = new OnTouchListener() {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (!(view.getId() == JukeCreatePartyFragment.this.mTitleView.getId() || view.getId() == R.id.img_edit || view.getWindowToken() == null)) {
                Util.a(JukeCreatePartyFragment.this.mContext, view);
            }
            return false;
        }
    };
    TypedValue typedValue = new TypedValue();

    public String getSectionName() {
        return "Party";
    }

    public float getSwipeThreshold(ViewHolder viewHolder) {
        return 0.5f;
    }

    public float getSwipeVelocityThreshold(float f) {
        return 0.5f;
    }

    public void onComplete(int i) {
    }

    public void showHideEmtpyView(boolean z) {
    }

    public static Bundle getBundle(BusinessObject businessObject, int i, String str, boolean z) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("BUSINESS_OBJECT", businessObject);
        bundle.putInt("page_type", i);
        if ((businessObject instanceof JukePlaylist) && i == -1) {
            ((JukePlaylist) businessObject).setPlStatus(-1);
        }
        bundle.putBoolean("is_my_pl", z);
        if (!TextUtils.isEmpty(str)) {
            bundle.putString("DEEPLINKING_SCREEN_EXTRA_PARAM", str);
        }
        return bundle;
    }

    public static BaseGaanaFragment newInstance(BusinessObject businessObject, int i, String str, boolean z) {
        JukeCreatePartyFragment jukeCreatePartyFragment = new JukeCreatePartyFragment();
        jukeCreatePartyFragment.setArguments(getBundle(businessObject, i, str, z));
        return jukeCreatePartyFragment;
    }

    private boolean init(Bundle bundle, ViewGroup viewGroup, boolean z) {
        boolean z2 = true;
        if (bundle != null) {
            this.greyColorRes = Constants.l ? R.color.black_alfa_5 : R.color.header_first_line_5;
            this.mContext.getTheme().resolveAttribute(R.attr.first_line_color, this.typedValue, true);
            this.mParentBusinessObject = (BusinessObject) bundle.getSerializable("BUSINESS_OBJECT");
            this.mPageType = bundle.getInt("page_type", -1);
            this.isFromMyPlaylist = bundle.getBoolean("is_my_pl", false);
            if (this.mParentBusinessObject != null) {
                this.mAppState.setGADParameter(this.mParentBusinessObject.getBusinessObjId());
                Iterator it;
                ListingButton listingButton;
                StringBuilder stringBuilder;
                StringBuilder stringBuilder2;
                if (this.mParentBusinessObject instanceof Playlist) {
                    this.partySource = ((Playlist) this.mParentBusinessObject).getPartySource();
                    this.sourcePlayListId = ((Playlist) this.mParentBusinessObject).getBusinessObjId();
                    this.mListingComponents = Constants.e();
                    it = this.mListingComponents.c().iterator();
                    while (it.hasNext()) {
                        listingButton = (ListingButton) it.next();
                        if (this.mParentBusinessObject.isLocalMedia()) {
                            listingButton.c().d(this.mParentBusinessObject.isLocalMedia());
                        } else {
                            stringBuilder = new StringBuilder();
                            stringBuilder.append(listingButton.c().k());
                            stringBuilder.append("playlist_id=");
                            stringBuilder.append(this.mParentBusinessObject.getBusinessObjId());
                            stringBuilder.append("&playlist_type=");
                            stringBuilder.append(((Playlist) this.mParentBusinessObject).getPlaylistType());
                            String stringBuilder3 = stringBuilder.toString();
                            if (((Playlist) this.mParentBusinessObject).getAutomated() != null && ((Playlist) this.mParentBusinessObject).getAutomated().equalsIgnoreCase("1")) {
                                stringBuilder2 = new StringBuilder();
                                stringBuilder2.append(stringBuilder3);
                                stringBuilder2.append("&automated=1");
                                stringBuilder3 = stringBuilder2.toString();
                            }
                            listingButton.c(VotingSongsItemView.class.getName());
                            listingButton.c().a(stringBuilder3);
                            if (DownloadManager.c().b(this.mParentBusinessObject).booleanValue()) {
                                listingButton.e(true);
                            }
                        }
                    }
                } else if (this.mParentBusinessObject instanceof JukePlaylist) {
                    this.mListingComponents = Constants.h();
                    this.partySource = ((JukePlaylist) this.mParentBusinessObject).getPartySource();
                    this.sourcePlayListId = ((JukePlaylist) this.mParentBusinessObject).getBusinessObjId();
                    if (this.mPageType != 0) {
                        it = this.mListingComponents.c().iterator();
                        while (it.hasNext()) {
                            listingButton = (ListingButton) it.next();
                            if (this.mParentBusinessObject.isLocalMedia()) {
                                listingButton.c().d(this.mParentBusinessObject.isLocalMedia());
                            } else {
                                String businessObjId;
                                stringBuilder = new StringBuilder();
                                stringBuilder.append(listingButton.c().k());
                                if (TextUtils.isEmpty(this.extraListingAction)) {
                                    stringBuilder2 = new StringBuilder();
                                    stringBuilder2.append("pid=");
                                    businessObjId = this.mParentBusinessObject.getBusinessObjId();
                                } else {
                                    stringBuilder2 = new StringBuilder();
                                    stringBuilder2.append("qid=");
                                    businessObjId = this.extraListingAction;
                                }
                                stringBuilder2.append(businessObjId);
                                stringBuilder.append(stringBuilder2.toString());
                                stringBuilder.append("&verbose=1");
                                listingButton.c().a(stringBuilder.toString());
                            }
                        }
                    } else if (z && this.savedInstanceState.getBoolean(HAS_TRACKS_LISTING, false)) {
                        String businessObjId2 = JukeSessionManager.getInstance().getCurrentBusinessObject() != null ? JukeSessionManager.getInstance().getCurrentBusinessObject().getBusinessObjId() : "";
                        if (this.mParentBusinessObject.getArrListBusinessObj() == null && businessObjId2.equals(this.mParentBusinessObject.getBusinessObjId())) {
                            this.mParentBusinessObject.setArrList(JukeSessionManager.getInstance().getCurrentBusinessObject().getArrListBusinessObj());
                        }
                        if (this.mParentBusinessObject.getArrListBusinessObj() == null) {
                            z2 = false;
                        }
                    }
                }
                this.mListingComponents.b(this.mParentBusinessObject.getName());
                this.mListingComponents.a(this.mParentBusinessObject);
                this.mAppState.setListingComponents(this.mListingComponents);
                initItemView((ListingButton) this.mListingComponents.c().get(0));
                initUI(viewGroup);
                return z2;
            }
        }
        ((GaanaActivity) this.mContext).popBackStack();
        z2 = false;
        return z2;
    }

    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.savedInstanceState = null;
        if (this.mParentBusinessObject != null) {
            String str = HAS_TRACKS_LISTING;
            boolean z = this.mParentBusinessObject.getArrListBusinessObj() != null && this.mParentBusinessObject.getArrListBusinessObj().size() > 0;
            bundle.putBoolean(str, z);
            this.mParentBusinessObject.setArrListBusinessObj(null);
            bundle.putSerializable("BUSINESS_OBJECT", this.mParentBusinessObject);
        }
        bundle.putInt("page_type", this.mPageType);
        bundle.putBoolean("is_my_pl", this.isFromMyPlaylist);
        bundle.putString("DEEPLINKING_SCREEN_EXTRA_PARAM", getArguments() != null ? getArguments().getString("DEEPLINKING_SCREEN_EXTRA_PARAM", "") : "");
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.isViewDestroyed = false;
        this.savedInstanceState = bundle;
        GaanaApplication.getInstance().setPlayoutSectionName(getSectionName());
        if (this.containerView == null) {
            boolean init;
            this.initialTime = Calendar.getInstance().getTimeInMillis();
            super.onCreateView(layoutInflater, viewGroup, bundle);
            this.extraListingAction = getArguments().getString("DEEPLINKING_SCREEN_EXTRA_PARAM");
            if (bundle == null) {
                init = init(getArguments(), viewGroup, false);
            } else {
                init = init(bundle, viewGroup, true);
            }
            if (!init) {
                ((GaanaActivity) this.mContext).popBackStack();
            } else if (this.mPageType != 0 || this.isFromMyPlaylist || (this.mParentBusinessObject instanceof Playlist)) {
                this.mProgressbar.setVisibility(0);
                fetchData();
            }
        } else {
            this.mAppState.setListingComponents(this.mListingComponents);
            if (this.mCustomListAdapter != null) {
                this.mBusinessObjectList = this.mParentBusinessObject.getArrListBusinessObj();
                this.mSizeOfList = this.mBusinessObjectList != null ? this.mBusinessObjectList.size() : 0;
                if (this.mSizeOfList > 0) {
                    this.mEmptyView = false;
                    this.mSizeOfList++;
                } else {
                    this.mEmptyView = true;
                }
                if (this.mPageType == 0) {
                    TextView textView = this.mTotalSongs;
                    String string = this.mContext.getResources().getString(R.string.num_songs);
                    Object[] objArr = new Object[1];
                    objArr[0] = Integer.valueOf(!this.mEmptyView ? this.mSizeOfList - 1 : this.mSizeOfList);
                    textView.setText(String.format(string, objArr));
                }
                this.mCustomListAdapter.updateAdapterCount(this.mSizeOfList);
            }
        }
        if (this.mSwipeRefreshLayout == null) {
            ((GaanaActivity) this.mContext).popBackStack();
        } else if (this.isRefreshing) {
            this.mSwipeRefreshLayout.setRefreshing(true);
        } else {
            this.mSwipeRefreshLayout.setRefreshing(false);
        }
        if (this.mParentBusinessObject != null) {
            this.TITLE = this.mParentBusinessObject.getEnglishName();
            if (this.mParentBusinessObject instanceof Playlist) {
                Playlist playlist = (Playlist) this.mParentBusinessObject;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("https://gaana.com/playlist/");
                stringBuilder.append(playlist.getSeokey());
                this.WEB_URL = stringBuilder.toString();
                stringBuilder = new StringBuilder();
                stringBuilder.append("android-app://com.gaana/gaanagoogle/playlist/");
                stringBuilder.append(playlist.getSeokey());
                this.APP_URL = stringBuilder.toString();
            }
        }
        if (this.mPageType == 0) {
            setGAScreenName("Create Party", "Create Party");
            this.shouldSendGaScreenName = false;
        } else if (this.mPageType == -1) {
            this.shouldSendGaScreenName = true;
        } else {
            String str = this.mPageType != 2 ? "Admin_PartyDetail" : "Joinee_PartyDetail";
            setGAScreenName(str, str);
            this.shouldSendGaScreenName = false;
        }
        loadBottomBanner();
        ((GaanaActivity) this.mContext).setRefreshData(true);
        this.mPlayerManager = PlayerManager.a(this.mContext);
        return this.containerView;
    }

    public void onStart() {
        super.onStart();
        if (this.mPageType != 0) {
            JukeSessionManager.getInstance().setSyncListener((JukePlaylist) this.mParentBusinessObject, this);
        }
        startAppIndexing();
    }

    public void onResume() {
        super.onResume();
        ((GaanaActivity) this.mContext).setFragment(this);
        if (this.loginStatus != this.mAppState.getCurrentUser().getLoginStatus()) {
            fetchData();
            this.loginStatus = this.mAppState.getCurrentUser().getLoginStatus();
        }
    }

    private void configurePage(int i) {
        a a = new a().a(this.mContext);
        View findViewById = this.containerView.findViewById(R.id.scrollContainer);
        this.containerView.findViewById(R.id.txt_subtitle).setVisibility(i != 0 ? 8 : 0);
        this.containerView.findViewById(R.id.img_edit).setVisibility(i != 0 ? 8 : 0);
        this.containerView.findViewById(R.id.dummy_shadow).setVisibility(i != 0 ? 8 : 0);
        if (i == 0) {
            this.mTitleView.setText(this.mParentBusinessObject.getName());
            ((TextView) this.containerView.findViewById(R.id.txt_subtitle)).setText(R.string.my_music_by_me);
            this.containerView.findViewById(R.id.img_edit).setOnClickListener(this);
            this.mCreatePlaylist.setVisibility(0);
            this.mTotalSongs.setVisibility(0);
            this.mTotalSongs.setText(String.format(this.mContext.getResources().getString(R.string.num_songs), new Object[]{Integer.valueOf(this.mBusinessObjectList.size())}));
            this.mTitleView.setVisibility(0);
            this.mPartyButton.setVisibility(4);
            a.b(false);
            a.a(false);
            setTouchListener((ViewGroup) this.containerView);
            if (Constants.l) {
                findViewById.setBackgroundColor(this.mContext.getResources().getColor(R.color.view_foreground_light));
            } else {
                findViewById.setBackgroundColor(this.mContext.getResources().getColor(R.color.view_foreground_dark));
            }
            this.mCreatePlaylist.setOnClickListener(this);
        } else if (i == 1) {
            a.a(this.mParentBusinessObject.getName());
            a.b(true);
            a.a(true);
            this.mCreatePlaylist.setVisibility(4);
            this.mTotalSongs.setVisibility(4);
            this.mTitleView.setVisibility(8);
            this.mPartyButton.setVisibility(0);
            this.mPartyButton.setOnClickListener(this);
        } else {
            findViewById.setVisibility(4);
            a.a(this.mParentBusinessObject.getName());
            a.b(true);
            a.a(false);
        }
        Toolbar toolbar = (Toolbar) this.containerView.findViewById(R.id.main_toolbar);
        toolbar.removeAllViews();
        toolbar.setContentInsetsAbsolute(0, 0);
        this.mActionBar = a.a();
        this.mActionBar.setParams(this, this.mParentBusinessObject);
        toolbar.addView(this.mActionBar);
    }

    private void setActionButton(int i) {
        View findViewById = this.containerView.findViewById(R.id.scrollContainer);
        if (i == 1 || i == 3) {
            findViewById.setVisibility(0);
            this.mCreatePlaylist.setVisibility(4);
            this.mTotalSongs.setVisibility(4);
            this.mTitleView.setVisibility(8);
            this.mPartyButton.setVisibility(0);
            this.mPartyButton.setOnClickListener(this);
            if (this.mParentBusinessObject instanceof JukePlaylist) {
                this.mPartyButton.setText(((JukePlaylist) this.mParentBusinessObject).getPlStatus() == 2 ? R.string.end_party : R.string.start_party);
            }
        } else if (this.mPageType == 0) {
            findViewById.setVisibility(0);
            this.mCreatePlaylist.setVisibility(0);
        } else {
            findViewById.setVisibility(4);
        }
    }

    private void initUI(ViewGroup viewGroup) {
        this.containerView = setContentView(R.layout.fragment_juke_create_party, viewGroup);
        this.mSwipeRefreshLayout = (SwipeRefreshLayout) this.containerView.findViewById(R.id.swipe_refresh_layout);
        this.mSwipeRefreshLayout.setOnRefreshListener(this);
        this.mRecyclerView = (ObservableRecyclerView) this.containerView.findViewById(R.id.scroll);
        this.mRecyclerView.addOnScrollListener(new OnScrollListener() {
            public void onScrolled(RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                if (JukeCreatePartyFragment.this.mCurrentPlayingIndex >= 0) {
                    JukeCreatePartyFragment.this.setPanelText(JukeCreatePartyFragment.this.mContext, JukeCreatePartyFragment.this.mCurrentPlayingIndex, JukeCreatePartyFragment.this.mBusinessObjectList.size());
                }
            }
        });
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this.mContext));
        this.mRecyclerView.setHasFixedSize(false);
        this.mCallback = new e(this);
        this.mCallback.a(false);
        this.mCallback.b(true);
        this.mItemTouchHelper = new ItemTouchHelper(this.mCallback);
        this.mItemTouchHelper.attachToRecyclerView(this.mRecyclerView);
        this.mCustomListAdapter = new CustomListAdapter(this.mContext, null);
        if (this.mParentBusinessObject.getArrListBusinessObj() == null || this.mParentBusinessObject.getArrListBusinessObj().size() <= 0) {
            this.mSizeOfList = 0;
        } else {
            this.mBusinessObjectList = this.mParentBusinessObject.getArrListBusinessObj();
            this.mSizeOfList = this.mBusinessObjectList.size() + 1;
        }
        this.mCustomListAdapter.setParamaters(this.mSizeOfList, this);
        this.mRecyclerView.setAdapter(this.mCustomListAdapter);
        this.mProgressbar = (ProgressBar) this.containerView.findViewById(R.id.progressbar);
        this.mTitleView = (EditText) this.containerView.findViewById(R.id.txt_title);
        this.mTotalSongs = (TextView) this.containerView.findViewById(R.id.txt_playlist_songs);
        this.mPartyButton = (Button) this.containerView.findViewById(R.id.btn_start_juke);
        this.mCreatePlaylist = (Button) this.containerView.findViewById(R.id.btn_create_playlist);
        this.mBtnAddMore = (Button) this.containerView.findViewById(R.id.btn_add_more);
        this.mBtnAddMore.setOnClickListener(this);
        this.mJukePartyPrevious = (TextView) this.containerView.findViewById(R.id.fragment_juke_party_previous);
        TypedArray obtainStyledAttributes = this.mContext.obtainStyledAttributes(R.styleable.VectorDrawables);
        this.mDeleteResId = obtainStyledAttributes.getResourceId(42, -1);
        obtainStyledAttributes.recycle();
        configurePage(this.mPageType);
    }

    private void setTouchListener(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View childAt = viewGroup.getChildAt(i);
            childAt.setOnTouchListener(this.touchListener);
            if (childAt instanceof ViewGroup) {
                setTouchListener((ViewGroup) childAt);
            }
        }
    }

    private void refresh() {
        if (this.mParentBusinessObject != null && this.mCustomListAdapter != null) {
            this.mCustomListAdapter.notifyDataSetChanged();
        }
    }

    public void refreshListView() {
        refresh();
    }

    public void refreshListView(BusinessObjectType businessObjectType) {
        refresh();
    }

    public void refreshListView(BusinessObject businessObject, boolean z) {
        refresh();
    }

    /* Access modifiers changed, original: protected */
    public int getActionBarSize() {
        int[] iArr = new int[]{R.attr.actionBarSize};
        TypedArray obtainStyledAttributes = getActivity().obtainStyledAttributes(new TypedValue().data, iArr);
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(0, -1);
        obtainStyledAttributes.recycle();
        return dimensionPixelSize;
    }

    private void fetchData() {
        if (this.mPageType != 0) {
            this.initialTime = Calendar.getInstance().getTimeInMillis();
            URLManager c = ((ListingButton) this.mListingComponents.c().get(0)).c();
            if (this.isRefreshing) {
                JukeSessionManager.getInstance().editPlaylist((JukePlaylist) this.mParentBusinessObject, new s() {
                    public void onErrorResponse(BusinessObject businessObject) {
                    }

                    public void onRetreivalComplete(BusinessObject businessObject) {
                        JukeCreatePartyFragment.this.onResponse(businessObject);
                    }
                }, this.shouldReorder, true);
                return;
            }
            c.c(Boolean.valueOf(this.isRefreshing));
            i.a().a(c, toString(), (b) this, (com.android.volley.i.a) this);
        } else if (this.isFromMyPlaylist && !this.isRefreshing) {
            PlaylistSyncManager.getInstance().getJukePlaylistDetailsAsync((JukePlaylist) this.mParentBusinessObject, new s() {
                public void onRetreivalComplete(final BusinessObject businessObject) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public void run() {
                            if (JukeCreatePartyFragment.this.isAdded()) {
                                JukeCreatePartyFragment.this.onResponse(businessObject);
                            }
                        }
                    });
                }

                public void onErrorResponse(BusinessObject businessObject) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public void run() {
                            if (JukeCreatePartyFragment.this.isAdded()) {
                                JukeCreatePartyFragment.this.mProgressbar.setVisibility(8);
                                JukeCreatePartyFragment.this.mSwipeRefreshLayout.setRefreshing(false);
                            }
                        }
                    });
                }
            });
        } else if (!(this.mParentBusinessObject instanceof Playlist) || this.isRefreshing) {
            this.isRefreshing = false;
            this.mSwipeRefreshLayout.setRefreshing(false);
        } else {
            this.initialTime = Calendar.getInstance().getTimeInMillis();
            i.a().a(((ListingButton) this.mListingComponents.c().get(0)).c(), toString(), (b) this, (com.android.volley.i.a) this);
        }
    }

    /* Access modifiers changed, original: protected */
    public void initItemView(ListingButton listingButton) {
        try {
            this.mBaseItemView = (BaseItemView) Class.forName(listingButton.f()).getConstructor(new Class[]{Context.class, BaseGaanaFragment.class}).newInstance(new Object[]{this.mContext, this});
            if (this.mBaseItemView instanceof VotingSongsItemView) {
                ((VotingSongsItemView) this.mBaseItemView).setSessionType(this.mPageType);
                ((VotingSongsItemView) this.mBaseItemView).setParentBusinessObject(this.mParentBusinessObject);
                ((VotingSongsItemView) this.mBaseItemView).setDragListener(this);
            }
        } catch (Exception unused) {
        }
    }

    public void setGAScreenName(String str, String str2) {
        sendGAScreenName(str, str2);
    }

    public BusinessObject getParentBusinessObject() {
        return this.mParentBusinessObject;
    }

    public void refreshData() {
        if (this.mListingComponents != null && this.mListingComponents.c() != null) {
            fetchData();
        }
    }

    public void onStop() {
        stopAppIndex();
        if (this.mPageType != 0) {
            JukeSessionManager.getInstance().removeSyncListener((JukePlaylist) this.mParentBusinessObject);
        }
        super.onStop();
    }

    public View addListItemView(int i, ViewHolder viewHolder, ViewGroup viewGroup) {
        if (viewHolder.getItemViewType() == 5 || viewHolder.getItemViewType() == 6) {
            return viewHolder.itemView;
        }
        View poplatedView = this.mBaseItemView.getPoplatedView(viewHolder, (BusinessObject) this.mBusinessObjectList.get(i), viewGroup);
        setPlaylistStatus((VotingSongsItemViewHolder) viewHolder, i);
        return poplatedView;
    }

    private void setPlaylistStatus(VotingSongsItemViewHolder votingSongsItemViewHolder, int i) {
        TextView textView = votingSongsItemViewHolder.mNowPlaying;
        TextView textView2 = votingSongsItemViewHolder.mUpNext;
        textView.setVisibility(8);
        textView2.setVisibility(8);
        this.mJukePartyPrevious.setVisibility(8);
        JukePlaylist jukePlaylist = (JukePlaylist) this.mParentBusinessObject;
        if (jukePlaylist.getPlStatus() == 2) {
            int i2;
            if (jukePlaylist.getAdmin()) {
                if (this.mPlayerManager.i() != null) {
                    String h = this.mPlayerManager.i().h();
                    if (!TextUtils.isEmpty(h) && ((BusinessObject) this.mBusinessObjectList.get(i)).getBusinessObjId().equals(h)) {
                        this.mCurrentPlayingIndex = i;
                        i2 = i;
                    }
                }
                i2 = -1;
            } else {
                i2 = jukePlaylist.getCurrentPlayingIndex();
                this.mCurrentPlayingIndex = i2;
            }
            if (this.mCurrentPlayingIndex > 0) {
                if (this.mFirstVisibleItem < this.mCurrentPlayingIndex) {
                    this.mJukePartyPrevious.setBackgroundResource(this.greyColorRes);
                    this.mJukePartyPrevious.setText(this.mContext.getResources().getString(R.string.previous_camel));
                    this.mJukePartyPrevious.setTextColor(this.typedValue.data);
                }
                this.mJukePartyPrevious.setVisibility(0);
            }
            if (i2 == 0 && i == 0) {
                textView.setVisibility(0);
                if (this.mBusinessObjectList.size() > 1) {
                    textView2.setVisibility(0);
                }
            } else if (i2 == this.mBusinessObjectList.size() - 1 && i == this.mBusinessObjectList.size() - 1) {
                textView.setVisibility(0);
            } else if (i2 == i) {
                textView.setVisibility(0);
                textView2.setVisibility(0);
            }
            if (this.mCurrentPlayingIndex == i) {
                votingSongsItemViewHolder.mPlayerIcon.setVisibility(0);
                votingSongsItemViewHolder.mPlayerIcon.setImageDrawable(ContextCompat.getDrawable(this.mContext, R.drawable.equalizer_red));
                votingSongsItemViewHolder.mImgVote.setVisibility(8);
                votingSongsItemViewHolder.mTxtVoteCount.setVisibility(8);
            } else if (this.mCurrentPlayingIndex == -1 && jukePlaylist.getCurrentPlayingIndex() == -1) {
                votingSongsItemViewHolder.mPlayerIcon.setVisibility(8);
                votingSongsItemViewHolder.mImgVote.setVisibility(0);
                votingSongsItemViewHolder.mTxtVoteCount.setVisibility(0);
            } else {
                int i3 = this.mCurrentPlayingIndex;
                int i4 = R.drawable.vector_ic_play_again;
                Context context;
                if (i3 == -1 && jukePlaylist.getCurrentPlayingIndex() > i) {
                    votingSongsItemViewHolder.mPlayerIcon.setVisibility(0);
                    context = this.mContext;
                    if (Constants.l) {
                        i4 = R.drawable.vector_ic_play_again_white;
                    }
                    votingSongsItemViewHolder.mPlayerIcon.setImageDrawable(ContextCompat.getDrawable(context, i4));
                    votingSongsItemViewHolder.mImgVote.setVisibility(8);
                    votingSongsItemViewHolder.mTxtVoteCount.setVisibility(8);
                } else if (this.mCurrentPlayingIndex < i) {
                    votingSongsItemViewHolder.mPlayerIcon.setVisibility(8);
                    votingSongsItemViewHolder.mImgVote.setVisibility(0);
                    votingSongsItemViewHolder.mTxtVoteCount.setVisibility(0);
                } else {
                    votingSongsItemViewHolder.mPlayerIcon.setVisibility(0);
                    context = this.mContext;
                    if (Constants.l) {
                        i4 = R.drawable.vector_ic_play_again_white;
                    }
                    votingSongsItemViewHolder.mPlayerIcon.setImageDrawable(ContextCompat.getDrawable(context, i4));
                    votingSongsItemViewHolder.mImgVote.setVisibility(8);
                    votingSongsItemViewHolder.mTxtVoteCount.setVisibility(8);
                }
            }
        }
    }

    private void setPanelText(Context context, int i, int i2) {
        if (this.mParentBusinessObject == null || ((JukePlaylist) this.mParentBusinessObject).getPlStatus() == 2) {
            this.mFirstVisibleItem = ((LinearLayoutManager) this.mRecyclerView.getLayoutManager()).findFirstVisibleItemPosition();
            if (i <= 0) {
                this.mJukePartyPrevious.setVisibility(8);
            } else if (this.mFirstVisibleItem == i && i2 > 0) {
                this.mJukePartyPrevious.setBackgroundResource(R.drawable.gradient_juke_session_on);
                this.mJukePartyPrevious.setText(context.getResources().getString(R.string.playing_in_party));
                this.mJukePartyPrevious.setTextColor(context.getResources().getColor(R.color.white));
                this.mJukePartyPrevious.setVisibility(0);
            } else if (this.mFirstVisibleItem > i && i2 > 1) {
                this.mJukePartyPrevious.setBackgroundResource(this.greyColorRes);
                this.mJukePartyPrevious.setText(context.getResources().getString(R.string.up_next_camel));
                this.mJukePartyPrevious.setTextColor(this.typedValue.data);
                this.mJukePartyPrevious.setVisibility(0);
            } else if (i > 0) {
                this.mJukePartyPrevious.setBackgroundResource(this.greyColorRes);
                this.mJukePartyPrevious.setText(context.getResources().getString(R.string.previous_camel));
                this.mJukePartyPrevious.setTextColor(this.typedValue.data);
                this.mJukePartyPrevious.setVisibility(0);
            } else if (i2 <= 1) {
                this.mJukePartyPrevious.setVisibility(8);
            } else if (i == 0 && i2 > 1) {
                this.mJukePartyPrevious.setBackgroundResource(R.drawable.gradient_juke_session_on);
                this.mJukePartyPrevious.setText(context.getResources().getString(R.string.playing_in_party));
                this.mJukePartyPrevious.setTextColor(context.getResources().getColor(R.color.white));
                this.mJukePartyPrevious.setVisibility(0);
            }
            return;
        }
        this.mJukePartyPrevious.setVisibility(8);
    }

    public void notifyItemChanged(int i) {
        if (this.mCustomListAdapter != null) {
            this.mCustomListAdapter.notifyItemChanged(i + 1);
        }
    }

    public ViewHolder createViewHolder(ViewGroup viewGroup, int i) {
        ViewHolder itemEmptyMessageHolder;
        if (i == 5) {
            UserMessage userMessage = new UserMessage();
            userMessage.setEmptyMsg(this.mContext.getResources().getString(R.string.NO_DATA));
            View emptyMsgView = this.mBaseItemView.getEmptyMsgView(userMessage, viewGroup);
            if (Constants.l) {
                emptyMsgView.findViewById(R.id.ll_view_user_msg).setBackgroundColor(-1);
            } else {
                emptyMsgView.findViewById(R.id.ll_view_user_msg).setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
            }
            itemEmptyMessageHolder = new ItemEmptyMessageHolder(emptyMsgView);
        } else if (i == 6) {
            return new ItemAdViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.view_item_empty, viewGroup, false));
        } else {
            itemEmptyMessageHolder = new VotingSongsItemViewHolder(this.mBaseItemView.createViewHolder(viewGroup, i));
        }
        return itemEmptyMessageHolder;
    }

    public int getItemViewType(int i) {
        if (i == 0 && this.mEmptyView) {
            return 5;
        }
        return i == this.mSizeOfList - 1 ? 6 : 1;
    }

    public void onRefresh() {
        if (!this.isRefreshing) {
            this.mSwipeRefreshLayout.setRefreshing(true);
            this.isRefreshing = true;
            fetchData();
        }
    }

    public void onClick(final View view) {
        int id = view.getId();
        boolean z = false;
        if (id == R.id.btn_add_more) {
            u.a().a("PartyHub", "AddSongs", ((JukePlaylist) this.mParentBusinessObject).getAdmin() ? "Admin" : "Joinee");
            if (this.mParentBusinessObject instanceof JukePlaylist) {
                JukeSessionManager.getInstance().setCurrentBusinessObject(this.mParentBusinessObject);
                JukeSessionManager.getInstance().setCurrentSessionType(this.mPageType);
            }
            BaseGaanaFragment songsSelectionFragment = new SongsSelectionFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("tab_position", 0);
            bundle.putInt("source_type", 2);
            songsSelectionFragment.setArguments(bundle);
            ((GaanaActivity) this.mContext).displayFragment(songsSelectionFragment);
        } else if (id == R.id.btn_create_playlist) {
            String b = d.a().b("pref_juke_nick", "", false);
            if (TextUtils.isEmpty(b) && TextUtils.isEmpty(JukeSessionManager.getInstance().getUserNick())) {
                z = true;
            } else if (TextUtils.isEmpty(b)) {
                d.a().a("pref_juke_nick", JukeSessionManager.getInstance().getUserNick(), false);
                b = JukeSessionManager.getInstance().getUserNick();
            } else if (TextUtils.isEmpty(JukeSessionManager.getInstance().getUserNick())) {
                JukeSessionManager.getInstance().setUserNick(b);
            }
            if (z) {
                b = "";
            }
            u.a().b("PartyHub", "CreateParty");
            ((BaseActivity) this.mContext).checkSetLoginStatus(new ad() {
                public void onLoginSuccess() {
                    if (TextUtils.isEmpty(b)) {
                        JukeSessionManager.getNickDialog(JukeCreatePartyFragment.this.mContext, "", new OnButtonClickListener() {
                            public void onPositiveButtonClick(String str) {
                                d.a().a("pref_juke_nick", str, false);
                                Util.a(JukeCreatePartyFragment.this.mContext, view);
                                JukeCreatePartyFragment.this.createJukePlaylist(str);
                            }

                            public void onNegativeButtonClick() {
                                aj.a().a(JukeCreatePartyFragment.this.mContext, JukeCreatePartyFragment.this.mContext.getResources().getString(R.string.error_nick_provide));
                            }
                        });
                    } else {
                        JukeCreatePartyFragment.this.createJukePlaylist(b);
                    }
                }
            }, GaanaApplication.getContext().getResources().getString(R.string.LOGIN_LAUNCHED_FOR_ADD_TO_PLAYLIST));
        } else if (id != R.id.btn_start_juke) {
            if (id == R.id.img_edit) {
                u.a().b("PartyHub", "EditName");
                this.mTitleView.setFocusableInTouchMode(true);
                this.mTitleView.setSelection(this.mTitleView.getText().length());
                Util.b(this.mContext, this.mTitleView);
            }
        } else if (Util.j(this.mContext)) {
            if (((JukePlaylist) this.mParentBusinessObject).getPlStatus() == 1) {
                z = true;
            }
            u.a().b("PartyHub", z ? "StartParty" : "EndParty");
            handlePartyStatus(z);
        } else {
            ap.a().f(this.mContext);
        }
    }

    private void handlePartyStatus(final boolean z) {
        if (!Constants.cY || JukeSessionManager.getInstance().isCurrentJukeSession(this.mParentBusinessObject)) {
            handleJukeOnOff(z);
        } else {
            JukeSessionManager.getErrorDialog(this.mContext, 1, new CustomDialogView.OnButtonClickListener() {
                public void onNegativeButtonClick() {
                }

                public void onPositiveButtonClick() {
                    JukeSessionManager.getInstance().stopJukeSession(new s() {
                        public void onRetreivalComplete(BusinessObject businessObject) {
                            JukeCreatePartyFragment.this.handleJukeOnOff(z);
                        }

                        public void onErrorResponse(BusinessObject businessObject) {
                            aj.a().a(JukeCreatePartyFragment.this.mContext, JukeCreatePartyFragment.this.mContext.getResources().getString(R.string.some_error_occured));
                        }
                    });
                }
            });
        }
    }

    private void startJukeSession() {
        JukeSessionManager.getInstance().startJukeSession(this.mParentBusinessObject.getBusinessObjId(), 2, new s() {
            public void onRetreivalComplete(BusinessObject businessObject) {
                JukePlaylist jukePlaylist = (JukePlaylist) businessObject;
                JukeCreatePartyFragment.this.mCurrentPlayingIndex = -1;
                if (jukePlaylist.getPlStatus() == 2) {
                    if (businessObject == null || businessObject.getArrListBusinessObj() == null || businessObject.getArrListBusinessObj().size() <= 0) {
                        ((JukePlaylist) JukeCreatePartyFragment.this.mParentBusinessObject).setPlStatus(2);
                    } else {
                        JukeCreatePartyFragment.this.onResponse(businessObject);
                    }
                    if (JukeCreatePartyFragment.this.isAdded()) {
                        JukeCreatePartyFragment.this.mPartyButton.setText(R.string.end_party);
                    }
                }
            }

            public void onErrorResponse(BusinessObject businessObject) {
                JukeCreatePartyFragment.this.mCurrentPlayingIndex = -1;
                if (JukeCreatePartyFragment.this.isAdded()) {
                    JukeCreatePartyFragment.this.mPartyButton.setText(R.string.start_party);
                }
            }
        });
    }

    private void handleJukeOnOff(boolean z) {
        if (z) {
            JukeSessionManager.getInstance().forceSync((JukePlaylist) this.mParentBusinessObject, false, new s() {
                public void onRetreivalComplete(BusinessObject businessObject) {
                    if (businessObject == null || businessObject.getArrListBusinessObj() == null || businessObject.getArrListBusinessObj().size() == 0) {
                        ((BaseActivity) JukeCreatePartyFragment.this.mContext).hideProgressDialog();
                        aj.a().a(JukeCreatePartyFragment.this.mContext, JukeCreatePartyFragment.this.mContext.getResources().getString(R.string.error_no_songs_party));
                        return;
                    }
                    JukeCreatePartyFragment.this.startJukeSession();
                }

                public void onErrorResponse(BusinessObject businessObject) {
                    JukeCreatePartyFragment.this.startJukeSession();
                }
            });
            return;
        }
        o.b(this.mContext, PauseReasons.MEDIA_BUTTON_TAP);
        JukeSessionManager.getInstance().stopJukeSession(new s() {
            public void onRetreivalComplete(BusinessObject businessObject) {
                JukeCreatePartyFragment.this.mCurrentPlayingIndex = -1;
                if (((JukePlaylist) businessObject).getPlStatus() == 1) {
                    if (businessObject.getArrListBusinessObj() == null || businessObject.getArrListBusinessObj().size() <= 0) {
                        ((JukePlaylist) JukeCreatePartyFragment.this.mParentBusinessObject).setPlStatus(1);
                    } else {
                        JukeCreatePartyFragment.this.onResponse(businessObject);
                    }
                    o.d(GaanaApplication.getContext());
                    if (JukeCreatePartyFragment.this.isAdded()) {
                        JukeCreatePartyFragment.this.mPartyButton.setText(R.string.start_party);
                        return;
                    }
                    return;
                }
                aj.a().a(JukeCreatePartyFragment.this.mContext, JukeCreatePartyFragment.this.mContext.getResources().getString(R.string.some_error_occured));
                o.b(JukeCreatePartyFragment.this.mContext, PauseReasons.MEDIA_BUTTON_TAP);
                if (JukeCreatePartyFragment.this.isAdded()) {
                    JukeCreatePartyFragment.this.mPartyButton.setText(R.string.end_party);
                }
            }

            public void onErrorResponse(BusinessObject businessObject) {
                JukeCreatePartyFragment.this.mCurrentPlayingIndex = -1;
                aj.a().a(JukeCreatePartyFragment.this.mContext, JukeCreatePartyFragment.this.mContext.getResources().getString(R.string.some_error_occured));
                o.b(JukeCreatePartyFragment.this.mContext, PauseReasons.MEDIA_BUTTON_TAP);
                if (JukeCreatePartyFragment.this.isAdded()) {
                    JukeCreatePartyFragment.this.mPartyButton.setText(R.string.end_party);
                }
            }
        });
    }

    private void createJukePlaylist(String str) {
        ((BaseActivity) this.mContext).showProgressDialog(Boolean.valueOf(false), getString(R.string.adding_to_playlist_text));
        this.mParentBusinessObject.setName(this.mTitleView.getText().toString());
        JukeSessionManager.createJukePlaylist(this.mBusinessObjectList, this.partySource, this.sourcePlayListId, this.mParentBusinessObject.getName(), str, new s() {
            public void onRetreivalComplete(BusinessObject businessObject) {
                ((BaseActivity) JukeCreatePartyFragment.this.mContext).hideProgressDialog();
                if (JukeCreatePartyFragment.this.isAdded()) {
                    if (businessObject instanceof JukePlaylist) {
                        JukePlaylist jukePlaylist = (JukePlaylist) businessObject;
                        if (TextUtils.isEmpty(jukePlaylist.getError())) {
                            jukePlaylist.setArrListBusinessObj(null);
                            jukePlaylist.setArrList(null);
                            String qrUrl = jukePlaylist.getQrUrl();
                            ((GaanaActivity) JukeCreatePartyFragment.this.mContext).popBackStackImmediate();
                            Bundle bundle = new Bundle();
                            bundle.putString("QR_URL", qrUrl);
                            bundle.putString(ShareConstants.ACTION, "Create");
                            bundle.putString("txt_name", businessObject.getName());
                            bundle.putString("DL_URL", jukePlaylist.getDlUrl());
                            BaseGaanaFragment partyQRFragment = new PartyQRFragment();
                            partyQRFragment.setArguments(bundle);
                            ((GaanaActivity) JukeCreatePartyFragment.this.mContext).displayFragment(partyQRFragment);
                        } else {
                            aj.a().a(JukeCreatePartyFragment.this.mContext, jukePlaylist.getError());
                            return;
                        }
                    }
                    aj.a().a(JukeCreatePartyFragment.this.mContext, JukeCreatePartyFragment.this.mContext.getResources().getString(R.string.some_error_occured));
                }
                d.a().a("PREFERENCE_CREATED_PLAYLIST", true, false);
            }

            public void onErrorResponse(BusinessObject businessObject) {
                ((BaseActivity) JukeCreatePartyFragment.this.mContext).hideProgressDialog();
                aj.a().a(JukeCreatePartyFragment.this.mContext, JukeCreatePartyFragment.this.mContext.getResources().getString(R.string.some_error_occured));
            }
        });
    }

    public void onDestroyView() {
        if (this.containerView.getParent() != null) {
            ((ViewGroup) this.containerView.getParent()).removeView(this.containerView);
        }
        super.onDestroyView();
        this.isViewDestroyed = true;
    }

    public void onErrorResponse(VolleyError volleyError) {
        this.isRefreshing = false;
        super.onErrorResponse(volleyError);
        showNetworkErrorView(null);
        this.mProgressbar.setVisibility(8);
    }

    public void onResponse(Object obj) {
        if (!this.isViewDestroyed) {
            this.isRefreshing = false;
            long timeInMillis = Calendar.getInstance().getTimeInMillis();
            if (this.initialTime != 0) {
                long j = timeInMillis - this.initialTime;
                if (this.mParentBusinessObject.getBusinessObjType() == BusinessObjectType.Playlists) {
                    Constants.a("Load", j, "Party Playlist detail", null);
                }
            }
            final BusinessObject businessObject = (BusinessObject) obj;
            if ((obj instanceof Tracks) || (obj instanceof Playlist)) {
                com.i.d.a(new Runnable() {
                    public void run() {
                        BusinessObject jukePlaylist;
                        if (businessObject instanceof Tracks) {
                            jukePlaylist = new JukePlaylist();
                            jukePlaylist.setAtw(businessObject.getAtw());
                            jukePlaylist.setArrList(JukeSessionManager.getJukeTrackList(businessObject.getArrListBusinessObj()));
                        } else {
                            jukePlaylist = JukeSessionManager.getJukePlaylist((Playlist) businessObject);
                        }
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            public void run() {
                                if (JukeCreatePartyFragment.this.isAdded()) {
                                    JukeCreatePartyFragment.this.onResponse(jukePlaylist);
                                    JukeCreatePartyFragment.this.mSwipeRefreshLayout.setRefreshing(false);
                                    JukeCreatePartyFragment.this.mProgressbar.setVisibility(8);
                                }
                            }
                        });
                    }
                });
            } else {
                this.mSwipeRefreshLayout.setRefreshing(false);
                this.mProgressbar.setVisibility(8);
                bindJukePlaylist(businessObject);
            }
            GaanaApplication.getInstance().setCurrentBusObjInListView(null);
        }
    }

    private void loadBottomBanner() {
        if (ap.a().b(this.mContext)) {
            LinearLayout linearLayout = (LinearLayout) this.containerView.findViewById(R.id.bottom_ad_banner);
            ColombiaAdViewManager.a().e();
            com.managers.e.a();
            if (com.managers.e.X == 0) {
                ColombiaAdViewManager.a().a(this.mContext, linearLayout, com.managers.e.A, "CREATEPARTY_BOTTOM_BANNER", new AdsUJData[0]);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:71:? A:{SYNTHETIC, RETURN} */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x01ab  */
    /* JADX WARNING: Missing block: B:53:0x0161, code skipped:
            if (r11 < r10.mSizeOfList) goto L_0x0165;
     */
    private void bindJukePlaylist(com.gaana.models.BusinessObject r11) {
        /*
        r10 = this;
        r0 = r11 instanceof com.gaana.juke.JukePlaylist;
        r1 = -1;
        r2 = 0;
        r3 = 1;
        if (r0 == 0) goto L_0x010a;
    L_0x0007:
        r0 = r11;
        r0 = (com.gaana.juke.JukePlaylist) r0;
        r4 = r0.getPlStatus();
        r6 = 0;
        r8 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
        if (r8 == 0) goto L_0x0107;
    L_0x0014:
        r10.mParentBusinessObject = r11;
        r4 = r10.mActionBar;
        r4.setParams(r10, r11);
        r4 = r10.mParentBusinessObject;
        r4 = (com.gaana.juke.JukePlaylist) r4;
        r4 = r4.getSyncIntervalInMillis();
        com.gaana.juke.JukeSessionManager.JUKE_SYNC_INTERVAL = r4;
        r4 = r0.getNickName();
        r4 = android.text.TextUtils.isEmpty(r4);
        if (r4 != 0) goto L_0x003a;
    L_0x002f:
        r4 = com.gaana.juke.JukeSessionManager.getInstance();
        r5 = r0.getNickName();
        r4.setUserNick(r5);
    L_0x003a:
        r4 = r10.mParentBusinessObject;
        r4 = (com.gaana.juke.JukePlaylist) r4;
        r4 = r4.getCurrentPlayingIndex();
        r5 = r10.mPageType;
        r6 = 2;
        if (r5 != r1) goto L_0x0065;
    L_0x0047:
        r5 = r0.getAdmin();
        if (r5 == 0) goto L_0x005e;
    L_0x004d:
        r0 = r0.getVoteEnable();
        if (r0 == 0) goto L_0x0055;
    L_0x0053:
        r0 = r3;
        goto L_0x0056;
    L_0x0055:
        r0 = 3;
    L_0x0056:
        r10.mPageType = r0;
        r0 = r10.mCallback;
        r0.b(r3);
        goto L_0x0065;
    L_0x005e:
        r0 = r10.mCallback;
        r0.b(r2);
        r10.mPageType = r6;
    L_0x0065:
        r0 = r10.mBaseItemView;
        r0 = r0 instanceof com.gaana.view.item.VotingSongsItemView;
        if (r0 == 0) goto L_0x007b;
    L_0x006b:
        r0 = r10.mBaseItemView;
        r0 = (com.gaana.view.item.VotingSongsItemView) r0;
        r0.setParentBusinessObject(r11);
        r0 = r10.mBaseItemView;
        r0 = (com.gaana.view.item.VotingSongsItemView) r0;
        r5 = r10.mPageType;
        r0.setSessionType(r5);
    L_0x007b:
        r0 = r10.shouldSendGaScreenName;
        r7 = 2;
        if (r0 == 0) goto L_0x00b1;
    L_0x0081:
        r0 = r10.mPageType;
        if (r0 == r6) goto L_0x0088;
    L_0x0085:
        r0 = "Admin_PartyDetail";
        goto L_0x008a;
    L_0x0088:
        r0 = "Joinee_PartyDetail";
    L_0x008a:
        r10.setGAScreenName(r0, r0);
        r10.shouldSendGaScreenName = r2;
        r0 = r10.mParentBusinessObject;
        r0 = (com.gaana.juke.JukePlaylist) r0;
        r5 = r0.getPlStatus();
        r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1));
        if (r0 != 0) goto L_0x00b1;
    L_0x009b:
        r0 = com.managers.aj.a();
        r5 = r10.mContext;
        r6 = r10.mContext;
        r6 = r6.getResources();
        r9 = 2131821895; // 0x7f110547 float:1.9276546E38 double:1.0532599614E-314;
        r6 = r6.getString(r9);
        r0.a(r5, r6);
    L_0x00b1:
        r0 = com.gaana.juke.JukeSessionManager.getInstance();
        r0.setCurrentBusinessObject(r11);
        r0 = com.gaana.juke.JukeSessionManager.getInstance();
        r5 = r10.mPageType;
        r0.setCurrentSessionType(r5);
        r0 = com.constants.Constants.cY;
        if (r0 != 0) goto L_0x00de;
    L_0x00c5:
        r0 = r10.mParentBusinessObject;
        r0 = (com.gaana.juke.JukePlaylist) r0;
        r0 = r0.getAdmin();
        if (r0 == 0) goto L_0x00de;
    L_0x00cf:
        r0 = r10.mParentBusinessObject;
        r0 = (com.gaana.juke.JukePlaylist) r0;
        r5 = r0.getPlStatus();
        r0 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1));
        if (r0 != 0) goto L_0x00de;
    L_0x00db:
        r10.handlePartyStatus(r3);
    L_0x00de:
        r0 = r10.mPageType;
        r10.setActionButton(r0);
        r0 = r10.mPageType;
        if (r0 == 0) goto L_0x010b;
    L_0x00e7:
        r0 = r10.mActionBar;
        r0.a(r3);
        r0 = r10.mActionBar;
        r5 = r11.getName();
        r6 = "";
        r0.a(r5, r6);
        r0 = r10.isFirstTime;
        if (r0 == 0) goto L_0x010b;
    L_0x00fb:
        r0 = com.gaana.juke.JukeSessionManager.getInstance();
        r5 = r10.mParentBusinessObject;
        r5 = (com.gaana.juke.JukePlaylist) r5;
        r0.setSyncListener(r5, r10);
        goto L_0x010b;
    L_0x0107:
        r10.destroy();
    L_0x010a:
        r4 = r1;
    L_0x010b:
        if (r11 == 0) goto L_0x018d;
    L_0x010d:
        r0 = r11.getArrListBusinessObj();
        if (r0 == 0) goto L_0x018d;
    L_0x0113:
        r0 = r11.getArrListBusinessObj();
        if (r0 == 0) goto L_0x0177;
    L_0x0119:
        r5 = r0.size();
        if (r5 == 0) goto L_0x0177;
    L_0x011f:
        r5 = r11.getArrListBusinessObj();
        r10.mBusinessObjectList = r5;
        r5 = r10.mBusinessObjectList;
        r5 = r5.size();
        r10.mSizeOfList = r5;
        r5 = r10.mParentBusinessObject;
        r5.setArrList(r0);
        r0 = r10.mRecyclerView;
        r5 = new android.support.v7.widget.DefaultItemAnimator;
        r5.<init>();
        r0.setItemAnimator(r5);
        r0 = r10.mParentBusinessObject;
        r11 = r11.getArrListBusinessObj();
        r11 = r11.size();
        r11 = java.lang.String.valueOf(r11);
        r0.setCount(r11);
        r10.mEmptyView = r2;
        r11 = r10.mSizeOfList;
        r11 = r11 + r3;
        r10.mSizeOfList = r11;
        r11 = r10.mCustomListAdapter;
        r0 = r10.mSizeOfList;
        r11.updateAdapterCount(r0);
        if (r4 == r1) goto L_0x0164;
    L_0x015d:
        r11 = r4 + 3;
        r0 = r10.mSizeOfList;
        if (r11 >= r0) goto L_0x0164;
    L_0x0163:
        goto L_0x0165;
    L_0x0164:
        r11 = r4;
    L_0x0165:
        r0 = r10.isFirstTime;
        if (r0 == 0) goto L_0x01a7;
    L_0x0169:
        if (r11 <= r1) goto L_0x01a7;
    L_0x016b:
        r0 = r10.mSizeOfList;
        if (r11 >= r0) goto L_0x01a7;
    L_0x016f:
        r0 = r10.mRecyclerView;
        r0.smoothScrollToPosition(r11);
        r10.isFirstTime = r2;
        goto L_0x01a7;
    L_0x0177:
        r10.mSizeOfList = r2;
        r11 = r10.mParentBusinessObject;
        r11.setArrListBusinessObj(r0);
        r11 = r10.mParentBusinessObject;
        r0 = "0";
        r11.setCount(r0);
        r11 = r10.mCustomListAdapter;
        r11.updateAdapterCount(r3);
        r10.mEmptyView = r3;
        goto L_0x01a7;
    L_0x018d:
        r10.mSizeOfList = r2;
        r11 = new java.util.ArrayList;
        r11.<init>();
        r0 = r10.mParentBusinessObject;
        r0.setArrListBusinessObj(r11);
        r11 = r10.mParentBusinessObject;
        r0 = "0";
        r11.setCount(r0);
        r11 = r10.mCustomListAdapter;
        r11.updateAdapterCount(r3);
        r10.mEmptyView = r3;
    L_0x01a7:
        r11 = r10.mPageType;
        if (r11 != 0) goto L_0x01d3;
    L_0x01ab:
        r11 = r10.mTotalSongs;
        r0 = r10.mContext;
        r0 = r0.getResources();
        r1 = 2131821987; // 0x7f1105a3 float:1.9276733E38 double:1.053260007E-314;
        r0 = r0.getString(r1);
        r1 = new java.lang.Object[r3];
        r4 = r10.mEmptyView;
        if (r4 != 0) goto L_0x01c4;
    L_0x01c0:
        r4 = r10.mSizeOfList;
        r4 = r4 - r3;
        goto L_0x01c6;
    L_0x01c4:
        r4 = r10.mSizeOfList;
    L_0x01c6:
        r3 = java.lang.Integer.valueOf(r4);
        r1[r2] = r3;
        r0 = java.lang.String.format(r0, r1);
        r11.setText(r0);
    L_0x01d3:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gaana.juke.JukeCreatePartyFragment.bindJukePlaylist(com.gaana.models.BusinessObject):void");
    }

    public void startAppIndexing() {
        if (!TextUtils.isEmpty(this.APP_URL)) {
            if (!this.mClient.isConnected()) {
                this.mClient.connect();
            }
            List arrayList = new ArrayList();
            AppIndex.AppIndexApi.view(this.mClient, (GaanaActivity) this.mContext, Uri.parse(this.APP_URL), this.TITLE, Uri.parse(this.WEB_URL), arrayList);
        }
    }

    public void stopAppIndex() {
        if (!TextUtils.isEmpty(this.APP_URL)) {
            AppIndex.AppIndexApi.viewEnd(this.mClient, (GaanaActivity) this.mContext, Uri.parse(this.APP_URL));
            this.mClient.disconnect();
        }
    }

    public void refreshDataandAds() {
        onRefresh();
    }

    public void onStartDrag(ViewHolder viewHolder) {
        this.mItemTouchHelper.startDrag(viewHolder);
    }

    public boolean onItemMove(int i, int i2) {
        Collections.swap(this.mBusinessObjectList, i, i2);
        this.mCustomListAdapter.notifyItemMoved(i, i2);
        if (this.mPageType != 0) {
            this.isReOrderRequired = true;
            JukeSessionManager.getInstance().setReOrderFlag((JukePlaylist) this.mParentBusinessObject, this.isReOrderRequired);
        }
        return true;
    }

    public void onItemDelete(int i, int i2) {
        if (this.mCurrentPlayingIndex < 0 || this.mCurrentPlayingIndex != i) {
            if (this.mPageType != 0) {
                JukeSessionManager.getInstance().addDeleteTracks((JukePlaylist) this.mParentBusinessObject, ((BusinessObject) this.mBusinessObjectList.get(i)).getBusinessObjId(), false);
            }
            this.mBusinessObjectList.remove(i);
            this.mCustomListAdapter.removeItem(i);
            this.mSizeOfList--;
            if (this.mPageType == 0) {
                TextView textView = this.mTotalSongs;
                String string = this.mContext.getString(R.string.num_songs);
                Object[] objArr = new Object[1];
                objArr[0] = Integer.valueOf(!this.mEmptyView ? this.mSizeOfList - 1 : this.mSizeOfList);
                textView.setText(String.format(string, objArr));
            }
            onResponse(this.mParentBusinessObject);
            u.a().b("PartyHub", "SwipeDelete");
            aj.a().a(this.mContext, this.mContext.getResources().getString(R.string.song_removed));
            return;
        }
        Toast.makeText(this.mContext, this.mContext.getResources().getString(R.string.current_playing_removed), 0).show();
    }

    public void onItemSetToLast(BusinessObject businessObject) {
        if (this.mBusinessObjectList != null) {
            int indexOf = this.mBusinessObjectList.indexOf(businessObject);
            if (indexOf > -1) {
                this.mBusinessObjectList.remove(indexOf);
                this.mCustomListAdapter.removeItem(indexOf);
                this.mBusinessObjectList.add(businessObject);
                this.mCustomListAdapter.addItem(this.mBusinessObjectList.size() - 1);
            }
        }
    }

    public void onItemDelete(BusinessObject businessObject) {
        if (this.mBusinessObjectList != null) {
            int i = 0;
            while (i < this.mBusinessObjectList.size()) {
                if (((BusinessObject) this.mBusinessObjectList.get(i)).getBusinessObjId().equals(businessObject.getBusinessObjId())) {
                    break;
                }
                i++;
            }
            i = -1;
            if (this.mCurrentPlayingIndex < 0 || this.mCurrentPlayingIndex != i) {
                if (i >= 0 && i < this.mBusinessObjectList.size()) {
                    if (this.mPageType != 0) {
                        JukeSessionManager.getInstance().addDeleteTracks((JukePlaylist) this.mParentBusinessObject, ((BusinessObject) this.mBusinessObjectList.get(i)).getBusinessObjId(), false);
                    }
                    this.mBusinessObjectList.remove(i);
                    this.mCustomListAdapter.removeItem(i);
                    this.mSizeOfList--;
                    if (this.mPageType == 0) {
                        TextView textView = this.mTotalSongs;
                        String string = this.mContext.getString(R.string.num_songs);
                        Object[] objArr = new Object[1];
                        objArr[0] = Integer.valueOf(!this.mEmptyView ? this.mSizeOfList - 1 : this.mSizeOfList);
                        textView.setText(String.format(string, objArr));
                    }
                    aj.a().a(this.mContext, this.mContext.getResources().getString(R.string.song_removed));
                }
                onResponse(this.mParentBusinessObject);
            } else {
                Toast.makeText(this.mContext, this.mContext.getResources().getString(R.string.current_playing_removed), 0).show();
            }
        }
    }

    public void onChildDraw(Canvas canvas, RecyclerView recyclerView, ViewHolder viewHolder, float f, float f2, int i, boolean z) {
        if ((this.mCurrentPlayingIndex < 0 || this.mCurrentPlayingIndex != viewHolder.getAdapterPosition()) && i == 1) {
            View view = viewHolder.itemView;
            f2 = (((float) view.getBottom()) - ((float) view.getTop())) / 3.0f;
            if (viewHolder.getAdapterPosition() >= 0) {
                view.setTranslationX(f);
                Drawable drawable;
                if (f > 0.0f) {
                    this.mPaint.setColor(view.getResources().getColor(R.color.f17gaana.red));
                    canvas.drawRect(new RectF((float) view.getLeft(), (float) view.getTop(), f, (float) view.getBottom()), this.mPaint);
                    drawable = ContextCompat.getDrawable(view.getContext(), this.mDeleteResId);
                    drawable.setBounds(new Rect((int) (((float) view.getLeft()) + f2), (int) (((float) view.getTop()) + f2), (int) (((float) view.getLeft()) + (2.0f * f2)), (int) (((float) view.getBottom()) - f2)));
                    drawable.draw(canvas);
                } else {
                    this.mPaint.setColor(view.getResources().getColor(R.color.f17gaana.red));
                    canvas.drawRect(new RectF(((float) view.getRight()) + f, (float) view.getTop(), (float) view.getRight(), (float) view.getBottom()), this.mPaint);
                    drawable = ContextCompat.getDrawable(view.getContext(), this.mDeleteResId);
                    drawable.setBounds(new Rect((int) (((float) view.getRight()) - (2.0f * f2)), (int) (((float) view.getTop()) + f2), (int) (((float) view.getRight()) - f2), (int) (((float) view.getBottom()) - f2)));
                    drawable.draw(canvas);
                }
            }
        }
    }

    public void onSyncStarted() {
        ((BaseActivity) this.mContext).showProgressDialog(Boolean.valueOf(false), this.mContext.getResources().getString(R.string.syncing));
    }

    public void onSyncEnd(BusinessObject businessObject) {
        ((BaseActivity) this.mContext).hideProgressDialog();
        if (isAdded()) {
            onResponse(businessObject);
        }
    }

    public String getSessionId() {
        return (this.mParentBusinessObject == null || TextUtils.isEmpty(this.mParentBusinessObject.getBusinessObjId())) ? "" : this.mParentBusinessObject.getBusinessObjId();
    }

    public void destroy() {
        ((GaanaActivity) this.mContext).popBackStackImmediate();
    }

    public void onSyncError() {
        ((BaseActivity) this.mContext).hideProgressDialog();
    }
}
