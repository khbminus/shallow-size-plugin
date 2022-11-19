package org.jub.kotlin.compiler.`code-gen`.fir.box.silly

data class MoreSilly(val a: Double) {
    fun <!FUNCTION_EXISTS!>shallowSize<!>() = a
}