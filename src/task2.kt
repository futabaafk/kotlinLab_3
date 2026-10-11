//Task 2 — Encapsulated counter
//class Counter(start: Int = 0): Store value in a private var
//fun value(): Int
//fun inc(step: Int = 1) / fun reset()
//Reject step <= 0 on inc
//Demonstrate default construction and inc(2) from main.
//
//Hint: Header param start need not be a property if you assign a private field.

class Counter(start: Int = 0) {
    private var count: Int = start

    fun value(): Int = count

    fun inc(step: Int = 1) {
        require(step > 0) {
            "step must be more than 0"
        }
        count += step
    }

    fun reset() {
        count = 0
    }
}

fun main() {
    val c = Counter()
    println(c.value())

    c.inc(2)
    println(c.value())

    c.inc()
    println(c.value())

    c.reset()
    println(c.value())

    val c2 = Counter(10)
    println(c2.value())

}