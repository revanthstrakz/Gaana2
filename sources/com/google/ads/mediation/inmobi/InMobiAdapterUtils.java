package com.google.ads.mediation.inmobi;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.inmobi.sdk.InMobiSdk;
import com.inmobi.sdk.InMobiSdk.AgeGroup;
import com.inmobi.sdk.InMobiSdk.Education;
import com.inmobi.sdk.InMobiSdk.Gender;
import com.inmobi.sdk.InMobiSdk.LogLevel;
import java.util.Calendar;
import java.util.Objects;

class InMobiAdapterUtils {
    InMobiAdapterUtils() {
    }

    public static void buildAdRequest(MediationAdRequest mediationAdRequest, Bundle bundle) {
        if (bundle == null) {
            Log.d("InMobiAdapter", "Bundle extras are null");
            bundle = new Bundle();
        }
        Object obj = "";
        Object obj2 = "";
        Object obj3 = "";
        for (String str : bundle.keySet()) {
            String string = bundle.getString(str);
            if (str.equals(InMobiNetworkKeys.AREA_CODE)) {
                if (!"".equals(string)) {
                    InMobiSdk.setAreaCode(string);
                }
            } else if (str.equals(InMobiNetworkKeys.AGE)) {
                try {
                    if (!"".equals(string)) {
                        InMobiSdk.setAge(Integer.parseInt(string));
                    }
                } catch (NumberFormatException e) {
                    Log.d("Please Set age properly", e.getMessage());
                    ThrowableExtension.printStackTrace(e);
                }
            } else if (str.equals(InMobiNetworkKeys.POSTAL_CODE)) {
                if (!"".equals(string)) {
                    InMobiSdk.setPostalCode(string);
                }
            } else if (str.equals(InMobiNetworkKeys.LANGUAGE)) {
                if (!"".equals(string)) {
                    InMobiSdk.setLanguage(string);
                }
            } else if (str.equals(InMobiNetworkKeys.CITY)) {
                obj = string;
            } else if (str.equals(InMobiNetworkKeys.STATE)) {
                obj2 = string;
            } else if (str.equals(InMobiNetworkKeys.COUNTRY)) {
                obj3 = string;
            } else if (str.equals(InMobiNetworkKeys.AGE_GROUP)) {
                if (string != null) {
                    AgeGroup ageGroup = getAgeGroup(string);
                    if (ageGroup != null) {
                        InMobiSdk.setAgeGroup(ageGroup);
                    }
                }
            } else if (str.equals(InMobiNetworkKeys.EDUCATION)) {
                if (string != null) {
                    Education education = getEducation(string);
                    if (education != null) {
                        InMobiSdk.setEducation(education);
                    }
                }
            } else if (str.equals(InMobiNetworkKeys.LOGLEVEL)) {
                if (string != null) {
                    InMobiSdk.setLogLevel(getLogLevel(string));
                } else {
                    InMobiSdk.setLogLevel(LogLevel.NONE);
                }
            } else if (str.equals(InMobiNetworkKeys.INTERESTS)) {
                InMobiSdk.setInterests(string);
            }
        }
        if (!(VERSION.SDK_INT < 19 || Objects.equals(obj, "") || Objects.equals(obj2, "") || Objects.equals(obj3, ""))) {
            InMobiSdk.setLocationWithCityStateCountry(obj, obj2, obj3);
        }
        if (mediationAdRequest.getLocation() != null) {
            InMobiSdk.setLocation(mediationAdRequest.getLocation());
        }
        if (mediationAdRequest.getBirthday() != null) {
            Calendar instance = Calendar.getInstance();
            instance.setTime(mediationAdRequest.getBirthday());
            InMobiSdk.setYearOfBirth(instance.get(1));
        }
        if (mediationAdRequest.getGender() != -1) {
            switch (mediationAdRequest.getGender()) {
                case 1:
                    InMobiSdk.setGender(Gender.MALE);
                    return;
                case 2:
                    InMobiSdk.setGender(Gender.FEMALE);
                    return;
                default:
                    return;
            }
        }
    }

