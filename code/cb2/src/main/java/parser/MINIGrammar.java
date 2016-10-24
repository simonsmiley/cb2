/* Generated By:JavaCC: Do not edit this line. MINIGrammar.java */
package parser;

import java.io.FileInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import components.*;
import java.util.ArrayList;
import testsuite.MINIException;
import errorHandling.ErrorHandler;

public class MINIGrammar implements MINIGrammarConstants {
    public static ArrayList<ClassNode> parse(File in) throws MINIException {
        try {

            MINIGrammar parser = new MINIGrammar(new FileInputStream(in));
            return parser.file();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (ParseException e) {
            ErrorHandler.handleParseError(in, e.currentToken, e.tokenImage, e.expectedTokenSequences);
            return null;
        }
    }

    final public ArrayList<ClassNode> file() throws ParseException {
        ArrayList<ClassNode> classes = new ArrayList<ClassNode>();
        ClassNode cls = new ClassNode();
        label_1: while (true) {
            switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
            case CLASS:
                ;
                break;
            default:
                jj_la1[0] = jj_gen;
                break label_1;
            }
            cls = mini_class();
            classes.add(cls);
        }
        jj_consume_token(0);
        {
            if (true)
                return classes;
        }
        throw new Error("Missing return statement in function");
    }

