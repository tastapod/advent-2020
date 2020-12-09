package day4

fun parsePassportBatch(input: String): List<String> =
    Regex("^$", RegexOption.MULTILINE).split(input)

fun parsePassport(passportText: String): Map<String, String> =
    emptyMap<String, String>().plus( // convert sequence of Pairs to Map
        Regex("""(\w+):(\S+)""").findAll(passportText).map {
            val (key, value) = it.destructured
            Pair(key, value)
        })

val MANDATORY_FIELDS = listOf(
    "byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"
)

fun isValidPassport(passport: Map<String, String>) = passport.keys.containsAll(MANDATORY_FIELDS)
