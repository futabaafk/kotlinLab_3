//Task 1 — Sensor with primary constructor
//Implement class Sensor(val id: String, var reading: Double):
//init { require(id.isNotBlank()) }
//fun update(reading: Double) — reject non-finite values
//override fun toString(): String → Sensor(id=…, reading=…)
//In main, create two sensors, update one, print both.
//Edge case: blank id must fail construction.


class Sensor(val id: String, var reading: Double){
    init {
        require(id.isNotBlank()) {
            "id can't be empty"
        }
    }

    fun update(reading: Double){
        require(reading.isFinite()) {
            "reading must be finite"
        }
        this.reading = reading
    }

    override fun toString(): String {
        return "Sensor(id=$id, reading=$reading)"
    }
}

fun main(){
    val s1 = Sensor("first", 1000.0)
    val s2 = Sensor("second", 1000.0)

    s1.update(123.45)
    println(s1)
    println(s2)
}

