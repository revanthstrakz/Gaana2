package com.gaana.view.item;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.v4.view.GravityCompat;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.PopupMenu.OnMenuItemClickListener;
import android.view.MenuItem;
import android.view.View;
import com.actionbar.PlayerQueueActionBar.a;
import com.fragments.BaseGaanaFragment;
import com.gaana.R;
import com.gaana.adapter.MusicQueueAdapter.IUpdatePlayer;
import com.gaana.adapter.MusicQueueAdapterV2;
import com.gaana.application.GaanaApplication;
import com.gaana.models.BusinessObject;
import com.gaana.models.Tracks.Track;
import com.gaana.view.PlayerQueueView;
import com.gaana.view.PlayerQueueViewV2;
import com.gaana.view.item.CustomDialogView.OnButtonClickListener;
import com.gaana.view.item.PopupItemView.DownloadPopupListener;
import com.gaana.view.item.PopupItemView.IDismissPopup;
import com.managers.PlayerManager;
import com.managers.aj;
import com.managers.ap;
import com.managers.u;
import com.models.PlayerTrack;
import java.util.ArrayList;
import java.util.Iterator;

public class PopupWindowView implements a, IDismissPopup {
    private static Dialog mPopupItemView;
    private IArtistItemListener _artistItemListener;
    private GaanaApplication mAppState = GaanaApplication.getInstance();
    private Context mContext;
    private DownloadPopupListener mDownloadPopupListener;
    private BaseGaanaFragment mFragment;

    public interface IArtistItemListener {
        void ChangeArtistItemStatus();
    }

    public PopupWindowView(Context context, BaseGaanaFragment baseGaanaFragment) {
        this.mContext = context;
        this.mFragment = baseGaanaFragment;
    }

    public static PopupWindowView getInstance(Context context, BaseGaanaFragment baseGaanaFragment) {
        return new PopupWindowView(context, baseGaanaFragment);
    }

    public void populatePlayerQueue(BusinessObject businessObject, IUpdatePlayer iUpdatePlayer) {
        if (businessObject != null) {
            playerQueuePopupWindow(PlayerManager.a(this.mContext).n(), iUpdatePlayer);
        }
    }

    public void populatePlayerQueue(BusinessObject businessObject, MusicQueueAdapterV2.IUpdatePlayer iUpdatePlayer) {
        if (businessObject != null) {
            playerQueuePopupWindow(PlayerManager.a(this.mContext).n(), iUpdatePlayer);
        }
    }

    public void contextPopupWindow(BusinessObject businessObject, boolean z, ap.a aVar, boolean z2) {
        if (this.mAppState == null) {
            this.mAppState = GaanaApplication.getInstance();
        }
        PopupItemView popupItemView = new PopupItemView(this.mContext, businessObject, this.mFragment);
        popupItemView.setFavoriteCompletedListner(aVar);
        popupItemView.setQueueOpen(z2);
        popupItemView.setDownloadPopupListener(this.mDownloadPopupListener);
        popupItemView.setOnDismissListener(this);
        popupItemView.getView(z);
        if (!((Activity) this.mContext).isFinishing()) {
            popupItemView.show();
        }
        setArtistItemListener(popupItemView);
    }

    public void contextPopupWindow(BusinessObject businessObject, boolean z, boolean z2) {
        if (this.mAppState == null) {
            this.mAppState = GaanaApplication.getInstance();
        }
        PopupItemView popupItemView = new PopupItemView(this.mContext, businessObject, this.mFragment);
        if (this.mDownloadPopupListener != null) {
            popupItemView.setDownloadPopupListener(this.mDownloadPopupListener);
        }
        popupItemView.setOnDismissListener(this);
        popupItemView.getView(z);
        popupItemView.setQueueOpen(z2);
        if (!((Activity) this.mContext).isFinishing()) {
            popupItemView.show();
        }
        setArtistItemListener(popupItemView);
    }

    public void contextPopupWindow(BusinessObject businessObject, boolean z, boolean z2, boolean z3, boolean z4) {
        if (this.mAppState == null) {
            this.mAppState = GaanaApplication.getInstance();
        }
        PopupItemView popupItemView = new PopupItemView(this.mContext, businessObject, this.mFragment);
        if (this.mDownloadPopupListener != null) {
            popupItemView.setDownloadPopupListener(this.mDownloadPopupListener);
        }
        popupItemView.setOnDismissListener(this);
        popupItemView.getView(z);
        popupItemView.setQueueOpen(z2);
        popupItemView.setRemoveRecentlyPlayed(z3);
        popupItemView.setRemoveRecentSearch(z4);
        if (!((Activity) this.mContext).isFinishing()) {
            popupItemView.show();
        }
        setArtistItemListener(popupItemView);
    }

