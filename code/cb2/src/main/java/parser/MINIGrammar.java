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
  public static ArrayList < ClassNode > parse(File in) throws MINIException
  {
    try
    {
      MINIGrammar parser = new MINIGrammar(new FileInputStream(in));
      return parser.file();
    }
    catch (FileNotFoundException e)
    {
      e.printStackTrace();
      return null;
    }
    catch (ParseException e)
    {
      ErrorHandler.handleParseError(in, e.currentToken, e.tokenImage, e.expectedTokenSequences);
      return null;
    }
  }

  final public ArrayList < ClassNode > file() throws ParseException {
  ArrayList < ClassNode > classes = new ArrayList < ClassNode > ();
  ClassNode cls;
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
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
    {if (true) return classes;}
    throw new Error("Missing return statement in function");
  }

  final public ClassNode mini_class() throws ParseException {
  ClassNode cls = new ClassNode();
  Node classMember;
    jj_consume_token(CLASS);
    jj_consume_token(ID);
           cls.name = token.image;
    jj_consume_token(BRACE_OPEN);
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
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
    {if (true) return cls;}
    throw new Error("Missing return statement in function");
  }

  final public Node classMember() throws ParseException {
  FieldNode declaration = new FieldNode();
  MethodNode m;
  Token memberName;
  Type type;
    type = type();
    memberName = jj_consume_token(ID);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case SEMICOLON:
      jj_consume_token(SEMICOLON);
      declaration.name = memberName;
      declaration.type = type;

      {if (true) return declaration;}
      break;
    case PARAN_OPEN:
      m = method();
      m.name = memberName;
      m.returnType = type;
      {if (true) return m;}
      break;
    default:
      jj_la1[2] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public MethodNode method() throws ParseException {
  MethodNode n = new MethodNode();
  BlockNode block;
    n.arguments = signature();
    n.body = blockStatement();
    {if (true) return n;}
    throw new Error("Missing return statement in function");
  }

  final public Type type() throws ParseException {
  Type t = new Type();
    t.baseType = jj_consume_token(ID);
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case ARRAY_DEF:
        ;
        break;
      default:
        jj_la1[3] = jj_gen;
        break label_3;
      }
      jj_consume_token(ARRAY_DEF);
                                       t.arrayDimensions++;
    }
    {if (true) return t;}
    throw new Error("Missing return statement in function");
  }

  final public ArrayList < NamedType > signature() throws ParseException {
  ArrayList < NamedType > arguments = new ArrayList < NamedType > ();
  NamedType nT = new NamedType();
    jj_consume_token(PARAN_OPEN);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ID:
      nT.type = type();
      nT.name = jj_consume_token(ID);
      arguments.add(nT);
      label_4:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case COMMA:
          ;
          break;
        default:
          jj_la1[4] = jj_gen;
          break label_4;
        }
        jj_consume_token(COMMA);
        nT = new NamedType();
        nT.type = type();
        nT.name = jj_consume_token(ID);
        arguments.add(nT);
      }
      break;
    default:
      jj_la1[5] = jj_gen;
      ;
    }
    jj_consume_token(PARAN_CLOSE);
    {if (true) return arguments;}
    throw new Error("Missing return statement in function");
  }

  final public StatementNode statement() throws ParseException {
 StatementNode s = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case BRACE_OPEN:
      s = blockStatement();
      break;
    case IF:
      s = ifStatement();
      break;
    case WHILE:
      s = whileStatement();
      break;
    case RETURN:
      s = returnStatement();
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
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case SEMICOLON:
        jj_consume_token(SEMICOLON);
        break;
      case ASSIGNMENT:
        jj_consume_token(ASSIGNMENT);
        expression();
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
    default:
      jj_la1[7] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return s;}
    throw new Error("Missing return statement in function");
  }

  final public BlockNode blockStatement() throws ParseException {
  BlockNode block = new BlockNode();
  StatementNode statement;
    jj_consume_token(BRACE_OPEN);
    label_5:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
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
      statement = statement();
      block.children.add(statement);
    }
    jj_consume_token(BRACE_CLOSE);
    {if (true) return block;}
    throw new Error("Missing return statement in function");
  }

  final public IfNode ifStatement() throws ParseException {
  IfNode n = new IfNode();
    jj_consume_token(IF);
    jj_consume_token(PARAN_OPEN);
    n.condition = expression();
    jj_consume_token(PARAN_CLOSE);
    n.first = blockStatement();
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ELSE:
      jj_consume_token(ELSE);
      n.second = blockStatement();
      break;
    default:
      jj_la1[9] = jj_gen;
      ;
    }
    {if (true) return n;}
    throw new Error("Missing return statement in function");
  }

  final public WhileNode whileStatement() throws ParseException {
  WhileNode n = new WhileNode();
    jj_consume_token(WHILE);
    jj_consume_token(PARAN_OPEN);
    n.condition = expression();
    jj_consume_token(PARAN_CLOSE);
    n.body = blockStatement();
    {if (true) return n;}
    throw new Error("Missing return statement in function");
  }

  final public ReturnNode returnStatement() throws ParseException {
  ReturnNode n = new ReturnNode();
    jj_consume_token(RETURN);
    n.value = expression();
    jj_consume_token(SEMICOLON);
    {if (true) return n;}
    throw new Error("Missing return statement in function");
  }

  final public ExpressionNode expression() throws ParseException {
  ExpressionNode n;
  UnaryExpressionNode unary;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case MINUS:
    case NEGATION:
      unary = unaryOperator();
      unary.child = expression();
      {if (true) return unary;}
      break;
    case PARAN_OPEN:
    case THIS:
    case NULL:
    case NEW:
    case ID:
    case INT:
    case BOOL:
    case STRING:
      n = atomicExpression();
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
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
        n = expression_suffix(n);
        break;
      default:
        jj_la1[10] = jj_gen;
        ;
      }
      break;
    default:
      jj_la1[11] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    {if (true) return n;}
    throw new Error("Missing return statement in function");
  }

  final public ExpressionNode expression_suffix(ExpressionNode prefix) throws ParseException {
  BinaryExpressionNode b;
  MemberExpressionNode m = null;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
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
      b = binaryOperator();
      b.second = expression();
          b.first = prefix;
          b.balance();
          {if (true) return b;}
      break;
    case DOT:
      jj_consume_token(DOT);
            Token identifier;
            ExpressionNode n=null;
      identifier = jj_consume_token(ID);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PARAN_OPEN:
        m = argumentList();
        break;
      default:
        jj_la1[12] = jj_gen;
        ;
      }
            if(m != null){
              m = new FieldMemberExpressionNode();
            }
            m.identifier = identifier;
            m.child = prefix;
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
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
        n = expression_suffix(m);
        break;
      default:
        jj_la1[13] = jj_gen;
        ;
      }
            if(n != null)
              {if (true) return n;}
            {if (true) return m;}
      break;
    default:
      jj_la1[14] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public UnaryExpressionNode unaryOperator() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case MINUS:
      jj_consume_token(MINUS);
              {if (true) return new MinusUnaryExpressionNode(token);}
      break;
    case NEGATION:
      jj_consume_token(NEGATION);
                 {if (true) return new NegationUnaryExpressionNode(token);}
      break;
    default:
      jj_la1[15] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public BinaryExpressionNode binaryOperator() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case PLUS:
      jj_consume_token(PLUS);
             {if (true) return new PlusBinaryExpressionNode(token);}
      break;
    case MINUS:
      jj_consume_token(MINUS);
              {if (true) return new MinusBinaryExpressionNode(token);}
      break;
    case MULTIPLY:
      jj_consume_token(MULTIPLY);
                 {if (true) return new MultiplyBinaryExpressionNode(token);}
      break;
    case DIVIDE:
      jj_consume_token(DIVIDE);
               {if (true) return new DivideBinaryExpressionNode(token);}
      break;
    case REMAINDER:
      jj_consume_token(REMAINDER);
                  {if (true) return new RemainderBinaryExpressionNode(token);}
      break;
    case EQUAL:
      jj_consume_token(EQUAL);
              {if (true) return new EqBinaryExpressionNode(token);}
      break;
    case NOTEQUAL:
      jj_consume_token(NOTEQUAL);
                 {if (true) return new NeqBinaryExpressionNode(token);}
      break;
    case LESS_THAN_EQUAL:
      jj_consume_token(LESS_THAN_EQUAL);
                        {if (true) return new LteBinaryExpressionNode(token);}
      break;
    case GREATER_THAN_EQUAL:
      jj_consume_token(GREATER_THAN_EQUAL);
                           {if (true) return new GteBinaryExpressionNode(token);}
      break;
    case LESS_THAN:
      jj_consume_token(LESS_THAN);
                  {if (true) return new LtBinaryExpressionNode(token);}
      break;
    case GREATER_THAN:
      jj_consume_token(GREATER_THAN);
                     {if (true) return new GtBinaryExpressionNode(token);}
      break;
    case AND:
      jj_consume_token(AND);
            {if (true) return new AndBinaryExpressionNode(token);}
      break;
    case OR:
      jj_consume_token(OR);
           {if (true) return new OrBinaryExpressionNode(token);}
      break;
    default:
      jj_la1[16] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
    throw new Error("Missing return statement in function");
  }

  final public MethodMemberExpressionNode argumentList() throws ParseException {
  MethodMemberExpressionNode n = new MethodMemberExpressionNode();
  ExpressionNode expr;
    jj_consume_token(PARAN_OPEN);
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
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
      expr = expression();
      n.children.add(expr);
      label_6:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
        case COMMA:
          ;
          break;
        default:
          jj_la1[17] = jj_gen;
          break label_6;
        }
        jj_consume_token(COMMA);
        expr = expression();
        n.children.add(expr);
      }
      break;
    default:
      jj_la1[18] = jj_gen;
      ;
    }
    jj_consume_token(PARAN_CLOSE);
    {if (true) return n;}
    throw new Error("Missing return statement in function");
  }

  final public ExpressionNode atomicExpression() throws ParseException {
  ExpressionNode n;
  MemberExpressionNode m = null;
  Token identifier;
    switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
    case ID:
      identifier = jj_consume_token(ID);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case PARAN_OPEN:
        m = argumentList();
        break;
      default:
        jj_la1[19] = jj_gen;
        ;
      }
    if(m != null){
          m = new FieldMemberExpressionNode();
        }
        m.identifier = identifier;
        {if (true) return m;}
      break;
    case THIS:
      jj_consume_token(THIS);
               n = new ThisAtomicExpressionNode(token);
      break;
    case STRING:
      jj_consume_token(STRING);
                 n = new StringAtomicExpressionNode(token);
      break;
    case INT:
      jj_consume_token(INT);
              n = new IntAtomicExpressionNode(token);
      break;
    case BOOL:
      jj_consume_token(BOOL);
               n = new BoolAtomicExpressionNode(token);
      break;
    case NULL:
      n = nullExpression();
      break;
    case NEW:
      n = newExpression();
      break;
    case PARAN_OPEN:
      jj_consume_token(PARAN_OPEN);
                     PriorityExpressionNode priorityNode = new PriorityExpressionNode();
      priorityNode.child = expression();
      jj_consume_token(PARAN_CLOSE);
                      {if (true) return priorityNode;}
      break;
    default:
      jj_la1[20] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  {if (true) return n;}
    throw new Error("Missing return statement in function");
  }

  final public NullExpressionNode nullExpression() throws ParseException {
  NullExpressionNode n = new NullExpressionNode();
    jj_consume_token(NULL);
    jj_consume_token(LESS_THAN);
    n.type = type();
    jj_consume_token(GREATER_THAN);
    {if (true) return n;}
    throw new Error("Missing return statement in function");
  }

  final public NewExpressionNode newExpression() throws ParseException {
  NewExpressionNode n = new NewExpressionNode();
    jj_consume_token(NEW);
    jj_consume_token(LESS_THAN);
    n.type = type();
    label_7:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case COMMA:
        ;
        break;
      default:
        jj_la1[21] = jj_gen;
        break label_7;
      }
      jj_consume_token(COMMA);
      switch ((jj_ntk==-1)?jj_ntk():jj_ntk) {
      case INT:
        jj_consume_token(INT);
        break;
      case ID:
        jj_consume_token(ID);
        break;
      default:
        jj_la1[22] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      n.arguments.add(token);
    }
    jj_consume_token(GREATER_THAN);
    {if (true) return n;}
    throw new Error("Missing return statement in function");
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
  final private int[] jj_la1 = new int[23];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
      jj_la1_init_0();
      jj_la1_init_1();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x400,0x0,0x82000,0x8000,0x40000,0x0,0x8080000,0x35f12800,0x35f12800,0x2000000,0xd0020000,0x30312000,0x2000,0xd0020000,0xd0020000,0x30000000,0xd0000000,0x40000,0x30312000,0x2000,0x312000,0x40000,0x0,};
   }
   private static void jj_la1_init_1() {
      jj_la1_1 = new int[] {0x0,0x400,0x0,0x0,0x0,0x400,0x0,0x3c00,0x3c00,0x0,0x3ff,0x3c00,0x0,0x3ff,0x3ff,0x0,0x3ff,0x0,0x3c00,0x0,0x3c00,0x0,0xc00,};
   }

  /** Constructor with InputStream. */
  public MINIGrammar(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public MINIGrammar(java.io.InputStream stream, String encoding) {
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new MINIGrammarTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 23; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream) {
     ReInit(stream, null);
  }
  /** Reinitialise. */
  public void ReInit(java.io.InputStream stream, String encoding) {
    try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 23; i++) jj_la1[i] = -1;
  }

  /** Constructor. */
  public MINIGrammar(java.io.Reader stream) {
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new MINIGrammarTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 23; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 23; i++) jj_la1[i] = -1;
  }

  /** Constructor with generated Token Manager. */
  public MINIGrammar(MINIGrammarTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 23; i++) jj_la1[i] = -1;
  }

  /** Reinitialise. */
  public void ReInit(MINIGrammarTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 23; i++) jj_la1[i] = -1;
  }

  private Token jj_consume_token(int kind) throws ParseException {
    Token oldToken;
    if ((oldToken = token).next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
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
    if (token.next != null) token = token.next;
    else token = token.next = token_source.getNextToken();
    jj_ntk = -1;
    jj_gen++;
    return token;
  }

/** Get the specific Token. */
  final public Token getToken(int index) {
    Token t = token;
    for (int i = 0; i < index; i++) {
      if (t.next != null) t = t.next;
      else t = t.next = token_source.getNextToken();
    }
    return t;
  }

  private int jj_ntk() {
    if ((jj_nt=token.next) == null)
      return (jj_ntk = (token.next=token_source.getNextToken()).kind);
    else
      return (jj_ntk = jj_nt.kind);
  }

  private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  private int[] jj_expentry;
  private int jj_kind = -1;

  /** Generate ParseException. */
  public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[47];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 23; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
          if ((jj_la1_1[i] & (1<<j)) != 0) {
            la1tokens[32+j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 47; i++) {
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
