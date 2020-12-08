package day1

fun findAddends(total: Int, numbers: Set<Int>, numRequired: Int): Set<Int>? {
    return findRemainingAddends(total, numbers, numRequired, setOf())
}

fun findRemainingAddends(total: Int, numbers: Set<Int>, numRequired: Int, acc: Set<Int>): Set<Int>? {
    if (numRequired == 1) {
        return if (total in numbers) acc + total else null
    }
    else {
        numbers.forEach {
            val result = (findRemainingAddends(total - it, numbers - it, numRequired - 1, acc + it))
            if (result != null) return result
        }
        return null
    }
}
