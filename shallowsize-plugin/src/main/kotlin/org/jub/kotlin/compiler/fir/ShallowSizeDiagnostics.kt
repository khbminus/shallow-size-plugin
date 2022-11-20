package org.jub.kotlin.compiler.fir

import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactoryToRendererMap
import org.jetbrains.kotlin.diagnostics.rendering.BaseDiagnosticRendererFactory
import org.jub.kotlin.compiler.Names

internal object ShallowSizeDiagnostics : BaseDiagnosticRendererFactory() {
    override val MAP: KtDiagnosticFactoryToRendererMap
        get() = KtDiagnosticFactoryToRendererMap(Names.pluginName).also {
            it.put(
                Errors.FUNCTION_EXISTS,
                "Could generate function `${Names.pluginName}()`, because it's already exists. " +
                        "Remove it or exclude class"
            )
        }
}
