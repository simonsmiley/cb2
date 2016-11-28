/* Generated By:JavaCC: Do not edit this line. MINIGrammarConstants.java */
package parser;


/**
 * Token literal values and constants.
 * Generated by org.javacc.parser.OtherFilesGen#start()
 */
public interface MINIGrammarConstants {

  /** End of File. */
  int EOF = 0;
  /** RegularExpression Id. */
  int SINGLE_LINE_COMMENT = 7;
  /** RegularExpression Id. */
  int MULTI_LINE_COMMENT = 8;
  /** RegularExpression Id. */
  int CLASS = 10;
  /** RegularExpression Id. */
  int BRACE_OPEN = 11;
  /** RegularExpression Id. */
  int BRACE_CLOSE = 12;
  /** RegularExpression Id. */
  int PARAN_OPEN = 13;
  /** RegularExpression Id. */
  int PARAN_CLOSE = 14;
  /** RegularExpression Id. */
  int ARRAY_BEGIN = 15;
  /** RegularExpression Id. */
  int ARRAY_END = 16;
  /** RegularExpression Id. */
  int THIS = 17;
  /** RegularExpression Id. */
  int DOT = 18;
  /** RegularExpression Id. */
  int COMMA = 19;
  /** RegularExpression Id. */
  int SEMICOLON = 20;
  /** RegularExpression Id. */
  int NULL = 21;
  /** RegularExpression Id. */
  int NEW = 22;
  /** RegularExpression Id. */
  int RETURN = 23;
  /** RegularExpression Id. */
  int VAR = 24;
  /** RegularExpression Id. */
  int IF = 25;
  /** RegularExpression Id. */
  int ELSE = 26;
  /** RegularExpression Id. */
  int WHILE = 27;
  /** RegularExpression Id. */
  int ASSIGNMENT = 28;
  /** RegularExpression Id. */
  int MINUS = 29;
  /** RegularExpression Id. */
  int NEGATION = 30;
  /** RegularExpression Id. */
  int PLUS = 31;
  /** RegularExpression Id. */
  int MULTIPLY = 32;
  /** RegularExpression Id. */
  int DIVIDE = 33;
  /** RegularExpression Id. */
  int REMAINDER = 34;
  /** RegularExpression Id. */
  int EQUAL = 35;
  /** RegularExpression Id. */
  int NOTEQUAL = 36;
  /** RegularExpression Id. */
  int LESS_THAN_EQUAL = 37;
  /** RegularExpression Id. */
  int GREATER_THAN_EQUAL = 38;
  /** RegularExpression Id. */
  int LESS_THAN = 39;
  /** RegularExpression Id. */
  int GREATER_THAN = 40;
  /** RegularExpression Id. */
  int AND = 41;
  /** RegularExpression Id. */
  int OR = 42;
  /** RegularExpression Id. */
  int BOOL = 43;
  /** RegularExpression Id. */
  int ID = 44;
  /** RegularExpression Id. */
  int INT = 45;
  /** RegularExpression Id. */
  int STRING = 46;
  /** RegularExpression Id. */
  int BAD_TOKEN = 47;

  /** Lexical state. */
  int DEFAULT = 0;
  /** Lexical state. */
  int IN_SINGLE_LINE_COMMENT = 1;
  /** Lexical state. */
  int IN_MULTI_LINE_COMMENT = 2;

  /** Literal token values. */
  String[] tokenImage = {
    "<EOF>",
    "\" \"",
    "\"\\r\"",
    "\"\\t\"",
    "\"\\n\"",
    "\"//\"",
    "\"/*\"",
    "<SINGLE_LINE_COMMENT>",
    "\"*/\"",
    "<token of kind 9>",
    "\"class\"",
    "\"{\"",
    "\"}\"",
    "\"(\"",
    "\")\"",
    "\"[\"",
    "\"]\"",
    "\"this\"",
    "\".\"",
    "\",\"",
    "\";\"",
    "\"null\"",
    "\"new\"",
    "\"return\"",
    "\"var\"",
    "\"if\"",
    "\"else\"",
    "\"while\"",
    "\":=\"",
    "\"-\"",
    "\"!\"",
    "\"+\"",
    "\"*\"",
    "\"/\"",
    "\"%\"",
    "\"==\"",
    "\"!=\"",
    "\"<=\"",
    "\">=\"",
    "\"<\"",
    "\">\"",
    "\"&&\"",
    "\"||\"",
    "<BOOL>",
    "<ID>",
    "<INT>",
    "<STRING>",
    "<BAD_TOKEN>",
  };

}
