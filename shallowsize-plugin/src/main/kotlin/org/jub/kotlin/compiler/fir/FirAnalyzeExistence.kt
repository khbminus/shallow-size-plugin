package org.jub.kotlin.compiler.fir

import org.jetbrains.kotlin.diagnostics.DiagnosticReporter
import org.jetbrains.kotlin.diagnostics.reportOn
import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.DeclarationCheckers
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirRegularClassChecker
import org.jetbrains.kotlin.fir.analysis.extensions.FirAdditionalCheckersExtension
import org.jetbrains.kotlin.fir.declarations.FirRegularClass
import org.jetbrains.kotlin.fir.symbols.SymbolInternals
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol

internal object FirAnalyzeExistence : FirRegularClassChecker() {
    @OptIn(SymbolInternals::class)
    override fun check(declaration: FirRegularClass, context: CheckerContext, reporter: DiagnosticReporter) {
        declaration
            .symbol
            .declarationSymbols
            .asSequence()
            .filterIsInstance<FirNamedFunctionSymbol>()
            .filter { it.name == ShallowSizeGenerator.FUNCTION_NAME }
            .filter { it.fir.valueParameters.isEmpty() }  // If overloads are possible
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
