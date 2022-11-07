package org.jub.kotlin.compiler.ir

import org.jetbrains.kotlin.GeneratedDeclarationKey
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.declarations.*
import org.jetbrains.kotlin.ir.expressions.IrBody
import org.jetbrains.kotlin.ir.expressions.IrConstKind
import org.jetbrains.kotlin.ir.expressions.impl.IrConstImpl
import org.jetbrains.kotlin.ir.expressions.impl.IrReturnImpl
import org.jetbrains.kotlin.ir.util.kotlinFqName
import org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid
import org.jetbrains.kotlin.ir.visitors.acceptChildrenVoid
import org.jub.kotlin.compiler.fir.ShallowSizeGenerator
import org.jub.kotlin.compiler.log
import org.jub.kotlin.compiler.messageCollector

class ShallowSizeBodyGenerator(pluginContext: IrPluginContext) : IrElementVisitorVoid {
    private val irFactory = pluginContext.irFactory
    private val irBuiltIns = pluginContext.irBuiltIns
    private fun isInterestedIn(key: GeneratedDeclarationKey) = key == ShallowSizeGenerator.Key

    private fun generateBodyForFunction(function: IrSimpleFunction): IrBody {
        require(function.name == ShallowSizeGenerator.FUNCTION_NAME)
        val size = function.parent.accept(ShallowSizeIrSizeCalculator(), null)
        messageCollector?.log("Ir transform of ${function.name} from ${function.parent.kotlinFqName}, size=$size")
        val const = IrConstImpl(
            -1,
            -1,
            irBuiltIns.intType,
            IrConstKind.Int,
            value = size
        )
        val returnStatement = IrReturnImpl(-1, -1, irBuiltIns.nothingType, function.symbol, const)
        return irFactory.createBlockBody(-1, -1, listOf(returnStatement))
    }

    override fun visitElement(element: IrElement) {
        when (element) {
            is IrDeclaration,
            is IrFile,
            is IrModuleFragment -> element.acceptChildrenVoid(this)

            else -> {}
        }
    }

    override fun visitSimpleFunction(declaration: IrSimpleFunction) {
        val origin = declaration.origin
        if (origin !is IrDeclarationOrigin.GeneratedByPlugin || !isInterestedIn(origin.pluginKey)) return
        require(declaration.body == null)
        messageCollector?.log("Found required function: ${declaration.name}")
        declaration.body = generateBodyForFunction(declaration)
    }
}

