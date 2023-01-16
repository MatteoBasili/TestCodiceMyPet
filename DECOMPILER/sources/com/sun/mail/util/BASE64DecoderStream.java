package com.sun.mail.util;

import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class BASE64DecoderStream extends FilterInputStream {
    private static final char[] pem_array = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    private static final byte[] pem_convert_array = new byte[256];
    private byte[] buffer;
    private int bufsize;
    private boolean ignoreErrors;
    private int index;
    private byte[] input_buffer;
    private int input_len;
    private int input_pos;

    public BASE64DecoderStream(InputStream in) {
        super(in);
        this.buffer = new byte[3];
        boolean z = false;
        this.bufsize = 0;
        this.index = 0;
        this.input_buffer = new byte[8190];
        this.input_pos = 0;
        this.input_len = 0;
        this.ignoreErrors = false;
        try {
            String s = System.getProperty("mail.mime.base64.ignoreerrors");
            if (s != null && !s.equalsIgnoreCase("false")) {
                z = true;
            }
            this.ignoreErrors = z;
        } catch (SecurityException e) {
        }
    }

    public BASE64DecoderStream(InputStream in, boolean ignoreErrors2) {
        super(in);
        this.buffer = new byte[3];
        this.bufsize = 0;
        this.index = 0;
        this.input_buffer = new byte[8190];
        this.input_pos = 0;
        this.input_len = 0;
        this.ignoreErrors = false;
        this.ignoreErrors = ignoreErrors2;
    }

    public int read() throws IOException {
        if (this.index >= this.bufsize) {
            byte[] bArr = this.buffer;
            int decode = decode(bArr, 0, bArr.length);
            this.bufsize = decode;
            if (decode <= 0) {
                return -1;
            }
            this.index = 0;
        }
        byte[] bArr2 = this.buffer;
        int i = this.index;
        this.index = i + 1;
        return bArr2[i] & 255;
    }

    /* JADX WARNING: Removed duplicated region for block: B:6:0x001d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public int read(byte[] r7, int r8, int r9) throws java.io.IOException {
        /*
            r6 = this;
            r0 = r8
        L_0x0002:
            int r1 = r6.index
            int r2 = r6.bufsize
            if (r1 >= r2) goto L_0x001b
            if (r9 > 0) goto L_0x000b
            goto L_0x001b
        L_0x000b:
            int r2 = r8 + 1
            byte[] r3 = r6.buffer
            int r4 = r1 + 1
            r6.index = r4
            byte r1 = r3[r1]
            r7[r8] = r1
            int r9 = r9 + -1
            r8 = r2
            goto L_0x0002
        L_0x001b:
            if (r1 < r2) goto L_0x0022
            r1 = 0
            r6.index = r1
            r6.bufsize = r1
        L_0x0022:
            int r1 = r9 / 3
            int r1 = r1 * 3
            r2 = -1
            if (r1 <= 0) goto L_0x0037
            int r3 = r6.decode(r7, r8, r1)
            int r8 = r8 + r3
            int r9 = r9 - r3
            if (r3 == r1) goto L_0x0037
            if (r8 != r0) goto L_0x0034
            return r2
        L_0x0034:
            int r2 = r8 - r0
            return r2
        L_0x0037:
            if (r9 > 0) goto L_0x003a
            goto L_0x0041
        L_0x003a:
            int r3 = r6.read()
            if (r3 != r2) goto L_0x0047
        L_0x0041:
            if (r8 != r0) goto L_0x0044
            return r2
        L_0x0044:
            int r2 = r8 - r0
            return r2
        L_0x0047:
            int r4 = r8 + 1
            byte r5 = (byte) r3
            r7[r8] = r5
            int r9 = r9 + -1
            r8 = r4
            goto L_0x0037
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sun.mail.util.BASE64DecoderStream.read(byte[], int, int):int");
    }

    public boolean markSupported() {
        return false;
    }

    public int available() throws IOException {
        return ((this.in.available() * 3) / 4) + (this.bufsize - this.index);
    }

    static {
        for (int i = 0; i < 255; i++) {
            pem_convert_array[i] = -1;
        }
        int i2 = 0;
        while (true) {
            char[] cArr = pem_array;
            if (i2 < cArr.length) {
                pem_convert_array[cArr[i2]] = (byte) i2;
                i2++;
            } else {
                return;
            }
        }
    }

    private int decode(byte[] outbuf, int pos, int len) throws IOException {
        boolean atEOF;
        int pos0 = pos;
        while (len >= 3) {
            int got = 0;
            int val = 0;
            while (got < 4) {
                int i = getByte();
                if (i == -1 || i == -2) {
                    if (i == -1) {
                        if (got == 0) {
                            return pos - pos0;
                        }
                        if (this.ignoreErrors) {
                            atEOF = true;
                        } else {
                            throw new IOException("Error in encoded stream: needed 4 valid base64 characters but only got " + got + " before EOF" + recentChars());
                        }
                    } else if (got < 2 && !this.ignoreErrors) {
                        throw new IOException("Error in encoded stream: needed at least 2 valid base64 characters, but only got " + got + " before padding character (=)" + recentChars());
                    } else if (got == 0) {
                        return pos - pos0;
                    } else {
                        atEOF = false;
                    }
                    int size = got - 1;
                    if (size == 0) {
                        size = 1;
                    }
                    int val2 = val << 6;
                    for (int got2 = got + 1; got2 < 4; got2++) {
                        if (!atEOF) {
                            int i2 = getByte();
                            if (i2 == -1) {
                                if (!this.ignoreErrors) {
                                    throw new IOException("Error in encoded stream: hit EOF while looking for padding characters (=)" + recentChars());
                                }
                            } else if (i2 != -2 && !this.ignoreErrors) {
                                throw new IOException("Error in encoded stream: found valid base64 character after a padding character (=)" + recentChars());
                            }
                        }
                        val2 <<= 6;
                    }
                    int val3 = val2 >> 8;
                    if (size == 2) {
                        outbuf[pos + 1] = (byte) (val3 & 255);
                    }
                    outbuf[pos] = (byte) ((val3 >> 8) & 255);
                    return (pos + size) - pos0;
                }
                got++;
                val = (val << 6) | i;
            }
            outbuf[pos + 2] = (byte) (val & 255);
            int val4 = val >> 8;
            outbuf[pos + 1] = (byte) (val4 & 255);
            outbuf[pos] = (byte) ((val4 >> 8) & 255);
            len -= 3;
            pos += 3;
        }
        return pos - pos0;
    }

    private int getByte() throws IOException {
        byte c;
        do {
            if (this.input_pos >= this.input_len) {
                try {
                    int read = this.in.read(this.input_buffer);
                    this.input_len = read;
                    if (read <= 0) {
                        return -1;
                    }
                    this.input_pos = 0;
                } catch (EOFException e) {
                    return -1;
                }
            }
            byte[] bArr = this.input_buffer;
            int i = this.input_pos;
            this.input_pos = i + 1;
            int c2 = bArr[i] & 255;
            if (c2 == 61) {
                return -2;
            }
            c = pem_convert_array[c2];
        } while (c == -1);
        return c;
    }

    private String recentChars() {
        int nc = this.input_pos;
        if (nc > 10) {
            nc = 10;
        }
        if (nc <= 0) {
            return "";
        }
        String errstr = String.valueOf("") + ", the " + nc + " most recent characters were: \"";
        for (int k = this.input_pos - nc; k < this.input_pos; k++) {
            char c = (char) (this.input_buffer[k] & 255);
            switch (c) {
                case 9:
                    errstr = String.valueOf(errstr) + "\\t";
                    break;
                case 10:
                    errstr = String.valueOf(errstr) + "\\n";
                    break;
                case 13:
                    errstr = String.valueOf(errstr) + "\\r";
                    break;
                default:
                    if (c >= ' ' && c < 127) {
                        errstr = String.valueOf(errstr) + c;
                        break;
                    } else {
                        errstr = String.valueOf(errstr) + "\\" + c;
                        break;
                    }
                    break;
            }
        }
        return String.valueOf(errstr) + "\"";
    }

    public static byte[] decode(byte[] inbuf) {
        int inpos;
        int size = (inbuf.length / 4) * 3;
        if (size == 0) {
            return inbuf;
        }
        if (inbuf[inbuf.length - 1] == 61) {
            size--;
            if (inbuf[inbuf.length - 2] == 61) {
                size--;
            }
        }
        byte[] outbuf = new byte[size];
        int val = 0;
        int outpos = 0;
        int size2 = inbuf.length;
        while (size2 > 0) {
            int osize = 3;
            byte[] bArr = pem_convert_array;
            int inpos2 = val + 1;
            int inpos3 = inpos2 + 1;
            int val2 = ((bArr[inbuf[val] & 255] << 6) | bArr[inbuf[inpos2] & 255]) << 6;
            if (inbuf[inpos3] != 61) {
                val2 |= bArr[inbuf[inpos3] & 255];
                inpos3++;
            } else {
                osize = 3 - 1;
            }
            int val3 = val2 << 6;
            if (inbuf[inpos3] != 61) {
                inpos = inpos3 + 1;
                val3 |= bArr[inbuf[inpos3] & 255];
            } else {
                osize--;
                inpos = inpos3;
            }
            if (osize > 2) {
                outbuf[outpos + 2] = (byte) (val3 & 255);
            }
            int val4 = val3 >> 8;
            if (osize > 1) {
                outbuf[outpos + 1] = (byte) (val4 & 255);
            }
            outbuf[outpos] = (byte) ((val4 >> 8) & 255);
            outpos += osize;
            size2 -= 4;
            val = inpos;
        }
        return outbuf;
    }
}
