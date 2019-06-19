package com.google.android.gms.wallet.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.dynamic.DeferredLifecycleHelper;
import com.google.android.gms.dynamic.LifecycleDelegate;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.dynamic.OnDelegateCreatedListener;
import com.google.android.gms.dynamic.SupportFragmentWrapper;
import com.google.android.gms.internal.wallet.zzam;
import com.google.android.gms.internal.wallet.zzn;
import com.google.android.gms.internal.wallet.zzr;
import com.google.android.gms.wallet.MaskedWallet;
import com.google.android.gms.wallet.MaskedWalletRequest;
import com.google.android.gms.wallet.R;

public final class SupportWalletFragment extends Fragment {
    private final Fragment fragment = this;
    private zzb zzfd;
    private boolean zzfe = false;
    private final SupportFragmentWrapper zzff = SupportFragmentWrapper.wrap(this);
    private final zzc zzfg = new zzc(this, null);
    private zza zzfh = new zza(this);
    private WalletFragmentOptions zzfi;
    private WalletFragmentInitParams zzfj;
    private MaskedWalletRequest zzfk;
    private MaskedWallet zzfl;
    private Boolean zzfm;

    public interface OnStateChangedListener {
        void onStateChanged(SupportWalletFragment supportWalletFragment, int i, int i2, Bundle bundle);
    }

    static class zza extends zzr {
        private OnStateChangedListener zzfn;
        private final SupportWalletFragment zzfo;

        zza(SupportWalletFragment supportWalletFragment) {
            this.zzfo = supportWalletFragment;
        }

        public final void zza(int i, int i2, Bundle bundle) {
            if (this.zzfn != null) {
                this.zzfn.onStateChanged(this.zzfo, i, i2, bundle);
            }
        }

        public final void zza(OnStateChangedListener onStateChangedListener) {
            this.zzfn = onStateChangedListener;
        }
    }

    private static class zzb implements LifecycleDelegate {
        private final zzn zzfp;

        private zzb(zzn zzn) {
            this.zzfp = zzn;
        }

        public final void onDestroy() {
        }

        public final void onDestroyView() {
        }

        public final void onLowMemory() {
        }

