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

data class MinMaxRule(val ch: Char, val min: Int, val max: Int) : Rule() {
    override fun isValid(password: String): Boolean {
        return password.groupBy { it }.getOrDefault(ch, emptyList()).size in min..max
    }
}

data class EitherOrRule(val char: Char, val pos1: Int, val pos2: Int) : Rule() {
    override fun isValid(password: String): Boolean {
        return (password[pos1 - 1] == char) xor (password[pos2 - 1] == char)
    }
}
