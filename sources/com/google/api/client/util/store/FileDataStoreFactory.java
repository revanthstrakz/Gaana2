package com.google.api.client.util.store;

import com.google.api.client.util.IOUtils;
import com.google.api.client.util.Maps;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.logging.Logger;

public class FileDataStoreFactory extends AbstractDataStoreFactory {
    private static final Logger LOGGER = Logger.getLogger(FileDataStoreFactory.class.getName());
    private final File dataDirectory;

    static class FileDataStore<V extends Serializable> extends AbstractMemoryDataStore<V> {
        private final File dataFile;

        FileDataStore(FileDataStoreFactory fileDataStoreFactory, File file, String str) throws IOException {
            super(fileDataStoreFactory, str);
            this.dataFile = new File(file, str);
            if (IOUtils.isSymbolicLink(this.dataFile)) {
                String valueOf = String.valueOf(String.valueOf(this.dataFile));
                StringBuilder stringBuilder = new StringBuilder(31 + valueOf.length());
                stringBuilder.append("unable to use a symbolic link: ");
                stringBuilder.append(valueOf);
                throw new IOException(stringBuilder.toString());
            } else if (this.dataFile.createNewFile()) {
                this.keyValueMap = Maps.newHashMap();
                save();
            } else {
                this.keyValueMap = (HashMap) IOUtils.deserialize(new FileInputStream(this.dataFile));
            }
        }

        /* Access modifiers changed, original: 0000 */
        public void save() throws IOException {
            IOUtils.serialize(this.keyValueMap, new FileOutputStream(this.dataFile));
        }

        public FileDataStoreFactory getDataStoreFactory() {
            return (FileDataStoreFactory) super.getDataStoreFactory();
        }
    }

    public FileDataStoreFactory(File file) throws IOException {
        file = file.getCanonicalFile();
        this.dataDirectory = file;
        String valueOf;
        StringBuilder stringBuilder;
        if (IOUtils.isSymbolicLink(file)) {
            valueOf = String.valueOf(String.valueOf(file));
            stringBuilder = new StringBuilder(31 + valueOf.length());
            stringBuilder.append("unable to use a symbolic link: ");
            stringBuilder.append(valueOf);
            throw new IOException(stringBuilder.toString());
        } else if (file.exists() || file.mkdirs()) {
            setPermissionsToOwnerOnly(file);
        } else {
            valueOf = String.valueOf(String.valueOf(file));
            stringBuilder = new StringBuilder(28 + valueOf.length());
            stringBuilder.append("unable to create directory: ");
            stringBuilder.append(valueOf);
            throw new IOException(stringBuilder.toString());
        }
    }

    public final File getDataDirectory() {
        return this.dataDirectory;
    }

    /* Access modifiers changed, original: protected */
    public <V extends Serializable> DataStore<V> createDataStore(String str) throws IOException {
        return new FileDataStore(this, this.dataDirectory, str);
    }

