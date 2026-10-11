//Task 3 — Vehicle hierarchy (open / override)
//open class Vehicle(val brand: String) {
//    open fun info(): String
//    open fun maxSpeedKmh(): Int
//}
//class Car(brand, val doors: Int) : Vehicle(brand) — override both methods
//class Bike(brand, val gears: Int) : Vehicle(brand) — override both
//info() should mention brand and subclass-specific fields.
//In main, print info() and maxSpeedKmh() for one of each.
//
//Edge cases: doors >= 2, gears >= 1, non-blank brand.

open class Vehicle(val brand: String) {
    init {
        require(brand.isNotBlank()) {
            "brand can't be empty"
        }
    }

    open fun info(): String = "Vehicle $brand"
    open fun maxSpeedKmh(): Int = 0
}

class Car(brand: String, val doors: Int) : Vehicle(brand) {
    init {
        require(doors >= 2) {
            "doors cant be less than 2"
        }
    }

    override fun info(): String = "Car $brand, doors=$doors"
    override fun maxSpeedKmh(): Int = 180
}

class Bike(brand: String, val gears: Int) : Vehicle(brand) {
    init {
        require(gears >= 1) {
            "gears must be at least 1"
        }
    }

    override fun info(): String = "Bike $brand, gears=$gears"
    override fun maxSpeedKmh(): Int = 40

}





//    Task 6 — Upcast catalogue
//    Using Task 3’s Vehicle hierarchy (or an equivalent you rewrite in-file):
//
//    Create val v: Vehicle = Car("Volvo", 4) (explicit upcast).
//    Call v.info() and confirm the Car override runs.
//    Write fun printFleet(fleet: List<Vehicle>) that prints each info() on its own line.
//    Sample shape
//
//    Car Volvo, doors=4
//    Bike Trek, gears=18
fun printFleet(fleet: List<Vehicle>) {
    for (v in fleet) {
        println(v.info())
    }
}


//Task 7 — Smart cast and safe downcast
//Continue with Vehicle / Car / Bike:
//
//fun doorsOrZero(v: Vehicle): Int
//If v is Car, return v.doors via smart cast (no as).
//Otherwise return 0.
//Also write a demo that:
//
//Does val c = (Bike("X", 3) as? Car) and prints null / a clear message.
//Shows that as Car on a Bike would throw — comment it out;
//do not leave a crashing line in the submitted main.

fun doorsOrZero(v: Vehicle): Int {
    if (v is Car) {
        return v.doors
    }
    return 0
}



fun main() {

    val car = Car("Volvo", 4)
    val bike = Bike("Trek", 18)

    println(car.info())
    println(car.maxSpeedKmh())
    println(bike.info())
    println(bike.maxSpeedKmh())

    // Car("Volvo", 1)    // ошьбка дверей должно быть больше или ровно 2



    // Task 6
    val v: Vehicle = Car("Volvo", 4)   //  upcast Car хранится в переменной типа vehile
    println(v.info())                  // сработает версия из Car

    printFleet(listOf(Car("Volvo", 4), Bike("Trek", 18)))



    // Task 7
    println(doorsOrZero(Car("Volvo", 4)))   // 4
    println(doorsOrZero(Bike("Trek", 18)))  // 0

    val c = (Bike("X", 3) as? Car)          // Bike не Car, вернётся null
    println(c)                              // null
    if (c == null) {
        println("Bike cannot be cast to Car, result is null")
    }

    // val bad = Bike("X", 3) as Car        // вывелбы ClassCastчтоТоТам поэтому закомментировано


}




