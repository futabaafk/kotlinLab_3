//Task 4 — Payroll with super (employee family)
//Port the lecture idea into working code:
//
//open class Employee(val name: String, val baseSalary: Int)
//open fun pay(): Int = baseSalary
//open fun describe(): String = "$name: ${pay()}"
//class Accountant(name, baseSalary, val bonusPercent: Int) : Employee(...)
//pay() = super.pay() plus percent bonus (integer arithmetic OK)
//describe() may call super.describe() and prefix "Accountant "
//class Intern(name, baseSalary, val mentor: String) : Employee(...)
//pay() = super.pay() / 2 (still call super.pay(), do not duplicate the field read only)
//Build a List<Employee> with all three kinds; print each describe() and the sum of pay().
//
//Constraints: bonusPercent in 0..100; salaries / names validated.

open class Employee(val name: String, val baseSalary: Int) {
    init {
        require(name.isNotBlank()) {
            "name can't be blank"
        }
        require(baseSalary >= 0) {
            "baseSalary can't be negative"
        }
    }

    open fun pay(): Int = baseSalary
    open fun describe(): String = "$name: ${pay()}"
}

class Accountant(name: String, baseSalary: Int, val bonusPercent: Int) : Employee(name, baseSalary) {
    init {
        require(bonusPercent in 0..100) {
            "bonusPercent must be in 0..100"
        }
    }

    override fun pay(): Int = super.pay() + super.pay() * bonusPercent / 100
    override fun describe(): String = "Accountant " + super.describe()
}

class Intern(name: String, baseSalary: Int, val mentor: String) : Employee(name, baseSalary) {
    init {
        require(mentor.isNotBlank()) {
            "mentor can't be blank"
        }
    }

    override fun pay(): Int = super.pay() / 2
}

fun main() {
    val staff: List<Employee> = listOf(
        Employee("Anna", 1000),
        Accountant("Bob", 2000, 10),
        Intern("Carl", 800, "Bob")
    )

    var total = 0
    for (e in staff) {
        println(e.describe())
        total += e.pay()
    }
    println("Total pay: $total")

    // Accountant("Dan", 1000, 150)   ошибка
    // Employee("", 1000) ошибка
}