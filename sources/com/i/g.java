package com.i;

import com.constants.Constants;
import com.google.devtools.build.android.desugar.runtime.ThrowableExtension;
import com.utilities.d;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketException;
import java.nio.channels.SocketChannel;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

public class g extends SSLSocketFactory {
    private final SSLSocketFactory a;

    public class a extends SSLSocket {
        protected final SSLSocket a;

        a(SSLSocket sSLSocket) {
            this.a = sSLSocket;
        }

        public String[] getSupportedCipherSuites() {
            return this.a.getSupportedCipherSuites();
        }

        public String[] getEnabledCipherSuites() {
            return this.a.getEnabledCipherSuites();
        }

        public void setEnabledCipherSuites(String[] strArr) {
            this.a.setEnabledCipherSuites(strArr);
        }

        public String[] getSupportedProtocols() {
            return this.a.getSupportedProtocols();
        }

        public String[] getEnabledProtocols() {
            return this.a.getEnabledProtocols();
        }

        public void setEnabledProtocols(String[] strArr) {
            this.a.setEnabledProtocols(strArr);
        }

        public SSLSession getSession() {
            return this.a.getSession();
        }

        public void addHandshakeCompletedListener(HandshakeCompletedListener handshakeCompletedListener) {
            this.a.addHandshakeCompletedListener(handshakeCompletedListener);
        }

        public void removeHandshakeCompletedListener(HandshakeCompletedListener handshakeCompletedListener) {
            this.a.removeHandshakeCompletedListener(handshakeCompletedListener);
        }

        public void startHandshake() throws IOException {
            this.a.startHandshake();
        }

        public void setUseClientMode(boolean z) {
            this.a.setUseClientMode(z);
        }

        public boolean getUseClientMode() {
            return this.a.getUseClientMode();
        }

        public void setNeedClientAuth(boolean z) {
            this.a.setNeedClientAuth(z);
        }

        public void setWantClientAuth(boolean z) {
            this.a.setWantClientAuth(z);
        }

        public boolean getNeedClientAuth() {
            return this.a.getNeedClientAuth();
        }

        public boolean getWantClientAuth() {
            return this.a.getWantClientAuth();
        }

        public void setEnableSessionCreation(boolean z) {
            this.a.setEnableSessionCreation(z);
        }

        public boolean getEnableSessionCreation() {
            return this.a.getEnableSessionCreation();
        }

        public void bind(SocketAddress socketAddress) throws IOException {
            this.a.bind(socketAddress);
        }

        public synchronized void close() throws IOException {
            this.a.close();
        }

        public void connect(SocketAddress socketAddress) throws IOException {
            this.a.connect(socketAddress);
        }

        public void connect(SocketAddress socketAddress, int i) throws IOException {
            this.a.connect(socketAddress, i);
        }

        public SocketChannel getChannel() {
            return this.a.getChannel();
        }

        public InetAddress getInetAddress() {
            return this.a.getInetAddress();
        }

        public InputStream getInputStream() throws IOException {
            return this.a.getInputStream();
        }

        public boolean getKeepAlive() throws SocketException {
            return this.a.getKeepAlive();
        }

        public InetAddress getLocalAddress() {
            return this.a.getLocalAddress();
        }

        public int getLocalPort() {
            return this.a.getLocalPort();
        }

        public SocketAddress getLocalSocketAddress() {
            return this.a.getLocalSocketAddress();
        }

        public boolean getOOBInline() throws SocketException {
            return this.a.getOOBInline();
        }

        public OutputStream getOutputStream() throws IOException {
            return this.a.getOutputStream();
        }

        public int getPort() {
            return this.a.getPort();
        }

        public synchronized int getReceiveBufferSize() throws SocketException {
            return this.a.getReceiveBufferSize();
        }

        public SocketAddress getRemoteSocketAddress() {
            return this.a.getRemoteSocketAddress();
        }

        public boolean getReuseAddress() throws SocketException {
            return this.a.getReuseAddress();
        }

        public synchronized int getSendBufferSize() throws SocketException {
            return this.a.getSendBufferSize();
        }

        public int getSoLinger() throws SocketException {
            return this.a.getSoLinger();
        }

        public synchronized int getSoTimeout() throws SocketException {
            return this.a.getSoTimeout();
        }

        public boolean getTcpNoDelay() throws SocketException {
            return this.a.getTcpNoDelay();
        }

        public int getTrafficClass() throws SocketException {
            return this.a.getTrafficClass();
        }

        public boolean isBound() {
            return this.a.isBound();
        }

        public boolean isClosed() {
            return this.a.isClosed();
        }

        public boolean isConnected() {
            return this.a.isConnected();
        }