    final public ClassNode mini_class() throws ParseException {
        ClassNode cls = new ClassNode();
        Token clsName;
        Node classMember;
        jj_consume_token(CLASS);
        clsName = jj_consume_token(ID);
        jj_consume_token(BRACE_OPEN);
        label_2: while (true) {
            switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
            case ID:
                ;
                break;
            default:
                jj_la1[1] = jj_gen;
                break label_2;
            }
            classMember = classMember();
            cls.children.add(classMember);
        }
        jj_consume_token(BRACE_CLOSE);
        cls.name = clsName.image;
        {
            if (true)
                return cls;
        }
        throw new Error("Missing return statement in function");
    }

    final public Node classMember() throws ParseException {
        FieldNode f = null;
        MethodNode m = null;
        Type memberType;
        Token memberName;
        memberType = type();
        memberName = jj_consume_token(ID);
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
        case SEMICOLON:
            f = field();
            break;
        case PARAN_OPEN:
            m = method();
            break;
        default:
            jj_la1[2] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
        }
        if (f == null) {
            m.name = memberName.image;
            m.returnType = memberType;
            {
                if (true)
                    return m;
            }
        } else {
            f.name = memberName.image;
            f.type = memberType;
            {
                if (true)
                    return f;
            }
        }
        throw new Error("Missing return statement in function");
    }

    final public FieldNode field() throws ParseException {
        jj_consume_token(SEMICOLON);
        {
            if (true)
                return new FieldNode();
        }
        throw new Error("Missing return statement in function");
    }

    final public MethodNode method() throws ParseException {
        MethodNode method = new MethodNode();
        ArrayList<FieldNode> arguments;
        arguments = signature();
        blockStatement();
        method.arguments = arguments;
        {
            if (true)
                return method;
        }
        throw new Error("Missing return statement in function");
    }

    final public Type type() throws ParseException {
        Token typeIdentifier;
        typeIdentifier = jj_consume_token(ID);
        label_3: while (true) {
            switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
            case ARRAY_DEF:
                ;
                break;
            default:
                jj_la1[3] = jj_gen;
                break label_3;
            }
            jj_consume_token(ARRAY_DEF);
        }
        switch (typeIdentifier.image) {
        case "int": {
            if (true)
                return Type.INTEGER;
        }
        case "string": {
            if (true)
                return Type.STRING;
        }
        case "boolean": {
            if (true)
                return Type.BOOLEAN;
        }
        case "void": {
            if (true)
                return Type.VOID;
        }
        default: {
            if (true)
                return Type.INVALID;
        }
        }
        throw new Error("Missing return statement in function");
    }

    final public ArrayList<FieldNode> signature() throws ParseException {
        ArrayList<FieldNode> arguments = new ArrayList<FieldNode>();
        Token argument;
        Type type;
        jj_consume_token(PARAN_OPEN);
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
        case ID:
            type = type();
            argument = jj_consume_token(ID);
            label_4: while (true) {
                switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                case COMMA:
                    ;
                    break;
                default:
                    jj_la1[4] = jj_gen;
                    break label_4;
                }
                jj_consume_token(COMMA);
                type = type();
                argument = jj_consume_token(ID);
                FieldNode n = new FieldNode();
                n.type = type;
                n.name = argument.image;
                arguments.add(n);
            }
            FieldNode n = new FieldNode();
            n.type = type;
            n.name = argument.image;
            arguments.add(n);
            break;
        default:
            jj_la1[5] = jj_gen;
            ;
        }
        jj_consume_token(PARAN_CLOSE);
        {
            if (true)
                return arguments;
        }
        throw new Error("Missing return statement in function");
    }

    final public void statement() throws ParseException {
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
        case BRACE_OPEN:
            blockStatement();
            break;
        case IF:
            ifStatement();
            break;
        case WHILE:
            whileStatement();
            break;
        case PARAN_OPEN:
        case THIS:
        case NULL:
        case NEW:
        case MINUS:
        case NEGATION:
        case ID:
        case INT:
        case BOOL:
        case STRING:
            expression();
            switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
            case ASSIGNMENT:
                jj_consume_token(ASSIGNMENT);
                expression();
                jj_consume_token(SEMICOLON);
                break;
            case SEMICOLON:
                jj_consume_token(SEMICOLON);
                break;
            default:
                jj_la1[6] = jj_gen;
                jj_consume_token(-1);
                throw new ParseException();
            }
            break;
        case VAR:
            jj_consume_token(VAR);
            jj_consume_token(ID);
            jj_consume_token(ASSIGNMENT);
            expression();
            jj_consume_token(SEMICOLON);
            break;
        case RETURN:
            returnStatement();
            break;
        default:
            jj_la1[7] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
        }
    }

    final public void blockStatement() throws ParseException {
        jj_consume_token(BRACE_OPEN);
        label_5: while (true) {
            switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
            case BRACE_OPEN:
            case PARAN_OPEN:
            case THIS:
            case NULL:
            case NEW:
            case RETURN:
            case VAR:
            case IF:
            case WHILE:
            case MINUS:
            case NEGATION:
            case ID:
            case INT:
            case BOOL:
            case STRING:
                ;
                break;
            default:
                jj_la1[8] = jj_gen;
                break label_5;
            }
            statement();
        }
        jj_consume_token(BRACE_CLOSE);
    }

    final public void ifStatement() throws ParseException {
        jj_consume_token(IF);
        jj_consume_token(PARAN_OPEN);
        expression();
        jj_consume_token(PARAN_CLOSE);
        blockStatement();
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
        case ELSE:
            jj_consume_token(ELSE);
            blockStatement();
            break;
        default:
            jj_la1[9] = jj_gen;
            ;
        }
    }

    final public void whileStatement() throws ParseException {
        jj_consume_token(WHILE);
        jj_consume_token(PARAN_OPEN);
        expression();
        jj_consume_token(PARAN_CLOSE);
        blockStatement();
    }

    final public void returnStatement() throws ParseException {
        jj_consume_token(RETURN);
        expression();
        jj_consume_token(SEMICOLON);
    }

    final public void expression() throws ParseException {
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
        case MINUS:
        case NEGATION:
            unaryOperator();
            expression();
            break;
        case PARAN_OPEN:
        case THIS:
        case NULL:
        case NEW:
        case ID:
        case INT:
        case BOOL:
        case STRING:
            atomicExpression();
            break;
        default:
            jj_la1[10] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
        }
        label_6: while (true) {
            switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
            case PARAN_OPEN:
            case DOT:
            case MINUS:
            case PLUS:
            case MULTIPLY:
            case DIVIDE:
            case REMAINDER:
            case EQUAL:
            case NOTEQUAL:
            case LESS_THAN_EQUAL:
            case GREATER_THAN_EQUAL:
            case LESS_THAN:
            case GREATER_THAN:
            case AND:
            case OR:
                ;
                break;
            default:
                jj_la1[11] = jj_gen;
                break label_6;
            }
            switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
            case MINUS:
            case PLUS:
            case MULTIPLY:
            case DIVIDE:
            case REMAINDER:
            case EQUAL:
            case NOTEQUAL:
            case LESS_THAN_EQUAL:
            case GREATER_THAN_EQUAL:
            case LESS_THAN:
            case GREATER_THAN:
            case AND:
            case OR:
                binaryOperator();
                expression();
                break;
            case PARAN_OPEN:
                argumentList();
                break;
            case DOT:
                jj_consume_token(DOT);
                jj_consume_token(ID);
                break;
            default:
                jj_la1[12] = jj_gen;
                jj_consume_token(-1);
                throw new ParseException();
            }
        }
    }

    final public void unaryOperator() throws ParseException {
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
        case MINUS:
            jj_consume_token(MINUS);
            break;
        case NEGATION:
            jj_consume_token(NEGATION);
            break;
        default:
            jj_la1[13] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
        }
    }

    final public void binaryOperator() throws ParseException {
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
        case PLUS:
            jj_consume_token(PLUS);
            break;
        case MINUS:
            jj_consume_token(MINUS);
            break;
        case MULTIPLY:
            jj_consume_token(MULTIPLY);
            break;
        case DIVIDE:
            jj_consume_token(DIVIDE);
            break;
        case REMAINDER:
            jj_consume_token(REMAINDER);
            break;
        case EQUAL:
            jj_consume_token(EQUAL);
            break;
        case NOTEQUAL:
            jj_consume_token(NOTEQUAL);
            break;
        case LESS_THAN_EQUAL:
            jj_consume_token(LESS_THAN_EQUAL);
            break;
        case GREATER_THAN_EQUAL:
            jj_consume_token(GREATER_THAN_EQUAL);
            break;
        case LESS_THAN:
            jj_consume_token(LESS_THAN);
            break;
        case GREATER_THAN:
            jj_consume_token(GREATER_THAN);
            break;
        case AND:
            jj_consume_token(AND);
            break;
        case OR:
            jj_consume_token(OR);
            break;
        default:
            jj_la1[14] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
        }
    }

    final public void argumentList() throws ParseException {
        jj_consume_token(PARAN_OPEN);
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
        case PARAN_OPEN:
        case THIS:
        case NULL:
        case NEW:
        case MINUS:
        case NEGATION:
        case ID:
        case INT:
        case BOOL:
        case STRING:
            expression();
            label_7: while (true) {
                switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
                case COMMA:
                    ;
                    break;
                default:
                    jj_la1[15] = jj_gen;
                    break label_7;
                }
                jj_consume_token(COMMA);
                expression();
            }
            break;
        default:
            jj_la1[16] = jj_gen;
            ;
        }
        jj_consume_token(PARAN_CLOSE);
    }

    final public void atomicExpression() throws ParseException {
        switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
        case ID:
            jj_consume_token(ID);
            break;
        case THIS:
            jj_consume_token(THIS);
            break;
        case STRING:
            jj_consume_token(STRING);
            break;
        case INT:
            jj_consume_token(INT);
            break;
        case BOOL:
            jj_consume_token(BOOL);
            break;
        case NULL:
            nullExpression();
            break;
        case NEW:
            newExpression();
            break;
        case PARAN_OPEN:
            jj_consume_token(PARAN_OPEN);
            expression();
            jj_consume_token(PARAN_CLOSE);
            break;
        default:
            jj_la1[17] = jj_gen;
            jj_consume_token(-1);
            throw new ParseException();
        }
    }

    final public void nullExpression() throws ParseException {
        jj_consume_token(NULL);
        jj_consume_token(PARAN_OPEN);
        type();
        jj_consume_token(PARAN_CLOSE);
    }

    final public void newExpression() throws ParseException {
        jj_consume_token(NEW);
        jj_consume_token(PARAN_OPEN);
        type();
        label_8: while (true) {
            switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
            case COMMA:
                ;
                break;
            default:
                jj_la1[18] = jj_gen;
                break label_8;
            }
            jj_consume_token(COMMA);
            switch ((jj_ntk == -1) ? jj_ntk() : jj_ntk) {
            case INT:
                jj_consume_token(INT);
                break;
            case ID:
                jj_consume_token(ID);
                break;
            default:
                jj_la1[19] = jj_gen;
                jj_consume_token(-1);
                throw new ParseException();
            }
        }
        jj_consume_token(PARAN_CLOSE);
    }

    /** Generated Token Manager. */
    public MINIGrammarTokenManager token_source;
    SimpleCharStream jj_input_stream;
    /** Current token. */
    public Token token;
    /** Next token. */
    public Token jj_nt;
    private int jj_ntk;
    private int jj_gen;
    final private int[] jj_la1 = new int[20];
    static private int[] jj_la1_0;
    static private int[] jj_la1_1;
    static {
        jj_la1_init_0();
        jj_la1_init_1();
    }

    private static void jj_la1_init_0() {
        jj_la1_0 = new int[] { 0x400, 0x0, 0x82000, 0x8000, 0x40000, 0x0, 0x8080000, 0x35f12800, 0x35f12800, 0x2000000,
                0x30312000, 0xd0022000, 0xd0022000, 0x30000000, 0xd0000000, 0x40000, 0x30312000, 0x312000, 0x40000,
                0x0, };
    }

    private static void jj_la1_init_1() {
        jj_la1_1 = new int[] { 0x0, 0x1000, 0x0, 0x0, 0x0, 0x1000, 0x0, 0xf000, 0xf000, 0x0, 0xf000, 0x3ff, 0x3ff, 0x0,
                0x3ff, 0x0, 0xf000, 0xf000, 0x0, 0x3000, };
    }

    /** Constructor with InputStream. */
    public MINIGrammar(java.io.InputStream stream) {
        this(stream, null);
    }

    /** Constructor with InputStream and supplied encoding */
    public MINIGrammar(java.io.InputStream stream, String encoding) {
        try {
            jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1);
        } catch (java.io.UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        token_source = new MINIGrammarTokenManager(jj_input_stream);
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for (int i = 0; i < 20; i++)
            jj_la1[i] = -1;
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
        for (int i = 0; i < 20; i++)
            jj_la1[i] = -1;
    }

    /** Constructor. */
    public MINIGrammar(java.io.Reader stream) {
        jj_input_stream = new SimpleCharStream(stream, 1, 1);
        token_source = new MINIGrammarTokenManager(jj_input_stream);
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for (int i = 0; i < 20; i++)
            jj_la1[i] = -1;
    }

    /** Reinitialise. */
    public void ReInit(java.io.Reader stream) {
        jj_input_stream.ReInit(stream, 1, 1);
        token_source.ReInit(jj_input_stream);
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for (int i = 0; i < 20; i++)
            jj_la1[i] = -1;
    }

    /** Constructor with generated Token Manager. */
    public MINIGrammar(MINIGrammarTokenManager tm) {
        token_source = tm;
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for (int i = 0; i < 20; i++)
            jj_la1[i] = -1;
    }

    /** Reinitialise. */
    public void ReInit(MINIGrammarTokenManager tm) {
        token_source = tm;
        token = new Token();
        jj_ntk = -1;
        jj_gen = 0;
        for (int i = 0; i < 20; i++)
            jj_la1[i] = -1;
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
            return token;
        }
        token = oldToken;
        jj_kind = kind;
        throw generateParseException();
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

    /** Generate ParseException. */
    public ParseException generateParseException() {
        jj_expentries.clear();
        boolean[] la1tokens = new boolean[49];
        if (jj_kind >= 0) {
            la1tokens[jj_kind] = true;
            jj_kind = -1;
        }
        for (int i = 0; i < 20; i++) {
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
        for (int i = 0; i < 49; i++) {
            if (la1tokens[i]) {
                jj_expentry = new int[1];
                jj_expentry[0] = i;
                jj_expentries.add(jj_expentry);
            }
        }
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

}
