package com.facebook.ads.internal.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.l.d;

public interface a {

    public interface a {
        void a(View view);

        void a(View view, int i);

        void a(String str);

        void a(String str, d dVar);

        void b(String str);
    }

    void a(Intent intent, Bundle bundle, AudienceNetworkActivity audienceNetworkActivity);

    void a(Bundle bundle);

    void a(boolean z);

    void b(boolean z);

    void onDestroy();

    void setListener(a aVar);
}
