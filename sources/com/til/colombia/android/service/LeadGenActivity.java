package com.til.colombia.android.service;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Xml;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import com.google.android.exoplayer2.util.MimeTypes;
import com.til.colombia.android.R;
import com.til.colombia.android.commons.CommonUtil;
import com.til.colombia.android.internal.LeadGenXmlParser;
import com.til.colombia.android.internal.LeadGenXmlParser.ViewObject;
import com.til.colombia.android.internal.Log;
import com.til.colombia.android.internal.e;
import com.til.colombia.android.internal.i;
import com.til.colombia.android.internal.views.CloseableLayout;
import com.til.colombia.android.network.l;
import com.til.colombia.android.utils.c;
import com.til.colombia.android.vast.h;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;

public class LeadGenActivity extends Activity {
    private TextView brand;
    private TextView desc;
    private LinearLayout formLayout;
    private Bitmap imgBmp;
    private Item item;
    private String itemId;
    private ImageView leadImg;
    private ScrollView lg_container;
    private int lineItemId;
    private LeadGenXmlParser lparser;
    private CloseableLayout mCloseableLayout;
    private Context mContext;
    private String snippet;
    private Button submit;
    private ImageView thankmsgView;
    private TextView title;

    private class a extends ArrayAdapter<String> {
        public a(Context context, int i, List<String> list) {
            super(context, 17367048, list);
        }

        public final int getCount() {
            int count = super.getCount();
            return count > 0 ? count - 1 : count;
        }
    }

    static class b extends Handler {
        private Context a;

        b(Context context) {
            this.a = context;
        }

        public final void handleMessage(Message message) {
            if (this.a != null) {
                ((Activity) this.a).finish();
            }
        }
    }