    /* JADX WARNING: Missing block: B:6:0x0085, code skipped:
            if (((java.lang.Boolean) r3.invoke(r11, new java.lang.Object[]{java.lang.Boolean.valueOf(false), java.lang.Boolean.valueOf(false)})).booleanValue() == false) goto L_0x0087;
     */
    static void setPermissionsToOwnerOnly(java.io.File r11) throws java.io.IOException {
        /*
        r0 = java.io.File.class;
        r1 = "setReadable";
        r2 = 2;
        r3 = new java.lang.Class[r2];	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r4 = java.lang.Boolean.TYPE;	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r5 = 0;
        r3[r5] = r4;	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r4 = java.lang.Boolean.TYPE;	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r6 = 1;
        r3[r6] = r4;	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r0 = r0.getMethod(r1, r3);	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r1 = java.io.File.class;
        r3 = "setWritable";
        r4 = new java.lang.Class[r2];	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r7 = java.lang.Boolean.TYPE;	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r4[r5] = r7;	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r7 = java.lang.Boolean.TYPE;	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r4[r6] = r7;	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r1 = r1.getMethod(r3, r4);	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r3 = java.io.File.class;
        r4 = "setExecutable";
        r7 = new java.lang.Class[r2];	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r8 = java.lang.Boolean.TYPE;	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r7[r5] = r8;	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r8 = java.lang.Boolean.TYPE;	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r7[r6] = r8;	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r3 = r3.getMethod(r4, r7);	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r4 = new java.lang.Object[r2];	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r7 = java.lang.Boolean.valueOf(r5);	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r4[r5] = r7;	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r7 = java.lang.Boolean.valueOf(r5);	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r4[r6] = r7;	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r4 = r0.invoke(r11, r4);	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r4 = (java.lang.Boolean) r4;	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r4 = r4.booleanValue();	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        if (r4 == 0) goto L_0x0087;
    L_0x0053:
        r4 = new java.lang.Object[r2];	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r7 = java.lang.Boolean.valueOf(r5);	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r4[r5] = r7;	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r7 = java.lang.Boolean.valueOf(r5);	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r4[r6] = r7;	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r4 = r1.invoke(r11, r4);	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r4 = (java.lang.Boolean) r4;	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r4 = r4.booleanValue();	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        if (r4 == 0) goto L_0x0087;
    L_0x006d:
        r4 = new java.lang.Object[r2];	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r7 = java.lang.Boolean.valueOf(r5);	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r4[r5] = r7;	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r7 = java.lang.Boolean.valueOf(r5);	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r4[r6] = r7;	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r4 = r3.invoke(r11, r4);	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r4 = (java.lang.Boolean) r4;	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r4 = r4.booleanValue();	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        if (r4 != 0) goto L_0x00ac;
    L_0x0087:
        r4 = LOGGER;	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r7 = java.lang.String.valueOf(r11);	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r7 = java.lang.String.valueOf(r7);	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r8 = new java.lang.StringBuilder;	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r9 = 44;
        r10 = r7.length();	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r9 = r9 + r10;
        r8.<init>(r9);	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r9 = "unable to change permissions for everybody: ";
        r8.append(r9);	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r8.append(r7);	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r7 = r8.toString();	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r4.warning(r7);	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
    L_0x00ac:
        r4 = new java.lang.Object[r2];	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r7 = java.lang.Boolean.valueOf(r6);	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r4[r5] = r7;	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r7 = java.lang.Boolean.valueOf(r6);	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r4[r6] = r7;	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r0 = r0.invoke(r11, r4);	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r0 = (java.lang.Boolean) r0;	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r0 = r0.booleanValue();	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        if (r0 == 0) goto L_0x00fa;
    L_0x00c6:
        r0 = new java.lang.Object[r2];	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r4 = java.lang.Boolean.valueOf(r6);	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r0[r5] = r4;	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r4 = java.lang.Boolean.valueOf(r6);	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r0[r6] = r4;	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r0 = r1.invoke(r11, r0);	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r0 = (java.lang.Boolean) r0;	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r0 = r0.booleanValue();	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        if (r0 == 0) goto L_0x00fa;
    L_0x00e0:
        r0 = new java.lang.Object[r2];	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r1 = java.lang.Boolean.valueOf(r6);	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r0[r5] = r1;	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r1 = java.lang.Boolean.valueOf(r6);	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r0[r6] = r1;	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r0 = r3.invoke(r11, r0);	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r0 = (java.lang.Boolean) r0;	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r0 = r0.booleanValue();	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        if (r0 != 0) goto L_0x014a;
    L_0x00fa:
        r0 = LOGGER;	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r1 = java.lang.String.valueOf(r11);	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r1 = java.lang.String.valueOf(r1);	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r2 = new java.lang.StringBuilder;	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r3 = 40;
        r4 = r1.length();	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r3 = r3 + r4;
        r2.<init>(r3);	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r3 = "unable to change permissions for owner: ";
        r2.append(r3);	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r2.append(r1);	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r1 = r2.toString();	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        r0.warning(r1);	 Catch:{ InvocationTargetException -> 0x014b, NoSuchMethodException -> 0x0120, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a, IllegalAccessException | IllegalArgumentException | SecurityException -> 0x014a }
        goto L_0x014a;
    L_0x0120:
        r0 = LOGGER;
        r11 = java.lang.String.valueOf(r11);
        r11 = java.lang.String.valueOf(r11);
        r1 = new java.lang.StringBuilder;
        r2 = 93;
        r3 = r11.length();
        r2 = r2 + r3;
        r1.<init>(r2);
        r2 = "Unable to set permissions for ";
        r1.append(r2);
        r1.append(r11);
        r11 = ", likely because you are running a version of Java prior to 1.6";
        r1.append(r11);
        r11 = r1.toString();
        r0.warning(r11);
    L_0x014a:
        return;
    L_0x014b:
        r11 = move-exception;
        r11 = r11.getCause();
        r0 = java.io.IOException.class;
        com.google.api.client.util.Throwables.propagateIfPossible(r11, r0);
        r0 = new java.lang.RuntimeException;
        r0.<init>(r11);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.api.client.util.store.FileDataStoreFactory.setPermissionsToOwnerOnly(java.io.File):void");
    }
}