    private static com.inmobi.sdk.InMobiSdk.AgeGroup getAgeGroup(java.lang.String r1) {
        /*
        r0 = r1.hashCode();
        switch(r0) {
            case -2144603857: goto L_0x004e;
            case -1892470079: goto L_0x0044;
            case -1873932011: goto L_0x003a;
            case -1017207884: goto L_0x0030;
            case -337149426: goto L_0x0026;
            case 1346187892: goto L_0x001c;
            case 1470305006: goto L_0x0012;
            case 1723710283: goto L_0x0008;
            default: goto L_0x0007;
        };
    L_0x0007:
        goto L_0x0058;
    L_0x0008:
        r0 = "BETWEEN_18_AND_24";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x0058;
    L_0x0010:
        r1 = 2;
        goto L_0x0059;
    L_0x0012:
        r0 = "BETWEEN_35_AND_44";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x0058;
    L_0x001a:
        r1 = 5;
        goto L_0x0059;
    L_0x001c:
        r0 = "BETWEEN_30_AND_34";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x0058;
    L_0x0024:
        r1 = 4;
        goto L_0x0059;
    L_0x0026:
        r0 = "BETWEEN_45_AND_54";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x0058;
    L_0x002e:
        r1 = 6;
        goto L_0x0059;
    L_0x0030:
        r0 = "BETWEEN_25_AND_29";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x0058;
    L_0x0038:
        r1 = 3;
        goto L_0x0059;
    L_0x003a:
        r0 = "BELOW_18";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x0058;
    L_0x0042:
        r1 = 1;
        goto L_0x0059;
    L_0x0044:
        r0 = "ABOVE_65";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x0058;
    L_0x004c:
        r1 = 0;
        goto L_0x0059;
    L_0x004e:
        r0 = "BETWEEN_55_AND_65";
        r1 = r1.equals(r0);
        if (r1 == 0) goto L_0x0058;
    L_0x0056:
        r1 = 7;
        goto L_0x0059;
    L_0x0058:
        r1 = -1;
    L_0x0059:
        switch(r1) {
            case 0: goto L_0x0073;
            case 1: goto L_0x0070;
            case 2: goto L_0x006d;
            case 3: goto L_0x006a;
            case 4: goto L_0x0067;
            case 5: goto L_0x0064;
            case 6: goto L_0x0061;
            case 7: goto L_0x005e;
            default: goto L_0x005c;
        };
    L_0x005c:
        r1 = 0;
        return r1;
    L_0x005e:
        r1 = com.inmobi.sdk.InMobiSdk.AgeGroup.BETWEEN_55_AND_65;
        return r1;
    L_0x0061:
        r1 = com.inmobi.sdk.InMobiSdk.AgeGroup.BETWEEN_45_AND_54;
        return r1;
    L_0x0064:
        r1 = com.inmobi.sdk.InMobiSdk.AgeGroup.BETWEEN_35_AND_44;
        return r1;
    L_0x0067:
        r1 = com.inmobi.sdk.InMobiSdk.AgeGroup.BETWEEN_30_AND_34;
        return r1;
    L_0x006a:
        r1 = com.inmobi.sdk.InMobiSdk.AgeGroup.BETWEEN_25_AND_29;
        return r1;
    L_0x006d:
        r1 = com.inmobi.sdk.InMobiSdk.AgeGroup.BETWEEN_18_AND_24;
        return r1;
    L_0x0070:
        r1 = com.inmobi.sdk.InMobiSdk.AgeGroup.BELOW_18;
        return r1;
    L_0x0073:
        r1 = com.inmobi.sdk.InMobiSdk.AgeGroup.ABOVE_65;
        return r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.ads.mediation.inmobi.InMobiAdapterUtils.getAgeGroup(java.lang.String):com.inmobi.sdk.InMobiSdk$AgeGroup");
    }

    private static Education getEducation(String str) {
        if (str.equals(InMobiNetworkValues.EDUCATION_COLLEGEORGRADUATE)) {
            return Education.COLLEGE_OR_GRADUATE;
        }
        if (str.equals(InMobiNetworkValues.EDUCATION_HIGHSCHOOLORLESS)) {
            return Education.HIGH_SCHOOL_OR_LESS;
        }
        return str.equals(InMobiNetworkValues.EDUCATION_POSTGRADUATEORABOVE) ? Education.POST_GRADUATE_OR_ABOVE : null;
    }

    private static LogLevel getLogLevel(String str) {
        if (str.equals(InMobiNetworkValues.LOGLEVEL_DEBUG)) {
            return LogLevel.DEBUG;
        }
        if (str.equals(InMobiNetworkValues.LOGLEVEL_ERROR)) {
            return LogLevel.ERROR;
        }
        if (str.equals(InMobiNetworkValues.LOGLEVEL_NONE)) {
            return LogLevel.NONE;
        }
        return LogLevel.NONE;
    }

    public static <T> T mandatoryChecking(@Nullable T t, String str) throws MandatoryParamException {
        if (t != null && !t.toString().isEmpty()) {
            return t;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Mandatory param ");
        stringBuilder.append(str);
        stringBuilder.append(" not present");
        throw new MandatoryParamException(stringBuilder.toString());
    }
}
