package org.jub.kotlin.compiler.`code-gen`.box

data class Nested(val a: Int){
    data class Nested2(val B: Int)
    val c = Nested2(a)
}

fun box3(): Int {
    val a = Nested(1)
    return a.shallowSize() + a.c.shallowSize()
}