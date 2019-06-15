package com.helpshift.a.a.a;

import com.helpshift.util.l;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class d extends SSLSocketFactory {
    private SSLSocketFactory a;
    private List<String> b;
    private List<String> c;
    private List<Socket> d = new ArrayList();

    public d(SSLSocketFactory sSLSocketFactory, List<String> list, List<String> list2) {
        this.a = sSLSocketFactory;
        this.b = list;
        this.c = list2;
    }

    public String[] getDefaultCipherSuites() {
        return this.a.getDefaultCipherSuites();
    }

    public String[] getSupportedCipherSuites() {
        return this.a.getSupportedCipherSuites();
    }

    public Socket createSocket() throws IOException {
        return a(this.a.createSocket());
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

    /* Access modifiers changed, original: 0000 */
    public Socket a(Socket socket) {
        this.d.add(socket);
        if (socket == null || !(socket instanceof SSLSocket)) {
            return socket;
        }
        SSLSocket sSLSocket = (SSLSocket) socket;
        String[] enabledProtocols = sSLSocket.getEnabledProtocols();
        if (enabledProtocols == null) {
            return sSLSocket;
        }
        ArrayList arrayList = new ArrayList(Arrays.asList(enabledProtocols));
        enabledProtocols = sSLSocket.getSupportedProtocols();
        List arrayList2 = new ArrayList();
        if (enabledProtocols != null) {
            arrayList2 = Arrays.asList(enabledProtocols);
        }
        ArrayList arrayList3 = new ArrayList();
        if (this.b != null && this.b.size() > 0) {
            for (String str : this.b) {
                if (!arrayList.contains(str) && arrayList2.contains(str)) {
                    arrayList3.add(str);
                }
            }
        }
        arrayList.addAll(arrayList3);
        if (this.c != null && this.c.size() > 0) {
            arrayList.removeAll(this.c);
        }
        sSLSocket.setEnabledProtocols((String[]) arrayList.toArray(new String[arrayList.size()]));
        return socket;
    }

    public void a() {
        try {
            for (Socket socket : this.d) {
                if (socket != null) {
                    socket.close();
                }
            }
        } catch (Exception e) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Exception on closing open sockets: ");
            stringBuilder.append(e);
            l.c("hs_ssl_factory", stringBuilder.toString());
        } catch (Throwable th) {
            this.d.clear();
        }
        this.d.clear();
    }
}
