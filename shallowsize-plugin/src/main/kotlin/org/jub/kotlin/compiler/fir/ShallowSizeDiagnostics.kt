package org.jub.kotlin.compiler.fir

import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactoryToRendererMap
import org.jetbrains.kotlin.diagnostics.rendering.BaseDiagnosticRendererFactory

internal object ShallowSizeDiagnostics : BaseDiagnosticRendererFactory() {
    override val MAP: KtDiagnosticFactoryToRendererMap
        get() = KtDiagnosticFactoryToRendererMap("shallowSize").also {
            it.put(
                Errors.FUNCTION_EXISTS, "Could generate function `shallowSize()`, because it's already exists. " +
                        "Remove it or exclude class"
            )
        }
}
