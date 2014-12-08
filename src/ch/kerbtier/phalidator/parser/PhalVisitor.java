// Generated from src/ch/kerbtier/phalidator/parser/Phal.g4 by ANTLR 4.2
package ch.kerbtier.phalidator.parser;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link PhalParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface PhalVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link PhalParser#primaryExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExpression(@NotNull PhalParser.PrimaryExpressionContext ctx);

	/**
	 * Visit a parse tree produced by {@link PhalParser#regexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegexp(@NotNull PhalParser.RegexpContext ctx);

	/**
	 * Visit a parse tree produced by {@link PhalParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(@NotNull PhalParser.ExpressionContext ctx);

	/**
	 * Visit a parse tree produced by {@link PhalParser#set}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSet(@NotNull PhalParser.SetContext ctx);

	/**
	 * Visit a parse tree produced by {@link PhalParser#string}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(@NotNull PhalParser.StringContext ctx);

	/**
	 * Visit a parse tree produced by {@link PhalParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(@NotNull PhalParser.StartContext ctx);

	/**
	 * Visit a parse tree produced by {@link PhalParser#pattern}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPattern(@NotNull PhalParser.PatternContext ctx);

	/**
	 * Visit a parse tree produced by {@link PhalParser#booleanExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanExpression(@NotNull PhalParser.BooleanExpressionContext ctx);

	/**
	 * Visit a parse tree produced by {@link PhalParser#norm}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNorm(@NotNull PhalParser.NormContext ctx);

	/**
	 * Visit a parse tree produced by {@link PhalParser#field}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField(@NotNull PhalParser.FieldContext ctx);

	/**
	 * Visit a parse tree produced by {@link PhalParser#array}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArray(@NotNull PhalParser.ArrayContext ctx);

	/**
	 * Visit a parse tree produced by {@link PhalParser#namespace}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNamespace(@NotNull PhalParser.NamespaceContext ctx);

	/**
	 * Visit a parse tree produced by {@link PhalParser#variable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(@NotNull PhalParser.VariableContext ctx);

	/**
	 * Visit a parse tree produced by {@link PhalParser#booleanValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanValue(@NotNull PhalParser.BooleanValueContext ctx);

	/**
	 * Visit a parse tree produced by {@link PhalParser#operation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperation(@NotNull PhalParser.OperationContext ctx);
}