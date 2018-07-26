/**
 * MozartSpaces - Java implementation of Extensible Virtual Shared Memory (XVSM)
 * Copyright 2009-2013 Space Based Computing Group, eva Kuehn, E185/1, TU Vienna
 * Visit http://www.mozartspaces.org for more information.
 *
 * MozartSpaces is free software: you can redistribute it and/or
 * modify it under the terms of version 3 of the GNU Affero General
 * Public License as published by the Free Software Foundation.
 *
 * MozartSpaces is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General
 * Public License along with MozartSpaces. If not, see
 * <http://www.gnu.org/licenses/>.
 */
/* Generated By:JavaCC: Do not edit this line. CCOqlParser.java */
package org.mozartspaces.util.parser.sql.javacc;

import java.util.ArrayList;
import java.util.List;

import org.mozartspaces.capi3.ComparableProperty;
import org.mozartspaces.capi3.Matchmaker;
import org.mozartspaces.capi3.Matchmakers;
import org.mozartspaces.util.parser.sql.ConditionType;
import org.mozartspaces.util.parser.sql.SQLParserResult;

public class CCOqlParser implements CCOqlParserConstants {

    public static String[] toPath(final String id) {
        return id.split("\u005c\u005c.");
    }

    public static String likeToRegexPattern(final String likePattern) {
        String pattern;
        pattern = likePattern.replaceAll("%", ".*");
        pattern = pattern.replaceAll("_", ".");
        return pattern;
    }

    public static String unquoteString(final String input) {
        String output;
        if (input.startsWith("\u005c'")) {
            output = input.replaceAll("^\u005c'", "");
            output = output.replaceAll("\u005c'$", "");
        } else {
            output = input.replaceAll("^\u005c"", "");
            output = output.replaceAll("\u005c"$", "");
        }
        return output;
    }

    public static String stripSuffix(final String input) {
        String output;
        output = input.replaceAll("(l|L)$", "");
        output = output.replaceAll("(f|F)$", "");
        output = output.replaceAll("(d|D)$", "");
        return output;
    }

    final public SQLParserResult statement() throws ParseException {
        Matchmaker m = null;
        Integer l = null;
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
        case OPEN_PARENS:
        case NOT:
        case IDENTIFIER:
            m = or_expr();
            break;
        default:
            jj_la1[0] = jj_gen;
            ;
        }
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
        case LIMIT:
            l = limit();
            break;
        default:
            jj_la1[1] = jj_gen;
            ;
        }
        jj_consume_token(0);
        {
            if (true)
                return new SQLParserResult(m, l);
        }
        throw new Error("Missing return statement in function");
    }

    final public Matchmaker or_expr() throws ParseException {
        List<Matchmaker> mm = new ArrayList<Matchmaker>();
        Matchmaker m;
        m = and_expr();
        mm.add(m);
        label_1: while (true) {
            if (jj_2_1(2147483647)) {
                ;
            } else {
                break label_1;
            }
            jj_consume_token(OR);
            m = and_expr();
            mm.add(m);
        }
        if (mm.size() == 1) {
            if (true)
                return mm.get(0);
        }

        Matchmaker[] mmArray = mm.toArray(new Matchmaker[mm.size()]);
        {
            if (true)
                return Matchmakers.or(mmArray);
        }
        throw new Error("Missing return statement in function");
    }

    final public Matchmaker and_expr() throws ParseException {
        List<Matchmaker> mm = new ArrayList<Matchmaker>();
        Matchmaker m;
        m = parens_expr();
        mm.add(m);
        label_2: while (true) {
            if (jj_2_2(2147483647)) {
                ;
            } else {
                break label_2;
            }
            jj_consume_token(AND);
            m = parens_expr();
            mm.add(m);
        }
        if (mm.size() == 1) {
            if (true)
                return mm.get(0);
        }

        Matchmaker[] mmArray = mm.toArray(new Matchmaker[mm.size()]);
        {
            if (true)
                return Matchmakers.and(mmArray);
        }
        throw new Error("Missing return statement in function");
    }

