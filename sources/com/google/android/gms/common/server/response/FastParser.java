package com.google.android.gms.common.server.response;

import android.util.Log;
import com.google.android.exoplayer2.C;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.server.response.FastJsonResponse.Field;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.common.util.JsonUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;

@ShowFirstParty
@KeepForSdk
public class FastParser<T extends FastJsonResponse> {
    private static final char[] zaqf = new char[]{'u', 'l', 'l'};
    private static final char[] zaqg = new char[]{'r', 'u', 'e'};
    private static final char[] zaqh = new char[]{'r', 'u', 'e', '\"'};
    private static final char[] zaqi = new char[]{'a', 'l', 's', 'e'};
    private static final char[] zaqj = new char[]{'a', 'l', 's', 'e', '\"'};
    private static final char[] zaqk = new char[]{10};
    private static final zaa<Integer> zaqm = new zaa();
    private static final zaa<Long> zaqn = new zab();
    private static final zaa<Float> zaqo = new zac();
    private static final zaa<Double> zaqp = new zad();
    private static final zaa<Boolean> zaqq = new zae();
    private static final zaa<String> zaqr = new zaf();
    private static final zaa<BigInteger> zaqs = new zag();
    private static final zaa<BigDecimal> zaqt = new zah();
    private final char[] zaqa = new char[1];
    private final char[] zaqb = new char[32];
    private final char[] zaqc = new char[1024];
    private final StringBuilder zaqd = new StringBuilder(32);
    private final StringBuilder zaqe = new StringBuilder(1024);
    private final Stack<Integer> zaql = new Stack();

    @ShowFirstParty
    @KeepForSdk
    public static class ParseException extends Exception {
        public ParseException(String str) {
            super(str);
        }

        public ParseException(String str, Throwable th) {
            super(str, th);
        }

        public ParseException(Throwable th) {
            super(th);
        }
    }

    private interface zaa<O> {
        O zah(FastParser fastParser, BufferedReader bufferedReader) throws ParseException, IOException;
    }

