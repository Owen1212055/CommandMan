package com.owen1212055.commandman.plugin.platform.bukkit.adapters.arguments.types;

import com.owen1212055.commandman.api.node.argument.type.custom.*;
import net.kyori.adventure.text.*;

public class StringReader {
    private static final char SYNTAX_ESCAPE = '\\';
    private static final char SYNTAX_DOUBLE_QUOTE = '"';
    private static final char SYNTAX_SINGLE_QUOTE = '\'';

    private final String string;
    private int cursor;

    public StringReader(final StringReader other) {
        this.string = other.string;
        this.cursor = other.cursor;
    }

    public StringReader(final String string) {
        this.string = string;
    }


    public String getString() {
        return string;
    }

    public void setCursor(final int cursor) {
        this.cursor = cursor;
    }


    public int getRemainingLength() {
        return string.length() - cursor;
    }


    public int getTotalLength() {
        return string.length();
    }


    public int getCursor() {
        return cursor;
    }


    public String getRead() {
        return string.substring(0, cursor);
    }


    public String getRemaining() {
        return string.substring(cursor);
    }


    public boolean canRead(final int length) {
        return cursor + length <= string.length();
    }


    public boolean canRead() {
        return canRead(1);
    }


    public char peek() {
        return string.charAt(cursor);
    }


    public char peek(final int offset) {
        return string.charAt(cursor + offset);
    }

    public char read() {
        return string.charAt(cursor++);
    }

    public void skip() {
        cursor++;
    }

    public static boolean isAllowedNumber(final char c) {
        return c >= '0' && c <= '9' || c == '.' || c == '-';
    }

    public static boolean isQuotedStringStart(char c) {
        return c == SYNTAX_DOUBLE_QUOTE || c == SYNTAX_SINGLE_QUOTE;
    }

    public void skipWhitespace() {
        while (canRead() && Character.isWhitespace(peek())) {
            skip();
        }
    }

    public int readInt() throws ArgumentParsingException {
        final int start = cursor;
        while (canRead() && isAllowedNumber(peek())) {
            skip();
        }
        final String number = string.substring(start, cursor);
        if (number.isEmpty()) {
            throw new ArgumentParsingException(Component.translatable("parsing.int.expected"));
        }
        try {
            return Integer.parseInt(number);
        } catch (final NumberFormatException ex) {
            cursor = start;
            throw new ArgumentParsingException(Component.translatable("parsing.int.invalid", Component.text(number)));
        }
    }

    public long readLong() throws ArgumentParsingException {
        final int start = cursor;
        while (canRead() && isAllowedNumber(peek())) {
            skip();
        }
        final String number = string.substring(start, cursor);
        if (number.isEmpty()) {
            throw new ArgumentParsingException(Component.translatable("parsing.long.expected"));
        }
        try {
            return Long.parseLong(number);
        } catch (final NumberFormatException ex) {
            cursor = start;
            throw new ArgumentParsingException(Component.translatable("parsing.long.invalid", Component.text(number)));
        }
    }

    public double readDouble() throws ArgumentParsingException {
        final int start = cursor;
        while (canRead() && isAllowedNumber(peek())) {
            skip();
        }
        final String number = string.substring(start, cursor);
        if (number.isEmpty()) {
            throw new ArgumentParsingException(Component.translatable("parsing.double.expected"));
        }
        try {
            return Double.parseDouble(number);
        } catch (final NumberFormatException ex) {
            cursor = start;
            throw new ArgumentParsingException(Component.translatable("parsing.double.invalid", Component.text(number)));
        }
    }

    public float readFloat() throws ArgumentParsingException {
        final int start = cursor;
        while (canRead() && isAllowedNumber(peek())) {
            skip();
        }
        final String number = string.substring(start, cursor);
        if (number.isEmpty()) {
            throw new ArgumentParsingException(Component.translatable("parsing.float.expected"));
        }
        try {
            return Float.parseFloat(number);
        } catch (final NumberFormatException ex) {
            cursor = start;
            throw new ArgumentParsingException(Component.translatable("parsing.float.invalid", Component.text(number)));
        }
    }

    public static boolean isAllowedInUnquotedString(final char c) {
        return c >= '0' && c <= '9'
                || c >= 'A' && c <= 'Z'
                || c >= 'a' && c <= 'z'
                || c == '_' || c == '-'
                || c == '.' || c == '+';
    }

    public String readUnquotedString() {
        final int start = cursor;
        while (canRead() && isAllowedInUnquotedString(peek())) {
            skip();
        }
        return string.substring(start, cursor);
    }

    public String readQuotedString() throws ArgumentParsingException {
        if (!canRead()) {
            return "";
        }
        final char next = peek();
        if (!isQuotedStringStart(next)) {
            throw new ArgumentParsingException(Component.translatable("parsing.quote.expected.start"));
        }
        skip();
        return readStringUntil(next);
    }

    public String readStringUntil(char terminator) throws ArgumentParsingException {
        final StringBuilder result = new StringBuilder();
        boolean escaped = false;
        while (canRead()) {
            final char c = read();
            if (escaped) {
                if (c == terminator || c == SYNTAX_ESCAPE) {
                    result.append(c);
                    escaped = false;
                } else {
                    setCursor(getCursor() - 1);
                    throw new ArgumentParsingException(Component.translatable("parsing.quote.escape", Component.text(c)));
                }
            } else if (c == SYNTAX_ESCAPE) {
                escaped = true;
            } else if (c == terminator) {
                return result.toString();
            } else {
                result.append(c);
            }
        }

        throw new ArgumentParsingException(Component.translatable("parsing.quote.expected.end"));
    }

    public String readString() throws ArgumentParsingException {
        if (!canRead()) {
            return "";
        }
        final char next = peek();
        if (isQuotedStringStart(next)) {
            skip();
            return readStringUntil(next);
        }
        return readUnquotedString();
    }

    public boolean readBoolean() throws ArgumentParsingException {
        final int start = cursor;
        final String value = readString();
        if (value.isEmpty()) {
            throw new ArgumentParsingException(Component.translatable("parsing.bool.expected"));
        }

        if (value.equals("true")) {
            return true;
        } else if (value.equals("false")) {
            return false;
        } else {
            cursor = start;
            throw new ArgumentParsingException(Component.translatable("parsing.bool.invalid", Component.text(value)));
        }
    }

    public void expect(final char c) throws ArgumentParsingException {
        if (!canRead() || peek() != c) {
            throw new ArgumentParsingException(Component.translatable("parsing.expected", Component.text(c)));
        }
        skip();
    }
}
