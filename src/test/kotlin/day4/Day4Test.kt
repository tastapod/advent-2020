package day4

import org.junit.jupiter.api.Test
import java.lang.Integer.parseInt
import kotlin.test.assertEquals

class Day4Test {
    @Test
    fun `finds missing passport fields`() {
        val input = """
            ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
            byr:1937 iyr:2017 cid:147 hgt:183cm

            iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
            hcl:#cfa07d byr:1929

            hcl:#ae17e1 iyr:2013
            eyr:2024
            ecl:brn pid:760753108 byr:1931
            hgt:179cm

            hcl:#cfa07d eyr:2025 pid:166559648
            iyr:2011 ecl:brn hgt:59in
        """.trimIndent()

        val passports = parsePassportBatch(input)

        assertEquals(2, passports.map(::parsePassport).count(::isValidPassport))
    }

    @Test
    fun `validates passport using rules`() {
        // given
        val checker = PassportChecker().withRule(Rule("byr") {
            Regex("""^\d{4}$""").matches(it) && parseInt(it) in 1920..2002
        })

        assertEquals(true, checker.isValid(mapOf("byr" to "2000")))
        assertEquals(false, checker.isValid(mapOf("byr" to "hello")))
    }
}
