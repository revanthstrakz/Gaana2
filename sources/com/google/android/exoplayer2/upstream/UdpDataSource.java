package com.google.android.exoplayer2.upstream;

import android.net.Uri;
import android.support.annotation.Nullable;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

public final class UdpDataSource extends BaseDataSource {
    public static final int DEFAULT_MAX_PACKET_SIZE = 2000;
    public static final int DEFAULT_SOCKET_TIMEOUT_MILLIS = 8000;
    @Nullable
    private InetAddress address;
    @Nullable
    private MulticastSocket multicastSocket;
    private boolean opened;
    private final DatagramPacket packet;
    private final byte[] packetBuffer;
    private int packetRemaining;
    @Nullable
    private DatagramSocket socket;
    @Nullable
    private InetSocketAddress socketAddress;
    private final int socketTimeoutMillis;
    @Nullable
    private Uri uri;

    public static final class UdpDataSourceException extends IOException {
        public UdpDataSourceException(IOException iOException) {
            super(iOException);
        }
    }

    public UdpDataSource() {
        this(2000);
    }

    public UdpDataSource(int i) {
        this(i, 8000);
    }

    public UdpDataSource(int i, int i2) {
        super(true);
        this.socketTimeoutMillis = i2;
        this.packetBuffer = new byte[i];
        this.packet = new DatagramPacket(this.packetBuffer, 0, i);
    }

    @Deprecated
    public UdpDataSource(@Nullable TransferListener transferListener) {
        this(transferListener, 2000);
    }

    @Deprecated
    public UdpDataSource(@Nullable TransferListener transferListener, int i) {
        this(transferListener, i, 8000);
    }

    @Deprecated
    public UdpDataSource(@Nullable TransferListener transferListener, int i, int i2) {
        this(i, i2);
        if (transferListener != null) {
            addTransferListener(transferListener);
        }
    }

    public long open(DataSpec dataSpec) throws UdpDataSourceException {
        this.uri = dataSpec.uri;
        String host = this.uri.getHost();
        int port = this.uri.getPort();
        transferInitializing(dataSpec);
        try {
            this.address = InetAddress.getByName(host);
            this.socketAddress = new InetSocketAddress(this.address, port);
            if (this.address.isMulticastAddress()) {
                this.multicastSocket = new MulticastSocket(this.socketAddress);
                this.multicastSocket.joinGroup(this.address);
                this.socket = this.multicastSocket;
            } else {
                this.socket = new DatagramSocket(this.socketAddress);
            }
            try {
                this.socket.setSoTimeout(this.socketTimeoutMillis);
                this.opened = true;
                transferStarted(dataSpec);
                return -1;
            } catch (SocketException e) {
                throw new UdpDataSourceException(e);
            }
        } catch (IOException e2) {
            throw new UdpDataSourceException(e2);
        }
    }

    public int read(byte[] bArr, int i, int i2) throws UdpDataSourceException {
        if (i2 == 0) {
            return 0;
        }
        if (this.packetRemaining == 0) {
            try {
                this.socket.receive(this.packet);
                this.packetRemaining = this.packet.getLength();
                bytesTransferred(this.packetRemaining);
            } catch (IOException e) {
                throw new UdpDataSourceException(e);
            }
        }
        int length = this.packet.getLength() - this.packetRemaining;
        i2 = Math.min(this.packetRemaining, i2);
        System.arraycopy(this.packetBuffer, length, bArr, i, i2);
        this.packetRemaining -= i2;
        return i2;
    }

    @Nullable
    public Uri getUri() {
        return this.uri;
    }

    public void close() {
        this.uri = null;
        if (this.multicastSocket != null) {
            try {
                this.multicastSocket.leaveGroup(this.address);
            } catch (IOException unused) {
            }
            this.multicastSocket = null;
        }
        if (this.socket != null) {
            this.socket.close();
            this.socket = null;
        }
        this.address = null;
        this.socketAddress = null;
        this.packetRemaining = 0;
        if (this.opened) {
            this.opened = false;
            transferEnded();
        }
    }
}
