//Task 5 — Polymorphic shipping costs
//open class Parcel(val id: String, val weightKg: Double)
//class ExpressParcel(...) : Parcel(...)
//class EconomyParcel(...) : Parcel(...)
//Base: open fun shippingCost(): Double — e.g. weightKg * 2.0
//Express: override to super.shippingCost() + 15.0 (surcharge on top of base formula)
//Economy: override to super.shippingCost() * 0.8
//Write fun totalShipping(parcels: List<Parcel>): Double.
//In main, mix types in one list and print the total (2 decimal places is fine).

open class Parcel(val id: String, val weightKg: Double) {
    init {
        require(id.isNotBlank()) {
            "id can't be blank"
        }
        require(weightKg.isFinite() && weightKg > 0) {
            "weightKg must be positive"
        }
    }

    open fun shippingCost(): Double = weightKg * 2.0
}

class ExpressParcel(id: String, weightKg: Double) : Parcel(id, weightKg) {
    override fun shippingCost(): Double = super.shippingCost() + 15.0
}

class EconomyParcel(id: String, weightKg: Double) : Parcel(id, weightKg) {
    override fun shippingCost(): Double = super.shippingCost() * 0.8
}

fun totalShipping(parcels: List<Parcel>): Double {
    var total = 0.0
    for (p in parcels) {
        total += p.shippingCost()
    }
    return total
}

fun main() {
    val parcels: List<Parcel> = listOf(
        Parcel("P-1", 10.0),
        ExpressParcel("E-1", 5.0),
        EconomyParcel("C-1", 20.0)
    )

    for (p in parcels) {
        println("${p.id}: ${"%.2f".format(p.shippingCost())}")
    }
    println("Total: ${"%.2f".format(totalShipping(parcels))}")

    // Parcel("", 5.0)    ошбика
    // Parcel("P-2", 0.0) ошбика
}