    private void setArtistItemListener(final PopupItemView popupItemView) {
        setArtistItemListener(new IArtistItemListener() {
            public void ChangeArtistItemStatus() {
                popupItemView.notifyDataSetChange();
            }
        });
    }

    public void contextPopupWindowPlayer() {
        if (this.mAppState == null) {
            this.mAppState = GaanaApplication.getInstance();
        }
        PopupItemView popupItemView = new PopupItemView(this.mContext, new BusinessObject(), this.mFragment);
        popupItemView.setOnDismissListener(this);
        popupItemView.getPlayerOptionView();
        if (!((Activity) this.mContext).isFinishing()) {
            popupItemView.show();
        }
        setArtistItemListener(popupItemView);
    }

    public void contextOneTouchPopup(BusinessObject businessObject) {
        if (this.mAppState == null) {
            this.mAppState = GaanaApplication.getInstance();
        }
        PopupItemView popupItemView = new PopupItemView(this.mContext, businessObject, this.mFragment);
        popupItemView.setOnDismissListener(this);
        popupItemView.getOneTouchRadioOptionView();
        if (!((Activity) this.mContext).isFinishing()) {
            popupItemView.show();
        }
        setArtistItemListener(popupItemView);
    }

    private void playerQueuePopupWindow(ArrayList<?> arrayList, MusicQueueAdapterV2.IUpdatePlayer iUpdatePlayer) {
        mPopupItemView = new PlayerQueueViewV2(this.mContext, this.mFragment);
        ((PlayerQueueViewV2) mPopupItemView).getView(this.mContext, arrayList, iUpdatePlayer);
        if (!((Activity) this.mContext).isFinishing()) {
            mPopupItemView.show();
        }
    }

    private void playerQueuePopupWindow(ArrayList<?> arrayList, IUpdatePlayer iUpdatePlayer) {
        mPopupItemView = new PlayerQueueView(this.mContext, this.mFragment);
        ((PlayerQueueView) mPopupItemView).getView(this.mContext, arrayList, iUpdatePlayer);
        if (!((Activity) this.mContext).isFinishing()) {
            mPopupItemView.show();
        }
    }

    public void dismiss() {
        if (mPopupItemView != null && mPopupItemView.isShowing()) {
            try {
                mPopupItemView.dismiss();
                mPopupItemView = null;
            } catch (Exception unused) {
            }
        }
    }

    public void dismiss(boolean z) {
        dismiss();
    }

    public void onBackClicked() {
        dismiss();
    }

    public void onMenuClicked(View view) {
        showMenu(view);
    }

    public void onItemClicked(int i) {
        handleClickListener(i);
    }

    private void handleClickListener(int i) {
        if (i == R.id.clearQueueActionbar) {
            u.a().a("PlayerQueue", "Clear queue", "PlayerQueue - Clear queue");
            new CustomDialogView(this.mContext, this.mContext.getResources().getString(R.string.toast_clear_player_queue), new OnButtonClickListener() {
                public void onNegativeButtonClick() {
                }

                public void onPositiveButtonClick() {
                    PlayerManager.a(PopupWindowView.this.mContext).B();
                }
            }).show();
        } else if (i == R.id.menu_add_playlist) {
            if (this.mAppState == null) {
                this.mAppState = (GaanaApplication) this.mContext.getApplicationContext();
            }
            if (mPopupItemView != null) {
                ArrayList adapterArrayList = ((PlayerQueueViewV2) mPopupItemView).getAdapterArrayList();
                if (adapterArrayList != null) {
                    ArrayList arrayList = new ArrayList();
                    Iterator it = adapterArrayList.iterator();
                    while (it.hasNext()) {
                        arrayList.add(((PlayerTrack) it.next()).a(true));
                    }
                    addToPlaylist(arrayList);
                    return;
                }
                aj.a().a(this.mContext, this.mContext.getString(R.string.no_songs_to_add));
                return;
            }
            aj.a().a(this.mContext, this.mContext.getString(R.string.no_songs_to_add));
        }
    }

    private void showMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this.mContext, view, GravityCompat.END);
        popupMenu.inflate(R.menu.player_queue_menu);
        popupMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            public boolean onMenuItemClick(MenuItem menuItem) {
                PopupWindowView.this.handleClickListener(menuItem.getItemId());
                return true;
            }
        });
        popupMenu.show();
    }

    private void addToPlaylist(ArrayList<Track> arrayList) {
        dismiss();
        ap.a().a(this.mContext, (ArrayList) arrayList, false);
    }

    public void setDownloadPopupListener(DownloadPopupListener downloadPopupListener) {
        this.mDownloadPopupListener = downloadPopupListener;
    }

    public Dialog getmPopupItemView() {
        return mPopupItemView;
    }

    private void setArtistItemListener(IArtistItemListener iArtistItemListener) {
        this._artistItemListener = iArtistItemListener;
    }

    public IArtistItemListener getArtistItemListener() {
        return this._artistItemListener;
    }
}
