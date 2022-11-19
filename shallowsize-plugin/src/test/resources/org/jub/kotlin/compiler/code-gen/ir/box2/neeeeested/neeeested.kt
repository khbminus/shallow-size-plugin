package org.jub.kotlin.compiler.`code-gen`.box

data class Neeeested(val a: Int){
    data class Neeeeested2(val B: Int)
    val c = Neeeeested2(a)
}

fun box3(): Int {
    val a = Neeeested(1)
    return a.shallowSize() + a.c.shallowSize()
}