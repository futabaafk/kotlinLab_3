//Task 8 — Graded assessment hierarchy
//Domain for a course module:
//
//open class Assessment(val title: String, val maxPoints: Int)
//open fun scoreTowardGrade(raw: Int): Double — default: (raw.coerceIn(0, maxPoints)).toDouble() / maxPoints
//class Quiz(title, maxPoints, val penaltyIfLate: Double) : Assessment(...)
//Override scoring: apply parent ratio, then multiply by (1.0 - penaltyIfLate) with penaltyIfLate in 0.0..0.5
//Use super.scoreTowardGrade(raw) inside the override
//class Project(title, maxPoints, val bonusCap: Double) : Assessment(...)
//If raw > maxPoints,
//allow up to maxPoints * (1.0 + bonusCap)
//before normalising to a ratio that may exceed 1.0 but not 1.0 + bonusCap
//Document your formula in a short comment;
//must call super at least once or justify in a comment why the parent formula is fully replaced
//(prefer calling super on the coerced raw portion)
//In main, put quizzes/projects in List<Assessment>, print each title and computed score for a fixed raw.

open class Assessment(val title: String, val maxPoints: Int) {
    init {
        require(title.isNotBlank()) {
            "title can't be blank"
        }
        require(maxPoints > 0) {
            "maxPoints must be positive"
        }
    }

    open fun scoreTowardGrade(raw: Int): Double =
        (raw.coerceIn(0, maxPoints)).toDouble() / maxPoints
}

class Quiz(title: String, maxPoints: Int, val penaltyIfLate: Double) : Assessment(title, maxPoints) {
    init {
        require(penaltyIfLate in 0.0..0.5) {
            "penaltyIfLate must be in 0.0..0.5"
        }
    }

    override fun scoreTowardGrade(raw: Int): Double =
        super.scoreTowardGrade(raw) * (1.0 - penaltyIfLate)
}

class Project(title: String, maxPoints: Int, val bonusCap: Double) : Assessment(title, maxPoints) {
    init {
        require(bonusCap.isFinite() && bonusCap >= 0.0) {
            "bonusCap must be >= 0"
        }
    }


    override fun scoreTowardGrade(raw: Int): Double {
        val base = super.scoreTowardGrade(raw)
        val extraPoints = (raw - maxPoints).coerceAtLeast(0).toDouble()
        val cappedExtra = extraPoints.coerceAtMost(maxPoints * bonusCap)
        return base + cappedExtra / maxPoints
    }
}

fun main() {
    val items: List<Assessment> = listOf(
        Assessment("Homework", 100),
        Quiz("Quiz 1", 100, 0.1),
        Project("Final project", 100, 0.2)
    )

    for (raw in listOf(90, 110)) {
        for (a in items) {
            println("${a.title} (raw=$raw): ${"%.2f".format(a.scoreTowardGrade(raw))}")
        }
        println()
    }

    // Quiz("Q", 100, 0.7)
    // Assessment("", 100)
}