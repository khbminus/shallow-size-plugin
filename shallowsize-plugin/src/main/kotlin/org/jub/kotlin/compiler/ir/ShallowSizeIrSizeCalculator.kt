package org.jub.kotlin.compiler.ir

import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.types.*
import org.jetbrains.kotlin.ir.util.properties
import org.jetbrains.kotlin.ir.visitors.IrElementVisitor

class ShallowSizeIrSizeCalculator : IrElementVisitor<Int, Nothing?> {
    override fun visitElement(element: IrElement, data: Nothing?) = TODO("not implemented")

    private fun IrType.size() = when {
        isByte() -> Byte.SIZE_BYTES
        isUByte() -> UByte.SIZE_BYTES
        isBoolean() -> 1
        isChar() -> Char.SIZE_BYTES
        isDouble() -> Double.SIZE_BYTES
        isFloat() -> Float.SIZE_BYTES
        isInt() -> Int.SIZE_BYTES
        isLong() -> Long.SIZE_BYTES
        isShort() -> Short.SIZE_BYTES
        isUInt() -> UInt.SIZE_BYTES
        isULong() -> ULong.SIZE_BYTES
        isUShort() -> UShort.SIZE_BYTES
        else -> REFERENCE_SIZE  // It is not true, because x32 systems exists
    }

    override fun visitClass(declaration: IrClass, data: Nothing?): Int = declaration.properties.sumOf {
            it.backingField?.type?.size() ?: 0
        }
    companion object {
        private const val REFERENCE_SIZE = 8
    }
}
