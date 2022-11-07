package org.jub.kotlin.compiler.fir

import org.jetbrains.kotlin.diagnostics.SourceElementPositioningStrategies
import org.jetbrains.kotlin.diagnostics.error0
import org.jetbrains.kotlin.diagnostics.rendering.RootDiagnosticRendererFactory
import org.jetbrains.kotlin.psi.KtFunction

internal object Errors {
    val FUNCTION_EXISTS by error0<KtFunction>(SourceElementPositioningStrategies.NAME_IDENTIFIER)

    init {
        RootDiagnosticRendererFactory.registerFactory(ShallowSizeDiagnostics)
    }
}