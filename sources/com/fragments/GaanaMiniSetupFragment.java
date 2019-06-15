package com.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.b.i;
import com.gaana.BaseActivity;
import com.gaana.GaanaActivity;
import com.gaana.R;
import com.gaana.application.GaanaApplication;
import com.gaana.models.BusinessObject;
import com.gaana.models.OfflineTrack;
import com.gaana.models.Tracks;
import com.gaana.models.Tracks.Track;
import com.gaana.view.item.GaanaMiniListView;
import com.gaana.view.item.GaanaMiniListView.GaanaMiniChildViewHolder;
import com.gaana.view.item.GaanaMiniListView.GaanaMiniParentViewHolder;
import com.managers.DownloadManager;
import com.managers.URLManager;
import com.managers.aj;
import com.managers.u;
import com.moengage.ActionMapperConstants;
import com.services.l.af;
import com.til.colombia.android.internal.e;
import com.til.colombia.android.internal.h;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import kotlin.TypeCastException;

public final class GaanaMiniSetupFragment extends BaseGaanaFragment implements OnClickListener, a {
    public static final a a = new a();
    private static final int r = 1;
    private static final int s = 8;
    private final int b;
    private boolean c;
    private RecyclerView d;
    private ViewGroup e;
    private ArrayList<OfflineTrack> f = new ArrayList();
    private ArrayList<OfflineTrack> g = new ArrayList();
    private ArrayList<Track> h = new ArrayList();
    private ArrayList<OfflineTrack> i = new ArrayList();
    private ArrayList<OfflineTrack> j = new ArrayList();
    private ArrayList<Track> k = new ArrayList();
    private ArrayList<BusinessObject> l = new ArrayList();
    private int m;
    private b n;
    private GaanaMiniListView o;
    private String p = "";
    private Button q;
    private HashMap t;

    public static final class a {
        private a() {
        }

        public /* synthetic */ a(a aVar) {
            this();
        }
    }

