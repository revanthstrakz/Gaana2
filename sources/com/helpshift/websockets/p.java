package com.helpshift.websockets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.security.SecureRandom;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class p {
    private static final SecureRandom a = new SecureRandom();

    public static byte[] a(String str) {
        if (str == null) {
            return null;
        }
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    public static String a(byte[] bArr) {
        return bArr == null ? null : a(bArr, 0, bArr.length);
    }

    public static String a(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            return null;
        }
        try {
            return new String(bArr, i, i2, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return null;
        } catch (IndexOutOfBoundsException unused2) {
            return null;
        }
    }

    public static byte[] b(byte[] bArr) {
        a.nextBytes(bArr);
        return bArr;
    }

    public static byte[] a(int i) {
        return b(new byte[i]);
    }

    public static String b(int i) {
        switch (i) {
            case 0:
                return "CONTINUATION";
            case 1:
                return "TEXT";
            case 2:
                return "BINARY";
            default:
                switch (i) {
                    case 8:
                        return "CLOSE";
                    case 9:
                        return "PING";
                    case 10:
                        return "PONG";
                    default:
                        if (1 <= i && i <= 7) {
                            return String.format("DATA(0x%X)", new Object[]{Integer.valueOf(i)});
                        } else if (8 > i || i > 15) {
                            return String.format("0x%X", new Object[]{Integer.valueOf(i)});
                        } else {
                            return String.format("CONTROL(0x%X)", new Object[]{Integer.valueOf(i)});
                        }
                }
        }
    }

    public static String a(InputStream inputStream, String str) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        while (true) {
            int read = inputStream.read();
            if (read != -1) {
                if (read == 10) {
                    break;
                } else if (read != 13) {
                    byteArrayOutputStream.write(read);
                } else {
                    int read2 = inputStream.read();
                    if (read2 == -1) {
                        byteArrayOutputStream.write(read);
                        break;
                    } else if (read2 == 10) {
                        break;
                    } else {
                        byteArrayOutputStream.write(read);
                        byteArrayOutputStream.write(read2);
                    }
                }
            } else if (byteArrayOutputStream.size() == 0) {
                return null;
            }
        }
        return byteArrayOutputStream.toString(str);
    }

    public static int a(int[] iArr) {
        int i = Integer.MAX_VALUE;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (iArr[i2] < i) {
                i = iArr[i2];
            }
        }
        return i;
    }

    public static int b(int[] iArr) {
        int i = Integer.MIN_VALUE;
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (i < iArr[i2]) {
                i = iArr[i2];
            }
        }
        return i;
    }

    public static String a(Collection<?> collection, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        a(stringBuilder, (Collection) collection, str);
        return stringBuilder.toString();
    }

    private static void a(StringBuilder stringBuilder, Collection<?> collection, String str) {
        Object obj = 1;
        for (Object next : collection) {
            if (obj != null) {
                obj = null;
            } else {
                stringBuilder.append(str);
            }
            stringBuilder.append(next.toString());
        }
    }

    public static String a(URI uri) {
        String host = uri.getHost();
        if (host != null) {
            return host;
        }
        host = b(uri.getRawAuthority());
        if (host != null) {
            return host;
        }
        return c(uri.toString());
    }

    static String b(String str) {
        if (str == null) {
            return null;
        }
        Matcher matcher = Pattern.compile("^(.*@)?([^:]+)(:\\d+)?$").matcher(str);
        if (matcher == null || !matcher.matches()) {
            return null;
        }
        return matcher.group(2);
    }

    static String c(String str) {
        if (str == null) {
            return null;
        }
        Matcher matcher = Pattern.compile("^\\w+://([^@/]*@)?([^:/]+)(:\\d+)?(/.*)?$").matcher(str);
        if (matcher == null || !matcher.matches()) {
            return null;
        }
        return matcher.group(2);
    }
}
