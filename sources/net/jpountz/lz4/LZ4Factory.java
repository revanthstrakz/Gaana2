package net.jpountz.lz4;

import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import net.jpountz.util.Native;
import net.jpountz.util.Utils;

public final class LZ4Factory {
    private static LZ4Factory JAVA_SAFE_INSTANCE;
    private static LZ4Factory JAVA_UNSAFE_INSTANCE;
    private static LZ4Factory NATIVE_INSTANCE;
    private final LZ4Compressor fastCompressor;
    private final LZ4FastDecompressor fastDecompressor;
    private final LZ4Compressor highCompressor;
    private final LZ4Compressor[] highCompressors = new LZ4Compressor[18];
    private final String impl;
    private final LZ4SafeDecompressor safeDecompressor;

    private static LZ4Factory instance(String str) {
        try {
            return new LZ4Factory(str);
        } catch (Exception e) {
            throw new AssertionError(e);
        }
    }

    public static synchronized LZ4Factory nativeInstance() {
        LZ4Factory lZ4Factory;
        synchronized (LZ4Factory.class) {
            if (NATIVE_INSTANCE == null) {
                NATIVE_INSTANCE = instance("JNI");
            }
            lZ4Factory = NATIVE_INSTANCE;
        }
        return lZ4Factory;
    }

    public static synchronized LZ4Factory safeInstance() {
        LZ4Factory lZ4Factory;
        synchronized (LZ4Factory.class) {
            if (JAVA_SAFE_INSTANCE == null) {
                JAVA_SAFE_INSTANCE = instance("JavaSafe");
            }
            lZ4Factory = JAVA_SAFE_INSTANCE;
        }
        return lZ4Factory;
    }

    public static synchronized LZ4Factory unsafeInstance() {
        LZ4Factory lZ4Factory;
        synchronized (LZ4Factory.class) {
            if (JAVA_UNSAFE_INSTANCE == null) {
                JAVA_UNSAFE_INSTANCE = instance("JavaUnsafe");
            }
            lZ4Factory = JAVA_UNSAFE_INSTANCE;
        }
        return lZ4Factory;
    }

    public static LZ4Factory fastestJavaInstance() {
        if (!Utils.isUnalignedAccessAllowed()) {
            return safeInstance();
        }
        try {
            return unsafeInstance();
        } catch (Throwable unused) {
            return safeInstance();
        }
    }

    public static LZ4Factory fastestInstance() {
        if (!Native.isLoaded() && Native.class.getClassLoader() != ClassLoader.getSystemClassLoader()) {
            return fastestJavaInstance();
        }
        try {
            return nativeInstance();
        } catch (Throwable unused) {
            return fastestJavaInstance();
        }
    }

    private static <T> T classInstance(String str) throws NoSuchFieldException, SecurityException, ClassNotFoundException, IllegalArgumentException, IllegalAccessException {
        ClassLoader classLoader = LZ4Factory.class.getClassLoader();
        if (classLoader == null) {
            classLoader = ClassLoader.getSystemClassLoader();
        }
        return classLoader.loadClass(str).getField("INSTANCE").get(null);
    }

    private LZ4Factory(String str) throws ClassNotFoundException, NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InstantiationException, InvocationTargetException {
        String str2 = str;
        this.impl = str2;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("net.jpountz.lz4.LZ4");
        stringBuilder.append(str2);
        stringBuilder.append("Compressor");
        this.fastCompressor = (LZ4Compressor) classInstance(stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("net.jpountz.lz4.LZ4HC");
        stringBuilder.append(str2);
        stringBuilder.append("Compressor");
        this.highCompressor = (LZ4Compressor) classInstance(stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("net.jpountz.lz4.LZ4");
        stringBuilder.append(str2);
        stringBuilder.append("FastDecompressor");
        this.fastDecompressor = (LZ4FastDecompressor) classInstance(stringBuilder.toString());
        stringBuilder = new StringBuilder();
        stringBuilder.append("net.jpountz.lz4.LZ4");
        stringBuilder.append(str2);
        stringBuilder.append("SafeDecompressor");
        this.safeDecompressor = (LZ4SafeDecompressor) classInstance(stringBuilder.toString());
        Constructor declaredConstructor = this.highCompressor.getClass().getDeclaredConstructor(new Class[]{Integer.TYPE});
        this.highCompressors[9] = this.highCompressor;
        for (int i = 1; i <= 17; i++) {
            if (i != 9) {
                this.highCompressors[i] = (LZ4Compressor) declaredConstructor.newInstance(new Object[]{Integer.valueOf(i)});
            }
        }
        byte[] bArr = new byte[]{(byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 32, (byte) 97, (byte) 98, (byte) 99, (byte) 100, (byte) 101, (byte) 102, (byte) 103, (byte) 104, (byte) 105, (byte) 106};
        for (LZ4Compressor lZ4Compressor : Arrays.asList(new LZ4Compressor[]{this.fastCompressor, this.highCompressor})) {
            int maxCompressedLength = lZ4Compressor.maxCompressedLength(bArr.length);
            byte[] bArr2 = new byte[maxCompressedLength];
            int compress = lZ4Compressor.compress(bArr, 0, bArr.length, bArr2, 0, maxCompressedLength);
            byte[] bArr3 = new byte[bArr.length];
            this.fastDecompressor.decompress(bArr2, 0, bArr3, 0, bArr.length);
            if (Arrays.equals(bArr, bArr3)) {
                Arrays.fill(bArr3, (byte) 0);
                if (this.safeDecompressor.decompress(bArr2, 0, compress, bArr3, 0) == bArr.length) {
                    if (!Arrays.equals(bArr, bArr3)) {
                    }
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
    }

    public LZ4Compressor fastCompressor() {
        return this.fastCompressor;
    }

    public LZ4Compressor highCompressor() {
        return this.highCompressor;
    }

    public LZ4Compressor highCompressor(int i) {
        if (i > 17) {
            i = 17;
        } else if (i < 1) {
            i = 9;
        }
        return this.highCompressors[i];
    }

    public LZ4FastDecompressor fastDecompressor() {
        return this.fastDecompressor;
    }

    public LZ4SafeDecompressor safeDecompressor() {
        return this.safeDecompressor;
    }

    public LZ4UnknownSizeDecompressor unknownSizeDecompressor() {
        return safeDecompressor();
    }

    public LZ4Decompressor decompressor() {
        return fastDecompressor();
    }

    public static void main(String[] strArr) {
        PrintStream printStream = System.out;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Fastest instance is ");
        stringBuilder.append(fastestInstance());
        printStream.println(stringBuilder.toString());
        printStream = System.out;
        stringBuilder = new StringBuilder();
        stringBuilder.append("Fastest Java instance is ");
        stringBuilder.append(fastestJavaInstance());
        printStream.println(stringBuilder.toString());
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getClass().getSimpleName());
        stringBuilder.append(":");
        stringBuilder.append(this.impl);
        return stringBuilder.toString();
    }
}
