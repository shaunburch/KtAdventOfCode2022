fun main() {
    // *slaps roof of function*
    // It ain't elf-egant, but it'll get you there
    fun getInventories(input: List<String>): List<Int> = buildList {
        val iterator = input.map { it.toIntOrNull() }.iterator()
        with(iterator) {
            var sum = 0
            do {
                val next = next()

                if (next != null) {
                    sum += next
                } else {
                    add(sum)
                    sum = 0
                }
            } while (hasNext())
            add(sum)
        }
    }

    fun part1(input: List<String>): Int {
        return getInventories(input).max()
    }

    fun part2(input: List<String>): Int {
        return getInventories(input).sorted().takeLast(3).sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 24000)
    check(part2(testInput) == 45000)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
