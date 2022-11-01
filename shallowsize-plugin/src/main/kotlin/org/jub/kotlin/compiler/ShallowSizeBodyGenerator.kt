package org.jub.kotlin.compiler

import org.jetbrains.kotlin.GeneratedDeclarationKey
import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.declarations.IrConstructor
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction
import org.jetbrains.kotlin.ir.expressions.IrBody
import org.jetbrains.kotlin.ir.expressions.IrConstKind
import org.jetbrains.kotlin.ir.expressions.impl.IrConstImpl
import org.jetbrains.kotlin.ir.expressions.impl.IrReturnImpl
import org.jetbrains.kotlin.ir.visitors.acceptChildrenVoid

class ShallowSizeBodyGenerator(pluginContext: IrPluginContext) : AbstractTransformerForGenerator(pluginContext) {
    override fun interestedIn(key: GeneratedDeclarationKey) = true

    override fun generateBodyForFunction(function: IrSimpleFunction, key: GeneratedDeclarationKey): IrBody {
        messageCollector?.log("Started IR: ${function.name.asString()}")
        require(function.name == ShallowSizeGenerator.FOO_ID.callableName)
        val const = IrConstImpl(-1, -1, irBuiltIns.intType, IrConstKind.Int, value = 0)
        val returnStatement = IrReturnImpl(-1, -1, irBuiltIns.nothingType, function.symbol, const)
        return irFactory.createBlockBody(-1, -1, listOf(returnStatement))
    }

    override fun generateBodyForConstructor(constructor: IrConstructor, key: GeneratedDeclarationKey): IrBody? {
        return generateBodyForDefaultConstructor(constructor)
    }
}

class ShallowSizeIrGenerationExtension: IrGenerationExtension {
    override fun generate(moduleFragment: IrModuleFragment, pluginContext: IrPluginContext) {
        messageCollector?.log("Generating!")
        moduleFragment.acceptChildrenVoid(ShallowSizeBodyGenerator(pluginContext))
    }

}