        public boolean isInputShutdown() {
            return this.a.isInputShutdown();
        }

        public boolean isOutputShutdown() {
            return this.a.isOutputShutdown();
        }

        public void sendUrgentData(int i) throws IOException {
            this.a.sendUrgentData(i);
        }

        public void setKeepAlive(boolean z) throws SocketException {
            this.a.setKeepAlive(z);
        }

        public void setOOBInline(boolean z) throws SocketException {
            this.a.setOOBInline(z);
        }

        public void setPerformancePreferences(int i, int i2, int i3) {
            this.a.setPerformancePreferences(i, i2, i3);
        }

        public synchronized void setReceiveBufferSize(int i) throws SocketException {
            this.a.setReceiveBufferSize(i);
        }

        public void setReuseAddress(boolean z) throws SocketException {
            this.a.setReuseAddress(z);
        }

        public synchronized void setSendBufferSize(int i) throws SocketException {
            this.a.setSendBufferSize(i);
        }

        public void setSoLinger(boolean z, int i) throws SocketException {
            this.a.setSoLinger(z, i);
        }

        public synchronized void setSoTimeout(int i) throws SocketException {
            this.a.setSoTimeout(i);
        }

        public void setTcpNoDelay(boolean z) throws SocketException {
            this.a.setTcpNoDelay(z);
        }

        public void setTrafficClass(int i) throws SocketException {
            this.a.setTrafficClass(i);
        }

        public void shutdownInput() throws IOException {
            this.a.shutdownInput();
        }

        public void shutdownOutput() throws IOException {
            this.a.shutdownOutput();
        }

        public String toString() {
            return this.a.toString();
        }

        public boolean equals(Object obj) {
            return this.a.equals(obj);
        }
    }

    private class b extends a {
        private b(SSLSocket sSLSocket) {
            super(sSLSocket);
        }

        public void setEnabledProtocols(String[] strArr) {
            if (strArr != null && strArr.length == 1 && "SSLv3".equals(strArr[0])) {
                ArrayList arrayList = new ArrayList(Arrays.asList(this.a.getEnabledProtocols()));
                if (arrayList.size() > 1 && d.h()) {
                    arrayList.remove("SSLv3");
                }
                strArr = (String[]) arrayList.toArray(new String[arrayList.size()]);
            }
            super.setEnabledProtocols(strArr);
        }
    }

    public g() {
        this.a = HttpsURLConnection.getDefaultSSLSocketFactory();
    }

    public g(SSLSocketFactory sSLSocketFactory) {
        this.a = sSLSocketFactory;
    }

    public String[] getDefaultCipherSuites() {
        return this.a.getDefaultCipherSuites();
    }

    public String[] getSupportedCipherSuites() {
        return this.a.getSupportedCipherSuites();
    }

    private Socket a(Socket socket) {
        if (!(socket instanceof SSLSocket)) {
            return socket;
        }
        Socket bVar = new b((SSLSocket) socket);
        ((b) bVar).setEnabledProtocols(new String[]{"SSLv3"});
        return bVar;
    }

    public Socket createSocket(Socket socket, String str, int i, boolean z) throws IOException {
        return a(this.a.createSocket(socket, str, i, z));
    }

    public Socket createSocket(String str, int i) throws IOException {
        return a(this.a.createSocket(str, i));
    }

    public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) throws IOException {
        return a(this.a.createSocket(str, i, inetAddress, i2));
    }

    public Socket createSocket(InetAddress inetAddress, int i) throws IOException {
        return a(this.a.createSocket(inetAddress, i));
    }

    public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) throws IOException {
        return a(this.a.createSocket(inetAddress, i, inetAddress2, i2));
    }

    public static SSLSocketFactory a() {
        try {
            SSLContext instance;
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(Constants.bl);
            stringBuilder.append(Constants.bm);
            stringBuilder.append(Constants.bn);
            stringBuilder.append(Constants.bo);
            stringBuilder.append(Constants.bp);
            stringBuilder.append(Constants.bq);
            TrustManager[] trustManagerArr = new TrustManager[]{new h(stringBuilder.toString())};
            try {
                instance = SSLContext.getInstance("TLSv1");
            } catch (NoSuchAlgorithmException e) {
                ThrowableExtension.printStackTrace(e);
                instance = null;
            }
            if (instance == null) {
                return null;
            }
            try {
                if (Constants.d) {
                    instance.init(null, trustManagerArr, null);
                } else {
                    instance.init(null, null, null);
                }
                return new g(instance.getSocketFactory());
            } catch (KeyManagementException e2) {
                ThrowableExtension.printStackTrace(e2);
                return null;
            }
        } catch (Exception e3) {
            throw new AssertionError(e3);
        }
    }
}
