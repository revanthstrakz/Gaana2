package com.b;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.LayoutInflater.Factory;
import android.view.LayoutInflater.Factory2;
import android.view.View;
import android.view.ViewGroup;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.xmlpull.v1.XmlPullParser;

class d extends LayoutInflater {
    private static final String[] a = new String[]{"android.widget.", "android.webkit."};
    private final int b;
    private final c c;
    private boolean d = false;
    private Field e = null;

    @TargetApi(11)
    private static class c implements Factory2 {
        protected final Factory2 a;
        protected final c b;

        public c(Factory2 factory2, c cVar) {
            this.a = factory2;
            this.b = cVar;
        }

        public View onCreateView(String str, Context context, AttributeSet attributeSet) {
            return this.b.a(this.a.onCreateView(str, context, attributeSet), context, attributeSet);
        }

        public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
            return this.b.a(this.a.onCreateView(view, str, context, attributeSet), context, attributeSet);
        }
    }

    @TargetApi(11)
    private static class a extends c {
        private final d c;

        public a(Factory2 factory2, d dVar, c cVar) {
            super(factory2, cVar);
            this.c = dVar;
        }

        public View onCreateView(View view, String str, Context context, AttributeSet attributeSet) {
            return this.b.a(this.c.a(view, this.a.onCreateView(view, str, context, attributeSet), str, context, attributeSet), context, attributeSet);
        }
    }

    private static class b implements Factory {
        private final Factory a;
        private final d b;
        private final c c;

        public b(Factory factory, d dVar, c cVar) {
            this.a = factory;
            this.b = dVar;
            this.c = cVar;
        }

        public View onCreateView(String str, Context context, AttributeSet attributeSet) {
            if (VERSION.SDK_INT < 11) {
                return this.c.a(this.b.a(null, this.a.onCreateView(str, context, attributeSet), str, context, attributeSet), context, attributeSet);
            }
            return this.c.a(this.a.onCreateView(str, context, attributeSet), context, attributeSet);
        }
    }

    protected d(LayoutInflater layoutInflater, Context context, int i, boolean z) {
        super(layoutInflater, context);
        this.b = i;
        this.c = new c(i);
        a(z);
    }

    public LayoutInflater cloneInContext(Context context) {
        return new d(this, context, this.b, true);
    }

    public View inflate(XmlPullParser xmlPullParser, ViewGroup viewGroup, boolean z) {
        a();
        return super.inflate(xmlPullParser, viewGroup, z);
    }

    private void a(boolean z) {
        if (!z) {
            if (!(VERSION.SDK_INT < 11 || getFactory2() == null || (getFactory2() instanceof c))) {
                setFactory2(getFactory2());
            }
            if (!(getFactory() == null || (getFactory() instanceof b))) {
                setFactory(getFactory());
            }
        }
    }

    public void setFactory(Factory factory) {
        if (factory instanceof b) {
            super.setFactory(factory);
        } else {
            super.setFactory(new b(factory, this, this.c));
        }
    }

    @TargetApi(11)
    public void setFactory2(Factory2 factory2) {
        if (factory2 instanceof c) {
            super.setFactory2(factory2);
        } else {
            super.setFactory2(new c(factory2, this.c));
        }
    }

    private void a() {
        if (this.d || !a.a().d()) {
            return;
        }
        if (getContext() instanceof Factory2) {
            Method b = h.b(LayoutInflater.class, "setPrivateFactory");
            if (b != null) {
                h.a((Object) this, b, new a((Factory2) getContext(), this, this.c));
            }
            this.d = true;
            return;
        }
        this.d = true;
    }

    /* Access modifiers changed, original: protected */
    @TargetApi(11)
    public View onCreateView(View view, String str, AttributeSet attributeSet) throws ClassNotFoundException {
        return this.c.a(super.onCreateView(view, str, attributeSet), getContext(), attributeSet);
    }

    /* Access modifiers changed, original: protected */
    public View onCreateView(String str, AttributeSet attributeSet) throws ClassNotFoundException {
        View view = null;
        for (String createView : a) {
            try {
                view = createView(str, createView, attributeSet);
            } catch (ClassNotFoundException unused) {
            }
        }
        if (view == null) {
            view = super.onCreateView(str, attributeSet);
        }
        return this.c.a(view, view.getContext(), attributeSet);
    }

    private View a(View view, View view2, String str, Context context, AttributeSet attributeSet) {
        if (!a.a().e()) {
            return view2;
        }
        if (view2 == null && str.indexOf(46) > -1) {
            View createView;
            if (this.e == null) {
                this.e = h.a(LayoutInflater.class, "mConstructorArgs");
            }
            Object obj = (Object[]) h.a(this.e, (Object) this);
            Object obj2 = obj[0];
            obj[0] = context;
            h.a(this.e, (Object) this, obj);
            try {
                createView = createView(str, null, attributeSet);
            } catch (ClassNotFoundException unused) {
            } finally {
                obj[0] = obj2;
                Field createView2 = this.e;
                h.a(createView2, (Object) this, obj);
            }
            view2 = createView2;
        }
        return view2;
    }
}
