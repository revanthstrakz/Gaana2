package com.google.android.gms.internal.clearcut;

import com.moengage.ActionMapperConstants;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class zzga {
    public static <T extends zzfz> String zza(T t) {
        String str;
        String valueOf;
        if (t == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        try {
            zza(null, t, new StringBuffer(), stringBuffer);
            return stringBuffer.toString();
        } catch (IllegalAccessException e) {
            str = "Error printing proto: ";
            valueOf = String.valueOf(e.getMessage());
            return valueOf.length() != 0 ? str.concat(valueOf) : new String(str);
        } catch (InvocationTargetException e2) {
            str = "Error printing proto: ";
            valueOf = String.valueOf(e2.getMessage());
            return valueOf.length() != 0 ? str.concat(valueOf) : new String(str);
        }
    }

    private static void zza(String str, Object obj, StringBuffer stringBuffer, StringBuffer stringBuffer2) throws IllegalAccessException, InvocationTargetException {
        if (obj != null) {
            int i;
            if (obj instanceof zzfz) {
                int length = stringBuffer.length();
                if (str != null) {
                    stringBuffer2.append(stringBuffer);
                    stringBuffer2.append(zzl(str));
                    stringBuffer2.append(" <\n");
                    stringBuffer.append("  ");
                }
                Class cls = obj.getClass();
                for (Field field : cls.getFields()) {
                    int modifiers = field.getModifiers();
                    String name = field.getName();
                    if (!("cachedSize".equals(name) || (modifiers & 1) != 1 || (modifiers & 8) == 8 || name.startsWith("_") || name.endsWith("_"))) {
                        Class type = field.getType();
                        Object obj2 = field.get(obj);
                        if (!type.isArray() || type.getComponentType() == Byte.TYPE) {
                            zza(name, obj2, stringBuffer, stringBuffer2);
                        } else {
                            modifiers = obj2 == null ? 0 : Array.getLength(obj2);
                            for (int i2 = 0; i2 < modifiers; i2++) {
                                zza(name, Array.get(obj2, i2), stringBuffer, stringBuffer2);
                            }
                        }
                    }
                }
                for (Method name2 : cls.getMethods()) {
                    String name3 = name2.getName();
                    if (name3.startsWith(ActionMapperConstants.KEY_SET)) {
                        name3 = name3.substring(3);
                        try {
                            String str2 = "has";
                            String valueOf = String.valueOf(name3);
                            if (((Boolean) cls.getMethod(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), new Class[0]).invoke(obj, new Object[0])).booleanValue()) {
                                str2 = "get";
                                valueOf = String.valueOf(name3);
                                zza(name3, cls.getMethod(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2), new Class[0]).invoke(obj, new Object[0]), stringBuffer, stringBuffer2);
                            }
                        } catch (NoSuchMethodException unused) {
                        }
                    }
                }
                if (str != null) {
                    stringBuffer.setLength(length);
                    stringBuffer2.append(stringBuffer);
                    stringBuffer2.append(">\n");
                }
                return;
            }
            str = zzl(str);
            stringBuffer2.append(stringBuffer);
            stringBuffer2.append(str);
            stringBuffer2.append(": ");
            int length2;
            if (obj instanceof String) {
                String str3 = (String) obj;
                if (!str3.startsWith("http") && str3.length() > 200) {
                    str3 = String.valueOf(str3.substring(0, 200)).concat("[...]");
                }
                length2 = str3.length();
                StringBuilder stringBuilder = new StringBuilder(length2);
                for (i = 0; i < length2; i++) {
                    char charAt = str3.charAt(i);
                    if (charAt < ' ' || charAt > '~' || charAt == '\"' || charAt == '\'') {
                        stringBuilder.append(String.format("\\u%04x", new Object[]{Integer.valueOf(charAt)}));
                    } else {
                        stringBuilder.append(charAt);
                    }
                }
                str = stringBuilder.toString();
                stringBuffer2.append("\"");
                stringBuffer2.append(str);
                str = "\"";
            } else {
                if (obj instanceof byte[]) {
                    byte[] bArr = (byte[]) obj;
                    if (bArr == null) {
                        str = "\"\"";
                    } else {
                        stringBuffer2.append('\"');
                        for (byte b : bArr) {
                            int i3 = b & 255;
                            if (i3 == 92 || i3 == 34) {
                                stringBuffer2.append('\\');
                            } else if (i3 < 32 || i3 >= 127) {
                                stringBuffer2.append(String.format("\\%03o", new Object[]{Integer.valueOf(i3)}));
                            }
                            stringBuffer2.append((char) i3);
                        }
                        stringBuffer2.append('\"');
                    }
                } else {
                    stringBuffer2.append(obj);
                }
                stringBuffer2.append("\n");
            }
            stringBuffer2.append(str);
            stringBuffer2.append("\n");
        }
    }

    private static String zzl(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (i != 0) {
                if (Character.isUpperCase(charAt)) {
                    stringBuffer.append('_');
                }
                stringBuffer.append(charAt);
            }
            charAt = Character.toLowerCase(charAt);
            stringBuffer.append(charAt);
        }
        return stringBuffer.toString();
    }
}
