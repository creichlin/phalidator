// Generated from src/ch/kerbtier/phalidator/parser/Phal.g4 by ANTLR 4.2
package ch.kerbtier.phalidator.parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class PhalParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__8=1, T__7=2, T__6=3, T__5=4, T__4=5, T__3=6, T__2=7, T__1=8, T__0=9, 
		NUMBER=10, MULTIPLICATION=11, DIVISION=12, ADDITION=13, SUBTRACTION=14, 
		NOT=15, AND=16, OR=17, LESS=18, MORE=19, LESS_EQUAL=20, MORE_EQUAL=21, 
		EQUAL=22, NOT_EQUAL=23, IN=24, RANGE=25, MATCHES=26, LETTERS=27, CHARS=28, 
		LENGTH=29, DELIMITER=30, QUOTED_STRING=31, REGEXP=32, IDENTIFIER=33, BLOCK_COMMENT=34, 
		EOL_COMMENT=35, WS=36;
	public static final String[] tokenNames = {
		"<INVALID>", "'$'", "'('", "')'", "':'", "'['", "'{'", "','", "']'", "'}'", 
		"NUMBER", "'*'", "'/'", "'+'", "'-'", "'!'", "'and'", "'or'", "'<'", "'>'", 
		"'<='", "'>='", "'=='", "'!='", "'in'", "'..'", "'matches'", "'letters'", 
		"'chars'", "'length'", "'.'", "QUOTED_STRING", "REGEXP", "IDENTIFIER", 
		"BLOCK_COMMENT", "EOL_COMMENT", "WS"
	};
	public static final int
		RULE_start = 0, RULE_namespace = 1, RULE_norm = 2, RULE_booleanExpression = 3, 
		RULE_booleanValue = 4, RULE_expression = 5, RULE_primaryExpression = 6, 
		RULE_pattern = 7, RULE_variable = 8, RULE_operation = 9, RULE_field = 10, 
		RULE_set = 11, RULE_string = 12, RULE_array = 13, RULE_regexp = 14, RULE_identifier = 15;
	public static final String[] ruleNames = {
		"start", "namespace", "norm", "booleanExpression", "booleanValue", "expression", 
		"primaryExpression", "pattern", "variable", "operation", "field", "set", 
		"string", "array", "regexp", "identifier"
	};

	@Override
	public String getGrammarFileName() { return "Phal.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public PhalParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class StartContext extends ParserRuleContext {
		public List<NamespaceContext> namespace() {
			return getRuleContexts(NamespaceContext.class);
		}
		public NamespaceContext namespace(int i) {
			return getRuleContext(NamespaceContext.class,i);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhalVisitor ) return ((PhalVisitor<? extends T>)visitor).visitStart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(33); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(32); namespace();
				}
				}
				setState(35); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << AND) | (1L << OR) | (1L << IN) | (1L << MATCHES) | (1L << LETTERS) | (1L << CHARS) | (1L << LENGTH) | (1L << IDENTIFIER))) != 0) );
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

	public static class NamespaceContext extends ParserRuleContext {
		public NormContext norm(int i) {
			return getRuleContext(NormContext.class,i);
		}
		public List<NamespaceContext> namespace() {
			return getRuleContexts(NamespaceContext.class);
		}
		public NamespaceContext namespace(int i) {
			return getRuleContext(NamespaceContext.class,i);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public List<NormContext> norm() {
			return getRuleContexts(NormContext.class);
		}
		public NamespaceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namespace; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhalVisitor ) return ((PhalVisitor<? extends T>)visitor).visitNamespace(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NamespaceContext namespace() throws RecognitionException {
		NamespaceContext _localctx = new NamespaceContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_namespace);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(37); identifier();
			setState(38); match(6);
			setState(43);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << AND) | (1L << OR) | (1L << IN) | (1L << MATCHES) | (1L << LETTERS) | (1L << CHARS) | (1L << LENGTH) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(41);
				switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
				case 1:
					{
					setState(39); namespace();
					}
					break;

				case 2:
					{
					setState(40); norm();
					}
					break;
				}
				}
				setState(45);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(46); match(9);
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

	public static class NormContext extends ParserRuleContext {
		public BooleanExpressionContext booleanExpression() {
			return getRuleContext(BooleanExpressionContext.class,0);
		}
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public NormContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_norm; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhalVisitor ) return ((PhalVisitor<? extends T>)visitor).visitNorm(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NormContext norm() throws RecognitionException {
		NormContext _localctx = new NormContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_norm);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48); identifier();
			setState(49); match(4);
			setState(50); booleanExpression(0);
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

	public static class BooleanExpressionContext extends ParserRuleContext {
		public TerminalNode NOT() { return getToken(PhalParser.NOT, 0); }
		public BooleanExpressionContext booleanExpression(int i) {
			return getRuleContext(BooleanExpressionContext.class,i);
		}
		public TerminalNode AND() { return getToken(PhalParser.AND, 0); }
		public List<BooleanExpressionContext> booleanExpression() {
			return getRuleContexts(BooleanExpressionContext.class);
		}
		public TerminalNode OR() { return getToken(PhalParser.OR, 0); }
		public BooleanValueContext booleanValue() {
			return getRuleContext(BooleanValueContext.class,0);
		}
		public BooleanExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_booleanExpression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhalVisitor ) return ((PhalVisitor<? extends T>)visitor).visitBooleanExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BooleanExpressionContext booleanExpression() throws RecognitionException {
		return booleanExpression(0);
	}

	private BooleanExpressionContext booleanExpression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		BooleanExpressionContext _localctx = new BooleanExpressionContext(_ctx, _parentState);
		BooleanExpressionContext _prevctx = _localctx;
		int _startState = 6;
		enterRecursionRule(_localctx, 6, RULE_booleanExpression, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				{
				setState(53); match(NOT);
				setState(54); booleanExpression(4);
				}
				break;

			case 2:
				{
				}
				break;

			case 3:
				{
				setState(56); booleanValue();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(67);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(65);
					switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
					case 1:
						{
						_localctx = new BooleanExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_booleanExpression);
						setState(59);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(60); match(AND);
						setState(61); booleanExpression(4);
						}
						break;

					case 2:
						{
						_localctx = new BooleanExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_booleanExpression);
						setState(62);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(63); match(OR);
						setState(64); booleanExpression(3);
						}
						break;
					}
					} 
				}
				setState(69);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
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

	public static class BooleanValueContext extends ParserRuleContext {
		public TerminalNode LESS() { return getToken(PhalParser.LESS, 0); }
		public PatternContext pattern() {
			return getRuleContext(PatternContext.class,0);
		}
		public TerminalNode MATCHES() { return getToken(PhalParser.MATCHES, 0); }
		public TerminalNode NOT_EQUAL() { return getToken(PhalParser.NOT_EQUAL, 0); }
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode MORE() { return getToken(PhalParser.MORE, 0); }
		public BooleanExpressionContext booleanExpression() {
			return getRuleContext(BooleanExpressionContext.class,0);
		}
		public SetContext set() {
			return getRuleContext(SetContext.class,0);
		}
		public TerminalNode IN() { return getToken(PhalParser.IN, 0); }
		public TerminalNode LESS_EQUAL() { return getToken(PhalParser.LESS_EQUAL, 0); }
		public TerminalNode EQUAL() { return getToken(PhalParser.EQUAL, 0); }
		public TerminalNode MORE_EQUAL() { return getToken(PhalParser.MORE_EQUAL, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public BooleanValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_booleanValue; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhalVisitor ) return ((PhalVisitor<? extends T>)visitor).visitBooleanValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BooleanValueContext booleanValue() throws RecognitionException {
		BooleanValueContext _localctx = new BooleanValueContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_booleanValue);
		try {
			setState(106);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(70); expression(0);
				setState(71); match(LESS);
				setState(72); expression(0);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(74); expression(0);
				setState(75); match(MORE);
				setState(76); expression(0);
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(78); expression(0);
				setState(79); match(LESS_EQUAL);
				setState(80); expression(0);
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(82); expression(0);
				setState(83); match(MORE_EQUAL);
				setState(84); expression(0);
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(86); expression(0);
				setState(87); match(EQUAL);
				setState(88); expression(0);
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(90); expression(0);
				setState(91); match(NOT_EQUAL);
				setState(92); expression(0);
				}
				break;

			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(94); expression(0);
				setState(95); match(IN);
				setState(96); set();
				}
				break;

			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(98); expression(0);
				setState(99); match(MATCHES);
				setState(100); pattern();
				}
				break;

			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(102); match(2);
				setState(103); booleanExpression(0);
				setState(104); match(3);
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

	public static class ExpressionContext extends ParserRuleContext {
		public TerminalNode DIVISION() { return getToken(PhalParser.DIVISION, 0); }
		public TerminalNode ADDITION() { return getToken(PhalParser.ADDITION, 0); }
		public TerminalNode SUBTRACTION() { return getToken(PhalParser.SUBTRACTION, 0); }
		public PrimaryExpressionContext primaryExpression() {
			return getRuleContext(PrimaryExpressionContext.class,0);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public TerminalNode MULTIPLICATION() { return getToken(PhalParser.MULTIPLICATION, 0); }
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhalVisitor ) return ((PhalVisitor<? extends T>)visitor).visitExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 10;
		enterRecursionRule(_localctx, 10, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(109); primaryExpression();
			}
			_ctx.stop = _input.LT(-1);
			setState(119);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=-1 ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(117);
					switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(111);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(112);
						_la = _input.LA(1);
						if ( !(_la==MULTIPLICATION || _la==DIVISION) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(113); expression(3);
						}
						break;

					case 2:
						{
						_localctx = new ExpressionContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(114);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(115);
						_la = _input.LA(1);
						if ( !(_la==ADDITION || _la==SUBTRACTION) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(116); expression(2);
						}
						break;
					}
					} 
				}
				setState(121);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
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

	public static class PrimaryExpressionContext extends ParserRuleContext {
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public TerminalNode NUMBER() { return getToken(PhalParser.NUMBER, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VariableContext variable() {
			return getRuleContext(VariableContext.class,0);
		}
		public PrimaryExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primaryExpression; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhalVisitor ) return ((PhalVisitor<? extends T>)visitor).visitPrimaryExpression(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrimaryExpressionContext primaryExpression() throws RecognitionException {
		PrimaryExpressionContext _localctx = new PrimaryExpressionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_primaryExpression);
		try {
			setState(129);
			switch (_input.LA(1)) {
			case 2:
				enterOuterAlt(_localctx, 1);
				{
				setState(122); match(2);
				setState(123); expression(0);
				setState(124); match(3);
				}
				break;
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(126); match(NUMBER);
				}
				break;
			case QUOTED_STRING:
				enterOuterAlt(_localctx, 3);
				{
				setState(127); string();
				}
				break;
			case 1:
			case LETTERS:
			case LENGTH:
			case DELIMITER:
				enterOuterAlt(_localctx, 4);
				{
				setState(128); variable();
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

	public static class PatternContext extends ParserRuleContext {
		public RegexpContext regexp() {
			return getRuleContext(RegexpContext.class,0);
		}
		public PatternContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pattern; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhalVisitor ) return ((PhalVisitor<? extends T>)visitor).visitPattern(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PatternContext pattern() throws RecognitionException {
		PatternContext _localctx = new PatternContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_pattern);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131); regexp();
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

	public static class VariableContext extends ParserRuleContext {
		public TerminalNode DELIMITER() { return getToken(PhalParser.DELIMITER, 0); }
		public OperationContext operation() {
			return getRuleContext(OperationContext.class,0);
		}
		public FieldContext field() {
			return getRuleContext(FieldContext.class,0);
		}
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhalVisitor ) return ((PhalVisitor<? extends T>)visitor).visitVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_variable);
		try {
			setState(140);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(133); match(DELIMITER);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(134); operation();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(135); field();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(136); field();
				setState(137); match(DELIMITER);
				setState(138); operation();
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

	public static class OperationContext extends ParserRuleContext {
		public TerminalNode LETTERS() { return getToken(PhalParser.LETTERS, 0); }
		public TerminalNode LENGTH() { return getToken(PhalParser.LENGTH, 0); }
		public OperationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operation; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhalVisitor ) return ((PhalVisitor<? extends T>)visitor).visitOperation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperationContext operation() throws RecognitionException {
		OperationContext _localctx = new OperationContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_operation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			_la = _input.LA(1);
			if ( !(_la==LETTERS || _la==LENGTH) ) {
			_errHandler.recoverInline(this);
			}
			consume();
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

	public static class FieldContext extends ParserRuleContext {
		public IdentifierContext identifier() {
			return getRuleContext(IdentifierContext.class,0);
		}
		public FieldContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_field; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhalVisitor ) return ((PhalVisitor<? extends T>)visitor).visitField(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FieldContext field() throws RecognitionException {
		FieldContext _localctx = new FieldContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_field);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144); match(1);
			{
			setState(145); identifier();
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

	public static class SetContext extends ParserRuleContext {
		public TerminalNode RANGE() { return getToken(PhalParser.RANGE, 0); }
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ArrayContext array() {
			return getRuleContext(ArrayContext.class,0);
		}
		public SetContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_set; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhalVisitor ) return ((PhalVisitor<? extends T>)visitor).visitSet(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SetContext set() throws RecognitionException {
		SetContext _localctx = new SetContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_set);
		try {
			setState(153);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(147); expression(0);
				setState(148); match(RANGE);
				setState(149); expression(0);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(151); string();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(152); array();
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

	public static class StringContext extends ParserRuleContext {
		public TerminalNode QUOTED_STRING() { return getToken(PhalParser.QUOTED_STRING, 0); }
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhalVisitor ) return ((PhalVisitor<? extends T>)visitor).visitString(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_string);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155); match(QUOTED_STRING);
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

	public static class ArrayContext extends ParserRuleContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhalVisitor ) return ((PhalVisitor<? extends T>)visitor).visitArray(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayContext array() throws RecognitionException {
		ArrayContext _localctx = new ArrayContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_array);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(157); match(5);
			setState(167);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 1) | (1L << 2) | (1L << NUMBER) | (1L << LETTERS) | (1L << LENGTH) | (1L << DELIMITER) | (1L << QUOTED_STRING))) != 0)) {
				{
				setState(163);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
				while ( _alt!=2 && _alt!=-1 ) {
					if ( _alt==1 ) {
						{
						{
						setState(158); expression(0);
						setState(159); match(7);
						}
						} 
					}
					setState(165);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
				}
				setState(166); expression(0);
				}
			}

			setState(169); match(8);
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

	public static class RegexpContext extends ParserRuleContext {
		public TerminalNode REGEXP() { return getToken(PhalParser.REGEXP, 0); }
		public RegexpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_regexp; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhalVisitor ) return ((PhalVisitor<? extends T>)visitor).visitRegexp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RegexpContext regexp() throws RecognitionException {
		RegexpContext _localctx = new RegexpContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_regexp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171); match(REGEXP);
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

	public static class IdentifierContext extends ParserRuleContext {
		public TerminalNode CHARS() { return getToken(PhalParser.CHARS, 0); }
		public TerminalNode MATCHES() { return getToken(PhalParser.MATCHES, 0); }
		public TerminalNode AND() { return getToken(PhalParser.AND, 0); }
		public TerminalNode LETTERS() { return getToken(PhalParser.LETTERS, 0); }
		public TerminalNode OR() { return getToken(PhalParser.OR, 0); }
		public TerminalNode IDENTIFIER() { return getToken(PhalParser.IDENTIFIER, 0); }
		public TerminalNode LENGTH() { return getToken(PhalParser.LENGTH, 0); }
		public TerminalNode IN() { return getToken(PhalParser.IN, 0); }
		public IdentifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_identifier; }
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof PhalVisitor ) return ((PhalVisitor<? extends T>)visitor).visitIdentifier(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdentifierContext identifier() throws RecognitionException {
		IdentifierContext _localctx = new IdentifierContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_identifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << AND) | (1L << OR) | (1L << IN) | (1L << MATCHES) | (1L << LETTERS) | (1L << CHARS) | (1L << LENGTH) | (1L << IDENTIFIER))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
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
		case 3: return booleanExpression_sempred((BooleanExpressionContext)_localctx, predIndex);

		case 5: return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2: return precpred(_ctx, 2);

		case 3: return precpred(_ctx, 1);
		}
		return true;
	}
	private boolean booleanExpression_sempred(BooleanExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return precpred(_ctx, 3);

		case 1: return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3&\u00b2\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\6\2$\n"+
		"\2\r\2\16\2%\3\3\3\3\3\3\3\3\7\3,\n\3\f\3\16\3/\13\3\3\3\3\3\3\4\3\4\3"+
		"\4\3\4\3\5\3\5\3\5\3\5\3\5\5\5<\n\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5D\n\5\f"+
		"\5\16\5G\13\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\5\6m\n\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7x\n"+
		"\7\f\7\16\7{\13\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u0084\n\b\3\t\3\t\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u008f\n\n\3\13\3\13\3\f\3\f\3\f\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\5\r\u009c\n\r\3\16\3\16\3\17\3\17\3\17\3\17\7\17\u00a4"+
		"\n\17\f\17\16\17\u00a7\13\17\3\17\5\17\u00aa\n\17\3\17\3\17\3\20\3\20"+
		"\3\21\3\21\3\21\2\4\b\f\22\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \2\6"+
		"\3\2\r\16\3\2\17\20\4\2\35\35\37\37\6\2\22\23\32\32\34\37##\u00bc\2#\3"+
		"\2\2\2\4\'\3\2\2\2\6\62\3\2\2\2\b;\3\2\2\2\nl\3\2\2\2\fn\3\2\2\2\16\u0083"+
		"\3\2\2\2\20\u0085\3\2\2\2\22\u008e\3\2\2\2\24\u0090\3\2\2\2\26\u0092\3"+
		"\2\2\2\30\u009b\3\2\2\2\32\u009d\3\2\2\2\34\u009f\3\2\2\2\36\u00ad\3\2"+
		"\2\2 \u00af\3\2\2\2\"$\5\4\3\2#\"\3\2\2\2$%\3\2\2\2%#\3\2\2\2%&\3\2\2"+
		"\2&\3\3\2\2\2\'(\5 \21\2(-\7\b\2\2),\5\4\3\2*,\5\6\4\2+)\3\2\2\2+*\3\2"+
		"\2\2,/\3\2\2\2-+\3\2\2\2-.\3\2\2\2.\60\3\2\2\2/-\3\2\2\2\60\61\7\13\2"+
		"\2\61\5\3\2\2\2\62\63\5 \21\2\63\64\7\6\2\2\64\65\5\b\5\2\65\7\3\2\2\2"+
		"\66\67\b\5\1\2\678\7\21\2\28<\5\b\5\69<\3\2\2\2:<\5\n\6\2;\66\3\2\2\2"+
		";9\3\2\2\2;:\3\2\2\2<E\3\2\2\2=>\f\5\2\2>?\7\22\2\2?D\5\b\5\6@A\f\4\2"+
		"\2AB\7\23\2\2BD\5\b\5\5C=\3\2\2\2C@\3\2\2\2DG\3\2\2\2EC\3\2\2\2EF\3\2"+
		"\2\2F\t\3\2\2\2GE\3\2\2\2HI\5\f\7\2IJ\7\24\2\2JK\5\f\7\2Km\3\2\2\2LM\5"+
		"\f\7\2MN\7\25\2\2NO\5\f\7\2Om\3\2\2\2PQ\5\f\7\2QR\7\26\2\2RS\5\f\7\2S"+
		"m\3\2\2\2TU\5\f\7\2UV\7\27\2\2VW\5\f\7\2Wm\3\2\2\2XY\5\f\7\2YZ\7\30\2"+
		"\2Z[\5\f\7\2[m\3\2\2\2\\]\5\f\7\2]^\7\31\2\2^_\5\f\7\2_m\3\2\2\2`a\5\f"+
		"\7\2ab\7\32\2\2bc\5\30\r\2cm\3\2\2\2de\5\f\7\2ef\7\34\2\2fg\5\20\t\2g"+
		"m\3\2\2\2hi\7\4\2\2ij\5\b\5\2jk\7\5\2\2km\3\2\2\2lH\3\2\2\2lL\3\2\2\2"+
		"lP\3\2\2\2lT\3\2\2\2lX\3\2\2\2l\\\3\2\2\2l`\3\2\2\2ld\3\2\2\2lh\3\2\2"+
		"\2m\13\3\2\2\2no\b\7\1\2op\5\16\b\2py\3\2\2\2qr\f\4\2\2rs\t\2\2\2sx\5"+
		"\f\7\5tu\f\3\2\2uv\t\3\2\2vx\5\f\7\4wq\3\2\2\2wt\3\2\2\2x{\3\2\2\2yw\3"+
		"\2\2\2yz\3\2\2\2z\r\3\2\2\2{y\3\2\2\2|}\7\4\2\2}~\5\f\7\2~\177\7\5\2\2"+
		"\177\u0084\3\2\2\2\u0080\u0084\7\f\2\2\u0081\u0084\5\32\16\2\u0082\u0084"+
		"\5\22\n\2\u0083|\3\2\2\2\u0083\u0080\3\2\2\2\u0083\u0081\3\2\2\2\u0083"+
		"\u0082\3\2\2\2\u0084\17\3\2\2\2\u0085\u0086\5\36\20\2\u0086\21\3\2\2\2"+
		"\u0087\u008f\7 \2\2\u0088\u008f\5\24\13\2\u0089\u008f\5\26\f\2\u008a\u008b"+
		"\5\26\f\2\u008b\u008c\7 \2\2\u008c\u008d\5\24\13\2\u008d\u008f\3\2\2\2"+
		"\u008e\u0087\3\2\2\2\u008e\u0088\3\2\2\2\u008e\u0089\3\2\2\2\u008e\u008a"+
		"\3\2\2\2\u008f\23\3\2\2\2\u0090\u0091\t\4\2\2\u0091\25\3\2\2\2\u0092\u0093"+
		"\7\3\2\2\u0093\u0094\5 \21\2\u0094\27\3\2\2\2\u0095\u0096\5\f\7\2\u0096"+
		"\u0097\7\33\2\2\u0097\u0098\5\f\7\2\u0098\u009c\3\2\2\2\u0099\u009c\5"+
		"\32\16\2\u009a\u009c\5\34\17\2\u009b\u0095\3\2\2\2\u009b\u0099\3\2\2\2"+
		"\u009b\u009a\3\2\2\2\u009c\31\3\2\2\2\u009d\u009e\7!\2\2\u009e\33\3\2"+
		"\2\2\u009f\u00a9\7\7\2\2\u00a0\u00a1\5\f\7\2\u00a1\u00a2\7\t\2\2\u00a2"+
		"\u00a4\3\2\2\2\u00a3\u00a0\3\2\2\2\u00a4\u00a7\3\2\2\2\u00a5\u00a3\3\2"+
		"\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a8\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a8"+
		"\u00aa\5\f\7\2\u00a9\u00a5\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00ab\3\2"+
		"\2\2\u00ab\u00ac\7\n\2\2\u00ac\35\3\2\2\2\u00ad\u00ae\7\"\2\2\u00ae\37"+
		"\3\2\2\2\u00af\u00b0\t\5\2\2\u00b0!\3\2\2\2\20%+-;CElwy\u0083\u008e\u009b"+
		"\u00a5\u00a9";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}