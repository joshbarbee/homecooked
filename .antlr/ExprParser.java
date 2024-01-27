// Generated from c:/Users/thisi/Documents/Programming/homecooked/ExprParser.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class ExprParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		LCURLY=1, RCURLY=2, LPAREN=3, RPAREN=4, LBRACKET=5, RBRACKET=6, SEMI=7, 
		UNDERSCORE=8, PERIOD=9, COMMA=10, COLON=11, STATEMENT_START=12, STATEMENT_END=13, 
		FINISH=14, FOR=15, IF=16, ELSE=17, OR=18, AND=19, NOT=20, IN=21, ASSIGN=22, 
		LESS=23, GREATER=24, EXP=25, MUL=26, DIV=27, ADD=28, SUB=29, MOD=30, NONE=31, 
		TRUE=32, FALSE=33, NUMBER=34, INTEGER=35, DECIMAL_INTEGER=36, OCT_INTEGER=37, 
		HEX_INTEGER=38, BIN_INTEGER=39, FLOAT_NUMBER=40, ID=41, WS=42, SYMBOLS=43;
	public static final int
		RULE_program = 0, RULE_block = 1, RULE_statement = 2, RULE_for_statement = 3, 
		RULE_finish_statement = 4, RULE_vars = 5, RULE_var = 6, RULE_expr = 7, 
		RULE_atom = 8, RULE_suffix = 9, RULE_slicelist = 10, RULE_slice = 11, 
		RULE_sliceop = 12, RULE_arglist = 13, RULE_argument = 14, RULE_name = 15, 
		RULE_html = 16, RULE_html_open_tag = 17, RULE_html_body = 18, RULE_html_close_tag = 19, 
		RULE_content = 20;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "block", "statement", "for_statement", "finish_statement", 
			"vars", "var", "expr", "atom", "suffix", "slicelist", "slice", "sliceop", 
			"arglist", "argument", "name", "html", "html_open_tag", "html_body", 
			"html_close_tag", "content"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'{'", "'}'", "'('", "')'", "'['", "']'", "';'", "'_'", "'.'", 
			"','", "':'", "'{%'", "'%}'", "'fi'", "'for'", "'if'", "'else'", "'or'", 
			"'and'", "'not'", "'in'", "'='", "'<'", "'>'", "'**'", "'*'", "'/'", 
			"'+'", "'-'", "'%'", "'None'", "'True'", "'False'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "LCURLY", "RCURLY", "LPAREN", "RPAREN", "LBRACKET", "RBRACKET", 
			"SEMI", "UNDERSCORE", "PERIOD", "COMMA", "COLON", "STATEMENT_START", 
			"STATEMENT_END", "FINISH", "FOR", "IF", "ELSE", "OR", "AND", "NOT", "IN", 
			"ASSIGN", "LESS", "GREATER", "EXP", "MUL", "DIV", "ADD", "SUB", "MOD", 
			"NONE", "TRUE", "FALSE", "NUMBER", "INTEGER", "DECIMAL_INTEGER", "OCT_INTEGER", 
			"HEX_INTEGER", "BIN_INTEGER", "FLOAT_NUMBER", "ID", "WS", "SYMBOLS"
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
	public String getGrammarFileName() { return "ExprParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ExprParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
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
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(45);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1+1 ) {
					{
					{
					setState(42);
					block();
					}
					} 
				}
				setState(47);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
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
	public static class BlockContext extends ParserRuleContext {
		public List<TerminalNode> STATEMENT_START() { return getTokens(ExprParser.STATEMENT_START); }
		public TerminalNode STATEMENT_START(int i) {
			return getToken(ExprParser.STATEMENT_START, i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<TerminalNode> STATEMENT_END() { return getTokens(ExprParser.STATEMENT_END); }
		public TerminalNode STATEMENT_END(int i) {
			return getToken(ExprParser.STATEMENT_END, i);
		}
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public HtmlContext html() {
			return getRuleContext(HtmlContext.class,0);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_block);
		try {
			int _alt;
			setState(62);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STATEMENT_START:
				enterOuterAlt(_localctx, 1);
				{
				setState(48);
				match(STATEMENT_START);
				setState(49);
				statement();
				setState(50);
				match(STATEMENT_END);
				setState(54);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
				while ( _alt!=1 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1+1 ) {
						{
						{
						setState(51);
						block();
						}
						} 
					}
					setState(56);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
				}
				setState(57);
				match(STATEMENT_START);
				setState(58);
				statement();
				setState(59);
				match(STATEMENT_END);
				}
				break;
			case LESS:
				enterOuterAlt(_localctx, 2);
				{
				setState(61);
				html();
				}
				break;
			default:
				throw new NoViableAltException(this);
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
		public For_statementContext for_statement() {
			return getRuleContext(For_statementContext.class,0);
		}
		public Finish_statementContext finish_statement() {
			return getRuleContext(Finish_statementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_statement);
		try {
			setState(66);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FOR:
				enterOuterAlt(_localctx, 1);
				{
				setState(64);
				for_statement();
				}
				break;
			case FINISH:
				enterOuterAlt(_localctx, 2);
				{
				setState(65);
				finish_statement();
				}
				break;
			default:
				throw new NoViableAltException(this);
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
	public static class For_statementContext extends ParserRuleContext {
		public TerminalNode FOR() { return getToken(ExprParser.FOR, 0); }
		public VarsContext vars() {
			return getRuleContext(VarsContext.class,0);
		}
		public TerminalNode IN() { return getToken(ExprParser.IN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public For_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_for_statement; }
	}

	public final For_statementContext for_statement() throws RecognitionException {
		For_statementContext _localctx = new For_statementContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_for_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			match(FOR);
			setState(69);
			vars();
			setState(70);
			match(IN);
			setState(71);
			expr(0);
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
	public static class Finish_statementContext extends ParserRuleContext {
		public TerminalNode FINISH() { return getToken(ExprParser.FINISH, 0); }
		public Finish_statementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_finish_statement; }
	}

	public final Finish_statementContext finish_statement() throws RecognitionException {
		Finish_statementContext _localctx = new Finish_statementContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_finish_statement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
			match(FINISH);
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
		public List<VarContext> var() {
			return getRuleContexts(VarContext.class);
		}
		public VarContext var(int i) {
			return getRuleContext(VarContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(ExprParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ExprParser.COMMA, i);
		}
		public VarsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_vars; }
	}

	public final VarsContext vars() throws RecognitionException {
		VarsContext _localctx = new VarsContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_vars);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			var();
			setState(80);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(76);
					match(COMMA);
					setState(77);
					var();
					}
					} 
				}
				setState(82);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			}
			setState(84);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(83);
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
	public static class VarContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(ExprParser.LPAREN, 0); }
		public VarsContext vars() {
			return getRuleContext(VarsContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(ExprParser.RPAREN, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public VarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_var; }
	}

	public final VarContext var() throws RecognitionException {
		VarContext _localctx = new VarContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_var);
		try {
			setState(91);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPAREN:
				enterOuterAlt(_localctx, 1);
				{
				setState(86);
				match(LPAREN);
				setState(87);
				vars();
				setState(88);
				match(RPAREN);
				}
				break;
			case UNDERSCORE:
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(90);
				name();
				}
				break;
			default:
				throw new NoViableAltException(this);
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
	public static class ExprContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(ExprParser.LPAREN, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(ExprParser.RPAREN, 0); }
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public TerminalNode EXP() { return getToken(ExprParser.EXP, 0); }
		public TerminalNode MUL() { return getToken(ExprParser.MUL, 0); }
		public TerminalNode DIV() { return getToken(ExprParser.DIV, 0); }
		public TerminalNode MOD() { return getToken(ExprParser.MOD, 0); }
		public TerminalNode ADD() { return getToken(ExprParser.ADD, 0); }
		public TerminalNode SUB() { return getToken(ExprParser.SUB, 0); }
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 14;
		enterRecursionRule(_localctx, 14, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LPAREN:
				{
				setState(94);
				match(LPAREN);
				setState(95);
				expr(0);
				setState(96);
				match(RPAREN);
				}
				break;
			case UNDERSCORE:
			case NONE:
			case TRUE:
			case FALSE:
			case NUMBER:
			case ID:
				{
				setState(98);
				atom();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			_ctx.stop = _input.LT(-1);
			setState(112);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(110);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(101);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(102);
						match(EXP);
						setState(103);
						expr(5);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(104);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(105);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 1275068416L) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(106);
						expr(4);
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(107);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(108);
						_la = _input.LA(1);
						if ( !(_la==ADD || _la==SUB) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(109);
						expr(3);
						}
						break;
					}
					} 
				}
				setState(114);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AtomContext extends ParserRuleContext {
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public List<SuffixContext> suffix() {
			return getRuleContexts(SuffixContext.class);
		}
		public SuffixContext suffix(int i) {
			return getRuleContext(SuffixContext.class,i);
		}
		public TerminalNode NUMBER() { return getToken(ExprParser.NUMBER, 0); }
		public TerminalNode NONE() { return getToken(ExprParser.NONE, 0); }
		public TerminalNode TRUE() { return getToken(ExprParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(ExprParser.FALSE, 0); }
		public AtomContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_atom; }
	}

	public final AtomContext atom() throws RecognitionException {
		AtomContext _localctx = new AtomContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_atom);
		try {
			int _alt;
			setState(126);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case UNDERSCORE:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(115);
				name();
				setState(119);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(116);
						suffix();
						}
						} 
					}
					setState(121);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
				}
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(122);
				match(NUMBER);
				}
				break;
			case NONE:
				enterOuterAlt(_localctx, 3);
				{
				setState(123);
				match(NONE);
				}
				break;
			case TRUE:
				enterOuterAlt(_localctx, 4);
				{
				setState(124);
				match(TRUE);
				}
				break;
			case FALSE:
				enterOuterAlt(_localctx, 5);
				{
				setState(125);
				match(FALSE);
				}
				break;
			default:
				throw new NoViableAltException(this);
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
	public static class SuffixContext extends ParserRuleContext {
		public TerminalNode LBRACKET() { return getToken(ExprParser.LBRACKET, 0); }
		public SlicelistContext slicelist() {
			return getRuleContext(SlicelistContext.class,0);
		}
		public TerminalNode RBRACKET() { return getToken(ExprParser.RBRACKET, 0); }
		public TerminalNode LPAREN() { return getToken(ExprParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(ExprParser.RPAREN, 0); }
		public ArglistContext arglist() {
			return getRuleContext(ArglistContext.class,0);
		}
		public TerminalNode PERIOD() { return getToken(ExprParser.PERIOD, 0); }
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public SuffixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_suffix; }
	}

	public final SuffixContext suffix() throws RecognitionException {
		SuffixContext _localctx = new SuffixContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_suffix);
		int _la;
		try {
			setState(139);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LBRACKET:
				enterOuterAlt(_localctx, 1);
				{
				setState(128);
				match(LBRACKET);
				setState(129);
				slicelist();
				setState(130);
				match(RBRACKET);
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 2);
				{
				setState(132);
				match(LPAREN);
				setState(134);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2231235510536L) != 0)) {
					{
					setState(133);
					arglist();
					}
				}

				setState(136);
				match(RPAREN);
				}
				break;
			case PERIOD:
				enterOuterAlt(_localctx, 3);
				{
				setState(137);
				match(PERIOD);
				setState(138);
				name();
				}
				break;
			default:
				throw new NoViableAltException(this);
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
	public static class SlicelistContext extends ParserRuleContext {
		public List<SliceContext> slice() {
			return getRuleContexts(SliceContext.class);
		}
		public SliceContext slice(int i) {
			return getRuleContext(SliceContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(ExprParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ExprParser.COMMA, i);
		}
		public SlicelistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_slicelist; }
	}

	public final SlicelistContext slicelist() throws RecognitionException {
		SlicelistContext _localctx = new SlicelistContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_slicelist);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(141);
			slice();
			setState(146);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(142);
					match(COMMA);
					setState(143);
					slice();
					}
					} 
				}
				setState(148);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			setState(150);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(149);
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
	public static class SliceContext extends ParserRuleContext {
		public List<AtomContext> atom() {
			return getRuleContexts(AtomContext.class);
		}
		public AtomContext atom(int i) {
			return getRuleContext(AtomContext.class,i);
		}
		public TerminalNode COLON() { return getToken(ExprParser.COLON, 0); }
		public SliceopContext sliceop() {
			return getRuleContext(SliceopContext.class,0);
		}
		public SliceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_slice; }
	}

	public final SliceContext slice() throws RecognitionException {
		SliceContext _localctx = new SliceContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_slice);
		int _la;
		try {
			setState(163);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(152);
				atom();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(154);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2231235510528L) != 0)) {
					{
					setState(153);
					atom();
					}
				}

				setState(156);
				match(COLON);
				setState(158);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2231235510528L) != 0)) {
					{
					setState(157);
					atom();
					}
				}

				setState(161);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==COLON) {
					{
					setState(160);
					sliceop();
					}
				}

				}
				break;
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
	public static class SliceopContext extends ParserRuleContext {
		public TerminalNode COLON() { return getToken(ExprParser.COLON, 0); }
		public AtomContext atom() {
			return getRuleContext(AtomContext.class,0);
		}
		public SliceopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sliceop; }
	}

	public final SliceopContext sliceop() throws RecognitionException {
		SliceopContext _localctx = new SliceopContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_sliceop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(165);
			match(COLON);
			setState(167);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 2231235510528L) != 0)) {
				{
				setState(166);
				atom();
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
	public static class ArglistContext extends ParserRuleContext {
		public List<ArgumentContext> argument() {
			return getRuleContexts(ArgumentContext.class);
		}
		public ArgumentContext argument(int i) {
			return getRuleContext(ArgumentContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(ExprParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(ExprParser.COMMA, i);
		}
		public ArglistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arglist; }
	}

	public final ArglistContext arglist() throws RecognitionException {
		ArglistContext _localctx = new ArglistContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_arglist);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(169);
			argument();
			setState(174);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(170);
					match(COMMA);
					setState(171);
					argument();
					}
					} 
				}
				setState(176);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
			}
			setState(178);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(177);
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
	public static class ArgumentContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public NameContext name() {
			return getRuleContext(NameContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(ExprParser.ASSIGN, 0); }
		public ArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argument; }
	}

	public final ArgumentContext argument() throws RecognitionException {
		ArgumentContext _localctx = new ArgumentContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_argument);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(183);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				{
				setState(180);
				name();
				setState(181);
				match(ASSIGN);
				}
				break;
			}
			setState(185);
			expr(0);
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
		public TerminalNode ID() { return getToken(ExprParser.ID, 0); }
		public TerminalNode UNDERSCORE() { return getToken(ExprParser.UNDERSCORE, 0); }
		public NameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_name; }
	}

	public final NameContext name() throws RecognitionException {
		NameContext _localctx = new NameContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_name);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(187);
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

	@SuppressWarnings("CheckReturnValue")
	public static class HtmlContext extends ParserRuleContext {
		public Html_open_tagContext html_open_tag() {
			return getRuleContext(Html_open_tagContext.class,0);
		}
		public Html_bodyContext html_body() {
			return getRuleContext(Html_bodyContext.class,0);
		}
		public Html_close_tagContext html_close_tag() {
			return getRuleContext(Html_close_tagContext.class,0);
		}
		public HtmlContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_html; }
	}

	public final HtmlContext html() throws RecognitionException {
		HtmlContext _localctx = new HtmlContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_html);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(189);
			html_open_tag();
			setState(190);
			html_body();
			setState(191);
			html_close_tag();
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
	public static class Html_open_tagContext extends ParserRuleContext {
		public TerminalNode LESS() { return getToken(ExprParser.LESS, 0); }
		public TerminalNode GREATER() { return getToken(ExprParser.GREATER, 0); }
		public List<ContentContext> content() {
			return getRuleContexts(ContentContext.class);
		}
		public ContentContext content(int i) {
			return getRuleContext(ContentContext.class,i);
		}
		public Html_open_tagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_html_open_tag; }
	}

	public final Html_open_tagContext html_open_tag() throws RecognitionException {
		Html_open_tagContext _localctx = new Html_open_tagContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_html_open_tag);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193);
			match(LESS);
			setState(195); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(194);
				content();
				}
				}
				setState(197); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 10995120472064L) != 0) );
			setState(199);
			match(GREATER);
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
	public static class Html_bodyContext extends ParserRuleContext {
		public List<ContentContext> content() {
			return getRuleContexts(ContentContext.class);
		}
		public ContentContext content(int i) {
			return getRuleContext(ContentContext.class,i);
		}
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public Html_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_html_body; }
	}

	public final Html_bodyContext html_body() throws RecognitionException {
		Html_bodyContext _localctx = new Html_bodyContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_html_body);
		int _la;
		try {
			int _alt;
			setState(218);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ASSIGN:
			case ID:
			case SYMBOLS:
				enterOuterAlt(_localctx, 1);
				{
				setState(202); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(201);
					content();
					}
					}
					setState(204); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 10995120472064L) != 0) );
				setState(209);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(206);
						block();
						}
						} 
					}
					setState(211);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,26,_ctx);
				}
				}
				break;
			case STATEMENT_START:
			case LESS:
				enterOuterAlt(_localctx, 2);
				{
				setState(215);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(212);
						block();
						}
						} 
					}
					setState(217);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
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
	public static class Html_close_tagContext extends ParserRuleContext {
		public TerminalNode LESS() { return getToken(ExprParser.LESS, 0); }
		public TerminalNode DIV() { return getToken(ExprParser.DIV, 0); }
		public ContentContext content() {
			return getRuleContext(ContentContext.class,0);
		}
		public TerminalNode GREATER() { return getToken(ExprParser.GREATER, 0); }
		public Html_close_tagContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_html_close_tag; }
	}

	public final Html_close_tagContext html_close_tag() throws RecognitionException {
		Html_close_tagContext _localctx = new Html_close_tagContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_html_close_tag);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(220);
			match(LESS);
			setState(221);
			match(DIV);
			setState(222);
			content();
			setState(223);
			match(GREATER);
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
	public static class ContentContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(ExprParser.ID, 0); }
		public TerminalNode SYMBOLS() { return getToken(ExprParser.SYMBOLS, 0); }
		public TerminalNode ASSIGN() { return getToken(ExprParser.ASSIGN, 0); }
		public ContentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_content; }
	}

	public final ContentContext content() throws RecognitionException {
		ContentContext _localctx = new ContentContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_content);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(225);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 10995120472064L) != 0)) ) {
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 7:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 4);
		case 1:
			return precpred(_ctx, 3);
		case 2:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001+\u00e4\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0001\u0000\u0005\u0000"+
		",\b\u0000\n\u0000\f\u0000/\t\u0000\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0005\u00015\b\u0001\n\u0001\f\u00018\t\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001?\b\u0001"+
		"\u0001\u0002\u0001\u0002\u0003\u0002C\b\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0005\u0005O\b\u0005\n\u0005\f\u0005R\t\u0005"+
		"\u0001\u0005\u0003\u0005U\b\u0005\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0003\u0006\\\b\u0006\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0003\u0007d\b\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\u0007\u0001\u0007\u0001\u0007\u0005\u0007o\b\u0007\n\u0007\f\u0007"+
		"r\t\u0007\u0001\b\u0001\b\u0005\bv\b\b\n\b\f\by\t\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0003\b\u007f\b\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0003\t\u0087\b\t\u0001\t\u0001\t\u0001\t\u0003\t\u008c\b\t\u0001\n"+
		"\u0001\n\u0001\n\u0005\n\u0091\b\n\n\n\f\n\u0094\t\n\u0001\n\u0003\n\u0097"+
		"\b\n\u0001\u000b\u0001\u000b\u0003\u000b\u009b\b\u000b\u0001\u000b\u0001"+
		"\u000b\u0003\u000b\u009f\b\u000b\u0001\u000b\u0003\u000b\u00a2\b\u000b"+
		"\u0003\u000b\u00a4\b\u000b\u0001\f\u0001\f\u0003\f\u00a8\b\f\u0001\r\u0001"+
		"\r\u0001\r\u0005\r\u00ad\b\r\n\r\f\r\u00b0\t\r\u0001\r\u0003\r\u00b3\b"+
		"\r\u0001\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u00b8\b\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0004\u0011\u00c4\b\u0011\u000b"+
		"\u0011\f\u0011\u00c5\u0001\u0011\u0001\u0011\u0001\u0012\u0004\u0012\u00cb"+
		"\b\u0012\u000b\u0012\f\u0012\u00cc\u0001\u0012\u0005\u0012\u00d0\b\u0012"+
		"\n\u0012\f\u0012\u00d3\t\u0012\u0001\u0012\u0005\u0012\u00d6\b\u0012\n"+
		"\u0012\f\u0012\u00d9\t\u0012\u0003\u0012\u00db\b\u0012\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0002-6\u0001\u000e\u0015\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010"+
		"\u0012\u0014\u0016\u0018\u001a\u001c\u001e \"$&(\u0000\u0004\u0002\u0000"+
		"\u001a\u001b\u001e\u001e\u0001\u0000\u001c\u001d\u0002\u0000\b\b))\u0003"+
		"\u0000\u0016\u0016))++\u00f0\u0000-\u0001\u0000\u0000\u0000\u0002>\u0001"+
		"\u0000\u0000\u0000\u0004B\u0001\u0000\u0000\u0000\u0006D\u0001\u0000\u0000"+
		"\u0000\bI\u0001\u0000\u0000\u0000\nK\u0001\u0000\u0000\u0000\f[\u0001"+
		"\u0000\u0000\u0000\u000ec\u0001\u0000\u0000\u0000\u0010~\u0001\u0000\u0000"+
		"\u0000\u0012\u008b\u0001\u0000\u0000\u0000\u0014\u008d\u0001\u0000\u0000"+
		"\u0000\u0016\u00a3\u0001\u0000\u0000\u0000\u0018\u00a5\u0001\u0000\u0000"+
		"\u0000\u001a\u00a9\u0001\u0000\u0000\u0000\u001c\u00b7\u0001\u0000\u0000"+
		"\u0000\u001e\u00bb\u0001\u0000\u0000\u0000 \u00bd\u0001\u0000\u0000\u0000"+
		"\"\u00c1\u0001\u0000\u0000\u0000$\u00da\u0001\u0000\u0000\u0000&\u00dc"+
		"\u0001\u0000\u0000\u0000(\u00e1\u0001\u0000\u0000\u0000*,\u0003\u0002"+
		"\u0001\u0000+*\u0001\u0000\u0000\u0000,/\u0001\u0000\u0000\u0000-.\u0001"+
		"\u0000\u0000\u0000-+\u0001\u0000\u0000\u0000.\u0001\u0001\u0000\u0000"+
		"\u0000/-\u0001\u0000\u0000\u000001\u0005\f\u0000\u000012\u0003\u0004\u0002"+
		"\u000026\u0005\r\u0000\u000035\u0003\u0002\u0001\u000043\u0001\u0000\u0000"+
		"\u000058\u0001\u0000\u0000\u000067\u0001\u0000\u0000\u000064\u0001\u0000"+
		"\u0000\u000079\u0001\u0000\u0000\u000086\u0001\u0000\u0000\u00009:\u0005"+
		"\f\u0000\u0000:;\u0003\u0004\u0002\u0000;<\u0005\r\u0000\u0000<?\u0001"+
		"\u0000\u0000\u0000=?\u0003 \u0010\u0000>0\u0001\u0000\u0000\u0000>=\u0001"+
		"\u0000\u0000\u0000?\u0003\u0001\u0000\u0000\u0000@C\u0003\u0006\u0003"+
		"\u0000AC\u0003\b\u0004\u0000B@\u0001\u0000\u0000\u0000BA\u0001\u0000\u0000"+
		"\u0000C\u0005\u0001\u0000\u0000\u0000DE\u0005\u000f\u0000\u0000EF\u0003"+
		"\n\u0005\u0000FG\u0005\u0015\u0000\u0000GH\u0003\u000e\u0007\u0000H\u0007"+
		"\u0001\u0000\u0000\u0000IJ\u0005\u000e\u0000\u0000J\t\u0001\u0000\u0000"+
		"\u0000KP\u0003\f\u0006\u0000LM\u0005\n\u0000\u0000MO\u0003\f\u0006\u0000"+
		"NL\u0001\u0000\u0000\u0000OR\u0001\u0000\u0000\u0000PN\u0001\u0000\u0000"+
		"\u0000PQ\u0001\u0000\u0000\u0000QT\u0001\u0000\u0000\u0000RP\u0001\u0000"+
		"\u0000\u0000SU\u0005\n\u0000\u0000TS\u0001\u0000\u0000\u0000TU\u0001\u0000"+
		"\u0000\u0000U\u000b\u0001\u0000\u0000\u0000VW\u0005\u0003\u0000\u0000"+
		"WX\u0003\n\u0005\u0000XY\u0005\u0004\u0000\u0000Y\\\u0001\u0000\u0000"+
		"\u0000Z\\\u0003\u001e\u000f\u0000[V\u0001\u0000\u0000\u0000[Z\u0001\u0000"+
		"\u0000\u0000\\\r\u0001\u0000\u0000\u0000]^\u0006\u0007\uffff\uffff\u0000"+
		"^_\u0005\u0003\u0000\u0000_`\u0003\u000e\u0007\u0000`a\u0005\u0004\u0000"+
		"\u0000ad\u0001\u0000\u0000\u0000bd\u0003\u0010\b\u0000c]\u0001\u0000\u0000"+
		"\u0000cb\u0001\u0000\u0000\u0000dp\u0001\u0000\u0000\u0000ef\n\u0004\u0000"+
		"\u0000fg\u0005\u0019\u0000\u0000go\u0003\u000e\u0007\u0005hi\n\u0003\u0000"+
		"\u0000ij\u0007\u0000\u0000\u0000jo\u0003\u000e\u0007\u0004kl\n\u0002\u0000"+
		"\u0000lm\u0007\u0001\u0000\u0000mo\u0003\u000e\u0007\u0003ne\u0001\u0000"+
		"\u0000\u0000nh\u0001\u0000\u0000\u0000nk\u0001\u0000\u0000\u0000or\u0001"+
		"\u0000\u0000\u0000pn\u0001\u0000\u0000\u0000pq\u0001\u0000\u0000\u0000"+
		"q\u000f\u0001\u0000\u0000\u0000rp\u0001\u0000\u0000\u0000sw\u0003\u001e"+
		"\u000f\u0000tv\u0003\u0012\t\u0000ut\u0001\u0000\u0000\u0000vy\u0001\u0000"+
		"\u0000\u0000wu\u0001\u0000\u0000\u0000wx\u0001\u0000\u0000\u0000x\u007f"+
		"\u0001\u0000\u0000\u0000yw\u0001\u0000\u0000\u0000z\u007f\u0005\"\u0000"+
		"\u0000{\u007f\u0005\u001f\u0000\u0000|\u007f\u0005 \u0000\u0000}\u007f"+
		"\u0005!\u0000\u0000~s\u0001\u0000\u0000\u0000~z\u0001\u0000\u0000\u0000"+
		"~{\u0001\u0000\u0000\u0000~|\u0001\u0000\u0000\u0000~}\u0001\u0000\u0000"+
		"\u0000\u007f\u0011\u0001\u0000\u0000\u0000\u0080\u0081\u0005\u0005\u0000"+
		"\u0000\u0081\u0082\u0003\u0014\n\u0000\u0082\u0083\u0005\u0006\u0000\u0000"+
		"\u0083\u008c\u0001\u0000\u0000\u0000\u0084\u0086\u0005\u0003\u0000\u0000"+
		"\u0085\u0087\u0003\u001a\r\u0000\u0086\u0085\u0001\u0000\u0000\u0000\u0086"+
		"\u0087\u0001\u0000\u0000\u0000\u0087\u0088\u0001\u0000\u0000\u0000\u0088"+
		"\u008c\u0005\u0004\u0000\u0000\u0089\u008a\u0005\t\u0000\u0000\u008a\u008c"+
		"\u0003\u001e\u000f\u0000\u008b\u0080\u0001\u0000\u0000\u0000\u008b\u0084"+
		"\u0001\u0000\u0000\u0000\u008b\u0089\u0001\u0000\u0000\u0000\u008c\u0013"+
		"\u0001\u0000\u0000\u0000\u008d\u0092\u0003\u0016\u000b\u0000\u008e\u008f"+
		"\u0005\n\u0000\u0000\u008f\u0091\u0003\u0016\u000b\u0000\u0090\u008e\u0001"+
		"\u0000\u0000\u0000\u0091\u0094\u0001\u0000\u0000\u0000\u0092\u0090\u0001"+
		"\u0000\u0000\u0000\u0092\u0093\u0001\u0000\u0000\u0000\u0093\u0096\u0001"+
		"\u0000\u0000\u0000\u0094\u0092\u0001\u0000\u0000\u0000\u0095\u0097\u0005"+
		"\n\u0000\u0000\u0096\u0095\u0001\u0000\u0000\u0000\u0096\u0097\u0001\u0000"+
		"\u0000\u0000\u0097\u0015\u0001\u0000\u0000\u0000\u0098\u00a4\u0003\u0010"+
		"\b\u0000\u0099\u009b\u0003\u0010\b\u0000\u009a\u0099\u0001\u0000\u0000"+
		"\u0000\u009a\u009b\u0001\u0000\u0000\u0000\u009b\u009c\u0001\u0000\u0000"+
		"\u0000\u009c\u009e\u0005\u000b\u0000\u0000\u009d\u009f\u0003\u0010\b\u0000"+
		"\u009e\u009d\u0001\u0000\u0000\u0000\u009e\u009f\u0001\u0000\u0000\u0000"+
		"\u009f\u00a1\u0001\u0000\u0000\u0000\u00a0\u00a2\u0003\u0018\f\u0000\u00a1"+
		"\u00a0\u0001\u0000\u0000\u0000\u00a1\u00a2\u0001\u0000\u0000\u0000\u00a2"+
		"\u00a4\u0001\u0000\u0000\u0000\u00a3\u0098\u0001\u0000\u0000\u0000\u00a3"+
		"\u009a\u0001\u0000\u0000\u0000\u00a4\u0017\u0001\u0000\u0000\u0000\u00a5"+
		"\u00a7\u0005\u000b\u0000\u0000\u00a6\u00a8\u0003\u0010\b\u0000\u00a7\u00a6"+
		"\u0001\u0000\u0000\u0000\u00a7\u00a8\u0001\u0000\u0000\u0000\u00a8\u0019"+
		"\u0001\u0000\u0000\u0000\u00a9\u00ae\u0003\u001c\u000e\u0000\u00aa\u00ab"+
		"\u0005\n\u0000\u0000\u00ab\u00ad\u0003\u001c\u000e\u0000\u00ac\u00aa\u0001"+
		"\u0000\u0000\u0000\u00ad\u00b0\u0001\u0000\u0000\u0000\u00ae\u00ac\u0001"+
		"\u0000\u0000\u0000\u00ae\u00af\u0001\u0000\u0000\u0000\u00af\u00b2\u0001"+
		"\u0000\u0000\u0000\u00b0\u00ae\u0001\u0000\u0000\u0000\u00b1\u00b3\u0005"+
		"\n\u0000\u0000\u00b2\u00b1\u0001\u0000\u0000\u0000\u00b2\u00b3\u0001\u0000"+
		"\u0000\u0000\u00b3\u001b\u0001\u0000\u0000\u0000\u00b4\u00b5\u0003\u001e"+
		"\u000f\u0000\u00b5\u00b6\u0005\u0016\u0000\u0000\u00b6\u00b8\u0001\u0000"+
		"\u0000\u0000\u00b7\u00b4\u0001\u0000\u0000\u0000\u00b7\u00b8\u0001\u0000"+
		"\u0000\u0000\u00b8\u00b9\u0001\u0000\u0000\u0000\u00b9\u00ba\u0003\u000e"+
		"\u0007\u0000\u00ba\u001d\u0001\u0000\u0000\u0000\u00bb\u00bc\u0007\u0002"+
		"\u0000\u0000\u00bc\u001f\u0001\u0000\u0000\u0000\u00bd\u00be\u0003\"\u0011"+
		"\u0000\u00be\u00bf\u0003$\u0012\u0000\u00bf\u00c0\u0003&\u0013\u0000\u00c0"+
		"!\u0001\u0000\u0000\u0000\u00c1\u00c3\u0005\u0017\u0000\u0000\u00c2\u00c4"+
		"\u0003(\u0014\u0000\u00c3\u00c2\u0001\u0000\u0000\u0000\u00c4\u00c5\u0001"+
		"\u0000\u0000\u0000\u00c5\u00c3\u0001\u0000\u0000\u0000\u00c5\u00c6\u0001"+
		"\u0000\u0000\u0000\u00c6\u00c7\u0001\u0000\u0000\u0000\u00c7\u00c8\u0005"+
		"\u0018\u0000\u0000\u00c8#\u0001\u0000\u0000\u0000\u00c9\u00cb\u0003(\u0014"+
		"\u0000\u00ca\u00c9\u0001\u0000\u0000\u0000\u00cb\u00cc\u0001\u0000\u0000"+
		"\u0000\u00cc\u00ca\u0001\u0000\u0000\u0000\u00cc\u00cd\u0001\u0000\u0000"+
		"\u0000\u00cd\u00d1\u0001\u0000\u0000\u0000\u00ce\u00d0\u0003\u0002\u0001"+
		"\u0000\u00cf\u00ce\u0001\u0000\u0000\u0000\u00d0\u00d3\u0001\u0000\u0000"+
		"\u0000\u00d1\u00cf\u0001\u0000\u0000\u0000\u00d1\u00d2\u0001\u0000\u0000"+
		"\u0000\u00d2\u00db\u0001\u0000\u0000\u0000\u00d3\u00d1\u0001\u0000\u0000"+
		"\u0000\u00d4\u00d6\u0003\u0002\u0001\u0000\u00d5\u00d4\u0001\u0000\u0000"+
		"\u0000\u00d6\u00d9\u0001\u0000\u0000\u0000\u00d7\u00d5\u0001\u0000\u0000"+
		"\u0000\u00d7\u00d8\u0001\u0000\u0000\u0000\u00d8\u00db\u0001\u0000\u0000"+
		"\u0000\u00d9\u00d7\u0001\u0000\u0000\u0000\u00da\u00ca\u0001\u0000\u0000"+
		"\u0000\u00da\u00d7\u0001\u0000\u0000\u0000\u00db%\u0001\u0000\u0000\u0000"+
		"\u00dc\u00dd\u0005\u0017\u0000\u0000\u00dd\u00de\u0005\u001b\u0000\u0000"+
		"\u00de\u00df\u0003(\u0014\u0000\u00df\u00e0\u0005\u0018\u0000\u0000\u00e0"+
		"\'\u0001\u0000\u0000\u0000\u00e1\u00e2\u0007\u0003\u0000\u0000\u00e2)"+
		"\u0001\u0000\u0000\u0000\u001d-6>BPT[cnpw~\u0086\u008b\u0092\u0096\u009a"+
		"\u009e\u00a1\u00a3\u00a7\u00ae\u00b2\u00b7\u00c5\u00cc\u00d1\u00d7\u00da";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}