    final public Matchmaker parens_expr() throws ParseException {
        Matchmaker m;
        Boolean not = false;
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
        case NOT:
            jj_consume_token(NOT);
            not = true;
            break;
        default:
            jj_la1[2] = jj_gen;
            ;
        }
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
        case IDENTIFIER:
            m = condition();
            break;
        case OPEN_PARENS:
            jj_consume_token(OPEN_PARENS);
            m = or_expr();
            jj_consume_token(CLOSE_PARENS);
            break;
        default:
            jj_la1[3] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
        }
        if (not) {
            if (true)
                return Matchmakers.not(m);
        }

        {
            if (true)
                return m;
        }
        throw new Error("Missing return statement in function");
    }

    final public Comparable<?> literal() throws ParseException {
        Token t;
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
        case INTEGER:
            t = jj_consume_token(INTEGER);
            {
                if (true)
                    return Integer.valueOf(t.image);
            }
            break;
        case LONG:
            t = jj_consume_token(LONG);
            {
                if (true)
                    return Long.valueOf(stripSuffix(t.image));
            }
            break;
        case FLOAT:
            t = jj_consume_token(FLOAT);
            {
                if (true)
                    return Float.valueOf(stripSuffix(t.image));
            }
            break;
        case DOUBLE:
            t = jj_consume_token(DOUBLE);
            {
                if (true)
                    return Double.valueOf(stripSuffix(t.image));
            }
            break;
        case STRING:
            t = jj_consume_token(STRING);
            {
                if (true)
                    return unquoteString(t.image);
            }
            break;
        default:
            jj_la1[4] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
        }
        throw new Error("Missing return statement in function");
    }

    final public Matchmaker condition() throws ParseException {
        Matchmaker m;
        if (jj_2_3(2)) {
            m = regular_condition();
        } else if (jj_2_4(2)) {
            m = between_condition();
        } else if (jj_2_5(2)) {
            m = in_condition();
        } else if (jj_2_6(2)) {
            m = like_condition();
        } else {
            jj_consume_token(-1);
            throw new ParseException();
        }
        {
            if (true)
                return m;
        }
        throw new Error("Missing return statement in function");
    }

    final public Matchmaker regular_condition() throws ParseException {
        Token id;
        Token valueId;
        ComparableProperty prop;
        ConditionType type;
        Comparable<?> literal = null;
        ComparableProperty valueIdProp = null;
        id = jj_consume_token(IDENTIFIER);
        prop = ComparableProperty.forName(toPath(id.image));
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
        case EQUALS:
            jj_consume_token(EQUALS);
            type = ConditionType.EQUALS;
            break;
        case NOT_EQUALS:
            jj_consume_token(NOT_EQUALS);
            type = ConditionType.NOT_EQUALS;
            break;
        case GREATER_THAN:
            jj_consume_token(GREATER_THAN);
            type = ConditionType.GREATER_THAN;
            break;
        case LESS_THAN:
            jj_consume_token(LESS_THAN);
            type = ConditionType.LESS_THAN;
            break;
        case GREATER_THAN_OR_EQUAL:
            jj_consume_token(GREATER_THAN_OR_EQUAL);
            type = ConditionType.GREATER_THAN_OR_EQUAL;
            break;
        case LESS_THAN_OR_EQUAL:
            jj_consume_token(LESS_THAN_OR_EQUAL);
            type = ConditionType.LESS_THAN_OR_EQUAL;
            break;
        default:
            jj_la1[5] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
        }
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
        case INTEGER:
        case LONG:
        case FLOAT:
        case DOUBLE:
        case STRING:
            literal = literal();
            break;
        case IDENTIFIER:
            valueId = jj_consume_token(IDENTIFIER);
            valueIdProp = ComparableProperty.forName(toPath(valueId.image));
            break;
        default:
            jj_la1[6] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
        }
        switch (type) {

        case EQUALS:
            if (valueIdProp == null) {
                if (true)
                    return prop.equalTo(literal);
            } else {
                if (true)
                    return prop.equalTo(valueIdProp);
            }

        case NOT_EQUALS:
            if (valueIdProp == null) {
                if (true)
                    return prop.notEqualTo(literal);
            } else {
                if (true)
                    return prop.notEqualTo(valueIdProp);
            }

        case GREATER_THAN:
            if (valueIdProp == null) {
                if (true)
                    return prop.greaterThan(literal);
            } else {
                if (true)
                    return prop.greaterThan(valueIdProp);
            }

        case LESS_THAN:
            if (valueIdProp == null) {
                if (true)
                    return prop.lessThan(literal);
            } else {
                if (true)
                    return prop.lessThan(valueIdProp);
            }

        case GREATER_THAN_OR_EQUAL:
            if (valueIdProp == null) {
                if (true)
                    return prop.greaterThanOrEqualTo(literal);
            } else {
                if (true)
                    return prop.greaterThanOrEqualTo(valueIdProp);
            }

        case LESS_THAN_OR_EQUAL:
            if (valueIdProp == null) {
                if (true)
                    return prop.lessThanOrEqualTo(literal);
            } else {
                if (true)
                    return prop.lessThanOrEqualTo(valueIdProp);
            }

        }
        throw new Error("Missing return statement in function");
    }

    final public Matchmaker between_condition() throws ParseException {
        ComparableProperty prop;
        Token id;
        Comparable<?> lower = null;
        Comparable<?> upper = null;
        id = jj_consume_token(IDENTIFIER);
        jj_consume_token(BETWEEN);
        lower = literal();
        jj_consume_token(AND);
        upper = literal();
        prop = ComparableProperty.forName(toPath(id.image));
        {
            if (true)
                return prop.between(lower, upper);
        }
        throw new Error("Missing return statement in function");
    }

    final public Matchmaker like_condition() throws ParseException {
        ComparableProperty prop;
        Token id;
        Token p;
        id = jj_consume_token(IDENTIFIER);
        jj_consume_token(LIKE);
        p = jj_consume_token(STRING);
        prop = ComparableProperty.forName(toPath(id.image));
        String pattern = likeToRegexPattern(unquoteString(p.image));
        {
            if (true)
                return prop.matches(pattern);
        }
        throw new Error("Missing return statement in function");
    }

    final public Matchmaker in_condition() throws ParseException {
        List<Object> values = new ArrayList<Object>();
        ComparableProperty prop;
        Token id;
        Comparable<?> val;
        id = jj_consume_token(IDENTIFIER);
        jj_consume_token(IN);
        jj_consume_token(OPEN_PARENS);
        val = literal();
        values.add(val);
        label_3: while (true) {
            switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
            case COMMA:
                ;
                break;
            default:
                jj_la1[7] = jj_gen;
                break label_3;
            }
            jj_consume_token(COMMA);
            val = literal();
            values.add(val);
        }
        jj_consume_token(CLOSE_PARENS);
        prop = ComparableProperty.forName(toPath(id.image));
        Object[] valuesArray = values.toArray(new Object[values.size()]);
        {
            if (true)
                return prop.elementOf(valuesArray);
        }
        throw new Error("Missing return statement in function");
    }

    final public Integer limit() throws ParseException {
        Token t;
        jj_consume_token(LIMIT);
        t = jj_consume_token(INTEGER);
        {
            if (true)
                return Integer.valueOf(t.image);
        }
        throw new Error("Missing return statement in function");
    }

    private boolean jj_2_1(int xla) {
        jj_la = xla;
        jj_lastpos = jj_scanpos = token;
        try {
            return !jj_3_1();
        } catch (LookaheadSuccess ls) {
            return true;
        } finally {
            jj_save(0, xla);
        }
    }

    private boolean jj_2_2(int xla) {
        jj_la = xla;
        jj_lastpos = jj_scanpos = token;
        try {
            return !jj_3_2();
        } catch (LookaheadSuccess ls) {
            return true;
        } finally {
            jj_save(1, xla);
        }
    }

    private boolean jj_2_3(int xla) {
        jj_la = xla;
        jj_lastpos = jj_scanpos = token;
        try {
            return !jj_3_3();
        } catch (LookaheadSuccess ls) {
            return true;
        } finally {
            jj_save(2, xla);
        }
    }

    private boolean jj_2_4(int xla) {
        jj_la = xla;
        jj_lastpos = jj_scanpos = token;
        try {
            return !jj_3_4();
        } catch (LookaheadSuccess ls) {
            return true;
        } finally {
            jj_save(3, xla);
        }
    }

    private boolean jj_2_5(int xla) {
        jj_la = xla;
        jj_lastpos = jj_scanpos = token;
        try {
            return !jj_3_5();
        } catch (LookaheadSuccess ls) {
            return true;
        } finally {
            jj_save(4, xla);
        }
    }

    private boolean jj_2_6(int xla) {
        jj_la = xla;
        jj_lastpos = jj_scanpos = token;
        try {
            return !jj_3_6();
        } catch (LookaheadSuccess ls) {
            return true;
        } finally {
            jj_save(5, xla);
        }
    }

    private boolean jj_3R_28() {
        if (jj_scan_token(LONG))
            return true;
        return false;
    }

    private boolean jj_3R_27() {
        if (jj_scan_token(INTEGER))
            return true;
        return false;
    }

    private boolean jj_3R_25() {
        Token xsp;
        xsp = jj_scanpos;
        if (jj_3R_27()) {
            jj_scanpos = xsp;
            if (jj_3R_28()) {
                jj_scanpos = xsp;
                if (jj_3R_29()) {
                    jj_scanpos = xsp;
                    if (jj_3R_30()) {
                        jj_scanpos = xsp;
                        if (jj_3R_31())
                            return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean jj_3R_10() {
        if (jj_scan_token(AND))
            return true;
        if (jj_3R_9())
            return true;
        return false;
    }

    private boolean jj_3R_21() {
        if (jj_3R_4())
            return true;
        Token xsp;
        while (true) {
            xsp = jj_scanpos;
            if (jj_3R_22()) {
                jj_scanpos = xsp;
                break;
            }
        }
        return false;
    }

    private boolean jj_3R_16() {
        if (jj_scan_token(LESS_THAN_OR_EQUAL))
            return true;
        return false;
    }

    private boolean jj_3R_15() {
        if (jj_scan_token(GREATER_THAN_OR_EQUAL))
            return true;
        return false;
    }

    private boolean jj_3R_19() {
        if (jj_scan_token(OPEN_PARENS))
            return true;
        if (jj_3R_21())
            return true;
        if (jj_scan_token(CLOSE_PARENS))
            return true;
        return false;
    }

    private boolean jj_3R_14() {
        if (jj_scan_token(LESS_THAN))
            return true;
        return false;
    }

    private boolean jj_3R_18() {
        if (jj_3R_20())
            return true;
        return false;
    }

    private boolean jj_3R_13() {
        if (jj_scan_token(GREATER_THAN))
            return true;
        return false;
    }

    private boolean jj_3R_12() {
        if (jj_scan_token(NOT_EQUALS))
            return true;
        return false;
    }

    private boolean jj_3R_11() {
        if (jj_scan_token(EQUALS))
            return true;
        return false;
    }

    private boolean jj_3_1() {
        if (jj_scan_token(OR))
            return true;
        if (jj_3R_4())
            return true;
        return false;
    }

    private boolean jj_3R_5() {
        if (jj_scan_token(IDENTIFIER))
            return true;
        Token xsp;
        xsp = jj_scanpos;
        if (jj_3R_11()) {
            jj_scanpos = xsp;
            if (jj_3R_12()) {
                jj_scanpos = xsp;
                if (jj_3R_13()) {
                    jj_scanpos = xsp;
                    if (jj_3R_14()) {
                        jj_scanpos = xsp;
                        if (jj_3R_15()) {
                            jj_scanpos = xsp;
                            if (jj_3R_16())
                                return true;
                        }
                    }
                }
            }
        }
        xsp = jj_scanpos;
        if (jj_3R_23()) {
            jj_scanpos = xsp;
            if (jj_3R_24())
                return true;
        }
        return false;
    }

    private boolean jj_3R_8() {
        if (jj_scan_token(IDENTIFIER))
            return true;
        if (jj_scan_token(LIKE))
            return true;
        if (jj_scan_token(STRING))
            return true;
        return false;
    }

    private boolean jj_3R_17() {
        if (jj_scan_token(NOT))
            return true;
        return false;
    }

    private boolean jj_3R_22() {
        if (jj_scan_token(OR))
            return true;
        if (jj_3R_4())
            return true;
        return false;
    }

    private boolean jj_3R_9() {
        Token xsp;
        xsp = jj_scanpos;
        if (jj_3R_17())
            jj_scanpos = xsp;
        xsp = jj_scanpos;
        if (jj_3R_18()) {
            jj_scanpos = xsp;
            if (jj_3R_19())
                return true;
        }
        return false;
    }

    private boolean jj_3_6() {
        if (jj_3R_8())
            return true;
        return false;
    }

    private boolean jj_3_5() {
        if (jj_3R_7())
            return true;
        return false;
    }

    private boolean jj_3_4() {
        if (jj_3R_6())
            return true;
        return false;
    }

    private boolean jj_3_3() {
        if (jj_3R_5())
            return true;
        return false;
    }

    private boolean jj_3R_26() {
        if (jj_scan_token(COMMA))
            return true;
        if (jj_3R_25())
            return true;
        return false;
    }

    private boolean jj_3R_4() {
        if (jj_3R_9())
            return true;
        Token xsp;
        while (true) {
            xsp = jj_scanpos;
            if (jj_3R_10()) {
                jj_scanpos = xsp;
                break;
            }
        }
        return false;
    }

    private boolean jj_3R_6() {
        if (jj_scan_token(IDENTIFIER))
            return true;
        if (jj_scan_token(BETWEEN))
            return true;
        if (jj_3R_25())
            return true;
        if (jj_scan_token(AND))
            return true;
        if (jj_3R_25())
            return true;
        return false;
    }

    private boolean jj_3_2() {
        if (jj_scan_token(AND))
            return true;
        if (jj_3R_4())
            return true;
        return false;
    }

    private boolean jj_3R_31() {
        if (jj_scan_token(STRING))
            return true;
        return false;
    }

    private boolean jj_3R_20() {
        Token xsp;
        xsp = jj_scanpos;
        if (jj_3_3()) {
            jj_scanpos = xsp;
            if (jj_3_4()) {
                jj_scanpos = xsp;
                if (jj_3_5()) {
                    jj_scanpos = xsp;
                    if (jj_3_6())
                        return true;
                }
            }
        }
        return false;
    }

    private boolean jj_3R_30() {
        if (jj_scan_token(DOUBLE))
            return true;
        return false;
    }

    private boolean jj_3R_24() {
        if (jj_scan_token(IDENTIFIER))
            return true;
        return false;
    }

    private boolean jj_3R_7() {
        if (jj_scan_token(IDENTIFIER))
            return true;
        if (jj_scan_token(IN))
            return true;
        if (jj_scan_token(OPEN_PARENS))
            return true;
        if (jj_3R_25())
            return true;
        Token xsp;
        while (true) {
            xsp = jj_scanpos;
            if (jj_3R_26()) {
                jj_scanpos = xsp;
                break;
            }
        }
        if (jj_scan_token(CLOSE_PARENS))
            return true;
        return false;
    }

    private boolean jj_3R_29() {
        if (jj_scan_token(FLOAT))
            return true;
        return false;
    }

    private boolean jj_3R_23() {
        if (jj_3R_25())
            return true;
        return false;
    }

    /** Generated Token Manager. */
    public CCOqlParserTokenManager token_source;
    SimpleCharStream jj_input_stream;
    /** Current token. */
    public Token token;
    /** Next token. */
    public Token jj_nt;
    private int jj_ntk;
    private Token jj_scanpos, jj_lastpos;
    private int jj_la;
    private int jj_gen;
    final private int[] jj_la1 = new int[8];
    static private int[] jj_la1_0;
    static private int[] jj_la1_1;
    static {
        jj_la1_init_0();
        jj_la1_init_1();
    }

    private static void jj_la1_init_0() {
        jj_la1_0 = new int[] { 0x4000100, 0x0, 0x4000000, 0x100, 0x83c00, 0xf8000000, 0x83c00, 0x80, };
    }

    private static void jj_la1_init_1() {
        jj_la1_1 = new int[] { 0x20, 0x10, 0x0, 0x20, 0x0, 0x1, 0x20, 0x0, };
    }

    final private JJCalls[] jj_2_rtns = new JJCalls[6];
    private boolean jj_rescan = false;
    private int jj_gc = 0;

    /** Constructor with InputStream. */
    public CCOqlParser(java.io.InputStream stream) {
        this(stream, null);
    }

    /** Constructor with InputStream and supplied encoding */
    public CCOqlParser(java.io.InputStream stream, String encoding) {
        try {
            jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1);
        } catch (java.io.UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        token_source = new CCOqlParserTokenManager(jj_input_stream);
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for (int i = 0; i < 8; i++)
            jj_la1[i] = -1;
        for (int i = 0; i < jj_2_rtns.length; i++)
            jj_2_rtns[i] = new JJCalls();
    }

    /** Reinitialise. */
    public void ReInit(java.io.InputStream stream) {
        ReInit(stream, null);
    }

    /** Reinitialise. */
    public void ReInit(java.io.InputStream stream, String encoding) {
        try {
            jj_input_stream.ReInit(stream, encoding, 1, 1);
        } catch (java.io.UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        token_source.ReInit(jj_input_stream);
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for (int i = 0; i < 8; i++)
            jj_la1[i] = -1;
        for (int i = 0; i < jj_2_rtns.length; i++)
            jj_2_rtns[i] = new JJCalls();
    }

    /** Constructor. */
    public CCOqlParser(java.io.Reader stream) {
        jj_input_stream = new SimpleCharStream(stream, 1, 1);
        token_source = new CCOqlParserTokenManager(jj_input_stream);
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for (int i = 0; i < 8; i++)
            jj_la1[i] = -1;
        for (int i = 0; i < jj_2_rtns.length; i++)
            jj_2_rtns[i] = new JJCalls();
    }

    /** Reinitialise. */
    public void ReInit(java.io.Reader stream) {
        jj_input_stream.ReInit(stream, 1, 1);
        token_source.ReInit(jj_input_stream);
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for (int i = 0; i < 8; i++)
            jj_la1[i] = -1;
        for (int i = 0; i < jj_2_rtns.length; i++)
            jj_2_rtns[i] = new JJCalls();
    }

    /** Constructor with generated Token Manager. */
    public CCOqlParser(CCOqlParserTokenManager tm) {
        token_source = tm;
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for (int i = 0; i < 8; i++)
            jj_la1[i] = -1;
        for (int i = 0; i < jj_2_rtns.length; i++)
            jj_2_rtns[i] = new JJCalls();
    }

    /** Reinitialise. */
    public void ReInit(CCOqlParserTokenManager tm) {
        token_source = tm;
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for (int i = 0; i < 8; i++)
            jj_la1[i] = -1;
        for (int i = 0; i < jj_2_rtns.length; i++)
            jj_2_rtns[i] = new JJCalls();
    }

    private Token jj_consume_token(int kind) throws ParseException {
        Token oldToken;
        if ((oldToken = token).next != null)
            token = token.next;
        else
            token = token.next = token_source.getNextToken();
        jj_ntk = -1;
        if (token.kind == kind) {
            jj_gen++;
            if (++jj_gc > 100) {
                jj_gc = 0;
                for (int i = 0; i < jj_2_rtns.length; i++) {
                    JJCalls c = jj_2_rtns[i];
                    while (c != null) {
                        if (c.gen < jj_gen)
                            c.first = null;
                        c = c.next;
                    }
                }
            }
            return token;
        }
        token = oldToken;
        jj_kind = kind;
        throw generateParseException();
    }

    static private final class LookaheadSuccess extends java.lang.Error {
    }

    final private LookaheadSuccess jj_ls = new LookaheadSuccess();

    private boolean jj_scan_token(int kind) {
        if (jj_scanpos == jj_lastpos) {
            jj_la--;
            if (jj_scanpos.next == null) {
                jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
            } else {
                jj_lastpos = jj_scanpos = jj_scanpos.next;
            }
        } else {
            jj_scanpos = jj_scanpos.next;
        }
        if (jj_rescan) {
            int i = 0;
            Token tok = token;
            while (tok != null && tok != jj_scanpos) {
                i++;
                tok = tok.next;
            }
            if (tok != null)
                jj_add_error_token(kind, i);
        }
        if (jj_scanpos.kind != kind)
            return true;
        if (jj_la == 0 && jj_scanpos == jj_lastpos)
            throw jj_ls;
        return false;
    }

    /** Get the next Token. */
    final public Token getNextToken() {
        if (token.next != null)
            token = token.next;
        else
            token = token.next = token_source.getNextToken();
        jj_ntk = -1;
        jj_gen++;
        return token;
    }

    /** Get the specific Token. */
    final public Token getToken(int index) {
        Token t = token;
        for (int i = 0; i < index; i++) {
            if (t.next != null)
                t = t.next;
            else
                t = t.next = token_source.getNextToken();
        }
        return t;
    }

    private int jj_ntk() {
        if ((jj_nt = token.next) == null)
            return (jj_ntk = (token.next = token_source.getNextToken()).kind);
        else
            return (jj_ntk = jj_nt.kind);
    }

    private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
    private int[] jj_expentry;
    private int jj_kind = -1;
    private int[] jj_lasttokens = new int[100];
    private int jj_endpos;

    private void jj_add_error_token(int kind, int pos) {
        if (pos >= 100)
            return;
        if (pos == jj_endpos + 1) {
            jj_lasttokens[jj_endpos++] = kind;
        } else if (jj_endpos != 0) {
            jj_expentry = new int[jj_endpos];
            for (int i = 0; i < jj_endpos; i++) {
                jj_expentry[i] = jj_lasttokens[i];
            }
            jj_entries_loop: for (java.util.Iterator<?> it = jj_expentries.iterator(); it.hasNext();) {
                int[] oldentry = (int[]) (it.next());
                if (oldentry.length == jj_expentry.length) {
                    for (int i = 0; i < jj_expentry.length; i++) {
                        if (oldentry[i] != jj_expentry[i]) {
                            continue jj_entries_loop;
                        }
                    }
                    jj_expentries.add(jj_expentry);
                    break jj_entries_loop;
                }
            }
            if (pos != 0)
                jj_lasttokens[(jj_endpos = pos) - 1] = kind;
        }
    }

    /** Generate ParseException. */
    public ParseException generateParseException() {
        jj_expentries.clear();
        boolean[] la1tokens = new boolean[42];
        if (jj_kind >= 0) {
            la1tokens[jj_kind] = true;
            jj_kind = -1;
        }
        for (int i = 0; i < 8; i++) {
            if (jj_la1[i] == jj_gen) {
                for (int j = 0; j < 32; j++) {
                    if ((jj_la1_0[i] & (1 << j)) != 0) {
                        la1tokens[j] = true;
                    }
                    if ((jj_la1_1[i] & (1 << j)) != 0) {
                        la1tokens[32 + j] = true;
                    }
                }
            }
        }
        for (int i = 0; i < 42; i++) {
            if (la1tokens[i]) {
                jj_expentry = new int[1];
                jj_expentry[0] = i;
                jj_expentries.add(jj_expentry);
            }
        }
        jj_endpos = 0;
        jj_rescan_token();
        jj_add_error_token(0, 0);
        int[][] exptokseq = new int[jj_expentries.size()][];
        for (int i = 0; i < jj_expentries.size(); i++) {
            exptokseq[i] = jj_expentries.get(i);
        }
        return new ParseException(token, exptokseq, tokenImage);
    }

    /** Enable tracing. */
    final public void enable_tracing() {
    }

    /** Disable tracing. */
    final public void disable_tracing() {
    }

    private void jj_rescan_token() {
        jj_rescan = true;
        for (int i = 0; i < 6; i++) {
            try {
                JJCalls p = jj_2_rtns[i];
                do {
                    if (p.gen > jj_gen) {
                        jj_la = p.arg;
                        jj_lastpos = jj_scanpos = p.first;
                        switch (i) {
                        case 0:
                            jj_3_1();
                            break;
                        case 1:
                            jj_3_2();
                            break;
                        case 2:
                            jj_3_3();
                            break;
                        case 3:
                            jj_3_4();
                            break;
                        case 4:
                            jj_3_5();
                            break;
                        case 5:
                            jj_3_6();
                            break;
                        }
                    }
                    p = p.next;
                } while (p != null);
            } catch (LookaheadSuccess ls) {
            }
        }
        jj_rescan = false;
    }

    private void jj_save(int index, int xla) {
        JJCalls p = jj_2_rtns[index];
        while (p.gen > jj_gen) {
            if (p.next == null) {
                p = p.next = new JJCalls();
                break;
            }
            p = p.next;
        }
        p.gen = jj_gen + xla - jj_la;
        p.first = token;
        p.arg = xla;
    }

    static final class JJCalls {
        int gen;
        Token first;
        int arg;
        JJCalls next;
    }

}