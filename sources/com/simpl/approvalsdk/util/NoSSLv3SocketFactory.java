package com.simpl.approvalsdk.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Arrays;
import javax.net.ssl.HandshakeCompletedListener;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public final class NoSSLv3SocketFactory extends SSLSocketFactory {
    private final SSLSocketFactory delegate;

    public class DelegateSSLSocket extends SSLSocket {
        protected final SSLSocket delegate;

        DelegateSSLSocket(SSLSocket sSLSocket) {
            this.delegate = sSLSocket;
        }

        public void addHandshakeCompletedListener(HandshakeCompletedListener handshakeCompletedListener) {
            this.delegate.addHandshakeCompletedListener(handshakeCompletedListener);
        }

        public void bind(SocketAddress socketAddress) {
            this.delegate.bind(socketAddress);
        }

        public synchronized void close() {
            this.delegate.close();
        }

        public void connect(SocketAddress socketAddress) {
            this.delegate.connect(socketAddress);
        }

        public void connect(SocketAddress socketAddress, int i) {
            this.delegate.connect(socketAddress, i);
        }

        public boolean equals(Object obj) {
            return this.delegate.equals(obj);
        }

        public SocketChannel getChannel() {
            return this.delegate.getChannel();
        }

        public boolean getEnableSessionCreation() {
            return this.delegate.getEnableSessionCreation();
        }

        public String[] getEnabledCipherSuites() {
            return this.delegate.getEnabledCipherSuites();
        }

        public String[] getEnabledProtocols() {
            return this.delegate.getEnabledProtocols();
        }

        public InetAddress getInetAddress() {
            return this.delegate.getInetAddress();
        }

        public InputStream getInputStream() {
            return this.delegate.getInputStream();
        }

        public boolean getKeepAlive() {
            return this.delegate.getKeepAlive();
        }

        public InetAddress getLocalAddress() {
            return this.delegate.getLocalAddress();
        }

        public int getLocalPort() {
            return this.delegate.getLocalPort();
        }

        public SocketAddress getLocalSocketAddress() {
            return this.delegate.getLocalSocketAddress();
        }

        public boolean getNeedClientAuth() {
            return this.delegate.getNeedClientAuth();
        }

        public boolean getOOBInline() {
            return this.delegate.getOOBInline();
        }

        public OutputStream getOutputStream() {
            return this.delegate.getOutputStream();
        }

        public int getPort() {
            return this.delegate.getPort();
        }

        public synchronized int getReceiveBufferSize() {
            return this.delegate.getReceiveBufferSize();
        }

        public SocketAddress getRemoteSocketAddress() {
            return this.delegate.getRemoteSocketAddress();
        }

        public boolean getReuseAddress() {
            return this.delegate.getReuseAddress();
        }

        public synchronized int getSendBufferSize() {
            return this.delegate.getSendBufferSize();
        }

        public SSLSession getSession() {
            return this.delegate.getSession();
        }

        public int getSoLinger() {
            return this.delegate.getSoLinger();
        }

        public synchronized int getSoTimeout() {
            return this.delegate.getSoTimeout();
        }

        public String[] getSupportedCipherSuites() {
            return this.delegate.getSupportedCipherSuites();
        }

        public String[] getSupportedProtocols() {
            return this.delegate.getSupportedProtocols();
        }

        public boolean getTcpNoDelay() {
            return this.delegate.getTcpNoDelay();
        }

        public int getTrafficClass() {
            return this.delegate.getTrafficClass();
        }

        public boolean getUseClientMode() {
            return this.delegate.getUseClientMode();
        }

        public boolean getWantClientAuth() {
            return this.delegate.getWantClientAuth();
        }

        public boolean isBound() {
            return this.delegate.isBound();
        }

        public boolean isClosed() {
            return this.delegate.isClosed();
        }

        public boolean isConnected() {
            return this.delegate.isConnected();
        }

        public boolean isInputShutdown() {
            return this.delegate.isInputShutdown();
        }

        public boolean isOutputShutdown() {
            return this.delegate.isOutputShutdown();
        }

        public void removeHandshakeCompletedListener(HandshakeCompletedListener handshakeCompletedListener) {
            this.delegate.removeHandshakeCompletedListener(handshakeCompletedListener);
        }

        public void sendUrgentData(int i) {
            this.delegate.sendUrgentData(i);
        }

        public void setEnableSessionCreation(boolean z) {
            this.delegate.setEnableSessionCreation(z);
        }

        public void setEnabledCipherSuites(String[] strArr) {
            this.delegate.setEnabledCipherSuites(strArr);
        }

        public void setEnabledProtocols(String[] strArr) {
            this.delegate.setEnabledProtocols(strArr);
        }

        public void setKeepAlive(boolean z) {
            this.delegate.setKeepAlive(z);
        }

        public void setNeedClientAuth(boolean z) {
            this.delegate.setNeedClientAuth(z);
        }

        public void setOOBInline(boolean z) {
            this.delegate.setOOBInline(z);
        }

        public void setPerformancePreferences(int i, int i2, int i3) {
            this.delegate.setPerformancePreferences(i, i2, i3);
        }

        public synchronized void setReceiveBufferSize(int i) {
            this.delegate.setReceiveBufferSize(i);
        }

        public void setReuseAddress(boolean z) {
            this.delegate.setReuseAddress(z);
        }

        public synchronized void setSendBufferSize(int i) {
            this.delegate.setSendBufferSize(i);
        }

        public void setSoLinger(boolean z, int i) {
            this.delegate.setSoLinger(z, i);
        }

        public synchronized void setSoTimeout(int i) {
            this.delegate.setSoTimeout(i);
        }

        public void setTcpNoDelay(boolean z) {
            this.delegate.setTcpNoDelay(z);
        }

        public void setTrafficClass(int i) {
            this.delegate.setTrafficClass(i);
        }

        public void setUseClientMode(boolean z) {
            this.delegate.setUseClientMode(z);
        }

        public void setWantClientAuth(boolean z) {
            this.delegate.setWantClientAuth(z);
        }

        public void shutdownInput() {
            this.delegate.shutdownInput();
        }

        public void shutdownOutput() {
            this.delegate.shutdownOutput();
        }

        public void startHandshake() {
            this.delegate.startHandshake();
        }

        public String toString() {
            return this.delegate.toString();
        }
    }

    class a extends DelegateSSLSocket {
        private a(SSLSocket sSLSocket) {
            super(sSLSocket);
        }

        /* synthetic */ a(NoSSLv3SocketFactory noSSLv3SocketFactory, SSLSocket sSLSocket, byte b) {
            this(sSLSocket);
        }

        public final void setEnabledProtocols(String[] strArr) {
            if (strArr != null && strArr.length == 1 && "SSLv3".equals(strArr[0])) {
                PrintStream printStream;
                String str;
                ArrayList arrayList = new ArrayList(Arrays.asList(this.delegate.getEnabledProtocols()));
                if (arrayList.size() > 1) {
                    arrayList.remove("SSLv3");
                    printStream = System.out;
                    str = "Removed SSLv3 from enabled protocols";
                } else {
                    printStream = System.out;
                    StringBuilder stringBuilder = new StringBuilder("SSL stuck with protocol available for ");
                    stringBuilder.append(String.valueOf(arrayList));
                    str = stringBuilder.toString();
                }
                printStream.println(str);
                strArr = (String[]) arrayList.toArray(new String[arrayList.size()]);
            }
            super.setEnabledProtocols(strArr);
        }
    }

    public NoSSLv3SocketFactory() {
        this.delegate = HttpsURLConnection.getDefaultSSLSocketFactory();
    }

    public NoSSLv3SocketFactory(SSLSocketFactory sSLSocketFactory) {
        this.delegate = sSLSocketFactory;
    }

    private Socket makeSocketSafe(Socket socket) {
        return socket instanceof SSLSocket ? new a(this, (SSLSocket) socket, (byte) 0) : socket;
    }

    public final Socket createSocket(String str, int i) {
        return makeSocketSafe(this.delegate.createSocket(str, i));
    }

    public final Socket createSocket(String str, int i, InetAddress inetAddress, int i2) {
        return makeSocketSafe(this.delegate.createSocket(str, i, inetAddress, i2));
    }

    public final Socket createSocket(InetAddress inetAddress, int i) {
        return makeSocketSafe(this.delegate.createSocket(inetAddress, i));
    }

    public final Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) {
        return makeSocketSafe(this.delegate.createSocket(inetAddress, i, inetAddress2, i2));
    }

    public final Socket createSocket(Socket socket, String str, int i, boolean z) {
        return makeSocketSafe(this.delegate.createSocket(socket, str, i, z));
    }

    public final String[] getDefaultCipherSuites() {
        return this.delegate.getDefaultCipherSuites();
    }

    public final String[] getSupportedCipherSuites() {
        return this.delegate.getSupportedCipherSuites();
    }
}
