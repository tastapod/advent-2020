package day2

class PasswordChecker {
    lateinit var rule: Rule

    fun setMinMaxRule(char: Char, min: Int, max: Int) = apply {
        rule = MinMaxRule(char, min, max)
    }

    fun isValid(password: String): Boolean {
        return rule.isValid(password)
    }

    fun setEitherOrRule(char: Char, pos1: Int, pos2: Int) = apply {
        rule = EitherOrRule(char, pos1, pos2)
    }
}

abstract class Rule {
    abstract fun isValid(password: String): Boolean
}

data class MinMaxRule(val char: Char, val min: Int, val max: Int) : Rule() {
    override fun isValid(password: String): Boolean {
        val counts = countLetters(password)
        return counts.getOrDefault(char, 0) in min..max
    }
}

data class EitherOrRule(val char: Char, val pos1: Int, val pos2: Int) : Rule() {
    override fun isValid(password: String): Boolean {
        return (password[pos1-1] == char) xor (password[pos2-1] == char)
    }
}

private fun countLetters(password: String): Map<Char, Int> {
    return password.fold(mutableMapOf()) {
            acc: MutableMap<Char, Int>, it: Char ->
        acc[it] = if (it in acc) acc[it]!! + 1 else 1
        acc
    }
}
