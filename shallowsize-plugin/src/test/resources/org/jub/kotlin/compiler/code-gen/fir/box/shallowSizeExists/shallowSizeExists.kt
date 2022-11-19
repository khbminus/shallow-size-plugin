package org.jub.kotlin.compiler.box

data class ShallowSizeExists(val a: Int) {
    fun <!FUNCTION_EXISTS!>shallowSize<!>(): String = "Huh"
}