package org.jub.kotlin.compiler.services

import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar
import org.jetbrains.kotlin.compiler.plugin.ExperimentalCompilerApi
import org.jetbrains.kotlin.config.CompilerConfiguration
import org.jetbrains.kotlin.fir.extensions.FirExtensionRegistrarAdapter
import org.jetbrains.kotlin.test.model.TestModule
import org.jetbrains.kotlin.test.services.EnvironmentConfigurator
import org.jetbrains.kotlin.test.services.TestServices
import org.jub.kotlin.compiler.ShallowSizeFirExtensionRegistrar
import org.jub.kotlin.compiler.ir.ShallowSizeIrGenerationExtension
import org.jub.kotlin.compiler.ir.SimpleIrViewerPlugin

class ExtensionRegistrarConfigurator(testServices: TestServices) : EnvironmentConfigurator(testServices) {
    // Imitate the behaviour of the plugin
    @OptIn(ExperimentalCompilerApi::class)
    override fun CompilerPluginRegistrar.ExtensionStorage.registerCompilerExtensions(
        module: TestModule,
        configuration: CompilerConfiguration
    ) {
        FirExtensionRegistrarAdapter.registerExtension(ShallowSizeFirExtensionRegistrar())
        IrGenerationExtension.registerExtension(SimpleIrViewerPlugin())
        IrGenerationExtension.registerExtension(ShallowSizeIrGenerationExtension())
    }
}