    @KeepForSdk
    public void parse(InputStream inputStream, T t) throws ParseException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream), 1024);
        try {
            this.zaql.push(Integer.valueOf(0));
            char zaj = zaj(bufferedReader);
            if (zaj != 0) {
                if (zaj == '[') {
                    this.zaql.push(Integer.valueOf(5));
                    Map fieldMappings = t.getFieldMappings();
                    if (fieldMappings.size() != 1) {
                        throw new ParseException("Object array response class must have a single Field");
                    }
                    Field field = (Field) ((Entry) fieldMappings.entrySet().iterator().next()).getValue();
                    t.addConcreteTypeArrayInternal(field, field.zapu, zaa(bufferedReader, field));
                } else if (zaj != '{') {
                    StringBuilder stringBuilder = new StringBuilder(19);
                    stringBuilder.append("Unexpected token: ");
                    stringBuilder.append(zaj);
                    throw new ParseException(stringBuilder.toString());
                } else {
                    this.zaql.push(Integer.valueOf(1));
                    zaa(bufferedReader, (FastJsonResponse) t);
                }
                zak(0);
                try {
                    bufferedReader.close();
                    return;
                } catch (IOException unused) {
                    Log.w("FastParser", "Failed to close reader while parsing.");
                    return;
                }
            }
            throw new ParseException("No data to parse");
        } catch (IOException e) {
            throw new ParseException(e);
        } catch (Throwable th) {
            try {
                bufferedReader.close();
            } catch (IOException unused2) {
                Log.w("FastParser", "Failed to close reader while parsing.");
            }
        }
    }

    private final boolean zaa(BufferedReader bufferedReader, FastJsonResponse fastJsonResponse) throws ParseException, IOException {
        Map fieldMappings = fastJsonResponse.getFieldMappings();
        Object zaa = zaa(bufferedReader);
        if (zaa == null) {
            zak(1);
            return false;
        }
        while (zaa != null) {
            Field field = (Field) fieldMappings.get(zaa);
            if (field == null) {
                zaa = zab(bufferedReader);
            } else {
                StringBuilder stringBuilder;
                this.zaql.push(Integer.valueOf(4));
                char zaj;
                switch (field.zapq) {
                    case 0:
                        if (!field.zapr) {
                            fastJsonResponse.zaa(field, zad(bufferedReader));
                            break;
                        }
                        fastJsonResponse.zaa(field, zaa(bufferedReader, zaqm));
                        break;
                    case 1:
                        if (!field.zapr) {
                            fastJsonResponse.zaa(field, zaf(bufferedReader));
                            break;
                        }
                        fastJsonResponse.zab(field, zaa(bufferedReader, zaqs));
                        break;
                    case 2:
                        if (!field.zapr) {
                            fastJsonResponse.zaa(field, zae(bufferedReader));
                            break;
                        }
                        fastJsonResponse.zac(field, zaa(bufferedReader, zaqn));
                        break;
                    case 3:
                        if (!field.zapr) {
                            fastJsonResponse.zaa(field, zag(bufferedReader));
                            break;
                        }
                        fastJsonResponse.zad(field, zaa(bufferedReader, zaqo));
                        break;
                    case 4:
                        if (!field.zapr) {
                            fastJsonResponse.zaa(field, zah(bufferedReader));
                            break;
                        }
                        fastJsonResponse.zae(field, zaa(bufferedReader, zaqp));
                        break;
                    case 5:
                        if (!field.zapr) {
                            fastJsonResponse.zaa(field, zai(bufferedReader));
                            break;
                        }
                        fastJsonResponse.zaf(field, zaa(bufferedReader, zaqt));
                        break;
                    case 6:
                        if (!field.zapr) {
                            fastJsonResponse.zaa(field, zaa(bufferedReader, false));
                            break;
                        }
                        fastJsonResponse.zag(field, zaa(bufferedReader, zaqq));
                        break;
                    case 7:
                        if (!field.zapr) {
                            fastJsonResponse.zaa(field, zac(bufferedReader));
                            break;
                        }
                        fastJsonResponse.zah(field, zaa(bufferedReader, zaqr));
                        break;
                    case 8:
                        fastJsonResponse.zaa(field, Base64Utils.decode(zaa(bufferedReader, this.zaqc, this.zaqe, zaqk)));
                        break;
                    case 9:
                        fastJsonResponse.zaa(field, Base64Utils.decodeUrlSafe(zaa(bufferedReader, this.zaqc, this.zaqe, zaqk)));
                        break;
                    case 10:
                        Map map;
                        zaj = zaj(bufferedReader);
                        if (zaj == 'n') {
                            zab(bufferedReader, zaqf);
                            map = null;
                        } else if (zaj != '{') {
                            throw new ParseException("Expected start of a map object");
                        } else {
                            this.zaql.push(Integer.valueOf(1));
                            map = new HashMap();
                            while (true) {
                                char zaj2 = zaj(bufferedReader);
                                if (zaj2 == 0) {
                                    throw new ParseException("Unexpected EOF");
                                } else if (zaj2 == '\"') {
                                    String zab = zab(bufferedReader, this.zaqb, this.zaqd, null);
                                    String str;
                                    String valueOf;
                                    if (zaj(bufferedReader) != ':') {
                                        str = "No map value found for key ";
                                        valueOf = String.valueOf(zab);
                                        throw new ParseException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                                    } else if (zaj(bufferedReader) != '\"') {
                                        str = "Expected String value for key ";
                                        valueOf = String.valueOf(zab);
                                        throw new ParseException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                                    } else {
                                        map.put(zab, zab(bufferedReader, this.zaqb, this.zaqd, null));
                                        zaj2 = zaj(bufferedReader);
                                        if (zaj2 != ',') {
                                            if (zaj2 == '}') {
                                                zak(1);
                                            } else {
                                                stringBuilder = new StringBuilder(48);
                                                stringBuilder.append("Unexpected character while parsing string map: ");
                                                stringBuilder.append(zaj2);
                                                throw new ParseException(stringBuilder.toString());
                                            }
                                        }
                                    }
                                } else if (zaj2 == '}') {
                                    zak(1);
                                }
                            }
                        }
                        fastJsonResponse.zaa(field, map);
                        break;
                    case 11:
                        if (field.zapr) {
                            zaj = zaj(bufferedReader);
                            if (zaj != 'n') {
                                this.zaql.push(Integer.valueOf(5));
                                if (zaj == '[') {
                                    fastJsonResponse.addConcreteTypeArrayInternal(field, field.zapu, zaa(bufferedReader, field));
                                    break;
                                }
                                throw new ParseException("Expected array start");
                            }
                            zab(bufferedReader, zaqf);
                            fastJsonResponse.addConcreteTypeArrayInternal(field, field.zapu, null);
                            break;
                        }
                        zaj = zaj(bufferedReader);
                        if (zaj == 'n') {
                            zab(bufferedReader, zaqf);
                            fastJsonResponse.addConcreteTypeInternal(field, field.zapu, null);
                            break;
                        }
                        this.zaql.push(Integer.valueOf(1));
                        if (zaj != '{') {
                            throw new ParseException("Expected start of object");
                        }
                        try {
                            FastJsonResponse zacp = field.zacp();
                            zaa(bufferedReader, zacp);
                            fastJsonResponse.addConcreteTypeInternal(field, field.zapu, zacp);
                            break;
                        } catch (InstantiationException e) {
                            throw new ParseException("Error instantiating inner object", e);
                        } catch (IllegalAccessException e2) {
                            throw new ParseException("Error instantiating inner object", e2);
                        }
                    default:
                        int i = field.zapq;
                        StringBuilder stringBuilder2 = new StringBuilder(30);
                        stringBuilder2.append("Invalid field type ");
                        stringBuilder2.append(i);
                        throw new ParseException(stringBuilder2.toString());
                }
                zak(4);
                zak(2);
                char zaj3 = zaj(bufferedReader);
                if (zaj3 == ',') {
                    zaa = zaa(bufferedReader);
                } else if (zaj3 != '}') {
                    stringBuilder = new StringBuilder(55);
                    stringBuilder.append("Expected end of object or field separator, but found: ");
                    stringBuilder.append(zaj3);
                    throw new ParseException(stringBuilder.toString());
                } else {
                    zaa = null;
                }
            }
        }
        zak(1);
        return true;
    }

    private final String zaa(BufferedReader bufferedReader) throws ParseException, IOException {
        this.zaql.push(Integer.valueOf(2));
        char zaj = zaj(bufferedReader);
        if (zaj == '\"') {
            this.zaql.push(Integer.valueOf(3));
            String zab = zab(bufferedReader, this.zaqb, this.zaqd, null);
            zak(3);
            if (zaj(bufferedReader) == ':') {
                return zab;
            }
            throw new ParseException("Expected key/value separator");
        } else if (zaj == ']') {
            zak(2);
            zak(1);
            zak(5);
            return null;
        } else if (zaj != '}') {
            StringBuilder stringBuilder = new StringBuilder(19);
            stringBuilder.append("Unexpected token: ");
            stringBuilder.append(zaj);
            throw new ParseException(stringBuilder.toString());
        } else {
            zak(2);
            return null;
        }
    }

    private final String zab(BufferedReader bufferedReader) throws ParseException, IOException {
        StringBuilder stringBuilder;
        bufferedReader.mark(1024);
        char zaj = zaj(bufferedReader);
        if (zaj != '\"') {
            if (zaj != ',') {
                int i = 1;
                if (zaj == '[') {
                    this.zaql.push(Integer.valueOf(5));
                    bufferedReader.mark(32);
                    if (zaj(bufferedReader) == ']') {
                        zak(5);
                    } else {
                        bufferedReader.reset();
                        int i2 = 0;
                        int i3 = i2;
                        while (i > 0) {
                            char zaj2 = zaj(bufferedReader);
                            if (zaj2 == 0) {
                                throw new ParseException("Unexpected EOF while parsing array");
                            } else if (Character.isISOControl(zaj2)) {
                                throw new ParseException("Unexpected control character while reading array");
                            } else {
                                if (zaj2 == '\"' && i2 == 0) {
                                    i3 ^= 1;
                                }
                                if (zaj2 == '[' && i3 == 0) {
                                    i++;
                                }
                                if (zaj2 == ']' && i3 == 0) {
                                    i--;
                                }
                                i2 = (zaj2 != '\\' || i3 == 0) ? 0 : i2 ^ 1;
                            }
                        }
                        zak(5);
                    }
                } else if (zaj != '{') {
                    bufferedReader.reset();
                    zaa(bufferedReader, this.zaqc);
                } else {
                    this.zaql.push(Integer.valueOf(1));
                    bufferedReader.mark(32);
                    zaj = zaj(bufferedReader);
                    if (zaj == '}') {
                        zak(1);
                    } else if (zaj == '\"') {
                        bufferedReader.reset();
                        zaa(bufferedReader);
                        do {
                        } while (zab(bufferedReader) != null);
                        zak(1);
                    } else {
                        stringBuilder = new StringBuilder(18);
                        stringBuilder.append("Unexpected token ");
                        stringBuilder.append(zaj);
                        throw new ParseException(stringBuilder.toString());
                    }
                }
            }
            throw new ParseException("Missing value");
        } else if (bufferedReader.read(this.zaqa) == -1) {
            throw new ParseException("Unexpected EOF while parsing string");
        } else {
            zaj = this.zaqa[0];
            int i4 = 0;
            do {
                if (zaj != '\"' || i4 != 0) {
                    i4 = zaj == '\\' ? i4 ^ 1 : 0;
                    if (bufferedReader.read(this.zaqa) == -1) {
                        throw new ParseException("Unexpected EOF while parsing string");
                    }
                    zaj = this.zaqa[0];
                }
            } while (!Character.isISOControl(zaj));
            throw new ParseException("Unexpected control character while reading string");
        }
        zaj = zaj(bufferedReader);
        if (zaj == ',') {
            zak(2);
            return zaa(bufferedReader);
        } else if (zaj != '}') {
            stringBuilder = new StringBuilder(18);
            stringBuilder.append("Unexpected token ");
            stringBuilder.append(zaj);
            throw new ParseException(stringBuilder.toString());
        } else {
            zak(2);
            return null;
        }
    }

    private final String zac(BufferedReader bufferedReader) throws ParseException, IOException {
        return zaa(bufferedReader, this.zaqb, this.zaqd, null);
    }

    private final <O> ArrayList<O> zaa(BufferedReader bufferedReader, zaa<O> zaa) throws ParseException, IOException {
        char zaj = zaj(bufferedReader);
        if (zaj == 'n') {
            zab(bufferedReader, zaqf);
            return null;
        } else if (zaj != '[') {
            throw new ParseException("Expected start of array");
        } else {
            this.zaql.push(Integer.valueOf(5));
            ArrayList arrayList = new ArrayList();
            while (true) {
                bufferedReader.mark(1024);
                char zaj2 = zaj(bufferedReader);
                if (zaj2 == 0) {
                    throw new ParseException("Unexpected EOF");
                } else if (zaj2 != ',') {
                    if (zaj2 != ']') {
                        bufferedReader.reset();
                        arrayList.add(zaa.zah(this, bufferedReader));
                    } else {
                        zak(5);
                        return arrayList;
                    }
                }
            }
        }
    }

    private final String zaa(BufferedReader bufferedReader, char[] cArr, StringBuilder stringBuilder, char[] cArr2) throws ParseException, IOException {
        char zaj = zaj(bufferedReader);
        if (zaj == '\"') {
            return zab(bufferedReader, cArr, stringBuilder, cArr2);
        }
        if (zaj != 'n') {
            throw new ParseException("Expected string");
        }
        zab(bufferedReader, zaqf);
        return null;
    }

    private static String zab(BufferedReader bufferedReader, char[] cArr, StringBuilder stringBuilder, char[] cArr2) throws ParseException, IOException {
        stringBuilder.setLength(0);
        bufferedReader.mark(cArr.length);
        int i = 0;
        int i2 = i;
        while (true) {
            int read = bufferedReader.read(cArr);
            if (read != -1) {
                int i3 = i2;
                i2 = i;
                for (i = 0; i < read; i++) {
                    char c = cArr[i];
                    if (Character.isISOControl(c)) {
                        int i4;
                        if (cArr2 != null) {
                            for (char c2 : cArr2) {
                                if (c2 == c) {
                                    i4 = 1;
                                    break;
                                }
                            }
                        }
                        i4 = 0;
                        if (i4 == 0) {
                            throw new ParseException("Unexpected control character while reading string");
                        }
                    }
                    if (c == '\"' && i2 == 0) {
                        stringBuilder.append(cArr, 0, i);
                        bufferedReader.reset();
                        bufferedReader.skip((long) (i + 1));
                        if (i3 != 0) {
                            return JsonUtils.unescapeString(stringBuilder.toString());
                        }
                        return stringBuilder.toString();
                    }
                    if (c == '\\') {
                        i2 ^= 1;
                        i3 = 1;
                    } else {
                        i2 = 0;
                    }
                }
                stringBuilder.append(cArr, 0, read);
                bufferedReader.mark(cArr.length);
                i = i2;
                i2 = i3;
            } else {
                throw new ParseException("Unexpected EOF while parsing string");
            }
        }
    }

    private final int zad(BufferedReader bufferedReader) throws ParseException, IOException {
        int zaa = zaa(bufferedReader, this.zaqc);
        if (zaa == 0) {
            return 0;
        }
        char[] cArr = this.zaqc;
        if (zaa > 0) {
            int i;
            int i2;
            int i3;
            int i4;
            if (cArr[0] == '-') {
                i = Integer.MIN_VALUE;
                i2 = 1;
                i3 = i2;
            } else {
                i3 = 0;
                i = -2147483647;
                i2 = i3;
            }
            if (i2 < zaa) {
                i4 = i2 + 1;
                i2 = Character.digit(cArr[i2], 10);
                if (i2 < 0) {
                    throw new ParseException("Unexpected non-digit character");
                }
                i2 = -i2;
            } else {
                int i5 = i2;
                i2 = 0;
                i4 = i5;
            }
            while (i4 < zaa) {
                int i6 = i4 + 1;
                i4 = Character.digit(cArr[i4], 10);
                if (i4 < 0) {
                    throw new ParseException("Unexpected non-digit character");
                } else if (i2 < -214748364) {
                    throw new ParseException("Number too large");
                } else {
                    i2 *= 10;
                    if (i2 < i + i4) {
                        throw new ParseException("Number too large");
                    }
                    i2 -= i4;
                    i4 = i6;
                }
            }
            if (i3 == 0) {
                return -i2;
            }
            if (i4 > 1) {
                return i2;
            }
            throw new ParseException("No digits to parse");
        }
        throw new ParseException("No number to parse");
    }

    private final long zae(BufferedReader bufferedReader) throws ParseException, IOException {
        int zaa = zaa(bufferedReader, this.zaqc);
        if (zaa == 0) {
            return 0;
        }
        char[] cArr = this.zaqc;
        if (zaa > 0) {
            long j;
            int i;
            int digit;
            long j2;
            int i2 = 0;
            if (cArr[0] == '-') {
                j = Long.MIN_VALUE;
                i2 = 1;
            } else {
                j = C.TIME_UNSET;
            }
            int i3 = i2;
            if (i2 < zaa) {
                i = i2 + 1;
                digit = Character.digit(cArr[i2], 10);
                if (digit < 0) {
                    throw new ParseException("Unexpected non-digit character");
                }
                j2 = (long) (-digit);
            } else {
                j2 = 0;
                i = i2;
            }
            while (i < zaa) {
                digit = i + 1;
                i = Character.digit(cArr[i], 10);
                if (i < 0) {
                    throw new ParseException("Unexpected non-digit character");
                } else if (j2 < -922337203685477580L) {
                    throw new ParseException("Number too large");
                } else {
                    j2 *= 10;
                    long j3 = (long) i;
                    if (j2 < j + j3) {
                        throw new ParseException("Number too large");
                    }
                    i = digit;
                    j2 -= j3;
                }
            }
            if (i3 == 0) {
                return -j2;
            }
            if (i > 1) {
                return j2;
            }
            throw new ParseException("No digits to parse");
        }
        throw new ParseException("No number to parse");
    }

    private final BigInteger zaf(BufferedReader bufferedReader) throws ParseException, IOException {
        int zaa = zaa(bufferedReader, this.zaqc);
        if (zaa == 0) {
            return null;
        }
        return new BigInteger(new String(this.zaqc, 0, zaa));
    }

    private final boolean zaa(BufferedReader bufferedReader, boolean z) throws ParseException, IOException {
        while (true) {
            char zaj = zaj(bufferedReader);
            if (zaj != '\"') {
                if (zaj == 'f') {
                    zab(bufferedReader, z ? zaqj : zaqi);
                    return false;
                } else if (zaj == 'n') {
                    zab(bufferedReader, zaqf);
                    return false;
                } else if (zaj != 't') {
                    StringBuilder stringBuilder = new StringBuilder(19);
                    stringBuilder.append("Unexpected token: ");
                    stringBuilder.append(zaj);
                    throw new ParseException(stringBuilder.toString());
                } else {
                    zab(bufferedReader, z ? zaqh : zaqg);
                    return true;
                }
            } else if (z) {
                throw new ParseException("No boolean value found in string");
            } else {
                z = true;
            }
        }
    }

    private final float zag(BufferedReader bufferedReader) throws ParseException, IOException {
        int zaa = zaa(bufferedReader, this.zaqc);
        if (zaa == 0) {
            return 0.0f;
        }
        return Float.parseFloat(new String(this.zaqc, 0, zaa));
    }

    private final double zah(BufferedReader bufferedReader) throws ParseException, IOException {
        int zaa = zaa(bufferedReader, this.zaqc);
        if (zaa == 0) {
            return 0.0d;
        }
        return Double.parseDouble(new String(this.zaqc, 0, zaa));
    }

    private final BigDecimal zai(BufferedReader bufferedReader) throws ParseException, IOException {
        int zaa = zaa(bufferedReader, this.zaqc);
        if (zaa == 0) {
            return null;
        }
        return new BigDecimal(new String(this.zaqc, 0, zaa));
    }

    private final <T extends FastJsonResponse> ArrayList<T> zaa(BufferedReader bufferedReader, Field<?, ?> field) throws ParseException, IOException {
        ArrayList arrayList = new ArrayList();
        char zaj = zaj(bufferedReader);
        StringBuilder stringBuilder;
        if (zaj == ']') {
            zak(5);
            return arrayList;
        } else if (zaj == 'n') {
            zab(bufferedReader, zaqf);
            zak(5);
            return null;
        } else if (zaj != '{') {
            stringBuilder = new StringBuilder(19);
            stringBuilder.append("Unexpected token: ");
            stringBuilder.append(zaj);
            throw new ParseException(stringBuilder.toString());
        } else {
            this.zaql.push(Integer.valueOf(1));
            while (true) {
                try {
                    FastJsonResponse zacp = field.zacp();
                    if (!zaa(bufferedReader, zacp)) {
                        return arrayList;
                    }
                    arrayList.add(zacp);
                    zaj = zaj(bufferedReader);
                    if (zaj != ',') {
                        if (zaj != ']') {
                            stringBuilder = new StringBuilder(19);
                            stringBuilder.append("Unexpected token: ");
                            stringBuilder.append(zaj);
                            throw new ParseException(stringBuilder.toString());
                        }
                        zak(5);
                        return arrayList;
                    } else if (zaj(bufferedReader) != '{') {
                        throw new ParseException("Expected start of next object in array");
                    } else {
                        this.zaql.push(Integer.valueOf(1));
                    }
                } catch (InstantiationException e) {
                    throw new ParseException("Error instantiating inner object", e);
                } catch (IllegalAccessException e2) {
                    throw new ParseException("Error instantiating inner object", e2);
                }
            }
        }
    }

    private final char zaj(BufferedReader bufferedReader) throws ParseException, IOException {
        if (bufferedReader.read(this.zaqa) == -1) {
            return 0;
        }
        while (Character.isWhitespace(this.zaqa[0])) {
            if (bufferedReader.read(this.zaqa) == -1) {
                return 0;
            }
        }
        return this.zaqa[0];
    }

    private final int zaa(BufferedReader bufferedReader, char[] cArr) throws ParseException, IOException {
        char zaj = zaj(bufferedReader);
        if (zaj == 0) {
            throw new ParseException("Unexpected EOF");
        } else if (zaj == ',') {
            throw new ParseException("Missing value");
        } else if (zaj == 'n') {
            zab(bufferedReader, zaqf);
            return 0;
        } else {
            int i;
            bufferedReader.mark(1024);
            if (zaj == '\"') {
                i = 0;
                int i2 = i;
                while (i < cArr.length && bufferedReader.read(cArr, i, 1) != -1) {
                    char c = cArr[i];
                    if (Character.isISOControl(c)) {
                        throw new ParseException("Unexpected control character while reading string");
                    } else if (c == '\"' && i2 == 0) {
                        bufferedReader.reset();
                        bufferedReader.skip((long) (i + 1));
                        return i;
                    } else {
                        i2 = c == '\\' ? i2 ^ 1 : 0;
                        i++;
                    }
                }
            } else {
                cArr[0] = zaj;
                i = 1;
                while (i < cArr.length && bufferedReader.read(cArr, i, 1) != -1) {
                    if (cArr[i] == '}' || cArr[i] == ',' || Character.isWhitespace(cArr[i]) || cArr[i] == ']') {
                        bufferedReader.reset();
                        bufferedReader.skip((long) (i - 1));
                        cArr[i] = 0;
                        return i;
                    }
                    i++;
                }
            }
            if (i == cArr.length) {
                throw new ParseException("Absurdly long value");
            }
            throw new ParseException("Unexpected EOF");
        }
    }

    private final void zab(BufferedReader bufferedReader, char[] cArr) throws ParseException, IOException {
        int i = 0;
        while (i < cArr.length) {
            int read = bufferedReader.read(this.zaqb, 0, cArr.length - i);
            if (read == -1) {
                throw new ParseException("Unexpected EOF");
            }
            for (int i2 = 0; i2 < read; i2++) {
                if (cArr[i2 + i] != this.zaqb[i2]) {
                    throw new ParseException("Unexpected character");
                }
            }
            i += read;
        }
    }

    private final void zak(int i) throws ParseException {
        if (this.zaql.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder(46);
            stringBuilder.append("Expected state ");
            stringBuilder.append(i);
            stringBuilder.append(" but had empty stack");
            throw new ParseException(stringBuilder.toString());
        }
        int intValue = ((Integer) this.zaql.pop()).intValue();
        if (intValue != i) {
            StringBuilder stringBuilder2 = new StringBuilder(46);
            stringBuilder2.append("Expected state ");
            stringBuilder2.append(i);
            stringBuilder2.append(" but had ");
            stringBuilder2.append(intValue);
            throw new ParseException(stringBuilder2.toString());
        }
    }
}
