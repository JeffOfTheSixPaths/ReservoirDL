// Generated from RDL.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RDLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		DATA=1, LOAD=2, CSV=3, LSM=4, LAYER=5, TORCH=6, CONNECT=7, WEIGHT=8, SIMULATE=9, 
		WITH=10, SAVE=11, SPIKE=12, IF=13, EMIT=14, SIZE=15, TYPE=16, STEPS=17, 
		ARROW=18, ASSIGN=19, GT=20, LT=21, GTE=22, LTE=23, EQ=24, NEQ=25, PLUS=26, 
		MINUS=27, MUL=28, DIV=29, LPAREN=30, RPAREN=31, LBRACE=32, RBRACE=33, 
		ID=34, INT=35, FLOAT=36, STRING=37, LINE_COMMENT=38, BLOCK_COMMENT=39, 
		WS=40;
	public static final int
		RULE_program = 0, RULE_statement = 1, RULE_dataDecl = 2, RULE_lsmDecl = 3, 
		RULE_layerDecl = 4, RULE_connectStmt = 5, RULE_simulateStmt = 6, RULE_saveStmt = 7, 
		RULE_block = 8, RULE_blockStatement = 9, RULE_spikeDecl = 10, RULE_ifStmt = 11, 
		RULE_emitStmt = 12, RULE_assignment = 13, RULE_sizeStmt = 14, RULE_typeStmt = 15, 
		RULE_stepsStmt = 16, RULE_typeValue = 17, RULE_expr = 18, RULE_comparisonExpr = 19, 
		RULE_additiveExpr = 20, RULE_multiplicativeExpr = 21, RULE_unaryExpr = 22, 
		RULE_primary = 23, RULE_number = 24;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "statement", "dataDecl", "lsmDecl", "layerDecl", "connectStmt", 
			"simulateStmt", "saveStmt", "block", "blockStatement", "spikeDecl", "ifStmt", 
			"emitStmt", "assignment", "sizeStmt", "typeStmt", "stepsStmt", "typeValue", 
			"expr", "comparisonExpr", "additiveExpr", "multiplicativeExpr", "unaryExpr", 
			"primary", "number"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'data'", "'load'", "'csv'", "'LSM'", "'layer'", "'torch'", "'connect'", 
			"'weight'", "'simulate'", "'with'", "'save'", "'spike'", "'if'", "'emit'", 
			"'size'", "'type'", "'steps'", "'->'", "'='", "'>'", "'<'", "'>='", "'<='", 
			"'=='", "'!='", "'+'", "'-'", "'*'", "'/'", "'('", "')'", "'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "DATA", "LOAD", "CSV", "LSM", "LAYER", "TORCH", "CONNECT", "WEIGHT", 
			"SIMULATE", "WITH", "SAVE", "SPIKE", "IF", "EMIT", "SIZE", "TYPE", "STEPS", 
			"ARROW", "ASSIGN", "GT", "LT", "GTE", "LTE", "EQ", "NEQ", "PLUS", "MINUS", 
			"MUL", "DIV", "LPAREN", "RPAREN", "LBRACE", "RBRACE", "ID", "INT", "FLOAT", 
			"STRING", "LINE_COMMENT", "BLOCK_COMMENT", "WS"
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
	public String getGrammarFileName() { return "RDL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public RDLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(RDLParser.EOF, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).enterProgram(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).exitProgram(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RDLVisitor ) return ((RDLVisitor<? extends T>)visitor).visitProgram(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << DATA) | (1L << LSM) | (1L << LAYER) | (1L << CONNECT) | (1L << SIMULATE) | (1L << SAVE))) != 0)) {
				{
				{
				setState(50);
				statement();
				}
				}
				setState(55);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(56);
			match(EOF);
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

	public static class StatementContext extends ParserRuleContext {
		public DataDeclContext dataDecl() {
			return getRuleContext(DataDeclContext.class,0);
		}
		public LsmDeclContext lsmDecl() {
			return getRuleContext(LsmDeclContext.class,0);
		}
		public LayerDeclContext layerDecl() {
			return getRuleContext(LayerDeclContext.class,0);
		}
		public ConnectStmtContext connectStmt() {
			return getRuleContext(ConnectStmtContext.class,0);
		}
		public SimulateStmtContext simulateStmt() {
			return getRuleContext(SimulateStmtContext.class,0);
		}
		public SaveStmtContext saveStmt() {
			return getRuleContext(SaveStmtContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RDLVisitor ) return ((RDLVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_statement);
		try {
			setState(64);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case DATA:
				enterOuterAlt(_localctx, 1);
				{
				setState(58);
				dataDecl();
				}
				break;
			case LSM:
				enterOuterAlt(_localctx, 2);
				{
				setState(59);
				lsmDecl();
				}
				break;
			case LAYER:
				enterOuterAlt(_localctx, 3);
				{
				setState(60);
				layerDecl();
				}
				break;
			case CONNECT:
				enterOuterAlt(_localctx, 4);
				{
				setState(61);
				connectStmt();
				}
				break;
			case SIMULATE:
				enterOuterAlt(_localctx, 5);
				{
				setState(62);
				simulateStmt();
				}
				break;
			case SAVE:
				enterOuterAlt(_localctx, 6);
				{
				setState(63);
				saveStmt();
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

	public static class DataDeclContext extends ParserRuleContext {
		public TerminalNode DATA() { return getToken(RDLParser.DATA, 0); }
		public TerminalNode ID() { return getToken(RDLParser.ID, 0); }
		public TerminalNode ASSIGN() { return getToken(RDLParser.ASSIGN, 0); }
		public TerminalNode LOAD() { return getToken(RDLParser.LOAD, 0); }
		public TerminalNode CSV() { return getToken(RDLParser.CSV, 0); }
		public TerminalNode STRING() { return getToken(RDLParser.STRING, 0); }
		public DataDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dataDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).enterDataDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).exitDataDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RDLVisitor ) return ((RDLVisitor<? extends T>)visitor).visitDataDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DataDeclContext dataDecl() throws RecognitionException {
		DataDeclContext _localctx = new DataDeclContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_dataDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(66);
			match(DATA);
			setState(67);
			match(ID);
			setState(68);
			match(ASSIGN);
			setState(69);
			match(LOAD);
			setState(70);
			match(CSV);
			setState(71);
			match(STRING);
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

	public static class LsmDeclContext extends ParserRuleContext {
		public TerminalNode LSM() { return getToken(RDLParser.LSM, 0); }
		public TerminalNode ID() { return getToken(RDLParser.ID, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public LsmDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lsmDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).enterLsmDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).exitLsmDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RDLVisitor ) return ((RDLVisitor<? extends T>)visitor).visitLsmDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LsmDeclContext lsmDecl() throws RecognitionException {
		LsmDeclContext _localctx = new LsmDeclContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_lsmDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
			match(LSM);
			setState(74);
			match(ID);
			setState(75);
			block();
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

	public static class LayerDeclContext extends ParserRuleContext {
		public TerminalNode LAYER() { return getToken(RDLParser.LAYER, 0); }
		public TerminalNode ID() { return getToken(RDLParser.ID, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public TerminalNode TORCH() { return getToken(RDLParser.TORCH, 0); }
		public LayerDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_layerDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).enterLayerDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).exitLayerDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RDLVisitor ) return ((RDLVisitor<? extends T>)visitor).visitLayerDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LayerDeclContext layerDecl() throws RecognitionException {
		LayerDeclContext _localctx = new LayerDeclContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_layerDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			match(LAYER);
			setState(79);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==TORCH) {
				{
				setState(78);
				match(TORCH);
				}
			}

			setState(81);
			match(ID);
			setState(82);
			block();
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

	public static class ConnectStmtContext extends ParserRuleContext {
		public TerminalNode CONNECT() { return getToken(RDLParser.CONNECT, 0); }
		public List<TerminalNode> ID() { return getTokens(RDLParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(RDLParser.ID, i);
		}
		public TerminalNode ARROW() { return getToken(RDLParser.ARROW, 0); }
		public TerminalNode WEIGHT() { return getToken(RDLParser.WEIGHT, 0); }
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public ConnectStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_connectStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).enterConnectStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).exitConnectStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RDLVisitor ) return ((RDLVisitor<? extends T>)visitor).visitConnectStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConnectStmtContext connectStmt() throws RecognitionException {
		ConnectStmtContext _localctx = new ConnectStmtContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_connectStmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			match(CONNECT);
			setState(85);
			match(ID);
			setState(86);
			match(ARROW);
			setState(87);
			match(ID);
			setState(90);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WEIGHT) {
				{
				setState(88);
				match(WEIGHT);
				setState(89);
				number();
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

	public static class SimulateStmtContext extends ParserRuleContext {
		public TerminalNode SIMULATE() { return getToken(RDLParser.SIMULATE, 0); }
		public TerminalNode WITH() { return getToken(RDLParser.WITH, 0); }
		public TerminalNode ID() { return getToken(RDLParser.ID, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public SimulateStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_simulateStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).enterSimulateStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).exitSimulateStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RDLVisitor ) return ((RDLVisitor<? extends T>)visitor).visitSimulateStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SimulateStmtContext simulateStmt() throws RecognitionException {
		SimulateStmtContext _localctx = new SimulateStmtContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_simulateStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			match(SIMULATE);
			setState(93);
			match(WITH);
			setState(94);
			match(ID);
			setState(95);
			block();
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

	public static class SaveStmtContext extends ParserRuleContext {
		public TerminalNode SAVE() { return getToken(RDLParser.SAVE, 0); }
		public TerminalNode ID() { return getToken(RDLParser.ID, 0); }
		public SaveStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_saveStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).enterSaveStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).exitSaveStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RDLVisitor ) return ((RDLVisitor<? extends T>)visitor).visitSaveStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SaveStmtContext saveStmt() throws RecognitionException {
		SaveStmtContext _localctx = new SaveStmtContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_saveStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			match(SAVE);
			setState(98);
			match(ID);
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

	public static class BlockContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(RDLParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(RDLParser.RBRACE, 0); }
		public List<BlockStatementContext> blockStatement() {
			return getRuleContexts(BlockStatementContext.class);
		}
		public BlockStatementContext blockStatement(int i) {
			return getRuleContext(BlockStatementContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).exitBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RDLVisitor ) return ((RDLVisitor<? extends T>)visitor).visitBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			match(LBRACE);
			setState(104);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << SPIKE) | (1L << IF) | (1L << EMIT) | (1L << SIZE) | (1L << TYPE) | (1L << STEPS) | (1L << ID))) != 0)) {
				{
				{
				setState(101);
				blockStatement();
				}
				}
				setState(106);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(107);
			match(RBRACE);
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

	public static class BlockStatementContext extends ParserRuleContext {
		public SpikeDeclContext spikeDecl() {
			return getRuleContext(SpikeDeclContext.class,0);
		}
		public IfStmtContext ifStmt() {
			return getRuleContext(IfStmtContext.class,0);
		}
		public EmitStmtContext emitStmt() {
			return getRuleContext(EmitStmtContext.class,0);
		}
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public SizeStmtContext sizeStmt() {
			return getRuleContext(SizeStmtContext.class,0);
		}
		public TypeStmtContext typeStmt() {
			return getRuleContext(TypeStmtContext.class,0);
		}
		public StepsStmtContext stepsStmt() {
			return getRuleContext(StepsStmtContext.class,0);
		}
		public BlockStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).enterBlockStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).exitBlockStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RDLVisitor ) return ((RDLVisitor<? extends T>)visitor).visitBlockStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BlockStatementContext blockStatement() throws RecognitionException {
		BlockStatementContext _localctx = new BlockStatementContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_blockStatement);
		try {
			setState(116);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case SPIKE:
				enterOuterAlt(_localctx, 1);
				{
				setState(109);
				spikeDecl();
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 2);
				{
				setState(110);
				ifStmt();
				}
				break;
			case EMIT:
				enterOuterAlt(_localctx, 3);
				{
				setState(111);
				emitStmt();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 4);
				{
				setState(112);
				assignment();
				}
				break;
			case SIZE:
				enterOuterAlt(_localctx, 5);
				{
				setState(113);
				sizeStmt();
				}
				break;
			case TYPE:
				enterOuterAlt(_localctx, 6);
				{
				setState(114);
				typeStmt();
				}
				break;
			case STEPS:
				enterOuterAlt(_localctx, 7);
				{
				setState(115);
				stepsStmt();
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

	public static class SpikeDeclContext extends ParserRuleContext {
		public TerminalNode SPIKE() { return getToken(RDLParser.SPIKE, 0); }
		public TerminalNode LPAREN() { return getToken(RDLParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(RDLParser.RPAREN, 0); }
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public SpikeDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_spikeDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).enterSpikeDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).exitSpikeDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RDLVisitor ) return ((RDLVisitor<? extends T>)visitor).visitSpikeDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SpikeDeclContext spikeDecl() throws RecognitionException {
		SpikeDeclContext _localctx = new SpikeDeclContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_spikeDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			match(SPIKE);
			setState(119);
			match(LPAREN);
			setState(120);
			match(RPAREN);
			setState(121);
			block();
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

	public static class IfStmtContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(RDLParser.IF, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public IfStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).enterIfStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).exitIfStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RDLVisitor ) return ((RDLVisitor<? extends T>)visitor).visitIfStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfStmtContext ifStmt() throws RecognitionException {
		IfStmtContext _localctx = new IfStmtContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_ifStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
			match(IF);
			setState(124);
			expr();
			setState(125);
			block();
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

	public static class EmitStmtContext extends ParserRuleContext {
		public TerminalNode EMIT() { return getToken(RDLParser.EMIT, 0); }
		public TerminalNode SPIKE() { return getToken(RDLParser.SPIKE, 0); }
		public EmitStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_emitStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).enterEmitStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).exitEmitStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RDLVisitor ) return ((RDLVisitor<? extends T>)visitor).visitEmitStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EmitStmtContext emitStmt() throws RecognitionException {
		EmitStmtContext _localctx = new EmitStmtContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_emitStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(127);
			match(EMIT);
			setState(128);
			match(SPIKE);
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

	public static class AssignmentContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(RDLParser.ID, 0); }
		public TerminalNode ASSIGN() { return getToken(RDLParser.ASSIGN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).enterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).exitAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RDLVisitor ) return ((RDLVisitor<? extends T>)visitor).visitAssignment(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			match(ID);
			setState(131);
			match(ASSIGN);
			setState(132);
			expr();
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

	public static class SizeStmtContext extends ParserRuleContext {
		public TerminalNode SIZE() { return getToken(RDLParser.SIZE, 0); }
		public TerminalNode INT() { return getToken(RDLParser.INT, 0); }
		public SizeStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sizeStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).enterSizeStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).exitSizeStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RDLVisitor ) return ((RDLVisitor<? extends T>)visitor).visitSizeStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SizeStmtContext sizeStmt() throws RecognitionException {
		SizeStmtContext _localctx = new SizeStmtContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_sizeStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
			match(SIZE);
			setState(135);
			match(INT);
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

	public static class TypeStmtContext extends ParserRuleContext {
		public TerminalNode TYPE() { return getToken(RDLParser.TYPE, 0); }
		public TypeValueContext typeValue() {
			return getRuleContext(TypeValueContext.class,0);
		}
		public TypeStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).enterTypeStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).exitTypeStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RDLVisitor ) return ((RDLVisitor<? extends T>)visitor).visitTypeStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeStmtContext typeStmt() throws RecognitionException {
		TypeStmtContext _localctx = new TypeStmtContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_typeStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			match(TYPE);
			setState(138);
			typeValue();
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

	public static class StepsStmtContext extends ParserRuleContext {
		public TerminalNode STEPS() { return getToken(RDLParser.STEPS, 0); }
		public TerminalNode INT() { return getToken(RDLParser.INT, 0); }
		public StepsStmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stepsStmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).enterStepsStmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).exitStepsStmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RDLVisitor ) return ((RDLVisitor<? extends T>)visitor).visitStepsStmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StepsStmtContext stepsStmt() throws RecognitionException {
		StepsStmtContext _localctx = new StepsStmtContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_stepsStmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(140);
			match(STEPS);
			setState(141);
			match(INT);
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

	public static class TypeValueContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(RDLParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(RDLParser.ID, i);
		}
		public TypeValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).enterTypeValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).exitTypeValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RDLVisitor ) return ((RDLVisitor<? extends T>)visitor).visitTypeValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeValueContext typeValue() throws RecognitionException {
		TypeValueContext _localctx = new TypeValueContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_typeValue);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(144); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(143);
					match(ID);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(146); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class ExprContext extends ParserRuleContext {
		public ComparisonExprContext comparisonExpr() {
			return getRuleContext(ComparisonExprContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RDLVisitor ) return ((RDLVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			comparisonExpr();
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

	public static class ComparisonExprContext extends ParserRuleContext {
		public List<AdditiveExprContext> additiveExpr() {
			return getRuleContexts(AdditiveExprContext.class);
		}
		public AdditiveExprContext additiveExpr(int i) {
			return getRuleContext(AdditiveExprContext.class,i);
		}
		public TerminalNode GT() { return getToken(RDLParser.GT, 0); }
		public TerminalNode LT() { return getToken(RDLParser.LT, 0); }
		public TerminalNode GTE() { return getToken(RDLParser.GTE, 0); }
		public TerminalNode LTE() { return getToken(RDLParser.LTE, 0); }
		public TerminalNode EQ() { return getToken(RDLParser.EQ, 0); }
		public TerminalNode NEQ() { return getToken(RDLParser.NEQ, 0); }
		public ComparisonExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparisonExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).enterComparisonExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).exitComparisonExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RDLVisitor ) return ((RDLVisitor<? extends T>)visitor).visitComparisonExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparisonExprContext comparisonExpr() throws RecognitionException {
		ComparisonExprContext _localctx = new ComparisonExprContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_comparisonExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
			additiveExpr();
			setState(153);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GT) | (1L << LT) | (1L << GTE) | (1L << LTE) | (1L << EQ) | (1L << NEQ))) != 0)) {
				{
				setState(151);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << GT) | (1L << LT) | (1L << GTE) | (1L << LTE) | (1L << EQ) | (1L << NEQ))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(152);
				additiveExpr();
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

	public static class AdditiveExprContext extends ParserRuleContext {
		public List<MultiplicativeExprContext> multiplicativeExpr() {
			return getRuleContexts(MultiplicativeExprContext.class);
		}
		public MultiplicativeExprContext multiplicativeExpr(int i) {
			return getRuleContext(MultiplicativeExprContext.class,i);
		}
		public List<TerminalNode> PLUS() { return getTokens(RDLParser.PLUS); }
		public TerminalNode PLUS(int i) {
			return getToken(RDLParser.PLUS, i);
		}
		public List<TerminalNode> MINUS() { return getTokens(RDLParser.MINUS); }
		public TerminalNode MINUS(int i) {
			return getToken(RDLParser.MINUS, i);
		}
		public AdditiveExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_additiveExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).enterAdditiveExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).exitAdditiveExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RDLVisitor ) return ((RDLVisitor<? extends T>)visitor).visitAdditiveExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AdditiveExprContext additiveExpr() throws RecognitionException {
		AdditiveExprContext _localctx = new AdditiveExprContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_additiveExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155);
			multiplicativeExpr();
			setState(160);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==PLUS || _la==MINUS) {
				{
				{
				setState(156);
				_la = _input.LA(1);
				if ( !(_la==PLUS || _la==MINUS) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(157);
				multiplicativeExpr();
				}
				}
				setState(162);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class MultiplicativeExprContext extends ParserRuleContext {
		public List<UnaryExprContext> unaryExpr() {
			return getRuleContexts(UnaryExprContext.class);
		}
		public UnaryExprContext unaryExpr(int i) {
			return getRuleContext(UnaryExprContext.class,i);
		}
		public List<TerminalNode> MUL() { return getTokens(RDLParser.MUL); }
		public TerminalNode MUL(int i) {
			return getToken(RDLParser.MUL, i);
		}
		public List<TerminalNode> DIV() { return getTokens(RDLParser.DIV); }
		public TerminalNode DIV(int i) {
			return getToken(RDLParser.DIV, i);
		}
		public MultiplicativeExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiplicativeExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).enterMultiplicativeExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).exitMultiplicativeExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RDLVisitor ) return ((RDLVisitor<? extends T>)visitor).visitMultiplicativeExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultiplicativeExprContext multiplicativeExpr() throws RecognitionException {
		MultiplicativeExprContext _localctx = new MultiplicativeExprContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_multiplicativeExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163);
			unaryExpr();
			setState(168);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==MUL || _la==DIV) {
				{
				{
				setState(164);
				_la = _input.LA(1);
				if ( !(_la==MUL || _la==DIV) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(165);
				unaryExpr();
				}
				}
				setState(170);
				_errHandler.sync(this);
				_la = _input.LA(1);
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

	public static class UnaryExprContext extends ParserRuleContext {
		public TerminalNode MINUS() { return getToken(RDLParser.MINUS, 0); }
		public UnaryExprContext unaryExpr() {
			return getRuleContext(UnaryExprContext.class,0);
		}
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public UnaryExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_unaryExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).enterUnaryExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).exitUnaryExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RDLVisitor ) return ((RDLVisitor<? extends T>)visitor).visitUnaryExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final UnaryExprContext unaryExpr() throws RecognitionException {
		UnaryExprContext _localctx = new UnaryExprContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_unaryExpr);
		try {
			setState(174);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case MINUS:
				enterOuterAlt(_localctx, 1);
				{
				setState(171);
				match(MINUS);
				setState(172);
				unaryExpr();
				}
				break;
			case LPAREN:
			case ID:
			case INT:
			case FLOAT:
			case STRING:
				enterOuterAlt(_localctx, 2);
				{
				setState(173);
				primary();
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

	public static class PrimaryContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(RDLParser.ID, 0); }
		public NumberContext number() {
			return getRuleContext(NumberContext.class,0);
		}
		public TerminalNode STRING() { return getToken(RDLParser.STRING, 0); }
		public TerminalNode LPAREN() { return getToken(RDLParser.LPAREN, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(RDLParser.RPAREN, 0); }
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).enterPrimary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).exitPrimary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RDLVisitor ) return ((RDLVisitor<? extends T>)visitor).visitPrimary(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_primary);
		try {
			setState(183);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(176);
				match(ID);
				}
				break;
			case INT:
			case FLOAT:
				enterOuterAlt(_localctx, 2);
				{
				setState(177);
				number();
				}
				break;
			case STRING:
				enterOuterAlt(_localctx, 3);
				{
				setState(178);
				match(STRING);
				}
				break;
			case LPAREN:
				enterOuterAlt(_localctx, 4);
				{
				setState(179);
				match(LPAREN);
				setState(180);
				expr();
				setState(181);
				match(RPAREN);
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

	public static class NumberContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(RDLParser.INT, 0); }
		public TerminalNode FLOAT() { return getToken(RDLParser.FLOAT, 0); }
		public NumberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).enterNumber(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RDLListener ) ((RDLListener)listener).exitNumber(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RDLVisitor ) return ((RDLVisitor<? extends T>)visitor).visitNumber(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberContext number() throws RecognitionException {
		NumberContext _localctx = new NumberContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_number);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(185);
			_la = _input.LA(1);
			if ( !(_la==INT || _la==FLOAT) ) {
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3*\u00be\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\3\2\7\2\66\n\2\f\2\16\29\13\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\5\3C\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\5\6R\n"+
		"\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\5\7]\n\7\3\b\3\b\3\b\3\b\3\b\3"+
		"\t\3\t\3\t\3\n\3\n\7\ni\n\n\f\n\16\nl\13\n\3\n\3\n\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\5\13w\n\13\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3"+
		"\16\3\16\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3"+
		"\22\3\23\6\23\u0093\n\23\r\23\16\23\u0094\3\24\3\24\3\25\3\25\3\25\5\25"+
		"\u009c\n\25\3\26\3\26\3\26\7\26\u00a1\n\26\f\26\16\26\u00a4\13\26\3\27"+
		"\3\27\3\27\7\27\u00a9\n\27\f\27\16\27\u00ac\13\27\3\30\3\30\3\30\5\30"+
		"\u00b1\n\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\5\31\u00ba\n\31\3\32\3"+
		"\32\3\32\2\2\33\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62"+
		"\2\6\3\2\26\33\3\2\34\35\3\2\36\37\3\2%&\2\u00bb\2\67\3\2\2\2\4B\3\2\2"+
		"\2\6D\3\2\2\2\bK\3\2\2\2\nO\3\2\2\2\fV\3\2\2\2\16^\3\2\2\2\20c\3\2\2\2"+
		"\22f\3\2\2\2\24v\3\2\2\2\26x\3\2\2\2\30}\3\2\2\2\32\u0081\3\2\2\2\34\u0084"+
		"\3\2\2\2\36\u0088\3\2\2\2 \u008b\3\2\2\2\"\u008e\3\2\2\2$\u0092\3\2\2"+
		"\2&\u0096\3\2\2\2(\u0098\3\2\2\2*\u009d\3\2\2\2,\u00a5\3\2\2\2.\u00b0"+
		"\3\2\2\2\60\u00b9\3\2\2\2\62\u00bb\3\2\2\2\64\66\5\4\3\2\65\64\3\2\2\2"+
		"\669\3\2\2\2\67\65\3\2\2\2\678\3\2\2\28:\3\2\2\29\67\3\2\2\2:;\7\2\2\3"+
		";\3\3\2\2\2<C\5\6\4\2=C\5\b\5\2>C\5\n\6\2?C\5\f\7\2@C\5\16\b\2AC\5\20"+
		"\t\2B<\3\2\2\2B=\3\2\2\2B>\3\2\2\2B?\3\2\2\2B@\3\2\2\2BA\3\2\2\2C\5\3"+
		"\2\2\2DE\7\3\2\2EF\7$\2\2FG\7\25\2\2GH\7\4\2\2HI\7\5\2\2IJ\7\'\2\2J\7"+
		"\3\2\2\2KL\7\6\2\2LM\7$\2\2MN\5\22\n\2N\t\3\2\2\2OQ\7\7\2\2PR\7\b\2\2"+
		"QP\3\2\2\2QR\3\2\2\2RS\3\2\2\2ST\7$\2\2TU\5\22\n\2U\13\3\2\2\2VW\7\t\2"+
		"\2WX\7$\2\2XY\7\24\2\2Y\\\7$\2\2Z[\7\n\2\2[]\5\62\32\2\\Z\3\2\2\2\\]\3"+
		"\2\2\2]\r\3\2\2\2^_\7\13\2\2_`\7\f\2\2`a\7$\2\2ab\5\22\n\2b\17\3\2\2\2"+
		"cd\7\r\2\2de\7$\2\2e\21\3\2\2\2fj\7\"\2\2gi\5\24\13\2hg\3\2\2\2il\3\2"+
		"\2\2jh\3\2\2\2jk\3\2\2\2km\3\2\2\2lj\3\2\2\2mn\7#\2\2n\23\3\2\2\2ow\5"+
		"\26\f\2pw\5\30\r\2qw\5\32\16\2rw\5\34\17\2sw\5\36\20\2tw\5 \21\2uw\5\""+
		"\22\2vo\3\2\2\2vp\3\2\2\2vq\3\2\2\2vr\3\2\2\2vs\3\2\2\2vt\3\2\2\2vu\3"+
		"\2\2\2w\25\3\2\2\2xy\7\16\2\2yz\7 \2\2z{\7!\2\2{|\5\22\n\2|\27\3\2\2\2"+
		"}~\7\17\2\2~\177\5&\24\2\177\u0080\5\22\n\2\u0080\31\3\2\2\2\u0081\u0082"+
		"\7\20\2\2\u0082\u0083\7\16\2\2\u0083\33\3\2\2\2\u0084\u0085\7$\2\2\u0085"+
		"\u0086\7\25\2\2\u0086\u0087\5&\24\2\u0087\35\3\2\2\2\u0088\u0089\7\21"+
		"\2\2\u0089\u008a\7%\2\2\u008a\37\3\2\2\2\u008b\u008c\7\22\2\2\u008c\u008d"+
		"\5$\23\2\u008d!\3\2\2\2\u008e\u008f\7\23\2\2\u008f\u0090\7%\2\2\u0090"+
		"#\3\2\2\2\u0091\u0093\7$\2\2\u0092\u0091\3\2\2\2\u0093\u0094\3\2\2\2\u0094"+
		"\u0092\3\2\2\2\u0094\u0095\3\2\2\2\u0095%\3\2\2\2\u0096\u0097\5(\25\2"+
		"\u0097\'\3\2\2\2\u0098\u009b\5*\26\2\u0099\u009a\t\2\2\2\u009a\u009c\5"+
		"*\26\2\u009b\u0099\3\2\2\2\u009b\u009c\3\2\2\2\u009c)\3\2\2\2\u009d\u00a2"+
		"\5,\27\2\u009e\u009f\t\3\2\2\u009f\u00a1\5,\27\2\u00a0\u009e\3\2\2\2\u00a1"+
		"\u00a4\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3+\3\2\2\2"+
		"\u00a4\u00a2\3\2\2\2\u00a5\u00aa\5.\30\2\u00a6\u00a7\t\4\2\2\u00a7\u00a9"+
		"\5.\30\2\u00a8\u00a6\3\2\2\2\u00a9\u00ac\3\2\2\2\u00aa\u00a8\3\2\2\2\u00aa"+
		"\u00ab\3\2\2\2\u00ab-\3\2\2\2\u00ac\u00aa\3\2\2\2\u00ad\u00ae\7\35\2\2"+
		"\u00ae\u00b1\5.\30\2\u00af\u00b1\5\60\31\2\u00b0\u00ad\3\2\2\2\u00b0\u00af"+
		"\3\2\2\2\u00b1/\3\2\2\2\u00b2\u00ba\7$\2\2\u00b3\u00ba\5\62\32\2\u00b4"+
		"\u00ba\7\'\2\2\u00b5\u00b6\7 \2\2\u00b6\u00b7\5&\24\2\u00b7\u00b8\7!\2"+
		"\2\u00b8\u00ba\3\2\2\2\u00b9\u00b2\3\2\2\2\u00b9\u00b3\3\2\2\2\u00b9\u00b4"+
		"\3\2\2\2\u00b9\u00b5\3\2\2\2\u00ba\61\3\2\2\2\u00bb\u00bc\t\5\2\2\u00bc"+
		"\63\3\2\2\2\16\67BQ\\jv\u0094\u009b\u00a2\u00aa\u00b0\u00b9";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}