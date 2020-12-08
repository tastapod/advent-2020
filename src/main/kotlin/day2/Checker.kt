package day2

class Checker {
    lateinit var rule: MinMaxRule

    fun setRule(char: Char, min: Int, max: Int): Checker {
        rule = MinMaxRule(char, min, max)
        return this
    }

    fun isValid(password: String): Boolean {
        return rule.isValid(password)
    }
}

data class MinMaxRule(val char: Char, val min: Int, val max: Int) {
    fun isValid(password: String): Boolean {
        val counts = countLetters(password)
        return counts.getOrDefault(char, 0) in min..max
    }
}

private fun countLetters(password: String): Map<Char, Int> {
    return password.fold(mutableMapOf()) {
            acc: MutableMap<Char, Int>, it: Char ->
        acc[it] = if (it in acc) acc[it]!! + 1 else 1
        acc
    }
}
