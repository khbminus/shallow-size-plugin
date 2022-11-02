package foo.bar

class Aboba {
    fun a() = 0
}

data class A(val a: Int)

fun box(): Int {
    val a = A(2)
    return (if (a.shallowSize() == 0) {
        1
    } else {
        0
    })
}