        private final void initialize(WalletFragmentInitParams walletFragmentInitParams) {
            try {
                this.zzfp.initialize(walletFragmentInitParams);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        private final void setEnabled(boolean z) {
            try {
                this.zzfp.setEnabled(z);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        private final void updateMaskedWalletRequest(MaskedWalletRequest maskedWalletRequest) {
            try {
                this.zzfp.updateMaskedWalletRequest(maskedWalletRequest);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        private final void updateMaskedWallet(MaskedWallet maskedWallet) {
            try {
                this.zzfp.updateMaskedWallet(maskedWallet);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        private final int getState() {
            try {
                return this.zzfp.getState();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        public final void onInflate(Activity activity, Bundle bundle, Bundle bundle2) {
            try {
                this.zzfp.zza(ObjectWrapper.wrap(activity), (WalletFragmentOptions) bundle.getParcelable("extraWalletFragmentOptions"), bundle2);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        public final void onCreate(Bundle bundle) {
            try {
                this.zzfp.onCreate(bundle);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            try {
                return (View) ObjectWrapper.unwrap(this.zzfp.onCreateView(ObjectWrapper.wrap(layoutInflater), ObjectWrapper.wrap(viewGroup), bundle));
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        public final void onStart() {
            try {
                this.zzfp.onStart();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        public final void onResume() {
            try {
                this.zzfp.onResume();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        public final void onPause() {
            try {
                this.zzfp.onPause();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        public final void onStop() {
            try {
                this.zzfp.onStop();
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        public final void onSaveInstanceState(Bundle bundle) {
            try {
                this.zzfp.onSaveInstanceState(bundle);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        private final void onActivityResult(int i, int i2, Intent intent) {
            try {
                this.zzfp.onActivityResult(i, i2, intent);
            } catch (RemoteException e) {
                throw new RuntimeException(e);
            }
        }

        /* synthetic */ zzb(zzn zzn, zza zza) {
            this(zzn);
        }
    }

    private class zzc extends DeferredLifecycleHelper<zzb> implements OnClickListener {
        private zzc() {
        }

        /* Access modifiers changed, original: protected|final */
        public final void createDelegate(OnDelegateCreatedListener<zzb> onDelegateCreatedListener) {
            FragmentActivity activity = SupportWalletFragment.this.fragment.getActivity();
            if (SupportWalletFragment.this.zzfd == null && SupportWalletFragment.this.zzfe && activity != null) {
                try {
                    SupportWalletFragment.this.zzfd = new zzb(zzam.zza(activity, SupportWalletFragment.this.zzff, SupportWalletFragment.this.zzfi, SupportWalletFragment.this.zzfh), null);
                    SupportWalletFragment.this.zzfi = null;
                    onDelegateCreatedListener.onDelegateCreated(SupportWalletFragment.this.zzfd);
                    if (SupportWalletFragment.this.zzfj != null) {
                        SupportWalletFragment.this.zzfd.initialize(SupportWalletFragment.this.zzfj);
                        SupportWalletFragment.this.zzfj = null;
                    }
                    if (SupportWalletFragment.this.zzfk != null) {
                        SupportWalletFragment.this.zzfd.updateMaskedWalletRequest(SupportWalletFragment.this.zzfk);
                        SupportWalletFragment.this.zzfk = null;
                    }
                    if (SupportWalletFragment.this.zzfl != null) {
                        SupportWalletFragment.this.zzfd.updateMaskedWallet(SupportWalletFragment.this.zzfl);
                        SupportWalletFragment.this.zzfl = null;
                    }
                    if (SupportWalletFragment.this.zzfm != null) {
                        SupportWalletFragment.this.zzfd.setEnabled(SupportWalletFragment.this.zzfm.booleanValue());
                        SupportWalletFragment.this.zzfm = null;
                    }
                } catch (GooglePlayServicesNotAvailableException unused) {
                }
            }
        }

        /* Access modifiers changed, original: protected|final */
        public final void handleGooglePlayUnavailable(FrameLayout frameLayout) {
            Button button = new Button(SupportWalletFragment.this.fragment.getActivity());
            button.setText(R.string.wallet_buy_button_place_holder);
            int i = -2;
            int i2 = -1;
            if (SupportWalletFragment.this.zzfi != null) {
                WalletFragmentStyle fragmentStyle = SupportWalletFragment.this.zzfi.getFragmentStyle();
                if (fragmentStyle != null) {
                    DisplayMetrics displayMetrics = SupportWalletFragment.this.fragment.getResources().getDisplayMetrics();
                    i2 = fragmentStyle.zza("buyButtonWidth", displayMetrics, -1);
                    i = fragmentStyle.zza("buyButtonHeight", displayMetrics, -2);
                }
            }
            button.setLayoutParams(new LayoutParams(i2, i));
            button.setOnClickListener(this);
            frameLayout.addView(button);
        }

        public final void onClick(View view) {
            FragmentActivity activity = SupportWalletFragment.this.fragment.getActivity();
            GooglePlayServicesUtil.showErrorDialogFragment(GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity, GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE), activity, -1);
        }

        /* synthetic */ zzc(SupportWalletFragment supportWalletFragment, zza zza) {
            this();
        }
    }

    public static SupportWalletFragment newInstance(WalletFragmentOptions walletFragmentOptions) {
        SupportWalletFragment supportWalletFragment = new SupportWalletFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("extraWalletFragmentOptions", walletFragmentOptions);
        supportWalletFragment.fragment.setArguments(bundle);
        return supportWalletFragment;
    }

    public final void initialize(WalletFragmentInitParams walletFragmentInitParams) {
        if (this.zzfd != null) {
            this.zzfd.initialize(walletFragmentInitParams);
            this.zzfj = null;
            return;
        }
        if (this.zzfj == null) {
            this.zzfj = walletFragmentInitParams;
            if (this.zzfk != null) {
                Log.w("SupportWalletFragment", "updateMaskedWalletRequest() was called before initialize()");
            }
            if (this.zzfl != null) {
                Log.w("SupportWalletFragment", "updateMaskedWallet() was called before initialize()");
                return;
            }
        }
        Log.w("SupportWalletFragment", "initialize(WalletFragmentInitParams) was called more than once. Ignoring.");
    }

    public final void updateMaskedWalletRequest(MaskedWalletRequest maskedWalletRequest) {
        if (this.zzfd != null) {
            this.zzfd.updateMaskedWalletRequest(maskedWalletRequest);
            this.zzfk = null;
            return;
        }
        this.zzfk = maskedWalletRequest;
    }

    public final void updateMaskedWallet(MaskedWallet maskedWallet) {
        if (this.zzfd != null) {
            this.zzfd.updateMaskedWallet(maskedWallet);
            this.zzfl = null;
            return;
        }
        this.zzfl = maskedWallet;
    }

    public final void setEnabled(boolean z) {
        if (this.zzfd != null) {
            this.zzfd.setEnabled(z);
            this.zzfm = null;
            return;
        }
        this.zzfm = Boolean.valueOf(z);
    }

    public final void setOnStateChangedListener(OnStateChangedListener onStateChangedListener) {
        this.zzfh.zza(onStateChangedListener);
    }

    public final int getState() {
        return this.zzfd != null ? this.zzfd.getState() : 0;
    }

    public final void onInflate(Activity activity, AttributeSet attributeSet, Bundle bundle) {
        super.onInflate(activity, attributeSet, bundle);
        if (this.zzfi == null) {
            this.zzfi = WalletFragmentOptions.zza((Context) activity, attributeSet);
        }
        Bundle bundle2 = new Bundle();
        bundle2.putParcelable("attrKeyWalletFragmentOptions", this.zzfi);
        this.zzfg.onInflate(activity, bundle2, bundle);
    }

    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            bundle.setClassLoader(WalletFragmentOptions.class.getClassLoader());
            WalletFragmentInitParams walletFragmentInitParams = (WalletFragmentInitParams) bundle.getParcelable("walletFragmentInitParams");
            if (walletFragmentInitParams != null) {
                if (this.zzfj != null) {
                    Log.w("SupportWalletFragment", "initialize(WalletFragmentInitParams) was called more than once.Ignoring.");
                }
                this.zzfj = walletFragmentInitParams;
            }
            if (this.zzfk == null) {
                this.zzfk = (MaskedWalletRequest) bundle.getParcelable("maskedWalletRequest");
            }
            if (this.zzfl == null) {
                this.zzfl = (MaskedWallet) bundle.getParcelable("maskedWallet");
            }
            if (bundle.containsKey("walletFragmentOptions")) {
                this.zzfi = (WalletFragmentOptions) bundle.getParcelable("walletFragmentOptions");
            }
            if (bundle.containsKey("enabled")) {
                this.zzfm = Boolean.valueOf(bundle.getBoolean("enabled"));
            }
        } else if (this.fragment.getArguments() != null) {
            WalletFragmentOptions walletFragmentOptions = (WalletFragmentOptions) this.fragment.getArguments().getParcelable("extraWalletFragmentOptions");
            if (walletFragmentOptions != null) {
                walletFragmentOptions.zza(this.fragment.getActivity());
                this.zzfi = walletFragmentOptions;
            }
        }
        this.zzfe = true;
        this.zzfg.onCreate(bundle);
    }

    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return this.zzfg.onCreateView(layoutInflater, viewGroup, bundle);
    }

    public final void onStart() {
        super.onStart();
        this.zzfg.onStart();
    }

    public final void onResume() {
        super.onResume();
        this.zzfg.onResume();
        FragmentManager supportFragmentManager = this.fragment.getActivity().getSupportFragmentManager();
        Fragment findFragmentByTag = supportFragmentManager.findFragmentByTag(GooglePlayServicesUtil.GMS_ERROR_DIALOG);
        if (findFragmentByTag != null) {
            supportFragmentManager.beginTransaction().remove(findFragmentByTag).commit();
            GooglePlayServicesUtil.showErrorDialogFragment(GooglePlayServicesUtil.isGooglePlayServicesAvailable(this.fragment.getActivity(), GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE), this.fragment.getActivity(), -1);
        }
    }

    public final void onPause() {
        super.onPause();
        this.zzfg.onPause();
    }

    public final void onStop() {
        super.onStop();
        this.zzfg.onStop();
    }

    public final void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.setClassLoader(WalletFragmentOptions.class.getClassLoader());
        this.zzfg.onSaveInstanceState(bundle);
        if (this.zzfj != null) {
            bundle.putParcelable("walletFragmentInitParams", this.zzfj);
            this.zzfj = null;
        }
        if (this.zzfk != null) {
            bundle.putParcelable("maskedWalletRequest", this.zzfk);
            this.zzfk = null;
        }
        if (this.zzfl != null) {
            bundle.putParcelable("maskedWallet", this.zzfl);
            this.zzfl = null;
        }
        if (this.zzfi != null) {
            bundle.putParcelable("walletFragmentOptions", this.zzfi);
            this.zzfi = null;
        }
        if (this.zzfm != null) {
            bundle.putBoolean("enabled", this.zzfm.booleanValue());
            this.zzfm = null;
        }
    }

    public final void onDestroy() {
        super.onDestroy();
        this.zzfe = false;
    }

    public final void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (this.zzfd != null) {
            this.zzfd.onActivityResult(i, i2, intent);
        }
    }
}