    /* Access modifiers changed, original: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = this;
        setRequestedOrientation(1);
        this.snippet = getIntent().getStringExtra(bm.a);
        this.lineItemId = getIntent().getIntExtra(bm.c, -1);
        this.itemId = getIntent().getStringExtra(bm.b);
        this.item = (Item) getIntent().getSerializableExtra(e.ab);
        requestImage();
        this.lparser = new LeadGenXmlParser(this, this.snippet);
        try {
            LeadGenXmlParser leadGenXmlParser = this.lparser;
            XmlPullParser newPullParser = Xml.newPullParser();
            newPullParser.setFeature("http://xmlpull.org/v1/doc/features.html#process-namespaces", false);
            newPullParser.setInput(new StringReader(leadGenXmlParser.b));
            newPullParser.nextTag();
            newPullParser.require(2, null, "leadgen");
            for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
                if (newPullParser.getName() != null) {
                    if (newPullParser.getName().equalsIgnoreCase(h.b)) {
                        leadGenXmlParser.a(newPullParser);
                    } else if (newPullParser.getName().equalsIgnoreCase("bgImage")) {
                        leadGenXmlParser.b(newPullParser);
                    } else if (newPullParser.getName().equalsIgnoreCase("conversionPixel")) {
                        leadGenXmlParser.c(newPullParser);
                    } else if (newPullParser.getName().equalsIgnoreCase("formItem")) {
                        leadGenXmlParser.d(newPullParser);
                    }
                }
            }
        } catch (Exception e) {
            Log.a(i.f, "", e);
            finish();
        }
        initView();
    }

    private void requestImage() {
        ce ceVar = new ce(this);
        com.til.colombia.android.utils.a aVar = new com.til.colombia.android.utils.a();
        aVar.a(ceVar, this.item.getImageUrl());
        aVar.b = new cg(this);
        try {
            aVar.a();
        } catch (Exception e) {
            String str = i.f;
            StringBuilder stringBuilder = new StringBuilder("is-error:");
            stringBuilder.append(e);
            android.util.Log.e(str, stringBuilder.toString());
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        setParams();
    }

    private void initView() {
        setContentView(R.layout.leadgen_layout);
        this.mCloseableLayout = (CloseableLayout) findViewById(R.id.parent_layout);
        this.mCloseableLayout.d = new ch(this);
        this.lg_container = (ScrollView) findViewById(R.id.lg_container);
        setParams();
        this.leadImg = (ImageView) findViewById(R.id.lead_img);
        if (this.imgBmp != null) {
            this.leadImg.setImageBitmap(this.imgBmp);
        }
        this.brand = (TextView) findViewById(R.id.brand_view);
        this.title = (TextView) findViewById(R.id.title);
        this.desc = (TextView) findViewById(R.id.desc);
        this.brand.setText(this.item.getBrandText());
        this.title.setText(this.item.getTitle());
        this.desc.setText(this.item.getBodyText());
        this.thankmsgView = (ImageView) findViewById(R.id.thanku_msg);
        this.submit = (Button) findViewById(R.id.submit);
        this.submit.setOnClickListener(new ci(this));
        this.formLayout = (LinearLayout) findViewById(R.id.formLayout);
        inflateItems(this.formLayout, this.lparser.e);
    }

    private void postForm(JSONObject jSONObject) throws JSONException {
        jSONObject.put("lineItemId", this.lineItemId);
        jSONObject.put(bm.b, this.itemId);
        jSONObject.put("conpix", this.lparser.d.get(0));
        l.a().a(new cj(this, jSONObject), 1);
        c.a(com.til.colombia.android.internal.a.a(), c.a, this.itemId, true);
        this.lg_container.setVisibility(8);
        this.submit.setVisibility(8);
        this.thankmsgView.setVisibility(0);
        closeLeadGen();
    }

    public boolean formIsValid(LinearLayout linearLayout, JSONObject jSONObject) throws JSONException {
        boolean z = true;
        int i = 0;
        while (i < linearLayout.getChildCount()) {
            View childAt = linearLayout.getChildAt(i);
            if (childAt instanceof EditText) {
                String trim = ((EditText) childAt).getText().toString().trim();
                ViewObject viewObject = (ViewObject) childAt.getTag();
                boolean validate = validate(viewObject, trim);
                i++;
                View childAt2 = linearLayout.getChildAt(i);
                if (childAt2 instanceof TextView) {
                    if (validate) {
                        childAt2.setVisibility(4);
                    } else {
                        childAt2.setVisibility(0);
                    }
                }
                if (validate) {
                    jSONObject.put(viewObject.getField(), trim);
                } else {
                    z = false;
                }
            } else if (childAt instanceof Spinner) {
                int i2;
                boolean z2;
                ViewObject viewObject2 = (ViewObject) childAt.getTag();
                Spinner spinner = (Spinner) childAt;
                if (spinner.getSelectedItemPosition() == spinner.getAdapter().getCount()) {
                    i2 = 0;
                    z2 = i2;
                } else {
                    jSONObject.put(viewObject2.getField(), spinner.getSelectedItem().toString());
                    z2 = z;
                    i2 = 1;
                }
                i++;
                View childAt3 = linearLayout.getChildAt(i);
                if (childAt3 instanceof TextView) {
                    if (i2 != 0) {
                        childAt3.setVisibility(4);
                    } else {
                        childAt3.setVisibility(0);
                    }
                }
                z = z2;
            }
            i++;
        }
        return z;
    }

    private boolean validate(ViewObject viewObject, String str) {
        if (com.til.colombia.android.internal.a.h.a(str)) {
            return false;
        }
        if (viewObject.getPattern() != null && !str.matches(viewObject.getPattern())) {
            return false;
        }
        if (viewObject.minLength != -1 && str.length() < viewObject.minLength) {
            return false;
        }
        if (viewObject.maxLength == -1 || str.length() <= viewObject.maxLength) {
            return true;
        }
        return false;
    }

    private void inflateItems(LinearLayout linearLayout, ArrayList<ViewObject> arrayList) {
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ViewObject viewObject = (ViewObject) it.next();
            if (viewObject.getType().equalsIgnoreCase(MimeTypes.BASE_TYPE_TEXT)) {
                createTextView(linearLayout, viewObject);
            }
            if (viewObject.getType().equalsIgnoreCase("email")) {
                createEmailView(linearLayout, viewObject);
            }
            if (viewObject.getType().equalsIgnoreCase("number")) {
                createNumberView(linearLayout, viewObject);
            }
            if (viewObject.getType().equalsIgnoreCase("spinner")) {
                createSpinnerView(linearLayout, viewObject);
            }
        }
    }

    private void createTextView(LinearLayout linearLayout, ViewObject viewObject) {
        EditText editText = new EditText(this);
        editText.setHint(viewObject.getPlaceholder());
        editText.setTag(viewObject);
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.topMargin = (int) getResources().getDimension(R.dimen.leadgen_form_margin);
        linearLayout.addView(editText, layoutParams);
        TextView textView = new TextView(this);
        textView.setTextSize((float) ((int) getResources().getDimension(R.dimen.error_text_size)));
        textView.setTextColor(getResources().getColor(R.color.error_color));
        textView.setText(viewObject.getErrormsg());
        textView.setVisibility(4);
        linearLayout.addView(textView, layoutParams);
    }

    private void createEmailView(LinearLayout linearLayout, ViewObject viewObject) {
        EditText editText = new EditText(this);
        editText.setHint(viewObject.getPlaceholder());
        editText.setTag(viewObject);
        editText.setInputType(524321);
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.topMargin = (int) getResources().getDimension(R.dimen.leadgen_form_margin);
        linearLayout.addView(editText, layoutParams);
        TextView textView = new TextView(this);
        textView.setTextSize((float) ((int) getResources().getDimension(R.dimen.error_text_size)));
        textView.setTextColor(getResources().getColor(R.color.error_color));
        textView.setText(viewObject.getErrormsg());
        textView.setTag(viewObject);
        textView.setVisibility(4);
        linearLayout.addView(textView, layoutParams);
    }

    private void createNumberView(LinearLayout linearLayout, ViewObject viewObject) {
        EditText editText = new EditText(this);
        editText.setHint(viewObject.getPlaceholder());
        editText.setTag(viewObject);
        editText.setInputType(2);
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.topMargin = (int) getResources().getDimension(R.dimen.leadgen_form_margin);
        linearLayout.addView(editText, layoutParams);
        TextView textView = new TextView(this);
        textView.setTextSize((float) ((int) getResources().getDimension(R.dimen.error_text_size)));
        textView.setTextColor(getResources().getColor(R.color.error_color));
        textView.setText(viewObject.getErrormsg());
        textView.setVisibility(4);
        linearLayout.addView(textView, layoutParams);
    }

    private void createSpinnerView(LinearLayout linearLayout, ViewObject viewObject) {
        Spinner spinner = new Spinner(this);
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(viewObject.getOptions().keySet());
        arrayList.add(viewObject.getPlaceholder());
        a aVar = new a(this, 17367048, arrayList);
        aVar.setDropDownViewResource(17367049);
        spinner.setAdapter(aVar);
        spinner.setTag(viewObject);
        spinner.setSelection(aVar.getCount());
        LayoutParams layoutParams = new LayoutParams(-1, -2);
        layoutParams.topMargin = (int) getResources().getDimension(R.dimen.leadgen_form_margin);
        linearLayout.addView(spinner, layoutParams);
        TextView textView = new TextView(this);
        textView.setTextSize((float) ((int) getResources().getDimension(R.dimen.error_text_size)));
        textView.setTextColor(getResources().getColor(R.color.error_color));
        textView.setText(viewObject.getErrormsg());
        textView.setVisibility(4);
        linearLayout.addView(textView, layoutParams);
    }

    private void setParams() {
        FrameLayout.LayoutParams layoutParams;
        if (getResources().getConfiguration().orientation == 1) {
            layoutParams = new FrameLayout.LayoutParams((int) (0.9d * ((double) CommonUtil.a())), (int) (0.85d * ((double) CommonUtil.b())));
        } else {
            layoutParams = new FrameLayout.LayoutParams((int) (0.8d * ((double) CommonUtil.a())), (int) (0.85d * ((double) CommonUtil.b())));
        }
        layoutParams.gravity = 17;
        this.mCloseableLayout.setLayoutParams(layoutParams);
    }

    private void closeLeadGen() {
        new b(this).sendEmptyMessageDelayed(0, 3000);
    }

    /* Access modifiers changed, original: protected */
    public void onDestroy() {
        if (!(this.imgBmp == null || this.imgBmp.isRecycled())) {
            this.imgBmp.recycle();
            this.imgBmp = null;
        }
        super.onDestroy();
    }
}
