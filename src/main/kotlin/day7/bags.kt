package day7

import java.lang.Integer.parseInt

data class BagRule(val colour: String, val contents: Set<BagSpec> = emptySet())
data class BagSpec(val count: Int, val colour: String)

fun parseBagRule(line: String): BagRule {
    // dotted black bags contain no other bags.
    val (colour, contents) = line.split(" bags contain ")
    return BagRule(
        colour = colour,
        contents = Regex("""(\d+) (\w+ \w+) bags?[, |.]""")
            .findAll(contents)
            .map {
                val (count, col) = it.destructured
                BagSpec(parseInt(count), col)
            }.toSet()
    )
}

fun parseBagRulesByContainer(rulesText: String) =
    rulesText.lines()
        .map(::parseBagRule)
        .fold(mapOf<String, Set<String>>()) { result, rule ->
            rule.contents.fold(result) { acc, bagSpec ->
                acc + Pair(bagSpec.colour, acc.getOrDefault(bagSpec.colour, emptySet()) + rule.colour)
            }
        }

fun parseBagRulesByContents(rulesText: String) =
    rulesText.lines()
        .map(::parseBagRule)
        .fold(mapOf<String, Set<BagSpec>>()) { acc, rule -> acc + Pair(rule.colour, rule.contents) }

fun possibleContainers(bagsByContainer: Map<String, Set<String>>, colour: String): Set<String> {
    fun recurse(result: Set<String>, containers: Set<String>): Set<String> =
        if (containers.isEmpty()) {
            result
        } else {
            containers.fold(result) { acc, containerColour ->
                recurse(
                    acc + containerColour,
                    bagsByContainer.getOrDefault(containerColour, emptySet())
                )
            }
        }
    return recurse(emptySet(), bagsByContainer.getOrDefault(colour, emptySet()))
}

fun countContents(bagsByContents: Map<String, Set<BagSpec>>, colour: String): Int {
    val specs = bagsByContents.getOrDefault(colour, emptySet())
    return if (specs.isEmpty()) {
        0
    } else {
        specs.fold(0) { acc, it -> acc + it.count * (1 + countContents(bagsByContents, it.colour)) }
    }
}