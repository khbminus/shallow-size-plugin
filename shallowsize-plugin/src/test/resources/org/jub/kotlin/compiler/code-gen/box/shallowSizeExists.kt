package org.jub.kotlin.compiler.box

data class ShallowSizeExists(val a: Int) {
    fun shallowSize(): String = "Huh"
}