    private final class b extends Adapter<ViewHolder> {
        private boolean b;
        private boolean c;

        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            kotlin.jvm.internal.c.b(viewGroup, "parent");
            Object inflate;
            if (i == GaanaMiniSetupFragment.r) {
                inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_setup_mini_header, viewGroup, false);
                kotlin.jvm.internal.c.a(inflate, "LayoutInflater.from(pare…ni_header, parent, false)");
                return new GaanaMiniParentViewHolder(inflate);
            } else if (i == GaanaMiniSetupFragment.s) {
                inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.view_setup_mini_, viewGroup, false);
                kotlin.jvm.internal.c.a(inflate, "LayoutInflater.from(pare…tup_mini_, parent, false)");
                return new GaanaMiniChildViewHolder(inflate);
            } else {
                inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_setup_mini_header, viewGroup, false);
                kotlin.jvm.internal.c.a(inflate, "LayoutInflater.from(pare…ni_header, parent, false)");
                return new GaanaMiniParentViewHolder(inflate);
            }
        }

        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            kotlin.jvm.internal.c.b(viewHolder, "holder");
            GaanaMiniListView a = GaanaMiniSetupFragment.this.o;
            if (a == null) {
                kotlin.jvm.internal.c.a();
            }
            this.c = a.getMostExpanded();
            a = GaanaMiniSetupFragment.this.o;
            if (a == null) {
                kotlin.jvm.internal.c.a();
            }
            this.b = a.getRecentExpanded();
            BusinessObject businessObject;
            ArrayList b;
            int size;
            ArrayList f;
            if (viewHolder instanceof GaanaMiniParentViewHolder) {
                int i2 = 0;
                Object listIterator;
                Iterator it;
                Object obj;
                if (!this.c || this.b) {
                    if (this.c || !this.b) {
                        if (this.c && this.b) {
                            if (i == 0) {
                                businessObject = new BusinessObject();
                                b = GaanaMiniSetupFragment.this.k;
                                if (b == null) {
                                    kotlin.jvm.internal.c.a();
                                }
                                listIterator = b.listIterator();
                                kotlin.jvm.internal.c.a(listIterator, "tempMostPlayed!!.listIterator()");
                                it = (Iterator) listIterator;
                                while (it.hasNext()) {
                                    obj = (Track) it.next();
                                    kotlin.jvm.internal.c.a(obj, "i");
                                    obj = obj.isSelected();
                                    kotlin.jvm.internal.c.a(obj, "i.isSelected");
                                    if (obj.booleanValue()) {
                                        i2++;
                                    }
                                }
                                businessObject.setCount(String.valueOf(i2));
                                businessObject.setEmptyMsg("most_played");
                                a = GaanaMiniSetupFragment.this.o;
                                if (a == null) {
                                    kotlin.jvm.internal.c.a();
                                }
                                a.getPoplatedView(viewHolder, businessObject);
                                return;
                            }
                            b = GaanaMiniSetupFragment.this.h;
                            if (b == null) {
                                kotlin.jvm.internal.c.a();
                            }
                            if (i == b.size() + 1) {
                                businessObject = new BusinessObject();
                                b = GaanaMiniSetupFragment.this.i;
                                if (b == null) {
                                    kotlin.jvm.internal.c.a();
                                }
                                listIterator = b.listIterator();
                                kotlin.jvm.internal.c.a(listIterator, "tempRecentlyPlayedTracks!!.listIterator()");
                                it = (Iterator) listIterator;
                                while (it.hasNext()) {
                                    obj = (OfflineTrack) it.next();
                                    kotlin.jvm.internal.c.a(obj, "i");
                                    if (obj.isSelected()) {
                                        i2++;
                                    }
                                }
                                businessObject.setCount(String.valueOf(i2));
                                businessObject.setEmptyMsg("recently_downloaded");
                                a = GaanaMiniSetupFragment.this.o;
                                if (a == null) {
                                    kotlin.jvm.internal.c.a();
                                }
                                a.getPoplatedView(viewHolder, businessObject);
                                return;
                            }
                            b = GaanaMiniSetupFragment.this.h;
                            if (b == null) {
                                kotlin.jvm.internal.c.a();
                            }
                            size = b.size();
                            f = GaanaMiniSetupFragment.this.f;
                            if (f == null) {
                                kotlin.jvm.internal.c.a();
                            }
                            if (i == (size + f.size()) + 2) {
                                businessObject = new BusinessObject();
                                b = GaanaMiniSetupFragment.this.g;
                                if (b == null) {
                                    kotlin.jvm.internal.c.a();
                                }
                                listIterator = b.listIterator();
                                kotlin.jvm.internal.c.a(listIterator, "allDownloadedTracks!!.listIterator()");
                                it = (Iterator) listIterator;
                                while (it.hasNext()) {
                                    obj = (OfflineTrack) it.next();
                                    kotlin.jvm.internal.c.a(obj, "i");
                                    if (obj.isSelected()) {
                                        i2++;
                                    }
                                }
                                businessObject.setCount(String.valueOf(i2));
                                businessObject.setEmptyMsg("all_downloads");
                                a = GaanaMiniSetupFragment.this.o;
                                if (a == null) {
                                    kotlin.jvm.internal.c.a();
                                }
                                a.getPoplatedView(viewHolder, businessObject);
                            }
                        } else if (i == 0) {
                            businessObject = new BusinessObject();
                            b = GaanaMiniSetupFragment.this.k;
                            if (b == null) {
                                kotlin.jvm.internal.c.a();
                            }
                            listIterator = b.listIterator();
                            kotlin.jvm.internal.c.a(listIterator, "tempMostPlayed!!.listIterator()");
                            it = (Iterator) listIterator;
                            while (it.hasNext()) {
                                obj = (Track) it.next();
                                kotlin.jvm.internal.c.a(obj, "i");
                                obj = obj.isSelected();
                                kotlin.jvm.internal.c.a(obj, "i.isSelected");
                                if (obj.booleanValue()) {
                                    i2++;
                                }
                            }
                            businessObject.setCount(String.valueOf(i2));
                            businessObject.setEmptyMsg("most_played");
                            a = GaanaMiniSetupFragment.this.o;
                            if (a == null) {
                                kotlin.jvm.internal.c.a();
                            }
                            a.getPoplatedView(viewHolder, businessObject);
                        } else if (i == 1) {
                            businessObject = new BusinessObject();
                            b = GaanaMiniSetupFragment.this.i;
                            if (b == null) {
                                kotlin.jvm.internal.c.a();
                            }
                            listIterator = b.listIterator();
                            kotlin.jvm.internal.c.a(listIterator, "tempRecentlyPlayedTracks!!.listIterator()");
                            it = (Iterator) listIterator;
                            while (it.hasNext()) {
                                obj = (OfflineTrack) it.next();
                                kotlin.jvm.internal.c.a(obj, "i");
                                if (obj.isSelected()) {
                                    i2++;
                                }
                            }
                            businessObject.setCount(String.valueOf(i2));
                            businessObject.setEmptyMsg("recently_downloaded");
                            a = GaanaMiniSetupFragment.this.o;
                            if (a == null) {
                                kotlin.jvm.internal.c.a();
                            }
                            a.getPoplatedView(viewHolder, businessObject);
                        } else if (i == 2) {
                            businessObject = new BusinessObject();
                            b = GaanaMiniSetupFragment.this.g;
                            if (b == null) {
                                kotlin.jvm.internal.c.a();
                            }
                            listIterator = b.listIterator();
                            kotlin.jvm.internal.c.a(listIterator, "allDownloadedTracks!!.listIterator()");
                            it = (Iterator) listIterator;
                            while (it.hasNext()) {
                                obj = (OfflineTrack) it.next();
                                kotlin.jvm.internal.c.a(obj, "i");
                                if (obj.isSelected()) {
                                    i2++;
                                }
                            }
                            businessObject.setCount(String.valueOf(i2));
                            businessObject.setEmptyMsg("all_downloads");
                            a = GaanaMiniSetupFragment.this.o;
                            if (a == null) {
                                kotlin.jvm.internal.c.a();
                            }
                            a.getPoplatedView(viewHolder, businessObject);
                        }
                    } else if (i == 0) {
                        businessObject = new BusinessObject();
                        b = GaanaMiniSetupFragment.this.k;
                        if (b == null) {
                            kotlin.jvm.internal.c.a();
                        }
                        listIterator = b.listIterator();
                        kotlin.jvm.internal.c.a(listIterator, "tempMostPlayed!!.listIterator()");
                        it = (Iterator) listIterator;
                        while (it.hasNext()) {
                            obj = (Track) it.next();
                            kotlin.jvm.internal.c.a(obj, "i");
                            obj = obj.isSelected();
                            kotlin.jvm.internal.c.a(obj, "i.isSelected");
                            if (obj.booleanValue()) {
                                i2++;
                            }
                        }
                        businessObject.setCount(String.valueOf(i2));
                        businessObject.setEmptyMsg("most_played");
                        a = GaanaMiniSetupFragment.this.o;
                        if (a == null) {
                            kotlin.jvm.internal.c.a();
                        }
                        a.getPoplatedView(viewHolder, businessObject);
                    } else if (i == 1) {
                        businessObject = new BusinessObject();
                        b = GaanaMiniSetupFragment.this.i;
                        if (b == null) {
                            kotlin.jvm.internal.c.a();
                        }
                        listIterator = b.listIterator();
                        kotlin.jvm.internal.c.a(listIterator, "tempRecentlyPlayedTracks!!.listIterator()");
                        it = (Iterator) listIterator;
                        while (it.hasNext()) {
                            obj = (OfflineTrack) it.next();
                            kotlin.jvm.internal.c.a(obj, "i");
                            if (obj.isSelected()) {
                                i2++;
                            }
                        }
                        businessObject.setCount(String.valueOf(i2));
                        businessObject.setEmptyMsg("recently_downloaded");
                        a = GaanaMiniSetupFragment.this.o;
                        if (a == null) {
                            kotlin.jvm.internal.c.a();
                        }
                        a.getPoplatedView(viewHolder, businessObject);
                    } else {
                        b = GaanaMiniSetupFragment.this.f;
                        if (b == null) {
                            kotlin.jvm.internal.c.a();
                        }
                        if (i == b.size() + 2) {
                            businessObject = new BusinessObject();
                            b = GaanaMiniSetupFragment.this.j;
                            if (b == null) {
                                kotlin.jvm.internal.c.a();
                            }
                            listIterator = b.listIterator();
                            kotlin.jvm.internal.c.a(listIterator, "tempAllDownloadedTracks!!.listIterator()");
                            it = (Iterator) listIterator;
                            while (it.hasNext()) {
                                obj = (OfflineTrack) it.next();
                                kotlin.jvm.internal.c.a(obj, "i");
                                if (obj.isSelected()) {
                                    i2++;
                                }
                            }
                            businessObject.setCount(String.valueOf(i2));
                            businessObject.setEmptyMsg("all_downloads");
                            a = GaanaMiniSetupFragment.this.o;
                            if (a == null) {
                                kotlin.jvm.internal.c.a();
                            }
                            a.getPoplatedView(viewHolder, businessObject);
                        }
                    }
                } else if (i == 0) {
                    businessObject = new BusinessObject();
                    b = GaanaMiniSetupFragment.this.k;
                    if (b == null) {
                        kotlin.jvm.internal.c.a();
                    }
                    listIterator = b.listIterator();
                    kotlin.jvm.internal.c.a(listIterator, "tempMostPlayed!!.listIterator()");
                    it = (Iterator) listIterator;
                    while (it.hasNext()) {
                        obj = (Track) it.next();
                        kotlin.jvm.internal.c.a(obj, "i");
                        obj = obj.isSelected();
                        kotlin.jvm.internal.c.a(obj, "i.isSelected");
                        if (obj.booleanValue()) {
                            i2++;
                        }
                    }
                    businessObject.setCount(String.valueOf(i2));
                    businessObject.setEmptyMsg("most_played");
                    a = GaanaMiniSetupFragment.this.o;
                    if (a == null) {
                        kotlin.jvm.internal.c.a();
                    }
                    a.getPoplatedView(viewHolder, businessObject);
                } else {
                    b = GaanaMiniSetupFragment.this.h;
                    if (b == null) {
                        kotlin.jvm.internal.c.a();
                    }
                    if (i == b.size() + 1) {
                        businessObject = new BusinessObject();
                        b = GaanaMiniSetupFragment.this.i;
                        if (b == null) {
                            kotlin.jvm.internal.c.a();
                        }
                        listIterator = b.listIterator();
                        kotlin.jvm.internal.c.a(listIterator, "tempRecentlyPlayedTracks!!.listIterator()");
                        it = (Iterator) listIterator;
                        while (it.hasNext()) {
                            obj = (OfflineTrack) it.next();
                            kotlin.jvm.internal.c.a(obj, "i");
                            if (obj.isSelected()) {
                                i2++;
                            }
                        }
                        businessObject.setCount(String.valueOf(i2));
                        businessObject.setEmptyMsg("recently_downloaded");
                        a = GaanaMiniSetupFragment.this.o;
                        if (a == null) {
                            kotlin.jvm.internal.c.a();
                        }
                        a.getPoplatedView(viewHolder, businessObject);
                        return;
                    }
                    b = GaanaMiniSetupFragment.this.h;
                    if (b == null) {
                        kotlin.jvm.internal.c.a();
                    }
                    if (i == b.size() + 2) {
                        businessObject = new BusinessObject();
                        b = GaanaMiniSetupFragment.this.g;
                        if (b == null) {
                            kotlin.jvm.internal.c.a();
                        }
                        listIterator = b.listIterator();
                        kotlin.jvm.internal.c.a(listIterator, "allDownloadedTracks!!.listIterator()");
                        it = (Iterator) listIterator;
                        while (it.hasNext()) {
                            obj = (OfflineTrack) it.next();
                            kotlin.jvm.internal.c.a(obj, "i");
                            if (obj.isSelected()) {
                                i2++;
                            }
                        }
                        businessObject.setCount(String.valueOf(i2));
                        businessObject.setEmptyMsg("all_downloads");
                        a = GaanaMiniSetupFragment.this.o;
                        if (a == null) {
                            kotlin.jvm.internal.c.a();
                        }
                        a.getPoplatedView(viewHolder, businessObject);
                    }
                }
            } else if (!(viewHolder instanceof GaanaMiniChildViewHolder)) {
            } else {
                Object obj2;
                ArrayList f2;
                if (!this.c && this.b) {
                    if (i > 1) {
                        b = GaanaMiniSetupFragment.this.f;
                        if (b == null) {
                            kotlin.jvm.internal.c.a();
                        }
                        if (i < b.size() + 2) {
                            b = GaanaMiniSetupFragment.this.f;
                            if (b == null) {
                                kotlin.jvm.internal.c.a();
                            }
                            obj2 = b.get(i - 2);
                            kotlin.jvm.internal.c.a(obj2, "recentlyPlayedTracks!!.get(position - 2)");
                            businessObject = (BusinessObject) obj2;
                            ((OfflineTrack) businessObject).setEmptyMsg("recently_downloaded");
                            a = GaanaMiniSetupFragment.this.o;
                            if (a == null) {
                                kotlin.jvm.internal.c.a();
                            }
                            a.getPoplatedView(viewHolder, businessObject);
                            return;
                        }
                    }
                    b = GaanaMiniSetupFragment.this.f;
                    if (b == null) {
                        kotlin.jvm.internal.c.a();
                    }
                    if (i > b.size() + 2) {
                        b = GaanaMiniSetupFragment.this.g;
                        if (b == null) {
                            kotlin.jvm.internal.c.a();
                        }
                        f2 = GaanaMiniSetupFragment.this.f;
                        if (f2 == null) {
                            kotlin.jvm.internal.c.a();
                        }
                        obj2 = b.get((i - f2.size()) - 3);
                        kotlin.jvm.internal.c.a(obj2, "allDownloadedTracks!!.ge…yPlayedTracks!!.size - 3)");
                        businessObject = (BusinessObject) obj2;
                        ((OfflineTrack) businessObject).setEmptyMsg("all_downloads");
                        a = GaanaMiniSetupFragment.this.o;
                        if (a == null) {
                            kotlin.jvm.internal.c.a();
                        }
                        a.getPoplatedView(viewHolder, businessObject);
                    }
                } else if (this.c && !this.b) {
                    if (i > 0) {
                        b = GaanaMiniSetupFragment.this.h;
                        if (b == null) {
                            kotlin.jvm.internal.c.a();
                        }
                        if (i < b.size() + 1) {
                            b = GaanaMiniSetupFragment.this.h;
                            if (b == null) {
                                kotlin.jvm.internal.c.a();
                            }
                            obj2 = b.get(i - 1);
                            kotlin.jvm.internal.c.a(obj2, "mostPlayed!!.get(position - 1)");
                            businessObject = (BusinessObject) obj2;
                            ((Track) businessObject).setEmptyMsg("most_played");
                            a = GaanaMiniSetupFragment.this.o;
                            if (a == null) {
                                kotlin.jvm.internal.c.a();
                            }
                            a.getPoplatedView(viewHolder, businessObject);
                            return;
                        }
                    }
                    b = GaanaMiniSetupFragment.this.h;
                    if (b == null) {
                        kotlin.jvm.internal.c.a();
                    }
                    if (i > b.size() + 2) {
                        b = GaanaMiniSetupFragment.this.g;
                        if (b == null) {
                            kotlin.jvm.internal.c.a();
                        }
                        f2 = GaanaMiniSetupFragment.this.h;
                        if (f2 == null) {
                            kotlin.jvm.internal.c.a();
                        }
                        obj2 = b.get((i - f2.size()) - 3);
                        kotlin.jvm.internal.c.a(obj2, "allDownloadedTracks!!.ge… - mostPlayed!!.size - 3)");
                        businessObject = (BusinessObject) obj2;
                        ((OfflineTrack) businessObject).setEmptyMsg("all_downloads");
                        a = GaanaMiniSetupFragment.this.o;
                        if (a == null) {
                            kotlin.jvm.internal.c.a();
                        }
                        a.getPoplatedView(viewHolder, businessObject);
                    }
                } else if (this.c && this.b) {
                    if (i > 0) {
                        b = GaanaMiniSetupFragment.this.h;
                        if (b == null) {
                            kotlin.jvm.internal.c.a();
                        }
                        if (i < b.size() + 1) {
                            b = GaanaMiniSetupFragment.this.h;
                            if (b == null) {
                                kotlin.jvm.internal.c.a();
                            }
                            obj2 = b.get(i - 1);
                            kotlin.jvm.internal.c.a(obj2, "mostPlayed!!.get(position - 1)");
                            businessObject = (BusinessObject) obj2;
                            ((Track) businessObject).setEmptyMsg("most_played");
                            a = GaanaMiniSetupFragment.this.o;
                            if (a == null) {
                                kotlin.jvm.internal.c.a();
                            }
                            a.getPoplatedView(viewHolder, businessObject);
                            return;
                        }
                    }
                    b = GaanaMiniSetupFragment.this.h;
                    if (b == null) {
                        kotlin.jvm.internal.c.a();
                    }
                    if (i > b.size() + 1) {
                        b = GaanaMiniSetupFragment.this.h;
                        if (b == null) {
                            kotlin.jvm.internal.c.a();
                        }
                        size = b.size();
                        f = GaanaMiniSetupFragment.this.f;
                        if (f == null) {
                            kotlin.jvm.internal.c.a();
                        }
                        if (i < (size + f.size()) + 2) {
                            b = GaanaMiniSetupFragment.this.f;
                            if (b == null) {
                                kotlin.jvm.internal.c.a();
                            }
                            f = GaanaMiniSetupFragment.this.h;
                            if (f == null) {
                                kotlin.jvm.internal.c.a();
                            }
                            obj2 = b.get((i - f.size()) - 2);
                            kotlin.jvm.internal.c.a(obj2, "recentlyPlayedTracks!!.g… - mostPlayed!!.size - 2)");
                            businessObject = (BusinessObject) obj2;
                            ((OfflineTrack) businessObject).setEmptyMsg("recently_downloaded");
                            a = GaanaMiniSetupFragment.this.o;
                            if (a == null) {
                                kotlin.jvm.internal.c.a();
                            }
                            a.getPoplatedView(viewHolder, businessObject);
                            return;
                        }
                    }
                    b = GaanaMiniSetupFragment.this.h;
                    if (b == null) {
                        kotlin.jvm.internal.c.a();
                    }
                    size = b.size();
                    f = GaanaMiniSetupFragment.this.f;
                    if (f == null) {
                        kotlin.jvm.internal.c.a();
                    }
                    if (i > (size + f.size()) + 2) {
                        b = GaanaMiniSetupFragment.this.g;
                        if (b == null) {
                            kotlin.jvm.internal.c.a();
                        }
                        f2 = GaanaMiniSetupFragment.this.h;
                        if (f2 == null) {
                            kotlin.jvm.internal.c.a();
                        }
                        i -= f2.size();
                        f2 = GaanaMiniSetupFragment.this.f;
                        if (f2 == null) {
                            kotlin.jvm.internal.c.a();
                        }
                        obj2 = b.get((i - f2.size()) - 3);
                        kotlin.jvm.internal.c.a(obj2, "allDownloadedTracks!!.ge…yPlayedTracks!!.size - 3)");
                        businessObject = (BusinessObject) obj2;
                        ((OfflineTrack) businessObject).setEmptyMsg("all_downloads");
                        a = GaanaMiniSetupFragment.this.o;
                        if (a == null) {
                            kotlin.jvm.internal.c.a();
                        }
                        a.getPoplatedView(viewHolder, businessObject);
                    }
                } else if (i > 2) {
                    b = GaanaMiniSetupFragment.this.g;
                    if (b == null) {
                        kotlin.jvm.internal.c.a();
                    }
                    obj2 = b.get(i - 3);
                    kotlin.jvm.internal.c.a(obj2, "allDownloadedTracks!!.get(position - 3)");
                    businessObject = (BusinessObject) obj2;
                    ((OfflineTrack) businessObject).setEmptyMsg("all_downloads");
                    a = GaanaMiniSetupFragment.this.o;
                    if (a == null) {
                        kotlin.jvm.internal.c.a();
                    }
                    a.getPoplatedView(viewHolder, businessObject);
                }
            }
        }

        public int getItemCount() {
            GaanaMiniListView a = GaanaMiniSetupFragment.this.o;
            if (a == null) {
                kotlin.jvm.internal.c.a();
            }
            this.c = a.getMostExpanded();
            a = GaanaMiniSetupFragment.this.o;
            if (a == null) {
                kotlin.jvm.internal.c.a();
            }
            this.b = a.getRecentExpanded();
            ArrayList c;
            int size;
            ArrayList e;
            if (this.c && !this.b) {
                c = GaanaMiniSetupFragment.this.h;
                if (c == null) {
                    kotlin.jvm.internal.c.a();
                }
                size = c.size();
                e = GaanaMiniSetupFragment.this.g;
                if (e == null) {
                    kotlin.jvm.internal.c.a();
                }
                return (size + e.size()) + 3;
            } else if (!this.c && this.b) {
                c = GaanaMiniSetupFragment.this.f;
                if (c == null) {
                    kotlin.jvm.internal.c.a();
                }
                size = c.size();
                e = GaanaMiniSetupFragment.this.g;
                if (e == null) {
                    kotlin.jvm.internal.c.a();
                }
                return (size + e.size()) + 3;
            } else if (this.c && this.b) {
                c = GaanaMiniSetupFragment.this.f;
                if (c == null) {
                    kotlin.jvm.internal.c.a();
                }
                size = c.size();
                e = GaanaMiniSetupFragment.this.h;
                if (e == null) {
                    kotlin.jvm.internal.c.a();
                }
                size += e.size();
                e = GaanaMiniSetupFragment.this.g;
                if (e == null) {
                    kotlin.jvm.internal.c.a();
                }
                return (size + e.size()) + 3;
            } else {
                c = GaanaMiniSetupFragment.this.g;
                if (c == null) {
                    kotlin.jvm.internal.c.a();
                }
                return c.size() + 3;
            }
        }

        public int getItemViewType(int i) {
            GaanaMiniListView a = GaanaMiniSetupFragment.this.o;
            if (a == null) {
                kotlin.jvm.internal.c.a();
            }
            this.c = a.getMostExpanded();
            a = GaanaMiniSetupFragment.this.o;
            if (a == null) {
                kotlin.jvm.internal.c.a();
            }
            this.b = a.getRecentExpanded();
            ArrayList c;
            if (this.c && !this.b) {
                if (i != 0) {
                    c = GaanaMiniSetupFragment.this.h;
                    if (c == null) {
                        kotlin.jvm.internal.c.a();
                    }
                    if (i != c.size() + 1) {
                        c = GaanaMiniSetupFragment.this.h;
                        if (c == null) {
                            kotlin.jvm.internal.c.a();
                        }
                        if (i != c.size() + 2) {
                            return GaanaMiniSetupFragment.s;
                        }
                    }
                }
                return GaanaMiniSetupFragment.r;
            } else if (this.b && !this.c) {
                if (!(i == 0 || i == 1)) {
                    c = GaanaMiniSetupFragment.this.f;
                    if (c == null) {
                        kotlin.jvm.internal.c.a();
                    }
                    if (i != c.size() + 2) {
                        return GaanaMiniSetupFragment.s;
                    }
                }
                return GaanaMiniSetupFragment.r;
            } else if (this.b && this.c) {
                if (i != 0) {
                    c = GaanaMiniSetupFragment.this.h;
                    if (c == null) {
                        kotlin.jvm.internal.c.a();
                    }
                    if (i != c.size() + 1) {
                        c = GaanaMiniSetupFragment.this.h;
                        if (c == null) {
                            kotlin.jvm.internal.c.a();
                        }
                        int size = c.size();
                        ArrayList f = GaanaMiniSetupFragment.this.f;
                        if (f == null) {
                            kotlin.jvm.internal.c.a();
                        }
                        if (i != (size + f.size()) + 2) {
                            return GaanaMiniSetupFragment.s;
                        }
                    }
                }
                return GaanaMiniSetupFragment.r;
            } else if (i == 0 || i == 1 || i == 2) {
                return GaanaMiniSetupFragment.r;
            } else {
                return GaanaMiniSetupFragment.s;
            }
        }
    }

    public static final class c implements af {
        final /* synthetic */ GaanaMiniSetupFragment a;

        public void onErrorResponse(BusinessObject businessObject) {
            kotlin.jvm.internal.c.b(businessObject, "businessObject");
        }

        c(GaanaMiniSetupFragment gaanaMiniSetupFragment) {
            this.a = gaanaMiniSetupFragment;
        }

        public void onRetreivalComplete(Object obj) {
            kotlin.jvm.internal.c.b(obj, "businessObj");
            Context context = this.a.mContext;
            if (context == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.gaana.BaseActivity");
            }
            Iterator it;
            Object obj2;
            ArrayList c;
            ArrayList b;
            ((BaseActivity) context).hideProgressDialog();
            Tracks tracks = (Tracks) obj;
            if (tracks.getArrListBusinessObj() != null) {
                it = tracks.getArrListBusinessObj().iterator();
                while (it.hasNext()) {
                    obj2 = (Track) it.next();
                    ArrayList c2 = this.a.h;
                    if (c2 == null) {
                        kotlin.jvm.internal.c.a();
                    }
                    if (c2.size() < 20) {
                        DownloadManager c3 = DownloadManager.c();
                        kotlin.jvm.internal.c.a(obj2, "tr");
                        if (c3.h(obj2.getBusinessObjId()) != null) {
                            obj2.setIsSelected(Boolean.valueOf(true));
                            c2 = this.a.h;
                            if (c2 == null) {
                                kotlin.jvm.internal.c.a();
                            }
                            c2.add(DownloadManager.c().h(obj2.getBusinessObjId()));
                        }
                    }
                }
                c = this.a.h;
                if (c != null) {
                    b = this.a.k;
                    if (b == null) {
                        kotlin.jvm.internal.c.a();
                    }
                    b.addAll(c);
                }
            }
            obj = DownloadManager.c().a("", true, false, 2, -1);
            kotlin.jvm.internal.c.a(obj, "DownloadManager.getInsta…Y.DOWNLOAD_TIME_DESC, -1)");
            c = obj.getArrListBusinessObj();
            b = this.a.h;
            if (b == null) {
                kotlin.jvm.internal.c.a();
            }
            ArrayList c4;
            if (b.size() > 0) {
                if (c == null) {
                    kotlin.jvm.internal.c.a();
                }
                obj = c.listIterator();
                kotlin.jvm.internal.c.a(obj, "allPlayedTracks!!.listIterator()");
                it = (Iterator) obj;
                while (it.hasNext()) {
                    boolean z;
                    obj2 = (OfflineTrack) it.next();
                    c4 = this.a.h;
                    if (c4 == null) {
                        kotlin.jvm.internal.c.a();
                    }
                    Object listIterator = c4.listIterator();
                    kotlin.jvm.internal.c.a(listIterator, "mostPlayed!!.listIterator()");
                    Iterator it2 = (Iterator) listIterator;
                    while (it2.hasNext()) {
                        Object obj3 = (Track) it2.next();
                        kotlin.jvm.internal.c.a(obj2, h.l);
                        String businessObjId = obj2.getBusinessObjId();
                        kotlin.jvm.internal.c.a(obj3, "most");
                        if (businessObjId.equals(obj3.getBusinessObjId())) {
                            z = true;
                            break;
                        }
                    }
                    z = false;
                    if (!z) {
                        c4 = this.a.f;
                        if (c4 == null) {
                            kotlin.jvm.internal.c.a();
                        }
                        if (c4.size() < 20) {
                            c4 = this.a.f;
                            if (c4 == null) {
                                kotlin.jvm.internal.c.a();
                            }
                            c4.add(obj2);
                        } else {
                            c4 = this.a.g;
                            if (c4 == null) {
                                kotlin.jvm.internal.c.a();
                            }
                            c4.add(obj2);
                        }
                    }
                }
            } else {
                if (c == null) {
                    kotlin.jvm.internal.c.a();
                }
                obj = c.listIterator();
                kotlin.jvm.internal.c.a(obj, "allPlayedTracks!!.listIterator()");
                it = (Iterator) obj;
                while (it.hasNext()) {
                    OfflineTrack offlineTrack = (OfflineTrack) it.next();
                    c4 = this.a.f;
                    if (c4 == null) {
                        kotlin.jvm.internal.c.a();
                    }
                    if (c4.size() < 20) {
                        c4 = this.a.f;
                        if (c4 == null) {
                            kotlin.jvm.internal.c.a();
                        }
                        c4.add(offlineTrack);
                    } else {
                        c4 = this.a.g;
                        if (c4 == null) {
                            kotlin.jvm.internal.c.a();
                        }
                        c4.add(offlineTrack);
                    }
                }
            }
            c = this.a.f;
            if (c == null) {
                kotlin.jvm.internal.c.a();
            }
            obj = c.iterator();
            kotlin.jvm.internal.c.a(obj, "recentlyPlayedTracks!!.iterator()");
            while (obj.hasNext()) {
                obj2 = (OfflineTrack) obj.next();
                kotlin.jvm.internal.c.a(obj2, e.o);
                obj2.setSelected(true);
            }
            c = this.a.f;
            if (c != null) {
                b = this.a.i;
                if (b == null) {
                    kotlin.jvm.internal.c.a();
                }
                b.addAll(c);
            }
            c = this.a.g;
            if (c == null) {
                kotlin.jvm.internal.c.a();
            }
            obj = c.iterator();
            kotlin.jvm.internal.c.a(obj, "allDownloadedTracks!!.iterator()");
            while (obj.hasNext()) {
                obj2 = (OfflineTrack) obj.next();
                kotlin.jvm.internal.c.a(obj2, e.o);
                obj2.setSelected(false);
            }
            c = this.a.g;
            if (c != null) {
                b = this.a.j;
                if (b == null) {
                    kotlin.jvm.internal.c.a();
                }
                b.addAll(c);
            }
            this.a.n = new b();
            this.a.l = new ArrayList();
            c = this.a.h;
            if (c != null) {
                b = this.a.l;
                if (b == null) {
                    kotlin.jvm.internal.c.a();
                }
                b.addAll(c);
            }
            c = this.a.f;
            if (c != null) {
                b = this.a.l;
                if (b == null) {
                    kotlin.jvm.internal.c.a();
                }
                b.addAll(c);
            }
            c = this.a.g;
            if (c != null) {
                b = this.a.l;
                if (b == null) {
                    kotlin.jvm.internal.c.a();
                }
                b.addAll(c);
            }
            RecyclerView j = this.a.d;
            if (j == null) {
                kotlin.jvm.internal.c.a();
            }
            j.setLayoutManager(new LinearLayoutManager(this.a.mContext));
            j = this.a.d;
            if (j == null) {
                kotlin.jvm.internal.c.a();
            }
            j.setAdapter(this.a.n);
            GaanaMiniSetupFragment gaanaMiniSetupFragment = this.a;
            b = this.a.j;
            if (b == null) {
                kotlin.jvm.internal.c.a();
            }
            int size = b.size();
            ArrayList d = this.a.i;
            if (d == null) {
                kotlin.jvm.internal.c.a();
            }
            size += d.size();
            d = this.a.k;
            if (d == null) {
                kotlin.jvm.internal.c.a();
            }
            gaanaMiniSetupFragment.a(size + d.size());
            Button k = GaanaMiniSetupFragment.k(this.a);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Keep ");
            int a = this.a.a();
            ArrayList g = this.a.j;
            if (g == null) {
                kotlin.jvm.internal.c.a();
            }
            stringBuilder.append(a - g.size());
            stringBuilder.append("/");
            stringBuilder.append(this.a.a());
            stringBuilder.append(" Downloads");
            k.setText(stringBuilder.toString());
        }
    }

    public void d() {
        if (this.t != null) {
            this.t.clear();
        }
    }

    public static final /* synthetic */ Button k(GaanaMiniSetupFragment gaanaMiniSetupFragment) {
        Button button = gaanaMiniSetupFragment.q;
        if (button == null) {
            kotlin.jvm.internal.c.b("keepDownloads");
        }
        return button;
    }

    public final int a() {
        return this.m;
    }

    public final void a(int i) {
        this.m = i;
    }

    public void onSaveInstanceState(Bundle bundle) {
        kotlin.jvm.internal.c.b(bundle, "outState");
        super.onSaveInstanceState(bundle);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View contentView;
        ViewGroup viewGroup2;
        kotlin.jvm.internal.c.b(layoutInflater, "inflater");
        if (this.e == null) {
            super.onCreateView(layoutInflater, viewGroup, bundle);
            contentView = setContentView(R.layout.fragment_setup_mini, viewGroup);
            if (contentView == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup");
            }
            this.e = (ViewGroup) contentView;
            viewGroup2 = this.e;
            if (viewGroup2 == null) {
                kotlin.jvm.internal.c.a();
            }
            contentView = viewGroup2.findViewById(R.id.txt_header);
            if (contentView == null) {
                throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
            }
            TextView textView = (TextView) contentView;
            Object obj = this.mContext;
            kotlin.jvm.internal.c.a(obj, "mContext");
            textView.setTypeface(i.a(obj.getAssets(), "fonts/Roboto-Medium.ttf"));
            viewGroup2 = this.e;
            if (viewGroup2 == null) {
                kotlin.jvm.internal.c.a();
            }
            this.d = (RecyclerView) viewGroup2.findViewById(R.id.recycler_view);
            viewGroup2 = this.e;
            if (viewGroup2 == null) {
                kotlin.jvm.internal.c.a();
            }
            Object findViewById = viewGroup2.findViewById(R.id.keep_downloads);
            kotlin.jvm.internal.c.a(findViewById, "containerViewSub!!.findV…ById(R.id.keep_downloads)");
            this.q = (Button) findViewById;
            Button button = this.q;
            if (button == null) {
                kotlin.jvm.internal.c.b("keepDownloads");
            }
            button.setOnClickListener(this);
            e();
            obj = this.mContext;
            kotlin.jvm.internal.c.a(obj, "mContext");
            this.o = new GaanaMiniListView(obj, this);
        }
        viewGroup2 = this.e;
        if (viewGroup2 == null) {
            kotlin.jvm.internal.c.a();
        }
        contentView = viewGroup2.findViewById(R.id.btnLeft);
        if (contentView == null) {
            throw new TypeCastException("null cannot be cast to non-null type android.widget.ImageView");
        }
        ((ImageView) contentView).setOnClickListener(this);
        updateView();
        return this.e;
    }

    public void onResume() {
        super.onResume();
    }

    public final void a(int i, boolean z) {
        ArrayList arrayList;
        Object obj;
        ArrayList arrayList2;
        ArrayList arrayList3;
        Object it;
        if (i == 0) {
            arrayList2 = this.l;
            if (arrayList2 == null) {
                kotlin.jvm.internal.c.a();
            }
            arrayList3 = this.k;
            if (arrayList3 == null) {
                kotlin.jvm.internal.c.a();
            }
            arrayList2.removeAll(arrayList3);
            arrayList2 = this.k;
            if (arrayList2 == null) {
                kotlin.jvm.internal.c.a();
            }
            it = arrayList2.iterator();
            kotlin.jvm.internal.c.a(it, "tempMostPlayed!!.iterator()");
            while (it.hasNext()) {
                ((Track) it.next()).setIsSelected(Boolean.valueOf(z));
            }
            if (this == null) {
                kotlin.jvm.internal.c.a();
            }
            arrayList2 = this.k;
            if (arrayList2 != null) {
                arrayList = this.l;
                if (arrayList == null) {
                    kotlin.jvm.internal.c.a();
                }
                arrayList.addAll(arrayList2);
            }
        } else if (i == 1) {
            arrayList2 = this.l;
            if (arrayList2 == null) {
                kotlin.jvm.internal.c.a();
            }
            arrayList3 = this.i;
            if (arrayList3 == null) {
                kotlin.jvm.internal.c.a();
            }
            arrayList2.removeAll(arrayList3);
            arrayList2 = this.i;
            if (arrayList2 == null) {
                kotlin.jvm.internal.c.a();
            }
            it = arrayList2.iterator();
            kotlin.jvm.internal.c.a(it, "tempRecentlyPlayedTracks!!.iterator()");
            while (it.hasNext()) {
                obj = (OfflineTrack) it.next();
                kotlin.jvm.internal.c.a(obj, e.o);
                obj.setSelected(z);
            }
            if (this == null) {
                kotlin.jvm.internal.c.a();
            }
            arrayList2 = this.i;
            if (arrayList2 != null) {
                arrayList = this.l;
                if (arrayList == null) {
                    kotlin.jvm.internal.c.a();
                }
                arrayList.addAll(arrayList2);
            }
        }
        i = 0;
        arrayList = this.l;
        if (arrayList == null) {
            kotlin.jvm.internal.c.a();
        }
        Object listIterator = arrayList.listIterator();
        kotlin.jvm.internal.c.a(listIterator, "keepAllList!!.listIterator()");
        Iterator it2 = (Iterator) listIterator;
        while (it2.hasNext()) {
            BusinessObject businessObject = (BusinessObject) it2.next();
            if (businessObject instanceof OfflineTrack) {
                if (((OfflineTrack) businessObject).isSelected()) {
                    i++;
                }
            } else if (businessObject == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.gaana.models.Tracks.Track");
            } else {
                obj = ((Track) businessObject).isSelected();
                kotlin.jvm.internal.c.a(obj, "(i as Tracks.Track).isSelected");
                if (obj.booleanValue()) {
                    i++;
                }
            }
        }
        RecyclerView recyclerView = this.d;
        if (recyclerView == null) {
            kotlin.jvm.internal.c.a();
        }
        if (!recyclerView.isComputingLayout()) {
            b bVar = this.n;
            if (bVar == null) {
                kotlin.jvm.internal.c.a();
            }
            bVar.notifyDataSetChanged();
        }
        Button button = this.q;
        if (button == null) {
            kotlin.jvm.internal.c.b("keepDownloads");
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Keep ");
        stringBuilder.append(i);
        stringBuilder.append("/");
        stringBuilder.append(this.m);
        stringBuilder.append(" Downloads");
        button.setText(stringBuilder.toString());
    }

    public final void a(Track track, boolean z, int i) {
        ArrayList arrayList;
        Object listIterator;
        OfflineTrack offlineTrack;
        kotlin.jvm.internal.c.b(track, ActionMapperConstants.KEY_TRACK);
        Iterator it;
        Object obj;
        String businessObjId;
        if (i != 0) {
            if (i != 1) {
                arrayList = this.j;
                if (arrayList == null) {
                    kotlin.jvm.internal.c.a();
                }
                listIterator = arrayList.listIterator();
                kotlin.jvm.internal.c.a(listIterator, "tempAllDownloadedTracks!!.listIterator()");
                it = (Iterator) listIterator;
                while (it.hasNext()) {
                    obj = (OfflineTrack) it.next();
                    businessObjId = track.getBusinessObjId();
                    kotlin.jvm.internal.c.a(obj, "t");
                    if (businessObjId.equals(obj.getBusinessObjId())) {
                        obj.setSelected(z);
                        break;
                    }
                }
            }
            arrayList = this.i;
            if (arrayList == null) {
                kotlin.jvm.internal.c.a();
            }
            listIterator = arrayList.listIterator();
            kotlin.jvm.internal.c.a(listIterator, "tempRecentlyPlayedTracks!!.listIterator()");
            it = (Iterator) listIterator;
            while (it.hasNext()) {
                obj = (OfflineTrack) it.next();
                businessObjId = track.getBusinessObjId();
                kotlin.jvm.internal.c.a(obj, "t");
                if (businessObjId.equals(obj.getBusinessObjId())) {
                    obj.setSelected(z);
                    break;
                }
            }
        }
        arrayList = this.k;
        if (arrayList == null) {
            kotlin.jvm.internal.c.a();
        }
        listIterator = arrayList.listIterator();
        kotlin.jvm.internal.c.a(listIterator, "tempMostPlayed!!.listIterator()");
        it = (Iterator) listIterator;
        while (it.hasNext()) {
            obj = (Track) it.next();
            businessObjId = track.getBusinessObjId();
            kotlin.jvm.internal.c.a(obj, "t");
            if (businessObjId.equals(obj.getBusinessObjId())) {
                obj.setIsSelected(Boolean.valueOf(z));
                break;
            }
        }
        ArrayList arrayList2 = this.l;
        if (arrayList2 == null) {
            kotlin.jvm.internal.c.a();
        }
        arrayList2.clear();
        arrayList2 = this.k;
        if (arrayList2 == null) {
            kotlin.jvm.internal.c.a();
        }
        Object listIterator2 = arrayList2.listIterator();
        kotlin.jvm.internal.c.a(listIterator2, "tempMostPlayed!!.listIterator()");
        Iterator it2 = (Iterator) listIterator2;
        while (it2.hasNext()) {
            Track track2 = (Track) it2.next();
            arrayList = this.l;
            if (arrayList == null) {
                kotlin.jvm.internal.c.a();
            }
            arrayList.add(track2);
        }
        arrayList2 = this.i;
        if (arrayList2 == null) {
            kotlin.jvm.internal.c.a();
        }
        listIterator2 = arrayList2.listIterator();
        kotlin.jvm.internal.c.a(listIterator2, "tempRecentlyPlayedTracks!!.listIterator()");
        it2 = (Iterator) listIterator2;
        while (it2.hasNext()) {
            offlineTrack = (OfflineTrack) it2.next();
            arrayList = this.l;
            if (arrayList == null) {
                kotlin.jvm.internal.c.a();
            }
            arrayList.add(offlineTrack);
        }
        arrayList2 = this.j;
        if (arrayList2 == null) {
            kotlin.jvm.internal.c.a();
        }
        listIterator2 = arrayList2.listIterator();
        kotlin.jvm.internal.c.a(listIterator2, "tempAllDownloadedTracks!!.listIterator()");
        it2 = (Iterator) listIterator2;
        while (it2.hasNext()) {
            offlineTrack = (OfflineTrack) it2.next();
            arrayList = this.l;
            if (arrayList == null) {
                kotlin.jvm.internal.c.a();
            }
            arrayList.add(offlineTrack);
        }
        int i2 = 0;
        ArrayList arrayList3 = this.l;
        if (arrayList3 == null) {
            kotlin.jvm.internal.c.a();
        }
        Object listIterator3 = arrayList3.listIterator();
        kotlin.jvm.internal.c.a(listIterator3, "keepAllList!!.listIterator()");
        Iterator it3 = (Iterator) listIterator3;
        while (it3.hasNext()) {
            BusinessObject businessObject = (BusinessObject) it3.next();
            if (businessObject instanceof OfflineTrack) {
                if (((OfflineTrack) businessObject).isSelected()) {
                    i2++;
                }
            } else if (businessObject == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.gaana.models.Tracks.Track");
            } else {
                listIterator = ((Track) businessObject).isSelected();
                kotlin.jvm.internal.c.a(listIterator, "(i as Tracks.Track).isSelected");
                if (listIterator.booleanValue()) {
                    i2++;
                }
            }
        }
        RecyclerView recyclerView = this.d;
        if (recyclerView == null) {
            kotlin.jvm.internal.c.a();
        }
        if (!recyclerView.isComputingLayout()) {
            b bVar = this.n;
            if (bVar == null) {
                kotlin.jvm.internal.c.a();
            }
            bVar.notifyDataSetChanged();
        }
        Button button = this.q;
        if (button == null) {
            kotlin.jvm.internal.c.b("keepDownloads");
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Keep ");
        stringBuilder.append(i2);
        stringBuilder.append("/");
        stringBuilder.append(this.m);
        stringBuilder.append(" Downloads");
        button.setText(stringBuilder.toString());
    }

    public final void b(int i) {
        GaanaMiniListView gaanaMiniListView;
        ArrayList arrayList;
        ArrayList arrayList2;
        if (i == 0) {
            gaanaMiniListView = this.o;
            if (gaanaMiniListView == null) {
                kotlin.jvm.internal.c.a();
            }
            if (gaanaMiniListView.getMostExpanded()) {
                arrayList = this.h;
                if (arrayList == null) {
                    kotlin.jvm.internal.c.a();
                }
                if (arrayList.size() == 0) {
                    arrayList = this.k;
                    if (arrayList != null) {
                        arrayList2 = this.h;
                        if (arrayList2 == null) {
                            kotlin.jvm.internal.c.a();
                        }
                        arrayList2.addAll(arrayList);
                    }
                }
            } else {
                arrayList = this.h;
                if (arrayList == null) {
                    kotlin.jvm.internal.c.a();
                }
                arrayList.clear();
            }
        } else if (i == 1) {
            gaanaMiniListView = this.o;
            if (gaanaMiniListView == null) {
                kotlin.jvm.internal.c.a();
            }
            if (gaanaMiniListView.getRecentExpanded()) {
                arrayList = this.f;
                if (arrayList == null) {
                    kotlin.jvm.internal.c.a();
                }
                if (arrayList.size() == 0) {
                    arrayList = this.i;
                    if (arrayList != null) {
                        arrayList2 = this.f;
                        if (arrayList2 == null) {
                            kotlin.jvm.internal.c.a();
                        }
                        arrayList2.addAll(arrayList);
                    }
                }
            } else {
                arrayList = this.f;
                if (arrayList == null) {
                    kotlin.jvm.internal.c.a();
                }
                arrayList.clear();
            }
        }
        b bVar = this.n;
        if (bVar == null) {
            kotlin.jvm.internal.c.a();
        }
        bVar.notifyDataSetChanged();
    }

    private final void e() {
        Context context = this.mContext;
        if (context == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.gaana.BaseActivity");
        }
        ((BaseActivity) context).showProgressDialog(Boolean.valueOf(true), this.mContext.getString(R.string.loading_string_text));
        URLManager uRLManager = new URLManager();
        uRLManager.c(1);
        uRLManager.b(Boolean.valueOf(false));
        uRLManager.a(Tracks.class);
        uRLManager.a("https://api.gaana.com/user.php?type=mymostplayed");
        com.i.i.a().a((af) new c(this), uRLManager);
    }

    public void onClick(View view) {
        kotlin.jvm.internal.c.b(view, "v");
        int id = view.getId();
        Context context;
        if (id == R.id.btnLeft) {
            u.a().b("Setup Gaana Mini Plus Screen", "Close");
            if (((GaanaActivity) getActivity()) != null) {
                if (this.b == 2 && this.c) {
                    context = this.mContext;
                    if (context == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.gaana.GaanaActivity");
                    }
                    ((GaanaActivity) context).popBackStackImmediate();
                }
                if (isAdded()) {
                    FragmentActivity activity = getActivity();
                    if (activity == null) {
                        throw new TypeCastException("null cannot be cast to non-null type com.gaana.GaanaActivity");
                    }
                    ((GaanaActivity) activity).onBackPressedHandling();
                }
            }
        } else if (id == R.id.keep_downloads) {
            BusinessObject businessObject;
            id = 0;
            ArrayList arrayList = this.l;
            if (arrayList == null) {
                kotlin.jvm.internal.c.a();
            }
            Object listIterator = arrayList.listIterator();
            kotlin.jvm.internal.c.a(listIterator, "keepAllList!!.listIterator()");
            Iterator it = (Iterator) listIterator;
            while (it.hasNext()) {
                businessObject = (BusinessObject) it.next();
                if (businessObject instanceof OfflineTrack) {
                    if (((OfflineTrack) businessObject).isSelected()) {
                        id++;
                    }
                } else if (businessObject == null) {
                    throw new TypeCastException("null cannot be cast to non-null type com.gaana.models.Tracks.Track");
                } else {
                    Object isSelected = ((Track) businessObject).isSelected();
                    kotlin.jvm.internal.c.a(isSelected, "(i as Tracks.Track).isSelected");
                    if (isSelected.booleanValue()) {
                        id++;
                    }
                }
            }
            listIterator = GaanaApplication.getInstance().getCurrentUser().getUserSubscriptionData().getProductProperties();
            kotlin.jvm.internal.c.a(listIterator, "GaanaApplication.getInst…().getProductProperties()");
            listIterator = Integer.valueOf(listIterator.getSongLimit());
            kotlin.jvm.internal.c.a(listIterator, "Integer.valueOf(GaanaApp…ctProperties().songLimit)");
            if (kotlin.jvm.internal.c.a(id, listIterator.intValue()) > 0) {
                aj a = aj.a();
                Context context2 = this.mContext;
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("You can only select ");
                Object instance = GaanaApplication.getInstance();
                kotlin.jvm.internal.c.a(instance, "GaanaApplication.getInstance()");
                instance = instance.getCurrentUser();
                kotlin.jvm.internal.c.a(instance, "GaanaApplication.getInstance().currentUser");
                instance = instance.getUserSubscriptionData();
                kotlin.jvm.internal.c.a(instance, "GaanaApplication.getInst…User.userSubscriptionData");
                instance = instance.getProductProperties();
                kotlin.jvm.internal.c.a(instance, "GaanaApplication.getInst…ionData.productProperties");
                stringBuilder.append(instance.getSongLimit());
                stringBuilder.append(" downloads");
                a.a(context2, stringBuilder.toString(), true);
                return;
            }
            ArrayList arrayList2 = this.l;
            if (arrayList2 == null) {
                kotlin.jvm.internal.c.a();
            }
            Object listIterator2 = arrayList2.listIterator();
            kotlin.jvm.internal.c.a(listIterator2, "keepAllList!!.listIterator()");
            Iterator it2 = (Iterator) listIterator2;
            while (it2.hasNext()) {
                businessObject = (BusinessObject) it2.next();
                if (businessObject instanceof Track) {
                    Track track = (Track) businessObject;
                    if (!track.isSelected().booleanValue()) {
                        DownloadManager.c().d(track.getBusinessObjId());
                    }
                } else if (businessObject instanceof OfflineTrack) {
                    OfflineTrack offlineTrack = (OfflineTrack) businessObject;
                    if (!offlineTrack.isSelected()) {
                        DownloadManager.c().d(offlineTrack.getBusinessObjId());
                    }
                }
            }
            context = this.mContext;
            if (context == null) {
                throw new TypeCastException("null cannot be cast to non-null type com.gaana.GaanaActivity");
            }
            ((GaanaActivity) context).popBackStack();
            DownloadManager.c().d();
            a(Boolean.valueOf(true));
        }
    }

    private final void a(Boolean bool) {
        Context context = this.mContext;
        if (context == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.gaana.BaseActivity");
        }
        ((BaseActivity) context).refreshListView();
    }

    public void refreshListView(BusinessObject businessObject, boolean z) {
        kotlin.jvm.internal.c.b(businessObject, "businessObject");
        super.refreshListView(businessObject, z);
    }

    public void setGAScreenName(String str, String str2) {
        kotlin.jvm.internal.c.b(str, "currentScreen");
        kotlin.jvm.internal.c.b(str2, "gaScreenName");
        sendGAScreenName(str, str2);
    }

    public void onDestroyView() {
        if (this.containerView != null) {
            View view = this.containerView;
            if (view == null) {
                kotlin.jvm.internal.c.a();
            }
            if (view.getParent() != null) {
                view = this.containerView;
                if (view == null) {
                    kotlin.jvm.internal.c.a();
                }
                ViewParent parent = view.getParent();
                if (parent == null) {
                    throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup");
                }
                ((ViewGroup) parent).removeView(this.containerView);
            }
        }
        super.onDestroyView();
        d();
    }
}
