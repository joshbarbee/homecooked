// Generated from c:/Users/thisi/Documents/Programming/homecooked/Tester.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class Tester extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		LCURLY=1, RCURLY=2, LPAREN=3, RPAREN=4, LBRACKET=5, RBRACKET=6, SEMI=7, 
		UNDERSCORE=8, PERIOD=9, COMMA=10, FOR=11, IF=12, ELSE=13, OR=14, AND=15, 
		NOT=16, IN=17, ASSIGN=18, EXP=19, MUL=20, DIV=21, ADD=22, SUB=23, MOD=24, 
		NONE=25, TRUE=26, FALSE=27, COMP_OP=28, AUG_ASSIGN=29, NUMBER=30, INTEGER=31, 
		DECIMAL_INTEGER=32, OCT_INTEGER=33, HEX_INTEGER=34, BIN_INTEGER=35, FLOAT_NUMBER=36, 
		ID=37, WS=38, STATEMENT_START=39, STATEMENT_END=40;
	public static final int
		RULE_program = 0, RULE_statement = 1, RULE_vars = 2, RULE_name = 3;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "statement", "vars", "name"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'{'", "'}'", "'('", "')'", "'['", "']'", "';'", "'_'", "'.'", 
			"','", "'for'", "'if'", "'else'", "'or'", "'and'", "'not'", "'in'", "'='", 
			"'**'", "'*'", "'/'", "'+'", "'-'", "'%'", "'None'", "'True'", "'False'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "LCURLY", "RCURLY", "LPAREN", "RPAREN", "LBRACKET", "RBRACKET", 
			"SEMI", "UNDERSCORE", "PERIOD", "COMMA", "FOR", "IF", "ELSE", "OR", "AND", 
			"NOT", "IN", "ASSIGN", "EXP", "MUL", "DIV", "ADD", "SUB", "MOD", "NONE", 
			"TRUE", "FALSE", "COMP_OP", "AUG_ASSIGN", "NUMBER", "INTEGER", "DECIMAL_INTEGER", 
			"OCT_INTEGER", "HEX_INTEGER", "BIN_INTEGER", "FLOAT_NUMBER", "ID", "WS", 
			"STATEMENT_START", "STATEMENT_END"
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

	@Override
	public String getGrammarFileName() { return "Tester.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public Tester(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(8);
			statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public TerminalNode STATEMENT_START() { return getToken(Tester.STATEMENT_START, 0); }
		public TerminalNode FOR() { return getToken(Tester.FOR, 0); }
		public TerminalNode STATEMENT_END() { return getToken(Tester.STATEMENT_END, 0); }
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(10);
			match(STATEMENT_START);
			setState(11);
			match(FOR);
			setState(12);
			match(STATEMENT_END);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VarsContext extends ParserRuleContext {
		public List<NameContext> name() {
			return getRuleContexts(NameContext.class);
		}
		public NameContext name(int i) {
			return getRuleContext(NameContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(Tester.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(Tester.COMMA, i);
		}
		public VarsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vars; }
	}

	public final VarsContext vars() throws RecognitionException {
		VarsContext _localctx = new VarsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_vars);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(14);
			name();
			setState(19);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(15);
					match(COMMA);
					setState(16);
					name();
					}
					} 
				}
				setState(21);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(23);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(22);
				match(COMMA);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class NameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(Tester.ID, 0); }
		public TerminalNode UNDERSCORE() { return getToken(Tester.UNDERSCORE, 0); }
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(25);
			_la = _input.LA(1);
			if ( !(_la==UNDERSCORE || _la==ID) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001(\u001c\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0001\u0000\u0001\u0000\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0005\u0002\u0012\b\u0002\n\u0002\f\u0002\u0015\t\u0002\u0001\u0002"+
		"\u0003\u0002\u0018\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0000\u0000"+
		"\u0004\u0000\u0002\u0004\u0006\u0000\u0001\u0002\u0000\b\b%%\u0019\u0000"+
		"\b\u0001\u0000\u0000\u0000\u0002\n\u0001\u0000\u0000\u0000\u0004\u000e"+
		"\u0001\u0000\u0000\u0000\u0006\u0019\u0001\u0000\u0000\u0000\b\t\u0003"+
		"\u0002\u0001\u0000\t\u0001\u0001\u0000\u0000\u0000\n\u000b\u0005\'\u0000"+
		"\u0000\u000b\f\u0005\u000b\u0000\u0000\f\r\u0005(\u0000\u0000\r\u0003"+
		"\u0001\u0000\u0000\u0000\u000e\u0013\u0003\u0006\u0003\u0000\u000f\u0010"+
		"\u0005\n\u0000\u0000\u0010\u0012\u0003\u0006\u0003\u0000\u0011\u000f\u0001"+
		"\u0000\u0000\u0000\u0012\u0015\u0001\u0000\u0000\u0000\u0013\u0011\u0001"+
		"\u0000\u0000\u0000\u0013\u0014\u0001\u0000\u0000\u0000\u0014\u0017\u0001"+
		"\u0000\u0000\u0000\u0015\u0013\u0001\u0000\u0000\u0000\u0016\u0018\u0005"+
		"\n\u0000\u0000\u0017\u0016\u0001\u0000\u0000\u0000\u0017\u0018\u0001\u0000"+
		"\u0000\u0000\u0018\u0005\u0001\u0000\u0000\u0000\u0019\u001a\u0007\u0000"+
		"\u0000\u0000\u001a\u0007\u0001\u0000\u0000\u0000\u0002\u0013\u0017";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}