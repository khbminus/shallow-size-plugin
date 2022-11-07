package org.jub.kotlin.compiler.`code-gen`.box

class A

fun box(): Int {
    val a = A()
    return a.shallowSize()
}