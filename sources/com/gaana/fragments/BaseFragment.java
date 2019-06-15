package com.gaana.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.constants.a.a;
import com.fragments.MiniPlayerFragment;
import com.fragments.MiniPlayerFragmentV4;
import com.fragments.PlayerFragmentV2;
import com.fragments.PlayerRadioFragmentV2;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.models.Tracks.Track;
import com.managers.PlayerManager;
import com.managers.PlayerManager.PlaySequenceType;
import com.managers.PlayerManager.PlayerType;
import com.managers.ad;
import com.models.PlayerTrack;
import com.player_framework.PlayerStatus.PlayerStates;
import com.services.l.av;

public abstract class BaseFragment extends Fragment implements a, com.fragments.a, av {
    protected GaanaApplication mAppState;
    protected Context mContext;
    protected PlayerTrack mCurrentTrack;
    protected PlayerManager mPlayerManager;
    protected PlayerStates mPlayerStates = PlayerStates.INVALID;
    protected PlayerType mPlayerType;
    protected ad mRadioManager;

    public String getFragmentStackName() {
        return "player";
    }

    public void onFragmentScroll() {
    }

    public void onLiveRadioUpdate() {
    }

    public void onPlayerStateChanged() {
    }

    public void onRadioTracksFetched(boolean z) {
    }

    public void on_deque() {
    }

    public void on_enque() {
    }

    public void refreshForFavorite() {
    }

    public void refreshList() {
    }

    public void refreshPlayerStatus() {
    }

    public abstract void setGAScreenName(String str, String str2);

    public void updateCardAdapter(boolean z) {
    }

    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.mAppState = GaanaApplication.getInstance();
        if (PlayerManager.a(GaanaApplication.getContext()).m() != this.mPlayerType) {
            switchPlayer();
        }
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        hideHomeActionBar();
        if (bundle != null) {
            this.mPlayerType = PlayerType.valueOf(bundle.getString("player_type", PlayerType.GAANA.name()));
        }
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public void onResume() {
        super.onResume();
        if (((GaanaActivity) this.mContext).getSlidingPanelLayout().a() != 2) {
            ((GaanaActivity) this.mContext).findViewById(R.id.bottom_bar).setVisibility(8);
            ((GaanaActivity) this.mContext).mPlayerStateChanged = true;
            ((GaanaActivity) this.mContext).setDrawerLockMode(1);
        }
        Fragment miniPlayer = ((GaanaActivity) this.mContext).getMiniPlayer();
        if (miniPlayer != null) {
            if (miniPlayer instanceof MiniPlayerFragment) {
                ((MiniPlayerFragment) miniPlayer).a((av) this);
            } else if (miniPlayer instanceof MiniPlayerFragmentV4) {
                ((MiniPlayerFragmentV4) miniPlayer).a((av) this);
            }
        }
        ((GaanaActivity) this.mContext).showHideVoiceCoachMark(R.id.voice_longpress_coachmark, false);
        if ((this instanceof PlayerFragmentV2) || (this instanceof PlayerRadioFragmentV2)) {
            ((GaanaActivity) this.mContext).findViewById(R.id.dummy_status_bar).setVisibility(0);
        } else {
            ((GaanaActivity) this.mContext).findViewById(R.id.dummy_status_bar).setVisibility(8);
        }
        ((GaanaActivity) this.mContext).showHideNewDownloadedSongCount();
        ((GaanaActivity) this.mContext).hideAnimationToMyMusic();
    }

    public void sendGAScreenName(String str, String str2) {
        ((BaseActivity) this.mContext).currentScreen = str;
        ((BaseActivity) this.mContext).setGoogleAnalyticsScreenName(str2);
    }

    /* Access modifiers changed, original: protected */
    public String getSubtitleText(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.append(" - ");
            stringBuilder.append(str2);
            return stringBuilder.toString();
        } else if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            return "";
        } else {
            return TextUtils.isEmpty(str) ? str2 : str;
        }
    }

    /* Access modifiers changed, original: protected */
    public Track getPlayingTrack() {
        if (this.mCurrentTrack != null) {
            return this.mCurrentTrack.a(true);
        }
        this.mCurrentTrack = this.mPlayerManager.a(PlaySequenceType.CURRENT);
        return this.mCurrentTrack.a(true);
    }

    /* Access modifiers changed, original: protected */
    public boolean isActivityDestroyed() {
        if (getActivity() == null || getActivity().isFinishing()) {
            return true;
        }
        this.mContext = getActivity();
        return false;
    }

    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("player_type", this.mPlayerType.name());
    }

    public void hideHomeActionBar() {
        LinearLayout linearLayout = (LinearLayout) ((BaseActivity) this.mContext).findViewById(R.id.home_toolbar);
        linearLayout.removeAllViews();
        linearLayout.setVisibility(8);
    }

    public void setPlayerType(PlayerType playerType) {
        this.mPlayerType = playerType;
    }

    /* Access modifiers changed, original: protected */
    public boolean switchPlayer() {
        return ((GaanaActivity) this.mContext).popBackStackImmediate() && ((GaanaActivity) this.mContext).launchExpandedPlayer();
    }
}
