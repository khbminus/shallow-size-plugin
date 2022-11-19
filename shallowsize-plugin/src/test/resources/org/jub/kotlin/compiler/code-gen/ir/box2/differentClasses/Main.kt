package org.jub.kotlin.compiler.`code-gen`.box.fir.differentClasses

data class A(private val a: Int) {
    fun f() = "String"
}
class B

enum class C {
    A, B, C
}

@JvmInline
value class D(val s: String)

class E {
    data class F(val x: Double)
//    inner class G
}

