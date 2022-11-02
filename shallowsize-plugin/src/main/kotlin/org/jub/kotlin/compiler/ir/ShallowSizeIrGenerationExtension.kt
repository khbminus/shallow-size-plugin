package org.jub.kotlin.compiler.ir

import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment
import org.jetbrains.kotlin.ir.visitors.acceptChildrenVoid
import org.jub.kotlin.compiler.log
import org.jub.kotlin.compiler.messageCollector

class ShallowSizeIrGenerationExtension : IrGenerationExtension {
    override fun generate(moduleFragment: IrModuleFragment, pluginContext: IrPluginContext) {
        messageCollector?.log("Generating!")
        moduleFragment.acceptChildrenVoid(ShallowSizeBodyGenerator(pluginContext))
    }
}
