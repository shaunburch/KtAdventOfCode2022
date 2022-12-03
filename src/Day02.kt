import Result.*
import Shape.*
import java.lang.IllegalArgumentException


// A, X (1) Rock
// B, Y (2) Paper
// C, Z (3) Scissors
enum class Shape(val points: Int) {
    Rock(1),
    Paper(2),
    Scissors(3);
}

// 0 lose
// 3 draw
// 6 win
enum class Result(val points: Int) {
    Lose(0), // X
    Draw(3), // Y
    Win(6) // Z
}

fun String.toResult(): Result = when (this) {
    "X" -> Lose
    "Y" -> Draw
    "Z" -> Win
    else -> throw IllegalArgumentException("Unknown result for: $this")
}

fun String.toShape(): Shape = when (this) {
    "A", "X" -> Rock
    "B", "Y" -> Paper
    "C", "Z" -> Scissors
    else -> throw IllegalArgumentException("Unknown shape for: $this")
}

fun Shape.vs(other: Shape): Result = when {
    this == other -> Draw

    (this == Rock && other == Scissors)
            || (this == Scissors && other == Paper)
            || (this == Paper && other == Rock) -> Win

    else -> Lose
}

fun Result.toPlay(their: Shape): Shape = when (this) {
    Draw -> their

    Win -> when (their) {
        Rock -> Paper
        Paper -> Scissors
        Scissors -> Rock
    }

    Lose -> when (their) {
        Rock -> Scissors
        Paper -> Rock
        Scissors -> Paper
    }
}

fun main() {
    fun part1(input: List<String>): Int = input.sumOf { round ->
        val (them, me) = round.split(" ").map(String::toShape)
        val result = me.vs(them)

        // Total Score = sum of each round
        // Score of each round = Shape selected + Result
        me.points + result.points
    }

    // X – lose
    // Y – draw
    // Z – win
    fun part2(input: List<String>): Int = input.sumOf { round ->
        val (t, o) = round.split(" ")
        val them = t.toShape()
        val outcome = o.toResult()
        val myPlay = outcome.toPlay(them)

        outcome.points + myPlay.points
    }

    val testInput = readInput("Day02_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 12)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}