package com.actionbar;

import android.content.Context;
import android.support.v7.widget.AppCompatCheckedTextView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.RelativeLayout;
import com.collapsible_header.SongParallexListingFragment;
import com.constants.Constants;
import com.fragments.AlbumDetailsMaterialListing;
import com.fragments.BaseGaanaFragment;
import com.fragments.DownloadDetailsFragment;
import com.fragments.FavoritesFragment;
import com.fragments.GaanaSpecialDetailsMaterialListing;
import com.fragments.ListingFragment;
import com.fragments.LocalMediaFragment;
import com.fragments.RevampedDetailListing;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.juke.JukeSessionManager;
import com.gaana.localmedia.LocalMediaManager;
import com.gaana.models.BusinessObject;
import com.gaana.models.OfflineTrack;
import com.gaana.models.Tracks.Track;
import com.managers.DownloadManager;
import com.managers.PlayerManager;
import com.managers.aj;
import com.managers.al;
import com.managers.ap;
import com.managers.u;
import com.services.l.ad;
import com.utilities.Util;
import java.util.ArrayList;
import java.util.Iterator;

public class BaseContextualActionBar extends RelativeLayout implements OnClickListener {
    private BusinessObject mBusinessObject;
    private Context mContext;
    private BaseGaanaFragment mFragment;
    private LayoutInflater mLayoutInflater;
    private AppCompatCheckedTextView textView;

    public BaseContextualActionBar(Context context) {
        this(context, null, 0);
    }

