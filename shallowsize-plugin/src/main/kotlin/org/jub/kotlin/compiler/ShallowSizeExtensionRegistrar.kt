package org.jub.kotlin.compiler

import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeys
import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar
import org.jetbrains.kotlin.compiler.plugin.ExperimentalCompilerApi
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrar
import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrarAdapter
import org.jub.kotlin.compiler.fir.ShallowSizeGenerator
import org.jub.kotlin.compiler.ir.ShallowSizeIrGenerationExtension
import org.jub.kotlin.compiler.ir.SimpleIrViewerPlugin

@OptIn(ExperimentalCompilerApi::class)
class ShallowSizeExtensionRegistrar : CompilerPluginRegistrar() {
    override val supportsK2: Boolean
        get() = true


    override fun ExtensionStorage.registerExtensions(configuration: CompilerConfiguration) {
        configuration.initMessageCollector("shallowsize.log")
        messageCollector = configuration[CLIConfigurationKeys.MESSAGE_COLLECTOR_KEY]
        FirExtensionRegistrarAdapter.registerExtension(ShallowSizeFirExtensionRegistrar())
        IrGenerationExtension.registerExtension(SimpleIrViewerPlugin())
        IrGenerationExtension.registerExtension(ShallowSizeIrGenerationExtension())
    }
}

class ShallowSizeFirExtensionRegistrar : FirExtensionRegistrar() {
    override fun ExtensionRegistrarContext.configurePlugin() {
        +::ShallowSizeGenerator
    }
}
