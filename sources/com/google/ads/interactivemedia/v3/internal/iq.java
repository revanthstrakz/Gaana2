package com.google.ads.interactivemedia.v3.internal;

import android.view.View;
import android.view.ViewGroup;
import com.google.ads.interactivemedia.v3.api.BaseDisplayContainer;
import com.google.ads.interactivemedia.v3.api.CompanionAdSlot;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class iq implements BaseDisplayContainer {
    private static int f;
    private ViewGroup a;
    private Collection<CompanionAdSlot> b = Collections.emptyList();
    private Map<String, CompanionAdSlot> c = null;
    private final Set<View> d = new HashSet();
    private a e = null;

    public interface a {
        void a();

        void a(View view);
    }

    public ViewGroup getAdContainer() {
        return this.a;
    }

    public void setAdContainer(ViewGroup viewGroup) {
        this.a = viewGroup;
    }

    public Collection<CompanionAdSlot> getCompanionSlots() {
        return this.b;
    }

    public void setCompanionSlots(Collection<CompanionAdSlot> collection) {
        this.b = collection;
    }

    public Map<String, CompanionAdSlot> a() {
        if (this.c == null) {
            com.google.ads.interactivemedia.v3.internal.lb.a aVar = new com.google.ads.interactivemedia.v3.internal.lb.a();
            for (CompanionAdSlot companionAdSlot : this.b) {
                if (companionAdSlot != null) {
                    int i = f;
                    f = i + 1;
                    StringBuilder stringBuilder = new StringBuilder(20);
                    stringBuilder.append("compSlot_");
                    stringBuilder.append(i);
                    aVar.a(stringBuilder.toString(), companionAdSlot);
                }
            }
            this.c = aVar.a();
        }
        return this.c;
    }

    public void registerVideoControlsOverlay(View view) {
        if (view != null && !this.d.contains(view)) {
            this.d.add(view);
            if (this.e != null) {
                this.e.a(view);
            }
        }
    }

    public void unregisterAllVideoControlsOverlays() {
        this.d.clear();
        if (this.e != null) {
            this.e.a();
        }
    }

    public Set<View> b() {
        return new HashSet(this.d);
    }

    public void a(a aVar) {
        this.e = aVar;
    }
}
