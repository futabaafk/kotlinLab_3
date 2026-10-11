//Task 10 — Zoo ticketing (combine hierarchy + casts)
//open class Ticket(val code: String, val basePrice: Int)
//class AdultTicket(...) : Ticket(...)
//class ChildTicket(..., val guardianCode: String) : Ticket(...)
//class VipTicket(..., val loungeAccess: Boolean) : Ticket(...)
//Rules:
//
//open fun price(): Int = basePrice on Ticket
//Child: price() = super.price() / 2
//VIP: price() = super.price() + if (loungeAccess) 50 else 20
//Write:
//
//fun checkout(tickets: List<Ticket>): Int
//fun childGuardianCodes(tickets: List<Ticket>): List<String>
//childGuardianCodes keeps order of appearance; use is + smart cast (or filterIsInstance only if you also show one explicit is loop elsewhere in the file).
//
//In main, build a mixed list, print checkout total and guardian codes.
//
//Sample idea
//
//total=230
//guardians=G-1, G-9

open class Ticket(val code: String, val basePrice: Int) {
    init {
        require(code.isNotBlank()) {
            "code can't be blank"
        }
        require(basePrice >= 0) {
            "basePrice can't be negative"
        }
    }

    open fun price(): Int = basePrice
}

class AdultTicket(code: String, basePrice: Int) : Ticket(code, basePrice)

class ChildTicket(code: String, basePrice: Int, val guardianCode: String) : Ticket(code, basePrice) {
    init {
        require(guardianCode.isNotBlank()) {
            "guardianCode can't be blank"
        }
    }

    override fun price(): Int = super.price() / 2
}

class VipTicket(code: String, basePrice: Int, val loungeAccess: Boolean) : Ticket(code, basePrice) {
    override fun price(): Int = super.price() + if (loungeAccess) 50 else 20
}

fun checkout(tickets: List<Ticket>): Int {
    var total = 0
    for (t in tickets) {
        total += t.price()
    }
    return total
}

fun childGuardianCodes(tickets: List<Ticket>): List<String> {
    val result = mutableListOf<String>()
    for (t in tickets) {
        if (t is ChildTicket) {
            result.add(t.guardianCode)   // smart cast: внутри if t уже ChildTicket
        }
    }
    return result
}

fun main() {
    val tickets: List<Ticket> = listOf(
        AdultTicket("A-1", 50),
        ChildTicket("C-1", 50, "G-1"),
        VipTicket("V-1", 80, true),
        ChildTicket("C-2", 50, "G-9"),
        AdultTicket("A-2", 50)
    )

    println("total=${checkout(tickets)}")
    println("guardians=${childGuardianCodes(tickets).joinToString(", ")}")

    // ChildTicket("C-3", 50, "")  ошбика guardianCode can't be blank
}