    public BaseContextualActionBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public BaseContextualActionBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mContext = context;
        this.mLayoutInflater = LayoutInflater.from(context);
        this.mLayoutInflater.inflate(R.layout.action_base_contextual, this);
    }

    public void setParams(BaseGaanaFragment baseGaanaFragment, BusinessObject businessObject) {
        this.mFragment = baseGaanaFragment;
        this.mBusinessObject = businessObject;
        initActionBarViews();
    }

    private void initActionBarViews() {
        this.textView = (AppCompatCheckedTextView) findViewById(R.id.title_count);
        findViewById(R.id.menu_icon_base).setOnClickListener(this);
        findViewById(R.id.context_menu_playlist).setOnClickListener(this);
        findViewById(R.id.context_menu_queue).setOnClickListener(this);
        this.textView.setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 1 && !view.hasFocus()) {
                    view.performClick();
                }
                return false;
            }
        });
        this.textView.setOnClickListener(this);
        findViewById(R.id.menu_icon_base).setOnClickListener(this);
    }

    public void updateSelectedCountinContextMode(int i) {
        if (al.a().d()) {
            AppCompatCheckedTextView appCompatCheckedTextView = this.textView;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(String.valueOf(al.a().f()));
            stringBuilder.append("/");
            stringBuilder.append(String.valueOf(i));
            appCompatCheckedTextView.setText(stringBuilder.toString());
        }
    }

    public void showContextMenu(boolean z) {
        al.a = z;
        if (z) {
            findViewById(R.id.action_bar_contextual).setVisibility(0);
        } else {
            findViewById(R.id.action_bar_contextual).setVisibility(8);
        }
    }

    public void hideContextMenu(boolean z) {
        if (z) {
            findViewById(R.id.action_bar_contextual).setVisibility(8);
        } else {
            findViewById(R.id.action_bar_contextual).setVisibility(0);
        }
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.context_menu_playlist /*2131296784*/:
                u.a().a("Add to Playlist", "Long Press", "Multiple");
                if (!isNoSongSelected()) {
                    if (this.mBusinessObject != null && this.mBusinessObject.isLocalMedia()) {
                        showAddPlaylist();
                        break;
                    } else {
                        ((BaseActivity) this.mContext).checkSetLoginStatus(new ad() {
                            public void onLoginSuccess() {
                                BaseContextualActionBar.this.showAddPlaylist();
                            }
                        }, GaanaApplication.getContext().getResources().getString(R.string.LOGIN_LAUNCHED_FOR_ADD_TO_PLAYLIST));
                        break;
                    }
                }
                aj.a().a(this.mContext, this.mContext.getString(R.string.playlist_error_notracks_selected));
                break;
            case R.id.context_menu_queue /*2131296785*/:
                ArrayList arrListBusinessObj = al.a().g().getArrListBusinessObj();
                if (arrListBusinessObj != null && arrListBusinessObj.size() > 0) {
                    int i = 0;
                    if (!Constants.cY) {
                        if (arrListBusinessObj.size() <= 500) {
                            ArrayList arrayList = new ArrayList();
                            int i2 = (((GaanaApplication) this.mContext.getApplicationContext()).isAppInOfflineMode() || !Util.j(this.mContext)) ? true : 0;
                            Iterator it = arrListBusinessObj.iterator();
                            while (it.hasNext()) {
                                BusinessObject businessObject = (BusinessObject) it.next();
                                if (businessObject instanceof Track) {
                                    Track track = (Track) businessObject;
                                    if (i2 == 0) {
                                        arrayList.add(track);
                                    } else if (DownloadManager.c().l(Integer.parseInt(track.getBusinessObjId())).booleanValue() || track.isLocalMedia()) {
                                        arrayList.add(track);
                                    }
                                } else {
                                    arrayList.add(getSingleTrackFromOfflineTrack(businessObject));
                                }
                            }
                            if (i2 != 0 && arrayList.size() == 0) {
                                aj.a().a(this.mContext, this.mContext.getString(R.string.player_nooffline_songs));
                                break;
                            }
                            ((GaanaActivity) this.mContext).showProgressDialog(Boolean.valueOf(true), this.mContext.getString(R.string.dlg_msg_adding_to_player));
                            if (arrListBusinessObj.size() == 0) {
                                aj.a().a(this.mContext, this.mContext.getString(R.string.player_nosongs_toplay));
                            } else {
                                PlayerManager.a(this.mContext).a(arrayList, this.mBusinessObject, this.mContext, false);
                                ((GaanaActivity) this.mContext).setUpdatePlayerFragment();
                            }
                            ((GaanaActivity) this.mContext).hideProgressDialog();
                            destroyActionMode();
                            break;
                        }
                        aj.a().a(this.mContext, this.mContext.getString(R.string.songs_selection_size_exceeds));
                        break;
                    }
                    while (i < arrListBusinessObj.size()) {
                        JukeSessionManager.getInstance().addPlayNext(JukeSessionManager.getInstance().getJukeSessionPlaylist(), ((Track) arrListBusinessObj.get(i)).getBusinessObjId());
                        i++;
                    }
                    return;
                }
                aj.a().a(this.mContext, this.mContext.getString(R.string.playlist_error_notracks_selected));
                break;
                break;
            case R.id.menu_icon_base /*2131297711*/:
                destroyActionMode();
                break;
            case R.id.title_count /*2131298637*/:
                if (this.mFragment instanceof AlbumDetailsMaterialListing) {
                    ((AlbumDetailsMaterialListing) this.mFragment).n();
                }
                if (this.mFragment instanceof SongParallexListingFragment) {
                    ((SongParallexListingFragment) this.mFragment).i();
                }
                if (this.mFragment instanceof GaanaSpecialDetailsMaterialListing) {
                    ((GaanaSpecialDetailsMaterialListing) this.mFragment).i();
                }
                if (this.mFragment instanceof FavoritesFragment) {
                    ((FavoritesFragment) this.mFragment).g();
                }
                if (this.mFragment instanceof DownloadDetailsFragment) {
                    ((DownloadDetailsFragment) this.mFragment).i();
                }
                if (this.mFragment instanceof LocalMediaFragment) {
                    ((LocalMediaFragment) this.mFragment).e();
                }
                if (this.mFragment instanceof ListingFragment) {
                    ((ListingFragment) this.mFragment).m();
                    break;
                }
                break;
        }
    }

    private void showAddPlaylist() {
        ArrayList arrListBusinessObj = al.a().g().getArrListBusinessObj();
        if (arrListBusinessObj.size() > 0) {
            ArrayList arrayList = new ArrayList();
            Iterator it = arrListBusinessObj.iterator();
            while (it.hasNext()) {
                Object next = it.next();
                if (next instanceof Track) {
                    arrayList.add((Track) next);
                } else if (next instanceof BusinessObject) {
                    Track singleTrackFromOfflineTrack = getSingleTrackFromOfflineTrack(next);
                    if (singleTrackFromOfflineTrack != null) {
                        arrayList.add(singleTrackFromOfflineTrack);
                    }
                }
            }
            GaanaApplication.getInstance().setArrListTracksForPlaylist(arrayList);
        }
        ap.a().a(this.mContext, false);
        destroyActionMode();
    }

    private Track getSingleTrackFromOfflineTrack(Object obj) {
        BusinessObject businessObject = (BusinessObject) obj;
        if (businessObject.isLocalMedia()) {
            return LocalMediaManager.getInstance(this.mContext).getTrackFromLocalMedia((OfflineTrack) obj);
        }
        return (Track) DownloadManager.c().a(businessObject.getBusinessObjId(), true);
    }

    private ArrayList<Track> getTrackFromOfflineTrack(ArrayList<?> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            Object next = it.next();
            BusinessObject businessObject = (BusinessObject) next;
            if (businessObject.isLocalMedia()) {
                next = LocalMediaManager.getInstance(this.mContext).getTrackFromLocalMedia((OfflineTrack) next);
            } else {
                next = (Track) DownloadManager.c().a(businessObject.getBusinessObjId(), true);
            }
            arrayList2.add(next);
        }
        return arrayList2;
    }

    private void destroyActionMode() {
        if (this.mFragment instanceof AlbumDetailsMaterialListing) {
            ((AlbumDetailsMaterialListing) this.mFragment).k();
        } else if (this.mFragment instanceof RevampedDetailListing) {
            ((RevampedDetailListing) this.mFragment).k();
        } else if (this.mFragment instanceof GaanaSpecialDetailsMaterialListing) {
            ((GaanaSpecialDetailsMaterialListing) this.mFragment).g();
        } else if (this.mFragment instanceof SongParallexListingFragment) {
            ((SongParallexListingFragment) this.mFragment).h();
        } else if (this.mFragment instanceof FavoritesFragment) {
            ((FavoritesFragment) this.mFragment).e();
        } else if (this.mFragment instanceof DownloadDetailsFragment) {
            ((DownloadDetailsFragment) this.mFragment).g();
        } else if (this.mFragment instanceof LocalMediaFragment) {
            ((LocalMediaFragment) this.mFragment).c();
        } else if (this.mFragment instanceof ListingFragment) {
            ((ListingFragment) this.mFragment).j();
        }
    }

    private boolean isNoSongSelected() {
        ArrayList arrListBusinessObj = al.a().g().getArrListBusinessObj();
        if (arrListBusinessObj != null && arrListBusinessObj.size() > 0) {
            return false;
        }
        return true;
    }
}
