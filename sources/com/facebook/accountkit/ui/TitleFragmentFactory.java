package com.facebook.accountkit.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.facebook.accountkit.R;

final class TitleFragmentFactory {

    public static class TitleFragment extends LoginFragment {
        private static final String TITLE_KEY = "title";
        private static final String TITLE_RESOURCE_ARGS_KEY = "titleResourceArgs";
        private static final String TITLE_RESOURCE_ID_KEY = "titleResourceId";
        protected TextView titleView;

        public /* bridge */ /* synthetic */ void onActivityCreated(Bundle bundle) {
            super.onActivityCreated(bundle);
        }

        public /* bridge */ /* synthetic */ void onCreate(Bundle bundle) {
            super.onCreate(bundle);
        }

        public /* bridge */ /* synthetic */ View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            return super.onCreateView(layoutInflater, viewGroup, bundle);
        }

        public /* bridge */ /* synthetic */ void onSaveInstanceState(Bundle bundle) {
            super.onSaveInstanceState(bundle);
        }

        private String getTitle() {
            return getViewState().getString("title");
        }

        public void setTitle(@Nullable String str) {
            getViewState().putString("title", str);
            updateTitleView();
        }

        @Nullable
        private String[] getTitleResourceArgs() {
            return getViewState().getStringArray(TITLE_RESOURCE_ARGS_KEY);
        }

        private int getTitleResourceId() {
            return getViewState().getInt(TITLE_RESOURCE_ID_KEY);
        }

        public void setTitleResourceId(int i, @Nullable String... strArr) {
            Bundle viewState = getViewState();
            viewState.putInt(TITLE_RESOURCE_ID_KEY, i);
            viewState.putStringArray(TITLE_RESOURCE_ARGS_KEY, strArr);
            updateTitleView();
        }

        /* Access modifiers changed, original: protected */
        public View createView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            return layoutInflater.inflate(R.layout.com_accountkit_fragment_title, viewGroup, false);
        }

        /* Access modifiers changed, original: protected */
        public void onViewReadyWithState(View view, Bundle bundle) {
            super.onViewReadyWithState(view, bundle);
            this.titleView = (TextView) view.findViewById(R.id.com_accountkit_title);
            updateTitleView();
        }

        /* JADX WARNING: Removed duplicated region for block: B:17:0x003d  */
        /* JADX WARNING: Removed duplicated region for block: B:16:0x0032  */
        private void updateTitleView() {
            /*
            r5 = this;
            r0 = r5.titleView;
            if (r0 != 0) goto L_0x0005;
        L_0x0004:
            return;
        L_0x0005:
            r0 = r5.getTitle();
            r1 = com.facebook.accountkit.internal.Utility.isNullOrEmpty(r0);
            r2 = 0;
            if (r1 == 0) goto L_0x002b;
        L_0x0010:
            r1 = r5.getTitleResourceId();
            r3 = r5.getTitleResourceArgs();
            if (r1 <= 0) goto L_0x002c;
        L_0x001a:
            if (r3 == 0) goto L_0x002c;
        L_0x001c:
            r4 = r3.length;
            if (r4 == 0) goto L_0x002c;
        L_0x001f:
            r4 = r5.getActivity();
            if (r4 == 0) goto L_0x002c;
        L_0x0025:
            r3 = (java.lang.Object[]) r3;
            r0 = r5.getString(r1, r3);
        L_0x002b:
            r1 = r2;
        L_0x002c:
            r3 = com.facebook.accountkit.internal.Utility.isNullOrEmpty(r0);
            if (r3 != 0) goto L_0x003d;
        L_0x0032:
            r1 = r5.titleView;
            r1.setText(r0);
            r0 = r5.titleView;
            r0.setVisibility(r2);
            goto L_0x0051;
        L_0x003d:
            if (r1 <= 0) goto L_0x004a;
        L_0x003f:
            r0 = r5.titleView;
            r0.setText(r1);
            r0 = r5.titleView;
            r0.setVisibility(r2);
            goto L_0x0051;
        L_0x004a:
            r0 = r5.titleView;
            r1 = 8;
            r0.setVisibility(r1);
        L_0x0051:
            return;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.accountkit.ui.TitleFragmentFactory$TitleFragment.updateTitleView():void");
        }
    }

    TitleFragmentFactory() {
    }

    public static TitleFragment create(@NonNull UIManager uIManager) {
        TitleFragment titleFragment = new TitleFragment();
        titleFragment.getViewState().putParcelable(ViewStateFragment.UI_MANAGER_KEY, uIManager);
        return titleFragment;
    }

    public static TitleFragment create(@NonNull UIManager uIManager, int i, @Nullable String... strArr) {
        TitleFragment create = create(uIManager);
        create.setTitleResourceId(i, strArr);
        return create;
    }
}
