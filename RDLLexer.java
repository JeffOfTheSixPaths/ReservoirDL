// Generated from RDL.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RDLLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		DATA=1, LOAD=2, CSV=3, LSM=4, LAYER=5, TORCH=6, CONNECT=7, WEIGHT=8, SIMULATE=9, 
		WITH=10, SAVE=11, SPIKE=12, IF=13, EMIT=14, SIZE=15, TYPE=16, STEPS=17, 
		ARROW=18, ASSIGN=19, PLUS_ASSIGN=20, MINUS_ASSIGN=21, MUL_ASSIGN=22, DIV_ASSIGN=23, 
		GT=24, LT=25, GTE=26, LTE=27, EQ=28, NEQ=29, PLUS=30, MINUS=31, INCREMENT=32, 
		MUL=33, DIV=34, LPAREN=35, RPAREN=36, COMMA=37, LBRACE=38, RBRACE=39, 
		ID=40, INT=41, FLOAT=42, STRING=43, LINE_COMMENT=44, HASH_COMMENT=45, 
		MINUS_COMMENT=46, BLOCK_COMMENT=47, WS=48;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"DATA", "LOAD", "CSV", "LSM", "LAYER", "TORCH", "CONNECT", "WEIGHT", 
			"SIMULATE", "WITH", "SAVE", "SPIKE", "IF", "EMIT", "SIZE", "TYPE", "STEPS", 
			"ARROW", "ASSIGN", "PLUS_ASSIGN", "MINUS_ASSIGN", "MUL_ASSIGN", "DIV_ASSIGN", 
			"GT", "LT", "GTE", "LTE", "EQ", "NEQ", "PLUS", "MINUS", "INCREMENT", 
			"MUL", "DIV", "LPAREN", "RPAREN", "COMMA", "LBRACE", "RBRACE", "ID", 
			"INT", "FLOAT", "STRING", "LINE_COMMENT", "HASH_COMMENT", "MINUS_COMMENT", 
			"BLOCK_COMMENT", "WS"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'data'", "'load'", "'csv'", "'LSM'", "'layer'", "'torch'", "'connect'", 
			"'weight'", "'simulate'", "'with'", "'save'", "'spike'", "'if'", "'emit'", 
			"'size'", "'type'", "'steps'", "'->'", "'='", "'+='", "'-='", "'*='", 
			"'/='", "'>'", "'<'", "'>='", "'<='", "'=='", "'!='", "'+'", "'-'", "'++'", 
			"'*'", "'/'", "'('", "')'", "','", "'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "DATA", "LOAD", "CSV", "LSM", "LAYER", "TORCH", "CONNECT", "WEIGHT", 
			"SIMULATE", "WITH", "SAVE", "SPIKE", "IF", "EMIT", "SIZE", "TYPE", "STEPS", 
			"ARROW", "ASSIGN", "PLUS_ASSIGN", "MINUS_ASSIGN", "MUL_ASSIGN", "DIV_ASSIGN", 
			"GT", "LT", "GTE", "LTE", "EQ", "NEQ", "PLUS", "MINUS", "INCREMENT", 
			"MUL", "DIV", "LPAREN", "RPAREN", "COMMA", "LBRACE", "RBRACE", "ID", 
			"INT", "FLOAT", "STRING", "LINE_COMMENT", "HASH_COMMENT", "MINUS_COMMENT", 
			"BLOCK_COMMENT", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public RDLLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "RDL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\62\u014d\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t"+
		" \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t"+
		"+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\3\2\3\2\3\2\3\2\3\2\3\3"+
		"\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t"+
		"\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3"+
		"\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3"+
		"\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3"+
		"\21\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\25\3\25\3"+
		"\25\3\26\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\32\3\32\3"+
		"\33\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3"+
		" \3 \3!\3!\3!\3\"\3\"\3#\3#\3$\3$\3%\3%\3&\3&\3\'\3\'\3(\3(\3)\3)\7)\u00fa"+
		"\n)\f)\16)\u00fd\13)\3*\6*\u0100\n*\r*\16*\u0101\3+\6+\u0105\n+\r+\16"+
		"+\u0106\3+\3+\6+\u010b\n+\r+\16+\u010c\3,\3,\3,\3,\7,\u0113\n,\f,\16,"+
		"\u0116\13,\3,\3,\3-\3-\3-\3-\7-\u011e\n-\f-\16-\u0121\13-\3-\3-\3.\3."+
		"\7.\u0127\n.\f.\16.\u012a\13.\3.\3.\3/\3/\3/\3/\7/\u0132\n/\f/\16/\u0135"+
		"\13/\3/\3/\3\60\3\60\3\60\3\60\7\60\u013d\n\60\f\60\16\60\u0140\13\60"+
		"\3\60\3\60\3\60\3\60\3\60\3\61\6\61\u0148\n\61\r\61\16\61\u0149\3\61\3"+
		"\61\3\u013e\2\62\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31"+
		"\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65"+
		"\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\61a\62\3\2\b"+
		"\5\2C\\aac|\6\2\62;C\\aac|\3\2\62;\4\2$$^^\4\2\f\f\17\17\5\2\13\f\17\17"+
		"\"\"\2\u0157\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2"+
		"\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2"+
		"\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2"+
		"\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2"+
		"\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3"+
		"\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2"+
		"\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2"+
		"S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2_\3"+
		"\2\2\2\2a\3\2\2\2\3c\3\2\2\2\5h\3\2\2\2\7m\3\2\2\2\tq\3\2\2\2\13u\3\2"+
		"\2\2\r{\3\2\2\2\17\u0081\3\2\2\2\21\u0089\3\2\2\2\23\u0090\3\2\2\2\25"+
		"\u0099\3\2\2\2\27\u009e\3\2\2\2\31\u00a3\3\2\2\2\33\u00a9\3\2\2\2\35\u00ac"+
		"\3\2\2\2\37\u00b1\3\2\2\2!\u00b6\3\2\2\2#\u00bb\3\2\2\2%\u00c1\3\2\2\2"+
		"\'\u00c4\3\2\2\2)\u00c6\3\2\2\2+\u00c9\3\2\2\2-\u00cc\3\2\2\2/\u00cf\3"+
		"\2\2\2\61\u00d2\3\2\2\2\63\u00d4\3\2\2\2\65\u00d6\3\2\2\2\67\u00d9\3\2"+
		"\2\29\u00dc\3\2\2\2;\u00df\3\2\2\2=\u00e2\3\2\2\2?\u00e4\3\2\2\2A\u00e6"+
		"\3\2\2\2C\u00e9\3\2\2\2E\u00eb\3\2\2\2G\u00ed\3\2\2\2I\u00ef\3\2\2\2K"+
		"\u00f1\3\2\2\2M\u00f3\3\2\2\2O\u00f5\3\2\2\2Q\u00f7\3\2\2\2S\u00ff\3\2"+
		"\2\2U\u0104\3\2\2\2W\u010e\3\2\2\2Y\u0119\3\2\2\2[\u0124\3\2\2\2]\u012d"+
		"\3\2\2\2_\u0138\3\2\2\2a\u0147\3\2\2\2cd\7f\2\2de\7c\2\2ef\7v\2\2fg\7"+
		"c\2\2g\4\3\2\2\2hi\7n\2\2ij\7q\2\2jk\7c\2\2kl\7f\2\2l\6\3\2\2\2mn\7e\2"+
		"\2no\7u\2\2op\7x\2\2p\b\3\2\2\2qr\7N\2\2rs\7U\2\2st\7O\2\2t\n\3\2\2\2"+
		"uv\7n\2\2vw\7c\2\2wx\7{\2\2xy\7g\2\2yz\7t\2\2z\f\3\2\2\2{|\7v\2\2|}\7"+
		"q\2\2}~\7t\2\2~\177\7e\2\2\177\u0080\7j\2\2\u0080\16\3\2\2\2\u0081\u0082"+
		"\7e\2\2\u0082\u0083\7q\2\2\u0083\u0084\7p\2\2\u0084\u0085\7p\2\2\u0085"+
		"\u0086\7g\2\2\u0086\u0087\7e\2\2\u0087\u0088\7v\2\2\u0088\20\3\2\2\2\u0089"+
		"\u008a\7y\2\2\u008a\u008b\7g\2\2\u008b\u008c\7k\2\2\u008c\u008d\7i\2\2"+
		"\u008d\u008e\7j\2\2\u008e\u008f\7v\2\2\u008f\22\3\2\2\2\u0090\u0091\7"+
		"u\2\2\u0091\u0092\7k\2\2\u0092\u0093\7o\2\2\u0093\u0094\7w\2\2\u0094\u0095"+
		"\7n\2\2\u0095\u0096\7c\2\2\u0096\u0097\7v\2\2\u0097\u0098\7g\2\2\u0098"+
		"\24\3\2\2\2\u0099\u009a\7y\2\2\u009a\u009b\7k\2\2\u009b\u009c\7v\2\2\u009c"+
		"\u009d\7j\2\2\u009d\26\3\2\2\2\u009e\u009f\7u\2\2\u009f\u00a0\7c\2\2\u00a0"+
		"\u00a1\7x\2\2\u00a1\u00a2\7g\2\2\u00a2\30\3\2\2\2\u00a3\u00a4\7u\2\2\u00a4"+
		"\u00a5\7r\2\2\u00a5\u00a6\7k\2\2\u00a6\u00a7\7m\2\2\u00a7\u00a8\7g\2\2"+
		"\u00a8\32\3\2\2\2\u00a9\u00aa\7k\2\2\u00aa\u00ab\7h\2\2\u00ab\34\3\2\2"+
		"\2\u00ac\u00ad\7g\2\2\u00ad\u00ae\7o\2\2\u00ae\u00af\7k\2\2\u00af\u00b0"+
		"\7v\2\2\u00b0\36\3\2\2\2\u00b1\u00b2\7u\2\2\u00b2\u00b3\7k\2\2\u00b3\u00b4"+
		"\7|\2\2\u00b4\u00b5\7g\2\2\u00b5 \3\2\2\2\u00b6\u00b7\7v\2\2\u00b7\u00b8"+
		"\7{\2\2\u00b8\u00b9\7r\2\2\u00b9\u00ba\7g\2\2\u00ba\"\3\2\2\2\u00bb\u00bc"+
		"\7u\2\2\u00bc\u00bd\7v\2\2\u00bd\u00be\7g\2\2\u00be\u00bf\7r\2\2\u00bf"+
		"\u00c0\7u\2\2\u00c0$\3\2\2\2\u00c1\u00c2\7/\2\2\u00c2\u00c3\7@\2\2\u00c3"+
		"&\3\2\2\2\u00c4\u00c5\7?\2\2\u00c5(\3\2\2\2\u00c6\u00c7\7-\2\2\u00c7\u00c8"+
		"\7?\2\2\u00c8*\3\2\2\2\u00c9\u00ca\7/\2\2\u00ca\u00cb\7?\2\2\u00cb,\3"+
		"\2\2\2\u00cc\u00cd\7,\2\2\u00cd\u00ce\7?\2\2\u00ce.\3\2\2\2\u00cf\u00d0"+
		"\7\61\2\2\u00d0\u00d1\7?\2\2\u00d1\60\3\2\2\2\u00d2\u00d3\7@\2\2\u00d3"+
		"\62\3\2\2\2\u00d4\u00d5\7>\2\2\u00d5\64\3\2\2\2\u00d6\u00d7\7@\2\2\u00d7"+
		"\u00d8\7?\2\2\u00d8\66\3\2\2\2\u00d9\u00da\7>\2\2\u00da\u00db\7?\2\2\u00db"+
		"8\3\2\2\2\u00dc\u00dd\7?\2\2\u00dd\u00de\7?\2\2\u00de:\3\2\2\2\u00df\u00e0"+
		"\7#\2\2\u00e0\u00e1\7?\2\2\u00e1<\3\2\2\2\u00e2\u00e3\7-\2\2\u00e3>\3"+
		"\2\2\2\u00e4\u00e5\7/\2\2\u00e5@\3\2\2\2\u00e6\u00e7\7-\2\2\u00e7\u00e8"+
		"\7-\2\2\u00e8B\3\2\2\2\u00e9\u00ea\7,\2\2\u00eaD\3\2\2\2\u00eb\u00ec\7"+
		"\61\2\2\u00ecF\3\2\2\2\u00ed\u00ee\7*\2\2\u00eeH\3\2\2\2\u00ef\u00f0\7"+
		"+\2\2\u00f0J\3\2\2\2\u00f1\u00f2\7.\2\2\u00f2L\3\2\2\2\u00f3\u00f4\7}"+
		"\2\2\u00f4N\3\2\2\2\u00f5\u00f6\7\177\2\2\u00f6P\3\2\2\2\u00f7\u00fb\t"+
		"\2\2\2\u00f8\u00fa\t\3\2\2\u00f9\u00f8\3\2\2\2\u00fa\u00fd\3\2\2\2\u00fb"+
		"\u00f9\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fcR\3\2\2\2\u00fd\u00fb\3\2\2\2"+
		"\u00fe\u0100\t\4\2\2\u00ff\u00fe\3\2\2\2\u0100\u0101\3\2\2\2\u0101\u00ff"+
		"\3\2\2\2\u0101\u0102\3\2\2\2\u0102T\3\2\2\2\u0103\u0105\t\4\2\2\u0104"+
		"\u0103\3\2\2\2\u0105\u0106\3\2\2\2\u0106\u0104\3\2\2\2\u0106\u0107\3\2"+
		"\2\2\u0107\u0108\3\2\2\2\u0108\u010a\7\60\2\2\u0109\u010b\t\4\2\2\u010a"+
		"\u0109\3\2\2\2\u010b\u010c\3\2\2\2\u010c\u010a\3\2\2\2\u010c\u010d\3\2"+
		"\2\2\u010dV\3\2\2\2\u010e\u0114\7$\2\2\u010f\u0113\n\5\2\2\u0110\u0111"+
		"\7^\2\2\u0111\u0113\13\2\2\2\u0112\u010f\3\2\2\2\u0112\u0110\3\2\2\2\u0113"+
		"\u0116\3\2\2\2\u0114\u0112\3\2\2\2\u0114\u0115\3\2\2\2\u0115\u0117\3\2"+
		"\2\2\u0116\u0114\3\2\2\2\u0117\u0118\7$\2\2\u0118X\3\2\2\2\u0119\u011a"+
		"\7\61\2\2\u011a\u011b\7\61\2\2\u011b\u011f\3\2\2\2\u011c\u011e\n\6\2\2"+
		"\u011d\u011c\3\2\2\2\u011e\u0121\3\2\2\2\u011f\u011d\3\2\2\2\u011f\u0120"+
		"\3\2\2\2\u0120\u0122\3\2\2\2\u0121\u011f\3\2\2\2\u0122\u0123\b-\2\2\u0123"+
		"Z\3\2\2\2\u0124\u0128\7%\2\2\u0125\u0127\n\6\2\2\u0126\u0125\3\2\2\2\u0127"+
		"\u012a\3\2\2\2\u0128\u0126\3\2\2\2\u0128\u0129\3\2\2\2\u0129\u012b\3\2"+
		"\2\2\u012a\u0128\3\2\2\2\u012b\u012c\b.\2\2\u012c\\\3\2\2\2\u012d\u012e"+
		"\7/\2\2\u012e\u012f\7/\2\2\u012f\u0133\3\2\2\2\u0130\u0132\n\6\2\2\u0131"+
		"\u0130\3\2\2\2\u0132\u0135\3\2\2\2\u0133\u0131\3\2\2\2\u0133\u0134\3\2"+
		"\2\2\u0134\u0136\3\2\2\2\u0135\u0133\3\2\2\2\u0136\u0137\b/\2\2\u0137"+
		"^\3\2\2\2\u0138\u0139\7\61\2\2\u0139\u013a\7,\2\2\u013a\u013e\3\2\2\2"+
		"\u013b\u013d\13\2\2\2\u013c\u013b\3\2\2\2\u013d\u0140\3\2\2\2\u013e\u013f"+
		"\3\2\2\2\u013e\u013c\3\2\2\2\u013f\u0141\3\2\2\2\u0140\u013e\3\2\2\2\u0141"+
		"\u0142\7,\2\2\u0142\u0143\7\61\2\2\u0143\u0144\3\2\2\2\u0144\u0145\b\60"+
		"\2\2\u0145`\3\2\2\2\u0146\u0148\t\7\2\2\u0147\u0146\3\2\2\2\u0148\u0149"+
		"\3\2\2\2\u0149\u0147\3\2\2\2\u0149\u014a\3\2\2\2\u014a\u014b\3\2\2\2\u014b"+
		"\u014c\b\61\2\2\u014cb\3\2\2\2\16\2\u00fb\u0101\u0106\u010c\u0112\u0114"+
		"\u011f\u0128\u0133\u013e\u0149\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}