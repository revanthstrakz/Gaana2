package com.facebook.accountkit.ui;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import com.facebook.accountkit.R;
import com.facebook.accountkit.internal.Utility;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

final class PhoneCountryCodeAdapter extends BaseAdapter implements SpinnerAdapter {
    private final Context context;
    private final PhoneCountryCode[] phoneCountryCodes;
    private final UIManager uiManager;

    private enum CountryCodeSource {
        APP_SUPPLIED_DEFAULT_VALUE,
        APP_SUPPLIED_PHONE_NUMBER,
        DEFAULT_VALUE,
        FIRST_VALUE,
        TELEPHONY_SERVICE
    }

    private static final class PhoneCountryCode {
        public final String countryCode;
        public final String isoCode;
        public final long itemId;
        public final String label;

        public PhoneCountryCode(String str, String str2, String str3) {
            this.countryCode = str;
            this.isoCode = str2;
            this.label = str3;
            str = str.replaceAll("[\\D]", "");
            int length = str2.length();
            for (int i = 0; i < length; i++) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(str);
                stringBuilder.append(Integer.toString(str2.charAt(i)));
                str = stringBuilder.toString();
            }
            this.itemId = Long.valueOf(str).longValue();
        }
    }

    public static class ValueData implements Parcelable {
        public static final Creator<ValueData> CREATOR = new Creator<ValueData>() {
            public ValueData createFromParcel(Parcel parcel) {
                return new ValueData(parcel, null);
            }

            public ValueData[] newArray(int i) {
                return new ValueData[i];
            }
        };
        public final String countryCode;
        public final String countryCodeSource;
        public final int position;

        public int describeContents() {
            return 0;
        }

        /* synthetic */ ValueData(Parcel parcel, AnonymousClass1 anonymousClass1) {
            this(parcel);
        }

        /* synthetic */ ValueData(String str, String str2, int i, AnonymousClass1 anonymousClass1) {
            this(str, str2, i);
        }

        private ValueData(String str, String str2, int i) {
            this.countryCode = str;
            this.countryCodeSource = str2;
            this.position = i;
        }

        private ValueData(Parcel parcel) {
            this.countryCode = parcel.readString();
            this.countryCodeSource = parcel.readString();
            this.position = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(this.countryCode);
            parcel.writeString(this.countryCodeSource);
            parcel.writeInt(this.position);
        }
    }

    public PhoneCountryCodeAdapter(Context context, UIManager uIManager, String[] strArr, String[] strArr2) {
        this.context = context;
        this.uiManager = uIManager;
        this.phoneCountryCodes = getAllPhoneCountryCodes(context, strArr, strArr2);
    }

    public int getCount() {
        return this.phoneCountryCodes.length;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0043  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0055  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0063  */
    public com.facebook.accountkit.ui.PhoneCountryCodeAdapter.ValueData getInitialValue(com.facebook.accountkit.PhoneNumber r8, java.lang.String r9) {
        /*
        r7 = this;
        r0 = 0;
        r1 = 0;
        r2 = -1;
        if (r8 == 0) goto L_0x0030;
    L_0x0005:
        r3 = com.facebook.accountkit.ui.PhoneCountryCodeAdapter.CountryCodeSource.APP_SUPPLIED_PHONE_NUMBER;
        r3 = r3.name();
        r4 = r7.phoneCountryCodes;
        r4 = r4.length;
        r5 = r8.getCountryCode();
        r8 = r8.getCountryCodeIso();
        if (r8 == 0) goto L_0x001d;
    L_0x0018:
        r8 = r7.getIndexOfCountryCode(r8);
        goto L_0x0033;
    L_0x001d:
        r8 = r0;
    L_0x001e:
        if (r8 >= r4) goto L_0x0032;
    L_0x0020:
        r6 = r7.phoneCountryCodes;
        r6 = r6[r8];
        r6 = r6.countryCode;
        r6 = r5.equalsIgnoreCase(r6);
        if (r6 == 0) goto L_0x002d;
    L_0x002c:
        goto L_0x0033;
    L_0x002d:
        r8 = r8 + 1;
        goto L_0x001e;
    L_0x0030:
        r3 = r1;
        r5 = r3;
    L_0x0032:
        r8 = r2;
    L_0x0033:
        if (r8 != r2) goto L_0x0040;
    L_0x0035:
        r8 = com.facebook.accountkit.ui.PhoneCountryCodeAdapter.CountryCodeSource.APP_SUPPLIED_DEFAULT_VALUE;
        r3 = r8.name();
        r8 = r7.getIndexOfCountryCode(r9);
        goto L_0x0041;
    L_0x0040:
        r9 = r5;
    L_0x0041:
        if (r8 != r2) goto L_0x0053;
    L_0x0043:
        r8 = com.facebook.accountkit.ui.PhoneCountryCodeAdapter.CountryCodeSource.TELEPHONY_SERVICE;
        r3 = r8.name();
        r8 = r7.context;
        r9 = com.facebook.accountkit.internal.Utility.getCurrentCountry(r8);
        r8 = r7.getIndexOfCountryCode(r9);
    L_0x0053:
        if (r8 != r2) goto L_0x0061;
    L_0x0055:
        r8 = com.facebook.accountkit.ui.PhoneCountryCodeAdapter.CountryCodeSource.DEFAULT_VALUE;
        r3 = r8.name();
        r9 = "US";
        r8 = r7.getIndexOfCountryCode(r9);
    L_0x0061:
        if (r8 != r2) goto L_0x0070;
    L_0x0063:
        r8 = com.facebook.accountkit.ui.PhoneCountryCodeAdapter.CountryCodeSource.FIRST_VALUE;
        r3 = r8.name();
        r8 = r7.phoneCountryCodes;
        r8 = r8[r0];
        r9 = r8.countryCode;
        r8 = r0;
    L_0x0070:
        r0 = new com.facebook.accountkit.ui.PhoneCountryCodeAdapter$ValueData;
        r0.<init>(r9, r3, r8, r1);
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.accountkit.ui.PhoneCountryCodeAdapter.getInitialValue(com.facebook.accountkit.PhoneNumber, java.lang.String):com.facebook.accountkit.ui.PhoneCountryCodeAdapter$ValueData");
    }

    public Object getItem(int i) {
        PhoneCountryCode phoneCountryCode = this.phoneCountryCodes[i];
        return new ValueData(phoneCountryCode.countryCode, phoneCountryCode.isoCode, i, null);
    }

    public long getItemId(int i) {
        return this.phoneCountryCodes[i].itemId;
    }

    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = View.inflate(this.context, R.layout.com_accountkit_phone_country_code_item_layout, null);
        }
        TextView textView = (TextView) view.findViewById(R.id.label);
        TextView textView2 = (TextView) view.findViewById(R.id.country_code);
        textView.setText(this.phoneCountryCodes[i].label);
        textView2.setText(String.format("+%s", new Object[]{r4.countryCode}));
        return view;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            view = View.inflate(this.context, R.layout.com_accountkit_phone_country_code_layout, null);
        }
        PhoneCountryCode phoneCountryCode = this.phoneCountryCodes[i];
        TextView textView = (TextView) view.findViewById(R.id.country_code);
        textView.setText(String.format("+%s", new Object[]{phoneCountryCode.countryCode}));
        if (!ViewUtility.useLegacy(this.uiManager)) {
            textView.setTextColor(((SkinManager) this.uiManager).getTextColor());
        }
        return view;
    }

    private int getIndexOfCountryCode(String str) {
        if (Utility.isNullOrEmpty(str)) {
            return -1;
        }
        int length = this.phoneCountryCodes.length;
        for (int i = 0; i < length; i++) {
            if (str.equalsIgnoreCase(this.phoneCountryCodes[i].isoCode)) {
                return i;
            }
        }
        return -1;
    }

    private static PhoneCountryCode[] getAllPhoneCountryCodes(Context context, String[] strArr, String[] strArr2) {
        String[] stringArray = context.getResources().getStringArray(R.array.com_accountkit_phone_country_codes);
        ArrayList arrayList = new ArrayList();
        Set hashSet = strArr2 != null ? new HashSet(Arrays.asList(strArr2)) : null;
        Set hashSet2 = (strArr == null || strArr.length <= 0) ? new HashSet() : new HashSet(Arrays.asList(strArr));
        for (String split : stringArray) {
            String[] split2 = split.split(":", 3);
            if (!hashSet2.contains(split2[1]) && (hashSet == null || hashSet.contains(split2[1]))) {
                arrayList.add(new PhoneCountryCode(split2[0], split2[1], split2[2]));
            }
        }
        final Collator instance = Collator.getInstance(Resources.getSystem().getConfiguration().locale);
        instance.setStrength(0);
        Collections.sort(arrayList, new Comparator<PhoneCountryCode>() {
            public int compare(PhoneCountryCode phoneCountryCode, PhoneCountryCode phoneCountryCode2) {
                return instance.compare(phoneCountryCode.label, phoneCountryCode2.label);
            }
        });
        PhoneCountryCode[] phoneCountryCodeArr = new PhoneCountryCode[arrayList.size()];
        arrayList.toArray(phoneCountryCodeArr);
        return phoneCountryCodeArr;
    }
}
