// Generated from Query.g4 by ANTLR 4.7
package ca.ece.ubc.cpen221.mp5;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QueryLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		IN=1, CATEGORY=2, NAME=3, RATING=4, PRICE=5, LPAREN=6, RPAREN=7, NUM=8, 
		TEXT=9, OR=10, AND=11, INEQ=12, GT=13, GTE=14, LT=15, LTE=16, EQ=17, WS=18;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"IN", "CATEGORY", "NAME", "RATING", "PRICE", "LPAREN", "RPAREN", "NUM", 
		"TEXT", "OR", "AND", "INEQ", "GT", "GTE", "LT", "LTE", "EQ", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, null, "'('", "')'", null, null, "'||'", 
		"'&&'", null, "'>'", "'>='", "'<'", "'<='", "'='"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "IN", "CATEGORY", "NAME", "RATING", "PRICE", "LPAREN", "RPAREN", 
		"NUM", "TEXT", "OR", "AND", "INEQ", "GT", "GTE", "LT", "LTE", "EQ", "WS"
	};
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


	public QueryLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Query.g4"; }

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

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 17:
			WS_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void WS_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			skip();
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\24\u008b\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\5\5G\n\5\3\5\3\5\5\5K\n\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6V\n"+
		"\6\3\6\3\6\5\6Z\n\6\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\6\nf\n\n\r"+
		"\n\16\ng\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\5\rw\n"+
		"\r\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\23\6"+
		"\23\u0086\n\23\r\23\16\23\u0087\3\23\3\23\2\2\24\3\3\5\4\7\5\t\6\13\7"+
		"\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\3\2"+
		"\5\3\2\63\67\7\2\"\"))\62;C\\c|\5\2\13\f\17\17\"\"\2\u0094\2\3\3\2\2\2"+
		"\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2"+
		"\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2"+
		"\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2"+
		"\2\3\'\3\2\2\2\5,\3\2\2\2\7\67\3\2\2\2\t>\3\2\2\2\13N\3\2\2\2\r]\3\2\2"+
		"\2\17_\3\2\2\2\21a\3\2\2\2\23c\3\2\2\2\25k\3\2\2\2\27n\3\2\2\2\31v\3\2"+
		"\2\2\33x\3\2\2\2\35z\3\2\2\2\37}\3\2\2\2!\177\3\2\2\2#\u0082\3\2\2\2%"+
		"\u0085\3\2\2\2\'(\7k\2\2()\7p\2\2)*\3\2\2\2*+\5\23\n\2+\4\3\2\2\2,-\7"+
		"e\2\2-.\7c\2\2./\7v\2\2/\60\7g\2\2\60\61\7i\2\2\61\62\7q\2\2\62\63\7t"+
		"\2\2\63\64\7{\2\2\64\65\3\2\2\2\65\66\5\23\n\2\66\6\3\2\2\2\678\7p\2\2"+
		"89\7c\2\29:\7o\2\2:;\7g\2\2;<\3\2\2\2<=\5\23\n\2=\b\3\2\2\2>?\7t\2\2?"+
		"@\7c\2\2@A\7v\2\2AB\7k\2\2BC\7p\2\2CD\7i\2\2DF\3\2\2\2EG\5%\23\2FE\3\2"+
		"\2\2FG\3\2\2\2GH\3\2\2\2HJ\5\31\r\2IK\5%\23\2JI\3\2\2\2JK\3\2\2\2KL\3"+
		"\2\2\2LM\5\21\t\2M\n\3\2\2\2NO\7r\2\2OP\7t\2\2PQ\7k\2\2QR\7e\2\2RS\7g"+
		"\2\2SU\3\2\2\2TV\5%\23\2UT\3\2\2\2UV\3\2\2\2VW\3\2\2\2WY\5\31\r\2XZ\5"+
		"%\23\2YX\3\2\2\2YZ\3\2\2\2Z[\3\2\2\2[\\\5\21\t\2\\\f\3\2\2\2]^\7*\2\2"+
		"^\16\3\2\2\2_`\7+\2\2`\20\3\2\2\2ab\t\2\2\2b\22\3\2\2\2ce\5\r\7\2df\t"+
		"\3\2\2ed\3\2\2\2fg\3\2\2\2ge\3\2\2\2gh\3\2\2\2hi\3\2\2\2ij\5\17\b\2j\24"+
		"\3\2\2\2kl\7~\2\2lm\7~\2\2m\26\3\2\2\2no\7(\2\2op\7(\2\2p\30\3\2\2\2q"+
		"w\5\33\16\2rw\5\35\17\2sw\5\37\20\2tw\5!\21\2uw\5#\22\2vq\3\2\2\2vr\3"+
		"\2\2\2vs\3\2\2\2vt\3\2\2\2vu\3\2\2\2w\32\3\2\2\2xy\7@\2\2y\34\3\2\2\2"+
		"z{\7@\2\2{|\7?\2\2|\36\3\2\2\2}~\7>\2\2~ \3\2\2\2\177\u0080\7>\2\2\u0080"+
		"\u0081\7?\2\2\u0081\"\3\2\2\2\u0082\u0083\7?\2\2\u0083$\3\2\2\2\u0084"+
		"\u0086\t\4\2\2\u0085\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0085\3\2"+
		"\2\2\u0087\u0088\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u008a\b\23\2\2\u008a"+
		"&\3\2\2\2\13\2FJUYegv\u0087\3\3\23\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}