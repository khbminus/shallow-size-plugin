package org.jub.kotlin.compiler.fir

import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrar
import org.jub.kotlin.compiler.fir.ShallowSizeGenerator

class ShallowSizeFirExtensionRegistrar : FirExtensionRegistrar() {
    override fun ExtensionRegistrarContext.configurePlugin() {
        +::ShallowSizeGenerator
    }
}
