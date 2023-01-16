package com.sun.activation.registries;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.core.os.EnvironmentCompat;

public class MailcapTokenizer {
    public static final int EOI_TOKEN = 5;
    public static final int EQUALS_TOKEN = 61;
    public static final int SEMICOLON_TOKEN = 59;
    public static final int SLASH_TOKEN = 47;
    public static final int START_TOKEN = 1;
    public static final int STRING_TOKEN = 2;
    public static final int UNKNOWN_TOKEN = 0;
    private char autoquoteChar;
    private int currentToken;
    private String currentTokenValue;
    private String data;
    private int dataIndex = 0;
    private int dataLength;
    private boolean isAutoquoting;

    public MailcapTokenizer(String inputString) {
        this.data = inputString;
        this.dataLength = inputString.length();
        this.currentToken = 1;
        this.currentTokenValue = "";
        this.isAutoquoting = false;
        this.autoquoteChar = ';';
    }

    public void setIsAutoquoting(boolean value) {
        this.isAutoquoting = value;
    }

    public int getCurrentToken() {
        return this.currentToken;
    }

    public static String nameForToken(int token) {
        switch (token) {
            case 0:
                return EnvironmentCompat.MEDIA_UNKNOWN;
            case 1:
                return "start";
            case 2:
                return TypedValues.Custom.S_STRING;
            case 5:
                return "EOI";
            case 47:
                return "'/'";
            case 59:
                return "';'";
            case 61:
                return "'='";
            default:
                return "really unknown";
        }
    }

    public String getCurrentTokenValue() {
        return this.currentTokenValue;
    }

    public int nextToken() {
        if (this.dataIndex < this.dataLength) {
            while (true) {
                int i = this.dataIndex;
                if (i >= this.dataLength || !isWhiteSpaceChar(this.data.charAt(i))) {
                    int i2 = this.dataIndex;
                } else {
                    this.dataIndex++;
                }
            }
            int i22 = this.dataIndex;
            if (i22 < this.dataLength) {
                char c = this.data.charAt(i22);
                if (this.isAutoquoting) {
                    if (c == ';' || c == '=') {
                        this.currentToken = c;
                        this.currentTokenValue = new Character(c).toString();
                        this.dataIndex++;
                    } else {
                        processAutoquoteToken();
                    }
                } else if (isStringTokenChar(c)) {
                    processStringToken();
                } else if (c == '/' || c == ';' || c == '=') {
                    this.currentToken = c;
                    this.currentTokenValue = new Character(c).toString();
                    this.dataIndex++;
                } else {
                    this.currentToken = 0;
                    this.currentTokenValue = new Character(c).toString();
                    this.dataIndex++;
                }
            } else {
                this.currentToken = 5;
                this.currentTokenValue = null;
            }
        } else {
            this.currentToken = 5;
            this.currentTokenValue = null;
        }
        return this.currentToken;
    }

    private void processStringToken() {
        int initialIndex = this.dataIndex;
        while (true) {
            int i = this.dataIndex;
            if (i >= this.dataLength || !isStringTokenChar(this.data.charAt(i))) {
                this.currentToken = 2;
                this.currentTokenValue = this.data.substring(initialIndex, this.dataIndex);
            } else {
                this.dataIndex++;
            }
        }
        this.currentToken = 2;
        this.currentTokenValue = this.data.substring(initialIndex, this.dataIndex);
    }

    private void processAutoquoteToken() {
        int i;
        int initialIndex = this.dataIndex;
        boolean foundTerminator = false;
        while (true) {
            i = this.dataIndex;
            if (i >= this.dataLength || foundTerminator) {
                this.currentToken = 2;
                this.currentTokenValue = fixEscapeSequences(this.data.substring(initialIndex, i));
            } else if (this.data.charAt(i) != this.autoquoteChar) {
                this.dataIndex++;
            } else {
                foundTerminator = true;
            }
        }
        this.currentToken = 2;
        this.currentTokenValue = fixEscapeSequences(this.data.substring(initialIndex, i));
    }

    private static boolean isSpecialChar(char c) {
        switch (c) {
            case '\"':
            case '(':
            case ')':
            case ',':
            case '/':
            case ':':
            case ';':
            case '<':
            case '=':
            case '>':
            case '?':
            case '@':
            case '[':
            case '\\':
            case ']':
                return true;
            default:
                return false;
        }
    }

    private static boolean isControlChar(char c) {
        return Character.isISOControl(c);
    }

    private static boolean isWhiteSpaceChar(char c) {
        return Character.isWhitespace(c);
    }

    private static boolean isStringTokenChar(char c) {
        return !isSpecialChar(c) && !isControlChar(c) && !isWhiteSpaceChar(c);
    }

    private static String fixEscapeSequences(String inputString) {
        int inputLength = inputString.length();
        StringBuffer buffer = new StringBuffer();
        buffer.ensureCapacity(inputLength);
        int i = 0;
        while (i < inputLength) {
            char currentChar = inputString.charAt(i);
            if (currentChar != '\\') {
                buffer.append(currentChar);
            } else if (i < inputLength - 1) {
                buffer.append(inputString.charAt(i + 1));
                i++;
            } else {
                buffer.append(currentChar);
            }
            i++;
        }
        return buffer.toString();
    }
}
