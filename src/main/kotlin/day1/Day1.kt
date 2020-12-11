package day1

fun findAddends(total: Long, numbers: Collection<Long>, numRequired: Int): Collection<Long> {
    fun findRemainingAddends(total: Long, numbers: Collection<Long>, numRequired: Int, acc: Collection<Long>): Collection<Long> {
        if (numRequired == 1) {
            return if (total in numbers) acc + total else emptySet()
        }
        else {
            numbers.forEach {
                val result = (findRemainingAddends(total - it, numbers - it, numRequired - 1, acc + it))
                if (!result.isEmpty()) return result
            }
            return emptySet()
        }
    }
    return findRemainingAddends(total, numbers, numRequired, setOf())
}

