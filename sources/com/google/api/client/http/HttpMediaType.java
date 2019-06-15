package com.google.api.client.http;

import com.google.android.exoplayer2.util.MimeTypes;
import com.google.api.client.util.Preconditions;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class HttpMediaType {
    private static final Pattern FULL_MEDIA_TYPE_REGEX;
    private static final Pattern PARAMETER_REGEX;
    private static final Pattern TOKEN_REGEX = Pattern.compile("[\\p{ASCII}&&[^\\p{Cntrl} ;/=\\[\\]\\(\\)\\<\\>\\@\\,\\:\\\"\\?\\=]]+");
    private static final Pattern TYPE_REGEX = Pattern.compile("[\\w!#$&.+\\-\\^_]+|[*]");
    private String cachedBuildResult;
    private final SortedMap<String, String> parameters = new TreeMap();
    private String subType = "octet-stream";
    private String type = MimeTypes.BASE_TYPE_APPLICATION;

    static {
        String str = "[^\\s/=;\"]+";
        String valueOf = String.valueOf(String.valueOf(str));
        String valueOf2 = String.valueOf(String.valueOf(str));
        String valueOf3 = String.valueOf(String.valueOf(";.*"));
        StringBuilder stringBuilder = new StringBuilder(((14 + valueOf.length()) + valueOf2.length()) + valueOf3.length());
        stringBuilder.append("\\s*(");
        stringBuilder.append(valueOf);
        stringBuilder.append(")/(");
        stringBuilder.append(valueOf2);
        stringBuilder.append(")");
        stringBuilder.append("\\s*(");
        stringBuilder.append(valueOf3);
        stringBuilder.append(")?");
        FULL_MEDIA_TYPE_REGEX = Pattern.compile(stringBuilder.toString(), 32);
        valueOf3 = String.valueOf(String.valueOf("\"([^\"]*)\""));
        valueOf = String.valueOf(String.valueOf("[^\\s;\"]*"));
        StringBuilder stringBuilder2 = new StringBuilder((1 + valueOf3.length()) + valueOf.length());
        stringBuilder2.append(valueOf3);
        stringBuilder2.append("|");
        stringBuilder2.append(valueOf);
        valueOf3 = stringBuilder2.toString();
        str = String.valueOf(String.valueOf(str));
        valueOf3 = String.valueOf(String.valueOf(valueOf3));
        StringBuilder stringBuilder3 = new StringBuilder((12 + str.length()) + valueOf3.length());
        stringBuilder3.append("\\s*;\\s*(");
        stringBuilder3.append(str);
        stringBuilder3.append(")");
        stringBuilder3.append("=(");
        stringBuilder3.append(valueOf3);
        stringBuilder3.append(")");
        PARAMETER_REGEX = Pattern.compile(stringBuilder3.toString());
    }

    public HttpMediaType(String str, String str2) {
        setType(str);
        setSubType(str2);
    }

    public HttpMediaType(String str) {
        fromString(str);
    }

    public HttpMediaType setType(String str) {
        Preconditions.checkArgument(TYPE_REGEX.matcher(str).matches(), "Type contains reserved characters");
        this.type = str;
        this.cachedBuildResult = null;
        return this;
    }

    public String getType() {
        return this.type;
    }

    public HttpMediaType setSubType(String str) {
        Preconditions.checkArgument(TYPE_REGEX.matcher(str).matches(), "Subtype contains reserved characters");
        this.subType = str;
        this.cachedBuildResult = null;
        return this;
    }

    public String getSubType() {
        return this.subType;
    }

    private HttpMediaType fromString(String str) {
        Matcher matcher = FULL_MEDIA_TYPE_REGEX.matcher(str);
        Preconditions.checkArgument(matcher.matches(), "Type must be in the 'maintype/subtype; parameter=value' format");
        setType(matcher.group(1));
        setSubType(matcher.group(2));
        str = matcher.group(3);
        if (str != null) {
            matcher = PARAMETER_REGEX.matcher(str);
            while (matcher.find()) {
                String group = matcher.group(1);
                String group2 = matcher.group(3);
                if (group2 == null) {
                    group2 = matcher.group(2);
                }
                setParameter(group, group2);
            }
        }
        return this;
    }

    public HttpMediaType setParameter(String str, String str2) {
        if (str2 == null) {
            removeParameter(str);
            return this;
        }
        Preconditions.checkArgument(TOKEN_REGEX.matcher(str).matches(), "Name contains reserved characters");
        this.cachedBuildResult = null;
        this.parameters.put(str.toLowerCase(), str2);
        return this;
    }

    public String getParameter(String str) {
        return (String) this.parameters.get(str.toLowerCase());
    }

    public HttpMediaType removeParameter(String str) {
        this.cachedBuildResult = null;
        this.parameters.remove(str.toLowerCase());
        return this;
    }

    public void clearParameters() {
        this.cachedBuildResult = null;
        this.parameters.clear();
    }

    public Map<String, String> getParameters() {
        return Collections.unmodifiableMap(this.parameters);
    }

    static boolean matchesToken(String str) {
        return TOKEN_REGEX.matcher(str).matches();
    }

    private static String quoteString(String str) {
        str = String.valueOf(String.valueOf(str.replace("\\", "\\\\").replace("\"", "\\\"")));
        StringBuilder stringBuilder = new StringBuilder(2 + str.length());
        stringBuilder.append("\"");
        stringBuilder.append(str);
        stringBuilder.append("\"");
        return stringBuilder.toString();
    }

    public String build() {
        if (this.cachedBuildResult != null) {
            return this.cachedBuildResult;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.type);
        stringBuilder.append('/');
        stringBuilder.append(this.subType);
        if (this.parameters != null) {
            for (Entry entry : this.parameters.entrySet()) {
                String str = (String) entry.getValue();
                stringBuilder.append("; ");
                stringBuilder.append((String) entry.getKey());
                stringBuilder.append("=");
                if (!matchesToken(str)) {
                    str = quoteString(str);
                }
                stringBuilder.append(str);
            }
        }
        this.cachedBuildResult = stringBuilder.toString();
        return this.cachedBuildResult;
    }

    public String toString() {
        return build();
    }

    public boolean equalsIgnoreParameters(HttpMediaType httpMediaType) {
        return httpMediaType != null && getType().equalsIgnoreCase(httpMediaType.getType()) && getSubType().equalsIgnoreCase(httpMediaType.getSubType());
    }

    public static boolean equalsIgnoreParameters(String str, String str2) {
        return (str == null && str2 == null) || !(str == null || str2 == null || !new HttpMediaType(str).equalsIgnoreParameters(new HttpMediaType(str2)));
    }

    public HttpMediaType setCharsetParameter(Charset charset) {
        setParameter("charset", charset == null ? null : charset.name());
        return this;
    }

    public Charset getCharsetParameter() {
        String parameter = getParameter("charset");
        if (parameter == null) {
            return null;
        }
        return Charset.forName(parameter);
    }

    public int hashCode() {
        return build().hashCode();
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (!(obj instanceof HttpMediaType)) {
            return false;
        }
        HttpMediaType httpMediaType = (HttpMediaType) obj;
        if (equalsIgnoreParameters(httpMediaType) && this.parameters.equals(httpMediaType.parameters)) {
            z = true;
        }
        return z;
    }
}
