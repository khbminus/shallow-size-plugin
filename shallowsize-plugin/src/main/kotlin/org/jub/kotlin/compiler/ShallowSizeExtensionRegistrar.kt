package org.jub.kotlin.compiler

import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.cli.common.CLIConfigurationKeys
import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar
import org.jetbrains.kotlin.compiler.plugin.ExperimentalCompilerApi
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrarAdapter
import org.jub.kotlin.compiler.fir.ShallowSizeFirExtensionRegistrar
import org.jub.kotlin.compiler.ir.ShallowSizeIrGenerationExtension

@OptIn(ExperimentalCompilerApi::class)
class ShallowSizeExtensionRegistrar : CompilerPluginRegistrar() {
    override val supportsK2: Boolean
        get() = true

    override fun ExtensionStorage.registerExtensions(configuration: CompilerConfiguration) {
        PluginConfig.init(configuration)
        configuration.initMessageCollector("${Names.pluginName}.log")
        messageCollector = configuration[CLIConfigurationKeys.MESSAGE_COLLECTOR_KEY]
        if (PluginConfig.enabled) {
            FirExtensionRegistrarAdapter.registerExtension(ShallowSizeFirExtensionRegistrar())
            IrGenerationExtension.registerExtension(ShallowSizeIrGenerationExtension())
        }
    }
}

