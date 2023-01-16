package javax.mail;

import java.util.Vector;
import javax.mail.event.ConnectionEvent;
import javax.mail.event.ConnectionListener;
import javax.mail.event.MailEvent;

public abstract class Service {
    private boolean connected = false;
    private Vector connectionListeners = null;
    protected boolean debug = false;
    private EventQueue q;
    private Object qLock = new Object();
    protected Session session;
    protected URLName url = null;

    protected Service(Session session2, URLName urlname) {
        this.session = session2;
        this.url = urlname;
        this.debug = session2.getDebug();
    }

    public void connect() throws MessagingException {
        connect((String) null, (String) null, (String) null);
    }

    public void connect(String host, String user, String password) throws MessagingException {
        connect(host, -1, user, password);
    }

    public void connect(String user, String password) throws MessagingException {
        connect((String) null, user, password);
    }

    /* JADX WARNING: Removed duplicated region for block: B:52:0x00e8 A[Catch:{ SecurityException -> 0x00d3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x012b  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x0140 A[SYNTHETIC, Splitter:B:70:0x0140] */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x016b A[Catch:{ SecurityException -> 0x00d3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0174 A[Catch:{ SecurityException -> 0x00d3 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void connect(java.lang.String r19, int r20, java.lang.String r21, java.lang.String r22) throws javax.mail.MessagingException {
        /*
            r18 = this;
            r1 = r18
            r0 = r21
            monitor-enter(r18)
            boolean r2 = r18.isConnected()     // Catch:{ all -> 0x01a6 }
            if (r2 != 0) goto L_0x019c
            r2 = 0
            r3 = 0
            r4 = 0
            r5 = 0
            javax.mail.URLName r6 = r1.url     // Catch:{ all -> 0x01a6 }
            if (r6 == 0) goto L_0x0068
            java.lang.String r6 = r6.getProtocol()     // Catch:{ all -> 0x01a6 }
            r4 = r6
            if (r19 != 0) goto L_0x0021
            javax.mail.URLName r6 = r1.url     // Catch:{ all -> 0x01a6 }
            java.lang.String r6 = r6.getHost()     // Catch:{ all -> 0x01a6 }
            goto L_0x0023
        L_0x0021:
            r6 = r19
        L_0x0023:
            r7 = -1
            r8 = r20
            if (r8 != r7) goto L_0x002f
            javax.mail.URLName r7 = r1.url     // Catch:{ all -> 0x01a6 }
            int r7 = r7.getPort()     // Catch:{ all -> 0x01a6 }
            goto L_0x0030
        L_0x002f:
            r7 = r8
        L_0x0030:
            if (r0 != 0) goto L_0x0045
            javax.mail.URLName r8 = r1.url     // Catch:{ all -> 0x01a6 }
            java.lang.String r8 = r8.getUsername()     // Catch:{ all -> 0x01a6 }
            r0 = r8
            if (r22 != 0) goto L_0x0042
            javax.mail.URLName r8 = r1.url     // Catch:{ all -> 0x01a6 }
            java.lang.String r8 = r8.getPassword()     // Catch:{ all -> 0x01a6 }
            goto L_0x005c
        L_0x0042:
            r8 = r22
            goto L_0x005c
        L_0x0045:
            if (r22 != 0) goto L_0x005a
            javax.mail.URLName r8 = r1.url     // Catch:{ all -> 0x01a6 }
            java.lang.String r8 = r8.getUsername()     // Catch:{ all -> 0x01a6 }
            boolean r8 = r0.equals(r8)     // Catch:{ all -> 0x01a6 }
            if (r8 == 0) goto L_0x005a
            javax.mail.URLName r8 = r1.url     // Catch:{ all -> 0x01a6 }
            java.lang.String r8 = r8.getPassword()     // Catch:{ all -> 0x01a6 }
            goto L_0x005c
        L_0x005a:
            r8 = r22
        L_0x005c:
            javax.mail.URLName r9 = r1.url     // Catch:{ all -> 0x01a6 }
            java.lang.String r9 = r9.getFile()     // Catch:{ all -> 0x01a6 }
            r5 = r9
            r14 = r4
            r15 = r5
            r4 = r7
            r5 = r8
            goto L_0x0071
        L_0x0068:
            r8 = r20
            r6 = r19
            r14 = r4
            r15 = r5
            r4 = r8
            r5 = r22
        L_0x0071:
            if (r14 == 0) goto L_0x00af
            if (r6 != 0) goto L_0x0091
            javax.mail.Session r7 = r1.session     // Catch:{ all -> 0x01a6 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x01a6 }
            java.lang.String r9 = "mail."
            r8.<init>(r9)     // Catch:{ all -> 0x01a6 }
            java.lang.StringBuilder r8 = r8.append(r14)     // Catch:{ all -> 0x01a6 }
            java.lang.String r9 = ".host"
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ all -> 0x01a6 }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x01a6 }
            java.lang.String r7 = r7.getProperty(r8)     // Catch:{ all -> 0x01a6 }
            r6 = r7
        L_0x0091:
            if (r0 != 0) goto L_0x00af
            javax.mail.Session r7 = r1.session     // Catch:{ all -> 0x01a6 }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ all -> 0x01a6 }
            java.lang.String r9 = "mail."
            r8.<init>(r9)     // Catch:{ all -> 0x01a6 }
            java.lang.StringBuilder r8 = r8.append(r14)     // Catch:{ all -> 0x01a6 }
            java.lang.String r9 = ".user"
            java.lang.StringBuilder r8 = r8.append(r9)     // Catch:{ all -> 0x01a6 }
            java.lang.String r8 = r8.toString()     // Catch:{ all -> 0x01a6 }
            java.lang.String r7 = r7.getProperty(r8)     // Catch:{ all -> 0x01a6 }
            r0 = r7
        L_0x00af:
            if (r6 != 0) goto L_0x00ba
            javax.mail.Session r7 = r1.session     // Catch:{ all -> 0x01a6 }
            java.lang.String r8 = "mail.host"
            java.lang.String r7 = r7.getProperty(r8)     // Catch:{ all -> 0x01a6 }
            r6 = r7
        L_0x00ba:
            if (r0 != 0) goto L_0x00c6
            javax.mail.Session r7 = r1.session     // Catch:{ all -> 0x01a6 }
            java.lang.String r8 = "mail.user"
            java.lang.String r7 = r7.getProperty(r8)     // Catch:{ all -> 0x01a6 }
            r0 = r7
            goto L_0x00c7
        L_0x00c6:
            r7 = r0
        L_0x00c7:
            if (r7 != 0) goto L_0x00e1
            java.lang.String r0 = "user.name"
            java.lang.String r0 = java.lang.System.getProperty(r0)     // Catch:{ SecurityException -> 0x00d3 }
            r7 = r0
            r0 = r7
            goto L_0x00e2
        L_0x00d3:
            r0 = move-exception
            boolean r8 = r1.debug     // Catch:{ all -> 0x01a6 }
            if (r8 == 0) goto L_0x00e1
            javax.mail.Session r8 = r1.session     // Catch:{ all -> 0x01a6 }
            java.io.PrintStream r8 = r8.getDebugOut()     // Catch:{ all -> 0x01a6 }
            r0.printStackTrace(r8)     // Catch:{ all -> 0x01a6 }
        L_0x00e1:
            r0 = r7
        L_0x00e2:
            if (r5 != 0) goto L_0x012b
            javax.mail.URLName r7 = r1.url     // Catch:{ all -> 0x01a6 }
            if (r7 == 0) goto L_0x012b
            javax.mail.URLName r13 = new javax.mail.URLName     // Catch:{ all -> 0x01a6 }
            r16 = 0
            r7 = r13
            r8 = r14
            r9 = r6
            r10 = r4
            r11 = r15
            r12 = r0
            r17 = r2
            r2 = r13
            r13 = r16
            r7.<init>(r8, r9, r10, r11, r12, r13)     // Catch:{ all -> 0x01a6 }
            r1.setURLName(r2)     // Catch:{ all -> 0x01a6 }
            javax.mail.Session r2 = r1.session     // Catch:{ all -> 0x01a6 }
            javax.mail.URLName r7 = r18.getURLName()     // Catch:{ all -> 0x01a6 }
            javax.mail.PasswordAuthentication r2 = r2.getPasswordAuthentication(r7)     // Catch:{ all -> 0x01a6 }
            if (r2 == 0) goto L_0x0128
            if (r0 != 0) goto L_0x0117
            java.lang.String r7 = r2.getUserName()     // Catch:{ all -> 0x01a6 }
            r0 = r7
            java.lang.String r7 = r2.getPassword()     // Catch:{ all -> 0x01a6 }
            r5 = r7
            r2 = r0
            goto L_0x012e
        L_0x0117:
            java.lang.String r7 = r2.getUserName()     // Catch:{ all -> 0x01a6 }
            boolean r7 = r0.equals(r7)     // Catch:{ all -> 0x01a6 }
            if (r7 == 0) goto L_0x012d
            java.lang.String r7 = r2.getPassword()     // Catch:{ all -> 0x01a6 }
            r5 = r7
            r2 = r0
            goto L_0x012e
        L_0x0128:
            r3 = 1
            r2 = r0
            goto L_0x012e
        L_0x012b:
            r17 = r2
        L_0x012d:
            r2 = r0
        L_0x012e:
            r7 = 0
            boolean r0 = r1.protocolConnect(r6, r4, r2, r5)     // Catch:{ AuthenticationFailedException -> 0x0138 }
            r17 = r0
            r16 = r7
            goto L_0x013e
        L_0x0138:
            r0 = move-exception
            r8 = r0
            r0 = r8
            r7 = r0
            r16 = r7
        L_0x013e:
            if (r17 != 0) goto L_0x0169
            java.net.InetAddress r0 = java.net.InetAddress.getByName(r6)     // Catch:{ UnknownHostException -> 0x0145 }
            goto L_0x014a
        L_0x0145:
            r0 = move-exception
            r7 = r0
            r0 = r7
            r7 = 0
            r0 = r7
        L_0x014a:
            javax.mail.Session r7 = r1.session     // Catch:{ all -> 0x01a6 }
            r11 = 0
            r8 = r0
            r9 = r4
            r10 = r14
            r12 = r2
            javax.mail.PasswordAuthentication r7 = r7.requestPasswordAuthentication(r8, r9, r10, r11, r12)     // Catch:{ all -> 0x01a6 }
            if (r7 == 0) goto L_0x0169
            java.lang.String r8 = r7.getUserName()     // Catch:{ all -> 0x01a6 }
            r2 = r8
            java.lang.String r8 = r7.getPassword()     // Catch:{ all -> 0x01a6 }
            r5 = r8
            boolean r8 = r1.protocolConnect(r6, r4, r2, r5)     // Catch:{ all -> 0x01a6 }
            r17 = r8
        L_0x0169:
            if (r17 != 0) goto L_0x0174
            if (r16 == 0) goto L_0x016e
            throw r16     // Catch:{ all -> 0x01a6 }
        L_0x016e:
            javax.mail.AuthenticationFailedException r0 = new javax.mail.AuthenticationFailedException     // Catch:{ all -> 0x01a6 }
            r0.<init>()     // Catch:{ all -> 0x01a6 }
            throw r0     // Catch:{ all -> 0x01a6 }
        L_0x0174:
            javax.mail.URLName r0 = new javax.mail.URLName     // Catch:{ all -> 0x01a6 }
            r7 = r0
            r8 = r14
            r9 = r6
            r10 = r4
            r11 = r15
            r12 = r2
            r13 = r5
            r7.<init>(r8, r9, r10, r11, r12, r13)     // Catch:{ all -> 0x01a6 }
            r1.setURLName(r0)     // Catch:{ all -> 0x01a6 }
            if (r3 == 0) goto L_0x0193
            javax.mail.Session r0 = r1.session     // Catch:{ all -> 0x01a6 }
            javax.mail.URLName r7 = r18.getURLName()     // Catch:{ all -> 0x01a6 }
            javax.mail.PasswordAuthentication r8 = new javax.mail.PasswordAuthentication     // Catch:{ all -> 0x01a6 }
            r8.<init>(r2, r5)     // Catch:{ all -> 0x01a6 }
            r0.setPasswordAuthentication(r7, r8)     // Catch:{ all -> 0x01a6 }
        L_0x0193:
            r0 = 1
            r1.setConnected(r0)     // Catch:{ all -> 0x01a6 }
            r1.notifyConnectionListeners(r0)     // Catch:{ all -> 0x01a6 }
            monitor-exit(r18)
            return
        L_0x019c:
            r8 = r20
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException     // Catch:{ all -> 0x01a6 }
            java.lang.String r3 = "already connected"
            r2.<init>(r3)     // Catch:{ all -> 0x01a6 }
            throw r2     // Catch:{ all -> 0x01a6 }
        L_0x01a6:
            r0 = move-exception
            monitor-exit(r18)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: javax.mail.Service.connect(java.lang.String, int, java.lang.String, java.lang.String):void");
    }

    /* access modifiers changed from: protected */
    public boolean protocolConnect(String host, int port, String user, String password) throws MessagingException {
        return false;
    }

    public synchronized boolean isConnected() {
        return this.connected;
    }

    /* access modifiers changed from: protected */
    public synchronized void setConnected(boolean connected2) {
        this.connected = connected2;
    }

    public synchronized void close() throws MessagingException {
        setConnected(false);
        notifyConnectionListeners(3);
    }

    public synchronized URLName getURLName() {
        URLName uRLName = this.url;
        if (uRLName == null || (uRLName.getPassword() == null && this.url.getFile() == null)) {
            return this.url;
        }
        return new URLName(this.url.getProtocol(), this.url.getHost(), this.url.getPort(), (String) null, this.url.getUsername(), (String) null);
    }

    /* access modifiers changed from: protected */
    public synchronized void setURLName(URLName url2) {
        this.url = url2;
    }

    public synchronized void addConnectionListener(ConnectionListener l) {
        if (this.connectionListeners == null) {
            this.connectionListeners = new Vector();
        }
        this.connectionListeners.addElement(l);
    }

    public synchronized void removeConnectionListener(ConnectionListener l) {
        Vector vector = this.connectionListeners;
        if (vector != null) {
            vector.removeElement(l);
        }
    }

    /* access modifiers changed from: protected */
    public synchronized void notifyConnectionListeners(int type) {
        if (this.connectionListeners != null) {
            queueEvent(new ConnectionEvent(this, type), this.connectionListeners);
        }
        if (type == 3) {
            terminateQueue();
        }
    }

    public String toString() {
        URLName url2 = getURLName();
        if (url2 != null) {
            return url2.toString();
        }
        return super.toString();
    }

    /* access modifiers changed from: protected */
    public void queueEvent(MailEvent event, Vector vector) {
        synchronized (this.qLock) {
            if (this.q == null) {
                this.q = new EventQueue();
            }
        }
        this.q.enqueue(event, (Vector) vector.clone());
    }

    static class TerminatorEvent extends MailEvent {
        private static final long serialVersionUID = 5542172141759168416L;

        TerminatorEvent() {
            super(new Object());
        }

        public void dispatch(Object listener) {
            Thread.currentThread().interrupt();
        }
    }

    private void terminateQueue() {
        synchronized (this.qLock) {
            if (this.q != null) {
                Vector dummyListeners = new Vector();
                dummyListeners.setSize(1);
                this.q.enqueue(new TerminatorEvent(), dummyListeners);
                this.q = null;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        super.finalize();
        terminateQueue();
    }
}
