package day7

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day7Test {
    @Test
    fun `parses a line`() {
        assertEquals(
            BagRule("dotted black"),
            parseBagRule("dotted black bags contain no other bags.")
        )

        assertEquals(
            BagRule("bright white", setOf(BagSpec(1, "shiny gold"))),
            parseBagRule("bright white bags contain 1 shiny gold bag.")
        )

        assertEquals(
            BagRule("dark orange", setOf(BagSpec(3, "bright white"), BagSpec(4, "muted yellow"))),
            parseBagRule("dark orange bags contain 3 bright white bags, 4 muted yellow bags.")
        )
    }


    @Test
    fun `finds containing bags`() {
        val rulesText = """
            light red bags contain 1 bright white bag, 2 muted yellow bags.
            dark orange bags contain 3 bright white bags, 4 muted yellow bags.
            bright white bags contain 1 shiny gold bag.
            muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
            shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
            dark olive bags contain 3 faded blue bags, 4 dotted black bags.
            vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
            faded blue bags contain no other bags.
            dotted black bags contain no other bags.
        """.trimIndent()

        val bagsByContainer = parseBagRulesByContainer(rulesText)
        assertEquals(4, possibleContainers(bagsByContainer, "shiny gold").size)
    }

    @Test
    fun `counts contained bags`() {
        val rulesText = """
            shiny gold bags contain 2 dark red bags.
            dark red bags contain 2 dark orange bags.
            dark orange bags contain 2 dark yellow bags.
            dark yellow bags contain 2 dark green bags.
            dark green bags contain 2 dark blue bags.
            dark blue bags contain 2 dark violet bags.
            dark violet bags contain no other bags.
        """.trimIndent()

        val bagsWithContents = parseBagRulesByContents(rulesText)
        assertEquals(126, countContents(bagsWithContents, "shiny gold"))
    }
}

