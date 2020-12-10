package day7

import java.lang.Integer.parseInt

data class BagRule(val colour: String, val contents: Set<BagSpec> = emptySet())
data class BagSpec(val count: Int, val color: String)

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
                acc + Pair(bagSpec.color, acc.getOrDefault(bagSpec.color, emptySet()) + rule.colour)
            }
        }

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
