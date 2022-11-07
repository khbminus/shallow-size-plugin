package org.jub.kotlin.compiler.fir

import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrar

class ShallowSizeFirExtensionRegistrar : FirExtensionRegistrar() {
    override fun ExtensionRegistrarContext.configurePlugin() {
        +::ShallowSizeGenerator
        +::ShallowSizeChecker
    }
}
