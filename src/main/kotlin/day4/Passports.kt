package day4

fun parsePassportBatch(input: String): List<String> =
    Regex("^$", RegexOption.MULTILINE).split(input)

fun parsePassport(passportText: String): Map<String, String> =
    Regex("""(\w+):(\S+)""").findAll(passportText).fold(mutableMapOf()) {
        acc: MutableMap<String, String>, result: MatchResult ->
        val (key, value) = result.destructured
        acc[key] = value
        acc
    }

val MANDATORY_FIELDS = listOf(
    "byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"
)

fun isValidPassport(passport: Map<String, String>) = passport.keys.containsAll(MANDATORY_FIELDS)
