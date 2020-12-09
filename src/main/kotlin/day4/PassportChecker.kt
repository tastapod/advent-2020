package day4

typealias Validator = (String) -> Boolean

class Rule(val key: String, val isValid: Validator) {
    fun isValid(passport: Map<String, String>) = passport[key]?.let { isValid(it) } ?: false
}

class PassportChecker {
    private val rules = mutableSetOf<Rule>()

    fun withRule(rule: Rule) = apply {
        rules.add(rule)
    }

    fun isValid(passport: Map<String, String>) = rules.all { it.isValid(passport) }

}
