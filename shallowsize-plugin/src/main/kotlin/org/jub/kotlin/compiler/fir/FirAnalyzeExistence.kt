package org.jub.kotlin.compiler.fir

import org.jetbrains.kotlin.diagnostics.*
import org.jetbrains.kotlin.diagnostics.rendering.BaseDiagnosticRendererFactory
import org.jetbrains.kotlin.diagnostics.rendering.RootDiagnosticRendererFactory
import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirRegularClassChecker
import org.jetbrains.kotlin.fir.analysis.extensions.FirAdditionalCheckersExtension
import org.jetbrains.kotlin.fir.declarations.FirRegularClass
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol
import org.jetbrains.kotlin.psi.KtFunction

internal object Errors {
    val FUNCTION_EXISTS by error0<KtFunction>(SourceElementPositioningStrategies.NAME_IDENTIFIER)

    init {
        RootDiagnosticRendererFactory.registerFactory(ShallowSizeDiagnostics)
    }
}

internal object ShallowSizeDiagnostics : BaseDiagnosticRendererFactory() {
    override val MAP: KtDiagnosticFactoryToRendererMap
        get() = KtDiagnosticFactoryToRendererMap("shallowSize").also {
            it.put(
                Errors.FUNCTION_EXISTS, "Could generate function `shallowSize()`, because it's already exists. " +
                        "Remove it or exclude class"
            )
        }
}

internal object FirAnalyzeExistence : FirRegularClassChecker() {

    override fun check(declaration: FirRegularClass, context: CheckerContext, reporter: DiagnosticReporter) {
        declaration
            .symbol
            .declarationSymbols
            .filterIsInstance<FirNamedFunctionSymbol>()
            .filter { it.name.identifier == "shallowSize" }
            .forEach {
                reporter.reportOn(it.source, Errors.FUNCTION_EXISTS, context)
            }
    }
}

internal class ShallowSizeChecker(session: FirSession) : FirAdditionalCheckersExtension(session) {
    override val declarationCheckers: DeclarationCheckers = object : DeclarationCheckers() {
        override val regularClassCheckers: Set<FirRegularClassChecker>
            get() = setOf(FirAnalyzeExistence)
    }
}
