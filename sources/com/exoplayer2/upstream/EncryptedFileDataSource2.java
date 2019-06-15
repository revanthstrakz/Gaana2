package com.exoplayer2.upstream;

import android.net.Uri;
import com.gaana.application.GaanaApplication;
import com.google.android.exoplayer2.upstream.BaseDataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class EncryptedFileDataSource2 extends BaseDataSource {
    SecretKeySpec a;
    IvParameterSpec b;
    private a c;
    private Uri d;
    private long e;
    private boolean f;
    private Cipher g;

    public static final class EncryptedFileDataSourceException extends IOException {
        public EncryptedFileDataSourceException(IOException iOException) {
            super(iOException);
        }
    }

    public static class a extends CipherInputStream {
        private InputStream a;
        private Cipher b;
        private SecretKeySpec c;
        private IvParameterSpec d;

        public a(InputStream inputStream, Cipher cipher, SecretKeySpec secretKeySpec, IvParameterSpec ivParameterSpec) {
            super(inputStream, cipher);
            this.a = inputStream;
            this.b = cipher;
            this.c = secretKeySpec;
            this.d = ivParameterSpec;
        }

        public int read(byte[] bArr, int i, int i2) throws IOException {
            return super.read(bArr, i, i2);
        }

        public long a(long j) throws IOException {
            long skip = this.a.skip(j);
            try {
                AlgorithmParameterSpec ivParameterSpec;
                int i = (int) (j % 16);
                byte[] toByteArray = new BigInteger(1, this.d.getIV()).add(BigInteger.valueOf((j - ((long) i)) / 16)).toByteArray();
                if (toByteArray.length < 16) {
                    byte[] bArr = new byte[16];
                    System.arraycopy(toByteArray, 0, bArr, 16 - toByteArray.length, toByteArray.length);
                    ivParameterSpec = new IvParameterSpec(bArr);
                } else {
                    ivParameterSpec = new IvParameterSpec(toByteArray, toByteArray.length - 16, 16);
                }
                this.b.init(1, this.c, ivParameterSpec);
                toByteArray = new byte[i];
                this.b.update(toByteArray, 0, i, toByteArray);
                Arrays.fill(toByteArray, (byte) 0);
                return skip;
            } catch (Exception unused) {
                return 0;
            }
        }

        public int available() throws IOException {
            return this.a.available();
        }
    }

    public EncryptedFileDataSource2() {
        super(false);
        try {
            this.g = Cipher.getInstance(GaanaApplication.getEncryptionKey(2));
            this.a = new SecretKeySpec(new StringBuilder(GaanaApplication.getEncryptionKey(0)).reverse().toString().getBytes(), "AES");
            this.b = new IvParameterSpec(new StringBuilder(GaanaApplication.getEncryptionKey(1)).reverse().toString().getBytes());
            this.g.init(2, this.a, this.b);
        } catch (NoSuchAlgorithmException e) {
            ThrowableExtension.printStackTrace(e);
        } catch (NoSuchPaddingException e2) {
            ThrowableExtension.printStackTrace(e2);
        } catch (InvalidAlgorithmParameterException e3) {
            ThrowableExtension.printStackTrace(e3);
        } catch (InvalidKeyException e4) {
            ThrowableExtension.printStackTrace(e4);
        }
    }

    public long open(DataSpec dataSpec) throws IOException {
        if (this.f) {
            return this.e;
        }
        this.d = dataSpec.uri;
        try {
            a();
            a(dataSpec);
            b(dataSpec);
            this.f = true;
            return this.e;
        } catch (IOException e) {
            throw new EncryptedFileDataSourceException(e);
        }
    }

    private void a() throws FileNotFoundException {
        this.c = new a(new FileInputStream(new File(this.d.getPath())), this.g, this.a, this.b);
    }

    private void a(DataSpec dataSpec) throws IOException {
        this.c.a(dataSpec.position);
    }

    private void b(DataSpec dataSpec) throws IOException {
        if (dataSpec.length != -1) {
            this.e = dataSpec.length;
            return;
        }
        this.e = (long) this.c.available();
        if (this.e == 2147483647L) {
            this.e = -1;
        }
    }

    public int read(byte[] bArr, int i, int i2) throws IOException {
        if (i2 == 0) {
            return 0;
        }
        if (this.e == 0) {
            return -1;
        }
        try {
            int read = this.c.read(bArr, i, a(i2));
            if (read != -1) {
                if (this.e != -1) {
                    this.e -= (long) read;
                }
                return read;
            } else if (this.e == -1) {
                return -1;
            } else {
                throw new EncryptedFileDataSourceException(new EOFException());
            }
        } catch (IOException e) {
            throw new EncryptedFileDataSourceException(e);
        }
    }

    public Uri getUri() {
        return this.d;
    }

    private int a(int i) {
        if (this.e == -1) {
            return i;
        }
        return (int) Math.min(this.e, (long) i);
    }

    public void close() throws EncryptedFileDataSourceException {
        this.d = null;
        try {
            if (this.c != null) {
                this.c.close();
            }
            this.c = null;
            if (this.f) {
                this.f = false;
            }
        } catch (IOException e) {
            throw new EncryptedFileDataSourceException(e);
        } catch (Throwable th) {
            this.c = null;
            if (this.f) {
                this.f = false;
            }
        }
    }
}
