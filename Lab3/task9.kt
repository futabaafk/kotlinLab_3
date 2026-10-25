//Task 9 — Notification channels (polymorphism + visibility)
//open class Notification(private val payload: String) {
//    protected fun body(): String = payload
//    open fun render(): String = body()
//}
//class EmailNotification(payload: String, val to: String) : Notification(payload)
//class SmsNotification(payload: String, val phone: String) : Notification(payload)
//Subclasses override render() to include channel metadata and body() / equivalent access to payload (they can see protected body()).
//Outsiders must not read payload directly.
//fun sendAll(items: List<Notification>) prints each render().
//Hint: If you add a public payload property, you have broken the task.

open class Notification(private val payload: String) {
    protected fun body(): String = payload
    open fun render(): String = body()
}

class EmailNotification(payload: String, val to: String) : Notification(payload) {
    init {
        require(to.isNotBlank()) {
            "to can't be blank"
        }
    }

    override fun render(): String = "[Email to $to] ${body()}"
}

class SmsNotification(payload: String, val phone: String) : Notification(payload) {
    init {
        require(phone.isNotBlank()) {
            "phone can't be blank"
        }
    }

    override fun render(): String = "[SMS to $phone] ${body()}"
}

fun sendAll(items: List<Notification>) {
    for (n in items) {
        println(n.render())
    }
}

fun main() {
    val items: List<Notification> = listOf(
        Notification("System started"),
        EmailNotification("Your order has shipped", "anna@example.com"),
        SmsNotification("Your code is 4821", "+996555123456")
    )

    sendAll(items)

    // println(items[0].payload)   ошибка payload приватный
    // println(items[0].body())  ошибка body() защищённый
}