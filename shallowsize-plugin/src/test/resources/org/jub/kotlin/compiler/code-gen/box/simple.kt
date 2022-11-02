package foo.bar

class Aboba {
    fun a() = 0
}

fun box(): Int {
    val a = Aboba()
    return (if (a.shallowSize() == 0) {
        1
    } else {
        0
    })
}

