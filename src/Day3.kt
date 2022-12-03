const val day = 3

fun main() {

    fun Char.toValue() = if (isUpperCase()) code - 38 else code - 96

    fun part1(input: List<String>): Int = input.sumOf { ruck ->
        with(ruck) {
            val p1 = substring(0 until length / 2)
            val p2 = substring(length / 2 until length)

            p1.first { p2.contains(it) }.toValue()
        }
    }

    /**
     * Group 1 from test (group 2 is remaining)
     * vJrwpWtwJgWrhcsFMMfFFhFp
     * jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
     * PmmdzqPrVvPwwTWBwg
     *
     * GP 1 => r (18)
     * GP 2 => Z (52)
     * ---
     * 70
     */

    fun part2(input: List<String>): Int = input
        .chunked(3)
        .sumOf { group ->
            group[0]
                .first { c -> group[1].contains(c) && group[2].contains(c) }
                .toValue()
        }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day${day}_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day$day")
    println(part1(input))
    println(part2(input))
}